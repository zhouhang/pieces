package com.ms.biz.shiro;

import com.ms.dao.UserDao;
import com.ms.dao.model.User;
import com.ms.dao.vo.UserVo;
import com.ms.service.UserService;
import com.ms.service.dto.Password;
import com.ms.service.enums.RedisEnum;
import com.ms.service.redis.RedisManager;
import com.ms.service.shiro.SerializableSimpleAuthenticationInfo;
import com.ms.service.utils.EncryptUtil;
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

import java.util.Set;


/**
 * 用于获取验证信息，权限信息
 *
 */
public class BizRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RedisManager redisManager;
	
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

		User user = userDao.findByPhone(token.getUsername());
		if(token.getOpenId()!=null){
			Password pass = EncryptUtil.PiecesEncode(user.getOpenid());
			user.setPassword(pass.getPassword());
			user.setSalt(pass.getSalt());
		} else if (token.getValidationCode() != null ){
			String code = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_LOGIN.getValue()+user.getPhone());
			Password pass = EncryptUtil.PiecesEncode(code);
			user.setPassword(pass.getPassword());
			user.setSalt(pass.getSalt());
		}
		// 从session 中取出验证码

		if(user == null){
			throw new AuthenticationException();
		}
		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(user.getPhone(),
				user.getPassword(),
				Util.bytes(user.getSalt()),
				getName()); // realm name
		return authenticationInfo;
	}

	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
		super.assertCredentialsMatch(token, info);
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
