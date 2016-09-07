package com.pieces.dao.vo;

import com.pieces.dao.enums.BillEnum;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.OrderCommodity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AccountBillVo extends AccountBill{

    private List<OrderCommodity> commodities;

    private String orderCode;

    private String statusText;

    private List<PayRecordVo> payRecordVoList;

    // 商品信息摘要
    private String commodityOverview;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatusText() {
       return BillEnum.findByValue(getStatus());
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public List<OrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderCommodity> commodities) {
        this.commodities = commodities;
    }

    public String getCommodityOverview() {
        if (commodities != null) {
            commodityOverview = "";
            int lenght = commodities.size() >= 3 ? 3 : commodities.size();
            String[] names = new String[lenght];
            for (int i = 0; i < lenght; i++) {
                names[i] = commodities.get(i).getName();
            }
            commodityOverview = StringUtils.join(names, ",");
            if (commodities.size() > 3) {
                commodityOverview += "...";
            }
        }

        return commodityOverview;
    }

    public List<PayRecordVo> getPayRecordVoList() {
        return payRecordVoList;
    }

    public void setPayRecordVoList(List<PayRecordVo> payRecordVoList) {
        this.payRecordVoList = payRecordVoList;
    }
}