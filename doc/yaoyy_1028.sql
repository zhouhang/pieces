/*
Navicat MySQL Data Transfer

Source Server         : yaoyy
Source Server Version : 50617
Source Host           : 192.168.1.41:3306
Source Database       : yaoyy

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2016-10-28 19:08:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ad
-- ----------------------------
DROP TABLE IF EXISTS `ad`;
CREATE TABLE `ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL COMMENT '广告类型ID',
  `name` varchar(64) DEFAULT NULL COMMENT '广告描述',
  `href` varchar(256) DEFAULT NULL COMMENT '广告链接',
  `sort` int(11) DEFAULT NULL,
  `picture_url` varchar(256) DEFAULT NULL COMMENT '广告图片url',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_mem` int(11) DEFAULT NULL,
  `update_mem` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
-- Records of ad
-- ----------------------------
INSERT INTO `ad` VALUES ('1', '2', '田七', '', '22', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/publicity/2016/10/28732a0d-601a-4070-97b1-b034f08d406a.jpg', '2016-10-28 18:17:24', null, '0', null, '1');
INSERT INTO `ad` VALUES ('2', '1', '冬虫夏草', '23', '11', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/publicity/2016/10/33cb2400-2308-4814-bf27-3fc2752e3624.jpg', '2016-10-28 18:18:34', null, '0', null, '1');
INSERT INTO `ad` VALUES ('3', '2', '麦冬', '', '23', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/publicity/2016/10/00b81feb-dc20-4b06-98a0-32788b4d1c48.jpg', '2016-10-28 18:19:11', null, '0', null, '1');
INSERT INTO `ad` VALUES ('4', '2', '白芍', 'http://127.0.0.1:8188/special/1', '45', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/publicity/2016/10/1bed5332-cb45-4708-8a9e-2f2f41c54c57.jpg', '2016-10-28 18:19:45', '2016-10-28 18:51:42', '0', '0', '1');
INSERT INTO `ad` VALUES ('5', '1', '安徽白芍', '', '23', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/publicity/2016/10/d003ba10-e2e4-4aae-aae8-48e9c0409ac7.jpg', '2016-10-28 18:20:52', null, '0', null, '1');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_del` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'D375FB5E379DD75629FEFE5DE51318E41C53AD28', 'rdWILZf11KlnoLdnGxKj9H5NA2fyymTq', 'wangbin', 'burgleaf@163.com', 'burgleaf@163.com', '\0', '2016/7/7 18:04:36', '2016/8/2 14:20:30');
INSERT INTO `admin` VALUES ('2', 'wang', 'E79B1DCF3D9858D788673E67202FE73F9382088C', 'mB2fc6sKQOyWDewylxHYGIK24V68e0nS', 'wangbin', '', 'burgleaf@163.com', '\0', '2016/7/8 17:44:03', '2016/9/20 16:04:31');
INSERT INTO `admin` VALUES ('3', 'wangbin1', 'A67CC9B926840CDB121BF5B82EE871FB2328F22E', 'vvSQg4DHJVXpXYWgULkY9mUHWgMhDCUK', 'wang', '', 'burgleaf@163.com', '\0', '2016/7/8 17:46:38', '2016/7/20 19:29:03');
INSERT INTO `admin` VALUES ('4', 'wangb12', '1223577F417B2D958BB29EEDF6F72B3179115CE7', 'sqJG8oDZpCq0CnYq0qeHcDauJDrMNBKY', 'wangb', '', 'burg@163.com', '\0', '2016/7/11 11:36:46', '');
INSERT INTO `admin` VALUES ('5', 'wangbin3', 'A851A9A3610AA1CA758E378A471D88C85EF0E152', 'bdKXsnKJXoReMRPedWfSpVwyAHJwGZn0', '?BIN', '', 'wangbin@163.com', '\0', '2016/7/12 15:33:16', '');
INSERT INTO `admin` VALUES ('6', 'hehuan', 'DE8BE6CC9673814DB1DAB44587BADC628E2CA1DA', 'gBChZNV8FT7ePLHlP2kw2wqEpfm8rIMF', '??', '', 'heh@yaocai.pro', '\0', '2016/7/13 13:59:16', '2016/7/13 14:02:50');
INSERT INTO `admin` VALUES ('7', 'zhouxj', 'EA37E516EA1144A08600B14B3F6FA506B6ACB835', 'LPIbwyvwbG1xtBasebdAkXWZCOmjdnAA', '???', '', 'zhouxj@yaocai.pro', '\0', '2016/7/25 16:29:35', '2016/7/25 16:38:19');
INSERT INTO `admin` VALUES ('10', 'zhouxj2', '08D14407C96AFF63A00AC5064DE07B7628774AD8', '1vPGq5xqGAbZlHw0cVPssisRdHsUYvzv', '???', '', 'zhouxj@yaocai.pro', '\0', '2016/7/25 16:34:20', '');
INSERT INTO `admin` VALUES ('13', 'wangbin4', '2EC8BA0DD76848917FE303C9C0F173D8E0DAEBE1', 'ySOuKVpSv0xaa9bpbSNuqO5ZMdARF2Tm', 'ww', '', 'bu2@1.com', '\0', '2016/7/25 16:38:13', '2016/9/26 16:54:27');

-- ----------------------------
-- Table structure for ad_type
-- ----------------------------
DROP TABLE IF EXISTS `ad_type`;
CREATE TABLE `ad_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='广告类型';

-- ----------------------------
-- Records of ad_type
-- ----------------------------
INSERT INTO `ad_type` VALUES ('1', '首页banner', '2016-10-26 17:36:11');
INSERT INTO `ad_type` VALUES ('2', '专场广告', '2016-10-26 17:36:19');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8,
  `url` varchar(256) CHARACTER SET utf8 NOT NULL COMMENT '链接',
  `status` int(5) NOT NULL DEFAULT '1' COMMENT '状态 1上架，0下架',
  `create_mem` int(11) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL,
  `update_mem` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父类id',
  `variety` varchar(128) NOT NULL COMMENT '品种名',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `price_desc` varchar(100) NOT NULL COMMENT '价格描述',
  `unit` varchar(10) DEFAULT NULL COMMENT '价格单位',
  `sort` int(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `picture_url` varchar(512) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0锛氫笅鏋讹紝1锛氫笂鏋',
  `level` int(11) NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '0', '根茎类', '根茎类', '', '', '0', '2016-10-13 17:35:09', '', '1', '1', '2016-10-13 17:35:18');
INSERT INTO `category` VALUES ('2', '0', '果实籽仁类', '果实籽仁类', '', '', '0', '2016-08-11 17:04:40', '', '1', '1', '2016-10-18 18:10:17');
INSERT INTO `category` VALUES ('3', '0', '花叶全草类', '花类', '', '', '0', '2016-08-11 17:04:40', '', '1', '1', '2016-10-18 18:10:23');
INSERT INTO `category` VALUES ('4', '0', '藤皮类', '树皮类', '', '', '0', '2016-08-11 17:04:42', '', '1', '1', '2016-10-18 18:10:25');
INSERT INTO `category` VALUES ('5', '0', '树脂菌藻类', '树脂类', '', '', '0', '2016-08-11 17:04:42', '', '1', '1', '2016-10-18 18:10:27');
INSERT INTO `category` VALUES ('6', '0', '矿石动物类', '动物类', '', '', '0', '2016-08-11 17:04:43', '', '1', '1', '2016-10-18 18:10:30');
INSERT INTO `category` VALUES ('7', '0', '其他类', '其他类', '', '', '0', '2016-09-09 17:56:41', '', '1', '1', '2016-10-18 18:10:32');
INSERT INTO `category` VALUES ('8', '1', '三七', '  白芍   安徽白芍  选货  过6-10号筛', '  380-400元/公斤', '', '100', '2016-10-19 16:12:26', '', '0', '2', '2016-10-20 09:48:47');
INSERT INTO `category` VALUES ('10', '1', '白芍', '亳州白芍 选货 过8-12号筛', '28元/公斤', '', '0', '2016-10-19 16:29:04', '', '1', '2', '2016-10-19 16:30:00');
INSERT INTO `category` VALUES ('11', '2', '黄芪', '黄芪质量好，价格有优势', '100-200元每公斤', '', '0', '2016-10-19 17:49:08', '', '1', '2', '2016-10-19 17:49:31');

-- ----------------------------
-- Table structure for code
-- ----------------------------
DROP TABLE IF EXISTS `code`;
CREATE TABLE `code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of code
-- ----------------------------
INSERT INTO `code` VALUES ('1', 'UNIT', '公斤', '2016-10-26 16:07:54');
INSERT INTO `code` VALUES ('2', 'UNIT', '吨', '2016-10-26 16:07:57');
INSERT INTO `code` VALUES ('4', 'UNIT', '袋', '2016-10-26 16:08:29');

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `name` varchar(128) NOT NULL,
  `har_year` varchar(20) NOT NULL,
  `origin` varchar(256) NOT NULL,
  `spec` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `picture_url` varchar(255) NOT NULL,
  `detail` text NOT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `attribute` text,
  `price` decimal(10,0) DEFAULT NULL,
  `unit` varchar(30) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `mark` smallint(10) NOT NULL DEFAULT '0' COMMENT '鏍囪?鏄?惁閲忓ぇ浠蜂紭',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '三七 120头', '三七', '2', '河南', '120头', '8', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/commodity/2016/10/0267c438-8422-4c6a-96f4-f16aa7829e75Crop.jpg', '<p>2</p>', '1', '{}', '2', '公斤', '2', '0', '2015-11-13 12:12:11', '2016-10-28 19:00:08');
INSERT INTO `commodity` VALUES ('2', '三七 100头', '三七', '2', '河南', '100头', '8', '2', '2', '2', '2', '2', '2', '2', '0', '2015-11-13 12:12:11', '2015-11-13 12:12:11');
INSERT INTO `commodity` VALUES ('3', '222', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2015-11-13 12:12:11', '2015-11-13 12:12:11');

-- ----------------------------
-- Table structure for gradient
-- ----------------------------
DROP TABLE IF EXISTS `gradient`;
CREATE TABLE `gradient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_id` int(11) NOT NULL,
  `start` float NOT NULL,
  `end` float NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `unit` varchar(30) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gradient
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(255) DEFAULT NULL COMMENT '密码盐',
  `name` varchar(255) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `is_del` bit(1) NOT NULL COMMENT '是否删除(1是,0否)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('2', 'wang', '51326C2B56950B10BD88E26237A9A777203787E4', 'CQMkiwrG3PSoduaokQwkTpxpy6zs268d', 'wangbin', '13655559999', 'burgleaf@163.com', '\0', '2016-07-08 17:44:03', '2016-10-28 10:19:17');
INSERT INTO `member` VALUES ('3', 'wangbin1', 'A67CC9B926840CDB121BF5B82EE871FB2328F22E', 'vvSQg4DHJVXpXYWgULkY9mUHWgMhDCUK', 'wang', '13655559999', 'burgleaf@163.com', '\0', '2016-07-08 17:46:38', '2016-07-20 19:29:03');
INSERT INTO `member` VALUES ('4', 'wangb12', '1223577F417B2D958BB29EEDF6F72B3179115CE7', 'sqJG8oDZpCq0CnYq0qeHcDauJDrMNBKY', 'wangb', '13655559999', 'burg@163.com', '\0', '2016-07-11 11:36:46', null);
INSERT INTO `member` VALUES ('5', 'wangbin3', 'A851A9A3610AA1CA758E378A471D88C85EF0E152', 'bdKXsnKJXoReMRPedWfSpVwyAHJwGZn0', '王BIN', '13655559999', 'wangbin@163.com', '\0', '2016-07-12 15:33:16', null);
INSERT INTO `member` VALUES ('6', 'hehuan', '10B479A0C6E4B93305B232E97D2CC59309D83B0B', 'YFtiCDNeWsfrbw4K5hd8wrFPlrpUWjo5', '何欢11', '13655559999', 'heh@yaocai.pro', '\0', '2016-07-13 13:59:16', '2016-10-25 15:52:46');
INSERT INTO `member` VALUES ('7', 'zhouxj', 'EA37E516EA1144A08600B14B3F6FA506B6ACB835', 'LPIbwyvwbG1xtBasebdAkXWZCOmjdnAA', '周雄军', '13655559999', 'zhouxj@yaocai.pro', '\0', '2016-07-25 16:29:35', '2016-07-25 16:38:19');
INSERT INTO `member` VALUES ('10', 'zhouxj2', '08D14407C96AFF63A00AC5064DE07B7628774AD8', '1vPGq5xqGAbZlHw0cVPssisRdHsUYvzv', '周雄军', '13655559999', 'zhouxj@yaocai.pro', '\0', '2016-07-25 16:34:20', null);
INSERT INTO `member` VALUES ('13', 'wangbin4', '2EC8BA0DD76848917FE303C9C0F173D8E0DAEBE1', 'ySOuKVpSv0xaa9bpbSNuqO5ZMdARF2Tm', 'ww', '13655559999', 'bu2@1.com', '\0', '2016-07-25 16:38:13', '2016-09-26 16:54:27');
INSERT INTO `member` VALUES ('34', 'admin', 'F8794D413125BFE4782AEE396CA5400A9B779658', 'S11gIrR2wJlp3I8bPSbvudnHe6xFgSdy', 'admin', '13699998888', '1234@qq.com', '\0', '2016-10-25 16:24:59', null);
INSERT INTO `member` VALUES ('36', '1', 'AD2F900CDF131933CE00113DD9346253C020497A', 'SNnqmZXgpGGt9IUPjj4xr0z1QAYvXxRp', 'd', '18801285391', 'dd@13.com', '\0', '2016-10-25 18:15:05', '2016-10-25 18:15:48');

-- ----------------------------
-- Table structure for pick
-- ----------------------------
DROP TABLE IF EXISTS `pick`;
CREATE TABLE `pick` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `code` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `status` int(10) DEFAULT '0' COMMENT '送货单状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pick
-- ----------------------------
INSERT INTO `pick` VALUES ('1', '1', '2016102511', '0', null, null);

-- ----------------------------
-- Table structure for pick_commodity
-- ----------------------------
DROP TABLE IF EXISTS `pick_commodity`;
CREATE TABLE `pick_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `commodity_id` int(11) DEFAULT NULL,
  `num` int(10) DEFAULT NULL,
  `unit` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `total` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pick_commodity
-- ----------------------------

-- ----------------------------
-- Table structure for pick_tracking
-- ----------------------------
DROP TABLE IF EXISTS `pick_tracking`;
CREATE TABLE `pick_tracking` (
  `id` int(11) NOT NULL,
  `op_type` int(5) DEFAULT NULL COMMENT '操作者类别',
  `operator` int(11) DEFAULT NULL COMMENT '操作者id',
  `record_type` int(5) DEFAULT NULL COMMENT '记录类型，同意/拒绝/记录/完成',
  `order_id` int(11) DEFAULT NULL,
  `extra` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '同意理由，拒绝理由，记录内容等附加内容',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pick_tracking
-- ----------------------------

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `path` varchar(255) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='资源';

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES ('1', '客户', 'button', '', '0', 'customer:index', '2016-07-11 09:58:34');
INSERT INTO `resources` VALUES ('2', '客户管理', 'menu', 'user/index', '1', 'customer:view', '2016-07-11 09:58:47');
INSERT INTO `resources` VALUES ('3', '新增客户', 'button', '', '2', 'customer:add', '2016-07-15 11:37:26');
INSERT INTO `resources` VALUES ('4', '修改客户', 'button', '', '2', 'customer:edit', '2016-07-15 11:37:29');
INSERT INTO `resources` VALUES ('10', '系统', 'button', '', '0', 'system:index', '2016-07-11 09:59:19');
INSERT INTO `resources` VALUES ('11', '用户管理', 'menu', 'member/index', '10', 'member:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('12', '角色管理', 'menu', 'role/index', '10', 'role:index', '2016-07-11 10:00:21');
INSERT INTO `resources` VALUES ('13', '添加用户', 'button', '', '11', 'member:add', '2016-07-20 19:16:55');
INSERT INTO `resources` VALUES ('14', '修改用户', 'button', '', '11', 'member:edit', '2016-07-20 19:16:55');
INSERT INTO `resources` VALUES ('15', '添加角色', 'button', '', '12', 'role:add', '2016-07-20 19:16:55');
INSERT INTO `resources` VALUES ('16', '修改角色', 'button', '', '12', 'role:edit', '2016-07-20 19:16:55');
INSERT INTO `resources` VALUES ('17', '角色权限操作', 'menu', '', '12', 'role:power', '2016-08-02 10:17:52');
INSERT INTO `resources` VALUES ('18', '用户角色操作', 'menu', '', '11', 'member:role', '2016-08-02 10:22:09');
INSERT INTO `resources` VALUES ('19', '删除角色', 'button', '', '12', 'role:delete', '2016-08-02 10:28:02');
INSERT INTO `resources` VALUES ('20', '目录', 'button', '', '0', 'directory:index', '2016-08-02 18:01:03');
INSERT INTO `resources` VALUES ('21', '商品管理', 'menu', '', '20', 'commodity:index', '2016-08-02 18:02:46');
INSERT INTO `resources` VALUES ('22', '分类管理', 'menu', '', '20', 'category:index', '2016-08-02 18:03:28');
INSERT INTO `resources` VALUES ('23', '品种管理', 'menu', '', '20', 'breed:index', '2016-08-02 18:04:02');
INSERT INTO `resources` VALUES ('24', '添加商品', 'button', '', '21', 'commodity:add', '2016-08-02 18:05:15');
INSERT INTO `resources` VALUES ('25', '修改商品', 'button', '', '21', 'commodity:edit', '2016-08-02 18:05:15');
INSERT INTO `resources` VALUES ('26', '删除商品', 'button', '', '21', 'commodity:delete', '2016-08-02 18:05:15');
INSERT INTO `resources` VALUES ('27', '添加分类', 'button', '', '22', 'category:add', '2016-08-02 18:03:28');
INSERT INTO `resources` VALUES ('28', '修改分类', 'button', '', '22', 'category:edit', '2016-08-02 18:03:28');
INSERT INTO `resources` VALUES ('29', '删除分类', 'button', '', '22', 'category:delete', '2016-08-02 18:03:28');
INSERT INTO `resources` VALUES ('30', '添加品种', 'menu', '', '23', 'breed:add', '2016-08-02 18:04:02');
INSERT INTO `resources` VALUES ('31', '修改品种', 'menu', '', '23', 'breed:edit', '2016-08-02 18:04:02');
INSERT INTO `resources` VALUES ('32', '删除品种', 'menu', '', '23', 'breed:delete', '2016-08-02 18:04:02');
INSERT INTO `resources` VALUES ('33', '销售', 'button', '', '0', 'sales:index', '2016-08-02 18:01:03');
INSERT INTO `resources` VALUES ('34', '询价管理', 'button', '', '33', 'enquiry:index', '2016-08-04 17:48:28');
INSERT INTO `resources` VALUES ('35', '询价详情', 'menu', '', '34', 'enquiry:info', '2016-08-04 17:52:43');
INSERT INTO `resources` VALUES ('36', '询价单报价', 'menu', '', '34', 'enquiry:quote', '2016-08-04 17:52:43');
INSERT INTO `resources` VALUES ('37', '订单管理', 'button', '', '33', 'order:index', '2016-09-01 16:02:22');
INSERT INTO `resources` VALUES ('38', '新建订单', 'menu', '', '37', 'order:add', '2016-09-01 16:04:37');
INSERT INTO `resources` VALUES ('39', '订单详情', 'menu', '', '37', 'order:info', '2016-09-01 16:05:16');
INSERT INTO `resources` VALUES ('40', '修改订单', 'menu', '', '37', 'order:edit', '2016-09-01 16:05:16');
INSERT INTO `resources` VALUES ('41', '支付管理', 'button', '', '33', 'pay:index', '2016-09-01 17:31:48');
INSERT INTO `resources` VALUES ('42', '支付详情', 'menu', '', '41', 'pay:info', '2016-09-01 17:33:18');
INSERT INTO `resources` VALUES ('43', '收款账户管理', 'menu', 'member/index', '10', 'bank:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('44', '新增收款账户', 'menu', '', '43', 'bank:add', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('45', '修改收款账户', 'menu', '', '43', 'bank:edit', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('46', 'CMS', 'menu', '', '0', 'cms:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('47', '单页面管理', 'menu', '', '46', 'single:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('48', '单页面分类管理', 'menu', '', '46', 'single:category', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('49', '文章管理', 'menu', '', '46', 'post:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('50', '文章分类管理', 'menu', '', '46', 'post:category', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('51', '促销', 'menu', '', '0', 'promotion:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('52', '广告管理', 'menu', '', '51', 'ad:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('53', '新增广告', 'menu', '', '51', 'ad:add', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('54', '修改广告', 'menu', '', '51', 'ad:edit', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('55', '账单管理', 'menu', '', '33', 'bill:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('56', '账单详情', 'menu', '', '55', 'bill:info', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('57', '账单审核', 'menu', '', '55', 'bill:edit', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('58', '物流管理', 'menu', '', '33', 'logistical:index', '2016-07-11 10:00:04');
INSERT INTO `resources` VALUES ('59', '物流详情', 'menu', '', '58', 'logistical:info', '2016-07-11 10:00:04');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('7', '超级管理员', null, '2016-07-12 14:57:59');
INSERT INTO `role` VALUES ('8', '网站客服', null, '2016-08-02 10:36:45');
INSERT INTO `role` VALUES ('10', '网站编辑33', null, '2016-08-02 17:52:57');

-- ----------------------------
-- Table structure for role_admin
-- ----------------------------
DROP TABLE IF EXISTS `role_admin`;
CREATE TABLE `role_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `admin_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`admin_id`),
  KEY `admin_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_admin
-- ----------------------------
INSERT INTO `role_admin` VALUES ('59', '7', '1');
INSERT INTO `role_admin` VALUES ('52', '7', '3');
INSERT INTO `role_admin` VALUES ('63', '7', '13');
INSERT INTO `role_admin` VALUES ('53', '8', '2');
INSERT INTO `role_admin` VALUES ('62', '8', '6');
INSERT INTO `role_admin` VALUES ('64', '10', '13');

-- ----------------------------
-- Table structure for role_member
-- ----------------------------
DROP TABLE IF EXISTS `role_member`;
CREATE TABLE `role_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `role_member_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_member_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_member
-- ----------------------------
INSERT INTO `role_member` VALUES ('52', '7', '3');
INSERT INTO `role_member` VALUES ('63', '7', '13');
INSERT INTO `role_member` VALUES ('66', '7', '34');
INSERT INTO `role_member` VALUES ('53', '8', '2');
INSERT INTO `role_member` VALUES ('62', '8', '6');
INSERT INTO `role_member` VALUES ('68', '8', '36');
INSERT INTO `role_member` VALUES ('64', '10', '13');

-- ----------------------------
-- Table structure for role_resources
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resources_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `resources_id` (`resources_id`),
  CONSTRAINT `role_resources_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_resources_ibfk_2` FOREIGN KEY (`resources_id`) REFERENCES `resources` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1356 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resources
-- ----------------------------
INSERT INTO `role_resources` VALUES ('951', '8', '1');
INSERT INTO `role_resources` VALUES ('952', '8', '2');
INSERT INTO `role_resources` VALUES ('953', '8', '3');
INSERT INTO `role_resources` VALUES ('954', '8', '4');
INSERT INTO `role_resources` VALUES ('955', '8', '10');
INSERT INTO `role_resources` VALUES ('956', '8', '11');
INSERT INTO `role_resources` VALUES ('957', '8', '13');
INSERT INTO `role_resources` VALUES ('958', '8', '14');
INSERT INTO `role_resources` VALUES ('959', '8', '18');
INSERT INTO `role_resources` VALUES ('960', '8', '12');
INSERT INTO `role_resources` VALUES ('961', '8', '15');
INSERT INTO `role_resources` VALUES ('962', '8', '16');
INSERT INTO `role_resources` VALUES ('963', '8', '17');
INSERT INTO `role_resources` VALUES ('964', '8', '19');
INSERT INTO `role_resources` VALUES ('965', '8', '43');
INSERT INTO `role_resources` VALUES ('966', '8', '44');
INSERT INTO `role_resources` VALUES ('967', '8', '45');
INSERT INTO `role_resources` VALUES ('968', '8', '20');
INSERT INTO `role_resources` VALUES ('969', '8', '21');
INSERT INTO `role_resources` VALUES ('970', '8', '24');
INSERT INTO `role_resources` VALUES ('971', '8', '25');
INSERT INTO `role_resources` VALUES ('972', '8', '26');
INSERT INTO `role_resources` VALUES ('973', '8', '22');
INSERT INTO `role_resources` VALUES ('974', '8', '27');
INSERT INTO `role_resources` VALUES ('975', '8', '28');
INSERT INTO `role_resources` VALUES ('976', '8', '29');
INSERT INTO `role_resources` VALUES ('977', '8', '23');
INSERT INTO `role_resources` VALUES ('978', '8', '30');
INSERT INTO `role_resources` VALUES ('979', '8', '31');
INSERT INTO `role_resources` VALUES ('980', '8', '32');
INSERT INTO `role_resources` VALUES ('981', '8', '33');
INSERT INTO `role_resources` VALUES ('982', '8', '34');
INSERT INTO `role_resources` VALUES ('983', '8', '35');
INSERT INTO `role_resources` VALUES ('984', '8', '36');
INSERT INTO `role_resources` VALUES ('985', '8', '37');
INSERT INTO `role_resources` VALUES ('986', '8', '38');
INSERT INTO `role_resources` VALUES ('987', '8', '39');
INSERT INTO `role_resources` VALUES ('988', '8', '40');
INSERT INTO `role_resources` VALUES ('989', '8', '41');
INSERT INTO `role_resources` VALUES ('990', '8', '42');
INSERT INTO `role_resources` VALUES ('991', '8', '55');
INSERT INTO `role_resources` VALUES ('992', '8', '56');
INSERT INTO `role_resources` VALUES ('993', '8', '57');
INSERT INTO `role_resources` VALUES ('994', '8', '58');
INSERT INTO `role_resources` VALUES ('995', '8', '59');
INSERT INTO `role_resources` VALUES ('996', '8', '46');
INSERT INTO `role_resources` VALUES ('997', '8', '47');
INSERT INTO `role_resources` VALUES ('998', '8', '48');
INSERT INTO `role_resources` VALUES ('999', '8', '49');
INSERT INTO `role_resources` VALUES ('1000', '8', '50');
INSERT INTO `role_resources` VALUES ('1001', '8', '51');
INSERT INTO `role_resources` VALUES ('1002', '8', '52');
INSERT INTO `role_resources` VALUES ('1003', '8', '53');
INSERT INTO `role_resources` VALUES ('1004', '8', '54');
INSERT INTO `role_resources` VALUES ('1068', '7', '1');
INSERT INTO `role_resources` VALUES ('1069', '7', '2');
INSERT INTO `role_resources` VALUES ('1070', '7', '3');
INSERT INTO `role_resources` VALUES ('1071', '7', '4');
INSERT INTO `role_resources` VALUES ('1072', '7', '10');
INSERT INTO `role_resources` VALUES ('1073', '7', '11');
INSERT INTO `role_resources` VALUES ('1074', '7', '13');
INSERT INTO `role_resources` VALUES ('1075', '7', '14');
INSERT INTO `role_resources` VALUES ('1076', '7', '18');
INSERT INTO `role_resources` VALUES ('1077', '7', '12');
INSERT INTO `role_resources` VALUES ('1078', '7', '15');
INSERT INTO `role_resources` VALUES ('1079', '7', '16');
INSERT INTO `role_resources` VALUES ('1080', '7', '17');
INSERT INTO `role_resources` VALUES ('1081', '7', '19');
INSERT INTO `role_resources` VALUES ('1082', '7', '43');
INSERT INTO `role_resources` VALUES ('1083', '7', '44');
INSERT INTO `role_resources` VALUES ('1084', '7', '45');
INSERT INTO `role_resources` VALUES ('1085', '7', '20');
INSERT INTO `role_resources` VALUES ('1086', '7', '21');
INSERT INTO `role_resources` VALUES ('1087', '7', '24');
INSERT INTO `role_resources` VALUES ('1088', '7', '25');
INSERT INTO `role_resources` VALUES ('1089', '7', '26');
INSERT INTO `role_resources` VALUES ('1090', '7', '22');
INSERT INTO `role_resources` VALUES ('1091', '7', '27');
INSERT INTO `role_resources` VALUES ('1092', '7', '28');
INSERT INTO `role_resources` VALUES ('1093', '7', '29');
INSERT INTO `role_resources` VALUES ('1094', '7', '23');
INSERT INTO `role_resources` VALUES ('1095', '7', '30');
INSERT INTO `role_resources` VALUES ('1096', '7', '31');
INSERT INTO `role_resources` VALUES ('1097', '7', '32');
INSERT INTO `role_resources` VALUES ('1098', '7', '33');
INSERT INTO `role_resources` VALUES ('1099', '7', '34');
INSERT INTO `role_resources` VALUES ('1100', '7', '35');
INSERT INTO `role_resources` VALUES ('1101', '7', '36');
INSERT INTO `role_resources` VALUES ('1102', '7', '37');
INSERT INTO `role_resources` VALUES ('1103', '7', '38');
INSERT INTO `role_resources` VALUES ('1104', '7', '39');
INSERT INTO `role_resources` VALUES ('1105', '7', '40');
INSERT INTO `role_resources` VALUES ('1106', '7', '41');
INSERT INTO `role_resources` VALUES ('1107', '7', '42');
INSERT INTO `role_resources` VALUES ('1108', '7', '55');
INSERT INTO `role_resources` VALUES ('1109', '7', '56');
INSERT INTO `role_resources` VALUES ('1110', '7', '57');
INSERT INTO `role_resources` VALUES ('1111', '7', '58');
INSERT INTO `role_resources` VALUES ('1112', '7', '59');
INSERT INTO `role_resources` VALUES ('1338', '10', '1');
INSERT INTO `role_resources` VALUES ('1339', '10', '2');
INSERT INTO `role_resources` VALUES ('1340', '10', '3');
INSERT INTO `role_resources` VALUES ('1341', '10', '4');
INSERT INTO `role_resources` VALUES ('1342', '10', '10');
INSERT INTO `role_resources` VALUES ('1343', '10', '11');
INSERT INTO `role_resources` VALUES ('1344', '10', '14');
INSERT INTO `role_resources` VALUES ('1345', '10', '20');
INSERT INTO `role_resources` VALUES ('1346', '10', '23');
INSERT INTO `role_resources` VALUES ('1347', '10', '30');
INSERT INTO `role_resources` VALUES ('1348', '10', '33');
INSERT INTO `role_resources` VALUES ('1349', '10', '34');
INSERT INTO `role_resources` VALUES ('1350', '10', '35');
INSERT INTO `role_resources` VALUES ('1351', '10', '37');
INSERT INTO `role_resources` VALUES ('1352', '10', '40');
INSERT INTO `role_resources` VALUES ('1353', '10', '46');
INSERT INTO `role_resources` VALUES ('1354', '10', '47');
INSERT INTO `role_resources` VALUES ('1355', '10', '48');

-- ----------------------------
-- Table structure for sample_address
-- ----------------------------
DROP TABLE IF EXISTS `sample_address`;
CREATE TABLE `sample_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL COMMENT '收货地址',
  `receiver` varchar(20) NOT NULL COMMENT '收货人',
  `receiver_phone` varchar(11) NOT NULL COMMENT '收货人电话',
  `remark` varchar(512) NOT NULL COMMENT '备注信息',
  `send_id` int(11) NOT NULL COMMENT '寄样单id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sample_address
-- ----------------------------
INSERT INTO `sample_address` VALUES ('1', 'dd1', 'dd', 'dd', '11,11', '1', '2016-10-20 18:33:12', '2016-10-21 10:42:47');
INSERT INTO `sample_address` VALUES ('2', '', '', '', '1', '2', '2016-10-21 10:49:36', '2016-10-21 10:58:42');

-- ----------------------------
-- Table structure for sample_tracking
-- ----------------------------
DROP TABLE IF EXISTS `sample_tracking`;
CREATE TABLE `sample_tracking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` int(11) NOT NULL COMMENT '操作人id',
  `name` varchar(20) NOT NULL COMMENT '操作人名称',
  `type` int(3) NOT NULL DEFAULT '0' COMMENT '0：后台人员,1:前台用户',
  `send_id` int(11) NOT NULL COMMENT '寄样单id',
  `record_type` int(11) NOT NULL DEFAULT '0' COMMENT '追踪类型 0：申请寄样，1：同意寄样，2：拒绝寄样，3:客户预约，4：寄送样品，5：客户已收货，6：跟踪记录，7：客户留言，8：寄样单受理完成',
  `extra` varchar(255) NOT NULL COMMENT '寄样追踪附加内容：如同意理由，等等',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sample_tracking
-- ----------------------------
INSERT INTO `sample_tracking` VALUES ('1', '1', '测试肖', '0', '1', '1', '同意理由：同意寄样', '2016-10-25 18:08:35');
INSERT INTO `sample_tracking` VALUES ('2', '1', '测试肖', '0', '1', '5', '预约时间：2016年10月25日 00:00 来访人：1 电话：1', '2016-10-25 18:09:12');
INSERT INTO `sample_tracking` VALUES ('3', '1', '测试肖', '0', '1', '6', '1', '2016-10-25 18:09:22');
INSERT INTO `sample_tracking` VALUES ('4', '1', '测试肖', '0', '1', '6', '短短的', '2016-10-25 18:09:25');
INSERT INTO `sample_tracking` VALUES ('5', '1', '测试肖', '0', '1', '8', '', '2016-10-25 18:09:28');

-- ----------------------------
-- Table structure for send_sample
-- ----------------------------
DROP TABLE IF EXISTS `send_sample`;
CREATE TABLE `send_sample` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT 'user表主键',
  `code` varchar(16) NOT NULL COMMENT '寄样单号',
  `intention` varchar(100) NOT NULL COMMENT '寄样商品',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '处理状态',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of send_sample
-- ----------------------------
INSERT INTO `send_sample` VALUES ('1', '1', '20161014003', '1,2', '5', '2016-10-18 14:52:54', '2016-10-19 14:52:58');
INSERT INTO `send_sample` VALUES ('2', '1', '20161020001', '2', '0', '2016-10-20 15:53:20', '2016-10-20 15:53:24');

-- ----------------------------
-- Table structure for special
-- ----------------------------
DROP TABLE IF EXISTS `special`;
CREATE TABLE `special` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `pictuer_url` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0 禁用 1 启用',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_mem` int(11) DEFAULT NULL COMMENT '修改人id',
  `create_mem` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专场';

-- ----------------------------
-- Records of special
-- ----------------------------
INSERT INTO `special` VALUES ('1', '白芍', '/Software/JAVAEE/apache-tomcat-8.0.23/webapps/ms-files/special/2016/10/c43b5d0f-4594-4d29-87ef-d6b0492efd87Crop.jpg', '', '1', '2016-10-28 18:26:11', '2016-10-28 18:26:11', '23', '34', '34');

-- ----------------------------
-- Table structure for special_commodity
-- ----------------------------
DROP TABLE IF EXISTS `special_commodity`;
CREATE TABLE `special_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `special_id` int(11) DEFAULT NULL COMMENT '专场id',
  `commodity_id` int(11) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='专场商品';

-- ----------------------------
-- Records of special_commodity
-- ----------------------------
INSERT INTO `special_commodity` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for tracking_detail
-- ----------------------------
DROP TABLE IF EXISTS `tracking_detail`;
CREATE TABLE `tracking_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_id` int(11) NOT NULL,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1：物流信息 ，2：客户来访信息',
  `company` varchar(128) DEFAULT NULL COMMENT '快递公司',
  `tracking_no` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `vistor` varchar(50) DEFAULT NULL COMMENT '来访人',
  `vistor_phone` varchar(11) DEFAULT NULL COMMENT '来访电话',
  `vistor_time` datetime DEFAULT NULL COMMENT '来访时间',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tracking_detail
-- ----------------------------
INSERT INTO `tracking_detail` VALUES ('1', '1', '1', null, null, '1', '1', '2016-10-25 00:00:00', '2016-10-25 18:09:12');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '1：注册用户，0：申请寄样生成的用户',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '用户密码',
  `salt` varchar(40) NOT NULL,
  `openid` varchar(32) DEFAULT NULL COMMENT '用户公众号对应的openid',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '-1', '13590000000', '123456', '', null, '2016-10-24 00:00:00', '2016-10-24 00:00:00');

-- ----------------------------
-- Table structure for user_detail
-- ----------------------------
DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(5) NOT NULL DEFAULT '0' COMMENT '补全信息类型',
  `nickname` varchar(255) NOT NULL COMMENT '联系人姓名',
  `phone` varchar(255) NOT NULL COMMENT '联系电话',
  `area` varchar(255) NOT NULL COMMENT '地区',
  `name` varchar(255) NOT NULL COMMENT '姓名/公司',
  `remark` varchar(255) NOT NULL COMMENT '用户备注',
  `user_id` int(11) NOT NULL COMMENT 'user表id',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_detail
-- ----------------------------
INSERT INTO `user_detail` VALUES ('2', '4', '肖先生', '13638654365', '湖北武汉', 'aa', '324trfdg', '1', '2016-10-18 14:52:17', '2016-10-21 10:54:10');
