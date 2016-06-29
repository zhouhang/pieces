package com.jointown.zy.web.controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jointown.zy.common.service.WxInterfaceService;

@Controller(value = "wxInterfaceController")
public class WxInterfaceController extends WxUserBaseController {
	@Autowired
	private WxInterfaceService wxInterfaceService;

	/**
	 * 接收微信服务器发送的get请求，验证服务器地址的有效性
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String respMessage = wxInterfaceService.checkSignature(request);
		writeResponse(response, respMessage);
	}

	/**
	 * 处理微信服务器发送的post请求
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String respMessage = wxInterfaceService.processRequest(request);
		writeResponse(response, respMessage);
	}

	/**
	 * 创建菜单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/createMenu", method = RequestMethod.GET)
	public void createMenu(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String respMessage = wxInterfaceService.createMenu(request);
		writeResponse(response, respMessage);
	}
	
	/**
	 * 返回请求信息
	 * 
	 * @param response
	 * @throws IOException 
	 */
	private void writeResponse(HttpServletResponse response, String msg) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(msg);
		out.flush();
		out.close();
	}
	

	
	/**
	 * 跳转到品种行情页面
	 * 
	 * @author lichenxiao 2015年6月24日
	 */
	@RequestMapping("breedPrice")
	public String newsPrice(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//add by Mr.song 2015.9.17
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "breed-price";
	}
	
	/**
	 * 跳转到我的小珍页面
	 * 
	 * @author lichenxiao 2015年6月24日
	 */
	@RequestMapping("myzyc")
	public String myzyc(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		/**************add by Mr.song  增加我要融资成功跳转后提示tips   start***************/
		String tag = request.getParameter("tag");
		if(StringUtils.isNotBlank(tag)){
			model.put("tag", tag);
		}
		/**************add by Mr.song  增加我要融资成功跳转后提示tips   end*****************/
		//add by Mr.song 2015.9.17
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "my-zyc";
	}
}
