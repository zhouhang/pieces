package com.jointown.zy.common.dao;

import java.util.List;

import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUserCertify;


/**
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 */
public interface UcUserCertifyDao {

	/**
	 * 根据条件查询认证会员
	 * @author ldp
	 * @param memberCertifySearchDto
	 * @return
	 */
	public List<UcUserCertify> searchCertifyUcUsersByCondition(Page<UcUserCertify> page);
	
	
	
	/**
	 * 根据会员认证Id获取认证会员信息
	 * @author ldp
	 * @param userName
	 * @return
	 */
	public UcPersonCertify getCertifyUcUserInfoByCertifyId(String certifyId);
	
	/**
	 * 根据会员Id获取认证会员信息
	 * @author ldp
	 * @param userId
	 * @return
	 */
	public UcPersonCertify getCertifyUcUserInfoByUserId(String userId);
	
	public UcPersonCertify getCertifyUcUserInfoByUserId1(String userId);
	
	/**
	 * 个人认证审核
	 * @author ldp
	 * @param ucPersonCertify
	 * @return
	 */
	public int updatePersonCertify(UcPersonCertify ucPersonCertify);
	
	/**
	 * 审核通过后，修改认证个人会员资料
	 * @author ldp
	 * @param uPersonCertify
	 * @return
	 */
	public int updatePersonCertifyInfoPassAfter(UcPersonCertify uPersonCertify);
	
	/**
	 * 提交个人认证会员资料
	 * @author ldp
	 * @param ucPersonCertify
	 * @return
	 */
	public int addPersonCertifyInfo(UcPersonCertify ucPersonCertify);
	
	/**
	 * 重新提交个人会员实名认证资料
	 * @param ucPersonCertify
	 * @return
	 */
	public int updatePersonCertifyInfo(UcPersonCertify ucPersonCertify);
	
	/**
	 * 修改会员表认证状态
	 * @param certifyStatus
	 * 			会员认证状态，通过类型：1个人认证 、2企业认证
	 * @param userId
	 *          会员ID
	 * @return
	 */
	public int updateUcUserCertifyStatusByUserId(String certifyStatus,String userId);
	
}
