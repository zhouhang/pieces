<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<#include "common/indexListTitle.ftl">
<link href="${RESOURCE_CSS}/css/reset.css" type="text/css" rel="stylesheet" />
<link href="${RESOURCE_CSS}/css/common.css" type="text/css" rel="stylesheet" />
<link href="${RESOURCE_CSS}/css/list.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${RESOURCE_JS}/js/datetimepicker/jquery.datetimepicker.css" />
</head>
<body>
	<!--topper strat-->
	<input type="hidden" id="imgBasePath" value="${RESOURCE_IMG_UPLOAD}" />
    <input type="hidden" id="resourceImg" value="${RESOURCE_IMG}" />   
    <#import 'page.ftl' as tools>
	<#include "common/indexListHeader.ftl">
	<!--topper over-->
<!-- 详情页主体开始 -->
<div class="area-1200 clearfix">
  <div class="area-998 fl mt10">
    <!--分类展开折叠 strat-->
	<#if keyWords!=null && keyWords!=''>
		<div id="searchAttribute" class="class-list">
			<b>您搜索的是 “</b>
			<b>
				<font color="red">${keyWords}</font>
				”
			</b>
		</div>
	<#elseif searchType=='CATEGORY'>
      <!--分类展开折叠 strat-->
      <div class="class-list" id="breed">
			<div class="box-1">
				<dl class="clearfix">
					<dt>${categoryName}</dt>
						<dd>
			    			<#if firstListBreed?? && firstListBreed?size gt 0>
						       	   <p class="title">A B C D E F</p>
								       <#list firstListBreed as breed >
									     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${searchId}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a>
									   </#list>
						       </#if>
						       <#if secondListBreed?? && secondListBreed?size gt 0>
						       	   <p class="title">G H I J K L</p>
								       <#list secondListBreed as breed >
									     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${searchId}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a>
									   </#list>
						       </#if>
						       <#if thirdListBreed?? && thirdListBreed?size gt 0>
						       	   <p class="title">M N O P Q R</p>
								       <#list thirdListBreed as breed >
									     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${searchId}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a>
									   </#list>
						       </#if>
						       <#if fourthListBreed?? && fourthListBreed?size gt 0>
						       	   <p class="title">S T U V W X Y Z</p>
								       <#list fourthListBreed as breed >
									     <a title="${(breed.BREED_NAME)!''}" <#if breed.FLAG='0'>class="col_999"</#if> href="${JOINTOWNURL}/search/category/${searchId}/breed/${(breed.BREED_ID)!''}?value=${breed.BREED_NAME?url}">${(breed.BREED_NAME)!''}</a>
									   </#list>
						      </#if>
				    	</dd>
				</dl>
			</div>
			<!--<div class="open-down">展开<i></i></div> -->
		</div>
	<#elseif searchType=='BREED'>
		<!--分类展开折叠 strat-->
		<div class="class-list" id="breedList">
			<div id="divBreedInfo" class="box-1">
				<ul id="breedcur" class="breed">
					<li class="title">
						<span class="tit"><strong>${categoryName}</strong></span>
						<span class="cont"><a id="breed_class" onclick="removeClass();" href="${JOINTOWNURL}/search/category/${cid}?value=${categoryName?url}" title="${breedName}" class="hover">${breedName}</a></span>
					</li>
					<li>
						<span class="tit">规格等级：</span>
							<#if breedStandLevelList??>
								<span class="tit2"><a href="#" title="全部" class="breed3 <#if grade=='' || grade=='*'>cur</#if>" name="all">全部</a></span>
								<span class="cont2">
									<#list breedStandLevelList as breedStandLevel>
										<#assign flag='false'/>
										<#if grade??>
											<#list grade?split("^") as s>
												<#if s == breedStandLevel.name>
													<a  class="breed3 hover" title="${breedStandLevel.name}" name="a" href="#">${breedStandLevel.name}</a>
													<#assign flag='true'/>
													<#break>
												</#if>
											</#list>
										</#if>	
										<#if flag=='false'>
											<a  class="breed3" title="${breedStandLevel.name}" name="a" href="#">${breedStandLevel.name}</a>
										</#if>
									</#list>
								</span>
							</#if>
					</li>
					<li>
						<span class="tit">所在仓库:</span>
							<#if listAllWarehouse??>
								<span class="tit2"><a href="#" title="全部" class="ware3 <#if warehouseName=='' || warehouseName=='*'>cur</#if>" name="all">全部</a></span>
								<span class="cont2">
									<#list listAllWarehouse as warehouse>
										<#assign flag='false'/>
										<#if warehouseName??>
											<#list warehouseName?split("^") as s>
												<#if s == warehouse.name>
													<a  class="ware3 hover" title="${warehouse.name}" name="a" href="#">${warehouse.name}</a>
													<#assign flag='true'/>
													<#break>
												</#if>
											</#list>
										</#if>	
										<#if flag=='false'>
											<a  class="ware3" title="${warehouse.name}" name="a" href="#">${warehouse.name}</a>
										</#if>
									</#list>
								</span>
							</#if>
					</li>
					<li>
						<span class="tit">常见产地:</span>
						<span class="tit2"><a href="#" title="全部" class="place3 <#if origin=='' || origin=='*'>cur</#if>" name="all">全部</a></span>
							<#if listBreedPlace ??>
								<span class="cont2">
									<#list listBreedPlace as breedPlace>
										<#assign flag='false'/>
										<#if origin??>
											<#list origin?split("^") as s>
												<#if s == breedPlace.name>
													<a  class="place3 hover" title="${breedPlace.name}" name="a" href="#">${breedPlace.name}</a>
													<#assign flag='true'/>
													<#break>
												</#if>
											</#list>
										</#if>	
										<#if flag=='false'>
											<a  class="place3" title="${breedPlace.name}" name="a" href="#">${breedPlace.name}</a>
										</#if>	
									</#list>
								</span>
							</#if>
					</li>
				</ul>
			</div>
		<!--	<div class="open-down">展开<i></i></div>-->
		</div>
	<#else>
	</#if>
	<div class="box-1">
		<form>
			<div class="price-search">
				<span>价格范围(单价)：</span>
				<input id="price_b" class="gray-price text1" type="text"
					<#if priceB==''||priceB=='*'>
						value="￥最低价格"
					<#else>
						value="${priceB}"
					</#if>
				 />—<input id="price_e" class="gray-price text1" type="text"
					<#if priceE==''||priceE=='*'>
						value="￥最高价格"
					<#else>
						value="${priceE}"
					</#if>	
					 /><span>产地：</span>
					 <input id="origin_i" class="text-price text2" type="text"
						<#if originText==''||originText=='*'> 
							value=""
						<#else>
							value="${originText}"
						</#if>
					  /><span>挂牌日期：</span> 
					<input type="text" class="text-price text3 mr10" name="date_b" id="date_b" 
						<#if dateB==''||dateB=='*'>
							value=""
						<#else>
							value="${dateB}"
						</#if>
					/>至<input type="text" class="text-price text3 ml10" id="date_e" name="date_e"
						<#if dateE==''||dateE=='*'>
							value=""
						<#else>
							value="${dateE}"
						</#if> 
					/><input class="btn-search ml10" id="btn-blue" value="筛选"  type="button" />
			</div>
		</form>
		<#--用来放置隐藏的表单(用来传递搜索的数据)-->
		<form id="sorListForm" method="post" action="${action}">
			<input   name="categoryName" id="categoryName" type="hidden" value="${categoryName}"/>
			<input   name="breedName" id="breedName" type="hidden" value="${breedName}"/>
			<input   name="grade" id="grade" type="hidden" value="${grade}"/> <!--等级规格-->
			<input   name="warehouseName" id="warehouseName" type="hidden" value="${warehouseName}"/> <!--仓库-->
			<input   name="priceB" id="priceB" type="hidden" value="${priceB}"/>
			<input   name="priceE" id="priceE" type="hidden" value="${priceE}"/>
			<input   name="dateB" id="dateB" type="hidden" value="${dateB}"/>
			<input   name="dateE" id="dateE" type="hidden" value="${dateE}"/>
			<input   name="origin" id="origin" type="hidden" value="${origin}"/> <!--产地-->
			<input   name="originText" id="originText" type="hidden" value="${originText}"/> <!--产地过滤框-->
			<input   name="sortListingSurplus" id="sortListingSurplus" type="hidden" value="${sortListingSurplus}"/>
			<input   name="sortPrice" id="sortPrice" type="hidden" value="${sortPrice}"/>
			<input   name="sortExamineTime" id="sortExamineTime" type="hidden" value="${sortExamineTime!'desc'}"/>
			<input   name="keyWords" id="keyWords" type="hidden" value="${keyWords}"/>
			<input   name="searchType" id="searchType" type="hidden" value="${searchType}"/>
			<input   name="searchId" id="searchId" type="hidden" value="${searchId}"/><!--传递searchId（有可能是品种id或者是类目id）-->
			<input   name="categorys_name" id="categorys_name" type="hidden" value="${categorys_name}"/><!--点击该品种传递该品种上面的类目的名称-->
			<input   name="lastSort" id="lastSort" type="hidden" value="${lastSort}"/> <!--保存上次搜索排序的类型-->
		</form>
		<div class="price-inventory clearfix">
			<span class="text_right fr">总共有<b  id="totalRecordResult">${page.totalRecord}</b>件商品，共<b id="totalPageResult">${page.totalPage}</b>页</span>
			<ul id="paixu">
				<li id='priceSort' 
					<#if sortPrice==''>
						sort='desc'
					<#else>
						sort='${sortPrice}'
					</#if>
					>价格
					<i 
						<#if sortPrice==''||sortPrice=='desc'>
							<#if lastSort=='sortPrice'>
								class="arrows_red"
							<#else>
								class="arrows_gray"
							</#if>
						<#elseif sortPrice=='asc'>
							class="arrows_red cur"
						</#if>
					>
					</i>
				</li>
				
				<li	id='storeSort'
					<#if sortListingSurplus==''> 
						sort='desc'
					<#else>
						sort=${sortListingSurplus}	
					</#if>	
				>库存
					<i 
						<#if sortListingSurplus==''||sortListingSurplus=='desc'>
							<#if lastSort=='sortListingSurplus'>
								class="arrows_red"
							<#else>
								class="arrows_gray"
							</#if>
						<#elseif sortListingSurplus=='asc'>
							class="arrows_red cur"
						</#if>
					></i>
				</li>
				<li	id='dateSort'
						<#if sortExamineTime==''> 
							sort='desc'
						<#else>
							sort=${sortExamineTime}	
						</#if>
					 >挂牌日期
					 <i
						 <#if sortExamineTime==''||sortExamineTime=='desc'>
						 	<#if lastSort=='sortExamineTime'>
								class="arrows_red"
							<#else>
								class="arrows_gray"
							</#if>
						 <#elseif sortExamineTime=='asc'>
							class="arrows_red cur"
						 </#if>
					  >
					  </i>
				 </li>
			</ul>
		</div>
	</div>
	<div class="price_list" id="listingList">
	<#if page.results>
		<ul>
			<li class="tit-th" id="tips_str" row-head-title='listingRowHeadTitle'>
				<div class="wid_1 fl">&nbsp;</div>
				<div class="wid_2 fl" align="center">
					<p class="tit2">所在仓库</p>
				</div>
				<div class="wid_2 fl" align="center">
					<p class="tit2">库存量</p>
				</div>
				<div class="wid_2 fl" align="center">
					<p class="tit2">单价</p>
				</div>
			</li>
				<#list page.results as busiListingSearchVo>
					<li>	
						<div class="wid_1 fl">
							<span class="img">
								<a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiListingSearchVo.listingId}" target="_blank">
									 <#if busiListingSearchVo.listingPic??>
					                  	<#assign minImg = busiListingSearchVo.listingPic?substring((busiListingSearchVo.listingPic?last_index_of("/")+1),(busiListingSearchVo.listingPic?last_index_of(".")))+"_120"+busiListingSearchVo.listingPic?substring(busiListingSearchVo.listingPic?last_index_of("."),busiListingSearchVo.listingPic?length)>
					  				  	<#assign tempurl = busiListingSearchVo.listingPic?substring(0,busiListingSearchVo.listingPic?last_index_of("/")+1)>
					                  	<img src="${RESOURCE_IMG_UPLOAD}/${tempurl}${minImg}" data-zoom-image="${RESOURCE_IMG_UPLOAD}/${busiListingSearchVo.listingPic}" alt="${busiListingSearchVo.breedName!''}${busiListingSearchVo.grade}"/>
					                  <#else>
					                  	<img src=""  alt="${busiListingSearchVo.breedName!''}${busiListingSearchVo.grade}"/>
					                  </#if>
								</a>
							</span>
							<h3 class="tit">
								<a href="${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId=${busiListingSearchVo.listingId}" target="_blank">${busiListingSearchVo.title}</a>
							</h3>
							<p class="attr">
								<span>
								等级/规格：
								<i class="col_303030">${busiListingSearchVo.grade}</i>
								</span>
								<span>
									产地：
									<i class="col_303030">${busiListingSearchVo.origin}</i>
								</span>
							</p>
							<p class="bz-box" title="现货保证，药材都在库，保证有现货；&#10;质量保障，药材批批质检，客观如实描述。">
								<img src="${RESOURCE_IMG}/images/xhc.png" style="padding-top:11px;float:left;padding-right:2px;">
								<span style="background: none repeat scroll 0 0 #ffffff;color:#000000;width:50px;">现货仓</span>
							</p>
						</div>
						<div class="wid_2 fl" align="center">
							<p class="tit2">${busiListingSearchVo.warehouseName}</p>
						</div>
						<div class="wid_2 fl" align="center">
							<p class="tit2"><b class="col_red f16">${busiListingSearchVo.listingSurplus} </b>${busiListingSearchVo.wlunitName}</p>
							<p class="attr mt20">${busiListingSearchVo.minSales}${busiListingSearchVo.wlunitName}起订</p>
						</div>
						<div class="wid_2 fl" align="center">
							<p class="tit2">
								<b class="col_red f16">${busiListingSearchVo.price}</b>
								元/${busiListingSearchVo.wlunitName}
							</p>
							<!-- 
							<p class="attr mt20" align="center">
							成交
							<b class="col_red f14">${busiListingSearchVo.dealCount}</b>
							笔
							</p>
							 -->
							<p class="attr mt20" align="center">
							已下单
							<b class="col_red f14">${busiListingSearchVo.ordersCount}</b>
							笔
							</p>
						</div>
					</li>
				</#list>
			</ul>
			<@tools.pages page=page form="sorListForm"/>
		<#else>
        <div class="regret">   
      		<p><i>很抱歉，<s>“<#if keyWords!=''>${keyWords}<#elseif categoryName!="" && breedName==''>${categoryName}<#elseif categoryName!="" && breedName!="">${breedName}</#if>”</s>暂无现货库存</i><br/>
      		<strong>查询最新货源及销售加盟</strong><br/>
      		<strong>请拨打：</strong><span>400-10-54315</span></p>
       </div>
		</#if>
	</div>
