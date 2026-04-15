# Backend - Fundacao de Autenticacao e Hardening de Build (2026-04-05)

## 1) Objetivo deste documento
Este documento resume, de forma cronologica e tecnica, as alteracoes aplicadas no backend (`Java/Payara`) para:

- desbloquear o build Maven no ambiente atual;
- consolidar a base de autenticacao no backend;
- retirar do frontend a responsabilidade de hashing e validacao de passwords;
- corrigir warnings/riscos de dependencias (Jackson);
- melhorar previsibilidade de dependencias e ferramentas de diagnostico;
- deixar a base preparada para integracao com o frontend Angular.

Foi mantida a estrategia incremental:
- sem refatoracao funcional agressiva;
- sem alterar o runtime Payara;
- sem reestruturar todo o backend legado de uma vez.

---

## 2) Contexto inicial
Sintomas e necessidades identificadas:

1. falha de build no frontend Docker (`npm ci`) - tratado em paralelo anteriormente;
2. no backend, apos instalar Maven, `mvn install` falhava;
3. warning/risco em dependencias Jackson;
4. necessidade de garantir base de autenticacao no backend;
5. necessidade de remover hashing e comparacao de passwords do frontend.

Estado tecnico relevante encontrado no backend:

- projeto Maven WAR em `CircPeticionario-WebApp`;
- uso de configuracoes antigas de compilador Maven (`endorseddirs`) nao compativeis com JDK 17;
- `maven-dependency-plugin` muito antigo (`2.6`), causando falha no `dependency:tree` com Maven 3.9;
- dependencia Jackson sem governance centralizada via BOM;
- necessidade de suportar autenticacao no backend de forma coerente com o estilo tecnico atual do projeto;
- passwords armazenadas na tabela `app_hist.utilizadores` em formato bcrypt.

---

## 3) Alteracoes realizadas

### 3.1 `CircPeticionario-WebApp/pom.xml`

#### A) Compatibilidade de compilacao em JDK moderno
- Removida a passagem de `endorseddirs` no `maven-compiler-plugin` (flag legada Java 8 que quebrava com JDK 17).
- Atualizado o plugin de compilacao para versao moderna e configurado com:
  - `<release>8</release>`

**Por que:**
- elimina erro `invalid flag .../target/endorsed`;
- compila para bytecode Java 8 de forma correta em JDK 17;
- reduz warning de bootstrap classpath.

#### B) Dependencia para bcrypt
- Adicionada dependencia:
  - `org.mindrot:jbcrypt:0.4`

**Por que:**
- habilita hashing e verificacao de password no backend;
- remove dependencia de hashing/comparacao no frontend.

#### C) Hardening Jackson + alinhamento de versoes
- Introduzida propriedade:
  - `jackson.version=2.18.6`
- Adicionado `dependencyManagement` com:
  - `com.fasterxml.jackson:jackson-bom:${jackson.version}`
- Dependencias Jackson explicitadas sem versao local (passam a herdar do BOM):
  - `jackson-databind`
  - `jackson-core`
  - `jackson-annotations`

**Por que:**
- mitiga risco de mismatch entre modulos Jackson;
- simplifica futuras atualizacoes (mudar apenas `jackson.version`);
- estabiliza resolucao transitive/compile/runtime.

#### D) Upgrade de plugin Maven para diagnostico
- Atualizado `maven-dependency-plugin`:
  - de `2.6` para `3.8.1`

**Por que:**
- `2.6` falhava com Maven 3.9 (`NoClassDefFoundError` / Aether antigo);
- desbloqueia `mvn dependency:tree` para auditoria de dependencias.

#### E) Dependencia para Lombok
- Adicionada dependencia de Lombok para reduzir boilerplate em DTOs e models simples da pasta `auth`.

**Por que:**
- reduzir getters/setters manuais em classes simples;
- manter services, repositories e resources explicitos;
- aplicar Lombok de forma controlada apenas onde traz beneficio real.

---

### 3.2 Fundacao da autenticacao na pasta `pt/ual/auth`

A autenticacao foi organizada com separacao por responsabilidade na pasta:

- `pt.ual.auth.dto`
- `pt.ual.auth.model`
- `pt.ual.auth.repository`
- `pt.ual.auth.service`
- `pt.ual.auth.resource`

Estrutura principal:

- `dto/`
  - `AuthLoginRequestDto.java`
  - `AuthUserDto.java`
- `model/`
  - `AuthUser.java`
- `repository/`
  - `AuthRepository.java`
- `service/`
  - `AuthService.java`
  - `PasswordService.java`
