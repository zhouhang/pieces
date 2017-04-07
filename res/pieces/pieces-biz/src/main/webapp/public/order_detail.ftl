<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>订单列表-${baseSetting.title!}</title>
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
                <h3>订单号：${orderForm.code!}</h3>
                <div class="extra"></div>
            </div>

            <div class="order-detail">
                <div class="guide">
                    <ul>

                        <li class="fore <#if [1,2,3,4,5,8,6]?seq_contains(orderForm.status)>curr</#if>">
                            <i class="fa fa-xiadan01"></i><em>下单</em>
                            <span><#if orderForm.createrTime??>${orderForm.createrTime?datetime}</#if></span>
                        </li>
                        <li class="<#if [1,2,3,4,5,8,6]?seq_contains(orderForm.status)>curr</#if>"><i class="fa fa-fukuan"></i>
                        <#if (orderForm.status == 2)>
                            <em>付款待确认</em>
                        </#if>
                        <#if user_session_biz?? && user_session_biz.type == 2>
                            <#if (orderForm.status == 1)>
                                <a href="/center/pay/go/${orderForm.id}" class="btn btn-red">支付保证金</a>
                            </#if>
                        <#else >
                            <#if (orderForm.status == 1)>
                                <a href="/center/pay/go/${orderForm.id}" class="btn btn-red">付款</a>
                            </#if>
                        </#if>
                        <#if (orderForm.status &lt;= 2 || orderForm.status == 8)>
                            <span><a href="${orderForm.id}" name="6" class="c-blue jremove status">取消订单</a></span>
                        </#if>
                        <#if (orderForm.status == 6)>
                            <em>已取消</em>
                            <span><a href="${orderForm.id}" name="7" class="btn btn-red jremove status">删除订单</a></span>
                        </#if>
                        <#if [3,4,5,8]?seq_contains(orderForm.status)>
                            <em>付款成功</em>
                        </#if>
                        </li>
                        <li class="<#if [3,4,5,8]?seq_contains(orderForm.status)>curr</#if>">
                            <i class="fa fa-chuku"></i>
                            <#if (orderForm.status == 3)>
                                <em>等待发货</em>
                            </#if>
                            <#if [4,5,8]?seq_contains(orderForm.status)>
                                <em>商品出库</em>
                                <#if orderForm.deliveryDate?exists><span>${orderForm.deliveryDate?datetime}</span></#if>
                            </#if>
                        <#if ![3,4,5,8]?seq_contains(orderForm.status)>
                            <em>商品出库</em>
                        </#if>
                        </li>
                        <li class="<#if [4,5,8]?seq_contains(orderForm.status)>curr</#if>">
                            <i class="fa fa-truck"></i>
                        <#if (orderForm.status == 4)>
                            <a href="${orderForm.id}" name="5" class="btn btn-red status">确认收货</a>
                        </#if>
                        <#if [5]?seq_contains(orderForm.status)>
                            <em>确认收货</em>
                            <#if orderForm.finishDate?exists><span>${orderForm.finishDate?datetime}</span></#if>
                        </#if>
                        <#if ![4,5,8]?seq_contains(orderForm.status)>
                            <em>确认收货</em>
                        </#if>

                        </li>
                        <li class="<#if [5,8]?seq_contains(orderForm.status)>curr</#if>">
                            <i class="fa fa-success"></i>
                        <#if (orderForm.status == 5)>
                            <em>完成</em>
                            <#if orderForm.finishDate?exists><span>${orderForm.finishDate?datetime}</span></#if>
                        </#if>
                        <#if ![5,8]?seq_contains(orderForm.status)>
                            <em>确认收货</em>
                        </#if>
                        </li>
                    </ul>
                </div>

                <div class="info">
                    <dl>
                        <dt>收货人信息</dt>
                        <dd>
                            <em>收货人：</em>
                            <span>${orderForm.address.consignee}</span>
                        </dd>
                        <dd>
                            <em>联系方式：</em>
                            <span>${orderForm.address.tel}</span>
                        </dd>
                        <dd>
                            <em>收货地址：</em>
                            <span>${orderForm.address.area}${orderForm.address.detail}</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt>付款信息</dt>
                    <#if payRecord?exists>
                        <dd>
                            <em>付款方式：</em>
                            <span>${payRecord.payTypeName}</span>
                        </dd>
                        <dd>
                            <em>付款时间：</em>
                            <span>${payRecord.paymentTime?datetime}</span>
                        </dd>
                        <dd>
                            <em>订单金额：</em>
                            <span>¥${payRecord.amountsPayable}</span>
                        </dd>
                        <#if payRecord.agentId?exists>
                            <dd>
                                <em>保证金：</em>
                                <span>¥${payRecord.deposit!}</span>
                            </dd>
                        <#else >
                            <dd>
                                <em>需付金额：</em>
                                <span>¥${payRecord.actualPayment?default(payRecord.deposit!)}</span>
                            </dd>
                        </#if>

                    </#if>
                    <#if accountBill?exists>
                        <dd>
                            <em>付款方式：</em>
                            <span>账期</span>
                        </dd>
                        <dd>
                            <em>还款时间：</em>
                            <span>${accountBill.repayTime?date}</span>
                        </dd>
                        <dd>
                            <em>应付金额：</em>
                            <span>¥${accountBill.amountsPayable!}</span>
                        </dd>
                        <dd>
                            <em>已付金额：</em>
                            <span>¥${accountBill.alreadyPayable!}</span>
                        </dd>
                    </#if>
                    <#if (orderForm.status == 1)>
                        <span>未付款</span>
                        <span>剩余付款时间</span>
                        <span>${orderForm.orderValidityPeriod}</span>
                    </#if>
                    <#if (orderForm.status == 2)>
                        <span>付款待确认</span>
                    </#if>
                    <#if (orderForm.status == 6 && !accountBill?exists && !payRecord?exists)>
                        <span>未付款</span>
                    </#if>


                    </dl>
                    <dl>
                        <dt>发票信息</dt>
                        <#if orderForm.invoice?exists>
                        <dd>
                            <em>发票类型：</em>
                            <span>${orderForm.invoice.typeText!}</span>
                        </dd>
                        <dd>
                            <em>发票抬头：</em>
                            <span>${orderForm.invoice.name!}</span>
                        </dd>
                        <dd>
                            <em>发票内容：</em>
                            <span>${orderForm.invoice.content!}</span>
                        </dd>
                        <#else >

                                <#--<#if ((orderForm.status == 4 || orderForm.status == 5) && !orderForm.invoiceId?exists)>-->
                                    <span>暂无发票</span><br>
                            <#if [1,2,3,4,5]?seq_contains(orderForm.status)>
                                    <span><a href="${orderForm.id}" name="-1" class="c-blue jinvoice">补开发票</a></span>
                            </#if>
                            <#--</#if>-->
                        </#if>
                    </dl>
                <#if logistical?exists>
                    <#--配送方式 1快递 2自提 3货运部发货-->
                    <dl class="row wide">
                        <dt>物流信息</dt>
                                <#if logistical.type = 1>
                                    <dd>
                                        <em>配送方式：</em>
                                        <span>快递</span>
                                    </dd>
                                    <dd>
                                        <em>快递单号：</em>
                                        <span>${logistical.code!}</span>
                                    </dd>
                                    <dd>
                                        <em>快递公司：</em>
                                        <span>${logistical.companyCodeName!} </span>
                                    </dd>
                                    <dd class="express_box">
                                        <ul>
                                            <#list logisticalTraceVos as trace>
                                                <li>
                                                    <div class="date">${trace.acceptTime?datetime}</div>
                                                    <div class="trace">${trace.acceptStation}</div>
                                                </li>
                                            </#list>
                                        </ul>
                                    </dd>
                                <#elseif logistical.type = 2>
                                    <dd>
                                        <em>配送方式：</em>
                                        <span>自提</span>
                                    </dd>
                                    <dd>
                                        <em>提货时间：</em>
                                        <span>${logistical.receivingDate?date}</span>
                                    </dd>
                                    <dd>
                                        <em>提货地点：</em>
                                        <span>${logistical.pickUp}</span>
                                    </dd>
                                <#elseif logistical.type = 3>
                                    <dd>
                                        <em>配送方式：</em>
                                        <span>货运部发货</span>
                                    </dd>
                                    <dd>
                                        <em>预计到货时间：</em>
                                        <span>${logistical.receivingDate?date}</span>
                                    </dd>
                                    <dd>
                                        <em>司机姓名：</em>
                                        <span>${logistical.driverName!}</span>
                                    </dd>
                                    <dd>
                                        <em>联系电话：</em>
                                        <span>${logistical.driverTel!}</span>
                                    </dd>
                                </#if>
                        </dl>
                </#if>
                </div>

                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th width="30"></th>
                            <th width="100"></th>
                            <th>商品信息</th>
                        <#if user_session_biz?? && user_session_biz.type == 2>
                            <th width="130">指导价<em>（元/公斤）</em></th>
                            <th width="130">开票价<em>（元/公斤）</em></th>
                        <#else >
                            <th width="130">单价<em>（元/公斤）</em></th>
                        </#if>
                            <th width="100">数量<em>（公斤）</em></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderForm.commodities as commodity>
                        <tr>
                            <td></td>
                            <td>
                                <div class="pic">
                                    <a href="/commodity/${commodity.commodityId}" target="_blank"><img style="width: 80px; height: 80px;" src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>" alt=""></a>
                                </div>
                            </td>
                            <td>
                                <div class="name">
                                    <a href="/commodity/${commodity.commodityId}" target="_blank">${commodity.name}${commodity.spec}${commodity.level}</a>
                                </div>
                            </td>
                            <#if user_session_biz?? && user_session_biz.type == 2>
                                <td><#if commodity.guidePrice??>${(commodity.guidePrice?default(0))?string .currency}</#if></td>
                            </#if>
                            <td><#if commodity.price??>${(commodity.price?default(0))?string .currency}</#if></td>
                            <td>${commodity.amount}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="summary">
                <#if user_session_biz?? && user_session_biz.type == 2>
                    <div class="row">
                        <label>订单总额：</label><span>${(orderForm.amountsPayable?default(0))?string .currency}</span>
                    </div>
                    <div class="row bold">
                        <label>需付保证金：</label><span>${(orderForm.deposit?default(0))?string .currency}</span>
                    </div>
                <#else >
                    <div class="row bold">
                        <label>订单总额：</label><span>${(orderForm.amountsPayable?default(0))?string .currency}</span>
                    </div>
                </#if>
                </div>
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