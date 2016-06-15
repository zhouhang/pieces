package com.jointown.zy.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.jointown.zy.common.dao.BaseDaoImpl;
import com.jointown.zy.common.dao.CertifyLogDao;
import com.jointown.zy.common.model.UcUserCertifyLog;

/**
 * @author ldp
 * date 2015年1月9日
 * Verison 0.0.1
 */
@Repository
public class CertifyLogDaoImpl extends BaseDaoImpl implements CertifyLogDao {

	@Override
	public int addCertifyLog(UcUserCertifyLog ucUserCertifyLog) {
		return getSqlSession().insert("com.jointown.zy.common.dao.CertifyLogDao.ucUsercertifyLogAdd", ucUserCertifyLog);
	}

}
