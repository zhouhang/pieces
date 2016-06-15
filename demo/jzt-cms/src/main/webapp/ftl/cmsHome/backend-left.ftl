<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>cms管理系统</title>
    <#include "/home/common.ftl">
   </head>
<body>
<div class="wapper">
<!-- nav start -->
    <div class="nav">
        <ul class="nav">
            <li class="cur"><a href="#"><i class="icon-1"></i>站点管理</a>
                <ul class="sub-nav">
                    <li><a href="/ftl/cmsSite/queryCmsSite" target="mainFrame">站点列表</a></li>
                    <li><a href="/ftl/cmsSite/toAddCmsSite" target="mainFrame">添加站点</a></li>
                    <li><a href="/cmsSite/toModifyAndDeleteCmsSite" target="mainFrame">站点管理</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="icon-2"></i>目录管理</a>
                <ul class="sub-nav">
                	<li><a href="/public/dd" target="mainFrame">查询目录</a></li>
                    <li><a href="/public/ff" target="mainFrame">增加目录</a></li>
                    <li><a href="/public/dd" target="mainFrame">修改目录</a></li>
                    <li><a href="/public/ee" target="mainFrame">删除目录</a></li>
                </ul>
            </li>
            <li><a href="#"><i class="icon-3"></i>文章管理</a>
                <ul class="sub-nav">
                    <li><a href="/articlelist" target="mainFrame">文章列表</a></li>
                    <li><a href="/addArticle" target="mainFrame">添加文章</a></li>
                    <li><a href="/modileArticle" target="mainFrame">文章管理</a></li>
                </ul>
            </li>
            <!--
            <li><a href="#"><i class="icon-3"></i>系统设置</a>
                <ul class="sub-nav">
                    <li><a href="#" target="mainFrame">未开通</a></li>
                </ul>
                
            </li>
            -->
        </ul>
    </div>
<!-- nav over -->

<!-- 弹层 over -->
<script>
    $(function(){
        //日期
        $('#datetimepicker1').datetimepicker({
            yearOffset:0,
            lang:'ch',
            timepicker:false,
            format:'d/m/Y',
            formatDate:'Y/m/d',
            minDate:'-1970/01/02', // yesterday is minimum date
            maxDate:'+1970/01/02' // and tommorow is maximum date calendar
        });
        $('#datetimepicker2').datetimepicker({
            yearOffset:0,
            lang:'ch',
            timepicker:false,
            format:'d/m/Y',
            formatDate:'Y/m/d',
            minDate:'-1970/01/02', // yesterday is minimum date
            maxDate:'+1970/01/02' // and tommorow is maximum date calendar
        });
        //tips
        $('.operate-1').hover(
                function(){
                    $(this).children('.tips').show();
;                },
                function(){
                    $(this).children('.tips').hide();
                }
        );
        //弹层
        var ReMember = $('.operate-2');
        var Close = $('.close');
        var html = '<div class="bghui"></div>';
        ReMember.each(function(){
            $(this).click(function(){
                $('#reMember').show();
                $('body').append(html);
            })
        });
        Close.each(function(){
            $(this).click(function(){
                $(this).parent().hide();
                $('.bghui').remove();
            })
        });
        $('.btn-add').on('click',function(){
            $('#addMember').show();
            $('body').append(html);
        })

    });
</script>
</body>
</html>