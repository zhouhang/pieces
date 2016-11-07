<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>用户列表-药优优</title>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>用户管理</li>
            <li>用户列表列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter" id="filterForm">
                <#--<form action="">-->
                    <label>手机：</label><input name="phone" type="text" class="ipt" placeholder="请输入">
                    <label>姓名/单位：</label><input name="name" type="text" class="ipt" placeholder="商品名称">
                    <label>身份类型：</label>
                    <select name="identityType" class="slt">
                        <option value="">全部</option>
                        <option value="1">饮片厂</option>
                        <option value="2">药厂</option>
                        <option value="3">药材经营公司</option>
                        <option value="4">个体经营户</option>
                        <option value="5">合作社</option>
                        <option value="6">种植基地</option>
                        <option value="8">个人经营</option>
                        <option value="9">采购经理</option>
                        <option value="10">销售经理</option>
                        <option value="7">其他</option>
                    </select>
                    <button type="button" class="ubtn ubtn-blue" id="search_btn">搜索</button>
                <#--</form>-->
            </div>
        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>手机</th>
                    <th>称呼</th>
                    <th>身份类型</th>
                    <th>姓名/单位</th>
                    <th>状态</th>
                    <th width="150">创建时间</th>
                    <th width="230" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list pageInfo.list as user>
                <tr <#if user.type==-1>class="gray"</#if>>
                    <td><input type="checkbox"></td>
                    <td>${user.phone}</td>
                    <td>${user.nickname?default("")}</td>
                    <td>${user.identityTypeName?default("")}</td>
                    <td>${user.name?default("")}</td>
                    <td>${user.typeName?default("")}</td>
                    <td>${user.createTime?datetime}</td>
                    <td class="tc">
                        <a href="javascript:;" class="ubtn ubtn-blue jedit" data-id="${user.id}">查看详情</a>
                        <#if user.type==1 || user.type==0>
                            <a href="javascript:;" class="ubtn ubtn-gray jdel" data-id="${user.id}">禁用账号</a>
                        <#else>
                            <a href="javascript:;" class="ubtn ubtn-gray jenable" data-id="${user.id}">启用账号</a>
                        </#if>

                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=pageInfo url="user/list" params="" />
    </div>
</div>
<#include "./common/footer.ftl"/>
<script>
    var _global = {
        v: {
            disableUrl: '/user/disable/',
            enableUrl: '/user/enable/',
            listUrl:'/user/list',
            flag: false
        },
        fn: {
            init: function() {
                this.bindEvent();
                $("#filterForm").initByUrlParams();
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length,
                        self = this;

                // 启用
                $table.on('click', '.jenable', function() {
                    var url = _global.v.enableUrl + $(this).attr('data-id');
                    layer.confirm('确认启用此账户？', {icon: 3, title: '提示'}, function (index) {
                        $.get(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false; // 阻止链接跳转
                })

                // 禁用
                $table.on('click', '.jdel', function() {
                    var url = _global.v.disableUrl + $(this).attr('data-id');
                    layer.confirm('确认禁用此账户？', {icon: 3, title: '提示'}, function (index) {
                        $.get(url, function (data) {
                            if (data.status == 200) {
                                layer.close(index);
                                window.location.reload();
                            }
                        }, "json");
                    });
                    return false; // 阻止链接跳转
                })

                // 查看详情
                $table.on('click', '.jedit', function() {
                    if (_global.v.flag) {
                        return false;
                    }
                    _global.v.flag = true;
                    self.showUserinfo($(this).data('id'));
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
                        if(val!= null && val != "") {
                            params.push($(this).attr('name') + '=' + val);
                        }
                    })
                    location.href=url+params.join('&');
                })
            },
            showUserinfo: function(id) {
                var showBox = function(data) {
                    var html = [];
                    html.push('<div class="fa-form fa-form-info fa-form-layer">');
                    html.push('<div class="item"> \n <div class="txt">手机：</div> \n <div class="val">', data.phone, '</div> \n </div>');
                    html.push('<div class="item"> \n <div class="txt">称呼：</div> \n <div class="val">', data.nickname, '</div> \n </div>');
                    html.push('<div class="item"> \n <div class="txt">身份类型：</div> \n <div class="val">', data.identityType, '</div> \n </div>');
                    html.push('<div class="item"> \n <div class="txt">姓名/单位：</div> \n <div class="val">', data.name, '</div> \n </div>');
                    html.push('<div class="item"> \n <div class="txt">用户备注：</div> \n <div class="val">', data.remark, '</div> \n </div>');
                    html.push('<div class="item"> \n <div class="txt">注册时间：</div> \n <div class="val">', data.createTime, '</div> \n </div>');
                    html.push('</div>');
                    layer.closeAll();
                    layer.open({
                        area: ['600px'],
                        type: 1,
                        moveType: 1,
                        content: html.join(''),
                        title: '用户详情'
                    });
                }

                // 加载数据
                var k = $.ajax({
                    url: 'user/detail/' + id,
//                    data: {id: id},
                    dataType: 'json',
                    success: function(data) {
                        if (data.status == 200) {
                            showBox(data.data);
                        }
                    },
                    complete: function() {
                        _global.v.flag = false;
                    }
                })

                // loading
                layer.open({
                    area: ['200px'],
                    type: 1,
                    moveType: 1,
                    content: '<div class="layer-loading">加载中...</div>',
                    title: '用户详情',
                    cancel: function() {
                        k.abort();
                    }
                });

            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>