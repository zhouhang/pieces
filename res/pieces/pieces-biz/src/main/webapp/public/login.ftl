<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>登录-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>
	<!-- header start -->
	<div class="header">
		<div class="wrap">
			<div class="logo">
				<a href="/">上工好药首页</a>
			</div>
			<div class="title">
				<h1>欢迎登录</h1>
			</div>
		</div>
	</div>
	<!-- header end -->

	<div class="slide slide-full">
		<div class="bd">
			<div class="item" style="background-image: url(/images/banner-login.jpg);"></div>
		</div>

		<div class="login-box">
			<h2 class="title">会员登录</h2>
			<div class="form">
				<form action="" id="myform">
					<div class="msg" id="msg">
						<i class="fa fa-prompt"></i> <span>账户名与密码不匹配</span>
					</div>

					<div class="group">
						<div class="txt">
							<i class="fa fa-people"></i>
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off" name="userName" id="username" placeholder="用户名/手机号">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i class="fa fa-lock"></i>
						</div>
						<div class="cnt">
							<input type="password" class="ipt" value="" autocomplete="off" name="password" id="pwd" placeholder="密码">
						</div>
					</div>

					<div class="links cf">
						<a class="fl" href="/user/register">免费注册</a> <a class="fr" href="/user/findpwd/stepone">忘记密码？</a>
					</div>

					<div class="button">
						<button type="submit" class="btn btn-red" id="submit">登 录</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<#include "./inc/footer.ftl"/>
	<script src="${urls.getForLookupPath('/js/login.js')}"></script>
	<script>
		_global = {
			fn: {
				init: function() {
					this.submit();
				},
				submit: function() {
                	var isSubmit = false;
					$('#submit').on('click', function() {
		                if (!isSubmit && loginPage.fn.checkForm()) {
		                    $.ajax({
		                        url : '/user/login',
		                        type : 'POST',
		                        dataType : 'json',
		                        data : {
		                            userName : $('#username').val(),
		                            password : $('#pwd').val()
		                        },
		                        success: function(result) {
		                            if (result.status != 'y') {
		                                loginPage.fn.showMsg('账户名与密码错误!');
		                            } else {
		                                window.location.href = result.info;
		                            }
		                        },
		                        beforeSend: function(){
		                            isSubmit = true;
		                        },
		                        complete: function() {
		                            isSubmit = false;
		                        }
		                    });
		                }
		                return false;
		            })
				}
			}
		}
		$(function(){
			_global.fn.init();
		})
	</script>
</body>
</html>