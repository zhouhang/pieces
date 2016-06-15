<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>${optTypeTitle}</title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/pay.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/Validform/css/style.css" />
</head>
<body>
<!--topper strat-->
<#include "common/smart_header.ftl" />
<div class="tophr"></div>
<div class="pay-bg pb30 clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pa30">
            <div class="re-pross">
                <div class="pross step1"></div>
                <ul>
                	<#if optType == 1>
                		<li class="cur">验证身份</li>
                    	<li>设置邮箱</li>
                    	<li>设置成功</li>
                	</#if>
                   <#if optType == 2>
                   		<li class="cur">验证身份</li>
                    	<li>修改邮箱</li>
                    	<li>修改成功</li>
                   </#if>
                </ul>
            </div>
        </div>


        <div class="pay-contbg mt5">
            <div class="re-caption">
                <p>请确保您输入的邮箱能正常接收激活邮件，新邮箱才能生效。</p>
            </div>
            <div class="re-form">
            	<form id="setEmailForm" action="/userEmailOpt/sendEmail" method="POST">
	                <ul>
	                    <li><label>新邮箱</label><input class="text" name="newEmail" ajaxurl="/userEmailOpt/emailIsExist" datatype="e" nullmsg="请输入邮箱"/></li>
	                    <li><label>再输一次新邮箱</label><input class="text" name="reNewEmail" recheck="newEmail" datatype="e" nullmsg="请再次输入邮箱" errormsg="两次输入的邮箱不一致!"/></li>
	                    <li class="mt20"><label></label><input type="submit" class="btn-red" value="下一步"> <#if optType == 2> <a href="/userEmailOpt/optEmail?optType=2" class="blue">重新选择验证方式</a></#if> </li>
	                </ul>
	                <input type="hidden" name="data" value="${data!'' }"/>
                </form>
            </div>

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
$(function(){
	
    $('.card-numbers li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    })
    $("input[name=newEmail]").focus();
    //验证
    $("#setEmailForm").Validform({
        tiptype:4,
        showAllError:true,
        dragonfly:false,
        ajaxPost:true,
        beforeSubmit:function(curform){
			var _em = $("input[name=newEmail]").val();
			$.ajax({
			   type: "POST",
			   url: "/userEmailOpt/emailIsExist",
			   data: {param:_em},
			   async:true,
			   success: function(msg){
			     if(msg != "y"){
			    	 $("input[name=newEmail]").next("span").addClass("Validform_wrong");
			    	 $("input[name=newEmail]").next("span").text(msg.errorMsg)
			     }
			   }
			});
		},
        callback:function(data){
        	if(data.status == "yes"){
        		location.href="/userEmailOpt/emailSended?data=" + data.dt + "&toEmail=" + data.toEmail + "&optType=" + data.optType;
        	}else if(data.status == "error01"){//用户未登录
        		location.href="http://uc.54315.com";
        	}else if(data.status == "errror02"){
        		 $("input[name=newEmail]").next("span").addClass("Validform_wrong");
		    	 $("input[name=newEmail]").next("span").text(data.errorMsg);
        	}
		}
    });

});
</script>
</body>
</html>