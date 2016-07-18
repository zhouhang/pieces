<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>商品管理-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>商品管理</h3>
                <div class="extra"><a class="btn btn-red" href="commodity/add"><i class="fa fa-plus"></i>增加新产品</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=pageInfo  pageUrl="commodity/index"  params=""/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="70">编号</th>
                        <th width="220">原药品种</th>
                        <th width="240">商品名称</th>
                        <th width="100">切制规格</th>
                        <th width="100">原料产地</th>
                        <th>生产厂家</th>
                        <th width="100">状态</th>
                        <th width="100">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td>
                            <select name="" id="">
                                <option value=""></option>
                                <option value="">激活</option>
                                <option value="">禁用</option>
                            </select>
                        </td>
                        <td></td>
                    </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list pageInfo.list as commodity>
                    <tr>
                        <td>${commodity.id}</td>
                        <td>${commodity.categoryName}</td>
                        <td>${commodity.name}</td>
                        <td>${commodity.specName}</td>
                        <td>${commodity.originOfName}</td>
                        <td>${commodity.factory}</td>
                        <td>${commodity.status}</td>
                        <td><a href="commodity/info/${commodity.id}">修改</a></td>
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
<script>

    //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${pageNum},
                pageSize:${pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
                    page.fn.filter();

                    $("#reset").click(function(){
                        $('.chart .ipt, .chart select').val("")
                    })
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');
                    var url="member/index?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    $('#search_btn').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        location.href=url+"&"+params.join('&');
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