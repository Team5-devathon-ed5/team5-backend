USE abled;
CREATE TABLE reservation (
                               id integer PRIMARY KEY,
                               lodging_id integer,
                               guest_id integer,
                               check_in timestamp,
                               check_out timestamp,
                               price decimal,
                               has_canceled boolean,
                               created_at timestamp,
                               update_at timestamp,
                               update_by integer
);