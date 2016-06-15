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
    <div class="baike-box">
        <div class="breed-list relative">
        	<#if eastYaocaiMap??>
        		<#assign keys = eastYaocaiMap?keys>
	   			<#list keys as key>
	   				<#if (eastYaocaiMap[key]?size>0)>
		   				<dl>
			                <dt id="${key!''}" name="${key!''}">${key?upper_case!''}</dt>
			                <dd>
	                    		<#list eastYaocaiMap[key] as eastYaocai>
	                    			<a href="/getEastPzDangan?ycnam=${eastYaocai.ycnam!''}">${eastYaocai.ycnam!''}</a>
	                    		</#list>
			                </dd>
			            </dl>
			        <#else>
			            <a id="${key!''}" name="${key!''}"></a>
		            </#if>
	    		</#list>
	   		</#if>
	   		
	   		<!--浮层-->
            <div class="search-breed">
                <a href="#a">A</a>
                <a href="#b">B</a>
                <a href="#c">C</a>
                <a href="#d">D</a>
                <a href="#e">E</a>
                <a href="#f">F</a>
                <a href="#g">G</a>
                <a href="#h">H</a>
                <a href="#i">I</a>
                <a href="#j">J</a>
                <a href="#k">K</a>
                <a href="#l">L</a>
                <a href="#m">M</a>
                <a href="#n">N</a>
                <a href="#o">O</a>
                <a href="#p">P</a>
                <a href="#q">Q</a>
                <a href="#r">R</a>
                <a href="#s">S</a>
                <a href="#t">T</a>
                <a href="#u">U</a>
                <a href="#v">V</a>
                <a href="#w">W</a>
                <a href="#x">X</a>
                <a href="#y">Y</a>
                <a href="#z">Z</a>
            </div>
            <!--浮层 over-->
        </div>
    </div>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jqueryTouch.js"></script>
<script>
	$(function(){	
		//touch事件替换CLICK事件
	    $('input[type=button]').touchStart(function () {
	        $(this).addClass('hover');
	    });
	    $('input[type=button]').touchMove(function () {
	        $(this).addClass('hover');
	    });
	    $('input[type=button]').touchEnd(function () {
	        $(this).removeClass('hover');
	    });
	    $('input[type=button]').tapOrClick(function () {
	        $(this).removeClass('hover');
	    });
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