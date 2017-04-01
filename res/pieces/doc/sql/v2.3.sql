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
