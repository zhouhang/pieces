<!DOCTYPE html>
<html lang="en">
<head>
    <title>修改密码-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body-nofoot">
<header class="ui-header">
    <div class="title">修改密码</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<div class="ui-content">
    <div class="ui-form">
        <div class="logo">药优优</div>
        <form action="">
            <div class="weinxin">
                <span>手机号：</span>
                <em>${phone!}</em>
            </div>
            <div class="item">
                <input type="text" class="ipt" name="SMSCode" id="SMSCode" placeholder="验证码" autocomplete="off">
                <span class="error"></span>
                <i class="mid"></i>
                <button type="button" class="send" id="send">发送验证码</button>
            </div>
            <div class="item">
                <input type="password" class="ipt" name="password" id="password" placeholder="新密码" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <button type="submit" class="ubtn ubtn-primary" id="submit">修改密码</button>
            </div>
        </form>
    </div>
</div>
<#include "./common/footer.ftl"/>
<script>

    var _global = {
        v:{
            sendSMSUrl:"/center/sendResetPasswordSms",
            resetPasswordUrl:"/center/resetPassword"
        },
        fn: {
            init: function() {
                this.validator();
            },
            validator: function() {
                var self = this;
                $('#submit').on('click', function() {
                    if (self.checkSMSCode() && self.checkPassword()){
                       var code = $("#SMSCode").val();
                       var password = $("#password").val();
                        $.ajax({
                            url: _global.v.resetPasswordUrl,
                            dataType: 'json',
                            type:"post",
                            data:{code:code,password:password},
                            success: function(data) {
                                if (data.status == '200') {
                                    popover(data.msg);
                                    //TODO: 跳转到那个页面
                                    location.href="/center/index";

                                } else {
                                    popover(data.msg);
                                }
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试!');
                            }
                        })
                    }
                    return false;
                })
                self.SMSCodeEvent();
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
                        url: _global.v.sendSMSUrl,
                        dataType: 'json',
                        type:"post",
                        success: function(data) {
                            if (data.status === 200) {
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
                    if(second === 0) {
                        second = 60; // 60秒倒计时
                        sendMSM();
                    }
                })
            }
        }
    }

    $(function(){
        _global.fn.init();

    });

</script>
</body>
</html>