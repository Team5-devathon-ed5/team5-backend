USE abled;
CREATE TABLE `reservation` (
  `id` integer PRIMARY KEY,
  `lodgingId` integer,
  `guestId` integer,
  `checkIn` datetime,
  `checkOut` datetime,
  `price` decimal(8,2),
  `hasCanceled` boolean DEFAULT false,
  `createdAt` timestamp DEFAULT "now()",
  `updateAt` timestamp,
  `updateBy` integer
);