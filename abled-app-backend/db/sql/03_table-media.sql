USE abled;
CREATE TABLE media (
                         id integer PRIMARY KEY,
                         lodging_id integer,
                         file_url varchar(255),
                         file_name varchar(255),
                         file_mime_type varchar(255)
)