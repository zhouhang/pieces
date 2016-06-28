<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>买方订单</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	 
    <#import 'macro.ftl' as tools>
	<#import 'page.ftl' as fenye>
</head>
<body>
<!-- 头部  -->
<#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
<!-- 会员侧左 -->
<#include 'common/left.ftl'>
<!-- 会员左侧 over -->
	<div class="hy-right fr">
		<div class="order-box">
            <h2 class="o-title">买方订单</h2>
            <ul class="tabs clearfix">
                <li id="default" class="cur"><a href="/order/listinfo">所有订单</a></li>
                <li id="0"><a href="/order/listinfo?orderstate=0">待付保证金</a></li>
                <li id="3"><a href="/order/listinfo?orderstate=3">待平台备货</a></li>
                <li id="5"><a href="/order/listinfo?orderstate=5">待付货款</a></li>
                <li id="1"><a href="/order/listinfo?orderstate=1">已完成交易</a></li>
                <li id="2"><a href="/order/listinfo?orderstate=2">已取消订单</a></li>
                <li id="-1"><a href="/order/listinfo?orderstate=-1">已关闭</a></li>
            </ul>

            <form action="/order/listinfo" method="post" id="conditionForm" onsubmit="return check();">
            <div class="o-search mt20">
				<span>摘牌时间：<input type="text" class="txt dat" name="orderStartDate" id="date_b" value="${(busiOrder.orderStartDate)!''}"  /> — <input type="text" class="txt dat" id="date_e" name="orderEndDate" value="${(busiOrder.orderEndDate)!''}" /></span>
				<span class="pl15">订单金额：<input type="text" id="orderStartPrice" name="orderStartPrice" class="txt" value="${(busiOrder.orderStartPrice)!''}" /> 元 — <input type="text" id="orderEndPrice" class="txt" name="orderEndPrice" value="${(busiOrder.orderEndPrice)!''}" /> 元</span>
				<span class="pl15">关键字：<input class="txt sech" type="text" id="title" name="title" value="${(busiOrder.title)!''}" onfocus="if(this.value=='输入商品标题进行搜索') this.value='';" onblur="if(this.value=='') this.value='输入商品标题进行搜索';"/>
				<input type="button" id="clear" class="btn-grey ml10" value="清空" />
				<input type="submit" class="btn-red ml10" value="查询" /></span>
			</div>
			<table cellspacing="0" cellpadding="0" width="100%" class="mt20" style="border: 1px solid #e0dddd; margin-bottom: -1px;">
                <tr bgcolor="#e7e7e7" align="center">
                    <th width="30%" height="35"><strong>药材</strong></th>
                    <th width="10%"><strong>单价</strong></th>
                    <th width="8%"><strong>订单数量</strong></th>
                    <th width="8%"><strong>成交数量</strong></th>
                    <th width="10%"><strong>实际付款</strong></th>
                    <th width="10%"><strong>订单状态</strong></th>
                    <th width="24%"><strong>操作</strong></th>
                </tr>
            </table>
            </form>
            <div id="tabsCont"  class="pickcard-box panone">
                <ul class="order-list" style="display: block;">
            <#if page.results?size gt 0>
	          <#list page.results as order>
                <li>
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <th colspan="7" align="left" class="fontstyle"><span class="pr50 relative dis-in-bk"><strong>订单信息：</strong>订单编号：${(order.orderid)!''}<!-- 账期订单 --><#if order.orderType==2><i class="o-icon"></i>
		                    <span class="o-sub dis-in-bk">
		                        <span class="sj leftposi"></span>		                      
		                      	     延期付款提示：您与卖方约定延期${order.dataDiff }天付款，请于${order.endTime?string('yyyy年MM月dd日')}前来平台上付款！
		                    </span>
		                      </#if>
                            </span>                            
                            <span>摘牌日期：<#if order.createtime??>${order.createtime?string('yyyy-MM-dd')}</#if></span></th>
                        </tr>
                        <tr>
                            <td class="order-intro" width="30%">
                                <span><#if order.path==''>
                                		<a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>
			                        		<img src="${RESOURCE_IMG}/images/jzt-user-center/purchase.jpg">
			                        	</a>
			                        <#else>
			                        	<#assign img = order.path?substring((order.path?last_index_of("/")+1),(order.path?last_index_of(".")))+"_120.jpg"> 
	                   					<#assign tempurl = order.path?substring(0,order.path?last_index_of("/")+1)> 
	                   				    <a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>
	                   				    	<img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}" alt="" width="80" height="80"/>
			                        	</a>
			                        </#if></span>
                                <h3 class="od"><a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>${(order.title)!''}</a></h3>
                            </td>
                            <td align="center" width="10%"><@tools.money num=order.unitprice format="0.##"/>元/${(order.wlunit)!''}</td>
                            <td align="center" width="8%"><@tools.money num=order.amount format="0.##"/>${(order.wlunit)!''}</td>
                            <td align="center" width="8%"><@tools.money num=order.volume format="0.##"/>${(order.wlunit)!''}</td>
                            <td align="center" width="10%"><@tools.money num=order.totalprice format="0.##"/>元</td>
                      		
                      		<!-- 已下单 -->
                      		<#if order.orderstate=="-1">
                      		<td align="center" width="10%">
                            	已关闭
                            </td>
                      		<td align="center" width="24%">
                                <!-- <a href="javascript:void(0);" name="btnDelete" value="${order.orderid!''}" class="col_blue">删除</a><br/> -->
                                <div> <a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
                      		<#elseif order.orderstate=="0">
                      		<td align="center" width="10%">
                            	未支付保证金
                            </td>
                      		<td align="center" width="24%" style="min-height: 200px;">
                                <div><a href="/order/payOrder?orderId=${order.orderid!""}&amtType=0&userId=${order.buyerId!""}" class="btn-red">支付保证金</a> <a href="/order/payOrder?orderId=${order.orderid!""}&amtType=2&userId=${order.buyerId!""}" class="btn-red">支付全款</a></div>
                               <div class="pt20"><a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
                            <!-- 已完成 -->
							<#elseif order.orderstate=="1">
							<td align="center" width="10%">
                            	交易完成
	                        </td>
	                      	<td align="center" width="24%">
	                      	<div>
	                      		<#if order.appealState==1>
                                <a href="/order/getBuyerOrderAppeal?orderId=${order.orderid!""}" class="col_blue">查看申诉进度</a><br/>
                                </#if>
	                        	<a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
	                        </td>
							<!-- 已取消 -->
							<#elseif order.orderstate=="2">
							<td align="center" width="10%">
                            	已取消
                            </td>
                      		<td align="center" width="24%" >
                      		<div>
                      			<#if order.appealState==1>
                                <a href="/order/getBuyerOrderAppeal?orderId=${order.orderid!""}" class="col_blue">查看申诉进度</a><br/>
                                </#if>
                                <a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
							<!-- 已付保证金 -->
							<#elseif order.orderstate=="3">
							<td align="center" width="10%">
								待平台备货
                            </td>
                      		<td align="center" width="24%">
                               <div> <a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
							<!-- 已付款 -->
							<#elseif order.orderstate=="4">
							<td align="center" width="10%">
                            	已付货款
                            </td>
                      		<td align="center" width="24%" >
                      			<#if order.appealState==1>
                                <a href="/order/getBuyerOrderAppeal?orderId=${order.orderid!""}" class="col_blue">查看申诉进度</a><br/>
                                </#if>
                                <a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
                      		<!-- 已备货 -->
							<#elseif order.orderstate=="5">
							<td align="center" width="10%">
                            	待付货款
                            </td>
                      		<td align="center" width="24%" style="min-height: 200px;">
                      			<#if order.appealState==0||order.examineState==3>
                                <div class="mb10"><a href="/order/payOrder?orderId=${order.orderid!""}&amtType=1&userId=${order.buyerId!""}" class="btn-red">立即付款</a></div>
                                </#if>
                                <#if order.appealState==0>
                                <div><a href="/order/applyReimburse?orderId=${order.orderid!""}">申请取消</a><br/>
                                <#elseif order.appealState==1>
                                <a href="/order/getBuyerOrderAppeal?orderId=${order.orderid!""}" class="col_blue">查看申诉进度</a><br/>
                                </#if>
                                <a href="/order/buyOrderDetail?orderId=${order.orderid!""}" class="col_blue">查看订单详情</a></div>
                            </td>
                      		</#if>
                        </tr>
                    </table>
                </li>
	          </#list>
			</#if>
                </ul>
            </div>
            <@fenye.pages page=page form="conditionForm"/>
        </div>
    </div>
