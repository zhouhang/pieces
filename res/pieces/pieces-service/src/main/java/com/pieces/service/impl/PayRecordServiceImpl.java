package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PayDocumentDao;
import com.pieces.dao.PayRecordDao;
import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.Member;
import com.pieces.dao.model.PayDocument;
import com.pieces.dao.model.PayRecord;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.OrderFormService;
import com.pieces.service.PayDocumentService;
import com.pieces.service.PayRecordService;
import com.pieces.service.constant.bean.Result;
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
	private PayDocumentService payDocumentService;

	@Autowired
	private OrderFormService orderFormService;


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
		//改变订单状态 TODO:
		orderFormService.changeOrderStatus(payRecord.getOrderId(), OrderEnum.WAIT_DELIVERY.getValue());
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
		//改变订单状态 TODO:
		orderFormService.changeOrderStatus(payRecord.getOrderId(), OrderEnum.CANCEL.getValue());
	}

	@Override
	public ICommonDao<PayRecord> getDao() {
		return payRecordDao;
	}

}
