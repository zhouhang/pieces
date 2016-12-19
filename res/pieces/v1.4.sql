
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