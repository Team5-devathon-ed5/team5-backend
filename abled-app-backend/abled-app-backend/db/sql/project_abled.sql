CREATE DATABASE project_abled;
USE project_abled;

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
	postal_code VARCHAR(25) NULL
);

CREATE TABLE lodging (
    id INT AUTO_INCREMENT PRIMARY KEY,
    owner_id INT NOT NULL,
    guest_capacity INT NOT NULL,
    price_per_night DECIMAL(6, 2) NOT NULL,
    detail VARCHAR(5000) NOT NULL,
	longitude DECIMAL(9, 6) NOT NULL,
    latitude DECIMAL(8, 6) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    reputation DECIMAL(2, 1) NOT NULL,
    check_in_hour TIME NOT NULL,
    check_out_hour TIME NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES account(id)
);

CREATE TABLE media (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lodging_id INT NOT NULL,
    file_url VARCHAR(5000) NOT NULL,
    file_name VARCHAR(250) NOT NULL,
    file_mime_type VARCHAR(50) NOT NULL,
    FOREIGN KEY (lodging_id) REFERENCES lodging(id)
);

CREATE TABLE certification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    file_url VARCHAR(5000) NOT NULL,
    file_name VARCHAR(250) NOT NULL,
    file_mime_type VARCHAR(50) NOT NULL,
    detail VARCHAR(5000) NOT NULL
);

CREATE TABLE certification_group (
    id INT AUTO_INCREMENT PRIMARY KEY,
    certification_id INT NOT NULL,
    lodging_id INT NOT NULL,
	FOREIGN KEY (certification_id) REFERENCES certification(id),
    FOREIGN KEY (lodging_id) REFERENCES lodging(id)
);

CREATE TABLE extra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    has_wheelchair_access BOOLEAN DEFAULT FALSE,
    has_kitchen BOOLEAN DEFAULT FALSE,
    has_internet BOOLEAN DEFAULT FALSE,
    has_tv BOOLEAN DEFAULT FALSE,
    has_laundry BOOLEAN DEFAULT FALSE,
    has_wc_adjust BOOLEAN DEFAULT FALSE,
    has_shower_adjust BOOLEAN DEFAULT FALSE
);

CREATE TABLE extra_group (
	id INT AUTO_INCREMENT PRIMARY KEY,
    extra_id INT NOT NULL,
    lodging_id INT NOT NULL,
	FOREIGN KEY (extra_id) REFERENCES extra(id),
    FOREIGN KEY (lodging_id) REFERENCES lodging(id)
);

CREATE TABLE reservation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT NOT NULL,
    lodging_id INT NOT NULL,
    check_in TIMESTAMP NOT NULL,
    check_out TIMESTAMP NOT NULL,
    price DECIMAL(8, 2) NOT NULL,
    has_canceled BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (guest_id) REFERENCES account(id),
    FOREIGN KEY (lodging_id) REFERENCES lodging(id)
);

CREATE TABLE opinion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    detail VARCHAR(5000) NULL,
    points INT NOT NULL,
    author_id INT NOT NULL,
    about_guest_id INT NULL,
    about_experience_id INT NULL,
    published_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL,
    FOREIGN KEY (author_id) REFERENCES account(id),
    FOREIGN KEY (about_guest_id) REFERENCES account(id),
    FOREIGN KEY (about_experience_id) REFERENCES lodging(id)
);

