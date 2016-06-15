package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BusiQualityPicDao;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.service.BusiQualityPicService;

@Service
public class BusiQualityPicServiceImpl implements BusiQualityPicService {

	@Autowired
	private BusiQualityPicDao busiQualityPicDao;

	@Override
	public int deleteByPrimaryKey(Long qcid) {
		return busiQualityPicDao.deleteByPrimaryKey(qcid);
	}

	@Override
	public int insertBusiQualitypic(BusiQualitypic record) {
		return busiQualityPicDao.insertBusiQualitypic(record);
	}

	@Override
	public int insertSelective(BusiQualitypic record) {
		return busiQualityPicDao.insertSelective(record);
	}

	@Override
	public BusiQualitypic selectByPrimaryKey(Long qcid) {
		return busiQualityPicDao.selectByPrimaryKey(qcid);
	}

	@Override
	public int updateByPrimaryKeySelective(BusiQualitypic record) {
		return busiQualityPicDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BusiQualitypic record) {
		return busiQualityPicDao.updateByPrimaryKey(record);
	}

	@Override
	public int updateByWlIdAndPicIndex(BusiQualitypic record){
		return busiQualityPicDao.updateByWlIdAndPicIndex(record);
    }
	
	@Override
	public List<BusiQualitypic> selectAllPicByWLID(String wlid) {
		return busiQualityPicDao.selectAllPicByWLID(wlid);
	}

	@Override
	public BusiQualitypic findZJPicByWLID(String wlid) {
		return busiQualityPicDao.selectZJPicByWLID(wlid);
	}
}