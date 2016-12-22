package com.pieces.service.enums;

/**
 * Author: koabs
 * 12/20/16.
 * 通知邮件模板
 */
public enum NotifyTemplateEnum {
    certify("待处理业务提醒","您好！\n\n有客户提交了一次企业资质审核，请尽快登录后台处理。"),
    anon("待处理业务提醒","您好！\n\n有客户提交了一次匿名询价，请尽快登录后台处理。"),
    enquiry("待处理业务提醒","您好！\n\n有客户提交了一次询价，请尽快登录后台处理。"),
    payment("待处理业务提醒","您好！\n\n有客户完成了一笔线下支付，请尽快登录后台处理。"),
    account_bill("待处理业务提醒","您好！\n\n有客户提交了一次账期申请，请尽快登录后台处理。");

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
