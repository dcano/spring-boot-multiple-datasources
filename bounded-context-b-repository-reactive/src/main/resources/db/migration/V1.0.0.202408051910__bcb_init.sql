CREATE SCHEMA IF NOT EXISTS bcb_context;

create table bcb_context.entity_bcb (
    id varchar(255) not null,
    propa varchar(255) not null,
    propb varchar(255) not null,
    propc varchar(255) not null,
    primary key (id)
);