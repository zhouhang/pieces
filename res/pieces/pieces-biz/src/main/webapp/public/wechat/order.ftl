<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>下单-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="order">
        <#if user.type==2>
        <div class="item">
            <input type="hidden" name="agentId" id="agentId" value="">
            <span class="error"></span>
            <strong class="t1" id="agentName">选择终端客户</strong>
            <a href="/h5c/order/agent?omd5=${omd5!}" class="arrow"></a>
        </div>
        </#if>
        <div class="item" id="address">
            <#if shippingAddress?exists>
            <strong class="t1">${shippingAddress.consignee}  ${shippingAddress.tel}</strong>
            <span class="t2">${shippingAddress.fullAdd}${shippingAddress.detail}</span>
            <input type="hidden" name="addrHistoryId" id="addrHistoryId" value="${shippingAddress.id!}">
            <span class="error"></span>
            <a href="/h5c/order/address?omd5=${omd5!}" class="arrow"></a>
            </#if>
        </div>
        <div class="goods">
            <ul>
                <#list enquiryCommoditys as commodity>
                <li>
                    <div class="hd"><a href="/h5/commodity/${commodity.commodityId}">${commodity.commodityName!}</a></div>
                    <div class="bd">${commodity.specs!}${commodity.level!}</div>
                    <div class="price">
                        <span>销售价:<em>${(commodity.myPrice?default(0))?string.currency}</em></span>
                        <#if user?exists && user.type == 2>
                            <span>开票价:${(commodity.price?default(commodity.myPrice?default(0)))?string.currency}</span>
                        </#if>
                    </div>
                    <div class="pic rs-pic">
                        <a href="/h5/commodity/${commodity.commodityId!}"><img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>"/></a>
                    </div>
                    <div class="num">
                        <div class="fr">
                            <input type="text" name="number" id="number" class="ipt" value="1" data-id="${commodity.id}" data-min="${(commodity.myPrice?default(0))?c}" data-max="${(commodity.price?default(commodity.myPrice?default(0)))?c}">公斤
                            <span class="error"></span>
                        </div>
                        <div>数量：</div>
                    </div>
                </li>
                </#list>
            </ul>
        </div>
        <div class="item note">
            订单备注
            <textarea name="remark"  id="remark" cols="30" rows="10" class="ipt mul"></textarea>
            <span class="error"></span>
        </div>
        <div class="item" id="invoice">
            <strong class="t1">选择发票</strong>
            <input type="hidden" name="invoiceId" id="invoiceId" value="">
            <span class="error"></span>
            <a href="/h5c/order/invoice?omd5=${omd5}" class="arrow"></a>
        </div>

        <div class="summary">
            <div class="money" style="<#if user.type==1>display:none;</#if>">
                <em class="fr" id="_max"></em>开票金额
            </div>
            <div class="li">
<#if user.type==1>订单金额<#else >需支付保证金</#if>：<em id="_min"></em>
            </div>
        </div>
    </div>
    <div class="ui-button mt20">
        <button type="button" id="submit" class="ubtn ubtn-red">确认订购</button>
    </div>

