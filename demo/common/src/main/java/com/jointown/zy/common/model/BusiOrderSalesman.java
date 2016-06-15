package com.jointown.zy.common.model;

import java.io.Serializable;

public class BusiOrderSalesman implements Serializable {
    private Long infoId;

    private String orderid;

    private Long buyerSalesmanid;

    private Long buyerOrgid;

    private String buyerOrgs;

    private Long sellerSalesmanid;

    private Long sellerOrgid;

    private String sellerOrgs;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public Long getBuyerSalesmanid() {
        return buyerSalesmanid;
    }

    public void setBuyerSalesmanid(Long buyerSalesmanid) {
        this.buyerSalesmanid = buyerSalesmanid;
    }

    public Long getBuyerOrgid() {
        return buyerOrgid;
    }

    public void setBuyerOrgid(Long buyerOrgid) {
        this.buyerOrgid = buyerOrgid;
    }

    public String getBuyerOrgs() {
        return buyerOrgs;
    }

    public void setBuyerOrgs(String buyerOrgs) {
        this.buyerOrgs = buyerOrgs;
    }

    public Long getSellerSalesmanid() {
        return sellerSalesmanid;
    }

    public void setSellerSalesmanid(Long sellerSalesmanid) {
        this.sellerSalesmanid = sellerSalesmanid;
    }

    public Long getSellerOrgid() {
        return sellerOrgid;
    }

    public void setSellerOrgid(Long sellerOrgid) {
        this.sellerOrgid = sellerOrgid;
    }

    public String getSellerOrgs() {
        return sellerOrgs;
    }

    public void setSellerOrgs(String sellerOrgs) {
        this.sellerOrgs = sellerOrgs;
    }
}