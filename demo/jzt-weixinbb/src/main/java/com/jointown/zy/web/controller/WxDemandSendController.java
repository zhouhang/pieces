package com.jointown.zy.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.constant.WxConstant;
import com.jointown.zy.common.dto.WxDemandDto;
import com.jointown.zy.common.enums.WxSupplyResourceEnum;
import com.jointown.zy.common.enums.WxSupplyStateEnum;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.exception.WxErrorException;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.WxDemand;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.WxDemandService;
import com.jointown.zy.common.service.WxSupplyService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.PropertiesUtils;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.common.vo.WxDemandVo;
import com.jointown.zy.common.vo.WxSupplyBreedVo;

/**
 * 
 * @ClassName: WxDemandSendController
 * @Description: 微信发布求购信息Controller
 * @Author: wangjunhu
 * @Date: 2015年5月15日
 * @Version: 1.0
 */
@Controller
@RequestMapping(value="/wxDemandSend")
public class WxDemandSendController extends WxUserBaseController {

	private final static Logger logger = LoggerFactory.getLogger(WxDemandSendController.class);
	
	@Autowired
	private WxSupplyService wxSupplyService;
	
	@Autowired
	private WxDemandService wxDemandService;
	
	@Autowired
	public ThreadPoolTaskExecutor threadPoolTaskExecutor;	
	
	@Autowired
	public MessageConfigManage messageConfigManage;
	
