package com.jointown.zy.common.service;

import java.text.ParseException;

import com.jointown.zy.common.dto.CompanyCertifyDto;
import com.jointown.zy.common.model.CompanyCertify;
import com.jointown.zy.common.vo.BossUserVo;

public interface CompanyCertifyService {
	/**
	 * 提交企业认证
	 * @author zhouji
	 * @param companyCertify
	 * @throws ParseException 
	 */
	public int addCompanyCertify(CompanyCertifyDto companyCertifyDto,String imgDir) throws Exception;
	
	public CompanyCertify  findReallyCompanyCertifyByUserId(Integer userId);
	
	/**
	 * 根据用户id查询企业认证信息
	 * @author zhouji
	 * @param userId
	 */
	public CompanyCertify findCompanyCertifyByUserId(Integer userId);
	
	public CompanyCertify findCompanyCertifyByUserId1(Integer userId);
	
	/**
	 * 条件查询
	 * @author zhouji
	 * @param userCode
	 * @return
	 */
	public CompanyCertify findCompanyCertifyByCondition(String userCode);
	
	/**
	 * 企业会员认证审核
	 * @author ldp
	 * @param comCertDto
	 * @param bossUserVo
	 * @return
	 */
	public int updateCompanyCertify(CompanyCertifyDto comCertDto,BossUserVo bossUserVo,String imgDir);
	
	/**
	 * 认证通过审核后的修改保存资料
	 * @author ldp
	 * @param comCertDto
	 * @param bossUserVo
	 * @return
	 */
	public int updateCompanyCertifyInfoAfterPass(CompanyCertifyDto comCertDto,BossUserVo bossUserVo,String imgDir);
	
	/**
	 * 根据认证Id查询企业信息
	 * @author ldp
	 * @param certifyId
	 * @return
	 */
	public CompanyCertify findCompanyCertifyByCertifyId(Integer certifyId);
}
