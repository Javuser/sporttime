drop table if exists membership;
drop table if exists member;

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
    status     varchar(255)
);


