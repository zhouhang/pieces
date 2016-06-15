<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
    <title>我的小珍-入驻珍药材</title>
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/reset.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS_WX}/js/Validform/css/style.css" />
    <style>
        body{
			background:#f5f5f5;
		}
        .data-box{
			padding-top: 0;
		}
    </style>
</head>
<body>
<div class="basic-box-head" >
        <i class="back" name="base"></i>
        <div align="center" class="inStore-title">设置新号码</div>
   </div>
<div class="password inStore-box info-body">
	<form action="/authenticateMobile/updateMobileNo" id="update-form" method="post">
		<input style="display:none;" type="text">
		<input type="hidden" value="${optType!''}" id="optType"/>
	    <input type="hidden" value="${data!''}" name="data" >
	    <ul class="login search-report" id="loginBox">
	        <li class="code-input">手机号码
	            <input class="code-box" type="text" name="mobileNo" id="mobileNo" datatype="m" ajaxurl="/authenticateMobile/checkMobileNo"  nullmsg="请输入手机号码！" errormsg="需填写有效的11位手机号码" />
	        </li>
	        <li class="code-input">
				<input type="text" class="code-box1" id="validateCode" name="validateCode" datatype="n" ajaxurl="/authenticateMobile/checkValidateCode" maxlength="6" errormsg="请输入正确的短信验证码" nullmsg="请输入您的短信验证码！" />
				<input class="yanz" value="获取验证码" id="getValidateCode" type="button"/>
	        </li>
        </ul>
        <div><span id="msg" style="margin-left:0.4em;background:none;"></span></div>
    	<div class="code-enter mt20"><input type="submit" value="下一步" class="btn-red1"/></div>
    </form>
</div>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS_WX}/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		$(".back").click(function(){
			history.go(-1);
		})
	})
</script>
<script>

//获取短信验证码后，120s可以重新发送 
var i = 120;
//防止短信幂操作标识
var reflag = true;

function time(){
	i-=1 ;
	$("#getValidateCode").val('(' + i + ')秒后可重新获取');
	$("#getValidateCode").attr("disabled","disabled");
	$("#mobileNo").attr("readonly","readonly");
	if(i==0){
		$("#getValidateCode").removeAttr("disabled");
		$("#mobileNo").removeAttr("readonly");
		$("#getValidateCode").val("重新获取验证码");
		i=120;
		reflag = true;
		return;
	}
	setTimeout("time()",1000);
}
$(function(){
    $('.card-numbers li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    })
    
    var optType = $("#optType").val();
    var url = "/authenticateMobile/checkValidateCode?optType=" + optType;
    $("#validateCode").attr("ajaxurl", url);
   
    
    //验证
    $("#update-form").Validform({
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
        showAllError:true,
        dragonfly:true,
        beforeSubmit:function(curform){
    		//验证 验证码是否正确
    		var validateCode = $("#validateCode").val();
    		var flag = false;
    			$.ajax({
    				tiptype : function(msg,o,cssctl){
						//msg：提示信息;
						//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
						//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
						if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
							var objtip=$('#msg');
							//cssctl(objtip,o.type);
							objtip.text(msg);
							objtip.show();
						}	
					},
    				url : "/authenticateMobile/checkValidateCode",
    				data : {param:validateCode,optType:$("#optType").val()},
    				async : false,
    				success : function(html){
   						if(html == 'y'){
   	         				flag = true;
   	         			}else{
   	             			$("#msg").text(html);//短信验证码错误!
   	         				flag = false;
   		          		}
    			  }
    			});
    	 		return flag;
    		}
    });
    
	$("#getValidateCode").click(function(){
		if(!reflag){
  			return;
  		}
    	reflag = false;
    	var mobileNo = $("#mobileNo").val();
    	var mobileNo_reg = /^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
    	if(mobileNo=="" || mobileNo==null){
 			$("#msg").text("请输入手机号!");
 			reflag = true;
 			return;
 		}
    	if(!mobileNo_reg.test(mobileNo)){
 			$("#msg").text("请输入正确的手机号!");
 			reflag = true;
 			return;
 		}
    	$.post("/authenticateMobile/checkMobileNo",{param:mobileNo},
    			function(data){
    			if(data!='y'){
         			$("#msg").text("该手机号已注册,请重新输入!");
         			reflag = true;
     				return;
    			}else{
    				//获取手机验证码 
     				$.post("/authenticateMobile/getValidateCode",{type:"2",mobileNo:mobileNo,optType:$("#optType").val()},
     				function(data){
     					if(data=='error'){
     		     			$("#msg").text("120秒内只能获取一次验证码");
     		     			reflag = true;
     					}
     					if(data =='y'){
     						$("#msg").text("");
     						time();
     					}
     				});
    			}
    		
    	});
    	
    });

});
</script>
</body>
</html>