<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="html/resources/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="html/resources/css/common.css" type="text/css" rel="stylesheet" />
    <link href="html/resources/css/detail.css" type="text/css" rel="stylesheet" />
    <style>
        .layout{width: 350px; margin: 0 auto;padding: 10px 0;}
        .layout h3{
            font-family: "microsoft yahei";
            font-size: 20px;
            color: #333;
            line-height: 44px;
            font-weight: normal;
        }
        .layout ul li label{
            font-size: 14px;
            color: #434343;
            display: block;
            line-height: 32px;
        }
        .layout .col_red{color: #ff4001;}
        .layout ul li .error{
            font-size: 12px;
            color: #fd0000;
            line-height: 30px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="layout">
    <h3>会员登录</h3>
    <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
    <ul>
        <li>
        	<label>会员名/手机号码</label>
        	<form:input cssClass="text login-text user" id="username" datatype="*" tabindex="1" nullmsg="请输入用户名！" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
        </li>
        <li>
        	<label>登录密码</label>
        	<form:password cssClass="text login-text password"  id="password" datatype="*" tabindex="2" path="password"  accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" /></li>
        <li>
        	<label>验证码</label>
        	<input class="text login-text yzm" tabindex="3"  id="vcode"  name="vcode"  autocomplete="off"  />
        	<a style="width:200px;height:30px; display:inline-block;" href="javascript:;" id="vcodeA">
            	<img width="60" height="30" style="display: block;position: relative;float: left;padding-left: 5px;" src="https://passport.54315.com/captcha.htm" id="vcodeImg">看不清楚,换一张!
        	</a>
        </li>
        <li><p id="errorMsg" class="error">
                    <form:errors path="*" id="msg" element="span" htmlEscape="false" /></p>
        <li class="">
            <input type="button" tabindex="4" accesskey="l" value="登录" name="button" id="subbtn" class="login-btn">
            <!-- <input name="submit" type="submit" value="登录" class="login-btn" />  -->
        </li>
        <input type="hidden" name="lt" value="${loginTicket}" />
		<input type="hidden" name="execution" value="${flowExecutionKey}" />
		<input type="hidden" name="_eventId" value="submit" /><br />
    </ul>
    </form:form>
    <div class="f14" align="center">还没有账号？<a href="http://uc.54315.com/getUcUserRegister" target="_top" class="col_red">马上注册</a></div>
</div>
<script type="text/javascript" src="/html/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/html/resources/js/public.js"></script>
<script type="text/javascript" src="/html/resources/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
    $(function(){
    	
        //底部最后一个A链接去除右边框
        $('.foot p.links').each(function(){
            $(this).children('a').first().css('border','none');
        })
        
    	/* $("#fm1").Validform({
            tiptype:2,
            showAllError:true
        }); */
        
     	// 绑定键盘按下事件  
    	$(document).keydown(function(e) {  
    		// 回车键事件  
    	    if(e.which == 13) {  
    	    	$('.login-btn').click();
    	    }    	  
    	});  	
    	//验证码事件绑定
		$('#vcodeA').click(function() {
			$('#vcodeImg').attr('src','/captcha.htm?t='+new Date().getTime());
		});
        
        $("#subbtn").click(function(){
        	$("#errorMsg").html("");
        	var _userName = $("#username").val();
        	var _pwd = $("#password").val();
        	var _vcode = $("#vcode").val();
        	if(_userName==null || _userName==''){
        		$("#errorMsg").html("会员名不能为空!");
        		return;
        	}
        	if(_pwd==null || _pwd==''){
        		$("#errorMsg").html("密码不能为空!");
        		return;
        	}
        	if(_vcode==null || _vcode==''){
        		$("#errorMsg").html("验证码不能为空!");
        		return;
        	}
        	$("#username").val($("#username").val().replace(/(^\s*)|(\s*$)/g, ""));
        	$("#fm1").submit();
        });
    })
</script>
</body>
</html>