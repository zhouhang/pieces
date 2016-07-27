<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
<title>登录-饮片B2B</title>
</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="/">饮片B2B首页</a>
			</div>
			<div class="title">
				<h1>欢迎登录</h1>
			</div>
		</div>
	</div>
	<!-- header end -->


	<!-- slide start -->
	<div class="slide slide-full">
		<div class="bd">
			<div class="item"
				style="background-image: url(/images/banner-login.jpg);"></div>
		</div>

		<div class="login-box">
			<h2 class="title">会员登录</h2>
			<div class="form">
				<form action="" id="myform">
					<div class="msg" id="msg">
						<i class="fa fa-prompt"></i> <span>用户名与密码不匹配</span>
					</div>

					<div class="group">
						<div class="txt">
							<i class="fa fa-people"></i>
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="userName" id="username" placeholder="用户名">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i class="fa fa-lock"></i>
						</div>
						<div class="cnt">
							<input onkeydown="loginPage.fn.keyDown(event)" type="password"
								class="ipt" value="" autocomplete="off" name="password" id="pwd"
								placeholder="密码">
						</div>
					</div>

					<div class="links cf">
						<a class="fl" href="/user/register">免费注册</a> <a class="fr"
							href="/user/findpwd/stepone">忘记密码？</a>
					</div>

					<div class="button">
						<button type="button" class="btn btn-red" id="submit">登 录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- slide end -->


	<#include "./inc/footer.ftl"/>
	<script src="/js/login.js"></script>
	<script>
		$(function() {
			var $submit = $('#submit');
			$submit.on('click', function() {
				if (loginPage.fn.checkForm()) {
					$.ajax({
						type : "POST",
						url : "/user/login",
						data : {
							userName : $('#username').val(),
							password : $('#pwd').val()
						},
						dataType : "json",
						success : function(data) {
							var status = data.status;
							if (status != "y") {
								loginPage.fn.showMsg("用户名密码错误!");
							} else {
								window.location = data.info;
							}
						}
					});
				} else {
					return false;
				}
			})
		})
	</script>
</body>
</html>