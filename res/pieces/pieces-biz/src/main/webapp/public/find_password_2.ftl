<!DOCTYPE html>
<html lang="en">
<head>
	<#include "./inc/meta.ftl"/>
    <title>找回密码-上工好药</title>
</head>

<body>


	<!-- header start -->
	<div class="header header-shadow">
		<div class="wrap">
			<div class="logo">
				<a href="/">上工好药首页</a>
			</div>
			<div class="title">
				<h1>找回密码</h1>
			</div>
		</div>
	</div>
	<!-- header end -->


    <!-- find password start -->
    <div class="reg-box">
        <div class="wrap">
            <div class="fa-form">

                <ul class="fa-guide">
                    <li>
                        <span>1</span>
                        <strong>填写账户名</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li class="curr">
                        <span>2</span>
                        <strong>验证身份</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li>
                        <span>3</span>
                        <strong>设置新密码</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li>
                        <span>4</span>
                        <strong>完成</strong>
                    </li>
                </ul>
                <form action="" id="myform">
                    <div class="group">
                        <div class="txt">
                            用户名：
                        </div>
                        <div class="val">
                            <span>${username!}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            手机号：
                        </div>
                        <div class="val">
                            <span>${phone!}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>验证码：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="code" id="code" placeholder="请填写短信验证码">
                            <button type="button" class="btn btn-gray btn-inside" id="send">获取验证码</button>
                            <span class="Validform_wrong" style="display:none;"></span>
                        </div>
                    </div>

                    <div class="ft">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide" id="submit">下一步</button>
                        </div>
                    </div>
                </form>
            </div>


            <div class="side">
                <div class="hd">
                    已有账号<a class="btn btn-gray" href="/user/login">请登录</a>
                </div>
                <div class="bd">
                    <dl>
                        <dt>平台流程提示：</dt>
                        <dd>注册平台账户</dd>
                        <dd>提交企业资质</dd>
                        <dd>在线选购商品</dd>
                        <dd>提交询价清单</dd>
                        <dd>选择合适报价</dd>
                        <dd>签订框架合同</dd>
                        <dd>等待平台发货</dd>
                        <dd>验收无误收货</dd>
                        <dd>网上对账付款</dd>
                        <dd>
                            <a href="/help/15">查看平台详细教程</a>
                        </dd>
                    </dl>
                </div>
                <div class="ft">
                    <p>在线咨询：</p>
                    <p>
                        <i class="fa fa-tel"></i><span>0558-5120088</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- find password end -->


	<!-- footer start -->
	<#include "./inc/footer.ftl"/>
    <!-- footer end -->
	<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
	<script src="/js/jquery.form.js"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                    this.SMSCode();
					this.formValidate();
                },
                formValidate: function () {
                    $('#myform').validator({
                        fields: {
                            code: 'required'
                        },
                        valid: function (form) {
                            myfromValid = this;
                            if ($(form).isValid()) {
                                $.ajax({
                                    url: "/user/findpwd/steptwo",
                                    data: $(form).formSerialize(),
                                    type: "POST",
                                    success: function (data) {
                                        var status = data.status;
                                        var info = data.info;
                                        if (status == 'y') {
                                            window.location.href = '/user/findpwd/stepthree';
                                        }else {
                                            myfromValid.showMsg("#code", {
                                                type: "error",
                                                msg: info
                                            })
                                        }
                                    }
                                });
                                return false;
                            }
                        }
                    })
                },
                SMSCode: function() {
                    var $send = $('#send'),
                            $msg = $send.next(),
                            self = this;
                    wait = 0,
                            txt = ' 秒后重试';

                    var showMsg = function(msg) {
                        if (msg) {
                            $msg.html(msg).show();
                        } else {
                            $msg.hide();
                        }
                    }

                    var lock = function() {
                        $send.text(wait + txt).prop('disabled', true);
                        wait--;
                        if (wait === 0) {
                            $send.text("获取验证码").prop('disabled', false);
                        } else {
                            setTimeout(function() {
                                lock(wait);
                            }, 1e3);
                        }
                    }

                    var sendMSM = function() {
                        $.ajax({
                            url: '/gen/find/code',
                            dataType: 'json',
                            beforeSend: function() {
                                $send.text('发送中...').prop('disabled', true);
                            },
                            success: function(data) {
                                if (data.status === 'y') {
                                    lock();
                                    showMsg();
                                } else {
                                    wait = 0;
                                    $send.text('获取验证码').prop('disabled', false);
                                    showMsg('发送失败');
                                }
                            },
                            error: function() {
                                wait = 0;
                                $send.text('获取验证码').prop('disabled', false);
                            }
                        })
                    }
                    $send.prop('disabled', false).on('click', function() {
                        if(wait === 0) {
                            wait = 60; // 60秒倒计时
                            sendMSM();
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