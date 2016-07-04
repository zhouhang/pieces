package com.pieces.service.enums;

import org.apache.commons.lang.ArrayUtils;

/**
 * 验证码模板
 * Created by wangbin on 2016/6/30.
 */
public enum TextTemplateEnum {

    SMS_CAPTCHA_TEMPLATE("{1}您的验证码是{2}");

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
        String la =  TextTemplateEnum.SMS_CAPTCHA_TEMPLATE.getText("【速采科技】","1024");
        System.out.println(la);
    }


}
