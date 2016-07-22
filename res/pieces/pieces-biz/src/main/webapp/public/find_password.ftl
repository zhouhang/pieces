<!DOCTYPE html>
<html lang="en">
<head>
	<#include "./inc/meta.ftl"/>
    <title>找回密码-饮片B2B</title>
</head>
<body>


	<!-- header start -->
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="home.html">饮片B2B首页</a>
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
				<form action="/findPasswordOne" id="myform">
					<div class="group">
						<ul class="fa-guide">
							<li class="curr"><span>1</span> <strong>验证手机</strong> <i
								class="fa fa-chevron-right"></i></li>
							<li><span>2</span> <strong>设置新密码</strong></li>
						</ul>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>用户名：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="username" id="username" placeholder="已注册的用户名"
								data-msg-required="请输入用户名">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>手机号：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="mobile" id="mobile" placeholder="用户名绑定的联系人手机号"
								data-msg-required="请输入联系人手机号码"
								data-msg-mobile="请输入正确的手机号码">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>验证码：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="mobileCode" id="mobileCode" placeholder="手机短信收到的验证码"
								data-msg-required="请输入正确的验证码">
							<button type="button" class="btn btn-gray btn-inside"
								id="getMobileCode">获取验证码</button>
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
					已有账号<a class="btn btn-gray" href="login.html">请登录</a>
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
							<a href="helper.html">查看平台详细教程</a>
						</dd>
					</dl>
				</div>
				<div class="ft">
					<p>在线咨询：</p>
					<p>
						<i class="fa fa-tel"></i><span>400-123-1234</span>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- find password end -->


	<!-- footer start -->
	<#include "./inc/footer.ftl"/>
    <!-- footer end -->

	<script src="/js/jquery.min.js"></script>
	<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
	<script src="/js/jquery.form.js"></script>
	<script>
		$(function() {
			
			$('#myform').validator({
				fields : {
					username : '用户名: required;username;',
					mobile : '手机号码: required;mobile',
					mobileCode : '验证码: required'
				},
				valid : function(form) {
					var myfromValid = this;
					if ($(form).isValid()) {
						$.ajax({
							url : "/user/findpwd/stepone",
							data : $(form).formSerialize(),
							type : "POST",
							success : function(data) {
								var status = data.status; 
								var info = data.info; 
								if (status == 'y') {
									window.location.href = '/user/findpwd/steptwo?userName='
											+ $('#username').val();
									return;
								}
								if (status == '10001') {
									myfromValid.showMsg("#username", {
									    type: "error",
									    msg: info
									})
									return;
								}
								if (status == '10002') {
									myfromValid.showMsg("#mobile", {
									    type: "error",
									    msg: info
									})
									return;
								}
								if (status == '10003') {
									myfromValid.showMsg("#mobileCode", {
									    type: "error",
									    msg: "验证码错误，请重新输入"
									})
									return;
								}
							}
						});
					}
				}
		})

		var $mobile = $('#mobile'), $getMobileCode = $('#getMobileCode'), timeout = 0, timer = 0, delay = 60, txt = '秒后重试'

		var _clock = function() {
			timer && clearInterval(timer);
			timer = setInterval(function() {
				timeout--;
				$getMobileCode.text(timeout + txt).prop('disabled', true);
				if (timeout === 0) {
					clearInterval(timer);
					$getMobileCode.text('获取验证码').prop('disabled', false);
				}
			}, 1e3);
		}
		//验证码
		var _sendMobileCode = function() {
			$.ajax({
				type : 'POST',
				url : '/gen/code',
				data : {
					contactMobile : $mobile.val()
				},
				dataType : 'json',
				success : function(data) {
					if (typeof data.ok === 'string') {
						timeout = delay;
						_clock();
						$getMobileCode.text(timeout + txt).prop('disabled',
								true);
					} else if (typeof data.error === 'string') {
						clearInterval(timer);
						$getMobileCode.text('获取验证码').prop('disabled', false);
						
						
						$('#myform').validator('showMsg', '#mobileCode', {
						    type: "error",
						    msg: data.error
						});
						
						timeout = 0;
					}
				}
			});
		}

		// 验证码
		$getMobileCode.prop('disabled', false).on('click', function() {
			if (timeout === 0 && $('#mobile').isValid()) {
				timeout = delay;
				_sendMobileCode();
			} else {
				$mobile.focus();
			}
		});

	})
	</script>
</body>
</html>