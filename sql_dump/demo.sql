/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MariaDB
 Source Server Version : 100411
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MariaDB
 Target Server Version : 100411
 File Encoding         : 65001

 Date: 20/05/2021 14:04:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'Design & tech');
INSERT INTO `category` VALUES (2, 'Film');
INSERT INTO `category` VALUES (3, 'Games');
INSERT INTO `category` VALUES (4, 'Music');
INSERT INTO `category` VALUES (5, 'Social');
INSERT INTO `category` VALUES (6, 'Food & craft');
INSERT INTO `category` VALUES (7, 'Publishing');
INSERT INTO `category` VALUES (8, 'Art');
INSERT INTO `category` VALUES (9, 'Film');
COMMIT;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
BEGIN;
INSERT INTO `class` VALUES (1, 'Tesst class 1');
INSERT INTO `class` VALUES (2, 'Tesst class 2');
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `comment_text` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`comment_id`,`project_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `country_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`country_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of country
-- ----------------------------
BEGIN;
INSERT INTO `country` VALUES (1, 'Vietnam');
INSERT INTO `country` VALUES (2, 'Mỹ ');
INSERT INTO `country` VALUES (3, 'Anh');
INSERT INTO `country` VALUES (4, 'Nhật ');
INSERT INTO `country` VALUES (5, 'Hàn');
INSERT INTO `country` VALUES (6, 'Trung Quốc');
COMMIT;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `item_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `option_id` int(10) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `quantity` int(10) DEFAULT NULL,
  `project_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES (1, 1, 'Test item name', 1, 1);
INSERT INTO `item` VALUES (2, 1, 'Test item name 2', 2, 1);
INSERT INTO `item` VALUES (3, 2, 'Test item name 3', 1, 1);
INSERT INTO `item` VALUES (4, 1, 'Test item name 4', 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(10) DEFAULT NULL,
  `material_type_id` int(10) unsigned DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`material_id`) USING BTREE,
  KEY `material_type_id` (`material_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material
