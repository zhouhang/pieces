<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
        <title>支付记录-${baseSetting.title!}</title>
        <meta name="description" content="${baseSetting.intro!}" />
        <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>
    <#include "./inc/header-center.ftl"/>

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>账期账单</h3>
                    <div class="extra"></div>
                </div>
                <div class="mybill">
                    <div class="fa-chart">
                        <table>
                            <thead>
                            <tr>
                                <th>账单编号</th>
                                <th>账期订单</th>
                                <th>应付</th>
                                <th>已付</th>
                                <th>未付</th>
                                <th>状态</th>
                                <th>约定还款时间</th>
                                <th width="90" class="tc">操作</th>
                            </tr>
                            </thead>

                                <tbody>
                                <#if billPage??&&billPage.list?has_content>
                                    <#list billPage.list as bill>
                                        <tr>
                                            <td>${bill.code!}</td>
                                            <td>${bill.orderCode!} <span>${bill.commodityOverview!}</span></td>
                                            <td>&yen;${bill.amountsPayable!}</td>
                                            <td>&yen;${bill.alreadyPayable!}</td>
                                            <td>&yen;${bill.unPayable!}</td>
                                            <td><em class="c-red">${bill.statusText!}</em></td>
                                            <td>${bill.repayTime?string("yyyy-MM-dd")}</td>
                                            <td class="tc">
                                                <#if bill.status==1>
                                                    <a class="btn btn-red" href="/center/bill/pay/${bill.id}">付款</a>
                                                </#if>
                                                <a href="/center/bill/detail/${bill.id}" class="c-blue">查看详情</a>
                                            </td>
                                        </tr>
                                    </#list>
                                    <#else>
                                        <td colspan="8">
                                            <div class="empty">
                                                您还没有任何账单信息！
                                            </div>
                                        </td>
                                </#if>
                                </tbody>
                        </table>
                    </div>
                    <#if billPage??>
                        <@p.pager inPageNo=billPage.pageNum-1 pageSize=billPage.pageSize recordCount=billPage.total toURL="/center/bill/index"/>
                    </#if>

                </div>
            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->



</body>
</html>

