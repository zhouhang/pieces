package com.jointown.zy.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.model.BusiPurchase;

/**
 * @ClassName: BusiQuoteVo
 * @Description: 报价VO
 * @Author: shangcuijuan
 * @Date: 2015年10月13日
 * @Version: 1.0
 */
public class BusiQuoteVo implements Serializable{

	private static final long serialVersionUID = -3953799272934543261L;
	
	private static final Logger logger = LoggerFactory.getLogger(BusiQuoteVo.class);
	
	private String quoteId;

    private String purchaseId;

    private String purchaseCode;

    private BigDecimal quotePrice;

    private String quoteDescription;

    private String quoter;

    private String phone;

    private Integer status;

    private String isValid;

    private Date createTime;

    private String operator;

    private Date updateTime;

    private BusiPurchase busiPurchasePurchaseId;
    
    private String username;
    
    private String mobile;
    
    private String breedname;
    
   
    

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public BigDecimal getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(BigDecimal quotePrice) {
        this.quotePrice = quotePrice;
    }

    public String getQuoteDescription() {
        return quoteDescription;
    }

    public void setQuoteDescription(String quoteDescription) {
        this.quoteDescription = quoteDescription;
    }

    public String getQuoter() {
        return quoter;
    }

    public void setQuoter(String quoter) {
        this.quoter = quoter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setBusiPurchasePurchaseId(BusiPurchase busiPurchasePurchaseId) {
        this.busiPurchasePurchaseId=busiPurchasePurchaseId;
    }

    public BusiPurchase getBusiPurchasePurchaseId() {
        return busiPurchasePurchaseId;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBreedname() {
		return breedname;
	}

	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}

}
