package com.jointown.zy.common.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ucs.creditpay.utils.UcsConfig;

import com.jointown.zy.common.pay.ucs.util.UcsBase;
/**
 * @ClassName:UcsListener
 * @author:Calvin.Wangh
 * @date:2015-6-2上午11:57:04
 * @version V1.0
 * @Description:UCS初始化商务签名 验签
 */
public class UcsInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UcsInitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UcsInitServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}
	
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			logger.info("[UCS]加载配置文件开始。。。");
			/** 加载配置文件 common/resources/ucs_config.properties  **/
			UcsConfig.getConfig().loadPropertiesFromSrc();
			logger.info("[UCS]加载配置文件完成。。。");
			logger.info("--------------------------------------");
			logger.info("[UCS]签名初始化开始。。。");
			UcsBase.initialize();
			logger.info("[UCS]签名初始化完成。。");
		} catch (Exception e) {
			logger.error("[UCS]签名初始化失败 :" + e);
		}
	}

}
