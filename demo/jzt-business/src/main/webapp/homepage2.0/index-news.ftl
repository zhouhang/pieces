<!-- 包括药材快讯、市场动态、品种分析、行业新闻 -->
<div class="news-box fl">
        <div class="border-1" style="*padding-bottom: 12px!important;">
            <h2 class="title4">药材快讯</h2>
            <div id="scroll3">
            	<ul>
		            <#if herbalNews??>
			            <#list herbalNews as hbn>
				            <li><p>${hbn.cont } <span class="col_51">${hbn.dtm?string("MM-dd")!'' }</span></p></li>
			            </#list>
		            </#if>
            	</ul>
            </div>
        </div>
    </div>

    <div class="news-breed fr">
        <div class="border-1">
            <ul class="tabs-3" id="Tab4">
                <li class="cur">市场动态<i></i></li>
                <li>品种分析<i></i></li>
                <li class="bn">行业新闻<i></i></li>
            </ul>
            <div id="Cont4">
                <div class="list_12" style="display: block;">
                    <ul>
                    <#if dynamiclist??>
                    	<#list dynamiclist as dml>
                    		<li><span class="fr co_c8">${dml.dtm?string("MM-dd")!'' }</span><a href="http://www.zyczyc.com/info/Content.aspx?lmid=${dml.lmid }&acid=${dml.acid }&shopid=-1" title="${dml.title!'' }" target="_blank"><#if (dml.title)?length gt 17>${dml.title?substring(0,17) }<#else>${dml.title }</#if> </a></li>
                    	</#list>
                    </#if>
                    </ul>
                </div>
                <div class="list_12">
                    <ul>
	                    <#if breed??>
	                    	<#list breed as br>
	                    		<li><span class="fr co_c8">${br.dtm?string("MM-dd")!'' }</span><a href="http://www.zyczyc.com/info/Content.aspx?lmid=${br.lmid }&acid=${br.acid }&shopid=-1" title="${br.title!'' }" target="_blank"><#if (br.title)?length gt 17>${br.title?substring(0,17) }<#else>${br.title} </#if></a></li>
	                    	</#list>
	                    </#if>
                    </ul>
                </div>
                <div class="list_12">
                    <ul>
	                    <#if tradeNews??>
	                    	<#list tradeNews as trn>
	                    		<li><span class="fr co_c8">${trn.dtm?string("MM-dd")!'' }</span><a href="http://www.zyczyc.com/info/Content.aspx?lmid=${trn.lmid }&acid=${trn.acid }&shopid=-1" title="${trn.title!'' }" target="_blank"><#if (trn.title)?length gt 17>${trn.title?substring(0,17) }<#else>${trn.title }</#if></a></li>
	                    	</#list>
	                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>