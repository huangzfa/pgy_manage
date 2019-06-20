#sql
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Records of sys_operator_login_log
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='资源配置日志记录表';

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
