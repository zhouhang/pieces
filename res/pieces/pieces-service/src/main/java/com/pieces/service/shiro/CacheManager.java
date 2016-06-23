package com.pieces.service.shiro;

import java.util.Map;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;


/**
 * @ClassName: CacheManager
 */
public interface CacheManager extends org.apache.shiro.cache.CacheManager {
	
	/**
	 * 
	 * @Description: 用于获取缓存元素的过期时间
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
	 */
	public <K, V> void putCache(String name,Map<K,V> cache);
	
	
	/**
	 * 
	 * @Description: 用于获取缓存值
	 */
	public <K,V> V getCache(String name,K key) throws CacheException;
	
	
	/**
	 * 
	 * @Description: 根据key值删除缓存值
	 */
	public <K> void remove(String name,K... key) throws CacheException;
	
	
	
}
