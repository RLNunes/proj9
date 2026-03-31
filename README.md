# Guia de Replicação da App - Circuitos Oceânicos

Este ficheiro descreve os passos necessários para replicar o sistema a partir do zero.

## Pré-requisitos

- Docker Desktop ou Podman Desktop instalado
- Acesso aos ficheiros da App

## Passos para replicar a aplicação

1. **Instalar o Docker Desktop ou o Podman Desktop**

   Certifique-se de que o Docker/Podman está corretamente instalado e funcional.

2. **Criar os contentores Docker/Podman**

   Abrir a linha de comandos na diretoria raíz da App e executar os comandos:
   ```bash
   cd CircPeticionario-WebApp
      call go-BuildWithClean.bat
   cd ..

   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario up --build
   ```
   Em alernativa ao comando `docker` pode utilizar o `podman`
   O parâmetro `--build` recompila a imagem do frontend Nginx definida em `FrontEnd/Dockerfile`, incorporando os ficheiros estáticos de `FrontEnd/public` e o `nginx/default.conf` (sem dependência de mounts locais).

   > **Nota:** Já não é necessário criar a rede manualmente. O Compose gere a rede `network_circ_peticionario` automaticamente.

   Outros comandos `docker`/`podman` que podem ser uteis

   Iniciar o compose
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario start
   ```

   Parar o compose
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario stop
   ```

   Remover o compose
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario rm
   ```

   Remover o compose e redes
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario down
   ```

   Remover o compose, redes e volumes
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario down -v
   ```

   Remover o compose, redes, volumes e imagens
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario down --rmi all
   ```

   Listar os contentores
   ```bash
   docker ps
   docker ps -a
   ```

   Listar os contentores de um compose
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario ps
   ```

   Listar as imagens
   ```bash
   docker image ls
   ```

   Remover uma imagem por nome
   ```bash
   docker rmi nome_da_imagem
   ```

   Remover uma imagem por ID
   ```bash
   docker rmi ID_da_imagem
   ```

   Listar as redes
   ```bash
   docker network ls
   ```

   Listar as redes de um compose
   ```bash
   docker compose -f CircPeticionario-Compose.yaml -p circ_peticionario network ls
   ```

   Remover uma rede
   ```bash
   docker network rm nome_da_rede
   ```

   Listar os volumes
   ```bash
   docker volume ls
   ```

   Remover um volume
   ```bash
   docker volume rm nome_do_volume
   ```
   
1. **Iniciar os contentores previamente criados no Podman/Docker**

   No Docker Desktop/Podman Desktop, iniciar todos os contentores que foram criados:
   
   - `circ_peticionario_cnt_db`
   - `circ_peticionario_cnt_payara`
   - `circ_peticionario_cnt_nginx`

   Certifique-se de que todos estão com o estado "Running".
   
2. **Aceder à aplicação**

   - **Frontend:** [http://localhost/](http://localhost/)
   - **Payara Admin Console:** [http://localhost:9848/](http://localhost:9848/)
   - **Payara Web Server:** [http://localhost:9082/](http://localhost:9082/)

   > O frontend utiliza `/api/...` (reverse proxy Nginx → Payara) para todas as chamadas à API, evitando erros CORS.

3. **Aceder à página de administração do Payara**

   No browser, aceder à página de administração do Payara: [http://localhost:9848](http://localhost:9848)

   Para fazer login, utilizar as seguintes credenciais:
   - **User Name**: `admin`
   - **Password**: `admin`
   - Pressionar o botão de Login.

3. **Confirmar que a App do Payara está instalad**

   Depois de autenticado, no menu vertical:
   
   - Selecionar **Applications**
   - A App `CircPeticionario.war` deverá estar disponível
   
4. **Criar um JDBC Connection Pool**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:
   - Ainda na página de administração do Payara, no menu vertical, selecionar **JDBC** > **JDBC Connection Pools**
   - Clicar em **New**
   - Preencher os seguintes campos:
     - **Pool Name:** `CircuitoPeticionario`
     - **Resource Type:** `javax.sql.XADataSource`
     - **Database Driver Vendor:** `Postgresql`
   - Clicar em **Next**
   
  Na página seguinte:
   - Fazer scroll até à secção **Additional Properties**
   - Adicionar as seguintes propriedades:

     | Name           | Value                   |
     |----------------|-------------------------|
     | `portNumber`   | `5432`                  |
     | `serverName`   | `circ.peticionario.db`  |
     | `password`     | `postgres`              |
     | `databaseName` | `proj_fapesp`           |
     | `user`         | `postgres`              |

   - Clicar em **Finish**
   
7.  **Criar um JDBC Resource**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:
   - Ainda na página de administração do Payara, no menu vertical, selecionar **JDBC** > **JDBC Resources**

   - Clicar em **New**
   - Preencher os seguintes campos:
     - **JNDI Name:** `jdbc/app_cpet`
     - **Pool Name:** `CircuitoPeticionario`
   - Clicar em **OK** para concluir o processo
   
8.  **Instalar o driver do Postgresql no Payara**
   
   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem.

9.  **Criar um Custom Resource no Payara (JNDI)**

   Estas configurações já estão no ficheiro `domain.xml` que é incluido automaticamente na imagem. Caso se pretenda replicar num sistema totalmente novo:

   - Voltar à página de administração do Payara: [http://localhost:4848](http://localhost:4848)
   - No menu vertical, selecionar **JNDI** > **Custom Resources**
   - Clicar em **New** e preencher os seguintes campos:
     - **JNDI Name**: `TOKEN_SERVICE`
     - **Resource Type**: `java.lang.String`
   - Ainda na mesma página, um pouco a baixo, no campo **Additional Properties** clicar em **Add Property** para adicionar uma nova propriedade:
     - **Name**: `value`
     - **Value**: (copiar o valor da variável `TOKEN_SERVICE` do ficheiro `.env` localizado na raiz do projeto)
   - Clicar em **OK** para concluir o processo

10. **Acesso à aaplicação "Circuitos Oceânicos"**

   No browser, aceder à página da aplicação: [http://localhost/](http://localhost/)
