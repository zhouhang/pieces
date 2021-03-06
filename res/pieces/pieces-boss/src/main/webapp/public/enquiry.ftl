<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>询价管理-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>询价管理</h3>
            <div class="extra">
                <a class="btn btn-red" href="/enquiry/customer"><i class="fa fa-plus"></i>新建报价</a>
            </div>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="search"><i class="fa fa-search"></i><span>搜索</span>
                </button>
            </div>
            <@p.pager pageInfo=pageInfo  pageUrl="/enquiry/index"  params=paramGet />
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th>询价单号</th>
                        <th>询价会员名</th>
                        <th width="200">询价日期</th>
                        <th width="120">状态</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <div class="ipt-wrap"><input type="text" name="code" class="ipt" value="${param.code}"></div>
                        </td>
                        <td>
                            <div class="ipt-wrap"><input type="text" name="userName" class="ipt" value="${param.userName}"></div>
                        </td>
                        <td><input type="text" class="ipt date" value=" <#if param.startTime?exists>${param.startTime?datetime}</#if>" name="startTime" id="start"> -
                            <input type="text" class="ipt date" value="<#if param.endTime?exists>${param.endTime?datetime}</#if>" name="endTime" id="end"></td>
                        <td>
                            <select name="status" id="status">
                                <option value=""></option>
                                <option value="1">已报价</option>
                                <option value="0">未报价</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list pageInfo.list as enquiry>
                <tr>
                    <td>${enquiry.id}</td>
                    <td>${enquiry.code}</td>
                    <td>${enquiry.userName}</td>
                    <td>${enquiry.createTime?datetime}</td>
                    <td><#if enquiry.status ==1>已报价<#else><span class="c-red">未报价</span></#if></td>
                    <td>
                        <@shiro.hasPermission name="enquiry:info">
                        <a href="enquiry/${enquiry.id}">查看</a>
                        </@shiro.hasPermission>
                    </td>
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
<script src="${urls.getForLookupPath('/js/laydate/laydate.js')}"></script>
<script>
    //定义根变量
    !(function ($) {
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
                    page.fn.eventInit();

                    $("#status").val("${param.status}");
                },
                //事件初始化
                eventInit: function () {
                    // 清空输入数据
                    $("#reset").click(function(){
                        window.location.href="/enquiry/index";
                        $('.chart .ipt, .chart select').val("")
                    })

                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/enquiry/index?";
                    $('#search').on('click', function() {
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
                        istoday: false,
                        choose: function (datas) {
                            end.min = datas;
                            end.start = datas;
                        }
                    };
                    var end = {
                        elem: '#end',
                        istoday: false,
                        choose: function (datas) {
                            start.max = datas; 
                        }
                    };
                    laydate(start);
                    laydate(end);
                }
            }
        }
        //加载页面js
        $(function () {
            page.fn.init();
        });
    })(jQuery);
</script>
</body>
</html>