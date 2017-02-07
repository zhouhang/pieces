<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>选择客户-boss-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>请选择客户</h3>
                <div class="extra"><a class="btn btn-gray" href="/order/index">返回</a></div>
            </div>
            <div class="pagin">
                <div class="extra"> 
                    <button class="btn btn-gray" type="button"  id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=customerPage  pageUrl="/order/customer"  params=customerParams/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th>会员名</th>
                            <th>用药单位</th>
                            <th>代理商</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input type="text"  name="userName" class="ipt" value="${userVo.userName!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="companyFullName" class="ipt" value="${userVo.companyFullName!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" name="agentName" value="${userVo.agentName!}"></div></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>

                    <#list customerPage.list as customer>
                        <tr>
                            <td agentId="${customer.agentId}">${customer.id!}</td>
                            <td>${customer.userName!}</td>
                            <td>${customer.companyFullName!}</td>
                            <td>${customer.agentName!}</td>
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

                    page.fn.createOrder();


                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/order/customer?"
                    $('#submit').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                },
                createOrder:function(){
                    $(".tc>tbody>tr").click(function(){
                        var customerId = $(this).find("td:first").text();
                        var agentId = $(this).find("td:first").attr("agentId");
                        location.href="/order/create/"+customerId+"?agentId="+agentId;
                    })

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