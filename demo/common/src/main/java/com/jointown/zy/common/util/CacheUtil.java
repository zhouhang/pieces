package com.jointown.zy.common.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public class CacheUtil {

	/**
	 * 獲取shiro緩存管理器
	 * @param cacheManagerBeanName
	 * @return
	 */
	public static CacheManager getShiroCacheManager(String... cacheManagerBeanName) {
		return ArrayUtils.isEmpty(cacheManagerBeanName) ? (CacheManager) SpringUtil
				.getBean("cacheManager") : (CacheManager) SpringUtil.getBean(cacheManagerBeanName[0]);
	}
	

}
