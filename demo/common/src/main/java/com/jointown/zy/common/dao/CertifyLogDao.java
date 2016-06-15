package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.UcUserCertifyLog;

/**
 * @author ldp
 * date 2015年1月9日
 * Verison 0.0.1
 */
public interface CertifyLogDao {

	/**
	 * 添加认证日志
	 * @param ucUserCertifyLog
	 * @return
	 */
	public int addCertifyLog(UcUserCertifyLog ucUserCertifyLog);
}
