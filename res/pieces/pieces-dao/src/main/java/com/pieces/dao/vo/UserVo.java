package com.pieces.dao.vo;

import java.lang.reflect.Field;

/**
 * Created by burgl on 2016/7/6.
 */
public class UserVo  {

    public String userName;

    public String companyFullName;

    public String areaFull;

    public String contactName;

    public String contactMobile;

    public Boolean bindErp;

    public String startDate;

    public String endDate;


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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Boolean getBindErp() {
        return bindErp;
    }

    public void setBindErp(Boolean bindErp) {
        this.bindErp = bindErp;
    }


    @Override
    public String toString() {
        Field[] fields = this.getClass().getFields();
        StringBuffer sb = new StringBuffer();
        try {
            for(Field field : fields){
                if(field.get(this)!=null){
                    sb.append("&").append(field.getName()).append("=").append(field.get(this).toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
}
