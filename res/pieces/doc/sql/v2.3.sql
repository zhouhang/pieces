ALTER TABLE `anon_enquiry`
ADD COLUMN `enquriy_bill_id`  int NULL DEFAULT NULL COMMENT '生成的询价单id' AFTER `status`;
