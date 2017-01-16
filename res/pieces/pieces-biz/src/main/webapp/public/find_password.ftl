<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
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
                    <li class="curr">
                        <span>1</span>
                        <strong>填写账户名</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li>
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
				<form id="myform">
					<div class="group">
						<div class="txt">
							<i>*</i>账户名：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="username" id="username" placeholder="用户名/手机号"
								data-msg-required="请输入用户名/手机号">
						</div>
					</div>
                    <div class="group">
                        <div class="txt">
                            <i>*</i>验证码：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt ipt-code" value="" autocomplete="off" name="code" id="code" placeholder="请输入验证码">
                            <img src="/gen/captcha" class="img">
                        </div>
                    </div>

                    <div class="ft">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide">下一步</button>
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
                init: function () {
                    this.formValidate();
                },
                formValidate: function () {
                    $('#myform').validator({
                        fields: {
                            username: 'required',
                            code: 'required'
                        },
                        valid: function (form) {
                            myfromValid = this;
                            if ($(form).isValid()) {
                                $.ajax({
                                    url: "/user/findpwd/stepone",
                                    data: $(form).formSerialize(),
                                    type: "POST",
                                    success: function (data) {
                                        var status = data.status;
                                        var info = data.info;
                                        if (status == 'y') {
                                            window.location.href = '/user/findpwd/steptwo';
                                        }
                                        if (status == '10001') {
                                            myfromValid.showMsg("#username", {
                                                type: "error",
                                                msg: info
                                            })
                                            return;
                                        }
                                        if (status == '10002') {
                                            myfromValid.showMsg("#code", {
                                                type: "error",
                                                msg: info
                                            })
                                            return;
                                        }
                                    }
                                });
                                return false;
                            }
                        }
                    })

                    // 换一张验证码
                    $('.img').on('click', function () {
                        this.src = '/gen/captcha?id=' + (new Date).getTime()
                    })
                }
            }
        }
        $(function () {
            _global.fn.init();
        })
    </script>
</body>
</html>