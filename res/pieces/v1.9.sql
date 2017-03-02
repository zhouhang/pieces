
ALTER TABLE `pieces`.`commodity`
ADD COLUMN `sort` INT NULL DEFAULT 0 COMMENT '排序字段越大越靠前' AFTER `create_time`;
