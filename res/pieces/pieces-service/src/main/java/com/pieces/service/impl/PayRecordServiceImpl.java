package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayRecordDao;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.*;
import com.pieces.dao.vo.AccountBillVo;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderFormService;
import com.pieces.service.PayDocumentService;
import com.pieces.service.PayRecordService;
import com.pieces.service.*;
import com.pieces.service.enums.PathEnum;
import com.pieces.tools.utils.FileUtil;
import com.pieces.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PayRecordServiceImpl  extends AbsCommonService<PayRecord> implements PayRecordService{

	@Autowired
	private PayRecordDao payRecordDao;
	@Autowired
	private AccountBillService accountBillService;
	@Autowired
	private PayAccountService payAccountService;
	@Autowired
	private PayDocumentService payDocumentService;

	@Autowired
	private OrderFormService orderFormService;

	@Autowired
	private SmsService smsService;



	@Override
	public PageInfo<PayRecordVo> findByParams(PayRecordVo payRecordVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
    	List<PayRecordVo>  list = payRecordDao.findByParams(payRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public List<PayRecordVo> findByParams(PayRecordVo payRecordVo) {
		List<PayRecordVo>  list = payRecordDao.findByParams(payRecordVo);
		return list;
	}


	@Override
	public PageInfo<PayRecordVo> findByNormalRecord(Integer pageNum, Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		List<PayRecordVo>  list = payRecordDao.findByNormalRecord();
		PageHelper.startPage(pageNum, pageSize);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public PageInfo<PayRecordVo> findByNormalRecord(PayRecordVo payRecordVo, Integer pageNum, Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		List<PayRecordVo>  list = payRecordDao.findByNormalRecord(payRecordVo);
		PageHelper.startPage(pageNum, pageSize);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	@Transactional
	public PayRecord createForBill(PayRecordVo payRecordVo, String[] imgs,Integer userId){
		Integer billId = payRecordVo.getAccountBillId();
		Integer orderId =	payRecordVo.getOrderId();
		AccountBill accountBill =	accountBillService.findById(billId);
		payRecordVo.setAmountsPayable(accountBill.getUnPayable());

		OrderForm orderForm = orderFormService.findById(orderId);
		payRecordVo.setOrderCode(orderForm.getCode());

		//添加收款账户信息
		PayAccount payAccount = payAccountService.findById(payRecordVo.getPayAccountId());
		payRecordVo.setReceiveAccount(payAccount.getReceiveAccount());
		payRecordVo.setReceiveBank(payAccount.getReceiveBank());
		payRecordVo.setReceiveBankCard(payAccount.getReceiveBankCard());


		//其他信息
		payRecordVo.setUserId(userId);
		payRecordVo.setPaymentTime(new Date());
		payRecordVo.setStatus(0);
		payRecordVo.setCreateTime(new Date());
		payRecordDao.create(payRecordVo);
		//生成支付流水号
		payRecordVo.setPayCode(SeqNoUtil.get("", payRecordVo.getId(), 6));
		payRecordDao.update(payRecordVo);

		//添加支付凭证
		createPayDocument(payRecordVo.getId(),imgs);


		return payRecordVo;
	}






	@Override
	@Transactional
	public PayRecord create(PayRecordVo payRecordVo, String[] imgs,Integer userId) {
		Integer orderId =	payRecordVo.getOrderId();
		//订单信息
		OrderForm orderForm = orderFormService.findById(orderId);
		payRecordVo.setOrderCode(orderForm.getCode());
		payRecordVo.setAmountsPayable(orderForm.getAmountsPayable());
		payRecordVo.setDeposit(orderForm.getDeposit());


		//添加收款账户信息
		PayAccount payAccount = payAccountService.findById(payRecordVo.getPayAccountId());
		payRecordVo.setReceiveAccount(payAccount.getReceiveAccount());
		payRecordVo.setReceiveBank(payAccount.getReceiveBank());
		payRecordVo.setReceiveBankCard(payAccount.getReceiveBankCard());

		//其他信息代理商支付不能用userId
		if(payRecordVo.getUserId()==null){
			payRecordVo.setUserId(userId);
		}
		payRecordVo.setPaymentTime(new Date());
		payRecordVo.setStatus(0);
		payRecordVo.setCreateTime(new Date());
		payRecordDao.create(payRecordVo);
		//生成支付流水号
		payRecordVo.setPayCode(SeqNoUtil.get("", payRecordVo.getId(), 6));
		payRecordDao.update(payRecordVo);

		createPayDocument(payRecordVo.getId(),imgs);
		// 支付添加成功后把订单状态改成付款待确认
		orderFormService.changeOrderStatus(orderId, OrderEnum.VERIFY.getValue());
		return payRecordVo;
	}

	@Override
	public PayRecordVo findVoById(Integer id) {
		PayRecordVo payRecordVo = new PayRecordVo();
		payRecordVo.setId(id);
		List<PayRecordVo> list = payRecordDao.findByParams(payRecordVo);
		payRecordVo = list.size() >= 1 ? list.get(0) : null;

		if (payRecordVo != null) {
			payRecordVo.setImgs(payDocumentService.findByPayId(id));
		}
		return payRecordVo;
	}
	@Transactional
	@Override
	public void success(Integer payId, Member member) {
		PayRecord payRecord = new PayRecord();
		payRecord.setId(payId);
		payRecord.setMemberId(member.getId());
		payRecord.setStatus(1);
		payRecord.setOperationTime(new Date());
		payRecordDao.update(payRecord);
		payRecord = payRecordDao.findById(payId);
		//改变订单状态
		orderFormService.changeOrderStatus(payRecord.getOrderId(), OrderEnum.WAIT_DELIVERY.getValue());

		OrderFormVo orderFormVo = orderFormService.findVoById(payRecord.getOrderId());

		// 支付成功发送短信
		if (payRecord.getAccountBillId() != null) {
			accountBillService.refreshStatus(payRecord.getAccountBillId());
			smsService.sendPayAccountSuccess(orderFormVo.getUser().getContactName(),payRecord.getActualPayment(),
					orderFormVo.getUser().getContactMobile());
		} else {
			smsService.sendPaySuccess(orderFormVo.getUser().getContactName(),payRecord.getActualPayment(),
					orderFormVo.getUser().getContactMobile());
		}
		//为代理商用户生成三个月账期
		if(payRecord.getAgentId()!=null){
			accountBillService.generateBill(payRecord,member.getId());
		}

}

	@Transactional
	@Override
	public void fail(Integer payId, String msg, Member member) {
		PayRecord payRecord = new PayRecord();
		payRecord.setId(payId);
		payRecord.setMemberId(member.getId());
		payRecord.setStatus(2);
		payRecord.setOperationTime(new Date());
		payRecord.setFailReason(msg);
		payRecordDao.update(payRecord);
		payRecord = payRecordDao.findById(payId);
		//改变订单状态
		orderFormService.changeOrderStatus(payRecord.getOrderId(), OrderEnum.CANCEL.getValue());

		// 支付失败发送短信
		OrderFormVo orderFormVo = orderFormService.findVoById(payRecord.getOrderId());
		smsService.sendPayFail(orderFormVo.getUser().getContactName(),payRecord.getActualPayment(),
				orderFormVo.getUser().getContactMobile());
	}

	@Override
	public PageInfo<PayRecordVo> findByUserId(PayRecordVo payRecordVo, Integer pageNum, Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		List<PayRecordVo>  list = payRecordDao.findByUserId(payRecordVo);
		PageHelper.startPage(pageNum, pageSize);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public Integer getNotHandleCount() {
		return payRecordDao.getNotHandleCount();
	}

	@Override
	public ICommonDao<PayRecord> getDao() {
		return payRecordDao;
	}


	private void createPayDocument(Integer payRecordId,String[] imgs){
		//添加支付凭证
		if(imgs!=null){
			for(String img : imgs){
				PayDocument payDocument = new PayDocument();
				payDocument.setPayRecordId(payRecordId);
				payDocument.setCreateDate(new Date());
				payDocument.setPath(FileUtil.saveFileFromTemp(img, PathEnum.COMMODITY.getValue()));
				payDocumentService.create(payDocument);
			}
		}
	}
}
