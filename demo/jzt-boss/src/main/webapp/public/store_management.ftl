<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>仓单查询</title>
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/store.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<#include "home/top.ftl" />  
<div class="wapper">
<#include "home/left.ftl" /> 
<!-- pageCenter start -->
    <div class="main">
        <div class="page-main">
            <h1 class="title1">仓单查询</h1>
            <form id="conditionForm" action="/busiWhlistManage" method="post">
                <ul class="store-search">
                    <li>
                        <span>仓单编号：</span><input name="wlId" value="${(page.params.busiWhlistSearchDto.wlId)!''}" class="text-store text-7" type="text" />
                        <span>入库时间：</span><input id="wdate1" type="text" class="text-store text-6 mr10 Wdate" name="startWlrkDate" value="${page.params.busiWhlistSearchDto.startWlrkDate!''}" />至<input id="wdate2" type="text" class="text-store text-6 ml10 Wdate" name="endWlrkDate" value="${page.params.busiWhlistSearchDto.endWlrkDate!''}"/>
                        <span>品种</span>：<input name="breedName" value="${(page.params.busiWhlistSearchDto.breedName)!''}" class="text-store text-7" type="text" />
                    </li>
                    <li>
                        <span>所在仓库：</span><div id="select-bg"><span>
                        <select name="wareHouseId">
                        <option value="">全部</option>
                    		<#if busiWareHouses??>
                    			<#list busiWareHouses as busiWareHouse>
	                    			<option value="${busiWareHouse.wareHouseId!''}" <#if page.params.busiWhlistSearchDto.wareHouseId == busiWareHouse.wareHouseId>selected</#if>>${busiWareHouse.wareHouseName!''}</option>
	                    		</#list>
                    		</#if>
                    </select></span></div>

                      <span>货主：</span><input name="account" value="${(page.params.busiWhlistSearchDto.account)!''}" class="text-store text-7" type="text" />
                      <!--<span>状态：</span><div id="select-bg"><span><select name="wlState">
                        <option value="">全部</option>
                   		<#if wlStateMap??>
                   			<#list wlStateMap?keys as key>
                    			<option value="${key!'' }" <#if page.params.busiWhlistSearchDto.wlState == key>selected</#if>>${wlStateMap[key]!'' }</option>
                    		</#list>
                   		</#if>
                    </select>
						</span></div>-->
                    <span></span><input type="submit" class="btn-blue" id="btn-blue" value="查询" />
                    <@shiro.hasPermission name="仓单查询-仓单信息导出">
                    <input type="button" class="btn-blue" id="exportExcel" value="导出Excel"/>
                    </@shiro.hasPermission></li>
                </ul>
            </form>
            
            <form action="/busiWhlistManage/exportWhlistExcel" method="POST" id="exportWhlistForm">
                  <input type="hidden" name="wlId" value="${page.params.busiWhlistSearchDto.wlId}" />
               	  <input type="hidden" name="startWlrkDate" value="${page.params.busiWhlistSearchDto.startWlrkDate}" />
                  <input type="hidden" name="endWlrkDate" value="${page.params.busiWhlistSearchDto.endWlrkDate}"/>
                  <input type="hidden" name="breedName" value="${page.params.busiWhlistSearchDto.breedName}"/> 
                  <input type="hidden" name="wareHouseId" value="${page.params.busiWhlistSearchDto.wareHouseId}"/>
                  <input type="hidden" name="account" value="${page.params.busiWhlistSearchDto.account}"/>
            </form>
            <div class="use-item1" style=" margin-top:20px;">
                <table class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">仓单编号</th>
                        <th width="100">品种</th>
                        <th width="100">货主</th>
                        <th width="100">产地</th>
                        <th width="100">等级/规格</th>
                        <!--<th width="100">状态</th>-->
                        <th width="150">已挂牌数量/总重量</th>
                        <th width="150">所在仓库</th>
                        <th width="150">入库时间</th>
                    </tr>
                    <#if (page.results?size>0)>
	                    <#list page.results as busiWhlist>
	                    	<tr>
	                    		<td><a href="/busiWhlistManage/detail?wlid=${busiWhlist.wlid!''}">${busiWhlist.wlid!''}</a></td>
	                    		<td>${busiWhlist.breedname!''}</td>
	                    		<td><a href="/getMemberManage/getMemberByUserName?memberName=${busiWhlist.username!''}">${busiWhlist.username!''}</a></td>
	                    		<td>${busiWhlist.origin!''}</td>
	                    		<td>${busiWhlist.grade!''}</td>
	                    		<!--<td>
		                    		<#if busiWhlist.wlstate == 0>
		                    			未质押
									<#elseif busiWhlist.wlstate == 1> 
										已质押
									</#if>
	                    		</td>-->
	                    		<td>
	                    		<@tools.money num=(busiWhlist.wltotal-busiWhlist.wlsurplus) format="0.##"/>${busiWhlist.dictvalue!''}/
	                    		<@tools.money num=busiWhlist.wltotal format="0.##"/>
	                    		${busiWhlist.dictvalue!''}</td>
	                    		<td>${busiWhlist.warehousename!''}</td>
	                    		<td>${(busiWhlist.wlrkdate?string("yyyy-MM-dd HH:mm:ss"))!''}</td>
	                    	</tr>
	                    </#list>
	                    <#else>
	                    	<tr>
	                    		<td colspan="8">没有数据!</td>
	                    	</tr>
                    </#if>
                </table>
            </div>
			<@tools.pages page=page form="conditionForm"/>
        </div>
    </div>
<!-- pageCenter over -->
</div>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
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
	
	//导出excel
	$("#exportExcel").click(function(){
		$("#exportWhlistForm").submit();
	}); 
</script>
</body>
</html>