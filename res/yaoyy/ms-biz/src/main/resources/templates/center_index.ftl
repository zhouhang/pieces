<!DOCTYPE html>
<html lang="en">
<head>
    <title>药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body body-gray">
<header class="ui-header">
    <div class="logo">药优优药材商城</div>
</header><!-- /ui-header -->

<footer class="ui-footer">
    <nav class="ui-nav">
        <ul>
            <li>
                <a class="current" href="index.html">
                    <i class="fa fa-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <li>
                <a href="product_list.html">
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
    <ul>
        <li>

            <h1>用户中心</h1>
        </li>
    </ul>


</section><!-- /ui-content -->

<#include "./common/footer.ftl"/>
<script>

    var _global = {
        fn: {
            init: function() {
                this.slide();



            },
            slide: function() {
                var $slide = $('#slide1'),
                        $nav,
                        length = $slide.find('li').length;

                if (length < 2) {
                    return;
                }
                var nav = ['<div class="nav">'];
                for (var i = 0 ; i < length; i++) {
                    nav.push( i === 0 ? '<i class="current"></i>' : '<i></i>');
                }
                nav.push('</div>');
                $slide.append(nav.join(''));
                $nav = $slide.find('i');

                $slide.swipeSlide({
                    firstCallback : function(i){
                        $nav.eq(i).addClass('current').siblings().removeClass('current');
                    },
                    callback : function(i){
                        $nav.eq(i).addClass('current').siblings().removeClass('current');
                    }
                });
            }
        }
    }

    $(function(){
        _global.fn.init();

    });

</script>
</body>
</html>