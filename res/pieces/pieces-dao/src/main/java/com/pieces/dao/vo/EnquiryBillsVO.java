package com.pieces.dao.vo;

import com.pieces.dao.model.EnquiryBills;

import java.util.Date;

/**
 * Author: koabs
 * 7/26/16.
 */
public class EnquiryBillsVO extends EnquiryBills{

    //用户名
    private String userName;

    //企业全称
    private String companyFullName;

    //地址全称
    private String areaFull;

    //联系人手机
    private String contactMobile;

    //联系人姓名
    private String contactName;

    //询价开始日期
    private Date startTime;

    //询价结束日期
    private Date endTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getAreaFull() {
        return areaFull;
    }

    public void setAreaFull(String areaFull) {
        this.areaFull = areaFull;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
