<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>文章管理-boss-上工好药</title>

<body>
<#include "./inc/header.ftl">


<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>文章管理</h3>
            <div class="extra"><a class="btn btn-red" href="cms/article/add?model=${vo.model}"><i class="fa fa-plus"></i>增加文章</a></div>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span>
                </button>
            </div>
        <@p.pager pageInfo=pageInfo  pageUrl="cms/article/index"  params=param />
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="100">编号</th>
                    <th class="tl">文章标题</th>
                    <th width="150">页面分类</th>
                    <th width="100">发布时间</th>
                    <th width="150">状态</th>
                    <th width="150">操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="ipt-wrap"><input type="text" name="title" class="ipt" value="${vo.title}"></div>
                    </td>
                    <td>
                        <select id="categoryId" name="categoryId">
                            <option value=""></option>
                        <#list categorys as category>
                            <option value="${category.id}">${category.name}</option>
                        </#list>
                        </select>
                    </td>
                    <td><input type="text" name="publishedDate" class="ipt" value="<#if vo.publishedDate?exists>${vo.publishedDate?date}</#if>" onclick="laydate()"></div></td>
                    <td>
                        <select id="status" name="status">
                            <option value=""></option>
                            <option value="1">激活</option>
                            <option value="0">禁用</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list pageInfo.list as article>
                <tr>
                    <td>${article.id}</td>
                    <td>
                        <div class="tl">${article.title}</div>
                    </td>
                    <td>${article.articleCategoryName}</td>
                    <td><#if article.publishedDate?exists>${article.publishedDate?date}</#if></td>
                    <td><#if article.status ==1>激活<#else>禁用</#if></td>
                    <td><a href="cms/article/detail/${article.id}">修改</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<script src="js/laydate/laydate.js"></script>
<!-- footer end -->
<script>
    //定义根变量
    !(function ($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page"
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.filter();
                    $("#status").val("${vo.status}");
                    $("#categoryId").val("${vo.categoryId}");
                },
                // 筛选
                filter: function () {
                    var url = "/cms/article/index?model=2";
                    $("#reset").on("click", function(){
                        window.location.href=url;
                    })

                    var $ipts = $('.chart .ipt, .chart select');

                    $('#submit').on('click', function () {
                        var params = [];
                        $ipts.each(function () {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        // console.log(params.join('&'))
                        location.href= url + "&" + params.join('&');
                    })
                }
            }
        }
        //加载页面js
        $(function () {
            page.fn.init();
        });
    })(jQuery);

</script>
</body>
</html>