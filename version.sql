/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : market

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-09-24 15:08:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` char(15) NOT NULL,
  `apk_path` varchar(50) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('1', '0.9.2', 'apk/market_0_9_2.apk', '可以再登陆界面下方进入注册界面;点击头像可以进入个人空间;个人空间可以查看已发布的物品和已预订的物品;物品详情界面右上角星星图标可以收藏该商品');
