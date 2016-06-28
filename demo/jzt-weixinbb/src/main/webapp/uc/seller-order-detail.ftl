<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>卖家订单-订单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/admin.css?t=${timestamp}">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
	<#import 'macro.ftl' as tools>
</head>
<body style="background: #f5f5f5;">
<div class="sell-box-head">
   <!-- <i class="back"></i>
    <div align="center" class="inStore-title">
    <#if busiOrderStateMap??>
		<#list busiOrderStateMap?keys as key>
			<#if key== busiOrder.orderstate>
				${busiOrderStateMap[key]}
			</#if>
		</#list>
	</#if>
    </div>-->
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	<tr>
    		<td width="12%"><i class="back"></i></td>
    		<td width="76%">
	    		<div align="center" style="width:100%;" class="inStore-title relative">
		    		<#if busiOrderStateMap??>
						<#list busiOrderStateMap?keys as key>
							<#if key== busiOrder.orderstate>
								${busiOrderStateMap[key]}
							</#if>
						</#list>
					</#if>
				</div>
			</td>
    		<td width="12%">&nbsp;</td>
    	</tr>
    </table>
</div>

<#if busiOrder.orderstate== -1 || busiOrder.orderstate== 2>
	<div class="orDe-box" style="padding-top:3.2em;">
	<div class="state1">
		<#if busiOrder.orderstate==-1>
        	<p class="sty1">当前订单状态：已关闭</p>
        <#else>
        	<p class="sty1">当前订单状态：已取消
	        	<br/>
	        	<span style="color:red;"><#if busiOrder.orderCancelReasean=='6'>买方未付货款过期<#elseif busiOrder.orderCancelReasean=='8'>买方申请取消</#if></span>
        	</p>
        </#if>	
    </div>
    <div class="orDetail">
<#else>
	<div class="orDe-box">
	   <div class="pross-bg relative">
	        <#if busiOrder.orderstate==0>
          		<span class="step1"></span>
          	<#elseif busiOrder.orderstate==1>
          		<span class="step4"></span>
          	<#elseif busiOrder.orderstate==3>
          		<span class="step2"></span>
          	<#elseif busiOrder.orderstate==5>
          		<span class="step3"></span>
          	<#else>
          		<span></span>
          	</#if>
	    </div>
	</div>
	<div class="orDe-box" style="padding-top:0em;">
	    <div class="orDetail" style="border-top:1px #E3E3E3 solid;">
</#if>    
        <h3>订单号：${busiOrder.orderid!''}  </h3>
        <dl>
            <dt><span><img src="${RESOURCE_IMG_UPLOAD_WX}/${busiOrder.path!''}"  /></span></dt>
            <dd>
                <h4>${busiOrder.title!''}</h4>
                <p>
                    <span>单价 :</span>
                    <#if busiOrder.orderstate==0>
	              		<span class="pl15" style="white-space:nowrap;"><input id="${busiOrder.orderid!''}UnitPriceHidden" type="hidden" value="${busiOrder.unitprice!''}" /><input id="${busiOrder.orderid!''}UnitPrice" type="text" value="<@tools.money num=busiOrder.unitprice format="0.##"/>" style="border: 1px solid #CFCFCF;width: 36%;border-radius: 3px;vertical-align: middle;text-align:center; *text-align:left;"></span> /${busiOrder.wlunit!''}
	              	<#else>
						<@tools.money num=busiOrder.unitprice format="0.##"/>元/${busiOrder.wlunit!''}
	              	</#if><br/>
                    <span>订单数量：</span><@tools.money num=busiOrder.amount format="0.##"/>${busiOrder.wlunit!''}<br/>
                    <span>成交数量：</span><@tools.money num=busiOrder.volume format="0.##"/>${busiOrder.wlunit!""}<br/>
                    <span>商品总价：</span><d id="orderTotalPrice1"><@tools.money num=busiOrder.totalprice format="0.##"/></d>元<br/>
                    <span>摘牌日期：</span>${busiOrder.createtime?string("yyyy-MM-dd")!''}<br/>
                    <span>买方：</span>${buyerName}<br/>
                </p>
            </dd>
            <div class="clearfix"></div>
        </dl>
        <dl style="border-bottom:0">
            <dt>&nbsp;</dt>
            <dd>
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td align="left">应付款</td>
                        <td align="right"><strong>￥<d id="orderTotalPrice2"><@tools.money num=busiOrder.totalprice format="0.##"/></d></strong>元</td>
                    </tr>
                    <tr>
                        <td align="left">实际付款</td>
                        <td align="right"><strong class="col-red">￥<@tools.money num=busiOrder.actualPayment format="0.##"/></strong>元</td>
                    </tr>
                </table>
            </dd>
            <div class="clearfix"></div>
        </dl>
    </div>
    <#if busiOrder.orderstate==0>
	    <div>&nbsp;</div><div>&nbsp;</div>
	    <div><a href="javascript:void(0);" dataval="${busiOrder.orderid!''}" onclick="updateOrderUnitPrice('${busiOrder.orderid!''}');" class="opr-tosell">保存单价</a></div>
	</#if>    
