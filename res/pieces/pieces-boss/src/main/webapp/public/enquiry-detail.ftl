<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>询价信息-boss-饮片B2B</title>
</head>

<body>
<#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="side">
            <dl>
                <dt>询价信息</dt>
                <dd>
                    <a class="curr" href="enquiry/index">询价信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">
            <div class="title">
                <h3><i class="fa fa-chevron-right"></i>E20160620170402</h3>
                <div class="extra">
                    <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                    <button type="button" id="submit" class="btn btn-red"><#if enquiryBills.status ==1>保存<#else>报价</#if></button>
                </div>
            </div>

            <div class="user-table">
                <div class="caption">
                    <!-- id -->
                    <span>用药单位：<em>${enquiryBills.companyFullName}</em></span>
                    <span>所在地区：<em>${enquiryBills.areaFull}</em></span>
                    <span>联系人姓名：<em>${enquiryBills.userName}</em></span>
                    <span>联系人手机号：<em>${enquiryBills.contactMobile}</em></span>
                    <#if enquiryBills.status ==1>
                    <span>报价时间：<em>${enquiryBills.quotedTime?date}</em></span>
                    <span>报价人：<em>${enquiryBills.quotedName}</em></span>
                    <span>修改时间：<em>${enquiryBills.updateTime?date}</em></span>
                    <span>修改人：<em>${enquiryBills.updateUserName}</em></span>
                    </#if>
                </div>
                <form action="" id="myform" class="chart">
                    <table id="form">
                        <thead>
                        <tr>
                            <th>商品名称</th>
                            <th width="80">切制规格</th>
                            <th width="80">等级</th>
                            <th>产地</th>
                            <th width="90">数量（公斤）</th>
                            <th width="140">期望单价（元/公斤）</th>
                            <th width="100">期望交货日期</th>
                            <th width="120">裸价（元/公斤）</th>
                            <th width="120">报价有效期至</th>
                        </tr>
                        </thead>
                        <tfoot></tfoot>
                        <tbody>
                        <tr>
                        <#list enquiryBills.enquiryCommoditys as commodity>
                            <!-- id -->
                            <td>${commodity.commodityName}</td>
                            <td>${commodity.specs}</td>
                            <td>${commodity.level}</td>
                            <td>${commodity.origin}</td>
                            <td>${commodity.amount}</td>
                            <td>${commodity.expectPrice}</td>
                            <td><#if commodity.expectDate?exists>${commodity.expectDate?date}</#if></td>
                            <td>
                                <input type="text" name="id" style="display: none" value="${commodity.id}">
                                <input type="text" name="myPrice" class="ipt ipt-price" value="${commodity.myPrice}">
                            </td>
                            <td><input type="text" name="expireDate" class="ipt ipt-date" value="<#if commodity.expireDate?exists>${commodity.expireDate?date}</#if>"></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->
<script src="js/laydate/laydate.js"></script>
<script src="/js/common.js"></script>
<script>

    var enquiryPage = {
        v: {},
        fn: {
            init: function () {
                this.pirceInput();
                this.dateInit();
                $("#submit").click(function () {
                    $.ajaxSetup({
                        headers : {
                            'Content-Type' : 'application/json;charset=utf-8'
                        }
                    });

                    $(this).attr("disabled", "disabled");
                    $.post( "<#if enquiryBills.status ==1>enquiry/quotedUpdate<#else>enquiry/quoted</#if>?billsId=${enquiryBills.id}", JSON.stringify(enquiryPage.fn.formatTableData()),function(data){
                        if(data.status == "y") {
                            $.notify({
                                type: 'success',
                                title: '保存成功',
                                text: data.info,
                                delay: 3e3,
                                call: function () {}
                            });
                        }
                    },"json")
                });
            },
            // 裸价
            pirceInput: function () {
                $('#myform').on('keyup', '.ipt-price', function (e) {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                        this.value = isNaN(val) ? '' : val;
                    }
                });
            },
            //日期选择
            dateInit: function () {
                $('.ipt-date').each(function (index) {
                    var id = 'iptDate' + index;
                    this.id = id;
                    laydate({
                        elem: '#' + id,
                        format: 'YYYY-MM-DD',
                        min: laydate.now(),
                        max: '2099-06-16',
                        istime: true,
                        istoday: false,
                        choose: function (datas) {

                        }
                    });
                })


            },
            formatTableData: function () {
                var tableObj = $('#form tbody tr').map(function (i) {
                    var row = {};
                    $(this).find('input').each(function (i) {
                        row[$(this).attr("name")] = $(this).val();
                    });
                    return row;
                }).get();

                return tableObj;
            }
        }
    }
    $(function () {
        enquiryPage.fn.init();
    })


</script>
</body>
</html>