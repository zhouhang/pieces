CREATE TABLE `carts_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `commodity_id` int(11) NOT NULL COMMENT '商品id',
  `create_time` datetime DEFAULT NULL COMMENT '加入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车商品';