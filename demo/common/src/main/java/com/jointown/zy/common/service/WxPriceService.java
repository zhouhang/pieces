/**
 * @author guoyb
 * 2015年3月11日 上午9:31:22
 */
package com.jointown.zy.common.service;

import java.util.List;
import java.util.Map;

import com.jointown.zy.common.vo.WxPriceVo;

/**
 * @author guoyb
 * 2015年3月11日 上午9:31:22
 */
public interface WxPriceService {
	
	public List<WxPriceVo> selectPriceByMasket(Map<String, Object> map);
	
	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map);

}
