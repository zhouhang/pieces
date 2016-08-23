<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>订单-饮片B2B</title>
    <link rel="stylesheet" href="/css/order.css" />
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
                <!-- start 收货信息 -->
                <form id="orderSave" action="/center/order/save" method="post">
                <div class="group">
                    <div class="hd">
                        <h3>收货信息</h3>
                    </div>
                    
                    <div class="consignee">
                        <#if shippingAddressCurrent??>
                        <ul>
                            <li id= "shippingAddressCurrent">
                            	<input type="hidden" name="addrHistoryId" id="addrHistoryId" value="${shippingAddressCurrent.id}">
                            	
                                <p><span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>${shippingAddressCurrent.consignee}</p>
                                <p><span>联系方式：</span>${shippingAddressCurrent.tel}</p>
                                <p><span>收货地址：</span>${shippingAddressCurrent.fullAdd}</p>
                                <div class="extra">
                                    <a href="javascript:;" class="c-blue" id="jchooseConsignee">切换地址</a>
                                    <div class="btn btn-lgray jaddConsignee">新增收货地址</div>
                                </div>
                            </li>
                        </ul>
                        <#else>
                        <div class="empty">
                            <div class="btn btn-lgray jaddConsignee">新建收货地址</div>
                        </div> 
                        </#if>
                    </div>
                </div><!-- end 收货信息 -->

                <!-- start 送货清单 -->
                <div class="group">
                    <div class="hd cf">
                        <a href="#" class="c-blue fr">返回询价单</a>
                        <h3>送货清单</h3>
                    </div>

                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th width="180">商品名称</th>
                                    <th width="80">切制规格</th>
                                    <th width="80">等级</th>
                                    <th width="110">产地</th>
                                    <th width="110">期望交货日期</th>
                                    <th width="110">数量<em>（公斤）</em></th>
                                    <th width="110">单价<em>（元/公斤）</em></th>
                                    <th>小计<em>（元）</em></th>
                                    <th width="200">运费<em>（元）</em></th>
                                </tr>
                            </thead>
                            <tfoot></tfoot>
                            <tbody>
                            <#list enquiryCommoditysList as enquiryCommoditys>	
                                <tr>
                                    <td>${enquiryCommoditys.commodityName}</td>
                                    <td>${enquiryCommoditys.specs}</td>
                                    <td>${enquiryCommoditys.level}</td>
                                    <td>${enquiryCommoditys.origin}</td>
                                    <td>${enquiryCommoditys.expectDate?date}</td>
                                    <td>${enquiryCommoditys.amount}</td>
                                    <td>${enquiryCommoditys.myPrice}</td>
                                    <td name="commoditysPrice">${enquiryCommoditys.amount * enquiryCommoditys.myPrice}</td>
                                    <#if enquiryCommoditys_index == 0>
                                    	<td rowspan="${enquiryCommoditysList?size}"><input class="ipt" name="shippingCosts" id="freightPrice" type="text" placeholder="请填写询价时协商好的运费"></td>
                                    </#if>
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
                        <textarea name="remark" id="remark" cols="30" rows="10" class="mul" placeholder="请填写本次采购另外需要注意的事项。"></textarea>
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
                    <input type="hidden" id="invoiceType" name="type" value="">
                    <input type="hidden" id="invoiceName" name="name" value="">
                    <input type="hidden" id="invoiceContent" name="content" value="">
                </div><!-- end 发票信息 -->
                <input type="hidden" name="token" id="token" value="${token}">
                <input type="hidden" name="commodityIds" value="${commodityIds}">
                </form>
                <!-- start 小计 -->
                <div class="summary">
                    <div class="item">
                        <span>商品合计：</span>
                        <em id="totalPriceDisplay">￥${totalPrice}</em>
                    </div>
                    <div class="item">
                        <span>运&#12288;&#12288;费：</span>
                        <em id="freightPriceDisplay">￥0.0</em>
                    </div>
                    <div class="item">
                        <span>实际应付：</span>
                        <em id="priceDisplay" class="price">￥${totalPrice}</em>
                    </div>
                </div><!-- end 小计 -->
                
                <!-- start 提交 -->
                <div class="submit">
                    <button id="orderSubmit" type="button" class="btn btn-red">确认订购</button>
                </div><!-- end 提交 -->
            </div>
        </div>
    </div><!-- order-success end -->


    <#include "./inc/footer.ftl"/>
    
    <!-- start 新增发票 -->
    <div class="fa-form fa-form-layer" id="jinvoiceBox">
        <form action="" id="invoiceForm">
            <div class="group">
                <div class="txt">
                    <span>发票类型：</span>
                </div>
                <div class="cnt">
                    <label><input type="radio" name="invoice" value="0" class="cbx" data-text="普通发票">普通发票</label>
                    <label><input type="radio" name="invoice" value="1" class="cbx" data-text="增值税专用发票">增值税专用发票</label>
                    <label><input type="radio" name="invoice" value="2" class="cbx" data-text="暂不需要">暂不需要</label>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <span>单位名称：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="companyName" class="ipt" autocomplete="off" placeholder="提示：若为个人客户，请在该项填写“个人”">
                    <span class="error"></span>
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <span>发票内容：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="invoiceDetail" class="ipt" autocomplete="off" placeholder="">
                    <span class="error"></span>
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
            <div class="group fl">
                <div class="txt">
                    <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="consignee" id="consigneeName" class="ipt ipt-short" autocomplete="off" placeholder="" data-msg-nickName="只能输入中文、英文，长度2-50" maxlength="50">
                </div>
            </div>

            <div class="group fr">
                <div class="txt">
                    <span>手机号码：</span>
                </div>
                <div class="cnt">
                    <input type="text" name="tel" id="consigneeMobile" class="ipt ipt-short" autocomplete="off" placeholder="">
                    <span class="error"></span>
                </div>
            </div>

            <div class="cf"></div>

            <div class="group">
                <div class="txt">
                    <span>所在地区：</span>
                </div>
                <div class="cnt">
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

    <div class="consignee consignee-list" id="jconsigneeList">
        <ul>
        	<#list shippingAddressList as shippingAddress>
            <li <#if shippingAddress.id=shippingAddressCurrent.id>class="current"</#if>>
            	<input type="hidden" name="shippingAddressId" value="${shippingAddress.id}">
                <p><span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>${shippingAddress.consignee}</p>
                <p><span>联系方式：</span>${shippingAddress.tel}</p>
                <p><span>收货地址：</span>${shippingAddress.fullAdd}</p>
                <#if shippingAddress.isDefault>
	                <div class="default">
	                    <span class="c-red">默认地址</span>
	                </div>
                </#if>
            </li>
            </#list>
        </ul>

        <div class="button">
            <button type="button" class="btn btn-red submit">保存</button>
            <button type="button" class="btn btn-gray cancel">取消</button>
        </div>
    </div>
	

    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="/js/jquery.form.js"></script>
    <script src="js/area.js"></script>
    <script>
    	var _global = {
    		v: {
    			totalPrice : ${totalPrice}
    		},
    		fn: {
    			init: function() {
                    this.addInvoice();
                    this.addConsignee();
                    this.chooseConsignee();
                    this.freightPrice();
                    this.orderSubmit();
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

                        layer.open({
                            area: ['600px'],
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
                        });
                        $invoiceBox.find('input[name="companyName"]').val(info.eq(1).html());
                        $invoiceBox.find('input[name="invoiceDetail"]').val(info.eq(2).html());

                        layer.open({
                            area: ['600px'],
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
                        fields : {
                            invoice: '发票类型: checked',
                            companyName : '单位名称: required',
                            invoiceDetail : '发票内容: required'
                        },
                        valid: function(form) {
                            var myfromValid = this;
                            if ( $(form).isValid() ) {
                                var html = ['<span class="tips">您当前的发票信息如下：</span>'];
                                html.push('<em>', $invoiceBox.find('input[name="invoice"]:checked').data('text'), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="companyName"]').val(), '</em>');
                                html.push('<em>', $invoiceBox.find('input[name="invoiceDetail"]').val(), '</em>');
                                html.push('<a href="javascript:;" class="c-blue jinvoiceEdit">修改</a>');
                                $invoice.html(html.join(''));
								
								$("#invoiceType").val($invoiceBox.find('input[name="invoice"]:checked').val());
								$("#invoiceName").val($invoiceBox.find('input[name="companyName"]').val());
								$("#invoiceContent").val($invoiceBox.find('input[name="invoiceDetail"]').val());
								
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
                    });

                    $invoiceBox.on('click', '.jinvoiceDel', function() {
                        closeLayer();
                        $invoice.html('<div class="btn btn-lgray jinvoiceAdd">新增发票</div>');
                        $invoiceBox.find('.submit').attr('type', 'submit').removeClass('jinvoiceDel');
                        return false;
                    })

                },
                // 新增收货地址
                addConsignee: function() {
                    var $consigneeBox = $('#jconsigneeBox');
                    $('.jaddConsignee').on('click', function() {
                        layer.open({
                            area: ['600px'],
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
                            var myfromValid = this;
                            if ( $(form).isValid() ) {
                            	//form.submit();
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
                                    var isDefault = $("#isDefault").is(':checked')
                                    var html = '<li class="current">'+
									            	'<input type="hidden" name="shippingAddressId" value="'+result.info+'">'+
									                '<p><span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>'+consigneeName+'</p>'+
									                '<p><span>联系方式：</span>'+consigneeMobile+'</p>'+
									                '<p><span>收货地址：</span>'+add+'</p>';
									if(isDefault){
										html = html + '<div class="default">'+
										              '<span class="c-red">默认地址</span>'+
										              '</div>';
									}       
										            
									    html = html +  '</li>';
									    $("#jconsigneeList").find("ul li").removeClass("current");
                                        $("#jconsigneeList").find("ul").append(html);
                                         
		                            	html = '<input type="hidden" name="addrHistoryId" id="addrHistoryId" value="'+result.info+'">'+
		                                '<p><span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：</span>'+consigneeName+'</p>'+
		                                '<p><span>联系方式：</span>'+consigneeMobile+'</p>'+
		                                '<p><span>收货地址：</span>'+add+'</p>'+
		                                '<div class="extra">'+
		                                    '<a href="javascript:;" class="c-blue" id="jchooseConsignee">切换地址</a>'+
		                                    '<div class="btn btn-lgray jaddConsignee">新增收货地址</div>'+
		                                '</div>';
			                            $("#shippingAddressCurrent").html(html);
			                            _global.fn.addConsignee();
			                            _global.fn.chooseConsignee();
                                    }
                                }
                            })
                            	layer.closeAll();
                            } 
                        }
                    });
                },
                // 切换地址
                chooseConsignee: function() {
                    var $consigneeList = $('#jconsigneeList');
                    $('#jchooseConsignee').on('click', function() {
                        layer.open({
                            area: ['600px'],
                            closeBtn: 1,
                            type: 1,
                            moveType: 1,
                            content: $consigneeList,
                            title: '选择地址'
                        });
                    })

                    // 关闭弹层
                    $consigneeList.on('click', '.cancel', function() {
                        layer.closeAll();
                    })

                    $consigneeList.on('click', 'li', function() {
                        $(this).addClass('current').siblings().removeClass('current');
                    })
                    
                    // 关闭弹层
                    $consigneeList.on('click', '.submit', function() {
                    	var currentid = $consigneeList.find(".current").find("input[name='shippingAddressId']").val();
                    	var $p = $consigneeList.find(".current").find("p")
                    	
                    	var html = '<input type="hidden" name="addrHistoryId" id="addrHistoryId" value="'+currentid+'">'+
                        '<p>'+$($p[0]).html()+'</p>'+
                        '<p>'+$($p[1]).html()+'</p>'+
                        '<p>'+$($p[2]).html()+'</p>'+
                        '<div class="extra">'+
                        '<a href="javascript:;" class="c-blue" id="jchooseConsignee">切换地址</a>'+
                        '<div class="btn btn-lgray jaddConsignee">新增收货地址</div>'+
                        '</div>';
                        $("#shippingAddressCurrent").html(html);
                        _global.fn.addConsignee();
			            _global.fn.chooseConsignee();
                        layer.closeAll();
                    })

                },
                // 运费
                freightPrice: function() {
                    // 单价
                    $('#freightPrice').on('keyup', function(e) {
                        var val = this.value;
                        if (!/^\d+\.?\d*$/.test(val)) {
                            val = Math.abs(parseFloat(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    }).on('blur', function(){
                    	var price = this.value == '' ? 0 : this.value;
                        $('#freightPriceDisplay').html("&yen;" +price);
                        $('#priceDisplay').html("&yen;" + (parseFloat(price) + parseFloat(_global.v.totalPrice)).toFixed(2));
                    });
                },
                //提交
                orderSubmit: function() {
                    $('#orderSubmit').on('click', function(e) {
                    	var addrHistoryId = $("#addrHistoryId").val();
                    	var freightPrice = $("#freightPrice").val();
                    	if(addrHistoryId=='undefind'||addrHistoryId==""){
                    		layer.msg('请填写收货地址！', {icon: 5});
                    		return false;
                    	}
                    	if(freightPrice==''){
                    		layer.msg('请填写运费！', {icon: 5});
                    		return false;
                    	}
                    	$("#orderSave").submit();
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