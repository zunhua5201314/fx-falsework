create table base_group_member
(
	id INTEGER not null
		constraint INDEX33557425
			primary key,
	group_id INTEGER,
	user_id INTEGER,
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

INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (54, 4, 29, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (57, 3, 29, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (58, 1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (59, 1, 4, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (60, 1, 29, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_group_member (id, group_id, user_id, description, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (61, 9, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);