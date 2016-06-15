package com.jointown.zy.web.controller.boss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.WxDemandDto;
import com.jointown.zy.common.dto.WxDemandSearchDto;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.service.BreedService;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.vo.BossUserVo;
import com.jointown.zy.common.vo.WxDemandVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 微信后台求购信息管理
 *
 * @author aizhengdong
 *
 * @date 2015年7月22日
 */
@Controller
@RequestMapping(value = "/WxBoss/demandManager")
public class WxBossDemandManagerController extends WxUserBaseController {

	@Autowired
	private WxDemandService wxDemandService;
	
	@Autowired
	private BreedService breedService;

	/**
	 * 查询求购信息
	 *
	 * @author aizhengdong
	 * @date 2015年7月31日
	 */
	@RequestMapping(value = "/getDemand", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDemand(HttpServletRequest request, @ModelAttribute WxDemandSearchDto wxDemandSearchDto) {
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {
			pageNo = "1";
		}
		
		// 查询条件
		Page<WxDemandVo> page = new Page<WxDemandVo>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("wxDemandSearchDto", wxDemandSearchDto);
		page.setParams(params);
		page.setPageNo(Integer.parseInt(pageNo));

		// 查询结果
		List<WxDemandVo> wxDemands = wxDemandService.findWxDemandsByCondition(page);
		
		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("demandList", wxDemands);
		return resultMap;
	}

	/**
	 * 求购信息详情
	 *
	 * @author aizhengdong
	 * @date 2015年7月29日
	 */
	@RequestMapping(value = "/demandDetail", method = RequestMethod.GET)
	public String getDemandDetail(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			// 验证登录
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				return "redirect:/WxBoss/wxSystem";
			}
						
			String demandId = request.getParameter("demandId");
			String applyResource = request.getParameter("applyResource");
			
			// 验证参数
			if(StringUtils.isBlank(demandId) || StringUtils.isBlank(applyResource)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
						
			WxDemandDto wxDemandDto = new WxDemandDto();
			wxDemandDto.setDemandId(Long.parseLong(demandId));
			wxDemandDto.setApplyResource(applyResource);
			WxDemandVo wxDemandVo = wxDemandService.findDemandByCondition(wxDemandDto);
			model.put("demand", wxDemandVo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "/boss/a-buyDetail";
	}
	
	/**
	 * 审核求购信息
	 * 
	 * @author aizhengdong
	 * @date 2015年7月29日
	 */
	@RequestMapping(value = "/checkDemand", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkDemand(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			// 验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if (bossUser == null) {
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			String demandId = request.getParameter("demandId");
			String applyResource = request.getParameter("applyResource");
			String status = request.getParameter("status");
			String remarks = request.getParameter("remarks");
			
			// 验证参数
			if (StringUtils.isBlank(demandId) || StringUtils.isBlank(applyResource) || StringUtils.isBlank(status)) {
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}

			WxDemandDto wxDemandDto = new WxDemandDto();
			wxDemandDto.setApprover(bossUser.getId().longValue());
			wxDemandDto.setApproveTime(new Date());
			wxDemandDto.setDemandId(Long.parseLong(demandId));
			wxDemandDto.setApplyResource(applyResource);
			wxDemandDto.setStatus(Short.parseShort(status));
			wxDemandDto.setRemarks(remarks);
			
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
			if (e instanceof WxErrorException) {
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			} else {
				map.put("msg", "处理失败！");
			}
		}

		return map;
	}
	
	/**
	 * 新增求购信息(珍药材“我要采购”功能)
	 *
	 * @author aizhengdong
	 * @date 2015年8月7日
	 */
	@RequestMapping(value = "/addDemand", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addDemand(@ModelAttribute WxDemandDto wxDemandDto){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证权限
			BossUserVo bossUser = getWxBossUserInfo();
			if(bossUser == null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			
			//验证参数
			String breedName = wxDemandDto.getBreedName();
			if(StringUtils.isBlank(breedName)){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			
			// 验证品种名称
			Breed breed = breedService.findBreedByBreedName(breedName);
			if(breed == null){
				throw new WxErrorException(ErrorRepository.WX_NO_BREED);
			}
			
			wxDemandDto.setApprover(bossUser.getId().longValue());
			wxDemandDto.setApproveTime(new Date());
			
			String breedPrice = wxDemandDto.getBreedPrice();
			if(StringUtils.isBlank(breedPrice)){
				wxDemandDto.setBreedPrice("面议");
			}else{
				wxDemandDto.setBreedPrice(breedPrice + "元/" + wxDemandDto.getBreedPriceUnit());
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
		} 
		
		return map;
	}
}
