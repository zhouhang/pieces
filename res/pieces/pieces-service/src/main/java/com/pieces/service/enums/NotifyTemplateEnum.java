package com.pieces.service.enums;

import org.apache.commons.lang.ArrayUtils;

/**
 * Author: koabs
 * 12/20/16.
 * 通知邮件模板
 */
public enum NotifyTemplateEnum {
    certify("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了会员提交资质审核，请点击以下链接前往处理：\n",1),
    anon("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了新客询价，请点击以下链接前往处理：\n",2),
    enquiry("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了会员询价，请点击以下链接前往处理：\n",3),
    payment("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了会员提交支付凭证，请点击以下链接前往处理：\n",4),
    account_bill("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了会员申请账期支付，请点击以下链接前往处理：\n。",5),
    recruit_agent("待处理业务提醒","您好！\n\n客户{1}于{2}，提交了合作伙伴申请，请点击以下链接前往处理：\n",6);

    private String content;

    private String title;

    private Integer type;




    NotifyTemplateEnum(String title,String content) {
        this.content = content;
        this.title = title;
    }

    NotifyTemplateEnum(String title,String content,Integer type) {
        this.content = content;
        this.title = title;
        this.type=type;
    }

    public String getContent(String... params ){
        String text = new String(this.content);
        if(!ArrayUtils.isEmpty(params)){
            for(int i=0;i<params.length;i++){
                String temKey = "{"+(i+1)+"}";
                String temVal = params[i];
                text = text.replace(temKey,temVal);
            }
        }

        return text;
    }

    public String getTitle(String... params ){
        return this.title;
    }

    public Integer getType() {
        return type;
    }
}
