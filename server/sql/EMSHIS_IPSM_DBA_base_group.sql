create table base_group
(
	id INTEGER not null
		constraint INDEX33557421
			primary key,
	code VARCHAR(255),
	name VARCHAR(255),
	parent_id INTEGER not null,
	path VARCHAR(2000),
	type CHAR(1),
	group_type INTEGER not null,
	description VARCHAR(255),
	crt_time TIMESTAMP(26,6),
	crt_user VARCHAR(255),
	crt_name VARCHAR(255),
	crt_host VARCHAR(255),
	upd_time TIMESTAMP(26,6),
	upd_user VARCHAR(255),
	upd_name VARCHAR(255),
	upd_host VARCHAR(255),
	attr1 VARCHAR(255),
	attr2 VARCHAR(255),
	attr3 VARCHAR(255),
	attr4 VARCHAR(255),
	attr5 VARCHAR(255),
	attr6 VARCHAR(255),
	attr7 VARCHAR(255),
	attr8 VARCHAR(255)
);

INSERT INTO IPSM_DBA.base_group (id, code, name, parent_id, path, type, group_type, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (7, 'financeDepart', '财务部', 6, '/company/financeDepart', null, 2, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group (id, code, name, parent_id, path, type, group_type, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (8, 'hrDepart', '人力资源部', 6, '/company/hrDepart', null, 2, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group (id, code, name, parent_id, path, type, group_type, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (9, 'admin', '管理员', -1, null, null, 1, '管理员', '2020-09-16 16:52:18.174000', '1', 'admin', '127.0.0.1', '2020-09-16 16:52:18.175000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group (id, code, name, parent_id, path, type, group_type, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (10, 'youke', '游客', -1, null, null, 1, 'aaa', '2020-10-26 16:10:36.949000', '1', 'admin', '127.0.0.1', '2020-10-26 16:10:36.949000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);