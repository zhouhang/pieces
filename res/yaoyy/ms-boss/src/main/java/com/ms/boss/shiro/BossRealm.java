package com.ms.boss.shiro;

import com.ms.dao.MemberDao;
import com.ms.dao.model.Member;
import com.ms.dao.model.Resources;
import com.ms.service.MemberService;
import com.ms.service.shiro.SerializableSimpleAuthenticationInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * bossRealm
 *
 */

public class BossRealm extends AuthorizingRealm {

	@Autowired
	private MemberDao memberDao;




	
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
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		List<Resources> resourcesList =  memberDao.findResourcesByUserName(userCode);
		Set<String> permissionSet = new HashSet<>();
		for(Resources resources : resourcesList){
			if(resources!=null&& StringUtils.isNotBlank(resources.getPermission())){
				permissionSet.add(resources.getPermission());
			}
		}

		authorizationInfo.setStringPermissions(permissionSet);
		return authorizationInfo;
	}

	
	/**
	 * 验证身份
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		BossToken token = (BossToken) authToken;
		Member member = memberDao.findByUsername(token.getUsername());
		if(member==null){
			throw new AuthenticationException();
		}

		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(member.getUsername(),
				member.getPassword(),
				ByteSource.Util.bytes(member.getSalt()),
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
