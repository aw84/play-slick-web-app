-- !Ups

create schema test_schema authorization sa;

create table if not exists test_schema.tokens (
id int primary key,
name varchar
);


-- !Downs


drop table test_schema.tokens;
drop schema test_schema;
