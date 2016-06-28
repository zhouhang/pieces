<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>品种账务统计查询</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include "home/top.ftl" />  
<#import 'macro.ftl' as tools>
<#include "home/left.ftl" />
<div class="wapper">
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">品种账务统计查询</h1>
            <form action="/breedAccount" method="POST" id="breedAccountForm">
                <ul class="store-search">
                	<li>
                	    <span>时间：</span>
                   	    <input id="wdate1" name="startTime" value="${page.params.dto.startTime}" type="text" class="text-store text-4 mr10 Wdate" />-
                        <input id="wdate2" name="endTime" value="${page.params.dto.endTime}" type="text" class="text-store text-4 ml10 Wdate" />
                        <span>品种/品种编码：</span>
                		<input name="breedInfo" value="${page.params.dto.breedInfo}"  type="text" class="text-store text-7" />
                		<!-- 
                		<span>
				 			<input type="button" class="btn-add" id="btn-clear" onclick="resetForm('financeReportForm')" value="清除" />
				 		</span> 
				 		-->
                    	<@shiro.hasPermission name="品种账务统计-品种账务导出表报"> 
	                    	<span class="btn-add mb10 export">
	                    		<a href="#">导出报表</a>
				 			</span>
                    	</@shiro.hasPermission>
					 	<span>
				 			<input type="button" class="btn-add search" id="btn-blue" value="查询" />
				 		</span>
                	</li>
                </ul>
            </form>
            <form action="/breedAccount/export" method="POST" id="breedAccountExportForm">
            	<input name="startTime" value="${page.params.dto.startTime}"  type="hidden"/>
            	<input name="endTime" value="${page.params.dto.endTime}"  type="hidden"/>
            	<input name="breedInfo" value="${page.params.dto.breedInfo}"  type="hidden"/>
            </form>
           	<div class="use-item1" style='width:100%;margin-top:20px;'/>
            	<table id="" class="table-store" width="100%"  cellpadding="1" cellspacing="1">
            		<tr>
            			<th>品种</th>
            			<th>单位</th>
            			<th>仓单总量</th>
            			<th>入仓用户（个）</th>
            			<th>仓单笔数（笔）</th>
            			<th>挂牌总量</th>
            			<th>挂牌用户（个）</th>
            			<th>挂牌笔数（笔）</th>
            			<th>交易总量</th>
            			<th>交易总金额（元）</th>
            			<th>交易用户（个）</th>
            			<th>交易笔数（笔）</th>
            		</tr>
                   <#if (page.results?size>0) >
                    	<#list page.results as r>
                    		<tr>
                    			<td id="bn">
	                    			<#list	alikeMap?keys as key>
	                    				<#if key  == r.breedId>
	                    					
	                    				</#if>
	                    			</#list> 
	                    			${r.breedName!'' }
	                    		</td>
                    			<td>${r.unit!'' }</td>
                    			<td>${r.whlistTotal!'' }</td>
                    			<td>${r.whlistUserAmount!'' }</td>
                    			<td>${r.whlistAmount!'' }</td>
                    			<td>${r.listingTotal!'' }</td>
                    			<td>${r.listingUserAmount!'' }</td>
                    			<td>${r.listingAmount!'' }</td>
                    			<td>${r.orderTotal!'' }</td>
                    			<td>${r.orderTotalMoney!'' }</td>
                    			<td>${r.orderUserAmount!'' }</td>
                    			<td>${r.orderAmount!'' }</td>
                    		</tr>
                    	</#list>
                    	<#else>
                    	<tr>
                    		<td colspan="13">没有数据!</td>
                    	</tr>
                    </#if>
                    <tr>
                    	<td>查询结果统计</td>
                    	<td>仓单总量：</td>
                    	<td colspan="3"><#if count??>${count.whlistCount!0 } 公斤<#else>0</#if></td>
                    	<td>挂牌总量：</td>
                    	<td colspan="2"><#if count??>${count.listingCount!0} 公斤<#else>0</#if></td>
                    	<td>交易总量：</td>
                    	<td colspan="3"><#if count??>${count.orderCount!0} 公斤<#else>0</#if></td>
                    <tr>
				</table>
			</div>
			<@tools.pages page=page form="breedAccountForm"/>
    	</div>
    </div>
<!-- pageCenter over -->
</div>

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>

<script>
$(function(){

	//日期控件
		$('#wdate1').click(function() {
			WdatePicker({
				dateFmt : 'yyyy/MM/dd HH:mm:ss',
				//maxDate : '#F{$dp.$D(\'wdate2\',{d:1});}',
				isShowClear : false,
				readOnly : true
			});
		});
		$('#wdate2').click(function() {
			WdatePicker({
				dateFmt : 'yyyy/MM/dd HH:mm:ss',
				//minDate : '#F{$dp.$D(\'wdate1\',{d:+1});}',
				isShowClear : false,
				readOnly : true
			});
		});

		$(".export").click(function() {
			$("#breedAccountExportForm").submit();
		});

		$(".search").click(function() {
			$("#breedAccountForm").submit();
		})
	})
	
	//清除
    function resetForm(id){
    	$("#" + id + " :text").not('.Wdate').val("");
    	$("#" + id + " select").val("");
    }
</script>
</body>
</html>