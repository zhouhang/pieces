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
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/js/Validform/css/style.css" />
</head>
<body>
<!-- 找回密码弹层 -->
	<div class="basic-box-head" >
        <i class="back"></i>
        <div align="center" class="inStore-title">找回密码</div>
    </div>
<div class="password inStore-box">
<form action="/findBackPwd/mobileVerity" method="post" id="findPwd">
	<input style="display:none;" type="text" id="hidden">
    <ul class="search-report" id="loginBox">
        <li class="code-input">验证方式
        	<span>手机验证<b class="arrow-right"></b></span></li>
        <li class="code-input">用户名
            <input class="code-box" type="text" placeholder="请输入用户名" id="memberMobName" name="memberMobName" ucmob="memberMobile" class="text-1" ajaxurl="/findBackPwd/memberNameIsExist" datatype="ucmob,da5-25" nullmsg="会员名不能为空！"  errormsg="填写错误，请重新输入！" sucmsg=""/>
        </li>
        <li class="code-input">手机号码
            <input type="text" id="memberMobile" class="code-box" placeholder="请输入手机号" name="memberMobile" class="text-1" ajaxurl="/findBackPwd/mobIsExist?memberMobName=" datatype="m"  nullmsg="手机号码不能为空！" errormsg="请输入正确手机号！"/>
        </li>
        <li class="code-input">
        	<input type="text" class="code-box2" disabled="disabled" placeholder="请输入验证码" id="inputMobileCode" name="inputMobileCode"  ajaxurl="/findBackPwd/verityMobileCode" maxlength="6" datatype="n1-6" nullmsg="验证码不能为空！"  errormsg="" style=" width:48%; color:#666;"/>
			<input class="yanz" disabled="disabled" value="获取验证码" id="getMobileCode" type="button"/>
        </li>
        </ul>
	<div><span id="findBackPwdMsg" class="ts_error" style="display:none;margin-left: 1.625em; line-height:3em;background:none;"></span></div>
    <div class="code-enter mt20"><input type="submit" value="下一步" id="next" class="btn-red1" /></div>
</form>
</div>
<!-- 找回密码弹层end -->
	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>	
	<script>
		$(function() {
			//会员名和手机号变更时，自动校验
			$.Datatype.ucmob = function(gets,obj,curform,regxp){
				var name = obj.attr('ucmob');
				if($('input[name='+ name +']', curform)[0].value != ''){
					$('input[name='+ name +']', curform)[0].validform_lastval = '';
				}
				return true;
			}
			
			$('#findPwd').Validform({
				tiptype:function(msg,o,cssctl){
					if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
						if(o.type==2||o.type==3){
							$('#findBackPwdMsg').text(msg);
							$('#findBackPwdMsg').show();
						}
						if(o.obj.attr('id')=='memberMobile'&&msg==' '){
							$('#getMobileCode').removeAttr('disabled');
							$('#inputMobileCode').attr('placeholder','验证时请不要修改手机号');
						}
					}
				},
				showAllError:false,
				dragonfly:true,
				beforeSubmit:function(curform){//表单提交前再次验证验证码是否正确
					var mobile = $("input[name=inputMobileCode]").val();
					var flag = false;
					$.ajax({
						url:"/findBackPwd/verityMobileCode",
						data:"param=" + mobile,
						async:false,
						success:function(html){
							if(html == 'y'){
	              				flag = true;
	              			}else{
	                  			$('#findBackPwdMsg').show("短信验证码错误!");
	              				flag = false;
	              			}
						}
					});
					return flag;
				}
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
			
			$("#memberMobName").change(function(){
				var memberMobName=$('#memberMobName').val();
				var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
				$("#memberMobile").attr("ajaxurl",ajaxurl);
			});
			$("#memberMobile").change(function(){
				var memberMobName=$('#memberMobName').val();
				var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
				$("#memberMobile").attr("ajaxurl",ajaxurl);
			});
			
			$("#memberMobName").blur(function(){
				$("#memberMobile").focus();
				$("#memberMobile").trigger("blur");
			});
		
			var memberMobName=$('#memberMobName').val();
			var ajaxurl="/findBackPwd/mobIsExist?memberMobName="+memberMobName;
			$("#memberMobile").attr("ajaxurl",ajaxurl);
			
			$('.back').click(function(){
				history.back(-1);
			})

		});
		
			var i = 120;
			var time = function(){
				i-=1 ;
				$("#getMobileCode").attr("disabled","disabled");
				$("#getMobileCode").val('(' + i + ')秒后再获取');
				if(i==0){
					$("#getMobileCode").removeAttr("disabled");
					$("#getMobileCode").val("重新获取验证码");
					i=120;
					return;
				}
				setTimeout(time,1000);
			}
			
			//获取手机短信校验码（bind为防止重复提交）
			$("#getMobileCode").click(function(){
				$("#getMobileCode").attr('disabled','disabled');
				//防止重复提交
				//$("#getMobileCode").attr("disabled","disabled");
				var memberMobile = $("#memberMobile").val();
				if(memberMobile==null || memberMobile==""){
					$('#findBackPwdMsg').text("手机号码不能为空");
					$('#findBackPwdMsg').show();
					return;
				}
				var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$|177[0-9]{8}$/;
				if(!reg.test(memberMobile)){
					$('#findBackPwdMsg').text("需填写有效的11位手机号码");
					$('#findBackPwdMsg').show();
					return;
				}
				var memberMobName=$("#memberMobName").val();
				if(memberMobName == null || memberMobName==""){
					$('#findBackPwdMsg').text("会员名不能为空");
					$('#findBackPwdMsg').show();
					return;
				}
				$.post("/findBackPwd/getMobileCodeXgmm",{"memberMobName":memberMobName,"memberMobile":memberMobile},function(data){
					if(data=='n'){
						$('#findBackPwdMsg').text("发送失败");
						$('#findBackPwdMsg').show();
					}else{
						time();
						$('#inputMobileCode').removeAttr('disabled');
					}
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