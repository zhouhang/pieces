package com.pieces.service.impl;

import com.pieces.dao.exception.SmsOverException;
import com.pieces.service.enums.RedisEnum;
import com.pieces.service.enums.TextTemplateEnum;
import com.pieces.service.redis.RedisManager;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.httpclient.HttpClientUtil;
import com.pieces.tools.utils.httpclient.common.HttpConfig;
import org.apache.commons.lang.StringUtils;
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

    private boolean enable = true;

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
        String timerStr = redisManager.get(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile);
        if(StringUtils.isNotBlank(timerStr)){
            Integer timer =  Integer.valueOf(timerStr);
            if(timer>=3){
                throw new SmsOverException("'"+mobile+"'该手机号短信发送次数超标!");
            }else{
                redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,(timer+1)+"",SMS_EXPIRE_TIME);
            }
        }else{
            redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,"1",SMS_EXPIRE_TIME);
        }

        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_CAPTCHA_TEMPLATE.getText("【上工之选】", code));

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
        param.put("text", TextTemplateEnum.SMS_BOSS_ADDUSER_PASSWORD_TEMPLATE.getText("【上工之选】",username,passWord));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
    }
    
    public void sendUpdateUserAccount(String passWord,String mobile,String username)throws Exception{
        //生成六位数密码
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BOSS_UPDATEUSER_PASSWORD_TEMPLATE.getText("【上工之选】",username,passWord));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
    }

    // 后台报价 quoted
    //【上工之选】@@@ 您好,您的询价单 @@@ (询价商品 @@@ 等)已有报价,请在询价记录中查看.
    public void sendQuoted(String username, String code, String commodity, String mobile) {
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_QUOTED.getText(username, code, commodity));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("后台报价短信发送失败", e);
            }
        }
    }

    //后台修改报价 quotedUpdate
    //【上工之选】@@@ 您好,您的询价单 @@@ 中部分商品的报价有更新,请在询价记录中查看.
    public void sendQuotedUpdate(String username, String code, String mobile) {
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_QUOTEDUPDATE.getText(username, code));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("后台修改报价短信发送失败", e);
            }
        }
    }

    //后台确认支付(非账期)paySuccess
    //【上工之选】@@@ 您好,您 @@@元 的款项支付成功,详情请在支付记录中查看,平台会尽快为您安排发货.
    public void sendPaySuccess(String username, Double money, String mobile) {
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_PAYSUCCESS.getText(username,String.valueOf(money)));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("后台确认支付(非账期)短信发送失败", e);
            }
        }
    }

    // 后台确认支付(账期)payAccountSuccess
    //【上工之选】@@@ 您好,您 @@@元 的款项支付成功,详情请在支付记录中查看.
    public void sendPayAccountSuccess(String username, Double money, String mobile){
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_PAYACCOUNTSUCCESS.getText(username, String.valueOf(money)));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("后台确认支付(账期)短信发送失败", e);
            }
        }
    }

    //后台确认支付失败payFail
    //【上工之选】@@@ 您好,您 @@@元 的款项支付失败,详情在支付记录中查看.
    public void sendPayFail(String username, Double money, String mobile) {
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_PAYFAIL.getText(username, String.valueOf(money)));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("后台确认支付失败短信发送失败", e);
            }
        }
    }

    //账期成功accountSuccess
    //【上工之选】@@@ 您好,您 @@@元 的账期申请成功,详情请在账期账单中查看,平台会尽快为您安排发货.
    public void sendAccountSuccess(String username, Double money, String mobile) {
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_ACCOUNTSUCCESS.getText(username, String.valueOf(money)));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("账期申请成功短信发送失败", e);
            }
        }
    }

    //账期失败accountFail
    //【上工之选】@@@ 您好,您 @@@元 的账期申请失败,详情请在账期账单中查看.
    public void sendAccountFail(String username, Double money, String mobile){
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BOSS_ACCOUNTFAIL.getText(username, String.valueOf(money)));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("账期申请失败短信发送失败", e);
            }
        }
    }

}
