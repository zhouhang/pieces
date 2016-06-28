package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.BusiTermOrderDetail;

public interface BusiTermOrderDetailDao {

	int deleteByPrimaryKey(String id);

    int insert(BusiTermOrderDetail record);

    int insertSelective(BusiTermOrderDetail record);

    BusiTermOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BusiTermOrderDetail record);

    int updateByPrimaryKey(BusiTermOrderDetail record);
    
    /**
     * @Description: 根据订单编号查询账期详情
     * @Author: 赵航
     * @Date: 2015年9月10日
     * @param orderId 订单编号
     * @return
     */
    BusiTermOrderDetail selectTermOrderByOrderId(String orderId);
}
