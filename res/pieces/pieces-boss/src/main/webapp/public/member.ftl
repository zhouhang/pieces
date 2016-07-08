<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>用户清单-boss-饮片B2B</title>
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
            <h3>用户管理</h3>
            <div class="extra"><a class="btn btn-red" href="member/add"><i class="fa fa-plus"></i>增加新用户</a></div>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
            </div>
            <@p.pager pageInfo=memberPage  pageUrl="member/index"  params=memberParams/>
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="70">编号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th width="200">状态</th>
                    <td width="200">操作</td>
                </tr>
                <tr>
                    <td><div class="ipt-wrap"><input name="id" type="text" class="ipt" value="${memberVo.id}"></div></td>
                    <td><div class="ipt-wrap"><input name="username" type="text" class="ipt" value="${memberVo.username}"></div></td>
                    <td><div class="ipt-wrap"><input name="name" type="text" class="ipt" value="${memberVo.name}"></div></td>
                    <td><div class="ipt-wrap"><input name="email" type="text" class="ipt" value="${memberVo.email}"></div></td>
                    <td>
                        <select name="isDel" >
                            <option <#if (!memberVo.bindErp??)>selected</#if> value=""> </option>
                            <option <#if (memberVo.bindErp??&&!memberVo.bindErp)>selected</#if> value="false">否</option>
                            <option <#if (memberVo.bindErp??&&memberVo.bindErp)>selected</#if> value="true">是</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list memberPage.list as member>
                    <tr>
                        <td>${member.id}</td>
                        <td>${member.username}</td>
                        <td>${member.name}</td>
                        <td>${member.email}</td>
                        <td>
                        <#if (member.isDel)>禁用
                        <#else>激活</#if>
                        </td>
                        <td><a href="member/edit/${member.id}">修改</a></td>
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
                    var url="member/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

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