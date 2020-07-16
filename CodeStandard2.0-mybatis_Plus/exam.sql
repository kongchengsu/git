/*
 Navicat Premium Data Transfer

 Source Server         : 戴尔
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 192.168.123.168:3306
 Source Schema         : arts_see

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 16/07/2020 16:03:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `exam_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `exam_start_time` datetime(0) NULL DEFAULT NULL COMMENT '考试开始时间',
  `exam_end_time` datetime(0) NULL DEFAULT NULL COMMENT '考试结束时间',
  `exam_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `exam_create_time` datetime(0) NULL DEFAULT NULL,
  `exam_verify_start_time` datetime(0) NULL DEFAULT NULL COMMENT '信息验证时间-开始',
  `exam_verify_end_time` datetime(0) NULL DEFAULT NULL COMMENT '信息验证时间-结束',
  `exam_print_start_time` datetime(0) NULL DEFAULT NULL COMMENT '准考证打印时间-开始',
  `exam_print_end_time` datetime(0) NULL DEFAULT NULL COMMENT '准考证打印时间-结束',
  `exam_score_start_time` datetime(0) NULL DEFAULT NULL COMMENT '成绩查询时间-开始',
  `exam_score_end_time` datetime(0) NULL DEFAULT NULL COMMENT '成绩查询时间-结束',
  `exam_total_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '总分',
  `exam_year` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '考试年份',
  `exam_order_start_time` datetime(0) NULL DEFAULT NULL COMMENT '可预约时间-开始',
  `exam_order_end_time` datetime(0) NULL DEFAULT NULL COMMENT '可预约时间-开始',
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
