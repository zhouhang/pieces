/**
 * Created by zhouli on 2014/11/12.
 */
/*
    zhouli by 2014.11.12
 */
$(function(){
    var Link = $('.nav > li > a');
    var Li = $('.nav > li');
    var subLink = $('.sub-nav li a');
    Link.on('click',function(){
        $(this).parent('li').addClass('cur').siblings().removeClass('cur');
        $(this).parent('li').children('.sub-nav').show();
        $(this).parent('li').siblings().children('.sub-nav').hide();
        $(this).parent('li').children('.pop-sub-nav').hide();
        subLink.removeClass('hover');
        return false;
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
    subLink.on('click',function(){
        $(this).addClass('hover');
        $(this).parent().siblings().children().removeClass('hover');
    });
    /* 左侧栏的高度 */
    var Height = $(window).height()+$(document).scrollTop();
    $('.nav').css('height',Height);
});
