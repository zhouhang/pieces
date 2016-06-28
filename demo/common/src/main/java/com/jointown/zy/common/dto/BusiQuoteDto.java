package com.jointown.zy.common.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.jointown.zy.common.util.HtmlSymbolsUtil;


public class BusiQuoteDto extends BaseDto{

			private static final long serialVersionUID = 5430961590351211397L;
			
			private String quoteId;

		    private String purchaseId;

		    private String purchaseCode;

		    private String quotePrice;

		    private String quoteDescription;

		    private String quoter;

		    private String phone;

		    private Integer status;

		    private String isValid;

		    private Date createTime;

		    private String operator;

		    private Date updateTime;
		    
		    private String StartDate;
		    
		    private String EndDate;
		   
		    private String breedname;
		    
		    private String companyName;
		    
		    /*记录采购状态*/
		    private Integer purchaseStatus;

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

		    public String getQuotePrice() {
		        return quotePrice;
		    }

		    public void setQuotePrice(String quotePrice) {
		        this.quotePrice = quotePrice;
		    }

		    public String getQuoteDescription() {
		        return quoteDescription;
		    }

		    public void setQuoteDescription(String quoteDescription) {
		        this.quoteDescription = HtmlSymbolsUtil.encode(quoteDescription);    //过滤html特殊字符
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

			public String getStartDate() {
				return StartDate;
			}

			public void setStartDate(String startDate) {
				StartDate = startDate;
			}

			public String getEndDate() {
				return EndDate;
			}

			public void setEndDate(String endDate) {
				EndDate = endDate;
			}

			public String getBreedname() {
				return breedname;
			}

			public void setBreedname(String breedname) {
				this.breedname = breedname;
			}

			public Integer getPurchaseStatus() {
				return purchaseStatus;
			}

			public void setPurchaseStatus(Integer purchaseStatus) {
				this.purchaseStatus = purchaseStatus;
			}

			public String getCompanyName() {
				return companyName;
			}

			public void setCompanyName(String companyName) {
				this.companyName = companyName;
			}

		  
}
