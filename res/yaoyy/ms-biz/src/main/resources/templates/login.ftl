<!DOCTYPE html>
<html lang="en">
<head>
    <title>登入-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body ui-body-nofoot">
<header class="ui-header">
    <div class="title">登录</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<div class="ui-content">
    <div class="ui-form">
        <div class="logo">药优优</div>
        <form action="/user/login" method="post">
            <div class="item">
                <input type="tel" class="ipt" name="phone" id="phone" placeholder="手机号" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <input type="password" class="ipt" name="password" id="password" placeholder="密码" autocomplete="off">
                <span class="error"></span>
            </div>
            <div class="item">
                <button type="submit" class="ubtn ubtn-primary" id="submit">登录</button>
            </div>
        </form>

        <div class="ui-extra">
            <a href="/user/loginSMS">短信验证码登录</a>
            <i>|</i>
            <a href="/user/register">快速注册</a>
        </div>
    </div>
</div>

<#include "./common/footer.ftl"/>
<script>

    var _global = {
        fn: {
            init: function() {
                this.validator();
            },
            validator: function() {
                var self = this;
                $('#submit').on('click', function() {
                    return self.checkMobile() && self.checkPassword();
                })
            },
            checkMobile: function() {
                var $el = $('#phone'),
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