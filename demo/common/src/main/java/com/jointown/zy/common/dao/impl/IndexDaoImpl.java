package com.jointown.zy.common.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.IndexDao;
import com.jointown.zy.common.dto.Article1Dto;
import com.jointown.zy.common.dto.ArticleDto;

/**
 * 首页DaoImpl
 * @author zhouji
 * 2015-03-18
 */
@Repository
public class IndexDaoImpl extends BaseDaoImpl implements IndexDao {

	@Override
	public String getWarrantsTunnage() {
		return this.getSqlSession().selectOne("getWarrantsTunnage");
	}

	@Override
	public List<ArticleDto> getArticleList(String categoryId,String num) {
		Map map = new HashMap<String, String>();
		map.put("categoryId", categoryId);
		map.put("num", num);
		return this.getSqlSession().selectList("getArticleList",map);
	}

	@Override
	public List<Article1Dto> getWeixinArticleList(String lmid,Integer rownum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lmid", lmid);
		params.put("rownum", rownum);
		return this.getSqlSession().selectList("getWeixinArticleList",params);
	}
	@Override
	public List<Article1Dto> getMKArticleList() {
		return this.getSqlSession().selectList("getMKArticleList");
	}

	@Override
	public List<Map<Object, Object>> broadcastEveryday() {
		return  this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getBroadcastEveryday");
		
	}
	
	@Override
	public List<Map<String, String>> getCateGorys() {
		return  this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getCateGorys");
	}
	
	@Override
	public String getWlTotalByCateGoryId(String categoryId) {
		return  this.getSqlSession().selectOne("com.jointown.zy.common.dao.IndexDao.getWlTotalByCateGoryId",categoryId);
	}

	@Override
	public List<String> findBreedIdsByCid(String categoryId) {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.findBreedIdsByCid",categoryId);
	}

	@Override
	public List<Map<String, Object>> getBigAreaTunnage() throws Exception {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getBigAreaTunnage");
	}

	@Override
	public List<Map<String,Object>> getPriceIndex(String type, String k) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		if ("y".equals(k)) {
			k = String.valueOf(0);
		}else if ("m".equals(k)) {
			k = String.valueOf(1);
		}else {
			k = String.valueOf(2);
		}
		params.put("k", k);
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getPriceIndex", params);
	}

	@Override
	public List<Map<String, Object>> getCompositeIndex(String type, String dateNums)
			throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("dateNums", dateNums);
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getCompositeIndex",params);
	}

	@Override
	public List<Article1Dto> getHerbalNews() throws Exception {
		return this.getSqlSession().selectList("com.jointown.zy.common.dao.IndexDao.getHerbalNews");
	}

}
