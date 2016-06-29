package com.jointown.zy.common.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.jointown.zy.common.util.TimeUtil;

/**
 * 
 * @ClassName: BusiPurchaseBatch
 * @Description: 采购表批次信息
 * @Author: 刘漂
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public class BusiPurchaseBatch implements Serializable {
    /** TODO */
	private static final long serialVersionUID = -3816686130574362006L;
	//采购批次号
    private String purchaseCode;
    //采购会员名
    private String purchaser;
    //采购单位
    private String purchaserOrg;
    //联系人
    private String contact;
    //联系电话
    private String contactPhone;
    //采购有效期
    private String validPeriod;
    //发布时间
    private Date createTime;
    //报价剩余时间
    private Integer remainDays;
    
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public String getPurchaserOrg() {
		return purchaserOrg;
	}
	public void setPurchaserOrg(String purchaserOrg) {
		this.purchaserOrg = purchaserOrg;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(String validPeriod) {
		this.validPeriod = validPeriod;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
   
	/**
	 * 设定采购剩余天数
	 * @param 
	 */
	public Integer getRemainDays(){
		if(StringUtils.isEmpty(validPeriod)){
			this.remainDays= 0;
		}
		else{
		int day1 = TimeUtil.daysOfTwo(createTime, new Date());
		this.remainDays= Integer.valueOf(validPeriod) - day1;		
		}
		return remainDays;
	}
}