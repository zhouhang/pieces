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
	var $aside = $('#jaside'),
		URL = document.URL.split('#')[0].split('?')[0].toLowerCase(),
        urlBefore = URL.split('/')[3] + URL.split('/')[4];

	$aside.on('click', 'dt', function() {
		$(this).next().slideToggle().parent().toggleClass('extend');
		$(this).parent().siblings().find('dd').slideUp().parent().removeClass('extend');
	})
	.find('.active').addClass('extend');

	// 导航高亮
	$aside.find('a').each(function() {
		var url = this.href.toLowerCase(),
			hrefBefore = url.split('/')[3] + url.split('/')[4];
        if (URL === url) {
            $(this).addClass('current').closest('dl').addClass('expand');
            return false; // break
        }
		if(urlBefore === hrefBefore){
			$(this).closest('dl').addClass('expand');
		}
    }) 

    // 以下代码本地专用
	$aside.html() === '' && $.ajax({
		url: 'inc/aside.html',
		success: function(innerHtml) {
			$aside.off().html(innerHtml);
			_aside();
		}
	})
}

;(function($){
	var defaults = {
		clickToHide: true 	// 点击关闭
		,delay: 5e3 		// 5秒后自动关闭，为0时不关闭
		,title: '提示消息' 	// 文字
		,text: '' 			// 说明
		,type: 'warn' 		// 类型：错误(error)，正确(success)，警告(warn)
		,call: null 		// 关闭后回调
	}

	var icons = {
		error: '<i class="fa fa-times-circle"></i>'
		,success: '<i class="fa fa-check-circle"></i>'
		,warn: '<i class="fa fa-prompt"></i>'
	}
	var $wrapper = $('<div class="notify-wrapper" />').appendTo($('body'));

	$.notify = function(options) {
		var settings = $.extend({}, defaults, options);
		var modal = [];
		modal.push('<div class="message ', settings.type, settings.clickToHide ? ' hidable' : '', '">');
		modal.push(	   '<div class="inner">');
		modal.push(	       '<div class="icon">');
		modal.push(	           icons[settings.type]);
		modal.push(	       '</div>');
		modal.push(	       '<div class="text', (settings.text ? '' : ' mid') , '">');
		modal.push(	           '<h3>', settings.title, '</h3>');
		modal.push(	           '<p>', settings.text, '</p>');
		modal.push(	       '</div>');
		modal.push(	   '</div>');
		modal.push('</div>');

		var $modal = $(modal.join(''));
		$wrapper.prepend($modal);
		if (settings.delay === 0) {
			$modal.slideDown(400);

		} else {
			$modal.slideDown(400).delay(settings.delay).slideUp(200, function() {
				$modal.remove();			
			});
		}
		typeof options.call === 'function' && options.call();
	};

	// 点击关闭
	$('body').on('click', '.hidable', function() {
		var $self = $(this);
		$self.stop().slideUp(200, function() {
			$self.remove();
		});
	})
})(jQuery)


$(function() {
	_fix();
	_aside();
})