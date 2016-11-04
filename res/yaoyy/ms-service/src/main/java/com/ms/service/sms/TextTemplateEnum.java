package com.ms.service.sms;

import org.apache.commons.lang.ArrayUtils;

/**
 * 验证码模板
 * Created by wangbin on 2016/6/30.
 */
public enum TextTemplateEnum {

    SMS_BIZ_CAPTCHA_REGISTER("{1}您的注册验证码是{2},该验证码在30分钟内有效."),
    SMS_BIZ_CAPTCHA_LOGIN("{1}您的密码是{2},该密码在30分钟内有效."),
    SMS_BIZ_RESET_PASSWORD("{1}您的验证码是{2},该密码在30分钟内有效.");

    private String value;

    TextTemplateEnum(String value){

        this.value = value;
    }

    /**
     * 根据所传数组值来替换模板变量
     * @param params
     * @return
     */
    public String getText(String... params ){
        String text = new String(this.value);
        if(!ArrayUtils.isEmpty(params)){
            for(int i=0;i<params.length;i++){
                String temKey = "{"+(i+1)+"}";
                String temVal = params[i];
                text = text.replace(temKey,temVal);
            }
        }
        return text;
    }
}
