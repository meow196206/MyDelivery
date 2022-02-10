--liquibase formatted sql
--changeset meow:create_orders_products logicalFilePath:/

CREATE TABLE orders_products
(
    order_id   BIGSERIAL NOT NULL ,
    product_id BIGSERIAL NOT NULL ,
    count      BIGINT,

    constraint FK_order FOREIGN KEY (order_id) references orders (id),
    constraint FK_product FOREIGN KEY (product_id) references product (id),
    constraint PK_order_product PRIMARY KEY (order_id, product_id)


)

