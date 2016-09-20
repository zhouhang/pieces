<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>账单管理-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>账单管理</h3>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=pageInfo  pageUrl="account/bill/index"  params=paramGet />
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th>账单编号</th>
                        <th>账期订单</th>
                        <th>订购用户</th>
                        <th>用药单位</th>
                        <th>状态</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input type="text" name="code" class="ipt" value="${param.code}"></div></td>
                        <td><div class="ipt-wrap"><input type="text" name="orderCode" class="ipt" value="${param.orderCode}"></div></td>
                        <td><div class="ipt-wrap"><input type="text" name="orderUser" class="ipt" value="${param.orderUser}"></div></td>
                        <td><div class="ipt-wrap"><input type="text" name="orderCompany" class="ipt" value="${param.orderCompany}"></div></td>
                        <td>
                            <select name="status" id="status">
                                <option value=""></option>
                                <option value="-1">拒绝</option>
                                <option value="0">申请中</option>
                                <option value="1">未完结</option>
                                <option value="2">已完结</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list pageInfo.list as bill>
                    <tr>
                        <td>${bill.id}</td>
                        <td>${bill.code}</td>
                        <td>${bill.orderCode}</td>
                        <td>${bill.orderUser}</td>
                        <td>${bill.orderCompany}</td>
                        <td>${bill.statusText}</td>
                        <td>
                        <@shiro.hasPermission name="bill:info">
                            <a href="/account/bill/detail?id=${bill.id}">查看</a>
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
                    page.fn.filter();
                    $("#status").val(${param.status});
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="account/bill/index?";

                    $('#submit').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+params.join('&');
                    })

                    $("#reset").on("click", function (){
                        window.location.href = url;
                    });
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