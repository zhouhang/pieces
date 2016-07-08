<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>登录-boss-饮片B2B</title>
</head>

<body>
    <div class="login-box">
        <div class="title"><strong>药优优</strong>电子商务管理系统</div>
        <div class="form">
            <form id="loginForm" action="login" method="post">
                <div id="msg" class="msg"></div>

                <div class="group">
                    <div class="txt">
                        <i class="fa fa-people"></i>
                    </div>
                    <div class="cnt">
                        <input type="text" placeholder="用户名" id="username" name="username" autocomplete="off" value="" class="ipt">
                    </div>
                </div>

                <div class="group">
                    <div class="txt">
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="cnt">
                        <input type="password" placeholder="密码" id="password" name="password" autocomplete="off" value="" class="ipt">
                    </div>
                </div>

                <div class="button">
                    <button id="submit" class="btn btn-red" type="button">登 录</button>
                </div>
            </form>
        </div>
    </div>

    <script src="js/validform.min.js"></script>
    <script src="js/jquery.form.js"></script>

</body>

<script type="text/javascript">
    var loginPage = {
        v: {
            $username: $('#username'),
            $password: $('#password'),
            $submit: $('#submit'),
            $msg: $('#msg')
        },
        fn: {
            init: function() {
                this.bindEvent();

                $("#submit").click(function(){
                    loginPage.fn.login();
                })

            },
            // 错误提示
            showMsg: function(msg) {
                if (msg) {
                    loginPage.v.$msg.html('<i class="fa fa-prompt"></i> ' + msg);
                } else {
                    loginPage.v.$msg.html('');
                }
            },
            checkUsername: function() {
                var msg = loginPage.v.$username.val() ? '' : '请输入用户名';
                this.showMsg(msg);
                return msg;
            },
            checkPassword: function() {
                var msg = loginPage.v.$password.val() ? '' : '请输入密码';
                this.showMsg(msg);
                return msg;
            },
            checkForm: function() {
                var c2 = this.checkPassword();
                var c1 = this.checkUsername();

                if (c2 || c1) {
                    this.showMsg(c1 && c2 ? '请输入用户名和密码' : c1 + c2);
                    return false;
                }
                this.showMsg('');
                return true;
            },
            bindEvent: function() {
                var self = this;
                loginPage.v.$username.on('blur', function() {
                    self.checkUsername();
                    $(this).closest('.group').removeClass('on');
                }).on('focus', function() {
                    $(this).closest('.group').addClass('on');
                });

                loginPage.v.$password.on('blur', function() {
                    self.checkPassword();
                    $(this).closest('.group').removeClass('on');
                }).on('focus', function() {
                    $(this).closest('.group').addClass('on');
                });

                loginPage.v.$submit.on('click', function() {
                    return self.checkForm();
                });

            },
            login:function(){
                $("#loginForm").ajaxSubmit({
                    dataType: "json",
                    success: function (result) {
                        if(result.status=="y"){
                            location.href="user/index"
                        }else{
                            loginPage.fn.showMsg(result.info)
                        }
                    }
                });
            }
        }
    }

    $(function() {
        loginPage.fn.init();
    })

</script>
</html>