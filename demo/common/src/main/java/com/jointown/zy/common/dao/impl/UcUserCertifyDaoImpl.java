package com.jointown.zy.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.UcUserCertifyDao;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUserCertify;

/**
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
@Repository
public class UcUserCertifyDaoImpl extends BaseDaoImpl implements
		UcUserCertifyDao {

	private static final Logger log = LoggerFactory.getLogger(UcUserCertifyDaoImpl.class);
	
	@Override
	public List<UcUserCertify> searchCertifyUcUsersByCondition(Page<UcUserCertify> page) {
		return getSqlSession().selectList("com.jointown.zy.common.dao.UcUserCertifyDao.searchCertifyUcUsersByCondition", page);
	}

	
	@Override
	public UcPersonCertify getCertifyUcUserInfoByCertifyId(String certifyId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserCertifyDao.getCertifyUcUserInfoByCertifyId", certifyId);
	}

	@Override
	public UcPersonCertify getCertifyUcUserInfoByUserId(String userId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserCertifyDao.getCertifyUcUserInfoByUserId", userId);
	}
	
	@Override
	public UcPersonCertify getCertifyUcUserInfoByUserId1(String userId) {
		return getSqlSession().selectOne("com.jointown.zy.common.dao.UcUserCertifyDao.getCertifyUcUserInfoByUserId1", userId);
	}


	@Override
	public int updatePersonCertify(UcPersonCertify ucPersonCertify) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserCertifyDao.updatePersonCertify", ucPersonCertify);
	}


	@Override
	public int updatePersonCertifyInfoPassAfter(UcPersonCertify uPersonCertify) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserCertifyDao.updatePersonCertifyInfoPassAfter", uPersonCertify);
	}


	@Override
	public int addPersonCertifyInfo(UcPersonCertify ucPersonCertify) {
		return getSqlSession().insert("com.jointown.zy.common.dao.UcUserCertifyDao.insertPersonCertifyInfo", ucPersonCertify);
	}


	@Override
	public int updatePersonCertifyInfo(UcPersonCertify ucPersonCertify) {
		return getSqlSession().update("com.jointown.zy.common.dao.UcUserCertifyDao.updatePersonCertifyInfo", ucPersonCertify);
	}


	@Override
	public int updateUcUserCertifyStatusByUserId(String certifyStatus,
			String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("certifyStatus", certifyStatus);
		params.put("userId", userId);
		int updateFlag = getSqlSession().update("com.jointown.zy.common.dao.UcUserCertifyDao.updateUcUserCertifyStatusByUserId", params);
		if (updateFlag == 1) {
			if (certifyStatus.equals("1")) {
				log.info("person certify !");
			}else if (certifyStatus.equals("2")) {
				log.info("company certify !");
			}
			log.info("update certifyStatus is success");
		}
		return updateFlag;
	}
	
}
