<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>采购信息列表</title>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/stock.css" type="text/css" rel="stylesheet" />
</head>
<body>
<#import 'page.ftl' as tools>
<#include "common/indexListHeader.ftl">

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
   <div class="area-948 fl mt10">
  	 <div class="box-1">
        <div class="s-search clearfix">
        	<span class="dis-in-bk search fl">
              <span>品种：</span>
              <input class="text1 mr10" type="text" value="<#if 'fuzzy' == query.breedNameMatchType>${query.breedName}</#if>" id="breedInput" /><input type="button" class="btn-search mr10" id="breedSearchBtn" value="搜索" />
            </span>
            <#if breedNames?? && (breedNames?size>0)>
                <span class="keywords fl">
                    <span class="col-red1"> 热门采购：</span>
        			<#list breedNames as breedName>
        				<#if breedName==query.breedName && 'accurate' == query.breedNameMatchType>
        					<a href="javascript:;" name="breedA" class="hover">${breedName}</a>
        				<#else>
        					<a href="javascript:;" name="breedA">${breedName}</a>
        				</#if>
                   </#list>
                </span>
            </#if>
            <div class="s-foot clearfix">
                <#if query.expireTimeSort =='asc'>
                	<span class="account dis-in-bk fl">剩余天数<i class="dis-in-bk up"></i></span>
                <#else>
                	<span class="account dis-in-bk fl">剩余天数<i class="dis-in-bk"></i></span>
                </#if>
                <span class="fr">共<span class="col_red2">${page.totalRecord}</span>条信息，共<span class="col_red2">${page.totalPage}</span>页</span>
            </div>
        </div>    
         
         <!-- 隐藏的提交表单 -->
         <form action="/purchaseSearch/list" method="post" id="queryForm" style="display:none;">
         	<input type="hidden" name="breedName" value="${query.breedName!''}"/>
         	<input type="hidden" name="breedNameMatchType" value="${query.breedNameMatchType!''}"/>
         	<input type="hidden" name="expireTimeSort" value="${query.expireTimeSort!''}"/>
         </form>
         
     </div>
     <!-- 采购列表信息 -->
       <div class="stock-list">
            <ul>
            	<#if page.results?? && (page.results?size>0)>
                	<#list page.results as purchase>
		                <li>
		                    <span class="fr">
		                        <span class="wait dis-in-bk">${stautsMap[purchase.status?string]!''}</span><br/>
		                        <a href="/busiPur/PurchaseDetail?purchaseId=${purchase.purchaseId}" class="price dis-in-bk">我 要 报 价</a>
		                    </span>
		                    <h2>${purchase.purchaseTitle!''}<span>报价剩余时间：<#if purchase.remainingDays < 1>不足1天<#else>${purchase.remainingDays}天</#if></span></h2>
		                    <p>品种名称：<span class="pr20">${purchase.breedName!''}</span>规格等级：<span class="pr20">${purchase.standardLevel!''}</span>产地：<span class="pr20">${purchase.origin!''}</span>数量：<span class="pr20">${purchase.quantity!''}${purchase.wunitName!''}</span><br/>
		                        质量要求：<span class="pr20">${purchase.qualityDescription!''}</span><br/>
		                        跟进交易员：<span class="pr20"><#if purchase.salesman??>${purchase.salesman}<#else>${defaultTradersName!''}</#if> <#if purchase.salesmanMobile??>${purchase.salesmanMobile}<#else>${defaultTradersPhone!''}</#if></span><br/>
		                    </p>
		                </li>
            		</#list>
                <#else>
                	<li>
						<h1>很抱歉，没有找到相关的采购信息！</h1>
					</li>
                </#if>
            </ul>
           <@tools.pages page=page form="queryForm"/>
       </div>
     <!-- 采购列表信息 end -->
  </div>
         
  <div class="area-230 fr mt20 relative">
      <div id="stepper" align="center"><img src="${RESOURCE_IMG}/images/stock1013.png" /> </div>
      <div class="sell-stock mt20"><a href="${brokerServer.getUserCenter()}/purchase/pub" class="dis-in-bk">免费发布采购信息</a></div>
      <div class="tradlist">
          <h3>最近成交</h3>
          <div id="Sider">
	          <ul id="recentlyPurchase">
	          </ul>
          </div>
      </div>
  </div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
<#include "common/listFooter.ftl">
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>

