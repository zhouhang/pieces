/**
 * Created by zhouli on 2015/1/30.
 */
(function($) {
    $.fn.showImg = function(option){
        var option = option || {};
        var animateSpeed = option.aTime || 500; //动画速度，默认200毫秒
        var timeSpeed = option.tTime || 5000; //时间速度，默认2000毫秒
        var picUrlSelector = option.picUrlSelector || ".fouce-img ul"; //时间速度，默认2000毫秒
        var prev = option.prev || "#ad-1 .prev";
        var next = option.next || "#ad-1 .next";
        var items = option.items || "#ad-1 .items p";
        //var goAuto; //自动轮播
        var curIndex = 0;
        var MoveFlag = 0;
        var t = $(this);
        var _picUl = t.children(picUrlSelector); //图片UL
        var _pic = _picUl.children('li').find('img');
        var _item = items; //图片Li
        var _wid = _pic.width();
        var _len = _picUl.children('li').length;
        var _next = next;
        var _prev = prev;
        //点击左边接钮
        $(_next).on('click',function(){
            MoveFlag = 0;
            curIndex++;
            if(curIndex<=_len-1){
                $(this).addClass('cur');
                _picUl.animate({left: -_wid*curIndex}, animateSpeed);
                $(_item).eq(curIndex).addClass('cur').siblings().removeClass('cur');
            }
            if(curIndex>=_len){
                curIndex = _len-1;
                _picUl.css('left',-_wid*curIndex);
                $(this).removeClass('cur');
            }
        });
        //点击右边接钮
        $(_prev).on('click',function(){
            MoveFlag = 1;
            --curIndex;
            if( curIndex>=0 ){
                $(this).addClass('cur');
                _picUl.animate({left: -_wid*curIndex}, animateSpeed);
                $(_item).eq(curIndex).addClass('cur').siblings().removeClass('cur');
            }
            if( curIndex<0 ){
                curIndex=0;
                _picUl.css('left','0');
                $(this).removeClass('cur');
            }
        });
        //自动播放
        function play(){
            if( MoveFlag == 0 ){
                curIndex++;
                if(curIndex<=_len-1){
                    //$(this).addClass('cur');
                    _picUl.animate({left:-_wid*curIndex}, animateSpeed);
                    $(_item).eq(curIndex).addClass('cur').siblings().removeClass('cur');
                }
                if(curIndex>=_len){
                    MoveFlag = 1;
                    curIndex = _len-1;
                    _picUl.css('left',-_wid*curIndex);
                    $(this).removeClass('cur');
                }
            }
            else{
                --curIndex;
                if( curIndex>=0 ){
                    _picUl.animate({left: -_wid*curIndex}, animateSpeed);
                    $(_item).eq(curIndex).addClass('cur').siblings().removeClass('cur');
                }
                if( curIndex<0 ){
                    MoveFlag = 0;
                    curIndex=0;
                    _picUl.css('left','0');
                }
            }
        }
        setInterval(play,timeSpeed);
    }
})(jQuery);

