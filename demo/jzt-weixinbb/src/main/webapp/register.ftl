<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>我的小珍-入驻珍药材</title>
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${RESOURCE_CSS_WX}/css/common.css">
</head>
<body>

	<div class="box-layout">
		<form class="registerform" action="/register_ajax" method="post">
			<ul class="search-report login" id="loginBox">
				<li class="search-input phone"><input type="text" id="Phone"
					placeholder="请输入您的手机号码" name="mobile"></li>
				<li class="yzm">
				<input type="text" id="Code" name="mobileCode" />
				<input
					type="button" value="获取验证码" id="VerityCode" /></li>
				<li id="codeErorr" style="border: 0px none; width: 100%; display:none;" class="yzm">
					<span class="error" style="color:#666;">五分钟有效</span>
            	</li>
				<li class="search-input login-box de"><input type="text"
					placeholder="请输入公司名/姓名" id="companyname" name="companyname"></li>
				<li class="search-input login-box re"><input type="text"
					placeholder="请输入用户名" id="username" name="username"></li>
				<li class="search-input pass-box de"><input type="text"
					placeholder="请输入您的密码" id="password" name="password"> <i class="eye"></i></li>
				<li class="search-input pass-box re"><input type="text"
					placeholder="请再次输入您的密码" id="passwordOk" name="passwordOk"> <i class="eye"></i></li>
				<li><span id="msg" style="margin-left: 0.4em;display: none;"></span></li>
				<li class="mt20">
				<input type="submit" class="btn-gray" value="提 交" id="Register_Ajax" disabled="disabled"></li>
			</ul>
		</form>
	</div>
	
	<div class="pull-down">
    <ul class="select clearfix">
        <li class="fl">
			<div class="inStore-select">
				<input type="checkbox" id="checkbox1" class="checkbox"/>
				<span class="control_text">我已阅读同意相关服务条款<i></i></span>
			</div>
            <div class="sub-nav">
				<div class="triangle"></div>
                <a href="/register_auth_service">《珍药材会员服务协议》</a>
                <a href="/register_auth_busi">《珍药材交易服务协议》</a>
            </div>
        </li>
    </ul>
	</div>

	<script type="text/javascript">
		var i = 120;
		function time() {
			i -= 1;
			$("#VerityCode").val(i + '秒重新获取');
			$("#VerityCode").addClass('disable');
			$("#VerityCode").attr("disabled", "disabled");
			if (i == 0) {
				$("#VerityCode").removeAttr("disabled");
				$("#VerityCode").val("重获验证码");
				$("#VerityCode").removeClass('disable');
				i = 120;
				return;
			}
			setTimeout("time()", 1000);
		}
	</script>

	<script src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"
		type="text/javascript"></script>
	<script src="${RESOURCE_JS_WX}/js/jqueryTouch.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>
	<script>
		$(function() {
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
			
			//注册
		    var registerform = $(".registerform").Validform({
			    tiptype:function(msg,o,cssctl){
					//msg：提示信息;
					//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
					//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
					if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
						var objtip=$('#msg');
						objtip.text(msg);
						if(o.type!=2){
							objtip.show();
						}
					}
				},
			    ajaxPost:true,
			    showAllError:false,
			    datatype:{
	     			"cn":function(gets,obj,curform,regxp){
	     				/*参数gets是获取到的表单元素值，
	     				  obj为当前表单元素，
	     				  curform为当前验证的表单，
	     				  regxp为内置的一些正则表达式的引用。*/
	     				var reg1=/[\u4E00-\u9FA5]{2,50}/,
	     					reg2=/^(?:\d*|[a-zA-Z]*|[\x00-\x2f\x3a-\x40\x5b-\x60\x7b-\x7f]*)$/;
	     				if(gets==""){
	     					return false;
	     				}
	     				if(!reg1.test(gets)){
	     					return false;
	     				}
	     				if(reg2.test(gets)){
	     					return false;
	     				}
	     				return true;
	     			}
	     		},
			    ajaxurl:{
			        success:function(data,obj){
			            //data是返回的json数据;
			            //obj是当前正做实时验证表单元素的jquery对象;
			            //注意：5.3版中，实时验证的返回数据须是含有status值的json数据！
			            //跟callback里的ajax返回数据格式统一，建议不再返回字符串"y"或"n"。目前这两种格式的数据都兼容。
			            var status = data.status;
						if(status==undefined){
			            	$('#msg').text('网络繁忙，请稍后再试！');
			            }else{
			            	//验证电话号码
			            	var id = $(obj).attr('id');
			            	if(id=='Phone'&&status=='y'){
			            		$('.yzm').slideDown();
			            	}
			            }
			        },
			        error:function(data,obj){
			            //data是{ status:**, statusText:**, readyState:**, responseText:** };
			            //obj是当前表单的jquery对象;
			            var readyState = data.readyState;
			            if(readyState!=0){
			            	$('#msg').text('网络繁忙，请稍后再试！');
			            }
			        }
			    },
			    beforeSubmit:function(curform){
			    	$('#Register_Ajax').val('提 交 中').attr('disabled','disabled');
			    },
			    callback:function(data){
			    	$('#Register_Ajax').val('提 交').removeAttr('disabled');
			    	var status = data.responseText;
			    	if(status==undefined){
		            	$('#msg').text('网络繁忙，请稍后再试！');
		            }else{
		            	if(status=='ok'){
							$('#msg').text("注册成功，请到登录页面登录！");
							window.location.href = '/login?username='+$('#username').val();
						}else{
							$('#msg').text(status).show();
						}
		            }
			    }
			});
			registerform.addRule([
				{
			        ele:"#Phone",
			        ajaxurl:"/checkMobileisExist",
			        datatype:"m",
			        nullmsg:"请输入您的手机号码！",
			        errormsg:"请输入正确的手机号码！",
			        sucmsg:""
			    },
			    {
			        ele:"#Code",
			        ajaxurl:"/mobileCodeisExist",
			        datatype:"*1-50",
			        nullmsg:"请输入您的验证码！",
			        errormsg:"验证码错误！",
			        sucmsg:""
			    },
			    {
			        ele:"#companyname",
			        datatype:"cn",
			        nullmsg:"请输入公司名/姓名！",
			        errormsg:"公司名/姓名：5-50个字符，必须包含中文！",
			        sucmsg:""
			    },
			    {
			        ele:"#username",
			        ajaxurl:"/userNameisExist",
			        datatype:"dn5-25",
			        nullmsg:"请输入用户名！",
			        errormsg:"用户名：5-25个字母或字母+数字组合，不支持符号和纯数字！",
			        sucmsg:""
			    },
			    {
			        ele:"#password",
			        datatype:"*6-16",
			        nullmsg:"请输入您的密码！",
			        errormsg:"密码：6-16个字符，区分大小写！",
			        sucmsg:""
			    },
			    {
			        ele:"#passwordOk",
			        recheck:"password",
			        datatype:"*6-16",
			        nullmsg:"请再次输入您的密码！",
			        errormsg:"两次输入的密码不一致！",
			        sucmsg:""
			    }
			]);
			
			 //获取验证码
	         $("#VerityCode").click(function(){
	     		var mobile = $("#Phone").val();
	     		var reg = /^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
	     		if(mobile=="" || mobile==null){
	     			$("#msg").text("请输入手机号!");
	     			reflag = true;
	     			return;
	     		}
	     		if(!reg.test(mobile)){
	     			$("#msg").text("请输入正确的手机号!");
	     			reflag = true;
	     			return;
	     		}
	     		
	     		//验证手机号
	     		$.post(
	     			"/checkMobileisExist",
	     			{
	     				"param":mobile
	     			},
	     			function(data){
		     			if(data!='y'){
		         			$("#msg").text(data);
		         			reflag = true;
		     				return;
		     			}else{
		     				//获取手机验证码 
		     	     		$.post(
		     	     			"/getMobileCode",
		     	     			{
		     	     				"memberMobile":mobile
		     	     			},
		     	     			function(data){
		     	     				if(data=='eorr'){
		     	     					$('#codeErorr').css("display","list-item");
		     							$("#codeErorr .error").text("120秒内只能获取一次验证码");
		     	     		    		reflag = true;
		     	     				}
		     	     				if(data=='y'){
		     	     					time(120);
		     	     				}
		     	     			}
		     	     		);
	     				}
	     			});
	     		});
			
			$(function(){
		        $('.select li .control_text').on('click',function(){
		            $(this).parents('li').find('.sub-nav').toggleClass('cur');
		            $(this).children('i').toggleClass('cur');
		            if($(this).children('.sub-nav').hasClass('cur')){
		                $(this).siblings().children('.sub-nav').removeClass('cur');
		            }
		        });
			});
			
			$("#Phone").change(function(){
				$('.yzm').slideUp();
			})
			
			$("#checkbox1").click(function(){
				if($("#checkbox1").is(':checked')){
					<!--$("#Register").css("background", "#dc4145");-->
					$("#Register_Ajax").removeAttr('disabled');
					$("#Register_Ajax").css({"background":"#dc4145","color":"#fff"});
				}else{
					$("#Register_Ajax").attr('disabled','disabled');
					$("#Register_Ajax").removeAttr("style");
				}
			});
	
			//touch事件替换CLICK事件
			$('input[type=button]').touchStart(function() {
				$(this).addClass('hover');
			});
			$('input[type=button]').touchMove(function() {
				$(this).addClass('hover');
			});
			$('input[type=button]').touchEnd(function() {
				$(this).removeClass('hover');
			});
			$('input[type=button]').tapOrClick(function() {
				$(this).removeClass('hover');
			});
			
			//清除空白字符
			$('input').on('blur',function(){
				var inputVal = $(this).val().replace(/\s/g,'');
				$(this).val(inputVal);
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