<!DOCTYPE html>
<html lang="en">
<head>
    <title>新客询价-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>新客询价</h3>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="search_btn"><i
                            class="fa fa-search"></i><span>搜索</span></button>
                </div>
            <@p.pager pageInfo=anonPage  pageUrl="/anon/enquiry"  params=params/>
            </div>
                <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th>消息内容</th>
                        <th>联系人</th>
                        <th>跟进人</th>
                        <th width="100">状态</th>
                        <th width="200">发布时间</th>
                        <th width="200">最后一次跟进时间</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${anonVo.content}" name="content"></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${anonVo.contacts}" name="contacts"></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value="${anonVo.lastFollowName}" name="lastFollowName"></div></td>
                        <td>
                            <select name="" id="">
                                <option value=""></option>
                                <option value="2">已处理</option>
                                <option value="1">未处理</option>
                            </select>
                        </td>
                        <td><input type="text" class="ipt date" value="${anonVo.publishTimeStart}" id="start" name="publishTimeStart"> - <input type="text" class="ipt date" name="publishTimeEnd" value="${anonVo.publishTimeEnd}" id="end"></td>
                        <td><input type="text" class="ipt date" value="${anonVo.lastFollowTimeStart}" id="start2" name="lastFollowTimeStart"> - <input type="text" class="ipt date" name="lastFollowTimeEnd" value="${anonVo.lastFollowTimeEnd}" id="end2"></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list anonPage.list as anon>
                    <tr>
                        <td>${anon.id!}</td>
                        <td>${anon.content!}</td>
                        <td>${anon.contacts!}</td>
                        <td>${anon.lastFollowName!}</td>
                        <td>
                            <#if anon.status == 1>
                                未处理
                            <#else>
                                已处理
                            </#if>
                        </td>
                        <td>${(anon.publishTime?datetime)!}</td>
                        <td><#if anon.lastFollowTime?is_date>${(anon.lastFollowTime?datetime)!}</#if></td>
                        <td><a href="/anon/detail?id=${anon.id}">查看</a></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>

    <#include "./inc/footer.ftl"/>
    <script src="/js/laydate/laydate.js"></script>
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
                    },
                    // 筛选
                    filter: function() {
                        var $ipts = $('.chart .ipt, .chart select');
                        var url="/anon/enquiry?";

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
                                $('#start').attr('title', datas);
                            }
                        };
                        var end = {
                            elem: '#end',
                            choose: function(datas){
                                start.max = datas;
                                $('#end').attr('title', datas);
                            }
                        };
                        laydate(start);
                        laydate(end);

                        var start2 = {
                            elem: '#start2',
                            choose: function(datas){
                                end2.min = datas;
                                end2.start = datas;
                                $('#start2').attr('title', datas);
                            }
                        };
                        var end2 = {
                            elem: '#end2',
                            choose: function(datas){
                                start2.max = datas;
                                $('#end2').attr('title', datas);
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