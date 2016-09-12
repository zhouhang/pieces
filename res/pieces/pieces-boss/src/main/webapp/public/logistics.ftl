<!DOCTYPE html>
<html lang="en">
<head>
    <title>物流管理-boss-饮片B2B</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>物流管理</h3>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=pageInfo pageUrl="/logistics/index" params=params/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th>运单号</th>
                            <th>发货日期</th>
                            <th>订单号</th>
                            <th>订单商品总数</th>
                            <th>本次发货商品数</th>
                            <th width="100">操作</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input type="text" name="lCode" class="ipt" value="${logisticalVo.lCode}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" id="date" name="shipDateStr" class="ipt" value="${logisticalVo.shipDateStr}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="oCode" class="ipt" value="${logisticalVo.oCode}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="total" class="ipt" value="${logisticalVo.total}"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="shipNumber" class="ipt" value="${logisticalVo.shipNumber}"></div></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list pageInfo.list as list>
                        <tr>
                            <td>${list.id}</td>
                            <td>${list.lCode}</td>
                            <td>${list.shipDate?date}</td>
                            <td>${list.oCode}</td>
                            <td>${list.total}</td>
                            <td>${list.shipNumber}</td>
                            <td>
                            <@shiro.hasPermission name="logistical:info">
                                <a href="/logistics/${list.id}">查看</a>
                            </@shiro.hasPermission>
                            </td>
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
</body>
</html>

<script>
	  //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${pageInfo.pageNum},
                pageSize:${pageInfo.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.dateInit();
                    $("#submit").click(function(){
                        page.fn.filter();
                    });
                    
                    $("#reset").click(function(){
                    	$('.tc :input').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="/logistics/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    var params = [];
                    $ipts.each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+"&"+params.join('&');
            	},
            	//日期选择
                dateInit: function () {
                    var date = {
                        elem: '#date',
                        format: 'YYYY-MM-DD',
                        min: '', //设定最小日期为当前日期
                        max: '2099-06-16', //最大日期
                        istime: true,
                        istoday: false
                    };
                    laydate(date);
                }
            }
        }
        //加载页面js
        $(function() {
            page.fn.init();
        });
    })(jQuery);
</script>