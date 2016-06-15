package com.jointown.zy.common.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.jointown.zy.common.util.SpringUtil;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemaker邮件模板发送邮件
 * 
 * @author seven
 *
 */
@SuppressWarnings("serial")
@Component(value="simpleMailSender")
public class SimpleMailSender extends SimpleMailMessage implements Runnable{
	private static final Logger log = LoggerFactory.getLogger(SimpleMailSender.class);
	private JavaMailSender mailSender;
	private MimeMessageHelper messageHellper;
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private Map <String,Object> map;
	private String templateName;
	private String subject;
	private String toEmail;
	
	public SimpleMailSender(){
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public static Logger getLog() {
		return log;
	}
	
	public MimeMessageHelper getMessageHellper() {
		return messageHellper;
	}
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}
	public void setFreeMarkerConfigurer(
			FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setMessageHellper(MimeMessageHelper messageHellper) {
		this.messageHellper = messageHellper;
	}
	/**
	 * 发送邮件模板（默认收件箱和发件箱）
	 * @param map 传递到ftl模板中的参数
	 * @param templateName	ftl模板的名称
	 * @param subject	模板的主题
	 * @throws javax.mail.MessagingException
	 */
	public void sendTemplateMail(Map <String,Object>map, String templateName,String subject) throws  javax.mail.MessagingException {
		String html =getMailHtml(map,templateName);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
	    messageHellper = new MimeMessageHelper(mimeMessage, true, "utf-8");  
	    messageHellper.setSubject(subject);  
		messageHellper.setFrom(SpringUtil.getConfigProperties().get("email.from").toString());
		messageHellper.setTo(SpringUtil.getConfigProperties().get("kefu.email.address").toString());
		messageHellper.setText(html, true);
		mailSender.send(mimeMessage);
	}
	/**
	 * 生成邮件的内容
	 * @param map
	 * @param templateName
	 * @return
	 */
	public String getMailHtml(Map <String,Object>map, String templateName) {
		log.info("更具ftl模板和传递的参数，生成邮件内容");
		String htmlText = null;
		try {
			Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
					templateName);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl,
					map);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		log.info("生成邮件数据结束");
		return htmlText;
	}
	/**
	 * 发送邮件模板（默认收件箱,自定义收件箱）
	 * @param map 传递到ftl模板中的参数
	 * @param templateName	ftl模板的名称
	 * @param subject	模板的主题
	 * @param to	收件人的地址
	 * @throws javax.mail.MessagingException
	 */
	public void sendTemplateMail(Map <String,Object>map, String templateName,String subject,String toEmail) throws  javax.mail.MessagingException {
		String html =getMailHtml(map,templateName);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
	    messageHellper = new MimeMessageHelper(mimeMessage, true, "utf-8");  
	    messageHellper.setSubject(subject);  
		messageHellper.setFrom(SpringUtil.getConfigProperties().get("email.from").toString());
		if(StringUtils.isNotEmpty(toEmail)){
			messageHellper.setTo(toEmail);
		}else{
			messageHellper.setTo(SpringUtil.getConfigProperties().get("kefu.email.address").toString());
		}
		messageHellper.setText(html, true);
		mailSender.send(mimeMessage);
	}
	@Override
	public void run() {
		try {
			if(StringUtils.isEmpty(this.getToEmail())){
				this.sendTemplateMail(this.getMap(), this.getTemplateName(), this.getSubject());
			}else{
				this.sendTemplateMail(this.getMap(), this.getTemplateName(), this.getSubject(),this.getToEmail());
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
