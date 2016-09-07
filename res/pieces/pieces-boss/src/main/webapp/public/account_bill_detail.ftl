<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>账单详情-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>账单信息</dt>
                    <dd>
                        <a class="curr" href="/account/bill/index">账单信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>${vo.code}</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" id="jsuc">成功</button>
                        <button type="button" class="btn btn-gray" id="jfail">失败</button>
                        <a href="/account/bill/index" class="btn btn-red">返回</a>
                    </div>
                </div>
                <div class="user-info">
                    <h3>交易信息</h3>
                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>账期订单：</em>${vo.orderCode}</td>
                                <td><em>支付方式：</em>账期支付</td>
                            </tr>
                            <tr>
                                <td><em>订购用户 ：</em>${vo.orderUser}</td>
                                <td><em>用药单位：</em>${vo.orderCompany}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            <#if !(vo.status == 1 || vo.status == 2) >
                <div class="user-info">
                    <h3>付款信息</h3><!-- 待处理时是 “付款信息” 其他是“账单信息”-->

                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>应付金额：</em>&yen; ${vo.amountsPayable?double}</td>
                                <td><em>账期时间：</em>${vo.billTime}月</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </#if>
            <#if vo.status == 1 || vo.status == 2 >
                <div class="user-info">
                    <h3>账单信息</h3>
                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>立账时间：</em>${vo.operateTime?date}</td>
                                <td><em>约定还款时间：</em>${vo.repayTime?date}</td>
                            </tr>
                            <tr>
                                <td><em>应付金额 ：</em>${vo.amountsPayable}</td>
                                <td><em>已付金额：</em>${vo.alreadyPayable}</td>
                            </tr>
                            <tr>
                                <td><em>未付金额 ：</em>${vo.unPayable}</td>
                                <td><em>账单状态：</em>${vo.statusText}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="user-info">
                    <h3>支付记录</h3><!-- 待处理时是 “付款信息” 其他是“账单信息”-->

                    <div class="info chart">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>支付流水号</th>
                                <th>支付渠道</th>
                                <th>支付时间</th>
                                <th>支付金额</th>
                                <th>支付状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list vo.payRecordVoList as record>
                            <tr>
                                <td>${record.id}</td>
                                <td>${record.payCode}</td>
                                <td>线下打款</td>
                                <td>${record.paymentTime?date}</td>
                                <td>&yen; ${record.actualPayment}</td>
                                <td>${record.statusText}</td>
                                <td><a href="payment_detail.html">查看</a></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>
                </#if>
                <#if vo.status != 0>
                <div class="user-info">
                    <h3>审核信息</h3>
                    <div class="info">
                        <table>
                            <tbody>
                            <tr>
                                <td><em>审核结果：</em><#if vo.status == -1>拒绝<#else>同意</#if></td>
                                <td><em>操作人员：</em>${vo.memberName}</td>
                            </tr>
                            <tr>
                                <td><em>记录时间：</em>${vo.operateTime?date}</td>
                                <#if vo.status == -1>
                                    <td><em>拒绝原因：</em>${vo.remark}</td>
                                </#if>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                </#if>
            </div>
        </div><!-- fa-floor end -->
    </div>

<!-- footer start -->
    <#include "./inc/footer.ftl"/>
<!-- footer end -->
    <script src="js/jquery.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/lightbox.js"></script>
<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                $('#jsuc').on('click', function() {
                    layer.confirm('确认本次账单审核成功？', {
                        title: '审核成功',
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        var url = "/account/bill/success?id="+${vo.id};
                        $.post(url, function (data) {
                            if (data.status == "y") {
                                window.location.reload();
                            }
                            layer.close(index);
                        })
                    });
                });

                $('#jfail').on('click', function() {
                    layer.confirm('确认本次账单审核失败？', {
                        title: '审核失败',
                        btn: ['确认','取消'] //按钮
                    }, function(pass){
                        layer.prompt({title: '审核失败原因', formType: 2, btn: ['确认']},
                                function(text){
                                    var url = "/account/bill/fail?id="+${vo.id};
                                    $.post(url,{msg:text}, function (data) {
                                        if (data.status == "y") {
                                            window.location.reload();
                                        }
                                        layer.close(index);
                                    })
                                    // layer.msg('演示完毕！您的口令：'+ pass +' 您最后写下了：'+ text);
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