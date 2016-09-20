<#include "./inc/nav.ftl"/>

<!-- header start -->
<div class="header">
    <div class="wrap">
        <div class="logo">
            <a href="/">上工好药首页</a>
        </div>
        <div class="search">
            <div class="form">
                <form id="_search_form" action="commodity/search" method="get">
                    <input id="_search_ipt" class="ipt" name="keyword"  placeholder="请输入原药名称或饮片名称" value="${keyword!}" type="text">
                    <button  class="btn" type="submit">搜索</button>
                </form>
            </div>
            <div class="hotwords">
                <@search_keyword />
            </div>
        </div>
    </div>
</div><!-- header end -->


<!-- nav start -->
<!-- nav start -->
<div class="nav">
    <div class="wrap">
        <div class="cat <#if CURRENT_PAGE?? && CURRENT_PAGE=="home">cat-show</#if> ">
            <div class="hd">商品分类</div>
            <div class="bd" id="jcat">
                <ul>
                    <@pinyin_category />
                </ul>
            </div>
        </div>
        <div class="menu">
            <ul>
                <li <#if CURRENT_PAGE??&&CURRENT_PAGE=='home'>class="current"</#if>><a href="/">首页</a></li>
                <li <#if CURRENT_PAGE??&&CURRENT_PAGE=='commodity'>class="current"</#if>><a href="/commodity/index">产品</a></li>
                <li <#if CURRENT_PAGE??&&CURRENT_PAGE=='prescription'>class="current"</#if>><a href="/channel/prescription">经方</a></li>
                <li <#if CURRENT_PAGE??&&CURRENT_PAGE=='efficacy'>class="current"</#if>><a href="/efficacy">功效</a></li>
            </ul>
        </div>
        <div class="plus">
            <a href="/center/enquiry/index" class="btn btn-gray"><i class="fa fa-question-circle"></i>快速询价</a>
        </div>
    </div>
</div><!-- nav end -->