package com.jointown.zy.common.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.jointown.zy.common.util.BeanUtil;
import com.jointown.zy.common.util.SpringUtil;

public class RedisUtil {
	private static final Log log = LogFactory.getLog(RedisUtil.class);

	private static ShardedJedisPool shardedJedisPool = (ShardedJedisPool) SpringUtil.getBean("shardedJedisPool");
	
	/** 默认的过期时间为2分钟*/
	public static final int DEFAULT_EXPIRE = 120;

	private static String REDIS_HOST = null;
	private static Integer REDIS_PORT = 6379;

	private static String REDIS_SLAVE_HOST = null;
	private static Integer REDIS_SLAVE_PORT = 6379;

	// 初始化
	static {
		try {
			
			PropertyPlaceholderConfigurer p = null;
			
			Properties props = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			if (in != null) {
				props.load(in);
				in.close();
			}

			JedisShardInfo jedisMaster = SpringUtil.getBean("jedis.shardInfo",JedisShardInfo.class);
			JedisShardInfo jedisSlave = SpringUtil.getBean("jedis.shardInfo.slave",JedisShardInfo.class);
			
			
			REDIS_HOST = props.getProperty("sendcloud.message_center.redis.host");
			REDIS_PORT = Integer.parseInt(props.getProperty("sendcloud.message_center.redis.port"));

			REDIS_SLAVE_HOST = props.getProperty("sendcloud.message_center.redis.slave.host");
			REDIS_SLAVE_PORT = Integer.parseInt(props.getProperty("sendcloud.message_center.redis.slave.port"));
		} catch (Exception e) {
			throw new RuntimeException("Config error, msg=" + e.getMessage(), e);
		}
	}

	public static String getRedisHost() {
		return REDIS_HOST;
	}

	public static int getRedisPort() {
		return REDIS_PORT;
	}

	public static String getRedisSlaveHost() {
		return REDIS_SLAVE_HOST;
	}

	public static int getRedisSlavePort() {
		return REDIS_SLAVE_PORT;
	}

	/**
	 * 获取连接池
	 * 
	 * @return shardedJedisPool
	 */
	public static ShardedJedis getShardedJedisPool() {
		return shardedJedisPool.getResource();
	}

	/**
	 * 将jedis对象释放回连接池中
	 * 
	 * @param jedis 使用完毕的Jedis对象
	 * @return true 释放成功；否则返回false
	 */
	public static boolean release(ShardedJedis jedis) {
		if (shardedJedisPool != null && jedis != null) {
			shardedJedisPool.returnResource(jedis);
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
	public static boolean releaseBroken(ShardedJedis jedis) {
		if (shardedJedisPool != null && jedis != null) {
			shardedJedisPool.returnBrokenResource(jedis);
			jedis = null;
			return true;
		}
		return false;
	}

	/**
	 * 将jedis对象get操作
	 * 
	 * @param key
	 * @return String
	 */
	public static String get(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
			return shardedJedis.get(key);
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
	 * 将jedis对象set操作
	 * 
	 * @param key，value
	 * @return true 成功；否则返回false
	 */
	public static boolean set(String key, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
			shardedJedis.set(key, value);
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
	 * 将jedis对象del操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public static boolean del(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
			shardedJedis.del(key);
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
	 * 将jedis对象incrby操作
	 * 
	 * @param key，value
	 * @return true 成功；否则返回false
	 */
	public static boolean incrBy(String key, long value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	public static boolean decrBy(String key, long value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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

	/**
	 * 将jedis对象hget操作
	 * 
	 * @param key，field
	 * @return true 成功；否则返回false
	 */
	public static String hget(String key, String field) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 将jedis对象hgetall操作
	 * 
	 * @param key，field
	 * @return true 成功；否则返回false
	 */
	public static Map<String, String> hgetall(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 根据redisKey获取对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T hgetObject(String key, Class<T> clazz) {
		Map<String, String> map = hgetall(key);
		return BeanUtil.mapToObject(map, clazz);
	}

	/**
	 * 将jedis对象hset操作
	 * 
	 * @param key，field，value
	 * @return true 成功；否则返回false
	 */
	public static boolean hset(String key, String field, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 批量设置信息到redis中
	 * 
	 * @param key
	 * @param map
	 * @return
	 */
	public static boolean hmset(String key, Map<String, String> map) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 设置对象到Redis中
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	public static <T> boolean hsetObject(String key, T object) {
		Map<String, String> map = BeanUtil.objectToMap(object);

		if (map == null || map.isEmpty()) {
			return false;
		}
		return hmset(key, map);
	}

	/**
	 * 将jedis对象hincrBy操作
	 * 
	 * @param key，field，value
	 * @return true 成功；否则返回false
	 */
	public static boolean hincrBy(String key, String field, Long value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
			shardedJedis.hincrBy(key, field, value);
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
	 * 将jedis对象hdel操作
	 * 
	 * @param key，field
	 * @return true 成功；否则返回false
	 */
	public static boolean hdel(String key, String... fields) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 将jedis对象smembers操作
	 * 
	 * @param key
	 * @return true 成功；否则返回false
	 */
	public static Set<String> smembers(String key) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	public static boolean sadd(String key, String... value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	public static boolean sismember(String key, String value) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	public static boolean srem(String key, String... values) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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

	/**
	 * 设置jedis对象的过期时间
	 * 
	 * @param key
	 * @param second 过期时间(秒)
	 */
	public static boolean expire(String key, int seconds) {
		ShardedJedis shardedJedis = null;
		try {
			shardedJedis = getShardedJedisPool();
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
	 * 獲取共享jedis pool實例
	 * @return
	 */
	public static ShardedJedisPool getPool(){
		return shardedJedisPool;
	}

}
