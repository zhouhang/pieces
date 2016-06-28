package com.jointown.zy.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dto.BusiOrderDto;
import com.jointown.zy.common.enums.BusiAmtTypeEnum;
import com.jointown.zy.common.enums.BusiAppealTypeEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderAppealStateEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.ExamineStateEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiAppealService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiOrderDepositService;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.BeanToMapUtil;
import com.jointown.zy.common.util.EncryptUtil;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.BossOrderDepositVo;
import com.jointown.zy.common.vo.BusiAppealVo;
import com.jointown.zy.common.vo.BusiListingDetailVo;
import com.jointown.zy.common.vo.BusiOrderVo;
import com.jointown.zy.common.vo.BusiWareHouseVo;
import com.jointown.zy.common.vo.UcUserVo;

/**
 * 
 * 描述： 前台订单操作<br/>
 * 
 * 日期： 2015年1月4日<br/>
 * 
 * 作者： 赵航<br/>
 *
 * 版本： V1.0<br/>
 */
@Controller
@RequestMapping(value = "/order")
public class BusiOrderController extends UserBaseController {
	private static final Logger log = LoggerFactory
			.getLogger(BusiOrderController.class);

	@Autowired
	private BusiOrderService busiOrderService;
	
	@Autowired
	private BusiOrderDepositService busiOrderDepositService;

	@Autowired
	private BusiAppealService busiAppealService;

	@Autowired
	private BusiListingService busiListingService;
	
	@Autowired
	private BusiWareHouseService busiWareHouseService;
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	
	@Autowired
	private UcUserService ucUserService;
	
	@Autowired
	private SftpConfigInfo sftpConfigInfo;
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	private MessageConfigManage messageConfigManage;

	/**
	 * @Description: 由 购买记录 改为 买家订单
	 * @Author: 赵航
	 * @updater:guoyb
	 * @Date: 2015年4月9日
	 * @param request
	 * @param busiOrder
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listinfo")
	public String selectMyOrderList(HttpServletRequest request,
			@ModelAttribute("busiOrder") BusiOrderDto busiOrder, ModelMap model) {
		log.info("BusiOrderController.selectMyOrderList controller");
		if (busiOrder == null) {
			busiOrder = new BusiOrderDto();
		}
		String orderstate = request.getParameter("orderstate");
//		Subject subject = SecurityUtils.getSubject();
//		UcUserVo user = (UcUserVo) subject.getSession().getAttribute(
//				RedisEnum.SESSION_USER_UC.getValue());
		UcUserVo user = getUserInfo(request);

		busiOrder.setBuyer(user.getUserId());
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setPageNo(busiOrder.getPageNo());
		page.setPageSize(10);
		page.setParams(BeanToMapUtil.getOriginalParameters(busiOrder));

		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);

		page.setResults(results);
		
		model.addAttribute("busiOrder",busiOrder);
		model.addAttribute("stateMap", BusiOrderStateEnum.toMap());
		model.addAttribute("page", page);
		model.addAttribute("userinfo", user);

		if (orderstate == null || "".equals(orderstate)) {
			model.addAttribute("orderstate", "default");
		} else {
			model.addAttribute("orderstate", busiOrder.getOrderstate());
		}
		return "business/myOrder";
	}
	
	/**
	 * 暂时禁用删除订单功能
	 */
//	@RequestMapping(value = "/deleteOrder",method=RequestMethod.POST)
//	public @ResponseBody MessageVo deleteOrder(@RequestParam(value = "orderid", required = true) String orderid,HttpServletRequest request) {
//		log.info("BusiOrderController.deleteOrder controller");
//		MessageVo vo = new MessageVo();
//		vo.setOk(false);
//		try {
//			if(busiOrderService.deleteColosedOrderById(orderid,getUserInfo(request).getUserId())==1){
//				vo.setOk(true);
//			}
//		} catch (Exception e) {
//			log.error("BusiOrderController.deleteOrder controller error ::"+ e);
//			vo.addError(new ErrorMessage(ErrorRepository.UC_ORDER_DELETE_ERROR));
//		}
//		return vo;
//	}

