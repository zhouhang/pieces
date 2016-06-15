<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head lang="en">
    <meta charset="UTF-8">
    <title>订单查询</title>
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
            <h1 class="title1">订单查询</h1>
            <form action="/bossorder" method="post" id="queryForm">
                <ul class="form-search ac-search">
                    <li><span>订单编号：</span><input class="text text-4" type="text" name="orderId" value="${bossOrderQuery.orderId!''}"/>&nbsp;
                        <span>挂牌编号：</span><input class="text text-4" type="text" name="listingId" value="${bossOrderQuery.listingId!''}"/>&nbsp;
                         <span>订单状态：</span><div id="select-bg"><span><select name="orderState">
                            <option value="">全部</option>
                            <#if stateMap??>
                            	<#list stateMap?keys as key>
                            		<option value="${key!''}" <#if bossOrderQuery.orderState == key>selected</#if>>${stateMap[key]!''}</option>
                            	</#list>
                            </#if>
                        </select></span></div>&nbsp;
                        <span>摘牌时间：</span><input class="text text-date" id="date_b" type="text" name="orderStartDate" value="${bossOrderQuery.orderStartDate!''}"/> — <input class="text text-date" id="date_e" type="text" name="orderEndDate" value="${bossOrderQuery.orderEndDate!''}"/>&nbsp;
                        <!--add by fanyuna 2015.12.01 增加“付款方式”查询条件-->
                        <span>付款方式：</span><div id="select-bg"><span><select name="payWay">
                            <option value="">请选择</option>
                            <option value="1" <#if bossOrderQuery.payWay == '1'>selected</#if>>保证金方式</option>
                            <option value="2" <#if bossOrderQuery.payWay == '2'>selected</#if>>全款方式</option>	
                        </select></span></div>&nbsp;
                    </li>
					<li><span>标题：</span><input class="text text-4" type="text" name="title" value="${bossOrderQuery.title!''}"/>&nbsp;
					    <span>品种：</span><input class="text text-4" type="text" name="breedname" value="${bossOrderQuery.breedname!''}"/>&nbsp;
						<span>买家：</span><input class="text text-4" type="text" name="buyerName" value="${bossOrderQuery.buyerName!''}"/>&nbsp;
						<span>卖家：</span><input class="text text-4" type="text" name="sellerName" value="${bossOrderQuery.sellerName!''}"/>&nbsp;
						<span>业务人员：</span><input class="text text-4" type="text" name="salesmanName" value="${bossOrderQuery.salesmanName!''}"/>&nbsp;
