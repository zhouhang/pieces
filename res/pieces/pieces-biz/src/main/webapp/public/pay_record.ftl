<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付记录-饮片B2B</title>
    <link rel="stylesheet" href="css/style.css" />
</head>

<body>
    <#include "./inc/header-center.ftl"/>


    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>支付记录</h3>
                    <div class="extra"></div>
                </div>

                <div class="mybill">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>                                                      
                                    <th width="180">支付流水号</th>
                                    <th width="170">支付订单</th>
                                    <th width="110">应付</th>
                                    <th width="110">实付</th>
                                    <th width="110">支付时间</th>
                                    <th width="100">状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <#if recordPage??&&recordPage?has_content>
                                <#list recordPage.list as payRecord>
                                    <tr>
                                        <td>${payRecord.payCode!}</td>
                                        <td>${payRecord.orderCode!} <span>${payRecord.commodityOverview!}</span></td>
                                        <td>&yen;${payRecord.amountsPayable!}</td>
                                        <td>&yen;${payRecord.actualPayment!}</td>
                                        <td>${payRecord.paymentTime?string("yyyy-MM-dd")}</td>
                                        <td><em class="c-red">${payRecord.statusText!}</em></td>
                                        <td><a href="/center/pay/details/${payRecord.id!}" class="c-blue">查看详情</a></td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>

                <#if recordPage??>
                    <@p.pager inPageNo=recordPage.pageNum-1 pageSize=recordPage.pageSize recordCount=recordPage.total toURL="/center/pay/record"/>
                </#if>

                </div>
            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->




    <script src="js/jquery.min.js"></script>
    <script src="js/layer/layer.js"></script>
    <script src="js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="js/area.js"></script>
    <script>
        var _global = {
            v: {
            },
            fn: {
                init: function() {

                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>