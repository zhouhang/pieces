package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.jointown.zy.common.dao.BossOrderDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.enums.AmtTypeEnum;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderLog;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.pay.PayUtil;
import com.jointown.zy.common.service.BusiOrderService;
import com.jointown.zy.common.service.BusiWhlistService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BusiOrderVo;
import com.jointown.zy.common.vo.OrderMobileEmailVo;
import com.jointown.zy.common.vo.RangeVo;
import com.jointown.zy.common.wms.WmsRestHttpUtil;

@Service
public class BusiOrderServiceImpl implements BusiOrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(BusiOrderServiceImpl.class);
	
	@Autowired
	private BusiOrderDao busiOrderDao;
	
	@Autowired
	private BusiListingDao busiListingDao;
	
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	@Autowired
	private BusiListingLogDao busiListingLogDao;
	
	@Autowired
	private BusiWhlistLogDao busiWhlistLogDao;
	
	@Autowired
	private BusiWhlistService busiWhlistService;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private BossOrderDao BossOrderDao;
	
	@Override
	public List<BusiOrderVo> selectOrderListByPage(Page<BusiOrderVo> page) {
		
		return busiOrderDao.selectOrderListByPage(page);
	}

	@Override
	public List<BusiOrderVo> findOrderListByCondition(Page<BusiOrderVo> page) {
		return busiOrderDao.selectOrderListByCondition(page);
	}
	
	@Override
	public void cancelOrder(String orderid) {
		busiOrderDao.cancleOrderById(orderid);
	}
	
	@Override
	public int deleteColosedOrderById(String orderid,Long...operator)throws Exception{
//		BusiOrder busiOrder =  busiOrderDao.selectBusiOrderById(orderid);
//		int result = busiOrderDao.deleteColosedOrderById(orderid);
//		if(result>0){
//			//添加订单日志记录
//			int busiOrderLogOk = busiOrderLogDao.insertBusiOrderLog(busiOrder, "删除已关闭的订单:"+orderid, ArrayUtils.isEmpty(operator)?null:operator[0], BusinessLogEnum.ORDER_DELETE.getCode());
//			if(busiOrderLogOk!=1){
//				throw new Exception("添加订单日志失败！");
//			}
//		}
//		return result;
		return -1;
	}
	
	@Override
	public int updateOrderState(BusiOrder record) {
		int orderState = record.getOrderstate();
		int ok = busiOrderDao.updateOrderState(record);
//		if(orderState==Short.valueOf(BusiOrderStateEnum.FINISHED.getCode())){
		if(orderState==1){//TODO BusiOrderStateEnum状态已经更改，等待交易二期修改
			record = busiOrderDao.selectBusiOrderById(record.getOrderid());
			int flag = busiListingDao.selectGoodsOrderState(record.getListingid());
			if(flag>0){
				BusiListing busiList = new BusiListing();
				busiList.setListingid(record.getListingid());
				busiList.setListingflag(Short.valueOf(BusiListingFlagEnum.LISTING_SOLDOUT.getCode()));
				busiListingDao.updateByIdSelective(busiList);
			}
		}
		return ok;
	}

	@Override
	public int updateDiscountPrice(BusiOrder record) {
		return busiOrderDao.updateDiscountPrice(record);
	}

	@Override
	public BusiOrder findBusiOrderById(String orderid){
		return busiOrderDao.selectBusiOrderById(orderid);
	}

	@Override
	public List<BusiOrder> selectMyOrdering(HashMap<String, Object> map) {
		return busiOrderDao.selectMyOrdering(map);
	}

	@Override
	public List<BusiOrder> findBusiOrdersByAttributes(BusiOrder record) {
		return busiOrderDao.selectBusiOrdersByAttributes(record);
	}
	
	@Override
	public int updateByIdSelective(BusiOrder record) {
		return busiOrderDao.updateByIdSelective(record);
	}

	/**
	 * 记录支付保证金的情况
	 * @author guoyb
	 * 2015年4月7日 上午11:15:07
	 * @param depositedOrder
	 * @return
	 */
	@Override
	public Integer updateDeposit(BusiOrder depositedOrder) {
		return this.busiOrderDao.updateByIdSelective(depositedOrder);
	}

	@Override
	public boolean updateBusiOrderJob(BusiOrder busiOrder, String donePayResultUrl, String resultId, int amtType, BigDecimal amount) throws Exception{
		//是否通知Rabbitmq挂牌信息
		boolean busiListingRabbitmq = false;
		String orderId = busiOrder.getOrderid();
		String listingId = busiOrder.getListingid();
		BigDecimal orderAmount = busiOrder.getAmount();
		BigDecimal actualPayment = busiOrder.getActualPayment();
		int orderState = busiOrder.getOrderstate();
		if(amtType==AmtTypeEnum.PAY_DEPOSIT.getCode()){//支付保证金
			actualPayment = amount;
			orderState = Integer.parseInt(BusiOrderStateEnum.PREPAID_DEPOSIT.getCode());
			busiListingRabbitmq = returnOrderAmount(busiOrder, listingId, orderAmount, orderId);
		}else if(amtType==AmtTypeEnum.PAY_BALANCE_PAYMENT.getCode()){//支付尾款
			int orderStateCanceled = Integer.parseInt(BusiOrderStateEnum.CANCELED_ORDER.getCode());
			if(orderState==orderStateCanceled){//订单已取消
				busiListingRabbitmq = returnOrderAmount(busiOrder, listingId, orderAmount, orderId);
			}
			actualPayment = actualPayment.add(amount);
			orderState = Integer.parseInt(BusiOrderStateEnum.PAYED_ORDER.getCode());
		}else if(amtType==AmtTypeEnum.PAY_FULL_PAYMENT.getCode()){
			actualPayment = amount;
			orderState = Integer.parseInt(BusiOrderStateEnum.PREPAID_DEPOSIT.getCode());
			busiListingRabbitmq = returnOrderAmount(busiOrder, listingId, orderAmount, orderId);
		}else{
			throw new Exception("付款类型错误！");
		}
		//更新订单
		BusiOrder BusiOrderRecord = new BusiOrder();
		BusiOrderRecord.setOrderid(orderId);
		BusiOrderRecord.setActualPayment(actualPayment);
		BusiOrderRecord.setOrderstate(orderState);
		if(amtType==AmtTypeEnum.PAY_FULL_PAYMENT.getCode()){
			BusiOrderRecord.setOrderType(Short.valueOf(BusiOrderTypeEnum.FULLPAY_ORDER.getCode()));
		}
		int ok = busiOrderDao.updateByIdSelective(BusiOrderRecord);
		if(ok!=1){
			throw new Exception("订单更新失败！");
		}
		//添加订单日志记录
		int busiOrderLogOk = 0;
		if(amtType==AmtTypeEnum.PAY_DEPOSIT.getCode()){//支付保证金
			busiOrderLogOk = busiOrderLogDao.insertBusiOrderLog(busiOrder, "定时任务扫描支付记录-订单支付保证金-更新订单状态、实际付款等", null, BusinessLogEnum.ORDER_DEPOSIT_PAID.getCode());
		}else if(amtType==AmtTypeEnum.PAY_BALANCE_PAYMENT.getCode()){//支付尾款
			busiOrderLogOk = busiOrderLogDao.insertBusiOrderLog(busiOrder, "定时任务扫描支付记录-订单支付尾款-更新订单状态、实际付款等", null, BusinessLogEnum.ORDER_BALANCE_PAID.getCode());
		}else if(amtType==AmtTypeEnum.PAY_FULL_PAYMENT.getCode()){//支付全款
			busiOrderLogOk = busiOrderLogDao.insertBusiOrderLog(busiOrder, "定时任务扫描支付记录-订单支付全款-更新订单状态、实际付款等", null, BusinessLogEnum.ORDER_DEPOSIT_PAID.getCode());
		}
		if(busiOrderLogOk!=1){
			throw new Exception("添加订单日志失败！");
		}
		//更新支付状态
		JsonObject reqParams = new JsonObject();
		//支付ID
		reqParams.addProperty("resultId", resultId);
		//支付状态（0：未处理；1：已处理；2：错误）
		reqParams.addProperty("status", 1);
		String encResultId = PayUtil.getDesEncryptData(reqParams.toString());
		String resStatus = WmsRestHttpUtil.wmsRestPost(donePayResultUrl, encResultId);
		if(!"1".equals(resStatus)){
			throw new Exception("更新支付记录状态失败！");
		}

		return busiListingRabbitmq;
	}
	
	/**
	 * 方法updateBusiOrderJob的共用部分
	 */
	private boolean returnOrderAmount(BusiOrder busiOrder, String listingId, BigDecimal orderAmount, String orderId) throws Exception{
		boolean busiListingRabbitmq = false;
		//查询挂牌
		BusiListing busiListing = busiListingDao.selectSingleListing(listingId);
		busiOrder.setListingEntity(busiListing);
		if(busiListing!=null){
			Short listingFlag = busiListing.getListingflag();
			int listingFlagOk = Integer.parseInt(BusiListingFlagEnum.LISTING.getCode());
			int listingFlagCancel = Integer.parseInt(BusiListingFlagEnum.LISTING_CANCEL.getCode());
			if(listingFlag==listingFlagOk){//挂牌中
				BigDecimal surpluses = busiListing.getSurpluses();
				if(surpluses.compareTo(orderAmount)>=0){
					surpluses = surpluses.subtract(orderAmount);
					//更新挂牌
					BusiListing busiListingRecord = new BusiListing();
					busiListingRecord.setListingid(listingId);
					busiListingRecord.setSurpluses(surpluses);
					int ok = busiListingDao.updateByIdSelective(busiListingRecord);
					if(ok!=1){
						throw new Exception("挂牌更新失败！");
					}
					int busiListingLogOk = busiListingLogDao.insertBusiListingLog(busiListing, "定时任务扫描支付记录-支付保证金-减少挂牌可摘数量", null, BusinessLogEnum.LISTINGUPDATE.getCode(),true);
					if(busiListingLogOk!=1){
						throw new Exception("添加挂牌日志失败！");
					}
					busiListingRabbitmq = true;
				}else{
					throw new Exception("挂牌可摘数量不够："+surpluses+"<"+orderAmount+"！");
				}
			}else if(listingFlag==listingFlagCancel){//挂牌已下架
				//查询仓单
				String wlId = busiListing.getWlid();
				BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(wlId);
				if(busiWhlist!=null){
					BusiWhlist oldBusiWhlist = (BusiWhlist)BeanUtilsBean.getInstance().cloneBean(busiWhlist);
					double wlSurplus = busiWhlist.getWlSurplus();
					int wlFlag = busiWhlist.getWlFlag();
					if(wlFlag == 0){//仓单正常
						if(wlSurplus>=orderAmount.doubleValue()){
							wlSurplus = wlSurplus - orderAmount.doubleValue();
							//更新仓单
							BusiWhlist busiWhlistRecord = new BusiWhlist();
							busiWhlistRecord.setWlId(wlId);
							busiWhlistRecord.setWlSurplus(wlSurplus);
							int ok = busiWhlistDao.updateBusiWhlistById(busiWhlistRecord);
							if(ok!=1){
								throw new Exception("仓单更新失败！");
							}
							//添加仓单日志记录
							busiWhlistService.addBusiWhlistLog(oldBusiWhlist, 
									GetBaseInfo.getBossUserId(), 
									BusinessLogEnum.WHLIST_UPDATE_ORDER_PAID
									, orderId,new RangeVo<Double>(oldBusiWhlist.getWlSurplus(), busiWhlist.getWlSurplus()));
						}else{
							throw new Exception("仓单可挂数量不够："+wlSurplus+"<"+orderAmount+"！");
						}
					}else{
						throw new Exception("仓单状态错误！");
					}
				}else{
					throw new Exception("仓单不存在！");
				}
			}else{
				throw new Exception("挂牌状态错误！");
			}
		}else{
			throw new Exception("挂牌不存在！");
		}
		return busiListingRabbitmq;
	}

	@Override
	public List<BusiOrder> findOverTimeOrdersByPlaced(Integer afterDays) throws Exception {
		List<BusiOrder> busiOrders = busiOrderDao.selectOverTimeOrdersByPlaced(afterDays);
		return busiOrders;
	}

	@Override
	public void updateOverTimeOrderById(String orderId) throws Exception {
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(orderId);
		Integer busiOrderStateClosed = Integer.parseInt(BusiOrderStateEnum.CLOSED_ORDER.getCode());
		busiOrder.setOrderstate(busiOrderStateClosed);
		//关闭过期订单
		int ok = busiOrderDao.updateByIdSelective(busiOrder);
		if(ok!=1){
			throw new Exception("关闭过期订单失败！");
		}
		//添加订单日志
		BusiOrder oldBusiOrder =  busiOrderDao.selectBusiOrderById(orderId);
		int busiOrderLogOk = busiOrderLogDao.insertBusiOrderLog(oldBusiOrder, "定时任务扫描过期订单时关闭该订单:"+orderId, null, BusinessLogEnum.ORDER_COLOSE.getCode());
		if(busiOrderLogOk!=1){
			throw new Exception("添加订单日志失败！");
		}
	}

	@Override
	public String selectCertifyName(Integer userId) {
		return busiOrderDao.selectCertifyName(userId);
	}

	@Override
	public BusiOrderLog selectLastOrderLog(String orderId) {
		List<BusiOrderLog> list = busiOrderLogDao.selectOrderLogList(orderId, false);
		if(list != null && list.size() > 0){
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @Description: 到期的未付尾款的账期订单 发短信、邮件通知
	 * @Author: fanyuna
	 * @Date: 2015年9月10日
	 * @param beforeDays 提前天数，为-1则到期发(因为是过期的第二天发提醒，SQL里是减，减减等于加)
	 * @param warnHms 提醒时间（时分秒）
	 */
	public void TermOrderSendMsg(int beforeDays,String warnHms){
		//查询即将到期的未付尾款的账期订单id、账期起止时间
				List<Map<String,Object>> busiTermOrderInfos = null;
				try{
					int optype = Integer.parseInt(BusinessLogEnum.TERM_ORDER_OVERTIME_WARN.getCode());
					//提前发
					if(beforeDays>0)
						optype = Integer.parseInt(BusinessLogEnum.TERM_ORDER_OVERTIME_WARN_ADVANCE.getCode());
					/**map里三个key，一个是beforeDays 提前天数，为-1则到期发(因为是过期的第二天发提醒，SQL里是减，减减等于加)
					 *         一个是optype 提醒类型，即将到期提醒或到期提醒
					 *         一个是hms 提醒时间（时分秒）
					 */
					Map<String,Object> map = new HashMap<String,Object>();
						map.put("beforeDays", beforeDays);
						map.put("optype", optype);
						map.put("hms", warnHms);
					busiTermOrderInfos = busiOrderDao.selectSoonTermOrder(map);
				}catch(Exception e){
					logger.error("BusiOrderServiceImpl execute selectSoonTermOrder, error is " + e.getMessage());
					//扫描出现异常，直接停止当前任务
					return;
				}
				if(busiTermOrderInfos != null && busiTermOrderInfos.size()>0){
					for(Map<String,Object> termOrderInfo:busiTermOrderInfos){
						try {
							sendMsgForSoonTermOrder(termOrderInfo,beforeDays);
							
						} catch (Exception e) {
							logger.error("BusiOrderServiceImpl execute termOrder sonn overtime sendMessageOrEmail[" + (String)termOrderInfo.get("orderid") +"], error is " + e.getMessage());
							continue;
						}
					}
				}
	}

	
	private void sendMsgForSoonTermOrder(Map<String, Object> termOrderInfo,int beforeDays) throws Exception {
			String orderid = (String)termOrderInfo.get("ORDERID"); //订单号
			Date startTime = (Date)termOrderInfo.get("START_TIME"); //账期起始时间
			Date endTime = (Date)termOrderInfo.get("END_TIME"); //账期结束时间
		
			String startTimeStr = new SimpleDateFormat("yyyy年MM月dd日").format(startTime);
			String endTimeStr = new SimpleDateFormat("yyyy年MM月dd日").format(endTime);
			//账期延期天数
			int days = TimeUtil.daysBetween(startTime, endTime);
			
			OrderMobileEmailVo orderMobileEmailInfo = BossOrderDao.selectOrderMobileEmail(orderid);
			
		if(beforeDays>0){
			 //给卖家发短信
			if(orderMobileEmailInfo.getSellerMobile()!=null){
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMobileEmailInfo.getSellerMobile(), GetMessageContext.getTermOrderSonnOverMsgOfSeller(orderid, days)));
			}
			//给买家发短信
			if(orderMobileEmailInfo.getBuyerMobile()!=null){
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMobileEmailInfo.getBuyerMobile(), GetMessageContext.getTermOrderSonnOverMsgOfBuyer(orderid,endTimeStr,days)));
			}
		}
			//给卖家业务员发短信
			if(orderMobileEmailInfo.getSellerSalesmanMobile()!=null){
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMobileEmailInfo.getSellerSalesmanMobile(), GetMessageContext.getTermOrderSonnOverMsgOfSellerSalesman(orderid,orderMobileEmailInfo.getSellerName(),startTimeStr,endTimeStr,days,beforeDays)));
			}
			//给买家业务员发短信
			if(orderMobileEmailInfo.getBuyerSalesmanMobile()!=null){
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMobileEmailInfo.getBuyerSalesmanMobile(), GetMessageContext.getTermOrderSonnOverMsgOfBuyerSalesman(orderid,orderMobileEmailInfo.getBuyerName(),startTimeStr,endTimeStr,days,beforeDays)));
			}
			String emailTitle ="";
			if(beforeDays>0)
				emailTitle=GetEmailContext.EMAIL_TERM_ORDER_SOON_OVERTIME;
			else 
				emailTitle=GetEmailContext.EMAIL_TERM_ORDER_OVERTIME;
			String emailAddr = GetEmailContext.getTrackerEmail();
			taskExecutor.execute(new EmailTaskSend(emailTitle,emailAddr, GetEmailContext.getTermOrderOverTimeMsg(orderid,startTimeStr,endTimeStr,days,beforeDays)));
			
			//发过短信或邮件，则往订单日志表里插入一记录，查需要提醒的记录时过滤掉发过的
			BusiOrder oldBusiOrder =  busiOrderDao.selectBusiOrderById(orderid);
			if(beforeDays>0)
				busiOrderLogDao.insertBusiOrderLog(oldBusiOrder, BusinessLogEnum.TERM_ORDER_OVERTIME_WARN_ADVANCE.getCodeName(), null, BusinessLogEnum.TERM_ORDER_OVERTIME_WARN_ADVANCE.getCode());
			else
				busiOrderLogDao.insertBusiOrderLog(oldBusiOrder, BusinessLogEnum.TERM_ORDER_OVERTIME_WARN.getCodeName(), null, BusinessLogEnum.TERM_ORDER_OVERTIME_WARN.getCode());
		
	}

}
