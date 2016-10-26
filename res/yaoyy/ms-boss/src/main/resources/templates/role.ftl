<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>角色清单-boss-上工好药</title>
</head>

<body class="wrapper">

<#include "./common/header.ftl">
<#include "./common/aside.ftl"/>

<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>账号权限</li>
            <li>角色列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <@shiro.hasPermission name="role:add">
            <div class="action-add">
                <a href="/role/add" class="ubtn ubtn-blue">新建角色</a>
            </div>
            </@shiro.hasPermission>
        </div>

        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>编号</th>
                        <th>角色名称</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <#list rolePage.list as role>
                    <tr>
                        <td>${role.id}</td>
                        <td>${role.name}</td>
                        <td>${role.name}</td>
                        <td>${role.name}</td>
                        <td>
                            <@shiro.hasPermission name="role:edit">
                                <a href="role/power/${role.id}" class="ubtn ubtn-blue jedit">配置</a>
                            </@shiro.hasPermission>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
        <#import "./module/pager.ftl" as pager />
        <@pager.pager info=rolePage url="role/index" params=roleParams />
    </div>
</div>

    <#include "./common/footer.ftl"/>

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
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/role/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

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