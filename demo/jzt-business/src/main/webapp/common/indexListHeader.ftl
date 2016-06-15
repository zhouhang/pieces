<#setting url_escaping_charset='utf8'>
<!--add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  start -->
    <script>
     <!--声明_czc对象:-->
	var _czc = _czc || [];
	//绑定siteid，请用您的siteid替换下方"XXXXXXXX"部分-->
	_czc.push(["_setAccount", "1254793674"]);
	<!--我要入仓事件-->
	_czc.push(["_trackPageview","/busiWarehouseApply/iWillWarehousing"]);
	<!--珍药材入仓申请事件-->
	_czc.push(["_trackEvent", "申 请","申请入仓"]);
	<!--登录-->
	_czc.push(["_trackPageview","/login"]);
	<!--头部免费注册pv事件-->
	_czc.push(["_trackPageview","${URL_SERVER_PREFIX_UC}${URL_REGISTER_UC}"]);
	<!--搜索框-->
	_czc.push(["_trackEvent", "搜 索", "搜索", "关键字搜索", "", "searchEngineListingButton"]);
	//详情页购买   按钮ajax请求-->
	_czc.push(["_trackPageview","/detail/buyGoods"]);
	//详情页收藏   按钮ajax请求-->
	_czc.push(["_trackPageview","/detail/collectGoods"]);
	<!--我要采购事件统计-->
	_czc.push(["_trackPageview", "${JOINTOWNURL}/index/getProcurement"]);
	<!--我要入仓事件统计-->
	_czc.push(["_trackPageview", "${JOINTOWNURL}/index/getWarehousing"]);
    //add by fanyuna 2015-06-12 添加cnzz统计跟踪代码  end
   </script>  

<!--topper strat-->
<#include "common/indexTopAndSearch.ftl"/>
<div class="nav relative">
  <div class="area-1200">
  <div class="list fl relative"><a href="${JOINTOWNURL}/search?" class="co_fff">全部<span class="col_yellow">在仓</span>药材${tunnage } 吨</a>
  <div class="sub-nav">
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
      <li><a href="${JOINTOWNURL}">首页</a></li>
      <#if url?? && url?starts_with("/search")>
      	<li class="cur" id="search">
      <#else>
      	<li id="search?">
      </#if>
      <a href="${JOINTOWNURL}/search?">挂牌现货</a></li>
      
      <#if url?? && url?starts_with("/purchaseSearch/list")>
      	<li class="cur" id="getProcurement">
      <#else>
      	<li id="getProcurement">
      </#if>
      <a href="${JOINTOWNURL}/purchaseSearch/list">采购信息</a></li>
      
      <#if (url== "/index/getBankingService")>
      		<li  class="cur" id="getBankingService">
      <#else>
      		<li id="getBankingService">
      </#if>
      <a href="${JOINTOWNURL}/index/getBankingService">药材金融</a></li>
      <#if (url== "/index/getStockService")>
      		<li  class="cur" id="getStockService">
      <#else>
      		<li id="getStockService">
      </#if>
      <a href="${JOINTOWNURL}/index/getStockService">仓储服务</a></li>
      <li class="noCur"><a href="http://www.zyczyc.com" target="_blank">价格行情</a></li>
      <li class="noCur bn"><a href="http://help.54315.com"  target="_blank">客服中心</a></li>
  </ul>
  </div>
</div>
<!-- 底部 end  -->
<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<#if ucUser??>
<#else>
	<script type="text/javascript" src="https://passport.54315.com/login?service=http%3A%2F%2Fwww.54315.com/casuc&get-lt=true&isajax=true"></script>
</#if>
<script type="text/javascript">
	//fl初始化
	var url = '${url!''}';
	if(url!=null){
		url = url.substring(url.lastIndexOf('/')+1);
		var tab_obj = $('#'+url);
		if(tab_obj){
			tab_obj.addClass('cur').siblings().removeClass('cur');
		}
	}
	
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
			var keyword=$(this).prev('.search-text').val();
			if(keyword == "输入名称找药材"){
				keyword='';
			}
			window.location.href='${JOINTOWNURL}/search?keyWords='+encodeURI(keyword);
		 	}
		});
	
	}); 
</script>
<!--topper over-->