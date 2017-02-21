/*! Lazyload  */
!function(t,e,n){function i(e){this.settings=t.extend({},s,e),this.imgs=t(e.selector),this.container=t(this.settings.container),this.init()}var o=t(e),r=t(n),s={selector:"img",threshold:50,failure_limit:0,speed:300,event:"scroll",effect:"fadeIn",attr:"original",container:e,nopic:"images/blank.png",defaultImg:"images/default-img.png",placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};i.prototype={init:function(){this.process(),this.bindEvent()},bindEvent:function(){var t=this;r.ready(function(){t.update()}),t.container.on(t.settings.event,function(){return t.update()}),o.on("resize",function(){t.update()})},update:function(){var e=this,n=0,i={container:e.settings.container,threshold:e.settings.threshold};e.imgs.each(function(){var o=t(this);if(t.abovethetop(this,i)||t.leftofbegin(this,i));else if(t.belowthefold(this,i)||t.rightoffold(this,i)){if(++n>e.settings.failure_limit)return!1}else o.trigger("appear"),n=0})},process:function(){var e=this;e.imgs.one("appear",function(){var n=t(this),i=n.data(e.settings.attr);if(!n.data("loaded"))if(""==i)n.addClass("def").attr("src",e.settings.nopic).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n);else{var o=new Image;o.onload=function(){o.onload=null,n.attr("src",i).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n)},o.onerror=function(){o.onerror=null,n.addClass("def").attr("src",e.settings.defaultImg).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n)},o.src=i}})},grep:function(){var e=this,n=t.grep(e.imgs,function(e){return!t(e).data("loaded")});e.imgs=t(n)}},t.belowthefold=function(n,i){var r;return r=void 0===i.container||i.container===e?(e.innerHeight?e.innerHeight:o.height())+o.scrollTop():t(i.container).offset().top+t(i.container).height(),r<=t(n).offset().top-i.threshold},t.rightoffold=function(n,i){var r;return r=void 0===i.container||i.container===e?o.width()+o.scrollLeft():t(i.container).offset().left+t(i.container).width(),r<=t(n).offset().left-i.threshold},t.abovethetop=function(n,i){var r;return r=void 0===i.container||i.container===e?o.scrollTop():t(i.container).offset().top,r>=t(n).offset().top+i.threshold+t(n).height()},t.leftofbegin=function(n,i){var r;return r=void 0===i.container||i.container===e?o.scrollLeft():t(i.container).offset().left,r>=t(n).offset().left+i.threshold+t(n).width()},e.lazyload=function(t){new i(t)}}(jQuery,window,document);

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
		var settings = $.extend({}, defaults, options || {});
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
		typeof settings.call === 'function' && settings.call();
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
	var $searchForm = $('#_search_form'),$searchForm2 = $('#_search_form2'),
		$fixed = $('.search-fixed');

	$('#_search_ipt').autocomplete({
        serviceUrl: '/commodity/search/auto',
        paramName: 'keyword',
        groupBy: 'category',
        transformResult: function(response) {
            response = JSON.parse(response);
            return  {suggestions:$.map(response, function(dataItem) {
            	return {
            		value: (dataItem.category ? dataItem.category + '：' : '') + dataItem.value,
            		data: {'category': dataItem.category}
            	}
            })};
        },
        onSelect: function (suggestion) {
            $searchForm.submit();
        }
    });
	if ($searchForm2) {
		$('#_search_ipt2').autocomplete({
			serviceUrl: '/commodity/search/auto',
			paramName: 'keyword',
			groupBy: 'category',
			transformResult: function (response) {
				response = JSON.parse(response);
				return {
					suggestions: $.map(response, function (dataItem) {
						return {
							value: (dataItem.category ? dataItem.category + '：' : '') + dataItem.value,
							data: {'category': dataItem.category}
						}
					})
				};
			},
			onSelect: function (suggestion) {
				$searchForm2.submit();
			}
		});
	}

	var searchBarFixed = function() {
		if ($(window).scrollTop() > 129) {
			$fixed.addClass('search-animated');
		} else {
			$fixed.removeClass('search-animated');
		}
	}


	$(window).on('scroll', function() {
		searchBarFixed();
	})

	searchBarFixed();
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