</div>
	<div class="area-192 fr">
		<div class="box-1 mt10">
			<h2 class="title-2">热门药材</h2>
			<ul class="img-list1 mt10" id="hotBusiListing">
			</ul>
		</div>
	</div>
</div>
	<!-- 主体over -->
	<!-- list的底部  -->
	<!--footer-->
	<#include "common/listFooter.ftl">
	<!-- 底部 end  -->
	<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/elevatezoom.js"></script>
	<script type="text/javascript">
		//数组移除
		function remove(name,val) {  
		    var index = _indexOf(name,val);  
		    if (index > -1) {  
		        name.splice(index, 1);  
		    }  
		};
		
		//判断数组里是否包含某个元素 add by Mr.song 2015.6.2
		function _indexOf(name,n){
			if("indexOf" in name){
				return name["indexOf"](n);
			}
			for(var i=0;i<name.length;i++){
				if(n===name[i]){
					return i;
				}
			}
			return -1;
		}; 
		
		//不需要刷新当前界面的tips
		function tips(str){
			bghui();
			Alert({
		            str: str,
		            buttons:[{
		            	id:'1',
		                name:'确定',
		                classname:'btn-style',
		                ev:{click:function(data){
		                	 disappear();
	                         $(".bghui").remove();
	                     }
		               }
		            }]
		    });
		}
		//移除某个样式
		function removeClass(){
			$("#breed_class").removeClass('hover');
		}
		
		//日期控件
		$('#date_b').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				maxDate:'#F{$dp.$D(\'date_e\',{d:-1});}',
				readOnly:true
			});
		});
		$('#date_e').click(function(){
			WdatePicker({
				startDate:'%y/%M/%d',
				dateFmt:'yyyy/MM/dd',
				minDate:'#F{$dp.$D(\'date_b\',{d:1});}',
				readOnly:true
			});
		});
		
		//验证价格
		function validatePrice(priceObj){
			var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$");
			for(var key in priceObj){
				if(reg.test(priceObj[key].value)){
			    	continue;
			    }else if(priceObj[key].value==priceObj[key].def){
			    	continue;
			    }else{
			    	tips(priceObj[key].name+'可以是包含2位小数点的非负数!');
			    	return false;
			    }
			}
			//同时不是默认值则，判断大小 
			if(priceObj.b.value!=priceObj.b.def && priceObj.e.value!=priceObj.e.def){
				if(parseFloat(priceObj.b.value)>parseFloat(priceObj.e.value)){
		    		tips('最低价格不能高于最高价格!');
		    		return false;
		    	}
			}
			return true;
		}
		
		////////////////////////////////////////////页头部部分//////////////////////////////////////////////
		//本页品种点击事件
		function breedLinkClick(){
			var breedId=$(this).attr("id");
      		var breedName=$(this).html();
      		$("#searchId").val(breedId);
      		searchBreed(breedId,breedName,'','','');
      		
		}
		//本页类目点击事件
		function categoryLinkClick(){
			var categorysId=$(this).attr("id");
			var categorysName=$(this).attr('value');
			$("#searchId").val(categorysId);
			searchCategory(categorysId,categorysName);
		}
		
		//选中条件，动态的将选中的值放入全局数组中	
		function getCheckValue(a,b){
			//if(a=='')return new Array();
			var name = a.split("^");
            var curName = b.html();
            if(_indexOf(name,curName)!=-1){
            	//如果包含则判断是去除还是添加
            	if(b.hasClass("hover")){
            		remove(name,curName);
            	}else{
            		name.push(curName);
            	}
            }else{
            	//如果不包含则直接添加
            	name.push(curName);
            }
            return name;
		}
	  
		//热门药材
		$.ajax({
			url : "/search/selectHotBusiListing",
			type : 'get',
			dataType : "json",
			success : function(data) {
				var imgPath = $("#imgBasePath").val();
				var resourceImgPath = $("#resourceImg").val();
				for (var i = 0; i < data.length; i++) {
					var path="";
					if (data[i].PATH != null && data[i].PATH != ""){
						var minImg = data[i].PATH.substring((data[i].PATH.lastIndexOf("/")+1),(data[i].PATH.lastIndexOf(".")))+"_120"+data[i].PATH.substring(data[i].PATH.lastIndexOf("."),data[i].PATH.length);
  				  		var tempurl = data[i].PATH.substring(0,data[i].PATH.lastIndexOf("/")+1);
  				  		path = imgPath + "/"+tempurl+minImg;
					}
					//var path = (data[i].PATH == null || data[i].PATH == "")?"":(imgPath + "/" + data[i].PATH);
					var listingId = data[i].LISTINGID;
					var html = "";
					html += "<li>";
					html += "<a href=${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId="
							+ listingId+ "  target='_blank'><img src='"+path+"'  title='"+ data[i].TITLE +"' alt='"+ data[i].TITLE +"'/></a>";
					html += "<h3><a href=${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId="
							+ listingId+ "  target='_blank'>" + data[i].TITLE + "</a></h3>";
					html += "<p>价格：";
					if (data[i].DICT_VALUE != undefined && data[i].LOWUNITPRICE !=undefined){
							html += "<strong>" + data[i].LOWUNITPRICE+ "</strong>元/"+ data[i].DICT_VALUE ;
					}		
					html += "</p></li>";
					$("#hotBusiListing").append(html);
				}
			}
		});
		
		$(function() {
			//搜索输入框提示语
			$('#searchEngineListingText').focus(function(){
				if($(this).val() == "输入名称找药材"){
					$(this).val('');
				}
				}).blur(function(){
					if($(this).val() === ''){
					$(this).val('输入名称找药材');
				}
			});
			
			//绑定筛选按钮
			$('#btn-blue').click(function() {
				var price_b = $('#price_b').val();
				var price_e = $('#price_e').val();
				var date_b = $('#date_b').val();
				var date_e = $('#date_e').val();
				var originText = $('#origin_i').val();
				var searchId = $("#searchId").val();
				searchFilter(searchId,price_b,price_e,originText,date_b,date_e);
			});
			//绑定排序按钮
			$('#paixu li').each(function() {
				$(this).click(function(){
					searchSort($(this));
				});
			});
			//最低最高价格显示
			$('#price_b').focus(function(){
	            if($(this).val() == "￥最低价格"){
	                $(this).val('');
	            }
	        }).blur(function(){
	            if($(this).val() === ''){
	                $(this).val('￥最低价格');
	            }
	        });
	        $('#price_e').focus(function(){
	            if($(this).val() == "￥最高价格"){
	                $(this).val('');
	            }
	        }).blur(function(){
	            if($(this).val() === ''){
	                $(this).val('￥最高价格');
	            }
	        });
		
		//查询判断参数
		var params = {searchType:'KEYWORDS',
				data:{categoryName:'',
					breedName:'',grade:'',warehouseName:'',
					priceRange:[],examineTimeRange:[],origin:'',
					sortListingSurplus:'',sortPrice:'',sortExamineTime:'desc',
					keyWords:''},
				origin:{categoryName:'',
						breedName:'',grade:'',warehouseName:'',
						priceRange:[],examineTimeRange:[],origin:'',
						sortListingSurplus:'',sortPrice:'',sortExamineTime:'desc',
						keyWords:''}};
		var searchType = {category:'CATEGORY',breed:'BREED',keywords:'KEYWORDS'};
			
		//////////////////////////////////////////////////////////搜索相关/////////////////////////////////////////////////////////////
		////////////绑定搜索事件////////////
		$('#searchEngineListingButton').searcher({
			   	onSearch:function() {
					searchKeyWords($('#searchEngineListingText').val());
			    },
				//onHot: '',
				onBreedLink: function(){
					breedLinkClick.call(this);
				},
				onCategoryLink: function(){
					categoryLinkClick.call(this);
				}
		   });
			
			////////////查询方法////////////
			//搜索类目
			function searchCategory(categoryId,categoryName){
				$("#searchAttribute").html('').hide();
				//重置表单
				resetSubmitForm();
				//将对象封装到form中去，然后进行submit进行提交
				setSearchData(searchType.category,{'categoryName':categoryName});
				$("#searchId").val(categoryId);
				//$("#categorys_name").val(categoryName);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				var url = getUrl(searchType.category,{id:categoryId,value:categoryName});
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
			}
			
			//搜索品种
		    function searchBreed(breedId,breedName,grade,warehouseName,origin){
				$("#searchAttribute").html('').hide();
		    	//重置表单
				resetSubmitForm();
				//将对象封装到form中去，然后进行submit进行提交
		    	setSearchData(searchType.breed,{'warehouseName':warehouseName,'breedName':breedName,'grade':grade,'origin':origin});
		    	$("#searchId").val(breedId);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				var url = getUrl(searchType.breed,{id:breedId,value:breedName});
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
			}
			
			//搜索关键字 
			function searchKeyWords(keyWords){
				//如果keyWords为空或者为 “输入名称找药材”为查询所有的内容
				if(''==keyWords||'输入名称找药材'==keyWords){
					keyWords='';
				}
				//重置表单
				resetSubmitForm();
				//将对象封装到form中去，然后进行submit进行提交
				setSearchData(searchType.keywords,{'keyWords':keyWords});
				$("#keyWords").val(keyWords);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				var url = getUrl(searchType.keywords);
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
			}
			
			//筛选
		    function searchFilter(searchId,price_b,price_e,originText,date_b,date_e){
		  		var sType = $("#searchType").val();
		  		//设置url
		  		var url = getUrl(sType);
		  		//设置form数据到param
		  		var srcObj = getParamFromForm();
		  		//重置表单
				resetSubmitForm();
				$("#searchId").val(searchId);
				//价格验证
		    	var priceObj = {b:{def:'￥最低价格',value:price_b,name:'最低价格'}, e:{def:'￥最高价格',value:price_e,name:'最高价格'}};
		    	var url = '';
		    	if(!validatePrice(priceObj)){
		    		return;
		    	}
				srcObj.priceRange=[priceObj.b.value==priceObj.b.def?'*':priceObj.b.value,
						priceObj.e.value==priceObj.e.def?'*':priceObj.e.value];
				srcObj.examineTimeRange=[date_b||'*',date_e||'*'];
				srcObj.originText=originText||'*';
		    	//查询
				setFilterData(srcObj,sType);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
			}
			
		  	//排序
		  	function searchSort($this){
		  		var sType = $("#searchType").val();
		  		var searchId = $("#searchId").val();
		  		//设置url
		  		var url = getUrl(sType);
		  		//设置form数据到param
		  		var srcObj = getParamFromForm();
		  		//获取上一次过滤的值
				var price_b = $('#priceB').val();
				var price_e = $('#priceE').val();
				var date_b = $('#dateB').val();
				var date_e = $('#dateE').val();
				var origin = $('#origin').val();
				//设置上一次过滤的值
		    	var priceObj = {b:{def:'￥最低价格',value:price_b,name:'最低价格'}, e:{def:'￥最高价格',value:price_e,name:'最高价格'}};
				srcObj.priceRange=[priceObj.b.value==priceObj.b.def?'*':priceObj.b.value,
						priceObj.e.value==priceObj.e.def?'*':priceObj.e.value];
				srcObj.examineTimeRange=[date_b||'*',date_e||'*'];
				srcObj.origin=origin||'*';
		  		//重置表单
				resetSubmitForm();
				$("#searchId").val(searchId);
				//排序字段
				if($this.attr('id')=='priceSort'){
					if($this.attr('sort')=='asc'){
						srcObj.sortPrice = 'desc';
						$this.attr('sort','desc');
					}else{
						srcObj.sortPrice = 'asc';
						$this.attr('sort','asc');
					}
				}else if($this.attr('id')=='storeSort'){
					if($this.attr('sort')=='asc'){
						srcObj.sortListingSurplus = 'desc';
						$this.attr('sort','desc');
					}else{
						srcObj.sortListingSurplus = 'asc';
						$this.attr('sort','asc');
					}
				}else if($this.attr('id')=='dateSort'){
					if($this.attr('sort')=='asc'){
						srcObj.sortExamineTime = 'desc';
						$this.attr('sort','desc');
					}else{
						srcObj.sortExamineTime = 'asc';
						$this.attr('sort','asc');
					}
				}
				//切换样式
				//$this.children().removeClass("arrows_gray").addClass("arrows_red"); // 追加样式 
				//$this.children().toggleClass('cur');
				//查询
				setSortData(srcObj,sType);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
		  	}
		  	
		  	/////////////////////////////////////公共方法////////////////////////////////////
		  	//获取URL
			function getUrl(sType,urlParam){
				var url = "/search";
				if(sType==searchType.keywords){
					/* if(urlParam && urlParam.value){
						url = url+"?keyWords="+encodeURI(urlParam.value);
					}else{
						url = url+"?keyWords="+encodeURI($("#keyWords").val());
					} */
				}else if(sType==searchType.category){
					if(urlParam && urlParam.id && urlParam.value){
						url = url+"/category/"+urlParam.id+"?value="+encodeURI(urlParam.value);
					}else{
						url = url+"/category/"+$("#searchId").val()+"?value="+encodeURI($("#categoryName").val());
					}
				}else if(sType==searchType.breed){
					if(urlParam && urlParam.id && urlParam.value){
						url = url+"/category/${cid}/breed/"+urlParam.id+"?value="+encodeURI(urlParam.value);
					}else{
						url = url+"/category/${cid}/breed/"+$("#searchId").val()+"?value="+encodeURI($("#breedName").val());
					}
				}
				return url;
			}
			
			//清空提交的表单的数据
			function resetSubmitForm(){
				$(':input','#sorListForm').val('');
			}

			//设置页面查询需要的所有数据
			function setSearchData(sType,srcObj){
				//将搜索的数据设置到页面上
				setReqestFormData(sType,srcObj);
			}
			//设置页面筛选需要的所有数据
			function setFilterData(srcObj,sType){
				//将搜索的数据设置到页面上
				setReqestFormData(sType,srcObj);
			}
			//设置页面排序需要的所有数据
			function setSortData(srcObj,sType){
				//将搜索的数据设置到页面上
				setReqestFormData(sType,srcObj);
				//记录上一次的拍下操作
				if($('#sortListingSurplus').val()){
					$("#lastSort").val("sortListingSurplus");
				}
				if($('#sortPrice').val()){
					$("#lastSort").val("sortPrice");
				}
				if($('#sortExamineTime').val()){
					$("#lastSort").val("sortExamineTime");
				}
			}
			
			//用设置表单的数据来代替查询的data数据从而进行表单的提交
			function setReqestFormData(sType,srcObj){
				/* if(sType==searchType.category){//类目相关链接 
					$("#categoryName").val(srcObj.categoryName);
				}else if(sType==searchType.breed){//品种相关链接
					$("#breedName").val(srcObj.breedName);
					$("#grade").val(srcObj.grade);
					$("#warehouseName").val(srcObj.warehouseName);
				}else if(sType==searchType.keywords){//关键字查询
					$("#keyWords").val(srcObj.keyWords);
				} */
				//封装基本查询数据
				if(srcObj.categoryName){
					$("#categoryName").val(srcObj.categoryName);
				}
				if(srcObj.breedName){
					$("#breedName").val(srcObj.breedName);
				}
				if(srcObj.grade){
					$("#grade").val(srcObj.grade);
				}
				if(srcObj.warehouseName){
					$("#warehouseName").val(srcObj.warehouseName);
				}
				if(srcObj.origin){
					$("#origin").val(srcObj.origin);
				}
				if(srcObj.keyWords){
					$("#keyWords").val(srcObj.keyWords);
				}
				//封装过滤
				if(srcObj.priceRange){
					//设置最低价格和最低价格
					var priceB=srcObj.priceRange[0];
					$("#priceB").val(priceB);
					var priceE=srcObj.priceRange[1];
					$("#priceE").val(priceE);
				}
				if(srcObj.examineTimeRange){
					var dateB = srcObj.examineTimeRange[0];
					$("#dateB").val(dateB);
					var dateE = srcObj.examineTimeRange[1];
					$("#dateE").val(dateE);
				}
				if(srcObj.originText){
					$("#originText").val(srcObj.originText);
				}
				//封装排序
				if(srcObj.sortListingSurplus){
					$("#sortListingSurplus").val( srcObj.sortListingSurplus);
				}
				if(srcObj.sortPrice){
					$("#sortPrice").val(srcObj.sortPrice);
				}
				if(srcObj.sortExamineTime){
					$("#sortExamineTime").val(srcObj.sortExamineTime);
				}
				//搜索类型
				if(sType){
					$("#searchType").val(sType);
				}
			}
			
			//设置form表单数据到新建的js对象
			function getParamFromForm(){
				var srcObj = {};
				if($("#keyWords").val()){
					srcObj.keyWords = $("#keyWords").val();
				}
				if($("#categoryName").val()){
					srcObj.categoryName = $("#categoryName").val();
				}
				if($("#breedName").val()){
					srcObj.breedName = $("#breedName").val();				
				}
				if($("#grade").val()){
					srcObj.grade = $("#grade").val();
				}if($("#warehouseName").val()){
					srcObj.warehouseName = $("#warehouseName").val();
				}if($("#origin").val()){
					srcObj.origin = $("#origin").val();
				}if($("#originText").val()){
					srcObj.originText = $("#originText").val();
				}
				return srcObj;
			}
			
			//搜索查询
			 var aLink = $('.class-list .breed li a[name = a]');
            var aAll = $('.class-list .breed li a[name = all]');
            var alength = $('.cont2 a').length;
            var ArrayFlag = new Array(alength);
            for(var i = 0; i < alength; i++){
                ArrayFlag[i] = 0;
            }
            aLink.each(function(){
                $(this).on('click',function(){
                	var gradeName = $("#grade").val() != "" ? $("#grade").val().split('^'): new Array();
                	var houseName = $("#warehouseName").val() != "" ? $("#warehouseName").val().split('^'): new Array();
                	var originName =$("#origin").val() != "" ? $("#origin").val().split('^'): new Array();
                	if($(this).hasClass("breed3")){
                		gradeName = getCheckValue($("#grade").val() ,$(this));
                	}else if($(this).hasClass("ware3")){
                		houseName = getCheckValue($("#warehouseName").val() ,$(this));
                	}else if($(this).hasClass("place3")){
                		originName = getCheckValue($("#origin").val() ,$(this));
                	}
                	var a='';
                    var i= $(this).index();
                    if(ArrayFlag[i] == 1)
                    {
                        ArrayFlag[i] = 0;
                        $(this).removeClass('hover');
                        var t =$(this).parents('li').find('a[name=a]');
                    }
                    else if(ArrayFlag[i] == 0)
                    {
                        ArrayFlag[i] = 1;
                        $(this).addClass('hover');

                        var nCount = 0;
                        var t =$(this).parents('li').find('a[name=a]');
                        for (var i = 0; i < t.length; i++) {
                        	 if($(t[i]).hasClass('hover')){
                           		 	a+=$(t[i]).text()+"^";
                           	 }
                            if (ArrayFlag[i] == 1) {
                                nCount++;
                            }
                        }
                      /* if (nCount == $(this).parents('li').find('a[name=a]').length) {
                            for (var i = 0; i < $(this).parents('li').find('a[name=a]').length; i++) {
                                ArrayFlag[i] = 0;
                            }
                        }
                        else{*/
                            $(this).parents('li').find('a[name=all]').removeClass('cur');
                       // }
                    }
                   if($(this).hasClass('breed3')){
	                    if(a.lastIndexOf("^")){
	                    	$("#grade").val(a.substring(0,(a.length-1)));
	                    }
	                }else  if($(this).hasClass('ware3')){
	                    if(a.lastIndexOf("^")){
	                    	$("#warehouseName").val(a.substring(0,(a.length-1)));
	                    }
	                }else  if($(this).hasClass('place3')){
	                    if(a.lastIndexOf("^")){
	                    	$("#origin").val(a.substring(0,(a.length-1)));
	                    }
	                }
	                var breedName = '${breedName}';
					searchBreed($('#searchId').val(),breedName,gradeName.join("^"),houseName.join("^"),originName.join("^"));
                    return true;
                });
            });
            aAll.on('click',function(){
                $(this).addClass('cur');
                $(this).parents('li').find('a[name=a]').removeClass('hover');
                 if($(this).hasClass('breed3')){
	                    $("#grade").val('');
	             }else  if($(this).hasClass('ware3')){
	                    $("#warehouseName").val('');
	            }else  if($(this).hasClass('place3')){
	                    $("#origin").val('');
	            }
	            var breedName = '${breedName}';
	            searchBreed($('#searchId').val(),breedName,$("#grade").val(),$("#warehouseName").val(),$("#origin").val());
                return true;
            })
            
            //放大镜
            $(".price_list li .img img").elevateZoom({
                zoomWindowFadeIn: 300,
                zoomWindowFadeOut: 300,
                lensBorderSize:0,
                zoomWindowWidth: 270,
                zoomWindowHeight: 270
            });
		});
	</script>
	<style>
	.zoomContainer{
		cursor:pointer;
	}
	.WdateFmtErr{
		font-weight:normal;
		color:#474e57;
	}
</style>
</body>
</html>