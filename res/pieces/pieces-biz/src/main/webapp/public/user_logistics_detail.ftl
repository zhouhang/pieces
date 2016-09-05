<!DOCTYPE html>
<html lang="en">
<head>
    <title>我的物流-饮片B2B</title>
<#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header-center.ftl"/>


    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>我的物流</h3>
                    <div class="extra"></div>
                </div>

                <div class="order-list order-detail">
                    <table class="tc">
                        <tbody>
                            <tr>
                                <th colspan="2" class="tl">
                                    <span>运单号：${logistic.lCode}</span>
                                    <span>订单号：${logistic.oCode}</span>
                                    <span>发货日期：${logistic.shipDate?date}</span>
                                </th>
                            </tr>
                            <tr>
                                <td class="tl nr">
                                    <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：<em>${addr.consignee}</em></span>
                                    <span>联系方式：<em>${addr.tel}</em></span>
                                    <span>收货地址：<em>${addr.area}${addr.detail}</em></span>
                                </td>
                                <td class="tl nl">
                                    <span>订单商品总数：<em>${logistic.total}个</em></span>
                                    <span>本次发货商品数：<em>${logistic.shipNumber}</em></span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="fa-table order-detail-list">
                    <div class="fa-chart">
                        <table>
                            <thead>
                                <tr>
                                    <th width="130">商品名称</th>
                                    <th width="70">切制规格</th>
                                    <th width="70">等级</th>
                                    <th width="100">产地</th>
                                    <th width="90">期望交货日期</th>
                                    <th width="90">数量<span>（公斤）</span></th>
                                </tr>
                            </thead>
                            <tfoot></tfoot>
                            <tbody>
                            <#list logisticCommoditys as logisticCommodity>
                                <tr>
                                    <td>${logisticCommodity.name}</td>
                                    <td>${logisticCommodity.spec}</td>
                                    <td>${logisticCommodity.level}</td>
                                    <td>${logisticCommodity.originOf}</td>
                                    <td>${logisticCommodity.expectDate?date}</td>
                                    <td>${logisticCommodity.oAmount}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->



    <script src="js/jquery.min.js"></script>
    
</body>
</html>