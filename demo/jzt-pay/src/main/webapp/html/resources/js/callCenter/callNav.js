/**
 * Created by zhouli on 2015/2/4.
 */
$(function(){
    //顶部搜索
    $('.call-text').focus(function(){
        if($(this).val() == "输入简单的问题描述或者问题关键字进行本站搜索"){
            $(this).val('');
        }
        else{
            $(this).val($(this).val);
        }
    }).blur(function(){
        if($(this).val() == 0){
            $(this).val('输入简单的问题描述或者问题关键字进行本站搜索');
        }else{
            $(this).val($(this).val());
        }
    });

    //左侧菜单
    $('.call-sub-nav>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).children('.call-sub-navs').show();
        $(this).siblings().children('.call-sub-navs').hide();
        $(this).siblings().children('.call-sub-navs').find('a').removeClass('cur');
    });
    $('.call-sub-navs>li a').on('click',function(){
        $(this).addClass('cur').parent().siblings().children().removeClass('cur');
    })
});