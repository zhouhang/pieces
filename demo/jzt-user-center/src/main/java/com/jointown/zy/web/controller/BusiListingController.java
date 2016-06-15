package com.jointown.zy.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.Session;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiListingSalesmanDao;
import com.jointown.zy.common.dto.BusiListingDto;
import com.jointown.zy.common.enums.BusiBillStateEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiWhlistStateEnum;
import com.jointown.zy.common.enums.ListingTimeLimitEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.exception.CommonErrorException;
import com.jointown.zy.common.exception.ErrorMessage;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.Breed;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiListingSalesman;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiListingVo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.BusiWhlistVo;
import com.jointown.zy.common.vo.UcUserVo;

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
public class BusiListingController {
	

	private static final Logger log = LoggerFactory.getLogger(BusiListingController.class);
	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
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
	public MessageConfigManage messageConfigManage;
	@Autowired
	private BusiListingSalesmanDao busiListingSalesmanDao;
	@Autowired
	private BossUserDao bossUserDao;
	@Autowired
	private BreedDao breedDao;
	
	public UcUserVo getUserInfo(HttpServletRequest... requests){
    	//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		if(userinfo!=null && (!ArrayUtils.isEmpty(requests))){
			userinfo.setUrl(requests[0].getRequestURI());
		}
		return userinfo;
    }
	
	
	//我要挂牌第一步-引导挂牌  add by Mr.songwei 挂牌第一步
	@RequestMapping(value="/first",method=RequestMethod.GET)
	public String listing_first(HttpServletRequest request,ModelMap model) throws Exception {
		log.info("IWillListingController.listing_first controller");
		/**********************匹配菜单*******************/
		//header登录名称使用
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		//left菜单选中使用
		String url=request.getRequestURI();
		userinfo.setUrl(url);
		model.addAttribute("userinfo", userinfo);
		/**********************匹配菜单*******************/
		return "firstListing";
	}
	
	/**
	 * @author chengchang
	 * @description 查询出仓单的类容，显示在页面上，然后进行点击其中的一行的数据，返回数据在页面上
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/queryWl",method=RequestMethod.POST)
	@ResponseBody
	public Map queryWareHouse() throws Exception{
		log.info("IWillListingController.queryWareHouse controller");
		//1.获取userid(用户登录的id)
		//获取用户的userID
		Long userId=0l;
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		userId= (user!=null && user.getUserId()!=0L)?user.getUserId():0L;
		
		//2.查询出用户的货物所在的仓单
		List <BusiWareHouseVo>  listBusiWareHouseVo= busiWareHouseService.selectBusiWareHousesByUserId(userId);
		Map resultMap = new HashMap();
		resultMap.put("listBusiWareHouseVo", listBusiWareHouseVo);
		return resultMap;
	}
	
	//仓单查询  alert by Mr.songwei 2014.1.17 11:40 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/queryWList",method=RequestMethod.POST)
	@ResponseBody
	public Map  queryWList(@RequestParam(value="pageNo", required = false) String pageNo,HttpServletRequest request, ModelMap model) throws Exception{
		log.info("IWillListingController.queryWList controller");
		String selectWareHouse = request.getParameter("selectWareHouse");
		String datetimepicker1 = request.getParameter("datetimepicker1");
		String datetimepicker2 = request.getParameter("datetimepicker2");
		String breedName = request.getParameter("breedName");
		
		Page<BusiWhlistVo> page = new Page<BusiWhlistVo>();
		if(pageNo!=null && !"".equals(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(ConfigConstant.USER_CENTER_PAGE_SIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		params.put("userId", (user!=null && user.getUserId()!=0L)?user.getUserId():0L);
		params.put("selectWareHouse", selectWareHouse);
		params.put("datetimepicker1", datetimepicker1);
		params.put("datetimepicker2", datetimepicker2);
		params.put("breedName", breedName);
		page.setParams(params);
		//查询仓单结果
		List listWhlist=busiWhlistService.selectBusiWhlistMohu(page);
		page.setResults(listWhlist);
		Map reMap = new HashMap();
		reMap.put("page", page);
		return reMap;
	} 
	
	//跳转到仓单挂牌创建界面
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addListingDetail(@RequestParam(value="wlid", required = false) String wlid,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("BusiListingController.addListing controller");
		try{
			UcUserVo user =getUserInfo(request);
			BusiWhlistVo vo = busiWhlistService.findBusiWhlistById(wlid,user.getUserId());
			//added by biran 20150810  查询仓单信息时，增加校验，防止后台报错
			if(vo==null){
				model.put("error", ErrorRepository.UC_WL_CHANGED.getMessage(wlid));
				return "error";
			}
			//add end
			//仓单是否质押
			int wlState = vo.getWlstate();
			//已质押
			int wlStateNoPlaced = Integer.parseInt(BusiWhlistStateEnum.NOPLEDGED.getId());
			if(wlState == wlStateNoPlaced){
				model.put("error", ErrorRepository.UC_WL_NOPLEDGED.getMessage(wlid));
				return "error";
			}
			model.put("whlistvo", vo);
			model.put("daylimit", "30");
			model.put("i320", sftpConfigInfo.getSftpXBigWidth());
			model.put("i640", sftpConfigInfo.getSftpXXBigWidth());
			model.put("limitMap", ListingTimeLimitEnum.toMap());
			model.put("billMap", BusiBillStateEnum.toMap());
			model.put("userinfo", user);
		}catch (NullPointerException e) {
			log.info("BusiListingController.addListing NullPointerException");
		}
		return "iWillListing";
	}
	
	//跳转到仓单挂牌编辑界面
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateListing(@RequestParam(value="id", required = false) String id,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("BusiListingController.updateListing controller");
		UcUserVo user = getUserInfo(request);
		BusiListingDetailVo vo = busiListingService.findSingleListingDetail(id);
		model.put("listingvo", vo);
		model.put("i320", sftpConfigInfo.getSftpXBigWidth());
		model.put("i640", sftpConfigInfo.getSftpXXBigWidth());
		model.put("limitMap", ListingTimeLimitEnum.toMap());
		model.put("billMap", BusiBillStateEnum.toMap());
		model.put("userinfo", user);
		return "updateBusiListing";
	}
	
	//挂牌详情
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String listingDetail(@RequestParam(value="id", required = false) String id,ModelMap model,HttpServletRequest request) throws Exception {
		log.info("BusiListingController.listingDetail controller");
		BusiListingDetailVo vo = busiListingService.findSingleListingDetail(id);
		Subject subject = SecurityUtils.getSubject();
		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		try{
			
			//String x=String.valueOf(vo.getWlsurplus());
			//String wlid=vo.getWlid();
			//双层验证挂牌是否失效，此处是点击挂牌详情时，检测挂牌是否失效；另外一种是定时器触发1小时轮训挂牌失效
			if(user!=null){
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
//					int num= busiListingService.updateListingState(id);
//					if(num >0){
//						HashMap<String, Object> map = new HashMap<String, Object>();
//						map.put("wlsurplus", x);
//						map.put("wlid", wlid);
//						busiWhlistService.updateWLsurplus(map);
//					}
				}
			}
		}catch(Exception e){log.info(e.getMessage());}
		model.put("i320", sftpConfigInfo.getSftpXBigWidth());
		model.put("i640", sftpConfigInfo.getSftpXXBigWidth());
		model.put("listingvo", vo);
		model.put("userinfo", user);

		return "busiListingDetail";
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
				throw new Exception("请登录再操作！");
			}
			//验证参数是否正确
			String wlId = busiListingDto.getWlid();
			if(wlId==null||wlId.isEmpty()){
				throw new Exception("请求参数错误");
			}
			//验证仓单是否存在
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
			//验证仓单权限
			long userId = user.getUserId();
			long busiWhlistUserId = busiWhlist.getUserId();
			if(userId!=busiWhlistUserId){
				throw new Exception("仓单权限错误！");
			}
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
			//验证药材详情是否上传图片
			String content = busiListingDto.getContent();
			List<String> imglist= getImgListFromTextArea(content);
			if(imglist!=null){
				Session session = null;
				try {
					//上传图片到资源服务器
					SFTPUtil sftp = SFTPUtil.getSingleton();
					session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
					for(String imgpath:imglist){
						HashMap<String,Object> sftpMap = new HashMap<String,Object>();
						sftpMap.put("imgpath", imgpath);
						sftpMap.put("dataDir", sftpConfigInfo.getSftpDataDir());
						sftpMap.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
						sftpMap.put("projectDir", sftpConfigInfo.getSftpProjectDir());
						sftpMap.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
						//开始迭代移到正式图片目录下
						sftp.moveImg(session, sftpMap);
					}
					content = content.replaceAll(sftpConfigInfo.getSftpTempPath(), sftpConfigInfo.getSftpPath());
					busiListingDto.setContent(content);
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				} finally{
					if (session != null) {
						if (session.isConnected()) {
							session.disconnect();
						}
					}
				}
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
			
			map.put("userinfo", user);
			//通知solr
			String listingId = busiListingDto.getBusiListing().getListingid();
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.ADD, SolrContentTypeEnum.LISTING, listingId);
			/*----delete by biran 20151019 删除发短信的逻辑，移植到service层
			//发送短信
			String userMobile = user.getMobile();
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,
					GetMessageContext.getAddListingMsg(listingId),"新增挂牌[" + listingId + "]发送短信通知出错，错误信息是："));
			--*/
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "新增挂牌失败！");
			log.error("新增挂牌失败："+e.getMessage());
		}
		return map;
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
		log.info("BusiListingController.updateBusiListing controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证是否登陆
			UcUserVo user = getUserInfo();
			if(user==null){
				throw new CommonErrorException(new ErrorMessage("", "请登录再操作！"));
			}
			//验证参数是否正确
			String listingId = busiListingDto.getListingid();
			if(listingId==null||listingId.isEmpty()){
				throw new CommonErrorException(new ErrorMessage("", "请求参数错误！"));
			}
			//验证挂牌是否存在
			BusiListing busiListing = busiListingService.findByPrimaryKey(listingId);
			if(busiListing==null){
				throw new CommonErrorException(new ErrorMessage("", "挂牌不存在！"));
			}
			if(busiListing.getListingflag().toString()
					.equals(BusiListingFlagEnum.LISTING_CANCEL.getCode())){
				throw new CommonErrorException(new ErrorMessage("", "挂牌已取消！"));
			}
			//验证挂牌权限
			long userId = user.getUserId();
			long busiListingUserId = busiListing.getUserid();
			if(userId!=busiListingUserId){
				throw new CommonErrorException(new ErrorMessage("", "挂牌权限错误！"));
			}
			//验证仓单是否存在
			String wlId = busiListing.getWlid();
			BusiWhlist busiWhlist = busiWhlistService.selectWhlistByWlId(wlId);
			if(busiWhlist==null){
				throw new CommonErrorException(new ErrorMessage("", "仓单不存在！"));
			}
			//验证仓单是否质押
			int wlState = busiWhlist.getWlState();
			int wlStateNoPlaced = Integer.parseInt(BusiWhlistStateEnum.NOPLEDGED.getId());
			if(wlState==wlStateNoPlaced){
				throw new CommonErrorException(new ErrorMessage("", "仓单已质押！"));
			}
			//验证药材详情是否上传图片
			String content = busiListingDto.getContent();
			List<String> imglist= getImgListFromTextArea(content);
			if(imglist!=null){
				Session session = null;
				try {
					//上传图片到资源服务器
					SFTPUtil sftp = SFTPUtil.getSingleton();
					session = sftp.openSession(sftpConfigInfo.getSftpIp(), sftpConfigInfo.getSftpUserName(), sftpConfigInfo.getSftpPassword(),Integer.parseInt(sftpConfigInfo.getSftpPort()));
					for(String imgpath:imglist){
						HashMap<String,Object> sftpMap = new HashMap<String,Object>();
						sftpMap.put("imgpath", imgpath);
						sftpMap.put("dataDir", sftpConfigInfo.getSftpDataDir());
						sftpMap.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
						sftpMap.put("projectDir", sftpConfigInfo.getSftpProjectDir());
						sftpMap.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
						//开始迭代移到正式图片目录下
						sftp.moveImg(session, sftpMap);
					}
					content = content.replaceAll(sftpConfigInfo.getSftpTempPath(), sftpConfigInfo.getSftpPath());
					busiListingDto.setContent(content);
				} catch (Exception e) {
					throw new Exception(e.getMessage());
				} finally{
					if (session != null) {
						if (session.isConnected()) {
							session.disconnect();
						}
					}
				}
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
					throw new CommonErrorException(new ErrorMessage("", "挂牌数量不能大于可挂牌数量！"));
				}
				if(Listingamount<minsales){
					throw new CommonErrorException(new ErrorMessage("", "最低起订数量不能大于挂牌数量！"));
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
			map.put("msg", "修改挂牌成功！请耐心等待工作人员审核！");
			map.put("url","/listing/manager");
	
			//通知solr
			RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingId);
			//发送短信
			String userMobile = user.getMobile();
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(userMobile,
			GetMessageContext.getAlterListingMsg(listingId),"修改挂牌[" + listingId + "]发送短信通知出错，错误信息是："));
		}catch (CommonErrorException e) {
			map.put("ok", false);
			map.put("msg", "修改挂牌失败!"+e.getMessage());
			log.error("修改挂牌失败："+e);
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "修改挂牌失败！");
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
	public Map<String,Object> updateListingState(@RequestParam("listingId") String listingId){
		log.info("BusiListingController.disabledListing controller");
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			//验证是否登陆
			Subject subject = SecurityUtils.getSubject();
			UcUserVo user = (UcUserVo) subject.getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
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
			
			//added by biran 20151019 增加给业务员发送通知短信
			UcUser sellers = ucUserService.findUcUserById(busiListing.getUserid().intValue());//卖家信息
			if(sellers!=null ){
				BusiListingSalesman salesman= busiListingSalesmanDao.findBusiListingSalesmanByListingId(listingId);
				if(salesman != null){//有挂牌业务员信息时
					BossUser salesManInfo = bossUserDao.getBossUserByUserId(salesman.getSalesmanId().longValue());//卖家关联的业务员信息
					if(salesManInfo.getMobile()!=null && !salesManInfo.getMobile().equals("")){//业务员有电话
						Breed breed=breedDao.selectByPrimaryKey(busiListing.getBreedid());//品种信息
						String realName=ucUserService.getCertifyNameByUserId(busiListing.getUserid());//认证名称
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(salesManInfo.getMobile(),
								GetMessageContext.getDisabledListingMsg4SalesMan(listingId,realName+"("+sellers.getUserName()+")",breed.getBreedName()),
								"挂牌[" + busiListing.getListingid() + "]下架发送短信通知出错，错误信息是："));
					}
						
				}
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "下架挂牌失败！");
			log.error("下架挂牌失败："+e.getMessage());
		}
		return map;		
	}
	
	//我的挂牌查询
	@RequestMapping(value="/manager",method=RequestMethod.GET)
	public String myWhListManager(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("BusiListingController.myWhListManager controller");
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
		page.setPageSize(ConfigConstant.USER_CENTER_PAGE_SIZE);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderflag", orderflag);
		params.put("listingflag", listingflag);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		params.put("breedname", breedname);
		UcUserVo user = getUserInfo(request);
		params.put("userid", (user!=null && user.getUserId()!=0L)?user.getUserId():0L);
		page.setParams(params);
		
		List<BusiListingVo> busiListings = busiListingService.findListingsByCondition(page);
		page.setResults(busiListings);
		
		//移除 已完成 状态
		Map<String, String> flagMap = BusiListingFlagEnum.toMap();
		flagMap.remove(BusiListingFlagEnum.LISTING_SOLDOUT.getCode());
		
		model.put("flagMap", flagMap);
		model.put("listingflag", listingflag);
		model.put("page", page);
		model.put("userinfo", user);
		return "busiListingManager";
	}
	
	/**
	 * 查找富文本框中的临时图片路径，方便替换
	 * @param content
	 * @return
	 * @throws Exception 
	 */
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
}