</div>
<script src="${RESOURCE_CSS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${RESOURCE_CSS_WX}/js/iscroll.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/layer/layer-Mobile/layer.js"></script>
<script type="text/javascript">
	//信息框
    function layerMsg(msg){
    	layer.open({
		    content: msg,
		    style: 'background-color:rgba(0, 0, 0, 0.6); color:#fff; border:none; text-align:center;',
		    time: 1
		});
    }
    
	//返回按钮事件
    $('.back').on('click',function(){
    	history.go(-1); 
    })
	
	//背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
	
	//修改订单单价
    function updateOrderUnitPrice(orderId){
    	var orderUnitPriceHidden = $('#'+orderId+'UnitPriceHidden').val();	
    	var orderUnitPrice = $('#'+orderId+'UnitPrice').val();	
    	var reg=/^[0-9]+(.[0-9]{1,2})?$/;
		if(!reg.test(orderUnitPrice)){
			layerMsg('请填写正数(至多两位小数)');
			$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
			$(".opr-tosell").blur();//让按钮按下去的样式消失。
			return false;
		 }
    	if(Number(orderUnitPriceHidden)<Number(orderUnitPrice)){
	    	layerMsg('单价不可改高！');
	    	$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
	    	$(".opr-tosell").blur();
			return false;
    	}
    	var Remove = '<div class="aRemove xiajia"><p>确定要修改编号 "'+orderId+'" 的订单单价？</p><div><input type="button" class="qx" value="取消" /><input type="button" dataval="'+orderId+'" class="qd" value="确定" /></div></div>'
        $('body').append(Remove);
        bgHiu();
    }
    
    $('body').delegate('.aRemove .qx','click',function(){
        $('.bghui').remove();
        $('.aRemove').remove();
    });
    
    $('body').delegate('.aRemove .qd','click',function(){
        $('.bghui').remove();
        $('.aRemove').remove();
        var orderId = $(this).attr("dataval");
    	var orderUnitPrice = $('#'+orderId+'UnitPrice').val();
    	var orderUnitPriceHidden = $('#'+orderId+'UnitPriceHidden').val();	
        $.ajax({
			async : false,
			cache : false,
			type : "POST",
			data : {"orderId":orderId,"orderUnitPrice":orderUnitPrice},
			dataType : "json",
			url : "/order/updateOrderUnitPrice",
			success : function(data) {
				var ok = data.ok;
				var msg = data.msg;
				var orderTotalPrice =data.orderTotalPrice;
				var unitprice =data.unitprice;
				if(ok){
					$("#orderTotalPrice1").html(orderTotalPrice);
					$("#orderTotalPrice2").html(orderTotalPrice);
					$('#'+orderId+'UnitPriceHidden').val(unitprice);
					layerMsg(msg);
				}else{
					$('#'+orderId+'UnitPrice').val(orderUnitPriceHidden);
					layerMsg(msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				layerMsg('网络超时，请重试！');
			}
		 });			
    });
</script>
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>