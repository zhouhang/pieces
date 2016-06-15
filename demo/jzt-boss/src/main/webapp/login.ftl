<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>后台管理会员登录页</title>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-boss/admin.css" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<style>
body {
	height: auto;
}
.Validform_error{
	border: 0 none;
}
</style>
</head>
<body>

	<div class="container">
		<div class="login-top">欢迎进入中药材电子商务管理系统</div>
		<div class="login-body clearfix">
			<div class="login-box fr">
				<form action="/login" method="post" class="loginform">
					<ul>
						<li><input class="login-text1 usename" type="text"
							value="用户名" name="username" datatype="*" errormsg="" nullmsg="用户名不能为空！" /></li>
						<li></li>
						<li style="position: relative;"><input class="login-text1 password" type="password"
							name="password" datatype="*6-16" errormsg="请填写6到16位任意字符！" nullmsg="请输入密码！" />
							<div class="mima">密码</div>
							</li>
						<li></li>
						<li><input id="YZM" class="login-text2" type="text" name="vcode" />
						<a href="javascript:void(0);" id="vcodeA" style="width:170px;height:30px;padding: 0px;margin: 0px; display:inline-block\9; *display:inline;">
        					<img id="vcodeImg" src="/vcode" style="padding-left: 5px;"/>
        					看不清?换一张
      					</a>
      					</li>
      					<li id="eMsg"></li>
      					<!--  <img src="html/resources/images/jzt-boss/yzm.jpg" />-->
						<li><input class="login-btn" type="button" value="登 录" /></li>
					</ul>
				</form>
			</div>
		</div>
	</div>
	<br/>
	<br/><br/><br/>
	<div class="foot">
		<p style="text-align:center;">
			Copyright© 2014-2015 珍药材版权所有 鄂ICP备14019602号-3
		</p>
	</div>
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<!-- <script type="text/javascript" src="../html/resources/js/Validform/js/Validform_v5.3.2.js"></script> -->
    <script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script>
		$(function() {
			$('.usename').focus(function() {
		        if ($(this).val() == '用户名') {
		            $(this).val('');
		        }
		        else {
		            $(this).val($(this).val());
		        }
		    }).blur(function(){
		        if($(this).val() == 0){
		            $(this).val('用户名');
		        }else{
		            $(this).val($(this).val());
		        }
		    });
		    $('.password').focus(function(){
		        
		        $('.mima').hide();

		    }).blur(function(){
		        if($(this).val() == ''){
		            $('.mima').show();
		        }else {
		            $(this).val($(this).val());
		            $('.mima').hide();
		        }
		    });
			
			//验证
			var validator=$(".loginform").Validform({tiptype:2,callback:function(data){return false}});
			validator.tipmsg.r="通过验证";
			
		    $('#YZM').blur(function(){
		    	if($(this).val() == 0){
		    		$('#eMsg').html("<span class='Validform_wrong' id='errorMsg'>请输入验证码！</span>");		    		
		    	}else{
		    		//alert('go');
		    		$.post("/vcode",
		    				{vcode:$('[name="vcode"]').val()},
		    				function(data,status){
								if(!data.ok){
									$('#eMsg').html("<span class='Validform_wrong' id='errorMsg'>验证码错误！</span>");	
									
								}
								else{
									$('#eMsg').html("<span class='Validform_right' id='errorMsg'></span>");	
								}
							}
		    		);
		    	}
		    })
			
			//验证码事件绑定
			$('#vcodeA').click(function() {
				$('#vcodeImg').attr('src','/vcode?t='+new Date().getTime());

			});
			
			// 绑定键盘按下事件  
		  	$(document).keydown(function(e) {  
		    	// 回车键事件  
		       if(e.which == 13) {  
		    	   $('.login-btn').click();
		       }  
		  	});
			//登陆验证
			$('.login-btn').click(function() {
				$('#errorMsg').html('');				
				$.post("/login",
				  {
					username:$('.usename').val(),
				    password:$('.password').val(),
				    vcode:$('[name="vcode"]').val()
				  },
				  function(data,status){
					if(!data.ok){
						//alert(' ');
						$('#eMsg').html("<span class='Validform_wrong' id='errorMsg'></span>");
						$('#errorMsg').html(data.errorMessages[0].message);
						//刷新验证码
						$('#vcodeImg').attr('src','/vcode?t='+new Date().getTime());
					}else{
						window.location = "/welcome";
					}
				  });
			});
		  
		})
	</script>







</body>
</html>