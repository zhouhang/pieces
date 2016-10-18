<#macro pager pageInfo pageUrl params>
<#--上一页-->
<#assign prePage=pageInfo.prePage>
<#--下一页-->
<#assign nextPage=pageInfo.nextPage>

<#if (!pageInfo.hasPreviousPage)>
    <#assign prePage=pageInfo.firstPage >
</#if>

<#if (!pageInfo.hasNextPage)>
    <#assign nextPage=pageInfo.lastPage >
</#if>

<#assign pageSize=pageInfo.pageSize>
<#assign pageNum=pageInfo.pageNum>

<div class="skip">
    <span>第</span>
    <a class="fa fa-chevron-left btn btn-gray" href="${pageUrl}?pageNum=${prePage}&pageSize=${pageSize}${(params)!}"></a><input type="text" class="ipt" value="${pageNum}"><a class="fa fa-chevron-right btn btn-gray" href="${pageUrl}?pageNum=${pageInfo.nextPage}&pageSize=${pageSize}${(params)!}"></a>
    <span>页，共</span><em>${pageInfo.pages}</em><span>页</span>
    <i>|</i>
    <span>每页</span>
    <select name="" id="page_select" >
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
        <option value="40">40</option>
    </select>
    <span>个记录，共有 ${pageInfo.total} 个记录</span>
</div>
<script type="text/javascript">
    $(function(){
        //初始化分页值
        $("#page_select").val("${pageSize}")
        //选择对应的pageSize
        $("#page_select").change(function(){
            var pageSize = $(this).val()
            location.href="${pageUrl}"+"?pageNum=${pageNum}&pageSize="+pageSize+"${(params)!}"
        })
    })
</script>
</#macro>

