ALTER TABLE `pieces`.`logistical`
ADD COLUMN `type` INT NOT NULL COMMENT '物流类型 1快递 2自提 3货运部发货' AFTER `create_date`,
ADD COLUMN `receiving_date` DATETIME NULL COMMENT '预计到货时间 或者提货时间' AFTER `type`,
ADD COLUMN `driver_name` VARCHAR(16) NULL COMMENT '司机姓名' AFTER `receiving_date`,
ADD COLUMN `driver_tel` VARCHAR(32) NULL COMMENT '司机电话' AFTER `driver_name`,
ADD COLUMN `pick_up` VARCHAR(64) NULL COMMENT '提货地点' AFTER `driver_tel`,
ADD COLUMN `company_code` VARCHAR(32) NULL COMMENT '快递公司编码' AFTER `pick_up`,
ADD COLUMN `mem_id` INT NOT NULL COMMENT '操作员Id' AFTER `company_code`;


-- 20170329
ALTER TABLE `pieces`.`user`
CHANGE COLUMN `contact_mobile` `contact_mobile` VARCHAR(11) NULL COMMENT '联系人手机' ,
DROP INDEX `phone` ;
-- 更新客户认证信息20170330
update user_certification set company = '东营市医药公司广饶分公司' where company='东营广饶';
update user_certification set company = '广饶县稻庄镇卫生院' where company='广饶稻庄';
update user_certification set company = '广饶县广饶街道社区卫生服务中心' where company='广饶服务中心';
update user_certification set company = '东营市医药药材采购供应站利津经营部' where company='东营利津';
update user_certification set company = '天津市河西荣皖中医医院' where company='天津荣皖';
update user_certification set company = '天津河西土城医院' where company='天津河西土城';
update user_certification set company = '天津市中医药研究院附属医院' where company='天津市附属医院';
update user_certification set company = '台州市黄岩建标中医门诊有限公司' where company='台州黄岩';
