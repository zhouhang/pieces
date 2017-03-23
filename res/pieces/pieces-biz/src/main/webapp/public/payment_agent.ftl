<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>支付-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
    <link rel="stylesheet" href="${urls.getForLookupPath('/css/order.css')}" />
</head>

<body>
<#include "./inc/header-center.ftl"/>
<div class="wrap">
    <div class="payment">
        <div class="title">
            <h3>支付</h3>
        </div>
        <form action="" id="myform" method="post">
            <input type="hidden" name="orderId" value="${orderForm.id!}">
            <input type="hidden" name="userId" value="${orderForm.userId!}">
            <input type="hidden" name="agentId" value="${orderForm.agentId}">
            <input type="hidden" name="token" value="${token!}">
            <div class="cont">
                <div class="info">
                    <span>订单号：<em>${orderForm.code}</em></span>
                    <span>需支付保证金：<em class="red">&yen;${orderForm.deposit}</em></span>
                    <span>请在<em class="red">${orderForm.expireDate?date}</em>前完成付款。</span>
                </div>

                <div class="paytype">
                    <strong class="h2">支付货款</strong>
                    <label class="checked"><input type="radio" name="type" value="alipay" checked><img height="38" src="images/alipay.png" alt=""><i></i></label>
                    <#--<label><input type="radio" name="type" value="wxpay"><img height="38" src="images/wxpay.png" alt=""><i></i></label>-->
                    <label><input type="radio" name="type" value="bank">银行转账<i></i></label>
                    <label><input type="radio" name="type" value="bill">账期支付<i></i></label>
                </div>

                <div class="bank">
                <#list payAccountList as payAccount>
                    <p><label><input type="radio" value="${payAccount.id!}" name="bank" class="cbx"><em>${payAccount.receiveAccount!}</em> <em>开户行：</em>${payAccount.receiveBank!}  <em>账号：</em>${payAccount.receiveBankCard!}</label></p>
                </#list>
                    <span class="error"></span>
                    <button type="button" class="send" id="send">将账号发送到手机</button>
                    <strong class="h2">转账成功请上传支付凭证</strong>
                    <span class="up-img" id="upfile1"></span>
                    <span class="tips">请上传银行开具的打款凭证照片。</span>
                </div>
                <div class="bill">
                    <label>账期时间：</label>
                    <select name="billtime" id="billtime">
                        <option value="">请选择</option>
                        <option value="1">1个月</option>
                        <option value="3">3个月</option>
                    </select>
                    <span class="error"></span>
                    <p>账期支付需要通过平台审核</p>
                </div>
                <div class="button">
                    <button type="button" class="btn btn-red" id="stageSubmit">去付款</button>
                    <span class="error"></span>
                </div>
            </div>
        </form>
    </div>
</div>

<form action="/pay/alipay/" id="payform" target="_blank" method="POST" target="_blank">
    <input type="hidden" name="orderId" value="${orderForm.id!}">
</form>
<script type="temp" id="payModal">
    <div class="pay-modal">
        <div class="hd"><i class="fa fa-prompt"></i>请在新打开的页面中完成付款</div>
        <p>付款完成前请不要关闭此窗口</p>
        <p>完成付款后请点击下面按钮</p>
        <div class="op">
            <a class="btn btn-red success" href="/center/order/agent">已完成付款</a>
            <a class="btn btn-gray fail" href="/help/25">付款遇到问题</a>
        </div>
        <div class="tc">
            <span class="c-blue" id="changePaytype">选择其它支付方式</span>
        </div>
    </div>
    </script>
