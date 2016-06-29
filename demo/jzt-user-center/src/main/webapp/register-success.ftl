<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-前台注册成功</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
    <script> 
	<!--add by fanyuna 2015-06-15 添加百度统计代码 start -->
   var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?6d44aadf5ca623f2e7c9a58b573e923c";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
	<!--add by fanyuna 2015-06-15 添加百度统计代码 end -->
	</script>	 
</head>
<body>

<!-- 头部  -->
	<div class="topper sty1">
    <div class="area-1200">
        <div class="logo clearfix">
        	<a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
            <span>欢迎注册</span>
        </div>
    </div>
    </div>
<!-- 头部 end  -->
<div class="area-1200">
    <div class="re-success">
        <strong>恭喜，${userName }已注册成功！</strong>
        <p>超过80%的用户登录后都选择第一时间补充信息资料，账户更安全交易更放心！</p>
        <div class="mt15"><input type="button" id="loginBtn" value="点击登录" class="register-btn mr10 wid1" /><!-- <input type="button" value="返回" class="yzm-btn wid2" /> --></div>
    </div>
</div>
<!-- 底部  -->
    <#include "common/footer.ftl">
<!-- 底部 end  -->

<script type="text/javascript">
	$("#loginBtn").click(function(){
		window.location="https://passport.54315.com/login?service=http://uc.54315.com/casuc";
	});
</script>
</body>
</html>