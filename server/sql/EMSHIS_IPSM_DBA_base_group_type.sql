create table base_group_type
(
	id INTEGER not null
		constraint INDEX33557429
			primary key,
	code VARCHAR(255),
	name VARCHAR(255),
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

INSERT INTO IPSM_DBA.base_group_type (id, code, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (1, 'role', '角色类型', 'role', null, null, null, null, '2017-08-25 17:52:37.000000', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_type (id, code, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (2, 'depart', '部门类型', '部门类型', null, null, null, null, '2020-08-01 19:25:33.984000', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_type (id, code, name, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (3, 'free', '自定义类型', 'sadf', null, null, null, null, '2017-08-26 08:22:25.000000', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);