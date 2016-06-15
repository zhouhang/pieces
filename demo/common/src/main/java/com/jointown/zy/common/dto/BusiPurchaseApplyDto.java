package com.jointown.zy.common.dto;

import java.io.Serializable;
import java.util.List;

import com.jointown.zy.common.model.BusiPurchaseApply;

/**
 * 
 * @ClassName: BusiPurchaseApplyDto
 * @Description: 采购申请Dto
 * @Author: wangjunhu
 * @Date: 2015年5月20日
 * @Version: 1.0
 */
public class BusiPurchaseApplyDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String applyName;
	
	private Long applyMobile;
	
	private List<BusiPurchaseApply> busiPurchaseApplies;

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public Long getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(Long applyMobile) {
		this.applyMobile = applyMobile;
	}

	public List<BusiPurchaseApply> getBusiPurchaseApplies() {
		return busiPurchaseApplies;
	}

	public void setBusiPurchaseApplies(List<BusiPurchaseApply> busiPurchaseApplies) {
		this.busiPurchaseApplies = busiPurchaseApplies;
	}
}
