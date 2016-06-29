/**
 * @author guoyb
 * 2015年3月11日 上午10:18:28
 */
package com.jointown.zy.common.dao;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.vo.WxPriceVo;

/**
 * @author guoyb
 * 2015年3月11日 上午10:18:28
 */
public interface WxPriceDao {
	
	public List<WxPriceVo> selectPriceByMasket(Map<String, Object> map);

	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map);
}
