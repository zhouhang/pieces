<!DOCTYPE html>
<html lang="en">
<head>
    <title>药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body body-gray">
<header class="ui-header">
    <div class="logo">药优优药材商城</div>
    <div class="abs-r mid">
        <a href="category/search"><i class="fa fa-search"></i></a>
    </div>
</header><!-- /ui-header -->
<#include "./common/navigation.ftl">
<section class="ui-content">

    <div class="ui-slide" id="slide1">
        <ul>
            <#list banners as banner>
                <li><a href="${banner.href!}"><img src="${banner.pictureUrl!}" alt="${banner.name!}"></a></li>
            </#list>
        </ul>
    </div>

    <#list specials as special>
        <div class="ui-floor">
            <a href="${special.href!}"><img src="${special.pictureUrl!}" alt="${special.name!}"></a>
        </div>
    </#list>


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