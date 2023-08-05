CREATE DATABASE abled;
USE abled;

CREATE TABLE account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150)  NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    phone_code VARCHAR(5) NULL,
    phone_number VARCHAR(15) NULL,
	phone_share BOOLEAN DEFAULT FALSE,
    image_url VARCHAR(5000) NULL,
    email VARCHAR(255) NOT NULL,
    account_active BOOLEAN DEFAULT FALSE,
    detail VARCHAR(2500) NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    password VARCHAR(255) NOT NULL,
	country VARCHAR(50) NOT NULL,
    city VARCHAR(50) NULL,
	address VARCHAR(255) NULL,
	postal_code VARCHAR(25) NULL,
);