<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>企业资质审核-boss-上工好药</title>
</head>

<body>

<#include "./inc/header.ftl"/>


<!-- fa-floor start -->
<div class="fa-floor">

    <div class="wrap">
        <div class="title title-btm">
            <h3>企业资质审核</h3>
        </div>
        <div class="pagin">
            <div class="extra">
                <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                <button class="btn btn-blue" type="button" id="search_btn"><i class="fa fa-search"></i><span>搜索</span></button>
            </div>
          <@p.pager pageInfo=pageInfo  pageUrl="certify/list"  params=certifyParams />
        </div>
        <div class="chart">
            <table class="tc">
                <thead>
                <tr>
                    <th width="70">编号</th>
                    <th>会员名</th>
                    <th>跟进人</th>
                    <th width="100">状态</th>
                    <th width="200">提交时间</th>
                    <th width="200">跟进时间</th>
                    <th width="100">操作</th>
                </tr>
                <tr>
                    <td></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="userName" value="${certifyRecordVo.userName}"></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" name="followName" value="${certifyRecordVo.followName}"></div></td>
                    <td>
                        <select name="status" id="status">
                            <option value=""></option>
                            <option value="1">审核通过</option>
                            <option value="2">审核不通过</option>
                            <option value="0">未处理</option>
                        </select>
                    </td>
                    <td><input type="text" class="ipt date" value="<#if certifyRecordVo.startTime?exists>${certifyRecordVo.startTime?datetime}</#if>" name="startTime" id="start"> - <input type="text" class="ipt date" value="<#if certifyRecordVo.endTime?exists>${certifyRecordVo.endTime?datetime}</#if>" name="endTime"  id="end"></td>
                    <td><input type="text" class="ipt date" value="<#if certifyRecordVo.startFollowTime?exists>${vo.startFollowTime?datetime}</#if>"  name="startFollowTime"  id="start2"> - <input type="text" class="ipt date" value="<#if certifyRecordVo.endFollowTime?exists>${vo.endFollowTime?datetime}</#if>" name="endFollowTime" id="end2"></td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
              <#list pageInfo.list as certifyRecord>
                <tr>
                    <td>${certifyRecord.id}</td>
                    <td>${certifyRecord.userName}</td>
                    <td>${(certifyRecord.followName??)}</td>
                    <td>${certifyRecord.statusText}</td>
                    <td>${(certifyRecord.createTime?datetime)!}</td>
                    <td><#if certifyRecord.followTime?exists>${(certifyRecord.followTime?datetime)!}</#if></td>
                    <td><a href="/certify/info/1">查看</a></td>
                </tr>
              </#list>
                </tbody>
            </table>
        </div>
    </div><!-- fa-floor end -->

</div>


<#include "./inc/footer.ftl"/>

<script src="js/jquery.min.js"></script>
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
                    $("#status").val(${vo.status});
                },

                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/certify/list?";

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+params.join('&');
                    })

                    $("#reset").on("click", function (){
                        window.location.href = url;
                    });
                },
                //日期选择
                dateInit: function () {
                    var start = {
                        elem: '#start',
                        choose: function(datas){
                            end.min = datas;
                            end.start = datas;
                            $('#start').attr('title', datas);
                        }
                    };
                    var end = {
                        elem: '#end',
                        choose: function(datas){
                            start.max = datas;
                            $('#end').attr('title', datas);
                        }
                    };
                    laydate(start);
                    laydate(end);

                    var start2 = {
                        elem: '#start2',
                        choose: function(datas){
                            end2.min = datas;
                            end2.start = datas;
                            $('#start2').attr('title', datas);
                        }
                    };
                    var end2 = {
                        elem: '#end2',
                        choose: function(datas){
                            start2.max = datas;
                            $('#end2').attr('title', datas);
                        }
                    };
                    laydate(start2);
                    laydate(end2);
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

