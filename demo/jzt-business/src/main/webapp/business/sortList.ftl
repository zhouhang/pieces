<%-- <!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>
<#if (keyWords!=null && keyWords!='') || (value!=null && value!='')>
	<#if keyWords!=null && keyWords!=''>
			${keyWords}_${keyWords}批发 - 珍药材
	<#elseif value!=null && value!=''>
			${value}_${value}批发 - 珍药材
	</#if>
<#else>
珍药材 - 全部药材
</#if>
</title>
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
	
<!-- 祥情页主体开始 -->
<div class="area-1200 clearfix">
  <div class="area-998 fl mt10">
    <!--分类展开折叠 strat-->
	<#if keyWords!=null && keyWords!=''>
		<div id="breed" class="class-list">
			<b>您搜索的是 “</b>
			<b>
				<font color="red">${keyWords}</font>
				”
			</b>
		</div>
	</#if>
	<#if searchType=='CATEGORY'>
		<div id="breed" class="class-list" style="display: block;">
			<div id="breedBox-1" class="box-1 up">
				<dl class="clearfix up">
					<dt>${categorys_name}</dt>
						<#if listBreed??>
							<dd>
								<#list listBreed as breed>
									<a id="${breed.BREED_ID}" class="breedLink" href="#" >${breed.BREED_NAME}</a>
								</#list>
							</dd>
						</#if>
				</dl>
			</div>
			<div id="breedOpen-down" class="open-down">
				收起
				<i></i>
			</div>
		</div>
	<#elseif searchType=='BREED'>
		<div id="breedList" class="class-list" >
			<div id="divBreedInfo" class="box-1">
				<ul id="breedcur" class="breed cur">
					<li>
						<span class="tit">
							<strong>${breed.BREED_NAME}</strong>
						</span>
					</li>
					<li class="bn">
						<span class="tit">规格等级：</span>
							<#if breedStandLevelList??>
								<span class="cont">
									<#list breedStandLevelList as breedStandLevel>
										<#if breedStandLevel.ATTR_VALUE=grade>
											<a  class="breed3" style="color:#d61b1b" href="#">${breedStandLevel.ATTR_VALUE}</a>
										<#else>
											<a  class="breed3"  href="#">${breedStandLevel.ATTR_VALUE}</a>
										</#if>
									</#list>
								</span>
							</#if>
					</li>
					<li class="bn">
						<span class="tit">所在仓库:</span>
							<#if breedStandLevelList??>
								<span class="cont">
									<#list listAllWarehouse as warehouse>
										<#if warehouse.WAREHOUSENAME=warehouseName>
											<a class="ware3" style="color:#d61b1b" href="#">${warehouse.WAREHOUSENAME}</a>
										<#else>
											<a class="ware3" href="#">${warehouse.WAREHOUSENAME}</a>
										</#if>
									</#list>
								</span>
							</#if>
					</li>
					<li class="bn">
						<span class="tit">常见产地:</span>
							<#if listBreedPlace ??>
								<span class="cont">
									<#list listBreedPlace as breedPlace>
										<#if breedPlace.ATTR_VALUE=origin>
											<a class="place3" style="color:#d61b1b" href="#">${breedPlace.ATTR_VALUE}</a>
										<#else>
											<a class="place3" href="#">${breedPlace.ATTR_VALUE}</a>
										</#if>
									</#list>
								</span>
							</#if>
					</li>
				</ul>
			</div>
			<div class="open-down">
				<span>展开</span>
				<i></i>
			</div>
		</div>
	<#else>
	</#if>
    <!--分类展开折叠 over-->
    <!--分类展开折叠 strat-->
    <!--
	    <div class="class-list" id="breedList">
	         <div class="box-1" id="divBreedInfo"></div>
		 	 <div  class="open-down"><span>展开</span><i></i></div>
	    </div>
    -->
    <!--分类展开折叠 over-->

	<div class="box-1 mt10">
		<form>
			<div class="price-search">
				<span>价格范围(单价)：</span>
				<input id="price_b" class="gray-price text1" type="text"
					<#if priceB==''||priceB=='*'>
						value="￥最低价格"
					<#else>
						value="${priceB}"
					</#if>
				 />— <input id="price_e" class="gray-price text1" type="text"
					<#if priceE==''||priceE=='*'>
						value="￥最高价格"
					<#else>
						value="${priceE}"
					</#if>	
					 />
					 <span>产地：</span>
					 <input id="origin_i" class="text-price text2" type="text"
						<#if origin==''||origin=='*'> 
							value=""
						<#else>
							value="${origin}"
						</#if>
					  />
					<span>挂牌日期：</span> 
					<input type="text" class="text-price text3 mr10  Wdate" name="date_b" id="date_b" 
						<#if dateB==''||dateB=='*'>
							value=""
						<#else>
							value="${dateB}"
						</#if>
					/>至 
					<input type="text" class="text-price text3 ml10  Wdate" id="date_e" name="date_e"
						<#if dateE==''||dateE=='*'>
							value=""
						<#else>
							value="${dateE}"
						</#if> 
					/>
					<input class="btn-search ml10"
					id="btn-blue" value="筛选" type="button" />
			</div>
		</form>
		<#--用来放置隐藏的表单(用来传递搜索的数据)-->
		<form id="sorListForm" method="post" action="${action}">
			<input   name="categoryName" id="categoryName" type="hidden" value="${categoryName}"/>
			<input   name="breedName" id="breedName" type="hidden" value="${breedName}"/>
			<input   name="grade" id="grade" type="hidden" value="${grade}"/>
			<input   name="warehouseName" id="warehouseName" type="hidden" value="${warehouseName}"/>
			<input   name="priceB" id="priceB" type="hidden" value="${priceB}"/>
			<input   name="priceE" id="priceE" type="hidden" value="${priceE}"/>
			<input   name="dateB" id="dateB" type="hidden" value="${dateB}"/>
			<input   name="dateE" id="dateE" type="hidden" value="${dateE}"/>
			<input   name="origin" id="origin" type="hidden" value="${origin}"/>
			<input   name="sortListingSurplus" id="sortListingSurplus" type="hidden" value="${sortListingSurplus}"/>
			<input   name="sortPrice" id="sortPrice" type="hidden" value="${sortPrice}"/>
			<input   name="sortExamineTime" id="sortExamineTime" type="hidden" value="${sortExamineTime!'desc'}"/>
			<input   name="keyWords" id="keyWords" type="hidden" value="${keyWords}"/>
			<input   name="searchType" id="searchType" type="hidden" value="${searchType}"/>
			<input   name="resultType" id="resultType" type="hidden" value="${resultType}"/>
			<input   name="searchId" id="searchId" type="hidden" value="${searchId}"/><!--传递searchId（有可能是品种id或者是类目id）-->
			<input   name="categorys_name" id="categorys_name" type="hidden" value="${categorys_name}"/><!--点击该品种传递该品种上面的类目的名称-->
			<input   name="lastSort" id="lastSort" type="hidden" value="${lastSort}"/> <!--保存上次搜索排序的类型-->
		</form>
		
		<div class="price-inventory clearfix">
			<span class="text_right fr">总共有 <b id="totalRecordResult"></b> ${page.totalRecord}件商品，共&nbsp;<b id="totalPageResult"></b>&nbsp;${page.totalPage}页
			</span>
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
									<img src="${RESOURCE_IMG_UPLOAD}/${busiListingSearchVo.listingPic}">
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
							<p class="attr mt20" align="center">
							成交
							<b class="col_red f14">${busiListingSearchVo.dealCount}</b>
							笔
							</p>
						</div>
					</li>
				</#list>
			</ul>
			<@tools.pages page=page form="sorListForm"/>
		<#else>
			<div class="ineffectiveness" style="margin-top:60px;">
				<ul>
					<li style="border:none;">
						<h1>很抱歉，没有找到相关的药材！</h1>
					</li>
				</ul>
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

	<!-- 祥情页主体over -->
	<!-- list的底部  -->
	<!--footer-->
	<#include "common/listFooter.ftl">
	<!-- 底部 end  -->
	
	<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/common.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/My97DatePicker/WdatePicker.js"></script>
	<style>
		.zoomContainer{
			cursor:pointer;
		}
	</style>
	<script type="text/javascript" src="${RESOURCE_JS}/js/elevatezoom.js"></script>
	<script type="text/javascript" src="${RESOURCE_JS}/js/Alert.js"></script>
	<script type="text/javascript">
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
		
			//页面的URL参数
			var paramObjs = {id:"${id!''}",type:"${type!''}",value:"${value!''}"};
			
			//查询判断参数
			var params = {searchType:'KEYWORDS',
					resultType:'KEYWORDS',
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
			var resultType = {category:'CATEGORY',breed:'BREED',keywords:'KEYWORDS'};
			var searchType = {category:'CATEGORY',breed:'BREED',keywords:'KEYWORDS'};
			
			//分类展开折叠效果
			
			//$('#breedList').hide();
			$('#breed a').on('click', function() {
				$(this).parents('.class-list').hide();
				$('#breedList').show();
				return false;
			});
			
			//$('.class-list .breed li .cont a').on('click',function(){
				//$(this).addClass('hover');
				//$(this).siblings().removeClass('hover');
			//})
			
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


			//热门药材
			$.ajax({
				url : "/search/selectHotBusiListing",
				type : 'get',
				dataType : "json",
				success : function(data) {
					var imgPath = $("#imgBasePath").val();
					var resourceImgPath = $("#resourceImg").val();
					for (var i = 0; i < data.length; i++) {
						var path = imgPath + "/" + data[i].PATH;
						if (data[i].PATH == null || data[i].PATH == "")
							path = resourceImgPath
									+ "/images/bigpic-1.png";
						var listingId = data[i].LISTINGID;
						var html = "";
						html += "<li>";
						html += "<a href=${JOINTOWNURL}/detail/getBusiGoodsDetail?listingId="
								+ listingId
								+ "  target='_blank'><img src='"+path+"' />";
						html += "<h3>" + data[i].TITLE + "</h3>";
						html += "<p>价格：<strong>" + data[i].LOWUNITPRICE
								+ "</strong>元/" + data[i].DICT_VALUE
								+ "</p>";
						html += "<div class='png'></div></a></li>";
						$("#hotBusiListing").append(html);
					}
				}
			});

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
			
	//****************		
			//绑定筛选按钮
			$('#btn-blue').click(function() {
				var price_b = $('#price_b').val();
				var price_e = $('#price_e').val();
				var date_b = $('#date_b').val();
				var date_e = $('#date_e').val();
				var origin = $('#origin_i').val();
				//resetSubmitForm();
				searchFilter(price_b,price_e,origin,date_b,date_e);
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
			
			////////////查询方法////////////
			
			//搜索类目
			function searchCategory(categoryName){
				$("#breed").html('').hide();
				//存储类目名称
				var data = {};
				var searchId = $("#searchId").val();
				resetSubmitForm();
				$("#searchId").val(searchId);
				setSearchData(searchType.category,{'categoryName':categoryName},data);
				//listingTable.refresh('/search/link',data,'POST');
				//修改后为通过关键字进行刷新页面分页,跳转到/search/link页面再返回到本页面进行刷新
				//data为封装的搜索参数
				//将data封装到form中去，然后进行submit进行提交
				$("#categorys_name").val(categoryName);
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action","/search/link");
				$("#sorListForm").submit();
			}
			//搜索品种
		    function searchBreed(breedName,grade,warehouseName,origin){
				$("#breed").html('').hide();
		    	//存储品种相关名称
		    	var data = {};
		    	var searchId = $("#searchId").val();
				resetSubmitForm();
				$("#searchId").val(searchId);
		    	setSearchData(searchType.breed,{'warehouseName':warehouseName,'breedName':breedName,'grade':grade,'origin':origin},data);
				//listingTable.refresh('/search/link',data,'POST');
				//data为封装的搜索参数
				//将data封装到form中去，然后进行submit进行提交
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action","/search/link");
				$("#sorListForm").submit();
			}
			//搜索关键字 
			function searchKeyWords(keyWords){
				var data = {};
				//如果keyWords为空或者为 “输入名称找药材”为查询所有的内容
				if(''==keyWords||'输入名称找药材'==keyWords){
					keyWords='';
				}
				resetSubmitForm();
				setSearchData(searchType.keywords,{'keyWords':keyWords},data);
				//原来的搜索关键字然后进行分页
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action","/search/keyWords");
				$("#keyWords").val(keyWords);
				$("#sorListForm").submit();
			}
		  	//筛选

		    function searchFilter(price_b,price_e,origin,date_b,date_e){
		    	var data = {};
		    	var srcObj = {};
		  		var formSearchType = $("#searchType").val();
		  		//设置form数据到param
		  		srcObj = setFormDataToParam();
		  		//重设js参数
		  		setParamsData(srcObj);
		  		
		  		
		    	var searchId = $("#searchId").val();
				resetSubmitForm();
				$("#searchId").val(searchId);
				
		    	var priceObj = {b:{def:'￥最低价格',value:price_b,name:'最低价格'}, e:{def:'￥最高价格',value:price_e,name:'最高价格'}};
		    	var url = '';
		    	if(!validatePrice(priceObj)){
		    		return;
		    	}
				srcObj.priceRange=[priceObj.b.value==priceObj.b.def?'*':priceObj.b.value,
						priceObj.e.value==priceObj.e.def?'*':priceObj.e.value];
				srcObj.examineTimeRange=[date_b||'*',date_e||'*'];
				srcObj.origin=origin||'*';
		    	//查询
				setFilterData(srcObj,data,formSearchType);
				if(formSearchType=="KEYWORDS"){
					url = '/search/keyWords';
				}else{
					url = '/search/link';
				}
				//listingTable.refresh(url, data, 'POST');
				//原来的搜索关键字然后进行分页
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action",url);
				
				$("#sorListForm").submit();

			}
		  	//排序
		  	function searchSort($this){
		  		var srcObj = {};
		  		var data = {};
		  		//设置form数据到param
		  		srcObj = setFormDataToParam();
		  		//重设js参数
		  		setParamsData(srcObj);
		  		//setFormDataToParamForFilter(srcObj);
		  		//获取上一次过滤的值
		  		var formSearchType = $("#searchType").val();
				var price_b = $('#priceB').val();
				var price_e = $('#priceE').val();
				var date_b = $('#dateB').val();
				var date_e = $('#dateE').val();
				var origin = $('#origin').val();
				//设置上一次过滤的值
				var srcObj = {};
		    	var priceObj = {b:{def:'￥最低价格',value:price_b,name:'最低价格'}, e:{def:'￥最高价格',value:price_e,name:'最高价格'}};
				srcObj.priceRange=[priceObj.b.value==priceObj.b.def?'*':priceObj.b.value,
						priceObj.e.value==priceObj.e.def?'*':priceObj.e.value];
				srcObj.examineTimeRange=[date_b||'*',date_e||'*'];
				srcObj.origin=origin||'*';
				//清空form
		  		var searchId = $("#searchId").val();
				resetSubmitForm();
				$("#searchId").val(searchId);
				var url = '';
				//排序字段
				if($this.attr('id')=='priceSort'){
					resetSort(true);
					if($this.attr('sort')=='asc'){
						srcObj.sortPrice = 'desc';
						$this.attr('sort','desc');
					}else{
						srcObj.sortPrice = 'asc';
						$this.attr('sort','asc');
					}
				}else if($this.attr('id')=='storeSort'){
					resetSort(true);
					if($this.attr('sort')=='asc'){
						srcObj.sortListingSurplus = 'desc';
						$this.attr('sort','desc');
					}else{
						srcObj.sortListingSurplus = 'asc';
						$this.attr('sort','asc');
					}
				}else if($this.attr('id')=='dateSort'){
					resetSort(true);
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
				setSortData(srcObj,data,formSearchType);
				if(formSearchType=="KEYWORDS"){
					url = '/search/keyWords';
				}else{
					url = '/search/link';
				}
				//listingTable.refresh(url, data, 'POST');
				//原来的搜索关键字然后进行分页
				//修改后为通过关键字进行刷新页面分页,跳转到keywords页面再返回到本页面进行刷新
				$("#sorListForm").attr("action",url);
				$("#sorListForm").submit();
		  	}
		  	
			////////////公共方法////////////
			//清空提交的表单的数据
			function resetSubmitForm(){
				$(':input','#sorListForm').val('');
			}

			//设置页面查询需要的所有数据
			function setSearchData(sType,srcObj,data){
				//清空提交表单的数据（先清空）
				//resetSubmitForm();
				resetFilter();
				resetSort();
				resetParamsData();
				setSearchType(sType);
				setParamsData(srcObj);
				setReqestFormData(sType);
			}
			//设置页面筛选需要的所有数据
			function setFilterData(srcObj,data,sType){
				//清空提交表单的数据（先清空）
				//resetSubmitForm();
				resetSort();
				setParamsData(srcObj);
				//setReqestData(data,params.searchType);(老方法)
				//将搜索的数据设置到页面上
				setReqestFormData(sType);
			}
			//设置页面排序需要的所有数据
			function setSortData(srcObj,data,sType){
				//清空提交表单的数据（先清空）
				//var lastSort = $("#lastSort").val();
				//resetSubmitForm();
				setParamsData(srcObj);
				//setReqestData(data,params.searchType);
				setReqestFormData(sType);
				//上次点击为空，表示是第一次进行点击
				//if(lastSort==''){
					//封装排序
				//本次点击事件
				if(params.data.sortListingSurplus){
					$("#lastSort").val("sortListingSurplus");
				}
				if(params.data.sortPrice){
					$("#lastSort").val("sortPrice");
				}
				if(params.data.sortExamineTime){
					$("#lastSort").val("sortExamineTime");
				}
				//}
			}
			
			//用设置表单的数据来代替查询的data数据从而进行表单的提交
			function setReqestFormData(sType){
				if(sType==searchType.category){//类目相关链接 
					$("#categoryName").val(params.data.categoryName);
				}else if(sType==searchType.breed){//品种相关链接
					$("#breedName").val(params.data.breedName);
					$("#grade").val(params.data.grade);
					$("#warehouseName").val(params.data.warehouseName);
				}else if(sType==searchType.keywords){//关键字查询
					$("#keyWords").val(params.data.keyWords);
				}
				//封装排序
				if(params.data.sortListingSurplus){
					$("#sortListingSurplus").val( params.data.sortListingSurplus);
				}if(params.data.sortPrice){
					$("#sortPrice").val(params.data.sortPrice);
				}if(params.data.sortExamineTime){
					$("#sortExamineTime").val(params.data.sortExamineTime);
				}
				
				//封装过滤
				if(params.data.priceRange){
					//设置最低价格和最低价格
					var priceB=params.data.priceRange[0];
					$("#priceB").val(priceB);
					var priceE=params.data.priceRange[1];
					$("#priceE").val(priceE);
				}if(params.data.examineTimeRange){
					var dateB = params.data.examineTimeRange[0];
					$("#dateB").val(dateB);
					var dateE = params.data.examineTimeRange[1];
					$("#dateE").val(dateE);
				}if(params.data.origin){
					$("#origin").val(params.data.origin);
				}
				//搜索类型
				if(sType){
					$("#searchType").val(sType);
				}
			}
			
			
			//设置当前搜索参数数据
			function setParamsData(obj){
				if(obj.keyWords){
					params.data.keyWords = obj.keyWords;
				}
				if(obj.categoryName){
					params.data.categoryName = obj.categoryName;
				}
				if(obj.breedName){
					params.data.breedName = obj.breedName;				
				}
				if(obj.grade){
					params.data.grade = obj.grade;
				}if(obj.warehouseName){
					params.data.warehouseName = obj.warehouseName;
				}
				if(obj.priceRange){
					params.data.priceRange = obj.priceRange;
				}if(obj.examineTimeRange){
					params.data.examineTimeRange = obj.examineTimeRange;
				}if(obj.origin){
					params.data.origin = obj.origin;
				}
				if(obj.sortListingSurplus){
					params.data.sortListingSurplus = obj.sortListingSurplus;
				}if(obj.sortPrice){
					params.data.sortPrice = obj.sortPrice;
				}if(obj.sortExamineTime){
					params.data.sortExamineTime = obj.sortExamineTime;
				}
			}
			//设置搜索类型
			function setSearchType(sType){
				params.searchType = sType;
				$("#searchType").val(sType);
				//var type=$("#searchType").val();
			}
			
			//设置搜索结果类型
			function setResultType(rType){
				params.resultType = rType;
				$("#resultType").val(rType);
			}
			
			
			
			function setFormDataToParam(){
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
				}
				return srcObj;
			}
			
			function setFormDataToParamForFilter(srcObj){
				
			}
			
			
			
			//重置排序数据
			function resetSort(isClear){
				//
				/*if(isClear){
					$('#priceSort').children().removeClass('arrows_red').removeClass('arrows_gray').addClass("arrows_gray");
					$('#storeSort').children().removeClass('arrows_red').removeClass('arrows_gray').addClass("arrows_gray");
					$('#dateSort').children().removeClass('arrows_red').removeClass('arrows_gray').addClass("arrows_gray");
				}else{
					$('#priceSort').children().removeClass('arrows_gray').removeClass('arrows_red').addClass("arrows_gray");
					$('#storeSort').children().removeClass('arrows_gray').removeClass('arrows_red').addClass("arrows_gray");
					$('#dateSort').children().removeClass('arrows_gray').removeClass('arrows_red').addClass("arrows_red");
					//.toggleClass('cur');
				}*/
				params.data.sortPrice = isClear?'':params.origin.sortPrice;
				params.data.sortListingSurplus = isClear?'':params.origin.sortListingSurplus;
				params.data.sortExamineTime = isClear?'':params.origin.sortExamineTime;
			}
			//重置筛选数据
			function resetFilter(){
				params.data.priceRange = [];
				params.data.examineTimeRange = [];
				params.data.origin = '';
			}
			//重置params数据
			function resetParamsData(){
				for(var key in params.data){
					params.data[key] = '';
				}
			}
			
			
			
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
			
			function tips(str){
				bghui();
				Alert({
			            str: str,
			            buttons:[{
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
			
			
			////////////////////////////////////////////页头部部分//////////////////////////////////////////////
			
			//本页品种点击事件
			function breedLinkClick(){
				resetSubmitForm();
				var breedId=$(this).attr("id");
	      		var breedName=$(this).html();
	      		var categorys_name=$(this).parent().prev().html();
	      		$("#searchId").val(breedId);
	      		searchBreed(breedName,'','','');
	      		
			}
			
			//本页类目点击事件
			function categoryLinkClick(){
				resetSubmitForm();
				var categorysId=$(this).attr("id");
				var categorysName=$(this).attr('value');
				$("#searchId").val(categorysId);
				$("#categorys_name").val(categorysName);
				searchCategory(categorysName);
				
			}
			
			$("#breedList").delegate("a","click",function(){
				//$(this).addClass('hover').siblings().removeClass('hover');
				$(this).css('color','#d61b1b').siblings().css('color','');
 				var aclass=$(this).attr("class");
 				if("breed3"==aclass){
 					var breedName = $(this).parent().parent().prev().find("strong").html();
					var grade =$(this).html();
					//alert(breedName+"  "+grade);
					searchBreed(breedName,grade,'','');
 				}else if("ware3"==aclass){
 					var breedName=$(this).parent().parent().prev().prev().find("strong").html();
					var warhouseName=$(this).html();
					//alert(breedName+"  "+warhouseName);
					searchBreed(breedName,'',warhouseName,'');
 				}else if("place3"==aclass){
 					var breedName=$(this).parent().parent().prev().prev().prev().find("strong").html();
					var breedPlace=$(this).html();
					//alert(breedName+"  "+breedPlace);
 					searchBreed(breedName,'','',breedPlace);
 				}
			});
			
			
			
			
			////////////////////////////////////////////页面初始化部分//////////////////////////////////////////////
			//页面初始化方法,根据不同类型请求不同搜索
			function init(paramsObject){
				if(paramsObject.type=='KEYWORDS'){
					$('#searchEngineListingButton').click();
				}else if(paramsObject.type=='CATEGORY'){
					$('a.categorysLink').filter('#'+paramsObject.id).click();
				}else if(paramsObject.type=='BREED'){
					$('a.breedLink').filter('#'+paramsObject.id).click();
				}
			}
			//调用页面初始化
			init(paramObjs);
			
			//详情页-放大镜
			$(".price_list li .img img").elevateZoom({
				zoomWindowFadeIn : 300,
				zoomWindowFadeOut : 300,
				lensBorderSize : 0,
				zoomWindowWidth : 270,
				zoomWindowHeight : 270
			});
			
		});
		
		
	</script>
</body>
</html> --%>