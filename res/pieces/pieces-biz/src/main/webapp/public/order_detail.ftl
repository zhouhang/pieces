<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>订单列表-饮片B2B</title>
</head>

<body>

<#include "./inc/header-center.ftl"/>

<!-- member-box start -->
<div class="member-box">
    <div class="wrap">
    <#include "./inc/side-center.ftl"/>
        <div class="main">
            <div class="title">
                <h3>订单详情</h3>
                <div class="extra"></div>
            </div>

            <div class="order-list order-detail">
                <table class="tc">
                    <tbody>
                    <tr>
                        <th colspan="3" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span>收&nbsp;&nbsp;货&nbsp;&nbsp;人：<em>何欢</em></span>
                            <span>联系方式：<em>18900557973</em></span>
                            <span>收货地址：<em>湖北省武汉市汉阳区武汉市汉阳区知音西村56号303</em></span>
                        </td>
                        <td class="tl nl nr">
                            <span>商品合计：<em class="price">¥2203000.00</em></span>
                            <span>运　　费：<em class="price">¥150.00</em></span>
                        </td>
                        <td class="nl">
                            <a href="#" class="btn btn-red">付款</a>
                            <span>剩余付款时间</span>
                            <span>9天23时40分</span>
                            <a href="#" class="c-blue jremove">取消订单</a>
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
                            <th width="100">单价<span>（元/公斤）</span></th>
                            <th width="130">小计<span>（元）</span></th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                        <tr>
                            <td>巴戟肉</td>
                            <td>薄片</td>
                            <td>1</td>
                            <td>安徽省</td>
                            <td>2016-08-20</td>
                            <td>6000</td>
                            <td>¥20.00</td>
                            <td>¥120000.00</td>
                            <td rowspan="2"><span class="c-red">待付款</span></td>
                        </tr>
                        <tr>
                            <td>艾绒</td>
                            <td>个</td>
                            <td>2</td>
                            <td>湖北省</td>
                            <td>2016-08-20</td>
                            <td>6000</td>
                            <td>¥20.00</td>
                            <td>¥120000.00</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

<#include "./inc/footer.ftl"/>
</body>
</html>