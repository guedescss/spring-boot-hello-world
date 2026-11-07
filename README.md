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

1. Crie seu arquivo local de configuração a partir do modelo:

   ```bash
   cp .env.example .env
   ```

   Edite `.env` e substitua `POSTGRES_PASSWORD` por uma senha local. O arquivo `.env` é ignorado pelo Git; não o publique nem inclua credenciais reais no repositório.

2. Inicie o banco de dados:

   ```bash
   docker compose up --pull always -d db
   ```

   O PostgreSQL estará disponível na porta `5332` do host. O Compose lê os valores de `.env` para configurar o banco.

3. Configure as variáveis que a aplicação Spring Boot usará. O `.env` do Compose não é carregado automaticamente pelo processo Maven.

   **Linux/macOS** (na mesma sessão de terminal):
   ```bash
   set -a
   . ./.env
   set +a
   export SPRING_DATASOURCE_USERNAME="$POSTGRES_USER"
   export SPRING_DATASOURCE_PASSWORD="$POSTGRES_PASSWORD"
   ```

   **Windows (PowerShell)**, defina os valores conforme seu `.env`:
   ```powershell
   $env:SPRING_DATASOURCE_USERNAME = "seu_usuario_local"
   $env:SPRING_DATASOURCE_PASSWORD = "sua_senha_local"
   ```

4. Inicie a aplicação na raiz do projeto:

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
