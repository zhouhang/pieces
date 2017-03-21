ALTER TABLE `pieces`.`commodity`
ADD COLUMN `guide_price` DECIMAL(10,2) NULL COMMENT '指导价' AFTER `sort`;

ALTER TABLE `pieces`.`enquiry_commoditys`
ADD COLUMN `price` DECIMAL(10,2) NULL COMMENT '开票价' AFTER `create_time`;

ALTER TABLE `pieces`.`user`
ADD COLUMN `open_id` VARCHAR(45) NULL COMMENT '微信openId' AFTER `update_time`;

ALTER TABLE `pieces`.`order_form`
ADD COLUMN `finish_date` DATETIME NULL COMMENT '订单完成时间' AFTER `expire_date`;

-- 2017-03-20
alter table carts_commodity add constraint cart_index UNIQUE(user_id,commodity_id);

DELETE
FROM carts_commodity WHERE id in (
  select id from (
    select id  from carts_commodity where
    (user_id,commodity_id) IN (
        SELECT
            user_id,
            commodity_id
        FROM
            carts_commodity
        GROUP BY
            user_id,
            commodity_id
        HAVING
            count(*) > 1
    ) AND id NOT IN (
    SELECT
        min(id)
    FROM
        carts_commodity
    GROUP BY
        user_id,
        commodity_id
    HAVING
        count(*) > 1
    )
  )b
) and id >30;



