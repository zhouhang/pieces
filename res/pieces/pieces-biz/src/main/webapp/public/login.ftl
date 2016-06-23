<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>1111</title>
</head>
<body>

	<div class="container">
		<div class="login-top">登录</div>
		<div class="login-body clearfix">
			<div class="login-box fr">
				<form action="/login" method="post" class="loginform">
					<ul>
						<li>用户名：<input class="login-text1 usename" type="text" value="" name="username"/></li>
						<li style="position: relative;">密码：<input class="login-text1 password" type="password" name="password"/></li>
						<li><input class="login-btn" type="button" value="提交" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<br/>
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<!-- <script type="text/javascript" src="../html/resources/js/Validform/js/Validform_v5.3.2.js"></script> -->
    <script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script>
		$(function() {
			$('.login-btn').click(function() {
				$.post("/login",
				  {
					username:$('.usename').val(),
				    password:$('.password').val(),
				  },
				  function(data,status){
					if(!data.ok){
					}else{
						window.location = "/welcome";
					}
				  });
			});
		  
		})
	</script>







</body>
</html>