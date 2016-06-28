package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.vo.BusiPurchaseApplyVo;

public interface BusiPurchaseApplyService {

	/**
	 * 
	 * @Description: 批量新增采购申请
	 * @Author: wangjunhu
	 * @Date: 2015年5月19日
	 * @throws Exception
	 */
	void addPurchaseApply(List<BusiPurchaseApply> purchaseApplies) throws Exception;	
	
	/**
	 * 
	 * @Description: 批量新增采购申请
	 * @Author: aizhengdong
	 * @Date: 2015年6月15日
	 * @throws Exception
	 */
	int addPurchaseApply(BusiPurchaseApply busiPurchaseApply) throws Exception;	
	
	/**
	 * 后台求购信息管理：查询单条采购信息
	 *
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月19日
	 */
	BusiPurchaseApplyVo findBusiPurchaseApplyById(Long purchaseId);
}
