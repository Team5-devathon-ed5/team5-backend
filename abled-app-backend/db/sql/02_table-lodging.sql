USE abled;

CREATE TABLE `lodging` (
  `id` integer PRIMARY KEY,
  `ownerId` integer NOT NULL,
  `guestCapacity` integer NOT NULL,
  `name` varchar(255),
  `category` enum('cottage', 'best-review', 'hotel', 'lodging'),
  `priceNight` decimal(6,2),
  `description` varchar(255),
  `longitude` decimal(9,6) NOT NULL,
  `latitude` decimal(8,6) NOT NULL,
  `reputation` decimal(2,1) NOT NULL,
  `address` varchar(255),
  `city` varchar(255),
  `country` varchar(255),
  `checkInHour` varchar(255) NOT NULL,
  `checkOutHour` varchar(255) NOT NULL,
  `createdAt` timestamp,
  `updateAt` timestamp
);