<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>${(goodsInfo.grade)!''}_${(goodsInfo.title)!''}-摘牌</title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/detail.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/form.css" type="text/css" rel="stylesheet" />
    <#import 'macro.ftl' as tools>
</head>
<body>
<!--topper strat-->
<#include "common/indexListHeader.ftl">
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="detail-submit mt20">
	    <div class="cap">
		    <i class="dis-in-bk"></i> 订单提交成功，等待付款！
		    <span class="dis-in-bk">请您在3天内付款，否则系统将自动关闭订单</span>
		</div>
		 <div class="account">
            <dl>
                <dt>选择付款方式：</dt>
                <dd><input type="radio" name="radiopay" id="radiopay1" value="1" checked="checked"/> 支付保证金 &nbsp;&nbsp;&nbsp;&nbsp;<span class="col_888 f12">选择保证金方式，您可在10天内看完货后再付尾款</span></dd>
                <dd><input type="radio" name="radiopay" id="radiopay2" value="2" /> 支付全款 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="col_888 f12">付完全款，您马上买到货</span> </dd>
                <dd class="paysty" id="txtpay" style="display:none;">保证金金额：&nbsp;<strong class="col_red2"><@tools.money num=orderInfo.deposit format="0.##"/></strong> 元<input id="pay" type="button" class="btn-red fn ml20" value="去付款" /></dd>
                <dd class="paysty" id="txtpayAll" style="display:none;">全款金额：&nbsp;&nbsp;<strong class="col_red2"><@tools.money num=orderInfo.discountprice format="0.##"/></strong> 元<input id="payall" type="button" class="btn-red fn ml20" value="去付款" /></dd>
            </dl>
            
        </div>
		<!--  <div class="account ml20">您需要支付保证金&nbsp;&nbsp;&nbsp;&nbsp;<strong class="f16 col_red"><@tools.money num=orderInfo.deposit format="0.##"/></strong> 元 <input id="pay" type="button" class="btn-red fn ml20" value="立即支付保证金" /></div> -->
        <table cellpadding="0" cellspacing="0" class="table" width="100%">
            <tr align="center">
                <th width="50%"></th>
                <th width="15%">订单数量</th>
                <th width="15%">单价</th>
                <th width="20%">总价</th>
            </tr>
            <tr>
                <td class="intro">
                    <span><img src="${RESOURCE_IMG_UPLOAD}/${path!""}"></span>
              	<#if goodsInfo??>
                    <h3 class="od">${goodsInfo.title!""}</h3>
                    <ul>
                        <li>挂牌日期：${goodsInfo.examintime?string('yyyy-MM-dd')}</li>
                        <li>最低起订：<#if (goodsInfo.minsalesAmount)??><@tools.money num=goodsInfo.minsalesAmount format="0.##"/><#else>0</#if>${(goodsInfo.unitname)!''}</li>
                        <li>等级/规格：${(goodsInfo.grade)!''}</li>
                        <li>能否提供发票：<#if orderInfo.hasbill==1>提供<#else>不提供</#if></li>
                        <li>产地：${(goodsInfo.origin)!''}</li>
                    </ul>
                </td>
                <td align="center"><#if (orderInfo.amount)??><@tools.money num=orderInfo.amount format="0.##"/></#if>${(goodsInfo.unitname)!''}</td>
                <td align="center"><strong class="f16 col_red"><@tools.money num=orderInfo.unitprice format="0.##"/></strong> 元/ ${(goodsInfo.unitname)!''}</td>
                <td align="center"><strong class="f16 col_red"><@tools.money num=orderInfo.totalprice format="0.##"/></strong> 元</td>
                </#if>
            </tr>
        </table>
         <dl class="message temp clearfix">
            <dt>须知：</dt>
            <dd><span class="col_333"> 支付全款方式：</span>一次性付完订单金额，将对您的药材进行锁货，无需再付尾款！</dd>
            <dd><span class="col_333">支付保证金方式：</span>支付保证金方式，是默认支付保证金20%，待平台备完货后，您再支付剩余80%的货款！<br/>
                <span style="padding-left: 95px;">若支付保证金成功，您预订的药材即被锁定，在10天内（在这10天内，您可以选择来仓库验货，若有质量异议，经平台验证属实，将退还保证金，并且您将得到违约赔偿），您必须要支付剩</span><br/>
                <span style="padding-left: 95px;">余货款，否则即平台将默认您放弃该订单，须对卖家做出违约赔偿，违约金金额为保证金的100%，违约金在您已交保证金中扣除。</span><br/>
                <span style="padding-left: 95px;">成功支付剩余货款后，您将获得该药材，并将立即生成仓单，平台将免费为您做仓储服务15天，这期间您可以选择将药材在平台上挂牌，或者继续保存在仓库（需办理相应入库手续），或者办理</span><br/>
                <span style="padding-left: 95px;">出库手续。若超过免费仓储时间还未对药材进行处置，平台将默认您继续办理仓储服务，仓储费用将在您药材出库时结清，直至货物价值抵扣完仓储费用。</span></dd>
        </dl>
    </div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript">
	$(function(){
		//调用搜索插件
		$('#searchEngineListingButton').searcher({
			onSearch:function() {
				var keyword=$('input[type="text"].search-text').val();
				if(keyword == "输入名称找药材"){
					keyword='';
				}
				window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
		 	}
		});
		
		//保证金支付按钮
		$("#pay").click(function(){
			window.location.href='http://uc.54315.com/order/payOrder?orderId=${orderInfo.orderid!""}&amtType=0&userId=${userId}';
			
		});
		//全款支付按钮
		$("#payall").click(function(){
			window.location.href='http://uc.54315.com/order/payOrder?orderId=${orderInfo.orderid!""}&amtType=2&userId=${userId}';
			
		});
		
		
		
	});
	//保留两位小数  不四舍五入	   
	   function formatNum(num){
		  	var bb = num+"";  
		    var dian = bb.indexOf('.');  
		    var result = "";  
		    if(dian == -1){  
		        result =  num;  
		    }else{  
		        var cc = bb.substring(dian+1,bb.length);  
		        if(cc.length >=3){  
		            //result =  (Number(num.toFixed(2)))*100000000000/100000000000; 
					result = bb.substring(0,dian)+"."+bb.substring(dian+1,dian+3);
		        }else{  
		            result =  num;  
		        }
		        if(bb.substring(0,dian)=="")
		        	result =0+result;
		    }  
			return result;
		 };
</script>

<script type="text/javascript">
//设置支付方式
$(function(){
 showCont();
 $("input[name=radiopay]").click(function(){
  showCont();
 });
});
function showCont(){
 switch($("input[name=radiopay]:checked").attr("id")){
  case "radiopay1":
   //alert("one");
 	 $("#txtpayAll").hide();
  	 $("#txtpay").show();
		
   break;
  case "radiopay2":
	  $("#txtpay").hide();
	  $("#txtpayAll").show();
	 	
   break;
  default:
   break;
 }
};
</script>
</body>
</html>