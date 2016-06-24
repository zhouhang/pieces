package com.pieces.biz.shiro;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.util.ByteSource.Util;

import com.pieces.dao.model.User;
import com.pieces.service.UserService;
import com.pieces.service.shiro.SerializableSimpleAuthenticationInfo;


/**
 * 用于获取验证信息，权限信息
 *
 */
public class BizRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	
	private Set<Permission> addCommonPermissions(Set<Permission> permissions){
		
		return permissions;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		return authorizationInfo;
	}

	
	/**
	 * 获取验证信息.
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		BizToken token = (BizToken) authToken;
		User user = new User();
		user.setUserName(token.getUsername());
		List<User> users = userService.findUserByCondition(user);
		if(users == null || users.size()==0){
			throw new AuthenticationException();
		}
		String name = getName();
		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(user.getUserName(),
				user.getPassword(),
				Util.bytes(user.getSalt()),
				name); // realm name
		return authenticationInfo;
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
