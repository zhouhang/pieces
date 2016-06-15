/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    //选项卡
    var Width = $('.tabs-1>li').width() * $('.tabs-1>li').length+10;
    $('#tabs').width(Width);
    $('#tabs>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        var j = $(this).parent().next('#tabCont').children('div');
        j.eq($(this).index()).show().siblings().hide();
    });

    $('input[type=checkbox]').each(function () {

        $(this).click(function(){

            if($(this).attr("checked")){
                $(this).removeAttr("checked");
            }
            else{
                $(this).attr("checked",'true');
            }
        });
    })
});