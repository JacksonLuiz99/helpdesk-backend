# Helpdesk API

API REST em Spring Boot para um sistema de chamados de suporte técnico (helpdesk), com autenticação via JWT. É o backend consumido pelo [`helpdesk-frontend`](https://github.com/JacksonLuiz99/helpdesk-frontend).

## Stack

- Java 11, Spring Boot 2.3.12, Maven
- Spring Data JPA, Spring Security, JWT (`jjwt`)
- H2 em memória (perfil `test`) ou MySQL (perfil `dev`)
- Deploy: Railway (Nixpacks)

## Como rodar

```bash
./mvnw spring-boot:run     # Linux/macOS
.\mvnw.cmd spring-boot:run # Windows
```

Sobe em `http://localhost:8080`. Por padrão usa o perfil `test` (`spring.profiles.active=test` em `application.properties`), que roda em H2 em memória e popula dados de exemplo automaticamente ao iniciar (ver `DBService`). O console do H2 fica disponível em `/h2console`.

Para usar MySQL local, troque o perfil para `dev` — requer um banco `helpdesk` em `localhost:3306` (usuário `root`, sem senha, ajustável em `application-dev.properties`).

## Autenticação

`POST /login` com `{"email": "...", "senha": "..."}` retorna o token JWT no header `Authorization` da resposta (não no corpo). Envie esse token como `Authorization: Bearer <token>` nas demais requisições — todas exigem autenticação, exceto `/h2console/**`.

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

Mais detalhes de arquitetura (camadas, DTOs, segurança) em `CLAUDE.md`.
