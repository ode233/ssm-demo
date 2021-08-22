/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2021-08-23 03:28:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `docID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `docName` varchar(255) DEFAULT NULL,
  `docPath` varchar(255) DEFAULT NULL,
  `docDescribe` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`docID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
