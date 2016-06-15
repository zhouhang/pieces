<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的珍药材</title>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/jquery-ui/jquery-ui.css" />    
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/store_foreground.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/list.css"  />
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<#import 'page.ftl' as fenye>
<#import 'macro.ftl' as tools>
<#assign TIPS="对不起，暂无数据!" /> <!--配置常用提示语-->
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
	<#include 'common/left.ftl'>
    <div class="hy-right fr">
                <div class="cd-main">
            <h1 class="title_deal">我的仓单</h1>
            <form action="/whlist/manager" method="get" id="conditionForm">
                <ul class="stage-search">
                    <li>
                      <span>所在仓库：</span><select id="wareHouseId" name="wareHouseId" class="text-store text-select" style="width: 155px;">
                        <option value="">全部仓库</option>
                         <#if warehouseslist??>
		            		<#list warehouseslist as warehouses>
		            			<option value="${warehouses.wareHouseId}" <#if page.params.busiWhlistSearchDto.wareHouseId == warehouses.wareHouseId>selected</#if>>${warehouses.wareHouseName}</option>
		            		</#list>
	            		</#if>
                    </select>
                     <span>入库时间：</span><input type="text" class="text-store text-6 mr10 Wdate" id="datetimepicker1" name="startWlrkDate" value="${(page.params.busiWhlistSearchDto.startWlrkDate)!''}"  />至<input type="text" class="text-store text-6 ml10 Wdate" id="datetimepicker2" name="endWlrkDate" value="${(page.params.busiWhlistSearchDto.endWlrkDate)!''}"/>
                    <span>品种：</span><input class="text-store text-5" type="text" id="breedCode" name="breedName" value="${(page.params.busiWhlistSearchDto.breedName)!''}"/>
                    <br/><span>仓单编号：</span><input class="text-store text-5" type="text" id="wlId" name="wlId" value="${(page.params.busiWhlistSearchDto.wlId)!''}"/>
                    <span><a class="col_999" href="javascript:void(0);" onclick="$(':input','#conditionForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');">清空</a></span><input type="submit" class="btn-red ml10" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div>
               <table class="table-store" width="100%" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="100">仓单编号</th>
                        <th width="100">品种</th>
                        <th width="100">产地</th>
                        <th width="100">等级/规格</th>
                        <!-- <th width="100">状态</th> -->
                        <th width="150">现存量/总重量</th>
                        <th width="150">所在仓库</th>
                        <th width="100">入库时间</th>
                        <th width="150">操作</th>
                    </tr>
                    <#if page.results ?? && page.results?size gt 0>
	                   	<#list page.results as busiWhlist>
		                    <tr>
		                        <td><a href="/whlist/detail?wlid=${busiWhlist.wlid!'' }">${busiWhlist.wlid!'' }</a></td>
		                        <td>${busiWhlist.breedname!'' }</td>
		                        <td class="opr-btn">
			                        <span class="operate-1 operate-a">
			                        	<#if busiWhlist.origin?? && busiWhlist.origin?length gt 6>
			                    			${busiWhlist.origin?substring(0,6)}...
			                    		<#else>
			                    			${busiWhlist.origin!'' }
			                    		</#if>
			                        	<div class="tips tipa" align="left">
				                        	<span class="sj"></span>
				                        	${busiWhlist.origin!'' }
			                        	</div>
			                        </span>
			                    </td>
		                        <td>${busiWhlist.grade!'' }</td>
		                        <!-- <#if busiWhlist.wlstate ??>
					              	<#if busiWhlist.wlstate==0>
					              		<td>未质押</td>
					              	<#elseif busiWhlist.wlstate==1>
					              		<td class="text_gray">已质押</td>
					              	</#if>
				              	</#if> -->
		                        <td><@tools.money num=busiWhlist.wlsurplus format="0.##"/>${busiWhlist.dictvalue!''}/<@tools.money num=busiWhlist.wltotal format="0.##"/>${busiWhlist.dictvalue!''}</td>
		                        <td>${busiWhlist.warehousename!''}</td>
		                        <td><#if busiWhlist.wlrkdate??>
		                        	  ${busiWhlist.wlrkdate?string("yyyy-MM-dd")!'' }
		                        	</#if>
		                        </td>
		                        <td>
		                        	<a href="/whlist/detail?wlid=${busiWhlist.wlid!'' }">详细</a>  
	                    			<#if busiWhlist.wlsurplus ?? && busiWhlist.wlstate ??>
						              	<#if busiWhlist.wlsurplus!=0 && busiWhlist.wlstate==0>
						              	  | <a href="/listing/add?wlid=${busiWhlist.wlid!'' }">挂牌</a> 
						              	</#if>
					              	</#if>
		                        </td>
		                    </tr> 
		                 </#list>
	                 <#else>
	                    <tr align="center">
					    	<td colspan="11" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td>
					    </tr>	
	                 </#if>  
                </table>
            </div>
            <@fenye.pages page=page form="conditionForm"/>
        </div>
</div></div>
<!-- 表格底部 end  -->
<#include 'common/footer.ftl'>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript"> 
    $(function(){
        //日期控件初始化
		$('#datetimepicker1').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				maxDate:'#F{$dp.$D(\'datetimepicker2\',{d:-1});}',
				readOnly:true
			});
		});
		$('#datetimepicker2').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				minDate:'#F{$dp.$D(\'datetimepicker1\',{d:1});}',
				readOnly:true
			});
		});
	});
</script>
</body>
</html>