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
            <input type="text" name="name" value="${name?default('')}" id="keyword" class="ipt" placeholder="请输入原药材品种名称" autocomplete="off">
        </form>
    </div>
    <div class="slist">
        <ul>
            <#list sampleList as sample>
            <li>
                <#list sample.commodityList as commodity>
                <a href="commodity/detail/${commodity.id?c}">
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
                        <img src="${commodity.pictureUrl?default('')}" width="110" height="90" alt="">
                    </div>
                </a>
                </#list>
                <div class="ft">
                    <span class="status-5">状态：${sample.statusText}</span>
                    <#if sample.status==4>
                    <button type="button" class="btn mid" sid="${sample.id?c}">确认收货</button>
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
            },

            bindEvent: function () {
                var $search =$("#submit");
                var $mid=$(".mid");
                $search.on('click', function() {
                    var url="sample/list";
                    var name=$("#keyword").value;
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


            }
        }
    }

    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>