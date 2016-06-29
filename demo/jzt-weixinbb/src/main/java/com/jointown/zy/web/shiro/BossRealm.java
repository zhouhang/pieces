package com.jointown.zy.web.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
/**
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
*/
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.WxUserService;
import com.jointown.zy.common.shiro.SerializableSimpleAuthenticationInfo;

/**
 * 用于获取验证信息，权限信息
 * 
 * @author LiuPiao
 *
 */
public class BossRealm extends AuthorizingRealm {
/**
	private static final Logger log = LoggerFactory.getLogger(BossRealm.class);
*/
	@Autowired
	private WxUserService wxUserService;

	/**
	 * 获取验证信息.（认证）
	 * 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		BossToken token = (BossToken) authToken;
		UcUser user = wxUserService.findByCondition(token.getUsername());

		// 设置包括盐的验证信息，此盐用于验证token时候直接提取
		SerializableSimpleAuthenticationInfo authenticationInfo = new SerializableSimpleAuthenticationInfo(
				user.getUserName(), // 用户名
				user.getPassword(), // 加密后密码
				Util.bytes(user.getSalt()),// 密码盐
				getName()); // realm name
		return authenticationInfo;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}
