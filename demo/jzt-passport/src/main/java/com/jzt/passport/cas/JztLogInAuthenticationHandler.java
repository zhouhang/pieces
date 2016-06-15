package com.jzt.passport.cas;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialNotFoundException;
import javax.security.auth.login.FailedLoginException;

import org.apache.commons.lang.StringUtils;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jointown.zy.common.dto.Password;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.EncryptUtil;


public class JztLogInAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler{
	
	private Logger logger = LoggerFactory.getLogger(JztLogInAuthenticationHandler.class);
	
	@Autowired
	private UcUserService ucUserService;
    
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {
        String username = credential.getUsername().trim();
    	UcUser user = ucUserService.findUcUserByUserName(username);
    	
    	if(user==null){
    		List<UcUser> userlist = ucUserService.findUcUserByMobile(username);
    		if(userlist.size()!=0){
    			user = userlist.get(0);
    		}
        	if(user==null){
	    		logger.error("The account:"+username+" was not found!");
	    		throw new AccountNotFoundException("The account was not found!");
        	}
        	username = user.getUserName();
        	credential.setUsername(username);
    	}
    	if(StringUtils.isEmpty(user.getPassword())){
    		logger.error("The account:"+username+"'s credential was not found!");
    		throw new CredentialNotFoundException("The account was not found!");
    	}
    	if(user.getStatus()==2){
    		logger.error("The account:"+username+" was locked please contact administrator!");
    		throw new AccountLockedException("The user was locked please contact administrator!");
    	}
    	Password ps = EncryptUtil.JointownEncode(credential.getPassword(), user.getSalt());
    	if(!ps.getPassword().equalsIgnoreCase(user.getPassword())){
    		logger.error("Invalid username/password pair!");
    		throw new FailedLoginException("Invalid username/password pair!");
    	}
    	HandlerResult result = createHandlerResult(credential, new SimplePrincipal(username), null);
    	logger.info("login successfully, the user is:"+result.getPrincipal());
    	return result;
    }
}
