CREATE DATABASE abled;
USE abled;

CREATE TABLE users (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250),
                       phone_code VARCHAR(5)NULL,
                       phone_number VARCHAR(15) NULL,
                       phone_share BOOLEAN DEFAULT FALSE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       image_url VARCHAR(5000) NULL,
                       user_active BOOLEAN DEFAULT FALSE,
                       detail VARCHAR(2500) NULL,
                       address VARCHAR(255) NULL,
                       country VARCHAR(50)NULL,
                       remember_token VARCHAR (100),
                       created_at TIMESTAMP DEFAULT NULL,
                       updated_at TIMESTAMP DEFAULT NULL
);