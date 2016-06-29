/**
 * Created by zhouli on 2014/12/3.
 */
$(function(){
    //弹层
    var ReMember = $('.operate-2');
    var Close = $('.close');
    var html = '<div class="bghui"></div>';
    ReMember.each(function(){
        $(this).click(function(){
            $('#reRole').show();
            $('body').append(html);
        })
    });
    Close.each(function(){
        $(this).click(function(){
            $(this).parent().hide();
            $('.bghui').remove();
        })
    });
    $('.btn-add').on('click',function(){
        $('#addRole').show();
        $('body').append(html);
    })
});