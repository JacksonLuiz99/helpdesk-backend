# Helpdesk API

API REST em Spring Boot para um sistema de chamados de suporte técnico (helpdesk), com autenticação via JWT. É o backend consumido pelo [`helpdesk-frontend`](https://github.com/JacksonLuiz99/helpdesk-frontend).

## Stack

- Java 11, Spring Boot 2.3.12, Maven
- Spring Data JPA, Spring Security, JWT (`jjwt`)
- H2 em memória (perfil `test`) ou MySQL (perfil `dev`)
- Deploy: Render (Docker)

## Como rodar

```bash
./mvnw spring-boot:run     # Linux/macOS
.\mvnw.cmd spring-boot:run # Windows
```

Sobe em `http://localhost:8080`. Por padrão usa o perfil `test` (controlado pela variável de ambiente `SPRING_PROFILES_ACTIVE`, que cai pra `test` se não for definida), que roda em H2 em memória e popula dados de exemplo automaticamente ao iniciar (ver `DBService`). O console do H2 fica disponível em `/h2console`.

Para usar MySQL local, rode com `SPRING_PROFILES_ACTIVE=dev` — requer um banco `helpdesk` em `localhost:3306` (usuário `root`, sem senha, ajustável em `application-dev.properties`).

## Autenticação

`POST /login` com `{"email": "...", "senha": "..."}` retorna o token JWT no header `Authorization` da resposta (não no corpo), válido por 30 minutos. Envie esse token como `Authorization: Bearer <token>` nas demais requisições — todas exigem autenticação, exceto `/h2console/**`. `GET /me` retorna o email e os papéis (`perfis`) do usuário autenticado.

Usuários de teste seedados no perfil `test` (ver `DBService`):

| Papel | Email | Senha |
|---|---|---|
| ADMIN | `jacksoncuiaba99@uol.com` | `1234` |
| Técnico (só papel CLIENTE) | `scooby.doo@hotmail.com`, `fred.flintstone@gmail.com`, ... | `123` |
| Cliente | `poderoso@gmail.com`, `maria@outlook.com`, ... | `123` |

## Endpoints principais

| Recurso | GET | POST / PUT / DELETE |
|---|---|---|
| `/tecnicos` | qualquer usuário autenticado | restrito a ADMIN |
| `/clientes` | qualquer usuário autenticado | restrito a ADMIN ou TECNICO |
| `/chamados` | qualquer usuário autenticado | qualquer usuário autenticado |

## Testes

```bash
./mvnw test
```

## Deploy (Render)

O `Dockerfile` na raiz faz um build multi-stage (Maven → JRE) e já foi testado localmente (`docker build` + `docker run`). No Render, crie um **Web Service** apontando pro repositório — o `Dockerfile` é detectado automaticamente, não precisa configurar build/start command na mão.

A aplicação lê a porta de `PORT` (padrão 8080 se a variável não existir, então local continua igual). O perfil `prod` (`application-prod.properties`) espera as seguintes variáveis de ambiente — nenhuma delas tem valor padrão, então o app não sobe sem elas:

| Variável | Descrição |
|---|---|
| `SPRING_PROFILES_ACTIVE` | `prod` |
| `DATABASE_URL` | URL JDBC do banco (ex.: `jdbc:postgresql://host:5432/helpdesk`) |
| `DATABASE_USERNAME` | usuário do banco |
| `DATABASE_PASSWORD` | senha do banco |
| `JWT_SECRET` | segredo usado pra assinar o JWT — **não reaproveite o valor padrão de dev** |
| `CORS_ORIGINS` | origem(s) do frontend em produção (ex.: URL do deploy no Vercel) |

`JWT_EXPIRATION` é opcional (padrão: 1800000 ms / 30 min).

Banco de dados: usamos o **Postgres gerenciado do Render** (driver `org.postgresql` já está no `pom.xml`, ao lado do MySQL usado localmente no perfil `dev`). O Render mostra a conexão no formato `postgres://usuario:senha@host:porta/banco` — isso **não** é o formato que o Spring espera. Converta assim:

- `DATABASE_URL` = `jdbc:postgresql://host:porta/banco` (troca `postgres://` por `jdbc:postgresql://` e tira o usuário/senha da URL)
- `DATABASE_USERNAME` = a parte `usuario` da URL original
- `DATABASE_PASSWORD` = a parte `senha` da URL original

Use a **Internal Database URL** do Render se o banco estiver na mesma região/projeto que o Web Service (mais rápido e não sai pra internet); a External só é necessária pra conectar de fora do Render.

Mais detalhes de arquitetura (camadas, DTOs, segurança) em `CLAUDE.md`.
