package com.ms.biz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * wechat mp configuration
 *
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxMpService.class)
public class WechatMpConfiguration {


    /**
     * 设置微信公众号的appid
     */
    @Value("${wechat.mp.appid}")
    private String appId;

    /**
     * 设置微信公众号的app secret
     */
    @Value("${wechat.mp.secret}")
    private String secret;

    /**
     * 微信支付partner id
     */
    private String partnerId;

    /**
     * 微信支付partner key
     */
    private String partnerKey;

    /**
     * 设置微信公众号的token
     */
    @Value("${wechat.mp.token}")
    private String token;

    /**
     * 设置微信公众号的EncodingAESKey
     */
    private String aesKey;


    @Bean
    @ConditionalOnMissingBean
    public WxMpConfigStorage configStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(this.appId);
        configStorage.setSecret(this.secret);
        configStorage.setToken(this.token);
        configStorage.setAesKey(this.aesKey);
        configStorage.setPartnerId(this.partnerId);
        configStorage.setPartnerKey(this.partnerKey);
        return configStorage;
    }

    @Bean
    @ConditionalOnMissingBean
    public WxMpService wxMpService(WxMpConfigStorage configStorage) {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(configStorage);
        return wxMpService;
    }


}
