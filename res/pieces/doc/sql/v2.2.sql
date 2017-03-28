ALTER TABLE `pieces`.`logistical`
ADD COLUMN `type` INT NOT NULL COMMENT '物流类型 1快递 2自提 3货运部发货' AFTER `create_date`,
ADD COLUMN `receiving_date` DATETIME NULL COMMENT '预计到货时间 或者提货时间' AFTER `type`,
ADD COLUMN `driver_name` VARCHAR(16) NULL COMMENT '司机姓名' AFTER `receiving_date`,
ADD COLUMN `driver_tel` VARCHAR(32) NULL COMMENT '司机电话' AFTER `driver_name`,
ADD COLUMN `pick_up` VARCHAR(64) NULL COMMENT '提货地点' AFTER `driver_tel`,
ADD COLUMN `company_code` VARCHAR(32) NULL COMMENT '快递公司编码' AFTER `pick_up`,
ADD COLUMN `mem_id` INT NOT NULL COMMENT '操作员Id' AFTER `company_code`;