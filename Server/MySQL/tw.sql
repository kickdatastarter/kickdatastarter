CREATE TABLE ACL (
  id INT UNIQUE primary key auto_increment,
  description varchar(1024) NOT NULL,
  starttime DATETIME NOT NULL,
  endtime DATETIME NOT NULL,
  facility_id INT NOT NULL,
  foreign key (facility_id) references Facility (id) on update cAScade on delete cAScade,
  reserver_id INT NOT NULL,
  foreign key (reserver_id) references kUser (id) on update cAScade on delete cAScade,
  group_id INT,
  foreign key (group_id) references Studygroup (id) on update cAScade on delete set NULL,
  resvstatus ENUM('RESERVED', 'INUSE', 'FIFS') ,
  maINTainstatus ENUM(‘MAINTAINING’, ’AVAILABLE’)
);

#K_querry
CREATE OR replace VIEW is_room_available AS(
SELECT *
FROM ACL a, StudyGroup sg, kUser ku, Facility f
WHERE (a.studygroup=sg.id AND a.kuser=ku.id AND a.facility=f.id) AND
(a.starttime<={%STARTTIME} AND a.endtime>={%ENDTIME});

#kuser_insert_update
CREATE OR replace VIEW kuser_insert_update AS(
SELECT a.id, a.description, a.starttime, a.endtime, a.resvstatus
FROM ACL a);

CREATE TRIGGER check_time BEFORE INSERT ON kuser_insert_update
FOR EACH ROW
BEGIN
  IF (DATE(NEW.starttime) != DATE(NEW.endtime)) THEN
    SET NEW.starttime=null
  ENDIF
  IF ( TIMEDIFF(NEW.endtime, NEW.starttime) > 
       (SELECT max_time_per_resv
        FROM kRights r, kUser u, Facility f
        WHERE (u.id=NEW.id AND f.id=NEW.facility)) AND
        (r.role=u.role AND r.facilitytype=f.type)
     ) THEN
    SET NEW.starttime=null
  ENDIF
END;

CREATE TRIGGER check_time BEFORE UPDATE ON kuser_insert_update
FOR EACH ROW
BEGIN
  IF (DATE(NEW.starttime) != DATE(NEW.endtime)) THEN
    SET NEW.starttime=null
  ENDIF
  IF ( MINUTE(NEW.endtime-NEW.starttime) > 
       (SELECT max_time_per_resv
        FROM kRights r, kUser u, Facility f
        WHERE (u.id=NEW.id AND f.id=NEW.facility)) AND
        (r.role=u.role AND r.facilitytype=f.type)
     ) THEN
    SET NEW.starttime=null
  ENDIF
END;

#get max_time_per_resv using role.id,facility.type.id
CREATE OR REPLACE VIEW find_max_time_per_resv AS(
SELECT UNIQUE max_time_per_resv
FROM kRights r, kUser u, Facility f
WHERE (u.id={%USERID} AND f.id={%FACILITYID})) AND (r.role=u.role AND r.facilitytype=f.type)


#kfadm_insert_update
CREATE OR REPLACE VIEW kfadm_insert_update AS(
SELECT a.id, a.description, a.starttime, a.endtime, a.maINTainstatus
FROM ACL a;

#auto_update
CREATE OR REPLACE VIEW kautocheck


