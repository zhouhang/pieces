package com.ms.service.shiro;

import com.ms.service.redis.RedisManager;
import com.ms.service.utils.SerializeUtils;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 
 * @ClassName: ShiroRedisCache
 * @Description: 实现shiro接口，基于redis的缓存实现.
 * 缓存实现是将对象序列化为string后在redis中作为hash存储，
 * 即存为形如：  cahceName:{key1:string1,key2:string2,...key3:stringN}
 */
public class ShiroRedisCache<K, V> implements Cache<K, V> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
	/**
     * The wrapped Jedis instance.
     */
	private RedisManager redisManager;
	
	/**
	 * 缓存管理器
	 */
	private CacheManager cacheManager;
	
	/**
	 * 缓存名称
	 */
	private String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	
	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom cache name.
	 * @param cache The cache manager instance
	 * @param name The cache name as the hash key in redis
	 */
	public ShiroRedisCache(CacheManager cacheManager, RedisManager redisManager,
                           String name){
		if (redisManager == null) {
	        throw new IllegalArgumentException("Cache argument cannot be null.");
	    }
		this.cacheManager = cacheManager;
	    this.redisManager = redisManager;
		this.name = name;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key){
    	return SerializeUtils.serialize(key);
	}
	
	@Override
	public V get(K key) throws CacheException {
		try {
			if (key == null) {
	            return null;
	        }else{
	        	byte[] byteKey = getByteKey(key);
	        	logger.debug("redis中获取 entry ["+getName()+"],key [" + SerializeUtils.deserialize(byteKey) + "]的值");
	        	byte[] rawValue = redisManager.getInHash(getName().getBytes(), byteKey);
	        	@SuppressWarnings("unchecked")
				V value = (V)SerializeUtils.deserialize(rawValue);
	        	return value;
	        }
		} catch (Throwable t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		 try {
			 	byte[] byteKey = getByteKey(key);
			 	logger.debug("redis中存储  entry ["+getName()+"],key [" + SerializeUtils.deserialize(byteKey) + "]的值");
			 	redisManager.setInHash(getName().getBytes(), byteKey, SerializeUtils.serialize(value));
			 	redisManager.expire(getName().getBytes(), cacheManager.cacheExpireTime(getName()));
	            return value;
	        } catch (Throwable t) {
	            throw new CacheException(t);
	        }
	}

	@Override
	public V remove(K key) throws CacheException {
		try {
            V previous = get(key);
            byte[] byteKey = getByteKey(key);
            logger.debug("redis中删除 entry ["+getName()+"],key [" + SerializeUtils.deserialize(byteKey) + "]的值");
            redisManager.removeInHash(getName().getBytes(), byteKey);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	/**
	 * 如果超过int的范围会丢失精度
	 */
	public int size() {
		try {
			logger.debug("redis中统计 entry ["+getName()+"]的元素数目");
			return redisManager.sizeInHash(getName().getBytes()).intValue();
		}catch (Throwable t) {
            throw new CacheException(t);
        }
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			logger.debug("redis中获取 entry ["+getName()+"]的key的集合");
			Set<K> set = new HashSet<K>();
			for(byte[] bytes:redisManager.getKeysInHash(getName().getBytes())){
				set.add((K)SerializeUtils.deserialize(bytes));
			}
			if(!set.isEmpty()){
				return Collections.unmodifiableSet(new LinkedHashSet<K>(set));
			}else{
				return Collections.emptySet();
			}
		}catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		try {
			logger.debug("redis中获取 entry ["+getName()+"]的value的集合");
			Set<V> set = new LinkedHashSet<V>();
			for(byte[] bytes:redisManager.getValuesInHash(getName().getBytes())){
				set.add((V)SerializeUtils.deserialize(bytes));
			}
			if(!set.isEmpty()){
				return Collections.unmodifiableSet(set);
			}else{
				return Collections.emptySet();
			}
		}catch (Throwable t) {
            throw new CacheException(t);
        }
	}

	@Override
	public void clear() throws CacheException {
		try {
			logger.debug("redis中清除 entry ["+getName()+"]的所有元素");
			redisManager.del(getName().getBytes());
		}catch (Throwable t) {
            throw new CacheException(t);
        }
	}
	

}
