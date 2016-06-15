/**
 * Copyright © 2014-2015 珍药材版权所有
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

import com.jointown.zy.common.dao.BusiPurchaseDao;
import com.jointown.zy.common.dao.BusiPurchaseLogDao;
import com.jointown.zy.common.dao.BusiQuoteDao;
import com.jointown.zy.common.dto.BossPurchaseAuditQueryDto;
import com.jointown.zy.common.dto.BossPurchaseManageQueryDto;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.enums.BusiQuoteStateEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.exception.BossErrorException;
import com.jointown.zy.common.exception.ErrorRepository;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiPurchase;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BossPurchaseService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetBaseInfo;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * @ClassName: BossPurchaseServiceImpl
 * @Description: 后台采购信息处理Service实现类
 * @Author: 赵航
 * @Date: 2015年10月12日
 * @Version: 1.0
 */
@Service
public class BossPurchaseServiceImpl extends BaseService implements BossPurchaseService{
	
	private static final Logger logger = LoggerFactory.getLogger(BossPurchaseServiceImpl.class);
	
	@Autowired
	private BusiPurchaseDao busiPurchaseDao;
	
	@Autowired
	private BusiPurchaseLogDao busiPurchaseLogDao;
	
	@Autowired
	private BusiQuoteDao busiQuoteDao;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private MessageConfigManage messageConfigManage;