</div>

<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<script>
$(function(){
	//初始化tab
	$("#${orderstate}").addClass('cur').siblings().removeClass('cur');
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
    //搜索框聚焦值置为空
    $('.sech').focus(function(){
        if($(this).val() == '输入订单编号或商品标题进行搜索'){
            $(this).val('');
        }else{
            $(this).val($(this).val());
        }
    }).blur(function(){
        if($(this).val() == ''){
            $(this).val('输入订单编号或商品标题进行搜索');
        }else{
             $(this).val($(this).val());
        }
    });
    //筛选
    $('.tabs li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        var id = $(this).attr('id');
        if(id!='default'){
			window.location.href = '/order/listinfo?orderstate='+id;
		}else{
			window.location.href = '/order/listinfo';
		}
        $('#tabsCont').children('ul').eq($(this).index()).show().siblings().hide();
    })
    
    //删除
    $("a[name='btnDelete']").on('click',function(){
    	var orderid = $(this).attr('value');
    	bghui();
		Alert({
            str: "您确认要删除该关闭订单的信息吗?",
            title: "删除订单",
            buttons:[{
                name:'确定',
                classname:'btn-style',
                ev:{click:function(data){
                	$.ajax({
	                   	 url: "/order/deleteOrder",
	       				 type: 'post', 
	       				 data: {'orderid':orderid},
	       				 dataType:"json",
	       				 success:function(data){
	       					var msg = '';
	       					if(data&&data.ok){
	       						msg = '操作成功！';
	       					}else{
	       						if(data.errorMessages&&data.errorMessages.length>0){
	       							msg = data.errorMessages[0].message;
	       						}else{
	       							msg = '操作失败！';
	       						}
	       					}
	                        //提示
	                        Alert.remind(msg,function(){
	                        	//删除页面重载
	                        	 $('#conditionForm').submit();
	                        });
	       				}
                     });
                 }
               }
            },
            {
            	name:'取消',
            	classname:'btn-style',
            	ev:{click:function(data){
   					disappear();
                    $(".bghui").remove();
            	}}
            }]
	    });
    });
    
});
	
	
	function check(){
		if($("#title").val()=="输入商品标题进行搜索"){
			$("#title").val("");
		}
		var id = $(".tabs .cur").attr("id");
		if(id!='default'){
			$("#conditionForm").attr("action","/order/listinfo?orderstate="+id);
		}
	}
	//清空
	$("#clear").click(function(){
		$('#date_b').val('');
		$('#date_e').val('');
		$('#orderStartPrice').val('');
		$('#orderEndPrice').val('');
		$('#title').val('');
	});
	
</script>

<script>
 $('.o-icon').hover(
            function(){
                $(this).next('.o-sub').show();
            },
            function(){
                $(this).next('.o-sub').hide();
            }
    )
</script>


</body>
</html>