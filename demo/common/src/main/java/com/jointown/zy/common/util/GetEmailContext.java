package com.jointown.zy.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.jointown.zy.common.constant.MessageConstant;
import com.jointown.zy.common.dto.BusiPurchaseApplyDto;
import com.jointown.zy.common.dto.BusiWarehouseApplyDto;
import com.jointown.zy.common.enums.ApiFlagEnums;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.BossUser;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.model.BusiPurchaseApply;
import com.jointown.zy.common.model.BusiWarehouseApply;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;
import com.jointown.zy.common.vo.BusiPurchaseVo;

/**
 * 获取邮件内容
 * author ldp
 * 2014年12月17日
 */
public class GetEmailContext {
	public static final String EMAIL_SUBJECT_FIND_PWD = "珍药材电商-修改密码";
	public static final String EMAIL_MEMBER_CERTIFY_PRESON="珍药材电商-个人认证";
	public static final String EMAIL_MEMBER_CERTIFY_COMPANY="珍药材电商-公司认证";
	public static final String EMAIL_LISTING_TRADE="珍药材电商-挂牌交易";
	public static final String EMAIL_MEMBER_ORDER="珍药材电商-买家下单";
	public static final String EMAIL_CANCER_LISTING="珍药材电商-取消挂牌";
	public static final String EMAIL_UPDATE_LISTING="珍药材电商-修改挂牌";
	public static final String EMAIL_USER_BUY="珍药材电商-买家下单";
	public static final String EMAIL_SUBJECT_FREEZE_SUCCESS="珍药材电商-完成备货信息";
	public static final String EMAIL_APPLY_CANCEL_ORDER="珍药材电商-客户申诉信息";
	public static final String EMAIL_PAY_DEPOSIT_ORDER="珍药材电商-保证金支付";
	public static final String EMAIL_PAY_BALANCE_ORDER="珍药材电商-尾款支付";
	public static final String EMAIL_ORDER_OVERTIME = "珍药材电商-订单过期信息";
	public static final String EMAIL_LISTING_OVERTIME = "珍药材电商-挂牌过期信息";
	public static final String EMAIL_LISTING_OVERTIME_WARNING = "珍药材电商-挂牌过期提醒";
	public static final String EMAIL_APPLY_PURCHASE = "珍药材电商-申请采购";
	public static final String EMAIL_APPLY_WAREHOUSE = "珍药材电商-申请入仓";
	//add by fanyuna 
	public static final String EMAIL_CALL_INTERFACE_FAIL = "珍药材电商-调用接口失败";
	//交易发送划账请求,等待财务确认
	public static final String EMAIL_INSERT_REMITFLOW = "珍药材电商-财务确认划账信息";
	//邮箱设置，邮件主题
	public static final String EMAIL_SET_SUBJECT = "设置安全邮箱";
	public static final String EMAIL_UPDATE_SUBJECT = "修改安全邮箱";
	public static final String MOBILE_UPDATE_SUBJECT = "修改手机号码";
	
	//add by fanyuna 2015.09.09 账期订单 过期邮件标题
	public static final String EMAIL_TERM_ORDER_SOON_OVERTIME = "账期订单即将到期";
	public static final String EMAIL_TERM_ORDER_OVERTIME = "账期订单已到期";
	public static final String EMAIL_TERM_ORDER_CANCEL = "账期订单已申请取消";
	
	//采购邮件标题
	public static final String EMAIL_PURCHASE_AUDIT_SUCCESS = "采购审核通过反馈";
	public static final String EMAIL_PURCHASE_AUDIT_FAILURE = "采购审核不通过反馈";
	public static final String EMAIL_PURCHASE_DEAL_SUCCESS = "完成采购提醒";
	
	public static final String EMAIL_PURCHASE_OVERTIME = "采购单结束通知";
	
	public static final String EMAIL_PURCHASE_MINPRICE="最新低价通知";
	
	public static final String EMAIL_PURCHASE_FIRSTPRICE="首条报价通知";
	
