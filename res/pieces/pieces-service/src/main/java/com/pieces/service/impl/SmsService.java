package com.pieces.service.impl;

import com.pieces.service.enums.RedisEnum;
import com.pieces.service.enums.TextTemplateEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.httpclient.HttpClientUtil;
import com.pieces.tools.utils.httpclient.common.HttpConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务类
 * Created by wangbin on 2016/6/29.
 */
@Component
public class SmsService {

    private static final int SMS_EXPIRE_TIME = 30*60*1000;

    private static final Logger logger = Logger.getLogger(SmsService.class);

    @Value("${sms.apikey}")
    private String apikey;

    private final String smsUrl = "https://sms.yunpian.com/v2/sms/single_send.json";

    @Autowired
    private RedisManager redisManager;

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public String sendSmsCaptcha(String mobile) throws Exception {
        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_CAPTCHA_TEMPLATE.getText("【药优优】", code));

        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }


    public void sendAddUserAccount(String passWord,String mobile,String username)throws Exception{
        //生成六位数密码
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BOSS_ADDUSER_PASSWORD_TEMPLATE.getText("【药优优】",username,passWord));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
    }
    
    public void sendUpdateUserAccount(String passWord,String mobile,String username)throws Exception{
        //生成六位数密码
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BOSS_UPDATEUSER_PASSWORD_TEMPLATE.getText("【药优优】",username,passWord));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
    }

}
