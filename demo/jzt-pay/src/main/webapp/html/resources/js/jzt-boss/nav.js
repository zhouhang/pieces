/**
 * Created by zhouli on 2014/11/12.
 */
/*
    zhouli by 2014.11.12
 */
$(function(){
    var Li = $('.nav > li'),
        subLi = $('.sub-nav > li'),
        popLi = $('.pop-sub-nav p a');
    $('.nav > li:first').addClass('cur');
    Li.on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).children('.sub-nav').show();
        $(this).siblings().children('.sub-nav').hide();
        $(this).siblings().children('.sub-nav').find('.sub-navs').hide();
        $(this).siblings().children('.sub-nav').children('li').removeClass('hover');
        $(this).siblings().children('.sub-nav').children('li').children('.sub-navs').children('li').removeClass('hover');
        $(this).children('.pop-sub-nav').hide();
    });
    subLi.on('click',function(){
        $(this).addClass('hover').siblings().removeClass('hover');
        $(this).children('.sub-navs').show();
        $(this).siblings().children('.sub-navs').hide();

    });
    Li.hover(
        function(){
            var k = $(this).children('.sub-nav');
            if(k.css('display')=='none'){
                $(this).children('.pop-sub-nav').show();
            }
            else
            {
                $(this).children('.pop-sub-nav').hide();
            }
        },
        function(){
            $(this).children('.pop-sub-nav').hide();
        }
    );
    popLi.on('click',function(){
        $(this).parents('.pop-sub-nav').prev('.sub-nav').show();
        $(this).parents('.pop-sub-nav').prev('.sub-nav').parent().siblings().children('.sub-nav').hide();
        $(this).parents('.pop-sub-nav').prev('.sub-nav').children('li').eq($(this).index()).children('.sub-navs').show();
        $(this).parents('.pop-sub-nav').prev('.sub-nav').children('li').eq($(this).index()).siblings().children('.sub-navs').hide();
        return false;
    });
    $('.sub-navs li a').on('click',function(){
        $(this).parent().addClass('hover').siblings().removeClass('hover');
        $(this).parents('.sub-nav li').siblings().children('.sub-navs ').children('li').removeClass('hover');
    });
    /* 左侧栏的高度 */
    var Height = $(document).height();
    var HeightLeft = Height - 63;
    $('.nav').css('height',HeightLeft);
});
