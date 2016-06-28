<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的报价</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/popup.css" />
        <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<#import 'page.ftl' as fenye>
	<#import 'macro.ftl' as tools>
	<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
</head>
<body>
<!-- 头部  -->
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
	<#include 'common/left.ftl'>
    <div class="hy-right fr">
         <div class="order-box stock-box">
            <h2 class="o-title">我的报价</h2>
            <form action="/Quote/QuoteList" method="get" id="conditionForm" >
             <div class="o-search mt20 clearfix">
                <span class="mr10">报价品种：<input type="text" class="txt" id="breedname" name="breedname" value="${busiQuoteDto.breedname}" /></span>
                <span class="mr10">报价状态：<select class="text-store text-select1" name="status">
                        	<option value="-1">全部</option>
		            		<#if stateMap??>
			            		<#list stateMap?keys as key>
			            		  <#if busiQuoteDto.status = key>
			            			<option value="${key}" selected>${stateMap[key]}</option>
			            			<#else>
			            			 <option value="${key}">${stateMap[key]}</option>
			            			</#if>
			            		</#list>
		            		</#if>
                    	</select></span>
                <span class="mr10">发布时间：<input type="text" class="txt dat" id="date_b" name="StartDate" value="${(busiQuoteDto.StartDate)!''}" /> — <input type="text" class="txt dat" id="date_e"  name="EndDate" value="${(busiQuoteDto.EndDate)!''}"/></span>
                <span class="mr10 fr"><input type="submit" class="btn-red" value="查询" /></span>
            </div>
            </form>
            <div id="tabsCont">
             <table cellspacing="1" cellpadding="1" width="100%" class="mt20" bgcolor="#e1e1e1" style="display: block;">
                    <tr bgcolor="#f5f5f5" align="center">
                        <th width="10%" height="35"><strong>报价品种</strong></th>
                        <th width="8%"><strong>报价</strong></th>
                        <th width="30%"><strong>备注</strong></th>
                        <th width="9%"><strong>交易员</strong></th>
                        <th width="10%"><strong>联系方式</strong></th>
                        <th width="8%"><strong>报价时间</strong></th>
                        <th width="10%"><strong>报价状态</strong></th>
                        <th width="15%"><strong>操作</strong></th>
                    </tr>
                 <#if page.results??&&page.results?size gt 0>
	             <#list page.results as quote>
                    <tr bgcolor="#ffffff">
                        <td class="blue">${(quote.breedname)!''}</td>
                        <td><@tools.money num=quote.quotePrice format="0.##"/>元</td>
                        <td>${(quote.quoteDescription)!''}</td>
                        <td><#if quote.username??>${(quote.username)!''}<#else>${salename}</#if></td>
                        <td><#if quote.mobile??>${(quote.mobile)!''}<#else>${salephone}</#if></td>
                        <td><#if quote.createTime??>${quote.createTime?string('yyyy-MM-dd')}</#if></td>
                      <#if quote.status==0>
                        <td class="red">
                        </#if>
                        <#if quote.status==10>
                         <td class="green">
                         </#if>
                        <#if quote.status==-10>
                        <td class="gray">
                        </#if>
                        <#if stateMap??>
			            		<#list stateMap?keys as key>
			            		  <#if quote.status = key>
			            			${stateMap[key]}
			            			</#if>
			            		</#list>
		            		</#if></td>
                        <td><div class="dis-in-bk ml10"><a href="${JOINTOWNURL}/busiPur/PurchaseDetail?purchaseId=${quote.purchaseId}" class="blue"  target="_blank">查看采购信息</a></div>
                        </td>
                    </tr>
                    </#list>
                      <#else>
                    <tr align="center">
				    	<td colspan="9" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td>
				    </tr>	
                    </#if>
                </table>               
            </div>
            <@fenye.pages page=page form="conditionForm"/>
        </div>
	</div>
</div>
<!-- 表格底部 end  -->
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
	//日期控件
	    $('#date_b').click(function(){
	        WdatePicker({
	            dateFmt:'yyyy/MM/dd',
	            maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
	            readOnly:true
	        });
	    });
	    $('#date_e').click(function(){
	        WdatePicker({
	            dateFmt:'yyyy/MM/dd',
	            minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
	            readOnly:true
	        });
	    });
});



	
	/* function check(){		
			$("#conditionForm").attr("action","/Quote/QuoteList");
		
	} */
	    </script>
	   
</body>

</html>