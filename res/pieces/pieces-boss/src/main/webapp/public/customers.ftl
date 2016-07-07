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
        <#if (advices??)>
            <div  class="message">
                <i class="fa fa-check-circle"></i>
                <span>${advices}</span>
            </div>
        </#if>

        <div class="title">
            <h3>客户管理</h3>
            <div class="extra"><a class="btn btn-red" href="user/add"><i class="fa fa-plus"></i>增加新客户</a></div>
        </div>
        <div class="pagin">
            <div class="extra">
                <a class="btn btn-gray" href="#"><i class="fa fa-export"></i>导出</a>
            </div>
            <@p.pager pageInfo=userPage  pageUrl="user/index"  params=userParams/>
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
                    <form id="search_form">
                        <td></td>
                        <td><input name="userName" type="text" class="ipt" value="${userVo.userName}"></td>
                        <td><input name="companyFullName" type="text" class="ipt" value="${userVo.companyFullName}"></td>
                        <td><input name="areaFull" type="text" class="ipt" value="${userVo.areaFull}"></td>
                        <td><input name="contactName" type="text" class="ipt" value="${userVo.contactName}"></td>
                        <td><input name="contactMobile" type="text" class="ipt" value="${userVo.contactMobile}"></td>
                        <td><input name="startDate" type="text" class="ipt date" value="${userVo.startDate}" id="start"> - <input name="endDate" type="text" class="ipt date" value="${userVo.endDate}" id="end"></td>
                        <td>
                            <select name="bindErp" id="">
                                <option <#if (!userVo.bindErp??)>selected</#if> value="false">否</option>
                                <option <#if (userVo.bindErp??&&userVo.bindErp)>selected</#if> value="true">是</option>
                            </select>
                        </td>
                        <td>
                            <button id="search_btn" class="button" type="button"><i class="fa fa-search"></i><span>搜索</span></button>
                        </td>
                    </form>
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
                        <td><a href="user/info/${user.id}">修改</a></td>
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
                id: "page",
                pageNum:${userPage.pageNum},
                pageSize:${userPage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    page.fn.filter();
                    $("#search_btn").click(function(){
                        page.fn.filter();
                    })

                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="user/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
                    })
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        format: 'YYYY/MM/DD',
                        min: '', //设定最小日期为当前日期
                        max: '2099-06-16', //最大日期
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
                        format: 'YYYY/MM/DD',
                        min: '',
                        max: '2099-06-16',
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