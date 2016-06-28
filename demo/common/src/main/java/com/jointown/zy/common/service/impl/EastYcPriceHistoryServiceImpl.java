/**
 * @author guoyb
 * 2015年3月11日 下午1:49:50
 */
package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.EastYcPriceHistoryDao;
import com.jointown.zy.common.service.EastYcPriceHistoryService;
import com.jointown.zy.common.vo.EastYcPriceHistoryVo;

/**
 * @author guoyb
 * 2015年3月11日 下午1:49:50
 */
@Service
public class EastYcPriceHistoryServiceImpl implements EastYcPriceHistoryService {
	
	@Autowired
	private EastYcPriceHistoryDao eastYcPriceHistoryDao;

	@Override
	public List<EastYcPriceHistoryVo> selectPriceBy(Map<String, Object> map) {
		return this.eastYcPriceHistoryDao.selectPriceBy(map);
	}

	/**
	 * @see com.jointown.zy.common.service.EastYcPriceHistoryService#selectAllPriceByBreedName(java.lang.String)
	 */
	@Override
	public List<EastYcPriceHistoryVo> selectAllPriceByBreedName(String breedName) {
		return eastYcPriceHistoryDao.selectAllPriceByBreedName(breedName);
	}


}
