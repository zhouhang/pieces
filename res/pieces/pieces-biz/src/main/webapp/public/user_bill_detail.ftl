<!DOCTYPE html>
<html lang="en">
<head>
    <title>账单详情-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header-center.ftl"/>


    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>账单详情</h3>
                    <div class="extra"></div>
                </div>

                <div class="order-list order-detail">
                    <table class="tc">
                        <tbody>
                            <tr>
                                <th colspan="3" class="tl">
                                    <span>账单编号：${accountBillVo.code!}</span>
                                </th>
                            </tr>
                            <tr>
                                <td class="tl nr">
                                    <span>应付：<em>&yen;${(accountBillVo.amountsPayable?default(0))?string("0.00")}</em></span>
                                    <span>已付：<em>&yen;${(accountBillVo.alreadyPayable?default(0))?string("0.00")}</em></span>
                                    <span>未付：<em>&yen;${(accountBillVo.unPayable?default(0))?string("0.00")}</em></span>
                                </td>
                                <td class="tl nl nr">
                                    <span>&#12288;&#12288;立账时间：<em><#if accountBillVo.operateTime??>${accountBillVo.operateTime?string("yyyy-MM-dd")}</#if> </em></span>
                                    <span>约定还款时间：<em>${accountBillVo.repayTime?string("yyyy-MM-dd")}</em></span>
                                </td>
                                <td class="nl">
                                <#if accountBillVo.status==1>
                                    <a class="btn btn-red" href="/center/bill/pay/${accountBillVo.id}">付款</a>
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
                                    <th width="130">支付流水号</th>
                                    <th width="70">支付渠道</th>
                                    <th width="70">支付时间</th>
                                    <th width="100">支付金额</th>
                                    <th width="90">状态</th>
                                </tr>
                            </thead>
                            <tfoot></tfoot>
                            <tbody>
                                <#if accountBillVo.payRecordVoList??&&accountBillVo.payRecordVoList?has_content>
                                    <#list accountBillVo.payRecordVoList as payRecord>
                                    <tr>
                                        <td>${payRecord.payCode!}</td>
                                        <td>${payRecord.payTypeName!}</td>
                                        <td>${payRecord.paymentTime?string("yyyy-MM-dd")}</td>
                                        <td>&yen;${payRecord.actualPayment!}</td>
                                        <#if payRecord_index == 0>
                                            <td rowspan="${accountBillVo.payRecordVoList?size}"><span class="c-red">${accountBillVo.statusText!}</span></td>
                                        </#if>
                                    </tr>
                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->

</body>
</html>
