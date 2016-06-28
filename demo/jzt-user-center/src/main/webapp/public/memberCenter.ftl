<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材-聚好药商，卖珍药材-会员中心首页</title>
     <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/reset.css" />
     <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/jzt-user-center/memberCenter.css" />
     <link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/css/common.css" />
	 <script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
</head>
<body>

<#include "common/header.ftl" /> 
<div class="area-1200 clearfix">
	<#include "common/left.ftl" /> 

    <div class="hy-center fl">
        <div class="border-1 clearfix p1525">
            <!--<p class="box-date fl">-->
            <p class="fl" style="line-height: 30px;padding-right: 10px;">
                <span class="f14"><span class="col-red1"><span id="greetings"></span>，${memberInfo.userName }</span> (<i class="legalize <#if (memberInfo.certifyStatus == 1 || memberInfo.certifyStatus == 2)>cur</#if>">实</i><#if (memberInfo.certifyStatus==1)> 个人实名认证 <#elseif (memberInfo.certifyStatus==2)> 企业认证  <#else> 未认证 </#if>)</span><br />
                当前登录IP：${memberInfo.accessIp!'127.0.0.1' }<br />
                上次登录时间：${memberInfo.lastAccessDate }<br />
            </p>
            <!--
            <div class="box-account fl">
                账户资金:
                <p>0.00  <a href="javascript:void(0);" class="tx">立即提现</a></p>
            </div>-->
        </div>
        <div class="box-tip border-1 mt10 relative">
        <#if (memberInfo.certifyStatus != 1 && memberInfo.certifyStatus != 2)>
	       
	            <p><i class="tip-icon"></i>只有实名认证才可进行交易 <a href="/getLegalize" class="lega">立即认证</a> </p>
	            <i class="sj"></i>
	       
        </#if>
        <#if (memberInfo.email == "")>
        		<p><i class="tip-iconb"></i>设置安全邮箱，保障账户安全 <a href="/authenticateMobile/authenticateIdentity?optType=1" target="_blank" class="lega">立即设置</a> </p>
        </#if>
        </div>
        
        	
        	
        	
        <div class="box-tabs relative">
            <div class="title2"><i></i>采<br/>购<br/>情<br/>况</div>
            <ul class="clearfix">
                <li>
                	<a href="/order/listinfo?orderstate=${PlACED_ORDER}">
	                    <i class="ico2"><span>${nums[4]}</span></i>
	                    <p>待付保证金&nbsp;&nbsp;</p>
                    </a>
                </li>
                <li>
                	<a href="/order/listinfo?orderstate=${READY_WARE}">
	                    <i class="ico3"><span>${nums[5]}</span></i>
	                    <p>待付货款 </p>
	                </a>    
                </li>
                <li>
            	<a href="/order/listinfo?orderstate=${COMPLETED_ORDER}">
                    <i class="ico1"><span>${nums[3]}</span></i>
                    <p>已成交&nbsp;&nbsp;&nbsp;</p>
                </a>    
            </li>
            </ul>
        </div>
        <div class="box-tabs relative">
        <div class="title1"><i></i>销<br/>售<br/>情<br/>况</div>
        <ul class="clearfix">
            <li>
            	<a href="/order/getSellOrderList?orderstate=${PlACED_ORDER}">
                    <i class="ico2"><span>${nums[1]}</span></i>
                    <p>待付保证金&nbsp;&nbsp;</p>
                </a>    
            </li>
            <li>
            	<a href="/order/getSellOrderList?orderstate=${READY_WARE}">
                    <i class="ico3"><span>${nums[2]}</span></i>
                    <p>待付货款 </p>
                </a>    
            </li>
            <li>
        	<a href="/order/getSellOrderList?orderstate=${COMPLETED_ORDER}">
                <i class="ico1"><span>${nums[0]}</span></i>
                <p>已成交&nbsp;&nbsp;&nbsp;</p>
            </a>
        </li>
        </ul>
    </div>
        <div class="box-tabs relative">
            <div class="title3"><i></i>我<br/>的<br/>仓<br/>单</div>
	            <ul class="clearfix">
	                <li>
	                	<a href="/whlist/manager">
		                    <i class="ico4"><span>${nums[6]}</span></i>
		                    <p>我的仓单&nbsp;&nbsp;&nbsp;&nbsp;</p>
	                    </a>  
	                </li>
	            </ul>
        </div>
    </div>


<!-- 右侧 -->
    <div class="hy-sidebar fr">
        <h2 class="title mt5"><i class="icon-recom"></i>药材推荐</h2>
        <div class="border-2">
            <ul class="list_img">
            	<#list hotlist as msg>
                <li>
                    <a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><span>
                    <#if msg.picurl??>
	                  	<#assign minImg = msg.picurl?substring((msg.picurl?last_index_of("/")+1),(msg.picurl?last_index_of(".")))+"_120"+msg.picurl?substring(msg.picurl?last_index_of("."),msg.picurl?length)>
	  				  	<#assign tempurl = msg.picurl?substring(0,msg.picurl?last_index_of("/")+1)>
	                  	<img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${minImg}" alt="${msg.name}"/>
	                <#else>
	                  	<img src="" alt="${msg.name}"/>
	                </#if>
                    </span>
                    <h3>${msg.name }</h3></a>
                    <p>规格：${msg.grade }<br/>
                        单价：<span class="col-red1"><@tools.money num=msg.price format="0.##"/></span>${msg.dictvalue }</p>
                </li>
                </#list>
            </ul>
        </div>
    </div>
 </div>
<#include "common/footer.ftl" /> 
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<script type="text/javascript">
	var gdate = new Date();
	var _hour = gdate.getHours();
	if(_hour >= 6 && _hour < 12){
		$("#greetings").html("上午好");
	}else if(_hour >=12 && _hour < 18){
		$("#greetings").html("下午好");
	}else if((_hour >=18 && _hour <= 23)||(_hour >= 0 && _hour < 6)){
		$("#greetings").html("晚上好");
	}
	
</script>

</body>
</html>