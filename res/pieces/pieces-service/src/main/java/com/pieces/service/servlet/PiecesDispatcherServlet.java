package com.pieces.service.servlet;

import javax.servlet.ServletException;

import org.springframework.web.servlet.DispatcherServlet;

import com.pieces.service.constant.StaticResourceFilePath;

/**
 * 自定义spring分发servlet。用于初始化一些基础的配置等
 *
 */
public class PiecesDispatcherServlet extends DispatcherServlet{
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void initFrameworkServlet() throws ServletException {
		//放入静态 resource路径
		getServletContext().setAttribute(StaticResourceFilePath.BIZ_RESOURCE_JS.name(), StaticResourceFilePath.BIZ_RESOURCE_JS.getValue());
		getServletContext().setAttribute(StaticResourceFilePath.BIZ_RESOURCE_CSS.name(), StaticResourceFilePath.BIZ_RESOURCE_CSS.getValue());
		getServletContext().setAttribute(StaticResourceFilePath.BIZ_RESOURCE_IMAGES.name(), StaticResourceFilePath.BIZ_RESOURCE_IMAGES.getValue());	
		
		getServletContext().setAttribute(StaticResourceFilePath.BOSS_RESOURCE_JS.name(), StaticResourceFilePath.BOSS_RESOURCE_JS.getValue());
		getServletContext().setAttribute(StaticResourceFilePath.BOSS_RESOURCE_CSS.name(), StaticResourceFilePath.BOSS_RESOURCE_CSS.getValue());
		getServletContext().setAttribute(StaticResourceFilePath.BOSS_RESOURCE_IMAGES.name(), StaticResourceFilePath.BOSS_RESOURCE_IMAGES.getValue());		
	}
}
