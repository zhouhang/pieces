package com.pieces.dao.vo;

import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.model.ShippingAddressHistory;

import java.util.List;

/**
 * Author: koabs
 * 8/15/16.
 */
public class OrderFormVo extends OrderForm {

    // 商品列表
    private List<OrderCommodity> commodities;

    // 邮寄地址
    private ShippingAddressHistory address;

    // 发票信息
    private OrderInvoice invoice;


    public List<OrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderCommodity> commodities) {
        this.commodities = commodities;
    }

    public ShippingAddressHistory getAddress() {
        return address;
    }

    public void setAddress(ShippingAddressHistory address) {
        this.address = address;
    }

    public OrderInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoice invoice) {
        this.invoice = invoice;
    }
}
