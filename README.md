# spring-boot-postgresql

API REST de exemplo desenvolvida com Spring Boot, Spring Web MVC, Spring Data JPA e PostgreSQL. Permite consultar e cadastrar profissionais de desenvolvimento de software.

## Tecnologias

- Java 21 e Spring Boot
- Spring Web MVC
- Spring Data JPA e Hibernate
- PostgreSQL
- Maven Wrapper
- Docker Compose

## Pré-requisitos

- JDK 21
- Docker com Docker Compose

## Configuração e execução

As configurações da aplicação ficam em `src/main/resources/application.properties`. O arquivo define a URL do PostgreSQL (`jdbc:postgresql://localhost:5332/guedesdb`), o usuário e a senha usados localmente, e as opções do Hibernate.

1. Inicie o banco de dados pelo Docker Compose:

   ```bash
   docker compose up -d
   ```

2. Confira os containers:

   ```bash
   docker compose ps
   docker ps -a
   ```

3. Para acessar o PostgreSQL dentro do container, abra um Bash e entre no `psql`. Substitua o marcador pelo usuário configurado em `application.properties`; o nome está censurado neste exemplo:

   ```bash
   docker exec -it postgres-spring-boot bash
   psql -U [USUÁRIO_CENSURADO]
   ```

4. No prompt do `psql`, crie e acesse o banco configurado pela aplicação:

   ```sql
   CREATE DATABASE guedesdb;
   \c guedesdb
   ```

   Para criar uma tabela manualmente e consultar seus registros:

   ```sql
   CREATE TABLE exemplo (
       id SERIAL PRIMARY KEY,
       nome VARCHAR(100)
   );
   SELECT * FROM exemplo;
   ```

   A aplicação também cria as tabelas das entidades JPA ao iniciar, pois `spring.jpa.hibernate.ddl-auto` está configurado como `create-drop`.

5. Inicie a aplicação na raiz do projeto:

   **Linux/macOS**
   ```bash
   ./mvnw spring-boot:run
   ```

   **Windows (PowerShell)**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

A API fica disponível em `http://localhost:8080`.

## Endpoints

| Método | Caminho | Descrição |
| --- | --- | --- |
| GET | `/` | Retorna uma mensagem de boas-vindas. |
| GET | `/api/v1/software-engineers` | Lista os profissionais cadastrados. |
| GET | `/api/v1/software-engineers/{id}` | Consulta um profissional pelo ID. |
| POST | `/api/v1/software-engineers` | Cadastra um profissional. |

### Exemplo de cadastro

```http
POST http://localhost:8080/api/v1/software-engineers
Content-Type: application/json

{
  "name": "Pedro",
  "techStack": "Java, Spring Boot, PostgreSQL"
}
```

Também é possível testar a API com o arquivo `requests.http` ou com `curl`:

```bash
curl -X POST http://localhost:8080/api/v1/software-engineers \
  -H "Content-Type: application/json" \
  -d '{"name":"Pedro","techStack":"Java, Spring Boot, PostgreSQL"}'
```

## Testes

Linux/macOS:

```bash
./mvnw test
```

Windows (PowerShell):

```powershell
.\mvnw.cmd test
```

## Banco de dados

A conexão pode ser configurada pelas variáveis `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME` e `SPRING_DATASOURCE_PASSWORD`. A URL padrão aponta para `localhost:5332/guedesdb`. O Hibernate usa `ddl-auto=create-drop`, então as tabelas são recriadas ao iniciar e removidas ao encerrar a aplicação; os dados não são persistidos entre execuções.
