create table base_menu
(
	id INTEGER not null
		constraint INDEX33557431
			primary key,
	code VARCHAR(255),
	title VARCHAR(255),
	parent_id INTEGER not null,
	href VARCHAR(255),
	icon VARCHAR(255),
	type CHAR(10),
	order_num INTEGER default 0 not null,
	description VARCHAR(255),
	path VARCHAR(500),
	enabled CHAR(1),
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

INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (1, 'userManager', '用户管理', 5, 'com.epri.fx.client.gui.uicomponents.admin.user.UserManagementController', 'yonghuguanli_huaban', 'menu      ', 11, '', '/adminSys/baseManager/userManager', null, '2020-10-14 15:59:15.380000', '1', 'admin', '127.0.0.1', '2020-10-14 15:59:15.381000', '1', 'admin', '127.0.0.1', 'admin/user/index', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (5, 'baseManager', '基础配置管理', 13, '/admin', 'jichupeizhi', 'dirt      ', 2, '', '/adminSys/baseManager', null, '2020-10-14 16:15:25.384000', '1', 'admin', '127.0.0.1', '2020-10-14 16:15:25.384000', '1', 'admin', '127.0.0.1', 'Layout', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (6, 'menuManager', '菜单管理', 5, 'com.epri.fx.client.gui.uicomponents.admin.menu.MenuManagementController', 'jiaoyixulie', 'menu      ', 12, '你大爷', '/adminSys/baseManager/menuManager', null, '2020-09-18 16:53:21.998000', '1', 'admin', '127.0.0.1', '2020-09-18 16:53:21.998000', '1', 'admin', '127.0.0.1', 'admin/menu/index', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (7, 'groupManager', '角色权限管理', 5, 'com.epri.fx.client.gui.uicomponents.admin.group.GroupManagementController', 'gongnengjiaosequanxianguanli', 'menu      ', 13, '', '/adminSys/baseManager/groupManager', null, '2020-08-20 15:45:42.451000', '1', 'admin', '127.0.0.1', '2020-08-20 15:45:42.451000', '1', 'admin', '127.0.0.1', 'admin/group/index', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (8, 'groupTypeManager', '角色类型管理', 5, 'com.epri.fx.client.gui.uicomponents.admin.grouptype.GroupTypeManagementController', 'jiaoseleixing', 'menu      ', 14, '', '/adminSys/baseManager/groupTypeManager', null, '2020-08-20 15:45:48.931000', '1', 'admin', '127.0.0.1', '2020-08-20 15:45:48.931000', '1', 'admin', '127.0.0.1', 'admin/groupType/index', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (13, 'adminSys', '系统菜单', -1, '/base', 'align-justify', 'dirt      ', 0, '', '/adminSys', null, '2020-08-02 18:39:34.519000', '1', 'admin', '127.0.0.1', '2020-08-02 18:39:34.520000', '1', 'admin', '127.0.0.1', 'Layout', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (27, 'gateLogManager', '操作日志', 5, '/admin/gateLog', 'caozuorizhi', 'menu      ', 15, '', '/adminSys/baseManager/gateLogManager', null, '2020-08-20 15:45:55.329000', '1', 'admin', '127.0.0.1', '2020-08-20 15:45:55.329000', '1', 'admin', '127.0.0.1', 'admin/gateLog/index', null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (44, 'home', '主页', 13, 'com.epri.fx.client.gui.uicomponents.home.HomeController', 'home-outline', null, 0, '', 'com.epri.fx.client.gui.uicomponents.home.HomeController', null, '2020-08-03 15:51:48.077000', '1', 'admin', '127.0.0.1', '2020-08-03 15:51:48.077000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (45, 'baseInfo', '基础信息录入', 13, '', 'jichuxinxi', null, 1, '基础信息录入', null, null, '2020-10-14 17:20:25.202000', '1', 'admin', '127.0.0.1', '2020-10-14 17:20:25.202000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (46, 'base', '基础参数', 45, 'com.epri.fx.client.gui.uicomponents.basicInfo.BasicDataSetController', 'jichucanshu', null, 0, '', null, null, '2020-10-14 17:20:04.099000', '1', 'admin', '127.0.0.1', '2020-10-14 17:20:04.099000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (47, '', '煤价差', 45, 'com.epri.fx.client.gui.uicomponents.basicInfo.CoalPriceDiffController', 'Energy-', null, 1, '', null, null, '2020-10-14 17:19:46.835000', '1', 'admin', '127.0.0.1', '2020-10-14 17:19:46.835000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (48, '', '铁路成本', 45, 'com.epri.fx.client.gui.uicomponents.basicInfo.RailwayCostController', 'tieluyunshu', null, 2, '', null, null, '2020-10-14 17:20:41.287000', '1', 'admin', '127.0.0.1', '2020-10-14 17:20:41.287000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);
INSERT INTO IPSM_DBA.base_menu (id, code, title, parent_id, href, icon, type, order_num, description, path, enabled, crt_time, crt_user, crt_name, crt_host, upd_time, upd_user, upd_name, upd_host, attr1, attr2, attr3, attr4, attr5, attr6, attr7, attr8) VALUES (49, '', '现行运价', 45, 'com.epri.fx.client.gui.uicomponents.basicInfo.CurrentFreightController', 'hangzheng', null, 3, '', null, null, '2020-10-14 17:21:09.450000', '1', 'admin', '127.0.0.1', '2020-10-14 17:21:09.450000', '1', 'admin', '127.0.0.1', null, null, null, null, null, null, null, null);