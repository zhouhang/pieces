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
                                        <span class="w0" style="display: none;">${commodity.commodityId!}</span>
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
                                <span class="w0" style="display: none;">${commodity.commodityId!}</span>
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
                <h3>订购商品</h3>
                <div class="chart">
                    <table class="tc">
                        <thead>
                            <tr>
                                <th>商品名称</th>
                                <th>片型</th>
                                <th>规格等级</th>
                                <th width="100">产地</th>
                                <th width="90">数量（公斤）</th>
                                <th width="120">销售价（元/公斤）</th>
                                <th width="100">开票价（元/公斤）</th>
                                <th width="120">开票价小计（元）</th>
                                <th width="100">操作</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="5" class="tl">共 <#if commodityVos??&&commodityVos?has_content>${commodityVos?size}<#else>0</#if> 件商品</td>
                                <td colspan="5" class="tr">总计：<b class="jsum"></b></td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <#if commodityVos??&&commodityVos?has_content>
                                <#list commodityVos as commodity>
                                    <tr>
                                        <td><div class="pr ipt-wrap">
                                            <input type="text" style="display:none" class="ipt ipt-name" name="commodityId" value="${commodity.commodityId!}" autocomplete="off">
                                            <input type="text" value="${commodity.name!}" class="ipt ipt-name" name="name" autocomplete="off"><span class="error1"></span>
                                        </div></td>
                                        <td><div class="pr ipt-wrap"><input type="text" value="${commodity.spec!}" class="ipt" name="spec" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr ipt-wrap"><input type="text" value="${commodity.level!}" class="ipt" name="level" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr ipt-wrap"><input type="text" value="${commodity.originOf!}" class="ipt" name="originOf" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt amount" name="amount" value="${commodity.amount!}" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice" value="${commodity.guidePrice!}" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt price" name="price" value="${commodity.price!}" autocomplete="off"><span class="error1"></span></div></td>
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
                                        <td><div class="pr ipt-wrap">
                                            <input type="text" style="display:none" class="ipt ipt-name" name="commodityId" autocomplete="off">
                                            <input type="text"  class="ipt ipt-name" name="name" autocomplete="off"><span class="error1"></span>
                                        </div></td>
                                        <td><div class="pr ipt-wrap"><input type="text"  class="ipt" name="spec" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr ipt-wrap"><input type="text"  class="ipt" name="level" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr ipt-wrap"><input type="text"  class="ipt" name="originOf" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt amount" name="amount"  autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice" autocomplete="off"><span class="error1"></span></div></td>
                                        <td><div class="pr"><input type="text" class="ipt price" name="price" autocomplete="off"><span class="error1"></span></div></td>
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
                            <input type="text" name="consignee" id="consignee" class="ipt" placehoder="" autocomplete="off" value="<#if shippingAddressHistory??>${shippingAddressHistory.consignee!}</#if>">
                        </div>
                    </div>
                    <div class="group">
                        <div class="txt">手机号码：</div>
                        <div class="cnt">
                            <input type="text" name="tel" id="tel" class="ipt" placehoder="" autocomplete="off" value="<#if shippingAddressHistory??>${shippingAddressHistory.tel!}</#if>">
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
                            <textarea name="detail" id="address" cols="30" rows="10" class="ipt ipt-mul"><#if shippingAddressHistory??>${shippingAddressHistory.detail!}</#if></textarea>
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
<div class="suggestions" id="suggestions" style="width: 860px;">
    <div class="hd">
        <div class="group">
            <span class="w1">商品名称</span><span class="w2">片型</span><span class="w3">规格等级</span><span class="w4">产地</span><span class="w4">当前价格</span><span class="w4">上次成交价格</span>
        </div>
    </div>
    <div class="bd"></div>
</div><!-- 输入框联想 end -->

<script type="temp" id="jmodal">
        <tr>
            <td><div class="pr ipt-wrap">
                <input type="text" style="display:none" class="ipt ipt-name" name="commodityId" autocomplete="off">
                <input type="text" class="ipt ipt-name" name="name" autocomplete="off"><span class="error1"></span>
            </div></td>
            <td><div class="pr ipt-wrap"><input type="text" class="ipt" name="spec" autocomplete="off"><span class="error1"></span></div></td>
            <td><div class="pr ipt-wrap"><input type="text" class="ipt" name="level" autocomplete="off"><span class="error1"></span></div></td>
            <td><div class="pr ipt-wrap"><input type="text" class="ipt" name="originOf" autocomplete="off"><span class="error1"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt amount" name="amount" value="" autocomplete="off"><span class="error1"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt guidePrice" name="guidePrice" value="" autocomplete="off"><span class="error1"></span></div></td>
            <td><div class="pr"><input type="text" class="ipt price" name="price" value="" autocomplete="off"><span class="error1"></span></div></td>
            <td class="jtotal"></td>
            <td>
                <a href="javascript:;" class="add">添加</a>
                <a href="javascript:;" class="remove c-red">删除</a>
            </td>
        </tr>
    </script>

