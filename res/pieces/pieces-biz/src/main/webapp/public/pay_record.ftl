<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付记录-上工好药</title>
</head>

<body>
    <#include "./inc/header-center.ftl"/>

    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>支付记录</h3>
                    <div class="extra"></div>
                </div>
                <div class="mybill">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th width="180">支付流水号</th>
                                    <th width="170">支付订单</th>
                                    <th width="110">应付</th>
                                    <th width="110">实付</th>
                                    <th width="110">支付时间</th>
                                    <th width="100">状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if recordPage??&&recordPage.list?has_content>
                                <#list recordPage.list as payRecord>
                                    <tr>
                                        <td>${payRecord.payCode!}</td>
                                        <td>${payRecord.orderCode!} <span>${payRecord.commodityOverview!}</span></td>
                                        <td>&yen;${payRecord.amountsPayable!}</td>
                                        <td>&yen;${payRecord.actualPayment!}</td>
                                        <td>${payRecord.paymentTime?string("yyyy-MM-dd")}</td>
                                        <td><em class="c-red">${payRecord.statusText!}</em></td>
                                        <td><a href="/center/pay/details/${payRecord.id!}" class="c-blue">查看详情</a></td>
                                    </tr>
                                </#list>
                                <#else>
                                <tr>
                                    <td colspan="7">
                                        <div class="empty">
                                            您还没有任何支付记录！
                                        </div>
                                    </td>
                                </tr>
                            </#if>
                            </tbody>
                        </table>
                    </div>

                    <#if recordPage??>
                        <@p.pager inPageNo=recordPage.pageNum-1 pageSize=recordPage.pageSize  recordCount=recordPage.total  toURL="/center/pay/record"/>
                    </#if>

                </div>
            </div>
        </div>
    </div><!-- member-box end -->


    <#include "./inc/footer.ftl"/>


</body>
</html>