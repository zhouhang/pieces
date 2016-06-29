/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.impl;

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

import com.jointown.zy.common.dao.BossOrderDao;
import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiAppealDao;
import com.jointown.zy.common.dao.BusiListingDao;
import com.jointown.zy.common.dao.BusiListingLogDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderDepositDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.BusiWhlistLogDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.dto.BossOrderCancelListDto;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiOrderDepositTypeEnum;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiOrderTypeEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.enums.ExamineStateEnum;
import com.jointown.zy.common.enums.SolrContentTypeEnum;
import com.jointown.zy.common.enums.SolrOperationTypeEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderDeposit;
import com.jointown.zy.common.model.BusiOrderSalesman;
import com.jointown.zy.common.model.BusiWhlist;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BossOrderCancelService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BossOrderCancelInfoVo;
import com.jointown.zy.common.vo.BossOrderCancelListVo;
import com.jointown.zy.common.vo.OrderMobileEmailVo;

/**
 * @ClassName: BossOrderCancelServiceImpl
 * @Description: 后台交易取消审核相关操作Service实现类
 * @Author: 赵航
 * @Date: 2015年4月13日
 * @Version: 1.0
 */
@Service
public class BossOrderCancelServiceImpl extends BaseService implements BossOrderCancelService{
	
	private static final Logger logger = LoggerFactory.getLogger(BossOrderCancelServiceImpl.class);
	
	@Autowired
	private BusiAppealDao busiAppealDao;
	
	@Autowired
	private BusiListingDao busiListingDao;
	
	@Autowired
	private BossOrderDao bossOrderDao;
	
	@Autowired
	private BusiOrderDao busiOrderDao;
	
	@Autowired
	private WmsApiService wmsApiService;
	
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private UcUserDao ucUserDao;
	
	@Autowired
	private BusiOrderDepositDao busiOrderDepositDao;
	
	@Autowired
	private BusiWhlistLogDao busiWhlistLogDao;
	
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	
	@Autowired
	private BusiListingLogDao busiListingLogDao;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Autowired
	private BusiOrderSalesmanDao busiOrderSalesmanDao;
	
	@Autowired
	private BossUserDao bossUserDao;
	
	@Autowired
	private BreedDao breedDao;
	
	
	@Override
	public Page<BossOrderCancelListVo> selectOrderCancelList(
			BossOrderCancelListDto query) throws Exception {
		logger.info("BossOrderCancelServiceImpl.selectOrderCancelList");
		// 实例化page对象
		Page<BossOrderCancelListVo> page = new Page<BossOrderCancelListVo>();
		page.setPageNo(query.getPageNo());
		page.setPageSize(query.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bossOrderCancelListDto", query);
		page.setParams(params);
		// 执行查询，获取结果
		List<BossOrderCancelListVo> results = busiAppealDao.selectOrderCancelListByPage(page);
		page.setResults(results);
		return page;
	}

	@Override
	public BossOrderCancelInfoVo selectOrderCancelInfoById(String orderId)
			throws Exception {
		logger.info("BossOrderCancelServiceImpl.selectOrderCancelInfoById");
		return busiAppealDao.selectOrderCancelInfoById(orderId);
	}

	@Override
	public String appealFinishById(final String orderId) throws Exception {
		logger.info("BossOrderCancelServiceImpl.appealFinishById");
		String result = "failure";
		final BusiOrder orderInfo = busiOrderDao.selectBusiOrderById(orderId);
		Exception exception = getTransactionTemplate().execute(new TransactionCallback<Exception>() {

			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					// 判断退款申请状态是否为已处理过
					BusiAppeal busiAppeal= busiAppealDao.selectBusiAppealByOrderId(orderId);
					//如果已经通过
					if(!ExamineStateEnum.WAIT_AUDIT.getCode().equals(String.valueOf(busiAppeal.getExamineState()))){
						logger.error("BossOrderCancelServiceImpl.appealFinishById:订单[" + orderId + "]的申请取消已经被审核处理过");
						throw new BossErrorException(ErrorRepository.BOSS_APPEALED_ERROR, orderId);
					} else {
						//没有通过就更新为通过
						int count = busiAppealDao.updateExamineStateById(orderId, ExamineStateEnum.FASSED.getCode());
						if(count != 1){
							logger.error("BossOrderCancelServiceImpl.appealFinishById:订单[" + orderId + "]的退款审核状态更新为已通过时出错");
							throw new Exception("系统处理异常，请联系管理员");
						}
						
						//判断划账信息是否已经存在
						BusiOrderDeposit deposit = busiOrderDepositDao.selectSingleOrderDeposit(orderId);
						if(deposit != null){
							logger.error("BossOrderCancelServiceImpl.appealFinishById:订单[" + orderId + "]的退款划账数据已经存在");
							throw new BossErrorException(ErrorRepository.BOSS_ORDER_DEPOSIT_EXIST_ERROR, orderId);
						} else {
							// 添加订单划账信息（订单退款处理）
							count = busiOrderDepositDao.insertOrderDeposit(orderInfo, BusiOrderDepositTypeEnum.ORDER_REFUND_DEPOSIT.getCode());
							if(count != 1){
								logger.error("BossOrderCancelServiceImpl.appealFinishById:添加订单[" + orderId + "]退款划账信息出错");
								throw new Exception("系统处理异常，请联系管理员");
							}
						}
					}
				} catch (Exception e) {
					status.setRollbackOnly();
					return e;
				}
				return null;
			}
		});
		
