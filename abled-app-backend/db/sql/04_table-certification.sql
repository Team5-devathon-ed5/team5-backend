USE abled;
CREATE TABLE certification (
                                 id integer PRIMARY KEY,
                                 file_url varchar(255),
                                 file_name varchar(255),
                                 file_mime_type varchar(255),
                                 detail text
);