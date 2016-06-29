<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改手机号</title>
   <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/pay.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_JS}/js/Validform/css/style.css" />
</head>
<body>
<!--topper strat-->
<#include "common/smart_header.ftl" />
<div class="tophr"></div>
<div class="pay-bg pb30 clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pa30">
            <div class="re-pross">
                <div class="pross step${step!''}"></div>
                <ul>
                    <li <#if step==1> class="cur"<#else>class=""</#if>>验证身份</li>
                    <li <#if step==2> class="cur"<#else>class=""</#if>>修改手机</li>
                    <li <#if step==3> class="cur"<#else>class=""</#if>>修改成功</li>
                </ul>
            </div>
        </div>


        <div class="pay-contbg mt5">
        <form action="/authenticateMobile/updateMobileNo" id="update-form" method="post">
            <div class="re-form">	
            	<input type="hidden" value="${optType!''}" id="optType"/>
            	<input type="hidden" value="${data!''}" name="data" >
                <ul>
                    <li>
                    	<label>手机号码</label>
                    	<input type="text" class="text" name="mobileNo" id="mobileNo" datatype="mo" ajaxurl="/authenticateMobile/checkMobileNo"  nullmsg="请输入手机号码！" errormsg="需填写有效的11位手机号码" />
                    	<span id="mobMsg" class="Validform_checktip"></span>
                    </li>
                    <li>
                    	<label>手机验证码</label>
                    	<input type="text" class="text" id="validateCode" name="validateCode" datatype="n" ajaxurl="/authenticateMobile/checkValidateCode" maxlength="6" errormsg="请输入正确的短信验证码" nullmsg="请输入您的短信验证码！" />
                    	<input id="getValidateCode" type="button" class="yzm ml10" value="获取验证码">
                    	<span id="vcodeMsg" class="Validform_checktip"></span>
                    </li>
                    <li class="mt20"><label></label><input type="submit" class="btn-red" value="下一步"> 
                    	<a href="/authenticateMobile" class="blue">重新选择验证方式</a> 
                    </li>
                </ul>
            </div>
		</form>
        </div>
    </div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/smart_footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
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
        tiptype:3,
        showAllError:true,
        dragonfly:true,
        beforeSubmit:function(curform){
    		//验证 验证码是否正确
    		var validateCode = $("#validateCode").val();
    		var flag = false;
    			$.ajax({
    			  url : "/authenticateMobile/checkValidateCode",
    			  data : {param:validateCode,optType:$("#optType").val()},
    			  async : false,
    			  success : function(html){
   						if(html == 'y'){
   	         				flag = true;
   	         			}else{
   	             			$("#validateCode").next().next("span").addClass("Validform_wrong");
   	             			$("#validateCode").next().next("span").text(html);//短信验证码错误!
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
 			$("#mobMsg").addClass("Validform_checktip Validform_wrong");
 			$("#mobMsg").text("请输入手机号!");
 			reflag = true;
 			return;
 		}
    	if(!mobileNo_reg.test(mobileNo)){
 			$("#mobMsg").addClass("Validform_checktip Validform_wrong");
 			$("#mobMsg").text("请输入正确的手机号!");
 			reflag = true;
 			return;
 		}
    	$.post("/authenticateMobile/checkMobileNo",{param:mobileNo},
    			function(data){
    			if(data!='y'){
    				$("#mobMsg").addClass("Validform_checktip Validform_wrong");
         			$("#mobMsg").text("该手机号已注册,请重新输入!");
         			reflag = true;
     				return;
    			}else{
    				//获取手机验证码 
     				$.post("/authenticateMobile/getValidateCode",{type:"2",mobileNo:mobileNo,optType:$("#optType").val()},
     				function(data){
     					if(data=='error'){
     						$("#vcodeMsg").addClass("Validform_checktip Validform_wrong");
     		     			$("#vcodeMsg").text("120秒内只能获取一次验证码");
     		     			reflag = true;
     					}
     					if(data =='y'){
     						$("#vcodeMsg").removeClass("Validform_wrong");
     						$("#vcodeMsg").text("");
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