<#include "./inc/footer.ftl"/>
<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
<script src="js/validator/jquery.validator.js?local=zh-CN"></script>
<script src="${urls.getForLookupPath('/js/croppic.min.js')}"></script>
<script src="${urls.getForLookupPath('/js/jquery.form.js')}"></script>
<script>
    var _global = {
        v: {
        },
        fn: {
            init: function() {
                this.upfileImg();
                this.bindEvent();
                // this.submit();
            },
            upfileImg: function() {
                var $upfile = $('#upfile1');
                
                $('body').append('<div id="upload" style="position:fixed;bottom:0;left:0;width:0;height:0;visibility:hidden;"></div>');

                new Croppic('upload', {
                    uploadUrl:'gen/img/upload',
                    onBeforeImgUpload: function() {
                        $upfile.html('<span class="loader">图片上传中...</span>');
                    },
                    onAfterImgUpload: function(response){
                        $upfile.html('<img src="' + response.url + '"><i class="del" title="删除"></i><input type="hidden" name="img" value="' + response.url + '">');
                    },
                    onError: function(msg){
                        $upfile.html('<span class="upimg-msg">' + msg + '</span>');
                    }
                });
                
                // 删除图片
                $upfile.on('click', '.del', function() {
                    var $self = $(this);
                    layer.confirm('确认删除图片？', function(index){
                        $self.parent().empty().next(':hidden').val('');
                        layer.close(index);
                    });
                    return false;
                })

                $upfile.on('click', function() {
                    $('#upload').find('.cropControlUpload').trigger('click');
                })
            },
            bindEvent: function() {
                var $bank = $('.bank'),
                        $bill = $('.bill'),
                        $billtime = $('#billtime'),
                        $submit = $('#stageSubmit'),
                        model = $('#payModal').html(),
                        ptype = 'alipay';

                // 支付方式
                $('.paytype').on('click', 'input', function() {
                    ptype = this.value;
                    $(this).parent().addClass('checked').siblings().removeClass('checked');
                    $('.error').empty();
                    $bank[ptype === 'bank' ? 'show' : 'hide']();
                    $bill[ptype === 'bill' ? 'show' : 'hide']();
                    if (ptype === 'alipay' || ptype === "wxpay") {
                        $submit.html('去付款');
                    } else {
                        $submit.html('确认');
                    }
                })

                // 提交
                $submit.on('click', function() {
                    var pass = true;
                    if (ptype == null) {
                        $(this).next().html('请选择支付方式');
                        pass = false;
                    } else if (ptype === 'bank') {
                        if ($bank.find('.cbx:checked').length === 0) {
                            $bank.find('.error').html('请选择转账账号');
                            pass = false;
                        } else {
                            $bank.find('.error').empty();
                        }
                    } else if (ptype === 'bill') {
                        if ($billtime.val() == '') {
                            $billtime.next().html('请选择账期时间');
                            pass = false;
                        } else {
                            $billtime.next().empty();
                        }
                    }
                    if (pass) {
                        $(this).next().empty();
                        if (ptype === 'alipay' || ptype === 'wxpay') {
                            layer.open({
                                title: '支付货款',
                                area: ['490px', '295px'],
                                content: model,
                                type: 1,
                                cancel: function() {
                                    $('#changePaytype').off();
                                }
                            })

                            $('#changePaytype').on('click', function() {
                                layer.closeAll();
                            })
                            $('#payform').submit();

                        }
                        else if(ptype ==="bill"){
                            $("#myform").ajaxSubmit({
                                url:"/center/pay/bill",
                                success:function(result) {

                                    if(result.status=="y"){
                                        location.href="/center/pay/success?state=bill"
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
                        }
                        else if(ptype ==="bank"){
                            var bank=$('input:radio[name="bank"]:checked').val();
                            $("#myform").ajaxSubmit({
                                data:{'payAccountId':bank},
                                url:"/center/pay/create",
                                beforeSend: function() {
                                    var imgLength =  $('input[name="img"]').length;
                                    if(imgLength<=1){
                                        $.notify({
                                            type: 'error',
                                            title: '',
                                            text: '请至少上传一张支付凭证!',
                                            delay: 3e3
                                        });
                                        return false;
                                    }
                                },
                                success: function(result) {
                                    if(result.status=="y"){
                                        location.href="/center/pay/success?state=payment"
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
                        }
                    }
                    return false;
                })

                $billtime.on('change', function() {
                    $(this).next().empty();
                })
                $bank.find('.cbx').on('click', function() {
                    $bank.find('.error').empty();
                })
                this.SMSCodeEvent();
            },
            SMSCodeEvent: function() {
                var $send = $('#send'),
                        $bank = $('.bank'),
                        second = 0,
                        txt = ' 秒后重试';

                var lock = function() {
                    $send.html(second + txt).prop('disabled', true);
                    if (second === 0) {
                        $send.html('将账号发送到手机').prop('disabled', false);
                    } else {
                        second --;
                        setTimeout(function() {
                            lock();
                        }, 1e3)
                    }
                }
                var sendMSM = function() {
                    var bank=$('input:radio[name="bank"]:checked').val();
                    var orderId=$('input:hidden[name="orderId"]').val();
                    $.ajax({
                        url: '/center/pay/sendAccount',
                        data:{'payAccountId':bank,"orderId":orderId},
                        dataType: 'json',
                        type:"POST",
                        beforeSend: function() {
                            $send.html('短信发送中').prop('disabled', true);
                        },
                        success: function(data) {
                            if (data.status === 'y') {
                                lock();
                            } else {
                                alert(data.info);
                            }
                        },
                        error: function() {
                            $send.html('将账号发送到手机').prop('disabled', false);
                        }
                    })
                }

                // 发送到手机
                $send.prop('disabled', false).on('click', function() {
                    if ($bank.find('.cbx:checked').length === 0) {
                        $bank.find('.error').html('请选择转账账号');
                    } else {
                        $bank.find('.error').empty();
                        if(second === 0) {
                            second = 60; // 60秒倒计时
                            sendMSM();
                        }
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