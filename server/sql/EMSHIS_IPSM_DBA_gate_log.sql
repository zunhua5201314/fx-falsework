create table gate_log
(
	id INTEGER not null
		constraint INDEX33557411
			primary key,
	menu VARCHAR(255),
	opt VARCHAR(255),
	uri VARCHAR(255),
	crt_time TIMESTAMP(26,6),
	crt_user VARCHAR(255),
	crt_name VARCHAR(255),
	crt_host VARCHAR(255)
);

INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (23, '用户管理', '删除', '/admin/user/{*}', '2018-11-16 14:33:52.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (24, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:35:39.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (25, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:36:44.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (26, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:39:28.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (27, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:40:56.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (28, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:42:25.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (29, '用户管理', '编辑', '/admin/user/{*}', '2018-11-16 14:43:54.000000', '9', 'admin', '/127.0.0.1:53169');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (30, '角色类型管理', '删除', '/admin/groupType/{*}', '2018-11-16 15:17:57.000000', '9', 'admin', '/127.0.0.1:54049');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (31, '角色类型管理', '删除', '/admin/groupType/{*}', '2018-11-16 15:17:58.000000', '9', 'admin', '/127.0.0.1:54049');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (32, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:51.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (33, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:52.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (34, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:53.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (35, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:54.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (36, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:55.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (37, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-16 15:40:57.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (38, '角色权限管理', '分配菜单', '/admin/group/{*}/authority/menu', '2018-11-16 15:41:54.000000', '9', 'admin', '/127.0.0.1:54428');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (39, '角色类型管理', '新增', '/admin/groupType', '2018-11-16 15:43:08.000000', '9', 'admin', '/127.0.0.1:54454');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (40, '角色权限管理', '新增', '/admin/group', '2018-11-16 15:52:53.000000', '9', 'admin', '/127.0.0.1:54780');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (41, '角色权限管理', '新增', '/admin/group', '2018-11-16 15:53:18.000000', '9', 'admin', '/127.0.0.1:54780');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (42, '角色类型管理', '删除', '/admin/groupType/{*}', '2018-11-16 15:59:20.000000', '9', 'admin', '/127.0.0.1:54909');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (43, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:02:49.000000', '9', 'admin', '/127.0.0.1:56699');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (44, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:03:03.000000', '9', 'admin', '/127.0.0.1:56699');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (45, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:03:18.000000', '9', 'admin', '/127.0.0.1:56699');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (46, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:04:27.000000', '9', 'admin', '/127.0.0.1:56747');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (47, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:04:53.000000', '9', 'admin', '/127.0.0.1:56747');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (48, '菜单管理', '编辑', '/admin/menu/{*}', '2018-11-21 11:05:06.000000', '9', 'admin', '/127.0.0.1:56747');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (49, '角色权限管理', '分配资源', '/admin/group/{*}/authority/element', '2018-11-29 10:29:00.000000', '9', 'admin', '/127.0.0.1:53564');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (50, '角色权限管理', '分配菜单', '/admin/group/{*}/authority/menu', '2018-11-29 10:29:05.000000', '9', 'admin', '/127.0.0.1:53564');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (51, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:29:56.000000', '9', 'admin', '/127.0.0.1:53611');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (52, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:34:34.000000', '9', 'admin', '/127.0.0.1:53688');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (53, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:37:14.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (54, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:37:16.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (55, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:37:17.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (56, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:37:33.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (57, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:38:27.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (58, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:39:24.000000', '9', 'admin', '/127.0.0.1:53745');
INSERT INTO IPSM_DBA.gate_log (id, menu, opt, uri, crt_time, crt_user, crt_name, crt_host) VALUES (59, '用户管理', '编辑', '/admin/user/{*}', '2018-11-29 10:39:48.000000', '9', 'admin', '/127.0.0.1:53745');