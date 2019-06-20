/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : sys_manage

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2019-06-20 16:19:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `p_val` varchar(32) DEFAULT NULL COMMENT '上级val',
  `dic_code` varchar(32) DEFAULT NULL COMMENT '中文编码',
  `dic_var` varchar(32) DEFAULT NULL COMMENT '上级val_英文缩写,代码中，以此为常量名称',
  `dic_val` varchar(32) DEFAULT NULL,
  `has_child` int(1) DEFAULT NULL COMMENT '0没有1有',
  `data_type` varchar(10) DEFAULT NULL COMMENT 'int,Stirng',
  `dic_sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', '', '菜单类型', 'menuType', 'menuType', '1', '', '100');
INSERT INTO `sys_dict` VALUES ('2', '1', 'menuType', '菜单', 'menuType_m', 'm', '0', 'String', '1');
INSERT INTO `sys_dict` VALUES ('3', '1', 'menuType', '权限项', 'menuType_mo', 'mo', '0', 'String', '2');
INSERT INTO `sys_dict` VALUES ('4', '0', ' ', '数据有效性', 'dataState', ' ', '1', ' ', '100');
INSERT INTO `sys_dict` VALUES ('5', '4', 'dataState', '有效', 'dataState_valid', '1', '0', 'int', '1');
INSERT INTO `sys_dict` VALUES ('6', '4', 'dataState', '无效', 'dataState_invalid', '0', '0', 'int', '2');
INSERT INTO `sys_dict` VALUES ('7', '0', 'smsType', '短信类型', 'smsType', ' ', '1', 'int', '100');
INSERT INTO `sys_dict` VALUES ('8', '7', 'smsType', '登录', 'smsType_login', '1', '0', 'int', '1');
INSERT INTO `sys_dict` VALUES ('9', '7', 'smsType', '登出', 'smsType_login_out', '0', '2', 'int', '2');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(32) NOT NULL COMMENT '菜单名称',
  `parent_id` int(11) NOT NULL COMMENT '父级id，root级为0',
  `menu_type` varchar(2) NOT NULL COMMENT '菜单类型：m菜单、mo权限【字典】',
  `permission` varchar(64) DEFAULT NULL COMMENT '权限标识：sys:menu:edit',
  `menu_url` varchar(64) DEFAULT NULL COMMENT '菜单路径',
  `menu_icon` varchar(64) DEFAULT NULL COMMENT '菜单图标',
  `menu_level` int(1) DEFAULT '0' COMMENT '菜单级别，默认0开始',
  `is_parent` int(1) DEFAULT '0' COMMENT '是否是父级1是0不是',
  `menu_sort` int(11) DEFAULT '0' COMMENT '排序默认0',
  `menu_state` int(1) DEFAULT '0' COMMENT '0无效1有效',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `add_operator_id` int(11) NOT NULL,
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `modify_operator_id` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0' COMMENT '!0删除',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', 'm', '', null, null, '1', '1', '1', '1', '2019-06-17 10:15:32', '1', null, null, '0');
INSERT INTO `sys_menu` VALUES ('2', '运营管理', '0', 'm', null, null, null, '1', '1', '2', '1', '2019-06-17 11:42:41', '1', null, null, '0');
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '1', 'm', null, '/sys/role/list', null, '2', '0', '2', '1', '2019-06-17 11:43:23', '1', null, null, '0');
INSERT INTO `sys_menu` VALUES ('4', '员工管理', '1', 'm', null, '/sys/operator/list', null, '2', '0', '1', '0', '2019-06-17 15:14:00', '1', null, null, '0');
INSERT INTO `sys_menu` VALUES ('5', '财务管理', '0', 'm', null, null, null, '0', '1', '2', '1', '2019-06-18 15:55:55', '1', null, null, '0');
INSERT INTO `sys_menu` VALUES ('10', '报表管理', '5', 'm', null, null, null, '0', '0', '0', '1', '2019-06-18 16:05:19', '1', null, null, '0');

-- ----------------------------
-- Table structure for `sys_operator`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator`;
CREATE TABLE `sys_operator` (
  `op_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(11) NOT NULL COMMENT '手机号',
  `login_pwd` varchar(64) NOT NULL COMMENT '默认 123456',
  `real_name` varchar(32) NOT NULL COMMENT '真实姓名',
  `operator_state` int(1) NOT NULL COMMENT '1启用0禁用',
  `session_id` varchar(64) DEFAULT NULL COMMENT '系统分配session id',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '最后一次登录ip',
  `last_login_time` datetime DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `add_operator_id` int(11) NOT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_operator_id` int(11) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0' COMMENT '!0删除',
  PRIMARY KEY (`op_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_operator
-- ----------------------------
INSERT INTO `sys_operator` VALUES ('1', '17607184395', '123456', '黄中发', '1', '8158bd54-1f16-4cd7-8db8-017cd0ac54ca', '0:0:0:0:0:0:0:1', '2019-06-19 17:25:32', '2019-06-15 15:08:18', '1', null, null, '0');
INSERT INTO `sys_operator` VALUES ('2', '17607184396', 'adae26ca0d33a6a103cae57394bcb6221a13cb716d3f0d9031cf2b650c772efa', '张三', '1', null, null, null, '2019-06-18 10:37:07', '1', '2019-06-18 10:39:55', '1', '0');

-- ----------------------------
-- Table structure for `sys_operator_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_login_log`;
CREATE TABLE `sys_operator_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `op_id` int(11) NOT NULL COMMENT '人员id',
  `session_id` varchar(64) NOT NULL COMMENT 'HTTPSESSIONID 或 终端SESSIONID',
  `login_type` int(1) NOT NULL COMMENT '1-登录，0-登出【字典】',
  `ip` varchar(32) NOT NULL,
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_operator_login_log
-- ----------------------------
INSERT INTO `sys_operator_login_log` VALUES ('1', '1', 'c335265f-11ef-4aff-89ed-0fe22bd19bd4', '1', '0:0:0:0:0:0:0:1', '2019-06-15 19:58:57');
INSERT INTO `sys_operator_login_log` VALUES ('2', '1', '6b3ea034-b0c0-4bd7-8fff-0a97d2170788', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:03:29');
INSERT INTO `sys_operator_login_log` VALUES ('3', '1', '2053c9cc-5764-4e32-8849-051b0c31e752', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:04:52');
INSERT INTO `sys_operator_login_log` VALUES ('4', '1', 'e19a3f24-256f-406a-958f-00e54811682d', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:06:06');
INSERT INTO `sys_operator_login_log` VALUES ('5', '1', 'ed793319-040e-4b66-88bd-d207ef418135', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:06:32');
INSERT INTO `sys_operator_login_log` VALUES ('6', '1', '13451411-9b3c-483a-a6e5-a2cca64224e3', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:09:16');
INSERT INTO `sys_operator_login_log` VALUES ('7', '1', '7a3e0226-f87e-4d5e-8369-9ad27f878f6f', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:12:13');
INSERT INTO `sys_operator_login_log` VALUES ('8', '1', 'a7627400-ca7d-43b1-a311-102330991df0', '1', '0:0:0:0:0:0:0:1', '2019-06-15 20:13:40');
INSERT INTO `sys_operator_login_log` VALUES ('9', '1', 'daec8eef-9a45-4053-90d3-4ce828d79ea0', '1', '0:0:0:0:0:0:0:1', '2019-06-17 15:18:57');
INSERT INTO `sys_operator_login_log` VALUES ('10', '1', '971ec0be-1b10-449f-90f4-e085344c68a8', '1', '0:0:0:0:0:0:0:1', '2019-06-17 15:28:19');
INSERT INTO `sys_operator_login_log` VALUES ('11', '1', '971ec0be-1b10-449f-90f4-e085344c68a8', '1', '0:0:0:0:0:0:0:1', '2019-06-17 15:31:50');
INSERT INTO `sys_operator_login_log` VALUES ('12', '1', '971ec0be-1b10-449f-90f4-e085344c68a8', '1', '0:0:0:0:0:0:0:1', '2019-06-17 15:39:30');
INSERT INTO `sys_operator_login_log` VALUES ('13', '1', 'fc9b509f-1012-4f68-a98e-d1ab0c774ecf', '1', '0:0:0:0:0:0:0:1', '2019-06-17 16:22:41');
INSERT INTO `sys_operator_login_log` VALUES ('14', '1', '6af43960-4e9c-4d9e-a4e2-26db595a8302', '1', '0:0:0:0:0:0:0:1', '2019-06-17 16:28:23');
INSERT INTO `sys_operator_login_log` VALUES ('15', '1', '937a3a2f-f082-4d7d-a207-3b979bba51ec', '1', '0:0:0:0:0:0:0:1', '2019-06-17 16:31:10');
INSERT INTO `sys_operator_login_log` VALUES ('16', '1', 'b8953a9a-9c41-489e-b159-a33fc86d1f14', '1', '0:0:0:0:0:0:0:1', '2019-06-17 16:32:47');
INSERT INTO `sys_operator_login_log` VALUES ('17', '1', '711ba291-69b6-4faa-a271-0667e1b220ef', '1', '0:0:0:0:0:0:0:1', '2019-06-17 17:10:42');
INSERT INTO `sys_operator_login_log` VALUES ('18', '1', '8d51933a-9136-4283-af90-c1f79dedc503', '1', '0:0:0:0:0:0:0:1', '2019-06-17 17:48:56');
INSERT INTO `sys_operator_login_log` VALUES ('19', '1', '8d51933a-9136-4283-af90-c1f79dedc503', '1', '0:0:0:0:0:0:0:1', '2019-06-17 17:56:29');
INSERT INTO `sys_operator_login_log` VALUES ('20', '1', '49c389fa-fb02-4fa9-9289-2ca2c3083182', '1', '0:0:0:0:0:0:0:1', '2019-06-18 09:31:10');
INSERT INTO `sys_operator_login_log` VALUES ('21', '1', 'f4c546a7-c2e9-49aa-807b-5b962288dbf2', '1', '0:0:0:0:0:0:0:1', '2019-06-18 09:41:19');
INSERT INTO `sys_operator_login_log` VALUES ('22', '1', '02ff97e1-a9c3-4615-b951-8f143f86b402', '1', '0:0:0:0:0:0:0:1', '2019-06-18 09:44:32');
INSERT INTO `sys_operator_login_log` VALUES ('23', '1', 'ece6ac43-bf9c-4092-b6be-2ab2fdec417d', '1', '0:0:0:0:0:0:0:1', '2019-06-18 09:46:19');
INSERT INTO `sys_operator_login_log` VALUES ('24', '1', 'a28f20eb-f0a5-43f3-ac30-d9844d856f21', '1', '0:0:0:0:0:0:0:1', '2019-06-18 10:21:40');
INSERT INTO `sys_operator_login_log` VALUES ('25', '1', '7e1c71cf-cd16-42c2-9b2a-d8fc68e8d101', '1', '0:0:0:0:0:0:0:1', '2019-06-18 10:32:45');
INSERT INTO `sys_operator_login_log` VALUES ('26', '1', '7400bf03-1683-4127-89ee-4a874c146e28', '1', '0:0:0:0:0:0:0:1', '2019-06-18 10:37:03');
INSERT INTO `sys_operator_login_log` VALUES ('27', '1', '97dfcaab-aee7-43aa-b3db-b6e3d9dcdd69', '1', '0:0:0:0:0:0:0:1', '2019-06-18 10:47:14');
INSERT INTO `sys_operator_login_log` VALUES ('28', '1', 'b4dd5b36-4cfb-4d0c-85f7-e91aaf268a14', '1', '0:0:0:0:0:0:0:1', '2019-06-18 11:08:38');
INSERT INTO `sys_operator_login_log` VALUES ('29', '1', '7faf6cc8-2625-4e1b-add6-7510e2e1e975', '1', '0:0:0:0:0:0:0:1', '2019-06-18 11:14:32');
INSERT INTO `sys_operator_login_log` VALUES ('30', '1', '4cdb3067-dd9a-4b4a-bdf4-7ba29fa17db5', '1', '0:0:0:0:0:0:0:1', '2019-06-18 14:00:19');
INSERT INTO `sys_operator_login_log` VALUES ('31', '1', '965b1154-9629-4669-ade2-71fddfcc1cde', '1', '0:0:0:0:0:0:0:1', '2019-06-18 15:52:20');
INSERT INTO `sys_operator_login_log` VALUES ('32', '1', '721d1e4a-a04d-4323-9a6d-70503e3c5213', '1', '0:0:0:0:0:0:0:1', '2019-06-18 16:10:24');
INSERT INTO `sys_operator_login_log` VALUES ('33', '1', '092fc746-e999-44a1-87d3-2920f0356f50', '1', '0:0:0:0:0:0:0:1', '2019-06-18 16:12:32');
INSERT INTO `sys_operator_login_log` VALUES ('34', '1', '55230bd6-7f76-4633-bad7-f3c364a8d579', '1', '0:0:0:0:0:0:0:1', '2019-06-18 17:39:13');
INSERT INTO `sys_operator_login_log` VALUES ('35', '1', 'ebfa387c-0cc7-4179-95ef-7aa0316f4521', '1', '0:0:0:0:0:0:0:1', '2019-06-19 09:45:26');
INSERT INTO `sys_operator_login_log` VALUES ('36', '1', 'd4d4a1ea-394c-4f81-970e-9a781a7048f9', '1', '0:0:0:0:0:0:0:1', '2019-06-19 09:48:32');
INSERT INTO `sys_operator_login_log` VALUES ('37', '1', 'af044fca-479e-4fe0-9615-2f840395e2d9', '1', '0:0:0:0:0:0:0:1', '2019-06-19 09:52:55');
INSERT INTO `sys_operator_login_log` VALUES ('38', '1', 'd406f53c-5428-428c-81e0-a233cab121ca', '1', '0:0:0:0:0:0:0:1', '2019-06-19 09:57:17');
INSERT INTO `sys_operator_login_log` VALUES ('39', '1', '1ac851c1-f582-43f6-94d2-bb016eee1022', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:07:52');
INSERT INTO `sys_operator_login_log` VALUES ('40', '1', '7dcf9710-dcdb-4ff5-99ed-1e12c7011a70', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:10:48');
INSERT INTO `sys_operator_login_log` VALUES ('41', '1', '23a07d78-34ae-44be-8338-c21595025c71', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:11:46');
INSERT INTO `sys_operator_login_log` VALUES ('42', '1', '88d7d5b8-59f6-4f39-8f18-1f4b562cc7a3', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:16:37');
INSERT INTO `sys_operator_login_log` VALUES ('43', '1', '82fd9f44-56ba-4bb2-9476-48aff14d5091', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:23:29');
INSERT INTO `sys_operator_login_log` VALUES ('44', '1', 'ea546bf3-a373-4d13-be2a-374c987fa3e5', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:26:01');
INSERT INTO `sys_operator_login_log` VALUES ('45', '1', 'f22bd57d-40b0-43f4-9489-27a52882a1cc', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:32:31');
INSERT INTO `sys_operator_login_log` VALUES ('46', '1', '93f518b3-9fa9-4212-abef-037e6c3b62f5', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:41:49');
INSERT INTO `sys_operator_login_log` VALUES ('47', '1', '15bf748c-d7a0-4566-87e2-05423003ca0e', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:51:19');
INSERT INTO `sys_operator_login_log` VALUES ('48', '1', '0d4f537c-8b2d-401a-b2d9-b454229da6b8', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:54:39');
INSERT INTO `sys_operator_login_log` VALUES ('49', '1', 'ad605532-dffb-4dc2-8a94-5f1eb5584200', '1', '0:0:0:0:0:0:0:1', '2019-06-19 10:58:56');
INSERT INTO `sys_operator_login_log` VALUES ('50', '1', 'f1d3a298-c96f-4d9a-a453-8b1d3b8467bd', '1', '0:0:0:0:0:0:0:1', '2019-06-19 11:10:09');
INSERT INTO `sys_operator_login_log` VALUES ('51', '1', 'b9e752a7-9422-45b8-b4cc-d886d3b11962', '1', '0:0:0:0:0:0:0:1', '2019-06-19 11:19:17');
INSERT INTO `sys_operator_login_log` VALUES ('52', '1', '3faf9f19-f02d-4cf6-9194-0bb1695945f7', '1', '127.0.0.1', '2019-06-19 11:24:25');
INSERT INTO `sys_operator_login_log` VALUES ('53', '1', '3faf9f19-f02d-4cf6-9194-0bb1695945f7', '1', '127.0.0.1', '2019-06-19 11:24:35');
INSERT INTO `sys_operator_login_log` VALUES ('54', '1', 'ce6ba656-4474-4eef-b349-caf5e2b1c42e', '1', '0:0:0:0:0:0:0:1', '2019-06-19 11:28:42');
INSERT INTO `sys_operator_login_log` VALUES ('55', '1', '4a771ebf-86fc-43c3-ba98-012c906b4aa6', '1', '0:0:0:0:0:0:0:1', '2019-06-19 11:35:10');
INSERT INTO `sys_operator_login_log` VALUES ('56', '1', '063d8674-3768-436f-a3c3-e5c933640eb7', '1', '0:0:0:0:0:0:0:1', '2019-06-19 11:38:39');
INSERT INTO `sys_operator_login_log` VALUES ('57', '1', '47a6bcd7-0ff1-45ac-be46-fc6397f0be8b', '1', '0:0:0:0:0:0:0:1', '2019-06-19 15:28:42');
INSERT INTO `sys_operator_login_log` VALUES ('58', '1', '9e0c153c-5f61-4754-b142-059f695f6f87', '1', '0:0:0:0:0:0:0:1', '2019-06-19 15:34:24');
INSERT INTO `sys_operator_login_log` VALUES ('59', '1', '4300b252-817a-4354-b177-29246cc75f11', '1', '0:0:0:0:0:0:0:1', '2019-06-19 15:37:41');
INSERT INTO `sys_operator_login_log` VALUES ('60', '1', 'b178a9fd-053a-412d-bc17-05b5d5b295ce', '1', '0:0:0:0:0:0:0:1', '2019-06-19 15:42:51');
INSERT INTO `sys_operator_login_log` VALUES ('61', '1', 'ef0a4e14-f0df-4cbb-ab22-acf44352438d', '1', '0:0:0:0:0:0:0:1', '2019-06-19 16:11:55');
INSERT INTO `sys_operator_login_log` VALUES ('62', '1', '8c019a60-b358-47b2-8d89-dc9d84e68693', '1', '0:0:0:0:0:0:0:1', '2019-06-19 16:49:23');
INSERT INTO `sys_operator_login_log` VALUES ('63', '1', '7d6ac1a7-0118-46f5-800c-c6373c9b28d2', '1', '0:0:0:0:0:0:0:1', '2019-06-19 17:15:52');
INSERT INTO `sys_operator_login_log` VALUES ('64', '1', '78c645aa-3e67-4a76-8174-4cbccb75872d', '1', '0:0:0:0:0:0:0:1', '2019-06-19 17:23:03');
INSERT INTO `sys_operator_login_log` VALUES ('65', '1', 'e67c765f-7d36-44d6-bf3d-645ba9fd21c2', '1', '0:0:0:0:0:0:0:1', '2019-06-19 17:24:00');
INSERT INTO `sys_operator_login_log` VALUES ('66', '1', '8158bd54-1f16-4cd7-8db8-017cd0ac54ca', '1', '0:0:0:0:0:0:0:1', '2019-06-19 17:25:33');

-- ----------------------------
-- Table structure for `sys_operator_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operator_role`;
CREATE TABLE `sys_operator_role` (
  `role_id` int(11) NOT NULL,
  `op_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`op_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_operator_role
-- ----------------------------
INSERT INTO `sys_operator_role` VALUES ('1', '1');
INSERT INTO `sys_operator_role` VALUES ('2', '1');

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `res_type` varchar(64) NOT NULL COMMENT '主类型',
  `res_type_sec` varchar(64) DEFAULT NULL COMMENT '附类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `int_value` int(11) DEFAULT NULL COMMENT '整型配置值',
  `long_value` bigint(20) DEFAULT NULL COMMENT '长整型配置值',
  `string_value` varchar(255) DEFAULT NULL COMMENT '字符串配置值',
  `string_value1` varchar(255) DEFAULT NULL COMMENT '字符串配置值1',
  `string_value2` varchar(255) DEFAULT NULL COMMENT '字符串配置值2',
  `decimal_value` decimal(10,4) DEFAULT NULL COMMENT 'decimal配置值',
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `add_operator_id` int(11) NOT NULL COMMENT '添加人id',
  `modify_operator_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `is_delete` int(11) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_res_type` (`res_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='业务资源配置表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('9', '渠道注册页地址', 'qdzcdz', null, '渠道注册', '10', '1', 'http://www.baidu.com', '', '', '2.2000', '2019-06-19 16:19:38', null, '1', null, '0');
INSERT INTO `sys_resource` VALUES ('10', '短信收集1开关', 'SMS_CONLLECT_SWITCH', '3333', '', null, '1', null, '', '', '2.2000', '2019-06-19 16:20:51', '2019-06-19 16:53:46', '1', '1', '0');

-- ----------------------------
-- Table structure for `sys_resource_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_log`;
CREATE TABLE `sys_resource_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `res_type` varchar(64) NOT NULL COMMENT 'resource表中类型res_type',
  `res_type_sec` varchar(64) DEFAULT NULL COMMENT 'resource表中类型res_type_sec',
  `old_json` varchar(1024) NOT NULL COMMENT '老的配置',
  `modify_json` varchar(1024) DEFAULT NULL COMMENT '修改后的配置',
  `add_operator_id` int(11) NOT NULL,
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='资源配置日志记录表';

-- ----------------------------
-- Records of sys_resource_log
-- ----------------------------
INSERT INTO `sys_resource_log` VALUES ('5', 'qdzcdz', null, '{\"decimalValue\":2.2,\"intValue\":10,\"longValue\":1,\"name\":\"渠道注册页地址\",\"remark\":\"渠道注册\",\"resType\":\"qdzcdz\",\"stringValue\":\"http://www.baidu.com\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', null, '1', '2019-06-19 16:19:38');
INSERT INTO `sys_resource_log` VALUES ('6', 'SMS_CONLLECT_SWITCH', null, '{\"decimalValue\":2.2,\"longValue\":1,\"name\":\"短信收集开关\",\"remark\":\"\",\"resType\":\"SMS_CONLLECT_SWITCH\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', null, '1', '2019-06-19 16:20:51');
INSERT INTO `sys_resource_log` VALUES ('7', 'SMS_CONLLECT_SWITCH', null, '{\"decimalValue\":2.2,\"id\":10,\"longValue\":1,\"name\":\"短信收集1开关\",\"remark\":\"\",\"resType\":\"SMS_CONLLECT_SWITCH\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', '{\"decimalValue\":2.2,\"id\":10,\"longValue\":1,\"name\":\"短信收集1开关\",\"remark\":\"\",\"resType\":\"SMS_CONLLECT_SWITCH\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', '1', '2019-06-19 16:53:31');
INSERT INTO `sys_resource_log` VALUES ('8', 'SMS_CONLLECT_SWITCH', '3333', '{\"decimalValue\":2.2,\"id\":10,\"longValue\":1,\"name\":\"短信收集1开关\",\"remark\":\"\",\"resType\":\"SMS_CONLLECT_SWITCH\",\"resTypeSec\":\"3333\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', '{\"decimalValue\":2.2,\"id\":10,\"longValue\":1,\"name\":\"短信收集1开关\",\"remark\":\"\",\"resType\":\"SMS_CONLLECT_SWITCH\",\"resTypeSec\":\"3333\",\"stringValue1\":\"\",\"stringValue2\":\"\"}', '1', '2019-06-19 16:53:46');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `role_type` int(1) NOT NULL COMMENT '1超级管理员2普通角色',
  `role_state` int(1) NOT NULL DEFAULT '0' COMMENT '1有效0无效',
  `role` varchar(32) NOT NULL COMMENT '角色唯一标识：admin',
  `is_delete` int(11) DEFAULT '0' COMMENT '!0删除',
  `modify_operator_id` int(11) DEFAULT NULL,
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `add_operator_id` int(11) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `AK_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '1', '1', 'super_admin', '0', '1', '2019-06-17 09:36:01', null, null);
INSERT INTO `sys_role` VALUES ('2', '技术中心', '2', '1', 'technology', '0', '1', '2019-06-18 11:07:19', '1', null);
INSERT INTO `sys_role` VALUES ('10', '产品中心', '2', '1', 'product1', '10', '1', '2019-06-19 10:08:33', '1', '2019-06-19 15:38:46');
INSERT INTO `sys_role` VALUES ('11', '产品中心', '2', '1', 'product2', '11', '1', '2019-06-19 10:10:50', '1', '2019-06-19 15:38:56');
INSERT INTO `sys_role` VALUES ('12', '产品中心', '2', '1', 'product4', '12', '1', '2019-06-19 10:11:49', '1', '2019-06-19 15:39:01');
INSERT INTO `sys_role` VALUES ('13', '产品中心', '2', '1', 'product3', '13', '1', '2019-06-19 10:14:47', '1', '2019-06-19 15:39:52');
INSERT INTO `sys_role` VALUES ('14', '产品中心', '2', '1', 'product536', '14', '1', '2019-06-19 10:16:42', '1', '2019-06-19 15:40:56');
INSERT INTO `sys_role` VALUES ('15', '产品中心', '2', '1', 'product53', '15', '1', '2019-06-19 10:17:06', '1', '2019-06-19 15:42:53');
INSERT INTO `sys_role` VALUES ('16', '产品中心', '2', '1', 'product41', '16', '1', '2019-06-19 10:23:31', '1', '2019-06-19 15:43:08');
INSERT INTO `sys_role` VALUES ('17', '产品中心', '2', '1', 'product8', '17', '1', '2019-06-19 10:26:01', '1', '2019-06-19 15:43:12');
INSERT INTO `sys_role` VALUES ('18', '产品中心', '2', '1', 'product6', '0', null, '2019-06-19 10:32:33', '1', null);
INSERT INTO `sys_role` VALUES ('19', '产品中心', '2', '1', 'product65', '0', null, '2019-06-19 10:34:19', '1', null);
INSERT INTO `sys_role` VALUES ('20', '产品中心', '2', '1', 'product22', '0', null, '2019-06-19 10:42:27', '1', null);
INSERT INTO `sys_role` VALUES ('21', '产品中心', '2', '1', 'product21', '0', null, '2019-06-19 10:51:21', '1', null);
INSERT INTO `sys_role` VALUES ('22', '产品中心', '2', '1', 'product56', '0', null, '2019-06-19 10:54:40', '1', null);
INSERT INTO `sys_role` VALUES ('23', '产品中心', '2', '1', 'product55', '0', null, '2019-06-19 10:57:19', '1', null);
INSERT INTO `sys_role` VALUES ('24', '产品中心', '2', '1', 'product12', '0', null, '2019-06-19 10:58:58', '1', null);
INSERT INTO `sys_role` VALUES ('25', '产品中心', '2', '1', 'product13', '0', null, '2019-06-19 11:19:21', '1', null);
INSERT INTO `sys_role` VALUES ('26', '产品中心', '2', '1', 'product10', '0', null, '2019-06-19 11:24:39', '1', null);
INSERT INTO `sys_role` VALUES ('27', '产品中心', '2', '1', 'product14', '0', null, '2019-06-19 11:28:45', '1', null);
INSERT INTO `sys_role` VALUES ('28', '产品中心', '2', '1', 'product16', '0', null, '2019-06-19 11:35:12', '1', null);
INSERT INTO `sys_role` VALUES ('29', '产品中心', '2', '1', 'product29', '0', null, '2019-06-19 11:38:49', '1', null);
INSERT INTO `sys_role` VALUES ('33', '产品中心', '2', '1', 'product', '0', null, '2019-06-19 15:37:43', '1', null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('5', '5');
INSERT INTO `sys_role_menu` VALUES ('5', '10');
INSERT INTO `sys_role_menu` VALUES ('6', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '10');
INSERT INTO `sys_role_menu` VALUES ('7', '5');
INSERT INTO `sys_role_menu` VALUES ('7', '10');
INSERT INTO `sys_role_menu` VALUES ('8', '5');
INSERT INTO `sys_role_menu` VALUES ('8', '10');
INSERT INTO `sys_role_menu` VALUES ('9', '5');
INSERT INTO `sys_role_menu` VALUES ('9', '10');
INSERT INTO `sys_role_menu` VALUES ('10', '5');
INSERT INTO `sys_role_menu` VALUES ('10', '10');
INSERT INTO `sys_role_menu` VALUES ('11', '5');
INSERT INTO `sys_role_menu` VALUES ('11', '10');
INSERT INTO `sys_role_menu` VALUES ('12', '5');
INSERT INTO `sys_role_menu` VALUES ('12', '10');
INSERT INTO `sys_role_menu` VALUES ('13', '5');
INSERT INTO `sys_role_menu` VALUES ('13', '10');
INSERT INTO `sys_role_menu` VALUES ('14', '5');
INSERT INTO `sys_role_menu` VALUES ('14', '10');
INSERT INTO `sys_role_menu` VALUES ('18', '5');
INSERT INTO `sys_role_menu` VALUES ('18', '10');
INSERT INTO `sys_role_menu` VALUES ('19', '5');
INSERT INTO `sys_role_menu` VALUES ('19', '10');
INSERT INTO `sys_role_menu` VALUES ('20', '5');
INSERT INTO `sys_role_menu` VALUES ('20', '10');
INSERT INTO `sys_role_menu` VALUES ('21', '5');
INSERT INTO `sys_role_menu` VALUES ('21', '10');
INSERT INTO `sys_role_menu` VALUES ('22', '5');
INSERT INTO `sys_role_menu` VALUES ('22', '10');
INSERT INTO `sys_role_menu` VALUES ('23', '5');
INSERT INTO `sys_role_menu` VALUES ('23', '10');
INSERT INTO `sys_role_menu` VALUES ('24', '5');
INSERT INTO `sys_role_menu` VALUES ('24', '10');
INSERT INTO `sys_role_menu` VALUES ('25', '5');
INSERT INTO `sys_role_menu` VALUES ('25', '10');
INSERT INTO `sys_role_menu` VALUES ('26', '5');
INSERT INTO `sys_role_menu` VALUES ('26', '10');
INSERT INTO `sys_role_menu` VALUES ('27', '5');
INSERT INTO `sys_role_menu` VALUES ('27', '10');
INSERT INTO `sys_role_menu` VALUES ('28', '5');
INSERT INTO `sys_role_menu` VALUES ('28', '10');
INSERT INTO `sys_role_menu` VALUES ('29', '5');
INSERT INTO `sys_role_menu` VALUES ('29', '10');
INSERT INTO `sys_role_menu` VALUES ('33', '5');
INSERT INTO `sys_role_menu` VALUES ('33', '10');

-- ----------------------------
-- Table structure for `sys_sms_record`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_record`;
CREATE TABLE `sys_sms_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(11) NOT NULL,
  `sms_type` int(1) NOT NULL COMMENT '1登录2修改密码【字典】',
  `code` varchar(6) NOT NULL,
  `invalid_time` datetime NOT NULL,
  `add_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='短信验证码';

-- ----------------------------
-- Records of sys_sms_record
-- ----------------------------
INSERT INTO `sys_sms_record` VALUES ('1', '13636001109', '1', '8888', '2019-06-14 17:59:27', '2019-06-14 17:29:27');
INSERT INTO `sys_sms_record` VALUES ('2', '17607184395', '1', '8888', '2019-06-19 20:50:51', '2019-06-14 17:31:51');
