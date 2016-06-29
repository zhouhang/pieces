/**
 * @author guoyb
 * 2015年3月11日 下午1:48:18
 */
package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.vo.EastYcPriceHistoryVo;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;

/**
 * @author guoyb
 * 2015年3月11日 下午1:48:18
 */
public interface EastYcPriceHistoryService {
	
	/**
	 * 从东网查询药材的历史价格
	 * 
	 * @author guoyb
	 * @return
	 */
	List<EastYcPriceHistoryVo> selectPriceBy(Map<String, Object> map);
	
	/**
	 * 通过品种名称查询所有历史价格
	 *
	 * @param breedName
	 * @return
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年4月23日
	 */
	List<EastYcPriceHistoryVo> selectAllPriceByBreedName(String breedName);
}