	public static final String EMAIL_PURCHASE_ADD="新增采购单提醒";
	
	public static final String EMAIL_PURCHASE_EDIT="重新提交采购单提醒";
	
	public static final String EMAIL_FULLPAYMENT_TITLE="买家实际付款大于应收款";
	
	public static final String EMAIL_FULLPAYMENT_ORDER="珍药材电商-全款支付";

	/**
	 * 获取找回密码时的邮件内容
	 * @param keyCode
	 * @return
	 */
	public static String getFindPwdEmailText(String keyCode){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们收到了您在中药材电商系统的找回密码请求，请点击以下链接进入密码修改页面：<br>");
		stringBuilder.append("http://uc.54315.com/findBackPwd/emailVerity?key=");
		stringBuilder.append(keyCode);
		stringBuilder.append("<br>如果您要放弃以上修改，或未曾申请密码重置，请忽略/或删除本邮件。<br>");
		stringBuilder.append("(如果链接无法直接点击，请复制上面的链接到您的浏览器地址栏打开)<br>");
		stringBuilder.append("【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。<br>");
		stringBuilder.append("此致<br>");
		stringBuilder.append("珍药材电商团队");
		return stringBuilder.toString();
	}
	
	/**
	 * 珍药材电商-个人认证(邮件通知客服)
	 * @param toPerson(收件人)
	 * @param memberName(注册会员的名称)
	 * @return
	 */
	public static String getMemberCertifyPersonText(String memberName){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("尊敬的客服人员：<br>");
		stringBuilder.append("&nbsp;&nbsp;您好!&nbsp;会员");
		stringBuilder.append(memberName);
		stringBuilder.append("已进行<font  color='red'>个人认证</font>，请尽快进行审核！<br>");
		stringBuilder.append("如您已审核，请忽略此邮件！<br>");
		stringBuilder.append("【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。<br>");
		stringBuilder.append("此致<br>");
		stringBuilder.append("珍药材电商团队");
		return stringBuilder.toString();
	}
	
	/**
	 * 珍药材电商-企业认证(邮件通知客服)
	 * @param toPerson(收件人)
	 * @param companyName(注册会员的名称)
	 * @return
	 */
	public static String getMemberCertifyCompanyText(String companyName){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("尊敬的客服人员：<br>");
		stringBuilder.append("&nbsp;&nbsp;您好!&nbsp;会员");
		stringBuilder.append(companyName);
		stringBuilder.append("已进行<font  color='red'>企业认证</font>，请尽快进行审核！<br>");
		stringBuilder.append("如您已审核，请忽略此邮件！<br>");
		stringBuilder.append("【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。<br>");
		stringBuilder.append("此致<br>");
		stringBuilder.append("珍药材电商团队");
		return stringBuilder.toString();
	}
	
