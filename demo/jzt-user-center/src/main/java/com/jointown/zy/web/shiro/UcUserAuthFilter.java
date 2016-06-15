package com.jointown.zy.web.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import com.jointown.zy.common.constant.WebConstatVar;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: UcUserAuthFilter
 * @Description: 访问控制拦截
 * @Author: Calvin.wh
 * @Date: 2015-10-26
 * @Version: 1.0
 */
public class UcUserAuthFilter extends UcUserFilter {


	private static final String DEFAULT_URL = "/";

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) {
		boolean flag = super.isAccessAllowed(request, response, mappedValue);

		if ((flag) && (!isLoginRequest(request, response))) {// 登录了的情况
			Subject subject = getSubject(request, response);
			UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			Integer certifyStatus = user.getCertifyStatus();
			// 增加校验，如果没有进行过认证，重定向到认证的URL
			String url = WebUtils.getPathWithinApplication((HttpServletRequest) request);
			// added by biran 20150906 如果是根目录，直接跳转到主页
			
				try {
					if(DEFAULT_URL.equals(url)){
		        		return flag;
		        	}
					if (!url.equals(WebConstatVar.AUTHENTICATION_URL) && certifyStatus == 0) {
							// 去除不需要认证的页面(配置里面添加不需要认证的页面),else为需要验证的页面
							WebUtils.issueRedirect(request, response,WebConstatVar.AUTHENTICATION_URL);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		
		return flag;
	}

}
