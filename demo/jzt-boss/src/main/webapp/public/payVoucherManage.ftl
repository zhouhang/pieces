<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>资金流水列表</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
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
    		<h1 class="title1">线下支付管理</h1>
            <form action="/payVoucherManage" method="POST" id="payFlowForm">
                <ul class="store-search">
                    <li>
                      <span>收款人名称：</span><input name="payeeName" value="${page.params.payFlowListDto.payeeName}" class="text-store text-7" type="text" />
                      <span>支付时间：</span>
                   	  <input id="wdate1" name="payStartDate" value="${page.params.payFlowListDto.payStartDate}" type="text" class="text-store text-7 mr10 Wdate" />-
                      <input id="wdate2" name="payEndDate" value="${page.params.payFlowListDto.payEndDate}" type="text" class="text-store text-7 ml10 Wdate" /> 
                      <span>付款人名称：</span><input name="payerName" value="${page.params.payFlowListDto.payerName}" class="text-store text-7" type="text" />
                      <span>支付流水号：</span><input name="payFlowId" value="${page.params.payFlowListDto.payFlowId}" class="text-store text-7" type="text" />
                    </li>
                    <li>
                    	<span>收款人账号：</span><input name="payeeAccount" value="${page.params.payFlowListDto.payeeAccount}" class="text-store text-7" type="text" />
                    	<span>订单号：</span><input name="orderId" value="${page.params.payFlowListDto.orderId}" class="text-store text-8" type="text" />
                    	<span>付款人账号：</span><input name="payerAccount" value="${page.params.payFlowListDto.payerAccount}" class="text-store text-7" type="text" />
                    	<span>支付状态：</span>
                    	<select name="payStatus" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if PayStatusEnumMap??>
	                   			<#list PayStatusEnumMap?keys as key>
	                    			<option value="${key!'' }" <#if page.params.payFlowListDto.payStatus == key>selected</#if>> ${PayStatusEnumMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select>
                    	<span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    </li>
                    <li>
                    	<span></span>
                   		<!--<@shiro.hasPermission name="资金流水-导出Excel"> </@shiro.hasPermission>  -->
                   		<input type="button" class="btn-blue" id="exportExcel" value="导出Excel" />
                   		<@shiro.hasPermission name="线下支付管理-添加支付记录"> 
                   		<input type="button" class="btn-blue" id="addPayVoucher" value="新建支付记录" />
                   		</@shiro.hasPermission>
                    </li>
                </ul>
            </form>
            <form action="" method="POST" id="exportPayVoucherForm">
                      <input type="hidden" name="payeeName" value="${page.params.payFlowListDto.payeeName}" class="text-store text-7" type="text" />
                   	  <input id="wdate1" type="hidden" name="payStartDate" value="${page.params.payFlowListDto.payStartDate}" type="text" class="text-store text-7 mr10 Wdate" />
                      <input id="wdate2" type="hidden" name="payEndDate" value="${page.params.payFlowListDto.payEndDate}" type="text" class="text-store text-7 ml10 Wdate" /> 
                      <input type="hidden" name="payerName" value="${page.params.payFlowListDto.payerName}" class="text-store text-7" type="text" />
                      <input type="hidden" name="payFlowId" value="${page.params.payFlowListDto.payFlowId}" class="text-store text-7" type="text" />
                      <input type="hidden" name="payeeAccount" value="${page.params.payFlowListDto.payeeAccount}" class="text-store text-7" type="text" />
                      <input type="hidden" name="orderId" value="${page.params.payFlowListDto.orderId}" class="text-store text-8" type="text" />
                      <input type="hidden" name="payerAccount" value="${page.params.payFlowListDto.payerAccount}" class="text-store text-7" type="text" />
                      <input type="hidden" name="payStatus" value="${page.params.payFlowListDto.payStatus }"/>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
            	<table id="busiListingTable" class="table-store" width="100%" cellpadding="1" cellspacing="1">
            		<tr>
                        <th width="130">收款人账号</th>
                        <th width="140">收款人名称</th>
                        <th width="120">付款人账号</th>
                        <th width="140">付款人名称</th>
                        <th width="100">金额</th>
                        <th width="90">创建时间</th>
                        <th width="90">支付时间</th>
                        <th width="120">类型</th>
                        <th width="100">订单号</th>
                        <th width="100">支付流水号</th>
                        <th width="100">支付状态</th>
                        <th width="100">支付渠道</th>
                        <th width="100">付款回单</th>
                        <th width="70">操作人</th>
                        <th width="100">操作时间</th>
                        <th width="100">操作</th>
                    </tr>
                    <#if (page.results?size>0) >
						<#list page.results as r>
						<tr>
							<td>${r.payeeAccount!''}</td>
							<td>
								<a href="/getMemberManage/getByUserId?userId=${r.payeeId!''}">${r.payeeName!''}
							</td>
							<td>${r.payerAccount!''}</td>
							<td>
								<a href="/getMemberManage/getByUserId?userId=${r.payerId!''}">${r.payerName!''}
							</td>
							<td><@tools.money num=r.amount format="0.##"/></td>
							<td>
								<#if (r.createTime)??>
									${r.createTime?string("yyyy-MM-dd HH:mm:ss")!''}
								</#if>
							</td>
							<td>
							<#if (r.payDate)??>
								<#if r.payDate?time?string?length gt 7>
									${r.payDate?string("yyyy-MM-dd HH:mm:ss")!''}
								<#else>
									${r.payDate?string("yyyy-MM-dd")!''}
								</#if>
							</#if>
							</td>
							<td>
								<#if amtTypeMap??>
		                   			<#list amtTypeMap?keys as key>
		                    			<#if (key == r.payType)>${amtTypeMap[key]!''}</#if>
		                    		</#list>
	                   			</#if>
							</td>
							<td>
								<!-- <a href="/bossorder?orderId=${r.orderId!''}">${r.orderId!''}</a> -->
								<a class="col_blue" name="nub" href="javascript:;" data-id="${r.orderId!''}" data-reclick="n">${r.orderId!''}</a>
							</td>
							<td>
								${r.payFlowId!''}
							</td>
							<td>
								<#if PayStatusEnumMap??>
		                   			<#list PayStatusEnumMap?keys as key>
		                    			<#if (key == r.payStatus)>${PayStatusEnumMap[key]!''}</#if>
		                    		</#list>
	                   			</#if>
							</td>
							<td>
								线下支付
							</td>
							<td>
								<#if (r.payVoucher != '')>
									<span><a href="javascript:void(0);" id="${RESOURCE_IMG_UPLOAD}/${r.payVoucher!''}">查看</a></span>
								<#else>
									无
								</#if>
							</td>
							<td>
								<#if (r.confirmor != '')>
									${r.confirmor!'' }
								<#else>
									无	
								</#if>
							</td>
							<td>
								<#if (r.confirmTime)??>
									${r.confirmTime?string("yyyy-MM-dd HH:mm:ss")!''}
								</#if>
							</td>
							<td class="opr-btn">
								<#if (r.payStatus == 0 )>
									<!-- <input id="${r.payFlowId!''}" type="button" class="btn-blue" onclick="javascritp:getPayFlowVoInfo('${r.payFlowId!''}');" value="确认付款"/> -->
									<@shiro.hasPermission name="线下支付管理-确认付款">
									<span class="operate-5" id="${r.payFlowId!''}" onclick="javascritp:getPayFlowVoInfo('${r.payFlowId!''}');" title="操作"></span>
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
			<@tools.pages page=page form="payFlowForm"/>
    	</div>
    </div>
