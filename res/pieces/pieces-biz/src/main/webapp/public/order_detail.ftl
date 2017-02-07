<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>订单列表-上工好药</title>
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

            <div style="display: none;">${orderForm.statusText}</div>

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
                            <span>订单总额：<em class="price">¥${orderForm.amountsPayable}</em></span>
                        <#if user_session_biz?? && user_session_biz.type == 2>
                            <span>需支付保证金：<em class="price">¥${orderForm.deposit}</em></span>
                        </#if>
                        </td>
                        <td class="nl">
                        <#if user_session_biz?? && user_session_biz.type == 2>
                            <#if (orderForm.status == 1)>
                                <a href="/center/pay/go/${orderForm.id}" class="btn btn-red">支付保证金</a>
                            </#if>
                        <#else >
                            <#if (orderForm.status == 1)>
                                <a href="/center/pay/go/${orderForm.id}" class="btn btn-red">付款</a>
                            </#if>
                        </#if>
                        <#if (orderForm.status == 4)>
                            <a href="${orderForm.id}" name="5" class="btn btn-red status">确认收货</a>
                        </#if>
                        <#if (orderForm.status &lt;= 2 || orderForm.status == 8)>
                            <span><a href="${orderForm.id}" name="6" class="c-blue jremove status">取消订单</a></span>
                        </#if>
                        <#if (orderForm.status == 6)>
                            <span><a href="${orderForm.id}" name="7" class="c-blue jremove status">删除订单</a></span>
                        </#if>
                        <#if (orderForm.status == 1)>
                            <span>剩余付款时间</span>
                            <span>${orderForm.orderValidityPeriod}</span>
                        </#if>
                        <#if ((orderForm.status == 4 || orderForm.status == 5) && !orderForm.invoiceId?exists)>
                            <span><a href="${orderForm.id}" name="-1" class="c-blue jinvoice">补开发票</a></span>
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
                            <th width="70">片型</th>
                            <th width="70">规格等级</th>
                            <th width="100">产地</th>
                            <th width="90">数量<span>（公斤）</span></th>
                        <#if user_session_biz?? && user_session_biz.type == 2>
                            <th width="80">指导价<span>（元/公斤）</span></th>
                            <th width="80">合同价<span>（元/公斤）</span></th>
                            <th width="80">合同价小计<span>（元）</span></th>
                        <#else >
                            <th width="100">单价<span>（元/公斤）</span></th>
                            <th width="130">小计<span>（元）</span></th>
                        </#if>
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
                            <td>${commodity.amount}</td>
                            <#if user_session_biz?? && user_session_biz.type == 2>
                                <td><#if commodity.guidePrice??>¥${commodity.guidePrice}</#if></td>
                            </#if>
                            <td><#if commodity.price??>¥${commodity.price}</#if></td>
                            <td><#if commodity.subtotal??>¥${commodity.subtotal}</#if></td>
                            <#if commodity_index == 0>
                                <td rowspan="${orderForm.commodities?size}"><span class="c-red">${orderForm.statusText}</span></td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

    <!-- start 新增发票 -->
    <div class="fa-form fa-form-layer" id="jinvoiceBox">
        <form action="" id="invoiceForm">
            <div class="group">
                <div class="txt">
                    <span>发票类型：</span>
                </div>
                <div class="cnt">
                    <label><input type="radio" name="type" value="1" class="cbx" data-text="普通发票">普通发票</label>
                    <label><input type="radio" name="type" value="2" class="cbx" id="tax" data-text="增值税专用发票">增值税专用发票</label>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i>*</i>
                    <span>发票抬头：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="name" class="ipt" autocomplete="off" placeholder="请填写发票抬头">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>纳税人识别号：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="identifier" class="ipt" autocomplete="off" placeholder="15，17，18或20位纳税人识别号">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>注册地址：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="registeredAddress" class="ipt" autocomplete="off" placeholder="请填写注册地址">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>注册电话：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="registeredTel" class="ipt" autocomplete="off" placeholder="请填写注册电话">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>开户银行：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="bankName" class="ipt" autocomplete="off" placeholder="请填写开户银行">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>银行帐号：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="bankAccount" class="ipt" autocomplete="off" placeholder="请填写银行帐号">
                </div>
            </div>

            <div class="button">
                <button type="submit" class="btn btn-red submit">保存</button>
                <button type="reset" class="btn btn-gray cancel">取消</button>
            </div>
        </form>
    </div><!-- end 新增发票 -->
    
<#include "./inc/footer.ftl"/>

<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
<script src="js/validator/jquery.validator.js?local=zh-CN"></script>
<script src="${urls.getForLookupPath('/js/jquery.form.js')}"></script>
<script src="${urls.getForLookupPath('/js/jquery_util.js')}"></script>
<script>
    $(function () {
        $("a.status").on("click", function () {
            var id = $(this).attr("href");
            var status = $(this).attr("name");
            var text = "";
            if (status == 5) {
                changeOrderStatus(id, status);
            } else {
                if (status == 6) {
                    text = '您确认吗？订单将取消'
                }
                if (status == 7) {
                    text = '您确认吗？订单将删除'
                }
                layer.confirm(text, {icon: 3, title: '提示'}, function (index) {
                    changeOrderStatus(id, status);
                });
            }

            function changeOrderStatus(id, status) {
                $.post("/center/order/status", {orderId: id, status: status}, function (data) {
                    if (data.status == "y") {
                        if (status != 7) {
                            window.location.reload();
                        } else {
                            window.location.href = "/center/order/list";
                        }

                    }
                }, "json")
            }

            return false;
        })

        var currentOrderId = null;
        $("a.jinvoice").on("click", function () {
            $invoiceBox.find('.cbx:eq(0)').prop('checked', true);
            $invoiceBox.find('.hide').hide();
            currentOrderId = $(this).attr("href");
            layer.open({
                area: ['540px'],
                closeBtn: 1,
                type: 1,
                moveType: 1,
                content: $invoiceBox,
                title: '新增发票'
            });
            return false;
        })

        var $invoiceBox = $('#jinvoiceBox');

        var closeLayer = function () {
            layer.closeAll();
            $('#invoiceForm').get(0).reset(); // 重置表单
        }

        // 关闭弹层
        $invoiceBox.on('click', '.cancel', function () {
            closeLayer();
        })
        $invoiceBox.find('.cbx').on('click', function () {
            if (this.id === 'tax') {
                $invoiceBox.find('.hide').show();
            } else {
                $invoiceBox.find('.hide').hide();
            }
        });

        $('#invoiceForm').validator({
            focusCleanup: true,
            stopOnError: true,
            rules: {
                isTax: function () {
                    return $('#tax').prop('checked');
                },
                phone: function (val) {
                    return this.test(val, "mobile") === true ||
                            this.test(val, "tel") === true ||
                            '请填写有效的电话号码或手机号码';
                }
            },
            fields: {
                "type": '发票类型: checked',
                "name": '发票抬头: required',
                "identifier": '纳税人识别号: required(isTax)',
                "registeredAddress": '注册地址: required(isTax)',
                "registeredTel": '注册电话: required(isTax); phone',
                "bankName": '开户银行: required(isTax)',
                "bankAccount": '银行帐号: required(isTax); bankNumber'
            },
            valid: function (form) {
                if ($(form).isValid()) {
                    // 提交表单
                    var data = $("#invoiceForm").serializeObject();
                    data.orderId = currentOrderId;
                    $.post("/center/order/invoice", data, function (data) {
                        if (data.status == "y") {
                            window.location.reload();
                        }
                    }, "json")
                    closeLayer();
                }
                return false;
            }
        })
    })
</script>
</body>
</html>