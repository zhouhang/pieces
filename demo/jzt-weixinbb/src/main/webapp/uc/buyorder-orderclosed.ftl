<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>微信-查看订单详情</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery.js"></script>
    <script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
</head>
<body style="background: #f5f5f5;">
<div class="sell-box-head">
    <i class="back"></i>
    <div align="center" class="inStore-title">已关闭</div>
</div>
<div class="orDe-box" style="padding-top:3.2em;">
    <div class="state1">
        <p class="sty1">当前订单状态：已关闭
        </p>
    </div>

    <div class="orDetail">
        <h3>订单号：${order.orderid!''}</h3>
        <dl>
            <dt><span><img src="${RESOURCE_IMG_UPLOAD_WX}/${order.path!''}"  /></span></dt>
            <dd>
                <h4>${order.title!''}</h4>
                <p>
                    <span>单价 :</span>
                    <#if order.orderstate==0>
	              		<span class="pl15" style="white-space:nowrap;"><input id="${order.orderid!''}UnitPriceHidden" type="hidden" value="${order.unitprice!''}" /><input id="${order.orderid!''}UnitPrice" type="text" value="<@tools.money num=order.unitprice format="0.##"/>" style="border: 1px solid #CFCFCF;width: 36%;border-radius: 3px;vertical-align: middle;text-align:center; *text-align:left;"></span> /${order.wlunit!''}
	              	<#else>
						<@tools.money num=order.unitprice format="0.##"/>元/${order.wlunit!''}
	              	</#if><br/>
                    <span>订单数量：</span><@tools.money num=order.amount format="0.##"/>${order.wlunit!''}<br/>
                    <span>成交数量：</span><@tools.money num=order.volume format="0.##"/>${order.wlunit!""}<br/>
                    <span>商品总价：</span><d id="orderTotalPrice1"><@tools.money num=order.totalprice format="0.##"/></d>元<br/>
                    <span>摘牌日期：</span>${order.createtime?string("yyyy-MM-dd")!''}<br/>
                    <span>买方：</span>${buyerName!''}<br/>
                </p>
            </dd>
            <div class="clearfix"></div>
        </dl>
        <dl>
            <dt>&nbsp;</dt>
            <dd>
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td align="left">保证金金额</td>
                        <td align="right"><strong>￥<@tools.money num=order.deposit format="0.##"/></strong></td>
                    </tr>
                    <tr>
                        <td align="left">已付保证金金额</td>
                        <td align="right"><strong>￥0.00</strong></td>
                    </tr>
                    <tr>
                        <td align="left">尾款金额</td>
                        <td align="right"><strong>￥<@tools.money num=(order.totalprice-order.deposit) format="0.##"/></strong></td>
                    </tr>
                    <tr>
                        <td align="left">已付尾款金额</td>
                        <td align="right"><strong>￥0.00</strong></td>
                    </tr>
                </table>
            </dd>
            <div class="clearfix"></div>
        </dl>
        <dl style="border: 0 none;">
            <dt>&nbsp;</dt>
            <dd>
                <table cellpadding="0" cellspacing="0" width="100%">
                    <tr>
                        <td align="left">订单金额</td>
                        <td align="right"><strong class="col-red">￥<@tools.money num=order.totalprice format="0.##"/></strong></td>
                    </tr>
                </table>
            </dd>
            <div class="clearfix"></div>
        </dl>
    </div>
</div>
<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script>
	$(function(){
		$(".back").click(function(){
			history.go(-1);
		})
	})
</script>
</body>
</html>