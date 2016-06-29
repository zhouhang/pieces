/**
 * Created by zhouli on 2014/12/23.
 */
$(function(){
    $('#myZYC').hover(
        function(){
            $(this).children('span').addClass('hover');
            $(this).children('div').show();
        },
        function(){
            $(this).children('span').removeClass('hover');
            $(this).children('div').hide();
        }
    );
//导航折叠效果
    $('.menu>li>a').on('click',function(){
        $(this).parent().children('.sub-menu').toggleClass('cur');
        $(this).children('.arrow').toggleClass('cur')
        return false;
    });
    var subLi = $('.sub-menu li');
    //var span = "<span></span>";
    subLi.on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
        $(this).parents('li').siblings().children('.sub-menu').children('li').removeClass('cur');
    });
//搜索框值清空
    function formVal (idName){
        idName.focus(function(){
            if (idName.val() == "请输入搜索内容") {
                idName.val('');
            }
            else {
                idName.val(idName.val());
            }
        }).blur(function(){
            if(idName.val() == 0){
                idName.val("请输入搜索内容");
            }else{
                idName.val(idName.val());
            }
        })
    }
    formVal($('#searchText'));
    //底部扫码
    $('#erweima').hide();
    $('#saoma').hover(
        function(){
            $('#erweima').show();
        },
        function(){
            $('#erweima').hide();
        }
    )
});
