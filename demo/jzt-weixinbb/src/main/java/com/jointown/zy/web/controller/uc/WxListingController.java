package com.jointown.zy.web.controller.uc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiListingDto;
import com.jointown.zy.common.dto.BusiWhlistSearchDto;
import com.jointown.zy.common.enums.BusiBillStateEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiWhlistStateEnum;
import com.jointown.zy.common.enums.ListingTimeLimitEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiListingVo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
* 项目名称：jzt-user-center  
* 类名称：BusiListingController  
* 类描述：  挂牌操作(查询列表、新增挂牌信息、修改挂牌)
* 创建人：Mr.songwei  
* 创建时间：2014-12-27 下午2:11:34  
* 修改人：  
* 修改时间：2014-12-27 下午2:11:34  
* 修改备注：  
* @version v1.0  
*
 */
@Controller	
@RequestMapping(value="/listing")
public class WxListingController extends WxUserBaseController{

	private static final Logger log = LoggerFactory.getLogger(WxListingController.class);
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	@Autowired
	private BusiListingService busiListingService;
	@Autowired
	private UcUserService ucUserService ;
	@Autowired
	private SftpConfigInfo sftpConfigInfo ;
	@Autowired
	public ThreadPoolTaskExecutor taskExecutor;	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	//调整到挂牌操作界面
	@RequestMapping(value="/add/{wlid}" ,method={RequestMethod.POST, RequestMethod.GET})
	public String addListingDetail(@PathVariable(value="wlid") String wlid,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("WxListingController.addListing controller");
		try{
			UcUserVo user =getUserInfo(request);
			if (user==null) {
				return "redirect:/login?go=/listing/add/"+wlid;
			}
			BusiWhlistVo vo = busiWhlistService.findBusiWhlistById(wlid,user.getUserId());
			if(vo == null){
				model.addAttribute("error", "仓单异常!");
				return "/busilisting-failure";
			}
			//仓单是否质押
			int wlState = vo.getWlstate();
			//已质押
			int wlStateNoPlaced = Integer.parseInt(BusiWhlistStateEnum.NOPLEDGED.getId());
			if(wlState == wlStateNoPlaced){
				model.addAttribute("error", "仓单已质押,不能挂牌");
				return "/busilisting-failure";
			}
			model.put("whlistvo", vo);
			model.put("daylimit", "30");  //默认挂牌天数 30天
			model.put("i320", sftpConfigInfo.getSftpXBigWidth());
			model.put("i640", sftpConfigInfo.getSftpXXBigWidth());
			model.put("limitMap", ListingTimeLimitEnum.toMap());
			model.put("billMap", BusiBillStateEnum.toMap());
			model.put("userinfo", user);
		}catch (NullPointerException e) {
			log.info("WxListingController.addListing NullPointerException");
		}
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/uc/warrant-compile";
	}
	
