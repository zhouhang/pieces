package com.jointown.zy.common.servlet;

import javax.servlet.ServletException;

import org.springframework.web.servlet.DispatcherServlet;

import com.jointown.zy.common.constant.MessageConstant;

/**
 * 自定义spring分发servlet。用于初始化一些基础的配置等
 * @author LiuPiao
 *
 */
public class JztDispatcherServlet extends DispatcherServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void initFrameworkServlet() throws ServletException {
		//放入公共 resource路径
		getServletContext().setAttribute(MessageConstant.RESOURCE_JS.name(), MessageConstant.RESOURCE_JS.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_CSS.name(), MessageConstant.RESOURCE_CSS.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG.name(), MessageConstant.RESOURCE_IMG.getValue());

		//alert by Mr.songwei 添加上传图片所在路径的外链格式，如http://img.54315.com
		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG_UPLOAD.name(), MessageConstant.RESOURCE_IMG_UPLOAD.getValue());
		//alert by Mr.songwei 添加上传图片所在路径的外链格式，如http://www.54315.com
		getServletContext().setAttribute(MessageConstant.JOINTOWNURL.name(), MessageConstant.JOINTOWNURL.getValue());
		//放入公共域名链接
		getServletContext().setAttribute(MessageConstant.URL_REGISTER_UC.name(), MessageConstant.URL_REGISTER_UC.getValue());
		getServletContext().setAttribute(MessageConstant.URL_SERVER_PREFIX_UC.name(), MessageConstant.URL_SERVER_PREFIX_UC.getValue());
		getServletContext().setAttribute(MessageConstant.URL_FIND_PASSWORD_UC.name(), MessageConstant.URL_FIND_PASSWORD_UC.getValue());
		
		//update by guoyb 放入微信resource路径
		getServletContext().setAttribute(MessageConstant.RESOURCE_JS_WX.name(), MessageConstant.RESOURCE_JS_WX.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_CSS_WX.name(), MessageConstant.RESOURCE_CSS_WX.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG_WX.name(), MessageConstant.RESOURCE_IMG_WX.getValue());
			
		//update by guoyb 放入微信resource路径
		getServletContext().setAttribute(MessageConstant.RESOURCE_JS_WX.name(), MessageConstant.RESOURCE_JS_WX.getValue()+MessageConstant.RESOURCE_VERSION_WX.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_CSS_WX.name(), MessageConstant.RESOURCE_CSS_WX.getValue()+MessageConstant.RESOURCE_VERSION_WX.getValue());
		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG_WX.name(), MessageConstant.RESOURCE_IMG_WX.getValue()+MessageConstant.RESOURCE_VERSION_WX.getValue());
 		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG_UPLOAD_WX.name(), MessageConstant.RESOURCE_IMG_UPLOAD_WX.getValue());
			
		//update by wangjunhu 放入东方中药材网图片路径
		getServletContext().setAttribute(MessageConstant.RESOURCE_IMG_EAST.name(), MessageConstant.RESOURCE_IMG_EAST.getValue());
		//存放支付系统地址
		getServletContext().setAttribute(MessageConstant.URL_PREFIX_PAY.name(), MessageConstant.URL_PREFIX_PAY.getValue());
		//added by biran 20150602 支付系统资源服务
		getServletContext().setAttribute(MessageConstant.RESOURCE_PAY.name(), MessageConstant.RESOURCE_PAY.getValue());
		//alter by biran 20150603  首页专题页增加URL配置
		getServletContext().setAttribute(MessageConstant.URL_TOPIC.name(), MessageConstant.URL_TOPIC.getValue());
		//财务确认划账信息的邮箱地址
		getServletContext().setAttribute(MessageConstant.EMAIL_CHECK_REMITFLOW.name(), MessageConstant.EMAIL_CHECK_REMITFLOW.getValue());
		//alter by zhouji 20150810  水印图片存放地址
		getServletContext().setAttribute(MessageConstant.IMG_PATH.name(), MessageConstant.IMG_PATH.getValue());
		//added by biran 20151113 增加客服中心的地址
		getServletContext().setAttribute(MessageConstant.URL_PREFIX_HELP.name(), MessageConstant.URL_PREFIX_HELP.getValue());
		
	}
}