</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                <#if (!agentUser?exists || agentUser?size==0)&&user.type == 2>
                    this.noCustomer();
                <#elseif !shippingAddress?exists>
                    this.noConsignee();
                </#if>
                this.bindEvent();
                this.calc();
                this.initLocalstorage();
            },
            initLocalstorage: function() {
                var invoice = _YYY.localstorage.get('invoice_${omd5!}');
                var agent = _YYY.localstorage.get('agent_${omd5!}');
                var address = _YYY.localstorage.get('address_${omd5!}');

                if (agent) {
                    agent = JSON.parse(agent);
                    $("#agentId").val(agent.id);
                    $("#agentName").html(agent.name);
                }

                if (address) {
                    address = JSON.parse(address);
                    $("#address .t1").html(address.tel);
                    $("#address .t2").html(address.detail);
                    $("#addrHistoryId").val(address.id);
                }

                if (invoice) {
                    invoice = JSON.parse(invoice);

                    $("#invoice .t1").html(invoice.name);
                    if (invoice.type==0) {
                        $("#invoiceId").val(null);
                    } else {
                        $("#invoiceId").val(invoice.type);
                    }
                }

                $(window).bind('beforeunload',function(){
                    _YYY.localstorage.set('remark_${omd5!}',$("#remark").val());
                });

                var remark = _YYY.localstorage.get('remark_${omd5!}');
                if (remark) {
                    $("#remark").val(remark);
                }

            },
            cleanLocalstorage:function() {
                _YYY.localstorage.remove('order_${omd5!}');
                _YYY.localstorage.remove('invoice_${omd5!}');
                _YYY.localstorage.remove('agent_${omd5!}');
                _YYY.localstorage.remove('address_${omd5!}');
                _YYY.localstorage.remove('remark_${omd5!}');
            },
            noCustomer: function() {
                layer.open({
                    className: 'layer-custom',
                    content: '<div class="bd"><p>您还没有绑定终端客户，暂时无法进行下单。如果您已提交终端客户资料，请联系客服人员帮您进行绑定。</p><p><a href="tel:客服电话：0558-5120088" class="c-blue">客服电话：0558-5120088。</a></p></div>',
                    btn: ['确定']
                })
            },
            noConsignee: function() {
                layer.open({
                    className: 'layer-custom',
                    content: '<div class="bd">您还没有收货地址，是否立即新建一个？</div>',
                    btn: ['确定', '取消'],
                    yes: function() {
                        window.location.href = '/h5c/address/edit?omd5=${omd5!}';
                    }
                })
            },
            bindEvent: function() {
                var that = this,
                        enable = true;

                // 确认订购
                $('#submit').on('click', function() {
                    if(enable && that.check()){
                        var commodityses=[];
                        var $ipts = $('.goods').find('.ipt');
                            $ipts.each(function() {
                                commodityses.push({id:$(this).data("id"),amount:this.value});
                            })
                        var param={};
                        param.commodityses=commodityses;
                        if ($("#invoiceId").val()){
                            param.invoice = {type:$("#invoiceId").val()};
                        }
                        param.addrHistoryId = $("#addrHistoryId").val();
                        param.remark = $("#remark").val();

                        <#if user.type==2>
                        param.agentId = $("#agentId").val();
                        </#if>

                        $.ajax({
                            url: '/h5c/order/save',
                            type : 'POST',
                            data: JSON.stringify(param),
                            contentType : 'application/json',
                            success: function(result) {
                                if (result.status == "y") {
                                    that.cleanLocalstorage();
                                    window.location.href = "/h5c/order/success?orderId="+result.data;
                                }
                            },
                            complete: function() {
                                enable = true;
                            }
                        })
                    }
                    enable = false;
                })
            },
            check: function() {
                return this.checkCustomer()
                        && this.checkConsignee()
                        && this.checkNumber()
                        && this.checkInvoice()
            },
            checkCustomer: function() {
                <#if user.type==2>
                var $el = $('#agentId'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请选择终端客户！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                window.scrollTo(0, 0);
                return false;
                <#else >
                return true;
                </#if>
            },
            checkConsignee: function() {
                var $el = $('#addrHistoryId'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请选择收货地址！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                window.scrollTo(0, 60);
                return false;
            },
            checkNumber: function() {
                var $el = $('#number'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请输入数量！').show();

                } else if (!/^\d*$/.test(val)) {
                    $el.next().html('数量只能输入正整数！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkInvoice: function() {
                var $el = $('#invoiceId'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请选择发票！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            // 计算价格
            calc: function() {
                var $ipts = $('.goods').find('.ipt');

                var totla = function() {
                    var max = 0,
                            min = 0,
                            num = 0;

                    $ipts.each(function() {
                        num = parseInt(this.value, 10);
                        min += parseFloat($(this).data('min')) * num;
                        max += parseFloat($(this).data('max')) * num;
                    })
                    $('#_min').html('&yen;' + min.toFixed(2));
                    $('#_max').html('&yen;' + max.toFixed(2));
                }
                totla();
                $('.goods').on('blur', '.ipt', function() {
                    var val = this.value;
                    if (val) {
                        val = (!isNaN(val = parseInt(val, 10)) && val) > 0 ? val : 1;
                    }
                    this.value = val;
                    totla();
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>