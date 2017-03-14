
CREATE TABLE `recruit_agent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(130) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `area_id` int(10) DEFAULT NULL COMMENT '区域id',
  `create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `last_follow_time` datetime DEFAULT NULL COMMENT '最后一次跟进时间',
  `last_follow_id` int(11) DEFAULT NULL COMMENT '跟进人',
  `status` int(2) DEFAULT '0' COMMENT '状态0未处理1已处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='代理商招募';

CREATE TABLE `recruit_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `recruit_agent_id` int(11) DEFAULT NULL COMMENT '招募记录id',
  `follow_id` int(11) DEFAULT NULL COMMENT '跟进人id',
  `create_time` datetime DEFAULT NULL,
  `result` text COMMENT '跟踪记录',
  PRIMARY KEY (`id`),
  KEY `recruit_fk_1` (`recruit_agent_id`),
  KEY `recruit_fk_2` (`follow_id`),
  CONSTRAINT `recruit_fk_2` FOREIGN KEY (`follow_id`) REFERENCES `member` (`id`),
  CONSTRAINT `recruit_fk_1` FOREIGN KEY (`recruit_agent_id`) REFERENCES `recruit_agent` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='招募代理商跟踪记录';

INSERT INTO `pieces`.`resources` (`id`, `name`, `type`, `path`, `pid`, `permission`, `create_date`) VALUES ('67', '合作伙伴申请', 'menu', 'recruit/index', '60', 'recruit:index', '2017-01-13 10:02:59');
INSERT INTO `pieces`.`resources` (`id`, `name`, `type`, `path`, `pid`, `permission`, `create_date`) VALUES ('68', '合作伙伴申请详情', 'button', '', '60', 'recruit:detail', '2017-01-13 10:03:36');
INSERT INTO `pieces`.`resources` (`id`, `name`, `type`, `path`, `pid`, `permission`, `create_date`) VALUES ('69', '合作伙伴申请记录', 'button', '', '60', 'recruit:trail', '2017-01-13 10:04:06');


ALTER TABLE `user`
ADD UNIQUE INDEX `phone` (`contact_mobile`) USING BTREE ;

