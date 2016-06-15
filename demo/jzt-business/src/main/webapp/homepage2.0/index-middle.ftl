<#setting url_escaping_charset='utf8'> 
<!-- 首页二期  包含珍药现货  珍药采购  -->

<!---------------------------  _(:зゝ∠)_我是【珍药现货】 START --------------------------- -->
	<h2 class="title1 bor1 mt20">
            <a target="_blank" href="http://www.54315.com/search" class="fr">进入现货频道 >></a>
           	 珍药现货
    </h2>
    
    
    <div class="area-968 fl">
    		<!-- 现货直销 -->
            <#if sellingAdds ??>
	          <ul class="ad-list1 clearfix">
            	<#list	sellingAdds as sell>
	            		<li><a href="${sell.url!''}" target="_blank"><img src="${sell.picurl!''}" alt="${sell.alt!''}"/></a></li>
            	</#list>
	          </ul>
            </#if>
            <!-- 现货直销 -->
			
			<!-- 大户资源 -->
            <div class="area-192 fl">
                <div class="border-1 bt-none">
                    <h2 class="title2 sty1">大户<span>资源</span></h2>
                    <ul class="pro-list1 clearfix">
                    <#list listingData['20'] as r>
                    	<li>
                    		<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${r.listingid!''}" title="${r.alt!'' }" target="_blank">
                            <h3 class="clearfix"><i class="dis-in-bk"></i><span title="${r.username!''}">${r.username!''}</span></h3>
                            <span><img src="${r.picurl!''}" /></span>
                            <p><i>${r.name!''}</i><br/>
                   			${r.grade!''}<br/>
                            <span class="col_red">${r.price!''}${r.dictvalue!''}</span></p></a>
                        </li>
                    </#list>	
                    </ul>
                </div>
            </div>
			<!-- 大户资源 -->
			
           	<div class="area-776 fl relative">
                <h2 class="title2 sty1">
                    <span class="fr subs">
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E4%BA%B3%E5%B7%9E&mode=WAREHOUSE">亳州</a>|
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E5%AE%89%E5%9B%BD&mode=WAREHOUSE">安国</a>|
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E9%99%87%E8%A5%BF&mode=WAREHOUSE">陇西</a>|
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E7%8E%89%E6%9E%97&mode=WAREHOUSE">玉林</a>|
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E6%88%90%E9%83%BD&mode=WAREHOUSE">成都</a>|
                    	<a target="_blank" href="http://www.54315.com/search?keyWords=%E6%AD%A6%E6%B1%89&mode=WAREHOUSE">石柱</a></span>
                                               全国<span>大仓</span></h2>
                <div class="thbg dacang">
                    <ul class="conts-5">
                    	<li class="bn"><span class="wid1">品种</span>
                            <span class="wid2">规格等级</span>
                            <span class="wid3">产地</span>
                            <span class="wid4">价格</span>
                            <span class="wid5">库存量</span>
                            <span class="wid6">所在仓库</span>
                            <span class="wid7" id="Tab5"><a href="#" class="left"></a><a href="#" class="right"></a></span></li>
                    </tr>
                </div>
                
                <div id="Conts5">
                	<#assign n = 0 />
                	<ul class="conts-5"  style="display:block;">
                <#if bigWarehouse??>
                    <#list bigWarehouse as b>
                    	<#assign n = b_index>
                    	<#if n gt 8><#break></#if>
                    	<li <#if n==8>class="bn"</#if>>
	                        <span class="wid1">${b.name!''}</span>
	                        <span class="wid2">
	                        	<#if (b.grade?length) gt 4>
	                        		${b.grade?substring(0,4)!''}${b.grade?substring(4 , b.grade?length)?replace(b.grade?substring(4,b.grade?length),"..")!''}
	                        	<#else>
	                        		${b.grade!''}
	                        	</#if>
	                        </span>
	                        <span class="wid3">
	                        <#if (b.origin?length) gt 4>
	                        	${b.origin?substring(0,4)!''}${b.origin?substring(4 , b.origin?length)?replace(b.origin?substring(4,b.origin?length),"..")!''}
	                        <#else>
	                        	${b.origin!''}
	                        </#if>
	                        </span>
	                        <span class="wid4">${b.price!''}元/${b.dictvalue!''}</span>
	                        <span class="wid5">${b.stockAmount!''}${b.dictvalue!''}</span>
	                        <span class="wid6">${b.inWarehouse!''}</span>
	                        <span class="wid7"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${b.listingid!''}" target="_blank" class="col_yellow">详细</a></span>
                    	</li>
                    </#list>
                    </ul>
                   <!-- 由于样式问题 需要做数据拆分显示  0-8 9-17 -->
                   <#if n gte 8>
                   <ul class="conts-5">
	                   <#list bigWarehouse as b>
	                   	 <#if n gt bigWarehouse?size><#break></#if>
	                   	  	<!-- 遍历 9 - 17条数据 -->
	                   	 	<#list bigWarehouse[n] as b>
		                   	 	<li <#if n=17>class="bn"</#if>>
			                        <span class="wid1">${b.name!''}</span>
			                        <span class="wid2">
			                        	<#if (b.grade?length) gt 4>
			                        		${b.grade?substring(0,4)!''}${b.grade?substring(4 , b.grade?length)?replace(b.grade?substring(4,b.grade?length),"..")!''}
			                        	<#else>
			                        		${b.grade!''}
			                        	</#if>
			                        </span>
			                        <span class="wid3">
			                        <#if (b.origin?length) gt 4>
			                        	${b.origin?substring(0,4)!''}${b.origin?substring(4 , b.origin?length)?replace(b.origin?substring(4,b.origin?length),"..")!''}
			                        <#else>
			                        	${b.origin!''}
			                        </#if>
			                        </span>
			                        <span class="wid4">${b.price!''}元/${b.dictvalue!''}</span>
			                        <span class="wid5">${b.stockAmount!''}${b.dictvalue!''}</span>
			                        <span class="wid6">${b.inWarehouse!''}</span>
			                        <span class="wid7"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${b.listingid!''}" target="_blank" class="col_yellow">详细</a></span>
	                    		</li>
	                   	 	</#list>
							<#assign n=n+1/>
	                   </#list>
                   </ul>
                   </#if>
                 </#if>
                </div>    
            </div>
        </div>
        
        <!-- 最新成交 -->
        <div class="area-232 fl relative">
            <div class="border-1 bt-none">
                <h2 class="title2 sty1">最新<span>成交</span></h2>
               <!-- <table class="table-list1 pa-8" cellpadding="0" cellspacing="0" align="center" width="232"> -->
	                <div class="thbg sty1">
	                    <span class="th1">品种
	                    </span><span class="th2">成交金额
	                    </span><span class="th3">交易状态
	                    </span><span class="th4">时间</span>
	                </div>
	                <div id="scroll1">
	                    <ul>
	                        <#if newBargainList?? >
			                	<#list newBargainList as bargain>
									  <li>
				                        <span class="th1"><#if (bargain.name?length) gt 3>${bargain.name?substring(0,3)!''}${bargain.name?substring(3 , bargain.name?length)?replace(bargain.name?substring(3,bargain.name?length),"..")!''}<#else>${bargain.name!'' }</#if>
				                        </span><span class="th2">${bargain.price!''}万
				                        </span><span class="th3">${orderStateMap[bargain.status?string]!''}
				                        </span><span class="th4">${bargain.updateTime?string("MM/dd")!''}</span>
			                   		 </li>
			                	</#list>
			               </#if>
	                    </ul>
                	</div>
                <!-- </table> -->
	        </div>
        </div>
 		<!-- 最新成交 -->
 		
 	<!-- 道地药材 -->	
 	<!-- IE7 下 换行 -->
 	<div style="clear:both; height:0; line-height：0; overflow:hidden;"></div>
    <div class="area-192 fl">
        <div class="border-1 bt-none hei317">
            <h2 class="title2 sty1">道地<span>药材</span></h2>
            <ul class="tabs-2">
            	<#if minSortno ??>
		           <li <#if 23 == minSortno.type >class="cur"</#if>>北方大区<span class="col_888"><span class="col_e6">｜</span>黑吉辽内冀鲁</span></li>
            	   <li <#if 24 == minSortno.type >class="cur"</#if>>西北大区<span class="col_888"><span class="col_e6">｜</span>晋陕甘宁青新</span></li>
            	   <li <#if 25 == minSortno.type >class="cur"</#if>>华东大区<span class="col_888"><span class="col_e6">｜</span>豫皖苏浙</span></li>
            	   <li <#if 26 == minSortno.type >class="cur"</#if>>中南大区<span class="col_888"><span class="col_e6">｜</span>鄂湘渝赣闽</span></li>
            	   <li <#if 27 == minSortno.type >class="cur"</#if>>西南大区<span class="col_888"><span class="col_e6">｜</span>川贵云藏</span></li>
            	   <li <#if 28 == minSortno.type >class="cur"</#if>>华南大区<span class="col_888"><span class="col_e6">｜</span>粤桂琼</span></li>
            	</#if>
            </ul>
        </div>
    </div>
        
    <div class="area-1007 fl" id="Cont2">
    	<!-- 北方大区 -->
    	<#if minSortno ??>
    	<div class="conts-2 clearfix" <#if 23 == minSortno.type >style="display: block;"</#if>>
        	<#list adData['23'] as ad>
	        	<div class="fl img-1">
	        		<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	        	</div>
        	</#list>
           	<ul class="fl">
	            <#list listingData['22'] as data>
	                <li>
	                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
	                    <p class="fr">
	                    	<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
	                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
	                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
	                    </p>
	                </li>
	            </#list>
            </ul>
        </div>
        <!-- 北方大区 -->
        
        <!-- 西北大区  -->
        <div class="conts-2 clearfix" <#if 24 == minSortno.type>style="display: block;"</#if>>
            <#list adData['24'] as ad>
	        	<div class="fl img-1">
	            	<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	            </div>
        	</#list>
           	<ul class="fl">
             <#list listingData['23'] as data>
                <li>
                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
                    <p class="fr">  
                   		<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
                    </p>
                </li>
            </#list>
            </ul>
        </div>
        <!-- 西北大区  -->
        
        <!-- 华东大区 -->
        <div class="conts-2 clearfix" <#if 25 == minSortno.type>style="display: block;"</#if>>
            <#list adData['25'] as ad>
	        	<div class="fl img-1">
	            	<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	            </div>
        	</#list>
            <ul class="fl">
             <#list listingData['24'] as data>
                <li>
                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
                    <p class="fr">  
                    	<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
                    </p>
                </li>
            </#list>
            </ul>
        </div>
        <!-- 华东大区  -->
        
        <!-- 中南大区 -->
        <div class="conts-2 clearfix" <#if 26 == minSortno.type>style="display: block;"</#if>>
           <#list adData['26'] as ad>
	        	<div class="fl img-1">
	            	<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	            </div>
        	</#list>
            <ul class="fl">
             <#list listingData['25'] as data>
                <li>
                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
                    <p class="fr">  
                    	<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
                    </p>
                </li>
            </#list>
            </ul>
        </div>
        <!-- 中南大区 -->
        
        <!-- 西南大区 -->
        <div class="conts-2 clearfix" <#if 27 == minSortno.type>style="display: block;"</#if>>
            <#list adData['27'] as ad>
	        	<div class="fl img-1">
	            	<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	            </div>
        	</#list>
            <ul class="fl">
             <#list listingData['26'] as data>
                <li>
                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
                    <p class="fr">  
                    	<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
                    </p>
                </li>
            </#list>
            </ul>
        </div>
        <!-- 西南大区 -->
        
        <!-- 华南大区   -->
        <div class="conts-2 clearfix" <#if 28 == minSortno.type>style="display: block;"</#if>>
            <#list adData['28'] as ad>
	        	<div class="fl img-1">
	            	<a href="${ad.url!''}" target="_blank" title="${ad.alt!''}"><img src="${ad.picurl!''}"/></a>
	            </div>
        	</#list>
            <ul class="fl">
             <#list listingData['27'] as data>
                <li>
                    <span class="fl"><a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }"><img src="${data.picurl!''}" alt="${data.alt!''}" /></a></span>
                    <p class="fr">  
                    	<a href="http://www.54315.com/detail/getBusiGoodsDetail?listingId=${data.listingid!''}" target="_blank" title="${data.alt!'' }">${data.name!''}</a><br/>
                        <span class="col_red">${data.price!''}${data.dictvalue!''}</span><br/>
                        <span class="col_75">${data.grade!''}<br/>${data.origin!''}<br/></span>
                    </p>
                </li>
            </#list>
            </ul>
        </div>
        <!-- 华南大区   -->
    </#if>
    </div>
    <!-- 道地药材 -->	
