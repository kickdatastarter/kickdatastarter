use kickdatastarter
#trigger
DROP TRIGGER IF EXISTS CheckTimeConflicts_insert;
delimiter //
CREATE TRIGGER CheckTimeConflicts_insert BEFORE INSERT ON KReservation
FOR EACH ROW
BEGIN
#!!!since starttime is not null, so set it to null will reject insert/update
#BASIC_CHECK(SAME_DAY_REQUIRED)
	IF ( DATE(NEW.starttime) != DATE(NEW.endtime) AND  NEW.maintainstatus IS null) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'BASIC_CHECK(SAME_DAY_REQUIRED)', MYSQL_ERRNO = 1001;
#TIME_CONFLICT_CHECK(IGNORE MAINTAINING)
	ELSEIF ( EXISTS (	SELECT * 
										FROM KReservation r
										WHERE ((r.starttime >= NEW.starttime AND r.starttime < NEW.endtime) OR
											 		 (NEW.starttime >= r.starttime AND NEW.starttime < r.endtime)) AND
													r.facility_id=NEW.facility_id AND NEW.maintainstatus IS null
									)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'TIME_CONFLICT_CHECK(IGNORE MAINTAINING)', MYSQL_ERRNO = 1001;
#FACILITY_CAPACITY_CHECK
	ELSEIF ( (SELECT COUNT(*)
							FROM KUser_Studygroup usg, KUser u
							WHERE usg.userid=u.id AND usg.groupid=NEW.group_id
						) >
					 (SELECT f.capacity
							FROM KFacility f
							WHERE f.id=NEW.facility_id
						)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'FACILITY_CAPACITY_CHECK', MYSQL_ERRNO = 1001;
#MAX_TIME_PER_RESV_CHECK(IGNORE MAINTAINING)
	ELSEIF (TIMEDIFF(NEW.endtime, NEW.starttime) > 
			(SELECT r.max_time_per_resv
				FROM KRights r, KUser u, KFacility f
				WHERE (u.id=NEW.reserver_id AND f.id=NEW.facility_id) AND
				(r.role=u.role AND r.facilitytype=f.type)
			) AND NEW.maintainstatus IS null
		 ) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_TIME_PER_RESV_CHECK', MYSQL_ERRNO = 1001;
#MAX_RESV_PER_DAY_CHECK
	ELSEIF ( (SELECT COUNT(*)
							FROM KUser u, KFacility f, KReservation re
							WHERE (re.facility_id=f.id AND re.reserver_id=u.id AND
							u.id=NEW.reserver_id AND re.resvstatus = 'RESERVED' AND 
							f.type=(SELECT f1.type FROM
									KFacility f1
									WHERE f1.id=NEW.facility_id
								)
							)
						)	+ 1 >
						(SELECT ri.max_resv_per_day FROM
							KFacility f2 ,KUser u2, KRights ri
							WHERE f2.id=NEW.facility_id AND f2.type=ri.facilitytype AND
							NEW.reserver_id=u2.id AND	u2.role=ri.role
						) AND NEW.maintainstatus IS null
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_RESV_PER_DAY_CHECK', MYSQL_ERRNO = 1001;
#MAX_ONGOING_RESV_TOTAL_TIME_CHECK
	ELSEIF ( (SELECT sum(TIMEDIFF(re.endtime, re.starttime))
							FROM KUser u, KFacility f, KReservation re
							WHERE (re.facility_id=f.id AND re.reserver_id=u.id AND
							u.id=NEW.reserver_id AND re.resvstatus = 'RESERVED' AND 
							f.type=(SELECT f1.type FROM
									KFacility f1
									WHERE f1.id=NEW.facility_id
								)
							)
						) + TIMEDIFF(NEW.endtime, NEW.starttime) > 
						(SELECT ri.max_total_ongoing_resv FROM
							KFacility f2 ,KUser u2, KRights ri
							WHERE f2.id=NEW.facility_id AND f2.type=ri.facilitytype AND
							NEW.reserver_id=u2.id AND u2.role=ri.role
						)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_ONGOING_RESV_TOTAL_TIME_CHECK', MYSQL_ERRNO = 1001;
	END IF;
END;//
delimiter ;

DROP TRIGGER IF EXISTS CheckTimeConflicts_update;
delimiter //
CREATE TRIGGER CheckTimeConflicts_update BEFORE UPDATE ON KReservation
FOR EACH ROW
BEGIN
#!!!since starttime is not null, so set it to null will reject insert/update
#BASIC_CHECK(SAME_DAY_REQUIRED)
	IF ( DATE(NEW.starttime) != DATE(NEW.endtime) AND  NEW.maintainstatus IS null) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'BASIC_CHECK(SAME_DAY_REQUIRED)', MYSQL_ERRNO = 1001;
#TIME_CONFLICT_CHECK(IGNORE MAINTAINING)
	ELSEIF ( EXISTS (	SELECT * 
										FROM KReservation r
										WHERE ((r.starttime >= NEW.starttime AND r.starttime < NEW.endtime) OR
											 		 (NEW.starttime >= r.starttime AND NEW.starttime < r.endtime)) AND
													r.facility_id=NEW.facility_id AND NEW.maintainstatus IS null AND
													r.reserver_id!=NEW.reserver_id AND r.id!= NEW.id AND
													NEW.resvstatus!='FIFS'
									)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'TIME_CONFLICT_CHECK(IGNORE MAINTAINING)', MYSQL_ERRNO = 1001;
#FACILITY_CAPACITY_CHECK
	ELSEIF ( (SELECT COUNT(*)
							FROM KUser_Studygroup usg, KUser u
							WHERE usg.userid=u.id AND usg.groupid=NEW.group_id
						) >
					 (SELECT f.capacity
							FROM KFacility f
							WHERE f.id=NEW.facility_id
						)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'FACILITY_CAPACITY_CHECK', MYSQL_ERRNO = 1001;
#MAX_TIME_PER_RESV_CHECK(IGNORE MAINTAINING)
	ELSEIF (TIMEDIFF(NEW.endtime, NEW.starttime) > 
			(SELECT r.max_time_per_resv
				FROM KRights r, KUser u, KFacility f
				WHERE (u.id=NEW.reserver_id AND f.id=NEW.facility_id) AND
				(r.role=u.role AND r.facilitytype=f.type)
			) AND NEW.maintainstatus IS null
		 ) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_TIME_PER_RESV_CHECK', MYSQL_ERRNO = 1001;
#MAX_RESV_PER_DAY_CHECK
	ELSEIF ( (SELECT COUNT(*)
							FROM KUser u, KFacility f, KReservation re
							WHERE (re.facility_id=f.id AND re.reserver_id=u.id AND
								u.id=NEW.reserver_id AND re.resvstatus = 'RESERVED' AND 
								f.type=(SELECT f1.type FROM
									KFacility f1
									WHERE f1.id=NEW.facility_id
								) AND 
								DATE(re.starttime)=DATE(NEW.starttime)
							)
						)	+ (SELECT COUNT(*)
							FROM KReservation re
							WHERE (re.id=NEW.id AND DATE(re.starttime)!=DATE(NEW.starttime))
						) >
						(SELECT ri.max_resv_per_day FROM
							KFacility f2 ,KUser u2, KRights ri
							WHERE f2.id=NEW.facility_id AND f2.type=ri.facilitytype AND
							NEW.reserver_id=u2.id AND	u2.role=ri.role
						) AND NEW.maintainstatus IS null
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_RESV_PER_DAY_CHECK', MYSQL_ERRNO = 1001;
#MAX_ONGOING_RESV_TOTAL_TIME_CHECK
	ELSEIF ( (SELECT sum(TIMEDIFF(re.endtime, re.starttime))
							FROM KUser u, KFacility f, KReservation re
							WHERE (re.facility_id=f.id AND re.reserver_id=u.id AND
							u.id=NEW.reserver_id AND re.resvstatus = 'RESERVED' AND 
							f.type=(SELECT f1.type FROM
									KFacility f1
									WHERE f1.id=NEW.facility_id
								)
							)
						) + TIMEDIFF(NEW.endtime, NEW.starttime) > 
						(SELECT ri.max_total_ongoing_resv FROM
							KFacility f2 ,KUser u2, KRights ri
							WHERE f2.id=NEW.facility_id AND f2.type=ri.facilitytype AND
							NEW.reserver_id=u2.id AND u2.role=ri.role
						)
					) THEN
		SIGNAL SQLSTATE '45000' 
		SET MESSAGE_TEXT = 'MAX_ONGOING_RESV_TOTAL_TIME_CHECK', MYSQL_ERRNO = 1001;
	END IF;
END;//
delimiter ;
