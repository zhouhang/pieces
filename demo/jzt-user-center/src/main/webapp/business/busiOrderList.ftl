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
	<#import 'macro.ftl' as tools>
	<#import 'page.ftl' as fenye>
</head>
<body>
<!-- 头部  -->
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
    <!-- 我的仓单左侧 -->
    <#include 'common/left.ftl'>
    <div class="hy-right fr">
                <div class="cd-main">
            <h1 class="title_deal">我的购买</h1>
            <form action="/order/listinfo" method="post" id="conditionForm" onsubmit="return check();">
                <ul class="stage-search">
                    <li>  
                     <span>购买时间：</span><input type="text" class="text-store text-6 mr10 Wdate" name="orderStartDate" id="datetimepicker1" value="${(busiOrder.orderStartDate)!''}"  />至<input type="text" class="text-store text-6 ml10 Wdate" id="datetimepicker2" name="orderEndDate" value="${(busiOrder.orderEndDate)!''}"/>
                     <span>购买状态：</span>
                     	<select class="text-store text-select1" name="orderstate">
                        	<option value="">全部</option>
		            		<#if stateMap??>
			            		<#list stateMap?keys as key>
			            			<option value="${key}" <#if busiOrder.orderstate = key>selected</#if>>${stateMap[key]}</option>
			            		</#list>
		            		</#if>
                    	</select>
                    <span>关键字：</span><input class="text-store gray1 text-5" type="text" id="title" name="title" value="${(busiOrder.title)!''}" onfocus="if(this.value=='输入商品标题进行搜索') this.value='';" onblur="if(this.value=='') this.value='输入商品标题进行搜索';"/>
                    <span></span><input class="btn-red"  type="submit" id="btn-blue" value="搜索" /></li>
                </ul>
            </form>
            <div class="use-item1">
            <table class="table-store" width="100%" cellpadding="1" cellspacing="0">
                    <tr>
                        <th width="100"></th>
                        <th width="200">药材名</th>
                        <th width="220">单价</th>
                        <th width="100">数量</th>
                        <th width="180">实际付款</th>
                        <th width="100">订单状态挂牌</th>
                    </tr>
             </table>
             <#if page.results?size gt 0>
	             <#list page.results as order>
	             	 <table class="table-store" style="margin-top:10px;" width="100%" cellpadding="1" cellspacing="0">
	                    <tr>
	                        <th width="100"><#if order.updatetime??>${order.updatetime?string('yyyy-MM-dd')}</#if></th>
	                        <th width="200">订单编号：${(order.orderid)!''}</th>
	                        <th width="220">订购日期：<#if order.createtime??>${order.createtime?string('yyyy-MM-dd HH:mm:ss')}</#if></th>
	                        <th width="100"></th>
	                        <th width="180"></th>
	                        <th width="100"></th>
	                    </tr>
	                    <tr>
	                        <td style="padding:10px;">
	                        	<a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>
			                        <#if order.path==''>
			                        	<img src="${RESOURCE_IMG}/images/jzt-user-center/purchase.jpg">
			                        <#else>
			                        	<#assign img = order.path?substring((order.path?last_index_of("/")+1),(order.path?last_index_of(".")))+"_120.jpg"> 
	                   					<#assign tempurl = order.path?substring(0,order.path?last_index_of("/")+1)> 
	                   				    <img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${img}" alt="" width="80" height="80"/>
			                        </#if>
		                        </a>
	                        </td>
	                        <td><a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>${(order.title)!''}</a></td>
	                        <td><@tools.money num=order.unitprice format="0.##"/>元/${(order.wlunit)!''}</td>
	                        <td><@tools.money num=order.amount format="0.##"/>${(order.wlunit)!''}</td>
	                        <td><@tools.money num=order.totalprice format="0.##"/>元</td>
	                        <td>
	                        	<#if order.orderstate = 2>
	                        		<span style="color:#dedede;">${stateMap[order.orderstate?string]}</span>
	                        	<#else>
	                        		<span>${stateMap[order.orderstate?string]}</span>
	                        	</#if>
	                        	<br/>
            					<#if order.orderstate = 0>
            						<a href="javascript:;" data-id="${(order.orderid)!''}" data-reclick="n" class="cancelBtn">取消</a>
            					</#if>
							</td>
	                    </tr> 
	                  </table>
	               </#list>
	               <#else>
		              <table class="table-store" style="margin-top:10px;" width="100%" cellpadding="1" cellspacing="0">
	                    <tr>
	                        <th width="100"></th>
	                        <th width="200"></th>
	                        <th width="220"></th>
	                        <th width="100"></th>
	                        <th width="180"></th>
	                        <th width="100"></th>
	                    </tr>
	                    <tr>
	                        <td colspan="6" style="font-family:微软雅黑;font-size:14px;">${TIPS}</td>
	                    </tr> 
	                </table>	
		            </#if>  
            </div>

            <@fenye.pages page=page form="conditionForm"/>
        </div>
</div>
<!-- 提示框 (确定与取消)-->
	<div class="msgdiv" id="classMsg" style="display: none;" title="提示">
		<ul class="form-s form-bk" style="text-align: center;">
			<li>
				<p id="classMsgInfo" class="msgtxt"></p>
			</li>
			<li class="mt25">
				<p>
					<input type="hidden" id="id"/>
					<input type="hidden" id="wlid"/>
					<input type="hidden" id="amount"/>
				</p>
			</li>
		</ul>
	</div>
</div>
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
		//弹框，是否取消订单  add by Mr.song 2015.4.2
		$(".cancelBtn").on('click',function(){
	    	var _this = $(this),
	    		orderid = _this.data("id"),
	    		reclick = _this.data("reclick");
	    	$("#classMsgInfo").html('确定要取消订单 "'+orderid+'" 吗?');	
	    	if(reclick == "n") {
	    		_this.data("reclick", "y");
	    	} else {
	    		return false;
	    	}
	    	//确认是否取消
	    	$("#classMsg").dialog({
	          	autoOpen : false,
	   			modal : true,
	   			resizable : false,
	   			buttons : {
	   				确定 : function() {
	   					$("#classMsg").dialog("close");
		   					$.ajax({
			    			type:"POST",
			    			url:"/order/cancel",
			    			data:{orderid:orderid},
			    			dataType:"json",
			    			success:function(data){
			    				if(data.result == "success"){
			    					_this.parent().html("${stateMap['2']}");
			    					_this.remove();
			    					tips("订单 " + orderid + " 取消成功！");
			    				} else {
			    					_this.data("reclick", "n");
			    					tips("订单 " + orderid + " 取消失败！");
			    				}
			    			},
			    			error:function(){
			    				_this.data("reclick", "n");
			    				tips("发生未知错误，请重试！");
			    			}
			    		});
	   				},
	   				取消 : function() {
	   					$("#classMsg").dialog("close");
	   					_this.data("reclick", "n");
	   				}
	   			}
	     }).dialog("open");		
	    });
	})
</script>
</body>
</html>