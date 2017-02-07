<!DOCTYPE html>
<html lang="en">
<head>
    <title>合作伙伴申请-boss-上工好药</title>
<#include "./inc/meta.ftl"/>
</head>

<body>
<#include "./inc/header.ftl"/>


<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>合作伙伴申请</h3>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="search_btn"><i
                        class="fa fa-search"></i><span>搜索</span></button>
            </div>
        <@p.pager pageInfo=recruitPage  pageUrl="/recruit/index"  params=params/>
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="70">编号</th>
                    <th>联系人</th>
                    <th>电话</th>
                    <th width="200">申请时间</th>
                    <th width="100">状态</th>
                    <th>跟进人</th>
                    <th width="200">最后一次跟进时间</th>
                    <th width="100">操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value="${recruitAgentVo.name!}" name="name"></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value="${recruitAgentVo.phone!}" name="phone"></div></td>
                    <td><input type="text" class="ipt date" value="<#if recruitAgentVo.publishTimeStart?? && recruitAgentVo.publishTimeStart?is_date>${recruitAgentVo.publishTimeStart?date!}</#if>" id="start" name="publishTimeStart"> -
                        <input type="text" class="ipt date" name="publishTimeEnd" value="<#if recruitAgentVo.publishTimeEnd?? && recruitAgentVo.publishTimeEnd?is_date>${recruitAgentVo.publishTimeEnd?date!}</#if>" id="end"></td>
                    <td>
                        <select name="status" id="status">
                            <option value=""></option>
                            <option value="1">已处理</option>
                            <option value="0">未处理</option>
                        </select>
                    </td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value="${recruitAgentVo.lastFollowName!}" name="lastFollowName"></div></td>
                    <td><input type="text" class="ipt date" value="<#if recruitAgentVo.lastFollowTimeStart?? && recruitAgentVo.lastFollowTimeStart?is_date>${recruitAgentVo.lastFollowTimeStart?date!}</#if>" id="start2" name="lastFollowTimeStart"> -
                        <input type="text" class="ipt date" name="lastFollowTimeEnd" value="<#if recruitAgentVo.lastFollowTimeEnd?? && recruitAgentVo.lastFollowTimeEnd?is_date>${recruitAgentVo.lastFollowTimeEnd?date!}</#if>" id="end2"></td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list recruitPage.list as recruit>
                <tr>
                    <td>${recruit.id!}</td>
                    <td>${recruit.name!}</td>
                    <td>${recruit.phone!}</td>
                    <td>${(recruit.createTime?datetime)!}</td>
                    <td>
                        <#if recruit.status == 0>
                            未处理
                        <#else>
                            已处理
                        </#if>
                    </td>
                    <td>${recruit.lastFollowName!}</td>
                    <td><#if recruit.lastFollowTime! && recruit.lastFollowTime?is_date>${recruit.lastFollowTime?datetime}</#if></td>
                    <td><a href="/recruit/detail?id=${recruit.id}">查看</a></td>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->
</div>

<#include "./inc/footer.ftl"/>
<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
<script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page"
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    page.fn.filter();

                    $("#status").val(${recruitAgentVo.status});

                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/recruit/index?";

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+params.join('&');
                    })
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        choose: function(datas){
                            end.min = datas;
                            end.start = datas;
                        }
                    };
                    var end = {
                        elem: '#end',
                        choose: function(datas){
                            start.max = datas;
                        }
                    };
                    laydate(start);
                    laydate(end);

                    var start2 = {
                        elem: '#start2',
                        choose: function(datas){
                            end2.min = datas;
                        }
                    };
                    var end2 = {
                        elem: '#end2',
                        choose: function(datas){
                            start2.max = datas;
                        }
                    };
                    laydate(start2);
                    laydate(end2);
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