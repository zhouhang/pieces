// 载入js
function loadScript(url) {
    var script = document.createElement('script');
    script.src = url;
    document.getElementsByTagName('body')[0].appendChild(script);
}

// 防抖动函数
function debounce(func, wait, immediate) {
    var timeout;
    return function() {
        var context = this, args = arguments;
        var later = function() {
            timeout = null;
            if (!immediate) func.apply(context, args);
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) func.apply(context, args);
    };
};

// 简单的节流函数
function throttle(func, wait, mustRun) {
    var timeout,
        startTime = new Date();
 
    return function() {
        var context = this,
            args = arguments,
            curTime = new Date();
 
        clearTimeout(timeout);
        // 如果达到了规定的触发时间间隔，触发 handler
        if(curTime - startTime >= mustRun){
            func.apply(context,args);
            startTime = curTime;
        // 没达到触发间隔，重新设定定时器
        }else{
            timeout = setTimeout(func, wait);
        }
    };
};

// 图片懒加载
function loazyload() {
	var $images = $('img[data-original]'),
		size = $images.length,
		count = 0;
		
	var getEleCoord = function(element) {
		var h = element.offsetHeight,
			t = 0;

	    while (element.offsetParent) {
			t += element.offsetTop;
	        element = element.offsetParent;
	    }
	    return {top: t, height: h};
	}

	var imgLoad = function() {
		$images.each(function() {
			var src = this.getAttribute('data-original');
			if ($(this).attr('loaded') === '1') {
				return true; // continue
			}

			if (src) {
				var docHeight = document.documentElement.clientHeight || document.body.clientHeight;
				var docTop  = document.documentElement.scrollTop || document.body.scrollTop;
				var coord  = getEleCoord(this);
				if (coord.top <= docTop + docHeight && coord.top + coord.height >= docTop) {
					this.onerror = function() {
						this.onerror = null;
						this.src = 'images/default-img.png';
					}
					this.src = src;
					this.removeAttribute('data-original');
				 	count ++;
					$(this).removeClass('lazyload').attr('loaded', '1');
				}
			} else {
				count ++;
				this.removeAttribute('data-original');
				$(this).removeClass('lazyload').attr({
					'loaded': '1',
					'src': 'images/default-img.png'
				});
			}

			count === size && $(window).off('scroll.lazyload');
		})
	}
	imgLoad();
	count < size && $(window).on('scroll.lazyload', throttle(imgLoad,200,500));
}

function bindSearch() {
	var $searchForm = $('#_search_form'),
		timer, call;

	if ($searchForm.length === 0) {
		return false;
	}


	// 可以页面其他地方引入了autocomplete.js
	if($.isFunction($.fn.autocomplete)){ 
		call();
	} else {
		loadScript('js/jquery.autocomplete.min.js');
		timer = setInterval(function() {
			call();
		}, 300);
	}

	call = function() {
		timer && clearTimeout(timer);
		$('#_search_ipt').autocomplete({
	        serviceUrl: '/commodity/search/auto',
	        paramName: 'keyword',
	        groupBy: 'category',
	        transformResult: function(response) {
	            response = JSON.parse(response);
	            return  {suggestions:$.map(response, function(dataItem) {
	            	return {
	            		value: (dataItem.category ? dataItem.category + ':' : '') + dataItem.value,
	            		data: {'category': dataItem.category}
	            	}
	            })};
	        },
	        onSelect: function (suggestion) {
	            $searchForm.submit();
	        }
	    });
	}
}

// 用户中心导航高亮
function currNav() {
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
}

// 询价
function quoteEvent() {
	$('body').on('click', '.btn-quote', function() {
        var url = $(this).attr('href');

		// 检查登录状态
    	$.ajax({
            url: "/pop",
            type: "POST",
            dataType : "json",
            // data : {url : url},
            success: function(data){
            	var status = data.status;
            	if(status === 'y') {
            		location.href = url;
            	}else{
            		layer.open({
                        type: 2,
                        title: '账户登录',
                        area: ['360px', '360px'],
                        content: ['/popLogin?url=' + url, 'no']
                    });
            	}
            }
        });
    	return false;
    })
}
function pageInit() {
	// 开启图片懒加载
	loazyload();
	// search
	bindSearch();
	// 用户中心导航高亮
	currNav();
	// 询价按钮
	quoteEvent();
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
	var $wrapper = $('<div class="notify-wrapper" />').prependTo($('body'));

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
	$wrapper.on('click', '.hidable', function() {
		var $self = $(this);
		$self.stop().slideUp(200, function() {
			$self.remove();
		});
	})

})(jQuery)

$(function() {
	pageInit();
})
