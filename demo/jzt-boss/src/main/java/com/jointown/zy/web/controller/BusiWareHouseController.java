package com.jointown.zy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.vo.BusiWareHouseVo;

/**
 * 仓库管理Controller
 * @author wangjunhu
 * 2014-12-22
 */
@Controller(value="busiWareHouseController")
@RequestMapping(value="/busiWareHouseController")
public class BusiWareHouseController {
	
	private final static Logger logger = LoggerFactory.getLogger(BusiWareHouseController.class);
	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	
	@RequestMapping(value="/busiWareHouseService",method=RequestMethod.GET)
	public String getBusiWareHouseManager(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		List<BusiWareHouseVo> busiWareHouses = busiWareHouseService.findBusiWareHouses();
		model.put("busiWareHouses", busiWareHouses);
		return "/public/busiWareHouseManager";
	}
	
	@RequestMapping(value="/getBusiWareHouseTree",method=RequestMethod.GET)
	@ResponseBody
	public String getBusiWareHouseTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BusiWareHouseVo> busiWareHouses = busiWareHouseService.findBusiWareHousesByTree();
		Gson gson = new Gson();
		String jsonArray = gson.toJson(busiWareHouses);
		return jsonArray;
	}
}
