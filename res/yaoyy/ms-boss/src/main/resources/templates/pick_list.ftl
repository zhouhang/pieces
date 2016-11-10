<!DOCTYPE html>
<html lang="en">
<head>
    <title>选货单列表-boss</title>
    <#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>选货单模块</li>
            <li>选货单列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter" id="filterForm">
                <form action="">
                    <label>选货单编号：</label>
                    <input type="text" name="code"class="ipt" placeholder="请输入">
                    <label>电话：</label>
                    <input type="text" name="phone" class="ipt" placeholder="电话">
                    <label>状态：</label>
                    <select name="status" class="slt">
                        <option value="">全部</option>
                        <option value="0">未受理</option>
                        <option value="1">已受理</option>
                        <option value="4">审核不通过</option>
                        <option value="2">交易未完成</option>
                        <option value="3">交易已完成</option>
                    </select>
                    <label>是否废弃</label>
                    <select name="abandon" class="slt">
                        <option value="0">正常</option>
                        <option value="1">废弃</option>
                    </select>
                    <button type="button" id="search" class="ubtn ubtn-blue">搜索</button>
                </form>
            </div>

        </div>

        <div class="table">
            <table>
                <thead>
                <tr>
                    <th><input type="checkbox" class="cbx"></th>
                    <th>选货单编号</th>
                    <th>客户姓名</th>
                    <th>客户电话</th>
                    <th>状态</th>
                    <th>下单时间</th>
                    <th width="170" class="tc">操作</th>
                </tr>
                </thead>
                <tbody>
                <#list pickVoPageInfo.list as pick>
                <tr>
                    <td><input type="checkbox" class="cbx"></td>
                    <td>${pick.code}</td>
                    <td>${pick.nickname}</td>
                    <td>${pick.phone}</td>
                    <td><em class="status-${pick.status+1}">${pick.statusText}</em></td>
                    <td>${(pick.createTime?datetime)!}</td>
                    <td class="tc">
                        <a href="pick/detail/${pick.id}" class="ubtn ubtn-blue jedit">查看详情</a>
                        <a href="javascript:;" pid="${pick.id}" abandon="${pick.abandon}"class="ubtn ubtn-gray jdel">
                            <#if pick.abandon==0>
                                废弃
                            <#else >
                                恢复
                            </#if>
                        </a>
                    </td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    <#import "./module/pager.ftl" as pager />
    <@pager.pager info=pickVoPageInfo url="pick/list" params="" />
    </div>
</div>

<#include "./common/footer.ftl"/>
<script>
    var _global = {
        v: {
            deleteUrl: 'pick/delete/',
            listUrl:'pick/list'
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
                        count = $cbx.length;

                // 删除
                $table.on('click', '.jdel', function() {
                    var pId=$(this).attr('pId');
                    var abandon=$(this).attr('abandon');
                    var setAbandon=1;
                    var showMsg="确认废弃此选货单？";
                    if(abandon==1){
                        setAbandon=0;
                        var showMsg="确认恢复此选货单？";
                    }

                    layer.confirm(showMsg, {icon: 3, title: '提示'}, function (index) {
                        $.ajax({
                            url: _global.v.deleteUrl,
                            data: {"id": pId, "abandon": setAbandon},
                            type: "POST",
                            success: function (data) {
                                if (data.status == "200") {
                                    window.location.reload();
                                }
                                layer.close(index);
                            }
                        });
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
                $("#search").on('click',function () {
                    var $ipts = $('.filter .ipt, .filter select');
                    var url=_global.v.listUrl+"?";
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