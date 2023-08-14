USE abled;

  ALTER TABLE reservation ADD FOREIGN KEY (guest_id) REFERENCES users (id);
  ALTER TABLE reservation ADD FOREIGN KEY (update_by) REFERENCES users (id);
  ALTER TABLE media ADD FOREIGN KEY (lodging_id) REFERENCES lodging (id);
  ALTER TABLE lodging ADD FOREIGN KEY (owner_id) REFERENCES users (id);
  ALTER TABLE certification_group ADD FOREIGN KEY (lodging_id) REFERENCES lodging (id);
  ALTER TABLE certification_group ADD FOREIGN KEY (certification_id) REFERENCES certification (id);
  ALTER TABLE extra_group ADD FOREIGN KEY (lodging_id) REFERENCES lodging (id);
  ALTER TABLE extra_group ADD FOREIGN KEY (extra_id) REFERENCES extra (id);
  ALTER TABLE reservation ADD FOREIGN KEY (lodging_id) REFERENCES lodging(id);
  ALTER TABLE opinion ADD FOREIGN KEY (reservation_id) REFERENCES reservation (id);
  ALTER TABLE opinion ADD FOREIGN KEY (author_id) REFERENCES users (id);