<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>
    	${optTypeTitle}
    </title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/pay.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
</head>
<body>
<!--topper strat-->
<#include "common/smart_header.ftl" />
<div class="tophr"></div>
<div class="pay-bg pb30 clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pa30">
            <div class="re-pross">
                <div class="pross step2"></div>
                <ul>
                <#if optType==0>
               		<li>验证身份</li>
                    <li class="cur">修改手机</li>
                    <li>修改成功</li>
                </#if>
                <#if optType=1>
                   	<li>验证身份</li>
                    <li class="cur">设置邮箱</li>
                    <li>设置成功</li>
                </#if>
                <#if optType=2>
                   	<li>验证身份</li>
                    <li class="cur">修改邮箱</li>
                    <li>修改成功</li>
                </#if>
                    
                </ul>
            </div>
        </div>

        <div class="pay-contbg mt5">
            <div class="re-sendbox">
                <i class="icon"></i>
                <#if optType==0>
                <p>我们已给您的邮箱<span class="col_red">${toEmail!'' }</span>发送了一封邮件，请您进入邮箱后修改</p>
                <#else>
                <p>邮件已发送到您的邮箱 <span class="col_red">${toEmail!'' }</span>。请按邮件中的提示操作，完成<#if optType==1>设置<#else>修改</#if>。<br/>
                    亲，进入到邮箱后，完成激活才算<#if optType==1>设置<#else>修改</#if>成功。</p>
                </#if>
                <div class="xx"></div>
                <p>一直没有收到邮件？  请先检查是否在垃圾邮件中。如果还未收到，请 <input type="button" class="yzm" value="点击重新发送邮件"></p>
                <input type="hidden" id="em" name="email" value="${toEmail!'' }"/>
                <input type="hidden" id="data" name="data" value="${data!'' }" >
                <input type="hidden" id="optType" name="optType" value="${optType!'' }" >
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
	var i = 120;
	function time(){
		i-=1 ;
		$(".yzm").attr("disabled","disabled");
		$(".yzm").val('邮件已发送,(' + i + ')秒后可重新发送邮件');
		if(i==0){
			$(".yzm").removeAttr("disabled");
			$(".yzm").val("点击重新发送邮件");
			i=120;
			return;
		}
		setTimeout("time()",1000);
	}
	
	
	$(function(){
		//重新发送邮件
		$(".yzm").click(function(){
			var _em = $("#em").val();
			var _optType = $("#optType").val();
			var _data = $("#data").val();
			if(_em == null || _em == ""){
				return;
			}
			//手机
			if(_optType == '0'){
				//发送邮件
				$.post("/userEmailOpt/modMobileEmAuth",function(data){
					time();
				});
				return;
			}
			//邮箱
			if(_data == null || _data == ""){
				return;
			}else{
				//发送邮件
				$.post("/userEmailOpt/sendEmail",{newEmail:_em,data:_data},function(data){
					time();
				});
			}
		});
	})
</script>
</body>
</html>