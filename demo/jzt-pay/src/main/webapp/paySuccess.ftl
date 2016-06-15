<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>付款成功</title>
    <link href="${RESOURCE_PAY}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_PAY}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_PAY}/css/pay.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${RESOURCE_PAY}/js/Validform/css/style.css" />
</head>
<body>
<!--topper strat-->
<#include "common/head.ftl">
<!--topper end-->
<div class="tophr"></div>
<div class="pay-bg clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pay-caption">
            <div class="caption" align="center"><i class="paySuc"></i> 您的付款已成功！ <a href="javasript:void(0);" onclick="javascript:CloseWebPage();">点击关闭</a></div>
        </div>
    </div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<div class="foot">
    <div class="area-1200">
            <p class="links" align="center">Copyright© 2014-2015 ，版权所有 鄂ICP备14019602号-2</p>
            <p class="links" align="center">增值电信业务经营许可证-鄂B2-20150052</p>
          <!--     <div class="mt10" align="center"><img src="${RESOURCE_IMG}/images/foot-img1.png"/>&nbsp;&nbsp;&nbsp;&nbsp;<img src="../resources/images/foot-img2.png"/> </div>
       	  -->
        </div>
</div>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_PAY}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_PAY}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_PAY}/js/Validform/js/Validform_v5.3.2.js"></script>
<script>
$(function(){

    $('.card-numbers li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    })
    //验证
    $(".pay-form").Validform({
        tiptype:3,
        showAllError:true,
        dragonfly:true
    });

});

function CloseWebPage() {  
	if (navigator.userAgent.indexOf("MSIE") > 0) {  
		if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {  
			window.opener = null; window.close();  
		}  
		else {  
			window.open('', '_top'); window.top.close();  
		}  
	}  
	else if (navigator.userAgent.indexOf("Firefox") > 0) {  
		window.location.href = 'about:blank ';  
		//window.history.go(-2);  
	}  
	else {  
		window.opener = null;   
		window.open('', '_self', '');  
		window.close();  
	}  
  }
</script>
</body>
</html>