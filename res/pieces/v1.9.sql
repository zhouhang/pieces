
ALTER TABLE `pieces`.`commodity`
ADD COLUMN `sort` INT NULL DEFAULT 0 COMMENT '排序字段越大越靠前' AFTER `create_time`;

-- 功效页面商品替换
UPDATE `pieces`.`home_weight` SET `value`='861,1628,1240,764,1743' WHERE `id`='200';
UPDATE `pieces`.`home_weight` SET `value`='607,1142,203,546,1214' WHERE `id`='201';
UPDATE `pieces`.`home_weight` SET `value`='259,276,485,720,183' WHERE `id`='202';
UPDATE `pieces`.`home_weight` SET `value`='1781,326,648,1915,1001' WHERE `id`='203';
UPDATE `pieces`.`home_weight` SET `value`='575,814,1546,1701,347' WHERE `id`='204';
UPDATE `pieces`.`home_weight` SET `value`='87,80,37,223,328' WHERE `id`='205';
UPDATE `pieces`.`home_weight` SET `value`='52,516,687,1050,1941' WHERE `id`='206';
UPDATE `pieces`.`home_weight` SET `value`='633,71,256,694,1420' WHERE `id`='207';
UPDATE `pieces`.`home_weight` SET `value`='405,1086,1509,1102,1415' WHERE `id`='208';
UPDATE `pieces`.`home_weight` SET `value`='143,646,1242,1436,1787' WHERE `id`='209';
UPDATE `pieces`.`home_weight` SET `value`='1877,1109,890,1167,932' WHERE `id`='210';
UPDATE `pieces`.`home_weight` SET `value`='429,458,1324,1233,1022' WHERE `id`='211';
