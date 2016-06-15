<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>珍药材-登录</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>


	<div class="box-layout">
		<form class="loginform" action="/login_ajax" method="post">
			<ul class="search-report login" id="loginBox">
				<li class="search-input login-box"><input type="text"
					id="username" name="username" value="${username!''}" placeholder="请输入用户名或手机号码"> <i class="remove"></i></li>
				<li class="search-input pass-box"><input type="text"
					id="password" name="password" placeholder="请输入您的密码"> <i class="eye"></i></li>
				<li><span id="msg" style="margin-left: 0.4em;display: none;"></span></li>
				<li class="mt20"><input type="submit" class="btn-1" value="登 录"
					id="login"></li>
				<li><input type="button" class="btn-2" value="注 册" id="reg"></li>
				<input type="hidden" id="username_login" value="" />
				<input type="hidden" id="go" name="go" value="${go}" />
				<input type="hidden" id="password_login" value="" />
			</ul>
		</form>
		<div class="forget"><a href="/findBackPwd/forgetPwd">忘记密码了？立即找回</a></div>
		<div>
			<#if wxAd.adType == 0> ${wxAd.adMemo} <#elseif wxAd.adType == 1> <img
				src="${wxAd.adMemo}" /> <#elseif wxAd.adType == 2>

			<object type="application/x-shockwave-flash"
				data="http://www.k7dj.com/v/f/1d31a6cf-d051-11e4-b137-0015c55db73d/k7dj.com.swf"
				width="100%" height="100%">
				<!--<![endif]-->
				<param name="quality" value="high">
				<param name="wmode" value="opaque">
				<param name="swfversion" value="9.0.45.0">
				<param name="expressinstall" value="Scripts/expressInstall.swf">
				<!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
				<div>
					<h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
					<p>
						<a href="http://www.adobe.com/go/getflashplayer"><img
							src="http://www.adobe.com/jztimages/shared/download_buttons/get_flash_player.gif"
							alt="获取 Adobe Flash Player" width="112" height="33"></a>
					</p>
				</div>
				<!--[if !IE]>-->
			</object>

			<#elseif wxAd.adType == 3>
			<audio src="${wxAd.adMemo}" autoplay="autoplay" />
			<#elseif wxAd.adType == 4>
				<video src="${wxAd.adMemo}" autoplay="autoplay" />
		</#if>
		</div>
	</div>

	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
	<script>
		$(function() {
			/*
			//击活改变边框色
			$(':input[type=text]').focus(function() {
				$(this).parent().css('borderColor', '#f59e73');
			}).blur(function() {
				$(this).parent().css('borderColor', '#cfcfcf');
			});
			*/
			
			//按下改变密码输入框的type
			$('#loginBox .pass-box input').keyup(function() {
				if ($(this).next().hasClass('cur')) {
					$(this).attr('type', 'text');
				} else {
					$(this).attr('type', 'password');
				}
			});

			//点眼睛ICON图标修改密码输入框中的type
			$('#loginBox .eye').on('click', function() {
				$(this).toggleClass('cur');
				if ($(this).hasClass('cur')) {
					$(this).prev().attr('type', 'text');
				} else {
					$(this).prev().attr('type', 'password');
					$(this).removeClass('cur');
				}

			});

			$('#loginBox .remove').on('click', function() {
				$(this).prev().val('');
			});

			//登陆
			var loginform = $(".loginform").Validform({
			    tiptype:function(msg,o,cssctl){
					//msg：提示信息;
					//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
					//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
					if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
						var objtip=$('#msg');
						//cssctl(objtip,o.type);
						objtip.text(msg);
						if(o.type!=2){
							objtip.show();
						}
					}	
				},
			    ajaxPost:true,
			    showAllError:false,
			    beforeSubmit:function(curform){
			    	$('#login').val('登 录 中').attr('disabled','disabled');
			    },
			    callback:function(data){
			    	$('#login').val('登 录').removeAttr('disabled');
			    	var ok = data.ok;
			    	if(ok==undefined){
			    		$('#msg').text('网络繁忙，请稍后再试！');
					}else{
						if(ok){
							var url = window.location.href;
							if(url.indexOf('login')!=-1){
								var obj = data.obj;
								if(obj==undefined){
									//默认跳转到基本资料信息
							        window.location.href = '/info';
								}else{
									window.location.href = obj.toString();
								}
							}else{
								window.location.reload(true);
							}
						}else{
							$('#msg').text('用户名或密码错误！').show();
						}
					}
			    }
			});
			loginform.addRule([
				{
			        ele:"#username",
			        datatype:"dn5-25|m",
			        nullmsg:"请输入用户名或手机号码！",
			        errormsg:"用户名：5-25个字母或字母+数字组合或手机号码，不支持符号！",
			        sucmsg:""
			    },
			    {
			        ele:"#password",
			        datatype:"*6-16",
			        nullmsg:"请输入您的密码！",
			        errormsg:"密码：6-16个字符，区分大小写！",
			        sucmsg:""
			    }
			]);

			//touch事件替换CLICK事件
			$('input[type=button],.see,.call').touchStart(function() {
				$(this).addClass('hover');
			});
			$('input[type=button],.see,.call').touchMove(function() {
				$(this).addClass('hover');
			});
			$('input[type=button],.see,.call').touchEnd(function() {
				$(this).removeClass('hover');
			});
			$('input[type=button],.see,.call').tapOrClick(function() {
				$(this).removeClass('hover');
			});

		})
		
		//注册跳转
		$('#reg').click(
				function() {
				window.location.replace("/registerController");
		});
		
		//找回密码
		$('#findPWD').click(function(){
		});
		
		//清除空白字符
		$('input').on('blur',function(){
			var inputVal = $(this).val().replace(/\s/g,'');
			$(this).val(inputVal);
		});
	</script>
	
<span style="display: none;">
<!-- 东网微信CNZZ
<script src="http://s95.cnzz.com/z_stat.php?id=1254784539&web_id=1254784539" language="JavaScript"></script> -->
<!-- 珍药网微信CNZZ -->
<script src="http://s4.cnzz.com/z_stat.php?id=1255308754&web_id=1255308754" language="JavaScript"></script>
</span>
</body>
</html>