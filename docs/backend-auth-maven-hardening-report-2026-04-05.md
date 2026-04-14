# Backend - Fundacao de Autenticacao e Hardening de Build (2026-04-05)

## 1) Objetivo deste documento
Este documento resume, de forma cronologica e tecnica, as alteracoes aplicadas no backend (`Java/Payara`) para:
- desbloquear o build Maven no ambiente atual;
- consolidar a base de autenticacao no backend (sem hashing no frontend);
- corrigir warnings/riscos de dependencias (Jackson);
- melhorar previsibilidade de dependencias e ferramentas de diagnostico.

Foi mantida a estratégia incremental: sem refatoracao funcional agressiva e sem alterar runtime Payara.

---

## 2) Contexto inicial
Sintomas reportados:
1. falha de build no frontend Docker (`npm ci`) - tratado em paralelo anteriormente;
2. no backend, apos instalar Maven, `mvn install` falhava;
3. warning/risco em dependencias Jackson;
4. necessidade de garantir base de autenticacao no backend com bcrypt.

Estado tecnico relevante encontrado no backend:
- projeto Maven WAR em `CircPeticionario-WebApp`;
- uso de configuracoes antigas de compilador Maven (`endorseddirs`) nao compativeis com JDK 17;
- `maven-dependency-plugin` muito antigo (`2.6`), causando falha no `dependency:tree` com Maven 3.9;
- dependencia Jackson sem governance centralizada via BOM;
- `PasswordService` a precisar de suporte bcrypt real e compatibilidade com formatos legados.

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
- habilita hashing/verificacao de password no backend;
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

---

### 3.2 `CircPeticionario-WebApp/src/main/java/pt/ual/auth/service/PasswordService.java`

- Integrado `org.mindrot.jbcrypt.BCrypt`.
- `hash(rawPassword)` passou a gerar hash bcrypt.
- `matches(rawPassword, storedPassword)` agora suporta:
  1. bcrypt (`$2a/$2b/$2y`),
  2. PBKDF2 legado (`PBKDF2$...`),
  3. fallback plaintext legado (compatibilidade incremental).
- `encodeForStorage(password)` preserva hashes já existentes (bcrypt ou PBKDF2) e so gera novo hash quando necessario.
- Adicionado metodo `isBcryptHash(...)`.
- Adicionado fallback de compatibilidade para verificacao bcrypt legado (`$2b$/$2y$` -> verificacao equivalente em `$2a$` quando necessario).
- Removidos campos de salt aleatorio que eram usados no fluxo anterior de PBKDF2 como estrategia primaria.

**Por que:**
- backend passa a ser dono da validacao/hash de passwords;
- mantem coexistencia com dados legados sem migracao massiva imediata;
- evita regressao de login em contas antigas durante transicao.

### 3.3 O que já funcionava e foi necessario ajustar

#### O que funcionava no legado
- O login legado (Node/frontend antigo) **ja validava password com bcrypt**.
- O endpoint `GET /utilizadores/exist/{username}` apenas devolvia dados do utilizador (incluindo hash), nao autenticava sessao.
- A comparacao era feita no cliente/Node via `bcrypt.compare(...)` contra hash armazenado na BD.

#### Porque o novo endpoint falhou inicialmente
- Ao migrar a validacao para backend Java (`POST /auth/login`), a comparacao passou a ser feita por `PasswordService`.
- Em runtime, o utilizador era encontrado, mas a validacao server-side retornava falha (`/utilizadores/passValidate` devolvia `0`), levando `auth/login` a `401`.
- A causa observada foi incompatibilidade de verificacao entre hashes bcrypt legados (`$2b$`) e a biblioteca usada no backend Java em alguns cenarios.

#### Porque esta implementacao era necessaria
- Objetivo da fase: remover do frontend a responsabilidade de hashing/comparacao.
- Para manter migracao incremental sem reset de passwords, era necessario suportar hashes ja existentes na BD.
- A solucao adotada foi compatibilidade de verificacao no backend (fallback controlado), sem alterar contrato HTTP nem runtime Payara.

