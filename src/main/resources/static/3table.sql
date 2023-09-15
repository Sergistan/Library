create table user_book
(
    user_id integer not null,
    book_id integer not null,
    primary key (user_id, book_id),
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_book_id foreign key (book_id) references books (id)
);

create table user_book
(
    user_id int references users (id),
    book_id int references books (id),
    primary key (user_id, book_id)
);

update books set is_free=true where id = 3;