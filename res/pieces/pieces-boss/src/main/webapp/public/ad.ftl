<!DOCTYPE html>
<html lang="en">
<head>
    <title>广告管理-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>广告管理</h3>
                <div class="extra"><a class="btn btn-red" href="ad/add"><i class="fa fa-plus"></i>增加广告</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <a class="btn btn-gray" href="/ad/index">重置条件</a>
                    <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=adPage  pageUrl="ad/index"  params=adParams/>

            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="100">编号</th>
                            <th class="tl">广告标题</th>
                            <th width="150">广告分类</th>
                            <th width="150">排序</th>
                            <th width="150">状态</th>
                            <th width="150">操作</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input name="title" type="text" class="ipt" value="${adVo.title!}"></div></td>
                            <td>
                                <select id="" name="typeId">
                                    <option value=""></option>
                                    <#list typeList as type>
                                        <option <#if type.id==adVo.typeId>selected</#if> value="${type.id!}">${type.title!}</option>
                                    </#list>
                                </select>
                            </td>
                            <td><div class="ipt-wrap"><input name="sort" type="text" class="ipt" value="${adVo.sort!}"></div></td>
                            <td>
                                <select id="" name="status">
                                    <option value=""></option>
                                    <option <#if adVo.status>selected</#if> value="true">激活</option>
                                    <option <#if !adVo.status>selected</#if>value="false">禁用</option>
                                </select>
                            </td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list adPage.list as ad>
                        <tr>
                            <td>${ad.id!}</td>
                            <td><div class="tl">${ad.title!}</div></td>
                            <td>${ad.typeName!}</td>
                            <td>${ad.sort!}</td>
                            <td>
                                <#if ad.status>
                                    激活
                                <#else>
                                    未激活
                                </#if>
                            </td>
                            <td><a href="ad/edit/${ad.id!}">修改</a></td>
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
                pageNum:${adPage.pageNum},
                pageSize:${adPage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.filter();
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/ad/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
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