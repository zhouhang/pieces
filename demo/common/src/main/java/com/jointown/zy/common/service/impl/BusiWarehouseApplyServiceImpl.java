package com.jointown.zy.common.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiWarehouseApplyDao;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.vo.AreaVo;

/**
 * 
 * @ClassName: BusiWarehouseApplyServiceImpl
 * @Description: 入仓申请
 * @Author: wangjunhu
 * @Date: 2015年5月19日
 * @Version: 1.0
 */
@Service
public class BusiWarehouseApplyServiceImpl implements BusiWarehouseApplyService {

	@Autowired
	private BusiWarehouseApplyDao busiWarehouseApplyDao;
	
	@Override
	public void addWarehouseApply(List<BusiWarehouseApply> busiWarehouseApplies) throws Exception {
		for (BusiWarehouseApply busiWarehouseApply : busiWarehouseApplies) {
			Date now = new Date();
			busiWarehouseApply.setStatus((short)0);
			busiWarehouseApply.setCreateTime(now);
			busiWarehouseApply.setUpdateTime(now);
			int busiWarehouseApplyOk = busiWarehouseApplyDao.insertSelective(busiWarehouseApply);
			if(busiWarehouseApplyOk!=1){
				throw new Exception("新增入仓申请失败！");
			}
		}
	}

	@Override
	public List<AreaVo> findAreasByCondition(AreaVo record) {
		return busiWarehouseApplyDao.selectAreasByCondition(record);
	}
	
	public Breed findBreedName(String breedName){
		return busiWarehouseApplyDao.getBreedInfo(breedName);
	}

}
