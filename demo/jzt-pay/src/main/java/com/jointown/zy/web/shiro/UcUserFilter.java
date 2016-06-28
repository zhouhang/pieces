package com.jointown.zy.web.shiro;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.redis.RedisManager;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.vo.UcUserVo;


public class UcUserFilter extends UserFilter {
	
	@Autowired
	private UcUserService userService;
	
	@Autowired
	public RedisManager redisManager;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
//		String url=((HttpServletRequest) request).getRequestURL().toString();
//		System.out.println(url);
//		String sourceSys=(String) request.getAttribute("sourceSys");
//		Map<String, String[]> pram = request.getParameterMap();
		boolean flag = super.isAccessAllowed(request, response, mappedValue);
//		flag=true;
		if( (flag) && (!isLoginRequest(request, response)) ){//没登陆且不是登陆请求
			//获取session设置user对象逻辑
			Subject subject = getSubject(request, response);
			String RemoteHost=getRemoteHost((HttpServletRequest) request);
			
        	if(subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue())==null){
        		UcUser user = userService.findUcUserByUserName(subject.getPrincipal().toString());
//        		UcUser user = userService.findUcUserByUserName("zhaohang");
        		if(user!=null){
        			UcUserVo ucUser = new UcUserVo();
        			BeanUtils.copyProperties(user, ucUser);
        			ucUser.setPassword(null);
        			ucUser.setSalt(null);
        			subject.getSession().setAttribute(RedisEnum.SESSION_USER_UC.getValue(), ucUser);
        		}
        		//更新每次用户访问
        		UcUser ucuser=new UcUser();
        		Date dt = new Date();
        		ucuser.setAccessIp(RemoteHost);
        		ucuser.setAccessTime(dt);
        		ucuser.setUserId(user.getUserId());
        		userService.updateUcUserLoginInfo(ucuser);
        	}
		}
//		else{
//			HttpServletRequest req = (HttpServletRequest)request;
//			/*String strBackUrl = "http://" + req.getServerName() //鏈嶅姟鍣ㄥ湴鍧� 
//                    + ":"   
//                    + req.getServerPort()           //绔彛鍙� 
//                    + req.getContextPath()      //椤圭洰鍚嶇О  
//                    + "/"+req.getQueryString();      //璇锋眰椤甸潰鎴栧叾浠栧湴鍧� 
//*///			subject.getSession().setAttribute("backUrl", strBackUrl);
//			String strBackUrl = "/"+(req.getQueryString()==null?"":req.getQueryString());
//			redisManager.set(RedisEnum.KEY_LOGIN_SUCCESS_BACKURL.getValue(), strBackUrl, Integer.MAX_VALUE);
////			redisManager.set(RedisEnum.KEY_LOGIN_SUCCESS_BACKURL.getValue(), strBackUrl, 0);
//		}
		return flag;
	}
	
	//鑾峰彇瀹㈡埛绔殑IP
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
}
