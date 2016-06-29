<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>支付流水列表</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<#include "home/top.ftl" />
<div class="wapper">
	<#include "home/left.ftl" />
	<!-- 订单详情公用js --> 
	<#include "public/dialog_details_common.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">支付流水查询</h1>
            <form action="/payManager" method="POST" id="payFlowForm">
                <ul class="store-search">
                    <li>
                      <span>订单号：</span><input name="orderId" value="${page.params.payFlowListDto.orderId}" class="text-store text-8" type="text" />
                      <span>付款人名称：</span><input name="payerName" value="${page.params.payFlowListDto.payerName}" class="text-store text-7" type="text" />
                      <span>收款人名称：</span><input name="payeeName" value="${page.params.payFlowListDto.payeeName}" class="text-store text-7" type="text" />
                      <span>支付渠道：</span>
                      <select name="payChannel" class="text-store text-7">
                        	<option value="">全部</option>
                        	<#if (payChannelList)??>
	                   			<#list payChannelList as pcl>
	                   				<option value="${pcl.key!''}"<#if page.params.payFlowListDto.payChannel == pcl.key>selected</#if>>${pcl.name!'' }</option>
	                    		</#list>
                  			</#if>
                      </select>
                      <span>支付状态：</span>
                    	<select name="payStatus" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if PayStatusEnumMap??>
	                   			<#list PayStatusEnumMap?keys as key>
	                    			<option value="${key!'' }" <#if page.params.payFlowListDto.payStatus == key>selected</#if>>${PayStatusEnumMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select>
                    </li>
                    <li>
                    	<span>支付流水号：</span><input name="payFlowId" value="${page.params.payFlowListDto.payFlowId}" class="text-store text-7" type="text" />
                    	<span>支付时间：</span>
                   	  	<input id="wdate1" name="payStartDate" value="${page.params.payFlowListDto.payStartDate}" type="text" class="text-store text-7 mr10 Wdate" />-
                      	<input id="wdate2" name="payEndDate" value="${page.params.payFlowListDto.payEndDate}" type="text" class="text-store text-7 ml10 Wdate" />
                      	<span>支付金额：</span>
                      	<input name="amount" value="${page.params.payFlowListDto.amount!'' }" class="text-store text-7" type="text" />
                    	<!--<span>收款人账号：</span><input name="payeeAccount" value="${page.params.payFlowListDto.payeeAccount}" class="text-store text-7" type="text" />  -->
                    	<!-- <span>付款人账号：</span><input name="payerAccount" value="${page.params.payFlowListDto.payerAccount}" class="text-store text-7" type="text" /> -->
                    	
                    	<span></span>
                   		<!--<@shiro.hasPermission name="支付流水-导出Excel"> </@shiro.hasPermission>  -->
                   		<input type="button" class="btn-blue" id="exportExcel" value="导出Excel" />
                   		<span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    </li>
                </ul>
            </form>
            <form action="" method="POST" id="exportPayFlowForm">
                      <input type="hidden" name="payeeName" value="${page.params.payFlowListDto.payeeName!'' }" class="text-store text-7" type="text" />
                   	  <input id="wdate1" type="hidden" name="payStartDate" value="${page.params.payFlowListDto.payStartDate!'' }" type="text" class="text-store text-7 mr10 Wdate" />
                      <input id="wdate2" type="hidden" name="payEndDate" value="${page.params.payFlowListDto.payEndDate!'' }" type="text" class="text-store text-7 ml10 Wdate" /> 
                      <input type="hidden" name="payerName" value="${page.params.payFlowListDto.payerName!'' }" class="text-store text-7" type="text" />
                      <input type="hidden" name="payFlowId" value="${page.params.payFlowListDto.payFlowId!'' }" class="text-store text-7" type="text" />
                      <input type="hidden" name="payeeAccount" value="${page.params.payFlowListDto.payeeAccount!'' }" class="text-store text-7" type="text" />
                      <input type="hidden" name="orderId" value="${page.params.payFlowListDto.orderId!'' }" class="text-store text-8" type="text" />
                      <input type="hidden" name="payerAccount" value="${page.params.payFlowListDto.payerAccount!'' }" class="text-store text-7" type="text" />
                      <input type="hidden" name="payStatus" value="${page.params.payFlowListDto.payStatus!''  }"/>
                      <input type="hidden" name="payChannel" value="${page.params.payFlowListDto.payChannel!'' }" />
                      <input type="hidden" name="amount" value="${page.params.payFlowListDto.amount!'' }" />
            </form>
            <div class="use-item1" style=" margin-top:20px;">
            	<table id="busiListingTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
            		<tr>
                        <th width="150">订单号</th>
                        <th width="100">支付流水号</th>
                        <th width="150">付款人名称</th>
                        <th width="150">收款人名称</th>
                        <th width="100">支付金额</th>
                        <th width="100">实收金额</th>
                        <th width="100">手续费</th>
                        <th width="100">类型</th>
                        <th width="100">支付渠道</th>
                        <th width="100">支付状态</th>
                        <th width="150">支付时间</th>
                    </tr>
		            <#if (page.results?size>0) >
						<#list page.results as r>
							<tr>
								<td>
									<a class="col_blue" name="nub" href="javascript:;" data-id="${r.orderId!''}" data-reclick="n">${r.orderId!''}</a>
								</td>
								<td>${r.payFlowId!''}</td>
								<!-- 付款人名称 -->
								<td><a href="/getMemberManage/getByUserId?userId=${r.payerId!''}">${r.payerName!''}</td>
								<!-- 收款人名称 -->
								<td><a href="/getMemberManage/getByUserId?userId=${r.payeeId!''}">${r.payeeName!''}</a></td>
								<!-- 支付金额 -->
								<td><@tools.money num=r.amount format="0.##"/></td>
								<!-- 实收金额 -->
								<td>${r.receivable }</td>
								<!-- 手续费 -->
								<td>${r.handlingFee }</td>
								<!-- 类型 -->
								<td><!-- 金额款项 -->
									<#if amtTypeMap??>
			                   			<#list amtTypeMap?keys as key>
			                   				<#if r.payType == key>
			                   					${amtTypeMap[key]!'' }
			                   				</#if>
			                    		</#list>
		                   			</#if>
								</td>
								<!-- 支付渠道 -->
								<td>
									<#if (payChannelList)??>
			                   			<#list payChannelList as pcl>
			                   				<#if r.payChannel == pcl.key>
			                   					${pcl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
								</td>
								<!-- 支付状态 -->
								<td>
									<#if PayStatusEnumMap??>
										<#list PayStatusEnumMap?keys as key>
			                   				<#if r.payStatus == key>
			                   					${PayStatusEnumMap[key]!'' }
			                   				</#if>
			                    		</#list>
									</#if>
								</td>
								<!-- 支付时间 -->
								<td>
								<#if (r.payDate)??>
										${r.payDate?string("yyyy-MM-dd HH:mm:ss")!''}
								</#if>
								</td>
								<!-- 
									<td>${r.payeeAccount!''}</td>
									<td>${r.payerAccount!''}</td>-->
							</tr>
						</#list>
					<#else>
						<tr>
                    		<td colspan="13">没有数据!</td>
                    	</tr>
					</#if>
				</table>
			</div>
			<@tools.pages page=page form="payFlowForm"/>
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
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
//日期控件
$('#wdate1').click(function(){
	WdatePicker({
		startDate:'%y/%M/%d',
		dateFmt:'yyyy/MM/dd',
		maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
		readOnly:true
	});
});
$('#wdate2').click(function(){
	WdatePicker({
		startDate:'%y/%M/%d',
		dateFmt:'yyyy/MM/dd',
		minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
		readOnly:true
	});
});

$("#exportExcel").click(function(){
	$("#exportPayFlowForm").attr("action","/payManager/exportPayFlow").submit();
}); 
</script>
</body>
</html>