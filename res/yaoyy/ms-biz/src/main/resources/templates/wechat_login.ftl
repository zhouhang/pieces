<!DOCTYPE html>
<html lang="en">
<head>
    <title>绑定手机号-药优优</title>
    <#include "./common/meta.ftl"/>
</head>
<body class="ui-body ui-body-nofoot">
<header class="ui-header">
    <div class="title">绑定手机号</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<div class="ui-content">
    <div class="ui-notice">
        通过绑定手机号可以查询您的寄样跟踪信息和采购单等业务！
    </div>
    <div class="ui-form">
        <form id="wechatLoginForm" method="post" action="/wechat/bind">
            <div class="weinxin">
                <span>微信号：</span>
                <img src="${headImgUrl!}" width="30" height="30" alt="">
                <em>${nickname!}</em>
            </div>
            <input type="hidden" name="callUrl" value="${call!}">
            <input type="hidden" name="openId" value="${openId!}">
            <input type="hidden" name="nickname" value="${nickname!}">
            <input type="hidden" name="headImgUrl" value="${headImgUrl!}">
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
                <a  href="javascript:;" class="ubtn ubtn-primary" id="submit">绑定</a>
            </div>
        </form>

        <div class="ui-extra">
            <a href="login.html">已有账号，直接登录</a>
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
                    if(self.checkMobile() && self.checkSMSCode()){

                        $.ajax({
                            url: "/wechat/bind",
                            type:'POST',
                            dataType: 'json',
                            data: $("#wechatLoginForm").serialize(),
                            success: function(data) {
                                if (data.status == '200') {
//                                    popover(data.msg);
                                    location.href=data.data;
                                } else {
                                    popover(data.msg);
                                }
                            },
                            error: function(XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试!');
                            }
                        })


                    }
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
                        type:'POST',
                        dataType: 'json',
                        data: 'phone=' + $mobile.val(),
                        success: function(data) {
                            if (data.status == '200') {
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

    });


</script>
</body>
</html>