<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <!--setting freemarker get chinese-->
    <#setting url_escaping_charset='utf8'> 
    <title>珍药材_中国领先的中药材现货交易平台</title>
    <#import 'macro.ftl' as tools>
    <meta name="description" content="珍药材54315.com是九州通打造的有质量保障的中药材现货交易平台,为中药材上下游企业提供专业的中药材信息资讯，中药材交易服务，中药材供应链金融服务，中药材质押服务，中药材物流仓储以及中药材质检服务。"/>
    <meta name="keywords" content="珍药材网，中药材市场，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/index.css" type="text/css" rel="stylesheet" />
    <!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  start -->
    <script>
     <!--声明_czc对象:-->
	var _czc = _czc || [];
	<!--绑定siteid，请用您的siteid替换下方"XXXXXXXX"部分-->
	_czc.push(["_setAccount", "1254793674"]);
	<!--头部免费注册pv事件-->
	_czc.push(["_trackPageview","${URL_SERVER_PREFIX_UC}${URL_REGISTER_UC}"]);
	<!--导航栏右下角免费注册pv事件-->
	_czc.push(["_trackPageview","http://uc.54315.com/getUcUserRegister"]);
	<!--首页免费申请入仓事件-->
	_czc.push(["_trackPageview","/index/getWarehousing"]);
	<!--登录-->
	_czc.push(["_trackPageview","/login"]);
	<!--搜索框-->
	_czc.push(["_trackEvent", "搜 索", "搜索", "关键字搜索", "", "searchBtn"]);
	<!--我要采购事件统计-->
	_czc.push(["_trackPageview", "${JOINTOWNURL}/index/getProcurement"]);
	<!--我要入仓事件统计-->
	_czc.push(["_trackPageview", "${JOINTOWNURL}/index/getWarehousing"]);
	<!--广告位-->
	_czc.push(["_trackPageview", "${URL_TOPIC}/150602/shfb.html"]);
    <!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  end -->	
   </script> 
   
</head>
<body>
<!--topper strat-->
<div class="zhaopin"><div><a href="${URL_TOPIC}/150813/zhaopin.html" target="_blank"></a><i id="zaop"></i></div></div>
<div class="topper clearfix">
    <p class="topleft fl">
        <!-- ucUser session里保存的用户信息  -->
       <#if ucUser??>
        	<span id="u"><span class="pr20">您好，${(ucUser.userName)!'' }</span> 欢迎来到珍药材！ <a href="${brokerServer.passport}/logout?service=${JOINTOWNURL}/logout">[退出]</a></span>
       <#else>
       		<span id="u"><span class="pr20">您好，游客</span> 欢迎来到珍药材！ <a href="/login">请登录</a>|<a href="${URL_SERVER_PREFIX_UC}${URL_REGISTER_UC}">免费注册</a></span>
       </#if>
    </p>
    <ul class="topright fr">
        <li  id="myZYC"  class="relative">
        	<span><a href="http://uc.54315.com/">我的珍药材<i class="arrow"></i></a></span>
            <div class="sub first">
            	<!--update by fanyuna 2015.08.17  我的珍药材下拉菜单内容的排序调整：按照"买方订单"、"卖方订单"、"我的挂牌"、"收藏夹"从上到下排序-->
                <!--<a href="#">卖出订单</a>-->
                <a href="${URL_SERVER_PREFIX_UC}/order/listinfo">买方订单</a>
                <a href="${URL_SERVER_PREFIX_UC}/order/getSellOrderList">卖方订单</a>
                <a href="${URL_SERVER_PREFIX_UC}/listing/manager">我的挂牌</a>
                <a href="${URL_SERVER_PREFIX_UC}/collect/queryMyCollect">收藏夹</a>
            </div>
        </li>
        <!--<li><span class="bl"><i class="stock"></i>进货单 <i class="col-red1">3</i></span></li>
        <li><span class="bl"><i class="collect"></i>消息中心 <i class="col-red1">0</i></span></li>-->
        <li class="relative" id="Service"><span class="bl"><i class="service"></i>平台服务<i class="arrow"></i></span>
            <div class="sub">
                <a href="http://help.54315.com/view-1970233306-7837069404.html" target="_blank">交易服务</a>
                <a href="${JOINTOWNURL}/index/getStockService" target="_blank">仓储服务</a>
                <a href="${JOINTOWNURL}/index/getBankingService" target="_blank">融资服务</a>
                <a href="http://help.54315.com/view-1463152138-1723459540.html" target="_blank">委托服务</a>
                <a href="http://www.zyczyc.com" target="_blank">价格行情</a>
            </div>
        </li>
        <li><a href="${JOINTOWNURL}/index/getQualitySystem" class="bl">质量保障体系</a></li>
        <li class="relative" id="QA"><span class="bl"><i class="qa"></i>帮助中心<i class="arrow"></i></span>
            <div class="sub">
                <a href="http://help.54315.com/list-4772646578.html" target="_blank">新手入门</a>
                <a href="http://help.54315.com/view-1222808050-4277461942.html" target="_blank">采购商帮助</a>
                <a href="http://help.54315.com/view-2011458294-7859114524.html" target="_blank">供应商帮助</a>
                <a href="http://wpa.qq.com/msgrd?v=3&uin=4001054315&site=qq&menu=yes" target="_blank">在线咨询</a>
                <a href="http://help.54315.com/list-7362477534.html" target="_blank">联系客服</a>
            </div>
        </li>
        <li class="relative" id="webNav"><span  class="bl cur2"><i class="menu"></i>网站导航<i class="arrow"></i></span>
            <div class="sub webnav">
                <dl>
                    <dt>入门指导</dt>
                    <dd><a href="http://help.54315.com/list-4772646578.html" target="_blank">新手入门
                        </a><a href="http://help.54315.com/view-1222808050-4277461942.html" target="_blank">采购商帮助
                        </a><a href="http://help.54315.com/view-2011458294-7859114524.html" target="_blank">供应商帮助</a>
                    </dd>
                    <dt>目录分类</dt>
                    <dd><a href="${JOINTOWNURL}/search/category/1020?value=${'根茎类'?url}" target="_blank">根茎类
                        </a><a href="${JOINTOWNURL}/search/category/1021?value=${'果实籽仁类'?url}" target="_blank">果实籽仁类
                        </a><a href="${JOINTOWNURL}/search/category/1022?value=${'全草类'?url}" target="_blank">全草类
                        </a><a href="${JOINTOWNURL}/search/category/1024?value=${'花类'?url}" target="_blank">花类
                        </a><a href="${JOINTOWNURL}/search/category/1023?value=${'叶类'?url}" target="_blank">叶类
                        </a><a href="${JOINTOWNURL}/search/category/1025?value=${'树皮/藤木类'?url}" target="_blank">树皮藤木类
                        </a><a href="${JOINTOWNURL}/search/category/1026?value=${'树脂/菌藻类'?url}" target="_blank">树脂菌藻类
                        </a><a href="${JOINTOWNURL}/search/category/1027?value=${'动物类'?url}" target="_blank">动物类
                    	</a><a href="${JOINTOWNURL}/search/category/1028?value=${'矿物类'?url}" target="_blank">矿物类
       					</a><a href="${JOINTOWNURL}/search/category/1029?value=${'香料'?url}" target="_blank">香料
                    	</a><a href="${JOINTOWNURL}/search/category/1060?value=${'其它类'?url}" target="_blank">其它类
                    </a>
                    </dd>
                    <dt>特色服务</dt>
                    <dd><a href="${JOINTOWNURL}/index/getProcurement">我要采购
                        </a><a href="${JOINTOWNURL}/index/getWarehousing">我要入仓
                        </a><a href="${JOINTOWNURL}/index/getStockService">仓储服务
                        </a><a href="${JOINTOWNURL}/index/getBankingService">融资服务
                        </a><a href="http://www.zyczyc.com">价格行情</a>
                    </dd>
                </dl>
            </div>
        </li>
    </ul>
