CREATE TABLE customer
(
    id        IDENTITY     NOT NULL,
    user_name VARCHAR(25)  NOT NULL,
    password  VARCHAR(255) NOT NULL,
    token     VARCHAR(255) NOT NULL,

    CONSTRAINT pk_user PRIMARY KEY (id)
);