&nbsp;<a href="javascript:;" onclick="resetForm('queryForm')" class="col_999">清除</a>&nbsp;
                      <input type="submit" class="btn-blue" id="btn-blue" value="查询" /></li>
                </ul>
            </form>
            <div class="use-item1 mt20" style="overflow-x: auto;padding-bottom: 60px;">
                <table class="table-1" width="1891" cellpadding="1" cellspacing="1">
                    <tr>
                        <th width="113" class="bgf5">订单编号</th>
                        <th width="113" class="bgf5">挂牌编号</th>
                        <th width="150" class="bgf5">标题</th>
                        <th width="100" class="bgf5">品种</th>
                        <th width="90" class="bgf5">商品单价</th>
                        <th width="90" class="bgf5">订单总价</th>
                        <th width="90" class="bgf5">订单数量</th>
                        <th width="90" class="bgf5">实际付款</th>
                        <!--add by fanyuna 2015.12.01 增加“付款方式”列-->
                        <th width="90" class="bgf5">付款方式</th>
                        <th width="90" class="bgf5">成交数量</th>
                        <th width="75" class="bgf5">订单状态</th>
                        <th width="90" class="bgf5">买家</th>
                        <th width="110" class="bgf5">买方业务人员</th>
                        <th width="90" class="bgf5">卖家</th>
                        <th width="110" class="bgf5">卖方业务人员</th>
                        <th width="120" class="bgf5">摘牌时间</th>
                        <th width="120" class="bgf5">更新时间</th>
                        <th width="150" class="bgf5">过期/账期时间</th>
                        <th width="120" class="bgf5">保证金/比例</th>
                        <th width="120" class="bgf5">操作</th>
                    </tr>
                    <#if page.results?? && (page.results?size>0)>
                    	<#list page.results as order>
                    		<tr>
		                        <td>
			                        <@shiro.hasPermission name="订单查询-查询订单详情">
			                        	<a class="col_blue" name="order" href="javascript:;" data-id="${order.orderId!''}" data-reclick="n">${order.orderId!''}</a>
			                        </@shiro.hasPermission>
			                        <@shiro.lacksPermission name="订单查询-查询订单详情">
	                    				${order.orderId!''}
	                    			</@shiro.lacksPermission>
		                        </td>
		                        <td>
		                        	<@shiro.hasPermission name="订单查询-挂牌详情">
			                        	<a class="col_blue" name="sell" href="javascript:;" data-id="${order.listingId!''}" data-reclick="n">${order.listingId!''}</a>
			                        </@shiro.hasPermission>
			                        <@shiro.lacksPermission name="订单查询-挂牌详情">
	                    				${order.listingId!''}
	                    			</@shiro.lacksPermission>
		                        </td>
		                        <td>${order.title!''}</td>
		                        <td>${order.breedname!''}</td>
		                        <td><@tools.money num=order.unitPrice format="0.##"/>元</td>
		                        <td><@tools.money num=order.totalPrice format="0.##"/>元</td>
		                        <td><@tools.money num=order.amount format="0.##"/>${order.wlunit!''}</td>
		                        <td><@tools.money num=order.actualPayment format="0.##"/>元</td>
		                         
                        		<td>
                        			<#if order.orderType == '1' || order.orderType == '2'>
                        				保证金方式
                        			</#if>
                        			<#if order.orderType == '3'>
                        				全款方式
                        			</#if>
                        		</td>
                        		
		                        <td><@tools.money num=order.volume format="0.##"/>${order.wlunit!''}</td>
		                        <td>${stateMap[order.orderState]!''}</td>
		                        <td class="caption"><span class="col_blue">${order.buyerName!''}</span> <span class="operate-2"><div class="tips tipa" align="left"><span class="sj"></span>姓名/公司名：${order.buyerRealName!''}<br/>联系方式：${order.buyerMobile!''}</div></span></td>
		                        <td>${order.buyerSalemanName!''}</td>
		                        <td class="caption"><span class="col_blue">${order.salerName!''}</span> <span class="operate-2"><div class="tips tipa" align="left"><span class="sj"></span>姓名/公司名：${order.salerRealName!''}<br/>联系方式：${order.salerMobile!''}</div></span></td>
		                        <td>${order.sellerSalemanName!''}</td>
		                        <td>${order.orderDate?string("yyyy-MM-dd HH:mm:ss")!''}</td>
		                        <td>${order.updateTime?string("yyyy-MM-dd HH:mm:ss")!''}</td>
		                        <td>
		                        	<#if order.orderState == '5'>
			                        	<#if order.orderType == '1' && order.expireTime??>${order.expireTime?string("yyyy-MM-dd HH:mm:ss")!''}/</#if>
			                        	<#if order.orderType == '2' && order.endTime??>/${order.endTime?string("yyyy-MM-dd")!''}</#if>
		                        	</#if>
		                        </td>
		                        <#if (order.totalPrice=='0'||order.totalPrice=='0.0'||order.totalPrice=='0.00' )>
		                        	<td><@tools.money num=(order.deposit)!0 format="0.##"/>元/--</td>
		                        <#else>
		                        	<td><@tools.money num=(order.deposit)!0 format="0.##"/>元/${(((order.deposit!0)/order.totalPrice) * 100)?string("0.0")}%</td></td>
		                        </#if>
                        		
                        		<td>
                        			<#if order.orderState == '0'>
                        				<@shiro.hasPermission name="订单查询-修改保证金">
                        					<a href="javascript:;" name="reMoney" class="blue" id="${order.orderId!''}" data_info="${order.totalPrice!'' }" data_deposit="${order.deposit!'' }">修改保证金</a>
                        				</@shiro.hasPermission>
                        			</#if>
                        			<#if order.orderState == '5'>
                        				<#if order.orderType == '1'>
	                        				<#if (order.examineState == '0') || (order.examineState == '3')>
	                        					<@shiro.hasPermission name="订单查询-订单延期">
	                        						<a href="javascript:;" name="delay" class="blue" data-time="<#if order.expireTime??>${order.expireTime?string("yyyy-MM-dd HH:mm:ss")!''}</#if>" data-orderid="${order.orderId!''}">订单延期</a>
	                        					</@shiro.hasPermission>
	                        				</#if>
	                        				<#if order.examineState == '0'>
	                        					<@shiro.hasPermission name="订单查询-转为账期">
	                        						<a href="javascript:;" name="account" class="blue" data-orderid="${order.orderId!''}">转为账期</a>
	                        					</@shiro.hasPermission>
	                        				</#if>
	                        			</#if>
	                        			<#if order.orderType == '2'>
	                        				<@shiro.hasPermission name="订单查询-查询账期详情">
	                        					<a href="javascript:;" name="accountDetail" class="blue" data-id="${order.orderId!''}" data-reclick="n">账期详情</a>
	                        				</@shiro.hasPermission>
	                        			</#if>
                        			</#if>
                        		</td>
		                        
		                        
                    		</tr>
                    	</#list>
                    <#else>
                    	<tr>
                    		<td colspan="18">暂无数据!</td>
                    	</tr>
                    </#if>
                </table>
                
                <@tools.pages page=page form="queryForm"/>
            </div>

            
        </div>
    </div>
   <!-- <input >-->
