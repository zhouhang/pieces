<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>订单详情-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>订单信息</dt>
                    <dd>
                        <a class="curr" href="/order/index">订单信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>订单 ${vo.code} | <#if vo.createrTime?exists>${vo.createrTime?datetime}</#if></h3>
                    <div class="extra">
                        <a  class="btn btn-gray" href="order/index">返回</a>
                        <a  id="editerOrder" class="btn btn-gray" href="javascript:;">修改</a>
                        <a type="button" class="btn btn-gray" href="javascript:;">发票</a>
                        <a id="delivery" type="button" class="btn btn-gray" href="javascript:;">配送</a>
                        <a  class="btn btn-red" href="/order/anew/${vo.id!}">重新下单</a>
                    </div>
                </div>

                <div class="groups">
                    <div class="group">
                        <dl>
                            <dt>订单号：${vo.code}</dt>
                            <dd>
                                <p>下单日期：<#if vo.createrTime?exists>${vo.createrTime?datetime}</#if></p>
                                <p>订单状态：${vo.statusText}</p>
                            </dd>
                        </dl>
                    </div>
                    <div class="group">
                        <dl>
                            <dt>客户信息</dt>
                            <dd>
                                <p>用药单位：${vo.user.companyFullName}</p>
                                <p>联&nbsp;&nbsp;系&nbsp;&nbsp;人：${vo.user.contactName}</p>
                                <p>联系电话：${vo.user.contactMobile}</p>
                            </dd>
                        </dl>
                    </div>
                    <div class="group">
                        <dl>
                            <dt>配送信息</dt>
                            <dd>
                                <p>收&nbsp;&nbsp;货&nbsp;&nbsp;人：${vo.address.consignee}</p>
                                <p>联系电话：${vo.address.tel}</p>
                                <p>收货地址：${vo.address.area}${vo.address.detail}</p>
                            </dd>
                        </dl>
                    </div>
                </div>

                <div class="chart-info">
                    <h3>订购商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th width="80">切制规格</th>
                                <th width="80">等级</th>
                                <th>产地</th>
                                <th width="100">期望交货日期</th>
                                <th width="120">数量（公斤）</th>
                                <th width="120">单价（元/公斤）</th>
                                <th width="200">小计（元）</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="8">
                                    <div class="summary">
                                        <div class="item">
                                            <span>商品合计：</span>
                                            <em>￥${vo.sum}</em>
                                        </div>
                                        <div class="item">
                                            <span>运&#12288;&#12288;费：</span>
                                            <em>￥${vo.shippingCosts}</em>
                                        </div>
                                        <div class="item">
                                            <span>实际应付：</span>
                                            <em class="price">￥${vo.amountsPayable}</em>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <#list vo.commodities as commoditie>
                            <tr>
                                <td>${commoditie.name}</td>
                                <td>${commoditie.spec}</td>
                                <td>${commoditie.level}</td>
                                <td>${commoditie.originOf}</td>
                                <td>${commoditie.expectDate?date}</td>
                                <td>${commoditie.amount}</td>
                                <td>${commoditie.price}</td>
                                <td>&yen;${commoditie.subtotal}</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="chart-info">
                    <h3>备注历史</h3>
                    <form action="" class="note-form">
                        <div class="txt">订单备注：</div>
                        <div class="cnt"><textarea class="ipt" name="content" id="content" cols="30" rows="10" placeholder="请填写本次采购另外需要注意的事项。"></textarea></div>
                        <div class="button">
                            <button class="btn btn-gray" type="button" id="summit_comment">提交备注</button>
                        </div>

                        <div class="history">
                            <ul id="remarklist">
                                <#if vo.remark?exists??&&vo.remark != "">
                                    <li>
                                        <span> <#if vo.createrTime?exists>${vo.createrTime?date}</#if></span>
                                        <span>客户备注</span>
                                        <span>${vo.remark}</span>
                                    </li>
                                </#if>
                                <#list remarks as remark>
                                <li>
                                    <span>${remark.createrTime?date}</span>
                                    <span>客服备注</span>
                                    <span>${remark.content}</span>
                                </li>
                                </#list>
                            </ul>
                        </div>
                    </form>

                </div>
            </div>
        </div><!-- fa-floor end -->
    </div>

    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
<!-- footer end -->
    <script src="/js/common.js"></script>
    <script src="/js/layer/layer.js"></script>
<script>
    var enquiryPage = {
        v: {},
        fn: {
            init: function () {
                $("#summit_comment").on("click", function () {
                    var content = $("#content").val();
                    $.post("/order/addComment",{content:content,orderId:${vo.id}}, function (data) {
                        if (data.status == "y") {
                            $.notify({
                                type: 'success',
                                title: '添加评论成功。',
                                delay: 3e3
                            });

                            var html = "<li><span>" + data.data.createrTime+"</span>" +
                                "<span>客服备注</span><span>"+data.data.content+"</span></li>";
                            $("#remarklist").append(html);
                        }
                    })
                })

                $("#editerOrder").click(function () {
                    var $this = $(this);
                    layer.confirm('您确认吗？该订单将会被取消并生成新的订单', {icon: 3, title: '提示'}, function (index) {
                        window.location.href = "/order/edit/${vo.id!}";
                        layer.close(index);
                    });
                    return false
                });

                $("#delivery").click(function () {
                    layer.confirm('您确认吗？订单已经发货', {icon: 3, title: '提示'}, function (index) {
                        layer.close(index);
                        $.post("/order/status",{status:4,orderId:${vo.id}}, function (data) {
                            if (data.status == "y") {
                                $.notify({
                                    type: 'success',
                                    title: '订单状态修为为已发货.',
                                    delay: 3e3,
                                    call: function () {
                                        window.location.reload();
                                    }
                                });
                            }
                        })
                    });
                    return false
                });
            }
        }
    }
    $(function() {
        enquiryPage.fn.init();
    })
</script>
</body>
</html>