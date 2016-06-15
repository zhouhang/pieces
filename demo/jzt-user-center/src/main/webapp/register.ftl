<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材 中药材电子商务 有质量保障的仓储式中药材综合服务平台</title>
    <meta name="description" content="珍药材网-中国首创最大最有保障的线上线下相结合的电子商务仓储式综合服务平台，提供各类大品种药材、小品种药材、涨跌价紧俏药材，保证现货，保证中药材质量，提供线上交易、仓储服务、物流运输、融资服务、委托服务和价格行情资讯，让你感受到最全面、最专业的中药材买卖及各类相关综合服务。"/>
	<meta name="keywords" content="珍药材网，中药材，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/list.css"  />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/index.css"  />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/common.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_JS}/js/Validform/css/style.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
	 
</head>
<body>

<!-- 头部  -->
    <div class="topper sty1">
    <div class="area-1200">
        <div class="logo clearfix">
        	<a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
            <span>欢迎注册</span>
        </div>
    </div>
    </div>
<!-- 头部 end  -->
<div class="area-1200">
    <div class="register-bg">
        <div class="register-box fr">
            <form class="registerform" action="ucUserRegister" method="post">
                <ul>
                    <li><label><i class="red">*</i> 用户名：</label>
                        <input type="text"  value="" id="reUserName" name="username" class="register-text" datatype="dn5-25" nullmsg="请填写用户名！" ajaxurl="userNameisExist" errormsg="填写错误，请重新输入"/>
                    	<span class="Validform_checktip"></span>    
                    </li>
                    <li><p>5-25个字母或字母+数字组合，如zyc54315,jztzyc。</p></li>

                    <li><label><i class="red">*</i> 密码：</label>
                        <input type="password" class="register-text" name="password" datatype="*6-16" nullmsg="请设置密码！" errormsg="填写错误，请重新输入" />
                        <span class="Validform_checktip"></span>
                    </li>
                     <li><p>6-16个字符，区分大小写</p></li>

                    <li><label><i class="red">*</i> 确认密码：</label>
                        <input type="password"  class="register-text" name="repassword"  datatype="*" recheck="password" nullmsg="请再输入一次密码！" errormsg="填写错误，两次密码不一致" />
                        <span class="Validform_checktip"></span>
                        </li>
                    <li><p>请再次输入您的密码</p>
                    </li>
                    <li><label><i class="red">*</i> 公司/姓名：</label>
                        <input type="text" class="register-text" name="companyname" datatype="cn" nullmsg="请填写姓名或公司名！" errormsg="填写错误，请重新输入" />
                        <span class="Validform_checktip"></span>
                    </li>
                    <li><p>请输入公司名，个体经营者或个人经营者可填写姓名</p></li>
                    <li><label><i class="red">*</i> 手机号码：</label>
                        <input type="text" class="register-text" name="mobile" id="mobile" datatype="mo" ajaxurl="mobileisExist"  nullmsg="请输入手机号码！" errormsg="需填写有效的11位手机号码" />
                        <span id="mobMsg" class="Validform_checktip"></span>
                    </li>
                    <li><p>有效的11位手机号码</p></li>
                     <li><label><i class="red">*</i> 图文验证码：</label>
                        <input type="text" class="register-text yzm" name="picCode" id="picCode" datatype="*" ajaxurl="/checkVode"  nullmsg="请输入图片验证码！" errormsg="请输入正确的图片验证码" />
                       <a href="javascript:void(0);" id="vcodeA" style="width:170px;height:30px;padding: 0px;margin: 0px; display:inline-block\9; *display:inline;">
        					<img id="vcodeImg" src="/vcode" style="padding-left: 5px;"/>
        					看不清?换一张
      					</a>
                        <span id="vcodeMsg" class="Validform_checktip"></span>
                    </li>
                    <li><p>请填写图片中的字符，不区分大小写</p></li>
                    <li><p style="margin-left:100px;"><input type="button" class="yzm-btn" id="getCode" value="获取验证码" /></p></li>
                    <li class="mt10"><label><i class="red">*</i> 手机验证码：</label>
                    	<input type="text" class="register-text" name="mobileCode" datatype="n" ajaxurl="/mobileCodeisExist" maxlength="6" errormsg="请输入正确的短信验证码" nullmsg="请输入手机收到的验证码！" />
<!--                     	<span id="mobCodeMsg" class="Validform_checktip"></span>   -->
                    </li>
                    <li><p id="mobileMsg">请输入手机收到的验证码</p>
                    </li>
                    <li class="mt5"><label></label>&nbsp;&nbsp;<input class="rt2" id="agree" name="agree" datatype="*" type="checkbox" checked nullmsg="请阅读《珍药材会员服务协议》《珍药材交易服务协议》！" /> <i>我已阅读并接受<a style="color:#fe4002" href="http://help.54315.com/view-532540433-720565143.html" target="_blank">《珍药材会员服务协议》</a><a style="color:#fe4002" href="http://help.54315.com/view-2057952693-1773087888.html" target="_blank">《珍药材交易服务协议》</a></i></li>
                    <li style="line-height:43px; padding-left:10px;"><span class="Validform_checktip"></span></li>
                    <li> <label></label>
                        <input type="submit" id="subBtn" class="register-btn" value="同意协议并注册" disabled="disabled"/><span class="yy_account" style="line-height: 48px;margin-left: 10px;vertical-align: middle;display: inline-block;padding-top: 10px;">&nbsp;&nbsp;已有账号，请<a style="color:#fe4002" href="https://passport.54315.com/login?service=http://uc.54315.com/casuc">登录</a></span></li>
                </ul>
            </form>

        </div>
    </div>
    </div>
