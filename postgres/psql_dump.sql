DROP TABLE IF EXISTS token_store;
CREATE TABLE token_store
(
  uuid character varying(255) NOT NULL,
  token text,
  expire_at timestamp without time zone,
  CONSTRAINT token_store_pkey PRIMARY KEY (uuid)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS music;
CREATE TABLE music
(
  id_music text,
  author text,
  title text,
  creation text,
  genre text,
  CONSTRAINT music_pkey PRIMARY KEY(id_music)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
  id_user text,
  username text,
  email text,
  salt text,
  passhash text,
  CONSTRAINT media_pkey PRIMARY KEY(id_user)
)
WITH (
OIDS=FALSE
);

DROP TABLE IF EXISTS loan;
CREATE TABLE loan
(
  id_loan text,
  id_music text REFERENCES music (id_music),
  id_user text REFERENCES users (id_user),
  CONSTRAINT loan_pkey PRIMARY KEY (id_music,id_user)
)
WITH (
OIDS=FALSE
);


INSERT INTO music (id_music, author, title, creation, genre)
VALUES ('1','Dream Theater','The Astonishing','Metal Progressive Rock','2016/01/29');

INSERT INTO music (id_music, author, title, creation, genre)
VALUES ('2','The Neal Morse Band','The Similitude of a Dream','Christian Metal Progressive Rock','2016/11/11');

