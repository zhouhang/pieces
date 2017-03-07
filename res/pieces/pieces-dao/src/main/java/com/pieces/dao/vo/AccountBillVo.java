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

    // 订购用户
    private String orderUser;

    // 用药单位
    private String orderCompany;

    private String memberName;

    // 联系人电话
    private String contactMobile;



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


    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }

    public String getOrderCompany() {
        return orderCompany;
    }

    public void setOrderCompany(String orderCompany) {
        this.orderCompany = orderCompany;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }
}