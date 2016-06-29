/**
 * Created by zhouli on 2015/1/7.
 */
$(function(){
    $('a[name=see]').click(function(){
		//var bigImg = $(this).parent().find('.storeimg img').attr('src');
		//$('#picbbox').append('<img src="'+bigImg+'" />');
        $('#picbbox').show();
        var html = '<div class="bghui"></div>';
        var height = $(document).height();
        $('body').append(html);
        $('.bghui').css('height',height);
        return false;
    });
    $('#Close').click (function(){
        $(this).parents('#picbbox').hide();
        $('.bghui').remove();
    });
    var bigImg = "<img src='http://10.3.1.151/test/1.jpg'   /> ";
    $('#picbbox').append(bigImg);

    //图片点击查看大图拖动效果
    $(function() {
        $('#picbbox').imageView({width:600, height:400});
    });
});