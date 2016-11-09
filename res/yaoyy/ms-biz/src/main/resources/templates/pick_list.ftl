<!DOCTYPE html>
<html lang="en">
<head>
    <title>选货单列表-药优优</title>
    <#include "./common/meta.ftl"/>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">选货单列表</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="pick-list">
    </div>

</section><!-- /ui-content -->
<#include "./common/footer.ftl"/>

<script>

    var _global = {
        v:{
            dataUrl:"/pick/list"
        },
        fn: {
            init: function() {
                this.loadPlist();
            },
            loadPlist: function() {
                var self = this;
                $('.ui-content').dropload({
                    scrollArea : window,
                    threshold : 50,
                    loadUpFn : function(me){
                        $.ajax({
                            type: 'POST',
                            url: _global.v.dataUrl,
                            data:{pageSize:5},
                            dataType: 'json',
                            success: function(data){
                                if (!data.data.list) {
                                    return;
                                }
                                me.unlock();
                                me.isDate = true;
                                var result = self.toHtml(data.data.list);
                                setTimeout(function(){
                                    $('.pick-list').html(result);
                                    me.resetload();
                                }, 1e3);
                            },
                            error: function(xhr, type){
                                popover('网络连接超时，请您稍后重试!');
                                me.resetload();
                            }
                        });
                    },
                    loadDownFn : function(me){
                        var showNum=$(".pick-list .item").length;
                        if(showNum!=0&&showNum<5){
                            popover('已经没有了!');
                            me.resetload();
                            return;
                        }
                        var pageNum=showNum%5+1;
                        $.ajax({
                            type: 'POST',
                            url: _global.v.dataUrl,
                            data:{pageSize:5,pageNum:pageNum},
                            dataType: 'json',
                            success: function(data){
                                if (!data.data.list) {
                                    return false;
                                }
                                var result = self.toHtml(data.data.list);

                                if(data.status === 'nomore'){
                                    me.lock();
                                    me.noData();
                                    me.resetload();
                                    return;
                                }
                                setTimeout(function(){
                                    $('.pick-list').append(result);
                                    me.resetload();
                                }, 1e3);
                            },
                            error: function(xhr, type){
                                popover('网络连接超时，请您稍后重试!');
                                me.resetload();
                            }
                        });
                    }
                });
            },
            toHtml: function(data) {
                console.log(data);
                var html = [];
                $.each(data, function(i, item) {
                    html.push('<div class="item">\n <dl>');
                    html.push(     '<dt>\n');
                    html.push(         '状态：<em>', item.bizStatusText, '</em>\n');
                    html.push(         '<time>', item.createTime, '</time>\n');
                    html.push(     '</dt>\n');

                    $.each(item.pickCommodityVoList, function(j, list) {
                        if (j > 4) {
                            return false;
                        }
                        html.push(     '<dd>\n');
                        html.push(         '<a href="/pick/detail/', item.id, '"><em>', list.name, '</em><span>', list.origin, '</span><span>', list.spec, '</span><span>', list.num,list.unit, '</span></a>\n');
                        html.push(     '</dd>\n');
                    })

                    html.push('</dl> \n');
                    item.pickCommodityVoList.length> 5 && html.push('<div class="more">更多</div>'); // 选货单超过5条显示更多按钮

                    html.push('</div>');
                })
                return html.join('');
            }
        }
    }

    $(function(){
        _global.fn.init();
    });

</script>
</body>
</html>