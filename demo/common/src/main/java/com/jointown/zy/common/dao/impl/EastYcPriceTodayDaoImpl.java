/**
 * @author guoyb
 * 2015年3月9日 下午5:17:38
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastYcPriceTodayDao;
import com.jointown.zy.common.vo.EastYcPriceTodayVo;

/**
 * @author guoyb
 * 2015年3月9日 下午5:17:38
 */
@Repository
public class EastYcPriceTodayDaoImpl extends BaseDaoImpl implements EastYcPriceTodayDao {

	@Override
	public List<EastYcPriceTodayVo> selectPriceBy(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastYcPriceMapper.selectPriceBy",map);
	}
}
