package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiGoodsSellerVo;
import com.jointown.zy.common.vo.BusiListingSearchVo;

/**
* 项目名称：common   
* 类名称：WxSearchService   
* 类描述：微信-小珍现货,无登录状态下的挂牌搜索、排序，查看货主信息等  
* 创建人：宋威   
* 创建时间：2015年7月16日
* @version v1.0   
 */
public interface WxSearchService {
	/**
	 * 通过关键字查询
	 * @param dto
	 * @param page
	 * @return List<BusiListingSearchVo>
	 */
	public List<BusiListingSearchVo> searchByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
	
//	/**
//	 * 高级搜索搜索
//	 * @param dto
//	 * @param page
//	 * @return List<BusiListingSearchVo>
//	 */
//	public List<BusiListingSearchVo> seniorSearchByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
	
	/**
	 * 仓单总吨数
	 * @param 
	 * @return String
	 */
	public String getWarrantsTunnage();	
	
	/**
	 * 微信获取形态分类下的所有类目分类列表
	 * @author 宋威 2015-7-20
	 * @return Map<String,String>
	 */
	public List<Map<String, String>> getCateGorys();
	
	/**
	 * 微信根据类目ID查询该指定类目下的所有在库药材吨数
	 * @author 宋威 2015-7-20
	 * @return Map<String,String>
	 */
	public String getWlTotalByCateGoryId(String categoryId);
	
	/**
	 * 商品详情-卖家信息<br/>
	 * @param listingid 商品挂牌ID
	 */
	BusiGoodsSellerVo selectGoodsSellerInfo(String listingid);
	
	/**
	 * 微信根据类目ID查询该指定类目下的所有正常可摘牌的品种id集合，且拼接成字符串
	 * @param categoryId
	 */
	public String getSearchBreedId(String categoryId);
	
	/**
	 * 
	 * @Description: 根据选择类目下的品种，点击品种搜索对应的品种所有有效挂牌列表
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param dto
	 * @param page
	 * @return List<BusiListingSearchVo>
	 */
	public List<BusiListingSearchVo> searchBreedsBySolr(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
}
