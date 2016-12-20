package com.pieces.service.enums;

import org.apache.commons.lang.ArrayUtils;

/**
 * Author: koabs
 * 12/20/16.
 * 通知邮件模板
 */
public enum NotifyTemplateEnum {
    certify("企业资质认证信息{1}","您好有新的企业资质信息需要审核,访问地址为: http://boss.sghaoyao.com/certify/info/{1}"),
    anon("新客询价信息{1}","您好有新的匿名询价信息,访问地址为: http://boss.sghaoyao.com/anon/detail?id={1}"),
    enquiry("询价信息{1}","您好有新的询价信息需要报价,访问地址为: http://boss.sghaoyao.com/enquiry/{1}"),
    payment("支付审核信息{1}","您好有新的支付记录需要审核,请处理. 访问地址为: http://boss.sghaoyao.com/payment/detail/{1}"),
    account_bill("账单审核{1}","您好有新的账单需要审核,请处理. 访问地址为: http://boss.sghaoyao.com/account/bill/detail?id={1}");

    private String content;

    private String title;

    NotifyTemplateEnum(String title,String content) {
        this.content = content;
        this.title = title;
    }

    public String getContent(String... params ){
        String text = new String(this.content);
        return regxText(text,params);
    }

    public String getTitle(String... params ){
        String text = new String(this.title);
        return regxText(text,params);
    }

    private String regxText(String text, String... params) {
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
