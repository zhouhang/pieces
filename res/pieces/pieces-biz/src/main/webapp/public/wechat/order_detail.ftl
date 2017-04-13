<!DOCTYPE html>
<html lang="en">
  <head>
   <#include "wechat/inc/meta.ftl"/>
    <title>订单-上工好药</title>
</head>
<body class="bg-gray">

<section class="ui-content">
    <div class="floors-info">
        <p><span>状态：</span><em>${vo.statusText}</em></p>
        <p><span>订单号：</span>${vo.code}</p>
        <p><span>下单时间：</span>${vo.createrTime?datetime}</p>
        <div class="button">
        <#if vo.status=1>
            <a href="/h5c/order/pay/${vo.id?c}" class="ubtn ubtn-red primary" id="pay">去支付</a>
            <button type="button" id="cancel" class="ubtn ubtn-white">取消订单</button>

        <#elseif vo.status=4>
            <button type="button" id="config" class="ubtn ubtn-red wide">确认收货</button>
        </#if>
       </div>
    </div>

    <div class="floors-info">
        <p><span>收货人：</span>${vo.address.consignee}  ${vo.address.tel}</p>
        <p><span>收货地址：</span>${vo.address.area}${vo.address.detail}</p>
         <#if vo.invoice?exists><p><span>发票信息：</span>${vo.invoice.typeText!}</p></#if>

    </div>
    <#if vo.status=4&&logistical?exists>
    <div class="floors-info">
        <#if logistical.type = 1>
            <p><span>配送方式：</span>快递</p>
            <p><span>快递公司：</span>${logistical.companyCodeName!} <a href="/h5c/order/logistical?orderId=${vo.id?c}" class="c-blue">[查询]</a></p>
            <p><span>快递单号：</span>${logistical.code!}</p>
            <a href="/h5c/order/logistical?orderId=${vo.id?c}" class="arrow"></a>
        <#elseif logistical.type = 2>
            <p><span>配送方式：</span>自提</p>
            <p><span>提货时间：</span>${logistical.receivingDate?date}</p>
            <p><span>提货地点：</span>${logistical.pickUp}</p>
        <#elseif logistical.type = 3>
            <p><span>配送方式：</span>货运部发货</p>
            <p><span>预计到货时间：</span>${logistical.receivingDate?date}</p>
            <p><span>司机姓名：</span>${logistical.driverName!}</p>
            <p><span>联系电话：</span>${logistical.driverTel!}</p>
        </#if>
    </div>
    </#if>

    <div class="goods">
           <#list vo.commodities as commodity>
        <ul>
            <li>
                <#if commodity.commodityId??>
                <a href="/h5/commodity/${commodity.commodityId}">
                </#if>
                <div class="hd">${commodity.name}</div>
                <div class="bd">${commodity.spec}${commodity.level}</div>
                <div class="price">
                   <#if userType==2>
                    <span>销售价:<em><#if commodity.guidePrice??>${(commodity.guidePrice?default(0))?string .currency}</#if></em></span>

                    <span>开票价:<#if commodity.price??>${(commodity.price?default(0))?string .currency}</#if></span>
                   <#else>
                       <span>单价:<#if commodity.price??>${(commodity.price?default(0))?string .currency}</#if></span>
                   </#if>
                </div>
                <div class="pic rs-pic">
                    <img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>" />
                </div>
                </a>
            </li>
        </ul>
        <div class="ft">
            数量：<em>${commodity.amount}</em>公斤
        </div>
           </#list>
    </div>

    <div class="summary">
        <div class="money">
            <em class="fr">¥${vo.amountsPayable}</em>
        </div>
        <#if userType==2>
            开票金额
        <div class="li">
            需支付保证金：<em>¥${vo.deposit}</em>
        </div>
        <#else >
            订单金额
        </#if>

    </div>

</section><!-- /ui-content -->

<#include "wechat/inc/footer_h5.ftl"/>
<script>
!(function($) {
    var _global = {
        init: function() {
            this.bindEvent();
        },
        bindEvent: function() {
            var enable = true;

            $('#config').on('click', function() {
                changeOrderStatus(${vo.id?c},5);
            })

            // 取消
            $('#cancel').on('click', function() {
                layer.open({
                    content: '确定要取消吗？',
                    btn: ['确定', '取消'],
                    yes: function(index) {
                        enable &&
                        changeOrderStatus(${vo.id?c},6);
                        enable = false;
                    }
                });
            })

            function changeOrderStatus(id, status) {
                $.post("/center/order/status", {orderId: id, status: status}, function (data) {
                    if (data.status == "y") {
                            window.location.reload();
                    }
                }, "json")
            }
        }
    }

    _global.init();
})(window.Zepto || window.jQuery);
</script>
</body>
</html>