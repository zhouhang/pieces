package com.jointown.zy.common.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.UcUserScopeDao;
import com.jointown.zy.common.model.UcUserBreed;
import com.jointown.zy.common.model.UcUserScope;
import com.jointown.zy.common.service.UcUserScopeService;

@Service
public class UcUserScopeServiceImpl implements UcUserScopeService {
	
	private static final Logger log = LoggerFactory.getLogger(UcUserScopeServiceImpl.class);
	
	@Autowired
	private UcUserScopeDao ucUserScopeDao;
	
	@Override
	public UcUserScope selectUcUserScopeById(Long userId) throws Exception {
		return ucUserScopeDao.selectUcUserScopeById(userId);
	}

	@Override
	public List<UcUserBreed> getBreedsById(Long userId) throws Exception {
		return ucUserScopeDao.getBreedsByUserId(userId);
	}

}
