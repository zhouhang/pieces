$(function(){
	// 导航高亮
	var $side = $('.side'),
        URL = document.URL.split('#')[0].split('?')[0];

    $side.find('a').each(function() {
        if (URL.toLowerCase().indexOf(this.href.toLowerCase()) !== -1) {
            $(this).addClass("curr").closest('dl').addClass('expand');
            return false; // break
        }
    }) 

	$side.on('click', 'dt', function() {
		$(this).parent().toggleClass('expand').siblings().removeClass('expand');
	})
})