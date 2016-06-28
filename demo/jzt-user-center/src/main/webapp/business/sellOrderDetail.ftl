<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的珍药材</title>
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
    <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
    <#import 'macro.ftl' as tools>
</head>
<body>
<#include 'common/header.ftl'>
<div class="area-1200 clearfix">
    <#include 'common/left.ftl'>
    <div class="hy-right fr">
    	<#if busiOrder.orderstate==2>
        	<h2 class="title4 mt10">订单信息</h2>
        </#if>
        <div class="pickcard-box">
        	<#if busiOrder.orderstate==2>
        		 <p class="fontstyle mt10"><strong>当前订单状态：</strong><span class="pr20">已取消</span><span class="col-red1"><#if busiOrder.orderCancelReasean=='6'>买方未付货款过期<#elseif busiOrder.orderCancelReasean=='8'>买方申请取消</#if></span></p>
        	<#elseif busiOrder.orderstate==-1>
        		 <p class="fontstyle mt10"><strong>当前订单状态：</strong>已关闭</p>
        	<#else>
        		<ul class="process-list sell clearfix">
	                <li class="cur">等待买家支付保证金</li>
	                <li <#if busiOrder.orderstate==3||busiOrder.orderstate==5||busiOrder.orderstate==4||busiOrder.orderstate==1>class="cur"<#else>class=""</#if> >买家支付保证金</li>
	                <li <#if busiOrder.orderstate==5||busiOrder.orderstate==4||busiOrder.orderstate==1>class="cur"<#else>class=""</#if> >平台已备货</li>
	                <li <#if busiOrder.orderstate==1>class="cur"<#else>class=""</#if> >买家已付款，交易完成</li>
	            </ul>
	            <div class="process">
	            	<#if busiOrder.orderstate==0>
		          		<span class="step1"></span>
		          	<#elseif busiOrder.orderstate==1>
		          		<span class="step4"></span>
		          	<#elseif busiOrder.orderstate==3>
		          		<span class="step2"></span>
		          	<#elseif busiOrder.orderstate==4><!--买家已付款-->
                        <span class="step3"></span>
		          	<#elseif busiOrder.orderstate==5>
		          		<span class="step3"></span>
		          	<#else>
		          		<span></span>
		          	</#if>
	            </div>
        	</#if>
            <p class="fontstyle mt20"><span class="pr50 relative dis-in-bk"><strong>订单信息：</strong>订单编号：${busiOrder.orderid!''}
            <!-- 账期订单 -->
            <#if busiOrder.orderType==2>
            <i class="o-icon"></i>
                    <span class="o-sub dis-in-bk" style="display: none;">
                        <span class="sj"></span>
                    	    延期付款提示：该笔订单买方与您已共同约定同意买家延期${daydiff}天付款，请您与买方沟通好按期来平台上付款！
                    </span></#if>
                  </span><span>摘牌日期：${busiOrder.createtime?string("yyyy-MM-dd HH:mm:ss")!''}</span>
            	<#if busiOrder.orderstate==0||busiOrder.orderstate==-1>
            	<#else>
            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;买方：${buyerName}
            	</#if>
            </p>
            <table cellpadding="0" cellspacing="0" class="order-message mt20" width="100%">
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
                        <a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiOrder.listingid}'  target='_blank'>
                        <img src="${RESOURCE_IMG_UPLOAD}/${busiOrder.path!''}">
                        </a>
                        </span>
                        <h3 class="od">
                        <a href='${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiOrder.listingid}'  target='_blank'>
                        ${busiOrder.title!''}
                        </a>
                        </h3>
                    </td>
                    <td align="center"><@tools.money num=busiOrder.unitprice format="0.##"/>元/${busiOrder.wlunit!''}</td>
                    <td align="center"><@tools.money num=busiOrder.amount format="0.##"/>${busiOrder.wlunit!''}</td>
                    <td align="center"><@tools.money num=busiOrder.volume format="0.##"/>${busiOrder.wlunit!""}</td>
                    <td align="center"><@tools.money num=busiOrder.totalprice format="0.##"/>元</td>
                </tr>
            </table>
            <div class="collect fr mt20">
		                应付款：<strong><@tools.money num=busiOrder.totalprice format="0.##"/></strong>元<br/>
		                实际付款：<strong><@tools.money num=busiOrder.actualPayment format="0.##"/></strong>元<br/>
            </div>
        </div>
    </div>
</div>
<#include 'common/footer.ftl'>

<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
 <!-- 账期订单 -->
<#if busiOrder.orderType==2>
<script type="text/javascript">
    $('.o-icon').hover(
            function(){
                $(this).next('.o-sub').show();
            },
            function(){
                $(this).next('.o-sub').hide();
            }
    )
</script>
</#if>
</body>
</html>