package com.jointown.zy.web.shiro;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.constant.WebConstatVar;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.vo.UcUserVo;


public class UcUserAuthFilter extends UcUserFilter {
	
	@Autowired
	private UcUserService userService;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		
		boolean flag = super.isAccessAllowed(request, response, mappedValue);
		if( (flag) && (!isLoginRequest(request, response)) ){//登录了的情况
			Subject subject = getSubject(request, response);
			UcUserVo user =  (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			Integer certifyStatus=user.getCertifyStatus();
			//增加校验，如果没有进行过认证，重定向到认证的URL
			String url = WebUtils.getPathWithinApplication((HttpServletRequest)request);
			if(certifyStatus == 0){
				//去除不需要认证的页面(配置里面添加不需要认证的页面),else为需要验证的页面
				try {
					WebUtils.issueRedirect(request, response, WebConstatVar.BUSI_AUTHENTICATION_URL);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
}
