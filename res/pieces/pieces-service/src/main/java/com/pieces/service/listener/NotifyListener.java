package com.pieces.service.listener;

import com.pieces.service.enums.NotifyTemplateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${send.mail}")
    public String sendMails;


    @Value("${boss.base.url}")
    public String bossUrl;

    // 1.企业提交资质审核
    // 2. 提交匿名询价时
    // 3.用户提交询价单
    // 4.用户支付时
    // 5.用户提交账单
    //6.合作伙伴申请
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
            //加链接
            String url="";
            switch (event.getType()){
                case 1:
                    url=bossUrl+"/certify/info/"+event.getEventId();
                    break;
                case 2:
                    url=bossUrl+"/anon/detail?id="+event.getEventId();
                    break;
                case 3:
                    url=bossUrl+"/enquiry/"+event.getEventId();;
                    break;
                case 4:
                    url=bossUrl+"/payment/detail/"+event.getEventId();
                    break;
                case 5:
                    url=bossUrl+"/account/bill/detail?id="+event.getEventId();
                    break;
                case 6:
                    url=bossUrl+"/recruit/detail?id="+event.getEventId();
                    break;

                default:break;
            }


            messageHelper.setText(event.getContent()+"<a href='"+url+"'>链接</a>", true);
            //邮件接收人的邮箱地址
            messageHelper.setTo(sendMails.split(","));
            //发送邮件
            mailSender.send(mailMessage);
        } catch (Exception e){

        }
    }
}
