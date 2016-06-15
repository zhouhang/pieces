<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-选择银行账户</title>
    <link href="${RESOURCE_PAY}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_PAY}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_PAY}/css/pay.css" type="text/css" rel="stylesheet" />
</head>
<body>
<!--topper strat-->
<#include "common/head.ftl">
<!--topper end-->
<div class="tophr"></div>
<div class="pay-bg clearfix">
    <div class="area-1200 ">
        <div class="pay-contbg">
            <ul class="pay-head clearfix">
                <li>您正在使用珍药材担保交易</li>
                <li>收款方：珍药材网</li>
                <li>订单编号：${payInfo.orderId }</li>
                <li>
                	<#if (amtTypes??)>
                		<#list amtTypes?keys as atkey>
                			<#if (payInfo.amtType == atkey)>${amtTypes[atkey]}&nbsp;-&nbsp;</#if>
                		</#list>
                	</#if>
                	${payInfo.orderTitle }
                </li>
                <li>金额：${payInfo.amount }元</li>
                <li>客服热线：400-10-54315</li>
            </ul>
        </div>
        <br/>
		<div id="Account">
			<tr>
				<td>
					<iframe ID="Frame1" SRC="/ucs/pay?orderId=${payInfo.orderId!''}&amount=${payInfo.amount!''}&amtType=${payInfo.amtType!''}&userId=${payInfo.userId!''}&recieveId=${payInfo.recieveId!''}&sourceSys=${payInfo.sourceSys!''}&orderTitle=${payInfo.orderTitle!''}&signData=${payInfo.signData!''}&data=${data!''}&signdata=${signdata!'' }&paymentNo=${paymentNo!''}" allowTransparency="true" style="width:100%; border:0 none;" ></iframe>
				</td>
			</tr>
		</div>
    </div>
</div>
<!-- 祥情页主体over -->
<!--弹层 start-->
<div class="pop-up" style="display: none;">
    <h3 class="title">温馨提示</h3>
    <i class="close"></i>
    <!--弹层一-->
    <div class="cop1" style="display: none;">
        <i class="bgicon"></i>
        <p>请选择一家银行！</p>
        <input type="button" class="btn-red" id="rePay" value="重新支付" />
    </div>

    <!--弹层二-->
    <div class="cop2" style="display: none;">
        <i class="bgicon"></i>
        <p class="mb10">请您到新打开银联页面上进行支付，<br />支付完成前请不要关闭该窗口。</p>
        <input type="button" class="btn-red" id="finishPay" value="已完成支付" /><input type="button" class="btn-gray ml10" id="problem" value="支付遇到问题" />
    </div>

    <!--弹层三-->
    <div class="cop3" style="display: none;">
        <i class="bgicon"></i>
        <p class="mb10 mt5">如果您的支付遇到问题，<br />请致电客服<strong class="col_red f16"> 400-10-54315。</strong></p>
    </div>

    <!--弹层四-->
    <div class="cop3" style="display: none;">
        <i class="bgicon"></i>
        <p class="mt5">因意外原因，支付未能成功。</p>
        <input type="button" class="btn-red" value="重新支付" />
    </div>

    <!--弹层五--> 
    <div class="cop3" style="display :none;">
        <i class="bgicon"></i>
        <p class="mt5">请选择一家银行！</p>
        <input type="button" class="btn-red" value="重新支付" />
    </div>

</div>
<!--弹层 end-->


<!-- 底部  -->
<div class="foot">
    <div class="area-1200">
            <p class="links" align="center">Copyright© 2014-2015 ，版权所有 鄂ICP备14019602号-2</p>
            <p class="links" align="center">增值电信业务经营许可证-鄂B2-20150052</p>
            <!-- <div class="mt10" align="center"><img src="${RESOURCE_IMG}/images/foot-img1.png"/>&nbsp;&nbsp;&nbsp;&nbsp;<img src="http://10.3.1.151/resources/images/foot-img2.png"/> </div> -->
        </div>
</div>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_PAY}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_PAY}/js/pay.js"></script>
<script>
$(function(){
	
	var Hei = $(document).height();
	$('#Frame1').css('height',Hei);
	
    function Tabs(els,contsID){
        els.on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            contsID.eq($(this).index()).show().siblings().hide();
            if($(this).hasClass('cur')){
                //alert(1);
                contsID.eq($(this).index()).find('li').each(function(){
                    $(this).removeClass('cur');
                })
            }
        })
    }
    Tabs($('.tabs-1 li'),$('#Account .tabscont-1'));

    $('.tabscont-1 ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });

    function bghui(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }
    $('input[name=pay]').on('click',function(){
        $('.pop-up').show();
        bghui();
    });
    /* $('.close').on('click',function(){
        $('.pop-up').hide();
        $('.bghui').remove();
    }) */
    
    $("#b2b-account span").click(function(){
    	//alert($(this).attr("id"));
		$("input[name=code]").val($(this).attr("id"))
	})
	
    $("#finishPay").click(function(){
    	location.href="http://uc.54315.com/order/listinfo";
    })
    
    function Closer(btnID){
    	var popCont = $('.pop-up');
    	$(btnID).on('click',function(){
    		popCont.hide();
    		$('.bghui').remove();
    		
    	})   	
    }
    Closer('#rePay');
    Closer('.close');
});
</script>
</body>
</html>