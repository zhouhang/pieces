/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.dto;

/**
 * @ClassName: BossOrderCancelListDto
 * @Description: 后台交易取消列表页查询条件DTO
 * @Author: 赵航
 * @Date: 2015年4月7日
 * @Version: 1.0
 */
public class BossOrderCancelListDto extends BaseDto{

	private static final long serialVersionUID = 3382497060599898291L;

	/** 订单编号 */
	private String orderId;
	
	/** 订单状态 */
	private String orderState;
	
	/** 审核状态 */
	private String examineState;
	
	/** 申请时间（始） */
	private String applyStartDate;
	
	/** 申请时间（止） */
	private String applyEndDate;
	
	//add by fanyuna 2015.07.30  业务员名称 
	
	private String salesmanName;
		
	public String getSalesmanName() {
			return salesmanName;
		}

	public void setSalesmanName(String salesmanName) {
			this.salesmanName = salesmanName;
		}

	/**
	 * 取得 订单编号
	 * @return 订单编号
	 */
	public String getOrderId() {
	    return orderId;
	}

	/**
	 * 設定 订单编号
	 * @param orderId 订单编号
	 */
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}

	/**
	 * 取得 订单状态
	 * @return 订单状态
	 */
	public String getOrderState() {
	    return orderState;
	}

	/**
	 * 設定 订单状态
	 * @param orderState 订单状态
	 */
	public void setOrderState(String orderState) {
	    this.orderState = orderState;
	}

	/**
	 * 取得 审核状态
	 * @return 审核状态
	 */
	public String getExamineState() {
	    return examineState;
	}

	/**
	 * 設定 审核状态
	 * @param examineState 审核状态
	 */
	public void setExamineState(String examineState) {
	    this.examineState = examineState;
	}

	/**
	 * 取得 申请时间（始）
	 * @return 申请时间（始）
	 */
	public String getApplyStartDate() {
	    return applyStartDate;
	}

	/**
	 * 設定 申请时间（始）
	 * @param applyStartDate 申请时间（始）
	 */
	public void setApplyStartDate(String applyStartDate) {
	    this.applyStartDate = applyStartDate;
	}

	/**
	 * 取得 申请时间（止）
	 * @return 申请时间（止）
	 */
	public String getApplyEndDate() {
	    return applyEndDate;
	}

	/**
	 * 設定 申请时间（止）
	 * @param applyEndDate 申请时间（止）
	 */
	public void setApplyEndDate(String applyEndDate) {
	    this.applyEndDate = applyEndDate;
	}

}
