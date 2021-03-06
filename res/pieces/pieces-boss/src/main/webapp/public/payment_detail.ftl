<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>单页面信息-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>支付信息</dt>
                <dd>
                    <a class="curr" href="/payment/index">支付信息</a>
                    <@shiro.hasPermission name="order:info">
                        <#if pay.orderId??>
                            <a target="_blank" href="/order/detail/${pay.orderId}">订单信息</a>
                        </#if>
                    </@shiro.hasPermission>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="title">
                <h3><i class="fa fa-chevron-right"></i>${pay.payCode}</h3>
                <div class="extra">
                    <a href="/payment/index" class="btn btn-gray">返回</a>
                <#if pay.status == 0>
                    <button type="button" class="btn  btn-gray" id="jfail">失败</button>
                    <button type="button" class="btn  btn-red" id="jsuc">成功</button>
                </#if>

                </div>
            </div>
            <div class="user-info">
                <h3>交易信息</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>支付流水号：</em>${pay.payCode}</td>
                            <td><em>订单编号：</em>${pay.orderCode}</td>
                        </tr>
                        <tr>
                            <td><em>订 购 用 户 ：</em>${pay.orderUserName}</td>
                            <td><em>用药单位：</em>${pay.companyFullName}</td>
                        </tr>
                        <tr>
                            <td><em>应 付 金 额 ：</em>&yen; ${pay.amountsPayable?string("0.00")}</td>
                            <td><em>支付渠道：</em>
                            <#if pay.paymentId?exists>
                            ${pay.payTypeName}
                            <#else>
                                线下打款
                            </#if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="user-info">
                <h3>付款信息</h3>
                <#if pay.paymentId?exists>
                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>支付金额：</em>&yen;&nbsp;<#if pay.actualPayment?exists>${pay.actualPayment?string("0.00")}<#else >${pay.deposit?string("0.00")}</#if></td>
                                <td><em>支付时间：</em>${(pay.paymentTime?date)!}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                    <#if pay.status != 0>
                        <div class="user-info">
                            <h3>支付结果</h3>
                            <div class="info">
                                <table>
                                    <tbody>
                                    <tr>
                                        <td><em>支付结果：</em>${pay.statusText!}</td>

                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </#if>
                <#else>
              <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td class="space"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>收款账户</b>
                            </td>
                        </tr>
                        <tr>
                            <td><em>开&nbsp; 户&nbsp; 行：</em>${pay.receiveBank!}</td>
                            <td><em>开户人：</em>${pay.receiveAccount!}</td>
                        </tr>
                        <tr>
                            <td><em>收款账号：</em>${pay.receiveBankCard!}</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="space"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>打款账户</b>
                            </td>
                        </tr>
                        <tr>
                            <td rowspan="2">
                                <div class="img">
                                    <em>支付凭证：</em>
                                            <span class="thumb">
                                            <#list pay.imgs as img>
                                                <img src="${img.path}"
                                                     data-src="${img.path}"
                                                     alt="" width="50" height="50">
                                            </#list>
                                            </span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><em>支付时间：</em>${(pay.paymentTime?date)!}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        <#if pay.status != 0>
            <div class="user-info">
                <h3>支付结果</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>支付结果：</em>${pay.statusText!}</td>
                            <td><em>操作人员：</em>${pay.memberName!}</td>
                        </tr>
                        <tr>
                            <td><em>记录时间：</em>${(pay.operationTime?date)!}</td>
                            <td>
                                <#if pay.failReason?exists>
                                    <em>失败原因：</em>${pay.failReason}
                                </#if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </#if>

        </#if>
        </div>
    </div><!-- fa-floor end -->
</div>



<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
<script src="${urls.getForLookupPath('/js/lightbox.js')}"></script>
<script>
    var _global = {
        v: {
            successUrl:"/payment/success",
            failUrl:"/payment/fail"
        },
        fn: {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                $('#jsuc').on('click', function() {
                    layer.confirm('确认本次交易付款成功？', {
                        title: '付款成功',
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        var url = _global.v.successUrl +"?payId=${pay.id}"
                        $.post(url, function(data) {
                            if (data.status == "y") {
                                window.location.reload();
                            }
                        },"json")
                        layer.close(index);
                    });
                });

                $('#jfail').on('click', function() {
                    layer.confirm('确认本次交易付款失败？', {
                        title: '付款失败',
                        btn: ['确认','取消'] //按钮
                    }, function(pass){
                        layer.prompt({title: '付款失败原因', formType: 2, btn: ['确认']},
                                function(text){
                                    var url = _global.v.failUrl +"?payId=${pay.id}"
                                    $.post(url,{msg:text}, function(data) {
                                        if (data.status == "y") {
                                            window.location.reload();
                                        }
                                    },"json")
                                });
                    });
                });
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>