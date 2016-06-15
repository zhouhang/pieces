/**
 * Created by zhouli on 2014/11/12.
 */
/*
    zhouli by 2014.11.12
 */

$(function(){
    $('.usename').focus(function() {
        if ($(this).val() == '用户名') {
            $(this).val('');
        }
        else {
            $(this).val($(this).val());
        }
    }).blur(function(){
        if($(this).val() == 0){
            $(this).val('用户名');
        }else{
            $(this).val($(this).val());
        }
    });
    $('.password').focus(function(){
        /*if($(this).val() =="密码"){
            $(this).val('');
        }
        $(this).attr('type','password');*/
        $('.mima').hide();

    }).blur(function(){
        if($(this).val() == ''){
            $('.mima').show();
        }else {
            $(this).val($(this).val());
            $('.mima').hide();
        }
    });

});