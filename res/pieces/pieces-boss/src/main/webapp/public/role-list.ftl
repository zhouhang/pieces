<!DOCTYPE html>
<html lang="en">
<head>
    <title>角色用户-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>角色信息</dt>
                    <dd>
                        <a href="role/info/${role.id}">角色信息</a>
                        <a href="role/power/${role.id}">角色权限</a>
                        <a class="curr" href="role/list/${role.id}">角色用户</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title title-btm">
                    <h3><i class="fa fa-people"></i>修改角色 “超级管理员”</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">保存</button>
                        <button type="submit" class="btn btn-red">保存并继续</button>
                    </div>
                </div>

                <div class="pagin">
                    <div class="extra">
                        <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                        <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
                    </div>
                    <@p.pager pageInfo=memberPage  pageUrl="role/list/${role.id}"  params=memberParams/>
                </div>

                <div class="chart">
                    <table class="tc">
                        <thead>
                            <tr>
                                <th width="100"><label><input type="checkbox" class="cbx" /></label></th>
                                <th width="100">编号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>邮箱</th>
                                <th width="100">状态</th>
                            </tr>
                            <tr>
                                <td>
                                    <select name="" >
                                        <option value="">是</option>
                                    </select>
                                </td>
                                <td><div class="ipt-wrap"><input name="id" type="text" class="ipt" value="${memberVo.id}"></div></td>
                                <td><div class="ipt-wrap"><input name="username" type="text" class="ipt" value="${memberVo.username}"></div></td>
                                <td><div class="ipt-wrap"><input name="name" type="text" class="ipt" value="${memberVo.name}"></div></td>
                                <td><div class="ipt-wrap"><input name="email" type="text" class="ipt" value="${memberVo.email}"></div></td>
                                <td>
                                    <select name="isDel" >
                                        <option <#if (!memberVo.isDel??)>selected</#if> value=""> </option>
                                        <option <#if (memberVo.isDel??&&!memberVo.isDel)>selected</#if> value="false">激活</option>
                                        <option <#if (memberVo.isDel??&&memberVo.isDel)>selected</#if> value="true">禁用</option>
                                    </select>
                                </td>
                            </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                        <#list memberPage.list as member>
                            <tr>
                                <td><label><input type="checkbox" class="cbx" /></label></td>
                                <td>${member.id}</td>
                                <td>${member.username}</td>
                                <td>${member.name}</td>
                                <td>${member.email}</td>
                                <td>
                                    <#if (member.isDel)>禁用
                                    <#else>激活</#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>


</body>
<script>

    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${memberPage.pageNum},
                pageSize:${memberPage.pageSize}
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
                    var url="role/list/${role.id}?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

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
</html>