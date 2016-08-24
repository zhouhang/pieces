<!DOCTYPE html>
<html lang="en">
<head>
    <title>订单管理-boss-饮片B2B</title>
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
                <a class="btn btn-gray" href="create_order.html">取消</a>
                <a class="btn btn-red" href="create_order.html">提交订单</a>
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
                            <span class="w2">规格</span>
                            <span class="w3">等级</span>
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
                        <span class="w2">规格</span>
                        <span class="w3">等级</span>
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
            <div class="chart-info">
                <h3>订购商品</h3>
                <div class="chart">
                    <form action="" id="myform">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th>切制规格</th>
                                <th>等级</th>
                                <th width="100">产地</th>
                                <th width="100">期望交货日期</th>
                                <th width="90">数量（公斤）</th>
                                <th width="100">单价（元/公斤）</th>
                                <th width="120">小计（元）</th>
                                <th width="120">操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="7" class="tl">共 <#if commodityVos??&&commodityVos?has_content>${commodityVos?size}<#else>0</#if> 件商品</td>
                                <td colspan="2" class="tl">总计：<b class="jsum"></b></td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <#if commodityVos??&&commodityVos?has_content>
                                <#list commodityVos as commodity>
                                    <tr>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.name!}" class="ipt ipt-name" name="name"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.spec!}" class="ipt" name="spec"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.level!}" class="ipt" name="level"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text" value="${commodity.originOf!}" class="ipt" name="originOf"></div><span class="error"></span></td>
                                        <td><input type="text" class="ipt" name="expectDate" value="${commodity.expectDate?string("yyyy-MM-dd")}" onclick="laydate({min:laydate.now()})"><span class="error"></span></td>
                                        <td><input type="text" class="ipt amount" name="amount" value="${commodity.amount!}"><span class="error"></span></td>
                                        <td><input type="text" class="ipt price" name="price" value="${commodity.price!}"><span class="error"></span></td>
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
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt ipt-name" name="name"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="spec"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="level"></div><span class="error"></span></td>
                                        <td><div class="ipt-wrap"><input type="text"  class="ipt" name="originOf"></div><span class="error"></span></td>
                                        <td><input type="text" class="ipt" name="expectDate"  onclick="laydate({min:laydate.now()})"><span class="error"></span></td>
                                        <td><input type="text" class="ipt amount" name="amount" ><span class="error"></span></td>
                                        <td><input type="text" class="ipt price" name="price" >><span class="error"></span></td>
                                        <td class="jtotal"></td>
                                        <td>
                                            <a href="javascript:;" class="add">添加</a>
                                        </td>
                                    </tr>
                            </#if>

                            </tbody>
                        </table>
                    </form>
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
                        <div class="cnt">
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

                <input type="hidden" id="orderId" name="orderId" value="<#if order_type=='修改订单'>${origOrderForm.id!}</#if>">

                <h3>订单总额</h3>
                <div class="fa-form summary">
                    <div class="item">
                        <span>商品合计：</span>
                        <em class="jsum"><#if origOrderForm??>${origOrderForm.sum!}</#if></em>
                    </div>
                    <div class="item">
                        <span>运&#12288;&#12288;费：</span>
                        <em><input value="<#if origOrderForm??>${origOrderForm.shippingCosts!}</#if>" type="text" class="ipt" id="jfreightPrice"></em>
                    </div>
                    <div class="item">
                        <span>实际应付：</span>
                        <em class="price jsum2"><#if origOrderForm??>${origOrderForm.amountsPayable!}</#if></em>
                    </div>
                </div>
            </div>

            <div class="submit">
                <button class="btn btn-red" id="submitOrder" type="button">提交订单</button>
            </div>

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
            <span class="w1">商品名称</span><span class="w2">切制规格</span><span class="w3">等级</span><span class="w4">产地</span>
        </div>
    </div>
    <div class="bd"></div>
</div><!-- 输入框联想 end -->

<script type="temp" id="jmodal">
        <tr>
            <td><div class="ipt-wrap"><input type="text" class="ipt ipt-name" name="name"></div><span class="error"></span></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="spec"></div><span class="error"></span></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="level"></div><span class="error"></span></td>
            <td><div class="ipt-wrap"><input type="text" class="ipt" name="originOf"></div><span class="error"></span></td>
            <td><input type="text" class="ipt" name="expectDate" value="" onclick="laydate({min:laydate.now()})"><span class="error"></span></td>
            <td><input type="text" class="ipt amount" name="amount" value=""><span class="error"></span></td>
            <td><input type="text" class="ipt price" name="price" value=""><span class="error"></span></td>
            <td class="jtotal"></td>
            <td>
                <a href="javascript:;" class="add">添加</a>
                <a href="javascript:;" class="remove c-red">删除</a>
            </td>
        </tr>
    </script>

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

                $("#order_address").change(function(){
                   var id =  $(this).val()
                   if(id!=null&&id!=""){
                       var data =  $(this).find('option:selected').data('val').split(',');
                       $(data).each(function(index,obj){
                            if(index==0){
                               $(".cnt input[name='consignee']").val(obj)
                            }
                            if(index==1){
                               $(".cnt input[name='tel']").val(obj)
                            }
                            if(index==2){
                               $(".cnt textarea[name='detail']").val(obj)
                            }
                       })
                       var area = $(this).find('option:selected').data('area').split(',');
                       $(area).each(function(index,obj){
                           if(index==0){
                               console.log("province:"+obj)
                               $("#province").val(obj);
                           }
                           if(index==1){
                               console.log("city:"+obj)
                               $("#province").change();
                               $("#city").val(obj);
                           }
                           if(index==2){
                               console.log("area:"+obj)
                               $("#area").val(obj);
                           }
                       })
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

                    if ($last.find('.ipt-name').val() === '') {
                        $last.remove();
                    } else if ($last.index() === 0) {
                        $last.find('.add').after(' <a class="remove c-red" href="javascript:;">删除</a>');
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
                var freightPrice = parseFloat($('#jfreightPrice').val());
                var subTotal = 0;
                if (isNaN(freightPrice)) {
                    freightPrice = 0;
                }
                $tbody.find('tr').each(function(i) {
                    var amount = parseInt($(this).find('.amount').val(), 10);
                    var price = parseFloat($(this).find('.price').val(), 10);
                    var total = parseFloat((amount * price).toFixed(2));
                    if(!isNaN(total)){
                        $(this).find('.jtotal').text(total)
                    }
                    subTotal += isNaN(total) ? 0 : total;
                });
                $('.jsum').html(subTotal === 0 ? '' : '&yen; ' + subTotal.toFixed(2));

                subTotal += freightPrice;
                $('.jsum2').html(subTotal === 0 ? '' : '&yen; ' + subTotal.toFixed(2));
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
                var isSubmit = false;

                $('#submitOrder').on('click', function() {
                    if (isSubmit) {
                        return false;
                    }
                    var result = {};
                    result.pass = true;
                    if (result.pass) {
                        isSubmit = true;

                        var formObj = {};

                        var userId = $("#userId").val();
                        var commodities = _global.fn.formatTableData();
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
                        var jfreightPrice = $("#jfreightPrice").val();
                        formObj.shippingCosts = jfreightPrice;
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
                                self.response(result);
                            },
                            complete: function() {
                                isSubmit = false;
                            }
                        });
                    }else{
                        isSubmit = false;
                    }
                    return false;
                })
            },
            insertArea: function() {

            }
        }
    }

    $(function() {
        _global.fn.init();
    })

</script>
</body>
</html>