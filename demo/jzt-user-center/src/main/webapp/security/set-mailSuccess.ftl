<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title><#if optType == 1>设置邮箱<#elseif optType == 2>修改邮箱 <#else>修改手机</#if></title>
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
                <div class="pross step3"></div>
                <ul>
                    <#if optType == 1>
                		<li class="cur">验证身份</li>
	                    <li class="cur">设置邮箱</li>
	                    <li class="cur">设置成功</li>
                	</#if>
                   <#if optType == 2>
                   		<li class="cur">验证身份</li>
	                    <li class="cur">修改邮箱</li>
	                    <li class="cur">修改成功</li>
                   </#if>
                </ul>
            </div>
        </div>


        <div class="pay-contbg mt5">
            <div class="re-sendbox">
                <i class="icon2"> <#if optType == 1>设置成功 <#else>修改成功</#if></i>
                <p>安全邮箱<span class="col_red">${email!''}</span> <#if optType == 1>设置成功 <#else>修改成功</#if><br/></p>

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
</script>
</body>
</html>