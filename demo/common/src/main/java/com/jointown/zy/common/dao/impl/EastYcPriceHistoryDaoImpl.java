/**
 * @author guoyb
 * 2015年3月11日 下午1:52:29
 */
package com.jointown.zy.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.EastYcPriceHistoryDao;
import com.jointown.zy.common.vo.EastYcPriceHistoryVo;

/**
 * @author guoyb
 * 2015年3月11日 下午1:52:29
 */
@Repository
public class EastYcPriceHistoryDaoImpl extends BaseDaoImpl implements EastYcPriceHistoryDao {

	@Override
	public List<EastYcPriceHistoryVo> selectPriceBy(Map<String, Object> map) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastYcPriceMapper.selectPriceHistoryBy",map);
	}

	/**
	 * @see com.jointown.zy.common.dao.EastYcPriceHistoryDao#selectAllPriceByBreedName(java.lang.String)
	 */
	@Override
	public List<EastYcPriceHistoryVo> selectAllPriceByBreedName(String breedName) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.EastYcPriceMapper.selectAllPriceByBreedName", breedName);
	}

}
