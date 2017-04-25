ALTER TABLE `anon_enquiry`
ADD COLUMN `enquriy_bill_id`  int NULL DEFAULT NULL COMMENT '询价单id' AFTER `status`;

CREATE TABLE `user_follow_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `follow_id` int(11) DEFAULT NULL COMMENT '跟进人id',
  `create_time` datetime DEFAULT NULL,
  `result` text COMMENT '跟进结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


#接入快递api
CREATE TABLE `logistical_trace` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `logistic_code` varchar(50) NOT NULL COMMENT '快递单号',
  `shipper_code` varchar(10) NOT NULL COMMENT '快递公司编码',
  `accept_station` text NOT NULL COMMENT '轨迹内容',
  `accept_time` datetime NOT NULL COMMENT '轨迹时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


# 更新首页橱窗商品
UPDATE `pieces`.`home_weight` SET `value`='1065,310,694,687,1485,1498,1824,542' WHERE `id`='91'; -- 根茎类
UPDATE `pieces`.`home_weight` SET `value`='267,710,886,1329,1380,1168,203,275' WHERE `id`='101'; -- 果实籽仁类
UPDATE `pieces`.`home_weight` SET `value`='1169,1725,1807,444,1907,494,183,1258' WHERE `id`='111'; -- 全草花叶类
UPDATE `pieces`.`home_weight` SET `value`='484,233,405,1546,1944,831,185,883' WHERE `id`='122'; -- 矿石动物类
UPDATE `pieces`.`home_weight` SET `value`='126,431,646,699,1884,1471,553,1022' WHERE `id`='131'; -- 藤皮类
UPDATE `pieces`.`home_weight` SET `value`='975,489,634,623,487,1873,492,249' WHERE `id`='141'; --树脂菌藻类


