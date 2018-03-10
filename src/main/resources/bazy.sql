-- create schema stan;
-- set SEARCH_PATH to cash;

create table "user"(
  uid serial not null,
  name text not null,
  nickname text not null,
  active boolean default false
);

create unique index  user_nickname_uindex
  on "user" (nickname);

create unique index  user_uid_uindex
  on "user" (uid);


create table crypto(
  cid SERIAL not NULL,
  uid int NOT NULL,
  hash text NOT NULL
);

create table account (
  aid SERIAL NOT NULL,
  name text,
  balance int,
  uid int
);

create table transfer(
  tid SERIAL NOT NULL,
  aidfrom int NOT NULL,
  aidto INT NOT NULL,
  amount int,
  timestamp TIMESTAMP
);


ALTER TABLE cash."crypto"
  ADD CONSTRAINT crypto_user_uid_fk
FOREIGN KEY (uid) REFERENCES cash."user" (uid) 
ON DELETE CASCADE ON UPDATE CASCADE;



