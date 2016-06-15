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
                <!-- <li>收款方：珍药材网</li> -->
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

        <h2 class="title1 mt20">选择银行账户</h2>
            <ul class="tabs-1">
	            <#if (payOrder.payChannel)??>
	            	<#if (payOrder.payChannel) = 0>
	            		 <li class="cur">珍药宝</li>
	            	<#elseif (payOrder.payChannel) = 1>
	            		  <li class="cur">企业账户</li>
	            	<#elseif (payOrder.payChannel) = 3>
	            		 <li class="cur">线下支付</li>	
	            	</#if>
	            <#else>
	            	<li class="cur">珍药宝</li>
	            	<!-- <li>个人账户</li> -->
	                <li>企业账户</li>
	                <li>线下支付</li>	
	            </#if>
            </ul>
        <div id="Account">
   		<#if (payOrder.payChannel)??>
          	<#if (payOrder.payChannel) = 0>
          		 <!--如果买家已经通过珍药宝支付保证金 则 不再显示银联支付 -->
	       		<div class="tabscont-1 pay-contbg clearfix"  style="display: block;">
	       			 <ul class="cur mt20">
	                    <iframe ID="Frame1" SRC="/ucs/pay?orderId=${payInfo.orderId!''}&amount=${payInfo.amount!''}&amtType=${payInfo.amtType!''}&userId=${payInfo.userId!''}&recieveId=${payInfo.recieveId!''}&sourceSys=${payInfo.sourceSys!''}&orderTitle=${encryptOrderTitle!''}&signData=${payInfo.signData!''}&data=${data!''}&signdata=${signdata!'' }&paymentNo=${paymentNo!''}" allowTransparency="true"  style="width:1170px; height:280px; border:0 none;" ></iframe>
	                 </ul>
	            </div>
          	<#elseif (payOrder.payChannel) = 1>
          		 <!-- 银联B2B -->
            	<div id="b2b-account" class="tabscont-1 pay-contbg clearfix" style="display: block;">
            	<div class="downline-box">
               	 	<p class="coption1">支付企业银行账户通过中国银联完成支付，个人银行账户请使用 “ 招行珍药宝支付 ” 或  “ 线下转账支付 ” 方式进行</p>
                </div>
                <ul class="clearfix sign mt30">
                    <li><span id="1-CCB" class="img construction"></span><i>企业</i></li>
                    <li><span id="1-CMB" class="img merchants"></span><i>企业</i></li>
                    <li><span id="1-ICBC" class="img icbc"></span><i>企业</i></li>
                    <li><span id="1-ABC" class="img abc"></span><i>企业</i></li>
                    <li><span id="1-BOC" class="img china"></span><i>企业</i></li>
                    <li><span id="1-PAB" class="img pingan"></span><i>企业</i></li>
                    <li><span id="1-BOCOM" class="img jiaotong"></span><i>企业</i></li>
                    <li><span id="1-CMBC" class="img minsheng"></span><i>企业</i></li>
                    <li><span id="1-CNCB" class="img zhongxin"></span><i>企业</i></li>
                    <li><span id="1-HXB" class="img huaxia"></span><i>企业</i></li>
                    <li><span id="1-CEB" class="img guada"></span><i>企业</i></li>
                    <li><span id="1-SPDB" class="img pufa"></span><i>企业</i></li>
                    <li><span id="1-BEA" class="img dongya"></span><i>企业</i></li>
                    <li><span id="1-GDB" class="img guangfa"></span><i>企业</i></li>
                </ul>
                <input type="button" class="btn-red" id="b2b-pay" value="立即支付">
            </div>
            <form action="/pay" id="form" method="post" target="_blank">
                <input type="hidden" name="orderId" value="${payInfo.orderId }"/>
				<input type="hidden" name="amount" value="${payInfo.amount }"/>
				<input type="hidden" name="amtType" value="${payInfo.amtType }"/>
				<input type="hidden" name="userId" value="${payInfo.userId }"/>
				<input type="hidden" name="code" value=""/>
				<input type="hidden" name="recieveId" value="${payInfo.recieveId }"/>
				<input type="hidden" name="sourceSys" value="${payInfo.sourceSys }"/>
				<input type="hidden" name="orderTitle" value="${payInfo.orderTitle }"/>
				<input type="hidden" name="signData" value="${payInfo.signData }"/>
             </form>
          	<#elseif (payOrder.payChannel) = 3>
          	<!-- 线下支付 -->
             <div class="tabscont-1 pay-contbg clearfix" style="display: block;">
             <input type="hidden" value="${payInfo.orderId!''}" id="hideOrderId">
                <div class="downline-box">
                    <p class="coption1">企业银行账户/个人银行账户，到银行营业网点柜台转账，或通过使用企业网银/个人网银转账的方式完成付款</p>
                    <ul class="clearfix" id="xxzful">
                        <li>
                            <h3>工商银行账号：</h3>
                            <div class="banklogo">
                                <span class="icbc"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>中国工商银行应城支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>1812  0231  1920  0134  304</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="ICBC" class="sendMsg"  href="javascript:void(0);">选择工商银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" value="getCode3" />
                            </div>
                        </li>
                        <li>
                            <h3>招商银行账号：</h3>
                            <div class="banklogo">
                                <span class="merchants"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>招商银行武汉经济开发区支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>1279  0724  6510  602</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="CMB" class="sendMsg"  href="javascript:void(0);">选择招商银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" id="getCode2"/>
                            </div>
                        </li>
                        <li>
                            <h3>建设银行账号：</h3>
                            <div class="banklogo">
                                <span class="construction"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>中国建设银行郭茨口支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>4200  1258  1080  5300  9523</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="CCB" class="sendMsg"  href="javascript:void(0);">选择建设银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" id="getCode1"/>
                            </div>
                        </li>
                    </ul>
                    <p class="coption2">为确保您的药材能够及时处理，请您在转账操作时务必注明您的名称和订单号！</p>
                    <div>
	                	<form id="fileUploadForm" method="post" action="/payVoucherController/payVoucherUpload" enctype="multipart/form-data">
	                    	<input type="hidden" name="orderId" value="${payInfo.orderId!''}"/>
							<input type="hidden" name="amount" value="${payInfo.amount!''}"/>
							<input type="hidden" name="amtType" value="${payInfo.amtType!''}"/>
							<input type="hidden" name="userId" value="${payInfo.userId!''}"/>
							<input type="hidden" name="recieveId" value="${payInfo.recieveId!''}"/>
							<input type="hidden" name="sourceSys" value="${payInfo.sourceSys!''}"/>
							<input type="hidden" name="orderTitle" value="${payInfo.orderTitle!''}"/>
		                    <span class="btn-red relative">
		                    	<input type="file" id="file1" name="file" class="fileo" 
		                    	onchange="javascript:fileUpload();" 
		                    	value="我已经完成了线下付款，上传付单回单"/>我已经完成了线下付款，上传付单回单
		                    </span>
	                    </form>
                    </div>
                </div>
            </div>
          	</#if>
          <#else>
	        <!-- 珍药宝 -->
       		<div class="tabscont-1 pay-contbg clearfix"  style="display: block;">
       			 <ul class="cur mt20">
                    <iframe ID="Frame1" SRC="/ucs/pay?orderId=${payInfo.orderId!''}&amount=${payInfo.amount!''}&amtType=${payInfo.amtType!''}&userId=${payInfo.userId!''}&recieveId=${payInfo.recieveId!''}&sourceSys=${payInfo.sourceSys!''}&orderTitle=${encryptOrderTitle!''}&signData=${payInfo.signData!''}&data=${data!''}&signdata=${signdata!'' }&paymentNo=${paymentNo!''}" allowTransparency="true"  style="width:1170px; height:280px; border:0 none;" ></iframe>
                 </ul>
            </div>
            
            <!-- 银联B2B -->
            <div id="b2b-account" class="tabscont-1 pay-contbg clearfix">
            	<div class="downline-box">
               	 	<p class="coption1">支付企业银行账户通过中国银联完成支付，个人银行账户请使用 “ 招行珍药宝支付 ” 或  “ 线下转账支付 ” 方式进行</p>
                </div>
                <ul class="clearfix sign mt30">
                    <li><span id="1-CCB" class="img construction"></span><i>企业</i></li>
                    <li><span id="1-CMB" class="img merchants"></span><i>企业</i></li>
                    <li><span id="1-ICBC" class="img icbc"></span><i>企业</i></li>
                    <li><span id="1-ABC" class="img abc"></span><i>企业</i></li>
                    <li><span id="1-BOC" class="img china"></span><i>企业</i></li>
                    <li><span id="1-PAB" class="img pingan"></span><i>企业</i></li>
                    <li><span id="1-BOCOM" class="img jiaotong"></span><i>企业</i></li>
                    <li><span id="1-CMBC" class="img minsheng"></span><i>企业</i></li>
                    <li><span id="1-CNCB" class="img zhongxin"></span><i>企业</i></li>
                    <li><span id="1-HXB" class="img huaxia"></span><i>企业</i></li>
                    <li><span id="1-CEB" class="img guada"></span><i>企业</i></li>
                    <li><span id="1-SPDB" class="img pufa"></span><i>企业</i></li>
                    <li><span id="1-BEA" class="img dongya"></span><i>企业</i></li>
                    <li><span id="1-GDB" class="img guangfa"></span><i>企业</i></li>
                </ul>
                <input type="button" class="btn-red" id="b2b-pay" value="立即支付">
            </div>
            <form action="/pay" id="form" method="post" target="_blank">
                <input type="hidden" name="orderId" value="${payInfo.orderId }"/>
				<input type="hidden" name="amount" value="${payInfo.amount }"/>
				<input type="hidden" name="amtType" value="${payInfo.amtType }"/>
				<input type="hidden" name="userId" value="${payInfo.userId }"/>
				<input type="hidden" name="code" value=""/>
				<input type="hidden" name="recieveId" value="${payInfo.recieveId }"/>
				<input type="hidden" name="sourceSys" value="${payInfo.sourceSys }"/>
				<input type="hidden" name="orderTitle" value="${payInfo.orderTitle }"/>
				<input type="hidden" name="signData" value="${payInfo.signData }"/>
             </form>
             
             <!-- 线下支付 -->
             <div class="tabscont-1 pay-contbg clearfix">
             <input type="hidden" value="${payInfo.orderId!''}" id="hideOrderId">
                <div class="downline-box">
                    <p class="coption1">企业银行账户/个人银行账户，到银行营业网点柜台转账，或通过使用企业网银/个人网银转账的方式完成付款</p>
                    <ul class="clearfix" id="xxzful">
                        <li>
                            <h3>工商银行账号：</h3>
                            <div class="banklogo">
                                <span class="icbc"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>中国工商银行应城支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>1812  0231  1920  0134  304</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="ICBC" class="sendMsg"  href="javascript:void(0);">选择工商银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" value="getCode3" />
                            </div>
                        </li>
                        <li>
                            <h3>招商银行账号：</h3>
                            <div class="banklogo">
                                <span class="merchants"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>招商银行武汉经济开发区支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>1279  0724  6510  602</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="CMB" class="sendMsg"  href="javascript:void(0);">选择招商银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" id="getCode2"/>
                            </div>
                        </li>
                        <li>
                            <h3>建设银行账号：</h3>
                            <div class="banklogo">
                                <span class="construction"></span>
                            </div>
                            <p class="mes"><strong>开户行：</strong>中国建设银行郭茨口支行<br />
                                <strong>收款人名称：</strong>九州通中药材电子商务有限公司<br />
                                <strong>收款账号：</strong><b>4200  1258  1080  5300  9523</b><br />
                            </p>
                            <div class="clearfix"></div>
                            <div class="botm">
                                <a id="CCB" class="sendMsg"  href="javascript:void(0);">选择建设银行转账，点击这里，把收款账号发送到我的手机</a>
                                <input type="button" id="getCode1"/>
                            </div>
                        </li>
                    </ul>
                    <p class="coption2">为确保您的药材能够及时处理，请您在转账操作时务必注明您的名称和订单号！</p>
                    <div>
	                	<form id="fileUploadForm" method="post" action="/payVoucherController/payVoucherUpload" enctype="multipart/form-data">
	                    	<input type="hidden" name="orderId" value="${payInfo.orderId!''}"/>
							<input type="hidden" name="amount" value="${payInfo.amount!''}"/>
							<input type="hidden" name="amtType" value="${payInfo.amtType!''}"/>
							<input type="hidden" name="userId" value="${payInfo.userId!''}"/>
							<input type="hidden" name="recieveId" value="${payInfo.recieveId!''}"/>
							<input type="hidden" name="sourceSys" value="${payInfo.sourceSys!''}"/>
							<input type="hidden" name="orderTitle" value="${payInfo.orderTitle!''}"/>
		                    <span class="btn-red relative">
		                    	<input type="file" id="file1" name="file" class="fileo" 
		                    	onchange="javascript:fileUpload();" 
		                    	value="我已经完成了线下付款，上传付单回单"/>我已经完成了线下付款，上传付单回单
		                    </span>
	                    </form>
                    </div>
                </div>
            </div>
	     </#if>
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
<script type="text/javascript" charset="utf-8" src="${RESOURCE_PAY}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${RESOURCE_PAY}/js/pay.js"></script>
<script type="text/javascript" src="${RESOURCE_PAY}/js/Alert.js"></script>
<script>
var i = 60;
var bankCodeCCB = "";
//倒计时重新发送短信通知
function time(){
	i-=1 ;
	$("#"+ bankCodeCCB +"").next('input').val('(' + i + ')秒后重新发送');
	$("#"+ bankCodeCCB +"").next('input').attr("disabled","disabled");
	if(i==0){
		$("#"+ bankCodeCCB +"").next('input').hide();
        $("#"+ bankCodeCCB +"").show();
		i=60;
		return;
	}
	setTimeout("time()",1000);
}

