<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>登录-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>
	<div class="login-box login-mini">
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
                        <input type="hidden" value="${ajaxurl}" id="ajaxurl" name="url">
						<input type="hidden" value="${url}" id="url" name="url">
						<input type="text" class="ipt" value="" autocomplete="off" name="username" id="username" placeholder="用户名/手机号">
					</div>
				</div>

				<div class="group">
					<div class="txt">
						<i class="fa fa-lock"></i>
					</div>
					<div class="cnt">
						<input type="password" class="ipt" value="" autocomplete="off" name="pwd" id="pwd" placeholder="密码">
					</div>
				</div>

				<div class="links cf">
					<a class="fl" href="/user/register" target="_top">免费注册</a> <a
						class="fr" href="/user/findpwd/stepone" target="_top">忘记密码？</a>
				</div>

				<div class="button">
					<button type="submit" class="btn btn-red" id="submit">登 录</button>
				</div>
			</form>
		</div>
	</div>
	<script src="${urls.getForLookupPath('/js/jquery.min.js')}"></script>
	<script src="${urls.getForLookupPath('/js/login.js')}"></script>
	<script>
		_global = {
			fn: {
				init: function() {
					this.submit();
				},
				submit: function() {
					var isSubmit = false,
                        url = $('#url').val(),
                        ajaxurl = $('#ajaxurl').val();
					$('#submit').on('click', function() {
						if (!isSubmit && loginPage.fn.checkForm()) {
							$.ajax({
	                            url : '/user/login',
                                type : 'POST',
                                dataType : 'json',
	                            data : {
                                    userName : $('#username').val(),
                                    password : $('#pwd').val(),
	                                url: url
	                            },
	                            success : function(result) {
	                                if (result.status != 'y') {
	                                    loginPage.fn.showMsg('账户名密码错误!');
	                                } else {
	                                    if(ajaxurl != '') {
	                                        $.ajax({
	                                            url: ajaxurl,
	                                            type: 'POST',
	                                            dataType : 'json',
	                                            success: function(data){
	                                                window.parent.loginCall && window.parent.loginCall(data.status);
	                                            }
	                                        });
	                                    } else {
	                                        window.parent.location.href = result.info;
										}
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