</div>
<div class="tophr"></div>
<div class="area-1200 header clearfix">
<div class="logo fl">
    <a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
</div>
<div class="search fl">
    <div class="text-bg clearfix relative mt20">
        <input type="text" class="search-text" id="Search" value="输入名称找药材"/><input  id="searchBtn" type="button" class="search-btn" value="搜 索" />
        <div class="match-bg">
        </div>
    </div>
    <p>
    	<#list categorylist as l >
    	<!--add by fanyuna 热门搜索关键词点击统计 start-->
	        <script>_czc.push(["_trackPageview","${JOINTOWNURL}/search?keyWords=${l.categorys_name?url}"]);</script>
	       <!--add by fanyuna 热门搜索关键词点击统计 end-->
    		<a href="${JOINTOWNURL}/search?keyWords=${l.categorys_name?url}" target="_blank">${l.categorys_name }</a>
    	</#list>
    </p>
</div>
<div class="fr mt20">
    <img src="${RESOURCE_IMG}/images/top-saoma.png" usemap="#top-saoma" border="0" />
    <map name="Map" id="top-saoma">
        <area shape="rect" coords="108,-4,129,18" href="#" />
    </map>
</div>

<!--修改360,ie7下 空留白问题 by Mr.song 2015.6.11 18:07-->
<!--div class="clearfix"></div-->
<div class="clearfix" style="*height: 0; *overflow: hidden;"></div>

<div class="nav mt10">
<div class="list fl relative"><a href="${JOINTOWNURL}/search?" class="co_fff">全部<span class="col_yellow">在仓</span>药材 ${tunnage } 吨</a>
<div class="sub-nav" style="display: block;">
	<#if sortList??>
		<#list sortList as sortMedicine >
		<div class="sub-nav-box">
				<div class="tit"><i class="icon-${sortMedicine_index+1}"></i><a href="${JOINTOWNURL}/search/category/${(sortMedicine.categorys_id)!''}?value=${sortMedicine.categorys_name?url}">${(sortMedicine.categorys_name)!''}</a></div>
				    <div class="sub-navs">
				    <#if sortMedicine.listBreed?size == 0 && sortMedicine.firstListBreed?size == 0 && sortMedicine.secondListBreed?size == 0 && sortMedicine.thirdListBreed?size == 0 && sortMedicine.fourthListBreed?size == 0>
				    	<div align="center">
							<img src="${RESOURCE_IMG}/images/guapaiing.png">
						</div>
				    <#else>
				       <dl>
					       <#if sortMedicine.listBreed?? && sortMedicine.listBreed?size gt 0 >
					       	   <dt>热门</dt>
					       	   <dd>
							       <#list sortMedicine.listBreed as breed >
								     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${sortMedicine.categorys_id}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a><#if breed_index+1!=sortMedicine.listBreed?size> |<#else></#if>
								   </#list>
							   </dd>
					       </#if>
					       <#if sortMedicine.firstListBreed?? && sortMedicine.firstListBreed?size gt 0>
					       	   <dt><span class="f16">A B C D E F</span></dt>
					       	   <dd>
							       <#list sortMedicine.firstListBreed as breed >
								     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${sortMedicine.categorys_id}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a><#if breed_index+1!=sortMedicine.firstListBreed?size> |<#else></#if>
								   </#list>
							   </dd>
					       </#if>
					       <#if sortMedicine.secondListBreed?? && sortMedicine.secondListBreed?size gt 0>
					       	   <dt><span class="f16">G H I J K L</span></dt>
					       	   <dd>
							       <#list sortMedicine.secondListBreed as breed >
								     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${sortMedicine.categorys_id}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a><#if breed_index+1!=sortMedicine.secondListBreed?size> |<#else></#if>
								   </#list>
							   </dd>
					       </#if>
					       <#if sortMedicine.thirdListBreed?? && sortMedicine.thirdListBreed?size gt 0>
					       	   <dt><span class="f16">M N O P Q R</span></dt>
					       	   <dd>
							       <#list sortMedicine.thirdListBreed as breed >
								     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${sortMedicine.categorys_id}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a><#if breed_index+1!=sortMedicine.thirdListBreed?size> |<#else></#if>
								   </#list>
							   </dd>
					       </#if>
					       <#if sortMedicine.fourthListBreed?? && sortMedicine.fourthListBreed?size gt 0>
					       	   <dt><span class="f16">S T U V W X Y Z</span></dt>
					       	   <dd>
							       <#list sortMedicine.fourthListBreed as breed >
								     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${sortMedicine.categorys_id}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a><#if breed_index+1!=sortMedicine.fourthListBreed?size> |<#else></#if>
								   </#list>
							   </dd>
					       </#if>
				       </dl>
				       <h4><a href="${JOINTOWNURL}/search/category/${(sortMedicine.categorys_id)!''}?value=${sortMedicine.categorys_name?url}"  target="_blank">${(sortMedicine.categorys_name)!''}所有药材<img src="${RESOURCE_IMG}/images/nav-jt.png"/></a></h4>
					</#if>
				</div>
		</div>
		</#list>
	</#if>
