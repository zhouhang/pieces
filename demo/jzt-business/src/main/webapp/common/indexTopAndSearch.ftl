<#setting url_escaping_charset='utf8'> 
<!-- 该文件包括页面最顶端top,LOGO,搜索框内容 ，发布采购按钮-->
<div id="topper">
	<!-- topest start -->
    <#include "common/indexTop.ftl"/>
    <!-- topest end -->
    <div class="area-1200 header clearfix">
        <div class="logo fl">
            <a href="${JOINTOWNURL}">聚好药商，卖真药材</a>
        </div>
        <div class="search fl h-search">
            <div class="text-bg clearfix relative">
                <#if keyWords??>
		    		<input type="text" class="search-text" id="Search" value="<#if (keyWords?length gt 0)>${keyWords}<#else>输入名称找药材</#if>" /><input  id="searchBtn" type="button" class="search-btn" value="搜 索" />
		    	<#else>
                	<input type="text" class="search-text" id="Search" value="输入名称找药材"/><input  id="searchBtn" type="button" class="search-btn" value="搜 索" />
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
