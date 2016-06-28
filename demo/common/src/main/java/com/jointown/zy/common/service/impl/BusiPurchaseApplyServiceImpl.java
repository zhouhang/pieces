package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiPurchaseApplyDao;
import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.service.BusiPurchaseApplyService;
import com.jointown.zy.common.vo.BusiPurchaseApplyVo;

/**
 * 
 * @ClassName: BusiWarehouseApplyServiceImpl
 * @Description: 采购申请
 * @Author: wangjunhu
 * @Date: 2015年5月19日
 * @Version: 1.0
 */
@Service
public class BusiPurchaseApplyServiceImpl implements BusiPurchaseApplyService {
	
	@Autowired
	private BusiPurchaseApplyDao busiPurchaseApplyDao;
	
	@Override
	public void addPurchaseApply(List<BusiPurchaseApply> purchaseApplies) throws Exception {
		for (BusiPurchaseApply busiPurchaseApply : purchaseApplies) {
			Date now = new Date();
			busiPurchaseApply.setStatus((short)0);
			busiPurchaseApply.setCreateTime(now);
			busiPurchaseApply.setUpdateTime(now);
			int busiPurchaseApplyOk = busiPurchaseApplyDao.insertSelective(busiPurchaseApply);
			if(busiPurchaseApplyOk!=1){
				throw new Exception("新增采购申请失败！");
			}
		}
	}

	/**
	 * @see com.jointown.zy.common.service.BusiPurchaseApplyService#addPurchaseApply(com.jointown.zy.common.model.BusiPurchaseApply)
	 */
	@Override
	public int addPurchaseApply(BusiPurchaseApply busiPurchaseApply) throws Exception {
		return busiPurchaseApplyDao.insertSelective(busiPurchaseApply);
	}

	/**
	 * @see com.jointown.zy.common.service.BusiPurchaseApplyService#findBusiPurchaseApplyById(java.lang.Long)
	 */
	@Override
	public BusiPurchaseApplyVo findBusiPurchaseApplyById(Long purchaseId) {
		return busiPurchaseApplyDao.selectBusiPurchaseApplyById(purchaseId);
	}

}
