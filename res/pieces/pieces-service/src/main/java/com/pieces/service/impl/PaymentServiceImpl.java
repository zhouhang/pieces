package com.pieces.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pieces.dao.ICommonDao;
import com.pieces.dao.PaymentDao;
import com.pieces.dao.enums.PayEnum;
import com.pieces.dao.enums.PayTypeEnum;
import com.pieces.dao.model.Payment;
import com.pieces.dao.model.User;
import com.pieces.dao.vo.OrderFormVo;
import com.pieces.dao.vo.PayRecordVo;
import com.pieces.dao.vo.PaymentVo;
import com.pieces.dao.vo.UserVo;
import com.pieces.service.*;
import com.pieces.tools.utils.SeqNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl  extends AbsCommonService<Payment> implements PaymentService{

	@Autowired
	private PaymentDao paymentDao;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderFormService orderFormService;

	@Autowired
	private PayRecordService payRecordService;


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
	@Transactional
	public void save(Payment payment) {
		Date now=new Date();
		if(payment.getId()!=null){
			paymentDao.update(payment);
		}
		else{
			payment.setCreateTime(now);
			paymentDao.create(payment);
			payment.setOutTradeNo(SeqNoUtil.get("P", payment.getId(), 6));
			paymentDao.update(payment);
		}
	}

	@Override
	@Transactional
	public void handleResult(Map<String, String> params) {
		String trade_status=params.get("trade_status");
		String out_trade_no=params.get("out_trade_no");
		String trade_no=params.get("trade_no");
		PaymentVo paymentVo=new PaymentVo();
		paymentVo.setOutTradeNo(out_trade_no);
		PaymentVo oldPayment=getByVo(paymentVo);
		if(oldPayment!=null&&oldPayment.getCallbackTime()==null&&oldPayment.getStatus()== PayEnum.UNPAID.getValue()){
			oldPayment.setTradeNo(trade_no);
			oldPayment.setCallbackTime(new Date());
			oldPayment.setInParam(params.toString());
			//支付成功
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				oldPayment.setStatus(PayEnum.SUCCESS.getValue());

			}//支付失败
			else{
				oldPayment.setStatus(PayEnum.FAIL.getValue());

			}
			save(oldPayment);
			PayRecordVo payRecordVo=new PayRecordVo();
			UserVo user=userService.findVoById(oldPayment.getUserId());
			OrderFormVo orderForm = orderFormService.findVoById(oldPayment.getOrderId());
			payRecordVo.setOrderId(orderForm.getId());
			payRecordVo.setPaymentId(oldPayment.getId());
			payRecordVo.setActualPayment(oldPayment.getMoney());
			payRecordVo.setPayType(PayTypeEnum.ALIPAY.getValue());

			payRecordVo.setUserId(orderForm.getUserId());
			//代理商的话加上agentId
			if(user.getType()==2){
				payRecordVo.setAgentId(oldPayment.getUserId());
			}

			payRecordService.paySuccess(payRecordVo);


		}

	}


	@Override
	public ICommonDao<Payment> getDao() {
		return paymentDao;
	}

}
