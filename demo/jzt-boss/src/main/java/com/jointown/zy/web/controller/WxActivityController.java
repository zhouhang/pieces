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

import com.jointown.zy.common.dto.WxActivitySearchDto;
import com.jointown.zy.common.enums.WxActivityTypeEnum;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxActivity;
import com.jointown.zy.common.service.WxActivityService;
import com.jointown.zy.common.vo.WxActivityVo;

/**
* 微信活动Controller
* @author wangjunhu
* 2015-3-2
*/
@Controller(value="wxActivityController")
@RequestMapping(value="bossWxActivity")
public class WxActivityController {

	private final static Logger logger = LoggerFactory.getLogger(WxActivityController.class);
	@Autowired
	private WxActivityService wxActivityService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getWxActivityManager(@ModelAttribute("pageNo") String pageNo, @ModelAttribute WxActivitySearchDto wxActivitySearchDto, ModelMap model) throws Exception {
		Page<WxActivityVo> page = new Page<WxActivityVo>();
		if(!pageNo.isEmpty()){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		//查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxActivitySearchDto", wxActivitySearchDto);
		page.setParams(params);
		//查询结果
		List<WxActivityVo> wxActivitys = wxActivityService.findWxActivitysByCondition(page);
		page.setResults(wxActivitys);
		//活动类型
		Map<String,String> wxActivityTypeMap = WxActivityTypeEnum.toMap();
		
		model.put("page", page);
		model.put("wxActivityTypeMap", wxActivityTypeMap);
		return "/public/wxActivityManage";
	}
	
	/**
	 * 得到当前活动
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWxActivityById",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> addWxActivityFormAction(@ModelAttribute("activityId") Long activityId) throws Exception {
		WxActivityVo wxActivity = wxActivityService.findWxActivityById(activityId);
		Map<String,Object> map = new HashMap<String,Object>();
		if(wxActivity!=null){
			map.put("ok", true);
			map.put("obj", wxActivity);
		}else{
			map.put("ok", false);
			map.put("msg", "获取失败！");
		}
		return map;
	}
	
	/**
	 * 添加活动
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addWxActivity",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addWxActivityFormAction(@ModelAttribute WxActivity wxActivity) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int ok = wxActivityService.addWxActivity(wxActivity);
		if(ok>0){
			map.put("status", true);
			map.put("info", "保存成功！");
		}else{
			map.put("status", false);
			map.put("info", "保存失败！");
		}
		return map;
	}
	
	/**
	 * 修改活动
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateWxActivity",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateWxActivityFormAction(@ModelAttribute WxActivity wxActivity) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int ok = wxActivityService.updateWxActivity(wxActivity);
		if(ok>0){
			map.put("status", true);
			map.put("info", "修改成功！");
		}else{
			map.put("status", false);
			map.put("info", "修改失败！");
		}
		return map;
	}
	
	/**
	 * 删除活动
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteWxActivity",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> deleteWxActivity(@ModelAttribute WxActivity wxActivity) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int ok = wxActivityService.deleteWxActivity(wxActivity);
		if(ok>0){
			map.put("ok", true);
			map.put("msg", "删除成功！");
		}else{
			map.put("ok", false);
			map.put("msg", "删除失败！");
		}
		return map;
	}
	
	/**
	 * 活动相关验证
	 * @param wxActivity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findByCondition",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findByCondition(@ModelAttribute WxActivity wxActivity) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		int ok = wxActivityService.findByCondition(wxActivity);
		if(ok>0){
			map.put("ok", true);
		}else{
			map.put("ok", false);
		}
		return map;
	}
}
