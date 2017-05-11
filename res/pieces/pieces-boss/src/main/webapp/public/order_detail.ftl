<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>订单详情-boss-上工好药</title>
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
                        <a class="btn btn-gray" href="order/index">返回</a>
                    <@shiro.hasPermission name="order:edit">
                        <#if vo.status != 7>
                        <a id="editerOrder" class="btn btn-gray" href="javascript:;">修改</a>
                        </#if>
                    </@shiro.hasPermission>
                    <#if vo.status == 3 || vo.status == 8>
                        <a id="jexpress" type="button" class="btn btn-gray" href="javascript:;">配送</a>
                    </#if>
                    <a id="exportOrder" type="button" class="btn btn-gray" href="/order/download/${vo.id}">导出订单</a>
                    <#if vo.status == 4>
                        <a id="deliveryFail" type="button" class="btn btn-gray" href="javascript:;">配送失败</a>
                    </#if>
                    <@shiro.hasPermission name="order:edit">
                        <a class="btn btn-red" href="/order/anew/${vo.id!}">重新下单</a>
                    </@shiro.hasPermission>
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
                            <dt>配送信息</dt>
                            <dd>
                                <p>收&nbsp;&nbsp;货&nbsp;&nbsp;人：${vo.address.consignee}</p>
                                <p>联系电话：${vo.address.tel}</p>
                                <p>收货地址：${vo.address.area}${vo.address.detail}</p>
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
                                <#if vo.agentId?exists>
                                    <p>代&nbsp;&nbsp;理&nbsp;&nbsp;商：${vo.agentName}</p>
                                    <p>联系电话：${vo.agentTel}</p>
                                </#if>
                            </dd>
                        </dl>
                    </div>

                    <#if vo.invoice?exists>
                        <div class="group">
                            <dl>
                                <dt>发票信息</dt>
                                <dd>
                                    <p>发票类型：${vo.invoice.typeText}</p>
                                    <p>发票抬头：${vo.invoice.name}</p>
                                    <p>纳税人识别号：${vo.invoice.identifier}</p>
                                    <p>注册地址：${vo.invoice.registeredAddress}</p>
                                    <p>注册电话：${vo.invoice.registeredTel}</p>
                                    <p>开户银行：${vo.invoice.bankName}</p>
                                    <p>银行账号：${vo.invoice.bankAccount}</p>
                                </dd>
                            </dl>
                        </div>
                    </#if>
                <#if logistical?exists>
                    <div class="group group-row">
                    <#--配送方式 1快递 2自提 3货运部发货-->
                        <dl>
                            <dt>物流信息</dt>
                            <dd>
                                <#if logistical.type = 1>
                                    <p>配送方式：快递</p>
                                    <p>快递公司：${logistical.companyCodeName!}</p>
                                    <p>快递单号：${logistical.code!} </p>

                                <ul class="express_box">
                                <li class="company">
                                <em>${logisticalcompanyCodeName}</em>
                                </li>
                                    <#list logisticalTraceVos as trace>
                                    <li>
                                    <div class="date">${trace.acceptTime?datetime}</div>
                                    <div class="trace">${trace.acceptStation}</div>
                                    </li>
                                    </#list>
                                </ul>
                                <#elseif logistical.type = 2>
                                    <p>配送方式：自提</p>
                                    <p>提货时间：${logistical.receivingDate?date} </p>
                                    <p>提货地点：${logistical.pickUp}</p>
                                <#elseif logistical.type = 3>
                                    <p>配送方式：货运部发货</p>
                                    <p>预计到货时间：${logistical.receivingDate?date}</p>
                                    <p>司机姓名：${logistical.driverName!}</p>
                                    <p>联系电话：${logistical.driverTel!}</p>
                                </#if>
                            </dd>
                        </dl>
                    </div>
                </#if>
                </div>
                <div class="chart-info">
                    <h3>订购商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th width="100">商品名称</th>
                                <th width="80">片型</th>
                                <th>规格等级</th>
                                <th width="110">产地</th>
                                <th>数量（公斤）</th>
                                <th>销售价（元/公斤）</th>
                                <th>开票价（元/公斤）</th>
                                <th>开票价小计（元）</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="9">
                                    <div class="summary">
                                        <div class="item">
                                            <span>商品合计：</span>
                                            <em>&yen; ${vo.sum}</em>
                                        </div>
                                        <div class="item">
                                            <span>实际应付：</span>
                                            <em class="price">&yen; ${vo.amountsPayable}</em>
                                        </div>
                                    <#if vo.agentId?exists>
                                        <div class="item">
                                            <span>需支付保证金：</span>
                                            <em class="price"><#if vo??>&yen; ${vo.deposit!}</#if></em>
                                        </div>
                                    </#if>
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
                                <td>${commoditie.amount}</td>
                                <td>${commoditie.guidePrice!}</td>
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
    <!-- 发货 -->
    <form id="myform" class="hide">
        <div class="fa-form fa-form-layer">
            <div class="group">
                <div class="txt"><i>*</i>配送方式：</div>
                <div class="cnt cbxs">
                    <label><input type="radio" name="type" value="1" id="way1" class="cbx" checked>快递</label>
                    <label><input type="radio" name="type" value="2" id="way2" class="cbx">自提</label>
                    <label><input type="radio" name="type" value="3" id="way3" class="cbx">货运部发货</label>
                </div>
            </div>

            <div class="way" id="_way1">
                <div class="group">
                    <div class="txt"><i>*</i>快递公司：</div>
                    <div class="cnt">
                        <select name="companyCode" style="width:420px;">
                            <option value="">请选择</option>
                            <option value="SF">顺丰快递</option>
                            <option value="YTO">圆通快递</option>
                            <option value="ZTO">中通快递</option>
                            <option value="STO">申通快递</option>
                            <option value="YD">韵达快递</option>
                            <option value="HTKY">百世汇通</option>
                        </select>
                    </div>
                </div>
                <div class="group">
                    <div class="txt"><i>*</i>快递单号：</div>
                    <div class="cnt">
                        <input type="text" name="code" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
            </div>

            <div class="way hide" id="_way2">
                <div class="group">
                    <div class="txt"><i>*</i>提货时间：</div>
                    <div class="cnt">
                        <input type="text" name="expressDate" id="expressDate" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="group">
                    <div class="txt"><i>*</i>提货地点：</div>
                    <div class="cnt">
                        <input type="text" name="pickUp" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
            </div>

            <div class="way hide" id="_way3">
                <div class="group">
                    <div class="txt"><i>*</i>预计到货时间：</div>
                    <div class="cnt">
                        <input type="text" name="expressExpected" id="expressExpected" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="group">
                    <div class="txt"><i>*</i>司机姓名：</div>
                    <div class="cnt">
                        <input type="text" name="driverName" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
                <div class="group">
                    <div class="txt"><i>*</i>联系电话：</div>
                    <div class="cnt">
                        <input type="text" name="driverTel" class="ipt" placeholder="" autocomplete="off">
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
    <script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
    <script src="/js/validator/jquery.validator.min.js?local=zh-CN"></script>
