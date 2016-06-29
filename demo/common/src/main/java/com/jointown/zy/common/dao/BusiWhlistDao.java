package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;

import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.BreedVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.CategorysVo;
import com.jointown.zy.common.vo.DictInfoVo;

/**
 * 仓单管理DAO
 * @author wangjunhu
 * 	2014-12-18
 */
public interface BusiWhlistDao {
	
	/**
	 * 保存仓单
	 * @author wangjunhu
	 * @param busiWhlist
	 */
	public int insertBusiWhlist(BusiWhlist busiWhlist);
	
	/**
	 * 更新仓单，根据仓单ID
	 * @author wangjunhu
	 * @param busiWhlist
	 */
	public int updateBusiWhlistById(BusiWhlist busiWhlist);
	
	/**
	 * 删除仓单，根据仓单ID
	 * @author wangjunhu
	 * @param busiWhlistDto
	 */
	public void deleteBusiWhlistById(BusiWhlist busiWhlist);
	
	/**
	 * 查找仓单，查找全部
	 * @author wangjunhu
	 */
	public List<BusiWhlist> selectBusiWhlists();
	
	/**
	 * 查找仓单，根据仓单ID
	 * @author wangjunhu
	 * @param wlId
	 */
	public BusiWhlistVo selectBusiWhlistById(HashMap<String,Object> map);
	
	/**
	 * 查找仓单，分页查找
	 * @author wangjunhu
	 * @param page
	 */
	public List<BusiWhlistVo> selectBusiWhlistsByCondition(Page<BusiWhlistVo> page);
	
	/**
	 * 查找仓单ID，根据仓单ID序列
	 * @return
	 */
	public BusiWhlistVo selectWlIdBySeqBusiWhlist();
	
	/**
	 * 查找类目，查找全部
	 * @author wangjunhu
	 */
	public List<CategorysVo> selectCategorysByTree();
	
	/**
	 * 查找类目，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public List<CategorysVo> selectCategorysByBreedId(Long breedId);
	
	/**
	 * 查找品种，查找全部
	 * @author wangjunhu
	 */
	public List<BreedVo> selectBreedsByTree();
	
	/**
	 * 查找品种，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public BreedVo selectBreedById(Long breedId);
	
	/**
	 * 查找品种，根据类目ID查找
	 * @author wangjunhu
	 * @param categorysId
	 */

	public List<BreedVo> selectBreedTreesByCategorysId(Long categorysId);

	/**
	 * 查找计量单位，根据品种ID查找
	 * @author wangjunhu
	 * @param breedId
	 */
	public DictInfoVo selectDictInfoByBreedId(Long breedId);
	
	/**
	 * 查找区域，根据ID查找
	 * @param firstCode
	 * @return
	 */
	public AreaVo selectAreaByCode(String firstCode);
	
	/**
	 * 查找区域，根据父类ID查找
	 * @param firstCode
	 * @return
	 */
	public List<AreaVo> selectAreasByFirstCode(String firstCode);
	
	/**
	 * 根据仓单ID修改仓单可挂数量(减)
	 * @author Mr.songwei
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	int updateWLsurplusById(HashMap map);
	
	/**
	 * 根据仓单ID修改仓单可挂数量(加)
	 * @author Mr.songwei
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	int updateWLsurplus(HashMap map);
	
	/**
	 * 查询出需要的仓单
	 * @param queryMap
	 * @return
	 */
	public List<BusiWhlistVo> selectBusiWhlistMohu(Page<BusiWhlistVo> page);
	
	/**
	 * 查找仓单ID，根据仓单ID
	 * @author wangjunhu
	 * @param wlId
	 */
	public BusiWhlistVo selectBusiWhlistByWlId(String wlId);

	/**
	 * WMS更新仓单，根据仓单ID
	 * @author fanyuna
	 * @param busiWhlist
	 */
	public int updateBusiWhlistByIdForWms(BusiWhlist busiWhlist);
	
	/**
	 * 
	 * @Description: 根据仓单ID查询仓单信息
	 * @Author: fanyuna
	 * @Date: 2015年4月15日
	 * @param wlId
	 * @return
	 */
	public BusiWhlist selectWhlistByWlId(String wlId);
	
	/**
	 * @Description: 根据订单编号查询分割的仓单信息
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @return
	 */
	BusiWhlist selectWhlistByOrderId(String orderId);

}
