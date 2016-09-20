<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>支付管理-boss-上工好药</title>
</head>

<body>
<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>支付管理</h3>
        </div>
        <div class="pagin">
            <div class="extra">
                <a class="btn btn-gray" href="#"><i class="fa fa-export"></i>导出</a>
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
            </div>
        <@p.pager pageInfo=pageInfo  pageUrl="payment/index"  params=paramGet />
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="70">编号</th>
                    <th>支付流水号</th>
                    <th>订单号</th>
                    <th>订购用户</th>
                    <th>用药单位</th>
                    <th>支付渠道</th>
                    <th>状态</th>
                    <th width="100">操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="payCode" value="${param.payCode}"></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="orderCode" value="${param.orderCode}"></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="userName" value="${param.userName}"></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="companyFullName" value="${param.companyFullName}"></div></td>
                    <td>
                        <select name="" id="">
                            <option value=""></option>
                            <option value="">线下打款</option>
                        </select>
                    </td>
                    <td>
                        <select name="status" id="status">
                            <option value=""></option>
                            <option value="0">待处理</option>
                            <option value="1">支付成功</option>
                            <option value="2">支付失败</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list pageInfo.list as pay>
                <tr>
                    <td>${pay.id}</td>
                    <td>${pay.payCode}</td>
                    <td>${pay.orderCode}</td>
                    <td>${pay.orderUserName}</td>
                    <td>${pay.companyFullName}</td>
                    <td>线下打款</td>
                    <td>${pay.statusText}</td>
                    <td>
                    <@shiro.hasPermission name="pay:info">
                        <a href="/payment/detail/${pay.id}">查看</a>
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
<script>
    $(function () {
        $("#status").val(${vo.param});
        var payment = {
            url:"/payment/index?"
        };

        $('#submit').on('click', function() {
            var $ipts = $('.chart .ipt, .chart select');
            var params = [];
            $ipts.each(function() {
                var val = $.trim(this.value);
                val && params.push($(this).attr('name') + '=' + val);
            })
            location.href=payment.url+params.join('&');
        })

        $("#reset").on("click", function (){
            window.location.href = payment.url;
        });
    })
</script>
</body>
</html>