package com.ms.service.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;

/**
 * Author: koabs
 * 10/25/16.
 */

@Configuration
public class RedisConfig {


    @Value("${redis.url}")
    private String url;

    @Value("${redis.port}")
    private Integer port;


    @Bean(name = "jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxActive(20);
        jpc.setMaxIdle(10);
        jpc.setMaxWait(100);
        return jpc;
    }

    @Bean(name = "jedis.shardInfo")
    public JedisShardInfo getJedisShardInfo() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(url,port);
        return jedisShardInfo;
    }

    @Bean(name = "shardedJedisPool")
    public ShardedJedisPool getShardedJedisPool() {
        ArrayList<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(getJedisShardInfo());
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(getJedisPoolConfig(),list);
        return shardedJedisPool;
    }

    @Bean(name = "redisManager")
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setExpire(3600);
        redisManager.setJedisPool(getShardedJedisPool());
        return redisManager;
    }
}
