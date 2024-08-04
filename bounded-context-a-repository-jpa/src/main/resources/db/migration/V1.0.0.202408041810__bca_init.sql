CREATE SCHEMA IF NOT EXISTS bca_context;

create table bca_context.entity_bca (
    id varchar(255) not null,
    propa varchar(255) not null,
    propb varchar(255) not null,
    propc varchar(255) not null,
    primary key (id)
);