package com.jointown.zy.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiQualityInfoDao;
import com.jointown.zy.common.dto.BusiQualityInfoDto;
import com.jointown.zy.common.model.BusiQualityinfo;
import com.jointown.zy.common.service.BusiQualityInfoService;

@Service
public class BusiQualityInfoServiceImpl implements BusiQualityInfoService {

	@Autowired
	private BusiQualityInfoDao busiQualityDao;

	@Override
	public int deleteByPrimaryKey(Long qualityinfoid) {
		return busiQualityDao.deleteByPrimaryKey(qualityinfoid);
	}

	@Override
	public int insertQualityInfo(BusiQualityInfoDto record) {
		BusiQualityinfo busiQualityInfo = record.getBusiqualityinfo();
		return busiQualityDao.insertQualityInfo(busiQualityInfo);
	}

	@Override
	public int insertSelective(BusiQualityInfoDto record) {
		BusiQualityinfo busiQualityInfo = record.getBusiqualityinfo();
		return busiQualityDao.insertSelective(busiQualityInfo);
	}

	@Override
	public BusiQualityinfo selectByPrimaryKey(Long qualityinfoid) {
		return busiQualityDao.selectByPrimaryKey(qualityinfoid);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualityinfo record) {
		return busiQualityDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BusiQualityinfo record) {
		return busiQualityDao.updateByPrimaryKey(record);
	}

	@Override
	public int updateByWLIDSelective(BusiQualityinfo record) {
		return busiQualityDao.updateByWLIDSelective(record);
	}
	
	@Override
	public BusiQualityinfo selectByWLID(String wlid) {
		return busiQualityDao.selectByWLID(wlid);
	}
}
