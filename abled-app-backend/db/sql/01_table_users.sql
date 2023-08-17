CREATE DATABASE abled;
USE abled;

CREATE TABLE users (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250),
                       phoneCode VARCHAR(5)NULL,
                       phoneNumber VARCHAR(15) NULL,
                       phoneShare BOOLEAN DEFAULT FALSE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       imageUrl VARCHAR(5000) NULL,
                       userActive BOOLEAN DEFAULT FALSE,
                       detail VARCHAR(2500) NULL,
                       address VARCHAR(255) NULL,
                       country VARCHAR(50)NULL,
                       forgotPassword VARCHAR (100),
                       createdAt TIMESTAMP DEFAULT NULL,
                       updatedAt TIMESTAMP DEFAULT NULL
);