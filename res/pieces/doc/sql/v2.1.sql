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

#seo 配置
CREATE TABLE `seo_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(6) NOT NULL COMMENT '类型：1基本设置,2商品列表3，商品详情4，搜索结果5，文章列表，文章详情',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `key_word` varchar(255) NOT NULL COMMENT '关键字',
  `intro` varchar(255) NOT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

INSERT INTO `resources` VALUES ('70', 'SEO管理', 'menu', '/seo/setting/1', '0', 'seo:index', '2017-03-20 21:42:13');

ALTER TABLE `category`
  ADD COLUMN `key_word`  varchar(255) NULL COMMENT '关键字' AFTER `level`,
  ADD COLUMN `intro`  varchar(255) NULL COMMENT '描述' AFTER `key_word`;

ALTER TABLE `commodity`
  ADD COLUMN `key_word`  varchar(255) NULL COMMENT '关键字' AFTER `guide_price`,
  ADD COLUMN `intro`  varchar(255) NULL COMMENT '描述' AFTER `key_word`;

ALTER TABLE `article_category`
  ADD COLUMN `key_word`  varchar(255) NULL COMMENT '关键字' AFTER `icon`,
  ADD COLUMN `intro`  varchar(255) NULL COMMENT '描述' AFTER `key_word`;

ALTER TABLE `article`
  ADD COLUMN `key_word`  varchar(255) NULL COMMENT '关键字' AFTER `is_top`,
  ADD COLUMN `intro`  varchar(255) NULL COMMENT '描述' AFTER `key_word`;

ALTER TABLE `pieces`.`order_commodity`
ADD COLUMN `commodity_id` INT NULL AFTER `order_id`;

update order_commodity, enquiry_commoditys
set order_commodity.commodity_id=enquiry_commoditys.commodity_id
where order_commodity.enquiry_commodity_id=enquiry_commoditys.id and order_commodity.id

-- 禁用商品sql
UPDATE TABLE `commodity` set status = 0 WHERE id IN (77,90,1934,19361937,1938,1939,1940,1941,1942,115,116,127,131,132,136,166,167,225,229,174,206,234,101,255,260,277,278,285,287,288,289,
1298,1643,339,340,355,356,360,362,365,366,367,433,160,454,457,112,119,483,485,490,511,512,513,516,517,514,515,530,532,
546,552,647,649,688,698,764,765,776,777,778,779,787,788,790,795,815,861,880,888,890,941,987,1001,1007,1047,1048,1066,138,1095,
1115,1151,1175,1179,1180,1182,1215,1220,1223,1267,1269,1322,1361,1362,1364,1391,1436,1473,1486,1487,1490,1496,1499,1509,
1511,1512,1562,1575,1576,1640,1649,1678,1680,196,1766,1814,1822,1823,652,653,1569,783,1874,1877,1916,1922);





