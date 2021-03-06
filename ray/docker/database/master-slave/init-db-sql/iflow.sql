/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.125.161-iflow
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 192.168.125.161:36001
 Source Schema         : iflow

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 30/06/2020 13:52:14
*/
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP SCHEMA IF EXISTS iflow;
CREATE SCHEMA iflow;
USE iflow;

-- ----------------------------
-- Table structure for chain
-- ----------------------------
DROP TABLE IF EXISTS `chain`;
CREATE TABLE `chain`  (
  `id` bigint(100) NOT NULL COMMENT '主键',
  `project_id` bigint(100) NULL DEFAULT NULL COMMENT '项目id',
  `chain_method_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评估链方法集合',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow`  (
  `id` bigint(100) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链名',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `organization_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与方角色配置',
  `member_count` int(2) NULL DEFAULT NULL COMMENT '参与方数量',
  `node_count` int(2) NULL DEFAULT NULL COMMENT '节点数量',
  `chain_method_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评估链方法集合',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flow
-- ----------------------------
INSERT INTO `flow` VALUES (1, '测试流程master', 'url-image', '测试流程', '{1,2,3,4}', 3, 4, 'method1,method2,method3', '2020-06-23 21:53:48', '2020-06-29 18:39:25');
INSERT INTO `flow` VALUES (1275309797083725826, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, NULL, NULL);
INSERT INTO `flow` VALUES (1275311102221090817, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, '2020-06-23 14:13:56', '2020-06-23 14:13:56');
INSERT INTO `flow` VALUES (1275324037958008834, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, '2020-06-23 15:05:20', '2020-06-23 15:05:20');
INSERT INTO `flow` VALUES (1275328238435999746, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, '2020-06-23 15:22:01', '2020-06-23 15:22:01');
INSERT INTO `flow` VALUES (1275673579601649665, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, '2020-06-24 14:14:17', '2020-06-24 14:14:17');
INSERT INTO `flow` VALUES (1275680033783951361, 'test流程', 'imageUrl', NULL, NULL, 3, 5, NULL, '2020-06-24 14:39:56', '2020-06-24 14:39:56');
INSERT INTO `flow` VALUES (1277556692220919810, '测试读写分离', NULL, NULL, NULL, NULL, NULL, NULL, '2020-06-29 18:57:06', '2020-06-29 18:57:06');

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation`  (
  `id` bigint(100) NOT NULL COMMENT '主键',
  `user_id` bigint(100) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `assess_status` int(2) NULL DEFAULT 1 COMMENT '操作状态',
  `project_status` int(2) NULL DEFAULT 1 COMMENT '项目状态',
  `project_id` bigint(100) NULL DEFAULT NULL COMMENT '项目id',
  `args` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数json字符串',
  `operation_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作描述，根据业务人为设定',
  `node_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评估节点名称',
  `node_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前节点关键字',
  `move_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转到的节点关键字',
  `organization_type` int(2) NULL DEFAULT NULL COMMENT '组织类型',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` bigint(100) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目描述',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人id',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '项目' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
