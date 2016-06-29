<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>珍药材-温馨提示</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css">

</head>
<body>
<div class="sell-box-head">
    <i class="back"></i>
    <div align="center" class="inStore-title">温馨提示</div>
</div>
<div class="delist_box">
    <div class="unShelve"></div>
    <div class="words">${error}</div>
</div>
<div class="delist_red">
    <div class="words">有事找小珍</div>
	<div align="center">
            <a href="tel://4001054315" name="phone" tel="400">
            <input type="button" class="knob" value="按我电询小珍" /></a>
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
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>