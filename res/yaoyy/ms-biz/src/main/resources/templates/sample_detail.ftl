<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>寄样详情-药优优</title>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">寄样详情</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="sinfo">
        <div class="item">
            <ul class="info">
                <li>寄样状态：${sendSampleVo.bizStatusText}</li>
                <li>寄样单号：${sendSampleVo.code}</li>
                <li>申请时间：${(sendSampleVo.createTime?datetime)!}</li>
            </ul>
            <ul class="time">
                <#list trackingList as tracking >
                    <#if tracking.recordType!=6&&tracking.recordType!=7>
                <li>
                    <time>${(tracking.createTime?datetime)!}</time>
                    <span>${tracking.recordTypeText}&nbsp;&nbsp;${tracking.extra?default('')}</span>
                </li>
                    </#if>
                </#list>
            </ul>
        </div>

        <div class="item">
            <div class="hd">寄样商品：</div>
            <ul class="list">
               <#list sendSampleVo.commodityList as commodity >
                <li><a href="commodity/detail/${commodity.commodityId?c}">${commodity.name}  ${commodity.origin}  ${commodity.spec}</a></li>
               </#list>
            </ul>
        </div>

        <div class="item">
            <div class="hd">留言：</div>
            <ul class="time">
                <#list trackingList as tracking >
                    <#if tracking.recordType==7>
                    <li>
                        <time>${(tracking.createTime?datetime)!}</time>
                        <span>${tracking.extra?default('')}</span>
                    </li>
                    </#if>
                </#list>
            </ul>

            <div class="reply">
                <a href="sample/msg/${sendSampleVo.id?c}" class="ubtn ubtn-primary">留言</a>
            </div>

            <div class="ui-extra">
                咨询电话：<a href="tel:027-33641141" target="_blank">027-33641141</a>
            </div>
        </div>
    </div>

</section><!-- /ui-content -->


<#include  "./common/footer.ftl"/>
</body>
</html>