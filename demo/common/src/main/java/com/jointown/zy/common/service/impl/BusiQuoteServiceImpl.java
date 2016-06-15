package com.jointown.zy.common.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.enums.BusiQuoteStateEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BusiQuote;
import com.jointown.zy.common.model.Page;
import com.jointown.zy.common.rabbitmq.RabbitmqProducerManager;
import com.jointown.zy.common.service.BaseService;
import com.jointown.zy.common.service.BusiQuoteService;
import com.jointown.zy.common.util.EmailUtils;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.util.SpringUtil;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;
import com.jointown.zy.common.vo.BusiPurchaseVo;
import com.jointown.zy.common.vo.BusiQuoteVo;
import com.jointown.zy.common.dto.BusiQuoteDto;
import com.jointown.zy.common.dao.BusiQuoteLogDao;
/**
 * 仓单挂牌所有业务ServiceImpl
 * @author Mr.songwei
 * 2014-12-27
 */
@Service
public class BusiQuoteServiceImpl extends BaseService implements BusiQuoteService {
	private static Logger logger = LoggerFactory.getLogger(BusiQuoteServiceImpl.class);
	@Autowired
	private BusiPurchaseDao busipurchaseDao;

	@Autowired
	private BusiQuoteDao busiQuoteDao;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private MessageConfigManage messageConfigManage;
	/**
	 * 报价操作日志数据dao
	 * @date 
	 */
	@Autowired
	private BusiPurchaseLogDao busiPurchaseLogDao;
	
	@Override
	public List<BusiQuoteVo> selectQuoteListByCondition(Page<BusiQuoteVo> page){
		return busiQuoteDao.selectQuoteListByCondition(page);
	}
	@Override
	public BusiQuote quoteReturn(final BusiQuoteDto busiQuoteDto,HttpServletRequest request) {
		logger.info("BusiQuoteServiceImpl.quoteReturn method");
		//根据采购批次号获取 此报价之前的  所有报价  （按价格升序排列）
		 List<BusiQuote> QuoteList=busiQuoteDao.selectQuoteByPurchaseCode(busiQuoteDto.getPurchaseCode());
		 
		BusiQuote busiQuote= getTransactionTemplate().execute(new TransactionCallback<BusiQuote>() {
             						
			@Override
			public BusiQuote doInTransaction(TransactionStatus status) {
				BusiQuote quote=null;
			try {
				//判断采购信息是否是 10：等待报价，20：洽谈中
				//BusiPurchaseVo busPur=busipurchaseDao.selectPurchaseDetail(busiQuoteDto.getPurchaseId());
				//if(!BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(String.valueOf(busPur.getStatus()))&&!BusiPurchaseStatusEnum.NEGOTIATING.getCode().equals(String.valueOf(busPur.getStatus()))){
				if(!BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(String.valueOf(busiQuoteDto.getPurchaseStatus()))&&!BusiPurchaseStatusEnum.NEGOTIATING.getCode().equals(String.valueOf(busiQuoteDto.getPurchaseStatus()))){	
				return null;
				}
				quote=new BusiQuote();
				quote.setPurchaseId(busiQuoteDto.getPurchaseId());
				quote.setPurchaseCode(busiQuoteDto.getPurchaseCode());
				quote.setQuotePrice(new BigDecimal(busiQuoteDto.getQuotePrice()));
				quote.setQuoteDescription(busiQuoteDto.getQuoteDescription()==null?"":busiQuoteDto.getQuoteDescription());
				quote.setQuoter(busiQuoteDto.getQuoter());
				quote.setPhone(busiQuoteDto.getPhone());
//				Date date = new Date();
//				quote.setCreateTime(date);
				int rec=busiQuoteDao.insertQuote(quote);
				if(rec>0){
					//报价成功后 修改 对应采购信息状态（若采购信息是 10：等待报价 则 改变状态为 20：洽谈中）
					if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(String.valueOf(busiQuoteDto.getPurchaseStatus()))){
						boolean staflg=busipurchaseDao.negotiaPurchaseByPurchaseCode(busiQuoteDto.getPurchaseId());
						}
				}
				else
				{
					return null;
				}
				}catch (Exception e) {
					//logger.error("BusiGoodsDetailsServiceImpl.buyGoodsOrderReturnOrder error is "+e.toString());
					logger.error("BusiQuoteServiceImpl.quoteReturn error is "+e.toString());
					status.setRollbackOnly();
					return null;
				}
	
				return quote;
			}
			});
		if(busiQuote!=null){
		//获取 邮件短信相关信息
		BusiPurchaseMobileEmailMsgVo busPurchase=busipurchaseDao.selectPurchaseMobileEmailMsgById(busiQuoteDto.getPurchaseId());
		 String tradename = busPurchase.getTradersName();
		 if(StringUtils.isEmpty(tradename)){
			 tradename=SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.name");
		 }
		 String tradePhone = busPurchase.getTradersMobile();
		 if(StringUtils.isEmpty(tradePhone)){
			 tradePhone=SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.phone");
		 }
		 String tradeEmail=busPurchase.getTradersEmail();
		 if(StringUtils.isEmpty(tradeEmail)){
			 tradeEmail=SpringUtil.getConfigProperties("jointown.busi.purchase.salesman.email");
		 }
		//首次报价  邮件通知相关人员
		if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(String.valueOf(busiQuoteDto.getPurchaseStatus()))){
			//修改SOLR中采购索引数据
			RabbitmqProducerManager.getInstance().pushPurchaseMsgForSolr(busiQuoteDto.getPurchaseId());
			//首条报价 发送邮件给相关交易员，采购跟踪组
			
			String context = GetEmailContext.getFirstQuoteEmailMsg(busPurchase.getPurchaser(), busPurchase.getPurchaserMobile(),
					busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),busPurchase.getBreedName(),busiQuoteDto.getCompanyName(),busiQuoteDto.getPhone());
			EmailUtils.sendMail(GetEmailContext.EMAIL_PURCHASE_FIRSTPRICE, tradeEmail , GetEmailContext.getCaigouEmail(), context);
			