</div>
</div>
<ul class="fl">
    <li class="cur"><a href="${JOINTOWNURL}">首页</a></li>
    <li><a href="/purchaseSearch/list")">采购信息</a></li>
    <li><a href="/index/getWarehousing">我要入仓</a></li>
    <li><a href="/index/getStockService">仓储服务</a></li>
    <!--<li><a href="#">运输服务</a></li>-->
    <!-- <li><a href="/index/getBankingService">融资服务</a></li>
    <li class="noCur"><a href="http://www.zyczyc.com" target="_blank">价格行情</a></li>-->
    <li><a>融资服务</a></li>
    <li class="noCur"><a>价格行情</a></li>
    <li class="noCur"><a href="http://help.54315.com" target="_blank">客服中心</a></li>
</ul>
<div class="fr kf-phone"></div>
</div>
</div>
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
    <div class="area-956 fl">
        <div class="box-1 mt10">

		<!--焦点图 开始-->
        <div class="ck-slide">
            <ul class="ck-slide-wrapper">
            	<#assign n = 0 />
            	<#assign adCount = admap['0']?size/>
           		<#list admap['0'] as a>
           			<#if n lt adCount>
               		   <li <#if a_index=0>style="display:block;"</#if>>
	               		   <#if a.url==null>
	                        	<a><img src="${a.picurl}" alt="${a.alt}"></a>
	                       <#else>
	                       		<!--add by fanyuna 首页焦点图点击统计 start-->
	                        	<script>_czc.push(["_trackPageview","${a.url}"]);</script>
	                        	<!--add by fanyuna 首页焦点图点击统计 end-->
	                        	<a href="${a.url}" target="_blank"><img src="${a.picurl}" alt="${a.alt}"></a>
	                       </#if>
                       </li>
                    </#if>    
                	<#assign n = n+1 /> 
           		</#list>
            </ul>
            <a href="javascript:;" class="ctrl-slide ck-prev">上一张</a> <a href="javascript:;" class="ctrl-slide ck-next">下一张</a>
            <div class="ck-slidebox">
                <div class="slideWrap">
                    <ul class="dot-wrap">
                    <#list admap['0'] as a>
                    	<#if a_index+1 = 1>
                    		 <li class="current"><em>${a_index+1}</em></li>
                    	<#else>
                    		<li><em>${a_index+1}</em></li>
                    	</#if>
                     </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="area-234 fr">
        <div class="border-1 mt10">
	       <#if ucUser??>
	       		<p class="leecr">您好，欢迎来到54315.com！</p>
	       <#else>
		        <p class="oprate">
	                <a href="http://uc.54315.com/getUcUserRegister" class="col_50 br"><i class="zc"></i>免费注册</a>
	                <a href="${JOINTOWNURL}/login?go=http://www.54315.com" class="col_50"><i class="dl"></i>立即登录</a>
	            </p>
	       </#if>
           
            <ul class="tabs-1 clearfix" id="tabs1">
                <li class="cur"><a href="http://help.54315.com/view-1222808050-4277461942.html" target="_blank">买家采购指引</a></li>
                <li> <a href="http://help.54315.com/view-2011458294-7859114524.html" target="_blank">卖家销售指引</a></li>
            </ul>
            <div id="tabCont1">
                <div class="conts-1" style="display: block;">
                    <ul class="list_12">
                        <li><span class="col_999">[新手指南] </span> <a href="http://help.54315.com/view-826324128-134455933.html" target="_blank">注册及实名认证</a> </li>
                        <li><span class="col_999">[新手指南]</span> <a href="http://help.54315.com/view-1222808050-4277461942.html" target="_blank"> 采购商如何加入</a> </li>
                        <li><span class="col_999">[交易服务]</span> <a href="http://help.54315.com/view-1717367380-9370413524.html" target="_blank"> 如何采购药材</a> </li>
                        <li><span class="col_999">[交易服务]</span> <a href="http://help.54315.com/view-1112212469-1417544386.html" target="_blank"> 如何付款</a> </li>
                    </ul>
                </div>
                <div class="conts-1">
                    <ul class="list_12">
                        <li><span class="col_999">[新手指南] </span> <a href="http://help.54315.com/view-826324128-134455933.html" target="_blank">注册及实名认证</a> </li>
                        <li><span class="col_999">[新手指南]</span> <a href="http://help.54315.com/view-2011458294-7859114524.html" target="_blank"> 供应商如何加入</a> </li>
                        <li><span class="col_999">[交易服务]</span> <a href="http://help.54315.com/view-1970233306-7837069404.html" target="_blank"> 如何挂牌销售</a> </li>
                        <li><span class="col_999">[交易服务]</span> <a href="http://help.54315.com/view-1442508962-9836334809.html" target="_blank"> 销售货款结算</a> </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="mt10"><a href="/index/getWarehousing" target="_blank" class="exit-rc">免费申请入仓</a></div>
		<!--现货天天报-->
        <div class="border-1 mt10">
            <h2 class="tit1">现货天天报</h2>
            <div id="scrollDiv">
                <ul class="list_12 charges">
                <#list broadcastEverydayList as broadcastEveryday >
                	 <li><span class="wid-1"><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${broadcastEveryday.LISTINGID}" target="_blank">${broadcastEveryday.BREEDNAME}</a></span><span class="wid-1">${broadcastEveryday.CREATETIME}</span><span class="wid-2"><@tools.money num=broadcastEveryday.WLSURPLUS format="0.##"/>${broadcastEveryday.WLUNIT}</span></li>
                </#list>
                </ul>
            </div>

        </div>
    </div>
    <div class="clearfix breed-box hidden">
        <ul>
            <li>
                <span class="fl"><i class="icon1"></i><br/>
                <strong>火爆品种</strong></span>
                <p>
	                <#list msgmap['1'] as l>
			    		<a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${l.listingid }" target="_blank">${l.name }</a></#list>
                </p>
            </li>
            <li>
                <span class="fl"><i class="icon2"></i><br/>
                    <strong>低价特卖</strong></span>
                <p>
                	<#list msgmap['4'] as l>
	                    <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${l.listingid }" target="_blank">${l.name } </a>
			    	</#list>
                </p>
            </li>
            <li>
                <span class="fl"><i class="icon3"></i><br/>
                    <strong>小品种专区</strong></span>
                <p>
                	<#list categorylist3 as l >
	                    <a href="${JOINTOWNURL}/search?keyWords=${l.categorys_name?url}" target="_blank">${l.categorys_name }</a>
			    	</#list>
                </p>
            </li>
            <li>
                <span class="fl"><i class="icon4"></i><br/>
                    <strong>各地大仓</strong></span>
                <p>
                   <a href="${JOINTOWNURL}/search?keyWords=${'亳州'?url}&mode=WAREHOUSE" target="_blank">安徽亳州
                    </a><a href="${JOINTOWNURL}/search?keyWords=${'安国'?url}&mode=WAREHOUSE" target="_blank">河北安国
                </a><a href="${JOINTOWNURL}/search?keyWords=${'陇西'?url}&mode=WAREHOUSE" target="_blank">甘肃陇西
                </a> <a href="${JOINTOWNURL}/search?keyWords=${'玉林'?url}&mode=WAREHOUSE" target="_blank">广西玉林
                </a><a href="${JOINTOWNURL}/search?keyWords=${'成都'?url}&mode=WAREHOUSE" target="_blank">四川成都
                </a><a href="${JOINTOWNURL}/search?keyWords=${'石柱'?url}&mode=WAREHOUSE" target="_blank">重庆石柱</a>
                </p>
            </li>
        </ul>
    </div>
    <div class="area-956 fl">
    	<h2 class="tit4 clearfix pt20"> 主推品种</h2>
        <div class="box-2 fl">
            <div class="relative actives">
            	<#assign r = 0 />
               	<#list admap['5'] as a>
            		<#assign r = r+1 />
            		<#if r=1>
            			<#if a.url==null>
                        	<a><img src="${a.picurl}" alt="${a.alt}"></a>
                        <#else>
                        	<a href="${a.url}" target="_blank"><img src="${a.picurl}" alt="${a.alt}"></a>
                        </#if>
            		</#if>
            	</#list>
            </div>
        </div>
        <div class="box-3 fl">
            <ul class="img-breedlist fl">
            	<#assign x = 0 />
               	<#list admap['5'] as a>
            		<#assign x = x+1 />
	            	<#if x=3>
            			<li class="ad-2">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	<#elseif x=2 || x=4>
	            		<li class="ad-1">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	</#if>	
            	</#list>
                <#list msgmap['9'] as msg>
                	<li>
	                    <div>
	                        <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><span><img src="${msg.picurl }"  alt="${msg.alt }"/> </span>
	                            <div class="png"></div>
	                        </a>
	                    </div>
	                        <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name } </a><img src="${RESOURCE_IMG}/images/xhc.png" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" alt="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" /> <br/>
	                            <strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }
	                        </h4>
	                        <p><span class="fl">${msg.grade }</span> <span class="fr">${msg.origin }</span></p>
	                </li>
                </#list>
            </ul>
        </div>
    
        <h2 class="tit2 pt20 clearfix"> 大宗品种</h2>
        <div class="box-2 fl">
            <div class="actives">
            	<#assign r = 0 />
               	<#list admap['1'] as a>
            		<#assign r = r+1 />
            		<#if r=1>
            			<#if a.url==null>
                        	<a><img src="${a.picurl}" alt="${a.alt}"></a>
                        <#else>
                        	<a href="${a.url}" target="_blank"><img src="${a.picurl}" alt="${a.alt}"></a>
                        </#if>
            		</#if>
            	</#list>
            </div>
        </div>
        <div class="box-3 fl">
            <ul class="img-breedlist fl">
                <#assign x = 0 />
               	<#list admap['1'] as a>
            		<#assign x = x+1 />
	            	<#if x=3>
            			<li class="ad-2">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	<#elseif x=2 || x=4>
	            		<li class="ad-1">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	</#if>	
            	</#list>
                <#list msgmap['2'] as msg>
                	<li>
	                    <div>
	                        <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><span><img src="${msg.picurl }"  alt="${msg.alt }"/> </span>
	                            <div class="png"></div>
	                        </a>
	                    </div>
	                        <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name } </a><img src="${RESOURCE_IMG}/images/xhc.png" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" alt="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" /> <br/>
	                            <strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }
	                        </h4>
	                        <p><span class="fl">${msg.grade }</span> <span class="fr">${msg.origin }</span></p>
	                </li>
                </#list>
            </ul>
        </div>
        <h2 class="tit3 clearfix pt20"> 常用品种</h2>
        <div class="box-2 fl">
            <div class="actives">
            	<#assign z = 0 />
               	<#list admap['2'] as a>
            		<#assign z = z+1 />
            		<#if z=1>
            			<#if a.url==null>
                        	<a><img src="${a.picurl}" alt="${a.alt}"></a>
                        <#else>
                        	<a href="${a.url}" target="_blank"><img src="${a.picurl}" alt="${a.alt}"></a>
                        </#if>
            		</#if>
            	</#list>
            </div>
        </div>
        <div class="box-3 fl">
            <ul class="img-breedlist fl">
                <#assign x = 0 />
               	<#list admap['2'] as a>
            		<#assign x = x+1 />
	            	<#if x=3>
            			<li class="ad-2">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	<#elseif x=2 || x=4>
	            		<li class="ad-1">
	            			<#if a.url==null>
	                    		<a><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    <#else>
		                    	<a href="${a.url}" target="_blank"><div><img src="${a.picurl}" alt="${a.alt}"></div>
		                    </#if>
            				<div class="png"></div></a>
            			</li>
	            	</#if>	
            	</#list>
                <#list msgmap['3'] as msg>
                	<li>
	                    <div>
	                        <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><span><img src="${msg.picurl }" /> </span>
	                            <div class="png"></div>
	                        </a>
	                    </div>
	                        <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name } </a><img src="${RESOURCE_IMG}/images/xhc.png" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" alt="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。" /> <br/>
	                            <strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }
	                        </h4>
	                        <p><span class="fl">${msg.grade }</span> <span class="fr">${msg.origin }</span></p>
	                </li>
                </#list>
            </ul>
        </div>
        <h2 class="tit5 clearfix pt20"> 药企采购</h2>
        <div class="box-2 fl">
            <div class="relative actives-2"><img src="${RESOURCE_IMG}/images/index-5.png" width="194">
                <span class="seecg"> <a href="${JOINTOWNURL}/busiPurchaseApply/iWillProcurement" target="_blank">我要申请采购 ></a></span>
            </div>
            <ul class="list_12 border-1">
            	<#list articlelist as article>
            		<li>【电商】<a href="http://help.54315.com/view-${article.categoryId }-${article.id }.html" target="_blank">${article.title }</a></li>
            	</#list>
            </ul>
        </div>
        <div class="box-3 fl">
            <div id="ad-4" class="ad-3 fouce-img">
                <ul>
                	<#assign y = 0 />
               		<#list admap['4'] as a>
	            		<#assign y = y+1 />
	            		<#if y=1>
			           		<#if a.url==null>
	                        	<li><a><img src="${a.picurl}" alt="${a.alt}"></a></li>
	                        <#else>
	                        	<li><a href="${a.url}" target="_blank"><img src="${a.picurl}" alt="${a.alt}"></a></li>
	                        </#if>
	            		</#if>
	            	</#list>
                </ul>
            </div>

            <ul class="detail-box">
                <li class="title">
                    <span class="wid-1"><strong>品种名称</strong></span>
                    <span class="wid-1"><strong>等级规格</strong></span>
                    <span class="wid-2 tr"><strong>采购量</strong></span>
                    <span class="wid-3 tc"><strong>采购商</strong></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-1596750392-766070762.html">西洋参</a></span><span class="wid-1" title="多种规格">多种规格</span><span class="wid-2 tr">12100 公斤</span><span class="wid-3"><i class="fl">江苏九州通医药有限公司等</i>  <i class="fr"> 共 5 家</i></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-1596750392-766070762.html">生晒参</a></span><span class="wid-1" title="多种规格">多种规格</span><span class="wid-2 tr">1600 公斤</span><span class="wid-3"><i class="fl">浙江九州通医药有限公司等</i><i class="fr">共 3 家</i></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-1596750392-766070762.html">石斛</a></span><span class="wid-1" title="多种规格">多种规格</span><span class="wid-2 tr">3300 公斤</span><span class="wid-3"><i class="fl">上海九州通医药有限公司等</i><i class="fr">共 4 家</i></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-1596750392-1308167561.html">蟾蜍</a></span><span class="wid-1" title="块">块</span><span class="wid-2 tr">20 公斤</span><span class="wid-3"><i class="fl">黄冈九州通中药材有限公司</i><i class="fr">共 1 家</i></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-1596750392-1047092927.html">野菊花</a></span><span class="wid-1" title="统货">统货</span><span class="wid-2 tr">100 吨</span><span class="wid-3"><i class="fl">麻城九州通中药材发展有限公司</i><i class="fr">共 1 家</i></span>
                </li>
                <li>
                    <span class="wid-1"><a href="http://help.54315.com/view-22053261-524566102.html">当归</a></span><span class="wid-1" title="归头一等">归头一等</span><span class="wid-2 tr">10 吨</span><span class="wid-3"><i class="fl">广东罗浮山国药、广州白云山敬修堂</i><i class="fr">共 2 家</i></span>
                </li>
            </ul>
        </div>
        <div class="box-4 clearfix">
            <div class="border-1 ov-hid">
                <ul class="list_style5 clearfix">
                    <li>
                        <a href="http://help.54315.com/view-1596750392-766070762.html" target="_blank"><span><img src="${RESOURCE_IMG}/images/index_pic/index-h_01.jpg" /></span>
                        <p>·西洋参<br/>
                            ·生晒参<br/>
                            ·石斛<br/>
                        </p>
                        <h3>完成采购额<strong class="col_red f16">50</strong>万元<br/>
                            采购进度<strong class="f16 col_303030">5%</strong>
                        </h3></a>
                    </li>
                    <li>
                        <a href="http://help.54315.com/view-1596750392-766070762.html" target="_blank"><span><img src="${RESOURCE_IMG}/images/index_pic/index-h_02.jpg" /></span>
                        <p>·西洋参<br/>
                        ·生晒参<br/>
                        ·石斛<br/>
                        </p>
                            <h3>完成采购额<strong class="col_red f16">30</strong>万元<br/>
                                采购进度<strong class="f16 col_303030">5%</strong>
                            </h3></a>
                    </li>
                    <li>
                        <a href="http://help.54315.com/view-1596750392-1395721969.html" target="_blank"><span><img src="${RESOURCE_IMG}/images/index_pic/index-h_03.jpg" /></span>
                            <p>·红参<br/>
                                ·党参<br/>
                                ·黄芪<br/>
                            </p>
                            <h3>完成采购额<strong class="col_red f16">20</strong>万元<br/>
                                采购进度<strong class="f16 col_303030">5%</strong>
                            </h3></a>
                    </li>
                    <li>
                        <a href="http://help.54315.com/view-1596750392-610124192.html" target="_blank"><span><img src="${RESOURCE_IMG}/images/index_pic/index-h_04.jpg" /></span>
                            <p>·三七<br/>
                                ·灵芝<br/>
                                ·川芎<br/>
                            </p>
                            <h3>完成采购额<strong class="col_red f16">120</strong>万元<br/>
                                采购进度<strong class="f16 col_303030">5%</strong>
                            </h3></a>
                    </li>
                    <li>
                        <a href="http://help.54315.com/view-1596750392-1300866896.html" target="_blank"><span><img src="${RESOURCE_IMG}/images/index_pic/index-h_05.jpg" /></span>
                        <p>·黄连<br/>
	                        ·川芎<br/>
	                        ·当归<br/>
	                    </p>
                            <h3>完成采购额<strong class="col_red f16">30</strong>万元<br/>
                                采购进度<strong class="f16 col_303030">5%</strong>
                            </h3></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!-- 右侧 -->
    <div class="area-234 fr pt20">
        <div class="border-1">
            <h2 class="tit1">热门推荐</h2>
            <ul class="list_style1">
                <#list msgmap["recom"] as msg>
	                <li>
                        <a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">
	                    <h3><span>${msg.name }</span> <img src="${RESOURCE_IMG}/images/hot.png"/></h3>
	                    <p class="col_31 f14">
	                    	<span class="">价格：<@tools.money num=msg.price format="0.##"/>${msg.dictvalue }</span>
	                    </p>
	                    <p><span class="">规格：${msg.grade }</span></p>
	                    <p><span class="">产地：${msg.origin }</span></p>
	                    </a>
	               </li>
                </#list>
            </ul>
        </div>
        <div class="border-1 mt10">
           <!-- <h2 class="tit1"><span class="more"><a href="http://www.zyczyc.com" target="_blank">更多>></a></span> 行情资讯</h2>-->
           <h2 class="tit1"><span class="more"><a>更多>></a></span> 行情资讯</h2>
            <ul class="list_style2">
            	<#list dynamiclist as article>
            	     <!--	<li><a href="http://www.zyczyc.com/info/Content.aspx?lmid=${article.lmid }&acid=${article.acid }&shopid=-1" target="_blank">${article.title }</a></li>-->
            	     <li><a>${article.title }</a></li>
            	</#list>
            </ul>
        </div>
        <div class="border-1 mt10">
            <h2 class="tit1"> 涨价药材</h2>
            <ul class="list_style3">
            	<#list msgmap['5'] as msg>
            		<li>
	                    <span><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><img src="${msg.picurl }"/><div class="png"></div> </a> </span>
	                    <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name }</a><br/><strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }</h4>
	                </li>
                </#list>
            </ul>
            <h2 class="tit1"> 跌价药材</h2>
            <ul class="list_style3">
            	<#assign dj = 0 />
	            <#list msgmap['6'] as msg>
	            <#assign dj = dj+1 />
	            <#if dj=4>
                	<li  class="bn">
                <#else>
                	<li>
                </#if>
	                    <span><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><img src="${msg.picurl }" alt="${msg.alt }"/><div class="png"></div></a></span>
	                        <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name }</a><br/>
	                        <strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }</h4>
	                </li>
                </#list>
            </ul>
        </div>
      <!-- <div align="center" class="mt10">
     			 <a href="${URL_TOPIC}/150602/shfb.html" target="_blank"><img src="${RESOURCE_IMG}/images/index_pic/index-ad1.jpg" />
     			  </a>
     		 </div>-->
        <div class="border-1 mt10">
            <h2 class="tit1"> 紧俏药材</h2>
            <ul class="list_style4">
            	<#assign jq = 0 />
            	<#list msgmap['7'] as msg>
            	<#assign jq = jq+1 />
	            <#if jq=4>
                	<li  class="bn">
                <#else>
                	<li>
                </#if>
	                    <span><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank"><img src="${msg.picurl }" alt="${msg.alt }"/><div class="png"></div> </a></span>
                        <h4><a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${msg.listingid }" target="_blank">${msg.name }</a><br/>${msg.grade }<br/>
                            <strong class="col_red"><@tools.money num=msg.price format="0.##"/></strong>${msg.dictvalue }
                        </h4>
	                </li>
                </#list>
            </ul>
        </div>
    </div>
    <!-- 右侧 end -->
    <div class="clearfix"></div>
    <!-- <div class="index-ad">
       <a href="1" title="上海封浜">
        </a><a href="2" title="河北楚风">
        </a><a href="3" title="新疆和济">
        </a><a href="4" title="黄冈金贵">
        </a><a href="5" title="湖北金贵"></a>
    </div>-->
    <div class="i-box-656 fl mt20">
        <ul class="i-tabs clearfix" id="tabs2">
            <li class="cur"><a><!-- href="http://www.zyczyc.com/info/MarkNews.aspx" target="_blank">-->市场快讯</a> </li>
            <li><a><!-- href="http://www.zyczyc.com/info/ChanDiInfo.aspx" target="_blank">-->产地快讯</a></li>
        </ul>
        <div class="border-1 i-cont" id="tabCont2">
            <div id="scroll1" style="display: block;">
                <ul class="style1">
                	<#list marketNews as article>
            			<li>
            				<span>${article.dtm?string("yyyy-MM-dd HH:mm:ss")!'' }</span>
            				<p>${article.cont }</p>
            			</li>
            		</#list>
                </ul>
            </div>

            <div id="scroll2">
                <ul class="style1">
                	<#list origin as article>
            			<li>
            				<span>${article.dtm?string("yyyy-MM-dd HH:mm:ss")!'' }</span>
            				<p>
            				<#if article.cont?length gt 100>
            					${article.cont?substring(0,100)+"..." }
            				<#else>
            					${article.cont}
            				</#if>
            				</p>
            			</li>
            		</#list>
                </ul>
            </div>
        </div>
    </div>

    <div class="i-box-528 fr mt20">
        <ul class="i-tabs clearfix">
            <li class="cur"><a><!-- href="http://www.zyczyc.com/info/WenZhang.aspx?lmid=1" target="_blank">-->品种分析</a> </li>
        </ul>
        <div class="border-1 i-cont">
            <ul class="style2">
            	<#list breed as article>
           			<li>
           				<h3><a><!-- href="http://www.zyczyc.com/info/Content.aspx?lmid=${article.lmid }&acid=${article.acid }&shopid=-1" target="_blank">-->${article.title }</a></h3>
           				<span>作者:${article.writer}&nbsp;&nbsp;&nbsp;&nbsp;${article.dtm?string("yyyy-MM-dd HH:mm:ss")!'' }</span>
           				<p>
           				<#if article.cont?length gt 60>
           					${article.cont?substring(0,60)+"..." }<!--<a href="http://www.zyczyc.com/info/Content.aspx?lmid=${article.lmid }&acid=${article.acid }&shopid=-1" class="red" target="_blank">>>全文</a>-->
           				<#else>
           					${article.cont}<!--<a href="http://www.zyczyc.com/info/Content.aspx?lmid=${article.lmid }&acid=${article.acid }&shopid=-1" class="red" target="_blank">>>全文</a>-->
           				</#if>
           				</p>
           			</li>
            	</#list>
            </ul>
        </div>
    </div>
    <div class="bank-box fl mt10">
        <h2 class="tit6">合作银行</h2>
        <div class="bank-bg">
        </div>
    </div>
    <div class="area-527 mt10 fr">
        <h2 class="tit6">珍药材动态</h2>
        <div class="news-box">
            <ul class="list_12">
            	<#list alist as article>
            		<li><span class="col_red">·</span><a href="http://help.54315.com/view-${article.categoryId }-${article.id }.html" target="_blank">${article.title }</a></li>
            	</#list>
            </ul>
        </div>
    </div>
