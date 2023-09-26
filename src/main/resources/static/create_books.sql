CREATE TABLE books
(
    id                  bigserial PRIMARY KEY,
    name                varchar(256) not null,
    author              varchar(256) not null,
    date_of_publication integer check (date_of_publication > 1814),
    is_free boolean default (true),
    time_get_book timestamp default (null)
);
