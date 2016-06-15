package com.jointown.zy.common.task;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jointown.zy.common.util.EmailUtils;

/**
 * 邮件发送任务线程
 * author ldp
 * 2014年12月24日
 */
public class EmailTaskSend implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(EmailTaskSend.class);
	
	private String subject;
	private String fromEmail;
	private String toEmail;
	private String emailMsg;
	private String logErrorPrefix;
	
	private String username;
	private String password;
	
	public EmailTaskSend(String subject,String toEmail,String emailMsg) {
		this.subject = subject;
		this.toEmail = toEmail;
		this.emailMsg = emailMsg;
	}
	
	public EmailTaskSend(String subject,String fromEmail,String toEmail,String emailMsg,String username,String password) {
		this.subject = subject;
		this.fromEmail = fromEmail;
		this.toEmail = toEmail;
		this.emailMsg = emailMsg;
		this.username = username;
		this.password = password;
	}
	
	public EmailTaskSend setLogErrorPrefiex(String logErrorPrefix){
		this.logErrorPrefix = logErrorPrefix;
		return this;
	}



	@Override
	public void run() {
		logger.info("send email start!");
		try {
			if(fromEmail==null){
				EmailUtils.sendMail(subject,toEmail,emailMsg);
			}else{
				EmailUtils.sendMail(subject,fromEmail,toEmail,emailMsg,username,password);
			}
		} catch (Exception e) {
			logger.error(StringUtils.isNotEmpty(logErrorPrefix)?logErrorPrefix:"send email error is :", e);
		}
		logger.info("send email end!");

	}

}
