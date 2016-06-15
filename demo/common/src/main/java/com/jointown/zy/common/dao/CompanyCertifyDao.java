package com.jointown.zy.common.dao;

import com.jointown.zy.common.model.CompanyCertify;
/**
 * 企业认证
 * @author zhouji
 *
 * 2015年1月5日
 */
public interface CompanyCertifyDao {
	/**
	 * 提交企业认证
	 * @author zhouji
	 * @param companyCertify
	 */
	public int addCompanyCertify(CompanyCertify companyCertify);
	/**
	 * 根据用户id查询企业认证信息
	 * @author zhouji
	 * @param userId
	 */
	public CompanyCertify findCompanyCertifyByUserId(Integer userId);
	
	public CompanyCertify findCompanyCertifyByUserId1(Integer userId);
	
	
	/**
	 * 根据用户id查询认证企业认证信息
	 * @author zhouji
	 * @param userId
	 */
	public CompanyCertify  findReallyCompanyCertifyByUserId(Integer userId);
	/**
	 * 条件查询
	 * @author zhouji
	 * @param userCode
	 * @return
	 */
	public CompanyCertify findCompanyCertifyByCondition(String userCode);
	public Integer findSeqNext();
	
	/**
	 * 根据会员Id和认证Id认证审核
	 * @author ldp
	 * @param userId
	 * @param certifyId
	 * @return
	 */
	public int updateCompanyCertify(CompanyCertify companyCertify);
	
	/**
	 * 认证审核通过后，修改保存资料
	 * @author ldp
	 * @param userId
	 * @param certifyId
	 * @return
	 */
	public int updateCompanyCertifyInfoAfterPass(CompanyCertify companyCertify);
	
	/**
	 * 根据企业认证Id查询企业信息
	 * @author ldp
	 * @param certifyId
	 * @return
	 */
	public CompanyCertify findCompanyCertifyByCertifyId(Integer certifyId); 
}
