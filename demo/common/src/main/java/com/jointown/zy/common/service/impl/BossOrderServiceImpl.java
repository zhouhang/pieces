/**
 * Copyright © 2014-2015 珍药材版权所有 ICP备14019602号-3
 */
package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
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
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiTermOrderDetailDao;
import com.jointown.zy.common.dto.BossOrderListDto;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusiParamEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiTermOrderDetail;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BossOrderService;
import com.jointown.zy.common.service.UcUserService;
import com.jointown.zy.common.service.WmsApiService;
import com.jointown.zy.common.util.BigDecimalUtil;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.NumberUtils;
import com.jointown.zy.common.util.TimeUtil;
import com.jointown.zy.common.vo.BossOrderInfoVo;
import com.jointown.zy.common.vo.BossOrderListVo;
import com.jointown.zy.common.vo.OrderMobileEmailVo;

/**
 * @ClassName: BossOrderServiceImpl
 * @Description: 后台订单相关操作Service实现类
 * @Author: 赵航
 * @Date: 2015年4月8日
 * @Version: 1.0
 */
@Service
public class BossOrderServiceImpl extends BaseService implements BossOrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(BossOrderServiceImpl.class);
	
	@Autowired
	private BossOrderDao bossOrderDao;
	
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	@Autowired
	private BusiOrderDao busiOrderDao;
	
	@Autowired
	private BusiTermOrderDetailDao busiTermOrderDetailDao;
	
	@Autowired
	private WmsApiService wmsApiService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Autowired
	private UcUserService ucUserService;
	
	@Override
	public Page<BossOrderListVo> selectOrderListByPage(BossOrderListDto query) throws Exception{
		logger.info("BossOrderServiceImpl.selectOrderListByPage");
		// 实例化page对象
		Page<BossOrderListVo> page = new Page<BossOrderListVo>();
		page.setPageNo(query.getPageNo());
		page.setPageSize(query.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bossOrderListDto", query);
		page.setParams(params);
		// 执行查询，获取结果
		List<BossOrderListVo> results = bossOrderDao.selectOrderListByPage(page);
		page.setResults(results);
		return page;
	}

	@Override
	public BossOrderInfoVo selectOrderInfoById(String orderId) throws Exception {
		logger.info("BossOrderServiceImpl.selectOrderInfoById");
		return bossOrderDao.selectOrderInfoById(orderId);
	}

	@Override
	public void updateExpireTime(String orderId, Date expireTime)
			throws Exception {
		logger.info("BossOrderServiceImpl.updateExpireTime");
		BusiOrder order = busiOrderDao.selectBusiOrderById(orderId);
		if(order == null){
			logger.error("BossOrderServiceImpl.updateExpireTime, error is:订单【" + orderId + "】已不存在" );
			throw new BossErrorException(ErrorRepository.NOT_EXIST, "订单");
		} else if(order.getExpiretime().compareTo(expireTime) >= 0){
			logger.error("BossOrderServiceImpl.updateExpireTime, error is:新到期时间需大于原到期时间" );
			throw new BossErrorException(ErrorRepository.ORDER_EXPIRETIME);
		}
		
		//更新过期时间
		BusiOrder newOrder = new BusiOrder();
		newOrder.setOrderid(order.getOrderid());
		newOrder.setExpiretime(expireTime);
		newOrder.setUpdatetime(new Date());
		bossOrderDao.updateOrderExpireTime(newOrder);
	
		// 记录订单日志信息
		busiOrderLogDao.insertBusiOrderLog(order, "后台操作订单延期", GetBaseInfo.getBossUserId(), BusinessLogEnum.ORDER_EXPIRETIME.getCode());
		
	}
	
	@Override
	public void updateOrderDeposit(String orderId,String deposit) throws Exception {
		logger.info("BossOrderServiceImpl.updateOrderDeposit orderId:" + orderId);
		logger.info("BossOrderServiceImpl.updateOrderDeposit deposit:" + deposit);
		//判断输入的保证金金额格式是否合法
		if (!NumberUtils.isIegalAmt(deposit)) {
			logger.info("BossOrderServiceImpl.updateOrderDeposit:deposit illegal");
			throw new BossErrorException(ErrorRepository.ORDER_DEPOSIT_ILLEGAL);
		}
		//判断订单是否存在
		BusiOrder orderInfo = busiOrderDao.selectBusiOrderById(orderId);
		if (null == orderInfo) {
			logger.error("BossOrderServiceImpl.updateOrderDeposit: not exist");
			throw new BossErrorException(ErrorRepository.NOT_EXIST,"订单");
		}
		//判断订单是否是已下单状态
		if (!BusiOrderStateEnum.PlACED_ORDER.getCode().equals(String.valueOf(orderInfo.getOrderstate()))) {
			logger.error("BossOrderServiceImpl.updateOrderDeposit:" + ErrorRepository.ORDER_NOT_PLACE_STATUS);
			throw new BossErrorException(ErrorRepository.ORDER_NOT_PLACE_STATUS);//订单不是已下单状态!
		}
		//保证金判断
		BigDecimal depositBigDecimal = new BigDecimal(deposit);//保证金
		BigDecimal bigDecimal100 = new BigDecimal(BusiParamEnum.BUSI_DEPOSIT_DEFAULT.getInfo());//保证金参考值100
		BigDecimal deposit2 = BigDecimalUtil.multiply(orderInfo.getTotalprice(), new BigDecimal(BusiParamEnum.BUSI_DEPOSIT_RATE.getInfo()));//按默认20%比例计算出的保证金
		//第一种情况，20%保证金大于等于100
		if (deposit2.compareTo(bigDecimal100) >= 0) {
			if (depositBigDecimal.compareTo(bigDecimal100) < 0) {
				logger.error("BossOrderServiceImpl.updateOrderDeposit:" + ErrorRepository.ORDER_DEPOSIT_LESS_THEN_INFO);
				throw new BossErrorException(ErrorRepository.ORDER_DEPOSIT_LESS_THEN_INFO,BusiParamEnum.BUSI_DEPOSIT_DEFAULT.getInfo());//修改保证金时，金额不能小于100!
			}
		}
		//第二种情况，20%保证金小于100
		if (deposit2.compareTo(bigDecimal100) < 0) {
			if (depositBigDecimal.compareTo(deposit2) < 0) {
				logger.error("BossOrderServiceImpl.updateOrderDeposit:" + ErrorRepository.ORDER_DEPOSIT_NOT_LESS_THEN_PER20);
				throw new BossErrorException(ErrorRepository.ORDER_DEPOSIT_NOT_LESS_THEN_PER20);//修改保证金时，金额不能小于保证金比例的20%!
			}
		}
		//保证金不能大于订单金额
		if (depositBigDecimal.compareTo(orderInfo.getTotalprice()) >= 0) {
			logger.error("BossOrderServiceImpl.updateOrderDeposit:" + ErrorRepository.ORDER_DEPOSIT_LESS_THEN_ORDERAMT);
			throw new BossErrorException(ErrorRepository.ORDER_DEPOSIT_LESS_THEN_ORDERAMT);//修改保证金时，金额不能大于订单金额!
		}
		//修改订单保证金
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(orderId);
		busiOrder.setDeposit(new BigDecimal(deposit));
		busiOrder.setUpdatetime(new Date());
		bossOrderDao.updateOrderDepositByOrderId(busiOrder);
		//记录日志
		busiOrderLogDao.insertBusiOrderLog(orderInfo, "订单保证金:"+orderInfo.getDeposit()+",修改为" + deposit, GetBaseInfo.getBossUserId(), BusinessLogEnum.ORDER_DEPOSIT_UPDATE.getCode());
		
	}

	@Override
	public void changeOrderTerm(final BusiTermOrderDetail termOrder) throws Exception {
		logger.info("BossOrderServiceImpl.changeOrderTerm");
		final BusiOrder order = busiOrderDao.selectBusiOrderById(termOrder.getOrderid());
		if(order == null){
			logger.error("BossOrderServiceImpl.changeOrderTerm, error is:订单【" + termOrder.getOrderid() + "】已不存在" );
			throw new BossErrorException(ErrorRepository.NOT_EXIST, "订单");
		}
		//请求wms进行仓单分割
		UcUser ucBuyer = ucUserService.findUcUserById(order.getBuyer().intValue());
		boolean rs = wmsApiService.applyWlSplit(order.getWlid(), order.getVolume().doubleValue(), order.getOrderid(), ucBuyer.getUserName());
		if(!rs){
			logger.error("BossOrderServiceImpl.changeOrderTerm, error is:请求WMS进行仓单分割时出错" );
			throw new BossErrorException(ErrorRepository.BOSS_TERM_ORDER_WMS);
		}
		Exception obj = getTransactionTemplate().execute(new TransactionCallback<Exception>() {

			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					//将订单改为账期订单
					int count = bossOrderDao.updateTermOrder(termOrder.getOrderid());
					if(count == 0){
						logger.error("BossOrderServiceImpl.changeOrderTerm, error is:订单【" + termOrder.getOrderid() + "】已是账期订单" );
						status.setRollbackOnly();
						return new BossErrorException(ErrorRepository.BOSS_TERM_ORDER_ERROR, termOrder.getOrderid());
					}
					
					// 添加订单日志信息
					busiOrderLogDao.insertBusiOrderLog(order, "后台操作转为账期订单", termOrder.getOperator(), BusinessLogEnum.ORDER_PAYMENTDAYS.getCode());
					
					//插入账期的详细信息
					busiTermOrderDetailDao.insert(termOrder);
					return null;
				} catch (Exception e) {
					logger.error("BossOrderServiceImpl.changeOrderTerm:订单转账期发生异常，异常是" + e.getMessage());
					status.setRollbackOnly();
					return e;
				}
			}
			
		});
		
		if(obj != null){
			throw obj;
		} else {
			OrderMobileEmailVo orderMoblieEmail = bossOrderDao.selectOrderMobileEmail(termOrder.getOrderid());
			int days = TimeUtil.daysBetween(termOrder.getStartTime(), termOrder.getEndTime());
			if(orderMoblieEmail != null){
				//发送短信通知卖方
				if(StringUtils.isNotEmpty(orderMoblieEmail.getSellerMobile())){
					try {
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMoblieEmail.getSellerMobile(),
								GetMessageContext.getTermOrderSellerMsg(orderMoblieEmail.getOrderId(), days)));
					} catch (Exception e) {
						logger.error("订单[" + orderMoblieEmail.getOrderId() + "]转账期时，短信通知卖方出错，错误信息是：" + e.getMessage());
					}
				}
				
				//发送短信通知卖方业务员
				if(StringUtils.isNotEmpty(orderMoblieEmail.getSellerSalesmanMobile())){
					try {
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMoblieEmail.getSellerSalesmanMobile(),
								GetMessageContext.getTermOrderSellerSalesmanMsg(orderMoblieEmail.getSellerName(), orderMoblieEmail.getOrderId(),
										termOrder.getStartTime(), termOrder.getEndTime(), days)));
					} catch (Exception e) {
						logger.error("订单[" + orderMoblieEmail.getOrderId() + "]转账期时，短信通知卖方业务员出错，错误信息是：" + e.getMessage());
					}
				}
				
				//发送短信通知买方
				if(StringUtils.isNotEmpty(orderMoblieEmail.getBuyerMobile())){
					try {
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMoblieEmail.getBuyerMobile(),
								GetMessageContext.getTermOrderBuyerMsg(orderMoblieEmail.getOrderId(), days, termOrder.getEndTime())));
					} catch (Exception e) {
						logger.error("订单[" + orderMoblieEmail.getOrderId() + "]转账期时，短信通知买方出错，错误信息是：" + e.getMessage());
					}
				}
				
				//发送短信通知买方业务员
				if(StringUtils.isNotEmpty(orderMoblieEmail.getBuyerSalesmanMobile())){
					try {
						taskExecutor.execute(messageConfigManage.getMessageChannelTask(orderMoblieEmail.getBuyerSalesmanMobile(),
								GetMessageContext.getTermOrderBuyerSalesmanMsg(orderMoblieEmail.getBuyerName(), orderMoblieEmail.getOrderId(),
										termOrder.getStartTime(), termOrder.getEndTime(), days)));
					} catch (Exception e) {
						logger.error("订单[" + orderMoblieEmail.getOrderId() + "]转账期时，短信通知买方业务员出错，错误信息是：" + e.getMessage());
					}
				}
			}
			
		}
		
	}

	@Override
	public BusiTermOrderDetail selectTermOrder(String orderId) throws Exception {
		logger.info("BossOrderServiceImpl.selectTermOrder");
		return busiTermOrderDetailDao.selectTermOrderByOrderId(orderId);
	}
}
