<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>订单列表-饮片B2B</title>
</head>

<body>

<#include "./inc/header-center.ftl"/>

<!-- member-box start -->
<div class="member-box">
    <div class="wrap">
    <#include "./inc/side-center.ftl"/>
        <div class="main">
            <div class="title">
                <h3>订单详情</h3>
                <div class="extra"></div>
            </div>

            <div class="order-list order-detail">
                <table class="tc">
                    <tbody>
                    <tr>
                        <th colspan="3" class="tl">
                            <span>订单单号：${orderForm.code}</span>
                            <span>下单时间：<#if orderForm.createrTime??>${orderForm.createrTime?datetime}</#if></span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：<em>${orderForm.address.consignee}</em></span>
                            <span>联系方式：<em>${orderForm.address.tel}</em></span>
                            <span>收货地址：<em>${orderForm.address.area}${orderForm.address.detail}</em></span>
                        </td>
                        <td class="tl nl nr">
                            <span>商品合计：<em class="price">¥${orderForm.amountsPayable}</em></span>
                            <span>运　　费：<em class="price">¥${orderForm.shippingCosts}</em></span>
                        </td>
                        <td class="nl">
                        <#if (orderForm.status == 1)>
                            <a href="#" class="btn btn-red">付款</a>
                        </#if>
                        <#if (orderForm.status == 4)>
                            <a href="#" class="btn btn-red">确认收货</a>
                        </#if>
                            <#if (orderForm.status == 1)>
                                <span>剩余付款时间</span>
                                <span>${orderForm.orderValidityPeriod}</span>
                            </#if>
                        <#if (orderForm.status &lt;= 2)>
                            <span><a href="#" class="c-blue jremove">取消订单</a></span>
                        </#if>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="fa-table order-detail-list">
                <div class="fa-chart">
                    <table>
                        <thead>
                        <tr>
                            <th width="130">商品名称</th>
                            <th width="70">切制规格</th>
                            <th width="70">等级</th>
                            <th width="100">产地</th>
                            <th width="90">期望交货日期</th>
                            <th width="90">数量<span>（公斤）</span></th>
                            <th width="100">单价<span>（元/公斤）</span></th>
                            <th width="130">小计<span>（元）</span></th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                        <#list orderForm.commodities as commodity>
                        <tr>
                            <td>${commodity.name}</td>
                            <td>${commodity.spec}</td>
                            <td>${commodity.level}</td>
                            <td>${commodity.originOf}</td>
                            <td><#if commodity.expectDate??>${commodity.expectDate?date}</#if></td>
                            <td>${commodity.amount}</td>
                            <td><#if commodity.price??>¥${commodity.price}</#if></td>
                            <td><#if commodity.subtotal??>¥${commodity.subtotal}</#if></td>
                            <#if commodity_index == 0>
                                <td rowspan="${commodity?size}"><span class="c-red">待付款</span></td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

<#include "./inc/footer.ftl"/>
</body>
</html>