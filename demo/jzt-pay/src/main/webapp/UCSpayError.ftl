<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>付款错误</title>
</head>
<body>

<div class="pay-bg clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg pay-caption">
            <div class="caption" align="center">
<!-- 	            <i class="payWro"></i>  -->
<!-- 	            “<span style="text-decoration: underline">XXXXX</span>”错误！ -->
				<#if (payErrorMsg.code == '013')>
				  <script type="text/javascript">
				  		//alert("未登录");
				  		location.href="http://uc.54315.com/";
				  </script>
				<#else>
					${payErrorMsg.code!''}</br>
					${payErrorMsg.codeDes!''}
				</#if>
            </div>
        </div>
    </div>
</div>

<!-- 祥情页主体over -->

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
</script>
</body>
</html>