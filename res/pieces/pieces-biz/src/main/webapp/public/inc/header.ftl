<#include "./inc/nav.ftl"/>

<!-- header start -->
<div class="header">
    <div class="wrap">
        <div class="logo">
            <a href="/">饮片B2B首页</a>
        </div>
        <div class="search">
            <div class="form">
                <form id="_search_form" action="commodity/search" method="get">
                    <input id="_search_ipt" class="ipt" name="keyword"  placeholder="请输入原药名称或饮片名称" value="${keyword!}" type="text">
                    <button  class="btn" type="submit">搜索</button>
                </form>
            </div>
            <div class="hotwords">
                <a href="/commodity/search?keyword=三七">三七</a>
                <a href="/commodity/search?keyword=黄芪">黄芪</a>
                <a href="/commodity/search?keyword=当归">当归</a>
                <a href="/commodity/search?keyword=西洋参">西洋参</a>
                <a href="/commodity/search?keyword=党参">党参</a>
                <a href="/commodity/search?keyword=天麻">天麻</a>
                <a href="/commodity/search?keyword=人参">人参</a>
                <a href="/commodity/search?keyword=枸杞子">枸杞子</a>
                <a href="/commodity/search?keyword=丹参">丹参</a>
                <a href="/commodity/search?keyword=玛咖">玛咖</a>
            </div>
        </div>
    </div>
</div><!-- header end -->


<script type="text/javascript">

    $(function(){
        $('#_search_ipt').autocomplete({
            serviceUrl: '/commodity/search/auto',
            paramName:'keyword',
            transformResult: function(response) {
                response = JSON.parse(response);
                return  {suggestions:$.map(response, function(dataItem) {
                    if(dataItem.category){
                        return {value: dataItem.category+":"+dataItem.value, data: {"category":dataItem.category}};
                    }else{
                        return {value: dataItem.value, data: {"category":dataItem.category}};
                    }
                })};
            },
            onSelect: function (suggestion) {
                $("#_search_form").submit();
            },
            groupBy:"category"
        });
    })

</script>


<!-- nav start -->
<div class="nav">
    <div class="wrap">
        <div class="cat">
            <h2 class="hd">商品分类</h2>
            <div class="bd">
                <dl>
                    <dt><a href="#!">团购分类1</a></dt>
                </dl>
            </div>
        </div>
        <div class="menu">
            <ul>
                <li><a href="/">首页</a></li>
                <li><a href="/commodity/index">产品</a></li>
                <li><a href="manufacturers.html">厂家</a></li>
                <li><a href="manufacturers.html">经方</a></li>
                <li><a href="manufacturers.html">功效</a></li>
            </ul>
        </div>
        <div class="plus">
            <a class="btn btn-gray" href="/center/enquiry/index"><i class="fa fa-question-circle"></i>快速询价</a>
        </div>
    </div>
</div><!-- nav end -->