package com.ms.service.shiro;

import com.ms.service.enums.RedisEnum;
import com.ms.service.redis.RedisManager;
import com.ms.service.shiro.CacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 
 * @ClassName: ShiroRedisCacheManager
 * @Description: 实现基于redis的缓存管理器
 * @Author: robin.liu
 * @Date: 2015年8月1日
 * @Version: 1.0
 */
public class ShiroRedisCacheManager implements CacheManager{

	private static final Logger logger = LoggerFactory.getLogger(ShiroRedisCacheManager.class);

	// fast lookup by name map
	@SuppressWarnings("rawtypes")
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

	private RedisManager redisManager;
	
	/**
	 * The application prefix for cache name
	 */
	private String applicationPrefix;
	
	/**
	 * The expire time for cache
	 */
	private Integer expire;

	/**
	 * The shiro cache prefix for cache name
	 */
	private String cacheNamePrefix = RedisEnum.KEY_PREFIX_SHIRO_REDIS_CACHE.getValue();
	
	public String getApplicationPrefix() {
		return applicationPrefix;
	}

	public void setApplicationPrefix(String applicationPrefix) {
		this.applicationPrefix = applicationPrefix;
	}

	/**
	 * Returns the cache name prefix
	 * prefix.
	 * @return The prefix
	 */
	public String getCacheNamePrefix() {
		return cacheNamePrefix;
	}

	/**
	 * Sets the cache name prefix
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setCacheNamePrefix(String keyPrefix) {
		this.cacheNamePrefix = keyPrefix;
	}
	
	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
	private String getCahceNameInRedis(String cacheName){
		return getApplicationPrefix()+getCacheNamePrefix()+cacheName;
	}
	

	public <K> Integer cacheExpireTime(String cacheName) {
		return getExpire()!=null?getExpire():getRedisManager().getExpire();
	}
	

	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");
		Cache c = caches.get(name);
		if (c == null) {
			c = new ShiroRedisCache<K, V>(this,redisManager, getCahceNameInRedis(name));
			caches.put(name, c);
		}
		return c;
	}
	

	public <K, V> V getCache(String name,K key) throws CacheException{
		return (V)getCache(name).get(key);
		
	}
	

	public <K, V> void putCache(String name,K key, V value){
		getCache(name).put(key, value);
	}
	

	public <K, V> void putCache(String name,Map<K,V> cache){
		Cache<K,V> ca = getCache(name);
		for(Map.Entry<K, V> entry:cache.entrySet()){
			ca.put(entry.getKey(), entry.getValue());
		}
	}


	public <K> void remove(String name, K... key) throws CacheException {
		for(K k:key){
			getCache(name).remove(k);
		}
	}

	public Integer getExpire() {
		return expire;
	}

	public void setExpire(Integer expire) {
		this.expire = expire;
	}

	




}
