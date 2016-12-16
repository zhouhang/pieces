

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