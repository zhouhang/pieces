package com.jointown.zy.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* 项目名称：jzt-weixinbb  
* 类名称：WxGotoController  
* 类描述：  主要是负责一些界面的简易逻辑跳转。
* 创建人：  宋威
* 创建时间：2015-08-03  
* @version v1.0  
 */
@Controller
public class WxGotoController extends WxUserBaseController {
	
	/**
	 * @Description: “购买数量超过了可购数量” 友好提示界面
	 * @Author: 宋威
	 * @Date: 2015-08-03
	 * @return String : delist-count界面
	 */
	@RequestMapping(value={"/delist-count"},method={RequestMethod.POST, RequestMethod.GET})
	public String delistcount(){
		return "delist-count";
	}
	
	//图片预览
	@RequestMapping(value={"/preview"},method={RequestMethod.POST, RequestMethod.GET})
	public String preview(HttpServletRequest request,ModelMap model){
		String img = request.getParameter("img");
		model.addAttribute("img",img);
		return "img-view";
	}
	
//	/**
//	 * @Description: 我要挂牌错误 友好提示界面
//	 * @Author: 宋威
//	 * @Date: 2015-08-03
//	 * @return String : bu-count界面
//	 */
//	@RequestMapping(value={"/busilisting-failure"},method={RequestMethod.POST, RequestMethod.GET})
//	public String busilistingFailure(@RequestParam(value="error", required = false) String error,ModelMap model){
//		model.addAttribute("error", error);
//		return "busilisting-failure";
//	}
	
	/**
	 * @Description: “摘牌失败” 友好提示界面
	 * @Author: 宋威
	 * @Date: 2015-08-03
	 * @return String : delist-failure界面
	 */
	@RequestMapping(value={"/delist-failure"},method={RequestMethod.POST, RequestMethod.GET})
	public String delistfailure(){
		return "delist-failure";
	}
	
	/**
	 * @Description: “摘牌成功” 友好提示界面
	 * @Author: 宋威
	 * @Date: 2015-08-03
	 * @return String : delist-succeed界面
	 */
	@RequestMapping(value={"/delist-succeed"},method={RequestMethod.POST, RequestMethod.GET})
	public String delistsucceed(){
		return "delist-succeed";
	}
	
	/**
	 * @Description: “商品已下架” 友好提示界面
	 * @Author: 宋威
	 * @Date: 2015-08-03
	 * @return String : delist-unShelve界面
	 */
	@RequestMapping(value={"/delist-unShelve"},method={RequestMethod.POST, RequestMethod.GET})
	public String delistunShelve(){
		return "delist-unShelve";
	}
	
	/**
	 * @Description: “摘牌商品已无效” 友好提示界面
	 * @Author: 宋威
	 * @Date: 2015-08-03
	 * @param request
	 * @return String : delist-void界面
	 */
	@RequestMapping(value={"/delist-void"},method={RequestMethod.POST, RequestMethod.GET})
	public String delistvoid(){
		return "delist-void";
	}
}