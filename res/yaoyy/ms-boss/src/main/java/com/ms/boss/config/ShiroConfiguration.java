package com.ms.boss.config;

import com.ms.boss.shiro.BossAuthorizationFilter;
import com.ms.boss.shiro.BossRealm;
import com.ms.boss.shiro.RetryLimitHashedCredentialsMatcher;
import com.ms.boss.shiro.ShiroTagFreeMarkerConfigurer;
import com.ms.service.redis.RedisConfig;
import com.ms.service.redis.RedisManager;
import com.ms.service.shiro.MsShiroFilterFactoryBean;
import com.ms.service.shiro.ShiroRedisCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
@AutoConfigureAfter(RedisConfig.class)
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
    public BossRealm getShiroRealm(RetryLimitHashedCredentialsMatcher credentialsMatcher) {
        BossRealm bossRealm =  new BossRealm();
        bossRealm.setCredentialsMatcher(credentialsMatcher);
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
    public DefaultWebSecurityManager getDefaultWebSecurityManager(BossRealm realm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(realm);
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(defaultWebSecurityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }

    @Bean(name = "bossAuthorizationFilter")
    public BossAuthorizationFilter getBizAuthorizationFilter() {
        BossAuthorizationFilter boss = new BossAuthorizationFilter();
        return boss;
    }

    @Bean(name = "shiroFilter")
    public MsShiroFilterFactoryBean getMsShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        MsShiroFilterFactoryBean shiroFilterFactoryBean = new MsShiroFilterFactoryBean();
        shiroFilterFactoryBean
                .setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/403");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");
        shiroFilterFactoryBean.setFiltersString("bossAuthorization=bossAuthorizationFilter");
        //Map<String, Filter> filters = new HashMap<>();
        //filters.put("bossAuthorization", getBizAuthorizationFilter());
        //shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setFilterChainDefinitionsString("/login=anon;/logout=logout;/assets/**=anon;/error/**=anon;" +
                "/role/** = bossAuthorization;" +
                "/category/**=bossAuthorization;" +
                "/ad/**=bossAuthorization;" +
                "/admin/**=bossAuthorization;" +
                "/cms/**=bossAuthorization;" +
                "/index=bossAuthorization;" +
                "/member/**=bossAuthorization;" +
                "/pick/**=bossAuthorization;" +
                "/tracking/**=bossAuthorization;" +
                "/sample/**=bossAuthorization;" +
                "/special/**=bossAuthorization;" +
                "/user/**=bossAuthorization;" +
                "/commodity/**=bossAuthorization;");
        return shiroFilterFactoryBean;
    }


    @Bean(name = "cacheManager")
    public ShiroRedisCacheManager getCacheManager(RedisManager redisManager) {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        shiroRedisCacheManager.setRedisManager(redisManager);
        shiroRedisCacheManager.setApplicationPrefix("boss:");
        shiroRedisCacheManager.setExpire(3600);
        return shiroRedisCacheManager;
    }

    @Bean(name = "credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher getCredentialsMatcher(ShiroRedisCacheManager cacheManager) {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
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
