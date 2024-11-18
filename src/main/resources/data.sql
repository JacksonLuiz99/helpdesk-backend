-- Inserindo Tecnicos
INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Tecnico', 'Valdir Cezar', '550.482.150-95', 'valdir@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Tecnico', 'Richard Stallman', '903.347.070-56', 'gustavo@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Tecnico', 'Claude Elwood Shannon', '271.068.470-54', 'shannon@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Tecnico', 'Tim Berners-Lee', '162.720.120-39', 'lee@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Tecnico', 'Linus Torvalds', '778.556.170-27', 'linus@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

-- Inserindo Clientes
INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Cliente', 'Albert Einstein', '111.661.890-74', 'einstein@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Cliente', 'Marie Curie', '322.429.140-06', 'curie@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Cliente', 'Charles Darwin', '792.043.830-62', 'darwin@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Cliente', 'Stephen Hawking', '177.409.680-30', 'hawking@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

INSERT INTO pessoa (dtype, nome, cpf, email, senha, data_criacao)
VALUES ('Cliente', 'Max Planck', '081.399.300-83', 'planck@mail.com', '$2a$04$K2xkIYR2LAeN7r.Y7oYMvO98NIRDLs4Cp0OwZJIlyFns1s/5a/LWu', CURRENT_DATE);

-- Inserindo perfil ADMIN para Valdir
INSERT INTO perfis (pessoa_id, perfis) VALUES (1, 0);

-- Inserindo perfil TECNICO para todos os tecnicos
INSERT INTO perfis (pessoa_id, perfis) VALUES (1, 0);
INSERT INTO perfis (pessoa_id, perfis) VALUES (2, 2);
INSERT INTO perfis (pessoa_id, perfis) VALUES (3, 2);
INSERT INTO perfis (pessoa_id, perfis) VALUES (4, 2);
INSERT INTO perfis (pessoa_id, perfis) VALUES (5, 2);

-- Inserindo perfil CLIENTE para todos os clientes
INSERT INTO perfis (pessoa_id, perfis) VALUES (6, 1);
INSERT INTO perfis (pessoa_id, perfis) VALUES (7, 1);
INSERT INTO perfis (pessoa_id, perfis) VALUES (8, 1);
INSERT INTO perfis (pessoa_id, perfis) VALUES (9, 1);
INSERT INTO perfis (pessoa_id, perfis) VALUES (10, 1);

-- Inserindo Chamados
INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura)
VALUES (1, 1, 'Chamado 1', 'Teste chamado 1', 1, 6, CURRENT_DATE);

INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura)
VALUES (2, 0, 'Chamado 2', 'Teste chamado 2', 1, 7, CURRENT_DATE);

INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura, data_fechamento)
VALUES (0, 2, 'Chamado 3', 'Teste chamado 3', 2, 8, CURRENT_DATE, CURRENT_DATE);

INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura)
VALUES (2, 0, 'Chamado 4', 'Teste chamado 4', 3, 8, CURRENT_DATE);

INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura)
VALUES (1, 1, 'Chamado 5', 'Teste chamado 5', 2, 6, CURRENT_DATE);

INSERT INTO chamado (prioridade, status, titulo, observacoes, tecnico_id, cliente_id, data_abertura, data_fechamento)
VALUES (0, 2, 'Chamado 7', 'Teste chamado 6', 1, 10, CURRENT_DATE, CURRENT_DATE);