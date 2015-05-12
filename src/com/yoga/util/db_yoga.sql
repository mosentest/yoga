/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : db_yoga

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2015-05-12 21:02:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_classrooms
-- ----------------------------
DROP TABLE IF EXISTS `tb_classrooms`;
CREATE TABLE `tb_classrooms` (
  `classrooms_id` varchar(32) NOT NULL COMMENT '课室编号',
  `classrooms_name` varchar(20) NOT NULL COMMENT '课室名称',
  `classrooms_state` tinyint(1) default '0' COMMENT '课室状态（0空闲，1占用）',
  PRIMARY KEY  (`classrooms_id`),
  UNIQUE KEY `classrooms_name` (`classrooms_name`),
  KEY `classrooms_id` USING HASH (`classrooms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_classrooms
-- ----------------------------
INSERT INTO `tb_classrooms` VALUES ('cr0001', 'A101', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0002', 'A102', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0003', 'A103', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0004', 'A104', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0005', 'A105', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0006', 'A201', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0007', 'A202', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0008', 'A203', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0009', 'A204', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0010', 'A205', '0');
INSERT INTO `tb_classrooms` VALUES ('cr0011', 'A301', '0');

-- ----------------------------
-- Table structure for tb_consume
-- ----------------------------
DROP TABLE IF EXISTS `tb_consume`;
CREATE TABLE `tb_consume` (
  `consume_id` varchar(32) NOT NULL COMMENT '消费品编号',
  `consume_name` varchar(20) NOT NULL COMMENT '消费品名称',
  `consume_price` varchar(10) default NULL COMMENT '消费品价格',
  PRIMARY KEY  (`consume_id`),
  UNIQUE KEY `consume_name` (`consume_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_consume
-- ----------------------------
INSERT INTO `tb_consume` VALUES ('cs0001', '苹果', '10');
INSERT INTO `tb_consume` VALUES ('cs0002', '汽水', '5');
INSERT INTO `tb_consume` VALUES ('cs0003', '蛋糕', '20');

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `course_id` varchar(32) NOT NULL COMMENT '课程编号',
  `coures_name` varchar(20) NOT NULL COMMENT '课程名称',
  `coures_type_id` int(11) default NULL COMMENT '课程类型编号',
  `course_date` date default NULL COMMENT '上课日期',
  `course_time1` time default NULL COMMENT '时间段1',
  `course_time2` time default NULL COMMENT '时间段2',
  `course_price` varchar(10) default NULL COMMENT '课程价格',
  PRIMARY KEY  (`course_id`),
  UNIQUE KEY `coures_name` (`coures_name`),
  KEY `coures_type_id` (`coures_type_id`),
  CONSTRAINT `tb_course_ibfk_1` FOREIGN KEY (`coures_type_id`) REFERENCES `tb_course_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES ('444', '444', '2', '2015-04-29', '10:55:15', '10:55:15', '44');
INSERT INTO `tb_course` VALUES ('ce0001', '舞蹈课', '1', '2015-05-09', '11:24:58', '12:25:00', '100');
INSERT INTO `tb_course` VALUES ('ce0002', '瑜伽课', '2', '2015-05-18', '11:25:32', '13:25:35', '90');

-- ----------------------------
-- Table structure for tb_course_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_course_type`;
CREATE TABLE `tb_course_type` (
  `id` int(11) NOT NULL auto_increment COMMENT '课程类型编号',
  `type` varchar(255) default NULL COMMENT '课程类型',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course_type
-- ----------------------------
INSERT INTO `tb_course_type` VALUES ('2', '瑜伽');
INSERT INTO `tb_course_type` VALUES ('1', '舞蹈');

-- ----------------------------
-- Table structure for tb_limit
-- ----------------------------
DROP TABLE IF EXISTS `tb_limit`;
CREATE TABLE `tb_limit` (
  `id` int(11) NOT NULL auto_increment COMMENT '权限编号',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `href` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_limit
-- ----------------------------
INSERT INTO `tb_limit` VALUES ('1', '会员信息管理', 'jsp/member/index.jsp');
INSERT INTO `tb_limit` VALUES ('2', '会员课程', 'jsp/memberComsume/index.jsp');
INSERT INTO `tb_limit` VALUES ('3', '会员消费', 'jsp/memberCourse/index.jsp');
INSERT INTO `tb_limit` VALUES ('4', '课程管理', 'jsp/course/index.jsp');
INSERT INTO `tb_limit` VALUES ('5', '消费品管理', 'jsp/consume/index.jsp');
INSERT INTO `tb_limit` VALUES ('6', '员工管理', 'jsp/staff/index.jsp');
INSERT INTO `tb_limit` VALUES ('7', '课室管理', 'jsp/classrooms/index.jsp');
INSERT INTO `tb_limit` VALUES ('8', '用户管理', 'jsp/user/index.jsp');
INSERT INTO `tb_limit` VALUES ('9', '角色管理', 'jsp/role/index.jsp');
INSERT INTO `tb_limit` VALUES ('10', '权限管理', 'jsp/limit/index.jsp');

-- ----------------------------
-- Table structure for tb_member
-- ----------------------------
DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
  `member_id` varchar(32) NOT NULL COMMENT '会员id',
  `member_username` varchar(20) NOT NULL COMMENT '会员账户',
  `member_type_id` int(11) default NULL COMMENT '类别',
  `member_name` varchar(20) default NULL COMMENT '姓名',
  `member_sex` tinyint(1) default NULL COMMENT '性别。0为男，1为女',
  `member_card` varchar(18) default NULL COMMENT '身份证',
  `member_phone` varchar(11) default NULL COMMENT '手机号',
  `member_address` varchar(255) default NULL COMMENT '家庭住址',
  PRIMARY KEY  (`member_id`),
  UNIQUE KEY `member_username` (`member_username`),
  KEY `member_type_id` (`member_type_id`),
  CONSTRAINT `tb_member_ibfk_1` FOREIGN KEY (`member_type_id`) REFERENCES `tb_member_type` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_member
-- ----------------------------
INSERT INTO `tb_member` VALUES ('m0001', 'guoxiaomai', '4', '国小麦', '1', '4419195238467426', '135814615', '湖南');

-- ----------------------------
-- Table structure for tb_member_consume
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_consume`;
CREATE TABLE `tb_member_consume` (
  `member_consume_id` varchar(32) NOT NULL COMMENT '用户消费订单编号',
  `member_id` varchar(32) default NULL COMMENT '会员编号',
  `create_time` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '购买时间',
  `cost` varchar(10) default NULL COMMENT '花费',
  PRIMARY KEY  (`member_consume_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `tb_member_consume_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_member_consume
-- ----------------------------
INSERT INTO `tb_member_consume` VALUES ('mc0001', 'm0001', '2015-05-12 20:46:59', '100');
INSERT INTO `tb_member_consume` VALUES ('mc0002', 'm0001', '2015-05-12 20:48:23', '200');

-- ----------------------------
-- Table structure for tb_member_consume_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_consume_detail`;
CREATE TABLE `tb_member_consume_detail` (
  `id` int(11) NOT NULL auto_increment,
  `member_consume_id` varchar(32) default NULL COMMENT '用户消费订单编号',
  `consume_id` varchar(32) default NULL COMMENT '消费编号',
  `num` int(11) default NULL COMMENT '数量',
  PRIMARY KEY  (`id`),
  KEY `consume_id` (`consume_id`),
  KEY `member_consume_id` (`member_consume_id`),
  CONSTRAINT `tb_member_consume_detail_ibfk_2` FOREIGN KEY (`consume_id`) REFERENCES `tb_consume` (`consume_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_member_consume_detail_ibfk_3` FOREIGN KEY (`member_consume_id`) REFERENCES `tb_member_consume` (`member_consume_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_member_consume_detail
-- ----------------------------
INSERT INTO `tb_member_consume_detail` VALUES ('1', 'mc0001', 'cs0001', '10');
INSERT INTO `tb_member_consume_detail` VALUES ('7', 'mc0002', 'cs0003', '10');

-- ----------------------------
-- Table structure for tb_member_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_course`;
CREATE TABLE `tb_member_course` (
  `member_course_id` varchar(32) NOT NULL COMMENT '用户课程订单编号',
  `member_id` varchar(32) default NULL COMMENT '会员编号',
  `create_time` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '购买时间',
  `cost` varchar(10) default NULL COMMENT '花费',
  PRIMARY KEY  (`member_course_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `tb_member_course_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `tb_member` (`member_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 10240 kB; (`course_id`) REFER `db_yoga/tb_cours';

-- ----------------------------
-- Records of tb_member_course
-- ----------------------------
INSERT INTO `tb_member_course` VALUES ('mc0001', 'm0001', '2015-05-12 20:56:40', '44');
INSERT INTO `tb_member_course` VALUES ('mc0002', 'm0001', '2015-05-12 20:58:55', '100');

-- ----------------------------
-- Table structure for tb_member_course_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_course_detail`;
CREATE TABLE `tb_member_course_detail` (
  `id` int(11) NOT NULL auto_increment,
  `member_course_id` varchar(32) default NULL COMMENT '用户课程订单编号',
  `course_id` int(11) default NULL COMMENT '课程编号',
  PRIMARY KEY  (`id`),
  KEY `member_course_id` (`member_course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `tb_member_course_detail_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tb_staff_course_classrooms` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_member_course_detail_ibfk_1` FOREIGN KEY (`member_course_id`) REFERENCES `tb_member_course` (`member_course_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_member_course_detail
-- ----------------------------
INSERT INTO `tb_member_course_detail` VALUES ('1', 'mc0001', '1');
INSERT INTO `tb_member_course_detail` VALUES ('2', 'mc0002', '2');

-- ----------------------------
-- Table structure for tb_member_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_type`;
CREATE TABLE `tb_member_type` (
  `id` int(11) NOT NULL auto_increment COMMENT '会员类型编号',
  `type` varchar(20) NOT NULL COMMENT '会员类型',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_member_type
-- ----------------------------
INSERT INTO `tb_member_type` VALUES ('1', '普通');
INSERT INTO `tb_member_type` VALUES ('2', '白金');
INSERT INTO `tb_member_type` VALUES ('4', '超级黄金');
INSERT INTO `tb_member_type` VALUES ('3', '黄金');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL auto_increment COMMENT '角色编号',
  `type` varchar(20) NOT NULL COMMENT '角色类型',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('4', '土豪');
INSERT INTO `tb_role` VALUES ('1', '普通管理员');
INSERT INTO `tb_role` VALUES ('2', '超级管理员');

-- ----------------------------
-- Table structure for tb_role_limit
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_limit`;
CREATE TABLE `tb_role_limit` (
  `id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `limit_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `role_id` (`role_id`),
  KEY `limit_id` (`limit_id`),
  CONSTRAINT `tb_role_limit_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_role_limit_ibfk_2` FOREIGN KEY (`limit_id`) REFERENCES `tb_limit` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_limit
-- ----------------------------
INSERT INTO `tb_role_limit` VALUES ('1', '1', '1');
INSERT INTO `tb_role_limit` VALUES ('2', '1', '2');
INSERT INTO `tb_role_limit` VALUES ('3', '1', '3');
INSERT INTO `tb_role_limit` VALUES ('4', '1', '4');
INSERT INTO `tb_role_limit` VALUES ('5', '1', '5');
INSERT INTO `tb_role_limit` VALUES ('6', '1', '6');
INSERT INTO `tb_role_limit` VALUES ('7', '1', '7');
INSERT INTO `tb_role_limit` VALUES ('8', '2', '1');
INSERT INTO `tb_role_limit` VALUES ('9', '2', '2');
INSERT INTO `tb_role_limit` VALUES ('10', '2', '3');
INSERT INTO `tb_role_limit` VALUES ('11', '2', '4');
INSERT INTO `tb_role_limit` VALUES ('12', '2', '5');
INSERT INTO `tb_role_limit` VALUES ('13', '2', '6');
INSERT INTO `tb_role_limit` VALUES ('14', '2', '7');
INSERT INTO `tb_role_limit` VALUES ('15', '2', '8');
INSERT INTO `tb_role_limit` VALUES ('16', '2', '9');
INSERT INTO `tb_role_limit` VALUES ('17', '2', '10');
INSERT INTO `tb_role_limit` VALUES ('21', '4', '1');
INSERT INTO `tb_role_limit` VALUES ('22', '4', '2');
INSERT INTO `tb_role_limit` VALUES ('23', '4', '3');

-- ----------------------------
-- Table structure for tb_staff
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff`;
CREATE TABLE `tb_staff` (
  `staff_id` varchar(32) NOT NULL COMMENT '员工编号',
  `staff_name` varchar(20) NOT NULL COMMENT '员工姓名',
  `staff_sex` tinyint(1) default NULL COMMENT '性别。0为男，1为女',
  `staff_age` tinyint(3) default NULL COMMENT '员工年龄',
  `staff_post` varchar(20) default NULL COMMENT '员工职务',
  `staff_phone` varchar(15) default NULL COMMENT '员工联系方式',
  PRIMARY KEY  (`staff_id`),
  UNIQUE KEY `staff_name` (`staff_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_staff
-- ----------------------------
INSERT INTO `tb_staff` VALUES ('st0001', '李小花', '1', '19', '销售', '13866666612');
INSERT INTO `tb_staff` VALUES ('st0002', '张全蛋', '0', '20', '行政', '13866666611');

-- ----------------------------
-- Table structure for tb_staff_course_classrooms
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff_course_classrooms`;
CREATE TABLE `tb_staff_course_classrooms` (
  `id` int(11) NOT NULL auto_increment,
  `staff_id` varchar(32) default NULL,
  `course_id` varchar(32) default NULL,
  `classrooms_id` varchar(32) default NULL,
  `create_time` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '使用时间',
  PRIMARY KEY  (`id`),
  KEY `staff_id` (`staff_id`),
  KEY `course_id` (`course_id`),
  KEY `classrooms_id` (`classrooms_id`),
  CONSTRAINT `tb_staff_course_classrooms_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`staff_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_staff_course_classrooms_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`course_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_staff_course_classrooms_ibfk_3` FOREIGN KEY (`classrooms_id`) REFERENCES `tb_classrooms` (`classrooms_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_staff_course_classrooms
-- ----------------------------
INSERT INTO `tb_staff_course_classrooms` VALUES ('1', 'st0001', 'ce0001', 'cr0001', '2015-05-12 20:59:57');
INSERT INTO `tb_staff_course_classrooms` VALUES ('2', 'st0002', 'ce0002', 'cr0002', '2015-05-12 21:00:44');

-- ----------------------------
-- Table structure for tb_staff_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff_detail`;
CREATE TABLE `tb_staff_detail` (
  `id` int(11) NOT NULL auto_increment,
  `staff_id` varchar(32) default NULL,
  `staff_card` varchar(18) default NULL COMMENT '员工身份证',
  `staff_address` varchar(255) default NULL COMMENT '家庭住址',
  `staff_email` varchar(255) default NULL COMMENT '邮箱地址',
  `staff_time` datetime default NULL COMMENT '入职日期',
  PRIMARY KEY  (`id`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `tb_staff_detail_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`staff_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_staff_detail
-- ----------------------------
INSERT INTO `tb_staff_detail` VALUES ('1', 'st0001', '410909192950115555', '黑龙江', '138138@qq.com', '2015-05-10 10:20:30');
INSERT INTO `tb_staff_detail` VALUES ('2', 'st0002', '410909192950115111', '黑龙江', '138138@qq.com', '2015-05-10 10:20:30');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` varchar(32) NOT NULL,
  `user_username` varchar(20) NOT NULL COMMENT '帐号',
  `user_password` varchar(32) default NULL COMMENT '密码',
  `staff_id` varchar(32) default NULL COMMENT '员工编号',
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `user_username` (`user_username`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `tb_staff` (`staff_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('u0001', 'lixiaohua', '123456', 'st0001');
INSERT INTO `tb_user` VALUES ('u0002', 'zhangquandan', '123456', 'st0002');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` varchar(32) default NULL,
  `role_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `tb_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `tb_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', 'u0001', '1');
INSERT INTO `tb_user_role` VALUES ('2', 'u0002', '2');
