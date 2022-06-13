CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    username   VARCHAR(128),
    firstname  VARCHAR(128),
    lastname   VARCHAR(128),
    birth_date DATE,
    age        INT,
    role       VARCHAR(32),
    company_id INT REFERENCES company (id) ON DELETE CASCADE
);

CREATE TABLE users_chat
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT REFERENCES users (id),
    chat_id BIGINT REFERENCES chats (id),
    created_at TIMESTAMP NOT NULL ,
    created_by VARCHAR(128) NOT NULL
);

DROP TABLE users_chat;

CREATE TABLE chats
(
    id BIGSERIAL PRIMARY KEY ,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE profile
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL UNIQUE REFERENCES users (id) ,
    street VARCHAR(128) ,
    language CHAR(2)
);

DROP TABLE profile;

CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);


DROP TABLE users;