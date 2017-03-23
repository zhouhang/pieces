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
                    <h3>支付记录</h3>
                    <div class="extra"></div>
                </div>

                <div class="mybill">
                    <#if payRecordVo.status == 1>
                        <div class="fa-msg">
                            <i class="fa fa-check-circle"></i>
                            <span>支付成功！</span>
                        </div>
                    </#if>

                    <#if payRecordVo.status == 2>
                        <div class="fa-msg">
                            <i class="fa fa-times-circle"></i>
                            <span>支付失败！<em>（失败原因：${payRecordVo.failReason!}）</em></span>
                        </div>
                    </#if>



                    <div class="info">
                        <div class="hd">交易信息</div>
                        <div class="bd">
                            <table>
                                <tr>
                                    <td><em>支付流水号：</em>${payRecordVo.payCode!}</td>
                                    <td><em>订单编号：</em>${payRecordVo.orderCode!}</td>
                                </tr>
                                <tr>
                                    <td><em>订 购 用 户 ：</em>${payRecordVo.orderUserName!}</td>
                                    <td><em>用药单位：</em>${payRecordVo.companyFullName!}</td>
                                </tr>
                                <tr>
                                    <td><em>应 付 金 额 ：</em>&yen; ${payRecordVo.amountsPayable!}</td>
                                    <td><em>支付渠道：</em>
                                       <#if payRecordVo.paymentId?exists>
                                           ${payRecordVo.payTypeName}
                                       <#else>
                                           线下打款
                                       </#if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="info">
                        <div class="hd">付款信息</div>
                        <#if payRecordVo.paymentId?exists>
                            <div class="bd">
                                <table>
                                    <tr>
                                        <td><em>付款金额：</em>
                                            <#if payRecordVo.actualPayment?exists>
                                            ${payRecordVo.actualPayment!}
                                            <#else>
                                            ${payRecordVo.amountsPayable!}
                                            </#if>
                                        </td>
                                        <td><em>支付时间：</em>${(payRecordVo.paymentTime?datetime)!}</td>
                                    </tr>
                                </table>
                            </div>
                        <#else>
                        <div class="bd">
                            <table>
                                <tr>
                                    <td colspan="2">
                                        <b>收款账户</b>
                                    </td>
                                </tr>
                                <tr>
                                    <td><em>开&nbsp; 户&nbsp; 行：</em>${payRecordVo.receiveBank!}</td>
                                    <td><em>开&nbsp; 户&nbsp; 人：</em>${payRecordVo.receiveAccount!}</td>
                                </tr>
                                <tr>
                                    <td><em>收款账号：</em>${payRecordVo.receiveBankCard!}</td>
                                    <td><em>付款金额：</em>
                                    <#if payRecordVo.actualPayment?exists>
                                    ${payRecordVo.actualPayment!}
                                    <#else>
                                    ${payRecordVo.amountsPayable!}
                                    </#if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="bd bt1">
                            <table>
                                <tr>
                                    <td colspan="2">
                                        <b>打款账户</b>
                                    </td>
                                </tr>
                                <tr>
                                    <#if payRecordVo.payBank?exists>
                                    <td><em>开&nbsp; 户&nbsp; 行：</em>${payRecordVo.payBank!}</td>
                                    </#if>
                                    <#if payRecordVo.payAccount?exists>
                                    <td><em>开&nbsp; 户&nbsp; 人：</em>${payRecordVo.payAccount!}</td>
                                    </#if>
                                </tr>
                                <tr>
                                    <#if payRecordVo.payBankCard?exists>
                                    <td><em>收款账号：</em>${payRecordVo.payBankCard!}</td>
                                    </#if>
                                    <td rowspan="2">
                                        <div class="img">
                                            <em>支付凭证：</em>
                                            <b class="thumb">
                                                <#list payRecordVo.imgs as document>
                                                    <img src="${document.path!}" data-src="${document.path!}" alt="" width="50" height="50">
                                                </#list>
                                            </b>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><em>支付时间：</em>${payRecordVo.paymentTime?string("yyyy-MM-dd")}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                        </#if>
                </div>


            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    <script src="${urls.getForLookupPath('/js/lightbox.js')}"></script>

</body>
</html>