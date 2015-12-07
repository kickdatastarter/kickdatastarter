use kickdatastarter
DROP TABLE IF EXISTS KUser_Studygroup;
DROP TABLE IF EXISTS KReservation;
DROP TABLE IF EXISTS KUser;
DROP TABLE IF EXISTS KComputer;
DROP TABLE IF EXISTS KGroupRoom;
DROP TABLE IF EXISTS KIndividualRoom;
DROP TABLE IF EXISTS KFacility;
DROP TABLE IF EXISTS KRights;
DROP TABLE IF EXISTS KStudygroup;
DROP TABLE IF EXISTS KFacilityType;
DROP TABLE IF EXISTS KRole;

CREATE TABLE KRole(
	id int primary key AUTO_INCREMENT,
	name varchar(500) not null
);

CREATE TABLE KFacilityType(
	id int primary key AUTO_INCREMENT,
	typename VARCHAR(50) not null,
	tablename VARCHAR(50) not null
);

CREATE TABLE KStudygroup (
	id int UNIQUE primary key AUTO_INCREMENT,
	name VARCHAR(1024) NOT NULL
);

CREATE TABLE KRights(
	max_time_per_resv time not null,
	max_resv_per_day int not null,
	max_total_ongoing_resv time not null,
	role int not null,
	foreign key(role) references KRole(id) on DELETE CASCADE on UPDATE CASCADE,
	facilitytype int not null,
	foreign key(facilitytype) references KFacilityType(id) on DELETE CASCADE on UPDATE CASCADE,
	primary key (role, facilitytype)
);

CREATE TABLE KFacility(
	id int primary key,
	type int not null,
	foreign key (type) references KFacilityType(id) on DELETE CASCADE on UPDATE CASCADE,
	capacity int not null
);

CREATE TABLE KIndividualRoom(
	facilityID int primary key,
	foreign key (facilityID) references KFacility (id) on DELETE CASCADE on UPDATE CASCADE,
	name VARCHAR (50)
);

CREATE TABLE KGroupRoom(
	facilityID int primary key,
	foreign key (facilityID) references KFacility (id) on DELETE CASCADE on UPDATE CASCADE,
	name VARCHAR(50) not null
);

CREATE TABLE KComputer(
	facilityID int primary key,
	foreign key (facilityID) references KFacility (id) on DELETE CASCADE on UPDATE CASCADE,
	name VARCHAR(50) not null
);

CREATE TABLE KUser(
	id int primary key AUTO_INCREMENT,
	loginid VARCHAR(20) not null,
	loginPassword VARCHAR(40) not null,
	role int,
	foreign key (role) references KRole (id) on DELETE CASCADE on UPDATE CASCADE,
	name VARCHAR(255)  not null,
	nuid INT UNIQUE
);

CREATE TABLE KReservation (
	id INT UNIQUE primary key AUTO_INCREMENT,
	description VARCHAR(1024) NOT NULL,
	starttime DATETIME NOT NULL,
	endtime DATETIME NOT NULL,
	facility_id INT NOT NULL,
	foreign key (facility_id) references KFacility (id) on UPDATE CASCADE on DELETE CASCADE,
	reserver_id INT NOT NULL,
	foreign key (reserver_id) references KUser (id) on UPDATE CASCADE on DELETE CASCADE,
	group_id INT,
	foreign key (group_id) references KStudygroup (id) on UPDATE CASCADE on DELETE set NULL,
	resvstatus ENUM('RESERVED', 'INUSE', 'FIFS'),
	maintainstatus ENUM('MAINTAINING', 'AVAILABLE')
);

CREATE TABLE KUser_Studygroup (
	userid int,
	foreign key (userid) references KUser (id) on UPDATE CASCADE on DELETE CASCADE,
	groupid int,
	foreign key (groupid) references KStudygroup (id) on UPDATE CASCADE on DELETE CASCADE,
	primary key (userid, groupid)
);

source trigger.sql;
source inserttable.sql;
