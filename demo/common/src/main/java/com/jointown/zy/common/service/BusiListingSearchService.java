package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.dto.BusiListingSearchEngineDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.BusiListingSearchVo;

public interface BusiListingSearchService {
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
	
	/**
	 * @Description: 根据所有类目品种列表集，按照A-F,G-L,M-R,S-Z等顺序封装集合，另外查询该目录下所有拥有正常挂牌的品种ID
	 * @Author: 宋威
	 * @Date: 2015年6月2日
	 * @param list 所有目录品种集合
	 * @param id   指定目录ID
	 * @param breedIds 该目录下所有拥有正常挂牌的品种ID集合
	 * @return Map <String ,Object> 返回 A-F,G-L,M-R,S-Z 品种导航
	 */
	public Map <String ,Object> getBreedsByCategoryId(List <Map<Object,Object>> list,String id,List<String> breedIds);
	
	/**
	 * 通过关键字查询
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<BusiListingSearchVo> searchByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
	
//	/**
//	 * 通过指定字段查询
//	 * @param dto
//	 * @param page
//	 * @return
//	 */
//	public List<BusiListingSearchVo> searchBySpecifiedField(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
	
	/**
	 * 通过关键字提示
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<BusiListingSearchVo> suggestByKeyWords(BusiListingSearchEngineDto dto,Page<BusiListingSearchVo> page);
	
	/**
	 * @Description: 根据品种ID，搜索相关查询的查询条件信息
	 * @Author: 赵航
	 * @Date: 2015年7月28日
	 * @param breedId
	 * @return
	 */
	public Map<String, Object> getBreedSearchQuery(String breedId);
	
}
