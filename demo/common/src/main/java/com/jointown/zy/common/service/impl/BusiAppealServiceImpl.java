package com.jointown.zy.common.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.jointown.zy.common.dao.BossUserDao;
import com.jointown.zy.common.dao.BreedDao;
import com.jointown.zy.common.dao.BusiAppealDao;
import com.jointown.zy.common.dao.BusiOrderDao;
import com.jointown.zy.common.dao.BusiOrderLogDao;
import com.jointown.zy.common.dao.BusiOrderSalesmanDao;
import com.jointown.zy.common.dao.BusiWhlistDao;
import com.jointown.zy.common.dao.UcUserDao;
import com.jointown.zy.common.enums.BusiOrderStateEnum;
import com.jointown.zy.common.enums.BusinessLogEnum;
import com.jointown.zy.common.messageconfig.MessageConfigManage;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiAppeal;
import com.jointown.zy.common.model.BusiOrder;
import com.jointown.zy.common.model.BusiOrderSalesman;
import com.jointown.zy.common.model.UcUser;
import com.jointown.zy.common.service.BusiAppealService;
import com.jointown.zy.common.task.EmailTaskSend;
import com.jointown.zy.common.util.GetEmailContext;
import com.jointown.zy.common.util.GetMessageContext;
import com.jointown.zy.common.vo.BusiAppealVo;

/**
 * 
 * @ClassName: BusiAppealServiceImpl
 * @Description: 订单申诉ServiceImpl
 * @Author: wangjunhu
 * @Date: 2015年4月9日
 * @Version: 1.0
 */
@Service
public class BusiAppealServiceImpl implements BusiAppealService {
	private static final Logger log = LoggerFactory
			.getLogger(BusiAppealService.class);
	
	@Autowired 
	private BusiAppealDao busiAppealDao;
	
	@Autowired 
	private BusiOrderDao busiOrderDao;
	
	@Autowired 
	private UcUserDao ucUserDao;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private BusiOrderLogDao busiOrderLogDao;
	
	@Autowired
	private MessageConfigManage messageConfigManage;
	
	@Autowired
	public BreedDao breedDao;
	
	@Autowired
	public BossUserDao bossUserDao;
	
	@Autowired
	public BusiOrderSalesmanDao busiOrderSalesmanDao;
	
	@Autowired
	private BusiWhlistDao busiWhlistDao;
	
	@Override
	public BusiAppealVo findBusiAppealVoByOrderId(Map<String,Object> map) {
		return busiAppealDao.selectBusiAppealVoByOrderId(map);
	}

	@Override
	public int inserBusiAppeal(BusiAppeal busiAppeal) {
		//插入申述数据
		busiAppealDao.insertBusiAppeal(busiAppeal);
		//修改订单APPEALSTATE为1
		BusiOrder busiOrder = new BusiOrder();
		busiOrder.setOrderid(busiAppeal.getOrderId());
		busiOrder.setOrderstate(Integer.parseInt(BusiOrderStateEnum.CANCELED_ORDER.getCode()));
		int state = busiOrderDao.updateOrderAppealState(busiOrder);
		
		busiOrder  = busiOrderDao.selectBusiOrderById(busiAppeal.getOrderId());
		busiOrderLogDao.insertBusiOrderLog(busiOrder, "买家申请退款", busiOrder.getBuyer(), BusinessLogEnum.ORDER_APPLY_REIMBURSE.getCode());
		try{
			//给卖家发短信
			UcUser seller = ucUserDao.getUcUserById(busiOrder.getUserid()+"");
			taskExecutor.execute(messageConfigManage.getMessageChannelTask(seller.getMobile(), GetMessageContext.getApplyCancelOrderMsg(busiOrder.getOrderid())));
			//给运营发邮件
			taskExecutor.execute(new EmailTaskSend(GetEmailContext.EMAIL_SUBJECT_FREEZE_SUCCESS, GetEmailContext.getListingOperatorEmail(), GetEmailContext.getApplyCancelOrderMsg(busiOrder.getOrderid())));
			//added by biran 20151020 申退时给业务员发送短信
			AppealSendMsg4SalesMans(busiAppeal.getOrderId(),busiOrder.getWlid(),busiOrder.getUserid(),busiOrder.getBuyer());
		}catch(Exception e){
			log.error("申诉成功给卖家发短信，给运营发邮件异常，"+e.getMessage(), e);
		}
		return state;
	} 
	
	
	/*申请退款时，给业务员发短信*/
	private void AppealSendMsg4SalesMans(String orderId,String wlId,Long sellerId,Long BuyerId){
		try {
			BusiOrderSalesman salesmans=busiOrderSalesmanDao.selectByOrderid(orderId);//获取到订单的对应的业务员信息
			if(salesmans!=null){
				Long breedCode=busiWhlistDao.selectWhlistByWlId(wlId).getBreedCode();//品种信息
				String breedName=breedDao.selectByPrimaryKey(breedCode).getBreedName();
				if(salesmans.getSellerSalesmanid()>new Long(0)){//卖家业务员不为空时
					BossUser sellerInfo = bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid());//卖家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(sellerId);//卖家认证名称
					String UserName=ucUserDao.findUcUserById(sellerId.intValue()).getUserName();//卖家用户名
					String buyerSalesManName="";//买家业务员名称
					if(salesmans.getBuyerSalesmanid()>new Long(0) ){
						buyerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(sellerInfo.getMobile(),
					GetMessageContext.getApplyCancelOrderMsg4SellerSalesMan(orderId,realName+"("+UserName+")",breedName,buyerSalesManName),
							"订单[" + orderId + "]申请退款，给卖家业务员发送短信通知出错，错误信息是："));
				}
				if(salesmans.getBuyerSalesmanid()>new Long(0)){//买家业务员不为空时
					BossUser buyerInfo = bossUserDao.getBossUserByUserId(salesmans.getBuyerSalesmanid());//买家关联的业务员信息
					String realName=ucUserDao.getCertifyNameByUserId(BuyerId);//买家认证名称
					String UserName=ucUserDao.findUcUserById(BuyerId.intValue()).getUserName();//买家用户名
					String sellerSalesManName="";//买家业务员名称
					if(salesmans.getSellerSalesmanid()>new Long(0) ){
						sellerSalesManName=  bossUserDao.getBossUserByUserId(salesmans.getSellerSalesmanid()).getUserName();
					}
					taskExecutor.execute(messageConfigManage.getMessageChannelTask(buyerInfo.getMobile(),
					GetMessageContext.getApplyCancelOrderMsg4BuyerSalesMan(orderId,realName+"("+UserName+")",breedName,sellerSalesManName),
							"订单[" + orderId + "]申请退款，给买家业务员发送短信通知出错，错误信息是："));
				}
			}
		} catch (Exception e) {
			log.error("申请退款,给业务员发送发短信失败："+e.getMessage());
		} finally{
		}
	}
	
}
