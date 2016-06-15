package com.jointown.zy.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.dto.UcUserContactDto;
import com.jointown.zy.common.dto.UcUserScopeDto;
import com.jointown.zy.common.model.UcUserContact;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiWarehouseApplyService;
import com.jointown.zy.common.service.UcUserContactService;
import com.jointown.zy.common.service.UcUserDealInService;
import com.jointown.zy.common.vo.AreaVo;
import com.jointown.zy.common.vo.MessageVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * @ClassName: UcUserDealInController
 * @Description: 会员经营信息Controller
 * @Author: ldp
 * @Date: 2015年10月18日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/userDealIn")
public class UcUserDealInController extends UserBaseController {

	private static final Logger log = LoggerFactory.getLogger(UcUserDealInController.class);
	@Autowired
	private BusiWarehouseApplyService warehouseApplyService;
	@Autowired
	private UcUserDealInService ucUserDealInService;
	@Autowired
	private UcUserContactService ucUserContactService;
	/**
	 * @Description: 添加经营信息
	 * @Author: ldp
	 * @Date: 2015年10月18日
	 * @return
	 */
	@RequestMapping("/addUserDealInfo")
	public @ResponseBody String addUserDealInfo(@ModelAttribute UcUserScopeDto ucUserScopeDto){
		log.info("UcUserDealInController．addUserDealInfo:" + ucUserScopeDto);
		String result = null;
		try {
			result = ucUserDealInService.addUserDealInfo(ucUserScopeDto);
		} catch (Exception e) {
			log.error("UcUserDealInController．addUserDealInfo error",e);
		}
		return result;
	}
	
	/**
	 * @Description: 保存联系人信息
	 * @Author: ldp
	 * @Date: 2015年10月22日
	 * @param dto
	 * @return
	 */
	@RequestMapping(value="/saveContacterInfo")
	public @ResponseBody String saveContacterInfo(UcUserContactDto dto){
		String result = null;
		try {
			//判断联系人信息是否存在,如果已存在，先删除，然后再添加
			Subject subject = SecurityUtils.getSubject();
			UcUserVo ucUserVo = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			List<UcUserContact> ucContacts = ucUserContactService.selectUcUserContactsByUserId(ucUserVo.getUserId());
			if (null != ucUserVo || ucContacts.size() != 0) {
				ucUserContactService.delContacter(dto);
			}
			result = ucUserContactService.addContacter(dto);
		} catch (Exception e) {
			log.error("UcUserDealInController.saveContacterInfo error:", e);
		}
		return result;
	}
	
	/**
	 * @Description: 根据省份Code获取下面的城市
	 * @Author: ldp
	 * @Date: 2015年10月18日
	 * @param provinceCode
	 * @return
	 */
	@RequestMapping(value="/getCity")
	public @ResponseBody MessageVo getCity(@RequestParam("provinceCode") String provinceCode){
		MessageVo mvo = new MessageVo();
		if(StringUtils.isBlank(provinceCode)){
			mvo.setOk(false);
			mvo.addError("error01", "请选择省份!");
			return mvo;
		}
		List<AreaVo> cityList = new ArrayList<AreaVo>();
		AreaVo record = new AreaVo();
		record.setFirstcode(provinceCode);
		record.setType(String.valueOf(2));
		cityList = warehouseApplyService.findAreasByCondition(record);
		if(cityList.size() <= 0){
			mvo.setOk(false);
			return mvo;
		}
		mvo.setObj(cityList);
		mvo.setOk(true);
		return mvo;
	}
	
}
