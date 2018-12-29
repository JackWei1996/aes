/*
 Navicat Premium Data Transfer

 Source Server         : JackLocal
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : aes

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 29/12/2018 13:29:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sender` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送人邮箱',
  `receiver_id` int(11) NOT NULL COMMENT '接收人id',
  `se_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `statu` int(3) NULL DEFAULT NULL COMMENT '状态,2XX代表发送成功/3XX代表发送方错误4XX代表接收方错误/5XX代表系统/配置文件错误',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 265 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for receiver
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `project_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名',
  `e_mail` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `adjunct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件',
  `cc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人列表,多个人用;进行隔开',
  `state` tinyint(5) NULL DEFAULT 1 COMMENT '控制是否启用发送',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
