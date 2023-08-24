USE abled;

  ALTER TABLE `reservation` ADD FOREIGN KEY (`guestId`) REFERENCES `users` (`id`);

  ALTER TABLE `reservation` ADD FOREIGN KEY (`updateBy`) REFERENCES `users` (`id`);

  ALTER TABLE `media` ADD FOREIGN KEY (`lodgingId`) REFERENCES `lodging` (`id`);

  ALTER TABLE `lodging` ADD FOREIGN KEY (`ownerId`) REFERENCES `users` (`id`);

  ALTER TABLE `certificationGroup` ADD FOREIGN KEY (`lodgingId`) REFERENCES `lodging` (`id`);

  ALTER TABLE `certificationGroup` ADD FOREIGN KEY (`certificationId`) REFERENCES `certification` (`id`);

  ALTER TABLE `extra` ADD FOREIGN KEY (`lodgingId`) REFERENCES `lodging` (`id`);

  ALTER TABLE `reservation` ADD FOREIGN KEY (`lodgingId`) REFERENCES `lodging` (`id`);

  ALTER TABLE `opinion` ADD FOREIGN KEY (`reservationId`) REFERENCES `reservation` (`id`);

  ALTER TABLE `opinion` ADD FOREIGN KEY (`userId`) REFERENCES `users` (`id`);
