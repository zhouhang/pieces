CREATE TABLE `carts_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commodity_id` int(11) NOT NULL COMMENT '商品id',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车商品';

ALTER TABLE `pieces`.`enquiry_bills`
ADD COLUMN `type` TINYINT NULL DEFAULT 0 COMMENT '0已读 1未读' AFTER `expire_date`;