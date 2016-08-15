package com.pieces.dao.vo;

import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.OrderForm;

import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderFormVo extends OrderForm {

    // 商品列表
    public List<OrderCommodity> commodities;


    public List<OrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderCommodity> commodities) {
        this.commodities = commodities;
    }
}
