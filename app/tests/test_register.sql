-- User Test Register
INSERT INTO users (name, phoneCode, phoneNumber, phoneShare, password, email, imageUrl, userActive, detail, address, country, forgotPassword, createdAt, updatedAt)
VALUES ('Juan Perez', '+1', '1234567890', TRUE, 'hashed_password_1', 'juan@example.com', 'http://example.com/juan.jpg', TRUE, 'Some details about Juan', '123 Main St, City', 'USA', NULL, NOW(), NULL),
('Maria Lopez', '+44', '9876543210', TRUE, 'hashed_password_2', 'maria@example.com', 'http://example.com/maria.jpg', TRUE, 'Details about Maria', '456 Elm St, Town', 'UK', NULL, NOW(), NULL),
('Carlos Rodriguez', '+33', '7418529630', FALSE, 'hashed_password_3', 'carlos@example.com', NULL, FALSE, 'Description for Carlos', '789 Oak St, Village', 'France', NULL, NOW(), NULL),
('Ana Martinez', '+49', '5551234567', TRUE, 'hashed_password_4', 'ana@example.com', 'http://example.com/ana.jpg', TRUE, 'Ana\'s details', '234 Pine St, Town', 'Germany', NULL, NOW(), NULL),
('David Smith', '+39', '3339876543', TRUE, 'hashed_password_5', 'david@example.com', 'http://example.com/david.jpg', TRUE, 'About David', '567 Maple St, City', 'Italy', NULL, NOW(), NULL),
('Luisa Fernandez', '+34', '666555444', TRUE, 'hashed_password_6', 'luisa@example.com', NULL, FALSE, NULL, '789 Oak St, Village', 'Spain', NULL, NOW(), NULL);


-- Lodging Test Register
INSERT INTO lodging (ownerId, guestCapacity, name, category, priceNight, description, longitude, latitude, reputation, address, city, country, checkInHour, checkOutHour, createdAt, updatedAt)
VALUES (1, 4, 'Cozy Cottage Retreat', 'cottage', 120.00, 'A charming cottage with beautiful countryside views.', -0.123456, 51.987654, 4.5, '123 Cottage Lane', 'Countryside Town', 'United Kingdom', '14:00:00', '11:00:00', NOW(), NULL),
(1, 2, 'Luxury Boutique Hotel', 'hotel', 250.00, 'Experience lavish comfort and exceptional service.', -74.006, 40.7128, 4.9, '789 Luxury Ave', 'Metropolis City', 'United States', '15:00:00', '12:00:00', NOW(), NULL),
(2, 6, 'Seaside Resort Getaway', 'lodging', 180.00, 'Relax on the beach and enjoy oceanfront views.', 2.345678, 48.912345, 4.2, '456 Shoreline Blvd', 'Beachside Town', 'France', '16:00:00', '10:00:00', NOW(), NULL),
(2, 8, 'Tranquil Mountain Lodge', 'lodging', 150.00, 'Escape to the mountains for peace and serenity.', -122.987654, 38.765432, 4.6, '789 Mountain View Rd', 'Mountain Village', 'United States', '14:30:00', '11:30:00', NOW(), NULL),
(3, 3, 'Riverside Cabin Retreat', 'cottage', 90.00, 'Cozy cabin nestled by a picturesque river.', -0.987654, 52.123456, 4.3, '234 Riverside Trail', 'Riverfront Town', 'United Kingdom', '14:00:00', '12:00:00', NOW(), NULL),
(3, 10, 'Urban Loft Living', 'best-review', 180.00, 'Modern loft with stunning city skyline views.', -75.456789, 40.567890, 4.8, '567 Loft Avenue', 'Cityscape City', 'United States', '16:00:00', '11:00:00', NOW(), NULL),
(4, 4, 'Charming Countryside B&B', 'lodging', 120.00, 'Enjoy the charm of the countryside at this B&B.', 1.234567, 47.890123, 4.4, '789 Meadow Lane', 'Rural Village', 'France', '15:00:00', '12:00:00', NOW(), NULL),
(4, 6, 'Secluded Forest Cabin', 'cottage', 110.00, 'Get away from it all in this cabin surrounded by trees.', -122.345678, 36.789012, 4.7, '345 Forest Retreat', 'Woodland Town', 'United States', '15:30:00', '11:30:00', NOW(), NULL),
(5, 4, 'Historic Inn Experience', 'best-review', 200.00, 'Step back in time at this beautifully restored inn.', -0.678901, 51.234567, 4.9, '123 Vintage Street', 'Historic Town', 'United Kingdom', '16:00:00', '12:00:00', NOW(), NULL),
(6, 8, 'Mountain View Resort', 'hotel', 280.00, 'Experience luxury and relaxation with stunning mountain views.', -74.123456, 41.987654, 4.6, '456 Summit Way', 'Mountain Retreat', 'United States', '14:00:00', '10:00:00', NOW(), NULL);



-- Reservation Test Register
INSERT INTO reservation (guestId, lodgingId, checkIn, checkOut, price, hasCanceled, createdAt, updatedAt, updatedBy)
VALUES (1, 1, '2223-08-15 14:00:00', '2223-08-20 11:00:00', 600.00, FALSE, NOW(), NULL, NULL),
(2, 3, '2223-09-10 15:00:00', '2223-09-15 11:00:00', 900.00, FALSE, NOW(), NULL, NULL),
(3, 2, '2223-10-05 16:00:00', '2223-10-10 12:00:00', 1250.00, FALSE, NOW(), NULL, NULL),
(4, 5, '2223-11-20 14:00:00', '2223-11-25 11:00:00', 450.00, FALSE, NOW(), NULL, NULL),
(5, 7, '2223-12-05 15:00:00', '2223-12-10 12:00:00', 720.00, FALSE, NOW(), NULL, NULL),
(6, 4, '2224-01-15 14:00:00', '2224-01-20 10:00:00', 600.00, FALSE, NOW(), NULL, NULL),
(4, 6, '2224-02-10 15:00:00', '2224-02-15 11:00:00', 900.00, TRUE, NOW(), NULL, NULL),
(4, 8, '2224-03-05 16:00:00', '2224-03-10 12:00:00', 1250.00, TRUE, NOW(), NULL, NULL);