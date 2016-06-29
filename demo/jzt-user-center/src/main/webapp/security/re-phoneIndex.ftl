<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改手机号首页</title>
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
            <div class="re-phonebox">
                <p class="sty1">请先确认当前手机号 <span class="col_red">${hideMobile!'' }</span> 是否能接收短信，再选择修改方式：</p>
                <p class="sty2">基于对您账户的实际情况，我们将提供以下方式供您选择</p>

                <div class="pay-contbg">
                    <div class="tab-tit">
                        <h3>能接收短信</h3>
                        <h4>通过原手机号接收短信校验码的方式修改</h4>
                        <i></i>
                    </div>
                    <ul class="list">
                        <li class="clearfix">
                            <p class="fl">
                                <i class="phone"></i>
                                <span class="title">通过验证短信</span>
                                <span class="cont">如果你的 <span class="col_red">${hideMobile!'' }</span>  手机还在正常使用，请选择此方式</span>
                            </p>
                            <p class="fr"><input id="optType1" onclick="updateMobile(this);" type="button" class="btn-red" value="立即修改" /></p>
                        </li>
                    </ul>
                </div>
                <div class="pay-contbg">
                    <div class="tab-tit">
                        <h3>无法接收短信</h3>
                        <h4>原手机号已丢失或停用，使用非手机身份验证方式修改</h4>
                        <i></i>
                    </div>
                    <ul class="list">
                    	<#if receive>
                        <li class="clearfix">
                            <p class="fl">
                                <i class="mail"></i>
                                <span class="title mt10">通过邮箱修改</span>
                                <!--<span class="cont">如果你的 <span class="col_red">185 **** 8678</span>  手机还在正常使用，请选择此方式</span>-->
                            </p>
                            <p class="fr"><input id="optType2" onclick="updateMobile(this);" type="button" class="btn-red" value="立即修改" /></p>
                        </li>
                        </#if>
                        <li class="clearfix">
                            <p class="fl">
                                <i class="qc"></i>
                                <span class="title">通过人工服务</span>
                                <span class="cont">请联系客服电话 <strong>400-10-54315</strong></span>
                            </p>
                        </li>
                    </ul>
                </div>
				
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
    $('.tab-tit').on('click',function(){
        $(this).children('i').toggleClass('cur');
        $(this).next('ul').toggleClass('cur');
    })
})
function updateMobile(obj){
	var optId = obj.id;
	if(optId=='optType1'){
		location.href="/authenticateMobile/authenticateIdentity?optType=0";
	}
	if(optId=='optType2'){
		$.post("/userEmailOpt/modMobileEmAuth",function(data){
			var json = parseJson(data);
			if(json.status == 'error01'){
				location.href="http://uc.54315.com";
				return;
			}
			location.href="/userEmailOpt/emailSended?toEmail=" + json.toEmail+"&optType=0"+"&data=" + json.data;
		});
	}
	
	function parseJson(text) {
		try {
			return JSON.parse(text);//ie 89 ff ch
		} catch (e) {
			return eval('(' + text + ')'); //ie7
		}
	}
	
}
</script>
</body>
</html>