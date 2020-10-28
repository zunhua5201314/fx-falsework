create table auth_client_service
(
	id INTEGER not null
		constraint INDEX33557415
			primary key,
	service_id VARCHAR(255),
	client_id VARCHAR(255),
	description VARCHAR(255),
	crt_time TIMESTAMP(26,6),
	crt_user VARCHAR(255),
	crt_name VARCHAR(255),
	crt_host VARCHAR(255),
	attr1 VARCHAR(255),
	attr2 VARCHAR(255),
	attr3 VARCHAR(255),
	attr4 VARCHAR(255),
	attr5 VARCHAR(255),
	attr6 VARCHAR(255),
	attr7 VARCHAR(255),
	attr8 VARCHAR(255)
);

INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (1, '3', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (2, '2', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (3, '2', '3', null, '2017-12-31 08:58:03.000000', 'null', 'null', 'null', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (16, '1', '7', null, '2018-11-15 19:50:50.055000', '9', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (17, '2', '7', null, '2018-11-15 19:50:50.067000', '9', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (18, '3', '7', null, '2018-11-15 19:50:50.072000', '9', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.auth_client_service (id, service_id, client_id, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (19, '7', '7', null, '2018-11-15 19:50:50.077000', '9', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);