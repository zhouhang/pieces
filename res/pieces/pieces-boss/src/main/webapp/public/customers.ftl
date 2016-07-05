<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>客户清单-boss-饮片B2B</title>
</head>

<body>

<#include "./inc/header.ftl"/>

<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title">
            <h3>客户管理</h3>
            <div class="extra"><a class="btn btn-red" href="customers-add.html"><i class="fa fa-plus"></i>增加新客户</a></div>
        </div>
        <div class="pagin">
            <div class="extra">
                <a class="btn btn-gray" href="#"><i class="fa fa-export"></i>导出</a>
            </div>
            <@p.pager pageInfo=userPage  pageUrl="user/index" />


        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="70">编号</th>
                    <th>会员名</th>
                    <th>企业全称</th>
                    <th>企业注册地区</th>
                    <th>联系人</th>
                    <th>手机号</th>
                    <th width="170">注册日期</th>
                    <th>与ERP关联</th>
                    <th>操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="text" class="ipt" value=""></td>
                    <td><input type="text" class="ipt" value=""></td>
                    <td><input type="text" class="ipt" value=""></td>
                    <td><input type="text" class="ipt" value=""></td>
                    <td><input type="text" class="ipt" value=""></td>
                    <td><input type="text" class="ipt date" value="" id="start"> - <input type="text" class="ipt date" value="" id="end"></td>
                    <td>
                        <select name="" id="">
                            <option value="">是</option>
                            <option value="">否</option>
                        </select>
                    </td>
                    <td>

                        <button class="button" type="button"><i class="fa fa-search"></i><span>搜索</span></button>
                    </td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <#list userPage.list as user>
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.companyFullName}</td>
                        <td>${user.areaFull}</td>
                        <td>${user.contactName}</td>
                        <td>${user.contactMobile}</td>
                        <td>${user.createTime?date}</td>
                        <td>${user.bindErp}</td>
                        <td><a href="customers-info.html">修改</a></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->
</div>

<#include "./inc/footer.ftl"/>


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
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD hh:mm:ss',
                        min: laydate.now(), //设定最小日期为当前日期
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
                        min: laydate.now(),
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