package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AccountBillDao;
import com.pieces.dao.enums.BillEnum;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.enums.PayEnum;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.AccountBillVo;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.AccountBillService;
import com.pieces.service.OrderFormService;
import com.pieces.service.PayRecordService;
import com.pieces.tools.utils.DateUtils;
import com.pieces.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AccountBillServiceImpl  extends AbsCommonService<AccountBill> implements AccountBillService{

	@Autowired
	private AccountBillDao accountBillDao;
	@Autowired
	private OrderFormService orderFormService;
	@Autowired
	private PayRecordService payRecordService;

	@Autowired
	private SmsService smsService;

	@Override
	public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo,Integer pageNum,Integer pageSize) {
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		PageHelper.startPage(pageNum, pageSize);
    	List<AccountBillVo>  list = accountBillDao.findByParams(accountBillVo);
        PageInfo page = new PageInfo(list);
        return page;
	}


	@Override
	@Transactional
	public AccountBill createBill(AccountBill accountBill) {
		OrderForm orderForm = orderFormService.findById(accountBill.getOrderId());
		accountBill.setAmountsPayable(orderForm.getAmountsPayable());
		accountBill.setUnPayable(orderForm.getAmountsPayable());
		accountBill.setAlreadyPayable(0.0);
		accountBill.setStatus(0);

		//计算还款时间
		Integer billTime = accountBill.getBillTime();
		Date repayTime = DateUtils.dateAddDay(new Date(),billTime);
		accountBill.setRepayTime(repayTime);
		accountBill.setCreateDate(new Date());
		this.create(accountBill);

		accountBill.setCode(SeqNoUtil.get("B", accountBill.getId(), 6));
		this.update(accountBill);
		// 改变订单状态
		orderFormService.changeOrderStatus(accountBill.getOrderId(), OrderEnum.VERIFY.getValue());
		return accountBill;
	}

	@Override
	public PageInfo<AccountBillVo> findVoAll(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<AccountBillVo>	 list = accountBillDao.findVoAll();
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public PageInfo<AccountBillVo> findVoAll(Integer userId, Integer pageNum, Integer pageSize) {
		AccountBillVo accountBillVo = new AccountBillVo();
		accountBillVo.setUserId(userId);
		PageHelper.startPage(pageNum, pageSize);
		List<AccountBillVo>	 list = accountBillDao.findVoAll(accountBillVo);
		PageInfo page = new PageInfo(list);
		return page;
	}

	@Override
	public AccountBillVo findVoById(Integer billId) {
		AccountBillVo accountBill =  accountBillDao.findVoById(billId);
		PayRecordVo payRecordVo = new PayRecordVo();
		payRecordVo.setAccountBillId(billId);
		payRecordVo.setStatus(1);
		List<PayRecordVo> list = payRecordService.findByParams(payRecordVo);
		accountBill.setPayRecordVoList(list);
		return accountBill;
	}

	@Override
	@Transactional
	public void auditSuccess(Integer billId, Integer memberId) {

		AccountBill temp = accountBillDao.findById(billId);

		// 约定还款时间(根据审核时间和 账期计算出来)
		AccountBill accountBill = new AccountBill();
		//账单状态 未完结
		accountBill.setStatus(1);
		accountBill.setMemberId(memberId);
		// 立账时间()
		accountBill.setOperateTime(new Date());
		accountBill.setId(billId);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, temp.getBillTime());
		accountBill.setRepayTime(calendar.getTime());
		accountBillDao.update(accountBill);
		// 改变订单状态
		orderFormService.changeOrderStatus(temp.getOrderId(), OrderEnum.WAIT_DELIVERY.getValue());

		//账期审核成功发送短信通知
		OrderFormVo orderFormVo = orderFormService.findVoById(temp.getOrderId());
		smsService.sendAccountSuccess(orderFormVo.getUser().getContactName(),temp.getAmountsPayable(),
				orderFormVo.getUser().getContactMobile());

	}

	@Override
	@Transactional
	public void auditFail(Integer billId, String msg, Integer memberId) {
		// 失败原因
		// 审核人审核时间
		AccountBill accountBill = new AccountBill();
		accountBill.setId(billId);
		accountBill.setOperateTime(new Date());
		accountBill.setMemberId(memberId);
		accountBill.setRemark(msg);
		// 更改状态拒绝
		accountBill.setStatus(-1);
		accountBillDao.update(accountBill);
		//改变订单状态
		AccountBill temp = accountBillDao.findById(billId);
		orderFormService.changeOrderStatus(temp.getOrderId(), OrderEnum.CANCEL.getValue());

		// 账期审核失败 发送短信通知
		OrderFormVo orderFormVo = orderFormService.findVoById(temp.getOrderId());
		smsService.sendAccountFail(orderFormVo.getUser().getContactName(),temp.getAmountsPayable(),
				orderFormVo.getUser().getContactMobile());
	}

	@Override
	@Transactional
	public void refreshStatus(Integer billId) {
		// 账单状态 和 已付未付金额
		AccountBillVo accountBillVo = findVoById(billId);
		if (accountBillVo.getStatus() == 1) {
			//已付
			Double alreadyPayable = 0D;
			for (PayRecordVo pay : accountBillVo.getPayRecordVoList()) {
				if (pay.getStatus() .equals( PayEnum.SUCCESS.getValue())) {
					alreadyPayable += pay.getActualPayment();
				}
			}
			//未付
			Double unPayable = accountBillVo.getAmountsPayable() - alreadyPayable;
			AccountBill accountBill = new AccountBill();
			accountBill.setId(billId);
			accountBill.setAlreadyPayable(alreadyPayable);
			accountBill.setUnPayable(unPayable);
			if (unPayable <= 0) {
				accountBill.setStatus(BillEnum.ALREADY_FINISH.getValue());
			}
			accountBillDao.update(accountBill);
		}
	}

	@Override
	@Transactional
	public void generateBill(PayRecord payRecord,Integer memberId) {
		AccountBill accountBill = new AccountBill();
		OrderForm orderForm = orderFormService.findById(payRecord.getOrderId());

		accountBill.setAmountsPayable(orderForm.getAmountsPayable());
		accountBill.setUnPayable(orderForm.getAmountsPayable());
		accountBill.setAlreadyPayable(0.0);

		accountBill.setUserId(payRecord.getUserId());
		accountBill.setOrderId(payRecord.getOrderId());
		accountBill.setBillTime(30);
		accountBill.setStatus(1);

		Integer billTime = accountBill.getBillTime();
		Date repayTime = DateUtils.dateAddDay(new Date(),billTime);
		accountBill.setRepayTime(repayTime);
		accountBill.setCreateDate(new Date());
		accountBill.setMemberId(memberId);
		accountBill.setOperateTime(new Date());

		this.create(accountBill);

		accountBill.setCode(SeqNoUtil.get("B", accountBill.getId(), 6));
		this.update(accountBill);

	}

	@Override
	public ICommonDao<AccountBill> getDao() {
		return accountBillDao;
	}




}
