<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付-饮片B2B</title>
    <link rel="stylesheet" href="css/order.css" />
</head>

<body>
    <#include "./inc/header-center.ftl"/>

    <div class="wrap">
        <div class="payment">
            <div class="title">
                <h3>支付</h3>
            </div>

            <div class="main">
                <dl>
                    <dt>订单号：</dt>
                    <dd>${orderForm.code!}</dd>

                    <dt>应付金额：</dt>
                    <dd>
                        <em>&yen;${orderForm.amountsPayable!}</em>
                        <span>剩余付款时间：9天23时20分</span>
                    </dd>

                    <dt>支付方式：</dt>
                    <dd id="jpayType">
                        <label><input type="radio" name="type" value="0" checked>现款支付</label>
                        <label><input type="radio" name="type" value="1">账期支付</label>
                    </dd>
                </dl>
                <dl>
                    <dt>平台收款帐号：</dt>
                    <dd>
                        <#list payAccountList as payAccount>
                            <p><label><input type="radio"  value="${payAccount.id!}" name="bank">${payAccount.receiveBank!} ${payAccount.receiveAccount!} ${payAccount.receiveBankCard!}</label></p>
                        </#list>
                    </dd>
                </dl>
                
                <div class="fa-form">
                    <form action="/center/pay/create" id="myform" method="post">

                    <div class="hd">填写付款信息</div>
                        <input type="hidden" name="orderId" value="${orderForm.id!}">
                        <input type="hidden" name="token" value="${token!}">
                        <div class="group">
                            <div class="txt">支付金额：</div>
                            <div class="cnt">
                                <input type="text" id="money" name="actualPayment" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">开户银行：</div>
                            <div class="cnt">
                                <input type="text" id="payBank" name="payBank" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">开  户  人：</div>
                            <div class="cnt">
                                <input type="text" id="payAccount" name="payAccount" autocomplete="off" value="" class="ipt">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">银行卡号：</div>
                            <div class="cnt">
                                <input type="text" id="payBankCard" name="payBankCard" autocomplete="off" value="" class="ipt" maxlength="23">
                                <div class="bank-tip" id="J_bank_tip" style="display:none;">
                                    <span class="icon-bank icon-CMB"></span>
                                    <span class="bank-type">信用卡</span>
                                </div>

                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">付款时间：</div>
                            <div class="cnt">
                                <input type="text" id="date" name="paymentTime" autocomplete="off" value="" class="ipt" onclick="laydate()">
                            </div>
                        </div>
                        <div class="group">
                            <div class="txt">支付凭证：</div>
                            <div class="cnt">
                                <span class="up-img" id="imgCrop"></span>
                                <em class="tips">请上传银行开具的打款凭证照片。</em>
                            </div>
                        </div>
                        <div class="group">
                            <button  id="cashSubmit" type="button" class="btn btn-red">确认</button>
                        </div>
                    </form>

                    <form action="" id="myform2">
                        <div class="hd">申请账期</div>
                        <div class="group">
                            <div class="txt">账期时间：</div>
                            <div class="cnt">
                                <select name="billtime" id="billtime" data-msg="请选择账期时间">
                                    <option value="">请选择</option>
                                    <option value="1">1个月</option>
                                    <option value="2">2个月</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="12">1年</option>
                                </select>
                            </div>
                        </div>
                        <div class="group">
                            <button type="submit" class="btn btn-red">确认</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    
    <div id="imgCropWrap"></div>

    <script src="js/layer/layer.js"></script>
    <script src="js/laydate/laydate.js"></script>
    <script src="js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="js/croppic.min.js"></script>
    <script src="/js/jquery.form.js"></script>

    <script>
    	var _global = {
    		v: {
    		},
    		fn: {
    			init: function() {
                    this.formInit();
                    this.goodsImg();
                    this.payType();

                    $("#cashSubmit").click(function(){
                        var bank=$('input:radio[name="bank"]:checked').val();
                        if(!bank){
                            $.notify({
                                type: 'error',
                                title: '',
                                text: '请选择平台收款账号!',
                                delay: 3e3
                            });
                            return false;
                        }

                        $("#myform").ajaxSubmit({
                            data:{'payAccountId':bank},
                            beforeSend: function() {
                                if(!$("#myform").isValid()){
                                    return false;
                                }
                            },
                            success: function(result) {
                                if(result.status=="y"){
                                    location.href="/center/pay/success"
                                }else{
                                    $.notify({
                                        type: 'error',
                                        title: '错误信息',   // 不允许的文件类型
                                        text: result.info,     //'支持 jpg、jepg、png、gif等格式图片文件',
                                        delay: 3e3,
                                        call:function(){
                                            setTimeout(function () {
                                                location.href = '/center/order/list';
                                            }, 3e3);
                                        }
                                    });
                                }
                            }
                        })
                    })

    			},
                formInit: function() {
                    var self = this;
                    $('#myform').validator({
                        rules: {
                            bankNumber: [/^\d{12,19}$/, '银行卡号是12-19位数字'],
                            money: [/^-?\d+\.{0,}\d{0,}$/, '请输入正确的金额']
                        },
                        fields: {
                            actualPayment: 'required, money',
                            payBank: 'required',
                            payAccount: 'required',
                            payBankCard: 'required, bankNumber',
                            paymentTime: 'required',
                            bank: 'required'
                        }
                    });

                    $('#myform2').validator({
                        fields: {
                            billtime: 'required'
                        }
                    });

                    // 金额
                    $('#freightPrice').on('keyup', function(e) {
                        var val = this.value;
                        if (!/^\d+\.?\d*$/.test(val)) {
                            val = Math.abs(parseFloat(val));
                            this.value = isNaN(val) ? '' : val;
                        }
                    });
                },
                goodsImg: function() {
                    var self = this,
                        $myform = $('#myform');

                    // 删除图片
                    $myform.on('click', '.del', function() {
                        var $self = $(this);
                        layer.confirm('确认删除图片？', {
                            btn: ['确认','取消'] //按钮
                        }, function(index){
                            $self.parent().remove();
                            layer.close(index);
                        });
                        return false;
                    })
                    // 点击图片看大图
                    $myform.on('click', 'img', function() {
                        var url = this.src;
                        window.open(url);
                        return false;
                    })
                    this.upImg();
                },
                upImg: function() {
                    var options = {
                        uploadUrl:'gen/img/upload',
                        customUploadButtonId: 'imgCrop',
                        onAfterImgUpload: function(response){
                            cropModal.destroy();
                            $('#imgCrop').before('<span class="up-img"><img src="' + response.url + '" title="点击图片看大图" /><i class="del" title="删除"></i><input type="hidden" name="img" value="' + response.url + '"></span>');
                            cropModal = new Croppic('imgCropWrap', options);
                        },
                        onError:function(msg){
                            $.notify({
                                type: 'error', 
                                text: msg.info,     //'支持 jpg、jepg、png、gif等格式图片文件',
                                delay: 3e3
                            });
                        }
                    }
                    var cropModal = new Croppic('imgCropWrap', options);
                },
                // 支付方式切换
                payType: function() {
                    var $el = $('#jpayType');

                    var changeForm = function($el) {
                        var value = $el.val();

                        if (value === '0') {
                            $el.closest('dl').next().show();
                            $('#myform').show();
                            $('#myform2').hide();

                        } else {
                            $el.closest('dl').next().hide();
                            $('#myform').hide();
                            $('#myform2').show();
                        }
                    }

                    $el.on('click', 'input', function() {
                        changeForm($(this));
                    });
                    changeForm($el.find('input:checked'));
                }
    		}
    	}
    	$(function() {
    		_global.fn.init();
    	})
    </script>
</body>
</html>