	/**
	 * 
	 * @Description: 卖方订单
	 * @Author: wangjunhu
	 * @Date: 2015年4月13日
	 * @param request
	 * @param busiOrder
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSellOrderList", method = RequestMethod.GET)
	public String getSellOrderList(HttpServletRequest request,
			@ModelAttribute("busiOrder") BusiOrderDto busiOrder, ModelMap model)
			throws Exception {
		log.info("BusiOrderController.getSellOrderList controller");

//		UcUserVo userinfo = (UcUserVo) SecurityUtils.getSubject().getSession()
//				.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		UcUserVo userinfo = getUserInfo(request);
		Long userId = userinfo.getUserId();
		busiOrder.setUserid(userId);

		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		Integer pageNo = busiOrder.getPageNo();
		page.setPageNo(pageNo);
		page.setParams(BeanToMapUtil.getOriginalParameters(busiOrder));

		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);
		page.setResults(results);
		
		//用户信息
		model.put("userinfo", userinfo);

		model.addAttribute("busiOrderStateMap", BusiOrderStateEnum.toMap());
		model.addAttribute("page", page);
		return "business/sellOrderList";
	}

	@RequestMapping(value = "/getSellOrderDetail", method = RequestMethod.GET)
	public String getSellOrderDetail(HttpServletRequest request,
			@RequestParam("orderId") String orderId, ModelMap model)
			throws Exception {
		log.info("BusiOrderController.getSellOrderDetail controller");
		
		try {
			UcUserVo userinfo = getUserInfo(request);
			Long userId = userinfo.getUserId();
			model.put("userinfo", userinfo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userId);
			map.put("orderid", orderId);
			Page<BusiOrderVo> page = new Page<BusiOrderVo>();
			page.setParams(map);

			List<BusiOrderVo> results = busiOrderService
					.selectOrderListByPage(page);
			if (results.size() == 1) {
				BusiOrderVo busiOrder = results.get(0);
				//获取买方认证名称
				String buyerName = busiOrderService.selectCertifyName(busiOrder.getBuyerId());
				//获取账期订单 开始结束时间差 的天数
				String daydiff="";
				if(busiOrder.getOrderType()==2)
				{
				 daydiff=String.valueOf(TimeUtil.daysBetween(busiOrder.getStartTime(), busiOrder.getEndTime()));
				}
				String path = busiOrder.getPath();
				if (path != null && !path.isEmpty()) {
					path = path.substring(0, path.lastIndexOf(".")) + "_"
							+ sftpConfigInfo.getSftpMiddleWidth()
							+ path.substring(path.lastIndexOf("."));
					busiOrder.setPath(path);
				}
				if(BusiOrderStateEnum.CANCELED_ORDER.getCode().equals(busiOrder.getOrderstate().toString())){
					BusiOrderLog orderLog = busiOrderService.selectLastOrderLog(busiOrder.getOrderid());
					if(orderLog != null){
						busiOrder.setOrderCancelReasean(String.valueOf(orderLog.getOptype()));
					}
				}
				model.addAttribute("busiOrder", busiOrder);
				model.addAttribute("buyerName", buyerName);
				model.addAttribute("daydiff",daydiff);
			} else {
				return "error";
			}
			return "business/sellOrderDetail";
		} catch (Exception e) {
			log.error("BusiOrderController.getSellOrderDetail error is " + e.getMessage());
			return "error";
		}
	
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public @ResponseBody String cancelOrder(
			@RequestParam(value = "orderid", required = true) String orderid) {
		log.info("BusiOrderController.cancelOrder controller");
		JsonObject json = new JsonObject();
		if (StringUtils.isEmpty(orderid)) {
			json.addProperty("result", "error");
			json.addProperty("message", "订单编号不能为空！");
			return json.toString();
		}
		busiOrderService.cancelOrder(orderid);
		json.addProperty("result", "success");
		return json.toString();
	}

	/**
	 * 
	 * @Description: 查看卖家订单申诉详情
	 * @Author: wangjunhu
	 * @Date: 2015年4月9日
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/getOrderAppeal", method = RequestMethod.GET)
	public String getOrderAppeal(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId, ModelMap model) {
		log.info("BusiOrderController.getOrderAppeal controller");

		UcUserVo userinfo = (UcUserVo) SecurityUtils.getSubject().getSession()
				.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("orderId", orderId);

		BusiAppealVo busiAppeal = busiAppealService
				.findBusiAppealVoByOrderId(map);
		if (busiAppeal != null) {
			// 取消原因
			String appealType = BusiAppealTypeEnum.toMap().get(
					busiAppeal.getAppealtype());
			busiAppeal.setAppealtype(appealType);
			// 图片缩放
			String path = busiAppeal.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiAppeal.setPath(path);
			}
			model.put("busiAppeal", busiAppeal);
		}else{
			return "error";
		}

		return "business/applyReimburseState";
	}
	
	
	/**
	 * @Description: 查看买家订单申诉详情
	 * @Author: guoyb
	 * @Date: 2015年4月20日
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getBuyerOrderAppeal")
	public String getBuyerOrderAppeal(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId, ModelMap model) {
		log.info("BusiOrderController.getOrderAppeal controller");

		UcUserVo userinfo = (UcUserVo) SecurityUtils.getSubject().getSession()
				.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", userId);
		map.put("orderId", orderId);

		BusiAppealVo busiAppeal = busiAppealService
				.findBusiAppealVoByOrderId(map);
		if (busiAppeal != null) {
			// 取消原因
			String appealType = BusiAppealTypeEnum.toMap().get(
					busiAppeal.getAppealtype());
			busiAppeal.setAppealtype(appealType);
			// 图片缩放
			String path = busiAppeal.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiAppeal.setPath(path);
			}
			model.put("busiAppeal", busiAppeal);
		}else {
			return "error";
		}

		return "business/applyReimburseState";
	}

	/**
	 * @Description: 订单付款通用
	 * @Author: guoyb
	 * @Date: 2015年4月13日
	 * @param request 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "payOrder")
	public String payOrder(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		log.info("BusiOrderController.payOrder controller");
		//String path = "http://10.0.24.15/gateway";
		String path = MessageConstant.URL_PREFIX_PAY.getValue()+"/gateway";
		String userId = request.getParameter("userId");// 验证
		String orderId = request.getParameter("orderId");// 必要
		String amtType = request.getParameter("amtType");// 必要
		String sourceSys = "0";

		// 查询其他参数
		BusiOrder busiOrder = this.busiOrderService.findBusiOrderById(orderId);
		if (busiOrder == null) {
			return "error";
		}

		/** 验证数据 start */
		UcUserVo user = (UcUserVo) SecurityUtils.getSubject().getSession()
				.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		// 验证输入的买家id是否为空，买家id是否为session中的买家id，买家id是否等于查询出来的订单中的买家id
		if (StringUtils.isEmpty(userId)) {
			model.addAttribute("error", "对不起！页面参数参数错误，请联系客服！");
			return "error";
		}else if (!user.getUserId().toString().equals(userId)) {
			model.addAttribute("error", "对不起！登陆用户信息已经发生变化，请尝试重新打开原页面并刷新！");
			return "error";
		}else if (!busiOrder.getBuyer().toString().equals(userId)) {
			model.addAttribute("error", "对不起！您所选择的订单的买家ID已经发生变化，请联系客服！");
			return "error";
		}
		
