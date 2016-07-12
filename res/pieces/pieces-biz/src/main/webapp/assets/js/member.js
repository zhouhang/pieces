$(function(){
	$('.side').on('click', 'dt', function() {
		$(this).parent().toggleClass('expand').siblings().removeClass('expand');
	})
})