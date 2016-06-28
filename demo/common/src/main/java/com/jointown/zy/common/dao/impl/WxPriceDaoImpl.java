package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.WxPriceDao;
import com.jointown.zy.common.vo.WxPriceVo;

/**
 * @author guoyb
 * 2015年3月11日 上午11:16:56
 */
@Repository
public class WxPriceDaoImpl extends BaseDaoImpl implements WxPriceDao{

	@Override
	public List<WxPriceVo> selectPriceByMasket(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxPriceMapper.selectPriceByMasket",map);
	}
	
	@Override
	public List<WxPriceVo> selectPriceByAll(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.WxPriceMapper.selectPriceByAll",map);
	}
}
