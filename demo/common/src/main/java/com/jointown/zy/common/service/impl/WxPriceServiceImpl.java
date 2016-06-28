/**
 * @author guoyb
 * 2015年3月11日 上午9:33:44
 */
package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.WxPriceDao;
import com.jointown.zy.common.service.WxPriceService;
import com.jointown.zy.common.vo.WxPriceVo;

/**
 * @author guoyb
 * 2015年3月11日 上午9:33:44
 */
@Service
public class WxPriceServiceImpl implements WxPriceService{

	@Autowired
	private WxPriceDao wxPriceDao;
	
	@Override
	public List<WxPriceVo> selectPriceByMasket(Map<String, Object> map) {
		return this.wxPriceDao.selectPriceByMasket(map);
	}
	
	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map){
		return this.wxPriceDao.selectPriceByAll(map);
	}
	
}
