/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    $('.form li span a').each(function(){
        $(this).click(function() {
            var reMessage = $(this).parents('li').children('.re-message');
            if(reMessage.length == 2)
            {
                if($(this).text()=='设置')
                {
                    $(this).parents('li').children('.re-message:eq(0)').show();
                    $(this).parents('li').children('.re-message:eq(1)').hide();
                }
                if($(this).text()=='修改')
                {
                    $(this).parents('li').children('.re-message:eq(1)').show();
                    $(this).parents('li').children('.re-message:eq(0)').hide();
                }
            }
            else{
                $(this).parents('li').children('.re-message').show();
                $(this).css('color','#bdbdbd');
                return false;
            }
        })
    });
    $('.re-message input[type=button]').click(function(){
        $(this).parents('.re-message').hide();
        $(this).parents('.re-message').siblings('.tip2').children('a').css('color','#0088cc');
    })
});