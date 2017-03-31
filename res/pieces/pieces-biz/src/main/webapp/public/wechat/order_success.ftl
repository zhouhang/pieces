<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>下单成功-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="ui-message">
        <div class="ico ico-right"></div>
        <h3 class="hd">下单成功</h3>
        <div class="bd">订单号：${order.code!} <#if user.type==2>需支付保证金：<em class="c-red">￥${order.deposit?string("0.00")}</em>
        <#else >应付金额：<em class="c-red">￥${order.amountsPayable?string("0.00")}</em></#if></div>
        <div class="bd">请在 ${order.expireDate?date} 前完成付款</div>
    </div>
    <div class="ui-button">
        <a href="/h5c/order/pay/${order.id!}" class="ubtn ubtn-red" id="pay">立即支付</a>
  </div>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
</body>
</html>