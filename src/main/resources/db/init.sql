create table if not exists member(
    id serial primary key,
    name varchar(25) not null,
    age integer not null
)