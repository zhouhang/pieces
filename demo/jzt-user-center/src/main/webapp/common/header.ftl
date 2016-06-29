<#setting url_escaping_charset='utf8'> 
<script type="text/javascript" src="${RESOURCE_JS}/js/search.js"></script>
<script type="text/javascript" src="${RESOURCE_JS}/js/jzt-user-center/public.js"></script>
<!-- 头部  -->
 <!-- 最顶端内容，包括用户名、导航等内容 -->
<div class="topper clearfix">
        <div class="area-1200">
            <p class="topleft fl">
            	<#if ucUser??>
		        	<span id="u"><span class="pr20">您好，${(ucUser.userName)!'' }</span> 欢迎来到珍药材！ <a href="${brokerServer.passport}/logout?service=${JOINTOWNURL}/logout">[退出]</a></span>
		       <#else>
		       		<span id="u"><span class="pr20">您好，游客</span> 欢迎来到珍药材！ <a href="/login">请登录</a>|<a href="${URL_SERVER_PREFIX_UC}${URL_REGISTER_UC}">免费注册</a></span>
		       </#if>
		       		<a href="${JOINTOWNURL}">珍药材首页</a>
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

<!--新的搜索框-->
<!--
<div class="area-1200 header clearfix">
	<div class="logo fl">
	    <a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
	</div>
	<div class="search fl">
	    <ul>

	    </ul>
	    <div class="text-bg clearfix relative">
		    <#if keyWords??>
	    		<input id="searchEngineListingText" type="text" class="search-text" value="输入名称找药材"/><input id="searchEngineListingButton" type="button" class="search-btn" value="搜 索" />
	    	<#else>
	    		<input id="searchEngineListingText" type="text" class="search-text" value="${keyWords!''}"/><input id="searchEngineListingButton" type="button" class="search-btn" value="搜 索" />
	    	</#if>
	        <div class="match-bg"></div>
	    </div>
	    <p>
	        <a href="#" class="breedHost">三七</a><a href="#" class="breedHost">当归
	    </a><a href="#" class="breedHost">人参
	    </a><a href="#" class="breedHost">麦冬
	    </a><a href="#" class="breedHost">红枣
	    </a><a href="#" class="breedHost">党参
	    </a><a href="#" class="breedHost">黄连
	    </a><a href="#" class="breedHost">玛咖
	    </a><a href="#" class="breedHost">金银花
	    </a><a href="#" class="breedHost">枸杞子
	    </a><a href="#" class="breedHost">山药
	    </a><a href="#" class="breedHost">山楂
	    </a><a href="#" class="breedHost">板蓝根
	    </a><a href="#" class="breedHost">白芍
	    </a><a href="#" class="breedHost">防风
	    </a><a href="#" class="breedHost">川芎</a>
	    </p>
	</div>
	<div class="fr mt20">
	    <img src="${RESOURCE_IMG}/images/top-saoma.png" usemap="#top-saoma" border="0" />
	    <map name="Map" id="top-saoma">
	        <area shape="rect" coords="108,-4,129,18" href="#" />
	    </map>
	</div>
</div>
-->


<!--老的的搜索框-->

<div class="hy-top">
    <div class="area-1200">
        <div class="logo fl"><a href="/memberHome/index">我的珍药材</a></div>
        <div class="search fr">
            <ul>
             <!--   <li class="sort">药材 <i class="arrow"></i></li>
                <li><input type="text" class="search-text" id="searchText" value="请输入搜索内容" /></li>
                <li class="btn"><input type="button" class="search-btn"  id="searchBtn"/></li>-->
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function() {
		/**head头搜索按钮对应的搜索功能  by Mr.song 2015.3.31
		$('#searchBtn').click(function(){
			window.location.href='${JOINTOWNURL}/search?keyWords='+$('input[type="text"].search-text').val();
		});
		/**顶部菜单弹层 start by Mr.song 2015.3.31**/
	    function hoverer(id){
	        $(id).hover(
	            function(){
	                $(this).children('span').addClass('cur1');
	                $(this).children().children('i').addClass('cur');
	                $(this).children('.sub').show();
	            },
	            function(){
	                $(this).children('span').removeClass('cur1');
	                $(this).children('span').children('i').removeClass('cur');
	                $(this).children('.sub').hide();
	            })
	    }
	    hoverer('#myZYC,#Service,#QA,#webNav');
		/**顶部菜单弹层 end by Mr.song 2015.3.31 ****/
	});
	
</script>
<!-- 头部 end  -->