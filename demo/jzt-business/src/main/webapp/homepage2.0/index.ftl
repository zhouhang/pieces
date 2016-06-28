<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>珍药材_中国领先的中药材现货交易平台</title>
    <#import 'macro.ftl' as tools>
    <meta name="description" content="珍药材54315.com是九州通打造的有质量保障的中药材现货交易平台,为中药材上下游企业提供专业的中药材信息资讯，中药材交易服务，中药材供应链金融服务，中药材质押服务，中药材物流仓储以及中药材质检服务"/>
    <meta name="keywords" content="珍药材网，中药材市场，中药材价格行情，中药材交易，中药材仓储物流，中药材融资，中药材贷款，中药材金融，中药材采购，中药材供应"/>
    <link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
    <link href="${RESOURCE_CSS}/css/index.css" type="text/css" rel="stylesheet" />
	<!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  start -->
    <script>
	     <!--声明_czc对象:-->
		var _czc = _czc || [];
		//绑定siteid，请用您的siteid替换下方"XXXXXXXX"部分
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
	    //add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  end
		
	</script> 
</head>
<body>
<!--topper strat-->
<!-- 滑动后显示的头部内容 -->
<#include "homepage2.0/index-topSlideSearch.ftl"/>
<!-- 滑动后显示的头部内容 end -->

<#include "homepage2.0/index-ListHeader.ftl"/>
<!--topper over-->

<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
	<!-- 大幅banner，专题banner，指南 start -->
    <#include "homepage2.0/index-bannerAndGuide.ftl"/>
	<!-- 大幅banner，专题banner，指南 end -->
	
<div class="conts clearfix">
    <!-- 珍药现货 珍药采购 -->
	<#include "homepage2.0/index-middle.ftl"/>
    <!-- 珍药现货 珍药采购 -->

    <h2 class="title1 bor3 pt20 clearfix">
        <a target="_blank" class="fr" href="http://www.54315.com/index/getBankingService">进入金融频道 &gt;&gt;</a>
        珍药金融
    </h2>
    <div class="jingrong-bg">
        <div class="form-help">
        <form action="/feedBackManage/addFeedBack">
        	<input type="hidden" name="type" value="4">
            <textarea name="content" placeholder="写下您的融资需求，包括药材品种、融资金额，融资期限，联系电话等，收到后我们会立即回电与您确认，剩下就交给融资小珍吧：）" class="text-input"></textarea>
            <input type="submit" class="btn-input" value="帮我融资" />
        </form>
        </div>
    </div>

    <h2 class="title1 bor4 pt20 clearfix">
        <a target="_blank" class="fr" href="${JOINTOWNURL}/index/getStockService">进入仓储频道 &gt;&gt;</a>
        珍药仓库
    </h2>
    <div class="stock-bg">
        <a href="${JOINTOWNURL}/busiWarehouseApply/iWillWarehousing" target="_blank">立即入驻</a>
    </div>
    
    <!-- 价格指数 start-->
    <#include "homepage2.0/index-priceIndex.ftl"/>
	<!-- 价格指数 end-->
	
	<!-- 药材快讯、市场动态、品种分析、行业新闻 start -->
    <#include "homepage2.0/index-news.ftl"/>
    <!-- 药材快讯、市场动态、品种分析、行业新闻 end -->
    
  </div>
</div>
<!-- 祥情页主体over -->

<!-- 底部  -->
	<#include "common/listFooter.ftl"/>
<!-- 底部 end  -->
<!-- 表单弹层 END -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css" href="${RESOURCE_CSS}/js/Validform/css/style.css" />
<script type="text/javascript" src="${RESOURCE_JS}/js/Validform/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/textSider.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/slide.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/index.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/HighCharts/highcharts.js"></script>
<!-- 价格指数js -->
<script type="text/javascript" src="${RESOURCE_JS}/js/priceIndex.js"></script>
<!-- 侧边固定操作项 start -->
	<#include "homepage2.0/index-sidebar.ftl"/>
<script type="text/javascript">
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
		 
		 $('#sider-search').focus(function(){
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
			var keyword=$(this).prev('.search-text').val();
			if(keyword == "输入名称找药材"){
				keyword='';
			}
			window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
		 	}
		});
		
	}); 
	
</script>
<#if ucUser??>
<#else>
	<script type="text/javascript" src="https://passport.54315.com/login?service=http%3A%2F%2Fwww.54315.com/casuc&get-lt=true&isajax=true"></script>
</#if>
</body>
</html>