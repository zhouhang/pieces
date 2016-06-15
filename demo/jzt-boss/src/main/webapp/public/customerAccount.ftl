<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>客户账务统计</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jquerytab.css">
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<!-- head start -->
<#include "home/top.ftl" />
<!-- head over -->
<div class="wapper">
<!-- nav start -->
	<#include "home/left.ftl" />
<!-- nav over -->

<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">客户账务统计</h1>
            <form action="/customerAccount" id="customerAccountForm" method="POST">
                <ul class="form-search">
                    <li>
                        &nbsp;&nbsp;&nbsp;&nbsp;时间段：<input type="text" id="wdate1" name="startDate" class="text text-3 mr10 Wdate"  value="${page.params.cAccountDto.startDate!'' }" />至
                        <input type="text" id="wdate2" name="endDate" class="text text-3 ml10 Wdate" value="${page.params.cAccountDto.endDate!'' }" />
                        <span class="ml10" >大区：</span><div id="select-bg">
	                        <span>
		                        <select name="orgId">
		                        	<option value="">全部</option>
		                        	<#if orgList??>
		                        		<#list orgList as ol>
		                        			<option value="${ol.id!'' }" <#if page.params.cAccountDto.orgId == ol.id>selected</#if> >${ol.orgName!'' }</option>
		                        		</#list>
		                        	</#if>	
		                        </select>
	                        </span>
                        </div> 
                        <span class="ml10" >业务人员：
                        </span><input class="text text-2 mr10" name="salesMan" type="text" value="${page.params.cAccountDto.salesMan!'' }" />
                    </li>
                    <li><span>&nbsp;&nbsp;&nbsp;&nbsp;会员名：</span><input class="text text-2 mr10" name="userName" type="text" value="${page.params.cAccountDto.userName!'' }"/>
                        <span>公司/真实姓名：</span><input class="text text-2 mr10" name="realName" type="text" value="${page.params.cAccountDto.realName!'' }" />
                        <a href="javascript:;" onclick="resetForm('customerAccountForm')" class="col_blue">清除</a> <input type="submit" class="btn-blue ml28" id="btn-blue" value="查询" /> 
                        <@shiro.hasPermission name="客户账务统计-导出Excel"> 
                        	<input type="button" id="excelBtn" class="btn-blue ml28" id="btn-blue" value="导出Excel" /> 
                    	</@shiro.hasPermission>
                    </li>
                    <li>
                        &nbsp;&nbsp;&nbsp;&nbsp;查询结果统计：
                        <span class="ml10" >仓单总量：<#if caTotals?? >${caTotals.wlTotal!0 } <#else> 0</#if>公斤</span>
                        <span class="ml10" >挂牌总量：<#if caTotals?? >${caTotals.listingTotal!0 } <#else> 0</#if>公斤</span>
                        <span class="ml10" >销售总量：<#if caTotals?? >${caTotals.orderTotal!0 } <#else> 0</#if>公斤</span>
                        <span class="ml10" >销售总金额：<#if caTotals?? >${caTotals.orderAmountTotal!0 } <#else> 0</#if>元</span>
                        <span class="ml10" >采购总量：<#if caTotals?? >${caTotals.purchaseTotal!0 } <#else> 0</#if>公斤</span>
                        <span class="ml10" >采购总金额：<#if caTotals?? >${caTotals.purchaseAmountTotal!0 } <#else> 0</#if>元</span>
                    </li>
                </ul>
            </form>
            <form action="/customerAccount/exportCustomerAccount"  method="post" id="exportCustomerAccount">
            	<input type="hidden" name="startDate" class="text text-3 ml10" value="${page.params.cAccountDto.startDate!'' }" />
            	<input type="hidden" name="endDate" class="text text-3 ml10" value="${page.params.cAccountDto.endDate!'' }" />
            	<input type="hidden" class="text text-2 mr10" name="salesMan" value="${page.params.cAccountDto.salesMan!'' }" />
            	<input type="hidden" class="text text-2 mr10" name="userName" value="${page.params.cAccountDto.userName!'' }"/>
            	<input type="hidden" class="text text-2 mr10" name="realName" value="${page.params.cAccountDto.realName!'' }" />
            	<input type="hidden" class="text text-2 mr10" name="orgId" value="${page.params.cAccountDto.orgId!'' }" />
            </form>
            <div class="use-item1 mt20" style="width: 100%; overflow-x: auto;">
                <table class="table-1" width="2260" cellpadding="1" cellspacing="1">
                    <tr>
                        <th></th>
                        <th colspan="3"></th>
                        <th colspan="4">入库情况</th>
                        <th colspan="3">挂牌情况</th>
                        <th colspan="5">销售情况</th>
                        <th colspan="5">采购情况</th>
                        <th colspan="2"></th>
                    </tr>
                    <tr>
                        <th width="60">序号</th>
                        <th width="100">会员名</th>
                        <th width="100">公司/真实姓名</th>
                        <th width="100">单位</th>
                        <th width="100">仓单总量</th>
                        <th width="100">入库总量</th>
                        <th width="100">仓单笔数(笔)</th>
                        <th width="100">仓单品种数(个)</th>
                        <th width="100">挂牌总量</th>
                        <th width="100">挂牌笔数(笔)</th>
                        <th width="100">挂牌品种数(个)</th>
                        <th width="100">销售总量</th>
                        <th width="100">销售品种数(个)</th>
                        <th width="100">销售笔数(笔)</th>
                        <th width="100">销售金额(元)</th>
                        <th width="100">销售总金额(元)</th>
                        <th width="100">采购总量</th>
                        <th width="100">采购品种数(个)</th>
                        <th width="100">采购笔数(笔)</th>
                        <th width="100">采购金额(元)</th>
                        <th width="100">采购总金额(元)</th>
                        <th width="100">大区</th>
                        <th width="100">业务人员</th>
                    </tr>
                    <#if (page.results?size>0)>
                    	<#list page.results as r> 
		                    <tr>
		                        <td>1</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.userName!'' }</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.realName!'' }</td>
		                        <td>${r.unit!'' }</td>
		                        <td>${r.wlTotal!0 }</td>
		                        <td>${r.inWLTotal!0 }</td>
		                        <td>${r.wlNums!0 }</td>
		                        <td>${r.wlBreeds!0 }</td>
		                        <td>${r.listingAmount!0 }</td>
		                        <td>${r.listingNum!0 }</td>
		                        <td>${r.listingBreedNum!0 }</td>
		                        <td>${r.orderTotalAmt!0 }</td>
		                        <td>${r.orderBreedNum!0 }</td>
		                        <td>${r.orderNum!0 }</td>
		                        <td>${r.orderPayment!0 }</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.orderPayment!0 }</td>
		                        <td>${r.purchaseAmount!0 }</td>
		                        <td>${r.purchaseBreedNum!0 }</td>
		                        <td>${r.purchaseOrderNum!0 }</td>
		                        <td>${r.purchaseOrderAmt!0 }</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.purchaseOrderAmt!0 }</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.orgName!'' }</td>
		                        <td <#if rowspan[r.userName]??>rowspan=${rowspan[r.userName]}</#if>>${r.salsManName!'' }</td>
		                    </tr>
	                    </#list>
	                <#else>
	                    <tr>
	                    	<td colspan="23">没有数据!</td>
	                    </tr>
                    </#if>
                    
                </table>
            </div>
			<@tools.pages page=page form="customerAccountForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script>
	//日期控件
	$('#wdate1').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd HH:mm:ss',
			maxDate:'#F{$dp.$D(\'wdate2\',{d:-1});}',
			readOnly:true
		});
	});
	$('#wdate2').click(function(){
		WdatePicker({
			startDate:'%y-%M-%d',
			dateFmt:'yyyy-MM-dd HH:mm:ss',
			minDate:'#F{$dp.$D(\'wdate1\',{d:1});}',
			readOnly:true
		});
	});
	
	$(function(){
		var _tr = $('table tr');
		$.each(_tr,function(i,n){
			if(i > 1){
				if(_tr.eq(i).children('td').length==1){
					return;
				}
				_tr.eq(i).children('td').eq(0).text(i-1);
				var cur_td1 = _tr.eq(i).children('td').eq(1);
				if(cur_td1.attr('rowspan') != undefined){
					var _rowspan = cur_td1.attr('rowspan');
					var _orderAmt = parseInt(_tr.eq(i).children('td').eq(14).text());//销售金额
					var _purchaseAmt = parseInt(_tr.eq(i).children('td').eq(19).text());//采购金额
					for(var j = 0; j < _rowspan-1; j++){
						_tr.eq(i+j+1).children('td').eq(1).remove();//用户名
						_tr.eq(i+j+1).children('td').eq(1).remove();//真实姓名
						//销售金额处理
						_orderAmt = _orderAmt + parseInt(_tr.eq(i+j+1).children('td').eq(13).text());
						_tr.eq(i+j+1).children('td').eq(13).remove();
						//采购金额处理
						_purchaseAmt = _purchaseAmt + parseInt(_tr.eq(i+j+1).children('td').eq(17).text());
						_tr.eq(i+j+1).children('td').eq(17).remove();
						//大区
						_tr.eq(i+j+1).children('td').eq(17).remove();
						//业务员
						_tr.eq(i+j+1).children('td').eq(17).remove();
					}
					_tr.eq(i).children('td').eq(15).text(_orderAmt);
					_tr.eq(i).children('td').eq(20).text(_purchaseAmt);
				} 
			}
		});
		
		$("#excelBtn").click(function(){
			$("#exportCustomerAccount").submit();
		});
	})
	
	//清除
    function resetForm(id){
    	$("#" + id + " :text").val("");
    	$("#" + id + " select").val("");
    }
</script>
</body>
</html>