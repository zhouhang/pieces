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

                    <div class="fa-table">

                    <#if userValidate.status == 1>
                        <div class="fa-msg">
                            <i class="fa fa-prompt"></i>
                            <span>您尚未提交企业资质审核，通过审核后方可进行在线下单</span>
                            <a class="btn-ghost" href="/center/certificate/stepOne">提交资格审核</a>
                        </div>
                    <#elseif userValidate.status == 2>
                        <div class="fa-msg">
                            <i class="fa fa-prompt"></i>
                            <span>您的企业资质正在审核中，通过审核后方可进行在线下单,如有问题请致电0558-5120088</span>
                        </div>
                    <#elseif userValidate.status == 3>
                        <div class="fa-msg">
                            <i class="fa fa-prompt"></i>
                            <span>您的企业资质审核未通过，无法进行下单.</span>
                            <em>不通过原因:${userValidate.msg!}</em>
                            <a class="btn-ghost" href="/center/certificate/stepOne">重新提交资质审核</a>
                        </div>
                    <#elseif userValidate.status == 4>
                        <div class="fa-msg">
                            <i class="fa fa-prompt"></i>
                            <span>您还没有绑定终端客户,暂时无法进行下单. 如果您已提交终端客户资料,请联系客服人员帮您进行绑定.客服电话:0558-5120088</span>
                        </div>
                    </#if>
                        <div class="fa-chart enquity-detail">
                            <div class="hd">
                                <span>询价单号：${bill.code!}</span>
                                <span>询价日期：${(bill.createTime?date)!}</span>
                                <span>报价截止日期：<#if bill.expireDate?exists>${(bill.expireDate?date)!}</#if></span>
                            </div>
                            <form action="" id="enquiryForm">
                                <input  id="billId" type="hidden" value="" name="billId">
                                <table>
                                    <thead>
                                    <tr>
                                        <th width="60"><input type="checkbox"></th>
                                        <th width="160">商品名称</th>
                                        <th width="120">片型</th>
                                        <th width="300">规格等级</th>
                                        <th width="120">产地</th>
                                        <th>单价<span>(元/公斤)</span></th>
                                    </tr>
                                    </thead>
                                    <tbody class="enquity-commodity">
                                    <#if bill.status==0>
                                    <!-- 未报价 -->
                                        <#list bill.enquiryCommoditys as commodity>
                                        <tr>
                                            <td class="td w1"><input type="checkbox" name="commodity" disabled></td>
                                            <td class="td w2">
                                                <#if commodity.id?exists>
                                                    <a href="/commodity/${commodity.id}" target="_blank">${commodity.commodityName!}</a>
                                                <#else >
                                                ${commodity.commodityName!}
                                                </#if>
                                            </td>
                                            <td class="td w3">${commodity.specs!}</td>
                                            <td class="td w4">${commodity.level!}</td>
                                            <td class="td w5">${commodity.origin!}</td>
                                            <td class="td w6"></td>
                                        </tr>
                                        </#list>
                                    <#else >
                                        <#list bill.enquiryCommoditys as commodity>
                                            <#if commodity.myPrice??&&(commodity.myPrice != 0)&&commodity.expireDate??&&((commodity.expireDate?date gte .now?date) || (commodity.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                            <tr>
                                                <td class="td w1">
                                                    <#if user_session_biz??&&user_session_biz.certifyStatus==1>
                                                        <input type="checkbox" name="commodity" value="${commodity.id!}">
                                                    <#else>
                                                        <input type="checkbox" name="commodity"  disabled>
                                                    </#if>
                                                </td>
                                                <td class="td w2">${commodity.commodityName!}</td>
                                                <td class="td w3">${commodity.specs!}</td>
                                                <td class="td w4">${commodity.level!}</td>
                                                <td class="td w5">${commodity.origin!}</td>
                                                <td class="td w6">${commodity.myPrice!}</td>
                                            </tr>
                                            <#else>
                                            <tr>
                                                <td class="td w1"><input type="checkbox" name="commodity" disabled></td>
                                                <td class="td w2">${commodity.commodityName!}</td>
                                                <td class="td w3">${commodity.specs!}</td>
                                                <td class="td w4">${commodity.level!}</td>
                                                <td class="td w5">${commodity.origin!}</td>
                                                <td class="td w6"><#if commodity.myPrice??&&commodity.myPrice == 0>
                                                    -<#else>${commodity.myPrice!}</#if></td>
                                            </tr>
                                            </#if>
                                        </#list>
                                    </#if>
                                    </tbody>
                                </table>
                                <div class="ft">
                                    <span>共 <em>${bill.enquiryCommoditys?size}</em> 个商品，
                                <#if bill.status==0>
                                    <em id="notPriceCount">1</em> 个未报价，<em id="buysCount">9</em> 个可下单。</span>
                                    <button class="btn btn-gray" type="button" id="buyBtn" disabled >下单</button>
                                    <button class="btn btn-white" type="button" id="exportBtn">导出报价表</button>
                                    <#--<span class="c2">未报价</span>-->
                                <#else >
                                    <#if bill.expireDate?exists&&bill.expireDate?is_date&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                                        <em id="notPriceCount">1</em> 个未报价，<em id="buysCount">9</em> 个可下单。</span>
                                        <button class="btn btn-red" type="button" id="buyBtn">下单</button>
                                        <button class="btn btn-white" type="button" id="exportBtn">导出报价表</button>
                                        <#--<span class="c1">已报价</span>-->
                                    <#else >
                                        <em id="notPriceCount">1</em> 个未报价，<em id="buysCount">9</em> 个可下单。</span>
                                        <button class="btn btn-red" type="button" id="enquiryBtn">重新询价</button>
                                        <button class="btn btn-white" type="button" id="exportBtn">导出报价表</button>
                                        <#--<span class="c3">已过期</span>-->
                                    </#if>
                                </#if>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
    </div><!-- member-box end -->
    <form action="/center/order/md5" method="post" id="orderForm">
        <input type="hidden" name="commodityIds" id="commodityIds" value="">
    </form>
    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    <script>

        var page = {
            v: {
            },
            fn: {
                init: function() {
                    this.checkAll();
                    this.submit();
                    this.count();
                },
                count: function(){
                    var notPrice = $('.enquity-commodity').find(".w6:contains('-')");//未报价
                    var buys = $('.enquity-commodity').find('td input:not(:disabled)')//可下单
                    $("#notPriceCount").html(notPrice.size());
                    $("#buysCount").html(buys.size());
                },
                // 全选
                checkAll: function() {
                    var $cbxAll = $('.enquity-detail').find('th input:not(:disabled)'),
                            $cbxs = $('.enquity-detail').find('td input:not(:disabled)'),
                            amount = $cbxs.length, // 总个数
                            total  = 0; // 当前选中的个数

                    // 单选
                    $cbxs.on('click', function() {
                        total += this.checked ? 1 : -1;
                        $cbxAll.prop('checked', total === amount);

                    }).each(function() {
                        // 统计已选个数
                        total += this.checked ? 1 : 0;
                        autoDisableBtn(total)
                    });

                    // 全选
                    $cbxAll.on('click', function() {
                        var icheck = this.checked;
                        $cbxs.each(function() {
                            this.checked = icheck;
                        });

                        total = icheck ? amount : 0;
                        autoDisableBtn(total)
                    })

                    function autoDisableBtn(check) {
                        if (check ==0 && $("#buyBtn")) {
                            $("#buyBtn").attr({"disabled":"disabled"}).addClass("btn-gray").removeClass("btn-red");
                        } else {
                            $("#buyBtn").removeAttr("disabled").removeClass("btn-gray").addClass("btn-red");
                        }
                    }

                    $cbxAll.trigger("click");

                },
                submit: function() {
                    // 重新询价
                    $("#enquiryBtn").click(function () {
                        window.location.href = "/cart/reEnquiry?billId=${bill.id!}";
                    })

                    //下单
                    $("#buyBtn").click(function () {
                        var commodityStr = [], commodityIds;
                        var $cbxs = $('.enquity-commodity').find('td input:not(:disabled)')

                        $cbxs.each(function() {
                            this.checked && commodityStr.push(this.value);
                        })

                        if (commodityStr.length === 0) {
                            $.notify({
                                type: 'warn',
                                title: '提示',
                                text: '请先勾选要订购的商品',
                                delay: 3e3
                            });
                            return false;
                        }
                        commodityIds = commodityStr.join(',');
                        if (commodityIds) {
                            $("#commodityIds").val(commodityIds);
                            $("#orderForm").submit();
                        }
                    })

                    //导出报价单
                    $("#exportBtn").click(function () {
                        window.location.href = "/center/enquiry/download?billId=${bill.id!}";
                    })
                }
            }
        }
        $(function() {
            page.fn.init();
        })
    </script>
</body>
</html>