<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>微信后台管理-目录页</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
</head>
<body>
<div id="boss_system" style="left:100%;position:fixed;width:100%">
<div class="sell-box-head">
    <i class="back" onclick="window.location='${RESOURCE_WWW_WX}/WxBoss/wxLogout'"></i>
    <div align="center" class="inStore-title">后台管理</div>
</div>
<dl class="nav">
    <dt>公共系统</dt>
    <!--
    <dd><a href="#">会员管理</a></dd>
    -->
    <dd><a href="${RESOURCE_WWW_WX}/Boss/wxBossWeiXinSys">微信系统</a></dd>
	<!--
	<dd><a href="#">后台账号</a></dd>
    <dd><a href="#">类目信息</a></dd>
    <dd><a href="#">平台首页</a></dd>
     -->
    <dd><a href="${RESOURCE_WWW_WX}/Boss/wxBossSupply">供求信息</a></dd>
    <!--
    <dt>交易系统</dt>
    <dd><a href="#">仓单管理</a></dd>
    <dd><a href="#">挂牌管理</a></dd>
    <dd><a href="#">订单管理</a></dd>
    <dt>支付系统</dt>
    <dd><a href="#">资金管理</a></dd>
     -->
</dl>
<div class="a-foot">
	<img src="${RESOURCE_IMG_WX}/images/zhen0724.png" style="width: 30px;" />  技术支持：
	<a href="${RESOURCE_WWW_WX}/feedback">54315@54315.com</a>
</div>
</div>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script>
    $(function(){
    	
    	$('#boss_system').animate({left:0},1000);
    	
       //touch事件替换CLICK事件
        $('.nav dd').touchStart(function () {
            $(this).addClass('hover');
        });
        $('.nav dd').touchMove(function () {
            $(this).removeClass('hover');
        });
        $('.nav dd').touchEnd(function () {
            $(this).removeClass('hover');
        });
        $('.nav dd').tapOrClick(function () {
            $(this).removeClass('hover');
        });
    })
    
    
    	//5厂地分类层，用于划入划出菜单页
		var Height = $(document).height();
		$('#base').attr('style','left:0');
		$('.pop-up').height(Height);
		
		//6.1从右像左滑
		$('.admin-list').click(function(){
			var target = $(this).attr('name');
			$('.main').hide();
			$('.main').attr('style','');
			var Height = $(document).height();
			$('.main').height(Height);
			$('.main').attr('class','pop-up-two');
			$('#'+target).attr('class','pop-up main');
			$('#'+target).show().animate({left:0},100);
		});
		
		//6.2从左向右滑的
		$('.back').click(function(){
			var target = $(this).attr('name');
			$('.main').hide();
			$('.main').attr('style','');
			var Height = $(document).height();
			$('.main').height(Height);
			$('.main').attr('class','pop-up');
			$('#'+target).attr('class','pop-up-two main');
			$('#'+target).show().animate({left:0},100);
		});
</script>

<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>