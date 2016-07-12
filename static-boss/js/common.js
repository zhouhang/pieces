;(function($){
	var defaults = {
		delay: 5e3 // 5秒后自动关闭
		,speed: 300 // 速度
		,msg: '提示消息'
		,type: 'error' // 类型：错误(error)，正确(success)，警告(warn)
	}

	var icons = {
		error: '<i class="fa fa-times-circle"></i>'
		,success: '<i class="fa fa-check-circle"></i>'
		,warn: '<i class="fa fa-prompt"></i>'
	}
	var $wrapper = $('<div class="notify-wrapper" />').appendTo($('body'));

	$.notify = function(options) {
		var settings = $.extend({}, defaults, options);
		var $modal = $('<div class="message ' + settings.type + '"><div class="inner">' + icons[settings.type] + '<span>' + settings.msg + '</span></div></div>');
		$wrapper.prepend($modal);
		$modal.slideDown(400).delay(settings.delay).slideUp(200, function() {
			$modal.remove();
		});
	};

	var $tips = $('<div class="tips-wrapper" />').appendTo($('body'));
	var tipsTimer = 0;
	$.tips = function(options) {
		var settings = $.extend({}, defaults, options);
		$tips.html('<span class="' + settings.type + '">' + icons[settings.type] + settings.msg + '</span>');
		$tips.stop(true).animate({top: 0}, 400);
		tipsTimer && clearTimeout(tipsTimer);
		tipsTimer = setTimeout(function() {
			$tips.animate({top: '-30px'}, 200);
		}, settings.delay);
	}
})(jQuery)
