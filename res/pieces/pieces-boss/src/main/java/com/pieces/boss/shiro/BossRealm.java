package com.pieces.boss.shiro;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.pieces.service.shiro.SerializableSimpleAuthenticationInfo;


/**
 * bossRealm
 *
 */
public class BossRealm extends AuthorizingRealm {

	
	
	private Set<Permission> addCommonPermissions(Set<Permission> permissions){
		
		return permissions;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		 String userCode = (String)principals.getPrimaryPrincipal();
		return null;
	}

	
	/**
	 * 验证身份
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		BossToken token = (BossToken) authToken;
		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(token.getUsername(),
				token.getPassword(),
				null,
				getName()); // realm name
		return authenticationInfo;
	}
	
	
	/**
	 * 
	 */
	@Override
	protected Object getAuthenticationCacheKey(AuthenticationToken token) {
        return token != null ? token.getPrincipal().toString() : null;
    }
	
	/**
	 * 
	 */
	protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
		return principals.getPrimaryPrincipal().toString();
    }
	
	/**
	 * 
	 */
	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        return principals.getPrimaryPrincipal().toString();
    }
	
	
	/**
	 * 
	 */
	public void removeAuthenticationCacheInfo(){
		clearCachedAuthenticationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	
	/**
	 * 
	 */
	public void removeAuthorizationCacheInfo(){
		clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
	}
	
}
