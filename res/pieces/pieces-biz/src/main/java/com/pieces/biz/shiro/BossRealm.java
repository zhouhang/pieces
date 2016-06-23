package com.pieces.biz.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


/**
 * 用于获取验证信息，权限信息
 *
 */
public class BossRealm extends AuthorizingRealm {

	
	
	private Set<Permission> addCommonPermissions(Set<Permission> permissions){
		
		return permissions;
	}

	/**
	 * 授权,存储的是Name（可换成ID？）
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		 String userCode = (String)principals.getPrimaryPrincipal();
		return null;
	}

	
	/**
	 * 获取验证信息.
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		return null;
	}
	
	
	/**
	 * 自定义获取authenticationInfo缓存key,此时为用户名
	 */
	@Override
	protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        return token != null ? token.getPrincipal().toString() : null;
    }
	
	/**
	 * 自定义获取authenticationInfo缓存key,此时为用户名
	 */
	protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
		return principals.getPrimaryPrincipal().toString();
    }
	
	/**
	 * 自定义获取authorizationInfo缓存key,此时为用户名
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return principals.getPrimaryPrincipal().toString();
    }
	
	
	/**
	 * 清除认证缓存
	 */
	public void removeAuthenticationCacheInfo(){
		clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	
	/**
	 * 清除权限缓存
	 */
	public void removeAuthorizationCacheInfo(){
		clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	
}
