<#setting url_escaping_charset='utf8'> 
<#include "common/indexTopAndSearch.ftl"/>
<!-- 品种类目内容，二级菜单menu -->
<div class="nav relative">
  <div class="area-1200">
  <div class="list fl relative"><a href="${JOINTOWNURL}/search?" class="co_fff">全部<span class="col_yellow">在仓</span>药材${tunnage } 吨</a>
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
      <li><a href="${JOINTOWNURL}/search?">挂牌现货</a></li>
      <li><a href="${JOINTOWNURL}/purchaseSearch/list">采购信息</a></li>
      <li><a href="${JOINTOWNURL}/index/getBankingService">药材金融</a></li>
      <li><a href="${JOINTOWNURL}/index/getStockService">仓储服务</a></li>
      <li><a href="http://www.zyczyc.com" target="_blank">价格行情</a></li>
      <li class="bn"><a href="http://help.54315.com" target="_blank">客服中心</a></li>
  </ul>
  </div>
</div>
