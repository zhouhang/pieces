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

<#include "./common/navigation.ftl">

<section class="ui-content">
    <div class="plist" id="categroyList"></div>
</section><!-- /ui-content -->
<#include "./common/footer.ftl"/>
<script>

    var _global = {
        v:{
            dataUrl:"/category/list"
        },
        fn: {
            init: function() {
                this.page();
                this.loadPlist();
            },
            loadPlist: function() {
                var self = this,
                    pageNum = 1; // 当前页

                $('.ui-content').dropload({
                    scrollArea : window,
                    threshold : 50,
                    loadDownFn : function(me){
                        $.ajax({
                            type: 'POST',
                            url: _global.v.dataUrl, 
                            data: {pageNum:pageNum, variety:'${variety?default('')}'},
                            dataType: 'json',
                            success: function(result){
                                if (result.data.list.length !== 0) {
                                    self.toHtml(result.data.list, pageNum);
                                    if (result.data.isLastPage) {
                                        me.lock();
                                        me.noData();
                                        setTimeout(function() {
                                            me.$domDown.addClass('dropload-down-hide');
                                        }, 2e3);
                                    }
                                    self.pagenav(result.data.pageNum, result.data.pages);
                                } else {
                                    if (result.data.isLastPage) {
                                        self.empty(true);
                                        me.$domDown.hide();
                                    }
                                }
                                pageNum ++;
                                me.resetload();
                            },
                            error: function(xhr, type){
                                popover('网络连接超时，请您稍后重试!');
                                me.resetload();
                            },

                            success2: function(data){
                                if (!data.data.list) {
                                    return false;
                                }
                                var result = self.toHtml(data.data.list);

                                if(data.data.pages === pageNum){
                                    $('.plist ul').append(result);
                                    me.resetload();
                                    me.lock();
                                    me.noData();
                                    me.resetload();
                                    return;
                                }
                                setTimeout(function(){
                                    $('.plist ul').append(result);
                                    me.resetload();
                                }, 1e3);
                            }
                        });
                    }
                });
            },
            toHtml: function(data, pageNum) {
                var html = [];
                html.push('<ul id="page' + pageNum + '">');
                $.each(data, function(i, item) {
                    html.push('<li>\n');
                    html.push( '<a href="/commodity/detail/' + data[i].defaultCommodityId + '">\n');
                    html.push(     '<div class="cnt">\n');
                    html.push(         '<div class="title">', data[i].variety, '</div>\n');
                    html.push(         '<div class="summary">', data[i].title, '</div>\n');
                    html.push(         '<div class="price"><i>¥</i>\n<em>');
                    html.push(              data[i].priceDesc,'</em>元/', data[i].unit);
                    html.push(          '</div>\n');
                    html.push(     '</div>');
                    html.push(     '<div class="pic"><img src="', data[i].pictureUrl, '" width="110" height="90" alt="', data[i].variety, '"></div>\n');
                    html.push( '</a>\n');
                    html.push('</li>');
                })
                html.push('</ul>');
                $('.plist').append(html.join(''));
                this.offset[pageNum] = $('#page' + pageNum).offset().top;
            },
            empty: function(isEmpty) {
                if (isEmpty) {
                    $('.ui-content').prepend('<div class="ui-notice ui-notice-extra"> \n 品种列表还没有商品，<br>去商品详情页面可以添加商品到选货单！ \n <a class="ubtn ubtn-primary" href="/">返回首页</a> \n </div>');
                }
            },
            pagenav: function(pageNum, pages) {
                $('#pagenav').show().html('<em>' + pageNum + '</em>/' + pages);
            },
            page: function() {
                var self = this;
                self.offset = {};
                $(window).on('scroll', function() {
                    var st = document.body.scrollTop || document.documentElement.scrollTop,
                        winHeight = $(window).height() / 1.5;
                    $.each(self.offset, function(key, val) {
                        if (st + winHeight >= val) {
                            $('#pagenav').find('em').html(key);
                        }
                    })
                })
            }
        }
    }

    $(function(){
        _global.fn.init();
    });

</script>
</body>
</html>