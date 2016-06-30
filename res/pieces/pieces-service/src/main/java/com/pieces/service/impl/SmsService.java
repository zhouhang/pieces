package com.pieces.service.impl;

import com.pieces.service.enums.SmsTemplateEnum;
import com.pieces.tools.utils.SeqNoUtil;
import com.pieces.tools.utils.httpclient.HttpClientUtil;
import com.pieces.tools.utils.httpclient.common.HttpConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangbin on 2016/6/29.
 */
@Component
public class SmsService {

    private static final Logger logger = Logger.getLogger(SmsService.class);


    @Value("${sms.apikey}")
    private String apikey;

    private final String smsUrl = "https://sms.yunpian.com/v2/sms/single_send.json";

    public String sendSmsCaptcha(String mobile) throws Exception {
        String code = SeqNoUtil.getRandomNum(5);
        Map<String, Object> param = new HashMap<>();
        param.put("apikey", apikey);
        param.put("mobile", mobile);
        param.put("text", SmsTemplateEnum.SMS_CAPTCHA_TEMPLATE.getValue("【药优优】", code));
        HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        return code;
    }

}
