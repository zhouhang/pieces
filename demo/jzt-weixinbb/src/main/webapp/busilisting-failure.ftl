<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我要挂牌-错误提示</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">

</head>
<body>
<div class="sell-box-head">
    <i class="back"></i>
    <div align="center" class="inStore-title">我要挂牌结果</div>
</div>
<div class="delist_box">
    <div class="failure"></div>
    <div class="words">${error}</div>
</div>
<div class="delist_red">
    <div class="words">请电询小珍进行付款</div>
	<div align="center">
            <a href="tel://4001054315" name="phone" tel="400">
            <input type="button" class="knob" value="按我电询小珍" /></a>
    </div>
    <div class="line">
    <p>您也可以登录</p>
    <p>www.54315.com</p>
    <p>进入用户中心/买方订单进行在线支付</p>
	</div>
</div>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		//返回按钮事件
	    $('.back').on('click',function(){
	    	history.go(-1); 
	    }) 
	})
</script> 
</body>
</html>