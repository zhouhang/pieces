<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>采购信息审核</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/jquery-ui/jquery-ui.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#import 'macro.ftl' as tools>
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
            <h1 class="title1">采购信息审核</h1>
            <form action="/purchaseAudit" method="post" id="queryForm">
                <ul class="form-search ac-search">
                    <li><span>采购批次号：</span><input class="text text-4" type="text" name="purchaseCode" value="${query.purchaseCode!''}"/>&nbsp;
                    	<span>&nbsp;&nbsp;&nbsp;采购品种：</span><input class="text text-4" type="text" name="breedName" value="${query.breedName!''}"/>
                        <span>采购单位：</span><input class="text text-4" type="text" name="purchaseOrg" value="${query.purchaseOrg!''}"/>&nbsp;
                        <span>会员名：</span><input class="text text-4" type="text" name="purchaser" value="${query.purchaser!''}"/>&nbsp;
                     </li>
                     <li>
                         <span>审核状态：</span><div id="select-bg"><span><select name="auditStatus">
                            <option value="">全部</option>
                            <#if stautsMap??>
                            	<#list stautsMap?keys as key>
                            		<option value="${key!''}" <#if query.auditStatus == key>selected</#if>>${stautsMap[key]!''}</option>
                            	</#list>
                            </#if>
                        </select></span></div>&nbsp;
                         <span>发布时间：</span><input class="text text-date" id="date_b" type="text" name="createTimeStart" value="${query.createTimeStart!''}"/> — <input class="text text-date" id="date_e" type="text" name="createTimeEnd" value="${query.createTimeEnd!''}"/>&nbsp;
                         &nbsp;<a href="javascript:;" onclick="resetForm('queryForm')" class="col_999">清除</a>&nbsp;
                         <input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                     </li>
                </ul>
            </form>
            <div class="use-item1 mt20">
                <table class="table-1" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="4%" class="bgf5">编号</th>
                        <th width="10%" class="bgf5">采购批次号</th>
                        <th width="6%" class="bgf5">采购品种</th>
                        <th width="8%" class="bgf5">规格等级</th>
                        <th width="6%" class="bgf5">产地</th>
                        <th width="9%" class="bgf5">采购数量</th>
                        <th width="8%" class="bgf5">采购单位</th>
                        <th width="8%" class="bgf5">会员名</th>
                        <th width="8%" class="bgf5">交易员</th>
                        <th width="5%" class="bgf5">有效期</th>
                        <th width="12%" class="bgf5">发布时间</th>
                        <th width="8%" class="bgf5">审核状态</th>
                        <th width="8%" class="bgf5">操作</th>
                    </tr>
                    <#if page.results?? && (page.results?size>0)>
                    	<#list page.results as purchase>
		                    <tr>
		                    	<td>${purchase.purchaseId!''}</td>
		                        <td>${purchase.purchaseCode!''}</td>
		                        <td>${purchase.breedName!''}</td>
		                        <td>${purchase.standardLevel!''}</td>
		                        <td>${purchase.origin!''}</td>
		                        <td><#if purchase.quantity??>${purchase.quantity}<#else>0</#if>${purchase.wunitName!''}</td>
		                        <td>${purchase.purchaserOrg!''}</td>
		                        <td><a href="/getMemberManage/getMemberByUserName?memberName=${purchase.purchaser!''}">${purchase.purchaser!''}</a></td>
		                        <!--<td><#if purchase.tradersName??>${purchase.tradersName}<#else>${defaultTradersName}</#if></td>-->
		                        <td>${purchase.tradersName!''}</td>
		                        <td><#if purchase.validPeriod??>${purchase.validPeriod}天</#if></td>
		                        <td>${purchase.createTime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
		                        <td>
		                        	<#if purchase.status?string == '0'>
		                        		<span class="col_red">${stautsMap[purchase.status?string]!''}</span>
		                        	<#elseif purchase.status?string == '-10'>
		                        		<span class="col_999">${stautsMap[purchase.status?string]!''}</span>
		                        	</#if>
		                        </td>
		                        <td>
			                        <@shiro.hasPermission name="采购信息审核-查看审核详情">
			                        	<a href="/purchaseAudit/detaiInfo?purchaseId=${purchase.purchaseId}" class="blue">查看详情</a>
			                        </@shiro.hasPermission>
		                        </td>
		                    </tr>
                    	</#list>
                    <#else>
                    	<tr>
                    		<td colspan="13">暂无数据!</td>
                    	</tr>
                    </#if>
                </table>
            </div>

            <@tools.pages page=page form="queryForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>

<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script>
    $(function(){
        //日期控件
        $('#date_b').click(function(){
            WdatePicker({
                maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
                readOnly:true,
                dateFmt:'yyyy-MM-dd'
            });
        });
        $('#date_e').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
                readOnly:true,
                dateFmt:'yyyy-MM-dd'
            });
        });
        
        //去掉输入框首尾的空格
        $("#queryForm :text").blur(function(){
        	$(this).val($(this).val().replace(/(^\s*)|(\s*$)/g, ""));
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