<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>错误页面</title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/pay.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!--topper strat-->
<#include "common/smart_header.ftl" />
<div class="tophr"></div>
<div class="pay-bg clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pay-caption">
            <div class="caption" align="center"><i class="payWro"></i> <span style="text-decoration: none;">${errorMsg!''}</span></div>
        </div>
    </div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/smart_footer.ftl" /> 
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
</body>
</html>