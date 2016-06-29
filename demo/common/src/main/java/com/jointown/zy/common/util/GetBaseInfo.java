package com.jointown.zy.common.util;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @date 2015-03-16
 */
public class GetBaseInfo{
	
	public static Long getBossUserId() {
		BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
		return bossUser == null?null:new Long((long)bossUser.getId());
	}
	
	public static Long getUcUserId() {
		UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		return user == null?null:user.getUserId();
	}
	
	public static String getIp(HttpServletRequest request) {
		return getRemoteHost(request);
	}
	
	public static Date getCreatetime() {
		return new Date();
	}
	public static Date getUpdatetime() {
		return new Date();
	}
	
	//获取客户端的IP
	public static String getRemoteHost(HttpServletRequest request){
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
	
	public static BossUserVo getBossUser() {
		return (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
	}
	
	public static UcUserVo getUcUser() {
		return (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
	}
}