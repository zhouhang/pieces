package com.pieces.service;

import com.pieces.dao.model.OrderCommodity;

import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
public interface OrderCommodityService extends ICommonService<OrderCommodity> {

    /**
     * 保存订单商品列表
     * @param list
     */
    public void save(List<OrderCommodity> list);

    /**
     * 根据订单id 获取商品信息
     * @param orderId
     */
    public void getCommodityByOrderId(Integer orderId);

}
