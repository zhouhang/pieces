<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>退款管理</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" />
<!-- 订单详情公用js --> 
<#include "public/dialog_details_common.ftl" /> 
<div class="wapper">
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">退款管理</h1>
            <form action="/refundManager" method="POST" id="refundManagerForm">
                <ul class="store-search">
                    <li>
                      <span>订单号：</span><input name="orderId" value="${page.params.remitDto.orderId}" class="text-store text-7" type="text" />
                      <span>退款时间：</span>
                   	  <input id="wdate1" name="remitStartTime" value="${page.params.remitDto.remitStartTime}" type="text" class="text text-date" />&nbsp;至&nbsp;
                      <input id="wdate2" name="remitEndTime" value="${page.params.remitDto.remitEndTime}" type="text" class="text text-date" />
                      <span>退款状态：</span>
                    	<select name="status" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if refundRemitStatusMap??>
	                   			<#list refundRemitStatusMap?keys as key>
	                    			<option value="${key!'' }" <#if page.params.remitDto.status == key>selected</#if>>${refundRemitStatusMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select>
                    	<span>支付渠道：</span>
	                  <select name="payChannel" class="text-store text-7">
	                       <option value="">全部</option>
	                        <#if (payChannelList)??>
	                   			<#list payChannelList as pcl>
	                   				<option value="${pcl.key!''}"<#if page.params.remitDto.payChannel == pcl.key>selected</#if>>${pcl.name!'' }</option>
	                    		</#list>
                   			</#if>
	                  </select>
	                  <span>退款类型：</span>
	                  <select name="remitType" class="text-store text-7">
	                       <option value="">全部</option>
	                        <#if (refundStatusList)??>
	                   			<#list refundStatusList as rsl>
	                   				<#if rsl.key!=1>
	                   					<option value="${rsl.key!''}"<#if page.params.remitDto.remitType == rsl.key>selected</#if>>${rsl.name!'' }</option>
	                   				</#if>
	                    		</#list>
                   			</#if>
	                  </select> 
	                  <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    </li>
                </ul>
            </form>
            <form action="" method="POST" id="exportRefundFlowForm">
                   <input type="hidden" name="orderId" value="${page.params.remitDto.orderId}"/>
                   <input id="wdate1" type="hidden" name="remitStartTime" value="${page.params.remitDto.remitStartTime}" />
                   <input id="wdate2" type="hidden" name="remitEndTime" value="${page.params.remitDto.remitEndTime}"/> 
                   <input type="hidden" name="status" value="${page.params.remitDto.status}"/>
                   <input type="hidden" name="payChannel" value="${page.params.remitDto.payChannel}"/>
                   <input type="hidden" name="remitListType" value="1002" />
            </form>
            <div class="use-item1" style=" margin-top:20px;">
           		 <!-- <@shiro.hasPermission name="入仓信息管理-入仓信息审核"> </@shiro.hasPermission> -->
           		 <span class="btn-add mb10">
					<a href="#">导出Excel</a>
				 </span>
            	<table id="" class="table-store" width="100%" cellpadding="1" cellspacing="1">
            		<tr>
                        <th width="5%">流水号</th>
                        <th width="5%">订单编号</th>
                        <th width="7%">保证金金额</th>
                        <th width="5%">买方金额</th>
                        <th width="8%">卖方金额</th>
                        <th width="5%">平台金额</th>
                        <th width="8%">支付渠道</th>
                        <th width="5%">退款状态</th>
                        <th width="5%">退款类型</th>
                        <th width="10%">退款完成时间</th>
                        <th width="8%">操作人</th>
                        <th width="8%">操作时间</th>
                        <th width="6%">操作</th>
                    </tr>
                   <#if (page.results?size>0) >
                    	<#list page.results as r>
                    		<tr>
                    			<td>${r.flowId!""}</td>
                    			<td><a class="col_blue" name="nub" href="javascript:;" data-id="${r.orderId!''}" data-reclick="n">${r.orderId!''}</a></td>
                    			<td><@tools.money num=r.totalAmount format="0.##"/></td> 
                    			<td>${r.buyerAmount!''}</td>
                    			<td>${r.sellerAmount!''}</td>
                    			<td>${r.platformAmount!''}</td>
                    			<td>
                    				<#if (payChannelList)??>
			                   			<#list payChannelList as pcl>
			                   				<#if r.remitChannel == pcl.key>
			                   					${pcl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
                    			</td>
                    			<td>
                    				<#if refundRemitStatusMap??>
										<#list refundRemitStatusMap?keys as key>
			                   				<#if r.status == key>
			                   					${refundRemitStatusMap[key]!'' }
			                   				</#if>
			                    		</#list>
									</#if>
                    			</td>
                    			<td>
                    				<#if (refundStatusList)??>
			                   			<#list refundStatusList as rsl>
			                   				<#if r.remitType == rsl.key>
			                   					${rsl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
                    			</td>
                    			<td>
                    				<#if (r.remitTime)??>
										${r.remitTime?string("yyyy-MM-dd HH:mm:ss")!''}
									</#if>
                    			</td>
                    			<td>${r.userName!'' }</td>
                    			<td>
                    				<#if (r.opraterTime)??>
										${r.opraterTime?string("yyyy-MM-dd HH:mm:ss")!''}
									</#if>
                    			</td>
                    			<td class="opr-btn" width="216">
                    			<#if r.status==1 || r.status==3 >
                    				<span id="461-0" class="operate-5" title="查看" onclick="javascript:viewRefundInfo('${r.flowId!''}',2);"></span>
                    				<#else>
                    				<@shiro.hasPermission name="退款管理-确认退款">
                    				<input type="button" value="确认退款" class="btn-hui" onclick="javascript:viewRefundInfo('${r.flowId!''}',1);">
                    				</@shiro.hasPermission>
                    			</#if>
								</td>
                    		</tr>
                    	</#list>
                    	<#else>
                    	<tr>
                    		<td colspan="16">没有数据!</td>
                    	</tr>
                    </#if>
				</table>
			</div>
			<@tools.pages page=page form="refundManagerForm"/>
    	</div>
    </div>
<!-- pageCenter over -->
</div>

<!--订单详情 弹层-->
<div class="order-popup" id="orderDetails">
	<div class="close"></div>
	<h2 class="title">订单详情</h2>
	<div class="orderDetail-box"></div>
</div>
<!--订单详情 弹层 end-->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>

<script>
//日期控件
$('#wdate1').click(function(){
	 WdatePicker({
         maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
         readOnly:true
     });
});
$('#wdate2').click(function(){
	WdatePicker({
        minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
        readOnly:true
    });
});
$(".btn-add").click(function(){
	$("#exportRefundFlowForm").attr("action","/refundManager/exportRefundFlow").submit();
}); 

function viewRefundInfo(flowId,type){
	if(flowId == null || flowId == ''){
		bghui();
		Alert({str:"支付流水号不能为空!"});
		return;
	}
	var url ="/refundManager/viewRefundInfo?flowId="+flowId+"&type="+type;
	location.href = url;
}
</script>
</body>
</html>