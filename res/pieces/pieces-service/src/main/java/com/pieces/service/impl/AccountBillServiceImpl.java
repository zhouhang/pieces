package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.AccountBillDao;
import com.pieces.dao.model.AccountBill;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.AccountBillVo;
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

	@Override
	public PageInfo<AccountBillVo> findByParams(AccountBillVo accountBillVo,Integer pageNum,Integer pageSize) {
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
	public ICommonDao<AccountBill> getDao() {
		return accountBillDao;
	}


}
