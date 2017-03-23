<!DOCTYPE html>
<html lang="en">
<head>

    <title>boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="guide">
        <div class="wrap">
            <ul>
                <li>
                    <a href="#" class="ico people"><em>${newUser}</em>今日注册账号</a>
                </li>
                <li>
                    <a href="#" class="ico sale"><em>${newEnquiry}</em>今日新增询价</a>
                </li>
                <li>
                    <a href="#" class="ico cart"><em>${newOrder}</em>今日新增订单</a>
                </li>
                <li class="op">
                    <a href="/user/add" class="btn">新增终端客户</a>
                    <a href="/user/add?userType=2" class="btn">新增代理商</a>
                    <a href="/enquiry/customer" class="btn">新建报价</a>
                    <a href="/order/customer" class="btn">新建订单</a>
                </li>
            </ul>
        </div>
    </div>

    <div class="fa-floor">
        <div class="wrap">
            <div class="fa-tab">
                <span class="curr">最新询价</span>
                <span>最新订单</span>
                <span>最新支付</span>
                <span>最新账单申请</span>
                <span>最新认证申请</span>
                <span>最新合作伙伴申请</span>
            </div>

            <div class="fa-tabcont">
                <div class="item">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>询价单号</th>
                                <th>询价会员名</th>
                                <th width="180">询价日期</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list enquiryList.list as enquiry>
                            <tr>
                                <td>${enquiry.id}</td>
                                <td>${enquiry.code}</td>
                                <td>${enquiry.userName}</td>
                                <td>${enquiry.createTime?datetime}</td>
                                <td><#if enquiry.status ==1>已报价<#else><span class="c-red">未报价</span></#if></td>
                                <td>
                                    <@shiro.hasPermission name="enquiry:info">
                                        <a href="enquiry/${enquiry.id}">查看</a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="more">
                        <a href="enquiry/index" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

                <div class="item hide">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>订单号</th>
                                <th>订购用户</th>
                                <th>用药单位</th>
                                <th>订单金额</th>
                                <th width="180">下单日期</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderList.list as order>
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.code}</td>
                                <td>${order.user.contactName}</td>
                                <td>${order.user.companyFullName}</td>
                                <td>¥${order.amountsPayable}</td>
                                <td><#if order.createrTime?exists>${order.createrTime?datetime}</#if></td>
                                <td>${order.statusText}</td>
                                <td>
                                    <@shiro.hasPermission name="order:info">
                                        <a href="/order/detail/${order.id}">查看</a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="more">
                        <a href="/order/list" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

                <div class="item hide">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>支付流水号</th>
                                <th>订单号</th>
                                <th>订购用户</th>
                                <th>用药单位</th>
                                <th>支付渠道</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list paymentList.list as pay>
                            <tr>
                                <td>${pay.id}</td>
                                <td>${pay.payCode}</td>
                                <td>${pay.orderCode}</td>
                                <td>${pay.orderUserName}</td>
                                <td>${pay.companyFullName}</td>
                                <td>${pay.payTypeName}</td>
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
                    <div class="more">
                        <a href="/payment/index" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

                <div class="item hide">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>账单编号</th>
                                <th>账期订单</th>
                                <th>订购用户</th>
                                <th>用药单位</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list billList.list as bill>
                            <tr>
                                <td>${bill.id}</td>
                                <td>${bill.code}</td>
                                <td>${bill.orderCode}</td>
                                <td>${bill.orderUser}</td>
                                <td>${bill.orderCompany}</td>
                                <td>${bill.statusText}</td>
                                <td>
                                    <@shiro.hasPermission name="bill:info">
                                        <a href="/account/bill/detail?id=${bill.id}">查看</a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="more">
                        <a href="/account/bill/index" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

                <div class="item hide">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>会员名</th>
                                <th>跟进人</th>
                                <th width="180">提交时间</th>
                                <th width="180">跟进时间</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list certifyList.list as certifyRecord>
                            <tr>
                                <td>${certifyRecord.id}</td>
                                <td>${certifyRecord.userName}</td>
                                <td>${(certifyRecord.createTime?datetime)!}</td>
                                <td>${certifyRecord.statusText}</td>
                                <td>${certifyRecord.followName?default("")}</td>
                                <td><#if certifyRecord.followTime?exists>${(certifyRecord.followTime?datetime)!}</#if></td>
                                <td><a href="/certify/info/${certifyRecord.id}">查看</a></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="more">
                        <a href="/certify/list" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

                <div class="item hide">
                    <div class="chart tc">
                        <table>
                            <thead>
                            <tr>
                                <th width="70">编号</th>
                                <th>联系人</th>
                                <th>电话</th>
                                <th>跟进人</th>
                                <th width="180">申请时间</th>
                                <th width="180">最后一次跟进时间</th>
                                <th width="120">状态</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list recruitList.list as recruit>
                            <tr>
                                <td>${recruit.id!}</td>
                                <td>${recruit.name!}</td>
                                <td>${recruit.phone!}</td>
                                <td>${(recruit.createTime?datetime)!}</td>
                                <td>
                                    <#if recruit.status == 0>
                                        未处理
                                    <#else>
                                        已处理
                                    </#if>
                                </td>
                                <td>${recruit.lastFollowName!}</td>
                                <td><#if recruit.lastFollowTime! && recruit.lastFollowTime?is_date>${recruit.lastFollowTime?datetime}</#if></td>
                                <td><a href="/recruit/detail?id=${recruit.id}">查看</a></td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>
                    </div>
                    <div class="more">
                        <a href="/recruit/index" class="c-blue">查看更多>></a>
                    </div>
                </div> <!-- end item -->

            </div>
        </div>
    </div>
    <#include "./inc/footer.ftl"/>
    <script>
        !(function($, window) {
            var _global = {
                init: function() {
                    this.tab();
                },
                tab: function() {
                    var that = this,
                            $tab = $('.fa-tab'),
                            $item = $('.fa-tabcont').find('.item');

                    $tab.on('click', 'span', function() {
                        var $el = $item.eq($(this).index());

                        $(this).addClass('curr').siblings().removeClass('curr');
                        $el.show().siblings().hide();
                    })
                }
            }

            _global.init();
        })(window.jQuery);
    </script>
</body>
</html>