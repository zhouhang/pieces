// 页面布局
function _fix() {
	$window = $(window);
	var sidebar_height =  $('.aside').height();

	var fix = function() {
	    $('.wrapper').css('min-height', Math.max($window.height(), sidebar_height));
	}

	$window.on('resize', fix);
	fix();
}

// 侧栏导航
function _aside() {
	var $aside = $('.aside');
	$aside.on('click', 'dt', function() {
		$(this).toggleClass('extend').next().slideToggle();
		$(this).parent().siblings().find('dd').slideUp().prev().removeClass('extend');
	})
	.find('.active dt').addClass('extend');

}

$(function() {
	_fix();
	_aside();
})