--liquibase formatted sql
--changeset meow:create_storage logicalFilePath:/

CREATE TABLE storage
(
    id      BIGSERIAL primary key,
    address VARCHAR(12) NOT NULL,
    name    VARCHAR(8)  NOT NULL
)