create table base_element
(
	id INTEGER not null
		constraint INDEX33557417
			primary key,
	code VARCHAR(255),
	type VARCHAR(255),
	name VARCHAR(255),
	uri VARCHAR(255),
	menu_id VARCHAR(255),
	parent_id VARCHAR(255),
	path VARCHAR(2000),
	method VARCHAR(10),
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

INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (4, 'menuManager:element', 'uri', '按钮页面', '/admin/element', '6', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (5, 'menuManager:btn_add', 'button', '新增', '/admin/menu/{*}', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (6, 'menuManager:btn_edit', 'button', '编辑', '/admin/menu/{*}', '6', '', '', 'PUT', '', '2017-06-24 00:00:00.000000', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (7, 'menuManager:btn_del', 'button', '删除', '/admin/menu/{*}', '6', '', '', 'DELETE', '', '2017-06-24 00:00:00.000000', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (8, 'menuManager:btn_element_add', 'button', '新增元素', '/admin/element', '6', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (9, 'menuManager:btn_element_edit', 'button', '编辑元素', '/admin/element/{*}', '6', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (10, 'menuManager:btn_element_del', 'button', '删除元素', '/admin/element/{*}', '6', null, null, 'DELETE', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (12, 'groupManager:btn_edit', 'button', '编辑', '/admin/group/{*}', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (14, 'groupManager:btn_userManager', 'button', '分配用户', '/admin/group/{*}/user', '7', null, null, 'PUT', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (15, 'groupManager:btn_resourceManager', 'button', '分配权限', '/admin/group/{*}/authority', '7', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (16, 'groupManager:menu', 'uri', '分配菜单', '/admin/group/{*}/authority/menu', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (17, 'groupManager:element', 'uri', '分配资源', '/admin/group/{*}/authority/element', '7', null, null, 'POST', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (19, 'menuManager:view', 'uri', '查看', '/admin/menu/{*}', '6', '', '', 'GET', '', '2017-06-26 00:00:00.000000', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (20, 'menuManager:element_view', 'uri', '查看', '/admin/element/{*}', '6', null, null, 'GET', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (22, 'groupTypeManager:view', 'uri', '查看', '/admin/groupType/{*}', '8', null, null, 'GET', '', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (23, 'groupTypeManager:btn_add', 'button', '新增', '/admin/groupType', '8', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (24, 'groupTypeManager:btn_edit', 'button', '编辑', '/admin/groupType/{*}', '8', null, null, 'PUT', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (25, 'groupTypeManager:btn_del', 'button', '删除', '/admin/groupType/{*}', '8', null, null, 'DELETE', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (27, 'serviceManager:view', 'URI', '查看', '/auth/service/{*}', '10', null, null, 'GET', null, '2017-12-26 20:17:42.000000', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (28, 'serviceManager:btn_add', 'button', '新增', '/auth/service', '10', null, null, 'POST', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (29, 'serviceManager:btn_edit', 'button', '编辑', '/auth/service/{*}', '10', null, null, 'PUT', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (30, 'serviceManager:btn_del', 'button', '删除', '/auth/service/{*}', '10', null, null, 'DELETE', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (31, 'serviceManager:btn_clientManager', 'button', '服务授权', '/auth/service/{*}/client', '10', null, null, 'POST', null, '2017-12-30 16:32:48.000000', '1', 'Mr.AG', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (34, 'home', 'uri', '查看', 'home/', '16', null, null, 'GET', null, '2018-11-08 17:50:05.437000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_element (id, code, type, name, uri, menu_id, parent_id, path, method, description, crt_time, crt_user, crt_name, crt_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (37, 'userManager:btn_add', 'button', '添加', '', '1', null, null, 'POST', '', '2020-09-17 09:31:03.257000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);