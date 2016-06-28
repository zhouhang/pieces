package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.vo.AreaVo;

public interface BusiWarehouseApplyService {
	
	/**
	 * 
	 * @Description: 批量新增入仓申请
	 * @Author: wangjunhu
	 * @Date: 2015年5月19日
	 * @throws Exception
	 */
	void addWarehouseApply(List<BusiWarehouseApply> busiWarehouseApplies) throws Exception;	
	
    /**
     * 
     * @Description: 查询地区，条件查询
     * @Author: wangjunhu
     * @Date: 2015年5月20日
     * @param areaVo
     * @return
     */
    List<AreaVo> findAreasByCondition(AreaVo record);
    
    /**
     * 查询品种信息
     * @param breedName
     * @return
     */
    Breed findBreedName(String breedName);
}
