<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>寄样列表-药优优</title>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">寄样列表</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="ui-search">
        <form action="">
            <button type="button" id="submit" class="fa fa-search submit mid"></button>
            <input type="text" name="name" value="${name?default('')}" id="keyword" class="ipt" placeholder="请输入商品名称" autocomplete="off">
        </form>
    </div>
    <div class="slist">
        <ul>
            <#list sampleList as sample>
            <li>
                <#list sample.commodityList as commodity>
                <a href="sample/detail/${sample.id?c}">
                    <div class="cnt">
                        <div class="title">${commodity.name}</div>
                        <div class="summary">
                        ${commodity.title}
                        </div>
                        <div class="attr">
                            <span>产地：${commodity.origin}</span>
                            <span>规格：${commodity.spec}</span>
                        </div>
                    </div>
                    <div class="pic">
                        <img src="${commodity.thumbnailUrl?default('')}" width="110" height="90" alt="">
                    </div>
                </a>
                </#list>
                <div class="ft">
                    <span class="status-${sample.status+1}">状态：${sample.bizStatusText}</span>
                    <#if sample.status==4 && sample.getSample==0>
                    <button type="button" class="btn mid receipt"  sid="${sample.id?c}">确认收货</button>
                    </#if>
                </div>
            </li>
            </#list>
        </ul>
    </div>

</section><!-- /ui-content -->

<#include  "./common/footer.ftl"/>
<script>
    var _global = {
        v: {
            trackingCreateUrl:"sample/feedBack"
        },
        fn: {
            init: function () {
                this.bindEvent();
                this.empty();
            },
            bindEvent: function () {
                var $search = $("#submit");
                var $mid = $(".receipt");
                $search.on('click', function() {
                    var url="sample/list";
                    var name=$("#keyword").val();
                    if($.trim(name)!=""){
                        location.href=url+"?name="+name;
                    }
                    else{
                        location.href=url;
                    }
                });
                $mid.on('click',function(){
                    var sendId=$(this).attr("sid");
                    $.ajax({
                        url: _global.v.trackingCreateUrl,
                        data:  {sendId:sendId,recordType:4},
                        type: "POST",
                        success: function(data) {
                            window.location.reload();
                        }
                    })
                })
            },
            empty: function() {
                if ($('.slist').find('li').length === 0) {
                    $('.ui-content').prepend('<div class="ui-notice ui-notice-extra"> \n 寄样列表还没有商品，<br>去商品详情页面可以添加商品到选货单！ \n <a class="ubtn ubtn-primary" href="/">返回首页</a> \n </div>');
                    $('.ui-search').remove();
                }
            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>