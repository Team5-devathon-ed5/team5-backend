USE abled;
CREATE TABLE opinion (
                           id integer PRIMARY KEY,
                           author_id integer,
                           reservation_id integer,
                           detail text,
                           points decimal,
                           created_at timestamp,
                           update_at timestamp
);