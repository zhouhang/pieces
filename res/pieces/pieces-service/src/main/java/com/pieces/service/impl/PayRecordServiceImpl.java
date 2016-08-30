package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayRecordDao;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.PayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;

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
	}

	@Override
	public ICommonDao<PayRecord> getDao() {
		return payRecordDao;
	}

}
