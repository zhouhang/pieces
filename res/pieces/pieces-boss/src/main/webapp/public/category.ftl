<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>分类管理-boss-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/css/style.css" />
</head>

<body>

	<#include "./inc/header.ftl"/>
    <!-- fa-floor start -->
    <div class="fa-floor">
        <div class="wrap">
            <div class="title title-btm">
                <h3>分类管理</h3>
                <div class="extra"><a class="btn btn-red" href="/category/add"><i class="fa fa-plus"></i>增加新分类</a></div>
            </div>
            <div class="pagin">
                <div class="extra">
                    <button class="btn btn-gray" type="button" id="reset">重置条件</button>
                    <button class="btn btn-blue" type="button" id="submit"><i class="fa fa-search"></i><span>搜索</span></button>
                </div>
                <@p.pager pageInfo=categoryPage pageUrl="/category/list" params=categoryParams/>
            </div>
            <div class="chart">
                <table class="tc">
                    <thead>
                        <tr>
                            <th width="100">编号</th>
                            <th>分类名称</th>
                            <th width="150">状态</th>
                            <th width="150">操作</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td><div class="ipt-wrap"><input type="text" name="categoryName" id="categoryName" class="ipt" value="${category.name }"></div></td>
                            <td>
                                <select name="status" id="status">
                                	<option <#if (!category.status??)>selected</#if> value=""></option>
									<option <#if (category.status==1)>selected</#if> value="1">激活</option>
									<option <#if (category.status==0)>selected</#if> value="0">禁用</option>
                                </select>
                            </td>
                            <td></td>
                        </tr>
                    </thead>
                    <tfoot></tfoot>
                    <tbody>
                    	<#list categoryPage.list as category>
                        <tr>
                            <td>${category.id }</td>
                            <td><div class="tl">${category.name }</div></td>
                            <td><#if (category.status==0)>禁用</#if><#if (category.status==1)>激活</#if></td>
                            <td><a href="/category/edit/${category.id }">修改</a></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div><!-- fa-floor end -->
    </div>


    <#include "./inc/footer.ftl"/>

    <script src="/js/jquery.min.js"></script>
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
                    var url="/category/list?pageNum="+page.v.pageNum+"&pageSize="+page.v.pageSize;

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