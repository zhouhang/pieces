package com.jointown.zy.common.service;

import java.util.List;

import com.jointown.zy.common.dto.MemberMotifyDto;
import com.jointown.zy.common.dto.MemberSearchDto;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.vo.BossUserVo;

public interface IMemberManageService {
	
	/**
	 * 添加会员
	 * @param ucUser
	 */
	public int addMember(UcUser ucUser);
	
	/**
	 * 根据用户ID获取前台用户的基本信息，不用判断是否删除
	 */
	public UcUser getUcUserById(Integer id);
	
	/**
	 * 会员信息修改
	 * @param memberMotifyDto
	 */
	public int modifyMember(MemberMotifyDto memberMotifyDto,BossUserVo userinfo);
	
	/**
	 * 根据会员ID获取会员信息
	 * @param userId
	 * @return
	 */
	public UcUser findMemberByUserID(String userId);
	
	/**
	 * 根据会员名查询会员
	 * @param memberName
	 * @return
	 */
	public UcUser findMemberByUserName(String memberName);
	
	/**
	 * 根据公司名查询会员
	 * @param companyName
	 * @return
	 */
	public UcUser findMemberByCompanyName(String companyName);
	/**
	 * 根据条件查询会员
	 * @param ucUser
	 * @return
	 */
	public List<UcUser> conditonSearchMember(MemberSearchDto memberSearchDto);
	
	/**
	 * 删除会员，永久删除，不可逆
	 * @param userId
	 */
	public int deleteMember(UcUser ucUser);
	
	/**
	 * 是否锁定，可逆操作
	 * @param UcUser
	 * @return
	 */
	public int isLock(UcUser ucUser);
	
	/**
	 * 密码重置
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	public int secretReset(UcUser ucUser);
	
	/**
	 * 查看会员备注
	 * @param userId
	 * @return
	 */
	public String lookRemark(String userId);
	
	/**
	 * 判断会员名是否已经存在
	 * @param userName
	 * @return
	 */
	public String memberNameIsHaved(String userName);
	
	/**
	 * 判断手机号是否存在
	 * @param mobile
	 * @return
	 */
	public List<UcUser> memberMobileIsExist(String mobile);
	
	/**
	 * 修改会员时，判断手机号是否存在
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public List<UcUser> reMemberMobIsExist(String userId,String mobile);
	
	/**
	 * 根据用户ID查询用户信息  不限制用户状态
	 * @author fanyuna
	 * @param id 用户ID
	 * @return
	 */
	public UcUser getUcUserById(String id);
	
}
