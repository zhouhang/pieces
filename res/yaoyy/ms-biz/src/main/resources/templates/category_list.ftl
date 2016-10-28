<!DOCTYPE html>
<html lang="en">
<head>
    <title>品种列表</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body">
<header class="ui-header">
    <div class="title">品种列表</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
    <div class="abs-r mid">
        <a href="category/search"><i class="fa fa-search"></i></a>
    </div>
</header><!-- /ui-header -->

<footer class="ui-footer">
    <nav class="ui-nav">
        <ul>
            <li>
                <a href="/index">
                    <i class="fa fa-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <li>
                <a class="current" href="category/list">
                    <i class="fa fa-list"></i>
                    <span>品种列表</span>
                </a>
            </li>
            <li>
                <a href="center.html">
                    <i class="fa fa-cart"></i>
                    <span>采购单</span>
                </a>
            </li>
            <li>
                <a href="center.html">
                    <i class="fa fa-order"></i>
                    <span>订单</span>
                </a>
            </li>
        </ul>
    </nav>
</footer>

<section class="ui-content">
    <div class="plist" id="categroyList">
        <ul>

        </ul>
    </div>

</section><!-- /ui-content -->
<#include "./common/footer.ftl"/>
<script>

    var _global = {
        v:{
            dataUrl:"/category/list"
        },
        fn: {
            init: function() {
                this.loadPlist();
            },
            loadPlist: function() {
                var self = this;
                $('.plist').dropload({
                    scrollArea : window,
                    threshold : 50,
                    loadUpFn : function(me){
                        $.ajax({
                            type: 'POST',
                            url: _global.v.dataUrl,
                            data:{variety:'${variety?default('')}'},
                            dataType: 'json',
                            success: function(data){
                                if (!data.data.list) {
                                    return;
                                }
                                me.unlock();
                                me.isDate = true;
                                var result = self.toHtml(data.data.list);
                                setTimeout(function(){
                                    $('.plist ul').html(result);
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
                        var showNum=$(".plist li").length;
                        if(showNum!=0&&showNum<10){
                            popover('已经没有了!');
                            me.resetload();
                            return;
                        }

                        var pageNum=showNum%10+1;
                        $.ajax({
                            type: 'POST',
                            url: _global.v.dataUrl,
                            data:{pageNum:pageNum,variety:'${variety?default('')}'},
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
                                    $('.plist ul').append(result);
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
                var html = [];
                $.each(data, function(i, item) {
                    html.push('<li>\n');
                    html.push( '<a href="' + "product_detail.html" + '">\n');
                    html.push(     '<div class="cnt">\n');
                    html.push(         '<div class="title">', data[i].variety, '</div>\n');
                    html.push(         '<div class="summary">', data[i].title, '</div>\n');
                    html.push(         '<div class="price">\n');
                    html.push(              data[i].priceDesc,'元/', data[i].unit);
                    html.push(          '</div>\n');
                    html.push(     '</div>');
                    html.push(     '<div class="pic"><img src="', data[i].pictureUrl, '" width="110" height="90" alt="', data[i].variety, '"></div>\n');
                    html.push( '</a>\n');
                    html.push('</li>');
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