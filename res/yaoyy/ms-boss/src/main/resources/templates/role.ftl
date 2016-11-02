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
                        <th><input type="checkbox"></th>
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
                        <td><input type="checkbox" value="${role.id}"></td>
                        <td>${role.id}</td>
                        <td>${role.name}</td>
                        <td>${role.name}</td>
                        <td>${role.name}</td>
                        <td class="tc">
                                <a href="role/power/${role.id}" class="ubtn ubtn-blue jedit">配置</a>
                                <a href="javascript:;"  class="ubtn ubtn-gray jdel" roleId="${role.id}">删除</a>
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
        var _global = {
            v: {
                deleteUrl: '/role/delete/',
            },
            fn: {
                init: function() {
                    this.bindEvent();
                },
                bindEvent: function() {
                    var $table = $('.table'),
                            $cbx = $table.find('td input:checkbox'),
                            $checkAll = $table.find('th input:checkbox'),
                            count = $cbx.length;

                    // 删除
                    $table.on('click', '.jdel', function() {
                        var url = _global.v.deleteUrl + $(this).attr('roleId');
                        layer.confirm('确认删除此账户？', {icon: 3, title: '提示'}, function (index) {
                            $.get(url, function (data) {
                                if (data.status == 200) {
                                    window.location.reload();
                                }
                            }, "json");
                            layer.close(index);
                        });
                        return false; // 阻止链接跳转
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