<!-- pageCenter over -->
</div>
<input type="hidden" id="depRate" name="depRate" value="${depRate!''}"/>
<input type="hidden" id="depDefault_1" name="depDefault" value="${depDefault!''}"/>
<!--订单详情 弹层-->
<div class="order-popup Order">
</div>
<!--订单详情 弹层 end-->

<!--挂牌详情 弹层-->
<div class="order-popup detail Sell">
    <div class="close"></div>
    <h1 class="title1">挂牌详情</h1>
    <div class="sellDetail-box">
    </div>
</div>
<!--挂牌详情 弹层 end-->

<!--订单延期 弹层 -->
<div class="order-popup orderDelay" datatype="orderDelay">
    <div class="close"></div>
    <!-- pageCenter start -->
    <h1 class="title1">设置到期时间</h1>
    <ul>
        <li><label>现到期时间：</label><span id="old"></span><input type="hidden" id="oldTimeHid"><input type="hidden" id="order_id_1"></li>
        <li><label>设置新到期时间：</label><input type="text" id="time"  class="text text-date"></li>
        <li><label></label><span id="error" class="col_999">新到期时间需大于现到期时间</span></li>
        <li><label></label><input type="button" class="btn-blue" id="tCertain" value="确定" /> <input type="button" class="btn-hui ml10" name="cancel" value="取消" /></li>
    </ul>
</div>
<!--订单延期 弹层 end-->

<!--订单延期确认 弹层 -->
<div class="order-popup orderDelay" datatype="oDelayCer">
    <div class="close"></div>
    <!-- pageCenter start -->
    <h1 class="title1">设置到期时间</h1>
    <ul>
        <li class="ml10 mt10">新到期时间：<span id="newTime"></span><input type="hidden" id="newTimeHid"><input type="hidden" id="order_id_2"></li>
        <li class="ml10"><strong>确定延长此笔订单吗？</strong></li>
        <li class="mt20"><label></label><input type="button" class="btn-blue" id="qd-1" value="确定"/> <input type="button" class="btn-hui ml10" name="cancel" value="取消" /></li>
    </ul>
</div>
<!--订单延期确认 弹层 end-->

<!--修改保证金 弹层 -->
<div class="order-popup orderDelay reMon" datatype="reMon">
    <div class="close"></div>
    <!-- pageCenter start -->
    <h1 class="title1">保证金比例设置</h1>
    <ul>
        <li><label>总额：<strong class="red" id="total">10000</strong> 元</label><span>默认保证金比例：<strong id="per" class="red">20%</strong> </span><input type="hidden" id="order_id_3"><input type="hidden" id="depDefault_2"></li>
        <li><label>新修改保证金：</label><input type="text" id="bzj" value="2000"  class="text"> 元&nbsp;&nbsp;&nbsp;&nbsp;<span><strong id="bili" class="red">20%</strong>比例</span></li>
        <li><label></label><span id="ts" style="color:red;">保证金比例低于20%的情况，保证金金额至少需要100元！</span></li>
        <li><label></label><input type="button" class="btn-blue" id="mCertain" value="确定" /> <input type="button" class="btn-hui ml10" name="cancel" value="取消" /></li>
        <li><label></label><span class="cap">修改的保证金金额,买家付款按照此保证金金额进行支付,尾款是根据总额减去保证金的金额数,体现在前台支付页面
</span></li>
    </ul>
</div>
<!--修改保证金 弹层 end-->

<!--修改保证金确认 弹层 -->
<div class="order-popup orderDelay reMon" datatype="reMonCer">
    <div class="close"></div>
    <!-- pageCenter start -->
    <h1 class="title1">保证金比例设置确认</h1>
    <ul>
        <li class="ml10 mt20">总额：<strong class="red" id="orderTotal">10000</strong> 元</li>
        <li class="ml10">新修改保证金：<strong class="red" id="newDep">1000</strong> 元&nbsp;&nbsp;&nbsp;&nbsp;<span><strong class="red" id="newBili">10%</strong>比例</span><input type="hidden" id="order_id_4"></li>
        <li class="ml10"><strong>确定按这个比例分配订单的首尾支付额度吗？</strong></li>
        <li class="mt25"><label></label><input type="button" class="btn-blue" id="qd-2" value="确定" /> <input type="button" class="btn-hui ml10" name="cancel" value="取消" /></li>
    </ul>
</div>
<!--修改保证金确认 弹层 end-->

