/**
 * Copyright © 2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.shiro;

import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;


/**
 * @ClassName: CacheManager
 * @Description: TODO
 * @Author: robin.liu
 * @Date: 2015年7月30日
 * @Version: 1.0
 */
public interface CacheManager extends org.apache.shiro.cache.CacheManager {
	
	/**
	 * 
	 * @Description: 用于获取缓存元素的过期时间
	 * @Author: robin.liu
	 * @Date: 2015年8月3日
	 * @return
	 */
	public <K> Integer cacheExpireTime(String name);
	
	/**
	 * 
	 * @Description: 用于存放缓存值
	 * @Author: robin.liu
	 * @Date: 2015年7月30日
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public <K, V> void putCache(String name,K key, V value);
	
	/**
	 * 
	 * @Description: 用于存放缓存值
	 * @Author: robin.liu
	 * @Date: 2015年7月30日
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public <K, V> void putCache(String name,Map<K,V> cache);
	
	
	/**
	 * 
	 * @Description: 用于获取缓存值
	 * @Author: robin.liu
	 * @Date: 2015年7月30日
	 * @param cacheName
	 * @param key
	 */
	public <K,V> V getCache(String name,K key) throws CacheException;
	
	
	/**
	 * 
	 * @Description: 根据key值删除缓存值
	 * @Author: robin.liu
	 * @Date: 2015年7月30日
	 * @param name
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public <K> void remove(String name,K... key) throws CacheException;
	
	
	
}
