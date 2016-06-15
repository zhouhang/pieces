package com.jointown.zy.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jointown.zy.common.service.EastJgPaiHangService;
import com.jointown.zy.common.vo.EastJgPaiHangVo;

/**
 * 涨跌Top10
 *
 * @author aizhengdong
 *
 * @data 2015年3月6日
 */
@Controller(value = "wxEastJgPaiHangController")
public class WxEastJgPaiHangController extends WxUserBaseController {
	@Autowired
	private EastJgPaiHangService eastJgPaiHangService;

	@RequestMapping(value = "/top10")
	public String findPaiHang(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {
		List<EastJgPaiHangVo> upList = eastJgPaiHangService.findUpPaiHang();
		List<EastJgPaiHangVo> downList = eastJgPaiHangService.findDownPaiHang();
		modelMap.put("up_list", upList);
		modelMap.put("down_list", downList);

		return "/top10";
	}
}
