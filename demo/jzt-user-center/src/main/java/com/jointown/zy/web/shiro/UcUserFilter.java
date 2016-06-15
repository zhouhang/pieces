package com.jointown.zy.web.shiro;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.model.UcUserContact;
import com.jointown.zy.common.model.UcUserScope;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.service.UcUserScopeService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.vo.UcUserVo;


/**
 * @ClassName: UcUserFilter
 * @Description: 用户登录拦截
 * @Author: Calvin.wh
 * @Date: 2015-10-26
 * @Version: 1.0
 */
public class UcUserFilter extends UserFilter {
	
	private static Logger log = LoggerFactory.getLogger(UcUserFilter.class);
	
	@Autowired
	private UcUserService userService;
	@Autowired 
	private UcUserScopeService userScopeService;
	@Autowired
	private UcUserContactService userContactService;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		boolean flag = super.isAccessAllowed(request, response, mappedValue);
		if( (flag) && (!isLoginRequest(request, response)) ){//登录了的情况
			//设置用户信息到session
			UcUserVo userInfo = getSessionUser(request, response);
        	if(userInfo==null){
        		String userName = getSubject(request, response).getPrincipal().toString();
        		log.info("Logined user,subject is:"+userName);
        		UcUser user = userService.findUcUserByUserName(userName);
        		
        		if(user!=null){
        			UcUserVo ucUser = new UcUserVo();
        			BeanUtils.copyProperties(user, ucUser);
        			ucUser.setPassword(null);
        			ucUser.setSalt(null);
        			ucUser = validataCompleteInfo(getSubject(request, response), ucUser);
        			this.saveAccessingUrl(request,response,ucUser);
        			updateLoginUserInfo(request, user.getUserId());
        		}
        	}else{//记录每次正在访问的菜单URL
        		this.saveAccessingUrl(request,response);
        	}
		}
		return flag;
	}
	
	/**
	 * 
	 * @Description: 从session中获取user信息
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param request
	 * @param response
	 * @return
	 */
	private UcUserVo getSessionUser(ServletRequest request, ServletResponse response){
		return (UcUserVo)getSubject(request, response).getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
	}
	
	/**
	 * 
	 * @Description: 保存user信息到session中
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param request
	 * @param response
	 * @param vo
	 */
	private void saveSessionUser(ServletRequest request, ServletResponse response, UcUserVo vo){
		getSubject(request, response).getSession().setAttribute(RedisEnum.SESSION_USER_UC.getValue(),vo);
	}
	
	/**
	 * 
	 * @Description: 保存每次正在访问的菜单URL
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param request
	 * @param userInfo
	 */
	private void saveAccessingUrl(ServletRequest request, ServletResponse response,UcUserVo...userInfo){
		UcUserVo user = ArrayUtils.isEmpty(userInfo)?getSessionUser(request,response):userInfo[0];
		if(user!=null){
			user.setUrl(((HttpServletRequest)request).getRequestURI());
			saveSessionUser(request, response, user);
		}
	}
	
	/**
	 * 
	 * @Description: 更新登陆信息
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param request
	 * @param userId
	 */
	private void updateLoginUserInfo(ServletRequest request,Long userId){
		//存储登录人的IP和时间信息
		UcUser ucuser=new UcUser();
		Date dt = new Date();
		ucuser.setAccessIp(getRemoteHost((HttpServletRequest) request));
		ucuser.setAccessTime(dt);
		ucuser.setUserId(userId);
		userService.updateUcUserLoginInfo(ucuser);
	}
	
	//获取客户端的IP
	public String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * @Description: 登录拦截 ,做用户是否完善惊经营信息 | 联系人信息验证
	 * @Author: Calvin.wh
	 * @Date: 2015-10-22
	 * @param subject
	 * @param ucUser
	 * @return
	 */
	public UcUserVo validataCompleteInfo(Subject subject, UcUserVo ucUser) {
		try {
			UcUserScope userScope = userScopeService.selectUcUserScopeById(ucUser.getUserId());
			List<UcUserContact> contacters = userContactService.selectUcUserContactsByUserId(ucUser.getUserId());
			// 1.如果经营信息完善
			if (null != userScope) {
				ucUser.setHasScope(Boolean.TRUE);
			} else {
				ucUser.setHasScope(Boolean.FALSE);
			}
			// 2.如果联系人信息完善
			if (null != contacters && contacters.size() > 0) {
				ucUser.setHasContacter(Boolean.TRUE);
			} else {
				ucUser.setHasContacter(Boolean.FALSE);
			}
		} catch (Exception e) {
			log.error("UcUserFilter validataCompleteInfo error msg :" + e);
		}
		return ucUser;
	}
}
