USE abled;

CREATE TABLE `extra` (
  `id` integer PRIMARY KEY,
  `lodgingId` integer,
  `hasWheelchairAccess` boolean DEFAULT false,
  `hasKitchen` boolean DEFAULT false,
  `hasInternet` boolean DEFAULT false,
  `hasTv` boolean DEFAULT false,
  `hasLaundry` boolean DEFAULT false,
  `hasWcAdjust` boolean DEFAULT false,
  `hasShowerAdjust` boolean DEFAULT false,
  `hasParking` boolean DEFAULT false,
  `hasElevator` boolean DEFAULT false
);