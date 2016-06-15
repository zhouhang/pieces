/**
 * Created by zhouli on 2014/12/23.
 */
$('.menu>li').on('click',function(){
    $(this).children('.sub-menu').toggleClass('cur');
});