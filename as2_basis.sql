create database if not exists as2;
use as2;
drop table if exists Parameter;
drop table if exists Method;
drop table if exists ServerUsage;
drop table if exists Server;
drop table if exists ServerType;

create table ServerType (
  id int primary key auto_increment,
  name varchar(100) not null unique,
  description varchar(255)
);
insert into ServerType(name,description)
values ('database server','database server desc'),
('content management','content management'),
('load balance','load blance'),
('def','st-def');

create table Server (
  id int primary key auto_increment,
  name varchar(255) not null unique,
  specification varchar(5000),
  type int not null,
  foreign key(type) references ServerType(id) on update cascade on delete no action
); 
insert into Server(name,specification,type)
values ('aaa','st-abc',1),
('bbb','sta-bcd',2),
('ccc','sta-def',3),
('ddd',null,1),
('eee',null,2),
('fff',null,3);

create table ServerUsage (
  usesServer int not null,
  foreign key(usesServer) references Server(id) on update cascade on delete no action,
  usedByServer int not null,
  foreign key(usedByServer) references Server(id) on update cascade on delete no action,
  primary key(usesServer, usedByServer)
);
insert into ServerUsage(usesServer, usedByServer)
values (1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,3),
(2,4),
(2,5),
(3,1),
(3,2),
(3,3),
(3,4),
(4,5),
(4,6),
(5,2),
(5,4),
(5,6);

create table Method (
  id int primary key auto_increment,
  name varchar(255) not null,
  partOf int not null,
  foreign key(partOf) references Server(id) on update cascade on delete cascade,
  unique(partOf, name)
);
insert into Method(name,partOf)
values ('m1',1),
('m1',2),
('m2',2),
('m1',3),
('m2',3),
('m1',4),
('m2',4),
('m3',4),
('m2',5),
('m3',5),
('m4',5),
('m5',5),
('m2',6),
('m3',6);

create table Parameter (
  id int primary key auto_increment,
  name varchar(255) not null,
  usedBy int not null,
  foreign key(usedBy) references Method(id) on update cascade on delete cascade,
  unique(usedBy, name),
  type varchar(255) not null,
  alternative enum('in', 'out', 'inout') not null
);
insert into Parameter(name, usedBy, type, alternative)
values ('p1-mid1',1,'Document','out'),
('p2-mid1',1,'Integer','in'),
('p3-mid1',1,'String','out'),
('p1-mid2',2,'Float','out'),
('p2-mid2',2,'Double','in'),
('p1-mid3',3,'HashMap','out'),
('p2-mid3',3,'Double','out'),
('p3-mid3',3,'Integer','in'),
('p4-mid3',3,'Document','inout'),
('p1-mid5',5,'String','in'),
('p2-mid5',5,'Integer','in'),
('p3-mid5',5,'Float','out'),
('p4-mid5',5,'Double','in'),
('p5-mid5',5,'Integer','in'),
('p1-mid6',6,'Integer','in'),
('p2-mid6',6,'String','in'),
('p1-mid7',7,'HashMap','in'),
('p1-mid8',8,'Integer','in'),
('p1-mid9',9,'Document','out'),
('p2-mid9',9,'String','in'),
('p3-mid9',9,'Document','out'),
('p1-mid10',10,'Integer','inout'),
('p1-mid11',11,'Double','in'),
('p1-mid12',12,'String','in'),
('p1-mid13',13,'Integer','out'),
('p2-mid13',13,'String','inout'),
('p3-mid13',13,'Float','in');
