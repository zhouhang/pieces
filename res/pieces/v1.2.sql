ALTER TABLE `pieces`.`order_commodity` DROP COLUMN `expectDate`;

ALTER TABLE `pieces`.`enquiry_commoditys`
DROP COLUMN `expect_date`,
DROP COLUMN `expect_price`;

ALTER TABLE `pieces`.`enquiry_commoditys`
CHANGE COLUMN `amount` `amount` INT(10) NULL COMMENT '数量' ;
