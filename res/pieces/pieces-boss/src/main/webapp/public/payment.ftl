<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>支付管理-boss-饮片B2B</title>
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
            <div class="skip">
                <span>第</span>
                <a class="fa fa-chevron-left btn btn-gray"></a><input type="text" class="ipt" value="1"><a class="fa fa-chevron-right btn btn-gray"></a>
                <span>页，共</span><em>6</em><span>页</span>
                <i>|</i>
                <span>每页</span>
                <select name="" id="">
                    <option value="">10</option>
                    <option value="">20</option>
                    <option value="">30</option>
                    <option value="">40</option>
                </select>
                <span>个记录，共有 2 个记录</span>
            </div>
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
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td>
                        <select name="" id="">
                            <option value=""></option>
                            <option value="">线下打款</option>
                            <option value="">在线支付</option>
                        </select>
                    </td>
                    <td>
                        <select name="" id="">
                            <option value=""></option>
                            <option value="">待处理</option>
                            <option value="">支付成功</option>
                            <option value="">支付失败</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <tr>
                    <td>01</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>02</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>在线支付</td>
                    <td>支付成功</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>03</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>支付失败</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>04</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>05</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>06</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>07</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>08</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>09</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment_detail.html">查看</a></td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>20160819180100001</td>
                    <td>20160711154600001</td>
                    <td>hehuan</td>
                    <td>速采（武汉）科技有限公司</td>
                    <td>线下打款</td>
                    <td>待处理</td>
                    <td><a href="payment/detail/1">查看</a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->
</div>
<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script>
</script>
</body>
</html>