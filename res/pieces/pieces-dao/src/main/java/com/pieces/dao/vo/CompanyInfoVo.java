package com.pieces.dao.vo;

/**
 * Created by xiao on 2016/12/14.
 * erp对接api返回信息
 *
 */
public class CompanyInfoVo {

    //userId
    private Integer id;

    //企业名称
    private String company;

    //联系人手机号
    private String contacts_mobile;

    //企业地址
    private String address;

    //企业法人
    private String corporation;

    //销售员
    private String seller;

    //医疗许可证
    private String medical_licence;

    //医疗许可证有效期
    private String medical_licence_expire;

    //营业执照
    private String business_licence;

    //营业执照有效期
    private String business_licence_expire;

    //GSP证书
    private String gsp_licence;

    //GSP证书有效期
    private String gsp_licence_expire;

    //业务员授权委托书
    private String entrust_licence;

    //业务员授权委托书有效期
    private String entrust_licence_expire;

    //企业类型
    private Integer company_type;

    //单条记录最后修改时间戳
    private Long timestamp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContacts_mobile() {
        return contacts_mobile;
    }

    public void setContacts_mobile(String contacts_mobile) {
        this.contacts_mobile = contacts_mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getMedical_licence() {
        return medical_licence;
    }

    public void setMedical_licence(String medical_licence) {
        this.medical_licence = medical_licence;
    }

    public String getMedical_licence_expire() {
        return medical_licence_expire;
    }

    public void setMedical_licence_expire(String medical_licence_expire) {
        this.medical_licence_expire = medical_licence_expire;
    }

    public String getBusiness_licence() {
        return business_licence;
    }

    public void setBusiness_licence(String business_licence) {
        this.business_licence = business_licence;
    }

    public String getBusiness_licence_expire() {
        return business_licence_expire;
    }

    public void setBusiness_licence_expire(String business_licence_expire) {
        this.business_licence_expire = business_licence_expire;
    }

    public String getGsp_licence() {
        return gsp_licence;
    }

    public void setGsp_licence(String gsp_licence) {
        this.gsp_licence = gsp_licence;
    }

    public String getGsp_licence_expire() {
        return gsp_licence_expire;
    }

    public void setGsp_licence_expire(String gsp_licence_expire) {
        this.gsp_licence_expire = gsp_licence_expire;
    }

    public String getEntrust_licence() {
        return entrust_licence;
    }

    public void setEntrust_licence(String entrust_licence) {
        this.entrust_licence = entrust_licence;
    }

    public String getEntrust_licence_expire() {
        return entrust_licence_expire;
    }

    public void setEntrust_licence_expire(String entrust_licence_expire) {
        this.entrust_licence_expire = entrust_licence_expire;
    }

    public Integer getCompany_type() {
        return company_type;
    }

    public void setCompany_type(Integer company_type) {
        this.company_type = company_type;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
