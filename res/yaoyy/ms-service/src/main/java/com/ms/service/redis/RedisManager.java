package com.ms.service.redis;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class RedisManager {
	
	
	
	
	private static final Log log = LogFactory.getLog(RedisManager.class);
	
	
	private ShardedJedisPool jedisPool;
	
	// 緩存過期時間，0為永不過期
	private int expire = 0;
	
	public RedisManager(){
		
	}
	
	public RedisManager(ShardedJedisPool jedisPool){
		
	}
	
	
	/**
	 * 獲取分片連接池
	 * @return
	 */
	public ShardedJedisPool getPool(){
		return jedisPool;
	}
	
	
	/**
	 * 获取分片jedis對象
	 * 
	 * @return jedis Pool
	 */
	public  ShardedJedis getJedis() {
		return jedisPool.getResource();
	}
	
	/**
	 * 獲取分片命中key的那個jedis部份
	 * @param key
	 * @return
	 */
	public Jedis getHitJedis(String key){
		return getJedis().getShard(key);
	}
	
	/**
	 * 獲取分片命中key的那個jedis部份
	 * @param key
	 * @return
	 */
	public Jedis getHitJedis(byte[] key){
		return getJedis().getShard(key);
	}

	
	/**
	 * 将jedis对象释放回连接池中
	 * 
	 * @param jedis 使用完毕的Jedis对象
	 * @return true 释放成功；否则返回false
	 */
	public boolean release(ShardedJedis jedis) {
		if (jedisPool != null && jedis != null) {
			jedisPool.returnResource(jedis);
			jedis = null;
			return true;
		}
		return false;
	}
	
	
	/**
	 * 将jedis对象释放回连接池中
	 * 
	 * @param jedis 使用完毕的Jedis对象
	 * @return true 释放成功；否则返回false
	 */
	public boolean releaseBroken(ShardedJedis jedis) {
		if (jedisPool != null && jedis != null) {
			jedisPool.returnBrokenResource(jedis);
			jedis = null;
			return true;
		}
		return false;
	}

	
	/**
	 * 從redis獲取值
	 * 
	 * @param key
	 * @return String
	 */
	public String get(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	
	
	/**
	 * set操作
	 * 
	 * @param key，value
	 * @return true 成功；否则返回false
	 */
	public boolean set(String key, String value,int...expire) {
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key,value);
			if(ArrayUtils.isEmpty(expire)){
				jedis.expire(key, this.expire);
		 	}else{
		 		jedis.expire(key, expire[0]);
		 	}
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
	
	/**
	 * 刪除
	 * @param key
	 */
	public boolean del(String key){
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
	/**
	 * 将jedis对象incrby操作
	 * 
	 * @param key，value
	 * @return true 成功；否则返回false
	 */
	public boolean incrBy(String key, long value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.incrBy(key, value);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	/**
	 * 将jedis对象decrBy操作
	 * 
	 * @param key，value
	 * @return true 成功；否则返回false
	 */
	public boolean decrBy(String key, long value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.decrBy(key, value);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
	
//////////////////////////////////////////////////////////////redis byte[] 操作//////////////////////////////////////////////////////////////////////

	/**
	 * 從redis獲取值
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key){
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.get(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * 設值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(byte[] key,byte[] value,int...expire){
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			if(ArrayUtils.isEmpty(expire)){
				jedis.setex(key, this.expire, value);
		 	}else{
		 		jedis.setex(key, expire[0], value);
		 	}
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}


	/**
	 * 当key不存在的时候设置key
	 * @param key
	 * @param value
     * @return
     */
	public boolean setnx(String key,String value){
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.setnx(key, value)==1L;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
	
	
	/**
	 * 刪除
	 * @param key
	 */
	public boolean del(byte[] key){
		ShardedJedis jedis = null;
		try {
			jedis = getJedis();
			Jedis jedisA = jedis.getShard(key);
			jedisA.del(key);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(jedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(jedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	

//////////////////////////////////////////////////////////////redis hash 數據結構操作//////////////////////////////////////////////////////////////////////
	
	
	/**
	 * @Description: 将jedis对象hget操作,获取hash中的元素
	 */
	public byte[] getInHash(byte[] key, byte[] field) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hget(key, field);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * @Description: 将jedis对象hgetall操作,获取hash中所有元素
	 */
	public Map<byte[], byte[]> getAllInHash(byte[] key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hgetAll(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	

	/**
	 * @Description: jedis对象hset操作,在hash中设置元素
	 */
	public boolean setInHash(byte[] key, byte[] field, byte[] value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.hset(key, field, value);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	/**
	 * @Description: 批量设置信息到redis中
	 */
	public boolean setMapInHash(byte[] key, Map<byte[], byte[]> map) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.hmset(key, map);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	
	/**
	 * @Description: 将jedis对象hdel操作,在hash中删除元素
	 */
	public boolean removeInHash(byte[] key, byte[]... fields) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.hdel(key, fields);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
	/**
	 * @Description: 将jedis对象hkeys操作,返回hash中的keys的集合
	 */
	public Set<byte[]> getKeysInHash(byte[] key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hkeys(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * @Description: 将jedis对象hsize操作,返回hash中的元素数量
	 */
	public Long sizeInHash(byte[] key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hlen(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * @Description: 将jedis对象hvals操作,返回hash中的值的集合
	 */
	public Collection<byte[]> getValuesInHash(byte[] key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hvals(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	 * @Description: 将jedis对象hexists操作,返回hash中是否存在某一元素
	 */
	public boolean existInHash(byte[] key,byte[] field) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.hexists(key, field);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	/**
	 * 判断key存不存在,存在返回true
	 * @param key
	 * @return
     */
	public boolean exists(String key){
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.exists(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
//////////////////////////////////////////////////////////////redis set 數據結構操作//////////////////////////////////////////////////////////////////////

	/**
	 * 将jedis对象smembers操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public Set<String> smembers(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.smembers(key);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	

	/**
	 * 将jedis对象sadd操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public boolean sadd(String key, String... value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.sadd(key, value);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	/**
	 * 将jedis对象sismember操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public boolean sismember(String key, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.sismember(key, value);
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}

	/**
	 * 将jedis对象srem操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public boolean srem(String key, String... values) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.srem(key, values);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;
	}
	
//////////////////////////////////////////////////////////////redis list 數據結構操作//////////////////////////////////////////////////////////////////////

	/**
	* jedis对象在名称为key的list尾添加一个或多个value的 元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public Long rpush(String key,String...value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.rpush(key, value);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	* jedis对象在名称为key的list头部添加一个或多个value的 元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public Long lpush(String key,String...value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.lpush(key, value);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	* jedis对象返回并删除名称为key的list中的尾元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public String rpop(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.rpop(key);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	* jedis对象返回并删除名称为key的list中的头元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public String lpop(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.lpop(key);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	* jedis对象返回名称为key的list全部元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public List<String> lrange(String key) {
		return lrange(key, 0, llength(key));
	}
	
	/**
	* jedis对象返回名称为key的list中start至end之间的元素
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public List<String> lrange(String key,long start,long end) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.lrange(key, start, end);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	/**
	* jedis对象返回名称为key的list长度
	* 
	* @param key
	* @return true 成功；否则返回false
	*/
	public Long llength(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			return shardedJedis.llen(key);
		} catch (JedisConnectionException e) {
		try {
			releaseBroken(shardedJedis);
		} catch (Exception ex) {
			log.error(ex.toString());
		}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return null;
	}
	
	
	/**
	 * 设置jedis对象的过期时间
	 * 
	 * @param key
	 * @param seconds 过期时间(秒)
	 */
	public boolean expire(String key, int seconds) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.expire(key, seconds);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;

	}
	
	/**
	 * 设置jedis对象的过期时间
	 * 
	 * @param key
	 * @param seconds 过期时间(秒)
	 */
	public boolean expire(byte[] key, int seconds) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getJedis();
			shardedJedis.expire(key, seconds);
			return true;
		} catch (JedisConnectionException e) {
			try {
				releaseBroken(shardedJedis);
			} catch (Exception ex) {
				log.error(ex.toString());
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			try {
				release(shardedJedis);
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
		return false;

	}

	public int getExpire() {
		return expire;
	}

	public RedisManager setExpire(int expire) {
		this.expire = expire;
		return this;
	}

	public ShardedJedisPool getJedisPool() {
		return jedisPool;
	}

	public RedisManager setJedisPool(ShardedJedisPool jedisPool) {
		this.jedisPool = jedisPool;
		return this;
	}
	
	
}