		if(exception != null){
			throw exception;
		} else {
			//账期订单，须向跟单员发邮件通知，以及向卖方业务员短信通知
			if(BusiOrderTypeEnum.ACCOUNTING_ORDER.getCode().equals(String.valueOf(orderInfo.getOrderType()))){
				BusiWhlist whlist = busiWhlistDao.selectWhlistByOrderId(orderId);
				OrderMobileEmailVo orderMobile = bossOrderDao.selectOrderMobileEmail(orderId);
				//邮件通知跟单员
				String emailAddr = GetEmailContext.getTrackerEmail();
				if(StringUtils.isNotEmpty(emailAddr)){
					try {
						taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_TERM_ORDER_CANCEL, emailAddr,
								GetEmailContext.getTermOrderCancelEmailMsg(orderId, whlist.getWlId(), orderMobile.getSellerName())));
					} catch (Exception e) {
						logger.error("账期订单[" + orderId + "]申退审核通过时，邮件通知跟单员出错，错误信息是：" + e.getMessage());
					}
				}
				
				//短信通知卖方业务员
				if(StringUtils.isNotEmpty(orderMobile.getSellerSalesmanMobile())){
					try {
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMobile.getSellerSalesmanMobile(),
								GetMessageContext.getTermOrderCancelSellerSalesmanMsg(orderId, whlist.getWlId(), orderMobile.getSellerName())));
					} catch (Exception e) {
						logger.error("账期订单[" + orderId + "]申退审核通过时，短信通知卖方业务员出错，错误信息是：" + e.getMessage());
					}
				}
			}
		}
		
		result = "success";
		return result;
	}

	@Override
	public String updateAppealRejectedById(String orderId,
			String rejectedComment) throws Exception {
		logger.info("BossOrderCancelServiceImpl.updateAppealRejectedById");
		BusiOrder orderInfo = busiOrderDao.selectBusiOrderById(orderId);
		String result = "failure";
		
		// 判断退款申请状态是否为“已驳回”
		BusiAppeal busiAppeal= busiAppealDao.selectBusiAppealByOrderId(orderId);
		//如果已经驳回
		if(!ExamineStateEnum.WAIT_AUDIT.getCode().equals(String.valueOf(busiAppeal.getExamineState()))){
			logger.error("BossOrderCancelServiceImpl.updateAppealRejectedById:订单[" + orderId + "]的申请取消已经被审核处理过");
			throw new BossErrorException(ErrorRepository.BOSS_APPEALED_ERROR, orderId);
		}
		
		Date date = new Date();
		// 实例化model对象
		BusiAppeal model = new BusiAppeal();
		model.setOrderId(orderId);
		model.setRejectReason(rejectedComment);
		model.setExamineState(Short.valueOf(ExamineStateEnum.REJECTED.getCode()));
		model.setUpdateTime(date);
		int count = busiAppealDao.updateRejectReason(model);
		if(count != 1){
			logger.error("BossOrderCancelServiceImpl.updateAppealRejectedById:订单[" + orderId + "]退款申请被驳回出错");
			throw new Exception("系统处理异常，请联系管理员");
		}
		
		if(BusiOrderTypeEnum.ORDINARY_ORDER.getCode().equals(String.valueOf(orderInfo.getOrderType()))){
			// 算出新的订单过期时间
			BusiAppeal appeal = busiAppealDao.selectBusiAppealByOrderId(orderId);
			BusiOrder order = busiOrderDao.selectBusiOrderById(orderId);
			long createTime = appeal.getCreateTime().getTime();
			long rejectedTime = appeal.getCreateTime().getTime();
			long oldExpiretime = order.getExpiretime().getTime();
			long newExpiretime = rejectedTime + (oldExpiretime - createTime);
			
			
			//更新订单的过期时间
			BusiOrder newOrder = new BusiOrder();
			newOrder.setOrderid(orderId);
			newOrder.setExpiretime(new Date(newExpiretime));
			newOrder.setUpdatetime(date);
			count = busiOrderDao.updateByIdSelective(newOrder);
			if(count != 1){
				logger.error("BossOrderCancelServiceImpl.updateAppealRejectedById:订单[" + orderId + "]重新设定过期时间出错");
				throw new Exception("系统处理异常，请联系管理员");
			}
			
			// 添加订单日志信息
			count = busiOrderLogDao.insertBusiOrderLog(
					order, "订单退款申请被驳回时重新设定订单过期时间", null, BusinessLogEnum.ORDER_REIMBURSE_REJECT.getCode());
			if(count != 1){
				logger.error("BossOrderCancelServiceImpl.updateAppealRejectedById:添加订单[" + order.getOrderid() + "]日志信息出错");
				throw new Exception("系统处理异常，请联系管理员");
			}
		}
		// 短信通知买家
		try {
			UcUser ucUser = ucUserDao.findMemberByUserId(String.valueOf(orderInfo.getBuyer()));
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(), GetMessageContext.getRejectAppealMsg(orderId)));
		} catch (Exception e) {
			logger.error("驳回订单[" + orderId + "]退款申请发送短信出错，错误信息是：" + e.getMessage());
		}
		
		result = "success";
		return result;
	}

	@Override
	public String orderCancelByTask(final BusiOrder order) throws Exception {
		logger.info("BossOrderCancelServiceImpl.orderCancelByTask");
		String result = "failure";
		
		final BusiListing listing = busiListingDao.selectSingleListing(order.getListingid());
		Exception exception = getTransactionTemplate().execute(new TransactionCallback<Exception>() {

			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					// 判断订单的挂牌是否已经下架
					int count = 0;
					if(BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(listing.getListingflag()))){
						BusiWhlist busiWhlist = busiWhlistDao.selectWhlistByWlId(listing.getWlid());
						busiWhlistLogDao.insertBusiWhlistLog(busiWhlist, "订单过期时，挂牌已下架的情况，订单成交数量返还到仓单", null, BusinessLogEnum.WHLIST_UPDATE_ORDER_CANCEL.getCode(), true);
						// 挂牌已下架的情况下，数量返还到仓单的可挂数量
						BusiListing record = new BusiListing();
						record.setWlid(listing.getWlid());//仓单ID
						record.setSurpluses(order.getVolume());
						count = busiListingDao.updateWlsurPlus(record);
						if(count != 1){
							logger.error("BossOrderCancelServiceImpl.orderCancelByTask:订单[" + order.getOrderid() + "]数量返还仓单[" + listing.getWlid() + "]出错");
							throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
						}
					} else {
						busiListingLogDao.insertBusiListingLog(listing, "订单过期时，挂牌未下架的情况，订单成交数量返还到挂牌", null, BusinessLogEnum.LISTING_ORDER_OVERTIME.getCode(), true);
						// 挂牌未下架的情况下，数量返还到该挂牌的可摘数量
						BusiListing record = new BusiListing();
						record.setListingid(listing.getListingid());//挂牌编号
						record.setSurpluses(order.getVolume());//可摘数量
						record.setUpdatetime(new Date());
						count = busiListingDao.updateListingSurplusByOrderAmount(record);
						if(count != 1){
							logger.error("BossOrderCancelServiceImpl.orderCancelByTask:订单[" + order.getOrderid() + "]数量返还挂牌[" + listing.getListingid() + "]出错");
							throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
						}
					}
					
					// 订单状态改为“已取消”
					count = bossOrderDao.updateOrderStateById(order.getOrderid(), BusiOrderStateEnum.CANCELED_ORDER.getCode());
					if(count != 1){
						logger.error("BossOrderCancelServiceImpl.orderCancelByTask:订单[" + order.getOrderid() + "]状态更新为已取消出错");
						throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
					}
					
					// 添加订单日志信息
					count = busiOrderLogDao.insertBusiOrderLog(
							order, "定时任务扫描过期订单时取消该订单", null, BusinessLogEnum.ORDER_CANCELED.getCode());
					if(count != 1){
						logger.error("BossOrderCancelServiceImpl.orderCancelByTask:添加订单[" + order.getOrderid() + "]日志信息出错");
						throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
					}
					
					// 添加订单划账信息（订单过期的分润）
					count = busiOrderDepositDao.insertOrderDeposit(order, BusiOrderDepositTypeEnum.ORDER_OVERTIME_DEPOSIT.getCode());
					if(count != 1){
						logger.error("BossOrderCancelServiceImpl.orderCancelByTask:添加订单[" + order.getOrderid() + "]过期分润划账信息出错");
						throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
					}
					
					// WMS数量解冻
					boolean rs = wmsApiService.unfreezeTrade(order.getWlid(), order.getVolume().doubleValue(),order.getOrderid());
					if(!rs){
						logger.error("BossOrderCancelServiceImpl.orderCancelByTask:WMS解冻订单数量[" + order.getOrderid() + "]失败");
						throw new BossErrorException(ErrorRepository.UNKNOW_ERROR);
					}
				} catch (Exception e) {
					status.setRollbackOnly();
					return e;
				}
				return null;
			}
		});
		
		if(exception != null){
			throw exception;
		}
		
		//数量返还挂牌时，要发消息通知solr更新挂牌索引
		if(!BusiListingFlagEnum.LISTING_CANCEL.getCode().equals(String.valueOf(listing.getListingflag()))){
			RabbitmqProducerManager.getInstance().pushMsgForSolr(
					SolrOperationTypeEnum.UPDATE, SolrContentTypeEnum.LISTING, listing.getListingid());
		}
		
		//邮件通知运营
		try {
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_ORDER_OVERTIME,
					GetEmailContext.getJYOperatorEmail(),
					GetEmailContext.getOrderOvertimeEmailMsg(order.getOrderid(),order.getExpiretime())));
		} catch (Exception e) {
			logger.error("订单[" + order.getOrderid() + "]过期发送邮件通知出错，错误信息是：" + e.getMessage());
		}
		
		//短信通知客户
		try {
			UcUser ucUser = ucUserDao.findMemberByUserId(String.valueOf(order.getBuyer()));
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(ucUser.getMobile(),
					GetMessageContext.getOrderOvertimeMsg(order.getOrderid(),order.getExpiretime())));
		} catch (Exception e) {
			logger.error("订单[" + order.getOrderid() + "]过期发送短信通知出错，错误信息是：" + e.getMessage());
		}
		//added by biran 20151021 新增给业务员发送短信
		OrderOvertimeSendMsg4SalesMans(order.getOrderid(),order.getWlid(),order.getUserid(),order.getBuyer());
		//added end
		result = "success";
		return result;
	}

	@Override
	public List<BusiOrder> selectOverTimeOrderList(Integer count)
			throws Exception {
		logger.info("BossOrderCancelServiceImpl.selectOverTimeOrderList");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderState", BusiOrderStateEnum.READY_WARE.getCode());//订单状态
		params.put("nowDate", new Date());//当前时间
		if(count != null){
			params.put("count", count);//检索最大条数
		}
		return bossOrderDao.selectOverdueOrderList(params);
	}
	
	
	
	
	/*超期限未付余款已取消，给业务员发短信*/
	private void OrderOvertimeSendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistDao.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserDao.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getOrderOvertimeMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]超期限未付余款已取消，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserDao.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getOrderOvertimeMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]超期限未付余款已取消，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			logger.error("订单超期限未付余款已取消,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
}
