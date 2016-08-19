<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>订单详情-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>订单信息</dt>
                    <dd>
                        <a class="curr" href="enquiry.html">订单信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <div class="title">
                    <h3><i class="fa fa-chevron-right"></i>订单 201608111514001 | 2016-08-11 15:14:13</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="button" class="btn btn-gray">修改</button>
                        <button type="button" class="btn btn-gray">发票</button>
                        <button type="button" class="btn btn-gray">配送</button>
                        <button type="submit" class="btn btn-red">重新下单</button>
                    </div>
                </div>

                <div class="groups">
                    <div class="group">
                        <dl>
                            <dt>订单号：201608111514001</dt>
                            <dd>
                                <p>下单日期：2016-08-11 15:14:13</p>
                                <p>订单状态：等待发货</p>
                            </dd>
                        </dl>
                    </div>
                    <div class="group">
                        <dl>
                            <dt>客户信息</dt>
                            <dd>
                                <p>用药单位：速采科技</p>
                                <p>联&nbsp;&nbsp;系&nbsp;&nbsp;人：何欢</p>
                                <p>联系电话：18971437973</p>
                            </dd>
                        </dl>
                    </div>
                    <div class="group">
                        <dl>
                            <dt>配送信息</dt>
                            <dd>
                                <p>收&nbsp;&nbsp;货&nbsp;&nbsp;人：何欢</p>
                                <p>联系电话：18971437973</p>
                                <p>收货地址：湖北省武汉市洪山区鲁磨路光谷银座15楼</p>
                            </dd>
                        </dl>
                    </div>
                </div>

                <div class="chart-info">
                    <h3>订购商品</h3>
                    <div class="chart">
                        <table class="tc">
                            <thead>
                            <tr>
                                <th>商品名称</th>
                                <th width="80">切制规格</th>
                                <th width="80">等级</th>
                                <th>产地</th>
                                <th width="100">期望交货日期</th>
                                <th width="120">数量（公斤）</th>
                                <th width="120">单价（元/公斤）</th>
                                <th width="200">小计（元）</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <td colspan="8">
                                    <div class="summary">
                                        <div class="item">
                                            <span>商品合计：</span>
                                            <em>￥2200000.00</em>
                                        </div>
                                        <div class="item">
                                            <span>运&#12288;&#12288;费：</span>
                                            <em>￥3000.0</em>
                                        </div>
                                        <div class="item">
                                            <span>实际应付：</span>
                                            <em class="price">￥2203000.00</em>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            </tfoot>
                            <tbody>
                            <tr>
                                <td>艾绒</td>
                                <td>个</td>
                                <td>1</td>
                                <td>安徽省</td>
                                <td>2016-06-20</td>
                                <td>60</td>
                                <td>20</td>
                                <td>&yen; 12000</td>
                            </tr>
                            <tr>
                                <td>天南星</td>
                                <td>个</td>
                                <td>2</td>
                                <td>安徽省</td>
                                <td>2016-06-20</td>
                                <td>60</td>
                                <td>20</td>
                                <td>&yen; 12000</td>
                            </tr>
                            <tr>
                                <td>艾绒</td>
                                <td>个</td>
                                <td>1</td>
                                <td>安徽省</td>
                                <td>2016-06-20</td>
                                <td>60</td>
                                <td>20</td>
                                <td>&yen; 12000</td>
                            </tr>
                            <tr>
                                <td>天南星</td>
                                <td>个</td>
                                <td>2</td>
                                <td>安徽省</td>
                                <td>2016-06-20</td>
                                <td>60</td>
                                <td>20</td>
                                <td>&yen; 12000</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="chart-info">
                    <h3>备注历史</h3>
                    <form action="" class="note-form">
                        <div class="txt">订单备注：</div>
                        <div class="cnt"><textarea class="ipt" name="" id="" cols="30" rows="10" placeholder="请填写本次采购另外需要注意的事项。"></textarea></div>
                        <div class="button">
                            <button class="btn btn-gray">提交备注</button>
                        </div>

                        <div class="history">
                            <ul>
                                <li>
                                    <span>2016-08-11</span>
                                    <span>客户备注</span>
                                    <span>蒸后切顶头片、薄片1-2mm、过2#、焦黑色。</span>
                                </li>
                                <li>
                                    <span>2016-08-11</span>
                                    <span>客户备注</span>
                                    <span>这是一条新备注。</span>
                                </li>
                                <li>
                                    <span>2016-08-11</span>
                                    <span>客户备注</span>
                                    <span>这是一条新备注。</span>
                                </li>
                            </ul>
                        </div>
                    </form>

                </div>
            </div>
        </div><!-- fa-floor end -->
    </div>

    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
<!-- footer end -->
    <script src="js/jquery.min.js"></script>
    <script src="js/laydate/laydate.js"></script>
<script>
    var enquiryPage = {
        v: {},
        fn: {
            init: function() {
                this.pirceInput();
                this.dateInit();
            },
            // 裸价
            pirceInput: function() {
                $('#myform').on('keyup', '.ipt-price', function(e) {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                        this.value = isNaN(val) ? '' : val;
                    }
                });
            },
            //日期选择
            dateInit: function () {
                // 重新定位
                $('.ipt-date').on('click', function() {
                    var
                            posX = $(this).offset().left,
                            w = this.offsetWidth,
                            obj = document.getElementById('laydate_box');
                    obj.style.left = posX + w - obj.offsetWidth + 'px';
                })
            },
        }
    }
    $(function() {
        enquiryPage.fn.init();
    })
</script>
</body>
</html>