--liquibase formatted sql
--changeset meow:create_favorites_products logicalFilePath:/

CREATE TABLE favorites_products
(
    user_id    BIGSERIAL NOT NULL,
    product_id BIGSERIAL NOT NULL,

    constraint FK_user FOREIGN KEY (user_id) references users (id),
    constraint FK_product FOREIGN KEY (product_id) references product (id),
    constraint PK_favorites_products PRIMARY KEY (user_id, product_id)


)

