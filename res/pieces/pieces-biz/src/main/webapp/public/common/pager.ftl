<#macro pager inPageNo pageSize toURL recordCount>
    <#assign pageCount=((recordCount + pageSize -1 ) / pageSize)?int>
    <#assign pageNo=inPageNo+1 >
    <#if recordCount==0><#return/></#if>
    <#if (pageNo < 1)>
        <#assign pageNo=1 >
    </#if>
    <#if (pageNo > pageCount)>
        <#assign pageNo=pageCount>
    </#if>

<div class="pagin">
    <#assign visiblePages = 5>
    <#assign  start=(pageNo-2+1-visiblePages%2)>
    <#assign  end=(pageNo+((visiblePages/2)?int))>

    <#if (start<1)>
        <#assign  start = 1>
        <#assign  end = visiblePages>
    </#if>
    <#if (end>=pageCount)>
        <#assign  start = (pageCount-visiblePages+1)>
        <#if start<=0>
            <#assign start = 1 />
        </#if>
        <#assign  end = pageCount>
    </#if>

    <a  href="${toURL}">首页</a>
<#-- 上一页处理 -->
    <#if (pageNo == 1)>
        <span class="disabled">上一页</span>
    <#else>
        <#if toURL?ends_with("?")>
        	<a  href="${toURL}pageNum=${pageNo-1}">上一页</a>
        <#else>
        	<a  href="${toURL}&pageNum=${pageNo-1}">上一页</a>
        </#if>
    </#if>

    <#if (start>1)>
    	<#if toURL?ends_with("?")>
        	<a href="${toURL}pageNum=1">1</a>
        <#else>
        	<a href="${toURL}&pageNum=1">1</a>
        </#if>
        &hellip;
    </#if>

<#-- 页号处理-->
    <#list start..end as i>
        <#if (pageNo==i)>
            <span class="curr">${i}</span>
        <#else>
        	<#if toURL?ends_with("?")>
	        	<a href="${toURL}pageNum=${i}">${i}</a>
	        <#else>
	        	<a href="${toURL}&pageNum=${i}">${i}</a>
	        </#if>
        </#if>
    </#list>

<#--如果后面页数过多,显示... -->
    <#if (end<pageCount)>
        <#if !(end==pageCount-1)>
            &hellip;
        </#if>
        <#if toURL?ends_with("?")>
        	<a href="${toURL}pageNum=${pageCount}"  >${pageCount}</a>
        <#else>
        	<a href="${toURL}&pageNum=${pageCount}"  >${pageCount}</a>
        </#if>
    </#if>

<#-- 下一页处理 -->
    <#if (pageNo == pageCount)>
        <span class="disabled">下一页</span>
    <#else>
        <#if toURL?ends_with("?")>
        	<a href="${toURL}pageNum=${pageNo + 1}" >下一页</a>
        <#else>
        	<a href="${toURL}&pageNum=${pageNo + 1}" >下一页</a>
        </#if>
    </#if>
<#-- 下一页处理 -->

    <#-- 尾页处理 -->
    <#if (pageCount>1)>
    	<#if toURL?ends_with("?")>
        	<a  href="${toURL}pageNum=${pageCount}">尾页</a>
        <#else>
        	<a  href="${toURL}&pageNum=${pageCount}">尾页</a>
        </#if>
        <em>共 ${recordCount} 个商品 / 共${pageCount}页 / 跳转到第</em>
        <input class="ipt" type="text" onkeydown="javascript:if(event.keyCode==13){page_jump();return false;}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="${pageNo}" maxlength="4" id="jPageSkip" >页
        <button class="btn btn-gray" type="button" onclick="page_jump();">确定</button>
    </#if>
    <#-- 尾页处理 -->
    <script language="javascript">

        function page_jump(){
           var no =   $("#jPageSkip").val();
            if(no>${pageCount}){no=${pageCount};}
            if(no<1){no=1;}

            var url = "${toURL}";
            if(url.indexOf("?")!=-1){
                url=url+"&";
            }else{
                url=url+"?";
            }
            location.href = url+"pageNum="+no;
        }

    </script>

</div>

<#--<div class="pagin">-->
<#--<span class="disabled">上一页</span>-->
<#--<span class="curr">1</span>-->
<#--<a href="?page=2">2</a>-->
<#--<a href="?page=3">3</a>-->
<#--<a href="?page=4">4</a>-->
<#--<a href="?page=5">5</a>-->
<#--<a href="?page=2">下一页</a>-->
<#--<a href="?page=2">尾页</a>-->
<#--<em>共 284 个商品 / 共29页 / 跳转到第</em>-->
<#--<input class="ipt" type="text" onkeydown="javascript:if(event.keyCode==13){page_jump();return false;}" onkeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="1" maxlength="4" id="jPageSkip" >页-->
<#--<button class="btn btn-gray" type="button" onclick="page_jump();">确定</button>-->
<#--</div>-->

</#macro>
