package com.jointown.zy.common.exception;

import java.util.Calendar;
import java.util.Date;

import com.jointown.zy.common.util.TimeUtil;

/**
 * 
 * @author LiuPiao
 * 错误描述资源池。所有错误均在此定义
 */
public enum ErrorRepository {
	ANONYMOUS_ERROR("未知异常,异常信息是:{0}"),
	UNKNOW_ERROR("系统发生未知错误"),
	NOT_EXIST("该{0}不存在！"),
	
	/************************COMMON****************************/
	NO_ERROR("没有指定error信息,校验失败"),
	NO_COMMON_BUT_ERROR_ERROR("没有普通校验注解但是却有Error注解"),
	NO_COMMON_BUT_AND_ERROR("没有普通校验注解但是却有And注解"),
	NUMBER_ERROR("数字校验失败,失败信息是:{0}"),
	STRING_ERROR("字符串校验失败,失败信息是:{0}"),
	DATE_ERROR("时间校验失败,失败信息是:{0}"),
	LIST_ERROR("{0}类型数组校验失败,失败信息是:{1}"),
	
	/************************BOSS后台****************************/
	BOSS_VCODE_ERROR("验证码错误!"),
	BOSS_USER_PASS_NOT_DEFAULT("用户名/密码不能为空!"),
	BOSS_USER_PASS_NOT_MATCH_LENGTH("用户名/密码长度要在规定范围内!"),
	BOSS_USER_PASS_NOT_MATCH("用户名/密码不匹配!"),
	BOSS_USER_PASS_EXCESSIVE_ATTEMPTS("输入错误次数超过限制!"),
	BOSS_USER_REPEATED("用户名已存在!"),
	BOSS_USER_LOCKED("用户名已锁定,请联系管理员!"),
	BOSS_ORDER_DEPOSIT_UPDATE_FAILURE("更新交易订单【{0}】划账申请状态为处理中失败"),
	BOSS_ORDER_DEPOSIT_PAY_FAILURE("调用支付划账申请接口失败，失败原因：{0}"),
	BOSS_ORDER_DEPOSIT_FAILURE("订单【{0}】划账失败"),
	BOSS_TERM_ORDER_ERROR("订单【{0}】已是账期订单"),
	BOSS_TERM_ORDER_WMS("请求WMS进行仓单分割时出错"),
	BOSS_APPEALED_ERROR("申请取消的订单【{0}】已经审核处理过了，请刷新数据！"),
	BOSS_ORDER_DEPOSIT_EXIST_ERROR("订单【{0}】的退款划账数据已经存在！"),

	/*************************BOSS后台添加会员、修改会员start******************************************************/
	UC_USER_NAME_NOT_NULL("会员名不能为空!"),
	UC_USER_MOBILE_NOT_NULL("手机号不能为空!"),
	UC_USER_REALNAME_NOT_NULL("公司/姓名不能为空!"),
	UC_USER_PASSWORD_NOT_NULL("密码不能为空"),
	UC_WL_NOPLEDGED("仓单【{0}】已质押,不能进行挂牌,请重新选择其他仓单！"),
	
	UC_WL_CHANGED("仓单【{0}】状态已经发生变化,不能进行挂牌,请重新操作！"),
	
	/*************************BOSS后台添加会员、修改会员end******************************************************/
	
	
	/*************************BOSS后台审核认证start******************************************************/
	UC_PERSON_CERTIFY_NAME_NOT_NULL("真实姓名不能为为空!"),
	UC_PERSON_CERTIFY_IDCARD_NOT_NULL("身份证号不能为空!"),
	UC_PERSON_CERTIFY_IDCARD_ZM_NOT_NULL("请上传身份证正面照!"),
	UC_PERSON_CERTIFY_IDCARD_FM_NOT_NULL("请上传身份证反面照!"),
	/*************************BOSS后台审核认证end******************************************************/
	
	/*************************BOSS后台修改订单保证金start******************************************************/
	ORDER_NOT_PLACE_STATUS("订单不是已下单状态!"),
	ORDER_DEPOSIT_ILLEGAL("保证金金额不合法!"),
	ORDER_DEPOSIT_LESS_THEN_INFO("修改保证金时，金额不能小于{0}!"),
	ORDER_DEPOSIT_LESS_THEN_ORDERAMT("修改保证金时，金额不能大于订单金额!"),
	ORDER_DEPOSIT_NOT_LESS_THEN_PER20("修改保证金时，金额不能低于保证金比例的20%!"),
	ORDER_EXPIRETIME("新到期时间需大于原到期时间"),
	/*************************BOSS后台修改订单保证金end******************************************************/
	
