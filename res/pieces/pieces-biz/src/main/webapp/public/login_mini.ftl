<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
<title>登录-饮片B2B</title>
</head>
<body>
	<div class="login-box login-mini">
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
						<input type="hidden" value="${url }" id="url" name="url">
						<input type="text" class="ipt" value="" autocomplete="off"
							name="username" id="username" placeholder="用户名">
					</div>
				</div>

				<div class="group">
					<div class="txt">
						<i class="fa fa-lock"></i>
					</div>
					<div class="cnt">
						<input type="password"
							class="ipt" value="" autocomplete="off" name="pwd" id="pwd"
							placeholder="密码">
					</div>
				</div>

				<div class="links cf">
					<a class="fl" href="/user/register" target="_top">免费注册</a> <a
						class="fr" href="/user/findpwd/stepone" target="_top">忘记密码？</a>
				</div>

				<div class="button">
					<button type="button" class="btn btn-red" id="submit">登 录</button>
				</div>
			</form>
		</div>
	</div>
	<#include "./inc/footer.ftl"/>
	<script src="/js/login.js"></script>
	<script>
		$(function() {
			var 
				$submit = $('#submit'),
				isSubmit = false;

			$submit.on('click', function() {
				if (!isSubmit && loginPage.fn.checkForm()) {
					isSubmit = true;
					$.ajax({
						type : 'POST',
						url : '/user/login',
						data : {
							userName : loginPage.v.$username.val(),
							password : loginPage.v.$pwd.val(),
							url      : $('#url').val()
						},
						dataType : 'json',
						success : function(data) {
							var status = data.status;
							if (status != 'y') {
								loginPage.fn.showMsg('用户名密码错误!'); // login.js
							} else {
								window.parent.location.href = data.info;
								// window.parent.productPage.fn.logined(data.info);
							}
						},
						complete: function() {
							isSubmit = false;
						}
					});
				}
				return false; // 阻止表单提交
			})
		})
	</script>
</body>
</html>