var j = 60;
var bankCodeCMB="";
function time2(){
	j-=1 ;
	$("#"+ bankCodeCMB +"").next('input').val('(' + j + ')秒后重新发送');
	$("#"+ bankCodeCMB +"").next('input').attr("disabled","disabled");
	if(j==0){
		$("#"+ bankCodeCMB +"").next('input').hide();
        $("#"+ bankCodeCMB +"").show();
		j=60;
		return;
	}
	setTimeout("time2()",1000);
}

var m = 60;
var bankCodeICBC="";
function time3(){
	m-=1 ;
	$("#"+ bankCodeICBC +"").next('input').val('(' + m + ')秒后重新发送');
	$("#"+ bankCodeICBC +"").next('input').attr("disabled","disabled");
	if(m==0){
		$("#"+ bankCodeICBC +"").next('input').hide();
        $("#"+ bankCodeICBC +"").show();
		m=60;
		return;
	}
	setTimeout("time3()",1000);
}

function getIds(){
	var inputArray = $("#xxzful input");
	inputArray.each(
        function (){
            var input =$(this);//循环中的每一个input元素
            //alert(input.attr("id"))//查看循环中的每一个input的id
            //alert($(this).prev('a').attr('id'));
            alert(bankCode_);
        }
    )
}

$(function(){
    function Tabs(els,contsID){
        els.on('click',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            contsID.eq($(this).index()).show().siblings().hide();
            if($(this).hasClass('cur')){
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
    
    $("#b2b-account span").click(function(){
		$("input[name=code]").val($(this).attr("id"))
	})
	
	$("#b2c-pay").click(function(){
		$('.pop-up').show();
		$('.pop-up').children('div').eq(1).show().siblings('div').hide();
		bghui();
		$("input[name=code]").val("2-UNIONPAY");
		$("#form").submit();
		$('#problem').on('click',function(){
			$('.pop-up').children('div').eq(1).hide();
			$('.pop-up').children('div').eq(2).show().siblings('div').hide();
		})
	})
    
    $("#b2b-pay").click(function(){
    	if($('#b2b-account li').hasClass('cur')){
    		$('.pop-up').show();
    		$('.pop-up').children('div').eq(1).show().siblings('div').hide();
    		bghui();
    		$("#form").submit();
    		$('#problem').on('click',function(){
    			$('.pop-up').children('div').eq(1).hide();
    			$('.pop-up').children('div').eq(2).show().siblings('div').hide();
    		})
    	}
    	else{
    		$('.pop-up').show();
    		$('.pop-up').children('div').eq(0).show().siblings('div').hide();
            bghui();
            return;
    	}
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
    
    $("a[id='CCB']").click(function(){
    	var orderId_ = $("#hideOrderId").val();
    	bankCodeCCB = $(this).attr("id");//获取点击银行组织编码
    	var smsFlag_ = 1;
    	$.ajax({
			type : "post",
			url : '/payVoucherController/smsSend',
			data : {orderId:orderId_ , bankCode:bankCodeCCB, smsFlag:smsFlag_ },
			dataType : "json",
			success : function(data){
				if(data.state){
					var msg = data.retMsg;
					$("#" + bankCodeCCB +"").hide();//隐藏文字
			        $("#" + bankCodeCCB +"").next('input').show();//显示读秒
			        bghui();//遮罩层
					Alert({str:msg});//输出提示
			        time();//倒计时读秒function
				}else{
					Alert({str:'短信发送失败！'});
				}
			}
		});
    })
    
    $("a[id='CMB']").click(function(){
    	var orderId_ = $("#hideOrderId").val();
    	bankCodeCMB = $(this).attr("id");
    	var smsFlag_ = 1;
    	$.ajax({
			type : "post",
			url : '/payVoucherController/smsSend',
			data : {orderId:orderId_ , bankCode:bankCodeCMB, smsFlag:smsFlag_ },
			dataType : "json",
			success : function(data){
				if(data.state){
					var msg = data.retMsg;
					$("#" + bankCodeCMB +"").hide();
			        $("#" + bankCodeCMB +"").next('input').show();
			        bghui();
					Alert({str:msg});
			        time2();
				}else{
					Alert({str:'短信发送失败！'});
				}
			}
		});
    	
    })
    
    $("a[id='ICBC']").click(function(){
    	var orderId_ = $("#hideOrderId").val();
    	bankCodeICBC = $(this).attr("id");
    	var smsFlag_ = 1;
    	$.ajax({
			type : "post",
			url : '/payVoucherController/smsSend',
			data : {orderId:orderId_ , bankCode:bankCodeICBC, smsFlag:smsFlag_ },
			dataType : "json",
			success : function(data){
				if(data.state){
					var msg = data.retMsg;
					$("#" + bankCodeICBC +"").hide();
			        $("#" + bankCodeICBC +"").next('input').show();
			        bghui();
					Alert({str:msg});
			        time3();
				}else{
					Alert({str:'短信发送失败！'});
				}
			}
		});
    	
    })
});
//线下支付回单上传
function fileUpload(){
	 var pic = $("#file1").val();
	 //不区分后缀名大小写 /i.
	 if(!/\.(gif|jpeg|bmp|jpg|png)$/i.test(pic)){
	//if(!/.(gif|jpeg|bmp|jpg|png)$/.test(pic)){
			bghui();
			Alert({str:'请上传gif、jpeg、bmp、jpg、png类型图片！'});
			return;
	 }else{
		 bghui();
		 Alert({
	            str:'回单上传成功!珍药宝运营人员会在审核完成后给您答复，请耐心等待！如有问题请拨打客服电话400-10-54315',
	            buttons:[{
	                name:'确定',
	                id:'1',
	                classname:'btn-style',
	                ev:{click:function(data){
	                        disappear();
	                        $(".bghui").remove();
	                        $("span.btn-red").attr("class","btn-disabled relative");
		            		$("#fileUploadForm").submit();
		            		$("#file1").attr("disabled","disabled");
	                    }
	                }
	            }]
	        });
	 }
}

/**
function ajaxFileUpload(){
  	 var data = $("#fileUploadForm").serializeArray();
  	 var pic = $("#file1").val();
  	 if(!/.(gif|jpeg|bmp|jpg|png)$/.test(pic)){
  		 bghui();
		 Alert({str:'请选择图片!'});
		 }else{
			 $.ajaxFileUpload({
		   		url:'/payVoucherController/payVoucherUpload',
		   		type: 'POST',
		   		data : data,
		   		secureuir:false,
		   		fileElementId:'file1',
		   		dataType: 'json',
		   		success: function (data, status){
		   			if(data){
		   				$("#file1").val("");
		   				bghui();
						Alert({str:'上传回单成功！'});
		   			}else{
		   				bghui();
						Alert({str:'上传回单失败！'});
		   			}
		   		}
	   	    });  
		}
  }*/
 
</script>
</body>
</html>