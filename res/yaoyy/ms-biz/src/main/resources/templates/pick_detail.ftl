<!DOCTYPE html>
<html lang="en">
<head>
    <title>选货单详情-药优优</title>
    <#include "./common/meta.ftl"/>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">选货单详情</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="sinfo">
        <div class="item">
            <ul class="info">
                <li>选货单状态：${pickVo.bizStatusText}</li>
                <li>寄样单号：${pickVo.code}</li>
                <li>申请时间：${(pickVo.createTime?datetime)!}</li>
            </ul>
            <ul class="time">
                <#list pickTrackingVos as tracking >
                    <#if tracking.recordType!=3>
                    <li>
                        <time>${(tracking.createTime?datetime)!}</time>
                        <span>${tracking.recordTypeText}</span>
                        <span>${tracking.extra?default('')}</span>
                    </li>
                    </#if>
                </#list>
            </ul>
        </div>

        <div class="item">
            <div class="hd">选货单：</div>
            <ul class="list">
                <#list pickVo.pickCommodityVoList as pickCommodityVo>
                <li><a href="commodity/detail/${pickCommodityVo.realCommodityId}"><em>${pickCommodityVo.name}</em><span>${pickCommodityVo.origin}  ${pickCommodityVo.spec}  ${pickCommodityVo.price}元/${pickCommodityVo.unit}</span></a><sub><em>${pickCommodityVo.num}</em> ${pickCommodityVo.unit} <b>&yen; <em>${pickCommodityVo.total}</em></b> 元</sub></li>
                </#list>
            </ul>
        </div>


        <div class="ui-extra">
            咨询电话：<a href="tel:027-33641141" target="_blank">027-33641141</a>
        </div>
    </div>
</section><!-- /ui-content -->

<#include "./common/footer.ftl"/>
<script>

    var _global = {
        fn: {
            init: function() {
            }
        }
    }

    $(function(){
        _global.fn.init();
    });

</script>
</body>
</html>