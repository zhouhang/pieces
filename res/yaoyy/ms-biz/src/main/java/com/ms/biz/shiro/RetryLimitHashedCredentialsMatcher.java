package com.ms.biz.shiro;

import com.google.common.base.Strings;
import com.ms.service.dto.Password;
import com.ms.service.utils.EncryptUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于验证密码是否匹配
 * @author LiuPiao
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {

    private Cache<String, AtomicInteger> passwordRetryCache;
    
    private String retryCounterCacheName;
    
    private CacheManager cacheManager;
    
    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setRetryCounterCacheName(String retryCounterCacheName) {
		this.retryCounterCacheName = retryCounterCacheName;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		passwordRetryCache = cacheManager.getCache(retryCounterCacheName);
	}
    
    /**
     * 获取cache中的retry key
     * @param name
     * @return
     */
    private String getRetryKeyInCache(String name){
    	return name;
    }

    /**
     * 验证密码是否匹配逻辑
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
    	boolean isMatch = false;
        String username = (String)token.getPrincipal();
        //计数+1
        AtomicInteger retryCount = passwordRetryCache.get(getRetryKeyInCache(username));
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        if(retryCount.incrementAndGet() > 50) {
            //if retry count > 50 throw exception,so we can't afford more than 5 login attempt.
            
        }
        passwordRetryCache.put(getRetryKeyInCache(username), retryCount);
        // 自定义验证逻辑
        isMatch = match(token,info);
        if(isMatch) {
            //清除计数器
            passwordRetryCache.remove(getRetryKeyInCache(username));
        }
        return isMatch;
    }
    
    /**
     * 自定义验证逻辑
     * @param token
     * @param info
     * @return
     */
    private boolean match(AuthenticationToken token, AuthenticationInfo info){
        if (token instanceof BizToken) {
            BizToken bizToken = (BizToken)token;
           if (!Strings.isNullOrEmpty(bizToken.getValidationCode()) || !Strings.isNullOrEmpty(bizToken.getOpenId())){
               return true;
           }
        }

    	String salt = "";
        if(info instanceof SaltedAuthenticationInfo){
        	ByteSource bs = ((SaltedAuthenticationInfo)info).getCredentialsSalt();
            try {
                salt = new String(bs.getBytes(),"utf-8");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        Password passObj = EncryptUtil.PiecesEncode(new String((char[])token.getCredentials()), salt);
        String tokenHashedCredentials = passObj.getPassword();
        String infoHashCredentials = (String)info.getCredentials();
        return super.equals(tokenHashedCredentials, infoHashCredentials);
    }

    
}
