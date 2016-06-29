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

    //搜索匹配
    //搜索框hover事件
	$('.search .text-bg').hover(
		function(){
			$('.match-bg h5').length>0?$('.match-bg').show():$('.match-bg').hide();
		},
		function(){
			$('.match-bg').hide();
		}
	);

    $('#searchEngineListingText').focus(function(){
        if($(this).val() == "输入名称找药材"){
            $(this).val('');
        }
    }).blur(function(){
        if($(this).val() === ''){
            $(this).val('输入名称找药材');
        }
    });

    //一级菜单点击增亮效果
    $('.nav ul>li').on('click',function(){
        $(this).addClass('cur').siblings().removeClass('cur');
    });

    //左侧菜层级菜单效果
    $('.nav .list').hover(
        function(){
            $(this).children('.sub-nav').show();
        },
        function(){
            $(this).children('.sub-nav').hide();
        }
    );
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

    //底部最后一个A链接去除右边框
    $('.foot p.links').each(function(){
        $(this).children('a').first().css('border','none');
    })
});

