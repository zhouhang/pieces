/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jointown.zy.common.dao.BossOrderDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderDepositDao;
import com.jointown.zy.common.dao.BusiOrderDepositLogDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.BossOrderDepositDto;
import com.jointown.zy.common.dto.BossOrderDepositQueryDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderDepositResultEnum;
import com.jointown.zy.common.enums.BusiOrderDepositStateEnum;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.RemitStatusEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderDeposit;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.RemitFlow;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BusiOrderDepositService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.GsonFactory;
import com.jointown.zy.common.util.RestHttpUtil;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BossOrderDepositVo;
import com.jointown.zy.common.vo.RefuseRemitFlowVo;

/**
 * @ClassName: BusiOrderDepositServiceImpl
 * @Description: 订单划账Service实现类
 * @Author: 赵航
 * @Date: 2015年5月18日
 * @Version: 1.0
 */
@Service
public class BusiOrderDepositServiceImpl extends BaseService implements BusiOrderDepositService{
	
	private static final Logger logger = LoggerFactory.getLogger(BusiOrderDepositServiceImpl.class);
	
	@Autowired
	private BusiOrderDepositDao busiOrderDepositDao;
	
	@Autowired
	private BusiOrderDepositLogDao busiOrderDepositLogDao;
	
	@Autowired
	private BusiListingDao busiListingDao;
	
	@Autowired
	private BusiOrderDao busiOrderDao;
	
	@Autowired
	private BossOrderDao bossOrderDao;
	
	@Autowired
	private WmsApiService wmsApiService;
	
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private UcUserDao ucUserDao;
	
	@Autowired
	private BusiWhlistLogDao busiWhlistLogDao;
	
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	
	@Autowired
	private BusiListingLogDao busiListingLogDao;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Override
	public BossOrderDepositVo getCanceledOrderDepositInfo(String orderId) throws Exception {
		return busiOrderDepositDao.getCanceledOrderDepositInfo(orderId);
	}