	/**
	 * 
	 * @Description: 查询微信求购信息，ID查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWxDemand", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxDemand(@RequestParam(value="demandId") Long demandId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(demandId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxDemand wxDemand = new WxDemand();
			wxDemand.setDemandId(demandId);
			Long userId = user.getUserId();
			wxDemand.setUserId(userId);
			WxDemandVo wxDemandVo = wxDemandService.findWxDemandById(wxDemand);
			if(wxDemandVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			map.put("ok", true);
			map.put("msg", "加载成功！");
			map.put("wxDemand", wxDemandVo);
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "加载失败！");
			}
			logger.error("WxDemandSendController.getWxDemand："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 查询更多的微信求购信息，分页查询
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param pageNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMoreWxDemands", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getMoreWxDemands(@RequestParam(value="pageNo",required=true,defaultValue="1") Integer pageNo) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(pageNo==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//分页条件查询
			Page<WxDemand> page = new Page<WxDemand>();
			page.setPageNo(pageNo);
			page.setPageSize(5);
			
			Map<String, Object> params = new HashMap<String, Object>();
			WxDemand wxDemand = new WxDemand();
			Long userId = user.getUserId();
			wxDemand.setUserId(userId);
			params.put("wxDemand", wxDemand);
			page.setParams(params);

			List<WxDemandVo> wxDemands = wxDemandService.findWxDemandsByPage(page);
			
			map.put("ok", true);
			map.put("wxDemands", wxDemands);
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "加载失败！");
			}
			logger.error("WxDemandSendController.getMoreWxDemands："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 新增微信求购信息
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param wxDemandDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addWxDemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addWxDemand(@ModelAttribute WxDemandDto wxDemandDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//新增信息
			Long userId = user.getUserId();
			wxDemandDto.setUserId(userId);
			String userName = user.getUserName();
			wxDemandDto.setUserName(userName);
			String userMobile = user.getMobile();
			wxDemandDto.setUserMobile(userMobile);
			String sypplyResourceWx = WxSupplyResourceEnum.WX.getCode();
			wxDemandDto.setApplyResource(sypplyResourceWx);
			
			wxDemandService.addWxDemand(wxDemandDto);
			
			map.put("ok", true);
			map.put("msg", "发布成功！");
			
			//短信通知运营审核信息
			//Map<String, Object> properties = PropertiesUtils.getProperties("com/jointown/zy/common/properties/config.properties");
			//String mobileNo = (String) properties.get("kefu.sms.moblie");
			String mobileNo = SpringUtil.getConfigProperties("kefu.sms.moblie");
			threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo, GetMessageContext.getWxSupplyOrDemandMsg(userName, false)));
			
			//成功后发送邮件
			String subject = WxConstant.EMAIL_SUBJECT_DEMAND;
			String toEmail = GetEmailContext.getWxEmail();
			String emailMsg = WxConstant.getAddDemandEmailContent(userName, userMobile);
			threadPoolTaskExecutor.execute(new EmailTaskSend(subject, toEmail, emailMsg));
		}catch(Exception e){
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "发布失败！");
			}
			logger.error("WxDemandSendController.addWxDemand："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 修改微信求购信息
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param wxDemandDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateWxDemand",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateWxDemand(@ModelAttribute WxDemandDto wxDemandDto) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			Long demandId = wxDemandDto.getDemandId();
			if(demandId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxDemand wxDemand = wxDemandDto.getWxDemand();
			Long userId = user.getUserId();
			wxDemand.setUserId(userId);
			WxDemandVo wxDemandVo = wxDemandService.findWxDemandById(wxDemand);
			if(wxDemandVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//验证信息状态是否正确
			short wxDemandStatus = wxDemandVo.getStatus();
			short wxDemandStateWaiting = Short.parseShort(WxSupplyStateEnum.WATING.getCode());
			//待审核
			if(wxDemandStatus==wxDemandStateWaiting){
				throw new WxErrorException(ErrorRepository.WX_WAITING_RECORD_NO_UPDATE);
			}
			//修改信息
			wxDemand.setStatus(wxDemandStateWaiting);
			
			wxDemandService.updateWxDemandById(wxDemand);
			
			map.put("ok", true);
			map.put("msg", "更新成功！");
			
			//短信通知运营审核信息
			//Map<String, Object> properties = PropertiesUtils.getProperties("com/jointown/zy/common/properties/config.properties");
			//String mobileNo = (String) properties.get("kefu.sms.moblie");
			String mobileNo = SpringUtil.getConfigProperties("kefu.sms.moblie");
			String userName = user.getUserName();
			threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo, GetMessageContext.getWxSupplyOrDemandMsg(userName, false)));
			
			//成功后发送邮件
			String subject = WxConstant.EMAIL_SUBJECT_DEMAND;
			String toEmail = GetEmailContext.getWxEmail();
			String emailMsg = WxConstant.getUpdateDemandEmailContent(wxDemandVo.getBreedName(), userName, user.getMobile());
			threadPoolTaskExecutor.execute(new EmailTaskSend(subject, toEmail, emailMsg));
		}catch(Exception e){
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "更新失败！");
			}
			logger.error("WxDemandSendController.updateWxDemand："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 撤销微信求购信息，ID撤销
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param demandId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteWxDemand",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteWxDemand(@RequestParam(value="demandId") Long demandId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(demandId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxDemand wxDemand = new WxDemand();
			wxDemand.setDemandId(demandId);
			Long userId = user.getUserId();
			wxDemand.setUserId(userId);
			WxDemandVo wxDemandVo = wxDemandService.findWxDemandById(wxDemand);
			if(wxDemandVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//撤销信息
			short wxDemandStateDelete = Short.parseShort(WxSupplyStateEnum.DELETE.getCode());
			wxDemand.setStatus(wxDemandStateDelete);
			
			wxDemandService.updateWxDemandById(wxDemand);
			
			map.put("ok", true);
			map.put("msg", "撤销成功！");
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "撤销失败！");
			}
			logger.error("WxDemandSendController.deleteWxDemand："+msg);
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 刷新微信求购信息，ID刷新
	 * @Author: wangjunhu
	 * @Date: 2015年5月18日
	 * @param demandId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/refreshWxDemand",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> refreshWxDemand(@RequestParam(value="demandId") Long demandId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(demandId==null){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//验证信息是否存在
			WxDemand wxDemand = new WxDemand();
			wxDemand.setDemandId(demandId);
			Long userId = user.getUserId();
			wxDemand.setUserId(userId);
			WxDemandVo wxDemandVo = wxDemandService.findWxDemandById(wxDemand);
			if(wxDemandVo==null){
				throw new WxErrorException(ErrorRepository.WX_NO_RECORD);
			}
			//刷新信息
			wxDemandService.updateWxDemandById(wxDemand);
			
			map.put("ok", true);
			map.put("msg", "刷新成功！");
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
				map.put("msg", "刷新失败！");
			}
			logger.error("WxSupplySendController.refreshWxDemand："+msg);
		}
		return map;
	}
	
	/**
	 * 品种名称联想求购信息，品种规格
	 * @param breedName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getWxDemandByBreedName",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWxDemandByBreedName(@RequestParam("breedName") String breedName) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证权限
			UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
			if(user==null){
				throw new WxErrorException(ErrorRepository.WX_NO_LOGIN);
			}
			//验证参数
			if(breedName==null || breedName.isEmpty()){
				throw new WxErrorException(ErrorRepository.WX_PARAM_ERROR);
			}
			//查询信息
			WxSupplyBreedVo wxSupply = wxSupplyService.findWxSupplyByBreedName(breedName);
			if(wxSupply==null){
				map.put("ok", false);
			}else{
				map.put("ok", true);
				map.put("obj", wxSupply);
			}
		} catch (Exception e) {
			map.put("ok", false);
			String msg = e.getMessage();
			if(e instanceof WxErrorException){
				msg = ((WxErrorException) e).getErrorMessage().getMessage();
				map.put("msg", msg);
			}else{
//				map.put("msg", "查询失败！");
			}
			logger.error("WxSupplySendController.getWxDemandByBreedName："+msg);
		}
		return map;
	}
}
