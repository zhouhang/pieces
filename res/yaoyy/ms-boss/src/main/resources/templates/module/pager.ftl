<#macro pager info url params>

<div class="pagination">
    <div class="pages">
        每页
        <select name="" id="page_select" >
            <option value="10">10</option>
            <option value="20">25</option>
            <option value="30">50</option>
            <option value="40">100</option>
        </select>
        <a href="javascript:return false;" class="text">上页</a>
        ${pageNum}


        <#if info.page <= 4 >
                //全部显示
            <#elseif info.page lt 4>
                <#--比较:> , < , >= , <= (lt , lte , gt , gte)-->
                <#if info.pageNum - info.firstPage < 4>
                    1-pageNum
                    // 最少 2个
                </#if>
                <#if info.pageNum - info.firstPage gt 4>
                    1,2
                    ...
                </#if>
                <#if info.lastPage-info.pageNum < 4>
                    pageNum - lastPage
                    // 最少 2个
                </#if>
                <#if info.lastPage-info.pageNum gt 4>
                    (pageNum-1),pageNum,(pageNum+1)
                    ...
                    lastPage-1,lastPage
                </#if>
                <#--pageNum - firstPage < 4-->
                    <#--1-pageNum-->
                <#--// pageNum 当前页码-->
                <#--pageNum - firstPage >= 4-->
                    <#--1,2-->
                    <#--...-->

                <#--lastPage-pageNum < 4-->
                    <#--pageNum - lastPage-->

                <#--lastPage-pageNum >= 4-->
                    <#--(pageNum-1),pageNum,(pageNum+1)-->
                    <#--...-->
                    <#--lastPage-1,lastPage-->
        </#if>

        <a href="javascript:return false;" class="curr">1</a>
        <a href="javascript:return false;">2</a>
        <a href="javascript:return false;">3</a>
        <i>...</i>
        <a href="javascript:return false;">8</a>
        <a href="javascript:return false;">9</a>
        <a href="javascript:return false;" class="text">下页</a>
    </div>
    <div class="info">
        显示第 ${info.startRow} 至 ${info.endRow} 项结果，共 <em id="pageSize">${info.total}</em> 项
    </div>
    <script type="text/javascript">
        $(function(){
            //初始化分页值
            $("#page_select").val("${pageSize}")

            $(".pagination .pages a").click(function () {
                $(this).attr(data);

            })
            //选择对应的pageSize
            $("#page_select").change(function(){
                var pageSize = $(this).val()
                location.href="${pageUrl}"+"?pageNum=${pageNum}&pageSize="+pageSize+"${(params)!}"
            })
        })
    </script>

</div>
</#macro>