<!---------------------------  _(:зゝ∠)_我是【珍药现货】 END --------------------------- -->



<!---------------------------  _(:зゝ∠)_我是【珍药采购】START --------------------------- -->
    <h2 class="title1 bor2 pt20 clearfix">
            <a target="_blank" class="fr" href="http://www.54315.com/purchaseSearch/list">进入采购频道 &gt;&gt;</a>
            珍药采购
    </h2>
    <div class="area-192 fl">
        <div class="border-1 bt-none">
            <h2 class="title2 sty2">药厂<span>招标</span></h2>
            <div class="img-2" align="center">
            <#list adData['29'] as ad>
             	<a href="${ad.url!'' }" title="${ad.alt!'' }" target="_blank">
                	<img src="${ad.picurl!''}" class="mt10" alt="${ad.alt!''}" width="173" height="85" />
                </a>
            </#list>
            </div>
        </div>
    </div>
    
    <!-- 大货采购 START -->
    <div class="area-776 fl relative border-botm">
        <h2 class="title2 sty2">
            <span class="fr subs">
            <#if categorylist4??>
            	<#list categorylist4 as c>
	            	<a target="_blank" href="http://www.54315.com/purchaseSearch/list?breedName=${c.categorys_name!''}">${c.categorys_name!''}</a><#if c_has_next>|</#if>
	            </#list>
            </#if>
            </span>
            大货<span>采购</span></h2>
        <div class="thbg"></div>
        <table id="dhcg" width="776" cellspacing="0" cellpadding="0" align="center" class="table-list1 hei38">
            <tbody><tr>
                <th>品种</th>
                <th>规格等级</th>
                <th>产地要求</th>
                <th>采购数量</th>
                <th>发票要求</th>
                <th>发布日期</th>
                <th>操作</th>
            </tr>
            <#if bigPurchase??>
            	<#list bigPurchase as p>
            	 <tr <#if !p_has_next>class="bn"</#if>>
	                <td>${p.breedName!''}</td>
	                <td>
	                <#if (p.standardLevel?length) gt 4>
	                	${p.standardLevel?substring(0,4)!''}${p.standardLevel?substring(4 , p.standardLevel?length)?replace(p.standardLevel?substring(4,p.standardLevel?length),"..")!''}
	                <#else>
	                	${p.standardLevel!''}
	                </#if>
	                </td>
	                <td>
	                <#if (p.origin?length) gt 4>
	                	${p.origin?substring(0,4)!''}${p.origin?substring(4 , p.origin?length)?replace(p.origin?substring(4,p.origin?length),"..")!''}
	                <#else>
	                	${p.origin!''}
	                </#if>
	                </td>
	                <td id="quantity">${p.quantity!''}</td>
	                <input type="hidden" id="wunitCode" value="${p.wunitCode!''}"/>
	                
	                <td>
		                <#if p.receipt??>
		                	${p.receipt!''}
		                <#else>
		                	商议
		                </#if>
	                </td>
	                
	                <td>${p.updateTime?string("yyyy-MM-dd")!''}</td>
                	<td><a class="col_blue" target="_blank" href="http://www.54315.com/busiPur/PurchaseDetail?purchaseId=${p.purchaseId!''}">查看</a></td>
           		 </tr>
            	</#list>
            </#if>
            </tbody>
       </table>
    </div>
    <!-- 大货采购 END -->
    
    <!-- 最新采购 START -->
    <div class="area-232 fl relative">
        <div class="border-1 bt-none">
            <h2 class="title2 sty2">最新<span>采购</span></h2>
            <div class="thbg sty1">
                <span class="th5">品种
                </span><span class="th6">数量
                </span><span class="th7">剩余天数
                </span>
            </div>
            
             <div id="scroll2">
                <ul>
                <#if nowPurchase ??>
	               	<#list nowPurchase as p>
		               	 <li>
		                    <span class="th5"><#if (p.breedName?length) gt 3>${p.breedName?substring(0,3)!''}${p.breedName?substring(3 , p.breedName?length)?replace(p.breedName?substring(3,p.breedName?length),"..")!''}<#else>${p.breedName!'' }</#if>
		                    </span><span class="th6">${p.quantity!''}<input type="hidden" value="${p.wunitCode!''}" id="wunit"/>
		                    </span><span class="th7 p-r">${p.validPeriod!''}天
		                    </span>
		                </li>
	               	</#list>
                </#if>
             	</ul>
             </div>
        </div>
    </div>
    <!-- 最新采购 END -->
<!---------------------------  _(:зゝ∠)_我是【珍药采购】END --------------------------- -->


<script type="text/javascript" src="${RESOURCE_JS}/js/jquery-1.11.1.min.js"></script>
<script>
$(function(){
	 Tabs('.tabs-2 li','#Cont2');
	//大货采购 数字添加单位
	var chReg=/^[\u4e00-\u9fa5]+$/;
	$("table td[id='quantity']").each(function(){
		var text = $.trim($(this).text());
		var unit = $("input[id='wunitCode']").val();
		if(!chReg.test(text)) {
			$(this).text(text+unit);
        }
	});
	
	$("div[id='scroll2'] .th6").each(function(){
		var text = $.trim($(this).text());//坑爹 这里有个空格 ,不然默认都成了中文
		var unit = $("input[id='wunit']").val();
		if(!chReg.test(text)) {
			$(this).text(text+unit);
        }
	});
	
	function Tabs(tabs,conts){
        $(tabs).bind('click mousemove',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $(conts).children().eq($(this).index()).show().siblings().hide();
        });
    }
});
</script> 