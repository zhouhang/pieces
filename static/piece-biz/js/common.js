/*! Lazy Load 1.9.7 - MIT license - Copyright 2010-2015 Mika Tuupola */
!function(t,e,o,i){var n=t(e);t.fn.lazyload=function(r){function a(){var e=0;l.each(function(){var o=t(this);if(!h.skip_invisible||o.is(":visible"))if(t.abovethetop(this,h)||t.leftofbegin(this,h));else if(t.belowthefold(this,h)||t.rightoffold(this,h)){if(++e>h.failure_limit)return!1}else o.trigger("appear"),e=0})}var f,l=this,h={threshold:0,failure_limit:0,event:"scroll",effect:"show",container:e,data_attribute:"original",skip_invisible:!1,appear:null,load:null,defaultImg:"images/default-img.png",placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};return l.each(function(){t(this).data(h.data_attribute)||t(this).attr("data-"+h.data_attribute,h.defaultImg),t(this).one("error",function(){t(this).attr("src",h.defaultImg)})}),r&&(i!==r.failurelimit&&(r.failure_limit=r.failurelimit,delete r.failurelimit),i!==r.effectspeed&&(r.effect_speed=r.effectspeed,delete r.effectspeed),t.extend(h,r)),f=h.container===i||h.container===e?n:t(h.container),0===h.event.indexOf("scroll")&&f.on(h.event,function(){return a()}),this.each(function(){var e=this,o=t(e);e.loaded=!1,(o.attr("src")===i||o.attr("src")===!1)&&o.is("img")&&o.attr("src",h.placeholder),o.one("appear",function(){if(!this.loaded){if(h.appear){var i=l.length;h.appear.call(e,i,h)}t("<img />").one("load",function(){var i=o.attr("data-"+h.data_attribute);o.hide(),o.is("img")?o.attr("src",i):o.css("background-image","url('"+i+"')"),o[h.effect](h.effect_speed),e.loaded=!0;var n=t.grep(l,function(t){return!t.loaded});if(l=t(n),h.load){var r=l.length;h.load.call(e,r,h)}}).attr("src",o.attr("data-"+h.data_attribute))}}),0!==h.event.indexOf("scroll")&&o.on(h.event,function(){e.loaded||o.trigger("appear")})}),n.on("resize",function(){a()}),/(?:iphone|ipod|ipad).*os 5/gi.test(navigator.appVersion)&&n.on("pageshow",function(e){e.originalEvent&&e.originalEvent.persisted&&l.each(function(){t(this).trigger("appear")})}),t(o).ready(function(){a()}),this},t.belowthefold=function(o,r){var a;return a=r.container===i||r.container===e?(e.innerHeight?e.innerHeight:n.height())+n.scrollTop():t(r.container).offset().top+t(r.container).height(),a<=t(o).offset().top-r.threshold},t.rightoffold=function(o,r){var a;return a=r.container===i||r.container===e?n.width()+n.scrollLeft():t(r.container).offset().left+t(r.container).width(),a<=t(o).offset().left-r.threshold},t.abovethetop=function(o,r){var a;return a=r.container===i||r.container===e?n.scrollTop():t(r.container).offset().top,a>=t(o).offset().top+r.threshold+t(o).height()},t.leftofbegin=function(o,r){var a;return a=r.container===i||r.container===e?n.scrollLeft():t(r.container).offset().left,a>=t(o).offset().left+r.threshold+t(o).width()},t.inviewport=function(e,o){return!(t.rightoffold(e,o)||t.leftofbegin(e,o)||t.belowthefold(e,o)||t.abovethetop(e,o))},t.extend(t.expr[":"],{"below-the-fold":function(e){return t.belowthefold(e,{threshold:0})},"above-the-top":function(e){return!t.belowthefold(e,{threshold:0})},"right-of-screen":function(e){return t.rightoffold(e,{threshold:0})},"left-of-screen":function(e){return!t.rightoffold(e,{threshold:0})},"in-viewport":function(e){return t.inviewport(e,{threshold:0})},"above-the-fold":function(e){return!t.belowthefold(e,{threshold:0})},"right-of-fold":function(e){return t.rightoffold(e,{threshold:0})},"left-of-fold":function(e){return!t.rightoffold(e,{threshold:0})}})}(jQuery,window,document);

!(function($, window) {
    var $window = $(window);
    $.fn.lazyload2 = function(options) {
        var elements = this;
        var settings = {
            threshold       : 80,
            effectSpeed		: 700,
            effect          : 'fadeIn',
            dataAttribute  	: 'data-original',
            defaultImg      : 'images/default-img.png'
        };

        $.extend(settings, options || {});

        function update() {
            var counter = 0;
            var stop = $window.scrollTop();
            var winHeight = window.innerHeight ? window.innerHeight : $window.height();

            elements.each(function() {
            	var y = $(this).offset().top,
            		h = $(this).height();

            	if (stop >= y + h + settings.threshold) {
            		// 图片在可视区域的上面
            	} else if (stop > y - winHeight - settings.threshold) {
					counter = 0;
                    $(this).trigger('appear');
            	}
            });
        }

        elements.each(function() {
            var self = this;
            var $self = $(this);
            var original = $self.attr(settings.dataAttribute) || settings.defaultImg;
            self.loaded = false;
            $self.one('appear', function() {
                if (!self.loaded) {
		            self.loaded = true;
		            var img = new Image();
		            img.onload = function() {
                		$self.attr('src', original).hide()[settings.effect](settings.effectSpeed);
		            }
		            img.src = original;
		            var temp = $.grep(elements, function(element) {
                        return !element.loaded;
                    });
                    elements = $(temp);
                }
            })
        });

        $window.on('resize scroll', function() {
            update();
        })
        update();
    };

})(jQuery, window);

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
            $(this).addClass("curr").closest('dl').addClass('expand');
            return false; // break
        }
		if(urlBefore === hrefBefore){
			// $(this).closest('dl').addClass('expand');
		}
    }) 

	$side.find('.expand dd').css({'display':'block'});
	$side.on('click', 'dt', function() {
		$(this).next().slideToggle();
		$(this).parent().toggleClass('expand').siblings().removeClass('expand').find('dd').slideUp();
	});
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
	var $win 	  = $(window);
	var $gotop 	  = $('#jgotop');
	var threshold = $(window).height();

	if ($gotop.length === 0) {
		$gotop = $('<div class="gotop" id="jgotop"><a href="javascript:;"><b class="fa fa-chevron-up"></b><em>返回顶部</em></a></div>').appendTo($('body'));
	}
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
  		placeholder : 'images/loader.gif',
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
