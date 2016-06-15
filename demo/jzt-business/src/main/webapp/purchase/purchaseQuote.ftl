<!DOCTYPE html>
<html>
<head lang="en">
 <meta charset="UTF-8">
    <title>采购报价</title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/stock.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/detail.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/form.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/popup.css" type="text/css" rel="stylesheet" />    
    <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
</head>
<body>
<!--header-->
<#include "common/indexListHeader.ftl">

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
   <div class="area-948 fl">
     <!-- 采购报价 -->
       <div class="stock-list charge">
            <ul>
                <li>
                    <h2>${purInfo.breedName} ${purInfo.standardLevel} ${purInfo.origin} ${purInfo.quantity}${purInfo.wunitName}<span>报价剩余时间：<#if purInfo.remainDays<1>不足1天<#else>${purInfo.remainDays}天</#if></span></h2>
                    <p>品种名称：<span class="pr20">${purInfo.breedName}</span>规格等级：<span class="pr20">${purInfo.standardLevel}</span>产地：<span class="pr20">${purInfo.origin}</span>数量：<span class="pr20">${purInfo.quantity}${purInfo.wunitName}</span><br/>
                        质量要求：<span class="pr20">${purInfo.qualityDescription}</span><br/>
                    </p>
                    <p class="mt10">交货地点：<span class="pr20">${purInfo.deliveryAddress}</span>交货时间：<span class="pr20"><#if purInfo.expectDeliveryTime??>${purInfo.expectDeliveryTime?string('yyyy-MM-dd')}</#if></span>发票要求：<span class="pr20">${purInfo.receipt}</span><br/>
                        其他：<span class="pr20">${purInfo.note}</span><br/>
                    </p>
                </li>
            </ul>
         <form action="" name="QuoteForm" id="QuoteForm" method="post" target="_top">
            <div class="confirm">
                <h2>请填写您的报价信息与内容</h2>
                <ul>
                    <li>
                    	<label><span class="col_red">*</span> 报价：</label> 
                    	<input id="qtprice" name="quotePrice" datatype="quotenum" type="text" nullmsg="请填写报价。"  onkeyup="clearNoNum(this)" class="text-sty2 text-2"/>元<#if purInfo.wunitName??>/${purInfo.wunitName}</#if>
                    	</li>
                    <li><label>备注：</label><textarea class="text-sty2 textarea" id="quoteDes" name="quoteDescription" maxlength="200"></textarea></li>
                    <li><div align="right"><input type="button" value="提 交" id="btnSubmit" class="btn btn-red-grad"></div></li>
                </ul>
                <input type="hidden" id="purchaseId" name="purchaseId" value="${(purInfo.purchaseId)!''}"/>
                <input type="hidden" id="purchaseCode" name="purchaseCode" value="${(purInfo.purchaseCode)!'' }">
                <input type="hidden" id="purchaseStatus" name="purchaseStatus" value="${purInfo.status}">
            </div>
     	 </form>
       </div>
     <!-- 采购报价 end -->

  </div>
         
  <div class="area-240 fr">
      <div class="border-1 mt10">
          <h2 class="tit2">交易员</h2>
          <p class="businesser">交易员：${salename}<br/>
		              电话：${salephone}<br/>
		              促成交易：${Busicou} 笔</p>
          <p class="cap">您不知道如何报价，或需要其他帮助，可以电话咨询交易员。</p>
      </div>
      <div class="border-1 mt10">
          <h2 class="tit2">同类采购</h2>
          <ul class="same-stock">
          <#if SameBreedList?size gt 0>
          <#list SameBreedList as sameBreeds>
              <li><a href="PurchaseDetail?purchaseId=${sameBreeds.purchaseId}" title="${sameBreeds.breedName}" target="_blank">采购 ${sameBreeds.breedName} ${sameBreeds.quantity}${sameBreeds.wunitName} ${sameBreeds.origin} ${sameBreeds.standardLevel}</a><br/>
                  <span>报价剩余时间：<#if sameBreeds.remainDays<1>不足1天<#else>${sameBreeds.remainDays}天</#if></span> </li>
          </#list>
          </#if>
          </ul>
      </div>
  </div>
</div>
<!-- 祥情页主体over -->