		if (orderId == null || "".equals(orderId) || amtType == null
				|| "".equals(amtType)) {
			return "error";
		}

		String amount = "";
		/* 已下单 状态 start*/
		if (busiOrder.getOrderstate().toString()
				.equals(BusiOrderStateEnum.PlACED_ORDER.getCode())) {
			//挂牌相关验证
			BusiListing listing = this.busiListingService.findByPrimaryKey(busiOrder.getListingid());
			if(listing == null){
				return "error";
			}
			//如果挂牌不是挂牌中不允许继续操作
			if(!listing.getListingflag().toString().equals(BusiListingFlagEnum.LISTING.getCode())){
				model.addAttribute("error", "对不起！您所选择的订单的挂牌单状态已经发生变化，请联系客服！");
				return "error";
			}
			//判断的订单的购买数量 小于 挂牌的可摘数量
			if(busiOrder.getAmount().compareTo(listing.getSurpluses()) > 0){
				model.addAttribute("error", "对不起！您所选择的药材库存不足！");
				return "error";
			}
			BigDecimal Deposit=busiOrder.getDeposit();
			if(Deposit==null||"".equals(Deposit)){
				model.addAttribute("error", "对不起！订单保证金金额计算错误，无法进行操作！");
				return "error";
			}
			
			//if(busiOrder.getOrderType().toString().equals(BusiOrderTypeEnum.ORDINARY_ORDER.getCode())){
			//if(request.getParameter("orderType") != null && !"".equals(request.getParameter("orderType"))&&request.getParameter("orderType").equals(BusiOrderTypeEnum.FULLPAY_ORDER.getCode())){
			if(amtType.equals(BusiAmtTypeEnum.BUSI_PAY_ALL.getCode())){
				//设置amount为此订单一次性付全款金额
				amount=busiOrder.getTotalprice().setScale(2, BigDecimal.ROUND_DOWN).toString();			
			}
			else{
				//设置amount为保证金金额
				amount = busiOrder.getDeposit().setScale(2, BigDecimal.ROUND_DOWN).toString();
				
				if (!amtType.equals(BusiAmtTypeEnum.BUSI_PAY_DEPOSIT.getCode())) {
					return "error";
				}
			}
			
			
			
		}
		/* 已下单 状态 end*/
		/* 平台备货 状态 start*/
		//那么设计amount为 尾款(总价-保证金)
		else if (busiOrder.getOrderstate().toString()
				.equals(BusiOrderStateEnum.READY_WARE.getCode())) {
			amount = (busiOrder.getTotalprice()
					.subtract(busiOrder.getDeposit())).setScale(2,
					BigDecimal.ROUND_DOWN).toString();
			if (!amtType.equals(BusiAmtTypeEnum.BUSI_PAY_FINAL.getCode())) {
				return "error";
			}
		}
		/* 平台备货 状态 end*/
		// 其他状态不允许发起支付
		else {
			return "error";
		}
		
