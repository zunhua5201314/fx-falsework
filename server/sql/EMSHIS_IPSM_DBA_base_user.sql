create table base_user
(
	id INTEGER not null
		constraint INDEX33557409
			primary key,
	username VARCHAR(255) default 'NULL',
	password VARCHAR(255) default 'NULL',
	name VARCHAR(255) default 'NULL',
	birthday VARCHAR(255) default 'NULL',
	address VARCHAR(255) default 'NULL',
	mobile_phone VARCHAR(255) default 'NULL',
	tel_phone VARCHAR(255) default 'NULL',
	email VARCHAR(255) default 'NULL',
	sex CHAR(2) default 'NULL',
	type CHAR(1) default 'NULL',
	status CHAR(1) default 'NULL',
	description VARCHAR(255) default 'NULL',
	crt_time TIMESTAMP(26,6) default NULL,
	crt_user VARCHAR(255) default 'NULL',
	crt_name VARCHAR(255) default 'NULL',
	crt_host VARCHAR(255) default 'NULL',
	upd_time TIMESTAMP(26,6) default NULL,
	upd_user VARCHAR(255) default 'NULL',
	upd_name VARCHAR(255) default 'NULL',
	upd_host VARCHAR(255) default 'NULL',
	attr1 VARCHAR(255) default 'NULL',
	attr2 VARCHAR(255) default 'NULL',
	attr3 VARCHAR(255) default 'NULL',
	attr4 VARCHAR(255) default 'NULL',
	attr5 VARCHAR(255) default 'NULL',
	attr6 VARCHAR(255) default 'NULL',
	attr7 VARCHAR(255) default 'NULL',
	attr8 VARCHAR(255) default 'NULL'
);

INSERT INTO IPSM_DBA.base_user (id, username, password, name, birthday, address, mobile_phone, tel_phone, email, sex, type, status, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (1, 'admin', '$2a$12$/Xa6YcdVm387zA.EE/6xce11UX.AZBiwerBztXLAVBMhVKQ/pZfNW', 'liwen', '', null, '', null, '', '男', null, null, '', '2020-09-10 16:58:10.327000', '1', 'admin', '127.0.0.1', '2020-10-28 10:08:32.279000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_user (id, username, password, name, birthday, address, mobile_phone, tel_phone, email, sex, type, status, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (4, 'blog', '$2a$12$oAXfILUbaRuD.ccalygnSeGvgg0MKkg9IqKHaYEf.rEtppqB67lrS', 'Mr.Liwen(博主)', '', null, '', null, '', '男', null, null, '12', '2020-10-22 09:51:08.219000', '1', 'admin', '127.0.0.1', '2020-10-22 09:51:08.220000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_user (id, username, password, name, birthday, address, mobile_phone, tel_phone, email, sex, type, status, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (29, 'test', '$2a$12$OjXths1o8rJ3oGykW3yQyuDokbZqMUlp2KXNfgYA2IObA8HVqXlJK', 'test', null, null, null, null, null, '男', null, null, 'test', '2020-08-03 16:48:49.075000', '1', 'admin', '127.0.0.1', '2020-08-04 17:17:42.056000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);