	/**
	 * 
	 * @Description: 新增挂牌
	 * @Author: wangjunhu
	 * @Date: 2015年4月25日
	 * @param busilistingDto
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/addBusiListing",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addBusiListing(@ModelAttribute BusiListingDto busiListingDto,HttpServletRequest request) {
		log.info("BusiListingController.addBusiListing controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				map.put("ok", false);
				map.put("url", "/login?go=/listing/add/"+busiListingDto.getWlid());
				log.error("请登录再操作！");
				return map;
			}else{
				// 判断用户是否认证
				if(user.getCertifyStatus()==0){
					map.put("ok", false);
					map.put("url","/info?main=cer");
					log.error("请实名认证！");
					return map;
				}
			}
			//验证参数是否正确
			String wlId = busiListingDto.getWlid();
			if(wlId==null||wlId.isEmpty()){
				map.put("ok", false);
				map.put("msg", "请求参数错误");
				log.error("请求参数错误");
				return map;
			}
			//验证仓单是否存在
			BusiWhlist busiWhlist = busiWhlistService.selectWhlistByWlId(wlId);
			if(busiWhlist==null){
				map.put("ok", false);
				map.put("msg", "仓单不存在！");
				log.error("仓单不存在！");
				return map;
			}
			//验证仓单是否质押
			int wlState = busiWhlist.getWlState();
			int wlStateNoPlaced = Integer.parseInt(BusiWhlistStateEnum.NOPLEDGED.getId());
			if(wlState==wlStateNoPlaced){
				map.put("ok", false);
				map.put("msg", "仓单已质押！");
				log.error("仓单已质押！");
				return map;
			}
			//验证仓单权限
			long userId = user.getUserId();
			long busiWhlistUserId = busiWhlist.getUserId();
			if(userId!=busiWhlistUserId){
				map.put("ok", false);
				map.put("msg", "仓单权限错误！");
				log.error("仓单权限错误！");
				return map;
			}
			//验证数据合法性
			double wlSurplus = busiWhlist.getWlSurplus();//仓单可挂数量
			double Listingamount = busiListingDto.getListingamount().doubleValue();//当前挂牌数量
			double minsales = busiListingDto.getMinsales().doubleValue();//最低起订数量
			if(wlSurplus<Listingamount){
				map.put("ok", false);
				map.put("msg", "挂牌数量不能大于可挂牌数量！");
				log.error("挂牌数量不能大于可挂牌数量！");
				return map;
			}
			if(Listingamount<minsales){
				map.put("ok", false);
				map.put("msg", "最低起订数量不能大于挂牌数量！");
				log.error("最低起订数量不能大于挂牌数量！");
				return map;
			}
			//新增挂牌
			Long breedId = busiWhlist.getBreedCode();
			
			busiListingDto.setWlid(wlId);
			busiListingDto.setUserid(userId);
			busiListingDto.setBreedid(breedId);
			
			busiListingService.addBusiListing(busiListingDto);
			
			map.put("ok", true);
			map.put("msg", "恭喜，您已成功提交挂牌申请！工作人员会在一个工作日内与您联系，告知相关事宜！");
			map.put("url","/listing/manager");
			
			//通知solr
			String listingId = busiListingDto.getBusiListing().getListingid();
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.ADD, SolrContentTypeEnum.LISTING, listingId);
			//发送短信
			String userMobile = user.getMobile();
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,
					GetMessageContext.getAddListingMsg(listingId),"新增挂牌[" + listingId + "]发送短信通知出错，错误信息是："));
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "新增挂牌失败！");
			map.remove("url");
			log.error("新增挂牌失败："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 根据用户id,查询所有他正在挂牌中、已下降的挂牌列表
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/historylisting",method=RequestMethod.GET)
	@ResponseBody
	public Page<BusiListingVo> historyListingManager(@RequestParam(value="pageNo") String pageNo,HttpServletRequest request) throws Exception {
		
		log.info("WxListingController.historyListingManager controller");
		Page<BusiListingVo> page = new Page<BusiListingVo>();
		////////////////////////////////////////////////////////////////
		if(pageNo!=null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		
		UcUserVo user = getUserInfo(request);
		//没有登录就跳转到登录界面
		if(user==null){
			return null;
		}
		String op = request.getParameter("op");
		String wlid = request.getParameter("wlid");
		String listingid = request.getParameter("listingid");
		
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", user.getUserId());
		params.put("op", op);
		params.put("wlid", wlid);
		params.put("listingid", listingid);
		
		page.setParams(params);
		
		List<BusiListingVo> busiListings = busiListingService.findHistoryListing(page);
		page.setResults(busiListings);
		return page;
	}
	
	
	/**
	 * 
	 * @Description: 我要挂牌-选择仓单-我的仓单列表
	 * @Author: Mr.song
	 * @Date: 2015年8月5日
	 * @param BusiWhlistSearchDto
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wxWhlistManage", method = RequestMethod.GET)
	public String wxWhlistManage(HttpServletRequest request,@ModelAttribute(value="wxWhlistSearchDto") BusiWhlistSearchDto wxWhlistSearchDto, ModelMap model) {
		//验证登陆
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return "redirect:/login?go=/listing/wxWhlistManage";
		}
		//我的仓单
		Page<BusiWhlistVo> wxWhlistPage = new Page<BusiWhlistVo>();
		wxWhlistPage.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		Map<String,Object> wxWhlistParams = new HashMap<String,Object>();
		Long userId = user.getUserId();
		wxWhlistParams.put("userId", userId);
		wxWhlistParams.put("busiWhlistSearchDto", wxWhlistSearchDto);
		wxWhlistPage.setParams(wxWhlistParams);
		List<BusiWhlistVo> wxWhlists = busiWhlistService.findBusiWhlistsByCondition(wxWhlistPage);
		wxWhlistPage.setResults(wxWhlists);
		
		model.put("page", wxWhlistPage);
		//仓库列表
		List<BusiWareHouseVo> wxWarehouses = busiWareHouseService.findBusiWareHousesByTree();
		
		model.put("wxWhlistSearchDto", wxWhlistSearchDto);
		//model.put("wxWhlists", wxWhlists);
		model.put("wxWarehouses", wxWarehouses);
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/uc/select_warrant";
	}
	
	//跳转到仓单挂牌编辑界面
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String updateListing(@PathVariable(value="id") String id,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("WxListingController.updateListing controller");
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return "redirect:/login?go=/listing/update/"+id;
		}
		BusiListingDetailVo vo = busiListingService.findSingleListingDetail(id);
		String content = vo.getContent();
		String _content = replaceImg(content);
		vo.setContent(_content);
		model.put("listingvo", vo);
		model.put("limitMap", ListingTimeLimitEnum.toMap());
		model.put("billMap", BusiBillStateEnum.toMap());
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/uc/mylistingedit";
	}
	
	//挂牌详情
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public String listingDetail(@PathVariable(value="id") String id,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("WxListingController.listingDetail controller");
		BusiListingDetailVo vo = busiListingService.findSingleListingDetail(id);
		UcUserVo user = getUserInfo(request);
		try{
			//双层验证挂牌是否失效，此处是点击挂牌详情时，检测挂牌是否失效；另外一种是定时器触发1小时轮训挂牌失效
			if(user==null){
				return "redirect:/login?go=/listing/detail/"+id;
			}
			if(vo.getListingflag()==Integer.valueOf(BusiListingFlagEnum.LISTING.getCode()) && TimeUtil.compare(vo.getExpiretime(),vo.getSysdate())<0){
				//修改挂牌状态
				BusiListing busiListing = busiListingService.findByPrimaryKey(id);
				busiListingService.updateListingFlagDisabled(busiListing);
				//发送邮件
				String username= user.getUserName();
				if(StringUtils.isNotEmpty(id)&&StringUtils.isNotEmpty(username)){
					//挂牌下架
					taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CANCER_LISTING, GetEmailContext.getListingOperatorEmail(), 
							GetEmailContext.getListingCancelEmailMsg(username, id, new Date()))
							.setLogErrorPrefiex("下架挂牌[" + id + "]发送邮件通知出错！错误是："));
				}
				//通知solr
				RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, id);
			}
		}catch(Exception e){log.info(e.getMessage());}
		String content = vo.getContent();
		String _content = replaceImg(content);
		vo.setContent(_content);
		model.put("listingvo", vo);
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/uc/mylistingdetail";
	}

	/**
	 * 
	 * @Description: 修改挂牌
	 * @Author: wangjunhu
	 * @Date: 2015年4月28日
	 * @param busilistingDto
	 * @return
	 */
	@RequestMapping(value="/updateBusiListing",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateBusiListing(@ModelAttribute BusiListingDto busiListingDto) {
		log.info("WxListingController.updateBusiListing controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证是否登陆
			UcUserVo user = getUserInfo();
			if(user==null){
				throw new Exception("请登录再操作！");
			}
			//验证参数是否正确
			String listingId = busiListingDto.getListingid();
			if(listingId==null||listingId.isEmpty()){
				throw new Exception("请求参数错误");
			}
			//验证挂牌是否存在
			BusiListing busiListing = busiListingService.findByPrimaryKey(listingId);
			if(busiListing==null){
				throw new Exception("挂牌不存在！");
			}
			//验证挂牌权限
			long userId = user.getUserId();
			long busiListingUserId = busiListing.getUserid();
			if(userId!=busiListingUserId){
				throw new Exception("挂牌权限错误！");
			}
			//验证仓单是否存在
			String wlId = busiListing.getWlid();
			BusiWhlist busiWhlist = busiWhlistService.selectWhlistByWlId(wlId);
			if(busiWhlist==null){
				throw new Exception("仓单不存在！");
			}
			//验证仓单是否质押
			int wlState = busiWhlist.getWlState();
			int wlStateNoPlaced = Integer.parseInt(BusiWhlistStateEnum.NOPLEDGED.getId());
			if(wlState==wlStateNoPlaced){
				throw new Exception("仓单已质押！");
			}
			//验证挂牌状态
			short listingFlag = busiListing.getListingflag();
			short BusiListingFlagWaiting = Short.parseShort(BusiListingFlagEnum.AUDIT_WAITING.getCode());
			short BusiListingFlagOk = Short.parseShort(BusiListingFlagEnum.LISTING.getCode());
			//待审核 或 挂牌中（更新限制中）
			if(listingFlag==BusiListingFlagWaiting || listingFlag==BusiListingFlagOk){
				busiListingDto.setListingamount(null);
				busiListingDto.setMinsales(null);
				//busiListingDto.setListingtimelimit(null);
				busiListingDto.setHasbill(null);
				busiListingDto.setSurpluses(null);
			}else{
				//验证数据合法性
				double wlSurplus = busiWhlist.getWlSurplus();//仓单可挂数量
				double Listingamount = busiListingDto.getListingamount().doubleValue();//当前挂牌数量
				double minsales = busiListingDto.getMinsales().doubleValue();//最低起订数量
				if(wlSurplus<Listingamount){
					throw new Exception("挂牌数量不能大于可挂牌数量！");
				}
				if(Listingamount<minsales){
					throw new Exception("最低起订数量不能大于挂牌数量！");
				}
				//更新可摘数量
				BigDecimal surpluses = busiListingDto.getListingamount();
				busiListingDto.setSurpluses(surpluses);
			}
			//修改挂牌
			Long breedId = busiWhlist.getBreedCode();
			busiListingDto.setWlid(wlId);
			busiListingDto.setUserid(userId);
			busiListingDto.setBreedid(breedId);
			busiListingDto.setListingflag(listingFlag);

			busiListingService.updateBusiListing(busiListingDto);
			
			map.put("ok", true);
			map.put("msg", "修改挂牌成功！");
			map.put("url","/listing/manager");
	
			//通知solr
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingId);
			//发送短信
			String userMobile = user.getMobile();
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,
			GetMessageContext.getAlterListingMsg(listingId),"修改挂牌[" + listingId + "]发送短信通知出错，错误信息是："));
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "修改挂牌失败！");
			map.remove("url");
			log.error("修改挂牌失败："+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 下架挂牌
	 * @Author: wangjunhu
	 * @Date: 2015年4月29日
	 * @param listingId
	 * @return
	 */
	@RequestMapping(value="/disabledListing",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateListingState(@RequestParam("listingId") String listingId,HttpServletRequest request){
		log.info("BusiListingController.disabledListing controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证是否登陆
			UcUserVo user =getUserInfo(request);
			if(user==null){
				throw new Exception("请登录再操作！");
			}
			//验证参数是否正确
			if(listingId==null||listingId.isEmpty()){
				throw new Exception("请求参数错误");
			}
			//验证挂牌是否存在
			BusiListing busiListing = busiListingService.findByPrimaryKey(listingId);
			if(busiListing==null){
				throw new Exception("挂牌不存在！");
			}
			//验证挂牌权限
			long userId = user.getUserId();
			long busiListingUserId = busiListing.getUserid();
			if(userId!=busiListingUserId){
				throw new Exception("挂牌权限错误！");
			}
			//下架挂牌
			busiListingService.updateListingFlagDisabled(busiListing);
			
			map.put("ok", true);
			map.put("msg", "下架成功！");
			
			//通知solr
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingId);
			//发送邮件
			String username= user.getUserName();
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_CANCER_LISTING, GetEmailContext.getListingOperatorEmail(), 
					GetEmailContext.getListingCancelEmailMsg(username, listingId, new Date()))
					.setLogErrorPrefiex("下架挂牌[" + listingId + "]发送邮件通知出错！错误信息是："));
			
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "下架挂牌失败！");
			log.error("下架挂牌失败："+e.getMessage());
		}
		return map;		
	}
	
	
	//跳转我的挂牌界面
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="",method=RequestMethod.GET)
	public String listingManager(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("WxListingController.listingManager controller");
		
		UcUserVo user = getUserInfo(request);
		//没有登录就跳转到登录界面
		if(user==null){
			return "redirect:/login?go=/listing";
		}
		// 判断用户是否认证
		if(user.getCertifyStatus()==0){
			return "redirect:/info?main=cer";
		}
		//移除 已完成 状态
		Map<String, String> flagMap = BusiListingFlagEnum.toMap();
		flagMap.remove(BusiListingFlagEnum.LISTING_SOLDOUT.getCode());
		
		//根据用户id,挂牌状态或者订单状态查询挂牌剩余量、挂牌被摘牌总量
		HashMap args = new HashMap();
		args.put("userid", (user!=null && user.getUserId()!=0L)?user.getUserId():0L);
		args.put("listingflag", BusiListingFlagEnum.LISTING.getCode());  //挂牌中
		args.put("orderstate", BusiOrderStateEnum.COMPLETED_ORDER.getCode());  //订单交易已完成
		HashMap map = busiListingService.getSurplusesAndVolume(args);
		
		model.put("map", map);
		model.put("flagMap", flagMap);
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "/uc/mylistings";
	}
	
	//ajax获取我的挂牌json
	@ResponseBody
	@RequestMapping(value="/getMyListings",method=RequestMethod.POST)
	public Page<BusiListingVo> getMyListings(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("WxListingController.listingManager controller");
		Page<BusiListingVo> page = new Page<BusiListingVo>();
		/**
		 * 前台参数统一获取
		 */
		String pageNo = request.getParameter("pageNo");
		String orderflag = request.getParameter("orderflag");
		String listingflag = request.getParameter("listingflag");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		String breedname = request.getParameter("breedname");
		
		////////////////////////////////////////////////////////////////
		if(pageNo!=null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		UcUserVo user = getUserInfo(request);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderflag", orderflag);
		params.put("listingflag", listingflag);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		params.put("breedname", breedname);
		params.put("userid", (user!=null && user.getUserId()!=0L)?user.getUserId():0L);
		page.setParams(params);
		
		List<BusiListingVo> busiListings = busiListingService.findListingsByCondition(page);
		page.setResults(busiListings);
		return page;
	}
	
	/**
	 * 查找富文本框中的临时图片路径，方便替换
	 * @param content
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private List<String> getImgListFromTextArea(String content) throws Exception{
		List<String> imgList = new ArrayList<String>();
		String regEx = "<img.*?src=\"(.*?)\"";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(content);
		while (mat.find()) {
			String path = mat.group(1);
			//判断是否是临时目录，若“是”就添加到list中
			if(path.startsWith(sftpConfigInfo.getSftpTempPath())){  
				imgList.add(path);
			}
		}
		return imgList;
	}
	
	/**
	 * 剔除字符串中包含<img>内容
	 * @param img
	 * @return String
	 */
	private String replaceImg(String htmlStr){
		String regEx = "<img.*?src=\"(.*?)\"";
		Pattern p_style = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        return htmlStr;
	}
}