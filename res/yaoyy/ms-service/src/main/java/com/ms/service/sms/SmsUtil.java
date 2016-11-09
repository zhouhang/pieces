package com.ms.service.sms;

import com.ms.service.enums.RedisEnum;
import com.ms.service.redis.RedisManager;
import com.ms.tools.httpclient.HttpClientUtil;
import com.ms.tools.httpclient.common.HttpConfig;
import com.ms.tools.utils.SeqNoUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务类
 * Created by wangbin on 2016/6/29.
 */
@Component
public class SmsUtil {

    private static final int SMS_EXPIRE_TIME = 30*60;//秒

    private static final int SMS_INTERVAL_TIME = 60 * 1000;//秒

    private static final Logger logger = Logger.getLogger(SmsUtil.class);

    @Value("${sms.apikey}")
    private String apikey;

    private boolean enable = false;

    private final String smsUrl = "https://sms.yunpian.com/v2/sms/single_send.json";

    @Autowired
    private RedisManager redisManager;

    /**
     * 发送登入验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public String sendLoginCaptcha(String mobile) throws Exception {

        check(mobile);

        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_CAPTCHA_LOGIN.getText("【药优优】", code));

        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //记录发送成功的时间
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile,new Date().getTime()+"");
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_LOGIN.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }

    /**
     * 发送注册验证码
     * @param mobile
     * @return
     * @throws Exception
     */
    public String sendRegistCaptcha(String mobile) throws Exception {
        check(mobile);

        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_CAPTCHA_REGISTER.getText("【药优优】", code));

        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //记录发送成功的时间
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile,new Date().getTime()+"");
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_REGISTER.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }

    /**
     * 重设密码短信
     * @param mobile
     * @return
     * @throws Exception
     */
    public String sendResetPasswordSms(String mobile) throws Exception {

        check(mobile);

        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_RESET_PASSWORD.getText("【药优优】", code));

        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //记录发送成功的时间
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile,new Date().getTime()+"");
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_RESET_PASSWORD.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }


    /**
     * 一个手机号一分钟之内只能发送1条短信；
     * 一个手机号三十分钟只能发送三次验证码；
     * @param mobile
     */
    public void check(String mobile){
        String timerStr = redisManager.get(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile);
        String intervalTimeStr = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile);
        if(StringUtils.isNotBlank(timerStr)){
            if(StringUtils.isNotBlank(intervalTimeStr)){
                Long intervalTime =  Long.valueOf(intervalTimeStr) + SMS_INTERVAL_TIME;
                if(new Date().getTime()<intervalTime){
                    throw new RuntimeException("'"+mobile+"',该手机号请求短信间隔太快!");
                }
            }


            Integer timer =  Integer.valueOf(timerStr);
            if(timer>=3){
                throw new RuntimeException("'"+mobile+"',该手机号短信发送次数超标!");
            }else{
                redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,(timer+1)+"",SMS_EXPIRE_TIME);
            }
        }else{
            redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,"1",SMS_EXPIRE_TIME);
        }
    }


}
