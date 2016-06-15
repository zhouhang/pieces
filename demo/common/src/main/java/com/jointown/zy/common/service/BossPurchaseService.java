/**
 * Copyright © 2014-2015 珍药材版权所有
 */
package com.jointown.zy.common.service;

import com.jointown.zy.common.dto.BossPurchaseAuditQueryDto;
import com.jointown.zy.common.dto.BossPurchaseManageQueryDto;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * @ClassName: BossPurchaseService
 * @Description: 后台采购信息处理Service
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
public interface BossPurchaseService {

	/**
	 * @Description: 后台-查询需审核的采购信息列表
	 * @Author: 赵航
	 * @Date: 2015年10月12日
	 * @param query 查询条件
	 * @return 分页信息
	 * @throws Exception
	 */
	Page<BusiPurchaseVo> selectPurchaseAuditPage(BossPurchaseAuditQueryDto query) throws Exception;
	
	/**
	 * @Description: 后台-查询审核采购详细信息
	 * @Author: 赵航
	 * @Date: 2015年10月12日
	 * @param purchaseId 采购ID
	 * @return BusiPurchaseVo 采购详细信息
	 * @throws Exception
	 */
	BusiPurchaseVo selectPurchaseDetail(String purchaseId) throws Exception;
	
	/**
	 * @Description: 后台-审核采购信息
	 * @Author: 赵航
	 * @Date: 2015年10月12日
	 * @param purchaseId 采购ID
	 * @param purchaseCode 采购批次号
	 * @param auditStatus 审核状态（通过或不通过）
	 * @param remark 通过时，可以设成null；不通过时，必须填写原因
	 * @throws Exception
	 */
	void changePurchaseAuditStatus(String purchaseId, String purchaseCode, String auditStatus, String remark) throws Exception;
	
	/**
	 * @Description: 后台-查询采购信息管理列表
	 * @Author: 赵航
	 * @Date: 2015年10月14日
	 * @param query 查询条件
	 * @return
	 * @throws Exception
	 */
	Page<BusiPurchaseVo> selectPurchaseManagePage(BossPurchaseManageQueryDto query) throws Exception;
	
	/**
	 * @Description: 根据采购Id，查询该采购信息的报价列表
	 * @Author: 赵航
	 * @Date: 2015年10月14日
	 * @param purchaseId
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	Page<BusiQuote> selectPurchaseQuotePage(String purchaseId, Integer pageNo) throws Exception;
	
	/**
	 * @Description: 设置采购交易成功
	 * @Author: 赵航
	 * @Date: 2015年10月15日
	 * @param purchaseId 采购信息ID
	 * @param quoteId 报价信息ID
	 * @throws Exception
	 */
	void purchaseDealSuccess(String purchaseId, String quoteId) throws Exception;
}
