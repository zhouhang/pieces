<!DOCTYPE html>
<html lang="en">
<head>
    <title>配送详情-boss-上工好药</title>
<#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>运单号</dt>
                    <dd>
                        <a class="curr" href="/logistics/index">运单号</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>运单号 ${logistic.lCode} | ${logistic.shipDate?date}</h3>
                    <div class="extra">
                        <a href="/logistics/index" class="btn btn-gray">返回</a>
                    </div>
                </div>

                <div class="groups">
                    <div class="group">
                        <dl>
                            <dt>订单号：${logistic.oCode}</dt>
                            <dd>
                                <p>订单商品总数：${logistic.total}</p>
                                <p>本次发货商品数：${logistic.shipNumber}</p>
                            </dd>
                        </dl>
                    </div>  
                    <div class="group">
                        <dl>
                            <dt>配送信息</dt>
                            <dd>
                                <p>收&nbsp;&nbsp;货&nbsp;&nbsp;人：${addr.consignee}</p>
                                <p>联系电话：${addr.tel}</p>
                                <p>收货地址：${addr.area}${addr.detail}</p>
                            </dd>
                        </dl>
                    </div>                      
                </div>
                
                <div class="chart-info">
                    <h3>发货商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                                <tr>
                                    <th width="110">商品名称</th>
                                    <th width="80">片型</th>
                                    <th>规格等级</th>
                                    <th width="120">产地</th>
                                    <th>数量（公斤）</th>
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
                                    <td>${logisticCommodity.oAmount}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>
</body>
</html>