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

CREATE TABLE profile
(
    id BIGSERIAL PRIMARY KEY ,
    user_id BIGSERIAL NOT NULL UNIQUE
);

DROP TABLE profile;

CREATE TABLE company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);


DROP TABLE users;