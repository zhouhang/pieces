package com.jointown.zy.web.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.model.BossPermission;
import com.jointown.zy.common.model.BossRole;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BossPermissionService;
import com.jointown.zy.common.service.BossRoleService;
import com.jointown.zy.common.service.BossUserService;
import com.jointown.zy.common.shiro.SerializableSimpleAuthenticationInfo;

/**
 * 用于获取验证信息，权限信息
 * @author LiuPiao
 *
 */
public class BossRealm2 extends AuthorizingRealm {

	@Autowired
	private BossUserService bossUserService;
	
	@Autowired
	private BossPermissionService bossPermissionService;
	
	@Autowired
	private BossRoleService bossRoleService;
	
	
	private Set<Permission> addCommonPermissions(Set<Permission> permissions){
		permissions.add(new BossShiroPermission(new BossPermission().setPermissionName("welcome").setOperationResource("/")));
		permissions.add(new BossShiroPermission(new BossPermission().setPermissionName("welcome").setOperationResource("/welcome")));
		permissions.add(new BossShiroPermission(new BossPermission().setPermissionName("hello").setOperationResource("/hello")));
		permissions.add(new BossShiroPermission(new BossPermission().setPermissionName("OrganizationTree").setOperationResource("/organization/getOrganizationTree")));
		permissions.add(new BossShiroPermission(new BossPermission().setPermissionName("RoleTree").setOperationResource("/organization/getRoleTree")));
		
		return permissions;
	}

	/**
	 * 授权,存储的是Name（可换成ID？）
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		 String userCode = (String)principals.getPrimaryPrincipal();
		 //查询权限信息
		List<BossPermission> bossPermissions = bossPermissionService.findBossPermissionByUserCode4BossRealm(userCode);
		//查询角色信息
		List<BossRole> bossRoles = bossRoleService.findBossRoleByUserCode(userCode);

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if(CollectionUtils.isNotEmpty(bossRoles)){
			Set<String> roles = new HashSet<String>();
			for(BossRole bossRole : bossRoles){
				roles.add(bossRole.getRoleName());
			}
			authorizationInfo.setRoles(roles);
		}
		Set<Permission> permissions = new HashSet<Permission>();
		//加上公共权限列表
		permissions = addCommonPermissions(permissions);
		if(CollectionUtils.isNotEmpty(bossPermissions)){
			//先加上菜单的URL
			for(BossPermission bossPermission : bossPermissions){
				permissions.add(new BossShiroPermission(bossPermission));
			}
			authorizationInfo.setObjectPermissions(permissions);
		}
		return authorizationInfo;
	}

	
	/**
	 * 获取验证信息.
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		BossToken token = (BossToken) authToken;
		BossUser user = bossUserService.findBossUserByUserCode(token.getUsername());
		if(user==null){
			throw new AuthenticationException(new BossErrorException(new ErrorMessage(ErrorRepository.BOSS_USER_PASS_NOT_MATCH)));
		}
		// 设置包括盐的验证信息，此盐用于验证token时候直接提取
		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(user.getUserCode(), // 用户名
				user.getPassword(), // 加密后密码
				Util.bytes(user.getSalt()),// 密码盐
				getName()); // realm name
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
