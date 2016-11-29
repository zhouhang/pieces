<!DOCTYPE html>
<html lang="en">
<head>
    <title>订单管理-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="create-order">
    <div class="wrap">
        <div class="title">
            <h3><i class="fa fa-chevron-right"></i>为${user.userName!}${order_type!}</h3>
            <div class="extra">
                <a class="btn btn-gray" href="/order/index">取消</a>
                <a class="btn btn-red" id="submitOrder2" href="javascript:;">提交订单</a>
            </div>
        </div>

        <div class="side">
        <#if billsPage.list?has_content>
            <div class="item">
                <div class="hd">
                    <button class="btn">添加</button>
                    <span>${user.userName!}的询价单</span>
                </div>
                    <div class="bd">
                        <div class="th">
                            <span class="w1">商品</span>
                            <span class="w2">片型</span>
                            <span class="w3">规格等级</span>
                            <span class="w4">产地</span>
                        </div>
                        <#list billsPage.list as bill>
                            <dl>
                                <dt>
                                    <em>${bill.code!}</em>
                                </dt>
                                <#list bill.enquiryCommoditys as commodity>
                                    <dd>
                                        <span class="w1">${commodity.commodityName!}</span>
                                        <span class="w2">${commodity.specs!}</span>
                                        <span class="w3">${commodity.level!}</span>
                                        <span class="w4">${commodity.origin!}</span>
                                        <#if commodity.myPrice??&&commodity.expireDate??&&(commodity.expireDate?date>.now?date)>
                                        <label><input type="checkbox"></label>
                                        </#if>
                                    </dd>
                                </#list>
                            </dl>
                        </#list>
                    </div>
            </div>
        </#if>
        <#if orderFormVo??>
            <div class="item">
                <div class="hd">
                    <button class="btn">添加</button>
                    <span>上次订购的商品</span>
                </div>

                <div class="bd">
                    <div class="th">
                        <span class="w1">商品</span>
                        <span class="w2">片型</span>
                        <span class="w3">规格等级</span>
                        <span class="w4">产地</span>
                    </div>
                    <dl>
                        <#list orderFormVo.commodityVos as commodity>
                            <dd>
                                <span class="w1">${commodity.name!}</span>
                                <span class="w2">${commodity.spec!}</span>
                                <span class="w3">${commodity.level!}</span>
                                <span class="w4">${commodity.originOf!}</span>
                                <label><input type="checkbox"></label>
                            </dd>
                        </#list>
                    </dl>
                </div>
            </div>
        </#if>


        </div>

        <div class="main">
            <form action="" id="myform">

            <div class="chart-info">
                <h3>订购商品  <button type="button"class="btn btn-gray" id="importExcel">导入报价</button></h3>
                <div class="chart">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th>片型</th>
                                <th>规格等级</th>
                                <th width="100">产地</th>
                                <th width="90">数量（公斤）</th>
                                <th width="90">指导价（元/公斤）</th>
                                <th width="100">合同价（元/公斤）</th>
                                <th width="120">合同价小计（元）</th>
                                <th width="120">操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="8" class="tl">共 <#if commodityVos??&&commodityVos?has_content>${commodityVos?size}<#else>0</#if> 件商品</td>
                                <td colspan="2" class="tl">总计：<b class="jsum"></b></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <#if commodityVos??&&commodityVos?has_content>
                                <#list commodityVos as commodity>
                                    <tr>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.name!}" class="ipt ipt-name" name="name"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.spec!}" class="ipt" name="spec"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.level!}" class="ipt" name="level"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.originOf!}" class="ipt" name="originOf"><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt amount" name="amount" value="${commodity.amount!}"><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice" value="${commodity.guidePrice!}"><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt price" name="price" value="${commodity.price!}"><span class="error"></span></div></td>
                                        <td class="jtotal"></td>
                                        <td>
                                            <a href="javascript:;" class="add">添加</a>
                                            <#if (commodityVos?size>1)>
                                                <a href="javascript:;" class="remove c-red">删除</a>
                                            </#if>
                                        </td>
                                    </tr>
                                </#list>
                                <#else>
                                    <tr>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt ipt-name" name="name"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="spec"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="level"><span class="error"></span></div></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="originOf"><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt amount" name="amount" ><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice"><span class="error"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt price" name="price" ><span class="error"></span></div></td>
                                        <td class="jtotal"></td>
                                        <td>
                                            <a href="javascript:;" class="add">添加</a>
                                        </td>
                                    </tr>
                            </#if>

                            </tbody>
                        </table>
                </div>
            </div>

            <div class="chart-info">
                <h3>配送地址</h3>
                <div class="fa-form">
                    <div class="choose">
                        <label>从已有客户地址中选择：</label>
                        <select name="" id="order_address">
                            <option value="">添加新地址</option>
                            <#if shippingAddressList??>
                                <#list shippingAddressList as shippingAddress>
                                    <option value="${shippingAddress.id!}" data-area="${shippingAddress.area.provinceId},${shippingAddress.area.cityId},${shippingAddress.area.id}" data-val="${shippingAddress.consignee!},${shippingAddress.tel!},${shippingAddress.detail!}" >${shippingAddress.consignee!}${shippingAddress.fullAdd!}${shippingAddress.detail!}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="group">
                        <div class="txt">收&nbsp;&nbsp;货&nbsp;&nbsp;人：</div>
                        <div class="cnt">
                            <input type="text" name="consignee" class="ipt" placehoder="" autocomplete="off" value="<#if shippingAddressHistory??>${shippingAddressHistory.consignee!}</#if>">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">手机号码：</div>
                        <div class="cnt">
                            <input type="text"  name="tel" class="ipt" placehoder="" autocomplete="off" value="<#if shippingAddressHistory??>${shippingAddressHistory.tel!}</#if>">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">所在地区：</div>
                        <div class="cnt" id="pickArea">
                            <select name="province" id="province" data-value="<#if shippingAddressHistory??&&shippingAddressHistory.areaObj??>${shippingAddressHistory.areaObj.provinceId!}</#if>">
                                <option value="">-省-</option>
                            </select>
                            <select name="city" id="city" data-value="<#if shippingAddressHistory??&&shippingAddressHistory.areaObj??>${shippingAddressHistory.areaObj.cityId!}</#if>">
                                <option value="">-市-</option>
                            </select>
                            <select name="area" id="area" data-value="<#if shippingAddressHistory??&&shippingAddressHistory.areaObj??>${shippingAddressHistory.areaObj.id!}</#if>">
                                <option value="">-区/县-</option>
                            </select>
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">详细地址：</div>
                        <div class="cnt">
                            <textarea name="detail"  cols="30" rows="10" class="ipt ipt-mul"><#if shippingAddressHistory??>${shippingAddressHistory.detail!}</#if></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="chart-info">
                <h3>备注</h3>
                <div class="fa-form">
                    <div class="group">
                        <div class="txt">订单备注：</div>
                        <div class="cnt">
                            <textarea name="remark"  id="" cols="30" rows="10" placeholder="" class="ipt ipt-note"><#if origOrderForm??>${origOrderForm.remark!}</#if></textarea>
                        </div>
                    </div>
                </div>
            </div>

            <div class="chart-info">
                <input type="hidden" id="userId" name="userId" value="${user.id!}">
                <input type="hidden" id="agentId" name="agentId" value="${agentId!}">
                <input type="hidden" id="orderId" name="orderId" value="<#if order_type=='修改订单'>${origOrderForm.id!}</#if>">

                <h3>订单总额</h3>
                <div class="fa-form summary">
                    <div class="item">
                        <span>商品合计：</span>
                        <em class="jsum"><#if origOrderForm??>${origOrderForm.sum!}</#if></em>
                    </div>
                    <div class="item">
                        <span>实际应付：</span>
                        <em class="price jsum2"><#if origOrderForm??>${origOrderForm.amountsPayable!}</#if></em>
                    </div>
                    <div class="item">
                        <span>需支付保证金：</span>
                        <em class="price jsum3"><#if origOrderForm??>${origOrderForm.deposit!}</#if></em>
                    </div>
                </div>
            </div>

            <div class="submit">
                <button class="btn btn-red" id="submitOrder" type="button">提交订单</button>
            </div>

            </form>

        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->


