package com.pieces.service.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;

/**
 * 用于验证密码是否匹配
 * @author LiuPiao
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean{

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
        return isMatch;
    }
    
    /**
     * 自定义验证逻辑
     * @param token
     * @param info
     * @return
     */
    private boolean match(AuthenticationToken token, AuthenticationInfo info){
    	return true;
    }

    
}
