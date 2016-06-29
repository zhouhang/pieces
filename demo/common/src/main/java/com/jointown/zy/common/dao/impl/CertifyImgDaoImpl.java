package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CertifyImgDao;
import com.jointown.zy.common.model.CertifyImg;

@Repository
public class CertifyImgDaoImpl extends BaseDaoImpl implements CertifyImgDao {

	@Override
	public void addCertifyImg(CertifyImg certifyImg) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.insert("insertCertifyImg",certifyImg);
	}

	@Override
	public void updateCertifyImg(CertifyImg certifyImg) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("updateCertifyImg",certifyImg);
	}

	@Override
	public List<CertifyImg> findCertifyImgByCertifyId(Integer certifyId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		return sqlSession.selectList("findCertifyImgByCertifyId",certifyId);
	}
	
	@Override
	public CertifyImg findCertifyImgByCertifyIdAndType(Integer certifyId,Integer type) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		Map<String, Integer> map=new HashMap();
		map.put("certifyId", certifyId);
		map.put("type", type);
		return sqlSession.selectOne("findCertifyImgByCertifyIdAndType",map);
	}

	@Override
	public void updateAllCertifyImg(Integer certifyId) {
		SqlSessionTemplate sqlSession = this.getSqlSession();
		sqlSession.update("updateAllCertifyImg",certifyId);
	}

	@Override
	public int updateAllTypeCertifyImg(Integer certifyId,
			Integer type, Integer smallType, Integer bigType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("certifyId", certifyId);
		params.put("type", type);
		params.put("smallType", smallType);
		params.put("bigType", bigType);
		return getSqlSession().update("updateAllTypeCertifyImg", params);
	}

	@Override
	public List<CertifyImg> findAllTypeImgByCertifyId(Integer certifyId,
			Integer type, Integer smallType, Integer bigType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("certifyId", certifyId);
		params.put("type", type);
		params.put("smallType", smallType);
		params.put("bigType", bigType);
		return getSqlSession().selectList("findAllTypeImgByCertifyId", params);
	}

	

}
