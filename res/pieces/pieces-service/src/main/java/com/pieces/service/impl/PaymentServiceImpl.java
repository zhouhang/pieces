package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PaymentDao;
import com.pieces.dao.model.Payment;
import com.pieces.dao.vo.PaymentVo;
import com.pieces.service.AbsCommonService;
import com.pieces.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentServiceImpl  extends AbsCommonService<Payment> implements PaymentService{

	@Autowired
	private PaymentDao paymentDao;


	@Override
	public PageInfo<PaymentVo> findByParams(PaymentVo paymentVo,Integer pageNum,Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    	List<PaymentVo>  list = paymentDao.findByParams(paymentVo);
        PageInfo page = new PageInfo(list);
        return page;
	}

	@Override
	public PaymentVo getByVo(PaymentVo paymentVo) {
		List<PaymentVo>  list = paymentDao.findByParams(paymentVo);
		if(list.size()!=0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public ICommonDao<Payment> getDao() {
		return paymentDao;
	}

}
