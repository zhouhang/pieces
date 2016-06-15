package com.jointown.zy.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.UcUser;

public interface UcUserDao {
    
    List<UcUser> getUserByUsernamePassword(Map<String, Object> map);
    
    List<UcUser> getUserByUsername(Map<String, Object> map);
    
    /**
     * @Description: 动态获取会员中心首页的数字（如销售已成交笔数，待付保证金笔数，待付货款笔数;采购已成交笔数，待付保证金笔数，待付货款笔数）
     * @Author: 宋威
     * @Date: 2015年5月22日
     * @param map
     * @return List<Integer>
     */
  	public List<Integer> getNums(HashMap map);
  	
  	/**
 	  * @Description: 根据挂牌ID查询UcUser信息
    * @Author: 宋威
    * @Date: 2015年7月17日
    * @param String listingId 挂牌id
    * @return UcUser
 	 */
 	public UcUser getUcUserByListingId(String listingId);
 	
 	
	/**
	 * 微信添加用户记录
	 * @param ucser
	 * @author lichenxiao
	 * @see UcUserService
	 * @param 
	 * @time 2015年7月15日
	 */
 	public int wxAddUcUser(UcUser ucuser);
  	
    /**
	 * 添加记录
	 * @author zhouji  修改author ldp
	 * @param ucuser
	 */
	public int addUcUser(UcUser ucuser);
	
	/**
	 * 根据用户ID获取前台用户的基本信息，不用判断是否删除
	 */
	public UcUser getUcUserById(Integer id);

	/**
	 * 根据会员name获取记录
	 * @author zhouji  
	 * @param userName
	 * @param ignoreStatus 是否忽略用户状态，默认只取有效状态的
	 * @return UcUser
	 */
	public UcUser findUcUserByUserName(String userName,boolean...ignoreStatus);
	
	
	
	/**
	 * 根据id获取记录
	 * @author zhouji
	 * @param id
	 * @return UcUser
	 */
	public UcUser findUcUserById(Integer id);
	
	/**
	 * 根据手机获取记录
	 * @author zhouji
	 * @param mobile
	 * @return
	 */
	public UcUser findUcUserByMobile(String mobile);
	
	/**
	 * 修改密码
	 * @author zhouji
	 * @param ucuser
	 */
	public int updateUcUserPassword(UcUser ucuser);
	
	/**
	 * 修改手机号码
	 * @author zhouji
	 * @param ucuser
	 */
	public int updateUcUserMobile(UcUser ucuser);
	
	/**
	 * 修改公司名称
	 * @author zhouji
	 * @param ucuser
	 */
	public int updateUcUserCompany(UcUser ucuser);
	
	/**
	 * 修改邮箱
	 * @author zhouji
	 * @param ucuser
	 */
	public int updateUcUserEmail(UcUser ucuser);
	
	/**
	 * 修改用户资料
	 * @author zhouji
	 * @param ucuser
	 */
	public int updateUcUserInfo(UcUser ucuser);
	
	/**
	 * 根据用户id 修改手机号邮箱 
	 * 身份认证使用
	 * @param ucuser
	 * @return
	 */
	public int updatePhoneAndEmail(UcUser ucuser);
	
	/**
	 * 修改用户登录信息资料
	 * @author biran
	 * @param ucuser
	 */
	public int updateUcUserLoginInfo(UcUser ucuser);
	
	/**
	 * 删除用户(逻辑删除,更改状态)
	 * @author zhouji 修改author  ldp
	 * @param ucUser
	 */
	public int deleteUcUser(UcUser ucUser);
	
	/**
	 * 根据条件查询会员
	 * @author ldp
	 * @param ucUserParams
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<UcUser> findMemberByCondition(Map ucUserParams);
	
	/**
	 * 根据公司名称查询会员信息
	 * @author ldp
	 * @param companyName
	 * @return
	 */
	public UcUser findMemberByCompanyName(String companyName);
	
	/**
	 * @author 
	 * 根据会员ID号查询
	 * @param userId
	 * @return
	 */
	public UcUser findMemberByUserId(String userId);
	
	/**
	 * @author ldp
	 * 会员锁定与解锁
	 * @param map
	 * @return
	 */
	public int isLock(UcUser ucUser);
	
	/**
	 * 重置密码
	 * @author ldp
	 * @param map
	 * @return
	 */
	public int secretReset(UcUser ucUser);
	
	/**
	 * 判断手机号是否存在
	 * @param mobile
	 * @return
	 */
	public List<UcUser> findMemberMobile(String mobile);
	
	/**
	 * 修改会员时判断手机号是否存在
	 * @param userName
	 * @param mobile
	 * @return
	 */
	public List<UcUser> reMemberMobIsExist(String userId,String mobile);
	
	/**
	 * 根据会员名查询会员，如果该会员名存在且已删除，直接update
	 * @param userName
	 * @return
	 */
	public UcUser findMemberByAllUserName(String userName);
	
	/**
	 * 根据会员名update
	 * @param userName
	 * @return
	 */
	public int updateMemberByUserName(UcUser ucUser);
	/**
	 * 判断手机号和用户名是否一一对应
	 * @param mobile
	 * @return
	 */
	public List<UcUser> findMemberByMobileAndUsername(Map<Object,Object> queryMap);
	/**
	 * 根据用户ID查询用户信息  不限制用户状态
	 * @author fanyuna
	 * @param id 用户ID
	 * @return
	 */
	public UcUser getUcUserById(String userId);
	/**
	 * 
	 * @Description: 为用户绑定业务员
	 * @Author: biran
	 * @Date: 2015年8月05日
	 * @param UcUser
	 * @return
	 */
	public void updateMemberSalesMan(UcUser ucUser);
	
	/**
	 * 
	 * @Description: 根据用户ID获取用户实名认证的名称
	 * @Author: fanyuna
	 * @Date: 2015年8月10日
	 * @param userId 用户ID
	 * @return
	 */
	public String getCertifyNameByUserId(Long userId);
	
	/**
	 * @Description: 根据邮箱查询用户是否存在
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param email
	 * @return
	 */
	public List<UcUser> findUcUserByEmail(String email) throws Exception;
	
}
