package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.WxFinanceDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.WxFinanceService;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 我要融资
 * @author Mr.song
 * 
 */
@Controller	
@RequestMapping(value="/financing")
public class WxFinancingController extends WxUserBaseController {

	private final static Logger logger = LoggerFactory.getLogger(WxFinancingController.class);
	
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	@Autowired
	private WxFinanceService wxFinanceService;
	
	/**
	 * 我要融资
	 * 	
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value={""},method={RequestMethod.POST, RequestMethod.GET})
	public String financing(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("WxFinancingController.financing start.");
		//验证登陆
		UcUserVo user = getUserInfo(request);
		if(user ==null){
			user =  new UcUserVo();
		}	
		//查询地区-省市
		AreaVo record = new AreaVo();
		record.setType("1");
		List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
		model.put("areas", areas);
		model.put("user", user);
		return "/fill-financing";
	}
	
	/**
	 * 保存我要融资基本信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/saveFinace",method=RequestMethod.POST)
	@ResponseBody
	public boolean saveFinace(@ModelAttribute("financingForm") WxFinanceDto wxFinanceDto, ModelMap model) {
		logger.info("WxFinancingController.saveFinace start.");
		boolean flag = wxFinanceService.saveFinance(wxFinanceDto);
		return flag;
	}
	
	/**
	 * 查询地区-市区
	 * 
	 * @author aizhengdong
	 * @date 2015年8月5日
	 */
	@RequestMapping(value="/getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAreasByCode(@RequestParam("code") String code) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证参数
			if(StringUtils.isBlank(code)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			if(areas != null && areas.size() > 0){
				map.put("ok", true);
				map.put("obj", areas);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
		}
		return map;
	}
}