			//发送短息给采购方
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(busPurchase.getPurchaserMobile(), GetMessageContext.getFirstQuoteMsg(busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),tradename,tradePhone,busPurchase.getBreedName())));
			//发送短息给交易员
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(tradePhone, GetMessageContext.getFirstQuoteMsgOfSale(busPurchase.getPurchaser(), busPurchase.getPurchaserMobile(),busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),busPurchase.getBreedName(),busiQuoteDto.getCompanyName(),busiQuoteDto.getPhone())));
			
		
		
		}
		if(BusiPurchaseStatusEnum.NEGOTIATING.getCode().equals(String.valueOf(busiQuoteDto.getPurchaseStatus()))){
			//List<BusiQuote> QuoteList=busiQuoteDao.selectQuoteByPurchaseCode(busiQuoteDto.getPurchaseCode());
			BigDecimal minprice=new BigDecimal(busiQuoteDto.getQuotePrice());
			if(QuoteList!=null && QuoteList.get(0).getQuotePrice().compareTo(minprice)>0){
				//采购单有更低的报价产生时，短信通知采购方，邮件通知交易员，采购跟踪组
				String context = GetEmailContext.getMinQuoteEmailMsg(busPurchase.getPurchaser(), busPurchase.getPurchaserMobile(),
						busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),busPurchase.getBreedName(),busiQuoteDto.getCompanyName(),busiQuoteDto.getPhone());
				EmailUtils.sendMail(GetEmailContext.EMAIL_PURCHASE_MINPRICE, tradeEmail , GetEmailContext.getCaigouEmail(), context);
				
				//发送短息给采购方
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(busPurchase.getPurchaserMobile(), GetMessageContext.getMinQuoteMsg(busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),tradename,tradePhone,busPurchase.getBreedName())));
				//发送短息给交易员
				taskExecutor.execute(messageConfigManage.getMessageChannelTask(tradePhone, GetMessageContext.getMinQuoteMsgOfSale(busPurchase.getPurchaser(), busPurchase.getPurchaserMobile(),busPurchase.getPurchaseCode(),busiQuoteDto.getQuotePrice(),busPurchase.getBreedName(),busiQuoteDto.getCompanyName(),busiQuoteDto.getPhone())));
				
			}
		}
		}
		return busiQuote;
	}
	@Override
	public List<BusiQuote> selectQuotePageByPurchaseId(Page<BusiQuote> page) {
		return busiQuoteDao.selectQuotePageByPurchaseId(page);
	}
}
	