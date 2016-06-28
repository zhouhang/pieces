package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.UcUserContactDto;
import com.jointown.zy.common.dto.UcUserScopeDto;
import com.jointown.zy.common.enums.UcUserSexEnum;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.service.UcUserDealInService;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 
 * 完善用户信息引导
 * 完善经营信息 | 完善联系人信息
 * @author Calvin.wh
 * @date 2015-10-19
 */

@Controller
@RequestMapping(value="/completeInfoGuide")
public class CompleteInfoGuideController extends UserBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompleteInfoGuideController.class);
	
	@Autowired
	private UcUserDealInService ucUserDealInService;
	@Autowired
	private UcUserContactService ucUserContactService;
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	
	private static final String BUSI_TYPE="busi";
	private static final String CONTACTER_TYPE="contacter";
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UcUserVo getUserInfo(){
		UcUserVo user = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		return user;
	}
	
	/**
	 * @Description: 完善经营信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/completeBusiInfo")
	public String completeBusiInfo(Model model){
		String redirect = "";
		//1.如果完成了所有信息引导
		if(getUserInfo().isHasScope() && getUserInfo().isHasContacter()){
			redirect = "redirect:/my_material/info";
		}else if(getUserInfo().isHasScope()){
			//2.如果只完成了经营信息
			redirect= "redirect:/completeInfoGuide/completeContacterInfo";
		}else{
			AreaVo record = new AreaVo();//查询地区-省市
			record.setType("1");
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			model.addAttribute("areas", areas);
			model.addAttribute("user", getUserInfo());
			redirect="public/complete-busi-info";
		}
		return redirect;
	}
	
	/**
	 * @Description: 完善联系人信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @return
	 */
	@RequestMapping(value="/completeContacterInfo",method={RequestMethod.GET,RequestMethod.POST})
	public String completeContacterInfo(Model model){
		String redirect = "";
		try {
			//如果经营信息完善 并且 联系人信息完善 则跳转至会员中心
			if(getUserInfo().isHasScope() && getUserInfo().isHasContacter()){
				redirect = "redirect:/my_material/info";
			}else if(!getUserInfo().isHasScope()){
				//如果经营信息未完善 则去完善联系人信息
				redirect = "redirect:/completeInfoGuide/completeBusiInfo";
			}else{//如果经营信息没有完善 则 去完善经营信息
				model.addAttribute("sexMap", UcUserSexEnum.toMap());//性别枚举
				model.addAttribute("user", getUserInfo());
				redirect = "public/complete-contacter-info";
			}
		} catch (Exception e) {
			logger.error("CompleteInfoGuideController completeContacterInfo error msg"+ e);
		}
		return redirect;
	}
	
	/**
	 * @Description: 完善经营信息
	 * @Author: Calvin.wh
	 * @Date: 2015-10-19
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/saveBusiInfo",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveBusiInfo(UcUserScopeDto dto){
		String result = "";
		try {
			result = ucUserDealInService.addUserDealInfo(dto);
		} catch (Exception e) {
			logger.error("CompleteInfoGuideController saveBusiInfo error msg :"+e);
		}
		return result;
	}
	
	/**
	 * @Description: 完善联系人信息保存
	 * @Author: Calvin.wh
	 * @Date: 2015-10-20
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/saveContacterInfo",produces="text/html;charset=UTF-8",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String saveContacterInfo(UcUserContactDto dto){
		String result = "";
		try {
			result = ucUserContactService.addContacter(dto);
		} catch (Exception e) {
			logger.error("CompleteInfoGuideController saveContacterInfo error msg :"+e);
		}
		return result;
	}
	
	/**
	 * @Description: 获取品种名称 输入的关键字标红
	 * @Author: Calvin.wh
	 * @Date: 2015-10-19
	 * @param param
	 * @param model
	 * @param request
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value="/getBreeds",method={RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> getBreeds(@RequestParam(value="param",defaultValue="")String param,Model model,HttpServletRequest request){
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		String topTag = "<span class='red'>";
		String footerTag = "</span>";
		try {
			if(StringUtils.isNotBlank(param)){
				List<Breed> breeds = ucUserDealInService.getBreeds(param);
				if(null!=breeds && breeds.size()>0){
					for(Breed breed : breeds){
						//获取品种名称 组装html代码 关键字标红
						breed.setBreedCname(breed.getBreedName());
						breed.setBreedName(breed.getBreedName().replace(param, topTag+param+footerTag));
					}
					map.put("ok", true);
					map.put("obj", breeds);
				}else{
					map.put("ok", false);
				}
			}
		} catch (Exception e) {
			map.put("ok", false);
			logger.error("CompleteInfoGuideController getBreeds error msg :"+e);
		}
		return map;
	}
	
	/**
	 * 查询地区
	 * @param firstCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getAreasByCode",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAreasByCode(@RequestParam("code") String code) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证参数
			if(code==null || code.isEmpty()){
				throw new Exception("参数错误！");
			}
			//查询地区-市区
			AreaVo record = new AreaVo();
			record.setFirstcode(code);
			List<AreaVo> areas = warehouseApplyService.findAreasByCondition(record);
			if(areas.size()>0){
				map.put("ok", true);
				map.put("obj", areas);
			}else{
				map.put("ok", false);
			}
		} catch (Exception e) {
			map.put("ok", false);
			logger.error("CompleteInfoGuideController getAreasByCode error msg :"+e);
		}
		return map;
	}
	
	/***
	 * 日(下)后(次)再说
	 * @Description: 改变session用户中的ignore状态 标记为已完善
	 * @Author: Calvin.wh
	 * @Date: 2015-10-22
	 * @return
	 */
    @RequestMapping(value="/inFuture")
	public String inFuture(@RequestParam String type){
    	UcUserVo userVo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
    	if(BUSI_TYPE.equals(type))
    		userVo.setHasScope(Boolean.TRUE);
    	if(CONTACTER_TYPE.equals(type))
    		userVo.setHasContacter(Boolean.TRUE);
		
    	SecurityUtils.getSubject().getSession().setAttribute(RedisEnum.SESSION_USER_UC.getValue(), userVo);
		return "redirect:http://uc.54315.com";
	}
	
}
