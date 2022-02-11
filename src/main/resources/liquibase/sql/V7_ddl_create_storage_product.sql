--liquibase formatted sql
--changeset meow:create_storage_product logicalFilePath:/

CREATE TABLE storage_product
(
    storage_id    BIGSERIAL NOT NULL,
    product_id BIGSERIAL NOT NULL,

    constraint FK_user FOREIGN KEY (storage_id) references storage (id),
    constraint FK_product FOREIGN KEY (product_id) references product (id),
    constraint PK_storage_product PRIMARY KEY (storage_id, product_id)


)

