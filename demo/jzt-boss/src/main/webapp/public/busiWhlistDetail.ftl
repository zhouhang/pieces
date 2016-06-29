<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>仓单详情</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" /> 
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#import 'macro.ftl' as tools>
<#include "home/top.ftl" /> 
<div class="wapper">
	<#include "home/left.ftl" /> 
    <!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
            <h1 class="title1">仓单管理</h1>
           	 	<div class="store-left fl">
	                <h2>${whlistvo.breedname!''}</h2>
	                <h3 class="title2">基本信息</h3>
	            	<ul class="de-list clearfix">
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
                	<div class="border-1 mt5">
                    <h3 class="title2"><#if (whlistvo.itemMap)?? && (whlistvo.itemMap)?size gt 0><a class="fr col_red f12 fn" href="javascript:;" id="btnReport" data-zhijian="${RESOURCE_IMG_UPLOAD}/${whlistvo.zjReportPic!''}">查看质检报告单</a></#if>
                                                                                              质检信息</h3>
                    <dl class="zj-mes">
                        <dt>性状描述：</dt>
                        <dd>${whlistvo.levelEva}</dd>
                    </dl>
                	<dl class="zj-mes">
                        <dt>理化检验：</dt>
                        <!--update by fanyuna 2015.07.22 仓单若无QC质检信息，不显示此版块-->
                        <#if (whlistvo.itemMap)?? && (whlistvo.itemMap)?size gt 0 >
                        <dd>
                        <strong>抽样数量：</strong>${whlistvo.numberofjc!''}&nbsp;克<br/>
                        
                        <#if (whlistvo.itemMap)??>
	                        <table cellspacing="1" cellpadding="1" bgcolor="#dddddd" width="100%" class="check-table">
	                                <tbody><tr align="center" bgcolor="#ececec">
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
	                                </tbody>
	                        </table>
                        </#if>        
                        </dd>
                        <#else>
                          <dd>暂无理化检验信息</dd>
                        </#if>
                    </dl>
                </div>
            </div>   
           <!--右边图片-->
           <div class="store-right fr">
        	<ul class="pic-list mt15">
        		<#assign i = 0>  
        		<#if whlistvo.piclist ??>
               		<#list whlistvo.piclist as qualitypic>
               			<#assign img = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i320}.jpg"> 
               			<#assign img1 = qualitypic.path?substring((qualitypic.path?last_index_of("/")+1),(qualitypic.path?last_index_of(".")))+"_${i640}.jpg"> 
               			<#assign tempurl = qualitypic.path?substring(0,qualitypic.path?last_index_of("/")+1)> 
               			<#if qualitypic_index==0>
               				<li><span class="storeimg"><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></span><p>散货照片</p></li>
               			<#elseif qualitypic_index== whlistvo.piclist?size-2>
               				<li><span class="storeimg"><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></span><p>包装照</p></li>
               			<#elseif qualitypic_index== whlistvo.piclist?size-1>
               				<li><span class="storeimg"><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></span><p>堆垛照</p></li>
               			<#else>
               				<#assign i = i + 1>
               				<li><span class="storeimg"><img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}"/></span><p>细节照${i}</p></li>
               			</#if>
               		</#list>
	            </#if>  		
              </ul>
           </div>
        </div>
    </div>    
        <!-- pageCenter over -->
</div>
<!--质检报告弹层 start-->
<div class="pop-report" id="reportPop">
    <img src="${RESOURCE_IMG_UPLOAD}/${whlistvo.zjReportPic!''}"/>
</div>
<div class="pop-report-border">
    <div class="close" id="rClose">关闭窗口</div>
</div>
<!--质检报告弹层 over-->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="http://10.3.1.156/svn/jointown/static/resources/js/imgView/jquery.imageView.js"></script>
<script type="text/javascript">
/* 	function openWindow(href){
		window.location.href=href;
	}
	 */
	
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

</script>
</body>
</html>