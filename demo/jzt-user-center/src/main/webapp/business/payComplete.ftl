<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>订单详情（支付保证金）</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    
    <#import 'macro.ftl' as tools>
</head>
<body>
<!-- 头部  -->
    <#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
    <!-- 会员左侧 -->
    <#include 'common/left.ftl'>
    <!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="pickcard-box">
            <ul class="process-list clearfix">
                <li class="cur">已下单</li>
                <li class="cur">等待平台备货</li>
                <li class="cur">平台已备货，等待买家支付货款</li>
                <li class="cur">买家已付货款，交易完成</li>
            </ul>
            <div class="process">
                <span class="step4"></span>
            </div>
            <p class="fontstyle"><strong>当前订单状态：</strong>买家已付货款，交易完成</p>
            <p class="fontstyle mt20"><span class="pr50 relative dis-in-bk"><strong>订单信息：</strong>订单编号：${order.orderid!""}
             <!-- 账期订单 -->
            <#if order.orderType==2>
            <i class="o-icon"></i>
                    <span class="o-sub dis-in-bk">
                        <span class="sj"></span>
                     	   延期付款提示：您与卖方约定延期${daydiffp}天付款，请于${order.endTime?string('yyyy年MM月dd日')}前来平台上付款！
                    </span>
                    </#if>
                    </span>
				<span>摘牌日期：${order.createtime?string('yyyy-MM-dd')}</span>&nbsp;&nbsp;卖方：${sellerName}</p>

            <table cellpadding="0" cellspacing="0" class="order-message" width="100%">
                <tr align="center">
                    <th width="50%">药材</th>
                    <th width="15%">单价</th>
                    <th width="10%">订单数量</th>
                    <th width="10%">成交数量</th>
                    <th width="15%">商品总价</th>
                </tr>
                <tr>
                    <td class="order-intro">
                        <span>
                        	<a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>
                        	<img src="${RESOURCE_IMG_UPLOAD}/${order.path!""}">
                        	</a>
                        </span>
                        <h3 class="od"><a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${order.listingid}'  target='_blank'>${order.title!""}</a></h3>
                    </td>
                    <td align="center"><@tools.money num=order.unitprice format="0.##"/>元/${order.wlunit!""}</td>
                    <td align="center"><@tools.money num=order.amount format="0.##"/>${order.wlunit!""}</td>
                    <td align="center"><@tools.money num=order.volume format="0.##"/>${order.wlunit!""}</td>
                    <td align="center"><@tools.money num=order.totalprice format="0.##"/>元</td>
                </tr>
            </table>
            <div class="collect fr mt20">
                	应付款：<strong><@tools.money num=order.totalprice format="0.##"/></strong>元<br/>
                	<i class="qus dis-in-bk"><span class="tips">若实际收到的货物变少出现退款情况，会在订单完成分润时退还给您，若货物无误，请不用理会！<i class="sj dis-in-bk"></i> </span></i> 实际已付款：<strong><@tools.money num=order.actualPayment format="0.##"/></strong>元
            </div>
            
        </div>
    </div>
</div>
<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
 <!-- 账期订单 -->
<#if order.orderType==2>
<script>
    $('.o-icon').hover(
        function(){
            $(this).next('.o-sub').show();
        },
        function(){
            $(this).next('.o-sub').hide();
        }
    );
</script>
</#if>

<script>
    $(function(){
        $('.qus').hover(
            function(){$(this).children('.tips').show();},
            function(){$(this).children('.tips').hide();}
        );
    });
</script>
</body>
</html>