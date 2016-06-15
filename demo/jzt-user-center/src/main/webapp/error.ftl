<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-数据错误</title>
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
 	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
  	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
</head>
<body>
<!-- 头部  -->
<#include 'common/header.ftl'>
<!-- 头部 end  -->
<div class="area-1200 clearfix">
<!-- 会员侧左 -->
<#include 'common/left.ftl'>
<!-- 会员左侧 over -->
    <div class="hy-right fr">
        <div class="wrong-box">
            <h3><#if error??>${error!''}</#if></h3>
            <span>Sorry！</span>
            <h4>您可以做如下操作：</h4>
            <p>1. 检查刚刚的输入数据<br/>
                2. 试试或者<a href="javascript:history.go(-1);">重新打开</a><br/>
                3. 访问本站其它内容</p>
        </div>
    </div>
</div>
<!-- 底部  -->
<#include 'common/footer.ftl'>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>

</body>
</html>