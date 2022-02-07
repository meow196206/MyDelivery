--liquibase formatted sql
--changeset meow:create_database logicalFilePath:/

CREATE TABLE users
(
    id       BIGSERIAL primary key,
    login    VARCHAR(12) NOT NULL,
    password VARCHAR(8)  NOT NULL,
    name     VARCHAR(12) NOT NULL
)

