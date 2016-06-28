package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.BusiWareHouseDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiWareHouseVo;

/**
 * 仓库管理Service
 * @author wangjunhu
 * 2014-12-22
 */
public interface BusiWareHouseService {
	/**
	 * 新增仓库
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public void addBusiWareHouse(BusiWareHouseDto busiWareHouseDto);
	
	/**
	 * 更新仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public void alterBusiWareHouse(BusiWareHouseDto busiWareHouseDto);
	
	/**
	 * 删除仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouseDto
	 */
	public void removeBusiWareHouse(BusiWareHouseDto busiWareHouseDto);
	
	/**
	 * 查找仓库，查找全部
	 * @author wangjunhu
	 */
	public List<BusiWareHouseVo> findBusiWareHouses();
	
	/**
	 * 查找仓库，查找部分
	 * @author wangjunhu
	 */
	public List<BusiWareHouseVo> findBusiWareHousesByTree();
	
	/**
	 * 查找仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public BusiWareHouseVo findBusiWareHouseById(String busiWareHouseId);
	
	/**
	 * @description 根据用户id查询出用户的货物所在的仓库
	 * @author chengchang
	 * @param userid
	 */
	public List <BusiWareHouseVo> selectBusiWareHousesByUserId(Long userId);
	
	/**
	 * 查找仓库，分页查找
	 * @author wangjunhu
	 * @param page
	 */
	public List<BusiWareHouseVo> findBusiWareHousesByCondition(Page<BusiWareHouseVo> page);

//	/**
//	 * 得到所有市场
//	 * @author guoyb
//	 * 2015年4月1日 上午10:10:32
//	 */
//	public List<String> selectAllMasket();
}
