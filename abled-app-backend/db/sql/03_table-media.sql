USE abled;
CREATE TABLE `media` (
  `id` integer PRIMARY KEY,
  `lodgingId` integer,
  `fileUrl` varchar(255),
  `fileName` varchar(255),
  `fileMimeType` varchar(255)
);