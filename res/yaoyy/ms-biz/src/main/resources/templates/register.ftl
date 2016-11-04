<!DOCTYPE html>
<html lang="en">
<head>
    <title>注册-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body ui-body-nofoot">
<header class="ui-header">
    <div class="title">快速注册</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<div class="ui-content">
    <div class="ui-form">
        <div class="logo">药优优</div>
        <form action="/user/register" method="post">
            <div class="item">
                <input type="tel" class="ipt" name="phone" id="mobile" placeholder="手机号" autocomplete="off">
                <span class="error"></span>
                <i class="mid"></i>
                <button type="button" class="send" id="send">发送验证码</button>
            </div>
            <div class="item">
                <input type="text" class="ipt" name="code" id="SMSCode" placeholder="验证码" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="password" class="ipt" name="password" id="password" placeholder="密码" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <button type="submit" class="ubtn ubtn-primary" id="submit">立即注册</button>
            </div>
        </form>

        <div class="ui-extra">
            <a href="/user/login">已有账号，直接登录</a>
            <i>|</i>
            <a href="/article/1">联系我们</a>
        </div>

    </div>
</div>
<#include "./common/footer.ftl"/>
<script>

    var _global = {
        v:{
            smsUrl:"/user/sendRegistSms"
        },
        fn: {
            init: function() {
                this.validator();
            },
            validator: function() {
                var self = this;
                $('#submit').on('click', function() {
                    return self.checkMobile() && self.checkSMSCode() && self.checkPassword();
                })

                self.SMSCodeEvent();
            },
            checkMobile: function() {
                var $el = $('#mobile'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入手机号码！').show();

                } else if (!_YYY.verify.isMobile(val)) {
                    $el.next().html('请输入有效的手机号！').show();

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
                    $el.next().html('请输入短信验证码！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            checkPassword: function() {
                var $el = $('#password'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入密码！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            },
            SMSCodeEvent: function() {
                var $send = $('#send'),
                        $mobile = $('#mobile'),
                        self = this;
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
                        url: _global.v.smsUrl,
                        dataType: 'json',
                        data: 'phone=' + $mobile.val(),
                        type: 'POST',
                        success: function(data) {
                            if (data.status === 200) {
                                $send.text(second + txt).prop('disabled', true);
                                lock();
                                popover(data.msg);
                            } else {
                                popover(data.msg);
                            }
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown) {
                            popover('网络连接超时，请您稍后重试!');
                        }
                    })
                }
                $send.prop('disabled', false).on('click', function() {
                    if(second === 0 && self.checkMobile()) {
                        second = 60; // 60秒倒计时
                        sendMSM();
                    }
                })
            }
        }
    }

    $(function(){
        _global.fn.init();
        <#if error?exists>popover('${error}');</#if>
    });

</script>
</body>
</html>