<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
<script src="${urls.getForLookupPath('/js/layer/layer.js')}"></script>
<script src="${urls.getForLookupPath('/js/area.js')}"></script>
<script src="${urls.getForLookupPath('/js/jquery.pagination.min.js')}"></script>
<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.formValidate();
                this.myformEvent();
                this.addGoodsToOrder();
                this.batch();
            },
            formValidate: function() {
                $('#myform').validator({
                    fields : {
                       // jfreightPrice : '运费: required;',
                        consignee : '收货人: required',
                        tel : '手机号: required; mobile',
                        area : '所在地区: required',
                        detail : '详细地址: required'
                    }
                });

                // 地区选择
                $('#pickArea').citys({
                    provinceField: 'province',
                    cityField: 'city',
                    areaField: 'area'
                });

                // 从已有客户地址中选择
                $("#order_address").change(function(){
                    if(this.value){
                        var $checked = $(this).find('option:selected'),
                            data = $checked.data('val').split(','),
                            area = $checked.data('area').split(',');

                        $("#consignee").val(data[0]);
                        $("#tel").val(data[1]);
                        $("#address").val(data[2]);

                        $('#pickArea').citys({
                            provinceField: 'province',
                            cityField: 'city',
                            areaField: 'area',
                            code: area[2]
                        });
                        $('#myform').validator('cleanUp');
                    }
                })
            },
            myformEvent: function() {
                var $body        = $('body');
                var $suggestions = $('#suggestions');
                var $myform      = $('#myform');
                var $tbody       = $myform.find('tbody');
                var $ipt         = $tbody.find('.ipt:first');
                var modal        = $('#jmodal').html();
                var self         = this;
                self.$suggestions = $suggestions;

                // 计算价格
                var calcPrice = function() {
                    self.calcPrice($tbody, $tbody.find('tr:eq(0)'));
                }

                calcPrice();

                // 隐藏错误提示
                $myform.on('focus', '.ipt', function() {
                    $(this).nextAll('.error1').html('').hide();
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
                }, '.price, .guidePrice');

                // 商品名联想
                $myform.on({
                    'click': function(event) {
                        event.stopPropagation();
                    },
                    'focus': function() {
                        $(this).after($suggestions);
                        $suggestions.find('.group').length > 1 && $suggestions.show();
                        // 关键字自动填充
                        $suggestions.on('click', '.bd .group', function() {
                            var data = $(this).data('val').split('-&');
                            $suggestions.prev().val(data[1]).prev().val(data[0])
                                    .closest('td').next().find('.ipt').val(data[2]).trigger('focus').end()
                                    .closest('td').next().find('.ipt').val(data[3]).trigger('focus').end()
                                    .closest('td').next().find('.ipt').val(data[4]).trigger('focus').end()
                                    .closest('td').next().closest('td').next().find('.ipt').val(data[5]!="null"?data[5]:"0.00");
                            $suggestions.hide();
                        })
                    },
                    'input': function() {
                        self.getKeywords(this.value);                           
                    }
                }, '.ipt-name');

                // 关闭联想层
                $body.on('click', function() {
                    $suggestions.hide();
                })
                $suggestions.on('click', function(event) {
                    event.stopPropagation();
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

                        $tr.find('.ipt:eq(0)').val($dd.find('.w0').html()).trigger('focus');
                        $tr.find('.ipt:eq(1)').val($dd.find('.w1').html()).trigger('focus');
                        $tr.find('.ipt:eq(2)').val($dd.find('.w2').html()).trigger('focus');
                        $tr.find('.ipt:eq(3)').val($dd.find('.w3').html()).trigger('focus');
                        $tr.find('.ipt:eq(4)').val($dd.find('.w4').html()).trigger('focus');
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
                    }, 300);
                }
            },
            ajaxSearch: function(keywords) {
                var self = this;
                $.ajax({
                    url: 'order/commodity/auto',
                    dataType: 'json',
                    data:{commodityName:keywords,userId:${user.id!}},
                    success: function(result) {
                        // 显示查询结果
                        if (result.status === 'y') {
                            if (result.data.list.length === 0) {
                                self.$suggestions.show().find('.bd').empty().html('暂无此商品:)');
                            } else {
                                self.toHtml(result.data.list, 0, 7);
                            }
                        } else {
                            self.$suggestions.hide();
                        }
                    }
                })
            },
            // 显示查询结果
            toHtml: function(item, page_index, pageSize) {
                var modal = [],
                    maxPage = Math.min((page_index + 1) * pageSize, item.length),
                    hasPage = pageSize < item.length;

                for (var i = page_index * pageSize; i < maxPage; i++) {
                    var val = item[i].id+ '-&' + item[i].name + '-&' + item[i].spec + '-&' + item[i].level + '-&' + item[i].originOf+"-&" +item[i].guidePrice;
                    modal.push('<div class="group" data-val="', val, '">');
                    modal.push(     '<span class="w1">', item[i].name, '</span>');
                    modal.push(     '<span class="w2">', item[i].spec, '</span>');
                    modal.push(     '<span class="w3">', item[i].level, '</span>');
                    modal.push(     '<span class="w4">', item[i].originOf, '</span>');
                    modal.push(     '<span class="w4">', item[i].guidePrice?item[i].guidePrice:"-", '</span>');
                    modal.push(     '<span class="w4">', item[i].orderPrice?item[i].orderPrice:"-", '</span>');
                    modal.push('</div>');
                }
                hasPage && modal.push('<div class="jq-page"></div>');
                this.$suggestions.show().find('.bd').empty().html(modal.join(''));
                hasPage && this.showPage(item, page_index, pageSize);
            },
            showPage: function(item, page_index, pageSize) {
                var self = this;
                $('.jq-page').pagination(item.length, {
                    items_per_page: pageSize, //pageSize 每页显示数量
                    current_page: page_index, //默认pageIndex,0(默认),false(不加载)
                    num_edge_entries: 2, //1(任何情况下都显示第一页和最后一页),0(不显示)
                    callback: function(page_index) {
                        self.toHtml(item, page_index, pageSize);
                    }
                })
                $('.jq-page').prepend('<div class="p-size">总共' + item.length + '条记录</div>')
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
                    self.submitOrder()
                })

                $('#submitOrder2').on('click',function(){
                    self.submitOrder();

                })

            },
            submitOrder:function () {

                var self     = this;

                if (!self.isSubmit&&$("#myform").isValid()) {
                    var result = self.checkForm();

                    if (result.pass) {
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
                        address.consignee= $("#consignee").val()
                        address.tel= $("#tel").val()
                        address.detail= $("#address").val()
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
                            beforeSend: function () {
                              self.isSubmit = true;
                            },
                            success : function(data) {
                                if(data.status=='y'){
                                    $.notify({
                                        type: 'success',
                                        title: data.info,
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
                                                location.href="/order/index";
                                            }, 3e3);
                                        }
                                    });
                                }else{
                                    $.notify({
                                        type: 'error',
                                        title: data.info,
                                        delay: 3e3,
                                        call: function() {
                                            setTimeout(function() {
//                                                location.reload();
                                            }, 3e3);
                                        }
                                    });
                                }
                            },
                            error: function() {
                                self.isSubmit = false;
                            }
                        });
                    }
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
                            price     = $.trim($price.val()),
                            $commodityId   = $(this).find('.ipt[name="commodityId"]'),
                            commodityId      = $.trim($commodityId.val());

                    if (name && commodityId) {
                        $name.nextAll('.error1').html('').hide();
                    } else {
                        $name.nextAll('.error1').html('请选择有效的商品').show();
                        result.pass = false;
                    }

                    if (standard) {
                        $standard.nextAll('.error1').css('display','none').html('');
                    } else {
                        $standard.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (level) {
                        $level.nextAll('.error1').css('display','none').html('');
                    } else {
                        $level.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (origin) {
                        $origin.nextAll('.error1').css('display','none').html('');
                    } else {
                        $origin.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }

                    if (price) {
                        $price.nextAll('.error1').css('display','none').html('');
                    } else {
                        $price.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }


                    if (amount) {
                        $amount.nextAll('.error1').css('display','none').html('');
                    } else {
                        $amount.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }
                    /*
                    if (date) {
                        $date.nextAll('.error1').css('display','none').html('');
                    } else {
                        $date.nextAll('.error1').css('display','block').html('不可空白');
                        result.pass = false;
                    }*/

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
            batch: function() { // 批量导入报价功能已删除.
                $("#importExcel").click(function(){
                    layer.open({
                        moveType: 1,
                        area: ['600px'],
                        title: '导入报价',
                        content: '<form action="/order/importExcel" id="excelForm" method="post" enctype="multipart/form-data"><p>上传报价文件</p><label class="btn btn-file enquiry_btn"><span>上传文件</span><input type="file" name="file"></label><label class="filename"></label></form>',
                        btn: ['确定', '取消'],
                        yes: function(index) {
                            $.ajax({
                                url: '/order/importExcel',
                                data: new FormData($('#excelForm')[0]),
                                type: "POST",
                                contentType: false,
                                processData:false,
                                cache: false,
                                success: function (data) {
                                    if (data.status == "y") {
                                        var $myform      = $('#myform');
                                        var $tbody       = $myform.find('tbody');
                                        var modal        = $('#jmodal').html();
                                        var $last = $tbody.find('tr:last');
                                        var list=data.data;
                                        if ($last.find('.ipt-name').val() === '') {
                                            $last.remove();
                                        } else if ($last.index() === 0) {
                                            if ($last.find('.remove').length === 0) {
                                                $last.find('.add').after(' <a class="remove c-red" href="javascript:;">删除</a>');
                                            }
                                        }
                                        list.forEach(function(item){
                                            var $tr = $(modal);
                                            $tr.find('.ipt:eq(0)').val(item.commodityName);
                                            $tr.find('.ipt:eq(1)').val(item.specs);
                                            $tr.find('.ipt:eq(2)').val(item.level);
                                            $tr.find('.ipt:eq(3)').val(item.myPrice);
                                            $tr.find('.ipt:eq(5)').val(item.myPrice);
                                            $tbody.append($tr);
                                        });
                                    }
                                }
                            });
                            layer.close(index);
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