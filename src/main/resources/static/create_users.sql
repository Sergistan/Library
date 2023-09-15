CREATE TABLE users
(
    id                  bigserial PRIMARY KEY,
    name                varchar(256) not null,
    phone_number        varchar(256) not null
);