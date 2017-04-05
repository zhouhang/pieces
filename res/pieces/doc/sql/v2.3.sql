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

