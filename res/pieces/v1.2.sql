ALTER TABLE `pieces`.`order_commodity` DROP COLUMN `expectDate`;

ALTER TABLE `pieces`.`enquiry_commoditys`
DROP COLUMN `expect_date`,
DROP COLUMN `expect_price`;

ALTER TABLE `pieces`.`enquiry_commoditys`
CHANGE COLUMN `amount` `amount` INT(10) NULL COMMENT '数量' ;

ALTER TABLE `pieces`.`enquiry_bills`
ADD COLUMN `expire_date` DATETIME NULL COMMENT '询价单过期时间' AFTER `quoted_time`;

ALTER TABLE `pieces`.`order_form`
ADD COLUMN `expire_date` DATETIME NULL COMMENT '订单过期时间' AFTER `delivery_date`;

## 准备数据回填方案
1. 询价单 报价日期的数据从商品上回填到询价单上.
2. 订单过期时间 待付款状态的 订单.需要设定 订单过期日期.
