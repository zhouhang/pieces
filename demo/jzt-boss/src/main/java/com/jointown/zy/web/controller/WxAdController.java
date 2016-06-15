package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.WxAdSearchDto;
import com.jointown.zy.common.enums.WxAdTypeEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.model.WxAd;
import com.jointown.zy.common.service.WxAdService;
import com.jointown.zy.common.vo.WxAdVo;

/**
 * 微信广告Controller
 * 
 * @author lichenxiao 2015-3-17
 */
@Controller(value = "wxAdController")
@RequestMapping(value = "bossWxAd")
public class WxAdController {

	private final static Logger logger = LoggerFactory
			.getLogger(WxAdController.class);
	@Autowired
	private WxAdService wxAdService;

	/**
	 * 广告列表
	 * @param pageNo
	 * @param wxAdSearchDto
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getWxAdManager(@ModelAttribute("pageNo") String pageNo,
			@ModelAttribute WxAdSearchDto wxAdSearchDto,
			ModelMap model) throws Exception {
		Page<WxAdVo> page = new Page<WxAdVo>();
		if (!pageNo.isEmpty()) {
			page.setPageNo(Integer.parseInt(pageNo));
		}
		// 查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxAdSearchDto", wxAdSearchDto);
		page.setParams(params);
		// 查询结果
		List<WxAdVo> wxAds = wxAdService
				.findWxAdsByCondition(page);
		page.setResults(wxAds);
		// 广告类型
		Map<String, String> wxAdTypeMap = WxAdTypeEnum.toMap();

		model.put("page", page);
		model.put("wxAdTypeMap", wxAdTypeMap);
		return "/public/wxAdManage";
	}

	
	/**
	 * 广告相关验证
	 * @param wxAd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findByCondition",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findByCondition(@ModelAttribute WxAd wxAd) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int ok = wxAdService.findByCondition(wxAd);
		if(ok>0){
			map.put("ok", true);
		}else{
			map.put("ok", false);
		}
		return map;
	}

	
	
	/**
	 * 得到当前广告
	 * 
	 * @param wxAd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWxAdById", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addWxAdFormAction(
			@ModelAttribute("adId") Long adId) throws Exception {
		WxAdVo wxAd = wxAdService
				.findWxAdById(adId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (wxAd != null) {
			map.put("ok", true);
			map.put("obj", wxAd);
		} else {
			map.put("ok", false);
			map.put("msg", "获取失败！");
		}
		return map;
	}

	/**
	 * 添加广告
	 * 
	 * @param wxAd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addWxAd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addWxAdFormAction(
			@ModelAttribute WxAd wxAd) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int ok = wxAdService.addWxAd(wxAd);
		if (ok > 0) {
			map.put("status", true);
			map.put("info", "保存成功！");
		} else {
			map.put("status", false);
			map.put("info", "保存失败！");
		}
		return map;
	}

	/**
	 * 修改广告
	 * 
	 * @param wxAd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateWxAd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateWxAdFormAction(
			@ModelAttribute WxAd wxAd) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int ok = wxAdService.updateWxAd(wxAd);
		if (ok > 0) {
			map.put("status", true);
			map.put("info", "修改成功！");
		} else {
			map.put("status", false);
			map.put("info", "修改失败！");
		}
		return map;
	}

	/**
	 * 删除广告
	 * 
	 * @param wxAd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteWxAd", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteWxAd(
			@ModelAttribute WxAd wxAd) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int ok = wxAdService.deleteWxAd(wxAd);
		if (ok > 0) {
			map.put("ok", true);
			map.put("msg", "删除成功！");
		} else {
			map.put("ok", false);
			map.put("msg", "删除失败！");
		}
		return map;
	}

}
