--liquibase formatted sql
--changeset meow:order_update logicalFilePath:/

ALTER TABLE orders add column status varchar(20) not null default 'NEW';
ALTER TABLE orders add column created_date timestamp default now();

