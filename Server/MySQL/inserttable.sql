use kickdatastarter
INSERT INTO KRole(name)
VALUES ('undergraduate'),
('graduate'),
('doctoral'),
('TA'),
('RA'),
('facilityManager'),
('UserManager');

INSERT INTO  KFacilityType(typename, tablename)
VALUES ('Individual Room', 'kIndividualRoom'),
('Group Room', 'KGroupRoom'),
('Computer', 'KComputer');

INSERT INTO  KStudygroup(name)
VALUES ('StudyGroup1'),
('StudyGroup2'),
('StudyGroup3'),
('StudyGroup4'),
('StudyGroup5');

INSERT INTO  KRights(max_time_per_resv, max_resv_per_day, max_total_ongoing_resv, role, facilitytype)
VALUES
('03:00:00', 1, '60:00:00', 1, 1),
('02:00:00', 2, '60:00:00', 1, 2),
('03:00:00', 1, '60:00:00', 1, 3),
('06:00:00', 2, '60:00:00', 2, 1),
('03:00:00', 2, '60:00:00', 2, 2),
('04:00:00', 2, '90:00:00', 2, 3),
('06:00:00', 2, '60:00:00', 3, 1),
('04:00:00', 3, '90:00:00', 3, 2),
('03:00:00', 2, '60:00:00', 3, 3),
('03:00:00', 2, '60:00:00', 4, 1),
('06:00:00', 2, '60:00:00', 4, 2),
('03:00:00', 2, '60:00:00', 4, 3),
('03:00:00', 2, '60:00:00', 5, 1),
('04:00:00', 2, '60:00:00', 5, 2),
('03:00:00', 2, '60:00:00', 5, 3),
('03:00:00', 2, '60:00:00', 6, 1),
('04:00:00', 2, '60:00:00', 6, 2),
('04:00:00', 3, '60:00:00', 6, 3);

INSERT INTO  KUser(loginid, loginPassword, role, name, nuid)
VALUES
('a.a', 'defaultPass', 1, 'aaa', 001779701),
('b.b', 'defaultPass', 1, 'bbb', 001779702),
('c.c', 'defaultPass', 1, 'ccc', 001779703),
('d.d', 'defaultPass', 1, 'ddd', 001779704),
('e.e', 'defaultPass', 1, 'eee', 001779705),
('f.f', 'defaultPass', 1, 'fff', 001779706),
('j.j', 'defaultPass', 1, 'jjj', 001779707),
('h.h', 'defaultPass', 1, 'hhh', 001779708),
('i.i', 'defaultPass', 1, 'iii', 001779709),
('g.g', 'defaultPass', 1, 'ggg', 001779710),
('k.k', 'defaultPass', 2, 'kkk', 001779711),
('l.l', 'defaultPass', 2, 'lll', 001779712),
('m.m', 'defaultPass', 2, 'mmm', 001779713),
('n.n', 'defaultPass', 2, 'nnn', 001779714),
('o.o', 'defaultPass', 2, 'ooo', 001779715),
('p.p', 'defaultPass', 2, 'ppp', 001779716),
('q.q', 'defaultPass', 2, 'qqq', 001779717),
('r.r', 'defaultPass', 2, 'rrr', 001779718),
('s.s', 'defaultPass', 2, 'sss', 001779719),
('t.t', 'defaultPass', 2, 'ttt', 001779720),
('u.u', 'defaultPass', 3, 'uuu', 001779721),
('v.v', 'defaultPass', 3, 'vvv', 001779722),
('w.w', 'defaultPass', 3, 'www', 001779723),
('x.x', 'defaultPass', 3, 'xxx', 001779724),
('y.y', 'defaultPass', 3, 'yyy', 001779725),
('z.z', 'defaultPass', 3, 'zzz', 001779726),
('a.b', 'defaultPass', 3, 'aab', 001779727),
('a.c', 'defaultPass', 3, 'aac', 001779728),
('a.d', 'defaultPass', 3, 'aad', 001779729),
('a.e', 'defaultPass', 3, 'aae', 001779730),
('a.f', 'defaultPass', 4, 'aaf', 001779731),
('a.g', 'defaultPass', 4, 'aag', 001779732),
('a.h', 'defaultPass', 5, 'aah', 001779733),
('a.i', 'defaultPass', 5, 'aai', 001779734),
('a.j', 'defaultPass', 6, 'aaj', 001779735),
('fmanager', 'defaultPass', 6, 'fmanager', 909878123),
('umanager', 'defaultPass', 7, 'umanager', 909878321);

INSERT INTO  KFacility(id, type, capacity)
VALUES
(1,3,1),
(2,2,8),
(3,3,1),
(4,1,1),
(5,1,1),
(6,3,1),
(7,2,6),
(8,2,4),
(9,2,6),
(10,3,1),
(11,2,8),
(12,1,1),
(13,3,1),
(14,2,4),
(15,1,1),
(16,3,1),
(17,2,8),
(18,1,1),
(19,1,1),
(20,3,1),
(21,3,1),
(22,2,6),
(23,1,1),
(24,1,1),
(25,3,1),
(26,3,1),
(27,1,1),
(28,2,6),
(29,1,1),
(30,2,4);

INSERT INTO  KIndividualRoom(facilityID, name)
VALUES
(4,'In A'),
(5,'In B'),
(12,'In C'),
(15,'In D'),
(18,'In E'),
(19,'In F'),
(23,'In G'),
(24,'In H'),
(27,'In I'),
(29,'In J');

INSERT INTO  KGroupRoom(facilityID, name)
VALUES
(2,'Colab A'),
(7,'Colab B'),
(8,'Colab C'),
(9,'Colab D'),
(11,'Colab E'),
(14,'Colab F'),
(17,'Colab G'),
(22,'Colab H'),
(28,'Colab I'),
(30,'Colab J');

INSERT INTO  KComputer(facilityID, name)
VALUES
(1, 'Mac A'),
(3, 'Mac B'),
(6, 'Linux A'),
(10, 'Win A'),
(13, 'Win B'),
(16, 'Mac C'),
(20, 'Win C'),
(21, 'Linux B'),
(25, 'Mac D'),
(26, 'Win D');

INSERT INTO  KUser_Studygroup(userid, groupid)
VALUES (1,2),
(1,5),
(2,4),
(4,2),
(5,1),
(5,2),
(5,3),
(10,2),
(10,3);

INSERT INTO KReservation (description, starttime, endtime, facility_id, reserver_id, group_id, resvstatus)
VALUES ('REV0', '2015-12-10 03:00:00', '2015-12-10 04:00:00', 7, 1, 2, 'RESERVED'),
('REV1', '2015-12-10 04:00:00', '2015-12-10 06:00:00', 5, 2, null, 'RESERVED'),
('REV2', '2015-12-11 03:00:00', '2015-12-11 04:30:00', 3, 1, null, 'RESERVED'),
('REV3', '2015-12-11 06:00:00', '2015-12-11 08:00:00', 4, 4, null, 'RESERVED');
INSERT INTO KReservation (description, starttime, endtime, facility_id, reserver_id, maintainstatus)
VALUES ('MNG0', '2015-12-11 04:00:00', '2015-12-12 03:00:00', 4, 35, 'MAINTAINING');