- `resource/`
  - `AuthResource.java`

**Objetivo:**
- separar transporte de dados, modelo interno, acesso a dados, logica de autenticacao e exposicao HTTP;
- retirar a autenticacao do frontend;
- manter consistencia com o estilo tecnico atual do projeto.

---

## 4) Detalhe funcional das classes

### 4.1 `AuthLoginRequestDto`
Representa o payload de entrada do login.

Contem:
- `username`
- `password`

Anotacoes utilizadas:
- `@Getter`
- `@Setter`
- `@JsonIgnoreProperties(ignoreUnknown = true)`
- `@NotBlank`
- `@Size(max = 255)` no campo `username`

**Objetivo:**
- reduzir boilerplate;
- validar input logo na fronteira da API;
- tolerar campos extra no JSON sem falhar desserializacao.

---

### 4.2 `AuthUserDto`
Representa a resposta segura devolvida ao frontend apos autenticacao.

Contem:
- `userId`
- `username`
- `email`
- `nome`
- `admin`

Anotacoes utilizadas:
- `@Getter`
- `@Setter`
- `@JsonInclude(JsonInclude.Include.NON_NULL)`

**Objetivo:**
- expor apenas os dados necessarios ao frontend;
- nao incluir `passwordHash`;
- manter o JSON de resposta mais limpo quando existirem campos nulos.

---

### 4.3 `AuthUser`
Representa o modelo interno do utilizador autenticavel.

Contem:
- `userId`
- `username`
- `email`
- `nome`
- `admin`
- `passwordHash`

Anotacoes utilizadas:
- `@Getter`
- `@Setter`

**Objetivo:**
- transportar os dados que o backend precisa para autenticar;
- manter o hash da password apenas do lado do servidor.

---

### 4.4 `AuthRepository`
O `AuthRepository` isola o acesso a dados da autenticacao.

Metodos principais:
- `findByUsername(String username)`
- `findById(int userId)`

Melhorias introduzidas:
- `@ApplicationScoped`
- uso de `try-with-resources`
- queries extraidas para constantes
- extracao do mapeamento para metodo privado `mapUser(ResultSet rs)`

Fonte de dados:
- tabela `app_hist.utilizadores`

**Objetivo:**
- manter consistencia com o backend atual, baseado em JDBC direto;
- reduzir duplicacao;
- melhorar legibilidade e manutencao.

---

### 4.5 `PasswordService`
O `PasswordService` centraliza a politica de passwords do backend.

Estado atual:
- hashing principal com **bcrypt**
- verificacao com **bcrypt**
- compatibilidade de verificacao entre variantes `$2a`, `$2b` e `$2y`
- sem suporte ativo a PBKDF2
- sem fallback para plaintext

Anotacoes utilizadas:
- `@ApplicationScoped`

Metodos principais:
- `hash(String rawPassword)`
- `encodeForStorage(String password)`
- `matches(String rawPassword, String storedPassword)`
- `isBcryptHash(String value)`

Comportamento atual:
- `hash(...)` gera hash bcrypt;
- `encodeForStorage(...)` preserva valores ja em bcrypt e so gera novo hash quando necessario;
- `matches(...)` valida password em texto claro contra hash armazenado;
- passwords nulas ou vazias nao sao aceites no fluxo atual.

**Objetivo:**
- centralizar hashing e verificacao no backend;
- alinhar a implementacao com o formato real presente na tabela `utilizadores`;
- simplificar a politica de passwords para uma unica estrategia suportada nesta fase.

---

### 4.6 `AuthService`
O `AuthService` centraliza a logica de autenticacao e sessao.

Responsabilidades:
- validar credenciais;
- obter o utilizador autenticado da sessao;
- estabelecer sessao apos login;
- terminar sessao;
- converter `AuthUser` em `AuthUserDto`

Anotacoes utilizadas:
- `@ApplicationScoped`
- `@Inject` no construtor

Melhorias relevantes:
- validacao basica do request;
- `trim()` aplicado apenas ao `username`;
- `toDto(...)` tornado privado;
- dependencias injetadas via CDI.

Atributo de sessao usado:
- `AUTH_USER`

**Objetivo:**
- concentrar a logica de autenticacao numa camada propria;
- evitar que o `resource` ou o frontend tratem regras de autenticacao.

---

### 4.7 `AuthResource`
O `AuthResource` expõe a API REST da autenticacao.

Base path:
- `/auth`

