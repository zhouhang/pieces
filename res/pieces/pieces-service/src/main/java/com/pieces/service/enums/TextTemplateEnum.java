package com.pieces.service.enums;

import org.apache.commons.lang.ArrayUtils;

/**
 * 验证码模板
 * Created by wangbin on 2016/6/30.
 */
public enum TextTemplateEnum {

    SMS_BIZ_CAPTCHA_TEMPLATE("{1}您的注册验证码是{2},该验证码在30分钟内有效.注册成功后即可询价."),
    SMS_BIZ_FINDPASSWORD_CAPTCHA("{1}验证码为{2}，该验证码在30分钟内有效，请在页面中输入以完成验证。"),
    SMS_BIZ_SENDACCOUNT_TEMPLATE("【上工好药】尊敬的用户,您选择的付款帐号开户名称:{1}，开户行:{2},账号:{3},本次应付{4}元，付款时请备注订单号:{5}."),
    SMS_BIZ_CERTIFYSUCCESS_TEMPLATE("【上工好药】尊敬的用户，您提交的企业资质审核已通过，立即开始下单吧。"),
    SMS_BIZ_CERTIFYFAIL_TEMPLATE("【上工好药】尊敬的用户，您的企业资质审核未通过，不通过原因：{1}，请整理好资料后重新提交。有问题请致电 0558-5120088。"),
    SMS_BOSS_ADDUSER_PASSWORD_TEMPLATE("{1}您的用户名是{2},密码是{3},请妥善保管."),
    SMS_BOSS_UPDATEUSER_PASSWORD_TEMPLATE("{1}{2}您好,您的新密码是{3},请妥善保管."),
    SMS_BOSS_QUOTED_NOTLOGIN("【上工好药】尊敬的用户: 您的询价单 {2} 已有回复.点击 http://sghaoyao.com/quote/{2} 查看详细。下单请登录平台操作。登录账号:您的手机号，登录密码:您的手机号后六位。"),
    SMS_BOSS_QUOTED("【上工好药】尊敬的用户: 您的询价单 {2} 已有回复.点击 http://sghaoyao.com/quote/{2} 查看详细。下单请登录平台操作。"),
    SMS_BOSS_QUOTEDUPDATE("【上工好药】尊敬的用户: 您的询价单 {2} 已有回复.点击 http://sghaoyao.com/quote/{2} 查看详细。下单请登录平台操作。"),
    SMS_BOSS_PAYSUCCESS("【上工好药】{1} 您好,您 {2}元 的款项支付成功,详情请在支付记录中查看,平台会尽快为您安排发货."),
    SMS_BOSS_PAYACCOUNTSUCCESS("【上工好药】{1} 您好,您 {2}元 的款项支付成功,详情请在支付记录中查看."),
    SMS_BOSS_PAYFAIL("【上工好药】{1} 您好,您 {2}元 的款项支付失败,详情在支付记录中查看."),
    SMS_BOSS_ACCOUNTSUCCESS("【上工好药】尊敬的用户，您的订单 {1} 已通过账期支付，我们将尽快为您发货，账期时间 {2}个月，届时请前往“用户中心”-“对账单”-“账期账单”中完成支付。"),
    SMS_BOSS_ACCOUNTFAIL("【上工好药】尊敬的用户，您的订单 {1} 的账期支付申请未通过，请使用其他方式付款。如有疑问请咨询客服0558-5120088。"),
    SMS_BOSS_ACCOUNT_TIP("【上工好药】尊敬的用户：您的账单 {1} 将在 {2} 到期，请提前准备好货款，登录平台，前往“会员中心”-“对账单”-“账期账单”完成支付。"),
    SMS_BOSS_ACCOUNT_ADMIN_TIP("【上工好药】客户 {1} 的账单 {2} 将在 {3} 到期，请及时跟踪处理。联系人：{4}，电话：{5}。");

    private String value;

    TextTemplateEnum(String value){

        this.value = value;
    }

    /**
     * 根据所传数组值来替换模板变量
     * @param params
     * @return
     */
    public String getText(String... params ){
        String text = new String(this.value);
        if(!ArrayUtils.isEmpty(params)){
            for(int i=0;i<params.length;i++){
                String temKey = "{"+(i+1)+"}";
                String temVal = params[i];
                text = text.replace(temKey,temVal);
            }
        }
        return text;
    }


    public static void main(String[] args) {
//        String la =  TextTemplateEnum.SMS_CAPTCHA_TEMPLATE.getText("【速采科技】","1024");
//        System.out.println(la);
    }


}
