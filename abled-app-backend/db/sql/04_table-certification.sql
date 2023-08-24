USE abled;
CREATE TABLE `certification` (
  `id` integer PRIMARY KEY,
  `fileUrl` varchar(255),
  `fileName` varchar(255),
  `fileMimeType` varchar(255),
  `description` text
);