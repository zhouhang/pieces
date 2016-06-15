package com.jointown.zy.common.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WxFinanceDto implements Serializable {

    /**
     * WEIXIN.WX_FINANCE.FINANCE_BREED_NAME (品种名称（药材名称）)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeBreedName;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_BREED_STANDARD_LEVEL (等级规格)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeBreedStandardLevel;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_BREED_AMOUNT (药材数量)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeBreedAmount;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_ADDRESS (货物质押地)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeAddress;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_DATE (质押货物产新时间)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeDate;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_NAME (融资人姓名)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeName;

    /**
     * WEIXIN.WX_FINANCE.FINANCE_MOBILE (融资人联系方式)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private String financeMobile;


    public String getFinanceBreedName() {
        return financeBreedName;
    }

    public void setFinanceBreedName(String financeBreedName) {
        this.financeBreedName = financeBreedName;
    }

    public String getFinanceBreedStandardLevel() {
        return financeBreedStandardLevel;
    }

    public void setFinanceBreedStandardLevel(String financeBreedStandardLevel) {
        this.financeBreedStandardLevel = financeBreedStandardLevel;
    }

    public String getFinanceBreedAmount() {
        return financeBreedAmount;
    }

    public void setFinanceBreedAmount(String financeBreedAmount) {
        this.financeBreedAmount = financeBreedAmount;
    }

    public String getFinanceAddress() {
        return financeAddress;
    }

    public void setFinanceAddress(String financeAddress) {
        this.financeAddress = financeAddress;
    }

    public String getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(String financeDate) {
        this.financeDate = financeDate;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getFinanceMobile() {
        return financeMobile;
    }

    public void setFinanceMobile(String financeMobile) {
        this.financeMobile = financeMobile;
    }
}