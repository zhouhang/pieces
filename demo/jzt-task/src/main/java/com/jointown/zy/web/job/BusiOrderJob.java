package com.jointown.zy.web.job;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiOrderDepositDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderSalesman;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.util.SysLogUtil;
import com.jointown.zy.common.wms.WmsRestHttpUtil;
import com.jointown.zy.web.listener.SpringBeanFactroy;

/**
 * 
 * @ClassName: BusiOrderJob
 * @Description: 支付——>交易——>WMS同步任务
 * @Author: wangjunhu
 * @Date: 2015年4月16日
 * @Version: 1.0
 */
public class BusiOrderJob extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(BusiOrderJob.class);
	
	private static BusiOrderJob busiOrderJob;
	
	private BusiOrderService busiOrderService;
	
	private BusiWhlistService busiWhlistService;
	
	private WmsApiService wmsApiService;
	
	private UcUserService ucUserService;
	
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private MessageConfigManage messageConfigManage;
	
	private BusiOrderDepositDao busiOrderDepositDao;
	
	//added by biran 20151020 为卖家，买家业务员发送短信
	private BusiOrderSalesmanDao busiOrderSalesmanDao;
	
	private BossUserDao bossUserDao;
	
	private BreedDao breedDao;
	
	
	//查询支付记录URL
	private static String queryPayResultUrl;
	//处理支付记录URL
	private static String donePayResultUrl;
	
	public static BusiOrderJob getInstance(){
		if(busiOrderJob == null){
			synchronized (BusiOrderJob.class) {
				if(busiOrderJob == null){
					busiOrderJob = new BusiOrderJob();
				}
			}
		}
		return busiOrderJob;
	}
	
	public BusiOrderJob() {
		super();
		busiOrderService = (BusiOrderService) SpringBeanFactroy.getBean(BusiOrderService.class);
		busiWhlistService = (BusiWhlistService) SpringBeanFactroy.getBean(BusiWhlistService.class);
		wmsApiService = (WmsApiService) SpringBeanFactroy.getBean(WmsApiService.class);
		ucUserService = (UcUserService) SpringBeanFactroy.getBean(UcUserService.class);
		threadPoolTaskExecutor = (ThreadPoolTaskExecutor) SpringBeanFactroy.getBean(ThreadPoolTaskExecutor.class);
		messageConfigManage = (MessageConfigManage) SpringBeanFactroy.getBean(MessageConfigManage.class);
		
		busiOrderDepositDao =(BusiOrderDepositDao) SpringBeanFactroy.getBean(BusiOrderDepositDao.class);
		//added by biran 20151020 为卖家，买家业务员发送短信
		busiOrderSalesmanDao=(BusiOrderSalesmanDao) SpringBeanFactroy.getBean(BusiOrderSalesmanDao.class);
		bossUserDao=(BossUserDao) SpringBeanFactroy.getBean(BossUserDao.class);
		breedDao=(BreedDao) SpringBeanFactroy.getBean(BreedDao.class);
		
		queryPayResultUrl = SpringUtil.getConfigProperties("busiOrderJob.queryPayResultUrl");
		donePayResultUrl =  SpringUtil.getConfigProperties("busiOrderJob.donePayResultUrl");
	}

	@Override
	public void run() {
		log.debug("开始执行更新订单支付信息定时任务！");
		try {
			JsonObject reqParams = new JsonObject();
			//系统标识
			reqParams.addProperty("sourceSys", 0);
			//记录行数
			reqParams.addProperty("num", 2);
			//加密请求
			String encParams = PayUtil.getDesEncryptData(reqParams.toString());
			String resResults = WmsRestHttpUtil.wmsRestPost(queryPayResultUrl, encParams);
			if("102".equals(resResults) || "101".equals(resResults)){
				return;
			}
			resResults = PayUtil.getDesDecryptData(resResults);
			//遍历信息
			Gson gson = new Gson();
			JsonArray jsonArray = gson.fromJson(resResults, JsonArray.class);
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = gson.fromJson(jsonElement, JsonObject.class);
				//支付ID
				String resultId = jsonObject.get("resultId").getAsString();
				//订单号
				String orderId = jsonObject.get("orderId").getAsString();
				//付款标识
				int sourceSys = jsonObject.get("sourceSys").getAsInt();
				//金额类型
				int amtType = jsonObject.get("amtType").getAsInt();
				//付款金额
				BigDecimal amount = jsonObject.get("amount").getAsBigDecimal();
				//支付时间
				long createTime = jsonObject.get("createTime").getAsLong();
				//订单条件判断
				if(sourceSys==0){//交易系统
					//查询订单
					BusiOrder busiOrder = busiOrderService.findBusiOrderById(orderId);
					if(busiOrder!=null){					
						try {
							JsonObject jsonObjectBusiness = new JsonObject();
							jsonObjectBusiness.addProperty("orderId", orderId);
							jsonObjectBusiness.addProperty("amtType", amtType);
							jsonObjectBusiness.addProperty("amount", amount);
							SysLogUtil.logForBusiness(jsonObjectBusiness.toString());
							
							//更新订单支付信息，更新支付记录状态
							boolean busiListingRabbitmq = busiOrderService.updateBusiOrderJob(busiOrder, donePayResultUrl, resultId, amtType, amount);
							
							//通知solr挂牌可摘数量已更新
							String listingId = busiOrder.getListingid();
							if(busiListingRabbitmq){
								RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listingId);
							}
							UcUser ucBuyer = ucUserService.findUcUserById(busiOrder.getBuyer().intValue());
							//通知WMS
							try {
								String wlId = busiOrder.getWlid();
								double freezeCount = busiOrder.getAmount().doubleValue();
								double splitCount = busiOrder.getVolume().doubleValue();
								String buyer = ucBuyer.getUserName();
								//支付保证金
								if(amtType==AmtTypeEnum.PAY_DEPOSIT.getCode()){
									JsonObject jsonObjectWms = new JsonObject();
									jsonObjectWms.addProperty("wlId", wlId);
									jsonObjectWms.addProperty("freezeCount", freezeCount);
									jsonObjectWms.addProperty("orderId", orderId);
									SysLogUtil.logForWMS(jsonObjectWms.toString());
									//通知冻结挂牌
									boolean ok = wmsApiService.applyWlFreeze(wlId, freezeCount, orderId);
									if(ok){
										log.debug("通知WMS冻结仓单成功！");
									}else{
										log.error("通知WMS冻结仓单失败！");
									}
								}
								//支付尾款
								else if(amtType==AmtTypeEnum.PAY_BALANCE_PAYMENT.getCode()){
									//普通订单
									if(busiOrder.getOrderType()!=null && busiOrder.getOrderType().compareTo(Short.valueOf(BusiOrderTypeEnum.ORDINARY_ORDER.getCode()))==0){
										reqParams.addProperty("buyer", buyer);
										JsonObject jsonObjectWms = new JsonObject();
										jsonObjectWms.addProperty("wlId", wlId);
										jsonObjectWms.addProperty("freezeCount", splitCount);
										jsonObjectWms.addProperty("orderId", orderId);
										//通知分割仓单
										SysLogUtil.logForWMS(jsonObjectWms.toString());
										boolean ok = wmsApiService.applyWlSplit(wlId, splitCount, orderId, buyer);
										if(ok){
											log.debug("通知WMS分割仓单成功！");
										}else{
											log.error("通知WMS分割仓单失败！");
										}
									}
								}
								//全款支付
								else if (amtType==AmtTypeEnum.PAY_FULL_PAYMENT.getCode()){
									JsonObject jsonObjectWms = new JsonObject();
									jsonObjectWms.addProperty("wlId", wlId);
									jsonObjectWms.addProperty("freezeCount", freezeCount);
									jsonObjectWms.addProperty("orderId", orderId);
									SysLogUtil.logForWMS(jsonObjectWms.toString());
									//通知冻结挂牌
									boolean ok = wmsApiService.applyWlFreeze(wlId, freezeCount, orderId);
									if(ok){
										log.debug("通知WMS冻结仓单成功！");
									}else{
										log.error("通知WMS冻结仓单失败！");
									}
								}
								
							} catch (Exception e) {
								SysLogUtil.logForWMS(e.getMessage(),true);
							}
							//短信、邮件提示信息
							try {
								//卖方用户ID
								int userId = busiOrder.getUserid().intValue();
								UcUser ucUser = ucUserService.findUcUserById(userId);
								String mobileNo = ucUser.getMobile();
								Date date = new Date(createTime);
								if(amtType==AmtTypeEnum.PAY_DEPOSIT.getCode()){//支付保证金
									//短信提示卖方
									threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo,GetMessageContext.getPayDepositMsg(orderId)));
									//邮件提示运营
									threadPoolTaskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_PAY_DEPOSIT_ORDER, GetEmailContext.getOrderOperatorEmail(), GetEmailContext.getPayDepositEmailMsg(orderId,date)));
									/*------added by biran 20151020 付保证金时为卖家，买家业务员发送短信-----------*/
									PayDepositSendMsg4SalesMans(orderId,busiOrder.getWlid(),busiOrder.getUserid(),busiOrder.getBuyer());
									/*------added by biran end--------*/
									
								}else if(amtType==AmtTypeEnum.PAY_BALANCE_PAYMENT.getCode()){//支付尾款
									//短信提示卖方
									threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo,GetMessageContext.getPayBalanceMsg(orderId)));
									//邮件提示运营
									threadPoolTaskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_PAY_BALANCE_ORDER, GetEmailContext.getOrderOperatorEmail(), GetEmailContext.getPayBalanceEmailMsg(orderId,date)));
									/*------added by biran 20151020 支付尾款时为卖家，买家业务员发送短信-----------*/
									PayBalanceSendMsg4SalesMans(orderId,busiOrder.getWlid(),busiOrder.getUserid(),busiOrder.getBuyer());
									/*------added by biran end--------*/
									
									//账期订单
									 if(busiOrder.getOrderType()!=null && busiOrder.getOrderType().compareTo(Short.valueOf(BusiOrderTypeEnum.ACCOUNTING_ORDER.getCode()))==0){
										//已分割,则交易完成
										if(busiOrder.getSplitFlag().compareTo(new Short("2"))==0){
											BusiOrder order = new BusiOrder();
											 order.setOrderid(busiOrder.getOrderid());
											 order.setOrderstate(Integer.valueOf(BusiOrderStateEnum.COMPLETED_ORDER.getCode()));
											 int updateNum = busiOrderService.updateByIdSelective(order);
											 if(updateNum>0){
												//插入划账信息
													try {
														BusiOrder orderInfo = busiOrderService.findBusiOrderById(orderId);
														busiOrderDepositDao.insertOrderDeposit(orderInfo,BusiOrderDepositTypeEnum.ORDER_FINISHED_DEPOSIT.getCode());
													} catch (Exception e1) {
														log.error("账期订单支付完尾款且分割成功即订单完成时，插入划账信息时异常，"+e1.getMessage(), e1);
													}
													try{
														//给买家发送短信
														threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(ucBuyer.getMobile(), GetMessageContext.getBuyerOrderMsg(orderId)));
														//给卖家发短信
														UcUser seller = ucUserService.findUcUserById(busiOrder.getUserid().intValue());
														threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(seller.getMobile(), GetMessageContext.getOwnerOrderMsg(orderId)));
														}catch(Exception e){
															log.error("仓单分割成功即交易成功后给买家、卖家发短信异常，"+e.getMessage(), e);
														}
													//更新solr 挂牌
													RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, busiOrder.getListingid());
											 }
										}
									}
								} else if (amtType==AmtTypeEnum.PAY_FULL_PAYMENT.getCode()){
									//短信提示卖方
									threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(mobileNo,GetMessageContext.getFullPayMsg(orderId)));
									//邮件提示运营
									threadPoolTaskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_FULLPAYMENT_ORDER, GetEmailContext.getOrderOperatorEmail(), GetEmailContext.getFullPayEmailMsg(orderId,date)));
									//短信提醒业务员
									fullPaySendMsg4SalesMans(orderId,busiOrder.getWlid(),busiOrder.getUserid(),busiOrder.getBuyer());
								}
							} catch (Exception e) {
								log.debug(e.getMessage());
							}
						} catch (Exception e) {
							SysLogUtil.logForBusiness(e.getMessage(), true);
							//更新支付状态
							JsonObject reqParams2 = new JsonObject();
							//支付ID
							reqParams2.addProperty("resultId", resultId);
							//支付状态（0：未处理；1：已处理；2：错误）
							reqParams2.addProperty("status", 2);
							String encResultId = PayUtil.getDesEncryptData(reqParams2.toString());
							String resStatus = WmsRestHttpUtil.wmsRestPost(donePayResultUrl, encResultId);
							if(!"1".equals(resStatus)){
								log.error("支付记录更新错误："+busiOrder.getOrderid());
							}
							//邮件通知运营
						}
					}else{
						//更新支付状态
						JsonObject reqParams2 = new JsonObject();
						//支付ID
						reqParams2.addProperty("resultId", resultId);
						//支付状态（0：未处理；1：已处理；2：错误）
						reqParams2.addProperty("status", 2);
						String encResultId = PayUtil.getDesEncryptData(reqParams2.toString());
						String resStatus = WmsRestHttpUtil.wmsRestPost(donePayResultUrl, encResultId);
						if(!"1".equals(resStatus)){
							log.error("支付记录更新错误："+orderId);
						}
						//邮件通知运营
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally{
			log.debug("更新订单支付信息定时任务执行结束！");
		}
	}
	
	
	
	/*支付保证金时，给业务员发短信*/
	private void PayDepositSendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistService.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserService.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getPayDepositMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]支付保证金，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserService.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getPayDepositMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]支付保证金，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			log.error("支付保证金时,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
	
	
	/*支付尾款时，给业务员发短信*/
	private void PayBalanceSendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistService.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserService.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getPayBalanceMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]支付货款，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserService.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getPayBalanceMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]支付货款，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			log.error("支付保证金时,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
	
	/*支付全款时，给业务员发短信*/
	private void fullPaySendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistService.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserService.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getFullPayMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]支付全款，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserService.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserService.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					threadPoolTaskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getfullPayMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]支付全款，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			log.error("支付全款时,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
}
