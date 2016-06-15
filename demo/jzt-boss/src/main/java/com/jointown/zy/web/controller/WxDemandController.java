package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
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

import com.jointown.zy.common.dto.WxDemandDto;
import com.jointown.zy.common.dto.WxDemandSearchDto;
import com.jointown.zy.common.enums.InfoWarehousStateEnum;
import com.jointown.zy.common.enums.InfoManagerColorEnum;
import com.jointown.zy.common.enums.WxInfoResourceEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxDemandVo;

/**
 * 微信求购信息管理
 *
 * @author aizhengdong
 *
 * @data 2015年3月27日
 */
@Controller(value = "wxDemandController")
@RequestMapping(value = "bossWxDemand")
public class WxDemandController {
	
	private final static Logger logger = LoggerFactory.getLogger(WxDemandController.class);
	
	@Autowired
	private WxDemandService wxDemandService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getWxDemandManager(
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNo,
			@ModelAttribute WxDemandSearchDto wxDemandSearchDto, ModelMap model)
			throws Exception {
		Page<WxDemandVo> page = new Page<WxDemandVo>();
		if (!pageNo.isEmpty()) {
			page.setPageNo(Integer.parseInt(pageNo));
		}

		// 查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxDemandSearchDto", wxDemandSearchDto);
		page.setParams(params);

		// 查询结果
		List<WxDemandVo> wxDemands = wxDemandService.findWxDemandsByCondition(page);
		page.setResults(wxDemands);
		model.put("page", page);
		model.put("statusMap", InfoWarehousStateEnum.toMap()); // 状态列表
		model.put("colorMap", InfoManagerColorEnum.toMap()); // 状态颜色列表
		//alter by biran 20150722 去除东网选项
		Map map=WxInfoResourceEnum.toMap();
		map.remove("1");
		model.put("resourceMap", map); // 信息来源列表

		return "/public/wxDemandManage";
	}

	/**
	 * 查询供应信息，伪查询
	 *
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月17日
	 */
	@RequestMapping(value = "/getDemand", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getDemand(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			String demandId = request.getParameter("demandId");
			String applyResource = request.getParameter("applyResource");
			
			// 验证参数
			if(demandId == null || applyResource == null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			WxDemandDto wxDemandDto = new WxDemandDto();
			wxDemandDto.setDemandId(Long.parseLong(demandId));
			wxDemandDto.setApplyResource(applyResource);
			WxDemandVo wxDemandVo = wxDemandService.findDemandByCondition(wxDemandDto);
			if (wxDemandVo != null) {
				map.put("ok", true);
				map.put("obj", wxDemandVo);
			} else {
				map.put("ok", false);
				map.put("msg", "获取失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "获取失败！");
			}
			logger.error("WxDemandController.getDemand：" + msg);
		}
		
		return map;
	}
	
	/**
	 * 处理为有效
	 *
	 * @param demandId
	 * @param mobileNo
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月15日
	 */
	@RequestMapping(value = "/checkDemandValid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDemandValid(@ModelAttribute WxDemandDto wxDemandDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证权限
			BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			//验证参数
			Long demandId = wxDemandDto.getDemandId();
			String applyResource = wxDemandDto.getApplyResource();
			if(demandId == null || applyResource == null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			wxDemandDto.setStatus((short) 1);
			int ok = wxDemandService.updateDemandStatus(wxDemandDto);
			if (ok > 0) {
				map.put("msg", "处理成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "处理失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "处理失败！");
			}
			logger.error("WxDemandController.checkDemandValid：" + msg);
		} 
		
		return map;
	}

	/**
	 * 处理为无效
	 *
	 * @param demandId
	 * @param mobileNo
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月15日
	 */
	@RequestMapping(value = "/checkDemandInvalid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDemandInvalid(@ModelAttribute WxDemandDto wxDemandDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证权限
			BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			//验证参数
			Long demandId = wxDemandDto.getDemandId();
			String applyResource = wxDemandDto.getApplyResource();
			if(demandId == null || applyResource == null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			wxDemandDto.setStatus((short) 2);
			int ok = wxDemandService.updateDemandStatus(wxDemandDto);
			if (ok > 0) {
				map.put("msg", "处理成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "处理失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "处理失败！");
			}
			logger.error("WxDemandController.checkDemandInvalid：" + msg);
		} 
		
		return map;
	}

	/**
	 * 添加求购信息
	 *
	 * @param wxDemandDto
	 * @return
	 * @throws Exception
	 *
	 * @author aizhengdong
	 *
	 * @data 2015年6月15日
	 */
	@RequestMapping(value = "/addDemand", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDemand(@ModelAttribute WxDemandDto wxDemandDto){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证权限
			BossUserVo bossUser = (BossUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_BOSS.getValue());
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			//验证参数
			String breedName = wxDemandDto.getBreedName();
			if(breedName==null || breedName.isEmpty()){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			Breed breed = wxDemandService.checkBreedName(breedName);
			if(breed == null){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			
			int ok = wxDemandService.addBusiPurchaseApply(wxDemandDto);
			if (ok > 0) {
				map.put("msg", "添加成功！");
				map.put("ok", true);
			} else {
				map.put("ok", false);
				map.put("msg", "添加失败！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "添加失败！");
			}
			logger.error("WxDemandController.addDemand：" + msg);
		} 
		
		return map;
	}

	/**
	 * 
	 * @Description: 验证品种名称是否存在
	 * @Author: wangjunhu
	 * @Date: 2015年6月16日
	 * @param breedName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkBreedName",method=RequestMethod.POST)
	public Map<String,Object> checkBreedName(@RequestParam(value="param",defaultValue="",required=true) String breedName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证参数
			if(breedName==null || breedName.isEmpty()){
				throw new Exception("参数错误！");
			}
			Breed breed = wxDemandService.checkBreedName(breedName);
			if(breed != null){
				map.put("ok", true);
				map.put("status", "y");
			}else{
				map.put("ok", false);
				map.put("status", "n");
				map.put("info", "请先在品种管理中添加该品种！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("status", "n");
			map.put("info", "请填写正确的品种名称！");
			logger.error("WxDemandController.checkBreedName："+e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * 品种名称联想
	 * 
	 * @param query
	 *            搜索参数必备
	 * @return
	 * 
	 * @author aizhengdong
	 *
	 * @data 2015年6月16日
	 */
	@ResponseBody
	@RequestMapping(value = "/getBreedNames", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getBreedNames(HttpServletRequest request) {
		String query = request.getParameter("query");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<Breed> breeds = wxDemandService.getBreedNames(query);
		List<String> data = new ArrayList<String>();
		for (Breed breed : breeds) {
			data.add(breed.getBreedName());
		}
		map.put("query", query);
		map.put("suggestions", data);
		map.put("data", breeds);
		return map;
	}
}
