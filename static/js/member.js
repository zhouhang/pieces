$(function(){
	$('.side').on('click', 'dl', function() {
		$(this).toggleClass('expand').siblings().removeClass('expand');
	})
})