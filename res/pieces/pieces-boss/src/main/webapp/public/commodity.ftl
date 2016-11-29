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
                <h3>商品管理</h3>
                <div class="extra">
                <@shiro.hasPermission name="commodity:add">
                    <a class="btn btn-red" href="commodity/add"><i class="fa fa-plus"></i>增加新产品</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=pageInfo  pageUrl="commodity/index"  params=param />
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th width="220">原药品种</th>
                        <th width="240">商品名称</th>
                        <th width="100">片型</th>
                        <th width="100">原料产地</th>
                        <th>规格等级</th>
                        <th width="100">状态</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input name="categoryName" type="text" class="ipt" value="${vo.categoryName}"></div></td>
                        <td><div class="ipt-wrap"><input name="name" type="text" class="ipt" value="${vo.name}"></div></td>
                        <td><div class="ipt-wrap"><input name="specName" type="text" class="ipt" value="${vo.specName}"></div></td>
                        <td><div class="ipt-wrap"><input name="originOfName" type="text" class="ipt" value="${vo.originOfName}"></div></td>
                        <td><div class="ipt-wrap"><input name="level" type="text" class="ipt" value="${vo.level}"></div></td>
                        <td>
                            <select name="status" id="">
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
                    <#list pageInfo.list as commodity>
                    <tr>
                        <td>${commodity.id}</td>
                        <td>${commodity.categoryName}</td>
                        <td>${commodity.name}</td>
                        <td>${commodity.spec}</td>
                        <td>${commodity.originOf}</td>
                        <td>${commodity.level}</td>
                        <td><#if commodity.status ==1>激活<#else>禁用</#if></td>
                        <td>
                            <@shiro.hasPermission name="commodity:edit">
                            <a href="commodity/editer/${commodity.id}">修改</a>
                            </@shiro.hasPermission>
                        </td>
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
<script>

    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${pageNum},
                pageSize:${pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.filter();

                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/commodity/index?";

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+params.join('&');
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