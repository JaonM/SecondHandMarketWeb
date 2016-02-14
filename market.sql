/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : market

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2014-09-28 23:16:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_path` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement
-- ----------------------------
INSERT INTO `advertisement` VALUES ('1', 'photos/advertisement/ad1.jpg', 'http://www.baidu.com');
INSERT INTO `advertisement` VALUES ('2', 'photos/advertisement/ad2.jpg', '');

-- ----------------------------
-- Table structure for classfication
-- ----------------------------
DROP TABLE IF EXISTS `classfication`;
CREATE TABLE `classfication` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `classfication2_name` varchar(30) NOT NULL,
  `classfication1_id` tinyint(4) NOT NULL COMMENT '一级分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classfication
-- ----------------------------
INSERT INTO `classfication` VALUES ('1', '书籍资料', '1');
INSERT INTO `classfication` VALUES ('2', '文具', '1');
INSERT INTO `classfication` VALUES ('3', '学习工具', '1');
INSERT INTO `classfication` VALUES ('4', '其他', '1');
INSERT INTO `classfication` VALUES ('5', '电脑', '2');
INSERT INTO `classfication` VALUES ('6', '手机', '2');
INSERT INTO `classfication` VALUES ('7', '照相机', '2');
INSERT INTO `classfication` VALUES ('8', '平板', '2');
INSERT INTO `classfication` VALUES ('9', '播放器', '2');
INSERT INTO `classfication` VALUES ('10', '移动硬盘/USB', '2');
INSERT INTO `classfication` VALUES ('11', '耳机', '2');
INSERT INTO `classfication` VALUES ('12', '其他', '2');
INSERT INTO `classfication` VALUES ('13', '椅子', '3');
INSERT INTO `classfication` VALUES ('14', '桌子', '3');
INSERT INTO `classfication` VALUES ('15', '照明', '3');
INSERT INTO `classfication` VALUES ('16', '风扇', '3');
INSERT INTO `classfication` VALUES ('17', '床上用品', '3');
INSERT INTO `classfication` VALUES ('18', '架子', '3');
INSERT INTO `classfication` VALUES ('19', '饰品', '3');
INSERT INTO `classfication` VALUES ('20', '饮水机', '3');
INSERT INTO `classfication` VALUES ('21', '背包', '3');
INSERT INTO `classfication` VALUES ('22', '其他', '3');
INSERT INTO `classfication` VALUES ('23', '自行车', '4');
INSERT INTO `classfication` VALUES ('24', '篮球', '4');
INSERT INTO `classfication` VALUES ('25', '足球', '4');
INSERT INTO `classfication` VALUES ('26', '网球', '4');
INSERT INTO `classfication` VALUES ('27', '羽毛球', '4');
INSERT INTO `classfication` VALUES ('28', '兵乓球', '4');
INSERT INTO `classfication` VALUES ('29', '排球', '4');
INSERT INTO `classfication` VALUES ('30', '运动器械', '4');
INSERT INTO `classfication` VALUES ('31', '其他', '4');
INSERT INTO `classfication` VALUES ('32', '上衣女', '5');
INSERT INTO `classfication` VALUES ('33', '下装女', '5');
INSERT INTO `classfication` VALUES ('34', '女鞋', '5');
INSERT INTO `classfication` VALUES ('35', '男鞋', '5');
INSERT INTO `classfication` VALUES ('36', '套装', '5');
INSERT INTO `classfication` VALUES ('37', '围巾配饰', '5');
INSERT INTO `classfication` VALUES ('38', '女士包', '5');
INSERT INTO `classfication` VALUES ('39', '男装', '5');
INSERT INTO `classfication` VALUES ('40', '其他', '5');
INSERT INTO `classfication` VALUES ('41', '其他', '6');

-- ----------------------------
-- Table structure for collectship
-- ----------------------------
DROP TABLE IF EXISTS `collectship`;
CREATE TABLE `collectship` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `item_id` int(11) NOT NULL COMMENT '物品id',
  PRIMARY KEY (`user_id`,`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collectship
-- ----------------------------
INSERT INTO `collectship` VALUES ('3', '3');
INSERT INTO `collectship` VALUES ('3', '4');
INSERT INTO `collectship` VALUES ('3', '5');
INSERT INTO `collectship` VALUES ('3', '9');
INSERT INTO `collectship` VALUES ('8', '3');
INSERT INTO `collectship` VALUES ('14', '25');
INSERT INTO `collectship` VALUES ('14', '26');
INSERT INTO `collectship` VALUES ('14', '28');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `description` text,
  `photo_count` tinyint(4) DEFAULT '0',
  `owner_id` int(11) NOT NULL,
  `upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `collect_count` int(10) unsigned DEFAULT '0' COMMENT '物品被收藏次数',
  `classfication1` char(20) NOT NULL COMMENT '物品一级分类',
  `classfication2` char(20) DEFAULT NULL COMMENT '物品二级分类',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物品状态 0-未出售出 1-被预定 2-已售出',
  `buyerid` int(4) DEFAULT '0' COMMENT '购买者的id',
  `is_send` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否已经发送过该物品消息',
  `order_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('1', '水果', '123.44', '这个水果很好吃', '3', '3', '2014-08-13 10:48:19', '34', '数码电器', '电脑', '0', '0', '0', null);
