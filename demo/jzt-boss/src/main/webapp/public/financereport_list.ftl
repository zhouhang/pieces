<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>订单账务报表查询</title>
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
	<!-- 订单详情公用js --> 
	<#include "public/dialog_details_common.ftl" /> 
<div class="wapper">
<!-- pageCenter start -->
    <div class="main">
    	<div class="page-main">
    		<h1 class="title1">订单账务报表查询</h1>
            <form action="/financeReport" method="POST" id="financeReportForm">
                <ul class="store-search">
                	<li>
                		<span>订单编号：</span>
                		<input name="orderid" value="${page.params.dto.orderid}"  type="text" class="text-store text-7" />
                		<span>挂牌编号：</span>
                		<input name="listingid" value="${page.params.dto.listingid}"  type="text" class="text-store text-7" />
                		<span>仓单编号：</span>
                		<input name="wlid" value="${page.params.dto.wlid}"  type="text" class="text-store text-7" />
                		<span>品种/品种编码：</span>
                		<input name="breedInfo" value="${page.params.dto.breedInfo}"  type="text" class="text-store text-7" />
                	</li>
                	<li>
                		<span>买家：</span>
                		<input name="buyer" value="${page.params.dto.buyer}"  type="text" class="text-store text-7" />
                		<span>卖家：</span>
                		<input name="seller" value="${page.params.dto.seller}"  type="text" class="text-store text-7" />
                		<span>大区：</span>
                    	<select name="org" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if (orgList)??>
	                        	<#list orgList as org>
	                        		<option value="${org.id }" <#if page.params.dto.org = org.id>selected</#if>>${org.orgName }</option>
	                        	</#list>
	                        </#if>
                    	</select>
                    	<span>业务员类型：</span>
                    	<select id="salesManType" name="salesManType" class="text-store text-7">
	                        <option value="">全部</option>
	                        <option value="1001" <#if page.params.dto.salesManType = 1001>selected</#if>>买方业务人员</option>
	                        <option value="1002" <#if page.params.dto.salesManType = 1002>selected</#if>>卖方业务人员</option>
                    	</select>
                    	<span>业务员：</span>
                    	<input id="salesMan" name="salesMan" value="${page.params.dto.salesMan}"  type="text" class="text-store text-7" />
                	</li>
                	<li>
                	    <span>资金变动时间：</span>
                   	    <input id="wdate1" name="startTime" value="${page.params.dto.startTime}" type="text" class="text-store text-4 mr10 Wdate" />-
                        <input id="wdate2" name="endTime" value="${page.params.dto.endTime}" type="text" class="text-store text-4 ml10 Wdate" />
                        <span>变动类型：</span>
                    	<select name="payType" class="text-store text-7">
	                        <option value="">全部</option>
	                        <#if (financingReportMap)??>
	                   			<#list financingReportMap?keys as key >
	                   				<option value="${key!''}"<#if page.params.dto.payType == key>selected</#if>>${financingReportMap[key]!'' }</option>
	                    		</#list>
                   			</#if>
                    	</select>
                    	<span>
				 			<input type="button" class="btn-add" id="btn-clear" onclick="resetForm('financeReportForm')" value="清除" />
				 		</span>
                    	<span class="btn-add mb10 export">
							<a href="#">导出报表</a>
			 			</span>
					 	<span>
				 			<input type="button" class="btn-add search" id="btn-blue" value="查询" />
				 		</span>
                	</li>
                </ul>
            </form>
            <form action="/financeReport/export" method="POST" id="financeReportExportForm">
            	<input name="orderid" value="${page.params.dto.orderid}"  type="hidden"/>
            	<input name="listingid" value="${page.params.dto.listingid}"  type="hidden"/>
            	<input name="wlid" value="${page.params.dto.wlid}"  type="hidden"/>
            	<input name="breedInfo" value="${page.params.dto.breedInfo}"  type="hidden"/>
            	<input name="buyer" value="${page.params.dto.buyer}"  type="hidden"/>
            	<input name="seller" value="${page.params.dto.seller}"  type="hidden"/>
            	<input name="startTime" value="${page.params.dto.startTime}" type="hidden"/>
                <input name="endTime" value="${page.params.dto.endTime}" type="hidden"/>
                <input name="payType" value="${page.params.dto.payType}" type="hidden"/>
                <input name="org" value="${page.params.dto.org}" type="hidden"/>
                <input name="salesManType" value="${page.params.dto.salesManType}" type="hidden"/>
                <input name="salesMan" value="${page.params.dto.salesMan}" type="hidden"/>
            </form>
           	<div class="use-item1" style='width:100%;margin-top:20px;overflow-x:scroll;'/>
            	<table id="" class="table-store" width="3800"  cellpadding="1" cellspacing="1">
            		<tr>
            			<th colspan="10">订单相关信息</th>
            			<th colspan="7">买家</th>
            			<th colspan="7">卖家</th>
            			<th colspan="5">平台</th>
            			<th>手续费</th>
            			<th colspan="2">买方业务人员</th>
            			<th colspan="2">卖方业务人员</th>
            			<th colspan="2">资金操作人</th>
            		</tr>
            		<tr>
                        <th width="100">订单号</th>
                        <th width="100">订单状态</th>
                        <th width="100">挂牌编号</th>
                        <th width="100">仓单编号</th>
                        <th width="150">品种/编码</th>
                        <th width="100">订单数量</th>
                        <th width="100">成交数量</th>
                        <th width="100">单位</th>
                        <th width="100">单价（元）</th>
                        <th width="100">成交总价（元）</th>
                        
                        <th width="100">付款人账号</th>
                        <th width="100">付款人名称</th>
                        <th width="100">流水号</th>
                        <th width="100">支出类型</th>
                        <th width="100">支出金额（元）</th>
                        <th width="150">支出时间</th>
                        <th width="100">支付渠道</th>
                        
                        <th width="100">收款人账号</th>
                        <th width="100">收款人名称</th>
                        <th width="100">流水号</th>
                        <th width="100">收入类型</th>
                        <th width="100">收入金额（元）</th>
                        <th width="150">收入时间</th>
                        <th width="100">支付渠道</th>
                        
                        <th width="100">流水号</th>
                        <th width="100">收入类型</th>
                        <th width="100">收入金额（元）</th>
                        <th width="150">收入时间</th>
                        <th width="100">支付渠道</th>
                        
                        <th width="100">手续费</th>
                        
                        <th width="100">大区</th>
                        <th width="100">业务人员</th>
                        
                        <th width="100">大区</th>
                        <th width="100">业务人员</th>
                        
                        <th width="100">账务发起人</th>
                        <th width="100">财务处理人</th>
                    </tr>
                   <#if (page.results?size>0) >
                    	<#list page.results as r>
                    		<tr>
                    			<td>
                    				<a class="col_blue" name="nub" href="javascript:;" data-id="${r.orderId!''}" data-reclick="n">${r.orderId!''}</a>
                    			</td>
                    			<td>
                    			<#if busiOrderStateMap??>
                    				<#list busiOrderStateMap?keys as key>
                    					<#if r.orderState == key>
			                   					${busiOrderStateMap[key]!'' }
			                   			</#if>
                    				</#list>
                    			</#if>
                    			</td>
                    			<td><a class="col_blue" name="sell" href="javascript:;" data-id="${r.listingid!''}" data-reclick="n">${r.listingid!''}</a></td>
                    			<td>${r.wlid!'' }</td>
                    			<td>
	                    			<#if (r.breedName)?? && (r.breedCode)??>
	                    				${r.breedName!'' }/${r.breedCode!'' }
	                    			</#if>
                    			</td>
                    			<td>${r.amount!'' }</td>
                    			<td>${r.volume!'' }</td>
                    			<td>${r.unit!'' }</td>
                    			<td>${r.unitPrice!''}</td> 
                    			<td>${r.totalPrice!''}</td>
                    			<!-- 买家信息 -->
                    			<td class="caption">
                    				<span class="col_blue">${r.buyerCode!''}</span>
                    				<span class="operate-1">
                    					<div class="tips tipa" align="left">
                    						<span class="sj"></span>姓名/公司名：${r.buyerName!''}<br/>联系方式：${r.buyerMobile!''}
                    					</div>
                    				</span>
                    			</td>
                    			<td>${r.buyerName!'' }</td>
                    			<td>${r.buyerFlowId!'' }</td>
                    			<td>
                    			<#if financingReportMap??>
                    				<#list financingReportMap?keys as key>
                    					<#if r.buyerType == key>
			                   					${financingReportMap[key]!'' }
			                   			</#if>
                    				</#list>
                    			</#if>
                    			</td>
                    			<td>${r.buyerAmount!'' }</td>
                    			<td>
	                    		<#if (r.buyerPayTime)??>
									<#if r.buyerPayTime?time?string?length gt 7>
										${r.buyerPayTime?string("yyyy-MM-dd HH:mm:ss")!''}
									<#else>
										${r.buyerPayTime?string("yyyy-MM-dd")!''}
									</#if>
								</#if>
                    			</td>
                    			<td>
                    				<#if (payChannelList)??>
			                   			<#list payChannelList as pl>
			                   				<#if r.buyerPayChannel == pl.key>
			                   					${pl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
                    			</td>
                    			<!-- 卖家信息 -->
                    			<td class="caption">
                    				<span class="col_blue">${r.sellerCode!''}</span> 
                    				<span class="operate-1">
                    					<div class="tips tipa" align="left">
                    						<span class="sj"></span>姓名/公司名：${r.sellerName!''}<br/>联系方式：${r.sellerMobile!''}
                    					</div>
                    				</span>
                    			</td>
                    			<td>${r.sellerName!'' }</td>
                    			<td>${r.sellerFlowId!'' }</td>
                    			<td>
                    			<#if financingReportMap??>
                    				<#list financingReportMap?keys as key>
                    					<#if r.sellerType == key>
			                   					${financingReportMap[key]!'' }
			                   			</#if>
                    				</#list>
                    			</#if>
                    			</td>
                    			<td>${r.sellerAmount!'' }</td>
                    			<td>
                    				<#if (r.sellerPayTime)??>
										${r.sellerPayTime?string("yyyy-MM-dd HH:mm:ss")!''}
									</#if>
                    			</td>
                    			<td>
                    				<#if (payChannelList)??>
			                   			<#list payChannelList as pl>
			                   				<#if r.sellerPayChannel == pl.key>
			                   					${pl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
                    			</td>
                    			<!-- 平台信息 -->
                    			<td>${r.platformFlowId!'' }</td>
                    			<td>
                    			<#if financingReportMap??>
                    				<#list financingReportMap?keys as key>
                    					<#if r.platformType == key>
			                   					${financingReportMap[key]!'' }
			                   			</#if>
                    				</#list>
                    			</#if>
                    			</td>
                    			<td>${r.platformAmount!'' }</td>
                    			<td>
                    				<#if (r.platformPayTime)??>
										${r.platformPayTime?string("yyyy-MM-dd HH:mm:ss")!''}
									</#if>
                    			</td>
                    			<td>
                    				<#if (payChannelList)??>
			                   			<#list payChannelList as pl>
			                   				<#if r.platformPayChannel == pl.key>
			                   					${pl.name!'' }
			                   				</#if>
			                    		</#list>
                   					</#if>
                    			</td>
                    			<td>${r.handlingCharge!''}</td>
                    			<!-- 人员信息 -->
                    			<td>${r.buyerOrg!'' }</td>
                    			<td>${r.buyerSalesMan!'' }</td>
                    			<td>${r.sellerOrg!'' }</td>
                    			<td>${r.sellerSalesMan!'' }</td>
                    			<td>${r.follower!'' }</td>
                    			<td>${r.accountant!'' }</td>
                    		</tr>
                    	</#list>
                   		<tr>
                   			<td>合计</td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td>
                   			<td>${totleMap.buyerTotle!'' }</td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td><td></td>
                   			<td>${totleMap.sellerTotle!'' }</td>
                   			<td></td><td></td><td></td><td></td>
                   			<td>${totleMap.platformTotle!'' }</td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td><td></td><td></td><td></td>
                   			<td></td>
                   		</tr>
                    	<#else>
                    	<tr>
                    		<td colspan="36">没有数据!</td>
                    	</tr>
                    </#if>
				</table>
			</div>
			<@tools.pages page=page form="financeReportForm"/>
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

<!--挂牌详情 弹层-->
<div class="order-popup detail" id="listingDetail">
    <div class="close"></div>
    <h1 class="title1">挂牌详情</h1>
    <div class="sellDetail-box"></div>
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
			$("#financeReportExportForm").submit();
		});

		$(".search").click(function() {
			var salesManType = $("#salesManType").val();
			var salesMan = $("#salesMan").val();
			if(salesManType!='' && salesManType!=null){
				if(salesMan==null || salesMan==''){
					bghui();
					Alert({str:"业务员名称不能为空!"});
					return false;
				}
			}
			$("#financeReportForm").submit();
		})

		//弹出用户信息
		$('td.caption').hover(function() {
			$(this).children('.operate-1').show();
		}, function() {
			$(this).children('.operate-1').hide();
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