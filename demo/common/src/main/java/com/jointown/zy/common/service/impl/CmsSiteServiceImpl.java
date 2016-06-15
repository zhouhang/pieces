package com.jointown.zy.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.CmsSiteDao;
import com.jointown.zy.common.model.CmsSite;
import com.jointown.zy.common.service.CmsSiteService;
@Service
public class CmsSiteServiceImpl implements CmsSiteService {
	@Autowired 
	private CmsSiteDao cmsSiteDao;
	@Override
	public int addCmsSite(CmsSite cmsSite) {
		return cmsSiteDao.insert(cmsSite);
	}
	@Override
	public List<CmsSite> selectAllCmsSite() {
		return cmsSiteDao.selectAllCmsSite();
	}
	@Override
	public List<CmsSite> selectDynamicCmsSite(Map queryMap) {
		return cmsSiteDao.selectDynamicCmsSite(queryMap);
	}
	@Override
	public CmsSite selectByPrimaryKey(Long id) {
		return cmsSiteDao.selectByPrimaryKey(id);
	}
	@Override
	public int updateByPrimaryKeySelective(CmsSite cmsSite) {
		return cmsSiteDao.updateByPrimaryKeySelective(cmsSite);
	}
	@Override
	public int deleteCmsSiteById(CmsSite cmsSite) {
		return cmsSiteDao.updateByPrimaryKeySelective(cmsSite);
	}
}