---

## 4) Comandos executados e o que fazem

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
git --no-pager diff -- "CircPeticionario-WebApp/pom.xml" "CircPeticionario-WebApp/src/main/java/pt/ual/auth/service/PasswordService.java"
Pop-Location
```
- Auditoria das alteracoes locais para documentacao e revisao.

---

## 5) Validacoes realizadas

### Build
- `mvn -DskipTests install` -> **BUILD SUCCESS**

### Dependencias Jackson (apos BOM)
- `mvn dependency:tree -Dincludes=com.fasterxml.jackson*` -> **BUILD SUCCESS**
- Versoes efetivas confirmadas:
  - `jackson-databind:2.18.6`
  - `jackson-core:2.18.6`
  - `jackson-annotations:2.18.6`

### Validacao funcional de autenticacao (runtime)
- `GET /api/CircPeticionario/webresources/utilizadores/exist/{username}` com token real -> **200** e utilizador encontrado.
- `POST /api/CircPeticionario/webresources/utilizadores/passValidate` com password valida:
  - antes do ajuste de compatibilidade -> **200** com body `0` (falha de comparacao);
  - apos ajuste -> **200** com body `15` (user_id autenticado).
- `POST /api/CircPeticionario/webresources/auth/login`:
  - antes do ajuste -> **401**;
  - apos ajuste -> **200** com `Set-Cookie: JSESSIONID=...` e `AuthUserDto`.

### Nota de teste importante
- Chamadas para `http://localhost/webresources/...` sem prefixo `/api/CircPeticionario` podem devolver `200` do frontend (HTML), criando falso positivo de endpoint.
- Endpoints legados com header `token` exigem o valor real configurado em JNDI; `TOKEN_SERVICE` literal devolve `401`.

### CVE check
- Validacao de CVEs para Jackson atualizado -> **sem CVEs conhecidas** no conjunto validado.

---

## 6) Ganhos obtidos (para relatorio)

1. **Build desbloqueado no ambiente atual**
   - removida configuracao legada que quebrava com JDK 17.

2. **Base de autenticacao no backend operacional**
   - bcrypt implementado no backend;
   - frontend deixa de precisar fazer hash/comparacao de password.

3. **Compatibilidade incremental preservada**
   - suporte a hashes/formatos legados durante a transicao.

4. **Seguranca de dependencias reforcada**
   - Jackson atualizado e alinhado por BOM.

5. **Governance de dependencias melhorada**
   - um unico ponto de versao (`jackson.version`).

6. **Ferramentas Maven de diagnostico restauradas**
   - `dependency:tree` funcional apos upgrade de plugin.

---

## 7) Ficheiros alterados
- `CircPeticionario-WebApp/pom.xml`
- `CircPeticionario-WebApp/src/main/java/pt/ual/auth/service/PasswordService.java`

Nota de trabalho local:
- `/.idea/compiler.xml` apareceu alterado no workspace, mas nao faz parte das alteracoes funcionais do backend documentadas aqui.

---

## 8) Compatibilidade temporaria e pendencias

### Compatibilidade temporaria mantida
- coexistencia de validacao de password para bcrypt + PBKDF2 + plaintext legado.

### Pendencias recomendadas (proxima fase)
1. Definir estrategia de migracao de passwords legadas para bcrypt (on-login ou batch).
2. Completar testes automatizados de autenticacao (`AuthService`/`PasswordService`).
3. Validar em ambiente Payara alvo eventual conflito de classloading de Jackson (se houver libs do servidor com Jackson diferente).
4. Rever necessidade real do passo de `endorsed` copy no build (mantido nesta fase por compatibilidade, sem ativar no compilador).

---

## 9) Resumo executivo
Foi concluida uma ronda de hardening incremental no backend que resolve falhas de build no ecossistema Maven moderno, fortalece o stack de autenticacao com bcrypt no servidor e normaliza a gestao de dependencias Jackson com BOM, mantendo compatibilidade temporaria com legado e sem alterar o runtime Payara.