<script>
$(function(){
    $('#breedInput').focus(function(){
    	$(document).keydown(function(e) {
            // 回车键事件
            if(e.which == 13) {
                $('#breedSearchBtn').click();
            }
        });               	                    
    });  
	
	//搜索按钮
	$('#breedSearchBtn').on('click',function(){
		$('#queryForm input[name=expireTimeSort]').val('');
		$('#queryForm input[name=breedName]').val(strTrim($('#breedInput').val()));
		$('#queryForm input[name=breedNameMatchType]').val('fuzzy');//模糊
		$('#queryForm').submit();
	});
	
	$('a[name=breedA]').on('click',function(){
		$('#queryForm input[name=expireTimeSort]').val('');
		$('#queryForm input[name=breedName]').val($(this).text());
		$('#queryForm input[name=breedNameMatchType]').val('accurate');//精确
		$('#queryForm').submit();
	})

    //剩余天数排序
    $('.account').on('click',function(){
        $(this).children('i').toggleClass('up');
        if($(this).children('i').hasClass('up')){
        	$('#queryForm input[name=expireTimeSort]').val('asc');
        } else {
        	$('#queryForm input[name=expireTimeSort]').val('desc');
        }
        $('#queryForm').submit();
    });
    
    getRecentlyPurchase();
    
    // 右侧成交信息滚动与固定效果
	function fixed(){
	    var t=$(".tradlist").offset().top;
	    var logo_t=$(".topper").height();
	    var nav_t=$(".header ").height();
	    var reg_t=$("#stepper").height();
	    var reg_sub=$(".sell-stock").height();
	
	    var t3=logo_t+nav_t+reg_t+reg_sub;
	    var l_h=$(".area-948").height();
	    var r_h=$(".area-230").height();
	    var deal_h=$(".tradlist").height();
	
	    if($(".area-948").height()<$(".area-230").height()){
	        $(".tradlist").css('height',l_h-reg_sub-reg_t-92);
	    }
	    if($(".area-948").height()<500){
	        $(".tradlist").css('height',500-reg_sub-reg_t-15);
	    }
	
	    $(window).scroll(function(){
	        if($(window).scrollTop()>t){
	            $(".tradlist").css({'position':'fixed','left':'50%','marginLeft':'370px','top':'0','z-index':'999'});
	            if($(window).scrollTop()>l_h-r_h+t3+20){
	                $(".tradlist").css('height',deal_h+l_h-r_h+t3-58-$(window).scrollTop());
	            }
	        }else{
	            $(".tradlist").css({'position':'static','marginLeft':'0'});
	
	        }
	    });
	}
    fixed();
    
    //跑马灯
    $.fn.scrollcontent=function(interval)
    {
        var $this=$(this);
        var box=$this.closest("div")
        var m=box.height();
        var n=$this.height();
        if(n>=m)
        {
            $this.append($this.html());
            var i=0;
            var timer;
            var start=function()
            {
               
                timer=setInterval(function()
                {
                    box.scrollTop(i);
                    i===n ? i=0 : i++;
                },interval);
            }
            var stop=function()
            {
                clearInterval(timer);
            }
            start();
            box.on("mouseover",stop).on("mouseout",start);
        }
    };
    
});

//获取最近交易
function getRecentlyPurchase(){
	$.ajax({
		type:"GET",
		url:"/purchaseSearch/recentlyPurchases",
		dataType:"json",
		success:function(data){
			if(data.state=="success"){
				var html = '';
				var purchaseAry = parseJson(data.info);
				for(var i=0; i<purchaseAry.length; i++){
					var purchase = purchaseAry[i];
					html += '<li>';
					var day = '';
					if(purchase.dealSuccessPassedDay == 0){
						day = '今天';
					} else if (purchase.dealSuccessPassedDay == 1){
						day = '昨天';
					} else {
						day = purchase.dealSuccessPassedDay + '天前'
					}
					html += '<span class="col_888">' + day + '</span>&nbsp;';
					html += strStarReplace(purchase.purchaserOrg);
					html += '成功采购了&nbsp;';
					html += '<span class="col_yellow2">';
					html += strNvl(purchase.breedName,'');
					html += '&nbsp;';
					html += strNvl(purchase.standardLevel,'');
					html += '&nbsp;';
					html += strNvl(purchase.quantity,'');
					html += strNvl(purchase.wunitName,'');
					html += '</span>';
					html += '</li>';
				}
				$('#recentlyPurchase').html(html);
				$("#Sider").find("ul").scrollcontent(50);
			}
		},
		error:function(textStatus){
		}
	});
}

function parseJson(text){
	try{
	    return JSON.parse(text);//ie 89 ff ch
	}catch(e){
	    return eval('('+text+')'); //ie7
	}
}

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
	 }

function strNvl(arg1, repStr){
	if(arg1 && arg1 != 'null'){
		return arg1;
	} else {
		return repStr;
	}
}

function strStarReplace(str){
	var str2 = strTrim(str);
	if(str2 != '' && str2.length > 1){
		return str2.substring(0,1) + '**';
	} else {
		return str2;
	}
}

function strTrim(str){
	if(str && str != 'null'){
		return str.replace(/(^\s*)|(\s*$)/g, "")
	} else {
		return '';
	}
}
</script>

</body>
</html>