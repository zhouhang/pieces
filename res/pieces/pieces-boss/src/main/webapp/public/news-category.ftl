<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>商品管理-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>文章分类管理</h3>
            <div class="extra"><a class="btn btn-red" href="/cms/category/add?model=${vo.model}"><i class="fa fa-plus"></i>增加新分类</a></div>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
            </div>
        <@p.pager pageInfo=pageInfo  pageUrl="/cms/category/index"  params=param />
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="100">编号</th>
                    <th class="tl">文章分类</th>
                    <th width="150">操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="ipt-wrap"><input type="text" name="name" class="ipt" value="${vo.name}"></div></td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list pageInfo.list as category>
                <tr>
                    <td>${category.id}</td>
                    <td><div class="tl">${category.name}</div></td>
                    <td><a href="/cms/category/detail/${category.id}">修改</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->
</div>

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="/js/common.js"></script>
<script>
    //定义根变量
    !(function($) {
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
                },
                // 筛选
                filter: function() {
                    var url = "/cms/category/index?model=2";

                    $("#reset").on("click", function(){
                        window.location.href=url;
                    })

                    var $ipts = $('.chart .ipt, .chart select');

                    $('#submit').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        // console.log(params.join('&'))
                        location.href=url + "&" + params.join('&');
                    })
                }
            }
        }
        //加载页面js
        $(function() {
            page.fn.init();
        });
    })(jQuery);

</script>
</body>
</html>