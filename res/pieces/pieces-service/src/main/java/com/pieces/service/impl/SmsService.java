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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务类
 * Created by wangbin on 2016/6/29.
 */
@Component
public class SmsService {

    private static final int SMS_EXPIRE_TIME = 30*60;//秒

    private static final int SMS_INTERVAL_TIME = 60 * 1000;//秒

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
        checkCaptcha(mobile);
        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);

        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_CAPTCHA_TEMPLATE.getText("【上工好药】", code));

        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //记录发送成功的时间
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile,new Date().getTime()+"");
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }

    private void checkCaptcha(String mobile){
        String timerStr = redisManager.get(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile);
        String intervalTimeStr = redisManager.get(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile);
        if(StringUtils.isNotBlank(timerStr)){
            if(StringUtils.isNotBlank(intervalTimeStr)){
                Long intervalTime =  Long.valueOf(intervalTimeStr) + SMS_INTERVAL_TIME;
                if(new Date().getTime()<intervalTime){
                    throw new SmsOverException("60秒内仅能获取一次验证码，请稍后重试.");
                }
            }


            Integer timer =  Integer.valueOf(timerStr);
            if(timer>=3){
                throw new SmsOverException("同一手机号1小时内只能发送3次，请稍后在试.");
            }else{
                redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,(timer+1)+"",SMS_EXPIRE_TIME);
            }
        }else{
            redisManager.set(RedisEnum.KEY_MOBILE_TIMER.getValue()+mobile,"1",SMS_EXPIRE_TIME);
        }
    }

    public String sendFindPasswordCaptcha(String mobile) throws Exception {
        checkCaptcha(mobile);
        //生成并发送验证码
        String code = SeqNoUtil.getRandomNum(5);
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BIZ_FINDPASSWORD_CAPTCHA.getText("【上工好药】", code));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        //记录发送成功的时间
        redisManager.set(RedisEnum.KEY_MOBILE_CAPTCHA_INTERVAL.getValue()+mobile,new Date().getTime()+"");
        //验证码存储在redis缓存里
        redisManager.set(RedisEnum.KEY_MOBILE_FINDPASSWORD_CAPTCHA.getValue()+mobile,code,SMS_EXPIRE_TIME);
        return code;
    }


    public void sendAddUserAccount(String passWord,String mobile,String username)throws Exception{
        //生成六位数密码
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BOSS_ADDUSER_PASSWORD_TEMPLATE.getText("【上工好药】",username,passWord));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
    }
    
    public void sendUpdateUserAccount(String passWord,String mobile,String username)throws Exception{
        //生成六位数密码
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", TextTemplateEnum.SMS_BOSS_UPDATEUSER_PASSWORD_TEMPLATE.getText("【上工好药】",username,passWord));
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
    //发送账号到手机
    //【上工好药】尊敬的用户,您选择的付款帐号开户名称:{1}，开户行{2},账号:{3},本次应付{4}.
    public void sendAccount(String mobile,String receiveAccount,String receiveBank,String receiveBankCard,Double money,String code){
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BIZ_SENDACCOUNT_TEMPLATE.getText(receiveAccount,receiveBank, receiveBankCard, String.valueOf(money),code));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("发送账号到手机发送失败", e);
            }
        }
    }

    //审核成功短信
    //【上工好药】尊敬的用户，您提交的企业资质审核已通过， 您已获得在线下单的权限，立即开始采购吧。
    public void sendCertifySuccess(String mobile){
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BIZ_CERTIFYSUCCESS_TEMPLATE.getText());
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("发送账号到手机发送失败", e);
            }
        }
    }
    //审核失败短信
    //【上工好药】尊敬的用户，您的企业资质审核未通过，不通过原因：{1}，请整理好资料后重新提交。有问题请致电 0558-5120088。
    public void sendCertifyFail(String mobile,String reason){
        if (enable) {
            try {
                Map<String, Object> param = new HashMap<>();
                param.put("apikey", apikey);
                param.put("mobile", mobile);
                param.put("text", TextTemplateEnum.SMS_BIZ_CERTIFYFAIL_TEMPLATE.getText(reason));
                HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
            } catch (Exception e) {
                throw new RuntimeException("发送账号到手机发送失败", e);
            }
        }
    }




}
