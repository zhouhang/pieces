package com.jointown.zy.web.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.jointown.zy.common.util.ValidationCodeUtil;

public class BossLoginFilter extends FormAuthenticationFilter {
	
	public static final String DEFAULT_VCODE_PARAM = ValidationCodeUtil.ValidatorCode.STORAGE_KEY;
	
	private String validationCodeParam = DEFAULT_VCODE_PARAM;
	

	public String getValidationCodeParam() {
		return validationCodeParam;
	}
	public void setValidationCodeParam(String validationCodeParam) {
		this.validationCodeParam = validationCodeParam;
	}

	/**
	 * 获取页面验证码
	 * @param request
	 * @return
	 */
	public String getValidationCode(ServletRequest request) {
        return WebUtils.getCleanParam(request, getValidationCodeParam());
    }
	
	
	
	/**
	 *  创建 Token 
	 */
	@Override
    public BossToken createToken( 
      ServletRequest request, ServletResponse response) { 
         String username = getUsername(request); 
      String password = getPassword(request); 
      String validationCode = getValidationCode(request); 
      boolean rememberMe = isRememberMe(request); 
      String host = getHost(request); 
      return new BossToken( 
         username, password, rememberMe, host, validationCode); 
    } 
	
	
	/**
	 * 失败处理
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		request.setAttribute(getFailureKeyAttribute(), e.getMessage());
		try {
//			WebUtils.SAVED_REQUEST_KEY;
			WebUtils.redirectToSavedRequest(request, response, getLoginUrl());
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 *  登陆认证
	 */
	@Override
    public boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception { 
	  BossToken token = createToken(request, response); 
      try { 
    	 // 验证码校验
    	 if(!ValidationCodeUtil.isValidationCodeMatched(token.getValidationCode())){
    		 throw new AuthenticationException("Incorrect validation code!");
    	 }
         Subject subject = getSubject(request, response); 
         // 身份校验
         subject.login(token);
         // 认证通过
         return onLoginSuccess(token, subject, request, response); 
      } catch (AuthenticationException e) { 
    	 // 认证失败
         return onLoginFailure(token, e, request, response); 
      } 
   } 
	
}
