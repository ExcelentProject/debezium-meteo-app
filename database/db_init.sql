CREATE DATABASE debezium_crypto;
\c debezium_crypto;
create table crypto (id varchar(255) not null, market_cap_usd float8, name varchar(255), price_usd float8, supply float8 not null, symbol varchar(255), primary key (id));
alter table crypto owner to test;