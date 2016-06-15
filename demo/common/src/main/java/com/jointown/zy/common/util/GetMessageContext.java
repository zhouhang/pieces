package com.jointown.zy.common.util;

import java.math.BigDecimal;
import java.util.Date;

import com.jointown.zy.common.constant.BankConfigConstant;
import com.jointown.zy.common.enums.BusiListingFlagEnum;
import com.jointown.zy.common.enums.BusiPurchaseStatusEnum;
import com.jointown.zy.common.model.BusiListing;
import com.jointown.zy.common.vo.BusiPurchaseMobileEmailMsgVo;


/**
 * 拼接短信内容（手机验证码短信内容、重置密码短信内容、添加会员发送密码短信内容...）
 * author ldp
 * 2014年12月11日
 */
public class GetMessageContext {
	
	/**
	 * 拼接重置密码短信内容
	 * 
	 * @return
	 */
	public static String getSecretResetMessgeContext(String userName,
			String newPassword) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的珍药材会员名:");
		sBuilder.append(userName);
		sBuilder.append(",重置的新登录密码是:");
		sBuilder.append(newPassword);
		sBuilder.append(".您可登录及时修改新密码,请不要把信息泄露给其他人");
		return sBuilder.toString();
	}
	
	/**
	 * 拼接添加会员的短信内容
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String getAddMemberMessageContext(String userName,String password){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的珍药材会员名:");
		sBuilder.append(userName);
		sBuilder.append(",登录密码是:");
		sBuilder.append(password);
		sBuilder.append(".您可登录及时修改新密码,请不要把信息泄露给其他人");
		return sBuilder.toString();
	}

	
	/**
	 * 拼接手机验证码短信
	 * @param code
	 * @return
	 */
	public static String getMobileCodeMsg(String code){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的验证码是:");
		sBuilder.append(code);
		sBuilder.append(".请不要把验证码泄露给其他人");
		return sBuilder.toString();
	}
	
	/**
	 * 拼接重置密码短信内容
	 * 
	 * @return
	 */
	public static String getSecretResetMsg(String userName,
			String newPassword) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的珍药材工作平台登录名:");
		sBuilder.append(userName);
		sBuilder.append(",重置的新登录密码是:");
		sBuilder.append(newPassword);
		sBuilder.append(".您可登录及时修改新密码,请不要把信息泄露给其他人");
		return sBuilder.toString();
	}
	
	/**
	 * 个人实名认证审核通过短信内容
	 * @return
	 */
	public static String getPersonCertifyPassMsg(){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您在珍药材的个人实名认证已通过。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 个人实名认证审核未通过短信内容
	 * @return
	 */
	public static String getPersonCertifyUnPassMsg(){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您在珍药材的个人实名认证未通过，请重新提交资料。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 企业实名认证通过短信内容
	 * @return
	 */
	public static String getCompanyCertifyPassMsg(){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您在珍药材的企业实名认证已通过。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 企业实名认证不通过短信内容
	 * @return
	 */
	public static String getCompanyCertifyUnPassMsg(){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您在珍药材的企业实名认证未通过，请重新提交资料。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 微信发布供求信息短信通知运营人员
	 * @Author: wangjunhu
	 * @Date: 2015年5月19日
	 * @param userName
	 * @param supplyOrDemand true：发布供应信息，false：发布求购信息
	 * @return
	 */
	public static String getWxSupplyOrDemandMsg(String userName,boolean supplyOrDemand){
		StringBuilder sBuilder = new StringBuilder();
		if(supplyOrDemand){
			sBuilder.append("hello，【"+userName+"】发布了供应信息，需要进入后台审核，请火速处理。");
		}else{
			sBuilder.append("hello，【"+userName+"】发布了求购信息，需要进入后台审核，请火速处理。");
		}
		return sBuilder.toString();
	}

	/**
	 * @Description: 通知买家平台已备货的短信内容
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static String getBuyerGoodsPreparedMsg(String orderId,String days){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，平台已备货成功，您还有")
		.append(days)
		.append("天时间支付货款。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 通知卖家平台已备货的短信内容
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @return
	 */
	public static String getOwnerGoodsPreparedMsg(String orderId,String days){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，平台已备货成功，买家还有")
		.append(days)
		.append("天时间支付货款。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 通知买家订单完成的短信内容
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @param orderId
	 * @return
	 */
	public static String getBuyerOrderMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，已完成交易，您有一个新的仓单生成。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 通知卖家家订单完成的短信内容
	 * @Author: 刘漂
	 * @Date: 2015年4月18日
	 * @param orderId
	 * @return
	 */
	public static String getOwnerOrderMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，已交易完成。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	
	/**
	 * @Description: WMS备货,发送给卖家业务员的短信
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param orderId,UserStr,BreedName,buyerSalesManName
	 * @return
	 */
	public static String getOrderPreparedMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，wms已备货成功，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进订单尾款情况");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: WMS备货，发送给买家的短信
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param orderId,UserStr,BreedName,sellerSalesManName
	 * @return
	 */
	public static String getOrderPreparedMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，wms已备货成功，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请跟进买家在规定的倒计时间内付尾款");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 备货减少数量（支付全款类型）,向卖方业务员发送短信
	 * @Author: 赵航
	 * @Date: 2015年12月3日
	 * @param orderId 订单编号
	 * @param userStr 用户
	 * @param needPayment 应付款
	 * @param actualPayment 实际付款
	 * @return
	 */
	public static String getOrderFullPay4SellerSalesMan(String orderId,String userStr, BigDecimal needPayment, BigDecimal actualPayment){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(userStr)
		.append("的订单：")
		.append(orderId)
		.append("，买方应付款")
		.append(needPayment)
		.append("元，实际已付款")
		.append(actualPayment)
		.append("元，请注意在订单分润环节是否退款");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 备货减少数量（支付全款类型）,向买方业务员发送短信
	 * @Author: 赵航
	 * @Date: 2015年12月3日
	 * @param orderId 订单编号
	 * @param userStr 用户
	 * @param needPayment 应付款
	 * @param actualPayment 实际付款
	 * @return
	 */
	public static String getOrderFullPay4BuyerSalesMan(String orderId,String userStr, BigDecimal needPayment, BigDecimal actualPayment){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(userStr)
		.append("的订单：")
		.append(orderId)
		.append("，买方应付款")
		.append(needPayment)
		.append("元，实际已付款")
		.append(actualPayment)
		.append("元，请注意在订单分润环节是否退款");
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description: 通知买家平台已完成赔付
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @updater fanyuna 短信模板内容增加退款金额
	 * @param orderId 订单编号
	 * @return
	 */
	public static String getCompleteRefundMsg(String orderId,BigDecimal amount){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您申请取消的订单：")
		.append(orderId)
		.append("，平台已完成退款"+amount+"元。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 买家申请取消发送给卖家的短信
	 * @Author: guoyb
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @return
	 */
	public static String getApplyCancelOrderMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，买家已申请取消，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description: 买家申请取消,发送给卖家业务员的短信
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId,UserStr,BreedName,buyerSalesManName
	 * @return
	 */
	public static String getApplyCancelOrderMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("提交了取消交易申请，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进好订单走向");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 买家申请取消，发送给买家的短信
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId,UserStr,BreedName,sellerSalesManName
	 * @return
	 */
	public static String getApplyCancelOrderMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("提交了取消交易申请，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请提交申请退款的邮件，并关注业务副总的审核，跟进好订单走向");
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description: 通知买家其申请退款已被平台驳回
	 * @Author: 赵航
	 * @Date: 2015年4月18日
	 * @param orderId 订单编号
	 * @return
	 */
	public static String getRejectAppealMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您申请取消的订单：")
		.append(orderId)
		.append("，平台已驳回申请，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 平台审核挂牌时，发送短信信息
	 * @Author: 赵航
	 * @Date: 2015年4月20日
	 * @param listingId
	 * @param auditFlg
	 * @return
	 */
	public static String getListingAuditMdg(String listingId, String auditFlg){
		String auditMsg = "";
		if(BusiListingFlagEnum.LISTING.getCode().equals(auditFlg)){
			auditMsg = "已通过审核";
		} else if(BusiListingFlagEnum.AUDIT_FAILURE.getCode().equals(auditFlg)){
			auditMsg = "未通过审核";
		}
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的挂牌：")
		.append(listingId)
		.append("，平台")
		.append(auditMsg)
		.append("，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 仓单挂牌，短信提示
	 * @Author: wangjunhu
	 * @Date: 2015年4月20日
	 * @param listingId
	 * @return
	 */
	public static String getAddListingMsg(String listingId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您提交的挂牌：")
		.append(listingId)
		.append("，已提交至后台进行审核。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	
	/**
	 * 
	 * @Description: 仓单挂牌，短信提示(给业务员)
	 * @Author: biran
	 * @Date: 2015年10月19日
	 * @param listingId,UserStr,BreedName
	 * @return
	 */
	public static String getAddListingMsg4SalesMan(String listingId,String UserStr,String BreedName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家")
		.append(UserStr)
		.append("提交了申请挂牌")
		.append(listingId)
		.append("，品种：")
		.append(BreedName)
		.append(",请跟进好运营部挂牌审核情况");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 下架挂牌，短信提示给对应业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param listingId,UserStr,BreedName
	 * @return
	 */
	public static String getDisabledListingMsg4SalesMan(String listingId,String UserStr,String BreedName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家")
		.append(UserStr)
		.append("取消了挂牌")
		.append(listingId)
		.append("，品种")
		.append(BreedName)
		.append(",请跟进情况");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 修改挂牌，短信提示
	 * @Author: wangjunhu
	 * @Date: 2015年4月20日
	 * @param listingId
	 * @return
	 */
	public static String getAlterListingMsg(String listingId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您修改了挂牌：")
		.append(listingId)
		.append("，已提交至后台进行审核。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description:客户下单，短信提示卖家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId,UserStr,BreedName,buyerSalesManName
	 * @return
	 */
	public static String getCreateOrderMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("刚被下了单，订单：")
		.append(orderId)
		.append("，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进撮合好交易");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description:客户下单，短信提示买家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId,UserStr,BreedName,sellerSalesManName
	 * @return
	 */
	public static String getCreateOrderMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("刚下了单，订单：")
		.append(orderId)
		.append("，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请跟进撮合好交易");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 订单改价，短信提示
	 * @Author: wangjunhu
	 * @Date: 2015年4月20日
	 * @param listingId
	 * @return
	 */
	public static String getAlterOrderUnitPriceMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，卖家已修改价格，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 支付保证金短信通知卖家
	 * @Author: 刘漂
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayDepositMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，买家已支付保证金，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 支付全款短信通知卖家
	 * @Author: 赵航
	 * @Date: 2015年12月9日
	 * @param orderId
	 * @return
	 */
	public static String getFullPayMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，买家已支付货款，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 支付保证金短信，通知卖家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayDepositMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，已支付保证金，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进wms备货情况");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 支付全款短信，通知卖家业务员
	 * @Author: 赵航
	 * @Date: 2015年12月9日
	 * @param orderId
	 * @param UserStr
	 * @param BreedName
	 * @param buyerSalesManName
	 * @return
	 */
	public static String getFullPayMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，通过全款方式已支付全款，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进wms备货情况");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 支付保证金短信，通知买家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayDepositMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，已支付保证金，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请跟进wms备货情况");
		return sBuilder.toString();
	}
	

	/**
	 * @Description: 支付全款短信，通知买家业务员
	 * @Author: 赵航
	 * @Date: 2015年12月9日
	 * @param orderId
	 * @param UserStr
	 * @param BreedName
	 * @param sellerSalesManName
	 * @return
	 */
	public static String getfullPayMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，通过支付全款方式已支付全款，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请跟进wms备货情况");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 支付货款短信通知卖家
	 * @Author: 刘漂
	 * @Date: 2015年4月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayBalanceMsg(String orderId){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，买家已支付全部货款，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	
	/**
	 * 
	 * @Description: 支付货款短信，通知卖家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayBalanceMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，已支付全部货款，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，请跟进订单分润");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 支付货款短信，通知买家业务员
	 * @Author: biran
	 * @Date: 2015年10月20日
	 * @param orderId
	 * @return
	 */
	public static String getPayBalanceMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("，已支付全部货款，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请跟进订单分润");
		return sBuilder.toString();
	}
	
	
	
	/**
	 * @Description: 备货后7天，还未付剩余货款，订单过期短信提醒客户
	 * @Author: 赵航
	 * @Date: 2015年4月25日
	 * @param orderId 订单编号
	 * @param overTime 过期时间
	 * @return
	 */
	public static String getOrderOvertimeMsg(String orderId, Date overTime){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，已于")
		.append(TimeUtil.getTimeShowByTimePartten(overTime, "yyyy年MM月dd日HH:mm:ss"))
		.append("过期，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description: 备货后7天，还未付剩余货款，订单过期短信提醒卖方业务员
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param orderId ,UserStr,BreedName,buyerSalesManName
	 * @return
	 */
	public static String getOrderOvertimeMsg4SellerSalesMan(String orderId,String UserStr,String BreedName,String buyerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("买家超期限未付余款已取消，品种：")
		.append(BreedName);
		if(buyerSalesManName!=null && !buyerSalesManName.equals("")){
			sBuilder.append("，买家业务员：").append(buyerSalesManName);
		}
		sBuilder.append("，跟进好订单走向");
		return sBuilder.toString();
	}
	
	
	
	
	/**
	 * @Description: 备货后7天，还未付剩余货款，订单过期短信提醒买方业务员
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param orderId ,UserStr,BreedName,sellerSalesManName
	 * @return
	 */
	public static String getOrderOvertimeMsg4BuyerSalesMan(String orderId,String UserStr,String BreedName,String sellerSalesManName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("买家：")
		.append(UserStr)
		.append("的订单：")
		.append(orderId)
		.append("超期限未付余款已取消，品种：")
		.append(BreedName);
		if(sellerSalesManName!=null && !sellerSalesManName.equals("")){
			sBuilder.append("，卖家业务员：").append(sellerSalesManName);
		}
		sBuilder.append("，请提交申请退款或者赔付的邮件，并关注业务副总的审核，跟进好订单走向");
		return sBuilder.toString();
	}
	
	
	
	/**
	 * 
	 * @Description: 挂牌过期任务发现挂牌过期，发短信通知卖家
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param listingId
	 * @param time
	 * @return
	 */
	public static String getListingOvertimeMsg(BusiListing busiListing, Date time){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的挂牌：")
		.append(busiListing.getListingid())
		.append("，标题：")
		.append(busiListing.getTitle())
		.append("，挂牌时间：")
		.append(TimeUtil.getTimeShowByTimePartten(busiListing.getExaminetime(), "yyyy年MM月dd日HH:mm:ss"))
		.append("，挂牌期限：")
		.append(busiListing.getListingtimelimit()+"天")
		.append("的挂牌信息已于")
		.append(TimeUtil.getTimeShowByTimePartten(time, "yyyy年MM月dd日HH:mm:ss"))
		.append("过期，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 挂牌过期，短信提示给业务员
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param listingId,UserStr,BreedName,overTime
	 * @return
	 */
	public static String getListingOvertimeMsg4SalesMan(String listingId,String UserStr,String BreedName,Date overTime){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家")
		.append(UserStr)
		.append("的挂牌")
		.append(listingId)
		.append(",已于")
		.append(TimeUtil.getTimeShowByTimePartten(overTime, "yyyy年MM月dd日HH:mm:ss"))
		.append("过期，品种：")
		.append(BreedName)
		.append("，如还需继续销售请联系卖家重新提交挂牌");
		return sBuilder.toString();
	}
	/**
	 * 
	 * @Description: 挂牌过期任务发现挂牌即将过期，发短信通知卖家
	 * @Author: 刘漂
	 * @Date: 2015年4月25日
	 * @param listingId
	 * @param time
	 * @return
	 */
	public static String getListingOvertimeWarningMsg(BusiListing busiListing, Date time){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的挂牌：")
		.append(busiListing.getListingid())
		.append("，标题：")
		.append(busiListing.getTitle())
		.append("，挂牌时间：")
		.append(TimeUtil.getTimeShowByTimePartten(busiListing.getExaminetime(), "yyyy年MM月dd日HH:mm:ss"))
		.append("，挂牌期限：")
		.append(busiListing.getListingtimelimit()+"天")
		.append("的挂牌信息即将于")
		.append(TimeUtil.getTimeShowByTimePartten(time, "yyyy年MM月dd日HH:mm:ss"))
		.append("过期，请登录平台查看。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 挂牌过期提醒，短信提示给业务员
	 * @Author: biran
	 * @Date: 2015年10月21日
	 * @param listingId,UserStr,BreedName,overTime
	 * @return
	 */
	public static String getListingOvertimeWarningMsg4SalesMan(String listingId,String UserStr,String BreedName,Date overTime){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("卖家")
		.append(UserStr)
		.append("的挂牌")
		.append(listingId)
		.append("，品种：")
		.append(BreedName)
		.append(",即将于")
		.append(TimeUtil.getTimeShowByTimePartten(overTime, "yyyy年MM月dd日HH:mm:ss"))
		.append("过期，如还需继续销售请联系卖家重新修改挂牌信息");
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description:线下支付账号信息
	 * @Author: biran
	 * @Date: 2015年9月8日
	 * @param orderId
	 * 		  bankCode
	 * @return
	 */
	
	public static String getPayVoucherAccountMsg(String orderId,String bankCode){
		String title = "感谢您在珍药材网采购药材，";
		String receiver = "九州通中药材电子商务有限公司；";
		String CCB_Account = "4200\t1258\t1080\t5300\t9523。";
		String CMB_Account= "1279\t0724\t6510\t602";
		String ICBC_Account = "1812\t0231\t1920\t0134\t304";
		StringBuffer sb = new StringBuffer(title).append("订单号：").append(orderId).append("；");
		if(BankConfigConstant.BankCode.CCB.equals(bankCode)){
			sb.append("开户行：").append(BankConfigConstant.BankCode.getName(bankCode)).append("郭茨口支行；");
			sb.append("收款人名称：").append(receiver);
			sb.append("收款账号：").append(CCB_Account);
		}else if(BankConfigConstant.BankCode.CMB.equals(bankCode)){
			sb.append("开户行：").append(BankConfigConstant.BankCode.getName(bankCode)).append("武汉经济开发区支行；");
			sb.append("收款人名称：").append(receiver);
			sb.append("收款账号：").append(CMB_Account);
		}else if(BankConfigConstant.BankCode.ICBC.equals(bankCode)){
			sb.append("开户行：").append(BankConfigConstant.BankCode.getName(bankCode)).append("应城支行；");
			sb.append("收款人名称：").append(receiver);
			sb.append("收款账号：").append(ICBC_Account);
		}
		sb.append("为确保您的药材能够及时处理，请您在转账操作时务必注明您的名称和订单号");
		sb.toString();
		return sb.toString();
	}
	
	/**
	 * @Description:凌讯中科短信营运商，短信验证码模板
	 * @Author: ldp
	 * @Date: 2015年7月16日
	 * @param code
	 * @return
	 */
	public static String getLxzkMobileCodeMsg(String code){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("【珍药材】验证码：");
		sBuilder.append(code);
		sBuilder.append("，欢迎使用珍药材，此验证码5分钟有效，请妥善保管。");
		return sBuilder.toString();
	}
	
	
	/**
	 * 
	 * @Description: 账期订单即将到期（提前四天）给卖家发短信
	 * @Author: fanyuna
	 * @Date: 2015年9月8日
	 * @param orderid
	 * @param days
	 * @return
	 */
	public static String getTermOrderSonnOverMsgOfSeller(String orderid,int days){
		StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("您的订单：");
			sBuilder.append(orderid);
			sBuilder.append("，买方与您双方已约定同意延期");
			sBuilder.append(days);
			sBuilder.append("天付款。期限快到了，您可提前跟买家沟通情况！如有疑问请致电客服4001054315");
			return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 账期订单即将到期（提前四天）给买家发短信
	 * @Author: fanyuna
	 * @Date: 2015年9月8日
	 * @param orderid 订单号
	 * @param endDateStr 结束日期如：2015年10月30日
	 * @param days 延期天数
	 * @return
	 */
	public static String getTermOrderSonnOverMsgOfBuyer(String orderid,String endDateStr,int days){
		StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("您的订单：");
			sBuilder.append(orderid);
			sBuilder.append("，您与卖方约定");
			sBuilder.append(endDateStr);
			sBuilder.append("前延期");
			sBuilder.append(days);
			sBuilder.append("天付款，期限快到了，请尽快完成付款，如有疑问请致电客服4001054315");
			return sBuilder.toString();
			
	}
	
	/**
	 * 
	 * @Description: 账期订单即将到期（提前四天）给卖家业务员发短信
	 * @Author: fanyuna
	 * @Date: 2015年9月8日
	 * @param orderid 订单号
	 * @param sellerName 卖家用户名
	 * @param startDateStr 开始日期如：2015年10月30日
	 * @param endDateStr 结束日期如：2015年10月30日
	 * @param days 延期天数
	 * @param beforeDays 0已过期，>0则即将过期
	 * @return
	 */
	public static String getTermOrderSonnOverMsgOfSellerSalesman(String orderid,String sellerName,String startDateStr,String endDateStr,int days,int beforeDays){
		StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("您的卖家");
			sBuilder.append(sellerName);
			sBuilder.append("的订单：");
			sBuilder.append(orderid);
			sBuilder.append("，买家账期时间是");
			sBuilder.append(startDateStr);
			sBuilder.append("到");
			sBuilder.append(endDateStr);
			sBuilder.append("延期");
			sBuilder.append(days);
			sBuilder.append("天付款，");
			if(beforeDays>0)
				sBuilder.append("期限快到了");
			else
				sBuilder.append("已到期");
			sBuilder.append("，请跟进！");
			return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 账期订单即将到期（提前四天）给买家业务员发短信
	 * @Author: fanyuna
	 * @Date: 2015年9月8日
	 * @param orderid 订单号
	 * @param buyerName 买家用户名
	 * @param startDateStr 开始日期如：2015年10月30日
	 * @param endDateStr 结束日期如：2015年10月30日
	 * @param days 延期天数
	 * @param beforeDays 0已过期，>0则即将过期
	 * @return
	 */
	public static String getTermOrderSonnOverMsgOfBuyerSalesman(String orderid,String buyerName,String startDateStr,String endDateStr,int days,int beforeDays){
		StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("您的买家");
			sBuilder.append(buyerName);
			sBuilder.append("的订单：");
			sBuilder.append(orderid);
			sBuilder.append("，买家账期时间是");
			sBuilder.append(startDateStr);
			sBuilder.append("到");
			sBuilder.append(endDateStr);
			sBuilder.append("延期");
			sBuilder.append(days);
			sBuilder.append("天付款，");
			if(beforeDays<0)
				sBuilder.append("已到期，");
			sBuilder.append("请跟进买方的付款情况！");
			return sBuilder.toString();
			
	}
	
	
	
	/**
	 * @Description: 转为账期订单短信通知卖方
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单号
	 * @param days 账期天数
	 * @return 短信内容
	 */
	public static String getTermOrderSellerMsg(String orderId, int days){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：");
		sBuilder.append(orderId);
		sBuilder.append("，买方与您双方已约定同意延期");
		sBuilder.append(days);
		sBuilder.append("天付款。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 转为账期订单短信通知卖方业务员
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param sellerName 卖方用户名
	 * @param orderId 订单编号
	 * @param startTime 账期开始时间
	 * @param endTime 账期结束时间
	 * @param days 账期天数
	 * @return 短信内容
	 */
	public static String getTermOrderSellerSalesmanMsg(String sellerName, String orderId, Date startTime, Date endTime, int days){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的卖家");
		sBuilder.append(sellerName);
		sBuilder.append("的订单：");
		sBuilder.append(orderId);
		sBuilder.append("，买家账期时间是");
		sBuilder.append(TimeUtil.getTimeShowByTimePartten(startTime, "yyyy年MM月dd日"));
		sBuilder.append("到");
		sBuilder.append(TimeUtil.getTimeShowByTimePartten(endTime, "yyyy年MM月dd日"));
		sBuilder.append("延期");
		sBuilder.append(days);
		sBuilder.append("天付款，请跟进");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 转为账期订单短信通知买方
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单号
	 * @param days 账期天数
	 * @param endTime 账期结束时间
	 * @return 短信内容
	 */
	public static String getTermOrderBuyerMsg(String orderId, int days, Date endTime){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：");
		sBuilder.append(orderId);
		sBuilder.append("，您与卖方约定延期");
		sBuilder.append(days);
		sBuilder.append("天付款。请于");
		sBuilder.append(TimeUtil.getTimeShowByTimePartten(endTime, "yyyy年MM月dd日"));
		sBuilder.append("前完成付款，如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 转为账期订单短信通知买方业务员
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param buyerName 买方用户名
	 * @param orderId 订单号
	 * @param startTime 账期开始时间
	 * @param endTime 账期结束时间
	 * @param days 账期天数
	 * @return 短信内容
	 */
	public static String getTermOrderBuyerSalesmanMsg(String buyerName, String orderId, Date startTime, Date endTime, int days){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的买家");
		sBuilder.append(buyerName);
		sBuilder.append("的订单：");
		sBuilder.append(orderId);
		sBuilder.append("，买家账期时间是");
		sBuilder.append(TimeUtil.getTimeShowByTimePartten(startTime, "yyyy年MM月dd日"));
		sBuilder.append("到");
		sBuilder.append(TimeUtil.getTimeShowByTimePartten(endTime, "yyyy年MM月dd日"));
		sBuilder.append("延期");
		sBuilder.append(days);
		sBuilder.append("天付款，请跟进买方的付款情况");
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 账期订单申请取消审核通过后，向卖方业务员发短信通知的内容
	 * @Author: 赵航
	 * @Date: 2015年9月10日
	 * @param orderId 订单编号
	 * @param wlid 分割的新仓单编号
	 * @param sellerName 原货主账号名
	 * @return
	 */
	public static String getTermOrderCancelSellerSalesmanMsg(String orderId, String wlid, String sellerName){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("订单：");
		sBuilder.append(orderId);
		sBuilder.append("是账期订单，买家已经申请取消，分割的新仓单编号");
		sBuilder.append(wlid);
		sBuilder.append("，请您去做出库操作处理，再重新做入库操作挂到原货主：");
		sBuilder.append(sellerName);
		return sBuilder.toString();
	}
	
	/**
	 * @Description: 发送短信通知采购方的信息
	 * @Author: 赵航
	 * @Date: 2015年10月16日
	 * @param vo
	 * @return
	 */
	public static String getPurchaseMobileMsg(BusiPurchaseMobileEmailMsgVo vo, String sendType){
		StringBuilder sBuilder = new StringBuilder();
		if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(sendType)){
			sBuilder.append("您的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("，已通过审核，您可以在用户中心-管理采购中查看。如有疑问请致电客服4001054315");
		} else if(BusiPurchaseStatusEnum.AUDIT_FAILURE.getCode().equals(sendType)){
			sBuilder.append("您的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("，未能通过审核，请在用户中心-管理采购中重新提交。如有疑问请致电客服4001054315");
		} else if(BusiPurchaseStatusEnum.DEAL_SUCCESS.getCode().equals(sendType)){
			sBuilder.append("恭喜您，您的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("中的");
			sBuilder.append(vo.getBreedName());
			sBuilder.append("已完成采购，推荐您使用珍药材平台线上交易，可享受仓储、质检等服务。如有疑问请致电客服4001054315");
		}
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 采购过期的提醒消息
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param purchaseCode
	 * @return
	 */
	public static String getExpiredPurchaseMsg(String purchaseCode){
		return new StringBuilder()
		.append("您的采购单：")
		.append(purchaseCode)
		.append("已过期，推荐您使用珍药材平台线上交易，可享受仓储、质检等服务。如有疑问请致电客服4001054315")
		.toString();
	}
	
	/**
	 * 
	 * @Description: 通知交易员采购过期的提醒消息
	 * @Author: 刘漂
	 * @Date: 2015年10月18日
	 * @param purchaseCode
	 * @param quoteCount
	 * @return
	 */
	public static String getExpiredPurchaseMsg(String purchaseCode,String purchaser,String mobile){
		return new StringBuilder()
		.append("您的客户：“").append(purchaser)
		.append("”，电话“").append(mobile)
		.append("”，发布的采购单：").append(purchaseCode)
		.append("，已过期。")
		.toString();
	}

	/**
	 * 
	 * @Description: 首次报价短信提醒采购方
	 * @Author: 尚翠娟
	 * @Date: 2015年10月21日
	 * @param purchaseCode
	 * @param quotePrice
	 * @param tradeName
	 * @param tradePhone
	 * @return
	 */
	public static String getFirstQuoteMsg(String purchaseCode,String quotePrice,String tradeName,String tradePhone,String breedname){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("有供应商对您的采购单：");
		sBuilder.append(purchaseCode);
		sBuilder.append("中的").append(breedname);
		sBuilder.append("进行了报价，报价金额为：");
		sBuilder.append(quotePrice);
		sBuilder.append("，您的交易员（");
		sBuilder.append(tradeName);
		sBuilder.append(",");
		sBuilder.append(tradePhone);
		sBuilder.append("）会帮您持续跟进。选择珍药材现货交易，质量有保障！如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}
	
	/**
	 * 
	 * @Description: 最新 低价 报价短信提醒采购方
	 * @Author: 尚翠娟
	 * @Date: 2015年10月21日
	 * @param purchaseCode
	 * @param quotePrice
	 * @param tradeName
	 * @param tradePhone
	 * @return
	 */
	public static String getMinQuoteMsg(String purchaseCode,String quotePrice,String tradeName,String tradePhone,String breedname){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的采购单：");
		sBuilder.append(purchaseCode);
		sBuilder.append("中的").append(breedname);
		sBuilder.append("有最低的报价产生，报价金额为：");
		sBuilder.append(quotePrice);
		sBuilder.append("，您的交易员（");
		sBuilder.append(tradeName);
		sBuilder.append(",");
		sBuilder.append(tradePhone);
		sBuilder.append("）会帮您持续跟进。选择珍药材现货交易，质量有保障！如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}

	
	/**
	 * 
	 * @Description: 首次报价短信提醒交易员
	 * @Author: 尚翠娟
	 * @Date: 2015年11月4日
	 * @param customer
	 * @param phone
	 * @param purchaseCode
	 * @param quoteAmount
	 * @param breedname
	 * @return
	 */
	public static String getFirstQuoteMsgOfSale(String customer, String phone, String purchaseCode, String quoteAmount,String breedname,String quoter,String quotephone){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("有供应商对您的客户");
		sBuilder.append(customer);
		sBuilder.append("（").append(phone);
		sBuilder.append("）采购的").append(breedname);
		sBuilder.append("（编号").append(purchaseCode);
		sBuilder.append("）进行了报价，报价金额").append(quoteAmount);
		sBuilder.append("，联系人").append(quoter);
		sBuilder.append("（").append(quotephone);
		sBuilder.append("），请跟进此报价信息!");		
		return sBuilder.toString();
	}
	/**
	 * 
	 * @Description: 最新 低价 报价短信提醒交易员
	 * @Author: 尚翠娟
	 * @Date: 2015年10月21日
	 * @param customer
	 * @param phone
	 * @param purchaseCode
	 * @param quoteAmount
	 * @param breedname
	 * @return
	 */
	public static String getMinQuoteMsgOfSale(String customer, String phone, String purchaseCode, String quoteAmount,String breedname,String quoter,String quotephone){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的客户“");
		sBuilder.append(customer);
		sBuilder.append("（").append(phone);
		sBuilder.append("）采购的").append(breedname);
		sBuilder.append("（编号").append(purchaseCode);
		sBuilder.append("）产生了最低的报价，报价金额");
		sBuilder.append(quoteAmount);
		sBuilder.append("，联系人").append(quoter);
		sBuilder.append("（").append(quotephone);
		sBuilder.append("），请跟进此报价信息!");
		
		return sBuilder.toString();
	}

	
	/**
	 * @Description: 发送短信通知交易员的信息
	 * @Author: 赵航
	 * @Date: 2015年10月16日
	 * @param vo
	 * @return
	 */
	public static String getPurchaseTradeMobileMsg(BusiPurchaseMobileEmailMsgVo vo, String sendType){
		StringBuilder sBuilder = new StringBuilder();
		if(BusiPurchaseStatusEnum.OFFER_WAITING.getCode().equals(sendType)){
			sBuilder.append("您的客户");
			sBuilder.append(vo.getPurchaser());
			sBuilder.append("，电话");
			sBuilder.append(vo.getPurchaserMobile());
			sBuilder.append("发布的采购单：");
			sBuilder.append(vo.getPurchaseCode());
			sBuilder.append("已通过审核，请时刻关注报价信息!");
		}
		return sBuilder.toString();
	}
	
	
	/**
	 * @Description: 订单划账分润成功，给卖家发送短信
	 * @Author: fanyuna
	 * @Date: 2015年11月20日
	 * @param orderId 订单编号
	 * @return
	 */
	public static String getOrderDepositSellerMsg(String orderId,BigDecimal depositAmount){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("您的订单：")
		.append(orderId)
		.append("，平台已完成货款分润"+depositAmount+"元。如有疑问请致电客服4001054315");
		return sBuilder.toString();
	}

}