	@Override
	public Page<BusiPurchaseVo> selectPurchaseAuditPage(
			BossPurchaseAuditQueryDto query) throws Exception {
		try {
			logger.info("BossPurchaseServiceImpl.selectPurchaseAuditPage");
			Page<BusiPurchaseVo> page = new Page<BusiPurchaseVo>();
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("query", query);
			page.setParams(params);
			// 获取审核采购信息分页信息
			List<BusiPurchaseVo> results = busiPurchaseDao.selectPurchaseAuditPage(page);
			page.setResults(results);
			return page;
		} catch (Exception e) {
			logger.error("BossPurchaseServiceImpl.selectPurchaseAuditPage:获取后台审核采购信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

	@Override
	public BusiPurchaseVo selectPurchaseDetail(String purchaseId)
			throws Exception {
		try {
			logger.info("BossPurchaseServiceImpl.selectPurchaseDetail");
			// 获取审核采购详情
			BusiPurchaseVo purchase = busiPurchaseDao.selectPurchaseDetail(purchaseId);
			if(purchase == null){
				throw new BossErrorException(ErrorRepository.NOT_EXIST, "采购信息");
			}
			return purchase;
		} catch (Exception e) {
			logger.error("BossPurchaseServiceImpl.selectPurchaseDetail:获取获取审核采购详情[" + purchaseId + "]失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

	@Override
	public void changePurchaseAuditStatus(final String purchaseId, final String purchaseCode,
			final String auditStatus, final String remark) throws Exception {
		logger.info("BossPurchaseServiceImpl.changePurchaseAuditStatus");
		Exception e = getTransactionTemplate().execute(new TransactionCallback<Exception>() {
			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					//查询采购信息
					BusiPurchase purchase = busiPurchaseDao.selectByPrimaryKey(purchaseId);
					if(purchase == null) {
						throw new BossErrorException(ErrorRepository.NOT_EXIST, "采购信息");
					}
					//采购信息存在时，根据不同的审核状态，更新采购信息
					BusiPurchase record = new BusiPurchase();
					record.setPurchaseId(purchaseId);
					String operator = GetBaseInfo.getBossUser() == null ? null : GetBaseInfo.getBossUser().getUserName();
					record.setAuditor(operator);
					Date date = new Date();
					record.setAuditTime(date);
					if(purchase.getFirstAuditTime() == null){
						record.setFirstAuditTime(date);
					}
					record.setUpdateTime(date);
					record.setStatus(Integer.valueOf(auditStatus));
					record.setRejectReason(remark);
					busiPurchaseDao.updateByPrimaryKeySelective(record);
					
					//更新成功了，向采购日志表里记录采购信息更新前的状态
					String optype = null;
					String rem = null;
					if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(auditStatus)){
						optype = BusinessLogEnum.PURCHASE_AUDIT_PASSED.getCode();
						rem = "采购信息审核通过";
					} else if(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode().equals(auditStatus)){
						optype = BusinessLogEnum.PURCHASE_AUDIT_UNPASSED.getCode();
						rem = "采购信息审核不通过";
					}
					busiPurchaseLogDao.insertByBusiPurchase(purchase, optype, operator, rem);
					return null;
				} catch (Exception e) {
					logger.error("BossPurchaseServiceImpl.changePurchaseAuditStatus:审核采购信息失败，错误信息：" + e.getMessage());
					status.setRollbackOnly();
					return setException(e);
				}
			}
		});
		
		if(e != null){
			throw e;
		} else {
			//审核通过的情况
			if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(auditStatus)){
				//向solr中发送采购信息
				RabbitmqProducerManager.getInstance().pushPurchaseMsgForSolr(purchaseId);
			}
			
			//发送短信及邮件通知
			List<BusiPurchase> list = null;
			try {
				list = busiPurchaseDao.selectPurchaseByCode(purchaseCode);
			} catch (Exception e2) {
				logger.error("BossPurchaseServiceImpl.changePurchaseAuditStatus:审核后查询同一批次的采购信息出错，错误信息：" + e2.getMessage());
			}
			if(list != null && list.size() > 0){
				//同一采购批次号内所有品种均审核了，才发送通知
				int auditedCount = 0;//已做过审核的个数
				int auditFailureCount = 0;//审核失败的个数
				for(BusiPurchase purchase : list){
					if(!BusiPurchaseStatusEnum.AUDIT_WAITING.getCode().equals(String.valueOf(purchase.getStatus()))){
						auditedCount++;
					}
					if(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode().equals(String.valueOf(purchase.getStatus()))){
						auditFailureCount++;
					}
				}
				if(auditedCount == list.size()){
					if(auditFailureCount > 0){
						sendMobileEmailMsg(purchaseId, BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode());
					} else {
						sendMobileEmailMsg(purchaseId, BusiPurchaseStatusEnum.OFFER_WAITING.getCode());
					}
				}
			}
		}
	}
	
	@Override
	public Page<BusiPurchaseVo> selectPurchaseManagePage(
			BossPurchaseManageQueryDto query) throws Exception {
		try {
			logger.info("BossPurchaseServiceImpl.selectPurchaseManagePage");
			Page<BusiPurchaseVo> page = new Page<BusiPurchaseVo>();
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("query", query);
			page.setParams(params);
			// 获取采购管理信息分页信息
			List<BusiPurchaseVo> results = busiPurchaseDao.selectPurchaseManagePage(page);
			page.setResults(results);
			return page;
		} catch (Exception e) {
			logger.error("BossPurchaseServiceImpl.selectPurchaseManagePage:获取后台采购管理信息列表失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

	@Override
	public Page<BusiQuote> selectPurchaseQuotePage(String purchaseId,
			Integer pageNo) throws Exception {
		try {
			logger.info("BossPurchaseServiceImpl.selectPurchaseQuotePage");
			Page<BusiQuote> page = new Page<BusiQuote>();
			page.setPageNo(pageNo);
			page.setPageSize(5);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("purchaseId", purchaseId);
			BusiPurchase purchase = busiPurchaseDao.selectByPrimaryKey(purchaseId);
			if(StringUtils.isNotEmpty(purchase.getAdoptedQuoteId())){
				params.put("dealOk", true);
			} else {
				params.put("dealOk", false);
			}
			page.setParams(params);
			// 获取采购信息的报价分页信息
			List<BusiQuote> results = busiQuoteDao.selectQuotePageByPurchaseId(page);
			page.setResults(results);
			return page;
		} catch (Exception e) {
			logger.error("BossPurchaseServiceImpl.selectPurchaseQuotePage:获取采购信息的报价分页信息失败，错误信息：" + e.getMessage());
			throw setException(e);
		}
	}

	@Override
	public void purchaseDealSuccess(final String purchaseId, final String quoteId)
			throws Exception {
		logger.info("BossPurchaseServiceImpl.purchaseDealSuccess");
		Exception e = getTransactionTemplate().execute(new TransactionCallback<Exception>() {
			@Override
			public Exception doInTransaction(TransactionStatus status) {
				try {
					//查询采购信息
					BusiPurchase purchase = busiPurchaseDao.selectByPrimaryKey(purchaseId);
					if(purchase == null) {
						throw new BossErrorException(ErrorRepository.NOT_EXIST, "采购信息");
					}
					Date date = new Date();
					String operator = GetBaseInfo.getBossUser() == null ? null : GetBaseInfo.getBossUser().getUserName();
					
					//更新采购信息状态为达成交易，及其他相关信息
					BusiPurchase record = new BusiPurchase();
					record.setPurchaseId(purchaseId);
					record.setOperator(operator);
					record.setUpdateTime(date);
					record.setStatus(Integer.valueOf(BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode()));
					record.setAdoptedQuoteId(quoteId);
					busiPurchaseDao.updateByPrimaryKeySelective(record);
					
					//更新成功了，向采购日志表里记录采购信息更新前的状态
					busiPurchaseLogDao.insertByBusiPurchase(purchase, BusinessLogEnum.PURCHASE_DEAL_SUCCESS.getCode(), operator, "交易员后台设置交易成功");
					
					//将已选中的报价标记为已完成
					BusiQuote quote = new BusiQuote();
					quote.setOperator(operator);
					quote.setStatus(Integer.valueOf(BusiQuoteStateEnum.DEAL_SUCCESS.getCode()));
					quote.setUpdateTime(date);
					quote.setQuoteId(quoteId);
					busiQuoteDao.updateByPrimaryKeySelective(quote);
					//将采购信息下除已选中的报价外，全更新为已结束
					BusiQuote quoteEx = new BusiQuote();
					quoteEx.setOperator(operator);
					quoteEx.setStatus(Integer.valueOf(BusiQuoteStateEnum.FINISHED.getCode()));
					quoteEx.setUpdateTime(date);
					quoteEx.setQuoteId(quoteId);
					quoteEx.setPurchaseId(purchaseId);
					busiQuoteDao.updateQuoteByPurchaseIdAndQuoteId(quoteEx);
					
					return null;
				} catch (Exception e) {
					logger.error("BossPurchaseServiceImpl.purchaseDealSuccess:设置采购信息交易成功失败，错误信息：" + e.getMessage());
					status.setRollbackOnly();
					return setException(e);
				}
			}
		});
		
		if(e != null){
			throw e;
		} else {
			//采购交易成功，向solr中发送采购信息
			RabbitmqProducerManager.getInstance().pushPurchaseMsgForSolr(purchaseId);
			//发送短信和邮件通知
			sendMobileEmailMsg(purchaseId, BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode());
		}
	}

	/**
	 * @Description: 发送邮件短信通知
	 * @Author: 赵航
	 * @Date: 2015年10月16日
	 * @param purchaseId
	 */
	private void sendMobileEmailMsg(String purchaseId, String sendTpye){
		try {
			BusiPurchaseMobileEmailMsgVo vo = busiPurchaseDao.selectPurchaseMobileEmailMsgById(purchaseId);
			//短信通知采购方
			if(StringUtils.isNotEmpty(vo.getPurchaserMobile())){
				try {
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(vo.getPurchaserMobile(),
							GetMessageContext.getPurchaseMobileMsg(vo, sendTpye)));
				} catch (Exception e) {
					logger.error("短信通知采购方出错，错误信息：" + e.getMessage());
				}
			}
			
			//审核通过时，要给交易员也发短信
			if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(sendTpye)){
				String tradersMobile = "";
				if(StringUtils.isNotEmpty(vo.getTradersMobile())){
					tradersMobile = vo.getTradersMobile();
				} else {
					tradersMobile = SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone");
				}
				try {
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(tradersMobile,
							GetMessageContext.getPurchaseTradeMobileMsg(vo, sendTpye)));
				} catch (Exception e) {
					logger.error("短信通知采购方出错，错误信息：" + e.getMessage());
				}
			}
			
			//邮件通知产品部周亮、产品部严飞、产品部何欢、交易员
			String emailTitle = "";
			if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(sendTpye)){
				emailTitle = GetEmailContext.EMAIL_PURCHASE_AUDIT_SUCCESS;
			} else if(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode().equals(sendTpye)){
				emailTitle = GetEmailContext.EMAIL_PURCHASE_AUDIT_FAILURE;
			} else if(BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode().equals(sendTpye)){
				emailTitle = GetEmailContext.EMAIL_PURCHASE_DEAL_SUCCESS;
			}
			
			String emailAddrs = GetEmailContext.getCaigouEmail();//采购邮件组
			try {
				taskExecutor.execute(new EmailTaskSend(emailTitle, emailAddrs,
						GetEmailContext.getPurchaseEmailMsg(vo, sendTpye)));
			} catch (Exception e) {
				logger.error("邮件通知采购组出错，错误信息：" + e.getMessage());
			}
			
			if(StringUtils.isNotEmpty(vo.getTradersEmail())){
				try {
					taskExecutor.execute(new EmailTaskSend(emailTitle, vo.getTradersEmail(),
							GetEmailContext.getPurchaseEmailMsg(vo, sendTpye)));
				} catch (Exception e) {
					logger.error("邮件通知交易员出错，错误信息：" + e.getMessage());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
