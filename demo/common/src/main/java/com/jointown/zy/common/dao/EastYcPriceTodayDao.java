/**
 * @author guoyb
 * 2015年3月9日 下午5:12:49
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;

/**
 * @author guoyb
 * 2015年3月9日 下午5:12:49
 */
public interface EastYcPriceTodayDao {
	
	List<EastYcPriceTodayVo> selectPriceBy(Map<String, Object> map);
}
