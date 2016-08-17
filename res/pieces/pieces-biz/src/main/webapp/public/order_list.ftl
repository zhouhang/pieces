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
                <h3>我的订单</h3>
                <div class="extra"></div>
            </div>

            <div class="order-list">
                <table class="tc">
                    <tbody>
                    <tr>
                        <th colspan="4" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span class="name"><a href="user_order_detail.html">艾叶、炙黄芪、炒白芍...</a></span>
                            <span>共10个商品</span>
                        </td>
                        <td class="nl" width="145">
                            <span class="price">¥2203000.00</span>
                            <span>（包含运费￥100.00元 ）</span>
                        </td>
                        <td width="140">
                            <span class="c-red">未付款</span>
                            <span>剩余付款时间</span>
                            <span>9天23时40分</span>
                        </td>
                        <td width="140">
                            <a href="#" class="btn btn-red">付款</a>
                            <span><a href="user_order_detail.html" class="c-blue">查看详情</a></span>
                            <span><a href="#" class="c-blue jremove">取消订单</a></span>
                        </td>
                    </tr>
                    <tr class="space"></tr>

                    <tr>
                        <th colspan="4" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span class="name"><a href="user_order_detail.html">艾叶、炙黄芪、炒白芍...</a></span>
                            <span>共10个商品</span>
                        </td>
                        <td class="nl">
                            <span class="price">¥2203000.00</span>
                            <span>（包含运费￥100.00元 ）</span>
                        </td>
                        <td>
                            <span class="c-red">等待发货</span>
                        </td>
                        <td>
                            <span><a href="user_order_detail.html" class="c-blue">查看详情</a></span>
                        </td>
                    </tr>
                    <tr class="space"></tr>

                    <tr>
                        <th colspan="4" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span class="name"><a href="user_order_detail.html">艾叶、炙黄芪、炒白芍...</a></span>
                            <span>共10个商品</span>
                        </td>
                        <td class="nl">
                            <span class="price">¥2203000.00</span>
                            <span>（包含运费￥100.00元 ）</span>
                        </td>
                        <td>
                            <span class="c-red">已收货</span>
                        </td>
                        <td>
                            <a href="#" class="btn btn-red">确认收货</a>
                            <span><a href="user_order_detail.html" class="c-blue">查看详情</a></span>
                        </td>
                    </tr>
                    <tr class="space"></tr>

                    <tr>
                        <th colspan="4" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span class="name"><a href="user_order_detail.html">艾叶、炙黄芪、炒白芍...</a></span>
                            <span>共10个商品</span>
                        </td>
                        <td class="nl">
                            <span class="price">¥2203000.00</span>
                            <span>（包含运费￥100.00元 ）</span>
                        </td>
                        <td>
                            <span class="c-red">已完成</span>
                        </td>
                        <td>
                            <span><a href="user_order_detail.html" class="c-blue">查看详情</a></span>
                        </td>
                    </tr>
                    <tr class="space"></tr>

                    <tr>
                        <th colspan="4" class="tl">
                            <span>订单单号：201607111621001</span>
                            <span>下单时间：2016-08-10 15:22:54</span>
                        </th>
                    </tr>
                    <tr>
                        <td class="tl nr">
                            <span class="name"><a href="user_order_detail.html">艾叶、炙黄芪、炒白芍...</a></span>
                            <span>共10个商品</span>
                        </td>
                        <td class="nl">
                            <span class="price">¥2203000.00</span>
                            <span>（包含运费￥100.00元 ）</span>
                        </td>
                        <td>
                            <span class="c-red">已取消</span>
                        </td>
                        <td>
                            <span><a href="user_order_detail.html" class="c-blue">查看详情</a></span>
                            <span><a href="#" class="c-blue jremove">取消订单</a></span>
                        </td>
                    </tr>
                    <tr class="space"></tr>
                    </tbody>
                </table>
                <div class="pagin tr">
                    <span class="disabled">上一页</span>
                    <span class="curr">1</span>
                    <a href="?page=2">2</a>
                    <a href="?page=3">3</a>
                    <a href="?page=4">4</a>
                    <a href="?page=5">5</a>
                    <a href="?page=2">下一页</a>
                    <a href="?page=2">尾页</a>
                    <em>共 284 个商品 / 共29页</em>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->

<#include "./inc/footer.ftl"/>
</body>
</html>