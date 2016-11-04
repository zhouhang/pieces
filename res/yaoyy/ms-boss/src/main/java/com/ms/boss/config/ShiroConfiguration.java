package com.ms.boss.config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ms.boss.shiro.BossAuthorizationFilter;
import com.ms.boss.shiro.BossRealm;
import com.ms.boss.shiro.RetryLimitHashedCredentialsMatcher;
import com.ms.boss.shiro.ShiroTagFreeMarkerConfigurer;
import com.ms.service.redis.RedisManager;
import com.ms.service.shiro.ShiroRedisCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ms.service.shiro.MsShiroFilterFactoryBean;
import org.springframework.web.filter.DelegatingFilterProxy;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;


@Configuration
public class ShiroConfiguration {

    @Value("${doc.base.url}")
    private String baseUrl;

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean(name = "bossRealm")
    public BossRealm getShiroRealm() {
        BossRealm bossRealm =  new BossRealm();
        bossRealm.setCredentialsMatcher(getCredentialsMatcher());
        return bossRealm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(getShiroRealm());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(getDefaultWebSecurityManager());
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "bossAuthorizationFilter")
    public BossAuthorizationFilter getBizAuthorizationFilter() {
        BossAuthorizationFilter boss = new BossAuthorizationFilter();
        return boss;
    }

    @Bean(name = "shiroFilter")
    public MsShiroFilterFactoryBean getMsShiroFilterFactoryBean() {
        MsShiroFilterFactoryBean shiroFilterFactoryBean = new MsShiroFilterFactoryBean();
        shiroFilterFactoryBean
                .setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/403");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFiltersString("bossAuthorization=bossAuthorizationFilter");
        //Map<String, Filter> filters = new HashMap<>();
        //filters.put("bossAuthorization", getBizAuthorizationFilter());
        //shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionsString("/login=anon;/logout=logout;/assets/**=anon;/error/**=anon;/role/** = bossAuthorization;/category/**=bossAuthorization;/commodity/**=bossAuthorization;");
        return shiroFilterFactoryBean;
    }

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
        JedisShardInfo jedisShardInfo = new JedisShardInfo("192.168.1.41",6379,"master");
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

    @Bean(name = "cacheManager")
    public ShiroRedisCacheManager getCacheManager() {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        shiroRedisCacheManager.setRedisManager(getRedisManager());
        shiroRedisCacheManager.setApplicationPrefix("boss:");
        shiroRedisCacheManager.setExpire(3600);
        return shiroRedisCacheManager;
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher getCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(getCacheManager());
        retryLimitHashedCredentialsMatcher.setRetryCounterCacheName("retryCounter");
        return retryLimitHashedCredentialsMatcher;
    }

    @Bean(name = "freemarkerConfig")
    public ShiroTagFreeMarkerConfigurer getShiroTagFreeMarkerConfigurer() {
        ShiroTagFreeMarkerConfigurer shiroTagFreeMarkerConfigurer = new ShiroTagFreeMarkerConfigurer();
        shiroTagFreeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/");
        Map<String , Object> variables = new HashMap<>();
        variables.put("baseUrl",baseUrl);
        shiroTagFreeMarkerConfigurer.setFreemarkerVariables(variables);
        return shiroTagFreeMarkerConfigurer;
    }

}
