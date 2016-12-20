package com.pieces.service.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * Author: koabs
 * 12/13/16.
 * 消息通知监听
 */
@Component
public class NotifyListener implements ApplicationListener<NotifyEvent> {

    @Autowired
    JavaMailSenderImpl mailSender;

    // 1. 提交匿名询价时
    // 2.企业提交资质审核
    // 3.用户提交询价单
    // 4.用户支付时
    // 5.用户提交账单
    // SpringUtil.getApplicationContext().publishEvent(new OrderStatusEvent(getId(), OrderEnum.COMPLETE.getValue()));

    @Override
    public void onApplicationEvent(NotifyEvent event) {
        try {
            //创建基本邮件信息
            MimeMessage mailMessage = mailSender.createMimeMessage();
            // 为防止乱码，添加编码集设置
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, "UTF-8");
            //发送者地址，必须填写正确的邮件格式，否者会发送失败
            mailMessage.setFrom(new InternetAddress(MimeUtility.encodeText("系统邮件") + " <system@sghaoyao.com>"));
            //邮件主题
            mailMessage.setSubject(event.getTitle());
            //邮件内容，简单的邮件信息只能添加文本信息
            messageHelper.setText(event.getContent(), true);
            //邮件接收人的邮箱地址
            messageHelper.setTo(new String[]{"cs@sghaoyao.com","heh@sghaoyao.com","zhangj@sghaoyao.com"});
            //发送邮件
            mailSender.send(mailMessage);
        } catch (Exception e){

        }
    }
}
