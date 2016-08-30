<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>单页面信息-boss-饮片B2B</title>
</head>

<body>
<#include "./inc/header.ftl">

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>支付信息</dt>
                <dd>
                    <a class="curr" href="payment_info.html">支付信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="title">
                <h3><i class="fa fa-chevron-right"></i>E20160620170402</h3>
                <div class="extra">
                    <button type="button" class="btn btn-gray" id="jsuc">成功</button>
                    <button type="button" class="btn btn-gray" id="jfail">失败</button>
                    <a href="#" class="btn btn-red">返回</a>
                </div>
            </div>
            <div class="user-info">
                <h3>交易信息</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>支付流水号：</em>20160819180100001</td>
                            <td><em>订单编号：</em>20160711154600001</td>
                        </tr>
                        <tr>
                            <td><em>订 购 用 户 ：</em>hehuan</td>
                            <td><em>用药单位：</em>速采（武汉）科技有限公司</td>
                        </tr>
                        <tr>
                            <td><em>应 付 金 额 ：</em>&yen; 2100.00</td>
                            <td><em>支付渠道：</em>线下打款</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="user-info">
                <h3>付款信息</h3>

                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><b>支付金额：<span class="price">&yen; 21000.00</span></b></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="space"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>收款账户</b>
                            </td>
                        </tr>
                        <tr>
                            <td><em>开&nbsp; 户&nbsp; 行：</em>中国招商银行</td>
                            <td><em>开户人：</em>周行</td>
                        </tr>
                        <tr>
                            <td><em>收款账号：</em>6222 0210 0107 0070 872</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="space"></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <b>打款账户</b>
                            </td>
                        </tr>
                        <tr>
                            <td><em>开&nbsp; 户&nbsp; 行：</em>中国招商银行</td>
                            <td><em>开户人：</em>周行</td>
                        </tr>
                        <tr>
                            <td><em>收款账号：</em>6222 0210 0107 0070 872</td>
                            <td rowspan="2">
                                <div class="img">
                                    <em>支付凭证：</em>
                                            <span class="thumb">
                                                <img src="uploads/p0.jpg" data-src="http://static.sankobuy.com/files/upload/wool/2016/8/5fcf1a8a-c6d6-4292-bd4e-3150437948e2芡实.jpg" alt="" width="50" height="50">
                                            </span>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><em>支付时间：</em>2016-08-22</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="user-info">
                <h3>支付结果</h3>
                <div class="info">
                    <table>
                        <tbody>
                        <tr>
                            <td><em>支付结果：</em>支付失败</td>
                            <td><em>操作人员：</em>何欢</td>
                        </tr>
                        <tr>
                            <td><em>记录时间：</em>2016-08-22</td>
                            <td><em>失败原因：</em>银行账户上查不到该付款记录。</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<div class="footer">
    <div class="wrap">
        <div class="copyright">
            <p>药优优电商管理系统 版本 1.0  版权所有 &copy; 2016 药优优</p>
        </div>
    </div>
</div><!-- footer end -->

<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="js/jquery.min.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/lightbox.js"></script>
<script>
    var _global = {
        v: {},
        fn: {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                $('#jsuc').on('click', function() {
                    layer.confirm('确认本次交易付款成功？', {
                        title: '付款成功',
                        btn: ['确认','取消'] //按钮
                    }, function(index){
                        layer.close(index);
                    });
                });

                $('#jfail').on('click', function() {
                    layer.confirm('确认本次交易付款失败？', {
                        title: '付款失败',
                        btn: ['确认','取消'] //按钮
                    }, function(pass){
                        layer.prompt({title: '付款失败原因', formType: 2, btn: ['确认']},
                                function(text){
                                    layer.msg('演示完毕！您的口令：'+ pass +' 您最后写下了：'+ text);
                                });
                    });
                });
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>