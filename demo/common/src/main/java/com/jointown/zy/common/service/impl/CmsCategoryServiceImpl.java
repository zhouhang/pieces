package com.jointown.zy.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CmsCategoryDao;
import com.jointown.zy.common.model.CmsCategory;
import com.jointown.zy.common.service.CmsCategoryService;
@Service
public class CmsCategoryServiceImpl implements CmsCategoryService {
	@Autowired 
	public CmsCategoryDao cmsCategoryDao; 
	@Override
	public List<CmsCategory> getAllCmsCategoryByCmsSiteId(Long cmsSiteID) {
		
		return cmsCategoryDao.selectCmsCategoryByCmsSiteID(cmsSiteID);
	}

}
