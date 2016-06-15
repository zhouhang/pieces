<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>微信后台管理-登录</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/admin.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/jquery.jslides.css" media="screen" />
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jQuery.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/iscroll.js"></script>
</head>
<body>
<!-- 登录界面start -->
	<div class="a-loginbox" style="left:-100%;position:fixed" id="login_page">
		<div class="logo"></div>
		<form class="loginform" action="/WxBoss/wx_login_ajax" method="post">
			<ul class="form">
				<li><input class="text" type="text" id="username"
					name="username" datatype="*" errormsg="" placeholder="请输入后台登录名" /></li>
				<li><input class="text" type="password" id="password"
					name="password" datatype="*6-16" errormsg="请填写6到16位任意字符！"
					placeholder="请输入密码" /></li>
				<li class="i-01"><input class="text" placeholder="请输入验证码" id="YZM"
					name="vcode" type="text" /></li>
				<li class="i-02" id="vcodeA">
        			<img id="vcodeImg" src="/WxBoss/vcode" style="width:80%;height:80%;color: #171d20;padding-left: 10px;"/>
        		</li>
        		<li id="eMsg" class="msg"></li>
        		
				<li class="btn"><input type="button" id="loginButton" class="btn-l" value="登 录"></li>
				<li class="btn pt"><input type="reset" class="btn-l null"
					value="重 置" onclick="javascript:reset_eMsg()"></li>
			</ul>
		</form>
	</div>
<!-- 登录界面end -->
	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script>
		//0其他事件
		$(function() {
			
			$('#login_page').animate({left:0},1000);
			
			//touch事件替换CLICK事件
			$('.nav dd').touchStart(function() {
				$(this).addClass('hover');
			});
			$('.nav dd').touchMove(function() {
				$(this).addClass('hover');
			});
			$('.nav dd').touchEnd(function() {
				$(this).removeClass('hover');
			});
			$('.nav dd').tapOrClick(function() {
				$(this).removeClass('hover');
			});
			
			//验证
			var validator=$(".loginform").Validform({tiptype:2,callback:function(data){return false}});
			validator.tipmsg.r="通过验证";
			
			// 绑定键盘按下事件  
		  	$(document).keydown(function(e) {  
		    	// 回车键事件  
		       if(e.which == 13) {  
		    	   $('#loginButton').click();
		       }  
		  	});

		})
		
		
		
		//1校验验证码
		$('#YZM').blur(function(){
		    	if($(this).val() == "" || $(this).val() == "请输入验证码"){
		    		$('#eMsg').html("请输入验证码！");		    		
		    	}else{
		    		$.get("/WxBoss/vcodes",
		    				{vcode:$('[name="vcode"]').val()},
		    				function(data,status){
								if(!data.ok){
									$('#eMsg').html("验证码错误！");
								}
								else{
									$('#eMsg').html("<span id='errorMsg'></span>");
								}
							}
		    		);
		    	}
		    });
		
		//2点击登录按钮
		$('#loginButton').click(function() {
			if($('[name="username"]').val() == "" || $('[name="username"]').val() == "请输入后台登录名"){
				$('#eMsg').html("请输入后台登录名！");		    		
			}else if($('[name="password"]').val() == "" || $('[name="password"]').val() == "请输入密码"){
				$('#eMsg').html("请输入密码！");		    		
			}else if($('[name="vcode"]').val() == "" || $('[name="vcode"]').val() == "请输入验证码"){
	    		$('#eMsg').html("请输入验证码！");		    		
	    	}else{
	    		$.get("/WxBoss/vcodes",
	    				{vcode:$('[name="vcode"]').val()},
	    				function(data,status){
							if(!data.ok){
								$('#eMsg').html("验证码错误！");
								$('#eMsg').show();
							}
							else{
								//登陆验证
								$('#eMsg').html("<span id='errorMsg'></span>");
								$('#errorMsg').html('');				
								$.post("/WxBoss/wx_login_ajax",
								  {
									username:$('[name="username"]').val(),
								    password:$('[name="password"]').val(),
								    vcode:$('[name="vcode"]').val()
								  },
								  function(data,status){
									if(!data.ok){
										$('#eMsg').html("<span class='Validform_wrong' id='errorMsg'></span>");
										$('#errorMsg').html(data.errorMessages[0].message);
										//刷新验证码
										$('#vcodeImg').attr('src','/vcode?t='+new Date().getTime());
									}else{
										window.location.replace('/WxBoss/manager');
									}
								  });
								
								
							}
						}
	    		);
	    	}
		});
		
		//3点击重置按钮
		function reset_eMsg(){
			$('#eMsg').html("");
		}
		
		//4验证码事件绑定
		$('#vcodeA').click(function() {
			$('#vcodeImg').attr('src','/WxBoss/vcode?t='+new Date().getTime());
		});

	</script>
	<span style="display: none;"> <!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
		<!-- 珍药网微信CNZZ --> <script
			src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754"
			language="JavaScript"></script>
	</span>
</body>
</html>