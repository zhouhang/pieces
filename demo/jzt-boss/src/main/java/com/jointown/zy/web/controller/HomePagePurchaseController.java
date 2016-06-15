package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.HomePagePurchaseDto;
import com.jointown.zy.common.enums.HomePagePurchaseEnum;
import com.jointown.zy.common.model.HomePagePurchaseModel;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.HomePagePurchaseService;

/**
 * @ClassName: HomePagePurchaseController
 * @Description: 首页采购信息
 * @Author: Calvin.wh
 * @Date: 2015-11-2
 * @Version: 1.0
 */
@Controller(value="homePagePurchaseController")
@RequestMapping(value="/homePagePurchase")
public class HomePagePurchaseController extends UserBaseController {
	@Autowired
	private HomePagePurchaseService purchaseService;
	
	/**
	 * @Description: 列表页数据
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param purchaseDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "", method = {RequestMethod.GET,RequestMethod.POST })
	public String getPageList(HomePagePurchaseDto purchaseDto,Model model){
		//input
		Page<HomePagePurchaseModel> page = new Page<HomePagePurchaseModel>();
		page.setPageNo(purchaseDto.getPageNo());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("purchaseDto", purchaseDto);
		page.setParams(params);
		page.setResults(purchaseService.getPageList(page));
		
		//output
		model.addAttribute("page", page);
		model.addAttribute("types", HomePagePurchaseEnum.toMap());
		return "public/homePagePurchase";
	}
	
	/**
	 * @Description: 验证采购单号
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param param 单号 
	 * @return
	 */
	@RequestMapping(value="/checkPurchaseId",method = {RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public String checkPurchaseId(@RequestParam(defaultValue="") String param){
		int isExist = purchaseService.getPurchaseOrder(param);
		if(0==isExist){
			return "采购单不存在,请重新输入!";
		}
		return "y";
	}
	
	/**
	 * @Description: 获取采购信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getPurchaseInfo")
	@ResponseBody
	public Map<String,Object> getPurchaseInfo(@RequestParam String id){
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtils.isBlank(id)){
			param.put("ok", Boolean.FALSE);
			param.put("msg", "采购单号不能为空");
			return param;
		}
		HomePagePurchaseModel model = purchaseService.getPurchaseInfo(id);
		if(null == model){
			param.put("ok", Boolean.FALSE);
			param.put("msg", "获取失败");
		}else{
			param.put("ok", Boolean.TRUE);
			param.put("obj", model);
		}
		return  param;
	}
	
	/**
	 * @Description: 添加首页采购信息
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param purchase
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addPurchase",method = {RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> addPurchase(HomePagePurchaseModel purchase,Model model){
		Map<String,Object> param = purchaseService.addPurchase(purchase);
		return param;
	}
	
	/**
	 * @Description: 采购信息修改
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param purchase
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/updatePurchase",method = {RequestMethod.GET,RequestMethod.POST })
	@ResponseBody
	public Map<String,Object> updatePurchase(HomePagePurchaseModel purchase,Model model){
		Map<String,Object> param = purchaseService.updatePurchase(purchase);
		return param;
	}
	
	/**
	 * @Description: 采购信息删除
	 * @Author: Calvin.wh
	 * @Date: 2015-11-3
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delPurchaseInfo",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> delPurchaseInfo(@RequestParam(defaultValue="") String id){
		Map<String,Object> param  = purchaseService.deletePurchase(id);
		return param;
	}
}
