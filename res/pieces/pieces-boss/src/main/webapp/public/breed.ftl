<!DOCTYPE html>
<html lang="en">
<head>
    <title>品种管理-boss-上工好药</title>
    <#include "./inc/meta.ftl"/>
</head>

<body>

    <#include "./inc/header.ftl"/>


    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>品种管理</h3>
                <div class="extra">
                <@shiro.hasPermission name="breed:add">
                    <a class="btn btn-red" href="/breed/add"><i class="fa fa-plus"></i>增加新品种</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=categoryPage pageUrl="/breed/list" params=categoryParams/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="70">编号</th>
                            <th width="310">原药品种</th>
                            <th>品种别名</th>
                            <th width="240">所属分类</th>
                            <th width="70">操作</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input type="text" name="name" class="ipt" value="${category.name }"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="aliases" class="ipt" value="${category.aliases }"></div></td>
                            <td><div class="ipt-wrap"><input type="text" name="classifyName" class="ipt" value="${category.classifyName }"></div></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    <#list categoryPage.list as category>
                        <tr>
                            <td>${category.id }</td>
                            <td><div class="tl">${category.name }</div></td>
                            <td><div class="tl">${category.aliases }</div></td>
                            <td>${category.classifyName }</td>
                            <td>
                                <@shiro.hasPermission name="breed:edit">
                                <a href="/breed/edit/${category.id }">修改</a>
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

        <script>
  //定义根变量
    !(function($) {
        var page = {
            //定义全局变量区
            v: {
                id: "page",
                pageNum:${categoryPage.pageNum},
                pageSize:${categoryPage.pageSize}
            },
            //定义方法区
            fn: {
                //初始化方法区
                init: function () {
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
                    var url="/breed/list?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

                    var params = [];
                    $ipts.each(function() {
                        var val = $.trim(this.value);
                        val && params.push($(this).attr('name') + '=' + val);
                    })
                    location.href=url+"&"+params.join('&');
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