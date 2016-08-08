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
                <h3>单页面分类管理</h3>
                <div class="extra"><a class="btn btn-red" href="cms_category_info.html"><i class="fa fa-plus"></i>增加新分类</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <div class="skip">
                    <span>第</span>
                    <a class="fa fa-chevron-left btn btn-gray"></a><input type="text" class="ipt" value="1"><a class="fa fa-chevron-right btn btn-gray"></a>
                    <span>页，共</span><em>6</em><span>页</span>
                    <i>|</i>
                    <span>每页</span>
                    <select name="" id="">
                        <option value="">10</option>
                        <option value="">20</option>
                        <option value="">30</option>
                        <option value="">40</option>
                    </select>
                    <span>个记录，共有 2 个记录</span>
                </div>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                    <tr>
                        <th width="100">编号</th>
                        <th class="tl">单页面分类</th>
                        <th width="150">操作</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                        <td></td>
                    </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">新手指南</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">产品询价</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">交期管理</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">增值服务</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">服务说明</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td><div class="tl">网站信息</div></td>
                        <td><a href="cms_category_info.html?id=1">修改</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>

<!-- footer start -->
    <#include "./inc/footer.ftl"/>
<!-- footer end -->
    <script src="/js/common.js"></script>
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
                    page.fn.filter();
                },
                // 筛选
                filter: function() {
                    var $ipts = $('.chart .ipt, .chart select');

                    $('#submit').on('click', function() {
                        var params = [];
                        $ipts.each(function() {
                            var val = $.trim(this.value);
                            val && params.push($(this).attr('name') + '=' + val);
                        })
                        console.log(params.join('&'))
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