package com.pieces.dao.vo;

import com.pieces.dao.enums.PayEnum;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.model.PayRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PayRecordVo extends PayRecord{

    private Integer payAccountId;

    private List<OrderCommodity> commodities;

    public Integer getPayAccountId() {
        return payAccountId;
    }

    public void setPayAccountId(Integer payAccountId) {
        this.payAccountId = payAccountId;
    }
    // 订购用户
    private String orderUserName;

    // 用药单位
    private String companyFullName;

    //状态
    private String statusText;

    //审核人员
    private String memberName;

    // 商品信息摘要
    private String commodityOverview;

    private List<PayDocumentVo> imgs;


    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getStatusText() {
        if (getStatus()!= null) {
            statusText = PayEnum.findByValue(getStatus());
        }
        return statusText;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<PayDocumentVo> getImgs() {
        return imgs;
    }

    public void setImgs(List<PayDocumentVo> imgs) {
        this.imgs = imgs;
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

}