Anotacoes utilizadas:
- `@Path("auth")`
- `@Consumes(MediaType.APPLICATION_JSON)`
- `@Produces(MediaType.APPLICATION_JSON)`
- `@Inject`
- `@Valid` no `login(...)`

Endpoints implementados:

#### `POST /auth/login`
- recebe `AuthLoginRequestDto`
- valida credenciais atraves do `AuthService`
- invalida sessao anterior, se existir
- cria nova sessao
- guarda o utilizador autenticado em `AUTH_USER`
- devolve `200 OK` com `AuthUserDto`
- devolve `401 Unauthorized` com `ApiErrorDto` se falhar

#### `GET /auth/me`
- tenta obter o utilizador autenticado da sessao atual
- devolve `200 OK` com `AuthUserDto` se existir sessao valida
- devolve `401 Unauthorized` com `ApiErrorDto` se nao existir sessao valida

#### `POST /auth/logout`
- invalida a sessao atual
- devolve `204 No Content`

**Objetivo:**
- expor um contrato simples para o frontend;
- centralizar login, sessao atual e logout no backend.

---

### 4.8 `ApiErrorDto`
Foi introduzido um DTO simples para erros de API.

Campos:
- `code`
- `message`

**Objetivo:**
- devolver erros estruturados em JSON;
- evitar strings JSON montadas manualmente no `AuthResource`;
- facilitar integracao e tratamento de erros no frontend.

---

### 4.9 `CORSFilter`
Foi revista a configuracao de CORS para desenvolvimento local.

Melhorias introduzidas:
- allowlist explicita de origins locais;
- `Access-Control-Allow-Credentials: true` por causa de autenticacao por sessao/cookie;
- inclusao do header legado `token` em `Access-Control-Allow-Headers`;
- tratamento mais cuidado do header `Vary`, evitando sobrescrita cega.

A politica ficou documentada no proprio codigo como configuracao orientada a desenvolvimento local.

**Objetivo:**
- suportar desenvolvimento local com frontend e backend em origins diferentes;
- deixar o ponto de alteracao claro para quem integrar ambientes futuros.

---

## 5) Fluxo de autenticacao

### Login
1. O frontend envia `username` e `password` para `POST /auth/login`.
2. O `AuthResource` recebe o pedido.
3. O DTO e validado.
4. O `AuthService` valida credenciais.
5. O `AuthRepository` procura o utilizador por username.
6. O `PasswordService` valida a password com bcrypt.
7. Em caso de sucesso, a sessao anterior e invalidada.
8. Uma nova sessao e criada.
9. O utilizador autenticado e guardado em `AUTH_USER`.
10. O backend devolve `AuthUserDto`.

### Sessao atual
1. O frontend chama `GET /auth/me`.
2. O `AuthService` tenta obter `AUTH_USER` da sessao atual.
3. Se existir, devolve `AuthUserDto`.
4. Se nao existir, devolve `401 Unauthorized`.

### Logout
1. O frontend chama `POST /auth/logout`.
2. O backend invalida a sessao atual.
3. O backend devolve `204`.

---

## 6) Comandos executados e o que fazem

```powershell
mvn -v
```
- Verifica instalacao do Maven e JDK ativo no ambiente.

```powershell
Push-Location "C:\Users\rafab\Documents\ISEL\SV\proj9\CircPeticionario-WebApp"
mvn -DskipTests install
Pop-Location
```
- Compila, empacota WAR e instala no repositorio local Maven (`.m2`), sem executar testes.
- Foi usado para validar cada alteracao incremental.

```powershell
Push-Location "C:\Users\rafab\Documents\ISEL\SV\proj9\CircPeticionario-WebApp"
mvn dependency:tree "-Dincludes=com.fasterxml.jackson*"
Pop-Location
```
- Mostra a arvore de dependencias apenas para artefactos Jackson.
- Serve para confirmar versoes efetivas resolvidas e detetar overrides/conflitos.

```powershell
Push-Location "C:\Users\rafab\Documents\ISEL\SV\proj9"
git --no-pager status --short
git --no-pager diff -- "CircPeticionario-WebApp/pom.xml"
Pop-Location
```
- Auditoria das alteracoes locais para documentacao e revisao.

---

## 7) Validacoes realizadas

### Build
- `mvn -DskipTests install` -> **BUILD SUCCESS**

### Dependencias Jackson (apos BOM)
- `mvn dependency:tree -Dincludes=com.fasterxml.jackson*` -> **BUILD SUCCESS**
- Versoes efetivas confirmadas:
  - `jackson-databind:2.18.6`
  - `jackson-core:2.18.6`
  - `jackson-annotations:2.18.6`