<!-- 底部  -->
		<#include "common/footer.ftl">
<!-- 底部 end  -->


<script>
	//获取短信验证码后，120s可以重新发送 
	var i = 120;
	//防止短信幂操作标识
	var reflag = true;
	
	function time(){
		i-=1 ;
		$("#getCode").val('(' + i + ')秒后可重新获取');
		$("#getCode").attr("disabled","disabled");
		$("#mobile").attr("readonly","readonly");
		$("#mobileMsg").html("验证码已发送至您的手机，5分钟内输入有效。");
		if(i==0){
			$("#getCode").removeAttr("disabled");
			$("#mobile").removeAttr("readonly");
			$("#getCode").val("重新获取验证码");
			i=120;
			reflag = true;
			return;
		}
		setTimeout("time()",1000);
	}
    $(function(){
         $(".registerform").Validform({
        	 tiptype:3,
             showAllError:true,
             dragonfly:true,
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
     					return "填写错误，请重新输入";
     				}
     				if(reg2.test(gets)){
     					return "填写错误，请重新输入";
     				}
     				return true;
     			}
     		},
     		beforeSubmit:function(curform){
     			//验证验证码是否正确
     			var mobileCode = $("input[name=mobileCode]").val();
     			var flag = false;
     			$.ajax({
    				  url: "/mobileCodeisExist",
    				  data: "param=" + mobileCode,
    				  async: false,
    				  success: function(html){
	     					if(html == 'y'){
	              				flag = true;
	              			}else{
	                  			$("input[name=mobileCode]").next("span").addClass("Validform_wrong");
	                  			$("input[name=mobileCode]").next("span").text(html);//短信验证码错误!
	              				flag = false;
	              			}
    				   	 }
    				  });
         		return flag;
    		}
         });
         
         $('input:not(button)').focus(function(){
       		this.validform_lastval=null;
       		/* alert($(this).find("[datatype]").data); */
       		//$(this).find("[datatype]").data("cked");
       		if($(this).next().is('span')){
       			$(this).next('span').remove();  
       			$(this).after('<span class="Validform_checktip"></span>');
       		}
       		if($(this).next().is('input')){
       			$(this).next().next('span').remove();   
       			$(this).next().after('<span class="Validform_checktip"></span>');
       		}
       	});
         
       	 //获取验证码
         $("#getCode").click(function(){
        	if(!reflag){
      			return;
      		}
        	reflag = false;
     		var mobile = $("#mobile").val();
     		var picCode = $("#picCode").val();
     		var reg = /^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
     		if(mobile=="" || mobile==null){
     			//alert("请输入手机号");
     			$("#mobMsg").addClass("Validform_checktip Validform_wrong");
     			$("#mobMsg").text("请输入手机号!");
     			reflag = true;
     			return;
     		}
     		if(!reg.test(mobile)){
     			//alert("请输入正确的手机号!");
     			$("#mobMsg").addClass("Validform_checktip Validform_wrong");
     			$("#mobMsg").text("请输入正确的手机号!");
     			reflag = true;
     			return;
     		}
     		if(picCode == null || picCode == ""){
     			$("#vcodeMsg").addClass("Validform_checktip Validform_wrong");
     			$("#vcodeMsg").text("请先填写图片验证码!");
     			reflag = true;
     			return;
     		}
     		
     		//验证手机号
     		$.post("checkMobileisExist",{
     			"mobile":mobile
     		},function(data){
     			if(data!='y'){
     				//alert("该手机号已注册,请重新输入！");
     				$("#mobMsg").addClass("Validform_checktip Validform_wrong");
         			$("#mobMsg").text("该手机号已注册,请重新输入!");
         			reflag = true;
     				return;
     			}else{
     				//验证验证码是否正确
     				$.post("/checkVode",{param:picCode},function(data){
     					if(data != 'y'){
     						$("#vcodeMsg").addClass("Validform_checktip Validform_wrong");
     		     			$("#vcodeMsg").text(data);
     		     			$('#vcodeImg').attr('src','/vcode?t='+new Date().getTime());
     		     			reflag = true;
     	     				return;
     					}else{
     						//获取手机验证码 
     	     				$.post("/getMobileCode",{
     	     					"memberMobile":mobile
     	     				},function(data){
     	     					if(data=='eorr'){
     	     						$("#vcodeMsg").addClass("Validform_checktip Validform_wrong");
     	     		     			$("#vcodeMsg").text("120秒内只能获取一次验证码");
     	     		     			reflag = true;
     	     					}
     	     					if(data=='y'){
     	     						time(120);
     	     					}
     	     				});
     					}
     				});
     				
     			}
     		});

     	 });
         
       //验证码事件绑定
		$('#vcodeA').click(function() {
			$("#picCode").val("");
			$('#vcodeImg').attr('src','/vcode?t='+new Date().getTime());
		});
       //防止按钮加载不上的问题
		$("#subBtn").removeAttr("disabled");
    });
</script>

	<!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  start -->
    <script>
     <!--声明_czc对象:-->
	var _czc = _czc || [];
	<!--绑定siteid，请用您的siteid替换下方"XXXXXXXX"部分-->
	_czc.push(["_setAccount", "1254793674"]);
	<!--注册时获取验证码事件-->
	_czc.push(["_trackEvent", "验证码", "获取验证码", "注册时获取手机验证码", "", "getCode"]);
	<!--注册时点击同意协议并注册按钮事件-->
	_czc.push(["_trackEvent", "同意协议并注册", "同意协议并注册", "注册时点击同意协议并注册按钮", "", "subBtn"]);
    <!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  end -->
    
   </script>
</body>
</html>