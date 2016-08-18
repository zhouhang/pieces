package com.pieces.dao.vo;

import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.OrderInvoice;
import com.pieces.dao.model.ShippingAddressHistory;
import com.pieces.tools.utils.httpclient.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

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

    // 商品信息摘要
    private String commodityOverview;


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


    public String getCommodityOverview() {
        if (commodities != null) {
            commodityOverview = "";
            int lenght = commodities.size() >= 3 ? 3 : commodities.size();
            String[] names = new String[3];
            for (int i = 0; i < lenght; i++) {
                names[i] = commodities.get(i).getName();
            }
            commodityOverview = StringUtils.join(names,",");
            commodityOverview += "...";
        }

        return commodityOverview;
    }
}