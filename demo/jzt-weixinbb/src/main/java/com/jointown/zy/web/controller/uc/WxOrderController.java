package com.jointown.zy.web.controller.uc;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
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
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jointown.zy.common.constant.ConfigConstant;
import com.jointown.zy.common.dto.BusiOrderDto;
import com.jointown.zy.common.enums.BusiAppealTypeEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.ExamineStateEnum;
import com.jointown.zy.common.enums.WxErrorTypeEnum;
import com.jointown.zy.common.enums.WxOrderStateEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;
import com.jointown.zy.common.model.BusiQualitypic;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.redis.RedisEnum;
import com.jointown.zy.common.service.BusiAppealService;
import com.jointown.zy.common.service.BusiListingService;
import com.jointown.zy.common.service.BusiOrderDepositService;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.BusiQualityPicService;
import com.jointown.zy.common.service.BusiWareHouseService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.util.BeanToMapUtil;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.SFTPUtil;
import com.jointown.zy.common.util.SftpConfigInfo;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.util.image.UploadUtils;
import com.jointown.zy.common.vo.BossOrderDepositVo;
import com.jointown.zy.common.vo.BusiAppealVo;
import com.jointown.zy.common.vo.BusiOrderVo;
import com.jointown.zy.common.vo.UcUserVo;
import com.jointown.zy.web.controller.WxUserBaseController;

/**
 * 订单操作
 * @date 2015-8-17
 */
@Controller
@RequestMapping(value = "/order")
public class WxOrderController extends WxUserBaseController {
	private static final Logger log = LoggerFactory.getLogger(WxOrderController.class);

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
	private BusiQualityPicService busiQualityPicService;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	//已下单订单72小时过期天数
	private Integer afterDays = Integer.parseInt(SpringUtil.getConfigProperties("jointown.busi.order.expire.days.after"));;
	
	/**
	 * @Description: 跳转到买家订单列表页面
	 * @Author: guoyb
	 * @Date: 2015年8月17日
	 * @param request 
	 * @param orderstate 
	 * @param map 
	 * @return
	 */
	@RequestMapping(value="order_buyer")
	public String buyerOrderDetail(HttpServletRequest request,@ModelAttribute("busiOrder") BusiOrderDto busiOrder,ModelMap map){
		log.info("BusiOrderController.buyerOrderDetail controller");
		if (busiOrder == null) {
			busiOrder = new BusiOrderDto();
		}
		UcUserVo user = getUserInfo(request);
		//未登陆用户跳转到登陆界面
		if(user==null){
			return "redirect:"+SpringUtil.getConfigProperties("jointown.wx.resource.path.www")+"/login?go=/order/order_buyer";
		}
		String orderstate = request.getParameter("orderstate");
		
		if (orderstate==null||"".equals(orderstate)) {
			orderstate="default";
		}
		
		busiOrder.setBuyer(user.getUserId());
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setPageNo(busiOrder.getPageNo());
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		page.setParams(BeanToMapUtil.getOriginalParameters(busiOrder));
		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);
		page.setResults(results);
		
		//判断是否还有更多数据
		int currentRec = ((page.getPageNo()-1)*ConfigConstant.WX_USER_CENTER_PAGE_SIZE)+page.getResults().size();
		if(currentRec < page.getTotalRecord()){
			map.put("ismore", 1);
		}else {
			map.put("ismore", 0);
		}

		map.put("busiOrder",busiOrder);
		map.put("stateMap", BusiOrderStateEnum.toMap());
		map.put("page", page);
		map.put("userinfo", user);
		map.put("orderstate", orderstate);
		map.put("afterdays", afterDays);
		map.put("deposit_delay",BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo());
		