	@Override
	public Page<BossOrderDepositVo> selectDepositList(
			BossOrderDepositQueryDto query) throws Exception {
		logger.info("BusiOrderDepositServiceImpl.selectDepositList");
		Page<BossOrderDepositVo> page = new Page<BossOrderDepositVo>();
		page.setPageNo(query.getPageNo());
		page.setPageSize(query.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bossOrderDepositQueryDto", query);
		page.setParams(params);
		// 执行查询，获取结果
		List<BossOrderDepositVo> results = busiOrderDepositDao.getDepositListByPage(page);
		page.setResults(results);
		return page;
	}

	@Override
	public String todoOrderDeposit(final BossOrderDepositDto deposit)
			throws Exception {
		logger.info("BusiOrderDepositServiceImpl.todoOrderDeposit");
		Map<String, Object> map = getTransactionTemplate().execute(new TransactionCallback<Map<String, Object>>() {
			@Override
			public Map<String, Object> doInTransaction(TransactionStatus status) {
				Map<String, Object> map = new HashMap<String, Object>();
				BusiOrderDeposit orderDeposit = null;
				try {
					//查询划账信息并锁定（防止多人操作）
					orderDeposit = busiOrderDepositDao.selectOrderDepositByLock(deposit.getOrderId());
					if(orderDeposit == null){
						logger.error("BusiOrderDepositServiceImpl.todoOrderDeposit:订单[" + deposit.getOrderId() + "]不存在");
						status.setRollbackOnly();
						return setMap(map, new BossErrorException(ErrorRepository.NOT_EXIST, "划账信息"), orderDeposit);
					}
					
					//更新划账信息为已处理
					orderDeposit.setSellerAmount(new BigDecimal(deposit.getSellerAmount()));
					orderDeposit.setBuyerAmount(new BigDecimal(deposit.getBuyerAmount()));
					orderDeposit.setPlatformAmount(new BigDecimal(deposit.getPlatformAmount()));
					orderDeposit.setDepositState(Short.valueOf(BusiOrderDepositStateEnum.DEPOSIT_PROCESSING.getCode()));
					orderDeposit.setUpdatetime(new Date());
					int count = busiOrderDepositDao.updateOrderDepositByOrderId(orderDeposit);
					if(count != 1){
						logger.error("BusiOrderDepositServiceImpl.todoOrderDeposit:订单[" + orderDeposit.getOrderId() + "]划账信息更新出错");
						status.setRollbackOnly();
						return setMap(map, new BossErrorException(ErrorRepository.BOSS_ORDER_DEPOSIT_UPDATE_FAILURE, orderDeposit.getOrderId()), orderDeposit);
					}
					
					//调用支付划账接口
					RemitFlow remitInfo = new RemitFlow();
					remitInfo.setOrderId(orderDeposit.getOrderId());
					remitInfo.setPlatformId(0L);
					remitInfo.setPlatformAmount(orderDeposit.getPlatformAmount());
					remitInfo.setSellerId(orderDeposit.getSellerId());
					remitInfo.setSellerAmount(orderDeposit.getSellerAmount());
					remitInfo.setBuyerId(orderDeposit.getBuyerId());
					remitInfo.setBuyerAmount(orderDeposit.getBuyerAmount());
					remitInfo.setTotalAmount(orderDeposit.getDepositAmount());
					remitInfo.setRemitType(Integer.valueOf(orderDeposit.getDepositType()));
					remitInfo.setCreaterid(deposit.getOperattorId());
					remitInfo.setClientIp(deposit.getOperattorIp());
					remitInfo.setSourcesys(0);
					String signdata = PayUtil.toRemitAccountSignData(remitInfo);
					remitInfo.setSigndata(signdata);
					String jsonString = GsonFactory.toJson(remitInfo);
					String url=SpringUtil.getConfigProperties("jointown.pay.remitAccount");
					String remit = RestHttpUtil.RestPost(url, jsonString);
					if(remit == null){
						logger.error("BusiOrderDepositServiceImpl.todoOrderDeposit: [RestHttpUtil.RestPost]方式调用支付划账接口执行结果是空");
						status.setRollbackOnly();
						return setMap(map, new BossErrorException(ErrorRepository.BOSS_ORDER_DEPOSIT_PAY_FAILURE, "[RestHttpUtil.RestPost]方式调用支付划账接口执行结果是空"), orderDeposit);
					}
					JsonObject json = GsonFactory.fromJson(remit, JsonObject.class);
					//划账失败
					if("0".equals(json.get("code").getAsString())){
						logger.error("BusiOrderDepositServiceImpl.todoOrderDeposit:【"
								+ BusiOrderDepositTypeEnum.toMap().get(String.valueOf(orderDeposit.getDepositType()))
								+ "】调用支付划账申请接口失败");
						status.setRollbackOnly();
						return setMap(map, new BossErrorException(ErrorRepository.BOSS_ORDER_DEPOSIT_PAY_FAILURE, json.get("msg").getAsString()), orderDeposit);
					} else if("1".equals(json.get("code").getAsString())){
						logger.info("BusiOrderDepositServiceImpl.todoOrderDeposit:【"
								+ BusiOrderDepositTypeEnum.toMap().get(String.valueOf(orderDeposit.getDepositType()))
								+ "】调用支付划账申请接口成功");
					}
					return setMap(map, null, orderDeposit);
				} catch (Exception e) {
					logger.error("BusiOrderDepositServiceImpl.todoOrderDeposit:交易划账出错: " + e.getMessage());
					status.setRollbackOnly();
					return setMap(map, e, orderDeposit);
				}
			}
		});
		
		Exception exception = (Exception) map.get("exception");
		BusiOrderDeposit orderDeposit = (BusiOrderDeposit) map.get("orderDeposit");
		if(exception != null){
			//插入划账操作日志（失败）
			busiOrderDepositLogDao.insertDepositLog(orderDeposit, deposit.getOperattorId(), deposit.getOperattorIp(),
					BusiOrderDepositResultEnum.DEPOSIT_FAILURE.getCode(), exception.getMessage());
			throw exception;
		} else {
			//插入划账操作日志（成功）
			busiOrderDepositLogDao.insertDepositLog(orderDeposit, deposit.getOperattorId(), deposit.getOperattorIp(),
					BusiOrderDepositResultEnum.DEPOSIT_SUCCESS.getCode(), null);
			//发邮件通知财务
			try {
				taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_INSERT_REMITFLOW, GetEmailContext.getRemitFlowFinanceEmail(), 
						GetEmailContext.getAddRemitFlowEmailMsg(orderDeposit.getDepositAmount().toString(),
								orderDeposit.getOrderId(), Integer.valueOf(orderDeposit.getDepositType()))));
			} catch (Exception e) {
				logger.error("订单[" + orderDeposit.getOrderId() + "]划账给财务发邮件通知出错，错误信息是：" + e.getMessage());
			}
			return "success";
		}
		
	}
	
	/**
	 * 仅供todoOrderDeposit调用，其他方法不可调用
	 */
	private Map<String, Object> setMap(Map<String, Object> map, Exception exception, BusiOrderDeposit orderDeposit){
		map.put("exception", exception);
		map.put("orderDeposit", orderDeposit);
		return map;
	}

	@Override
	public void changOrderDepositState(JsonObject updateJson) throws Exception {
		logger.info("BusiOrderDepositServiceImpl.changOrderDepositState");
		String orderId = updateJson.get("orderId").getAsString();//订单编号
		final String remitType = updateJson.get("remitType").getAsString();//划账类型
		final String remitResultId = updateJson.get("remitResultId").getAsString();//划账结果ID
		int remitStatus = updateJson.get("remitStatus").getAsInt();//划账状态
		String remitTime = updateJson.get("remitTime").getAsString();//划账时间
		
		final String doneRemitUrl = SpringUtil.getConfigProperties("jointown.pay.doneRemitResult");
		
		final BusiOrderDeposit deposit = new BusiOrderDeposit();
		deposit.setOrderId(orderId);
		deposit.setDepositType(Short.valueOf(remitType));
		//订单划账被拒绝
		if(RemitStatusEnum.REMIT_REFUSE.getStatus() == remitStatus){
			deposit.setDepositState(Short.valueOf(BusiOrderDepositStateEnum.DEPOSIT_REFUSED.getCode()));
			deposit.setPayedTime(null);
		}
		//订单划账被通过
		else if(RemitStatusEnum.REMIT_SUCCESS.getStatus() == remitStatus){
			deposit.setDepositState(Short.valueOf(BusiOrderDepositStateEnum.DEPOSIT_TREATED.getCode()));
			if(!StringUtils.isEmpty(remitTime)){
				deposit.setPayedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(remitTime));
			} else {
				deposit.setPayedTime(null);
			}
		}
		deposit.setUpdatetime(new Date());
		
		//订单划账被拒绝
		if(RemitStatusEnum.REMIT_REFUSE.getStatus() == remitStatus){
			getTransactionTemplate().execute(new TransactionCallback<String>() {

				@Override
				public String doInTransaction(TransactionStatus status) {
					try {
						//更新订单划账任务表的状态为"已拒绝"
						busiOrderDepositDao.updateOrderDepositByPayFinish(deposit);
						
						//通知支付处理已经结束
						JsonObject param = new JsonObject();
						param.addProperty("remitResultId", remitResultId);
						String rsStr = RestHttpUtil.RestPost(doneRemitUrl, PayUtil.getDesEncryptData(param.toString()));
						String decryptStr = PayUtil.getDesDecryptData(rsStr);
						JsonObject json = GsonFactory.fromJson(decryptStr, JsonObject.class);
						if("0".equals(json.get("rStatus").getAsString())){
							logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:交易划账被拒绝时，执行划账流水结果修改接口失败");
							status.setRollbackOnly();
							return "101";
						}
						return "100";
					} catch (Exception e) {
						logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:更新订单[" + deposit.getOrderId() + "]划账状态为已拒绝时出错");
						status.setRollbackOnly();
						return "101";
					}
				}
			});
		}
		//订单划账被通过
		else if(RemitStatusEnum.REMIT_SUCCESS.getStatus() == remitStatus){
			final BusiOrder orderInfo = busiOrderDao.selectBusiOrderById(deposit.getOrderId());
			final BusiListing listing = busiListingDao.selectSingleListing(orderInfo.getListingid());
			String result = getTransactionTemplate().execute(new TransactionCallback<String>() {

				@Override
				public String doInTransaction(TransactionStatus status) {
					try {
						int count = 0;
						//更新订单划账任务表的状态为"已处理"
						count = busiOrderDepositDao.updateOrderDepositByPayFinish(deposit);
						if(count == 0){
							logger.warn("BusiOrderDepositServiceImpl.changOrderDepositState:已更新过订单[" + deposit.getOrderId() + "]的划账状态为已处理");
							return "102";
						}
						//订单申请退款
						if(BusiOrderDepositTypeEnum.ORDER_REFUND_DEPOSIT.getCode().equals(remitType)){
							
							if(BusiOrderTypeEnum.ORDINARY_ORDER.getCode().equals(String.valueOf(orderInfo.getOrderType()))){
								// 判断订单的挂牌是否已经下架
								if(BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(listing.getListingflag()))){
									BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(listing.getWlid());
									busiWhlistLogDao.insertBusiWhlistLog(busiWhlist, "订单退款完成后，挂牌已下架的情况，订单成交数量返还到仓单", null, BusinessLogEnum.WHLIST_UPDATE_ORDER_CANCEL.getCode(), true);
									// 挂牌已下架的情况下，数量返还到仓单的可挂数量
									BusiListing record = new BusiListing();
									record.setWlid(listing.getWlid());//仓单ID
									record.setSurpluses(orderInfo.getVolume());
									count = busiListingDao.updateWlsurPlus(record);
									if(count != 1){
										logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:订单[" + orderInfo.getOrderid() + "]退款完成，并将数量返还仓单[" + listing.getWlid() + "]时出错");
										status.setRollbackOnly();
										return "101";
									}
								} else {
									busiListingLogDao.insertBusiListingLog(listing, "订单退款完成后，挂牌未下架的情况，订单成交数量返还到挂牌", null, BusinessLogEnum.LISTING_ORDER_REFUND.getCode(), true);
									// 挂牌未下架的情况下，数量返还到该挂牌的可摘数量
									BusiListing record = new BusiListing();
									record.setListingid(listing.getListingid());//挂牌编号
									record.setSurpluses(orderInfo.getVolume());//可摘数量
									record.setUpdatetime(new Date());
									count = busiListingDao.updateListingSurplusByOrderAmount(record);
									if(count != 1){
										logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:订单[" + orderInfo.getOrderid() + "]退款完成，并将数量返还挂牌[" + listing.getListingid() + "]时出错");
										status.setRollbackOnly();
										return "101";
									}
								}
							}
							
							// 订单状态改为“已取消”
							count = bossOrderDao.updateOrderStateById(orderInfo.getOrderid(), BusiOrderStateEnum.CANCELED_ORDER.getCode());
							if(count != 1){
								logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:订单[" + orderInfo.getOrderid() + "]退款完成，并将订单状态更新为已取消时出错");
								status.setRollbackOnly();
								return "101";
							}
							
							// 添加订单日志信息
							count = busiOrderLogDao.insertBusiOrderLog(
									orderInfo, "订单退款完成", null, BusinessLogEnum.ORDER_REIMBURSE_PAY.getCode());
							if(count != 1){
								logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:添加订单[" +  orderInfo.getOrderid() + "]日志信息出错");
								status.setRollbackOnly();
								return "101";
							}
							
							// WMS数量解冻
							boolean rs = wmsApiService.unfreezeTrade(orderInfo.getWlid(), orderInfo.getVolume().doubleValue(),orderInfo.getOrderid());
							if(!rs){
								logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:退款完成时，WMS解冻订单[" + orderInfo.getOrderid() + "]数量失败");
								status.setRollbackOnly();
								return "101";
							}
						}
						return "100";
					} catch (Exception e) {
						logger.error("BusiOrderDepositServiceImpl.changOrderDepositState:更新订单[" + deposit.getOrderId() + "]划账状态为已处理时出错: " + e.getMessage());
						status.setRollbackOnly();
						return "101";
					}
				}
			});
			
			//如果执行结果为成功(100)或者已经执行过("102")
			if("100".equals(result) || "102".equals(result)){
				//通知支付处理已经结束
				JsonObject param = new JsonObject();
				param.addProperty("remitResultId", remitResultId);
				RestHttpUtil.RestPost(doneRemitUrl, PayUtil.getDesEncryptData(param.toString()));
			}
			
			//执行成功(100)并且是订单申请退款的情况
			if("100".equals(result) && BusiOrderDepositTypeEnum.ORDER_REFUND_DEPOSIT.getCode().equals(remitType)){
				
				//数量返还挂牌时，要发消息通知solr更新挂牌索引
				if(BusiOrderTypeEnum.ORDINARY_ORDER.getCode().equals(String.valueOf(orderInfo.getOrderType())) && !BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(listing.getListingflag()))){
					RabbitmqProducerManager.getInstance().pushMsgForSolr(SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listing.getListingid());
				}
				
				// 短信通知买家退款完成  
				//update by fanyuna 2015.11.20   短信模板内容增加退款金额
				try {
					UcUser ucUser = ucUserDao.findMemberByUserId(String.valueOf(orderInfo.getBuyer()));
					BusiOrderDeposit busiDeposit = busiOrderDepositDao.selectSingleOrderDeposit(orderId);
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getCompleteRefundMsg(orderId,busiDeposit.getBuyerAmount())));
				} catch (Exception e) {
					logger.error("订单[" + orderId + "]完成退款发送短信出错，错误信息是：" + e.getMessage());
				}
			}
			
			
			//add by fanyuna 2015.11.20 增加 卖家分润大于0，给卖家手机发短信,财务在支付系统操作，划账成功状态后，触发短信
			
			if("100".equals(result) && BusiOrderDepositTypeEnum.ORDER_FINISHED_DEPOSIT.getCode().equals(remitType)){
				try {
					UcUser ucUser = ucUserDao.findMemberByUserId(String.valueOf(orderInfo.getUserid()));
					BusiOrderDeposit busiDeposit = busiOrderDepositDao.selectSingleOrderDeposit(orderId);
					//卖家分润大于0 才发短信
					if(busiDeposit!=null && busiDeposit.getSellerAmount().compareTo(BigDecimal.ZERO)>0)
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getOrderDepositSellerMsg(orderId,busiDeposit.getSellerAmount())));
				} catch (Exception e) {
					logger.error("订单[" + orderId + "]划账成功给卖家发送短信出错，错误信息是：" + e.getMessage());
				}
			}
			
		}
	}

	@Override
	public List<JsonObject> selectOrderDepositResults(int count)
			throws Exception {
		logger.info("BusiOrderDepositServiceImpl.changOrderDepositState");
		List<JsonObject> list = null;
		//设定支付划账结果查询接口参数
		String queryRemitUrl = SpringUtil.getConfigProperties("jointown.pay.queryRemitResult");
		JsonObject param = new JsonObject();
		param.addProperty("source_sys", 0);
		param.addProperty("num", count);
		//调用支付接口
		String rsStr = RestHttpUtil.RestPost(queryRemitUrl, PayUtil.getDesEncryptData(param.toString()));
		String decryptStr = PayUtil.getDesDecryptData(rsStr);
		JsonObject resultJson = GsonFactory.fromJson(decryptStr, JsonObject.class);
		//调用成功
		if("100".equals(resultJson.get("resultCode").getAsString())){
			list = new ArrayList<JsonObject>();
			String resultInfo = resultJson.get("resultInfo").getAsString();
			JsonArray jsonArray = GsonFactory.fromJson(resultInfo, JsonArray.class);
			for(JsonElement element : jsonArray){
				list.add(element.getAsJsonObject());
			}
		} else {
			throw new Exception(resultJson.get("codeMessage").getAsString());
		}
		return list;
	}

	@Override
	public List<RefuseRemitFlowVo> RejectReason(String reqJson) throws Exception {
		logger.info("BusiOrderDepositServiceImpl.RejectReason");
		//加密请求
		String encParams = PayUtil.getDesEncryptData(reqJson);
		String resResults = RestHttpUtil.RestPost( SpringUtil.getConfigProperties("jointown.pay.queryRefuseRemitFlow"), encParams);
		if("102".equals(resResults) || "101".equals(resResults)){
			return new ArrayList<RefuseRemitFlowVo>();
		}
		resResults = PayUtil.getDesDecryptData(resResults);
		Gson gson = new Gson();
		List<RefuseRemitFlowVo> refuseRemitFlowVos = gson.fromJson(resResults, new TypeToken<List<RefuseRemitFlowVo>>(){}.getType());
		if (refuseRemitFlowVos==null) {
			refuseRemitFlowVos = new ArrayList<RefuseRemitFlowVo>();
		}
		return refuseRemitFlowVos;
	}
	
}