-- ----------------------------
BEGIN;
INSERT INTO `material` VALUES (1, 10, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.7983935263964652571.8080/upload/');
INSERT INTO `material` VALUES (2, 12, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.7958231426523113775.8080/upload/');
INSERT INTO `material` VALUES (3, 12, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.8713781969677030704.8080/upload/');
INSERT INTO `material` VALUES (4, 12, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.3047401940995054616.8080/upload/');
INSERT INTO `material` VALUES (5, 1, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.1594922026579655574.8080/upload/Screenshot 2021-03-03 at 9.34.35 PM (2).png');
INSERT INTO `material` VALUES (6, 1, 2, 'test project', '/private/var/folders/rm/9d3fp3kj3t363406cpv20z980000gn/T/tomcat-docbase.743632578804465752.8080/upload/Screenshot 2021-03-03 at 9.34.35 PM (2).png');
COMMIT;

-- ----------------------------
-- Table structure for material_type
-- ----------------------------
DROP TABLE IF EXISTS `material_type`;
CREATE TABLE `material_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of material_type
-- ----------------------------
BEGIN;
INSERT INTO `material_type` VALUES (1, 'Thumbnail');
INSERT INTO `material_type` VALUES (2, 'Image');
INSERT INTO `material_type` VALUES (3, 'Video');
INSERT INTO `material_type` VALUES (4, 'Text');
COMMIT;

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option` (
  `option_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_id` int(10) NOT NULL,
  `option_name` varchar(255) DEFAULT NULL,
  `option_description` varchar(255) DEFAULT NULL,
  `fund_max` decimal(15,0) DEFAULT NULL,
  `fund_min` decimal(15,0) DEFAULT NULL,
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of option
-- ----------------------------
BEGIN;
INSERT INTO `option` VALUES (1, 1, 'Test option 1', 'Test option 1 description \r\nTest save da', 200, 20111);
INSERT INTO `option` VALUES (2, 1, 'Test option 2 ', 'Test option 2 description', 300, 150);
INSERT INTO `option` VALUES (3, 1, 'Test option 3', 'Test option 3 description ', 400, 300);
COMMIT;

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `package_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `project_id` int(10) NOT NULL,
  `option_id` int(10) NOT NULL,
  `pledged` decimal(15,2) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `project_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL,
  `user_id` int(10) DEFAULT NULL,
  `project_short_des` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `goal` double(25,0) DEFAULT NULL,
  `pledged` double(25,0) DEFAULT NULL,
  `investor_count` int(10) DEFAULT NULL,
  `status_id` int(10) DEFAULT NULL,
  `recommended` int(1) DEFAULT 0,
  `category_id` int(10) DEFAULT NULL,
  `country_id` int(10) DEFAULT NULL,
  `thumbnail_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES (1, 'test project 11111', NULL, 'this is test project ', NULL, NULL, 1231231, NULL, NULL, 3, 1, 3, NULL, 'images/project-assets/1/Screenshot 2021-04-01 at 10.22.01 PM.png');
INSERT INTO `project` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `project` VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for project_status_history
-- ----------------------------
DROP TABLE IF EXISTS `project_status_history`;
CREATE TABLE `project_status_history` (
  `project_id` int(10) NOT NULL,
  `project_status_id` int(10) NOT NULL,
  `project_status_history_id` int(10) NOT NULL AUTO_INCREMENT,
  `timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`project_status_history_id`,`project_status_id`,`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'ADMIN');
INSERT INTO `role` VALUES (2, 'INVESTOR');
INSERT INTO `role` VALUES (3, 'CREATOR');
INSERT INTO `role` VALUES (4, 'ANONYMOUS');
COMMIT;

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`status_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of status
-- ----------------------------
BEGIN;
INSERT INTO `status` VALUES (1, 'WAITING');
INSERT INTO `status` VALUES (2, 'APPROVED');
INSERT INTO `status` VALUES (3, 'RUNNING');
INSERT INTO `status` VALUES (4, 'PAUSE');
INSERT INTO `status` VALUES (5, 'COMPLETE');
INSERT INTO `status` VALUES (6, 'REJECT');
INSERT INTO `status` VALUES (7, 'EDITING');
COMMIT;

-- ----------------------------
-- Table structure for story
-- ----------------------------
DROP TABLE IF EXISTS `story`;
CREATE TABLE `story` (
  `project_id` int(10) NOT NULL,
  `story_des` longtext DEFAULT NULL,
  `story_type_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story
-- ----------------------------
BEGIN;
INSERT INTO `story` VALUES (1, '<p>This is test story</p><p><br></p><p><img src=\"../../../images/project-assets/1/Screenshot 2021-03-19 at 11.55.53 AM.png\" class=\"img-fluid\" style=\"width: 50%;\"><br></p><p>this is second image&nbsp;</p><p><img src=\"../../../../../../images/project-assets/1/Screenshot 2021-03-03 at 9.34.35 PM (2).png\" class=\"img-fluid\" style=\"width: 50%;\"><br></p>', NULL);
COMMIT;

-- ----------------------------
-- Table structure for story_type
-- ----------------------------
DROP TABLE IF EXISTS `story_type`;
CREATE TABLE `story_type` (
  `story_type_id` int(10) NOT NULL,
  `story_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`story_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) DEFAULT NULL,
  `class_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, 'Thanh', 1);
INSERT INTO `student` VALUES (2, 'Thanh', NULL);
INSERT INTO `student` VALUES (3, 'Thanh', NULL);
COMMIT;

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `team_id` int(10) NOT NULL,
  `team_name` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE_USERNAME` (`username`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'thanhdao', '$2a$10$yPVhHV6UswsDBv5R1vX.gut7JzNRFGs9QnJVLTUQZXVAbMmcuI7Ka');
INSERT INTO `user` VALUES (2, 'thanhdk', '$2a$10$f0Typ2ECWNHnH0exL6HXfOKO8T1mIVAA6pXunT4SIFBjgEfKVSfrq');
INSERT INTO `user` VALUES (3, 'user1', '$2a$10$qBaROE3BmNiI1ERIJbU8P.Fy3/VaRVD4f49sUn9k1.6Ci7fkbA.Pq');
COMMIT;

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `user_id` int(10) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `project_supported` varchar(255) DEFAULT NULL,
  `total_amount` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `country_id` int(10) DEFAULT NULL,
  `phone_num` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
BEGIN;
INSERT INTO `user_detail` VALUES (3, 'Thanh', 'Dao', 'daokythanh@gmail.com', '0', '0', 'Hanoi', 1, '0977634901');
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(10) NOT NULL,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  UNIQUE KEY `USER_ROLE_UNIQUE` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 2);
INSERT INTO `user_role` VALUES (2, 3);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (3, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
