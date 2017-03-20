ALTER TABLE `pieces`.`commodity`
ADD COLUMN `guide_price` DECIMAL(10,2) NULL COMMENT '指导价' AFTER `sort`;

ALTER TABLE `pieces`.`enquiry_commoditys`
ADD COLUMN `price` DECIMAL(10,2) NULL COMMENT '开票价' AFTER `create_time`;

ALTER TABLE `pieces`.`user`
ADD COLUMN `open_id` VARCHAR(45) NULL COMMENT '微信openId' AFTER `update_time`;

ALTER TABLE `pieces`.`order_form`
ADD COLUMN `finish_date` DATETIME NULL COMMENT '订单完成时间' AFTER `expire_date`;

#seo 配置
CREATE TABLE `seo_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(6) NOT NULL COMMENT '类型：1基本设置,2商品列表3，商品详情4，搜索结果5，文章列表，文章详情',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `key_word` varchar(255) NOT NULL COMMENT '关键字',
  `intro` varchar(255) NOT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO `resources` VALUES ('70', 'SEO管理', 'menu', '/seo/setting/1', '0', 'seo:index', '2017-03-20 21:42:13');

