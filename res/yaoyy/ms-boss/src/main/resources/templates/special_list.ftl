<!DOCTYPE html>
<html lang="en">
<head>
    <title>专场列表-boss</title>
    <#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>

<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>

<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>专场广告</li>
            <li>专场列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter">
                <form action="" id="searchForm">
                    <label>标题：</label>
                    <input type="text" class="ipt"  name="title" value="${specialVo.title?default('')}" placeholder="请输入">
                    <button type="button" id="search" class="ubtn ubtn-blue">搜索</button>
                </form>
            </div>

            <div class="action-add">
                <a href="special/create" class="ubtn ubtn-blue">新建专场</a>
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>标题</th>
                    <th>链接</th>
                    <th>创建时间</th>
                    <th>修改时间</th>
                    <th>排序</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list  specialVoPageInfo.list as special>
                <tr <#if special.status==0>class="gray"</#if>>
                    <td><input type="checkbox"></td>
                    <td>${special.title}</td>
                    <td>${bizBaseUrl}special/${special.id}</td>
                    <td>${(special.createTime?datetime)!}</td>
                    <td>${(special.updateTime?datetime)!}</td>
                    <td>${special.sort!}</td>
                    <td class="tc">
                        <a href="special/edit/${special.id?c}" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="#" class="ubtn ubtn-gray jdel" sid="${special.id?c}">删除</a>
                        <a href="#" class="ubtn ubtn-gray jputaway" sid="${special.id?c}" status="${special.status}">
                            <#if special.status==0>
                                上架
                            <#else>
                                下架
                            </#if>

                        </a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=specialVoPageInfo url="special/list" params=specialVoParams />
    </div>
</div>

<#include "./common/footer.ftl"/>

<script>
    var _global = {
        v: {
            deleteUrl: 'special/delete/',
            listUrl:'special/list',
            updateUrl:'special/updateStatus'
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
                var $search=$("#search");

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl+$(this).attr("sid");
                    layer.confirm('确认删除此品种？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == "200") {
                                layer.close(index);
                                window.location.reload();
                            }
                    });
                    });
                    return false; // 阻止链接跳转
                })

                // 上架&下架
                $table.on('click', '.jputaway', function() {
                    var sid=$(this).attr("sid");
                    var status = $(this).attr("status");
                    var setStatus = 1;
                    if (status == 1) {
                        setStatus = 0;
                    }
                    $.ajax({
                        url: _global.v.updateUrl,
                        data: {"id": sid, "status": setStatus},
                        type: "POST",
                        success: function (data) {
                            if (data.status == "200") {
                                window.location.reload();
                            }
                        }
                    });
                    return false; // 阻止链接跳转
                })
                $search.on('click',function () {
                    var params = [];
                    $("#searchForm .ipt").each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=_global.v.listUrl+'?'+params.join('&');
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