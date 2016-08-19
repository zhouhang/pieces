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
                <h3>我的订单</h3>
                <div class="extra"></div>
            </div>

            <div class="order-list">
                <table class="tc">
                    <tbody>
                    <#if (pageInfo??&&pageInfo.list?size>0)>
                        <#list pageInfo.list as orderForm>
                        <tr>
                            <th colspan="4" class="tl">
                                <span>订单单号：${orderForm.code}</span>
                                <span>下单时间：<#if orderForm.createrTime??>${orderForm.createrTime?datetime}</#if></span>
                            </th>
                        </tr>
                        <tr>
                            <td class="tl nr">
                                <span class="name"><a
                                        href="user_order_detail.html">${orderForm.commodityOverview}</a></span>
                                <span>共${orderForm.commodities?size}个商品</span>
                            </td>
                            <td class="nl" width="145">
                                <span class="price">¥${orderForm.amountsPayable}</span>
                                <span>（包含运费￥${orderForm.shippingCosts}元 ）</span>
                            </td>
                            <td width="140">
                                <span class="c-red">${orderForm.statusText}</span>
                                <#if (orderForm.status == 1)>
                                    <span>剩余付款时间</span>
                                    <span>9天23时40分</span>
                                </#if>
                            </td>
                            <td width="140">
                                <#if (orderForm.status == 1)>
                                    <a href="#" class="btn btn-red">付款</a>
                                </#if>
                                <#if (orderForm.status == 4)>
                                    <a href="#" class="btn btn-red">确认收货</a>
                                </#if>
                                <span><a href="/center/order/detail/${orderForm.id}" class="c-blue">查看详情</a></span>
                                <#if (orderForm.status &lt;= 2)>
                                    <span><a href="#" class="c-blue jremove">取消订单</a></span>
                                </#if>
                            </td>
                        </tr>
                        <tr class="space"></tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            <#if !pageInfo??||(pageInfo??&&pageInfo.list?size == 0)>
                <div class="fa-pro-empty">
                    <p class="tc">暂无订单</p>
                </div>
            </#if>
            <#if pageInfo?? && pageInfo.size &gt; 0>
                <@p.pager inPageNo=pageInfo.pageNum-1 pageSize=pageInfo.pageSize recordCount=pageInfo.total toURL="/center/order/list"/>
            </#if>
            </div>
        </div>
    </div><!-- member-box end -->

<#include "./inc/footer.ftl"/>
</body>
</html>