<!-- 输入框联想 start -->
<div class="suggestions" id="suggestions">
    <div class="hd">
        <div class="group">
            <span class="w1">商品名称</span><span class="w2">片型</span><span class="w3">规格等级</span><span class="w4">产地</span>
        </div>
    </div>
    <div class="bd"></div>
</div><!-- 输入框联想 end -->

<script type="temp" id="jmodal">
        <tr>
            <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" name="name"><span class="error"></span></div></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="spec"><span class="error"></span></div></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="level"><span class="error"></span></div></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="originOf"><span class="error"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt amount" name="amount" value=""><span class="error"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice" value=""><span class="error"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt price" name="price" value=""><span class="error"></span></div></td>
            <td class="jtotal"></td>
            <td>
                <a href="javascript:;" class="add">添加</a>
                <a href="javascript:;" class="remove c-red">删除</a>
            </td>
        </tr>
    </script>

<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
<script src="js/laydate/laydate.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/area.js"></script>
<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.myformEvent();
                this.addGoodsToOrder();
                this.insertArea();
                this.batch();


                $('#myform').validator({
                    fields : {
                        consignee : '收货人: required',
                        tel : '手机号: required; ',
                        area : '所在地区: required;',
                        detail : '详细地址: required;',
                       // jfreightPrice : '运费: required;',
                    }
                });

            },
            myformEvent: function() {
                var $body        = $('body');
                var $suggestions = $('#suggestions');
                var $myform      = $('#myform');
                var $tbody       = $myform.find('tbody');
                var $ipt         = $tbody.find('.ipt:first');
                var modal        = $('#jmodal').html();
                var self         = this;

                // 计算价格
                var calcPrice = function() {
                    self.calcPrice($tbody, $tbody.find('tr:eq(0)'));
                }

                calcPrice();

                // 隐藏错误提示
                $myform.on('focus', '.ipt', function() {
                    $(this).nextAll('.error').html('').hide();
                })

                // 数量
                $myform.on({
                    'keyup': function() {
                        var val = this.value;
                        if (!/^\d*$/.test(val)) {
                            val = Math.abs(parseInt(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    },
                    'blur': function() {
                        self.calcPrice($tbody, $(this).closest('tr'));
                    }
                }, '.amount');

                // 单价
                $myform.on({
                    'keyup': function() {
                        var val = this.value;
                        if (!/^\d+\.?\d*$/.test(val)) {
                            val = Math.abs(parseFloat(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    },
                    'blur': function() {
                        self.calcPrice($tbody, $(this).closest('tr'));
                    }
                }, '.price');

                // 商品名联想
                $myform.on({
                    'click': function(event) {
                        $(this).after($suggestions);
                        // self.getKeywords(this.value); // 获取焦点时查询一次
                        event.stopPropagation();
                    },
                    'input': function() {
                        self.getKeywords(this.value);
                    }
                }, '.ipt-name');

                // 关闭联想层
                $body.on('click', function() {
                    $suggestions.hide();
                })
                $body.on('click', '.suggestions', function(event) {
                    event.stopPropagation();
                })

                // 关键字自动填充
                $body.on('click', '.suggestions .bd .group', function() {
                    var data = $(this).data('val').split('-');
                    $suggestions.prev().val(data[0])
                            .closest('td').next().find('.ipt').val(data[1]).trigger('focus').end()
                            .closest('td').next().find('.ipt').val(data[2]).trigger('focus').end()
                            .closest('td').next().find('.ipt').val(data[3]).trigger('focus');
                    $suggestions.hide();
                })

                // 新增一行
                $myform.on('click', '.add', function() {
                    $tbody.append(modal);
                    // 没有删除按钮时添加删除按钮
                    $(this).siblings().length === 0 && $(this).after(' <a class="remove c-red" href="javascript:;">删除</a>');

                })

                // 删除一行
                $myform.on('click', '.remove', function() {
                    var $tr = $(this).closest('tr'),
                            $btnRemove = $tbody.find('.remove');

                    if ($btnRemove.length < 2) {
                        return false;
                    }
                    // 弹层确认删除
                    layer.confirm('确认删除行？', {icon: 3, title:'提示'}, function(index){
                        $btnRemove.length === 2 && $btnRemove.remove();
                        $tr.remove();
                        layer.close(index);
                    });
                })

                // 添加商品
                $('.side').on('click', '.btn', function() {
                    var tr = [];
                    var $cbs = $(this).parent().next().find('input:checked');
                    var $last = $tbody.find('tr:last');
                    var length = $cbs.length;

                    if (length === 0) {
                        return;
                    }
                    
                    if ($last.find('.ipt-name').val() === '') {
                        $last.remove();
                    } else if ($last.index() === 0) {
                        if ($last.find('.remove').length === 0) {
                            $last.find('.add').after(' <a class="remove c-red" href="javascript:;">删除</a>');
                        }
                    }
                    $cbs.each(function() {
                        var $tr = $(modal),
                                $dd = $(this).closest('dd');

                        $tr.find('.ipt:eq(0)').val($dd.find('.w1').html()).trigger('focus');
                        $tr.find('.ipt:eq(1)').val($dd.find('.w2').html()).trigger('focus');
                        $tr.find('.ipt:eq(2)').val($dd.find('.w3').html()).trigger('focus');
                        $tr.find('.ipt:eq(3)').val($dd.find('.w4').html()).trigger('focus');
                        $tbody.append($tr);
                    });
                })

                // 运费
                $('#jfreightPrice').on('keyup', function() {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                        this.value = isNaN(val) ? '' : val;
                    }
                }).on('blur', function() {
                    calcPrice();
                })
            },
            // 小计&总计
            calcPrice: function($tbody, $tr) {
                //var freightPrice = parseFloat($('#jfreightPrice').val());
                var freightPrice =0;
                var subTotal = 0;
                //保证金
                var subDeposit = 0;

                //if (isNaN(freightPrice)) {
                //    freightPrice = 0;
                //}
                $tbody.find('tr').each(function(i) {
                    var amount = parseInt($(this).find('.amount').val(), 10);
                    var price = parseFloat($(this).find('.price').val(), 10);
                    var deposit = parseFloat($(this).find('.guidePrice').val());
                    var total = parseFloat((amount * price).toFixed(2));
                    var depositTotal = parseFloat((amount * deposit).toFixed(2));
                    if (!isNaN(total)) {
                        $(this).find('.jtotal').text(total)
                    }
                    subTotal += isNaN(total) ? 0 : total;
                    subDeposit += isNaN(depositTotal) ? 0 : depositTotal;
                });
                $('.jsum').html(subTotal === 0 ? '' : '&yen; ' + subTotal.toFixed(2));

                subTotal += freightPrice;
                $('.jsum2').html(subTotal === 0 ? '' : '&yen; ' + subTotal.toFixed(2));

                // 代理商保证金
                subDeposit += freightPrice;
                $('.jsum3').html(subDeposit === 0 ? '' : '&yen; ' + subDeposit.toFixed(2));
            },
            // ajax 查询关键词
            getKeywords: function(keywords) {
                var self = this;
                var keywords = $.trim(keywords);
                var $suggestions = $('#suggestions');
                if (keywords === '') {
                    $suggestions.hide();
                } else {
                    // ajax 查询关键词
                    self.timer && clearTimeout(self.timer);
                    self.timer = setTimeout(function() {
                        self.ajaxSearch(keywords);
                    }, 500);
                }
            },
            ajaxSearch: function(keywords) {
                var self = this;
                $.ajax({
                    url: 'order/auto',
                    dataType: 'json',
                    data:{commodityName:keywords},
                    success: function(result) {
                        // 显示查询结果
                        if (result.status === 'y') {
                            self.toHtml(result.data);
                        } else {
                            $('#suggestions').hide();
                        }
                    }
                })
            },
            // 显示查询结果
            toHtml: function(json) {
                var modal = [];
                $.each(json, function(i, item) {
                    var val = item.name + '-' + item.spec + '-' + item.level + '-' + item.originOf;
                    modal.push('<div class="group" data-val="', val, '">');
                    modal.push(     '<span class="w1">', item.name, '</span>');
                    modal.push(     '<span class="w2">', item.spec, '</span>');
                    modal.push(     '<span class="w3">', item.level, '</span>');
                    modal.push(     '<span class="w4">', item.originOf, '</span>');
                    modal.push('</div>');
                })
                $('#suggestions .bd').empty().html(modal.join('')).parent().show();
            },
            formatTableData: function () {
                var tableObj = $('#myform tbody tr').map(function (i) {
                    var row = {};
                    $(this).find('input').each(function (i) {
                        row[$(this).attr("name")] = $(this).val();
                    });
                    return row;
                }).get();

                return tableObj;
            },
            addGoodsToOrder: function() {
                var self     = this;

                $('#submitOrder').on('click', function() {
                    self.submitOrder();
                })

                $('#submitOrder2').on('click',function(){
                    self.submitOrder();
                })

            },
            submitOrder:function () {

                var self     = this;
                var isSubmit = false;

                if (!isSubmit&&$("#myform").isValid()) {

                    var result = self.checkForm();

                    if (result.pass) {
                        isSubmit = true;

                        var formObj = {};

                        // 代理商id
                        var agentId = $("#agentId").val();
                        var userId = $("#userId").val();
                        var commodities = _global.fn.formatTableData();
                        formObj.agentId = agentId;
                        //用户ID
                        formObj.userId=userId;
                        //商品
                        formObj.commodities=commodities;
                        //收货地址
                        var address={};
                        address.consignee= $(".cnt input[name='consignee']").val()
                        address.tel= $(".cnt input[name='tel']").val()
                        address.detail= $(".cnt textarea[name='detail']").val()
                        address.areaId= $("#area").val()
                        address.userId= $("#userId").val()
                        formObj.shippingAddress = address;
                        //订单备注
                        var remark =  $(".cnt textarea[name='remark']").val();
                        formObj.remark = remark;
                        //运费
                        //var jfreightPrice = $("#jfreightPrice").val();
                        formObj.shippingCosts = 0;
                        //订单号
                        var orderId = $("#orderId").val();
                        formObj.orderId = orderId;

                        var formData = JSON.stringify(formObj);
                        $.ajax({
                            type : 'post',
                            url : '/order/submit',
                            contentType : 'application/json',
                            data :formData,
                            dataType : 'json',
                            success : function(result) {
                                isSubmit = false;
                                location.href="/order/index";
                            },
                            complete: function() {
                                isSubmit = false;
                            }
                        });
                    }else{
                        isSubmit = false;
                    }
                    return false;

                }

            },
            checkForm: function() {
                var result = {
                    pass: true,
                    serialize: []
                };

                $("#myform").find('tbody').find('tr').each(function() {
                    var
                            $name     = $(this).find('.ipt[name="name"]'),
                            name      = $.trim($name.val()),
                            $standard = $(this).find('.ipt[name="spec"]'),
                            standard  = $.trim($standard.val()),
                            $level    = $(this).find('.ipt[name="level"]'),
                            level     = $.trim($level.val()),
                            $origin   = $(this).find('.ipt[name="originOf"]'),
                            origin    = $.trim($origin.val()),
                            $amount   = $(this).find('.ipt[name="amount"]'),
                            amount    = $.trim($amount.val()),
                            $price    = $(this).find('.ipt[name="price"]'),
                            price     = $.trim($price.val());

                    if (name) {
                        $name.nextAll('.error').html('').hide();
                    } else {
                        $name.nextAll('.error').html('不可空白').show();
                        result.pass = false;
                    }

                    if (standard) {
                        $standard.nextAll('.error').css('display','none').html('');
                    } else {
                        $standard.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (level) {
                        $level.nextAll('.error').css('display','none').html('');
                    } else {
                        $level.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (origin) {
                        $origin.nextAll('.error').css('display','none').html('');
                    } else {
                        $origin.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (price) {
                        $price.nextAll('.error').css('display','none').html('');
                    } else {
                        $price.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }


                    if (amount) {
                        $amount.nextAll('.error').css('display','none').html('');
                    } else {
                        $amount.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (date) {
                        $date.nextAll('.error').css('display','none').html('');
                    } else {
                        $date.nextAll('.error').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (result.pass) {
                        result.serialize.push({
                            name: name,
                            spec: standard,
                            level: level,
                            originOf: origin,
                            amount: amount,
                            price: price,
                        })
                    } else {
                        window.scrollTo(0, 200);
                        return false;
                    }
                })
                return result;
            },
            insertArea: function() {

                // 地区选择
                $('#pickArea').citys({
                    provinceField: 'province',
                    cityField: 'city',
                    areaField: 'area'
                });

                // 从已有客户地址中选择
                $("#order_address").change(function(){
                    if(this.value){
                        var $checked = $(this).find('option:selected');
                        var data = $checked.data('val').split(',');
                        var area = $checked.data('area').split(',');
                        var timer;

                        $(".cnt input[name='consignee']").val(data[0]);
                        $(".cnt input[name='tel']").val(data[1]);
                        $(".cnt textarea[name='detail']").val(data[2]);

                        $('#pickArea').citys({
                            provinceField: 'province',
                            cityField: 'city',
                            areaField: 'area',
                            code: area[2]
                        });
                    }
                })
            },
            batch: function() {
                $("#importExcel").click(function(){
                    layer.open({
                        moveType: 1,
                        area: ['600px'],
                        title: '导入报价',
                        content: '<form action="/order/importExcel" id="excelForm" method="post" enctype="multipart/form-data"><p>上传报价文件</p><label class="btn btn-file enquiry_btn"><span>上传文件</span><input type="file" name="file"></label><label class="filename"></label></form>',
                        btn: ['确定', '取消'],
                        yes: function(index) {
                            layer.close(index);
                            $.ajax({
                                url: '/order/importExcel',
                                data: new FormData($('#excelForm')[0]),
                                type: "POST",
                                contentType: false,
                                processData:false,
                                cache: false,
                                success: function (data) {
                                    if (data.status == "y") {
                                        console.log(data);
                                    }
                                }
                            });
                        },
                        end: function() {
                            $('.enquiry_btn').off();
                        }
                    })

                    $('.enquiry_btn').on('change', 'input', function() {
                        $('.filename').html($(this).val());
                    })
                })
            }
        }
    }

    $(function() {
        _global.fn.init();
    })

</script>
</body>
</html>