var cookieFn = {
	set: function(key, val, day) {
		var str = key + "=" + escape(val);
		if(day > 0){
            var date = new Date();
            var ms = day * 24 * 3600 * 1e3;
            date.setTime(date.getTime() + ms);
            str += "; expires=" + date.toGMTString();
        }
		str += '; path=/';
		// str += '; path=/; domain=lppz.com';
		document.cookie = str;
	},
	get: function(key) {
		var arrStr = document.cookie.split("; ");
		for (var i = 0; i < arrStr.length; i++) {
			var temp = arrStr[i].split("=");
			if (temp[0] == key) return unescape(temp[1]);
		}
	},
	delete: function(key) {
		var date = new Date();
		date.setTime(date.getTime() - 1e4);
		document.cookie = key + "=v; expires =" + date.toGMTString();
	}
}

// 购物车
var shopcart = {
	init: function() {
		this.count = 0;
		this.initCart();
		this.bindEvent();
	},
	initCart: function() {
		var that = this,
			cart = this.getCart(),
			$header = $('.header');

		$.ajax({
			url: '',
			data: {id: cart.split('@').join()},
			beforeSend: function() {
				$header.find('.cart .bd').html('<div class="arrow"></div><div class="loading"></div>');
			},
			success: function(res) {
				res = {
					"list": [{
						"id": "1944",
						"name": "壁虎（天龙）",
						"norms": "净",
						"url": "/commodity/1944"
					},{
						"id": "1939",
						"name": "白芍",
						"norms": "直径1.6-1.8cm，厚度2-3mm,16-18号筛",
						"url": "/commodity/1939"
					},{
						"id": "1941",
						"name": "白芍",
						"norms": "圆片、厚2-3mm、直径0.6cm-1.8cm以上、 无空心片、异形片、黑片 6-18号筛",
						"url": "/commodity/1941"
					},{
						"id": "1922",
						"name": "紫菀",
						"norms": "厚片4-6mm、 1号筛",
						"url": "/commodity/1922"
					},{
						"id": "1904",
						"name": "煅紫石英",
						"norms": "2",
						"url": "/commodity/1904"
					},{
						"id": "42",
						"name": "安息香",
						"norms": "小块、成团块、表面橙黄色、具蜡样光泽",
						"url": "/commodity/42"
					},{
						"id": "37",
						"name": "艾叶",
						"norms": "除去杂质，长梗，2号筛",
						"url": "/commodity/37"
					}]
				};
				that.toHtml(res.list);
			}
		})
	},
	toHtml: function(data) {
		var that = this,
			model = [];
			
		that.count = data.length;

		if (that.count > 0) {
			model.push('<div class="arrow"></div><div class="tb">');
			model.push('<ul>');
			$.each(data, function(i, item) {
				model.push('<li>');
				model.push('<a href="' , item.url ,'" class="name">', item.name , '</a>');
				model.push('<span class="norms">', item.norms, '</span>');
				model.push('<a href="javascript:;" data-id="', item.id, '" class="fa fa-times"></a>');
				model.push('</li>');
			})
			model.push('</ul></div>');
			model.push('<div class="tf">');
			model.push('<a href="#" class="btn btn-red">查看询价单</a>');
			model.push('共 <em class="count">', that.count, '</em> 件商品 ');
			model.push('</div>');
			$('.header').find('.cart .bd').html(model.join(''));
			that.calcCount(0);
		} else {
			that.empty();	// 购物车为空
		}
	},
	bindEvent: function() {
		var that = this,
			$header = $('.header');

		// 开关
		$header.find('.cart').on('mouseenter', function() {
			$(this).addClass('cart-hover');
		}).on('mouseleave', function() {
			$(this).removeClass('cart-hover');
		})

		// 删除购物车商品
		$header.on('click', '.fa-times', function() {
			var $li = $(this).parent(),
				$ul = $li.parent(),
				id = $(this).data('id');

			layer.confirm('您确定要将该商品从购物清单中删除吗？', {icon: 3, title:'提示'}, function(index){
				$li.remove();
				$header.find('.cart ul').html($ul.html());
				that.delCart(id);
	            layer.close(index);
	        });  
		})

		// 加入询价单
		$('.fa-pro-list').on('click', '.btn-white', function() {
			var cart = that.getCart(),
				data = ($(this).data('s') || '').split('|'), // data-s = "id|name|norms"
				id = data[0],
				count = 1,
				model = [];

			if (cart == '') {
				cart = id;
				model = [{
					"id": id,
					"name": data[1],
					"norms": data[2],
					"url": "/commodity/" + id
				}]
				that.toHtml(model);
			} else {
				cart = cart.replace('@' + id, '').replace(id + '@', '');
				cart = id + '@' + cart;

				model.push('<li>');
				model.push('<a href="/commodity/' , id ,'" class="name">', data[1] , '</a>');
				model.push('<span class="norms">', data[2], '</span>');
				model.push('<a href="javascript:;" data-id="', id, '" class="fa fa-times"></a>');
				model.push('</li>');

				$header.find('.cart ul').prepend(model.join(''));
				// count = cart.split('@').length - that.count; // 重复添加时，数量不变
			}
			that.saveCart(cart);
			that.calcCount(count);
			$(this).removeClass('.btn-white').addClass('btn-gray').prop('disabled', true).html('已加入询价单');
			return false;
			
		}).find('.btn-white').each(function() {
			var cart = that.getCart(),
				data = ($(this).data('s') || '').split('|'),
				id = data[0];
			if (cart.indexOf('@' + id) >= 0 || cart.indexOf(id + '@') >= 0) {
				$(this).removeClass('.btn-white').addClass('btn-gray').prop('disabled', true).html('已加入询价单');
			} else {
				$(this).prop('disabled', false).html('加入询价单');
			}
		})

		// 已加入
		$('.fa-pro-list').on('click', '.btn-gray', function() {
			return false;
		})
	},
	getCart: function() {
		return cookieFn.get('cart') || '';
	},
	saveCart: function(val) {
		cookieFn.set('cart', val, 30); // 保存30天
	},
	delCart: function(id) {
		var cart = this.getCart();
		if (this.count < 2) {
			this.saveCart('');
			this.empty();
		} else {
			cart = cart.replace('@' + id, '').replace(id + '@', '');
			this.saveCart(cart);
		}
		this.calcCount(-1);
	},
	calcCount: function(num) {
		var $count = $('.header').find('.cart .count'),
			anmi = '';

		if (num < 0) {
			anmi = '<i>-' + num + '</i>';
		} else if (num > 0) {
			anmi = '<i>+' + num + '</i>';
		} else {
			anmi = '';
		}

		this.count += num;
		$count.html(this.count + anmi);
		$count.find('i').animate({top: '-30px', 'opacity': 0}, 1e3);
	},
	empty: function() {
		$('.header').find('.cart .bd').html('<div class="arrow"></div><div class="empty">询价单中还没有商品，立即挑选吧！</div>');
	}
}

function pageInit() {
	// 开启图片懒加载
    lazyload({
		placeholder : 'images/blank.gif',
		selector: '.lazyload'
    });

    // gotop
    gotop();
	// search
	bindSearch();
	// 用户中心导航高亮
	currNav();
	// 商品分类
	cat();
	// 购物车
	shopcart.init();
}

$(function() {
	pageInit();
})
