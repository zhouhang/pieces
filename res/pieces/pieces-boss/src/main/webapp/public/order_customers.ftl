<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>选择客户-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>请选择客户</h3>
                <div class="extra"><a class="btn btn-gray" href="customers-add.html">返回</a></div>
            </div>
            <div class="pagin">
                <div class="extra"> 
                    <button class="btn btn-gray" type="button" onclick="" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=customerPage  pageUrl="order/customer"  params=customerParams/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th>会员名</th>
                            <th>用药单位</th>
                            <th>所在地区</th>
                            <th width="110">联系人</th>
                            <th>手机号</th>
                            <th width="170">注册日期</th>
                            <th width="130">是否与ERP关联</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input type="text"  name="userName" class="ipt" value="${userVo.userName!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="companyFullName" class="ipt" value="${userVo.companyFullName!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" name="areaFull" value="${userVo.areaFull!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" name="contactName" value="${userVo.contactName!}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" name="contactMobile" value="${userVo.contactMobile!}"></div></td>
                            <td><input type="text" class="ipt date" name="startDate" value="${userVo.startDate!}" id="start"> - <input type="text" name="endDate"  value="${userVo.endDate!}"class="ipt date"  id="end"></td>
                            <td>
                                <select name="bindErp" id="bindErp">
                                    <option <#if (!userVo.bindErp??)>selected</#if>
                                            value=""></option>
                                    <option <#if
                                            (userVo.bindErp??&&!userVo.bindErp)>selected</#if>
                                            value="false">否</option>
                                    <option <#if
                                            (userVo.bindErp??&&userVo.bindErp)>selected</#if>
                                            value="true">是</option>
                                </select>
                            </td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>

                    <#list customerPage.list as customer>
                        <tr>
                            <td>${customer.id!}</td>
                            <td>${customer.userName!}</td>
                            <td>${customer.companyFullName!}</td>
                            <td>${customer.areaFull!}</td>
                            <td>${customer.contactName!}</td>
                            <td>${customer.contactMobile}</td>
                            <td>${customer.createTime?date}</td>
                            <td><#if (customer.bindErp)>是 <#else>否</#if></td>
                        </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->

    <script src="js/laydate/laydate.js"></script>
    <script>
    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page"
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    page.fn.filter();

                    page.fn.createOrder();


                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: '2000-06-16 23:59:59', //设定最小日期为当前日期
                        max: '2099-06-16 23:59:59', //最大日期
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                             end.min = datas; //开始日选好后，重置结束日的最小日期
                             end.start = datas; //将结束日的初始值设定为开始日
                             $('#start').attr('title', datas);
                        }
                    };
                    var end = {
                        elem: '#end',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: '2000-06-16 23:59:59',
                        max: '2099-06-16 23:59:59',
                        istime: true,
                        istoday: false,
                        choose: function(datas){
                            start.max = datas; //结束日选好后，重置开始日的最大日期
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/order/customer?"
                    $('#submit').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                },
                createOrder:function(){
                    $(".tc>tbody>tr").click(function(){
                        var customerId = $(this).find("td:first").text();

                        location.href="/order/create/"+customerId
                    })

                }
            }
        }
        //加载页面js
        $(function() {
            page.fn.init();
        });
    })(jQuery);

        
    </script>
</body>
</html>