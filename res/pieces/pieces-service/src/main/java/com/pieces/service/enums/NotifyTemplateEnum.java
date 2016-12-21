package com.pieces.service.enums;

/**
 * Author: koabs
 * 12/20/16.
 * 通知邮件模板
 */
public enum NotifyTemplateEnum {
    certify("企业资质审核","您好！\n\n有客户提交了一次企业资质审核，请尽快登录后台处理。"),
    anon("匿名询价","您好！\n\n有客户提交了一次匿名询价，请尽快登录后台处理。"),
    enquiry("会员询价","您好！\n\n有客户提交了一次询价，请尽快登录后台处理。"),
    payment("用户完成线下支付","您好！\n\n有客户完成了一笔线下支付，请尽快登录后台处理。"),
    account_bill("用户提交账期申请","您好！\n\n有客户提交了一次账期申请，请尽快登录后台处理。");

    private String content;

    private String title;

    NotifyTemplateEnum(String title,String content) {
        this.content = content;
        this.title = title;
    }

    public String getContent(String... params ){
        return this.content;
    }

    public String getTitle(String... params ){
        return this.title;
    }

}
