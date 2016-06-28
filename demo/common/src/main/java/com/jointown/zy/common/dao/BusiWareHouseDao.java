package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.BusiWareHouse;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiWareHouseVo;

/**
 * 仓库管理DAO
 * @author wangjunhu
 * 2012-12-22
 */
public interface BusiWareHouseDao {
	/**
	 * 保存仓库
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public int insertBusiWareHouse(BusiWareHouse busiWareHouse);
	
	/**
	 * 更新仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public int updateBusiWareHouseById(BusiWareHouse busiWareHouse);
	
	/**
	 * 删除仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public void deleteBusiWareHouseById(BusiWareHouse busiWareHouse);
	
	/**
	 * 查找仓库，查找全部
	 * @author wangjunhu
	 */
	public List<BusiWareHouseVo> selectBusiWareHouses();
	
	/**
	 * 查找仓库，查找部分
	 * @author wangjunhu
	 */
	public List<BusiWareHouseVo> selectBusiWareHousesByTree();
	
	
	/**
	 * 查找仓库，根据仓库ID
	 * @author wangjunhu
	 * @param busiWareHouse
	 */
	public BusiWareHouseVo selectBusiWareHouseById(String wareHouseId);
	
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
	public List<BusiWareHouseVo> selectBusiWareHousesByCondition(Page<BusiWareHouseVo> page);
	
	/**
	 * 
	 * @Description: 根据仓库ID删除仓库
	 * @Author: fanyuna
	 * @Date: 2015年4月16日
	 * @param wareHouseId
	 * @return
	 */
	public int delWarehouseById(String wareHouseId);
	
	public int updateByPrimaryKeySelective(BusiWareHouse busiWareHouse);
}
