package com.pieces.service.impl;

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

    private static final String ENCODING = "UTF-8";


    @Value("${sms.apikey}")
    private String apikey;

    private final String smsUrl = "https://sms.yunpian.com/v2/sms/single_send.json";

    public String sendSmsCaptcha(String mobile){
        try {
            Map<String,Object> param = new HashMap<>();
            param.put("apikey",apikey);
            param.put("mobile",mobile);
            param.put("text","【速采科技】您的验证码是1234");
//            String tpl_value = URLEncoder.encode("#code#",ENCODING) +"=" + URLEncoder.encode("1234", ENCODING) ;
//            param.put("tpl_value",tpl_value);
            return  HttpClientUtil.post(HttpConfig.custom().url(smsUrl).map(param));
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }


    }

}
