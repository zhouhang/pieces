<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台</title>
    <meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商务仓
储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，保证中药材质量
，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面、最
专业的中药材买卖及各类相关综合服务。
"/>
    <meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储物流
，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/detail.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
	.procurement{ width:1200px; margin:0 auto;}
	.procurement .procurement_first{width:1200px; height:383px; position:relative; background:url(${RESOURCE_IMG}/images/procurement_01.png) no-repeat; margin:6px auto;  margin-bottom:80px;}
	.procurement .procurement_first a{position:absolute; width:234px; height:46px; background-image:url(${RESOURCE_IMG}/images/procurement_button.png); top: 314px;left: 476px;clear: both;}
	</style>
</head>
<body>
<!--topper strat-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="detail-submit mt20">
        <p class="caption">您的信息已提交，工作人员会在1个工作日内联系您！</p>
        <dl class="message clearfix">
            <dt>您的信息：</dt>
            <dd>用户名：${(ucUser.userName)!''}</dd>
            <dd>公司或姓名：
            <#if ucUser.userName==''>
            	${(ucUser.companyName)!''}
            <#else>
            	${(ucUser.userName)!''}	
            </#if>
            <dd>手机号码：${(ucUser.mobile)!''}</dd>
        </dl>
        <dl class="message clearfix">
            <dt>订单信息：</dt>
            <dd>订单编号：${(busiOrder.orderid)!''}</dd>
            <dd>摘牌时间：${busiOrder.createtime?string("yyyy-MM-dd   HH:mm:ss")}
            </dd>
        </dl>

        <table cellpadding="0" cellspacing="0" class="table" width="100%">
            <tr align="center">
                <th width="50%">药材</th>
                <th width="15%">数量</th>
                <th width="15%">单价</th>
                <th width="20%">总价</th>
            </tr>
            <tr>
                <td class="intro">
                    <span>
                    <a target="_self" href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${listingId!''}"><img <#if (firstGoodsPic.path)?? >src="${RESOURCE_IMG_UPLOAD}/${(firstGoodsPic.path)!''}"<#else> src=""</#if> id="elevate-zoom-img" />
                    	 <h3>${(goodsInfo.title)!''}</h3>
                    </a>
                    <ul>
                        <li>最低起订：<#if (goodsInfo.minsalesAmount)??><@tools.money num=goodsInfo.minsalesAmount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}</li>
                        <li>产地：${(goodsInfo.origin)!''}</li>
                        <li>等级/规格：${(goodsInfo.grade)!''}</li>
                        <li>能否提供发票：<#if goodsInfo??><#if goodsInfo.hasbill==1>提供<#else>不提供</#if></#if></li>
                    </ul>
                </td>
                <td align="center">
                <#if (busiOrder.amount)??><@tools.money num=busiOrder.amount format="0.##"/><#else>0</#if>
                ${(goodsInfo.unitname)!''}</td>
                <td align="center"><strong class="f16 col_red"><#if (busiOrder.unitprice)??><@tools.money num=busiOrder.unitprice format="0.##"/><#else>0</#if></strong> 元/${(goodsInfo.unitname)!''}</td>
                <td align="center"><strong class="f16 col_red"><#if (busiOrder.totalprice)??><@tools.money num=busiOrder.totalprice format="0.##"/><#else>0</#if></strong> 元</td>
            </tr>
        </table>
        <div class="account" align="center">
        	总金额：<strong class="f16 col_red"> <#if (busiOrder.totalprice)??><@tools.money num=busiOrder.totalprice format="0.##"/><#else>0</#if></strong>元 
        	<a target="_self" href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${listingId!''}">
                    	<input type="button" class="btn-red ml10" value="确定,继续购买" />
            </a>
        	
	        	查看
	        	<strong >
		        	<a target="_blank"  href="${URL_SERVER_PREFIX_UC}/order/listinfo" class="col_red">
		        		购买记录
		        	</a>
	        	</strong>
        	</div>
    	</div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script src="${RESOURCE_JS}/js/imgView/jquery.imageView.js" type="text/javascript"></script>

<script type="text/javascript">
var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254171531'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1254171531' type='text/javascript'%3E%3C/script%3E"));
$(function(){
	$('#searchEngineListingButton').searcher({
		onSearch:function() {
		var keyword=$('input[type="text"].search-text').val();
		if(keyword == "输入名称找药材"){
			keyword='';
		}
		window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
	 	}
	});
	
});






</script>
</body>
</html>