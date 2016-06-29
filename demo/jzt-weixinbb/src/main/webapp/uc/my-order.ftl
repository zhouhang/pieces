<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-我的订单</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
     <style>
        body{background:#f5f5f5;}
    </style>
</head>
<body>
<div class="sell-box-head">
    <a href="${RESOURCE_WWW_WX}/myzyc"><i class="back"></i></a></div>
<div class="order">
    <div class="ad"><img src="${RESOURCE_IMG_WX}/images/order.jpg" /></div>
    <ul>
        <a href="${RESOURCE_WWW_WX}/order/order_buyer"><li>买方订单<#if my_order_flag== '0'><i></i></#if></li>
        <a href="${RESOURCE_WWW_WX}/order/sellorders"><li>卖方订单<#if my_order_flag== '1'><i></i></#if></li></a>
    </ul>
    <div class="foot" align="center">
        热线电话：<a href="tel:4001054315">4001054315</a><br/>
        <span>珍药材 版权所有</span>
    </div>
</div>
</body>
</html>