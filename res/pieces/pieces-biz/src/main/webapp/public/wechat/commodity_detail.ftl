<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>商品详情-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="goods-info">
        <div class="pic rs-pic">
            <img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>" />
        </div>

        <h1>${title}</h1>
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
                            <strong>${commodity.name!}</strong>
                        </li>
                        <li>
                            <em>片&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</em>
                            <strong>${commodity.spec!}</strong>
                        </li>
                        <li>
                            <em>规格等级：</em>
                            <strong>${commodity.level!}</strong>
                        </li>
                        <li>
                            <em>原药产地：</em>
                            <strong>${commodity.originOf!}</strong>
                        </li>
                        <li>
                            <em>外观描述：</em>
                            <strong>${commodity.exterior!}</strong>
                        </li>
                        <li>
                            <em>执行标准：</em>
                            <strong>${commodity.executiveStandard!}</strong>
                        </li>
                    <#if commodity.attributeView??>
                        <#list commodity.attributeView?keys as key>
                            <#if key!="生产厂家">
                                <li title="${commodity.attributeView[key]}"><em>${key}：</em>${commodity.attributeView[key]}</li>
                            </#if>
                        </#list>
                    </#if>
                    </ul>
                </div>

                <div class="item">
                    <div class="img">
                        ${commodity.details}
                    </div>
                </div>
            </div>
        </div>
    </div>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script src="/h5-static/js/dragloader.min.js"></script>
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
                        this.src = this.getAttribute('_src');
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