--liquibase formatted sql
--changeset meow:create_order logicalFilePath:/

CREATE TABLE orders
(
    id      BIGSERIAL primary key,
    user_id BIGINT NOT NULL,
    constraint FK_user FOREIGN KEY (user_id) references users (id)
)

