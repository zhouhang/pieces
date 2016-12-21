
DROP TABLE IF EXISTS `qualification_pics`;
CREATE TABLE `qualification_pics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) DEFAULT NULL COMMENT 'user_qualification_id',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `index_num` int(5) DEFAULT NULL COMMENT '图片顺序',
  `type` int(5) DEFAULT NULL COMMENT '标记图片类型，暂为null',
  PRIMARY KEY (`id`),
  KEY `qualification_pics_ibfk` (`qid`),
  CONSTRAINT `qualification_pics_ibfk` FOREIGN KEY (`qid`) REFERENCES `user_qualification` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/**
迁移老数据的存储过程
 */
CREATE PROCEDURE insertdata()
BEGIN
DECLARE quoteid int;
DECLARE url varchar(255) ;
DECLARE s int default 0;
declare cur cursor for (select id,picture_url from user_qualification);
declare CONTINUE HANDLER FOR SQLSTATE '02000' SET s = null;
OPEN cur;
FETCH cur INTO quoteid,url;
WHILE
( s is not null) DO
 insert into qualification_pics (qid,picture_url,index_num) VALUES(quoteid,url,0);
 FETCH cur INTO quoteid,url;
    END WHILE;
CLOSE cur;
END
call insertdata();

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `pay_type` int(5) DEFAULT NULL COMMENT '支付方式',
  `status` int(5) DEFAULT NULL COMMENT '支付状态',
  `money` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `account_bill_id` int(11) DEFAULT NULL COMMENT '账单id',
  `trade_no` varchar(64) DEFAULT NULL COMMENT '支付宝/微信交易号',
  `out_trade_no` varchar(255) DEFAULT NULL COMMENT '提供给第三方支付的订单号，必须唯一',
  `remark` varchar(255) DEFAULT NULL,
  `in_param` text COMMENT '返回参数',
  `out_param` text COMMENT '传出参数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `callback_time` datetime DEFAULT NULL COMMENT '回调时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='线上支付表';

ALTER TABLE `pay_record`
DROP COLUMN `payment_id`,
ADD COLUMN `payment_id`  int(11) NULL DEFAULT NULL COMMENT '三方支付id' AFTER `account_bill_id`;

ALTER TABLE `pay_record`
DROP COLUMN `pay_type`,
ADD COLUMN `pay_type`  int(5) NULL DEFAULT 0 COMMENT '0,线下打款，1支付宝支付' AFTER `fail_reason`;