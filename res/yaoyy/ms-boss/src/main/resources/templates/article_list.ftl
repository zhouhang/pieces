<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>文章列表-药优优</title>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>CMS管理</li>
            <li>CMS列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter" id="filterForm">
                    <label>标题：</label>
                    <input type="text" name="title" class="ipt" placeholder="请输入">
                    <button id="search_btn" class="ubtn ubtn-blue">搜索</button>
            </div>

            <div class="action-add">
                <a href="/cms/create" class="ubtn ubtn-blue">新建文章</a>
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox" class="cbx"></th>
                    <th>标题</th>
                    <th>链接</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th width="170" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list pageInfo.list as article>
                <tr <#if article.status==0>class="gray"</#if>>
                    <td><input type="checkbox" class="cbx"></td>
                    <td>${article.title}</td>
                    <td>${bizBaseUrl}/article/${article.id}</td>
                    <td>${(article.createTime?datetime)!}</td>
                    <td>${(article.updateTime?datetime)!}</td>
                    <td class="tc">
                        <a href="/cms/editor/${article.id};" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="javascript:;" class="ubtn ubtn-gray jdel" data-id="${article.id}">删除</a>
                        <#if article.status == 0>
                            <a href="javascript:;" data-id="${article.id}"  class="ubtn ubtn-gray jputup">启用</a>
                        <#else>
                            <a href="javascript:;" data-id="${article.id}"  class="ubtn ubtn-gray jputdown">禁用</a>
                        </#if>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=pageInfo url="article/list" params="" />
    </div>
</div>
<#include "./common/footer.ftl"/>
<script>
    var _global = {
        v: {
            deleteUrl: '/cms/delete/',
            detailUrl:'/cms/detail/',
            enableUrl:'/cms/enable/',
            disableUrl:'/cms/disable/',
            listUrl: '/cms/list'
        },
        fn: {
            init: function() {
                $("#filterForm").initByUrlParams();
                this.bindEvent();
                this.filter();
            },
            // 筛选
            filter: function() {
                var $ipts = $('.filter .ipt, .filter select');
                var url=_global.v.listUrl+"?";

                $('#search_btn').on('click', function() {
                    var params = [];
                    $ipts.each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+params.join('&');
                })
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl + $(this).attr('href');
                    layer.confirm('确认删除此文章？', {icon: 3, title: '提示'}, function (index) {
                        $.get(url, function (data) {
                            if (data.status == 200) {
                                window.location.reload();
                            }
                        }, "json");
                        layer.close(index);
                    });
                    return false; // 阻止链接跳转
                })

                // 上架
                $table.on('click', '.jputup', function() {
                    var url = _global.v.enableUrl + $(this).attr('data-id');
                    layer.confirm('确认启用此文章？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false;
                })

                // 下架
                $table.on('click', '.jputdown', function() {
                    var url = _global.v.disableUrl + $(this).attr('data-id');
                    layer.confirm('确认禁用此文章？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false;
                })

                // 全选
                $checkAll.on('click', function() {
                    var isChecked = this.checked;
                    $cbx.each(function() {
                        this.checked = isChecked;
                    })
                })
                // 单选
                $cbx.on('click', function() {
                    var _count = 0;
                    $cbx.each(function() {
                        _count += this.checked ? 1 : 0;
                    })
                    $checkAll.prop('checked', _count === count);
                })
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>