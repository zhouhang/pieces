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

<#include "./common/navigation.ftl">

<section class="ui-content">
    <ul>
        <li>
            <a id="wechatLogin" class="current" href="javascript:;"       >
                <i class="fa fa-home"></i>
                <span>登陆按钮</span>
            </a>
        </li>
    </ul>


</section><!-- /ui-content -->

<#include "./common/footer.ftl"/>
<script>

    var _global = {
        fn: {
            init: function() {
                this.slide();


                $("#wechatLogin").click(function(){
                    alert("is:"+is_weixin());
                    var url = "/center/index";
                    if(is_weixin()){
                        url+="?source=WECHAT";
                    }
                    location.href=url;
                })

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