drop table if exists membership, member, bot_state, users, role cascade;

create table if not exists member(
    id serial primary key,
    name varchar(255) not null,
    age integer not null
);

create table if not exists membership(
    id         serial not null primary key,
    end_date   date,
    start_date date,
    type       varchar(255),
    member_id  bigint not null
        constraint fk_membership_member
            references member(id),
    status     varchar(255),
    code varchar(255)
);


create table if not exists bot_state (
    id         serial not null primary key,
    chat_id bigint not null,
    state varchar(128) not null,
    unique (chat_id)
);

CREATE TABLE if not exists role
(
    id   BIGSERIAL    NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    unique (name)
);

CREATE TABLE if not exists users
(
    id            BIGSERIAL    NOT NULL PRIMARY KEY,
    role_id       int REFERENCES role (id) ON DELETE CASCADE,
    username      varchar(255) NOT NULL,
    password      varchar(255) NOT NULL,
    first_name    varchar(255) NOT NULL,
    last_name     varchar(255) NOT NULL,
    email         varchar(255) NOT NULL,
    personal_id   varchar(255) not null,
    phone_number  varchar(255) not null,
    UNIQUE (username)
);

create table if not exists userdemo (
    id bigserial not null primary key ,
    fname varchar(255) not null ,
    lname varchar(255),
    uname varchar(255),
    registeredat timestamp,
    command varchar(255)
);

drop table userdemo