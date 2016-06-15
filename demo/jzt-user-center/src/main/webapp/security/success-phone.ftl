<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改手机</title>
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
                <div class="pross step3"></div>
                <ul>
                    <li <#if step==1> class="cur"<#else>class=""</#if>>验证身份</li>
                    <li <#if step==2> class="cur"<#else>class=""</#if>>修改手机</li>
                    <li <#if step==3> class="cur"<#else>class=""</#if>>修改成功</li>
                </ul>
            </div>
        </div>


        <div class="pay-contbg mt5">
            <div class="re-sendbox">
                <i class="icon2"> 修改成功</i>
                <p>您可以使用 <span class="col_red">${user.mobile!'' }</span> 登录珍药材<br/>
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