<!-- pageCenter over -->
</div>

<!-- 图片弹层 -->
<div class="popup-box" id="picBox">
    <span class="close" id="Close"></span>
    <img src="" id="showImg"   />
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
<script type="text/javascript" src="${RESOURCE_JS}/js/imgView/jquery.imageView.js"></script>
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
	$("#exportPayVoucherForm").attr("action","/payVoucherManage/exportPayVoucher").submit();
}); 
$("#addPayVoucher").click(function(){
	location.href="/payVoucherManage/forwardAddPayVoucher";
});

$("td span a").click(function(){
	var path = $(this).attr("id");
	  $('#picBox').show();
      var html = '<div class="bghui"></div>';
      var height = $(document).height();
      $('body').append(html);
      $('.bghui').css('height',height);
	  $('#showImg').attr('src',path);
	  $('#picBox').imageView({width:600, height:400});
      return false;
});
$('#Close').click (function(){
    $(this).parents('#picBox').hide();
    $('.bghui').remove();
});

//根据订单号获取PayflowVo
function getPayFlowVoInfo(flowId){
	if(flowId == null || flowId == ''){
		bghui();
		Alert({str:"支付流水号不能为空!"});
		return;
	}
	location.href="/payVoucherManage/getFlowInfo?flowId=" + flowId;
}
</script>
</body>
</html>