USE abled;

ALTER TABLE role_group ADD FOREIGN KEY (id_user) REFERENCES users (id);
ALTER TABLE role_group ADD FOREIGN KEY (id_role) REFERENCES role (id);