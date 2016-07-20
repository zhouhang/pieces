<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>角色清单-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
        <#if (advices??)>
            <div  class="message">
                <i class="fa fa-check-circle"></i>
                <span>${advices}</span>
            </div>
        </#if>

            <div class="title title-btm">
                <h3>角色管理</h3>
                <@shiro.hasPermission name="role:add">
                    <div class="extra"><a class="btn btn-red" href="role/add"><i class="fa fa-plus"></i>增加新角色</a></div>
                </@shiro.hasPermission>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=rolePage  pageUrl="role/index"  params=roleParams/>

            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="200">编号</th>
                            <th>角色名称</th>
                            <th width="200">操作</th>
                        </tr>
                        <tr>
                            <td><div class="ipt-wrap"><input name="id" type="text" class="ipt" value="${roleVo.id!}"></div></td>
                            <td><div class="ipt-wrap"><input name="name" type="text" class="ipt" value="${roleVo.name!}"></div></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list rolePage.list as role>
                        <tr>
                            <td>${role.id}</td>
                            <td><div class="tl">${role.name}</div></td>
                            <td>
                                <@shiro.hasPermission name="role:edit">
                                    <a href="role/info/${role.id}">配置</a>
                                </@shiro.hasPermission>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${rolePage.pageNum},
                pageSize:${rolePage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.filter();
                    $("#search_btn").click(function(){
                        page.fn.filter();
                    })


                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="role/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

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