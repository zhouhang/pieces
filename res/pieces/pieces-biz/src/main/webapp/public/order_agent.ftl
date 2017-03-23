<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
        <title>代理商生成订单-${baseSetting.title!}</title>
        <meta name="description" content="${baseSetting.intro!}" />
        <meta name="Keywords" content="${baseSetting.keyWord!}" />
    <link rel="stylesheet" href="${urls.getForLookupPath('/css/order.css')}" />
</head>

<body>
	
    <#include "./inc/header-center.ftl"/>

    <!-- order-success start -->
    <div class="wrap">
        <div class="order-wrap">
            <div class="title">
                <h3>订购商品</h3>
            </div>

            <div class="order">
                <form id="orderSave" action="/center/order/save" method="post">
                <!-- start 选择客户 -->
                <div class="group">
                    <div class="hd">
                        <h3>选择客户</h3>
                    </div>
                    <div class="consignee">
                        <div class="list">
                            <#list agentUser as user>
                            <label><input type="radio" name="userId" class="cbx" value="${user.id}" checked>${user.companyFullName}</label>
                            </#list>
                        </div>
                    </div>
                </div><!-- end 选择客户 -->

                <!-- start 收货信息 -->
                <div class="group">
                    <div class="hd">
                        <h3>收货信息</h3>
                    </div>
                    <div class="consignee">

                            <div class="list" id="jconsigneeList" <#if !(shippingAddressList?exists && shippingAddressList?size gt 0) >style="display: none"</#if>>
                                <#list shippingAddressList as addr>
                                <label><input type="radio" name="addrHistoryId" class="cbx" value="${addr.id}" <#if addr.isDefault>checked</#if>>${addr.fullAdd!}${addr.detail!}</label>
                                </#list>
                            </div>
                            <div class="extra" <#if !(shippingAddressList?exists && shippingAddressList?size gt 0 )>style="display: none"</#if>>
                                <div class="btn btn-lgray jaddConsignee">新增收货地址</div>
                            </div>

                            <div id="consigneeEmpty" class="empty" <#if shippingAddressList?exists && shippingAddressList?size gt 0 >style="display: none"</#if>>
                                <button class="btn btn-lgray jaddConsignee" type="button">新建收货地址</button>
                            </div>
                    </div>
                </div><!-- end 收货信息 -->

                <!-- start 送货清单 -->
                <div class="group">
                    <div class="fa-chart">
                        <table id="orderList">
                            <thead>
                            <tr>
                                <th width="110">商品名称</th>
                                <th width="90">片型</th>
                                <th>规格等级</th>
                                <th width="100">产地</th>
                                <th width="110">销售价<em>（元/公斤）</em></th>
                                <th width="110">开票价<em>（元/公斤）</em></th>
                                <th width="110">数量<em>（公斤）</em></th>
                                <th width="150">开票价小计<em>（元）</em></th>
                                <td width="100">操作</td>
                            </tr>
                            </thead>
                            <tfoot></tfoot>
                            <tbody>
                            <#list enquiryCommoditysList as enquiryCommoditys>
                            <tr>
                                <td>${enquiryCommoditys.commodityName}</td>
                                <input type="hidden" data-name="enquiryCommodityId" value="${enquiryCommoditys.id}">
                                <td>${enquiryCommoditys.specs}</td>
                                <td>${enquiryCommoditys.level}</td>
                                <td>${enquiryCommoditys.origin}</td>
                                <td><i>&yen;</i> ${enquiryCommoditys.myPrice}</td>
                                <td><div class="ipt-wrap"><input type="text" value="1" class="ipt price" id="price" placeholder="请输入合同价"></div></td>
                                <td><div class="ipt-wrap"><input type="text" value="${enquiryCommoditys.amount}" data-price="${enquiryCommoditys.myPrice}" class="ipt amount" placeholder="请输入数量"></div></td>
                                <td ><i>&yen;</i> <span class="sum">${enquiryCommoditys.amount * enquiryCommoditys.myPrice}</span></td>
                                <td><a href="javascript:;" class="c-blue">删除</a></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>

                </div><!-- end 送货清单 -->

                <!-- start 订单备注 -->
                <div class="group">
                    <div class="hd">
                        <h3>订单备注</h3>
                    </div>
                    <div>
                        <textarea name="remark" id="remark" cols="30" rows="10" class="mul" placeholder="期望交货日期等需要注意的事项。"></textarea>
                    </div>
                </div><!-- end 订单备注 -->

                <!-- start 发票信息 -->
                <div class="group">
                    <div class="hd">
                        <h3>发票信息</h3>
                    </div>
                    <div class="invoice">
                        <!-- <span class="tips">您当前的发票信息如下：</span>
                        <em>普通发票</em><em>速采科技</em><em>药材</em><a href="javascript:;" class="c-blue jinvoiceEdit">修改</a> -->
                        <div class="btn btn-lgray jinvoiceAdd">新增发票</div>
                    </div>
                </div><!-- end 发票信息 -->
                <input type="hidden" name="token" id="token" value="${token}">
                <input type="hidden" name="commodityIds" value="${commodityIds}">

                <!-- start 小计 -->
                <div class="summary">
                    <div class="item">
                        <span class="dt">订单金额：</span>
                        <span class="dd price"><i class="rmb">&yen;</i><em id="total">40.00</em></span>
                    </div>
                    <div class="item">
                        <span class="dt">需支付保证金：</span>
                        <span class="dd"><i class="rmb">&yen;</i><em id="deposit">40.00</em></span>
                    </div>
                </div><!-- end 小计 -->
                </form>
                <!-- start 提交 -->
                <div class="submit">
                    <button id="orderSubmit" type="button" class="btn btn-red">确认订购</button>
                    <p><a href="center/enquiry/record" class="c-blue fr">返回询价单</a></p>
                </div><!-- end 提交 -->
            </div>
        </div>
    </div><!-- order-success end -->
    <!-- start 新增发票 -->
    <div class="fa-form fa-form-layer" id="jinvoiceBox">
        <form action="" id="invoiceForm">
            <div class="group">
                <div class="txt">
                    <span>发票类型：</span>
                </div>
                <div class="cnt">
                    <label><input type="radio" name="invoice" value="1" class="cbx" data-text="普通发票">普通发票</label>
                    <label><input type="radio" name="invoice" value="2" class="cbx" id="tax" data-text="增值税专用发票">增值税专用发票</label>
                    <label><input type="radio" name="invoice" value="0" class="cbx" data-text="暂不需要">暂不需要</label>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i>*</i>
                    <span>发票抬头：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceTitle" class="ipt" autocomplete="off" placeholder="请填写发票抬头">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>纳税人识别号：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceNumber" class="ipt" autocomplete="off" placeholder="15，17，18或20位纳税人识别号">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>注册地址：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceAddress" class="ipt" autocomplete="off" placeholder="请填写注册地址">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>注册电话：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceTel" class="ipt" autocomplete="off" placeholder="请填写注册电话">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>开户银行：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceBank" class="ipt" autocomplete="off" placeholder="请填写开户银行">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="hide">*</i>
                    <span>银行帐号：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceBankNumber" class="ipt" autocomplete="off" placeholder="请填写银行帐号">
                </div>
            </div>

            <div class="button">
                <button type="submit" class="btn btn-red submit">保存</button>
                <button type="reset" class="btn btn-gray cancel">取消</button>
            </div>
        </form>
    </div><!-- end 新增发票 -->
    <!-- start 新增收货地址 -->
    <div class="fa-form fa-form-layer" id="jconsigneeBox">
        <form action="/center/address/add" id="consigneeForm">
        <input type="hidden" name="commodityIds" value="${commodityIds}">
            <div class="group">
                <div class="txt">
                    <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="consignee" id="consigneeName" class="ipt" autocomplete="off" placeholder="" data-msg-nickName="只能输入中文、英文，长度2-50" maxlength="50">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <span>手机号码：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="tel" id="consigneeMobile" class="ipt" autocomplete="off" placeholder="">
                    <span class="error"></span>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <span>所在地区：</span>
                </div>
                <div class="cnt" id="pickArea">
                    <select name="provinceCode" id="province">
                        <option value="">-省-</option>
                    </select>
                    <select name="cityCode" id="city">
                        <option value="">-市-</option>
                    </select>
                    <select name="areaId" id="area" data-msg-required="请选择至最后一级">
                        <option value="">-区/县-</option>
                    </select>
                    <span class="error"></span>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <span>详细地址：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="detail" id="consigneeAddress" class="ipt ipt-wide" autocomplete="off">
                    <span class="error"></span>
                </div>
            </div>

            <div class="group ah">
                <div class="cnt">
                    <label><input type="checkbox" class="cbx" name="isDefault" id="isDefault" checked>设为默认地址</label>
                </div>
            </div>

            <div class="button">
                <button type="submit" class="btn btn-red submit">保存</button>
                <button type="reset" class="btn btn-gray cancel">取消</button>
            </div>
        </form>
    </div><!-- end 新增收货地址 -->

    <#include "./inc/footer.ftl"/>

    <script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
    <script src="js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="${urls.getForLookupPath('/js/jquery.form.js')}"></script>
    <script src="${urls.getForLookupPath('/js/jquery_util.js')}"></script>
    <script src="${urls.getForLookupPath('/js/area.js')}"></script>
    <script>
        var _global = {
            v: {
                invoice:null
            },
            fn: {
                init: function() {
                    this.addInvoice();
                    this.addConsignee();
                    this.computePrice();
                    this.orderSubmit();
                    <#if !(agentUser?exists && agentUser?size>0)>
                    this.warning();
                    <#else >
                        <#if !(shippingAddressList?exists && shippingAddressList?size gt 0) >
                            layer.confirm('您还没有收货地址,是否立即新建一个？', {icon: 3, title:'提示'}, function(index){
                                layer.close(index);
                                $('.jaddConsignee').eq(0).trigger("click");
                            });
                        </#if>
                    </#if>
                },
                // 新增发票
                addInvoice: function() {
                    var $invoiceBox = $('#jinvoiceBox'),
                            $invoice = $('.invoice');

                    var closeLayer = function() {
                        layer.closeAll();
                        $('#invoiceForm').get(0).reset(); // 重置表单
                    }

                    // 新增
                    $invoice.on('click', '.jinvoiceAdd', function() {
                        $invoiceBox.find('.cbx:eq(0)').prop('checked', true);
                        $invoiceBox.find('.hide').hide();

                        layer.open({
                            area: ['540px'],
                            closeBtn: 1,
                            type: 1,
                            moveType: 1,
                            content: $invoiceBox,
                            title: '新增发票'
                        });
                    })

                    // 修改
                    $invoice.on('click', '.jinvoiceEdit', function() {
                        var info = $invoice.find('em');
                        $invoiceBox.find('.cbx').each(function() {
                            this.checked = $(this).data('text') === info.eq(0).html();
                            if (this.checked && this.id === 'tax') {
                                $invoiceBox.find('.hide').show();
                            }
                        });
                        $invoiceBox.find('input[name="invoiceTitle"]').val(info.eq(1).html());
                        $invoiceBox.find('input[name="invoiceNumber"]').val(info.eq(2).html());
                        $invoiceBox.find('input[name="invoiceAddress"]').val(info.eq(3).html());
                        $invoiceBox.find('input[name="invoiceTel"]').val(info.eq(4).html());
                        $invoiceBox.find('input[name="invoiceBank"]').val(info.eq(5).html());
                        $invoiceBox.find('input[name="invoiceBankNumber"]').val(info.eq(6).html());

                        layer.open({
                            area: ['540px'],
                            closeBtn: 1,
                            type: 1,
                            moveType: 1,
                            content: $invoiceBox,
                            title: '修改发票'
                        });
                    })

                    // 关闭弹层
                    $invoiceBox.on('click', '.cancel', function() {
                        closeLayer();
                    })

                    $('#invoiceForm').validator({
                        focusCleanup: true,
                        stopOnError: true,
                        rules: {
                            isTax: function() {
                                return $('#tax').prop('checked');
                            },
                            phone: function(val) {
                                return this.test(val, "mobile") === true ||
                                        this.test(val, "tel") === true ||
                                        '请填写有效的电话号码或手机号码';
                            }
                        },
                        fields : {
                            invoice: '发票类型: checked',
                            invoiceTitle : '发票抬头: required',
                            invoiceNumber: '纳税人识别号: required(isTax)',
                            invoiceAddress: '注册地址: required(isTax)',
                            invoiceTel: '注册电话: required(isTax); phone',
                            invoiceBank: '开户银行: required(isTax)',
                            invoiceBankNumber: '银行帐号: required(isTax); bankNumber'
                        },
                        valid: function(form) {
                            if ( $(form).isValid() ) {
                                var html = ['<span class="tips">您当前的发票信息如下：</span>'];
                                html.push('<em>', $invoiceBox.find('input[name="invoice"]:checked').data('text'), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceTitle"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceNumber"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceAddress"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceTel"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceBank"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceBankNumber"]').val(), '</em>');
                                html.push('<a href="javascript:;" class="c-blue jinvoiceEdit">修改</a>');
                                $invoice.html(html.join(''));
                                //
                                _global.v.invoice = {
                                    type: $('#jinvoiceBox input[name="invoice"]:checked').val(),
                                    name : $('#jinvoiceBox input[name="invoiceTitle"]').val(),
                                    identifier: $('#jinvoiceBox input[name="invoiceNumber"]').val(),
                                    registeredAddress: $('#jinvoiceBox input[name="invoiceAddress"]').val(),
                                    registeredTel: $('#jinvoiceBox input[name="invoiceTel"]').val(),
                                    bankName: $('#jinvoiceBox input[name="invoiceBank"]').val(),
                                    bankAccount: $('#jinvoiceBox input[name="invoiceBankNumber"]').val()
                                }
                                closeLayer();
                            }
                        }
                    });

                    // 不需要发票
                    var pass = true;
                    $invoiceBox.find('.cbx').on('click', function() {
                        var text = $(this).data('text');
                        if (text === '暂不需要') {
                            $invoiceBox.find('.submit').attr('type', 'button').addClass('jinvoiceDel');
                            $invoiceBox.find('.msg-box').html('').hide();
                            $invoiceBox.find('.ipt').removeClass('n-invalid');
                        } else {
                            $invoiceBox.find('.submit').attr('type', 'submit').removeClass('jinvoiceDel');
                        }

                        if (this.id === 'tax') {
                            $invoiceBox.find('.hide').show();
                        } else {
                            $invoiceBox.find('.hide').hide();
                        }
                    });

                    $invoiceBox.on('click', '.jinvoiceDel', function() {
                        closeLayer();
                        $invoice.html('<button type="button" class="btn btn-lgray jinvoiceAdd">新增发票</button>');
                        $invoiceBox.find('.submit').attr('type', 'submit').removeClass('jinvoiceDel');
                        return false;
                    });
                },
                // 新增收货地址
                addConsignee: function() {
                    var $consigneeBox = $('#jconsigneeBox');
                    var $consigneeForm = $("#consigneeForm");

                    $('.jaddConsignee').on('click', function() {
                        var total = $('#jconsigneeList').find('label').length;
                        if (total >= 10) {
                            $.notify({
                                type: 'warn',
                                title: '警告',
                                text: '收货地址不能超过10条!',
                                delay: 3e3
                            });
                            return false;
                        }
                        $consigneeForm[0].reset();
                        $('#pickArea').citys(); // 地区级联
                        layer.open({
                            area: ['540px'],
                            closeBtn: 1,
                            type: 1,
                            moveType: 1,
                            content: $consigneeBox,
                            title: '新建地址'
                        });
                    })
                    // 关闭弹层
                    $consigneeBox.on('click', '.cancel', function() {
                        layer.closeAll();
                    })

                    $('#consigneeForm').validator({
                        fields : {
                            consignee : '收货人: required; nickName',
                            tel : '手机号码: required; mobile',
                            areaId : '所在地区: required',
                            detail : '详细地址: required'
                        },
                        valid: function(form) {
                            if ( $(form).isValid() ) {
                                $.ajax({
                                    url: '/center/address/add',
                                    dataType: 'json',
                                    data: $(form).formSerialize(),
                                    success: function(result) {
                                        if (result.status=="y") {

                                            var consigneeName = $("#consigneeName").val();
                                            var consigneeMobile = $("#consigneeMobile").val();
                                            var add = $("#province").find("option:selected").text() +
                                                    $("#city").find("option:selected").text()+
                                                    $("#area").find("option:selected").text()+
                                                    $("#consigneeAddress").val();
                                            var html = '<label><input type="radio" name="addrHistoryId" class="cbx" value="'+result.info+
                                            '" checked>'+add +'</label>';
                                            $(".consignee .list").css("display","block");
                                            $(".consignee .extra").css("display","block");
                                            $(".consignee .empty").css("display","none");
                                            $("#jconsigneeList").append(html);
                                        }else{
                                            layer.msg(result.info, {icon: 5});
                                        }
                                    }
                                })
                                layer.closeAll();
                                //$("#areaFull").val($('#province option:selected').text() + $('#city option:selected').text() + $('#area option:selected').text());
                            }
                        }
                    });
                },
                //提交
                orderSubmit: function() {
                    $('#orderSubmit').on('click', function(e) {
                        var addrHistoryId = $("input[name='addrHistoryId']:checked").val();
                        if(!addrHistoryId){
                            layer.msg('请填写收货地址！', {icon: 5});
                            window.scrollTo(0, 0);
                            return false;
                        }
                        var userId = $("input[name='userId']:checked").val();
                        if(!userId){
                            layer.msg('请选择客户！', {icon: 5});
                            window.scrollTo(0, 0);
                            return false;
                        }
                        var $table=$("#orderList");
                        var commodityses=[];
                        $table.find("tbody tr").each(function(item){
                            var enId=$(this).find("input[data-name='enquiryCommodityId']").val();
                            var count=$(this).find(".amount").val();
                            var price=$(this).find(".price").val();
                            var commodity={};
                            commodity.id=enId;
                            commodity.amount=count;
                            commodity.myPrice = price;
                            commodityses.push(commodity);
                        })

                        var param=$("#orderSave").serializeObject();
                        param.commodityses=commodityses;
                        param.invoice = _global.v.invoice;
                        $.ajax({
                            type : 'POST',
                            url : '/center/order/save',
                            data: JSON.stringify(param),
                            contentType : 'application/json',
                            success : function(data) {
                                var status = data.status;
                                if (status == 'y') {
                                    // 代理商 付款界面
                                    window.location = "/center/pay/go/"+data.data;
                                }
                                else{
                                    $.notify({
                                        type: 'error',
                                        title:"提交订单错误",
                                        text: data.data,
                                        delay: 3e3
                                    });
                                }
                            }

                        });
                    })
                },
                // 计算价格
                computePrice: function() {
                    var $table = $('.fa-chart');

                    var formatPrice = function(val) {
                        return val.toFixed(2);
                    }

                    var total = function() {
                        var sum = 0,g =0;
                        $table.find('tbody tr').each(function() {
                            var t = 0,
                                    price = $(this).find('.price').val(),
                                    amount = $(this).find('.amount').val(),
                                    guidePrice = $(this).find('.amount').data("price"),
                                    $sum = $(this).find('.sum');

                            if (isNaN(price) || isNaN(amount)) {
                                return true; // continue
                            }
                            t = price * amount;
                            g += guidePrice* amount;
                            sum += t;
                            $sum.html(formatPrice(t));
                        })
                        $('#deposit').html(formatPrice(g));
                        $('#total').html(formatPrice(sum));
                    }

                    $table.on('blur', '.price', function() {
                        var val = this.value;

                        if (!/^\d+\.?\d*$/.test(val)) {
                            val = Math.abs(parseFloat(val));
                            if (isNaN(val)) {
                                val = '';
                            }
                            this.value = isNaN(val) ? '' : val;
                        }
                        total();
                    })
                    $table.on('blur', '.amount', function() {
                        var val = this.value;

                        if (val) {
                            val = (!isNaN(val = parseInt(val, 10)) && val) > 0 ? val : 1;
                            this.value = Math.max(val, 1);
                        } else {
                            this.value = 1;
                        }
                        total();
                    })

                    // 删除商品
                    $table.on('click', '.c-blue', function() {
                        var $tr = $(this).closest('tr'),
                                idx = $tr.index();
                        layer.confirm('确认删除吗？', function(index) {
                            $tr.remove();
                            layer.close(index);
                            total();
                        })
                    })

                    total();
                },
                warning: function() {
                    layer.open({
                        // title: '',
                        closeBtn: 0,
                        moveType: 1,
                        btn: ['返回询价单'],
                        content: '<p>您还没有绑定终端客户，暂时无法进行下单</p><p>如果您已提交终端客户资料，请联系客服人员帮您进行绑定。客服电话：0558-5120088。</p>',
                        yes: function(index) {
                            layer.close(index);
                            window.location.href = '/center/enquiry/record';
                        }
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