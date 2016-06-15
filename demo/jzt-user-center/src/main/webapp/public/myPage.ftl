<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-珍药宝</title>
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/reset.css" />
	<link rel="stylesheet" type="text/css"  href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
	<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>
<#include "common/header.ftl" /> 
<div class="area-1200 clearfix">
	<#include "common/left.ftl" /> 

	<div class="hy-right fr">
	     <IFRAME ID="payLoingFrame" SRC="/ucs/payLogin?type=${type }" style="width:100%; border:0 none;" ></IFRAME> 
	</div>
</div>
<#include "common/footer.ftl" /> 
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		var Hei = $(document).height();
		$('#payLoingFrame').css('height',Hei);
	})
</script>
</body>
</html>