DROP TABLE IF EXISTS customer_file;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS file;


CREATE TABLE customer
(
    id           INT AUTO_INCREMENT,
    first_name   VARCHAR(50) NOT NULL,
    last_name    VARCHAR(50) NOT NULL,
    email        varchar(80) NOT NULL,
    phone_number varchar(20) NOt NULL,
    password     varchar(50) NOT NULL,
    created_at   datetime    NOT NULL,
    is_signed_in tinyint(1) default 0,
    unique (email),
    PRIMARY KEY (id)

);

CREATE TABLE category
(
    id   INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          INT AUTO_INCREMENT,
    name        VARCHAR(50)    NOT NULL,
    brand_name  VARCHAR(50)    NOT NULL,
    category_id INT            NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE file
(
    id          INT AUTO_INCREMENT,
    file_name   VARCHAR(50) NOT NULL,
    file_type   VARCHAR(50) NOT NULL,
    data        BLOB        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customer_file
(
    file_id   INT         NOT NULL,
    customer_id INT         NOT NULL,
    FOREIGN KEY (file_id) REFERENCES file (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);