<script>
    var _globle = {
        v: {
            orderId:${vo.id}
        },
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
                // 配送失败按钮
                $("#deliveryFail").click(function () {
                    layer.confirm('您确认吗？货物配送失败', {icon: 3, title: '提示'}, function (index) {
                        layer.close(index);
                        $.post("/order/status",{status:8,orderId:${vo.id}}, function (data) {
                            if (data.status == "y") {
                                $.notify({
                                    type: 'success',
                                    title: '订单状态修为配送失败.',
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
                this.deliver();
            },
            // 发货
            deliver: function() {
                var $myform = $('#myform');

                $myform.validator({
                    fields: {
                        type: '派送方式: checked(1)',
                        companyCode: '快递公司: required(#way1:checked)',
                        code: '快递单号: required(#way1:checked)',

                        expressDate: '提货时间: required(#way2:checked)',
                        pickUp: '提货地点: required(#way2:checked)',

                        expressExpected: '预计到货时间: required(#way3:checked)',
                        driverName: '司机姓名: required(#way3:checked)',
                        driverTel: '联系电话: required(#way3:checked); mobile'
                    },
                    valid: function (form) {
                        var  lock = false;
                        var logistical = $(form).serializeObject();
                        logistical.orderId = _globle.v.orderId;
                        // receivingDate
                        if (logistical.type==1) {
                            // noting
                        } else if (logistical.type==2) {
                            logistical.receivingDate = logistical.expressDate;
                        } else if(logistical.type==3) {
                            logistical.receivingDate = logistical.expressExpected;
                        }
                        if (lock) return false;
                        lock = true;
                        $.post("/logistics/create", logistical, function (data) {
                            if (data.status == "y") {
                                $.notify({
                                    type: 'success',
                                    title: '发货信息以保存,订单状态修为已发货.',
                                    delay: 3e3,
                                    call: function () {
                                        window.location.reload();
                                    }
                                });
                            }
                        })
                        return false;
                    }
                });

                // 配送方式
                $myform.on('click', '.cbx', function() {
                    $('#_' + this.id).show().siblings('.way').hide();
                })

                // 提货时间
                laydate({
                    elem: '#expressDate',
                    min: laydate.now(),
                    choose: function(datas){
                        $('#expressDate').trigger('validate');
                    }
                });

                // 预计到货时间
                laydate({
                    elem: '#expressExpected',
                    min: laydate.now(),
                    choose: function(datas){
                        $('#expressExpected').trigger('validate');
                    }
                });

                $('#jexpress').on('click', function() {
                    $myform[0].reset();
                    layer.open({
                        area: ['600px'],
                        type: 1,
                        moveType: 1,
                        content: $myform,
                        btn: ['确定', '取消'],
                        btn1: function() {
                            $myform.submit();
                        },
                        title: '物流信息'
                    });
                    $myform.parent().height('auto')
                })

                <#--$("#delivery").click(function () {-->
                    <#--layer.confirm('您确认吗？订单已经发货', {icon: 3, title: '提示'}, function (index) {-->
                        <#--layer.close(index);-->
                        <#--$.post("/order/status",{status:4,orderId:${vo.id}}, function (data) {-->
                            <#--if (data.status == "y") {-->
                                <#--$.notify({-->
                                    <#--type: 'success',-->
                                    <#--title: '订单状态修为已发货.',-->
                                    <#--delay: 3e3,-->
                                    <#--call: function () {-->
                                        <#--window.location.reload();-->
                                    <#--}-->
                                <#--});-->
                            <#--}-->
                        <#--})-->
                    <#--});-->
                    <#--return false-->
                <#--});-->
            }
        }
    }
    $(function() {
        _globle.fn.init();
    })
</script>
</body>
</html>