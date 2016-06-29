package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class WxFinance implements Serializable {
    /**
     * WEIXIN.WX_FINANCE.FINANCE_ID (我要融资ID，主键)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private Long financeId;

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
    private Date financeDate;

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

    /**
     * WEIXIN.WX_FINANCE.STATUS (记录状态（未处理-0、有效-1、无效-2、用户撤销-3）)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private Short status;

    /**
     * WEIXIN.WX_FINANCE.CREATE_TIME (对比时间，创建时间)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private Date createTime;

    /**
     * WEIXIN.WX_FINANCE.UPDATE_TIME (最后更新时间)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private Date updateTime;

    /**
     * WEIXIN.WX_FINANCE.UPDATER (最后一次更新人ID)
     * @ibatorgenerated 2015-08-25 12:30:24
     */
    private Long updater;

    public Long getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Long financeId) {
        this.financeId = financeId;
    }

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

    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }
}