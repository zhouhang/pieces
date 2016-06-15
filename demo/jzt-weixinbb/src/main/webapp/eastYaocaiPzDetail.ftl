<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>品种行情-药材百科</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
    <div class="box-layout">
      <div class="detail-box2">
      	  <#if eastPzDangan??>
      	  	<h1>【${eastPzDangan.ycnam!''}】</h1>
      	  	<div class="pic-box2"><img src="${RESOURCE_IMG_EAST}/pic/dangan/${eastPzDangan.pic}"/></div>
      	  	<#if (eastPzDangan.bynam?length>0)>
      	  		<p style="text-indent: 0;">${eastPzDangan.bynam!''}</p>
          	</#if>
          	<#if (eastPzDangan.gaishu?length>0)>
		  		<h3>概述</h3>
		  		<p>${eastPzDangan.gaishu!''}</p>
		  	</#if>
		  	<#if (eastPzDangan.chandi?length>0)>
		  		<h3>产地分布</h3>
          		<p>${eastPzDangan.chandi!''}</p>
          	</#if>
          	<#if (eastPzDangan.xingtai?length>0)>
          		<h3>形态特征</h3>
          		<p>${eastPzDangan.xingtai!''}</p>
          	</#if>
          	<#if (eastPzDangan.shengtai?length>0)>
          		<h3>生态环境</h3>
          		<p>${eastPzDangan.shengtai!''}</p>
          	</#if>
          	<#if (eastPzDangan.chucang?length>0)>
          		<h3>储藏养护</h3>
          		<p>${eastPzDangan.chucang!''}</p>
          	</#if>
          	<#if (eastPzDangan.guige?length>0)>
          		<h3>形状规格</h3>
          		<p>${eastPzDangan.guige!''}</p>
          	</#if>
          	<#if (eastPzDangan.xingwei?length>0)>
          		<h3>性味功能</h3>
          		<p>${eastPzDangan.xingwei!''}</p>
          	</#if>
          	<#if (eastPzDangan.jianbie?length>0)>
          		<h3>真伪鉴别</h3>
          		<p>${eastPzDangan.jianbie!''}</p>
          	</#if>
          	<#if (eastPzDangan.shengwu?length>0)>
          		<h3>生物学特性</h3>
          		<p>${eastPzDangan.shengwu!''}</p>
          	</#if>
          	<#if (eastPzDangan.xixing?length>0)>
          		<h3>生长习性</h3>
          		<p>${eastPzDangan.xixing!''}</p>
          	</#if>
          	<#if (eastPzDangan.zhouqi?length>0)>
          		<h3>生长周期</h3>
          		<p>${eastPzDangan.zhouqi!''}</p>
          	</#if>	
          	<#if (eastPzDangan.caishou?length>0)>
          		<h3>采收加工</h3>
          		<p>${eastPzDangan.caishou!''}</p>
          	</#if>
          	<#if (eastPzDangan.zhongzhi?length>0)>	
          		<h3>种植技术</h3>
          		<p>${eastPzDangan.zhongzhi!''}</p>
          	</#if>
          	<#if (eastPzDangan.gongxu?length>0)>
	  			<h3>供需情况</h3>
          		<p>${eastPzDangan.gongxu!''}</p>
	  		</#if>
	  		<#if (eastPzDangan.yaodian?length>0)>
          		<h3>药典标准</h3>
          		<p>${eastPzDangan.yaodian!''}</p>
          	</#if>      		          		
      	  </#if>
      </div>
    </div>
    <div>
    <h3>更多内容请长按识别关注二维码：</h3>
    <!-- 东网二维码 
    <div class="pic-box2"><img src="${RESOURCE_IMG_WX}/images/jztzyds_wx.jpg"/> </div>-->
    <!-- 珍药材二维码 --> <div class="pic-box2"><img src="${RESOURCE_IMG_WX}/images/zyc_wx.jpg"/> </div>
    </div>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
    <script>
    	//table自适应
    	var tables = $("table");
    	$.each(tables,function(tableIndex,tableObj){
    		var tableWidth = $(tableObj).attr("width");
    		if(tableWidth){
    			//alert(tableWidth);
    			$(tableObj).attr("width","100%");
    			var num = tableWidth/100;
    			var tableTds = $(tableObj).find("th,td");
    			$.each(tableTds,function(tableTdIndex,tableTdObj){
    				var tableTdWidth = $(tableTdObj).attr("width");
    				if(tableTdWidth){
    					//alert(tableTdWidth);
    					$(tableTdObj).attr("width",(tableTdWidth/num)+"%");
    				}
    			})
    		}
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