		request.getSession(true).setAttribute("my_order_flag", "0"); //0表示买家订单
		return "order-buyer";
	}

	/**
	 * @Description: 买方订单列表  ajax获取信息
	 * @Author: guoyb
	 * @Date: 2015年8月17日
	 * @param request 
	 * @param busiOrder 
	 * @return
	 */
	@RequestMapping(value = "/order_buyer_info")
	@ResponseBody
	public String selectMyOrderList(HttpServletRequest request,
			@ModelAttribute("busiOrder") BusiOrderDto busiOrder) {
		log.info("BusiOrderController.selectMyOrderList controller");
		if (busiOrder == null) {
			busiOrder = new BusiOrderDto();
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		String orderstate = request.getParameter("orderstate");
		
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return "redirect:/login?go=/order/myorder";
		}
		busiOrder.setBuyer(user.getUserId());
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setPageNo(busiOrder.getPageNo()+1);
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		page.setParams(BeanToMapUtil.getOriginalParameters(busiOrder));
		List<BusiOrderVo> results = busiOrderService
				.selectOrderListByPage(page);
		page.setResults(results);

		resMap.put("busiOrder",busiOrder);
		resMap.put("stateMap", BusiOrderStateEnum.toMap());
		resMap.put("page", page);
		resMap.put("userinfo", user);

		if (orderstate == null || "".equals(orderstate)) {
			resMap.put("orderstate", "default");
		} else {
			resMap.put("orderstate", busiOrder.getOrderstate());
		}
		
		//判断是否还有更多数据
		int currentRec = ((page.getPageNo()-1)*ConfigConstant.WX_USER_CENTER_PAGE_SIZE)+page.getResults().size();
		if(currentRec<page.getTotalRecord()){
			resMap.put("ismore", 1);
		}else {
			resMap.put("ismore", 0);
		}

		Gson gson = GsonFactory.createGson("yyyy/MM/dd hh:mm:ss");
		String resGson = gson.toJson(resMap);

		return resGson;
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
	 * @Description: 跳转到卖方订单
	 * @param request
	 * @param busiOrder
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myorder", method = RequestMethod.GET)
	public String myOrders(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("WxOrderController.myOrders controller");
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return "redirect:/login?go=/order/myorder";
		}
		// 判断用户是否认证
		if(user.getCertifyStatus()==0){
			return "redirect:/info?main=cer";
		}
		//request.getSession(true).setAttribute("my_order_flag", "0"); //0表示买家订单
		return "uc/my-order";
	}

	/**
	 * @Description: 跳转到卖方订单
	 * @param request
	 * @param busiOrder
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sellorders", method = RequestMethod.GET)
	public String sellorders(HttpServletRequest request, ModelMap model) throws Exception {
		log.info("WxOrderController.sellorders controller");
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return "redirect:/login?go=/order/sellorders";
		}
		request.getSession(true).setAttribute("my_order_flag", "1"); //1表示卖家订单
		model.addAttribute("busiOrderStateMap", BusiOrderStateEnum.toMap());
		//add by Mr.song 2015.9.17
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "uc/seller-order";
	}
	
	/**
	 * 根据仓单id获取仓单图片列表
	 * @param wlid 仓单id
	 * @return List<BusiQualitypic>
	 * @throws Exception
	 */
	@RequestMapping(value = "/getWlidPic/{wlid}", method = RequestMethod.GET)
	@ResponseBody
	public List<BusiQualitypic> getWlidPic(@PathVariable(value="wlid") String wlid) throws Exception {
		log.info("WxOrderController.getWlidPic controller");
		if(StringUtils.isBlank(wlid)){
			return null;
		}
		List<BusiQualitypic> list = busiQualityPicService.selectAllPicByWLID(wlid);
		return list;
	}
	
	/**
	 * @Description: 获取卖方订单json
	 * @param request
	 * @param busiOrder
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getsellorders", method = RequestMethod.POST)
	@ResponseBody
	public Page<BusiOrderVo> getSellorders(HttpServletRequest request,@ModelAttribute("busiOrder") BusiOrderDto busiOrder, ModelMap model) throws Exception {
		log.info("WxOrderController.getSellorders controller");
		UcUserVo user = getUserInfo(request);
		if (user==null) {
			return null;
		}
		Long userId = user.getUserId();
		busiOrder.setUserid(userId);

		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		Integer pageNo = busiOrder.getPageNo();
		page.setPageNo(pageNo);
		page.setParams(BeanToMapUtil.getOriginalParameters(busiOrder));
		page.setPageSize(ConfigConstant.WX_USER_CENTER_PAGE_SIZE);
		List<BusiOrderVo> results = busiOrderService.selectOrderListByPage(page);
		page.setResults(results);
		
		return page;
	}

	/**
	 * 获取卖家订单详情
	 * @param request
	 * @param orderId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSellOrderDetail/{orderId}", method = RequestMethod.GET)
	public String getSellOrderDetail(HttpServletRequest request,@PathVariable(value="orderId") String orderId, ModelMap model) throws Exception {
		log.info("WxOrderController.getSellOrderDetail controller");
		if (orderId==null || "".equals(orderId)) {
			model.addAttribute("error", WxErrorTypeEnum.ORDER_IS_NULL.getErrorName());
			return "error";
		}
		UcUserVo userinfo = getUserInfo(request);
		if (userinfo==null) {
			return "redirect:/login?go=/order/getSellOrderDetail/"+orderId;
		}
		Long userId = userinfo.getUserId();
		model.put("userinfo", userinfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userId);
		map.put("orderid", orderId);
		Page<BusiOrderVo> page = new Page<BusiOrderVo>();
		page.setParams(map);

		List<BusiOrderVo> results = busiOrderService.selectOrderListByPage(page);
		if (results.size() == 1) {
			BusiOrderVo busiOrder = results.get(0);
			//获取买方认证名称
			String buyerName = busiOrderService.selectCertifyName(busiOrder.getBuyerId());
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
		} else {
			model.addAttribute("error", WxErrorTypeEnum.ORDER_ERROR.getErrorName());
			return "error";
		}
		model.addAttribute("busiOrderStateMap", WxOrderStateEnum.toMap());
		model.put("timestamp", getTimestamp()); //生成时间戳，方便资源文件实时无缓存获取
		return "uc/seller-order-detail";
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
	@ResponseBody
	public BusiAppealVo getOrderAppeal(HttpServletRequest request,@RequestParam(value = "orderId") String orderId, ModelMap model) {
		log.info("WxOrderController.getOrderAppeal controller");
		UcUserVo userinfo = getUserInfo(request);
		if (userinfo==null) {
			return null;
		}
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("orderId", orderId);

		BusiAppealVo busiAppeal = busiAppealService.findBusiAppealVoByOrderId(map);
		if(busiAppeal == null){
			busiAppeal = new BusiAppealVo();
		}else{
			// 取消原因
			String appealType = BusiAppealTypeEnum.toMap().get(busiAppeal.getAppealtype());
			busiAppeal.setAppealtype(appealType);
			// 图片缩放
			String path = busiAppeal.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiAppeal.setPath(path);
			}
		}
		return busiAppeal;
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
	@RequestMapping(value = "/getBuyerOrderAppeal", method = RequestMethod.GET)
	@ResponseBody
	public BusiAppealVo getBuyerOrderAppeal(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId) {
		log.info("WxOrderController.getOrderAppeal controller");
		UcUserVo userinfo = getUserInfo(request);
		if (userinfo==null) {
			return null;
		}
		Long userId = userinfo.getUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer", userId);
		map.put("orderId", orderId);

		BusiAppealVo busiAppeal = busiAppealService
				.findBusiAppealVoByOrderId(map);
		if(busiAppeal == null){
			busiAppeal = new BusiAppealVo();
		}else{
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
		}
		return busiAppeal;
	}
	
	
	/**
	 * @Description: 更新订单单价
	 */
	@RequestMapping(value = "/updateOrderUnitPrice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrderUnitPrice(HttpServletRequest request,
			@RequestParam(value = "orderId") String orderId,
			@RequestParam(value = "orderUnitPrice") BigDecimal orderUnitPrice,
			ModelMap model) {
		log.info("WxOrderController.updateOrderUnitPrice controller");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UcUserVo userinfo = getUserInfo(request);
			if (userinfo==null) {
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
			BigDecimal unitPrice = busiOrder.getUnitprice();
			if (unitPrice.compareTo(orderUnitPrice) == -1) {
				throw new Exception("单价不可改高！");
			}
			//更新单价、总价
			BigDecimal orderTotalPrice = busiOrder.getAmount().multiply(orderUnitPrice);
			BusiOrder record = new BusiOrder();
			record.setOrderid(orderId);
			record.setUnitprice(orderUnitPrice);
			record.setTotalprice(orderTotalPrice);
			record.setUpdatetime(new Date());
			int ok = busiOrderService.updateByIdSelective(record);
			if (ok == 1) {
				map.put("ok", true);
				map.put("msg", "修改成功！");
				map.put("unitprice",orderUnitPrice);
				map.put("orderTotalPrice",orderTotalPrice.setScale(2, BigDecimal.ROUND_DOWN));
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
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));
			channel = sftp.getChannelSftp(session, "sftp"); // 获取管道符

			// 上传原图片到资源服务器临时目录下
			sftp.upload(channel, fileName, file.getInputStream(), dateDir,
					sftpConfigInfo.getSftpDataDirWx(),
					sftpConfigInfo.getSftpImagesDir(),
					sftpConfigInfo.getSftpTempProjectDirWx());

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
					+ sftpConfigInfo.getSftpTempPathWx() + "'}}";
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
		log.info("WxOrderController.insertApplyReimburse controller");
		
		//查询当前的订单下是否已经又申述
		UcUserVo userinfo = (UcUserVo)SecurityUtils.getSubject().getSession().getAttribute(RedisEnum.SESSION_USER_WX.getValue());
		if (userinfo==null) {
			return "redirect:/login?go=/order/myorder";
		}
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
			session = sftp.openSession(sftpConfigInfo.getSftpIpWx(),
					sftpConfigInfo.getSftpUserName(),
					sftpConfigInfo.getSftpPassword(),
					Integer.parseInt(sftpConfigInfo.getSftpPort()));
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("imgpath", pic);
			map.put("dataDir", sftpConfigInfo.getSftpDataDirWx());
			map.put("imagesDir", sftpConfigInfo.getSftpImagesDir());
			map.put("projectDir", sftpConfigInfo.getSftpProjectDirWx());
			map.put("tempProjectDir", sftpConfigInfo.getSftpTempProjectDirWx());
			// 开始迭代移到正式图片目录下
			sftp.moveImg(session, map);
			if (session != null) {
				if (session.isConnected()) {
					session.disconnect();
				} else {
					log.info("WxOrderController.insertApplyReimburse==>picFromTemToImg controller session is closed already");
				}
			} // 操作完毕关闭管道连接
			// 修改pic路径
			pic = pic.replaceAll(sftpConfigInfo.getSftpTempProjectDirWx(),
					sftpConfigInfo.getSftpProjectDirWx());
			pic = pic.replaceAll("http://"+sftpConfigInfo.getSftpIpWx()+"/",
					"");
			return pic;
		} catch (NumberFormatException e) {
			log.info("WxOrderController.insertApplyReimburse==>picFromTemToImg controller error ::"
					+ e.getMessage());
		} catch (Exception e) {
			log.info("WxOrderController.insertApplyReimburse==>picFromTemToImg controller error ::"
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
	 * @Description: 订单详情页
	 * @Author: guoyb
	 * @Date: 2015年4月18日
	 * @return
	 */
	@RequestMapping(value = "/getBuyOrderDetail")
	public String buyOrderDetail(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@Param("orderId") String orderId) {
		log.info("BusiOrderController.payDetail controller");

		UcUserVo userinfo = getUserInfo(request);
		if (userinfo==null) {
			return "redirect:/login?go=/order/myorder";
		}
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
			String path = busiOrderVo.getPath();
			if (path != null && !path.isEmpty()) {
				path = path.substring(0, path.lastIndexOf(".")) + "_"
						+ sftpConfigInfo.getSftpMiddleWidth()
						+ path.substring(path.lastIndexOf("."));
				busiOrderVo.setPath(path);
			}
			model.addAttribute("order", busiOrderVo);
			model.addAttribute("sellerName", sellerName);
			model.put("afterdays", afterDays);
			model.put("deposit_delay",BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo());

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
					return "/uc/buyorder-placeOrder";
				//否则跳到取消的订单的详情页
				}else {
					return "/uc/buyorder-orderclosed";
				}
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.PREPAID_DEPOSIT.getCode())) {
				return "/uc/buyorder-paiddeposit";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.READY_WARE.getCode())) {
				model.addAttribute("duration",Integer.parseInt(BusiParamEnum.BUSI_DEPOSIT_DELAY.getInfo()));
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/uc/buyorder-readyware";
			}else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.PAYED_ORDER.getCode())) {
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/uc/buyorder-paycomplete";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.COMPLETED_ORDER.getCode())) {
				busiOrderVo.setFinalPayment(busiOrderVo.getVolume().multiply(busiOrderVo.getUnitprice()).subtract(busiOrderVo.getDeposit()));
				return "/uc/buyorder-paycomplete";
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
				BusiOrderLog orderLog = busiOrderService.selectLastOrderLog(busiOrderVo.getOrderid());
				if(orderLog != null){
					busiOrderVo.setOrderCancelReasean(String.valueOf(orderLog.getOptype()));
				}
				return "/uc/buyorder-ordercancel";
			} else if (busiOrderVo.getOrderstate().toString()
					.equals(BusiOrderStateEnum.CLOSED_ORDER.getCode())){
				return "/uc/buyorder-orderclosed";
			} else {
				return "error";
			}
			
		} else {
			return "error";
		}
	}
}