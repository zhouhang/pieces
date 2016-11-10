<!DOCTYPE html>
<html lang="en">
<head>
    <title>寄样列表-boss-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class='wrapper'>

<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>

<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>寄样服务</li>
            <li>寄样列表</li>
        </ul>
    </div>

    <div class="box">
        <div class="tools">
            <div class="filter">
                <form action="" id="searchForm">
                    <div class="group">
                        <label>联系人：</label>
                        <input name="nickname" type="text" class="ipt" placeholder="请输入" value="">
                        <label>联系电话：</label>
                        <input name="phone" type="text" class="ipt" placeholder="联系电话" value="">
                        <label>寄样编号：</label>
                        <input name="code" type="text" class="ipt" placeholder="寄样编号" value="">
                    </div>
                    <div class="group">
                        <label>状态：</label>
                        <select name="status" class="slt">
                            <option value="">全部</option>
                            <option value="0">未受理</option>
                            <option value="1">同意寄样</option>
                            <option value="2">拒绝寄样</option>
                            <option value="3">客户来访</option>
                            <option value="4">已寄样</option>
                            <option value="5">寄样完成</option>
                        </select>
                        <label>是否废弃</label>
                        <select name="abandon" class="slt">
                            <option value="0">正常</option>
                            <option value="1">废弃</option>
                        </select>
                        <button type="button" class="ubtn ubtn-blue" id="search">搜索</button>
                    </div>
                </form>
            </div>

            <!--
            <div class="action-length pb">
                <span>显示</span>
                <select id="pageSize" class="slt">
                    <option  <#if (sendSampleVoPageInfo.pageSize==10)> selected</#if> value="10">10</option>
                    <option  <#if (sendSampleVoPageInfo.pageSize==25)> selected</#if> value="25">25</option>
                    <option  <#if (sendSampleVoPageInfo.pageSize==50)> selected</#if> value="50">50</option>
                    <option  <#if (sendSampleVoPageInfo.pageSize==100)> selected</#if> value="100">100</option>
                </select>
                <span>条结果</span>
            </div>
            -->
        </div>

        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th>寄样编号</th>
                        <th>联系人</th>
                        <th>手机号</th>
                        <th>地区</th>
                        <th>意向商品</th>
                        <th width="120">创建时间</th>
                        <th>状态</th>
                        <th width="170" class="tc">操作</th>
                    </tr>
                </thead>
                <tbody>
                <#list sendSampleVoPageInfo.list as sendSample>
                    <tr>
                        <td>${sendSample.code}</td>
                        <td>${sendSample.nickname}</td>
                        <td>${sendSample.phone}</td>
                        <td>${sendSample.area}</td>
                        <td>${sendSample.intentionText}</td>
                        <td>${(sendSample.createTime?datetime)!}</td>
                        <td><em class="status-${sendSample.status+1}">${sendSample.statusText}</em></td>
                        <td class="tc">
                            <a href="/sample/detail/${sendSample.id?c}" class="ubtn ubtn-blue jedit">查看详情</a>
                            <a href="javascript:;" class="ubtn ubtn-gray jdel" abandon="${sendSample.abandon}" sendId="${sendSample.id?c}">
                                <#if sendSample.abandon==0>
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
    <@pager.pager info=sendSampleVoPageInfo url="sample/list" params=sendSampleVoParams />
    </div>
</div>

<#include "./common/footer.ftl"/>
<script src="assets/plugins/validator/jquery.validator.js"></script>
<script>
    var _global = {
        v: {
            deleteUrl: '/sample/delete/',
        },
        fn: {
            init: function() {
                this.bindEvent();
                $("#searchForm").initByUrlParams();
            },
            bindEvent: function() {
                var $table = $('.table'),
                        $cbx = $table.find('td input:checkbox'),
                        $checkAll = $table.find('th input:checkbox'),
                        count = $cbx.length;
                var $search =$("#search");
                var $pageSize=$("#pageSize");

                // 删除
                $table.on('click', '.jdel', function() {
                    var sendId=$(this).attr('sendId');
                    var abandon=$(this).attr('abandon');
                    var setAbandon=1;
                    var showMsg="确认废弃此寄样单？";
                    if(abandon==1){
                        setAbandon=0;
                        var showMsg="确认恢复此寄样单？";
                    }

                    layer.confirm(showMsg, {icon: 3, title: '提示'}, function (index) {
                        $.ajax({
                            url: _global.v.deleteUrl,
                            data: {"id": sendId, "abandon": setAbandon},
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

                $search.on("click",function(){
                    var condition=$("#searchForm").serializeArray();
                    var conditionText="";
                    $.each(condition, function(i, field){
                        if(field.value!=""){
                            if(conditionText!=""){
                                conditionText=conditionText+"&"+field.name+"="+field.value;
                            }else{
                                conditionText=conditionText+field.name+"="+field.value;
                            }
                        }
                    });
                    if (condition!=""){
                       window.location.href="/sample/list?"+conditionText;
                    }

                })

                $pageSize.on("change",function(){
                    var pageSize=$(this).val();
                    var condition=$("#searchForm").serializeArray();
                    var conditionText="";
                    $.each(condition, function(i, field){
                        if(field.value!=""){
                            if(conditionText!=""){
                                conditionText=conditionText+"&"+field.name+"="+field.value;
                            }else{
                                conditionText=conditionText+field.name+"="+field.value;
                            }
                        }
                    });
                    var url="/sample/list?pageSize="+pageSize;
                    if (conditionText!=""){
                        url=url+"&"+conditionText;
                    }
                    window.location.href=url;

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