</div>

<!-- 祥情页主体over -->

<!-- 底部  -->
<div class="foot">
    <div class="area-1200">
          <!-- <ul class="clearfix">
           <li class="style-1">
                <strong class="f14">新手指南</strong>
                <a href="http://help.54315.com/view-1770909150-4890870192.html"  target="_blank">了解珍药材</a>
                <a href="http://help.54315.com/view-826324128-134455933.html" target="_blank">注册及实名认证</a>
				<a href="http://help.54315.com/view-1222808050-4277461942.html"  target="_blank">采购商入门</a>
                <a href="http://help.54315.com/view-2011458294-7859114524.html"  target="_blank">供应商入门</a>
            </li>
           <li class="style-1">
                <strong class="f14">交易服务</strong>
                <a href="http://help.54315.com/view-1970233306-7837069404.html" target="_blank">挂牌销售</a>
                <a href="http://help.54315.com/view-1717367380-9370413524.html" target="_blank">药材采购</a>
                <a href="http://help.54315.com/view-1763402417-5532681146.html" target="_blank">交易保障</a>
                <a href="http://help.54315.com/view-1112212469-1417544386.html" target="_blank">如何付款</a>
                <a href="http://help.54315.com/view-1074337588-1881602956.html" target="_blank">药材交收</a>
                <a href="http://help.54315.com/view-1442508962-9836334809.html" target="_blank">货款结算</a>
            </li>
            <li class="style-1">
                <strong class="f14">金融服务</strong>
                <a href="http://help.54315.com/view-165069101-570170504.html"  target="_blank">药材质押</a>
                <a href="http://help.54315.com/view-90284155-1702282056.html"  target="_blank">应收保理</a>
                <a href="http://help.54315.com/view-362846536-471937280.html"  target="_blank">授信采购</a>
            </li>
            <li class="style-1">
                <strong class="f14">仓储物流</strong>
                <a href="http://help.54315.com/view-1154327359-8543844178.html" target="_blank">仓库介绍</a>
                <a href="http://help.54315.com/view-1990279866-6239033474.html" target="_blank">入库流程</a>
                <a href="http://help.54315.com/view-1148978752-4550043483.html" target="_blank">保管安全</a>
                <a href="http://help.54315.com/view-1291479146-6465076424.html" target="_blank">远程查看</a>
            </li>
            <li class="style-1">
                <strong class="f14">委托服务</strong>
                <a href="http://help.54315.com/view-296408919-7787049031.html" target="_blank">委托采购</a>
                <a href="http://help.54315.com/view-1463152138-1723459540.html" target="_blank">委托销售</a>
                <a href="http://help.54315.com/view-443979987-1583418185.html" target="_blank">委托养护</a>
                <a href="http://help.54315.com/view-1393195069-8959880481.html" target="_blank">委托配送</a>
                <a href="http://help.54315.com/view-1585124319-3648243659.html" target="_blank">全外包服务</a>
            </li>
           <li class="style-1">
                <strong class="f14">价格行情</strong>
                <a href="http://www.zyczyc.com/info/WenZhang.aspx?lmid=2&key=十日谈"  target="_blank">专家十日谈</a>
                <a href="http://www.zyczyc.com/info/MarkNews.aspx"  target="_blank">行情报道</a>
                <a href="http://www.zyczyc.com/info/JiaGe.aspx"  target="_blank">今日价格</a>
                <a href="http://www.zyczyc.com/info/ChanDiInfo.aspx"  target="_blank">产地动态</a>
                <a href="http://www.zyczyc.com/BBS/LunTan.aspx"  target="_blank">药商论坛</a>
            </li>
           <li class="style-2"><img src="${RESOURCE_IMG}/images/bottom-pic.png"/></li>
        </ul>

    	  <div class="hr"></div>-->
        <div align="center" class="mt10">
            <p class="links">
            	友情链接：<a href="http://www.jztey.com/" target="_blank">九州通医药集团</a>
                <a href="http://www.jztzygs.com/zycygs/" target="_blank">九州通中药产业公司</a>
                <a href="http://www.yyjzt.com/" target="_blank">药品在线采购平台</a>
                <a href="http://www.ehaoyao.com/" target="_blank">好药师网上药店</a>
                <a href="http://www.qumaiyao.com/" target="_blank" >去买药网</a>
                <a href="http://www.jzsyg.com" target="_blank" >九州上医馆</a>
                <a href="http://www.50yc.com" target="_blank" >物联云仓</a>
            </p>
            <p class="links"><a href="http://help.54315.com/view-4152142030-1649064836.html">关于珍药材</a>
                <!--<a href="#">各地招商</a>-->
                <a href="http://help.54315.com/view-4152142030-998555506.html">合作伙伴</a>
                <a href="http://help.54315.com/view-4152142030-969783539.html">经营证书</a>
                <a href="http://help.54315.com/view-4152142030-3504604999.html">法律声明</a>
                <a href="http://help.54315.com/view-4152142030-548339172.html">联系我们</a>
                <a href="http://help.54315.com/view-4152142030-8088297484.html">诚聘英才</a>
                <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254793674'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1254793674' type='text/javascript'%3E%3C/script%3E"));</script>
            </p>
            <!-- <p class="links">Copyright© 2014-2015 珍药材版权所有 鄂ICP备14019602号-2</p>
            <p class="links">增值电信业务经营许可证-鄂B2-20150052</p> -->
            <p class="links mt10">
            	九州通中药材电子商务有限公司     湖北省武汉市汉阳区龙阳大道特8号     027-84719677 <br/>
            	 增值电信业务经营许可证：<span style="color:#666;">鄂B2-20150052</span> | 互联网药品信息服务资格证：<a class="bn pn" href="
			http://help.54315.com/view-4152142030-969783539.html
			" target="_blank">(鄂)-经营性-2015-0019</a><br/>
			                Copyright© 2014-2016 ，珍药材版权所有 鄂ICP备14019602号-2

            </p>
        </div>
    </div>
</div>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/textSider.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/slide.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/index.js"></script>
<script type="text/javascript">
 	$('.ck-slide').ckSlide({
        autoPlay: true
    });
	$(function(){
		 $('#Search').focus(function(){
	        if($(this).val() == "输入名称找药材"){
	            $(this).val('');
	        }
		    }).blur(function(){
		        if($(this).val() === ''){
		            $(this).val('输入名称找药材');
		        }
		    });
		$('#searchBtn').searcher({
			onSearch:function() {
			var keyword=$('input[type="text"].search-text').val();
			if(keyword == "输入名称找药材"){
				keyword='';
			}
			window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
		 	}
		});
		
		
	});
	
	 $('#zaop').on('click',function(){
	        $(this).parents('.zhaopin').slideUp(300);
	    })
</script>
<#if ucUser??>
<#else>
	<script type="text/javascript" src="https://passport.54315.com/login?service=http%3A%2F%2Fwww.54315.com/casuc&get-lt=true&isajax=true"></script>
</#if>
</body>
</html>