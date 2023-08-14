USE abled;
CREATE TABLE lodging (
                           id integer PRIMARY KEY,
                           owner_id integer,
                           guest_capacity integer,
                           price_night decimal,
                           detail varchar(500),
                           longitude decimal,
                           latitude decimal,
                           reputation decimal,
                           check_in_hour varchar(255),
                           check_out_hour varchar(255),
                           created_at timestamp,
                           update_at timestamp
);