<!--转为账期 弹层 -->
<div class="order-popup orderDelay account" datatype="account">
    <div class="close"></div>
    <h1 class="title1">转为账期</h1>
    <ul>
    	<input type="hidden" id="orderidZQ">
        <li><label>约定的账期时间：</label><input type="text" class="text text-date" id="timeStart">&nbsp;至&nbsp;<input type="text" class="text text-date" id="timeEnd">&nbsp;<b class="col_red2 ml10" id="iDays"></b> 天</li>
        <li><label>备注：</label><textarea class="text" id="remarkZQ"></textarea></li>
        <li><label></label><input type="button" value="确定" id="Confirm" class="btn-blue"><input type="button" value="取消" name="cancel" class="btn-hui ml10"></li>
        <li><label></label><span class="caption">转为账期订单后，wms会立即分割仓单以供提前出库；<br/>
            但若有对提前分割的仓单进行退货情况，跟单员需告知卖方业务人员确认，<br/>
            需要将此仓单在wms出库操作后，再进行入库操作挂到原货主上，<br/>
            请跟单员及时提醒大区业务人员或者买家，买家的付款时间！<br/>
        </span></li>
    </ul>
</div>
<!--转为账期 弹层 over  -->

<!--转为账期确认 弹层 -->
<div class="order-popup orderDelay account" datatype="accountComfirm">
    <div class="close"></div>
    <h1 class="title1">转为账期</h1>
    <ul>
    	<input type="hidden" id="qeOrderid">
        <li><label>约定的账期时间：</label> <span><span id="qeTimeStart"></span>&nbsp;至&nbsp;<span id="qeTimeEnd"></span> <b class="col_red2 ml10" id="qeDays"></b> 天</span></li>
        <li><label>备注：</label><textarea class="text" id="qeRemark" readonly="readonly"></textarea></li>
        <li><label></label><span class="caption">转为账期的前提一定是在买方业务人员与卖方业务人员的撮合下，买卖双方已经同意并确定延期付款的情况。
        </span></li>
        <li style="margin-top: -10px;"><label></label><strong>确定将此笔订单转为账期订单吗？</strong></li>
        <li style="margin-top: -10px;"><label></label><input type="button" value="确定" id="qeBtn" class="btn-blue"><input type="button" value="取消" name="cancel" class="btn-hui ml10"></li>
    </ul>
</div>
<!--转为账期确认 弹层 over  -->

<!--账期详情 弹层 -->
<div class="order-popup orderDelay account" datatype="accountDetail">
    <div class="close"></div>
    <h1 class="title1">账期详情</h1>
    <ul>
        <li><label>约定的账期时间：</label> <span><span id="deStartTime"></span> 至 <span id="deEndTime"></span> <b class="col_red2 ml10" id="deDays"></b> 天</span></li>
        <li><label>备注：</label><textarea class="text" id="deRemark" readonly="readonly"></textarea></li>
        <li><label></label><span class="caption">转为账期的前提一定是在买方业务人员与卖方业务人员的撮合下，买卖双方已经同意并确定延期付款的情况。
        </span></li>
        <li><label></label><input type="button" value="关闭" name="cancel" class="btn-blue"></li>
    </ul>
