package com.pieces.boss.shiro;

import java.util.Set;

import com.pieces.dao.model.Member;
import com.pieces.service.MemberService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.pieces.service.shiro.SerializableSimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * bossRealm
 *
 */
public class BossRealm extends AuthorizingRealm {

	@Autowired
	private MemberService memberService;
	
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
		Member member = memberService.findByUsername(token.getUsername());
		if(member==null){
			throw new AuthenticationException();
		}
		token.setMember(member);
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
