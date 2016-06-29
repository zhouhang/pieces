package com.jointown.zy.common.service;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.vo.MessageVo;

/**
 * @ClassName: UserSecurityOptService
 * @Description: 会员安全信息操作(修改手机号码、修改邮箱、设置邮箱)
 * @Author: ldp
 * @Date: 2015年8月26日
 * @Version: 1.0
 */
public interface UserSecurityOptService {

	/**
	 * @Description: 获取发送邮件的内容(邮箱设置)
	 * @Author: ldp
	 * @Date: 2015年8月26日
	 * @param email 邮箱
	 * @return 返回邮件发送的内容（加密数据）
	 * @throws Exception
	 */
	public String getSetEmailContext(String email,String optType,UcUser ucUser) throws Exception;
	
	/**
	 * @Description: 发送邮件的方式修改手机号码，获取发邮件的内容
	 * @Author: ldp
	 * @Date: 2015年8月28日
	 * @param ucUser
	 * @return
	 * @throws Exception
	 */
	public String getModMobileEmContext(UcUser ucUser)throws Exception;
	
	/**
	 * @Description: 发邮件的方式修改手机号码，根据发的邮箱链接验证身份
	 * @Author: ldp
	 * @Date: 2015年8月28日
	 * @param mmveContext 邮箱链接内容
	 * @return
	 * @throws Exception
	 */
	public MessageVo emailAuth(String mmveContext)throws Exception;
	
	/**
	 * @Description: 修改、设置新邮箱时，进行邮箱验证
	 * @Author: ldp
	 * @Date: 2015年8月28日
	 * @param data 加密数据
	 * @return
	 * @throws Exception
	 */
	public MessageVo validateEmail(String data)throws Exception;
	
	/**
	 * @Description: 邮箱激活(绑定)
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param veContext 邮件内容数据
	 * @return 
	 * @throws Exception
	 */
	public MessageVo activeEmail(String veContext)throws Exception;
	
	/**
	 * @Description: 判断邮箱是否已经存在
	 * @Author: ldp
	 * @Date: 2015年8月27日
	 * @param email
	 * @return false 邮箱已存在   
	 * 		   	true 邮箱不存在
	 * @throws Exception
	 */
	public MessageVo emailIsExist(String email) throws Exception;
		
	
}