</div>
<!--账期详情 弹层 over  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-boss/nav.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript">
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
</script>
<script>
    $(function(){
        //日期控件
        $('#date_b').click(function(){
            WdatePicker({
                maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
                readOnly:true
            });
        });
        $('#date_e').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
                readOnly:true
            });
        });
        $('#time').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$D(\'oldTimeHid\',{s:1});}',
                readOnly:true,
                dateFmt:'yyyy-MM-dd HH:mm:ss'
            });
        });
        $('#timeStart').click(function(){
            WdatePicker({
                maxDate:'#F{$dp.$D(\'timeEnd\',{d:-1});}',
                readOnly:true,
                dateFmt:'yyyy-MM-dd'
            });
        });
        $('#timeEnd').click(function(){
            WdatePicker({
                minDate:'#F{$dp.$DV(getEndDate(),{d:1});}',
                readOnly:true,
                dateFmt:'yyyy-MM-dd'
            });
        });
        
        //背景遮罩层
        function bghui(){
            var html = '<div class="bghui"></div>';
            var height = $(document).height();
            $('body').append(html);
            $('.bghui').css('height',height);
        }
        
        //查看和关闭订单详情弹层
        $('td a[name=order]').on('click',function(){
            getOrderInfoById(this);
        });
        $('.Order').on('click','.close',function(){
            $('.Order').hide();
            $('.Order').empty();
            $('.bghui').remove();
        });
        
        //查看和关闭挂牌详情弹层
        $('td a[name=sell]').on('click',function(){
            getListingInfo(this);
        });
        $('.Sell').on('click','.close',function(){
            $('.Sell').hide();
            $('.Sell .sellDetail-box').empty();
            $('.bghui').remove();
        });
        
        //查看和关闭订单延期弹层
        $('td a[name=delay]').on('click',function(){
        	$('#old').text("");
        	$('#oldTimeHid').val("");
        	$('#time').val("");
        	$('#error').text("");
        	$("#order_id_1").val("");
        	$('#old').text($(this).data('time'));
        	$('#oldTimeHid').val($(this).data('time'));
        	$("#order_id_1").val($(this).data('orderid'));
        	bghui();
        	$('[datatype=orderDelay]').show();
        });
        $('[datatype=orderDelay]').on('click','.close',function(){
            $('[datatype=orderDelay]').hide();
            $('.bghui').remove();
        });
        
        //取消按钮
        $('input[name=cancel]').on('click',function(){
            $(this).parents('.order-popup').hide();
            $('.bghui').remove();
        });
        
        //弹出和关闭订单延期确认框
        $('#tCertain').on('click',function(){
            var oldTime = $('#old').text(),
                newTime = $('#time').val(),
                orderId = $("#order_id_1").val();
            if(Date.parse(oldTime.replace(/-/g, "/")) >= Date.parse(newTime.replace(/-/g, "/"))){
                $('#error').text('新到期时间需大于现到期时间');
            }
            else{
                $('#error').text("");
                $(this).parents('.orderDelay').hide();
                $('[datatype=oDelayCer] #newTime').text("");
                $('[datatype=oDelayCer] #newTimeHid').val("");
                $('[datatype=oDelayCer] #order_id_2').val("");
                $('[datatype=oDelayCer] #newTime').text(newTime);
                $('[datatype=oDelayCer] #newTimeHid').val(newTime);
                $('[datatype=oDelayCer] #order_id_2').val(orderId);
                $('[datatype=oDelayCer]').show();
            }
        });
        $('[datatype=oDelayCer]').on('click','.close',function(){
            $('[datatype=oDelayCer]').hide();
            $('.bghui').remove();
        });
        //订单延期确认执行按钮
        $('[datatype=oDelayCer]').on('click','#qd-1',function(){
        	updateOrderExpireTime();
        });
        
        
        //查看和关闭修改保证金弹层
        $('td a[name=reMoney]').on('click',function(){
        	$("#total").text("");
        	$("#per").text("");
        	$("#bzj").val("");
        	$("#bili").text("");
        	$("#order_id_3").val("");
        	$("#depDefault_2").val("");
        	$("#ts").text("");
        	var depRate = $("#depRate").val();
        	var depDefault = $("#depDefault_1").val();
        	var totalPrice = $(this).attr("data_info");
        	var deposit = $(this).attr("data_deposit");
        	$("#order_id_3").val($(this).attr("id"))
        	$("#depDefault_2").val(depDefault);
        	$("#total").text(totalPrice);
        	$("#per").text((depRate*100).toFixed(1) + "%");
        	$("#bili").text(((deposit/totalPrice)*100).toFixed(1) + "%");
        	$("#bzj").val(deposit);
        	bghui();
        	$('[datatype=reMon]').show();
        });
        
        //保证金框失去焦点时，自动计算保证金比例
        $("#bzj").on("blur mouseout",function(){
        	var _deposit = $("#bzj").val();
        	var totalPrice = $("#total").text();
        	$("#bili").text(((_deposit/totalPrice)*100).toFixed(1) + "%");
        });
        //聚焦时，清空错误提示
        $("#bzj").on("focus",function(){
        	$('#ts').text("");
        });
        
        $('[datatype=reMon]').on('click','.close',function(){
            $('[datatype=reMon]').hide();
            $('.bghui').remove();
        });

        //弹出修改保证金确认框
        $('#ts').hide();
        $('#mCertain').on('click',function(){
            $('#ts').hide();
            var _deposit = $("#bzj").val();//输入的保证金
        	var totalPrice = $("#total").text();//订单总额
        	var depRate = $("#depRate").val();//默认比例
        	var defaultDep = $("#depDefault_2").val();//保证金默认值
        	var _orderId = $("#order_id_3").val();
        	
        	var reg = /^\d+(\.?\d{0,2})?$/;
        	if(!reg.test(_deposit)){
        		$('#ts').text("输入的保证金金额不合法").show();
        		return;
        	}
        	//判断保证金是否在范围内，第一种情况，20%保证金大于等于100
        	if(parseInt((totalPrice * depRate * 100).toFixed(0)) >= parseInt(100 * 100)){
        		if(parseInt((_deposit * 100).toFixed(0)) < parseInt(100 * 100)){
        			//$('#ts').text("修改保证金时，保证金金额不能小于100").show();
        			$('#ts').text("保证金比例低于"+ (depRate * 100).toFixed(0) +"%的情况，保证金金额至少需要" + defaultDep + "元!").show();
        			return;
        		}
        	}else{//第二种情况,20%bao保证金小于100
        		if(parseInt((_deposit * 100).toFixed(0)) < parseInt((totalPrice * depRate * 100).toFixed(0))){
        			//$('#ts').text("修改保证金时，保证金金额不能小于订单金额的" + (depRate * 100).toFixed(0) + "%").show();
        			$('#ts').text("保证金比例低于"+ (depRate * 100).toFixed(0) +"%的情况，保证金金额至少需要" + defaultDep + "元!").show();
        			return;
        		}
        	}
        	//保证金金额不能大于订单总金额
        	if(parseInt((_deposit * 100).toFixed(0)) >= parseInt((totalPrice*100).toFixed(0))){
    			$('#ts').text("保证金金额不能大于等于订单总金额").show();
    			return;
    		}
        	$(this).parents('.reMon').hide();
        	$('[datatype=reMonCer] #orderTotal').text("");
        	$('[datatype=reMonCer] #newDep').text("");
        	$('[datatype=reMonCer] #newBili').text("");
        	$('[datatype=reMonCer] #order_id_4').val("");
        	$('[datatype=reMonCer] #orderTotal').text(totalPrice);
        	$('[datatype=reMonCer] #newDep').text(_deposit);
        	$('[datatype=reMonCer] #newBili').text(((_deposit/totalPrice) * 100).toFixed(1) + "%");
        	$('[datatype=reMonCer] #order_id_4').val(_orderId);
            $('[datatype=reMonCer]').show();
            
        });
        
        //订单保证金修改确认执行按钮
        $('[datatype=reMonCer]').on('click','#qd-2',function(){
        	updateOrderDeposit();
        });
        
        //弹出和关闭转为账期 弹层
        $('td a[name=account]').on('click',function(){
        	$('#timeStart').val("");
        	$('#timeEnd').val("");
        	$('#iDays').text("");
        	$('#remarkZQ').val("");
        	$('#orderidZQ').val("");
        	$('#orderidZQ').val($(this).data('orderid'));
        	bghui();
        	$('[datatype=account]').show();
        });
        $('[datatype=account]').on('click','.close',function(){
            $('[datatype=account]').hide();
            $('.bghui').remove();
        });
        //计算账期天数
        var temp;
        $('#timeEnd').focus(function(){
        	if(!$('#timeStart').val() || !$(this).val()){
        		return false;
        	}
            var start = new Date($('#timeStart').val().replace(/-/g,   "/"));
            var  end = new Date($(this).val().replace(/-/g,   "/"));
            var  iDays = parseInt(Math.abs(end - start) / 1000 / 60 / 60 / 24);
            if((!isNaN(iDays)) && temp!=iDays){
                temp=iDays;
                $('#iDays').text(iDays);
            }
        });
        $('#timeStart').focus(function(){
        	if(!$('#timeEnd').val() || !$(this).val()){
        		return false;
        	}
            var start = new Date($(this).val().replace(/-/g,   "/"));
            var  end = new Date($('#timeEnd').val().replace(/-/g,   "/"));
            var  iDays = parseInt(Math.abs(end - start) / 1000 / 60 / 60 / 24);
            if((!isNaN(iDays)) && temp!=iDays){
                temp=iDays;
                $('#iDays').text(iDays);
            }
        });
        
        //弹出和关闭转为账期确认 弹层
        $('#Confirm').on('click',function(){
        	if(!$('#timeStart').val() || !$('#timeEnd').val()){
        		return false;
        	}
        	$('#qeTimeStart').text($('#timeStart').val());
        	$('#qeTimeEnd').text($('#timeEnd').val());
        	$('#qeDays').text($('#iDays').text());
        	$('#qeRemark').val($('#remarkZQ').val());
        	$('#qeOrderid').val($('#orderidZQ').val());
            $('[datatype=accountComfirm]').show();
            $('[datatype=account]').hide();
        });
        $('[datatype=accountComfirm]').on('click','.close',function(){
            $('[datatype=accountComfirm]').hide();
            $('.bghui').remove();
        });
        //转为账期确认执行按钮
        $('[datatype=accountComfirm]').on('click','#qeBtn',function(){
        	changeToTermOrder();
        });
        
        //弹出和关闭账期详情 弹层
        $('td a[name=accountDetail]').on('click',function(){
        	var _this = $(this),
    		orderId = _this.data("id"),
    		reclick = _this.data("reclick");
	    	if(reclick == "n") {
	    		_this.data("reclick", "y");
	    	} else {
	    		return false;
	    	}
        
        	$('#deStartTime').text("");
        	$('#deEndTime').text("");
        	$('#deDays').text("");
        	$('#deRemark').val("");
        	
        	$.ajax({
	    		type:"POST",
	    		url:"/bossorder/selectTermOrder",
	    		data:{orderId:orderId},
	    		dataType:"json",
	    		success:function(data){
	    			if(data.state=="success"){
	    				var termOrder = parseJson(data.result);
	    				$('#deStartTime').text(termOrder.startTime);
			        	$('#deEndTime').text(termOrder.endTime);
			        	$('#deDays').text(data.days);
			        	$('#deRemark').val(termOrder.note);
	    				bghui();
        				$('[datatype=accountDetail]').show();
	    			} else {
	    				customAlert(data.result);
	    			}
	    			_this.data("reclick", "n");
	    		},
	    		error:function(textStatus){
	    			customAlert("账期详情查看失败！");
	    			_this.data("reclick", "n");
	    		}
	    	});
        });
        $('[datatype=accountDetail]').on('click','.close',function(){
            $('[datatype=accountDetail]').hide();
            $('.bghui').remove();
        });
        

        //弹出用户信息
        $('td.caption').hover(
            function(){
                $(this).children('.operate-2').show();
            },
            function(){
                $(this).children('.operate-2').hide();
            }
        )
    })
    
    //获取订单详情
    function getOrderInfoById(obj){
    	var _this = $(obj),
    		orderId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
    
    	var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/bossorder/info",
    		data:{orderId:orderId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				var orderInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="close"></div>';
    				html += '<h2 class="title">订单详情</h2>';
    				var stepCount = "";
    				if("0" == orderInfo.orderState){
    					//已摘牌
    					stepCount = "1";
    				}
    				if("3" == orderInfo.orderState){
    					//已付保证金
    					stepCount = "2";
    				}
    				if("5" == orderInfo.orderState){
    					//已备货
    					stepCount = "2";
    				}
    				if("4" == orderInfo.orderState){
    					//已付款
    					stepCount = "3";
    				}
    				if("1" == orderInfo.orderState){
    					//交易完成
    					stepCount = "4";
    				}
    				if("2" != orderInfo.orderState && "-1" != orderInfo.orderState){
    					//交易取消,交易关闭,不显示该DIV
	    				html += '<div class="proses"><span class="step'+ stepCount +'"></span></div>';
	    				$('.Order').removeClass('tempHei');
    				} else {
    					//弹层显示不同高度
    					$('.Order').addClass('tempHei');
    				}
    				html += '<p class="message"><strong>订单信息：</strong>&nbsp;订单编号：'+ orderInfo.orderId +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;摘牌日期：'+ orderInfo.orderDate +'</p>';
    				html += '<table class="table" cellpadding="0" cellspacing="0">';
    				html += '<tr>';
    				html += '<th width="40%">药材</th>';
    				html += '<th width="15%">单价</th>';
    				html += '<th width="15%">订单数量</th>';
    				html += '<th width="15%">成交数量</th>';
    				html += '<th width="15%">商品总价</th>';
    				html += '</tr>';
    				html += '<tr>';
    				html += '<td class="intro">';
    				html += '<span><img src="'+ imgServer + '/' + orderInfo.goodsPic +'" /></span>';
    				html += '<h3>'+ orderInfo.goodsTitle +'</h3>';
    				html += '</td>';
    				html += '<td align="center">'+ orderInfo.unitPrice +'元/'+ orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+ orderInfo.amount + orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+ orderInfo.volume + orderInfo.wlunit +'</td>';
    				html += '<td align="center">'+orderInfo.totalPrice+'元</td>';
    				html += '</tr>';
    				html += '</table>';
    				html += '<div class="pay-cash" align="right">实际付款：<strong> '+ orderInfo.actualPayment +'</strong> 元</div>';
    				$('.Order').append(html);
    				bghui();
    				$('.Order').show();
    			} else {
    				customAlert(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			customAlert("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
    }
    
    //获取挂牌详情
	function getListingInfo(obj){
		var _this = $(obj),
    		listingId = _this.data("id"),
    		reclick = _this.data("reclick");
    	if(reclick == "n") {
    		_this.data("reclick", "y");
    	} else {
    		return false;
    	}
		var imgServer = "${RESOURCE_IMG_UPLOAD}";
    	$.ajax({
    		type:"POST",
    		url:"/bossorder/listingInfo",
    		data:{listingId:listingId},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				bghui();
    				$('.Sell').show();
    				var listingInfo = parseJson(data.result);
    				var html = '';
    				html += '<div class="box-1 fl">';
    				html += '<ul>';
    				html += '<li><label>仓单编号：</label><span>' + listingInfo.wlid + '</span></li>';
    				html += '<li><label>标    题：</label><span>' + listingInfo.title + '</span></li>';
    				html += '<li><label>品    种：</label><span>' + listingInfo.breedname+ '</span></li>';
    				html += '<li><label>仓单总量/可挂数量：</label><span>' + listingInfo.wltotal + listingInfo.dictvalue + '/' + listingInfo.wlsurplus + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌重量：</label><span>' + listingInfo.listingamount + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>挂牌价格：</label><span>' + listingInfo.lowunitprice +'元</span></li>';
    				html += '<li><label>最低起订：</label><span>' + listingInfo.minsales + listingInfo.dictvalue + '</span></li>';
    				html += '<li><label>包装规格：</label><span>' + listingInfo.packingway +'</span></li>';
    				html += '<li><label>挂牌期限：</label><span>' + listingInfo.listingtimelimit +'天</span></li>';
    				if(0 == listingInfo.hasbill){
    					html += '<li><label>能否提供发票：</label><span>不提供</span></li>';
    				} else {
    					html += '<li><label>能否提供发票：</label><span>提供</span></li>';
    					html += '<li><label>提供发票单价：</label><span>' + listingInfo.billprice + '元/' + listingInfo.dictvalue + '</span></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-2 fl">';
    				html += '<ul>';
    				if(listingInfo.piclist && listingInfo.piclist.length > 0){
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[0].path + '"/> </span><p>散货照片</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[1].path + '"/> </span><p>细节照1</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[2].path + '"/> </span><p>细节照2</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[3].path + '"/> </span><p>细节照3</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[4].path + '"/> </span><p>包装照</p></li>';
	    				html += '<li><span><img src="' + imgServer + '/' + listingInfo.piclist[5].path + '"/> </span><p>堆垛照</p></li>';
    				}
    				html += '</ul>';
    				html += '</div>';
    				html += '<div class="box-3 clearfix">';
    				html += '<h3 class="title">药材详情</h3>';
    				html += '<p>' + listingInfo.content + '</p>';
    				html += '</div>';
    				$('.Sell .sellDetail-box').append(html);
    			} else {
    				customAlert(data.result);
    			}
    			_this.data("reclick", "n");
    		},
    		error:function(textStatus){
    			customAlert("操作失败！");
    			_this.data("reclick", "n");
    		}
    	});
	}
	
	//延长订单过期时间
	function updateOrderExpireTime(){
		$('[datatype=oDelayCer]').hide();
        $('.bghui').remove();
    	var orderId = $("#order_id_2").val(),
    		expireTime = $("#newTimeHid").val();
    	$.ajax({
    		type:"POST",
    		url:"/bossorder/updateExpireTime",
    		data:{orderId:orderId,expireTime:expireTime},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				customAlert(data.result,function(){
    					$("#queryForm").submit();
    				});
    			} else {
    				customAlert(data.result);
    			}
    		},
    		error:function(textStatus){
    			customAlert("订单延期操作失败！");
    		}
    	});
	}
	
	//转为账期
	function changeToTermOrder(){
		$('[datatype=accountComfirm]').hide();
        $('.bghui').remove();
    	var orderId = $("#qeOrderid").val(),
    		startTime = $("#qeTimeStart").text(),
    		endTime = $("#qeTimeEnd").text(),
    		remark = $("#qeRemark").val();
    	$.ajax({
    		type:"POST",
    		url:"/bossorder/changeTermOrder",
    		data:{orderId:orderId,startTime:startTime,endTime:endTime,remark:remark},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				customAlert(data.result,function(){
    					$("#queryForm").submit();
    				});
    			} else {
    				customAlert(data.result);
    			}
    		},
    		error:function(textStatus){
    			customAlert("账期设置失败！");
    		}
    	});
	}
    
	//修改订单保证金
	function updateOrderDeposit(){
		$('[datatype=reMonCer]').hide();
        $('.bghui').remove();
		var _deposit = $("#newDep").text(),
    	    _orderId = $("#order_id_4").val();
    	$.ajax({
    		type:"POST",
    		url:"/bossorder/alterOrderDeposit",
    		data:{orderId:_orderId,deposit:_deposit},
    		dataType:"json",
    		success:function(data){
    			if(data.state=="success"){
    				customAlert(data.result,function(){
    					$("#queryForm").submit();
    				});
    			} else {
    				customAlert(data.result);
    			}
    		},
    		error:function(textStatus){
    			customAlert("订单保证金修改操作失败！");
    		}
    	});
	}
	
    //自定义的alert框
    function customAlert(str,fn){
    	bghui();
    	Alert({
            str: str,
            buttons:[{
                name:'确定',
                classname:'btn-blue',
                ev:{click:function(){
                	 disappear();
                     $(".bghui").remove();
                     if(fn){
                     	fn();
                     }
                 }
               }
            }]
	    });
    }
    
    //清除
    function resetForm(id){
    	$("#" + id + " :text").val("");
    	$("#" + id + " select").val("");
    }
    
	function parseJson(text){
		try{
		    return JSON.parse(text);//ie 89 ff ch
		}catch(e){
		    return eval('('+text+')'); //ie7
		}
	}
	
	function getEndDate(){
		var startTime = $('#timeStart').val();
		var date = new Date();
		if(startTime && new Date(startTime) > date){
			return startTime;
		} else {
			return date.Format('yyyy-MM-dd');
		}
	}
</script>
</body>
</html>