INSERT INTO `item` VALUES ('3', '水果', '123.44', '这个水果很好吃', '3', '3', '2014-08-15 08:48:26', '47', '数码电器', '电脑', '0', '0', '0', null);
INSERT INTO `item` VALUES ('4', '水果', '123.44', '这个水果很好吃', '3', '6', '2014-08-14 10:48:27', '133', '数码电器', '电脑', '0', '0', '0', null);
INSERT INTO `item` VALUES ('5', '小霸王学习机', '8923', '小霸王学习机！！！', '3', '6', '2014-08-01 09:48:29', '56', '数码电器', '电脑', '0', '0', '0', null);
INSERT INTO `item` VALUES ('6', '水果', '12.3', '爱上大声地', '3', '6', '2014-08-14 10:48:30', '44', '数码电器', '电脑', '0', '0', '0', null);
INSERT INTO `item` VALUES ('9', '超人平板', '1233', '你值得拥有', '3', '3', '2014-08-14 12:48:43', '22', '数码电器', '手机', '0', '0', '0', null);
INSERT INTO `item` VALUES ('10', '超人洗衣粉', '23.4', '去污能手', '3', '3', '2014-08-15 11:36:16', '46', '学习用品', '文具', '0', '0', '0', null);
INSERT INTO `item` VALUES ('13', 'iPhone 300', '6000', 'iPhone 300横空出世', '3', '8', '2014-08-26 09:48:14', '0', '数码电器', '电脑', '1', '8', '1', null);
INSERT INTO `item` VALUES ('24', '小Y', '5000', '彪悍的小Y', '1', '8', '2014-09-08 16:08:29', '0', '数码电器', '电脑', '1', '3', '1', null);
INSERT INTO `item` VALUES ('25', '超大水杯', '12', '可以装很多水(⊙o⊙)哦', '1', '8', '2014-09-13 21:11:59', '1', '限制日用', '其他', '0', '0', '0', null);
INSERT INTO `item` VALUES ('26', '纸巾', '5', '纸巾', '1', '8', '2014-09-13 21:23:48', '1', '限制日用', '其他', '0', '0', '0', null);
INSERT INTO `item` VALUES ('28', '小熊饼', '5', '好吃', '1', '8', '2014-09-13 22:33:09', '1', '其他', '其他', '1', '14', '0', '2014-09-22');
INSERT INTO `item` VALUES ('29', '宿舍', '44', '放的开手机看的家家户户不不不斤斤计较积极积极积极积极你', '1', '14', '2014-09-28 16:45:26', '0', '衣鞋配饰', '套装', '0', '0', '0', null);
INSERT INTO `item` VALUES ('30', '烦烦烦', '88', '收拾收拾！收拾收拾宿舍是说撒说撒说撒说撒说撒说撒说撒说撒说撒收拾收拾点点滴滴电视上说', '1', '14', '2014-09-28 16:49:46', '0', '衣鞋配饰', '套装', '0', '0', '0', null);