### Validacao funcional de autenticacao (runtime)
Validados com sucesso:
- `POST /api/CircPeticionario/webresources/auth/login`
- `GET /api/CircPeticionario/webresources/auth/me`
- `POST /api/CircPeticionario/webresources/auth/logout`

Comportamentos confirmados:
- login com sessao HTTP;
- criacao de `JSESSIONID`;
- leitura do utilizador autenticado via sessao;
- logout com invalidacao de sessao;
- respostas de erro estruturadas via `ApiErrorDto`.

### Nota de teste importante
- Chamadas para `http://localhost/webresources/...` sem prefixo `/api/CircPeticionario` podem devolver `200` do frontend (HTML), criando falso positivo de endpoint.
- Endpoints legados com header `token` exigem o valor real configurado em JNDI; `TOKEN_SERVICE` literal devolve `401`.

### CVE check
- Na validacao realizada nesta iteracao, nao foram identificadas CVEs conhecidas no conjunto Jackson validado.

---

## 8) Ganhos obtidos (para relatorio)

1. **Build desbloqueado no ambiente atual**
   - removida configuracao legada que quebrava com JDK 17.

2. **Base de autenticacao no backend operacional**
   - autenticacao centralizada no backend;
   - frontend deixa de precisar fazer hash ou comparacao de password.

3. **Politica de passwords simplificada**
   - bcrypt como estrategia unica de hashing nesta fase;
   - codigo mais limpo e mais facil de explicar/manter.

4. **Separacao clara de responsabilidades**
   - `resource`, `service`, `repository`, `dto` e `model` com papeis distintos.

5. **Seguranca de dependencias reforcada**
   - Jackson atualizado e alinhado por BOM.

6. **Governance de dependencias melhorada**
   - um unico ponto de versao (`jackson.version`).

7. **Ferramentas Maven de diagnostico restauradas**
   - `dependency:tree` funcional apos upgrade de plugin.

8. **Melhor integracao com frontend**
   - endpoints claros;
   - erros estruturados;
   - sessao HTTP gerida no backend.

---

## 9) Ficheiros alterados

### Build / dependencias
- `CircPeticionario-WebApp/pom.xml`

### Fundacao de autenticacao
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/dto/AuthLoginRequestDto.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/dto/AuthUserDto.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/model/AuthUser.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/repository/AuthRepository.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/service/AuthService.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/service/PasswordService.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/resource/AuthResource.java`

### Infra de apoio
- `CircPeticionario-WebApp/src/main/java/pt/ual/views/CORSFilter.java`
- `CircPeticionario-WebApp/src/main/java/pt/ual/common/dto/ApiErrorDto.java` *(caso esta classe tenha sido criada nesta iteracao)*

Nota de trabalho local:
- `/.idea/compiler.xml` pode aparecer alterado no workspace, mas nao faz parte das alteracoes funcionais documentadas aqui.

---

## 10) Compatibilidade temporaria e pendencias

### Compatibilidade temporaria mantida
- coexistencia com o backend legado fora da nova pasta `auth`;
- coexistencia com endpoints antigos ainda existentes;
- suporte a desenvolvimento local com CORS controlado no backend.

### Pendencias recomendadas (proxima fase)
1. Completar testes automatizados de autenticacao (`AuthService` / `PasswordService` / `AuthResource`).
2. Integrar o frontend Angular com `login`, `me` e `logout`.
3. Rever os fluxos legados de criacao/edicao de utilizadores para garantir coerencia com a politica final de passwords.
4. Validar em ambiente Payara alvo eventual conflito de classloading de Jackson (se houver libs do servidor com Jackson diferente).
5. Rever necessidade real do passo de `endorsed` copy no build (mantido nesta fase por compatibilidade, sem ativar no compilador).

---

## 11) Resumo executivo
Foi concluida uma ronda de hardening incremental no backend que resolve falhas de build no ecossistema Maven moderno, fortalece a autenticacao no servidor com bcrypt e normaliza a gestao de dependencias Jackson com BOM, sem alterar o runtime Payara.

Em paralelo, foi consolidada uma fundacao de autenticacao no backend alinhada com o contexto Java/Payara do projeto. A autenticacao passou a estar centrada em `AuthResource`, `AuthService`, `AuthRepository` e `PasswordService`, com sessao HTTP no backend, bcrypt como estrategia de hashing e DTOs simples para comunicacao com o frontend.

A solucao melhora a separacao de responsabilidades, reduz o acoplamento ao frontend e deixa uma base mais clara para integracao futura e manutencao incremental.
