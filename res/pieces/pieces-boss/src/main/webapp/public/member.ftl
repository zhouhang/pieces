<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>用户清单-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">
<!-- fa-floor start -->
<div class="fa-floor">
    <div class="wrap">
        <div class="title title-btm">
            <h3>用户管理</h3>
            <div class="extra"><a class="btn btn-red" href="member/info"><i class="fa fa-plus"></i>增加新用户</a></div>
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
                    <th width="70">编号</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th width="200">状态</th>
                    <td width="200">操作</td>
                </tr>
                <tr>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td><div class="ipt-wrap"><input type="text" class="ipt" value=""></div></td>
                    <td>
                        <select name="" id="">
                            <option value="">激活</option>
                        </select>
                    </td>
                    <td></td>
                </tr>
                </thead>
                <tfoot></tfoot>
                <tbody>
                <tr>
                    <td>10</td>
                    <td>administrator</td>
                    <td>超级管理员</td>
                    <td>super.yaicai.pro</td>
                    <td>激活</td>
                    <td><a href="user_info.html">修改</a></td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>administrator</td>
                    <td>超级管理员</td>
                    <td>super.yaicai.pro</td>
                    <td>激活</td>
                    <td><a href="user_info.html">修改</a></td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>administrator</td>
                    <td>超级管理员</td>
                    <td>super.yaicai.pro</td>
                    <td>激活</td>
                    <td><a href="user_info.html">修改</a></td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>administrator</td>
                    <td>超级管理员</td>
                    <td>super.yaicai.pro</td>
                    <td>激活</td>
                    <td><a href="user_info.html">修改</a></td>
                </tr>
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