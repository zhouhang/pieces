<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>订单管理-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>订单管理</h3>
                <div class="extra"><a class="btn btn-red" href="article_info.html"><i class="fa fa-plus"></i>新建订单</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <a class="btn btn-gray" href="#"><i class="fa fa-export"></i>导出</a>
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
            <@p.pager pageInfo=pageInfo  pageUrl="order/index"  params=param />
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th>订单号</th>
                        <th>订购用户</th>
                        <th>用药单位</th>
                        <th>订单金额</th>
                        <th width="170">下单日期</th>
                        <th width="80">状态</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><input type="text" class="ipt date" value="" id="start"> - <input type="text" class="ipt date" value="" id="end"></td>
                        <td>
                            <select name="" id="">
                                <option value=""></option>
                                <option value="">未付款</option>
                                <option value="">等待发货</option>
                                <option value="">已发货</option>
                                <option value="">已完成</option>
                                <option value="">已取消</option>
                                <option value="">已删除</option>
                                <option value="">配送失败</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list pageInfo.list as order>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.code}</td>
                        <td>${order.user.contactName}</td>
                        <td>${order.user.companyFullName}</td>
                        <td>¥${order.amountsPayable}</td>
                        <td><#if order.createrTime?exists>${order.createrTime?datetime}</#if></td>
                        <td>${order.statusText}</td>
                        <td><a href="order_detail.html">查看</a></td>
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
    <script src="js/jquery.min.js"></script>
    <script src="js/laydate/laydate.js"></script>
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
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: laydate.now(), //设定最小日期为当前日期
                        max: '2099-06-16 23:59:59', //最大日期
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            end.min = datas; //开始日选好后，重置结束日的最小日期
                            end.start = datas; //将结束日的初始值设定为开始日
                            $('#start').attr('title', datas);
                        }
                    };
                    var end = {
                        elem: '#end',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: laydate.now(),
                        max: '2099-06-16 23:59:59',
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            start.max = datas; //结束日选好后，重置开始日的最大日期
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);
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