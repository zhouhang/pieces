/**
 * Created by zhouli on 2015/1/19.
 */
$(function(){
    //顶部菜单弹层
    function hoverer(id){
        $(id).hover(
            function(){
                $(this).children('span').addClass('cur1');
                $(this).children().children('i').addClass('cur');
                $(this).children('.sub').show();
            },
            function(){
                $(this).children('span').removeClass('cur1');
                $(this).children('span').children('i').removeClass('cur');
                $(this).children('.sub').hide();
            })
    }
    hoverer('#myZYC,#Service,#QA,#webNav');
});

