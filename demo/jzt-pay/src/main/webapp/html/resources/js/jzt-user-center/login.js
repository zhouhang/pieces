/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    $(function(){
        $('input[name=username]').focus(function() {
            if ($(this).val() == '手机号/登录名') {
                $(this).val('');
            }
            else {
                $(this).val($(this).val());
            }
        }).blur(function(){
            if($(this).val() == 0){
                $(this).val('手机号/登录名');
            }else{
                $(this).val($(this).val());
            }
        });
    })
});