	/**************************会员信息安全 add by ldp 2015-08-27 start********************************/
	UC_SEC_EMAIL_CONTEXT_NOT_NULL("邮箱内容不能为空!"),
	UC_SEC_EMAIL_CONTEXT_DES_FAILED("邮箱内容解密失败!"),
	UC_SEC_EMAIL_CONTEXT_IS_EXPIRE("邮箱链接已过期!"),
	UC_SEC_EMAIL_AUTH_FAILED("邮箱认证失败!"),
	UC_SEC_EMAIL_VALIDATE_FAILED("邮箱校验失败!"),
	UC_SEC_EMAIL_EXIST("邮箱已存在!"),
	/**************************会员信息安全 add by ldp 2015-08-27 start********************************/
	
	/************************CMS后台表单验证 edit by Mr.songwei 2014-11-15 12:00 start****************************/
	CMS_NULLPOINTER("很抱歉,空指针异常!"),
	CMS_ART_TITLE_ISNULL("文章标题不允许为空！"),
	CMS_ART_KEYWORDS_ISNULL("文章关键字不允许为空！"),
	CMS_ART_DESCRIPTION_ISNULL("文章摘要不允许为空！"),
	CMS_ART_CONTENT_ISNULL("文章内容不允许为空！"),
	/************************CMS后台表单验证 edit by Mr.songwei 2014-11-15 12:00 end ****************************/
	
	/*************************WEIXIN微信start******************************************************/
	WX_NO_LOGIN("请登录再操作！"),
	WX_PARAM_ERROR("参数错误！"),
	WX_NO_RECORD("信息不存在！"),
	WX_WAITING_RECORD_NO_UPDATE("待审核的信息不能修改！"),
	WX_UPLOAD_IMG_NUM_ERROR("上传图片数量错误！"),
	WX_DELETE_IMG_NUM_ONE("至少保留一张图片！"),
	WX_NO_BREED("品种不存在！"),
	/*************************WEIXIN微信end******************************************************/
	
	
	/*************************UC系统错误定义start******************************************************/
	UC_ORDER_DELETE_ERROR("删除订单失败!"),
	
	/*************************UC错误定义end******************************************************/
	
	/*************************UC经营信息系统错误定义start******************************************************/
	UC_DEALIN_INFO_NOT_NULL("经营信息不能为空!"),
	UC_DEALIN_DEALTYPE_NOT_NULL("请选择经营类型"),
	UC_DEALIN_DEALROLE_NOT_NULL("请选择经营身份"),
	UC_DEALIN_SCALE_NOT_NULL("请选择经营规模"),
	UC_DEALIN_PROVINCECODE_NOT_NULL("请选择省份"),
	UC_DEALIN_CITYCODE_NOT_NULL("请选择城市"),
	UC_DEALIN_ADDRESS_NOT_NULL("请输入地址"),
	UC_DEALIN_ZIPCODE_ERROR("需填写6位邮政编码!(6位数字)"),
	UC_DEALIN_BREED_NOT_NULL("请至少输入一个品种！"),
	/*************************UC经营信息系统错误定义end******************************************************/
	
	/*************************UC联系人信息系统错误定义start******************************************************/
	UC_CONTACTER_INFO_NOT_NULL("联系人信息不能为空!"),
	UC_CONTACTER_NAME_NOT_NULL("姓名不能为空"),
	UC_CONTACTER_NAME_FORMART_ERROR("请输入1-50字符"),
	UC_CONTACTER_SEX_NOT_NULL("请选择性别"),
	UC_CONTACTER_MOBILEPHONE_NOT_NULL("手机号不能为空"),
	UC_CONTACTER_MOBILE_FORMART_ERROR("手机号格式有误"),
	/*************************UC联系人信息系统错误定义end******************************************************/
	
	
	/*************************Solr admin start******************************************************/
	BOSS_SOLR_PARAMETER_EMPTY("所有输入参数不能为空!"),
	BOSS_SOLR_LISTINGID_EMPTY("挂牌ID列表不能为空!"),
	BOSS_SOLR_PASSWORD_EMPTY("秘钥和口令不能为空!"),
	BOSS_SOLR_UPDATE_INDEX_ERROR("更新索引失败!"),
	BOSS_SOLR_VERIFICATION_ERROR("秘钥/口令验证失败!"),
	/*************************Solr admin end******************************************************/
	;
	private String message;
	
	ErrorRepository(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取{0}占位符替换后的message
	 * @param objs
	 * @return
	 */
	public String getMessage(Object...objs){
		String result = message;
		if(objs!=null&&objs.length>0){
			for(int i=0;i<objs.length;i++){
				if(objs[i] instanceof String
						|| objs[i] instanceof Number){
					result = result.replaceAll("\\{"+i+"\\}", String.valueOf(objs[i]));
				}else if(objs[i] instanceof Date){
					result = result.replaceAll("\\{"+i+"\\}", TimeUtil.getYMDHMS((Date)objs[i]));
				}else if(objs[i] instanceof Calendar){
					result = result.replaceAll("\\{"+i+"\\}", TimeUtil.getYMDHMS(((Calendar)objs[i]).getTime()));
				}else{
					result = result.replaceAll("\\{"+i+"\\}", String.valueOf(objs[i]));
				}
			}
		}
		return result;
	}

}