		String recieveId = busiOrder.getUserid().toString();
		BusiListingDetailVo busiListingDetailVo = this.busiListingService
				.findSingleListingDetail(busiOrder.getListingid());
		String orderTitle = busiListingDetailVo.getTitle();
		/** 验证数据 end */
		
		// 组织订单信息
		StringBuilder orderInfo = new StringBuilder();
		orderInfo.append("orderId=" + orderId).append("&amount=" + amount)
				.append("&amtType=" + amtType).append("&userId=" + userId)
				.append("&recieveId=" + recieveId)
				.append("&sourceSys=" + sourceSys)
				.append("&orderTitle=" + orderTitle);
		String signData = EncryptUtil.getMD5(orderInfo.toString()
				+ BankConfigConstant.TX_PAY_MD5KEY, "UTF-8");

		model.addAttribute("orderId", orderId);
		model.addAttribute("amount", amount);
		model.addAttribute("amtType", amtType);
		model.addAttribute("userId", userId);
		model.addAttribute("recieveId", recieveId);
		model.addAttribute("sourceSys", sourceSys);
		model.addAttribute("orderTitle", orderTitle);
		model.addAttribute("signData", signData);
		String payurl = "redirect:" + path;
		response.setCharacterEncoding("UTF-8");
		log.info("BusiOrderController.payOrder payUrl"+payurl);
		return payurl;
	}

	/**
	 * @Description: 进入申诉页面
	 * @Author: guoyb
	 * @Date: 2015年4月14日
	 * @param request
	 * @param response
	 * @param busiAppeal
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "applyReimburse")
	public String applyReimburse(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "orderId") String orderId, ModelMap model) {

		log.info("BusiOrderController.applyReimburse controller");

		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", userId);
		map.put("orderid", orderId);
		
		//查询订单详情，放入左边
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setParams(map);
		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);
		if (results.size() == 1) {
			BusiOrderVo busiOrder = results.get(0);
			String path = busiOrder.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiOrder.setPath(path);
			}
			//判断当前订单的申诉状态是否为无申诉状态，如果有申诉则返回错误页面
			if(busiOrder.getAppealState().toString().equals(BusiOrderAppealStateEnum.APPEALING.getCode())){
				return "error";
			}
			model.addAttribute("busiOrder", busiOrder);
			//获取当前订单的所在仓库名
			String wareHouseName="";
			BusiListing busiListing = this.busiListingService.findByPrimaryKey(busiOrder.getListingid());
			if (busiListing!=null) {
				BusiWhlist busiWhlist = this.busiWhlistService.selectWhlistByWlId(busiListing.getWlid());
				if(busiWhlist!=null){
					BusiWareHouseVo busiWareHouse = this.busiWareHouseService.findBusiWareHouseById(busiWhlist.getWareHouseId());
					if (busiWareHouse!=null) {
						wareHouseName = busiWareHouse.getWareHouseName(); 
					}
				}
			}
			model.addAttribute("wareHouseName", wareHouseName);
		} else {
			return "error";
		}

		return "/business/applyReimburse";
	}

	/**
	 * @Description: 图片从临时迁移到正式
	 * @Author: guoyb
	 * @Date: 2015年4月20日
	 * @param pic
	 * @return
	 */
	private String picFromTemToImg(String pic) {
		// 图片
		SFTPUtil sftp = SFTPUtil.getSingleton();
		Session session = null;
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("imgpath", pic);
			map.put("dataDir", sftpConfigInfo.getSftpDataDir());
			map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
			map.put("projectDir", sftpConfigInfo.getSftpProjectDir());
			map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDir());
			// 开始迭代移到正式图片目录下
			sftp.moveImg(session, map);
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				} else {
					log.info("BusiOrderController.insertApplyReimburse controller session is closed already");
				}
			} // 操作完毕关闭管道连接
			// 修改pic路径
			pic = pic.replaceAll(sftpConfigInfo.getSftpTempProjectDir(),
					sftpConfigInfo.getSftpProjectDir());
			pic = pic.replaceAll("http://"+sftpConfigInfo.getSftpIp()+"/",
					"");
			return pic;
		} catch (NumberFormatException e) {
			log.info("BusiOrderController.insertApplyReimburse controller error ::"
					+ e.getMessage());
		} catch (Exception e) {
			log.info("BusiOrderController.insertApplyReimburse controller error ::"
					+ e.getMessage());
		}finally {
			// 关闭管道连接
			try {
				ChannelSftp channel = sftp.getChannelSftp(session, "sftp"); // 获取管道符
				sftp.closeChannel(channel, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pic;
	}

	/**
	 * @Description: 插入申诉数据
	 * @Author: guoyb
	 * @Date: 2015年4月14日
	 * @param request
	 * @param response
	 * @param busiAppeal
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "insertApplyReimburse")
	public String insertApplyReimburse(HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute("busiAppeal") BusiAppeal busiAppeal, ModelMap model) {
		log.info("BusiOrderController.insertApplyReimburse controller");
		
		//查询当前的订单下是否已经又申述
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_UC.getValue());
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", userId);
		map.put("orderId", busiAppeal.getOrderId());
		BusiAppealVo vo = this.busiAppealService.findBusiAppealVoByOrderId(map);
		if (vo != null) {
			return "请不要重复提交申诉！";
		}
		
		String pic1 = request.getParameter("pic1");
		String pic2 = request.getParameter("pic2");
		String pic3 = request.getParameter("pic3");
		if (null != pic1 && !"".equals(pic1)) {
			pic1 = this.picFromTemToImg(pic1);
		}else {
			pic1="";
		}
		if (null != pic2 && !"".equals(pic2)) {
			pic2 = this.picFromTemToImg(pic2);
		}
		else {
			pic2="";
		}
		if (null != pic3 && !"".equals(pic3)) {
			pic3 = this.picFromTemToImg(pic3);
		}
		else {
			pic3="";
		}

		String evidencePic = pic1 + "," + pic2 + "," + pic3;
		// 去除后逗号和前逗号
		while(evidencePic.endsWith(",")) {
			evidencePic = evidencePic.substring(0,evidencePic.length()-1);
		}
		while(evidencePic.startsWith(",")) {
			evidencePic = evidencePic.substring(1,evidencePic.length());
		}
		busiAppeal.setEvidencePic(evidencePic);
		busiAppeal.setAppealor(userinfo.getUserId());
		busiAppeal.setExamineState(ExamineStateEnum.WAIT_AUDIT.getShortCode());
		this.busiAppealService.inserBusiAppeal(busiAppeal);
		return "y";
	}

	/**
	 * @Description: 上传图片(临时)
	 * @Author: guoyb
	 * @Date: 2015年4月14日
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadpic", method = RequestMethod.POST)
	public void uploadPic(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upload") MultipartFile file) throws IOException {
		String res = "";
		if (null == file) {
			res = "{status:{code:-1,desc:'请选择图片！'}}";
			return;
		}
		boolean isImage = UploadUtils.isImage(file.getInputStream());
		if (!isImage) {
			res = "{status:{code:-1,desc:''}}";
			return;
		}
		String dateDir = TimeUtil
				.getTimeShowByTimePartten(new Date(), "yyyyMM");
		String fileName = UploadUtils.generateFilename("jpg");
		SFTPUtil sftp = SFTPUtil.getSingleton();// sftp初始化
		ChannelSftp channel = null;// sftp管道声明
		Session session = null; // session声明
		try {
			session = sftp.openSession(sftpConfigInfo.getSftpIp(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session, "sftp"); // 获取管道符

			// 上传原图片到资源服务器临时目录下
			sftp.upload(channel, fileName, file.getInputStream(), dateDir,
					sftpConfigInfo.getSftpDataDir(),
					sftpConfigInfo.getSftpImagesDir(),
					sftpConfigInfo.getSftpTempProjectDir());

			// 上传缩略图到资源服务器临时目录下
//			int[] widths = { sftpConfigInfo.getSftpMiddleWidth(),
//					sftpConfigInfo.getSftpXBigWidth(),
//					sftpConfigInfo.getSftpXXBigWidth() };
//			for (int width : widths) {
//				String scaleFileName = fileName.substring(0,
//						fileName.lastIndexOf("."))
//						+ "_" + width + ".jpg";
//				OutputStream thumbOutstream = channel.put(scaleFileName);
//				ImageHelper.scaleImage(file.getInputStream(), thumbOutstream,
//						width, width);
//			}

			String newFileName = fileName.substring(0, fileName.lastIndexOf("."))+fileName.substring(fileName.lastIndexOf("."));
			res = "{status:{code:0,desc:''},con:{filename:'" + newFileName
					+ "',dateDir:'" + dateDir + "',path:'"
					+ sftpConfigInfo.getSftpTempPath() + "'}}";
		} catch (Exception e) {
			if (e instanceof SizeLimitExceededException) {
				res = "{status:{code:-1,desc:'图片大小超过5M'}}";
			}
			res = "{status:{code:-1,desc:'上传图片失败'" + e.getMessage() + "}}";
			log.debug("UploadController.uploadPic ::::" + res + e.getMessage());
		} finally {
			// 关闭管道连接
			try {
				sftp.closeChannel(channel, session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		response.getWriter().println(res);
	}

	/**
	 * 
	 * @Description: 更新订单单价
	 * @Author: wangjunhu
	 * @Date: 2015年4月9日
	 * @param request
	 * @param orderId
	 * @param orderUnitPrice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateOrderUnitPrice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrderUnitPrice(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId,
			@RequestParam(value = "orderUnitPrice") BigDecimal orderUnitPrice,
			ModelMap model) {
		log.info("BusiOrderController.updateOrderUnitPrice controller");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//验证是否登陆
			UcUserVo userinfo = (UcUserVo) SecurityUtils.getSubject().getSession()
					.getAttribute(RedisEnum.SESSION_USER_UC.getValue());
			if(userinfo==null){
				throw new Exception("请登录再操作！");
			}
			//验证参数是否正确
			if(orderId==null || orderId.isEmpty() || orderUnitPrice==null){
				throw new Exception("请求参数错误！");
			}
			//验证订单是否存在
			BusiOrder busiOrder = busiOrderService.findBusiOrderById(orderId);
			if(busiOrder==null){
				throw new Exception("订单不存在！");
			}
			//订单权限验证
			long userinfoId = userinfo.getUserId();
			long userorderId = busiOrder.getUserid();
			if (userinfoId != userorderId) {
				throw new Exception("订单权限错误！");
			}
			//订单类型验证
			Integer orderState = busiOrder.getOrderstate();
			if (orderState != 0) {
				throw new Exception("订单状态错误！");
			}
			//单价大小验证
//			BigDecimal unitPrice = busiOrder.getUnitprice();
			if (orderUnitPrice.compareTo(new BigDecimal(0)) <= 0) {
				throw new Exception("单价不可修改为0！");
			}
			//更新单价、总价
			BigDecimal orderTotalPrice = busiOrder.getAmount().multiply(
					orderUnitPrice);
			BusiOrder record = new BusiOrder();
			record.setOrderid(orderId);
			record.setUnitprice(orderUnitPrice);
			record.setTotalprice(orderTotalPrice);
			record.setUpdatetime(new Date());
			int ok = busiOrderService.updateByIdSelective(record);
			if (ok == 1) {
				map.put("ok", true);
				map.put("msg", "修改成功！");
				map.put("orderTotalPrice",
						orderTotalPrice.setScale(2, BigDecimal.ROUND_DOWN));
				//发送邮件
				UcUser ucUser = ucUserService.findUcUserById(busiOrder.getBuyer().intValue());
				threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(),GetMessageContext.getAlterOrderUnitPriceMsg(busiOrder.getOrderid()),"修改订单单价[" + orderId + "]发送短信通知出错，错误信息是："));
			}else{
				map.put("ok", false);
				map.put("msg", "修改失败！");
			}
		} catch (Exception e) {
			map.put("ok", false);
			map.put("msg", "修改失败！");
			log.error("订单修改单价失败："+e.getMessage());
		}

		return map;
	}

	/**
	 * @Description: 订单详情页
	 * @Author: guoyb
	 * @Date: 2015年4月18日
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/buyOrderDetail")
	public String buyOrderDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@Param("orderId") String orderId) throws ParseException{
		log.info("BusiOrderController.payDetail controller");

		UcUserVo userinfo = getUserInfo(request);
		Long userId = userinfo.getUserId();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", userId);
		map.put("orderid", orderId);
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setParams(map);
		model.addAttribute("userinfo", userinfo);
		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);
		// 如果数据正常
		if (results.size() == 1) {
			BusiOrderVo busiOrderVo = results.get(0);
			//获取卖方认证名称
			String sellerName = busiOrderService.selectCertifyName(busiOrderVo.getUserId());
			//获取账期订单 开始结束时间差 的天数
			String daydiffp="";
			if(busiOrderVo.getOrderType()==2)
			{
			 daydiffp=String.valueOf(TimeUtil.daysBetween(busiOrderVo.getStartTime(), busiOrderVo.getEndTime()));
			}
			String path = busiOrderVo.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiOrderVo.setPath(path);
			}
			model.addAttribute("order", busiOrderVo);
			model.addAttribute("sellerName", sellerName);
			model.addAttribute("daydiffp",daydiffp);

			if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.PlACED_ORDER.getCode())) {
				//判断订单是否已经下架
				BusiListing listing = this.busiListingService.findByPrimaryKey(busiOrderVo.getListingid());
				//挂牌状态正常，跳转到摘牌详情页
				if (listing.getListingflag().toString().equals(BusiListingFlagEnum.LISTING.getCode())) {
					//判断的订单的购买数量 小于 挂牌的可摘数量
					if(busiOrderVo.getAmount().compareTo(listing.getSurpluses()) > 0){
						model.addAttribute("error", "对不起！您所选择的药材库存不足！");
						return "error";
					}
					return "/business/pickCard";
				//否则跳到取消的订单的详情页
				}else {
					return "/business/buy-orderClosed";
				}
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.PREPAID_DEPOSIT.getCode())) {
				return "/business/payBail";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.READY_WARE.getCode())) {
				model.addAttribute("duration",Integer.parseInt(BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo()));
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/business/payGoods";
			}else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.PAYED_ORDER.getCode())) {
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/business/payComplete";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.COMPLETED_ORDER.getCode())) {
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/business/payComplete";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.CANCELED_ORDER.getCode())) {
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				//查询退款金额
				try {
					BossOrderDepositVo orderDepositInfo = busiOrderDepositService.getCanceledOrderDepositInfo(orderId);
					busiOrderVo.setRemitPayment(orderDepositInfo!=null?orderDepositInfo.getBuyerAmount():null);
				} catch (Exception e) {
					log.error("error occured in busiOrderDepositService.getCanceledOrderDepositInfo ,"+e);
				}
				BusiOrderLog orderLog = busiOrderService.selectLastOrderLog(orderId);
				if(orderLog != null){
					busiOrderVo.setOrderCancelReasean(String.valueOf(orderLog.getOptype()));
				}
				return "/business/buy-orderCancel";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.CLOSED_ORDER.getCode())){
				return "/business/buy-orderClosed";
			} else {
				return "error";
			}
		} else {
			return "error";
		}
	}
}