	/**
	 * 珍药材电商-挂牌交易(邮件通知客服)
	 * @param toPerson(收件人)
	 * @param memberName(会员名称)
	 * @return
	 */
	public static String getListingTradeText(String memberName,String listingId){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("尊敬的客服人员：<br>");
		stringBuilder.append("&nbsp;&nbsp;您好!&nbsp;会员");
		stringBuilder.append(memberName);
		stringBuilder.append("已提交<font color='red'>挂牌信息</font>，挂牌编号：");
		stringBuilder.append(listingId);
		stringBuilder.append("请尽快进行审核！<br>");
		stringBuilder.append("如您已审核，请忽略此邮件！<br>");
		stringBuilder.append("【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。<br>");
		stringBuilder.append("此致<br>");
		stringBuilder.append("珍药材电商团队");
		return stringBuilder.toString();
	}
	
	
	/**
	 * 珍药材电商-挂牌交易(邮件通知客服)
	 * @param toPerson(收件人)
	 * @param memberName(会员名称)
	 * @return
	 */
	public static String getMemberOrderText(
			String memberName,String listingId,
			String buyerName,String buyerPhone
			,String sellerName,String sellerPhone){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("尊敬的客服人员：<br>");
		stringBuilder.append("&nbsp;&nbsp;您好!&nbsp;会员");
		stringBuilder.append(memberName);
		stringBuilder.append("已对挂牌编号为：<font  color='red'>");
		stringBuilder.append(listingId);
		stringBuilder.append("</font>的药材<font  color='red'>下单</font>！<br>");
		stringBuilder.append("<br>");
		stringBuilder.append("买家信息：<br>");
		stringBuilder.append("姓名/企业名称：").append(buyerName).append("&nbsp;&nbsp;").append("联系方式：").append(buyerPhone)
		.append("<br><br>");
		stringBuilder.append("卖家信息：<br>");
		stringBuilder.append("姓名/企业名称：").append(sellerName).append("&nbsp;&nbsp;").append("联系方式：").append(sellerPhone)
		.append("<br><br><br>");
		stringBuilder.append("【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。<br><br>");
		stringBuilder.append("此致<br>");
		stringBuilder.append("珍药材电商团队");
		return stringBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 获取交易处理人员邮箱地址
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param key
	 * @return
	 */
	public static String getJYOperatorEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_JY_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 获取配置的挂牌运营人员邮箱地址
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static String getListingOperatorEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_GUAPAI_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 获取配置的摘牌运营人员邮箱地址
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static String getOrderOperatorEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_ZHAIPAI_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 获取配置的仓库运营人员邮箱地址
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static String getWarehouseOperatorEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_CANGKU_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	/**
	 * 
	 * @Description: 获取配置的财务人员邮箱地址
	 * @Author: zhouji
	 * @Date: 2015年7月8日
	 * @return
	 */
	public static String getRemitFlowFinanceEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.EMAIL_CHECK_REMITFLOW.getMessageKey():key[0]);
	}

	/**
	 * 
	 * @Description: 获取配置的微信运营人员邮箱地址
	 * @Author: aizhengdong
	 * @Date: 2015年7月20日
	 * @return
	 */
	public static String getWxEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_WX_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * @Description: 获取配置的跟单员邮箱地址
	 * @param key
	 * @return
	 */
	public static String getTrackerEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.EMAIL_TRACKER.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 获取配置的采购相关人员邮箱地址
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param key
	 * @return
	 */
	public static String getCaigouEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_CAIGOU_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 获取指定的邮箱地址
	 * @Author: fanyuna
	 * @Date: 2015年5月18日
	 * @param key 邮箱地址的key
	 * @return
	 */
	public static String getEmailAddress(String key){
		return SpringUtil.getConfigProperties(key);
	}
	
	/**
	 * 
	 * @Description: 备货成功给平台运营发的邮件内容 
	 * @Author: fanyuna
	 * @Date: 2015年4月18日
	 * @param orderId 订单号
	 * @return
	 */
	public static String getOperatorEmailMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单编号：")
		.append(orderId)
		.append("，仓库已于"+TimeUtil.getTimeShowByTimePartten(new Date(), "yyyy年MM月dd日HH:mm:ss")+"备货完成，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 买家实际付款大于应收款时，邮件通知指定的跟单员
	 * @Author: 赵航
	 * @Date: 2015年12月3日
	 * @param orderId 订单号
	 * @param needPayment 应付款
	 * @param actualPayment 实际付款
	 * @return
	 */
	public static String getFullPaymentEmailMsg(String orderId, BigDecimal needPayment, BigDecimal actualPayment){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("跟单员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单：")
		.append(orderId)
		.append("是一次性全款支付方式wms备货减少了订单成交数量，买方应付款")
		.append(needPayment)
		.append("元，实际已付款")
		.append(actualPayment)
		.append("元，请注意提醒业务人员核实在订单分润环节是否退款")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 买家申请取消订单发送给运营的邮件说
	 * @Author: guoyb
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @return
	 */
	public static String getApplyCancelOrderMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;你好！订单编号：")
		.append(orderId)
		.append("，买家已于"+TimeUtil.getTimeShowByTimePartten(new Date(), "yyyy年MM月dd日HH:mm:ss")+"申请取消，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 通知运营买家已支付保证金
	 * @Author: 刘漂
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @param name
	 * @param date
	 * @return
	 */
	public static String getPayDepositEmailMsg(String orderId,Date date){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单编号：")
		.append(orderId)
		.append("，买家已于"+TimeUtil.getTimeShowByTimePartten(date, "yyyy年MM月dd日HH:mm:ss")+"支付了保证金，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 通知运营买家已支付全款
	 * @Author: 赵航
	 * @Date: 2015年12月9日
	 * @param orderId
	 * @param date
	 * @return
	 */
	public static String getFullPayEmailMsg(String orderId,Date date){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单编号：")
		.append(orderId)
		.append("，买家已于"+TimeUtil.getTimeShowByTimePartten(date, "yyyy年MM月dd日HH:mm:ss")+"通过全款方式，已支付全款，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 通知运营买家已尾款
	 * @Author: 刘漂
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @param name
	 * @param date
	 * @return
	 */
	public static String getPayBalanceEmailMsg(String orderId,Date date){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单编号：")
		.append(orderId)
		.append("，买家已于"+TimeUtil.getTimeShowByTimePartten(date, "yyyy年MM月dd日HH:mm:ss")+"支付了全部货款，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 备货后7天，还未付剩余货款，订单过期短信提醒运营人员
	 * @Author: 赵航
	 * @Date: 2015年4月25日
	 * @param orderId 订单编号
	 * @param overTime 过期时间
	 * @return
	 */
	public static String getOrderOvertimeEmailMsg(String orderId, Date overTime){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！订单编号：")
		.append(orderId)
		.append("，已于"+TimeUtil.getTimeShowByTimePartten(overTime, "yyyy年MM月dd日HH:mm:ss")+"过期，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 挂牌过期任务发现挂牌过期，发邮件通知运营
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @updater fanyuna  2015.11.20 增加卖方业务员名称，如果卖方业务员为空，则不加
	 * @param listingId
	 * @param time
	 * @return
	 */
	public static String getListingOvertimeEmailMsg(BusiListing busiListing,String userName, Date time,BossUser salesmanInfo){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！")
		.append("用户【"+userName+"】")
		.append("的挂牌已于"+TimeUtil.getTimeShowByTimePartten(time, "yyyy年MM月dd日HH:mm:ss")+"过期，");
		if(salesmanInfo!=null && StringUtils.isNotBlank(salesmanInfo.getUserName()))
			sBuilder.append("卖方业务员："+salesmanInfo.getUserName()+"，");
		sBuilder.append("请尽快跟进！")
		.append("挂牌信息如下：<p>")
		.append("&nbsp;&nbsp;挂牌编号：")
		.append(busiListing.getListingid())
		.append("，仓单编号：")
		.append(busiListing.getWlid())
		.append("，标题：")
		.append(busiListing.getTitle())
		.append("，用户：")
		.append(userName)
		.append("，挂牌时间：")
		.append(TimeUtil.getTimeShowByTimePartten(busiListing.getExaminetime(), "yyyy年MM月dd日HH:mm:ss"))
		.append("，挂牌期限：")
		.append(busiListing.getListingtimelimit()+"天")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 挂牌过期任务发现挂牌快过期，发邮件通知运营
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param listingId
	 * @param time
	 * @return
	 */
	public static String getListingOvertimeWarningEmailMsg(BusiListing busiListing,String userName, Date time,BossUser salesmanInfo){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！")
		.append("用户【"+userName+"】")
		.append("的挂牌即将于"+TimeUtil.getTimeShowByTimePartten(time, "yyyy年MM月dd日HH:mm:ss")+"过期，");
		if(salesmanInfo!=null && StringUtils.isNotBlank(salesmanInfo.getUserName()))
			sBuilder.append("卖方业务员："+salesmanInfo.getUserName()+"，");
		sBuilder.append("请尽快跟进！")
		.append("挂牌信息如下：<p>")
		.append("&nbsp;&nbsp;挂牌编号：")
		.append(busiListing.getListingid())
		.append("，仓单编号：")
		.append(busiListing.getWlid())
		.append("，标题：")
		.append(busiListing.getTitle())
		.append("，用户：")
		.append(userName)
		.append("，挂牌时间：")
		.append(TimeUtil.getTimeShowByTimePartten(busiListing.getExaminetime(), "yyyy年MM月dd日HH:mm:ss"))
		.append("，挂牌期限：")
		.append(busiListing.getListingtimelimit()+"天")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 会员取消挂牌，发邮件通知运营
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param userName
	 * @param listingId
	 * @param time
	 * @return
	 */
	public static String getListingCancelEmailMsg(String userName,String listingId, Date time){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的客服人员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！会员<i style=>"+userName+"</i>")
		.append("，已于"+TimeUtil.getTimeShowByTimePartten(time, "yyyy年MM月dd日HH:mm:ss")+"取消了挂牌信息，挂牌编号：")
		.append(listingId)
		.append("，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 获取需求受理处理人员邮箱地址
	 * @Author: wangjunhu
	 * @Date: 2015年5月20日
	 * @param key
	 * @return
	 */
	public static String getXuQiuOperatorEmail(String...key){
		return SpringUtil.getConfigProperties(key.length==0?MessageConstant.OPERATOR_XUQIU_EMAIL_ADDRESS.getMessageKey():key[0]);
	}
	
	/**
	 * 
	 * @Description: 会员申请采购，发邮件通知运营
	 * @Author: wangjunhu
	 * @Date: 2015年5月20日
	 * @param busiPurchaseApplyDto
	 * @return
	 */
	public static String getApplyPurchaseMsg(BusiPurchaseApplyDto busiPurchaseApplyDto){
		String applyName = busiPurchaseApplyDto.getApplyName();
		Long applyMobile = busiPurchaseApplyDto.getApplyMobile();
		List<BusiPurchaseApply> busiPurchaseApplies = busiPurchaseApplyDto.getBusiPurchaseApplies();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！有客户 '")
		.append(applyName+"' ，电话 '"+applyMobile+"' ")
		.append("，提交采购申请，请尽快进行处理！")
		.append("<p><table cellpadding=5 style=text-align:center;><tr><th width=150px>药材名称</th><th width=150px>等级规格</th><th width=150px>数量</th><th width=150px>价格</th><th width=150px>产地</th><th width=150px>描述</th></tr>");
		for (BusiPurchaseApply busiPurchaseApply : busiPurchaseApplies) {
			String breedName = busiPurchaseApply.getBreedName();
			String breedStandardLevel = busiPurchaseApply.getBreedStandardLevel();
			String breedAmount = busiPurchaseApply.getBreedAmount();
			String breedPrice = busiPurchaseApply.getBreedPrice();
			String breedPlace = busiPurchaseApply.getBreedPlace();
			String breedDesc = busiPurchaseApply.getBreedDesc();
			sBuilder.append("<tr><td>"+breedName+"</td><td>"+breedStandardLevel+"</td><td>"+breedAmount+"</td><td>"+breedPrice+"</td><td>"+breedPlace+"</td><td>"+breedDesc+"</td></tr>");
		}
		sBuilder.append("</table><p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 会员申请入仓，发邮件通知运营
	 * @Author: wangjunhu
	 * @Date: 2015年5月20日
	 * @param busiWarehouseApplyDto
	 * @return
	 */
	public static String getApplyWarehouseMsg(BusiWarehouseApplyDto busiWarehouseApplyDto){
		String applyName = busiWarehouseApplyDto.getApplyName();
		Long applyMobile = busiWarehouseApplyDto.getApplyMobile();
		List<BusiWarehouseApply> busiWarehouseApplies = busiWarehouseApplyDto.getBusiWarehouseApplies();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！有客户 '")
		.append(applyName+"' ，电话 '"+applyMobile+"' ")
		.append("，提交入仓申请，请尽快进行处理！")
		.append("<p><table cellpadding=5 style=text-align:center;><tr><th width=150px>药材名称</th><th width=150px>数量</th><th width=150px>仓库类型</th><th width=150px>预计面积</th></tr>");
		for (BusiWarehouseApply busiWarehouseApply : busiWarehouseApplies) {
			String breedName = busiWarehouseApply.getBreedName();
			String breedAmount = busiWarehouseApply.getBreedAmount();
			String warehouseType = busiWarehouseApply.getWarehouseType();
			String warehouseArea = busiWarehouseApply.getWarehouseArea();
			sBuilder.append("<tr><td>"+breedName+"</td><td>"+breedAmount+"</td><td>"+warehouseType+"</td><td>"+warehouseArea+"</td></tr>");
		}
		sBuilder.append("</table><p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}

	/**
	 * 
	 * @Description: 接口调用失败给开发、产品、运营发的邮件内容 
	 * @Author: fanyuna
	 * @Date: 2015年5月18日
	 * @param orderId 订单号
	 * @return
	 */
	public static String getInterfaceEmailMsg(ApiFlagEnums apiFlag,String dataId,String json,String res){
		String dataIdStr="仓单编号";
		if(apiFlag.equals(ApiFlagEnums.BREED_ADD)||apiFlag.equals(ApiFlagEnums.BREED_UPDATE)){
			dataIdStr="品种编码";
		}else if(apiFlag.equals(ApiFlagEnums.WARE_HOUSE_ADD)||apiFlag.equals(ApiFlagEnums.WARE_HOUSE_UPDATE)){
			dataIdStr="仓库编号";
		}else if(apiFlag.equals(ApiFlagEnums.BOSS_USER_ADD)||apiFlag.equals(ApiFlagEnums.BOSS_USER_UPDATE)
				||apiFlag.equals(ApiFlagEnums.UC_USER_ADD)||apiFlag.equals(ApiFlagEnums.UC_USER_UPDATE)){
			dataIdStr="用户名";
		}
		else if(apiFlag.equals(ApiFlagEnums.WL_FREEZE)||apiFlag.equals(ApiFlagEnums.WL_APPLY_SPLIT)||apiFlag.equals(ApiFlagEnums.WL_UNFREEZE)){
			dataIdStr="订单号";
		}
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！")
		.append(dataIdStr+"："+dataId+"，在"+TimeUtil.getTimeShowByTimePartten(new Date(), "yyyy年MM月dd日HH:mm:ss")
				  +"调用"+apiFlag.getDes()+"接口时失败，请求参数为："+json+"，返回结果为："+res+"，请尽快跟进！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	/**
	 * @Description: 交易分润操作处理成功，发邮件通知运营
	 * @Author: zhouji
	 * @Date: 2015年7月3日
	 * @param totalAmount
	 * @param remitNo
	 * @return
	 */
	public static String getAddRemitFlowEmailMsg(String totalAmount,String remitNo,Integer remitType){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的财务人员：<p>");
		if(remitType==1){//1-订单完成划账
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！您有一笔货物交割的分润订单要处理，分润金额:");
		}else if(remitType==2){//2-订单过期划账
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！您有一笔过期订单要处理，订单金额:");
		}else{//3-订单申退划账
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！您有一笔保证金退款订单要处理，保证金退款金额:");
		}
		sBuilder.append(totalAmount)
		.append("元，订单编号:")
		.append(remitNo)
		.append("，请尽快处理!")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 账期订单 过期邮件内容
	 * @Author: fanyuna
	 * @Date: 2015年9月9日
	 * @param orderid 订单编号
	 * @param startDateStr	延期开始日期
	 * @param endDateStr  结束日期
	 * @param days  延期天数
	 * @param beforeDays 0已过期，>0则即将过期
	 * @return
	 */
	public static String getTermOrderOverTimeMsg(String orderid,String startDateStr,String endDateStr,int days,int beforeDays){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的跟单员：<p>")
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好！")
		.append("订单："+orderid+"，买家账期时间是"+startDateStr+"到"+endDateStr
				  +"延期"+days+"天付款，约定期限");
		if(beforeDays>0)
		 sBuilder.append("快到了");
		else 
		 sBuilder.append("已过期");
		 sBuilder.append("，请跟进买方的付款情况！")
		.append("<p>")
		.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！")
		.append("<p>")
		.append("&nbsp;&nbsp;此致，")
		.append("<p>&nbsp;&nbsp;中药材电商团队");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 账期订单申请取消审核通过时，向跟单员发送邮件通知内容
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @param wlid 仓单编号
	 * @param sellerName 原货主账号
	 * @return
	 */
	public static String getTermOrderCancelEmailMsg(String orderId, String wlid, String sellerName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("亲爱的跟单员：<p>");
		sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;你好！订单：");
		sBuilder.append(orderId);
		sBuilder.append("是账期订单，买家已经申请取消，分割的新仓单编号");
		sBuilder.append(wlid);
		sBuilder.append("，请您去做出库操作处理，再重新做入库操作挂到原货主：");
		sBuilder.append(sellerName);
		sBuilder.append("<p>");
		sBuilder.append("&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
		sBuilder.append("<p>");
		sBuilder.append("&nbsp;&nbsp;此致，");
		sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 发送邮件通知采购操作信息
	 * @Author: 赵航
	 * @Date: 2015年10月16日
	 * @param vo
	 * @param sendType
	 * @return
	 */
	public static String getPurchaseEmailMsg(BusiPurchaseMobileEmailMsgVo vo, String sendType){
		StringBuilder sBuilder = new StringBuilder();
		if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(sendType)){
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，您的客户");
			sBuilder.append(vo.getPurchaser());
			sBuilder.append("，电话");
			sBuilder.append(vo.getPurchaserMobile());
			sBuilder.append("，发布的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("已通过审核，请时刻关注报价信息!");
			sBuilder.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！");
			sBuilder.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
			sBuilder.append("<p>&nbsp;&nbsp;此致，");
			sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		} else if(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode().equals(sendType)){
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，您的客户");
			sBuilder.append(vo.getPurchaser());
			sBuilder.append("，电话");
			sBuilder.append(vo.getPurchaserMobile());
			sBuilder.append("，发布的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("，未能通过审核，请知晓!");
			sBuilder.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！");
			sBuilder.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
			sBuilder.append("<p>&nbsp;&nbsp;此致，");
			sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		} else if(BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode().equals(sendType)){
			sBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，您的客户");
			sBuilder.append(vo.getPurchaser());
			sBuilder.append("，电话");
			sBuilder.append(vo.getPurchaserMobile());
			sBuilder.append("，发布的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("中的");
			sBuilder.append(vo.getBreedName());
			sBuilder.append("已完成采购，请引导双方进行线上交易!");
			sBuilder.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！");
			sBuilder.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
			sBuilder.append("<p>&nbsp;&nbsp;此致，");
			sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		}
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 过期采购邮件提醒
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param purchaserOrg
	 * @param phone
	 * @param purchaseCode
	 * @param quoteCount
	 * @return
	 */
	public static String getExpiredPurchaseEmailMsg(String customer, String phone, String purchaseCode){
		StringBuilder sBuilder = new StringBuilder()
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，您的客户“")
		.append(customer)
		.append("”，电话“").append(phone)
		.append("”,发布的采购单：").append(purchaseCode)
		.append("，已过期.")
		.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！")
		.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
		sBuilder.append("<p>&nbsp;&nbsp;此致，");
		sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 首次报价邮件提醒
	 * @Author: 尚翠娟
	 * @Date: 2015年10月21日
	 * @param purchaserOrg
	 * @param phone
	 * @param purchaseCode
	 * @param quoteAmount
	 * @return
	 */
	public static String getFirstQuoteEmailMsg(String customer, String phone, String purchaseCode, String quoteAmount,String breedname,String quoter,String quotephone){
		StringBuilder sBuilder = new StringBuilder()
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，有供应商对您的客户“")
		.append(customer)
		.append("（").append(phone)
		.append("）采购的").append(breedname)
		.append("（编号").append(purchaseCode)
		.append("）进行了报价，报价金额")
		.append(quoteAmount)
		.append("，联系人").append(quoter)
		.append("（").append(quotephone)
		.append("），请跟进此报价信息!")
		.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！")
		.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
		sBuilder.append("<p>&nbsp;&nbsp;此致，");
		sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	}
	/**
	 * 
	 * @Description: 最新低价  报价邮件提醒
	 * @Author: 尚翠娟
	 * @Date: 2015年10月21日
	 * @param purchaserOrg
	 * @param phone
	 * @param purchaseCode
	 * @param quoteAmount
	 * @return
	 */
	public static String getMinQuoteEmailMsg(String customer, String phone, String purchaseCode, String quoteAmount,String breedname,String quoter,String quotephone){
		StringBuilder sBuilder = new StringBuilder()
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，您的客户“")
		.append(customer)
		.append("（").append(phone)
		.append("）采购的").append(breedname)
		.append("（编号")
		.append(purchaseCode)
		.append("），产生了最低报价，报价金额").append(quoteAmount)
		.append("，联系人").append(quoter)
		.append("（").append(quotephone)
		.append("），请跟进此报价信息!")
		.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！")
		.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
		sBuilder.append("<p>&nbsp;&nbsp;此致，");
		sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	} 
	
	
	/**
	 * 
	 * @Description: 发布采购单邮件内容
	 * @Author: fanyuna
	 * @Date: 2015年10月27日
	 * @param name 发布采购的用户名称，若认证了则是认证名称，若无认证 则是注册名称
	 * @param mobile 发布采购的用户电话
	 * @param busiPurchaseList
	 * @param type 1 为新增，2为修改
	 * @return
	 */
	public static String getEmailMsgOfAddPurchase(String name,String mobile,List<BusiPurchaseVo> busiPurchaseList,int type){
		StringBuilder sBuilder = new StringBuilder()
		.append("&nbsp;&nbsp;&nbsp;&nbsp;您好，有客户“")
		.append(name)
		.append("”，电话“").append(mobile)
		.append("”,");
		if(type==1)
			sBuilder.append("发布了");
		else if(type==2)
			sBuilder.append("修改了")	;
		sBuilder.append("采购单，请尽快处理！")
		.append("<p><table cellpadding=5 style=text-align:center;>")
		.append("<tr><th>采购批次号</th><th>采购品种</th><th>规格等级</th><th>产地要求</th><th>采购数量</th><th>采购单位</th></tr>");
		if(busiPurchaseList != null && busiPurchaseList.size()>0){
			for(BusiPurchaseVo purchase:busiPurchaseList){
				sBuilder.append("<tr align='center'><td>")
						.append(purchase.getPurchaseCode()).append("</td><td>")
						.append(purchase.getBreedName()).append("</td><td>")
						.append(purchase.getStandardLevel()!=null?purchase.getStandardLevel():"").append("</td><td>")
						.append(purchase.getOrigin()!=null?purchase.getOrigin():"").append("</td><td>")
						.append(purchase.getQuantity()!=null?purchase.getQuantity():"").append("</td><td>")
						.append(purchase.getWunitName()!=null?purchase.getWunitName():"").append("</td></tr>");
			}
		}
		sBuilder.append("</table>")
		.append("<p>&nbsp;&nbsp;如您已处理，请忽略此邮件！")
		.append("<p>&nbsp;&nbsp;【注意】：本邮件为系统自动发送，不必回复！");
		sBuilder.append("<p>&nbsp;&nbsp;此致，");
		sBuilder.append("<p>&nbsp;&nbsp;中药材电商团队");
		
		return sBuilder.toString();
	} 
	
	
}
