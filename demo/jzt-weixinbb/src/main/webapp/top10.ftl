<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>品种行情-涨跌TOP10</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>
    <ul id="tab_id" class="tabs clearfix">
        <li class="cur" name="珍药材-涨价排行"><a href="#"><span>涨价排行</span></a> </li>
        <li name="珍药材-跌价排行"><a href="#"><span>跌价排行</span></a></li>
    </ul>
<div id="conts">
    <div class="box-layout pt5" style="display: block;">
        <#list up_list as up>
        <div class="topten-box1">
            <h2>
                <span class="fr">${up.hangQing}</span>
                NO.${up_index + 1}  ${up.ycName}
            </h2>
            
            <div class="boder">
            <!-- 
            	<#if up.pic??>
            		<p><img src="${RESOURCE_IMG_EAST}/pic/dangan/${up.pic}"/></p>
            	</#if>
             -->
            	<p class="clearfix">
                <span class="fl">规格：${up.guiGe}</span><br/>
                    <span class="fl">今日价格：${up.price}</span><span class="fr">对比价格：${up.yuanPrice}</span></p>
                <p class="cap clearfix"><span class="fl"> 产地：${up.chanDi}</span><span class="fr">对比时间：${up.yuanDtm}</span></p>
            </div>
        </div>
        </#list>
    </div>

    <div class="box-layout pt5">
        <#list down_list as down>
        <div class="topten-box1">
            <h2>
                <span class="fr green">${down.hangQing}</span>
                NO.${down_index + 1}  ${down.ycName}
            </h2>
            <div class="boder">
            <!-- 
            	<#if down.pic??>
            		<p><img src="${RESOURCE_IMG_EAST}/pic/dangan/${down.pic}"/></p>
            	</#if>
             -->	
             <p class="clearfix">
                <span class="fl">规格：${down.guiGe}</span><br/>
                    <span class="fl">今日价格：${down.price}</span><span class="fr">对比价格：${down.yuanPrice}</span></p>
                <p class="cap clearfix"><span class="fl"> 产地：${down.chanDi}</span><span class="fr">对比时间：${down.yuanDtm}</span></p>
            </div>
        </div>
        </#list>
    </div>
</div>

<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js" type="text/javascript"></script>
<script type="text/javascript">
	//去掉tab中最后一个的li的竖分割线
	$("#tab_id  li").last().find('span').css("border-right","0px");
	
    $(function(){
        $('.tabs li').on('click mouseover',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $('#conts').children('.box-layout').eq($(this).index()).show().siblings().hide();
        });
        
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