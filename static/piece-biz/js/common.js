/*! Lazy Load 1.9.7 - MIT license - Copyright 2010-2015 Mika Tuupola */
!function(e,t,o,r){var n=e(t);e.fn.lazyload=function(i){function f(){var t=0;l.each(function(){var o=e(this);if(!d.skip_invisible||o.is(":visible"))if(e.abovethetop(this,d)||e.leftofbegin(this,d));else if(e.belowthefold(this,d)||e.rightoffold(this,d)){if(++t>d.failure_limit)return!1}else o.trigger("appear"),t=0})}var a,l=this,d={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:t,data_attribute:"original",skip_invisible:!1,appear:null,load:null,defaultImg:"images/default-img.png",placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return i&&(r!==i.failurelimit&&(i.failure_limit=i.failurelimit,delete i.failurelimit),r!==i.effectspeed&&(i.effect_speed=i.effectspeed,delete i.effectspeed),e.extend(d,i)),a=d.container===r||d.container===t?n:e(d.container),0===d.event.indexOf("scroll")&&a.on(d.event,function(){return f()}),this.each(function(){var t=this,o=e(t);t.loaded=!1,(o.attr("src")===r||o.attr("src")===!1)&&o.is("img")&&o.attr("src",d.placeholder),o.one("appear",function(){if(!this.loaded){if(d.appear){var r=l.length;d.appear.call(t,r,d)}e("<img />").one("load",function(){var r=o.attr("data-"+d.data_attribute);o.hide(),o.is("img")?o.attr("src",r):o.css("background-image","url('"+r+"')"),o[d.effect](d.effect_speed),t.loaded=!0;var n=e.grep(l,function(e){return!e.loaded});if(l=e(n),d.load){var i=l.length;d.load.call(t,i,d)}}).one("error",function(){var r=d.defaultImg;o.addClass("def").hide(),o.is("img")?o.attr("src",r):o.css("background-image","url('"+r+"')"),o[d.effect](d.effect_speed),t.loaded=!0;var n=e.grep(l,function(e){return!e.loaded});if(l=e(n),d.load){var i=l.length;d.load.call(t,i,d)}}).attr("src",o.attr("data-"+d.data_attribute))}}),0!==d.event.indexOf("scroll")&&o.on(d.event,function(){t.loaded||o.trigger("appear")})}),n.on("resize",function(){f()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&n.on("pageshow",function(t){t.originalEvent&&t.originalEvent.persisted&&l.each(function(){e(this).trigger("appear")})}),e(o).ready(function(){f()}),this},e.belowthefold=function(o,i){var f;return f=i.container===r||i.container===t?(t.innerHeight?t.innerHeight:n.height())+n.scrollTop():e(i.container).offset().top+e(i.container).height(),f<=e(o).offset().top-i.threshold},e.rightoffold=function(o,i){var f;return f=i.container===r||i.container===t?n.width()+n.scrollLeft():e(i.container).offset().left+e(i.container).width(),f<=e(o).offset().left-i.threshold},e.abovethetop=function(o,i){var f;return f=i.container===r||i.container===t?n.scrollTop():e(i.container).offset().top,f>=e(o).offset().top+i.threshold+e(o).height()},e.leftofbegin=function(o,i){var f;return f=i.container===r||i.container===t?n.scrollLeft():e(i.container).offset().left,f>=e(o).offset().left+i.threshold+e(o).width()},e.inviewport=function(t,o){return!(e.rightoffold(t,o)||e.leftofbegin(t,o)||e.belowthefold(t,o)||e.abovethetop(t,o))},e.extend(e.expr[":"],{"below-the-fold":function(t){return e.belowthefold(t,{threshold:0})},"above-the-top":function(t){return!e.belowthefold(t,{threshold:0})},"right-of-screen":function(t){return e.rightoffold(t,{threshold:0})},"left-of-screen":function(t){return!e.rightoffold(t,{threshold:0})},"in-viewport":function(t){return e.inviewport(t,{threshold:0})},"above-the-fold":function(t){return!e.belowthefold(t,{threshold:0})},"right-of-fold":function(t){return e.rightoffold(t,{threshold:0})},"left-of-fold":function(t){return!e.rightoffold(t,{threshold:0})}})}(jQuery,window,document);

$.easing.easeInOutExpo = function (x, t, b, c, d) {
	if (t==0) return b;
	if (t==d) return b+c;
	if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
	return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
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
})(jQuery);

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

function bindSearch() {
	var $searchForm = $('#_search_form');
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

// 用户中心导航高亮
function currNav() {
	var $side = $('.member-box').find('.side'),
        URL = document.URL.split('#')[0].split('?')[0].toLowerCase(),
        urlBefore = URL.split('/')[3] + URL.split('/')[4];

	$side.find('a').each(function() {
		var url = this.href.toLowerCase(),
			hrefBefore = url.split('/')[3] + url.split('/')[4];

        if (URL === url) {
            $(this).addClass("curr");
            return false; // break
        }
    }) 

}

// 询价
function quoteEvent() {
	$('body').on('click', '.j_pop_login', function() {
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

// 商品分类
function cat() {
	$cat = $('#jcat');
	var timer = 0;
	var isShow = false;
	var defautShow = $cat.parent().hasClass('cat-show');
	var hideCat = function() {
		timer && clearTimeout(timer);
		timer = setTimeout(function() {
			$cat.hide();
		}, 200);
	}

	$cat.on({
		'mouseenter': function() {
			var $self = $(this);
			timer && clearTimeout(timer);
			timer = setTimeout(function() {
				isShow = true;
				$self.addClass('on').siblings().removeClass('on');
			}, isShow ? '0' : 200);
		}
	}, 'li');

	$cat.on('mouseleave', function() {
		timer && clearTimeout(timer);
		$(this).find('.on').removeClass('on');
		isShow = false;
		!defautShow && hideCat();
	})

	// cat-list
	!defautShow && $cat.prev().on({
		'mouseenter': function() {
			timer && clearTimeout(timer);
			timer = setTimeout(function() {
				$cat.show();
			}, 200);
		},
		'mouseleave': function() {
			hideCat();
		}
	})

}

function gotop() {
	var $win = $(window);
	var $gotop = $('<div class="gotop"><a href="javascript:;"><b class="fa fa-chevron-up"></b><em>返回顶部</em></a></div>').appendTo($('body'));
	var threshold = $(window).height();

	$win.on('scroll', function() {
		var stop  = $win.scrollTop();
		$gotop[stop < threshold ? 'fadeOut' : 'fadeIn'](100);
	})
	$gotop.on('click', function() {
		$('html, body').animate({
			scrollTop: 0
		}, 700, 'easeInOutExpo');
	});
}

function pageInit() {
	// 开启图片懒加载
	$('.lazyload').lazyload({
		placeholder : 'images/blank.gif',
       	effect_speed: 700,
       	effect: 'fadeIn'
    }); 
    // gotop
    gotop();
	// search
	// bindSearch();
	// 用户中心导航高亮
	currNav();
	// 询价按钮
	quoteEvent();
	// 商品分类
	cat();
}

$(function() {
	pageInit();
})
