<#setting url_escaping_charset='utf8'>
<!--topper strat-->
<!-- topAndSearch start -->
<div id="topper">
	<!-- topest start -->
    <div class="topper clearfix">
        <div class="area-1200">
            <p class="topleft fl">
            	<#if ucUser??>
		        	<span id="u"><span class="pr20">您好，${(ucUser.userName)!'' }</span> 欢迎来到珍药材！ <a href="${brokerServer.passport}/logout?service=${JOINTOWNURL}/logout">[退出]</a></span>
		       		<input type="hidden" id="traceUserId" value="${(ucUser.userId)!'' }" />
		       <#else>
		       		<span id="u"><span class="pr20">您好，游客</span> 欢迎来到珍药材！ <a href="/login">请登录</a>|<a href="${URL_SERVER_PREFIX_UC}${URL_REGISTER_UC}">免费注册</a></span>
		       </#if>
            </p>
            <ul class="topright fr">
                <li class="relative" id="myZYC"><span><a href="${URL_SERVER_PREFIX_UC}/memberHome/index">我的珍药材<i class="arrow"></i></a></span>
                    <div class="sub first">
                        <a href="${URL_SERVER_PREFIX_UC}/order/listinfo">买方订单</a>
                		<a href="${URL_SERVER_PREFIX_UC}/order/getSellOrderList">卖方订单</a>
                		<a href="${URL_SERVER_PREFIX_UC}/listing/manager">我的挂牌</a>
                		<a href="${URL_SERVER_PREFIX_UC}/purchase/pub">我的采购</a>
                		<a href="${URL_SERVER_PREFIX_UC}/collect/queryMyCollect">收藏夹</a>
                    </div>
                </li>
                <li class="relative" id="Service"><span class="bl"><i class="service"></i>平台服务<i class="arrow"></i></span>
                    <div class="sub">
                        <a href="${URL_PREFIX_HELP}/view-1970233306-7837069404.html" target="_blank">挂牌销售</a>
                        <a href="${JOINTOWNURL}/purchaseSearch/list" target="_blank">药材采购</a>
                        <a href="${JOINTOWNURL}/index/getBankingService" target="_blank">药材金融</a>
                        <a href="${JOINTOWNURL}/index/getStockService" target="_blank">仓储服务</a>
                    </div>
                </li>
                <li><a href="${JOINTOWNURL}/index/getQualitySystem" class="bl">质量保障体系</a></li>
                <li class="relative" id="QA"><span class="bl"><i class="qa"></i>帮助中心<i class="arrow"></i></span>
                    <div class="sub">
                        <a href="${URL_PREFIX_HELP}/list-4772646578.html" target="_blank">新手入门</a>
                        <a href="${URL_PREFIX_HELP}/view-1222808050-4277461942.html" target="_blank">采购商帮助</a>
                        <a href="${URL_PREFIX_HELP}/view-2011458294-7859114524.html" target="_blank">供应商帮助</a>
                        <a href="http://crm2.qq.com/page/portalpage/wpa.php?uin=4001054315&aty=0&a=0&curl=&ty=1" target="_blank">在线咨询</a>
                        <a href="${URL_PREFIX_HELP}/list-7362477534.html" target="_blank">联系客服</a>
                    </div>
                </li>
                <li class="relative" id="webNav"><span  class="bl cur2"><i class="menu"></i>网站导航<i class="arrow"></i></span>
                    <div class="sub webnav">
                        <dl>
                            <dt>入门指导</dt>
                            <dd><a href="${URL_PREFIX_HELP}/list-4772646578.html" target="_blank">新手入门
                            </a><a href="${URL_PREFIX_HELP}/view-1222808050-4277461942.html" target="_blank">采购商帮助
                            </a><a href="${URL_PREFIX_HELP}/view-2011458294-7859114524.html" target="_blank">供应商帮助</a>
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
                            <dd><a href="${URL_PREFIX_HELP}/view-1970233306-7837069404.html" target="_blank">挂牌销售</a>
		                        <a href="${JOINTOWNURL}/purchaseSearch/list" target="_blank">药材采购</a>
		                        <a href="${JOINTOWNURL}/index/getBankingService" target="_blank">药材金融</a>
		                        <a href="${JOINTOWNURL}/index/getStockService" target="_blank">仓储服务</a>
                            </dd>
                        </dl>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- topest end -->
    <div class="area-1200 header clearfix">
        <div class="logo fl">
            <a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
        </div>
        <div class="search fl">
            <div class="text-bg clearfix relative">
                <#if keyWords??>
		    		<input type="text" class="search-text" id="Search" value="${keyWords!''}"  onkeypress="if(event.keyCode==13){Javascript:formSubmit()}" /><input  id="searchBtn" type="button" class="search-btn" value="搜 索" />
		    	<#else>
                	<input type="text" class="search-text" id="Search" value="输入名称找药材"  onkeypress="if(event.keyCode==13){Javascript:formSubmit()}" /><input  id="searchBtn" type="button" class="search-btn" value="搜 索" />
		    	</#if>
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
        <div class="sendstock fl ml10"><a href="http://uc.54315.com/purchase/pub"><i class="dis-in-bk"></i>发布采购需求</a></div>
        <div class="fr kf-phone"></div>
        <div class="clearfix" style="*height: 0; *overflow: hidden;"></div>
    </div>
</div>
<!-- topAndSearch end -->
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