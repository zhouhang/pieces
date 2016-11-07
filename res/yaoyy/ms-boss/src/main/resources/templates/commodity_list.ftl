<!DOCTYPE html>
<html lang="en">
<head>
    <title>药优优-商品列表</title>
<#include "./common/meta.ftl"/>
</head>
<body class="wrapper">
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>商品管理</li>
            <li>商品列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter" id="filterForm">
                <#--<form action="">-->
                    <label>品种：</label><input type="text" name="categoryName" class="ipt" placeholder="请输入">
                    <label>商品名称：</label><input name="name" type="text" class="ipt" placeholder="商品名称">
                    <label>上/下架：</label>
                    <select name="status" class="slt">
                        <option value="">全部</option>
                        <option value="1">上架</option>
                        <option value="0">下架</option>
                    </select>
                    <button type="button" class="ubtn ubtn-blue" id="search_btn">搜索</button>
                <#--</form>-->
            </div>
            <div class="action-add">
                <button class="ubtn ubtn-blue" id="jaddNewCat">新建商品</button>
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>商品名称</th>
                    <th>品种</th>
                    <th>标题</th>
                    <th>规格等级</th>
                    <th>产地</th>
                    <th>排序</th>
                    <th width="150">创建时间</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list pageInfo.list as commodity>
                <tr <#if commodity.status==0>class="gray"</#if>>
                    <td><input type="checkbox" value="${commodity.id}"></td>
                    <td>${commodity.name}<#if commodity.mark == 1 ><em class="c-red">【量大价优】</em></#if></td>
                    <td>${commodity.categoryName}</td>
                    <td>${commodity.title}</td>
                    <td>${commodity.spec}</td>
                    <td>${commodity.origin}</td>
                    <td>${commodity.sort}</td>
                    <td><#if commodity.createTime?exists>${commodity.createTime?datetime}</#if></td>
                    <td class="tc">
                        <a href="/commodity/detail/${commodity.id}" class="ubtn ubtn-blue jedit">编辑</a>
                        <a href="${commodity.id}" class="ubtn ubtn-gray jdel">删除</a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=pageInfo url="commodity/list" params="" />
    </div>
</div>

<#include "./common/footer.ftl"/>

<script>
    var _global = {
        v: {
            deleteUrl: '/commodity/detete/',
            listUrl: '/commodity/list'
        },
        fn: {
            init: function() {
                this.bindEvent();
                $("#filterForm").initByUrlParams();
            },
            bindEvent: function() {

                $("#jaddNewCat").click(function () {
                    location.href="/commodity/add"
                })

                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;

                // 删除
                $table.on('click', '.jdel', function() {
                    var url = _global.v.deleteUrl + $(this).attr('href');
                    layer.confirm('确认删除此商品？', {icon: 3, title: '提示'}, function (index) {
                        $.post(url, function (data) {
                            if (data.status == "200") {
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

                _global.fn.filter();
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
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>

</body>
</html>