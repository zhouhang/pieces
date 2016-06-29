package com.jointown.zy.web.shiro;


import java.util.ArrayList;

import java.util.List;

import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.AuthenticationInfo;

import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.SimpleAuthenticationInfo;

import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import org.apache.shiro.cas.CasAuthenticationException;

import org.apache.shiro.cas.CasRealm;

import org.apache.shiro.cas.CasToken;

import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.subject.PrincipalCollection;

import org.apache.shiro.subject.SimplePrincipalCollection;

import org.apache.shiro.util.CollectionUtils;

import org.apache.shiro.util.StringUtils;

import org.jasig.cas.client.authentication.AttributePrincipal;

import org.jasig.cas.client.validation.Assertion;

import org.jasig.cas.client.validation.Saml11TicketValidator;

import org.jasig.cas.client.validation.TicketValidationException;

import org.jasig.cas.client.validation.TicketValidator;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.vo.UcUserVo;


public class MyCasRealm
extends AuthorizingRealm
{
	public static final String DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME = "longTermAuthenticationRequestTokenUsed";
	public static final String DEFAULT_VALIDATION_PROTOCOL = "CAS";
	private static Logger log = LoggerFactory
			.getLogger(CasRealm.class);
	
	private String casServerUrlPrefix;
	
	private String casService;
	
	private String validationProtocol = "CAS";
	
	private String rememberMeAttributeName = "longTermAuthenticationRequestTokenUsed";
	
	private TicketValidator ticketValidator;
	
	private String defaultRoles;
	
	private String defaultPermissions;
	
	private String roleAttributeNames;
	
	private String permissionAttributeNames;
	
			 @Autowired
			 private UcUserService userService;

	
	public MyCasRealm()
	{
		setAuthenticationTokenClass(CasToken.class);
		}

	
	protected void onInit()
	{
		super.onInit();
		ensureTicketValidator();
		}

	
	protected TicketValidator ensureTicketValidator() {
		log.debug("###########ensureTicketValidator: ticketValidator is:"+ticketValidator+"#############");
		if (this.ticketValidator == null) {
			this.ticketValidator = createTicketValidator();
			}
		return this.ticketValidator;
		}

	
	protected TicketValidator createTicketValidator() {
		String urlPrefix = getCasServerUrlPrefix();
		log.debug("###########createTicketValidator:"+urlPrefix+"#############");
		if ("saml".equalsIgnoreCase(getValidationProtocol())) {
			return new Saml11TicketValidator(urlPrefix);
			}
		return new MyCas20ServiceTicketValidator(urlPrefix);
		}
	
	
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token)
	throws AuthenticationException
	{
		CasToken casToken = (CasToken) token;
		if (token == null) {
			return null;
			}
		
		String ticket = (String) casToken.getCredentials();
		log.debug("#######MyCasRealm ticket is:" + ticket
				+ "#######");
		if (!StringUtils.hasText(ticket)) {
			return null;
			}
		log.debug("###########doGetAuthenticationInfo step2: before ensureTicketValidator#############");
		TicketValidator ticketValidator = ensureTicketValidator();
		log.debug("###########doGetAuthenticationInfo step2: after ensureTicketValidator#############");
		try
		{
			log.debug("###########doGetAuthenticationInfo step3: before ticketValidator.validate, ticket is:"+ticket+"; getCasService() is:"+getCasService()+"#############");
			Assertion casAssertion = ticketValidator.validate(
					ticket, getCasService());
			log.debug("###########doGetAuthenticationInfo step3: after ticketValidator.validate, ticket is:"+ticket+"; getCasService() is:"+getCasService()+"#############");
			
			AttributePrincipal casPrincipal = casAssertion
					.getPrincipal();
			log.debug("#######casPrincipal is:" + casPrincipal
					+ "#######");
			String userId = casPrincipal.getName();
			log
					.debug("Validate ticket : {} in CAS server : {} to retrieve user : {}",
							new Object[] { ticket, getCasServerUrlPrefix(),
									userId });
			
			Map<String, Object> attributes = casPrincipal
					.getAttributes();
			
			casToken.setUserId(userId);
			String rememberMeAttributeName = getRememberMeAttributeName();
			String rememberMeStringValue = (String) attributes
					.get(rememberMeAttributeName);
			boolean isRemembered = (rememberMeStringValue != null)
					&& (Boolean.parseBoolean(rememberMeStringValue));
			if (isRemembered) {
				casToken.setRememberMe(true);
				}
			
			List<Object> principals = CollectionUtils
					.asList(new Object[] { userId, attributes });
			PrincipalCollection principalCollection = new SimplePrincipalCollection(
					principals, getName());
			return new SimpleAuthenticationInfo(
					principalCollection, ticket);
			} catch (TicketValidationException e) {
			throw new CasAuthenticationException(
					"Unable to validate ticket [" + ticket + "]", e);
			}
		}

	
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals)
	{
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
		List<Object> listPrincipals = principalCollection.asList();
		Map<String, String> attributes = (Map) listPrincipals
				.get(1);
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		addRoles(simpleAuthorizationInfo, split(this.defaultRoles));
		
		addPermissions(simpleAuthorizationInfo,
				split(this.defaultPermissions));
		
		List<String> attributeNames = split(this.roleAttributeNames);
		for (String attributeName : attributeNames) {
			String value = (String) attributes.get(attributeName);
			addRoles(simpleAuthorizationInfo, split(value));
			}
		
		attributeNames = split(this.permissionAttributeNames);
		for (String attributeName : attributeNames) {
			String value = (String) attributes.get(attributeName);
			addPermissions(simpleAuthorizationInfo, split(value));
			}
		return simpleAuthorizationInfo;
		}

	
	private List<String> split(String s)
	{
		List<String> list = new ArrayList();
		String[] elements = StringUtils.split(s, ',');
		if ((elements != null) && (elements.length > 0)) {
			for (String element : elements) {
				if (StringUtils.hasText(element)) {
					list.add(element.trim());
					}
				}
			}
		return list;
		}

	
	private void addRoles(
			SimpleAuthorizationInfo simpleAuthorizationInfo, List<String> roles)
	{
		for (String role : roles) {
			simpleAuthorizationInfo.addRole(role);
			}
		}

	
	private void addPermissions(
			SimpleAuthorizationInfo simpleAuthorizationInfo,
			List<String> permissions)
	{
		for (String permission : permissions) {
			simpleAuthorizationInfo
					.addStringPermission(permission);
			}
		}

	
	public String getCasServerUrlPrefix() {
		return this.casServerUrlPrefix;
		}

	
	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
		}

	
	public String getCasService() {
		return this.casService;
		}

	
	public void setCasService(String casService) {
		this.casService = casService;
		}

	
	public String getValidationProtocol() {
		return this.validationProtocol;
		}

	
	public void setValidationProtocol(String validationProtocol) {
		this.validationProtocol = validationProtocol;
		}

	
	public String getRememberMeAttributeName() {
		return this.rememberMeAttributeName;
		}

	
	public void setRememberMeAttributeName(
			String rememberMeAttributeName) {
		this.rememberMeAttributeName = rememberMeAttributeName;
		}

	
	public String getDefaultRoles() {
		return this.defaultRoles;
		}

	
	public void setDefaultRoles(String defaultRoles) {
		this.defaultRoles = defaultRoles;
		}

	
	public String getDefaultPermissions() {
		return this.defaultPermissions;
		}

	
	public void setDefaultPermissions(String defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
		}

	
	public String getRoleAttributeNames() {
		return this.roleAttributeNames;
		}

	
	public void setRoleAttributeNames(String roleAttributeNames) {
		this.roleAttributeNames = roleAttributeNames;
		}

	
	public String getPermissionAttributeNames() {
		return this.permissionAttributeNames;
		}

	
	public void setPermissionAttributeNames(
			String permissionAttributeNames) {
		this.permissionAttributeNames = permissionAttributeNames;
		}
	
}