-- ----------------------------
-- Table structure for item_photos
-- ----------------------------
DROP TABLE IF EXISTS `item_photos`;
CREATE TABLE `item_photos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `photo_path` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item_photos
-- ----------------------------
INSERT INTO `item_photos` VALUES ('1', '1', 'photos/items/1/3.jpg');
INSERT INTO `item_photos` VALUES ('2', '1', 'photos/items/1/4.jpg');
INSERT INTO `item_photos` VALUES ('3', '1', 'photos/items/1/5.jpg');
INSERT INTO `item_photos` VALUES ('4', '3', 'photos/items/3/3.jpg');
INSERT INTO `item_photos` VALUES ('5', '3', 'photos/items/3/4.jpg');
INSERT INTO `item_photos` VALUES ('6', '3', 'photos/items/3/5.jpg');
INSERT INTO `item_photos` VALUES ('7', '4', 'photos/items/4/3.jpg');
INSERT INTO `item_photos` VALUES ('8', '4', 'photos/items/4/4.jpg');
INSERT INTO `item_photos` VALUES ('9', '4', 'photos/items/4/5.jpg');
INSERT INTO `item_photos` VALUES ('10', '5', 'photos/items/5/3.jpg');
INSERT INTO `item_photos` VALUES ('11', '5', 'photos/items/5/4.jpg');
INSERT INTO `item_photos` VALUES ('12', '5', 'photos/items/5/5.jpg');
INSERT INTO `item_photos` VALUES ('13', '6', 'photos/items/6/3.jpg');
INSERT INTO `item_photos` VALUES ('14', '6', 'photos/items/6/4.jpg');
INSERT INTO `item_photos` VALUES ('15', '6', 'photos/items/6/5.jpg');
INSERT INTO `item_photos` VALUES ('16', '9', 'photos/items/9/3.jpg');
INSERT INTO `item_photos` VALUES ('17', '9', 'photos/items/9/4.jpg');
INSERT INTO `item_photos` VALUES ('18', '9', 'photos/items/9/5.jpg');
INSERT INTO `item_photos` VALUES ('19', '10', 'photos/items/10/3.jpg');
INSERT INTO `item_photos` VALUES ('20', '10', 'photos/items/10/4.jpg');
INSERT INTO `item_photos` VALUES ('21', '10', 'photos/items/10/5.jpg');
INSERT INTO `item_photos` VALUES ('22', '13', 'photos/items/13/3e7ddda5-bf43-4571-8a75-94b2666a923b.jpg');
INSERT INTO `item_photos` VALUES ('23', '13', 'photos/items/13/5ae3c8a9-7f4c-4dbb-9a68-d682ff49a46f.jpg');
INSERT INTO `item_photos` VALUES ('24', '13', 'photos/items/13/9f510fb30f2442a78c4d59d2d343ad4bd1130201.jpg');
INSERT INTO `item_photos` VALUES ('43', '24', 'photos/items/24/9f510fb30f2442a78c4d59d2d343ad4bd1130201.jpg');
INSERT INTO `item_photos` VALUES ('44', '25', 'photos/items/25/0.jpg');
INSERT INTO `item_photos` VALUES ('45', '26', 'photos/items/26/0.jpg');
INSERT INTO `item_photos` VALUES ('47', '28', 'photos/items/28/0.jpg');
INSERT INTO `item_photos` VALUES ('48', '29', 'photos/items/29/0.jpg');
INSERT INTO `item_photos` VALUES ('49', '30', 'photos/items/30/0.jpg');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `message` text NOT NULL COMMENT '用户对物品留言',
  `is_send` tinyint(4) NOT NULL DEFAULT '0' COMMENT '表示该消息是否发送过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '3', '24', '多少钱', '1');

-- ----------------------------
-- Table structure for relationship
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship` (
  `user1_id` int(11) NOT NULL,
  `user2_id` int(11) NOT NULL,
  `relation` tinyint(4) NOT NULL,
  PRIMARY KEY (`user1_id`,`user2_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relationship
-- ----------------------------
INSERT INTO `relationship` VALUES ('1', '2', '1');
INSERT INTO `relationship` VALUES ('2', '1', '1');
INSERT INTO `relationship` VALUES ('8', '3', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` char(30) NOT NULL,
  `password` varchar(120) CHARACTER SET latin1 NOT NULL COMMENT '密码 密文',
  `nickname` char(20) DEFAULT NULL,
  `name` char(20) DEFAULT NULL,
  `phone` char(11) CHARACTER SET latin1 NOT NULL,
  `sno` char(10) CHARACTER SET latin1 DEFAULT NULL,
  `sex` tinyint(4) NOT NULL DEFAULT '1',
  `self_introduction` varchar(50) DEFAULT NULL,
  `user_photo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`account`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '1', '2', '猪', 'www', '2121344412', '1234125', '1', '我是超人', 'photos/users/default_image.png');
INSERT INTO `user` VALUES ('4', 'asd', 'aaa', 'zxc', 'ma', '1231414', '0909122393', '1', '我是谁', null);
INSERT INTO `user` VALUES ('6', '123', '123', 'aqw', 'asq', '15074861246', '09090909', '1', '我从哪里来', 'photos/users/6.jpg');
INSERT INTO `user` VALUES ('8', 'a', 'b', '平头', '阿强', '15074864321', '', '0', '', 'photos/users/8.jpg');
INSERT INTO `user` VALUES ('10', 'q', 'w', 'sa', '', '15074863246', '', '1', '', 'photos/users/10.jpg');
INSERT INTO `user` VALUES ('13', 'z', 'x', '平头定足球', '', '15078463216', '', '0', '', 'photos/users/13.jpg');
INSERT INTO `user` VALUES ('14', 'w', '7694f4a66316e53c8cdd9d9954bd611d', 'XXX', '嘎嘎嘎', '15074861246', '0909122846', '0', '', 'photos/users/14.jpg');

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
