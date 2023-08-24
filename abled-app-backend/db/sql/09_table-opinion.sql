USE abled;
CREATE TABLE `opinion` (
  `id` integer PRIMARY KEY,
  `userId` integer,
  `reservationId` integer,
  `description` text,
  `rating` decimal(2,1),
  `created_at` timestamp DEFAULT "now()",
  `update_at` timestamp
);