-- Registros en la tabla "account"
INSERT INTO account (name, last_name, phone_code, phone_number, phone_share, image_url, email, account_active, detail, created_at, updated_at, password, country, city, address, postal_code)
VALUES ('John', 'Doe', '+1', '1234567890', TRUE, 'https://example.com/image1.jpg', 'john.doe@example.com', TRUE, 'Account details for John', '2023-08-02 10:00:00', '2023-08-02 10:00:00', 'hashed_password_1', 'United States', 'New York', '123 Main St', '10001'),
       ('Jane', 'Smith', '+44', '7890123456', FALSE, 'https://example.com/image2.jpg', 'jane.smith@example.com', TRUE, 'Account details for Jane', '2023-08-02 11:00:00', '2023-08-02 11:00:00', 'hashed_password_2', 'United Kingdom', 'London', '456 Oak St', 'SW1A 1AA'),
       ('Michael', 'Johnson', '+49', '987654321', TRUE, 'https://example.com/image3.jpg', 'michael.johnson@example.com', FALSE, 'Account details for Michael', '2023-08-02 12:00:00', '2023-08-02 12:00:00', 'hashed_password_3', 'Germany', 'Berlin', '789 Elm St', '10115'),
       ('Emily', 'Williams', '+33', '6543210987', FALSE, 'https://example.com/image4.jpg', 'emily.williams@example.com', TRUE, 'Account details for Emily', '2023-08-02 13:00:00', '2023-08-02 13:00:00', 'hashed_password_4', 'France', 'Paris', '321 Maple St', '75001'),
       ('David', 'Lee', '+61', '478512369', TRUE, 'https://example.com/image5.jpg', 'david.lee@example.com', TRUE, 'Account details for David', '2023-08-02 14:00:00', '2023-08-02 14:00:00', 'hashed_password_5', 'Australia', 'Sydney', '987 Pine St', '2000'),
       ('Sophia', 'Garcia', '+34', '654789321', FALSE, 'https://example.com/image6.jpg', 'sophia.garcia@example.com', FALSE, 'Account details for Sophia', '2023-08-02 15:00:00', '2023-08-02 15:00:00', 'hashed_password_6', 'Spain', 'Barcelona', '456 Oak St', '08001');

-- Registros en la tabla "lodging"
INSERT INTO lodging (owner_id, guest_capacity, price_per_night, detail, longitude, latitude, created_at, updated_at, reputation, check_in_hour, check_out_hour)
VALUES (1, 4, 120.00, 'Beautiful house near Central Park', -73.9653, 40.7829, '2023-08-02 10:00:00', '2023-08-02 10:00:00', 4.8, '14:00:00', '12:00:00'),
       (1, 2, 80.00, 'Cozy apartment in downtown', -74.006, 40.7128, '2023-08-02 11:00:00', '2023-08-02 11:00:00', 4.5, '15:00:00', '11:00:00'),
       (2, 6, 180.00, 'Spacious villa with pool', 0.1278, 51.5074, '2023-08-02 12:00:00', '2023-08-02 12:00:00', 4.9, '13:00:00', '10:00:00'),
       (2, 3, 100.00, 'Charming cottage in the countryside', 10.7522, 53.3498, '2023-08-02 13:00:00', '2023-08-02 13:00:00', 4.6, '16:00:00', '12:00:00'),
       (3, 8, 220.00, 'Luxurious penthouse with city view', 13.4049, 52.5200, '2023-08-02 14:00:00', '2023-08-02 14:00:00', 4.7, '15:00:00', '11:00:00'),
       (3, 4, 140.00, 'Modern loft in trendy neighborhood', 2.3522, 48.8566, '2023-08-02 15:00:00', '2023-08-02 15:00:00', 4.4, '13:00:00', '10:00:00'),
       (4, 2, 90.00, 'Cosy cabin by the lake', -0.1276, 51.5072, '2023-08-02 16:00:00', '2023-08-02 16:00:00', 4.8, '14:00:00', '12:00:00'),
       (4, 5, 160.00, 'Traditional house with garden', -0.1801, 51.4926, '2023-08-02 17:00:00', '2023-08-02 17:00:00', 4.3, '16:00:00', '11:00:00'),
       (5, 10, 280.00, 'Stunning mansion with pool', 151.2093, -33.8688, '2023-08-02 18:00:00', '2023-08-02 18:00:00', 5.0, '15:00:00', '10:00:00'),
       (5, 6, 180.00, 'Rustic farmhouse surrounded by nature', 2.3522, 48.8566, '2023-08-02 19:00:00', '2023-08-02 19:00:00', 4.9, '16:00:00', '11:00:00'),
       (1, 3, 100.00, 'Modern apartment with a view', -73.9866, 40.7488, '2023-08-02 20:00:00', '2023-08-02 20:00:00', 4.7, '14:00:00', '11:00:00'),
       (2, 4, 140.00, 'Chic townhouse in the heart of the city', 0.1278, 51.5074, '2023-08-02 21:00:00', '2023-08-02 21:00:00', 4.6, '15:00:00', '10:00:00'),
       (3, 2, 80.00, 'Comfortable apartment close to attractions', 13.4049, 52.5200, '2023-08-02 22:00:00', '2023-08-02 22:00:00', 4.2, '16:00:00', '11:00:00'),
       (4, 8, 220.00, 'Secluded villa with stunning views', -0.1276, 51.5072, '2023-08-02 23:00:00', '2023-08-02 23:00:00', 4.9, '14:00:00', '12:00:00'),
       (5, 5, 160.00, 'Quaint cottage in the countryside', 151.2093, -33.8688, '2023-08-03 00:00:00', '2023-08-03 00:00:00', 4.5, '15:00:00', '11:00:00'),
       (1, 6, 180.00, 'Elegant penthouse with city skyline', -73.9866, 40.7488, '2023-08-03 01:00:00', '2023-08-03 01:00:00', 4.8, '14:00:00', '12:00:00'),
       (2, 2, 90.00, 'Cozy cabin in the woods', 0.1278, 51.5074, '2023-08-03 02:00:00', '2023-08-03 02:00:00', 4.1, '16:00:00', '11:00:00'),
       (3, 5, 160.00, 'Modern apartment near city center', 13.4049, 52.5200, '2023-08-03 03:00:00', '2023-08-03 03:00:00', 4.7, '15:00:00', '10:00:00');


