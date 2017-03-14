<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>商品详情-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="goods-info">
        <div class="pic rs-pic">
            <img src="uploads/p2.jpg" />
        </div>

        <h1>樟树子 无硫</h1>
    </div>

    <div class="goods-info">
        <div class="tab">
            <ul class="cf">
                <li class="curr">规格详情</li>
                <li>样品图片</li>
            </ul>
        </div>
        <div class="cnt">
            <div class="inner">
                <div class="item">
                    <ul>
                        <li>
                            <em>商品名称：</em>
                            <strong>樟树子</strong>
                        </li>
                        <li>
                            <em>片       型：</em>
                            <strong>个</strong>
                        </li>
                        <li>
                            <em>规格等级：</em>
                            <strong>2号筛</strong>
                        </li>
                        <li>
                            <em>原药产地：</em>
                            <strong>安徽亳州</strong>
                        </li>
                        <li>
                            <em>外观描述：</em>
                            <strong>干燥果实，圆球形，棕黑色至紫黑色，表面皱缩不平，或有光泽，直径约5～8毫米，有的基部尚包有宿存的花被。果皮肉质而薄，内含种子1枚，黑色。气香、味辛辣。</strong>
                        </li>
                        <li>
                            <em>执行标准：</em>
                            <strong>《广东中药炮制规范》2011版</strong>
                        </li>
                        <li>
                            <em>袋装规格：</em>
                            <strong>1公斤/袋</strong>
                        </li>
                        <li>
                            <em>年       限：</em>
                            <strong>1年</strong>
                        </li>
                        <li>
                            <em>原药产地：</em>
                            <strong>安徽亳州</strong>
                        </li>
                        <li>
                            <em>外观描述：</em>
                            <strong>干燥果实，圆球形，棕黑色至紫黑色，表面皱缩不平，或有光泽，直径约5～8毫米，有的基部尚包有宿存的花被。果皮肉质而薄，内含种子1枚，黑色。气香、味辛辣。</strong>
                        </li>
                        <li>
                            <em>执行标准：</em>
                            <strong>《广东中药炮制规范》2011版</strong>
                        </li>
                        <li>
                            <em>袋装规格：</em>
                            <strong>1公斤/袋</strong>
                        </li>
                        <li>
                            <em>年       限：</em>
                            <strong>1年</strong>
                        </li>
                    </ul>
                </div>

                <div class="item">
                    <div class="img">
                        <img src="assets/images/blank.gif" data-src="uploads/p3_01.jpg" />
                        <img src="assets/images/blank.gif" data-src="uploads/p3_02.jpg" />
                        <img src="assets/images/blank.gif" data-src="uploads/p3_03.jpg" />
                        <img src="assets/images/blank.gif" data-src="http://static.yaobest.com/ueditor/2016/11/204534f3-2364-4a88-967d-ecf8882101cb.jpg" />
                        <img src="assets/images/blank.gif" data-src="http://static.yaobest.com/ueditor/2016/11/942af4f3-a99b-43f9-8526-a54cd40959e2.jpg" />
                        <img src="assets/images/blank.gif" data-src="http://static.yaobest.com/ueditor/2016/11/9ea385e4-2e65-411d-b5d1-e54d2ea2d536.jpg" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- /ui-content -->
<#include "./inc/footer.ftl"/>
<script src="/js/dragloader.min.js"></script>
<script>
    !(function($) {
        var _global = {
            init: function() {
                this.tab();
                this.bindEvent();
            },
            // 详情内容
            tab: function() {
                var self = this,
                        $tab = $('.tab'),
                        $cnt = $('.cnt'),
                        $item = $cnt.find('.item'),
                        $inner = $cnt.find('.inner'),
                        length = $item.length,
                        _distance = $tab.offset().top;

                var lazyimg = function(idx) {
                    $item.eq(idx).find('img').each(function() {
                        this.src = this.getAttribute('data-src');
                    })
                }

                var dragger = new DragLoader(document.body, {
                    disableDragDown: true,
                    dragUpLoadFn: function() {
                        dragger.reset();
                        setTimeout(function() {
                            $('.tab').find('.curr').next().trigger('click');
                        }, 200);
                    },
                    dragUpDom: {
                        before : '继续上拉查看样品图片',
                        prepare : '释放查看样品图片',
                        load: ''
                    }
                });

                $tab.on('click', 'li', function() {
                    var $el = $(this),
                            idx = $el.index(),
                            distance = idx * 100;

                    dragger.options.disableDragUp = idx == length - 1; // 切换到样式图片tab后禁用上拉
                    $el.addClass('curr').siblings().removeClass('curr');
                    $item.css('position','absolute').eq(idx).css('position','relative');
                    $inner.css({
                        '-webkit-transition':'all .3s ease',
                        'transition':'all .3s ease',
                        '-webkit-transform':'translate3d(-' + distance + '%,0,0)',
                        'transform':'translate3d(-' + distance + '%,0,0)'
                    });
                    if(!$el.data('lazy')) {
                        lazyimg(idx);
                        $el.data('lazy', 'true');
                    }
                })

                $(window).on('scroll', function() {
                    var st = document.body.scrollTop || document.documentElement.scrollTop;
                    if (st >= _distance) {
                        $tab.addClass('tab-fix');
                    } else {
                        $tab.removeClass('tab-fix');
                    }
                })
            }
        }
        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>