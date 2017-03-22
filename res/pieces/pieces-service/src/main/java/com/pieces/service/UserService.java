package com.pieces.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.dto.UserValidate;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * 前台用户service
 * @author feng
 *
 */
public interface UserService extends ICommonService<User>{

	int addUser(User user);

	int updateUser(User user);
	
	User createPwdAndSaltMd5(User user);

	User getPwdAndSaltMd5(User user);
	
	int updateUserByCondition(User user);
	
	boolean ifExistMobile(String contactMobile);
	
	boolean checkUserName(String userName);

	User findByUserName(String userName);

	PageInfo<User> findByCondition(UserVo userVo, Integer pageNum, Integer pageSize);

	PageInfo<UserVo> findVoByCondition(UserVo userVo, Integer pageNum, Integer pageSize);

	/**
	 * 新建订单时选择客户来源
	 * @param userVo
	 * @param pageNum
	 * @param pageSize
     * @return
     */
	PageInfo<UserVo> findProxyUser(UserVo userVo, Integer pageNum, Integer pageSize);

	/**
	 * 根据代理商ID 查询代理的所有用户
	 * @param proxId
	 * @return
     */
	List<UserVo> findUserByProxy(Integer proxId);


	public void login(Subject subject, UsernamePasswordToken token);

	UserVo findVoById(Integer id);

	/**
	 * 根据时间查找更新的user
	 * @param updateTime
	 * @return
	 */
	List<UserVo> findUpdateUser(Date updateTime);

	/**
	 * 根据手机号或用户名查找user
	 * @param accountName
	 * @return
	 */
	User findByAccount(String accountName);


	/**
	 *姓名和手机后台自动生成一个新用户
	 */
	int generateUser(User user);

	/**
	 * 检查用户是否能下单
	 * @param user
	 * @return
     */
	UserValidate validateUser(User user);


	/**
	 * 增加合并前台cookie和后台数据的功能
	 */
	void loginNew(Subject subject, UsernamePasswordToken token,HttpServletRequest request, HttpServletResponse response);

	// 用户微信登入获取授权创建默认账户
	User createWxUser(WxMpUser wxMpUser, String userName, String phone);

	/**
	 * 根据openId 查询用户
	 * @param openId
	 * @return
     */
	User findByOpenId(String openId);

	/**
	 * 统计当前注册的新用户
	 * @return
     */
	Integer countNewUser();

}
