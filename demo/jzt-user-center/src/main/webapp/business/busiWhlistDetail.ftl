<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>仓单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#import 'macro.ftl' as tools>
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
    <#include 'common/left.ftl'>
    <div class="hy-right fr">
        <div class="cd-main">
            <h1 class="title_war">仓单信息</h1>
            <!--左边详情-->
            <div class="left_details">
            	<div class="public">
	                <h1>${whlistvo.breedname!''}</h1>
	                <h2>基本信息</h2>
	            	<ul>
	                   <li>仓单号：${whlistvo.wlid!''}</li>
	                   <li>入库日期：<#if whlistvo.wlrkdate??>${whlistvo.wlrkdate?string("yyyy-MM-dd")!''}</#if></li>
	                   <li>仓单状态：<#if whlistvo.wlstate??>${whlistStateMap[whlistvo.wlstate?string]}</#if></li>
	                   <li>合同编号：<i>${whlistvo.contractnum!''}</i></li>
	                   <li>所在仓库：${whlistvo.warehousename!''}</li>
	                   <li>包装方式：${whlistvo.packingway!''}</li>
	                   <li>可挂牌数量/仓单总量：<@tools.money num=whlistvo.wlsurplus format="0.##"/> ${whlistvo.dictvalue!''}/<@tools.money num=whlistvo.wltotal format="0.##"/> ${whlistvo.dictvalue!''}</li>
	                   <li>产地：${whlistvo.origin!''}</li>
	                   <li class="last">规格：${whlistvo.grade!''}</li>
	                </ul>
                </div>
                <div class="public">
                	<h2 class="bn mb5"><#if (whlistvo.itemMap)?? && (whlistvo.itemMap)?size gt 0><a class="fr col_red f12 fn" href="javascript:;" id="btnReport" data-zhijian="${RESOURCE_IMG_UPLOAD}/${whlistvo.zjReportPic!''}">查看质检报告单</a></#if> 质检信息</h2>
                	<div class="border-eee">
                		<h2>性状描述</h2>
                		<p class="fontstyle">${whlistvo.levelEva}</p>
                		<h2>理化检验</h2>
                		<!--update by fanyuna 2015.07.22 QC质检信息没有时不显示-->
                		<#if (whlistvo.itemMap)?? && (whlistvo.itemMap)?size gt 0>
                		<p class="fontstyle">抽样数量：${whlistvo.numberofjc!''} &nbsp;克&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                		<p class="fontstyle">
                		<#if (whlistvo.itemMap)??>
                			<table cellspacing="1" cellpadding="1" bgcolor="#f1d5d5" class="check-table">
                				<tr align="left" bgcolor="#ffffff">
                					<th width="90" height="30">检验项目</th>
                					<th>标准规定</th>
                					<th>检验结果</th>
                				</tr>
                				<#list whlistvo.itemMap?keys as key>
                				<tr align="left">
                					<td colspan="3" bgcolor="#f6f6f6" height="30">【${key}】</td>
                				</tr>
	                				<#list whlistvo.itemMap[key] as item>
	                				<tr align="left" bgcolor="#ffffff">
	                					<td height="30">${(item.qualityItemName)!'' }</td>
	                					<td>${(item.qualityItemStandard)!'' }</td>
	                					<td>${(item.qualityItemResult)!'' }</td>
	                				</tr>
	                				</#list>
                				</#list>
                			</table>
                		</#if>
                		</p>
                	    <#else>
                	     <p class="fontstyle">暂无理化检验信息</p>
                	    </#if>
                	</div>
                	<#if whlistvo.wlsurplus ?? && whlistvo.wlstate ??>
		              	<#if whlistvo.wlsurplus!=0 && whlistvo.wlstate==0>
		              		<div class="clearfix"><input type="button" class="btn-maxred mt20" id="btn-blue" value="挂牌" onclick="openWindow('/listing/add?wlid=${whlistvo.wlid!''}');"/></div>
		              	</#if>
	              	</#if>
                </div>
            </div>
               <!--右边图片-->
               <div class="right_pic">
            	<ul class="gallery">
            		<#assign i = 0>  
            		<#if whlistvo.piclist ??>
                   		<#list whlistvo.piclist as qualitypic>
                   			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i320}.jpg"> 
                   			<#assign img1 = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i640}.jpg"> 
                   			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
                   			<#if qualitypic_index==0>
                   				<li><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/><span>散货照片</span></li>
                   			<#elseif qualitypic_index== whlistvo.piclist?size-2>
                   				<li><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/><span>包装照</span></li>
                   			<#elseif qualitypic_index== whlistvo.piclist?size-1>
                   				<li><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/><span>堆垛照</span></li>
                   			<#else>
                   				<#assign i = i + 1>
                   				<li><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/><span>细节照${i}</span></li>
                   			</#if>
                   		</#list>
		            </#if>  		
                </ul><div class="clear"></div>
               </div>
            </div>
</div></div>
<!-- 底部  -->
<#include 'common/footer.ftl'>
<!--质检报告弹层 start-->
<div class="pop-report" id="reportPop">
    <img src="${RESOURCE_IMG_UPLOAD}/${whlistvo.zjReportPic!''}"/>
</div>
<div class="pop-report-border">
    <div class="close" id="rClose">关闭窗口</div>
</div>
<!--质检报告弹层 over-->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
<script type="text/javascript">

 $(function(){
    $('#btnReport').click(function(){
        $('#reportPop').show();
        $('.pop-report-border').show();
        //$('#reportPop img').attr("src", srcUrl);
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
        return false;
    });
    $('#rClose').click (function(){
        $('#reportPop').hide();
        $('.pop-report-border').hide();
        $('.bghui').remove();
        $(document).bind("click",function(e){
            var target  = $(e.target);
            if(target.closest("#reportPop,.pop-report-border").length == 0){
                $('#reportPop').hide();
                $('.pop-report-border').hide();
                $('.bghui').remove();
            }
            e.stopPropagation();
        });
        //$('#reportPop').imageView({width:1060, height:500,ee:-297})
    });
    $('#reportPop').imageView({width:1060, height:500,ee:-297})
 });
 
 function openWindow(href){
	window.location.href=href;
 }
</script>
</body>
</html>