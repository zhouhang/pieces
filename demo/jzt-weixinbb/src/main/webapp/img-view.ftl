<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>查看质检报告单</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
<div class="sell-box-head">
    <i id="back" class="back"></i>
    <div align="center" class="inStore-title">质检报告单</div>
</div>
<div class="sellDetail">
    <div class="wraper">
       <#if img!=null && img!=''>
      	 <img src="${RESOURCE_IMG_UPLOAD_WX}/${img}" alt="质检报告单" />
       <#else>
       	 <img src="${RESOURCE_IMG_WX}/images/qc.jpg"/>
       </#if>
    </div>
</div>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
<script  type="text/javascript">
	    //返回按钮事件
        $('#back').on('click',function(){
        	history.go(-1); 
        }) 
</script>
</span>
</body>
</html>