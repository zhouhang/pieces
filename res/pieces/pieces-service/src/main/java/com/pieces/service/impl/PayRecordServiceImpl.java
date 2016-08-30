package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayRecordDao;
import com.pieces.dao.model.OrderForm;
import com.pieces.dao.model.PayAccount;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;
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
	private OrderFormService orderFormService;
	@Autowired
	private PayAccountService payAccountService;
	@Autowired
	private PayDocumentService payDocumentService;

	@Override
	public PageInfo<PayRecordVo> findByParams(PayRecordVo payRecordVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PayRecordVo>  list = payRecordDao.findByParams(payRecordVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	@Transactional
	public PayRecord create(PayRecordVo payRecordVo, String[] imgs,Integer userId) {
		Integer orderId =	payRecordVo.getOrderId();
		//订单信息
		OrderForm orderForm = orderFormService.findById(orderId);
		payRecordVo.setOrderCode(orderForm.getCode());
		payRecordVo.setAmountsPayable(orderForm.getAmountsPayable());

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

		for(String img : imgs){
			PayDocument payDocument = new PayDocument();
			payDocument.setPayRecordId(payRecordVo.getId());
			payDocument.setCreateDate(new Date());
			payDocument.setPath(FileUtil.saveFileFromTemp(img, PathEnum.COMMODITY.getValue()));
			payDocumentService.create(payDocument);
		}

		return payRecordVo;
	}


	@Override
	public ICommonDao<PayRecord> getDao() {
		return payRecordDao;
	}

}
