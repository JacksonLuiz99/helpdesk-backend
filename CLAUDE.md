# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Spring Boot 2.3.12 REST API (Java 11, Maven) backing a Helpdesk/ticketing system. This is the `helpdesk-backend` half of a larger project; a separate Angular frontend consumes this API (see the parent `Helpdesk` directory). Domain and code comments/messages are in Portuguese.

## Commands

Build and run (Windows, from this directory):

```
.\mvnw.cmd clean package
.\mvnw.cmd spring-boot:run
```

Run tests:

```
.\mvnw.cmd test
```

Run a single test class:

```
.\mvnw.cmd test -Dtest=HelpdeskApplicationTests
```

There is currently only one (empty) test class (`src/test/java/.../HelpdeskApplicationTests.java`), so this is largely scaffolding — do not assume meaningful test coverage exists.

## Spring profiles

The app is driven by `spring.profiles.active` in `src/main/resources/application.properties`, which is currently set to `test`. Three profiles exist:

- **test** (`application-test.properties`) — H2 in-memory DB, console enabled at `/h2console`, seeds data via `DBService.instanciaDB()` unconditionally (wired through `TestConfig`).
- **dev** (`application-dev.properties`) — MySQL at `localhost:3306/helpdesk`, seeds data only if `spring.jpa.hibernate.ddl-auto=create` (wired through `DevConfig`).
- Production (Railway) — deployed via `railway.json` (Nixpacks builder). Check environment-specific datasource config there/in Railway env vars, not in a committed properties file.

`SecurityConfig` disables the frame-options header only when the `test` profile is active, which is what allows the H2 console to render in an iframe.

## Architecture

Classic layered Spring MVC structure under `src/main/java/com/jackson/helpdesk/`:

- `domain/` — JPA entities. `Pessoa` is an abstract base entity (id, nome, cpf, email, senha, perfis, dataCriacao) extended by `Cliente` and `Tecnico` (single-table-per-subclass via plain inheritance, not `@Inheritance`-annotated — verify actual strategy before assuming table layout). `Chamado` (ticket) has `@ManyToOne` relations to both `Tecnico` and `Cliente`.
  - `domain/enums/` — `Perfil` (ADMIN/CLIENTE/TECNICO role), `Prioridade`, `Status` are integer-backed enums with a `toEnum(Integer)` static lookup pattern used throughout services/DTOs instead of ordinal/name-based (de)serialization.
  - `domain/dtos/` — DTOs mirror entities and carry a constructor that maps from the entity (e.g. `new ChamadoDTO(chamado)`), used to shape JSON API responses/requests instead of exposing entities directly.
- `repositories/` — plain `JpaRepository` interfaces, one per aggregate root (`ChamadoRepository`, `ClienteRepository`, `PessoaRepository`, `TecnicoRepository`).
- `services/` — business logic and entity<->DTO assembly (e.g. `ChamadoService.newChamado` resolves `Tecnico`/`Cliente` by id before building a `Chamado`). Throws `ObjectnotFoundException` / `DataIntegrityViolationException` (custom, under `services/exceptions/`) rather than returning nulls/Optionals to callers.
- `resources/` — `@RestController`s, one per aggregate (`ChamadoResource`, `ClienteResource`, `TecnicoResource`), thin — delegate straight to services and wrap results in DTOs. `resources/exceptions/ResourceExceptionHandler` is a `@ControllerAdvice` that maps the custom service exceptions (and bean-validation errors) to `StandardError`/`validationError` JSON bodies.
- `security/` — custom JWT auth stack: `JWTAuthenticationFilter` (login, issues token), `JWTAuthorizationFilter` (validates bearer token per request), `JWTUtil` (jjwt-based sign/parse, config via `jwt.secret`/`jwt.expiration`), `UserSS` (Spring Security `UserDetails` wrapping a `Pessoa`), `UserDetailsServiceImpl` (loads by email via `PessoaRepository`).
- `config/SecurityConfig` — stateless JWT security chain (CSRF disabled, CORS permissive, `/h2console/**` public, everything else authenticated); `EnableGlobalMethodSecurity(prePostEnabled = true)` means endpoint-level authorization is expected via `@PreAuthorize` rather than URL matchers (check individual resource/service methods for role checks).
- `config/DevConfig` / `config/TestConfig` — `@Profile`-gated beans that trigger `DBService.instanciaDB()` to seed sample technicians, clients, and tickets.

## Conventions to preserve

- Entities are never returned directly from controllers — always go through the matching DTO in `domain/dtos/`.
- Enum-like domain concepts (`Perfil`, `Prioridade`, `Status`) are transmitted as integers and converted with a static `toEnum(Integer)` method; follow this pattern if adding new enums rather than relying on Jackson's default enum handling.
- New service-layer failures should use/extend the existing exceptions in `services/exceptions/` so they're picked up by `ResourceExceptionHandler`.
