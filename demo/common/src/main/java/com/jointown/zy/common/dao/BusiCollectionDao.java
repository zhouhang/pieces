package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BusiCollection;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiCollectionVo;

/**
 * 
 * 描述：收藏表DAO <br/>
 * 
 * 日期： 2015年1月9日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
public interface BusiCollectionDao {
	/**
	 * 插入收藏信息
	 */
	int insertBusiCollention(BusiCollection collection);
	
	/**
	 * 根据收藏者及挂牌ID查询收藏信息
	 */
	BusiCollection selectCollention(Long userid, String listingid);
	
	/**
	 * 根据收藏者及挂牌ID更新收藏信息状态
	 * @param userid 收藏者
	 * @param listingid 挂牌ID
	 * @param cstate 收藏状态
	 * @return 更新条数
	 */
	int updateCollention(Long userid, String listingid, String cstate);
	/**
	 * 根据条件，如用户ID，排序规则查询我的收藏集合
	 * @param HashMap<String,Object> map
	 * @return 收藏列表集合 @see BusiCollectionVo
	 */
	List<BusiCollectionVo> selectCollentionsByUserId(Page<Map<String, Object>> page);
	
	//add by fanyuna  更新收藏信息
	int updateCollectionBy(BusiCollection collection);
	/**
	 * 根据用户ID查询我的收藏列表中的收藏品种清单
	 */
	List<HashMap<String,String>> selectCollectionBread(Long userid);
	
	/**
	 * 查询正在进行中的挂牌的有交易的，交易最多的前五个
	 * @return 前五个的挂牌ID及交易数
	 */
	List<HashMap<String,Object>> getListingSaleCountBy();
	
	/**
	 *  根据条件查询正在进行的挂牌信息
	 * @param listingIds 挂牌ID，多个用逗号隔开
	 * @return
	 */
	List<HashMap<String,Object>> getListingSaleInfo(List<String> listingIds);
	
	List<HashMap<String,Object>> getListingSaleIds(HashMap<String,Object> map);
}
