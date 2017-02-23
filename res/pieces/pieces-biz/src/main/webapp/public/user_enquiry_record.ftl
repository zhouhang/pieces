<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>询价记录-上工好药</title>
</head>

<body>

    <#include "./inc/header-center.ftl"/>
    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>

                <div class="main">
                    <div class="title">
                        <h3>我的询价</h3>
                        <div class="extra"></div>
                    </div>

                    <div class="enquity-list">
                    <#if user_session_biz??&&user_session_biz.certifyStatus!=1>
                        <div class="fa-msg">
                            <i class="fa fa-prompt"></i>
                            <span>您尚未提交企业资质审核，通过审核后方可进行在线下单</span>
                            <a class="btn-ghost" href="/center/certificate/stepOne">提交资格审核</a>
                        </div>
                    </#if>
                        <div class="tab">
                            <span <#if !status?exists>class="curr"</#if>><a href="/center/enquiry/record">全部</a></span>
                            <span <#if status=1>class="curr"</#if>><a href="/center/enquiry/record?status=1">已报价</a></span>
                            <span <#if status=0>class="curr"</#if>><a href="/center/enquiry/record?status=0">未报价</a></span>
                            <span <#if status=2>class="curr"</#if>><a href="/center/enquiry/record?status=2">已过期</a></span>
                        </div>
                        <div class="list">
                            <ul>
                            <#if billsPage??&&billsPage.list?has_content>
                                <#list billsPage.list as bill>
                                    <li>
                                        <div class="hd">
                                            <span>询价单号：${bill.code!}</span>
                                            <span>询价日期：${(bill.createTime?date)!}</span>
                                            <span>报价截止日期：${(bill.expireDate?date)!}</span>
                                        </div>
                                        <div class="col1">
                                            <span class="name">海马、天冬、天麻、党参、煅人中白、制马钱子...</span>
                                            <i class="fa fa-new"></i>
                                            <span class="count">共${bill.enquiryCommoditys?size}个商品</span>
                                        </div>
                                        <div class="col2">
                                            <#if bill.status==0>
                                                <span class="c2">未报价</span>
                                            <#else >
                                                <#if bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                                    <span class="c1">已报价</span>
                                                <#else >
                                                    <span class="c3">已过期</span>
                                                </#if>
                                            </#if>
                                        </div>
                                        <div class="col2"><a href="user_enquiry.html" class="c-blue">
                                            <#if user_session_biz??&&user_session_biz.certifyStatus==1&& bill.status == 1 && bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                                <a href="/center/enquiry/detail?billId=${bill.id!}" class="c-blue">去下单</a>
                                            <#else >
                                                <a href="/center/enquiry/detail?billId=${bill.id!}" class="c-blue">查看详情</a>
                                            </#if>
                                        </a></div>
                                    </li>
                                </#list>
                            <#else>
                                <div class="empty">
                                    <p>您还没有询价记录，现在<a href="/center/enquiry/index">立即询价</a>吧！</p>
                                </div>
                            </#if>
                            </ul>
                        </div>
                    <#if billsPage??>
                        <@p.pager inPageNo=billsPage.pageNum-1 pageSize=billsPage.pageSize recordCount=billsPage.total toURL="/center/enquiry/record?${enquiryRecordParam}"/>
                    </#if>
                    </div>
                </div>
        </div>
    </div><!-- member-box end -->

    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    <script>
        var _blobal = {
            fn: {
                init: function () {
                }
            }
        }
        $(function() {
            _blobal.fn.init();
        });
    </script>
</body>
</html>