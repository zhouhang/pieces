package com.jointown.zy.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jointown.zy.common.model.EastPzDangan;
import com.jointown.zy.common.service.EastPzDanganService;

/**
 * 品种档案Controller
 * 
 * @author wangjunhu
 *
 * @data 2015年3月6日
 */
@Controller(value="wxEastPzDanganController")
public class WxEastPzDanganController extends WxUserBaseController {
	
	@Autowired
	private EastPzDanganService eastPzDanganService;
	
	@RequestMapping(value = "/getEastPzDangan", method = RequestMethod.GET)
	public String getEastPzDangan(@RequestParam("ycnam") String ycnam, ModelMap model)
			throws Exception {
		EastPzDangan eastPzDangan = eastPzDanganService.findByPrimaryKey(ycnam);
		model.put("eastPzDangan", eastPzDangan);
		
		return "/eastYaocaiPzDetail";
	}
}
