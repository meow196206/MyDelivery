--liquibase formatted sql
--changeset meow:create_product logicalFilePath:/

CREATE TABLE product
(
    id          BIGSERIAL primary key,
    name        VARCHAR(12) NOT NULL,
    price       VARCHAR(8)  NOT NULL,
    description VARCHAR(12) NOT NULL
)

