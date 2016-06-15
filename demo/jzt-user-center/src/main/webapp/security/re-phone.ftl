<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>
    	${optTypeTitle!'' }
    </title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/pay.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css"  href="${RESOURCE_JS}/js/Validform/css/style.css" />
</head>
<body>
<#include "common/smart_header.ftl" /> 
<!--topper strat-->
<div class="tophr"></div>
<div class="pay-bg pb30 clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pa30">
            <div class="re-pross">
                <div class="pross step${step!''}"></div>
                <ul>
                    <li <#if step==1> class="cur"<#else>class=""</#if>>验证身份</li>
                    <#if optType==0>
                    	<li <#if step==2> class="cur"<#else>class=""</#if>>修改手机</li>
                    	<li <#if step==3> class="cur"<#else>class=""</#if>>修改成功</li>
                    </#if>
                    <#if optType==1>
                    	 <li <#if step==2> class="cur"<#else>class=""</#if>>设置邮箱</li>
                   		 <li <#if step==3> class="cur"<#else>class=""</#if>>设置成功</li>
                    </#if>
                    <#if optType==2>
                    	 <li <#if step==2> class="cur"<#else>class=""</#if>>修改邮箱</li>
                    	 <li <#if step==3> class="cur"<#else>class=""</#if>>修改成功</li>
                    </#if>
                </ul>
            </div>
        </div>


        <div class="pay-contbg mt5">
         <form action="/authenticateMobile/validataCiphertext"  id="reForm" method="GET">
            <div class="re-form">
                <ul>
                    <li><label>手机号码</label><span>${hideMobileNo!'' }</span></li>
                    <input type="hidden" id="mobileNo" value="${user.mobile!''}"/>
                    <input type="hidden" id="data" name="data" value="${data!'' }"/><!-- 密文参数 -->
                    <input type="hidden" id="optType" name="optType" value="${optType!''}"/>
                    <li><label>手机验证码</label>
                    	<input type="text" class="text" id="validateCode" datatype="n" maxlength="6" ajaxurl="/authenticateMobile/checkValidateCode" errormsg="请输入正确的短信验证码" nullmsg="请输入您的短信验证码！" />
                    	<input id="getValidateCode" type="button" class="yzm ml10" value="获取验证码">
                    	<span id="vcodeMsg" class="Validform_checktip"></span>
                    </li>
                    <li class="mt20"><label></label><input type="submit" class="btn-red" value="下一步"> 
                    	<#if optType==0>
                    		<a href="/authenticateMobile" class="blue">重新选择验证方式</a> 
                    	<#elseif optType==2>
                    		<a href="/userEmailOpt/optEmail?optType=2" class="blue">重新选择验证方式</a>
                    	</#if>
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
	if(i==0){
		$("#getValidateCode").removeAttr("disabled");
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
    $("#reForm").Validform({
        tiptype:3,
        showAllError:true,
        dragonfly:true,
   		beforeSubmit:function(curform){
		//验证 验证码是否正确
		var validateCode = $("#validateCode").val();
		var flag = false;
			$.ajax({
			  url: "/authenticateMobile/checkValidateCode",
			  data: {param:validateCode,optType:$("#optType").val()},
			  async: false,
			  success: function(html){
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
    
    //获取短信验证码
    $("#getValidateCode").click(function(){
    	var mobileNo = $("#mobileNo").val();
    	$.post("/authenticateMobile/getValidateCode",{type:"1",mobileNo:mobileNo,optType:$("#optType").val()},
    		function(data){
				if(data=='error'){
					$("#vcodeMsg").addClass("Validform_checktip Validform_wrong");
	     			$("#vcodeMsg").text("120秒内只能获取一次验证码");
	     			reflag = true;
				}
				if(data=='y'){
					$("#vcodeMsg").removeClass("Validform_wrong");
					$("#vcodeMsg").text("");
					time();
				}
			});
    });
});
</script>
</body>
</html>