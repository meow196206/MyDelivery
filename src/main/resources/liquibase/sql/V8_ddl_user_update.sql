--liquibase formatted sql
--changeset meow:user_update logicalFilePath:/

ALTER TABLE users alter column password type varchar(256) using password::varchar(256);

