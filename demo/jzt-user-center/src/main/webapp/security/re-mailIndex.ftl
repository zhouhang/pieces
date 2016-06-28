<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改邮箱首页</title>
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
            <div class="re-phonebox">
                <p class="sty2">基于对您账户的实际情况，我们将提供以下方式供您选择</p>

                    <ul class="list mial cur">
                        <li class="clearfix">
                        	<p class="fl">
                                <i class="phone"></i>
                                <span class="title">通过验证短信</span>
                                <span class="cont">如果你的 <span class="col_red">${hideMobile!'' }</span>  手机还在正常使用，请选择此方式</span>
                            </p>
                            <p class="fr"><input type="button" class="btn-red" id="modEmail" value="立即修改" /></p>
                        </li>
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

<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/smart_footer.ftl" />
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
	$(function(){
		$("#modEmail").click(function(){
			location.href="/authenticateMobile/authenticateIdentity?optType=2"
		});
	})

</script>
</body>
</html>