<!--footer-->
<#include "common/listFooter.ftl">
<!-- 登录窗口弹层  -->
<div class="logon-box">
    <iframe width="100%" height="390" src="" id="popLoginIframe" name="popLoginIframe" scrolling="0" frameborder="0"></iframe>
    <div class="close"></div>
</div>
<!-- 登录窗口弹层  over -->

<!-- 提交成功弹层 -->
<div class="popup-box">
    <div class="close"></div>
    <div class="box4">
        <div align="center">
            <p class="sty1">提交成功！</p>
        </div>

        <p class="sty2" align="center">您的报价已提交，请等待交易员与您联系。</p>
    </div>
</div>
<!-- 提交成功弹层 over -->

<input type="hidden" value="" id="userOperate"/>
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/elevatezoom.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
<script type="text/javascript">
$(function(){
	//头部搜索
	$('#searchEngineListingButton').searcher();
});
</script>
<script>
    //背景变灰
    function bgHiu(){
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
    }

    //关闭层
    function close(els,cont){
        els.on('click',function(){
            cont.hide();
            $('.bghui').remove();
        });
    }
    close($('.close'),$('.popup-box'));

    //显示层
    function show(els,cont){
        els.on('click',function(){
            cont.show();
            bgHiu();
        });
    }

    //弹层显示垂直居中位置
    function popUp(par,elm){
        var wid = elm.width();
        var Hei = elm.height();
        par.css({
            'position':'fixed',
            'top':'50%',
            'left':'50%',
            'zIndex':10001,
            'marginLeft':-wid/2,
            'marginTop':-Hei/2,
            'border':'3px solid #cf5445',
            'background':'#ffffff'
        });
    }
    popUp($('.popup-box'),$('.popup-box .box4'));

	//登录框
	function popLogin(type){
		$('#userOperate').val(type);
	    $('#popLoginIframe').attr('src','https://passport.54315.com/login?service=http://www.54315.com/casuc&isajax=true&isPopLogin=true&type='+type);
	    $('.logon-box').show();
	    bghui();
	}
	$('.logon-box .close').on('click',function(){
		  $('.logon-box').hide();
		  $('.bghui').remove();
	    
	});
	
	function popCallBack(){
		buyValidForm.submitForm();
		$('#popLoginIframe').remove(); 
	 	//$("#QuoteForm").submit();
	}
	  //输入改变默认色
    $('input[type=text]').keydown(function(){
        $(this).css('color','#333');
    });
	
	 $('textarea').keydown(function(){
	        $(this).css('color','#333');
	    });

	 var buyValidForm=$("#QuoteForm").Validform({
		 tiptype:4,
         showAllError:true,
         ajaxPost:true,
	     datatype:{
  			"quotenum":function(){
	    	 var num = $("#qtprice").val();
				if(num==null || num==''){
					return "请输入报价！";
				}
				if(isNaN(num)){					
					return false;
				}}
						
		},
		callback:function(data){
			if(data.loginStatus=='0'){
				popLogin("QuoteForm");
				return;
			}else if(data.loginStatus=='2'){
				$('.popup-box').show();
	            bgHiu();
	            
	        	//关闭层，刷新页面
			    $('.close').on('click',function(){
		            $('.popup-box').hide();
		            $('.bghui').remove();
		            window.location.replace("/busiPur/PurchaseDetail?purchaseId="+$("#purchaseId").val());
	       		 });
	        	
				return;
			}else if(data.loginStatus=='3'){
				Alert({
					str:data.extra,
					buttons:[{
						name:'确定',
						id:'1',
						classname:'btn-style',
						ev:{click:function(data){
								disappear();
							}
							}
						}]
					});
				
		    
			}
			
		}
	});

    //文本框只能输入数字，且小数位数最多为2位
    function clearNoNum(obj){
	  obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
	  obj.value = obj.value.replace(/^\./g,"");  //验证第一个字符是数字而不是.  
	  obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
	  obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
	  obj.value=obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    };
  //报价
    $("#btnSubmit").click(function(){
    	$("#QuoteForm").attr("action","/busiPur/addQuote?go=busiPur/PurchaseDetail?purchaseId="+$("#purchaseId").val());
       	$("#QuoteForm").submit();
    });

	</script>
</body>
</html>