-- Create tables
DROP TABLE UC_USER;
CREATE TABLE UC_USER (
  ID INTEGER NOT NULL PRIMARY KEY,
  USER_NAME VARCHAR2(100) NOT NULL ,
  PASSWORD VARCHAR2(200) NOT NULL ,
  SALT VARCHAR2(200) DEFAULT NULL ,
  PHONE VARCHAR2(50) DEFAULT NULL ,
  MOBILE VARCHAR2(45) DEFAULT NULL,
  EMAIL VARCHAR2(200) DEFAULT NULL ,
  ID_CARD VARCHAR2(100) DEFAULT NULL,
  ADDRESS VARCHAR2(200) DEFAULT NULL,
  ACCESS_IP VARCHAR2(45) DEFAULT NULL,
  CREATE_TIME date DEFAULT NULL ,
  UPDATE_TIME date DEFAULT NULL ,
  EXPIRE_TIME date DEFAULT NULL ,
  LAST_ACCESS_TIME date DEFAULT NULL ,
  STATUS INTEGER DEFAULT 0 NOT NULL,
  CONSTRAINT UC_USER_UNIQUE UNIQUE(USER_NAME)
) tablespace ZYC_DATA;

-- Add comment on tables
comment on table UC_USER is '前台会员中心用户表'; 
comment on column UC_USER.USER_NAME is '用户名'; 
comment on column UC_USER.PASSWORD is '密码'; 
comment on column UC_USER.SALT is '加密盐'; 
comment on column UC_USER.PHONE is '电话'; 
comment on column UC_USER.MOBILE is '手机'; 
comment on column UC_USER.EMAIL is '电子邮箱'; 
comment on column UC_USER.ID_CARD is '身份证'; 
comment on column UC_USER.ADDRESS is '地址'; 
comment on column UC_USER.ACCESS_IP is '访问IP'; 
comment on column UC_USER.CREATE_TIME is '创建时间'; 
comment on column UC_USER.UPDATE_TIME is '修改时间'; 
comment on column UC_USER.EXPIRE_TIME is '过期时间'; 
comment on column UC_USER.LAST_ACCESS_TIME is '上次访问时间'; 
comment on column UC_USER.STATUS is '状态 0：有效 1：删除'; 

CREATE SEQUENCE SEQ_UC_USER START WITH 1 INCREMENT BY 1;
