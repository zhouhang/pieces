<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>珍药材-我的小珍</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
</head>
<body>
<div class="breed-box relative">
    <div class="ad"><img src="${RESOURCE_IMG_WX}/images/news-breed.jpg?t=${timestamp}" /></div>
    <ul>
        <li><a href="${RESOURCE_WWW_WX}/info"><i class="icon10"></i><h3>入驻珍药材</h3><span class="hott"></span></a></li>
        <li><a href="${RESOURCE_WWW_WX}/wxWarehouseApply/iWillWarehousing"><i class="icon14"></i><h3>我要入仓</h3></a></li>
        <li><a href="${RESOURCE_WWW_WX}/wxWhlist/wxWhlistManage"><i class="icon15"></i><h3>我的仓库</h3></a></li>
        <li>
        <a href="${RESOURCE_WWW_WX}/listing"><i class="icon12"></i><h3>我的挂牌</h3><span class="hott"></span></a></li>
        <li><a href="${RESOURCE_WWW_WX}/order/myorder"><i class="icon13"></i><h3>我的订单</h3><span class="hott"></span></a></li>
        <li><a href="${RESOURCE_WWW_WX}/wxSupplySend/supplySendAuthentication"><i class="icon11"></i><h3>发布供求</h3></a></li>
    	<li><a href="${RESOURCE_WWW_WX}/financing"><i class="icon22"></i><h3>我要融资</h3></a></li>
    	<li></li>
    	<li></li>
    </ul>
	<div class="foot" align="center">
		热线电话：
		<a href="tel:4001054315">4001054315</a>
		<br>
		<span>珍药材 版权所有</span>
	</div>
</div>
<#if tag?? && tag==1>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<div id="checker" class="h-view" style="margin-top:-3em;display: block;">
	<div class="h-view2">
		<ul>
			<li>
				<p>小珍已收到您提交的融资申请，</p>
				<p>稍后会与您电话联系！</p>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
       //隐藏
       function hide() {
            $("#checker").hide();
       }
	   setTimeout(hide, 3000);
</script>
</#if>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>