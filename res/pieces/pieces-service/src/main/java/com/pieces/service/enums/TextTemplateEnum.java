package com.pieces.service.enums;

import org.apache.commons.lang.ArrayUtils;

/**
 * 验证码模板
 * Created by wangbin on 2016/6/30.
 */
public enum TextTemplateEnum {
	
	SMS_BIZ_CAPTCHA_TEMPLATE("{1}您的注册验证码是{2},该验证码在30分钟内有效.注册成功后即可询价."),
    SMS_BOSS_ADDUSER_PASSWORD_TEMPLATE("{1}您的用户名是{2},密码是{3},请妥善保管."),
    SMS_BOSS_UPDATEUSER_PASSWORD_TEMPLATE("{1}{2}您好,您的新密码是{3},请妥善保管.");

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


    public static void main(String[] args) {
//        String la =  TextTemplateEnum.SMS_CAPTCHA_TEMPLATE.getText("【速采科技】","1024");
//        System.out.println(la);
    }


}
