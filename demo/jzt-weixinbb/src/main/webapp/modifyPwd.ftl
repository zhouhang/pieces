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
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
</head>
<body>
<!-- 验证方式弹层 -->
	<div class="basic-box-head">
        <i class="back"></i>
        <div align="center" class="inStore-title">重置密码</div>
    </div>
<div class="password inStore-box">
 <form action="/findBackPwd/modityPwd" method="post" id="modifyPwd">
 	<input type="hidden" name="userName" value="${userName }" />
    <ul class="search-report" id="loginBox" style="margin-top:4.2em;">
        <li class="code-input">新密码
            <input class="code-box" type="password" placeholder="请输入新密码" name="newPassword" id="newPassword" dataType="*" nullmsg="密码不能为空!"/>
        </li>
        <li class="code-input">确认密码
            <input class="code-box" type="password" placeholder="请确认新密码" name="verityPassword"  id="verityPassword"  recheck="newPassword" dataType="*"  nullmsg="密码不能为空!"/>
        </li>
    </ul>
	 <div><span class="ts_error" id="msg" style="display:none;margin-left:0.4em;background:none;"></span></div>
    <div class="code-enter mt20"><input type="button" value="确定" id="modifyPwdBtn" class="btn-red1" id="Login"/></div>
</form>
    <div class="declare">请您重新设置新登录密码，同时珍药材平台不会发送任何中奖信息，请用户妥善保管好自己的密码。</div>   
</div>
<!-- 验证方式弹层end -->
	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
	<script>
		$(function() {
		
			$('.back').click(function(){
				history.back(-1);
			});
			
			$("#modifyPwdBtn").click(function( event ) {
		    	var _newPwd = $("input[name='newPassword']").val();
		    	var _verityPwd = $("input[name='verityPassword']").val();
		    	var _memberName = $("input[name='userName']").val();
		    	$("#msg").html("");
		    	if(_newPwd==null||_newPwd==''){
		    		$("#msg").html(" 密码不能为空!");
		    		$("#msg").show();
		    		return;
		    	}
		    	var reg1=/^[a-zA-Z0-9\\pP]{6,16}$/; 
				var	reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
		    	if(!reg1.test(_newPwd)){
		    		$("#msg").html(" 密码为6-16个字符!");
		    		$("#msg").show();
					//$("#msg").html(" 密码为6-16个字符,数字或字母或标点符号的组成!");
					return;
				}
		    	if(_newPwd != _verityPwd){
		    		$("#msg").html(" 两次输入的密码不一致!");
		    		$("#msg").show();
		    		return;
		    	}
		    	$("#msg").html("");
		    	$("#msg").hide();
		    	
			$.post("/findBackPwd/modityPwd", 
				{
					memberName : _memberName,
					newPassword : _newPwd
				},
				function(data) {
					if (data >= 1) {
						$("#msg").html('密码修改成功，可登录！');
						location.replace('/login');
					} else if (data == 0) {
						$("#msg").html("修改失败");
						$("#msg").show();
						return;
					}
				});
			});
			
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