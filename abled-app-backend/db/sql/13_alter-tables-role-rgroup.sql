USE abled;

ALTER TABLE roleGroup ADD FOREIGN KEY (idUser) REFERENCES users (id);
ALTER TABLE roleGroup ADD FOREIGN KEY (idRole) REFERENCES role (id);