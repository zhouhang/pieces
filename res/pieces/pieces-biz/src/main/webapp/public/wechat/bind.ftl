<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>绑定账号-上工好药</title>
</head>

<body>
<section class="ui-content">
    <form action="" id="myform">
        <div class="ui-form">
            <div class="item">
                <input type="tel" class="ui-ipt" name="phone" id="mobile" placeholder="手机号" value="" autocomplete="off">
                <span class="error"></span>
                <button type="button" class="send mid" id="send">发送验证码</button>
            </div>
            <div class="item item-b">
                <input type="text" class="ui-ipt" name="code" id="SMSCode" placeholder="验证码" value="" autocomplete="off">
                <span class="error"></span>
            </div>
        </div>

        <div class="ui-button mt30">
            <button type="button" id="submit" class="ubtn ubtn-red">绑定</button>
        </div>
    </form>
</section><!-- /ui-content -->

<#include "wechat/inc/footer_h5.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                var that = this;

                $('#submit').on('click', function() {
                    if (that.check()) {
                        $.ajax({
                            url: "/h5/bind",
                            data: {code:$("#SMSCode").val(),phone:$("#mobile").val()},
                            type: 'POST',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status=="y") {
                                    window.location.href = result.data;
                                } else {
                                    popover(result.info);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试！');
                            }
                        })
                    }
                })

                $('.ui-form').on('focus blur', '.ui-ipt', function() {
                    $(this).next().hide();
                })
                that.SMSCodeEvent();
            },
            check: function() {
                return this.checkMobile()
                        && this.checkSMSCode()
            },
            checkMobile: function() {
                var $el = $('#mobile'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入手机号！').show();

                } else if (!_YYY.verify.isMobile(val)) {
                    $el.next().html('请输入正确的手机号！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkSMSCode: function() {
                var $el = $('#SMSCode'),
                        val = $el.val();
                if (!val) {
                    $el.next().html('请输入验证码！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            SMSCodeEvent: function() {
                var $send = $('#send'),
                        that = this;
                second = 0,
                        wait = 0,
                        txt = '秒后重试';

                var lock = function() {
                    wait && clearInterval(wait);
                    wait = setInterval(function() {
                        second--;
                        $send.text(second + txt).prop('disabled', true);
                        if (second === 0) {
                            clearInterval(wait);
                            $send.text("获取验证码").prop('disabled', false);
                        }
                    }, 1e3);
                }
                var sendMSM = function() {
                    popover('验证码发送中，请稍后...!');
                    $.ajax({
                        url: '/gen/code/enquiry',
                        dataType: 'json',
                        data:  {phone:$("#mobile").val()},
                        success: function(data) {
                            if (data.status === 'y') {
                                $send.text(second + txt).prop('disabled', true);
                                lock();
                                popover(data.info);
                            } else {
                                popover(data.info);
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            popover('网络连接超时，请您稍后重试!');
                        }
                    })
                }
                $send.prop('disabled', false).on('click', function() {
                    if(second === 0 && that.checkMobile()) {
                        second = 60; // 60秒倒计时
                        sendMSM();
                    }
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>