/**
 * @author guoyb
 * 2015年3月11日 下午1:50:59
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.vo.EastYcPriceHistoryVo;

/**
 * @author guoyb
 * 2015年3月11日 下午1:50:59
 */
public interface EastYcPriceHistoryDao {
	
	public List<EastYcPriceHistoryVo> selectPriceBy(Map<String, Object> map);
	
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
