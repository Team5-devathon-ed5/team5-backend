USE abled;

 ALTER TABLE `roleGroup` ADD FOREIGN KEY (`roleId`) REFERENCES `role` (`id`);

 ALTER TABLE `roleGroup` ADD FOREIGN KEY (`userId`) REFERENCES `users` (`id`);