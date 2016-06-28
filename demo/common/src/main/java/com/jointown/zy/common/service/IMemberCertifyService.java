package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.PersonCertifyDto;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcPersonCertify;
import com.jointown.zy.common.model.UcUserCertify;
import com.jointown.zy.common.vo.BossUserVo;

/**
 * @author ldp
 * date 2015年1月6日
 * Verison 0.0.1
 * @useby guoyb:WxCertifyController
 */
public interface IMemberCertifyService {

	/**
	 * 根据条件查询认证会员
	 * @author ldp
	 * @param memberCertifySearchDto
	 * @return
	 */
	public List<UcUserCertify> getCertifyUcUsersByCondition(Page<UcUserCertify> page);
	
	/**
	 * 根据会员认证ID获取认证会员信息
	 * @author ldp
	 * @param certifyId
	 * @return
	 */
	public UcPersonCertify getCertifyUcUserInfoByCertifyId(String certifyId);
	
	/**
	 * 根据会员ID获取认证会员信息
	 * @author ldp
	 * @param userId
	 * @return
	 */
	public UcPersonCertify getCertifyUcUserInfoByUserId(String userId);
	
	/**
	 * 根据会员ID获取认证会员信息
	 * @author ldp
	 * @param userId
	 * @return
	 */
	public UcPersonCertify getCertifyUcUserInfoByUserId1(String userId);
	
	/**
	 * 个人认证审核
	 * @author ldp
	 * @param pcdto
	 * @return
	 */
	public int updatePersonCertify(PersonCertifyDto pcdto,BossUserVo bossUserVo,String imgDir);
	
	/**
	 * 个人认证通过后，修改资料保存
	 * @param pcDto
	 * @return
	 */
	public int updatePersonCertifyInfoPassAfter(PersonCertifyDto pcDto,BossUserVo bossUserVo,String imgDir);
	
	/**
	 * 提交个人会员认证审核资料
	 * @param pcCerifyDto
	 * @return
	 */
	public int addPersonCertifyInfo(PersonCertifyDto pcCerifyDto,String imgDir);
	
}
