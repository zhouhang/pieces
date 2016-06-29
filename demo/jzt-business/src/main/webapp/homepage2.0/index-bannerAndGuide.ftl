<!-- 头部滑动搜索框 -->
<div class="area-956 relative fl">
        <div class="box-1 mt10">
            <!--焦点图 开始-->
            <div class="ck-slide">
                <ul class="ck-slide-wrapper">
	                <#assign n = 0 />
	            	<#assign adCount = admap?size/>
	           		<#list admap as a>
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
                            <#list admap as a>
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
            <!--焦点图 over-->
            <div class="active-box relative mt10">
                <a class="ctrl-slide prev" href="#"></a>
                <a class="ctrl-slide next" href="#"></a>
                <div class="img-list">
                    <ul>
                    <#if adSpacialList??>
	                    <#list adSpacialList as ads>
	                        <li><a href="${ads.url!'' }" title="${ads.alt!'' }" target="_blank"><img src="${ads.picurl!'' }" alt="${ads.alt!'' }"/></a></li>
	                    </#list>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    
    <div class="area-230  fr">
    	<#if ucUser??>
    		<p class="leecr">您好，欢迎来到54315.com！</p>
    	<#else>
	        <p class="oprate mt10">
	            <a href="http://uc.54315.com/getUcUserRegister" class="fl"><i class="register"></i>免费注册</a>
	            <a href="${JOINTOWNURL}/login?go=http://uc.54315.com" class="fr"><i class="login"></i>立即登录</a>
	        </p>
        </#if>
        <div class="border-1 ">
           <!--未登录-->

            <!--未登录end-->
            <!--登录以后-->
            <!--<div>
            	<p class="leecr">您好，欢迎来到54315.com！</p>
            </div>-->
            <!--登录以后end-->
            <ul class="tabs-1 clearfix" id="tabs1">
                <li class="cur">买家采购指引</li>
                <li>卖家销售指引</li>
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

            <div class="assured">
                <i class="dis-in-bk xh" title="现货保障">现货保障</i>
                <i class="dis-in-bk zl" title="质量保障">质量保障</i>
                <i class="dis-in-bk jy" title="放心交易">放心交易</i>
            </div>
        </div>

        <div class="goods mt10">
            <h2 class="title">仓库现货</h2>
            <ul class="clearfix">
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E4%BA%B3%E5%B7%9E&mode=WAREHOUSE">亳州大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['亳州大区']}<#else>0</#if>吨</span>
                </li>
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E5%AE%89%E5%9B%BD&mode=WAREHOUSE">安国大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['安国大区']}<#else>0</#if>吨</span>
                </li>
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E9%99%87%E8%A5%BF&mode=WAREHOUSE">陇西大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['陇西大区']}<#else>0</#if>吨</span>
                </li>
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E7%8E%89%E6%9E%97&mode=WAREHOUSE">玉林大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['玉林大区']}<#else>0</#if>吨</span>
                </li>
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E6%88%90%E9%83%BD&mode=WAREHOUSE">成都大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['成都大区']}<#else>0</#if>吨</span>
                </li>
                <li>
                    <i class="dis-in-bk icon"></i><a target="_blank" href="http://www.54315.com/search?keyWords=%E6%AD%A6%E6%B1%89&mode=WAREHOUSE">石柱大区</a><br/>
                    <span class="col_888"><#if bigAreaTunage??>${bigAreaTunage['石柱大区']}<#else>0</#if>吨</span>
                </li>
            </ul>
        </div>
    </div>