-- Registros en la tabla "reservation"
INSERT INTO reservation (guest_id, lodging_id, check_in, check_out, price, has_canceled)
VALUES
(6, 1, '2023-08-05 14:00:00', '2023-08-10 11:00:00', 600.00, FALSE),
(3, 2, '2023-08-15 15:00:00', '2023-08-20 10:00:00', 400.00, FALSE),
(5, 3, '2023-09-01 12:00:00', '2023-09-07 11:00:00', 1320.00, FALSE),
(5, 4, '2023-09-10 16:00:00', '2023-09-15 12:00:00', 800.00, FALSE),
(2, 5, '2023-09-20 15:00:00', '2023-09-25 11:00:00', 1080.00, FALSE),
(1, 6, '2023-10-02 14:00:00', '2023-10-08 10:00:00', 1080.00, FALSE),
(4, 7, '2023-10-10 12:00:00', '2023-10-15 11:00:00', 800.00, FALSE),
(3, 8, '2023-10-20 14:00:00', '2023-10-25 10:00:00', 1080.00, FALSE),
(1, 9, '2023-11-01 12:00:00', '2023-11-07 11:00:00', 1320.00, FALSE),
(5, 10, '2023-11-10 16:00:00', '2023-11-15 12:00:00', 800.00, FALSE),
(2, 11, '2023-11-20 15:00:00', '2023-11-25 11:00:00', 1080.00, FALSE),
(2, 12, '2023-12-01 14:00:00', '2023-12-08 10:00:00', 1080.00, FALSE),
(1, 13, '2023-12-10 12:00:00', '2023-12-18 11:00:00', 1760.00, FALSE),
(5, 14, '2023-12-20 16:00:00', '2023-12-25 12:00:00', 800.00, FALSE),
(3, 15, '2023-12-28 15:00:00', '2024-01-02 11:00:00', 720.00, FALSE);


-- Registros en la tabla "certification"
INSERT INTO certification (file_url, file_name, file_mime_type, detail)
VALUES
('https://example.com/certification_file_1.pdf', 'Certification File 1', 'application/pdf', 'Certification details for file 1'),
('https://example.com/certification_img_1.png', 'Certification File 2', 'img/png', 'Certification details for file 2');

-- Registros en la tabla "certification_group"
INSERT INTO certification_group (certification_id, lodging_id)
VALUES
(1, 1),
(2, 2),
(1, 3),
(1, 4),
(2, 5);