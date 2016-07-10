<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>角色清单-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>角色管理</h3>
                <div class="extra"><a class="btn btn-red" href="role/info"><i class="fa fa-plus"></i>增加新角色</a></div>
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
                            <th width="200">编号</th>
                            <th>角色名称</th>
                            <th width="200">操作</th>
                        </tr>
                        <tr>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                            <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                        <tr>
                            <td>10</td>
                            <td><div class="tl">超级管理员</div></td>
                            <td><a href="role_info.html">配置</a></td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td><div class="tl">财务</div></td>
                            <td><a href="role_info.html">配置</a></td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td><div class="tl">客服</div></td>
                            <td><a href="role_info.html">配置</a></td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td><div class="tl">运营</div></td>
                            <td><a href="role_info.html">配置</a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

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