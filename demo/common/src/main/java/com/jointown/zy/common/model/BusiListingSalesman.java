package com.jointown.zy.common.model;

import java.io.Serializable;

/* 挂牌业务员
 * 
 * */
public class BusiListingSalesman implements Serializable {
    private Integer infoId;

    private String listingid;

    private Integer salesmanId;

    private Integer orgId;
    

    private String orgs;

   
    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getListingid() {
        return listingid;
    }

    public void setListingid(String listingid) {
        this.listingid = listingid;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgs() {
        return orgs;
    }

    public void setOrgs(String orgs) {
        this.orgs = orgs;
    }

}