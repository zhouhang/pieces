/**
 * Created by zhouli on 2015/1/19.
 */
$(function(){
    //顶部扫码关闭
    $('#top-saoma').on('click',function(){
        $(this).parent().hide();
    });

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


    //搜索框分类切换
    $('.search ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });
    /*//搜索匹配
    $('.search .text-bg').hover(
        function(){
            $(this).children('.match-bg').show();
        },
        function(){
            $(this).children('.match-bg').hide();
        }
    );
    $('.match-bg h5').click(function(){
        var _val = $(this).text();
        $('#Search').val(_val);
        $(this).parent().hide();
    });

    $('#Search').keydown(function(){
        $(this).children('.match-bg').show();


    });*/

    //一级菜单点击增亮效果
    $('.nav ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });

    //左侧菜层级菜单效果
    $('.sub-nav .sub-nav-box').hover(
        function(){
            $(this).children('.tit').addClass('cur');
            //alert($(this).next('sub-navs'));
            $(this).children('.sub-navs').show();
        },
        function(){
            $(this).children('.tit').removeClass('cur');
            $(this).children('.sub-navs').hide();
        }
    );
    $('.sub-nav .sub-nav-box').slice(6, 11).children('.sub-navs').css('bottom','-2px');
    $('.sub-nav .sub-nav-box').slice(0, 6).children('.sub-navs').css('top','0');

    //tabs
    function Tabs(tabs,conts){
        $(tabs).bind('click mousemove',function(){
            $(this).addClass('cur').siblings().removeClass('cur');
            $(conts).children().eq($(this).index()).show().siblings().hide();
        });
    }
    Tabs('#tabs1 li','#tabCont1');

    //焦点图
    function Hover(elm){
        $(elm).hover(
            function(){
                $('.prev').show();
                $('.next').show();
            },
            function(){
                $('.prev').hide();
                $('.next').hide();
            }
        )
    }
    Hover('.fouce-img');

    /*$("#ad-1").showImg({
        aTime: 500,	//设置动画时间
        items: $('#ad-1 .items p')
        //tTime: 5000, //设置轮播时间
        //imgStep:5,
        //goAuto:true//每页显示多少张缩略图
    });
    $("#ad-2").showImg({
        aTime: 500,	//设置动画时间
        prev: $('#ad-2 .prev'),
        next: $('#ad-2 .next'),
        items: $('#ad-2 .items p')
    });
    $("#ad-3").showImg({
        aTime: 500,	//设置动画时间
        prev: $('#ad-3 .prev'),
        next: $('#ad-3 .next'),
        items: $('#ad-3 .items p')
    });
    $("#ad-4").showImg({
        aTime: 500,	//设置动画时间
        prev: $('#ad-4 .prev'),
        next: $('#ad-4 .next'),
        items: $('#ad-4 .items p')
    });*/

    $('#searchBtn').searcher({
        onHot: function(){
            window.open('http://www.54315.com/search?keyWords='+$(this).html());
        },
        onSearch: function(){
            window.location.href='http://www.54315.com/search?keyWords='+$('input[type="text"].search-text').val()

        }
    });

    $('#searchEngineListingText').focus(function(){
        if($(this).val() == "输入名称找药材"){
            $(this).val('');
        }
    }).blur(function(){
        if($(this).val() === ''){
            $(this).val('输入名称找药材');
        }
    });

    $("#scrollDiv").Scroll({line:4,speed:600,timer:3000});

    //底部最后一个A链接去除右边框
    $('.foot p.links').each(function(){
        $(this).children('a').first().css('border','none');
    })
});






