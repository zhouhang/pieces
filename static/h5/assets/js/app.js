// swipeSlide
!function(a,b){"use strict";function h(a,b,c){b.css({"-webkit-transition":"all "+c+"s "+a.opts.transitionType,transition:"all "+c+"s "+a.opts.transitionType})}function i(a,b,c){var d=a.opts.axisX?c+"px,0,0":"0,"+c+"px,0";b.css({"-webkit-transform":"translate3d("+d+")",transform:"translate3d("+d+")"})}function j(a,c){var d=a.opts.ul.children(),e=d.eq(c).find("[data-src]");e&&e.each(function(){var c=b(this);c.is("img")?(c.attr("src",c.data("src")),c.removeAttr("data-src")):(c.css({"background-image":"url("+c.data("src")+")"}),c.removeAttr("data-src"))})}function k(a){e.touch&&!a.touches&&(a.touches=a.originalEvent.touches)}function l(a,b){b.isScrolling=void 0,b._moveDistance=b._moveDistanceIE=0,b._startX=e.touch?a.touches[0].pageX:a.pageX||a.clientX,b._startY=e.touch?a.touches[0].pageY:a.pageY||a.clientY}function m(a,b){b.opts.autoSwipe&&p(b),b.allowSlideClick=!1,b._curX=e.touch?a.touches[0].pageX:a.pageX||a.clientX,b._curY=e.touch?a.touches[0].pageY:a.pageY||a.clientY,b._moveX=b._curX-b._startX,b._moveY=b._curY-b._startY,"undefined"==typeof b.isScrolling&&(b.isScrolling=b.opts.axisX?!!(Math.abs(b._moveX)>=Math.abs(b._moveY)):!!(Math.abs(b._moveY)>=Math.abs(b._moveX))),b.isScrolling&&(a.preventDefault?a.preventDefault():a.returnValue=!1,h(b,b.opts.ul,0),b._moveDistance=b._moveDistanceIE=b.opts.axisX?b._moveX:b._moveY),b.opts.continuousScroll||(0==b._index&&b._moveDistance>0||b._index+1>=b._liLength&&b._moveDistance<0)&&(b._moveDistance=0),i(b,b.opts.ul,-(b._slideDistance*b._index-b._moveDistance))}function n(a){a.isScrolling||o(a),(c.ie10||c.ie11)&&(Math.abs(a._moveDistanceIE)<5&&(a.allowSlideClick=!0),setTimeout(function(){a.allowSlideClick=!0},100)),Math.abs(a._moveDistance)<=a._distance?q(a,"",".3"):a._moveDistance>a._distance?q(a,"prev",".3"):Math.abs(a._moveDistance)>a._distance&&q(a,"next",".3"),a._moveDistance=a._moveDistanceIE=0}function o(a){a.opts.autoSwipe&&(p(a),a.autoSlide=setInterval(function(){q(a,"next",".3")},a.opts.speed))}function p(a){clearInterval(a.autoSlide)}function q(a,b,c){"number"==typeof b?(a._index=b,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index),j(a,a._index+1),j(a,a._index+2)):(j(a,a._index-1),j(a,a._index),j(a,a._index+1)))):"next"==b?(a._index++,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index+1),a._index+1==a._liLength?j(a,1):a._index==a._liLength&&j(a,0)):j(a,a._index+1))):"prev"==b&&(a._index--,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index),0==a._index?j(a,a._liLength):a._index<0&&j(a,a._liLength-1)):j(a,a._index-1))),a.opts.continuousScroll?a._index>=a._liLength?(r(a,c),a._index=0,setTimeout(function(){r(a,0)},300)):a._index<0?(r(a,c),a._index=a._liLength-1,setTimeout(function(){r(a,0)},300)):r(a,c):(a._index>=a._liLength?a._index=0:a._index<0&&(a._index=a._liLength-1),r(a,c)),""!==arguments[1]&&a.opts.callback(a._index,a._liLength,a.$el)}function r(a,b){h(a,a.opts.ul,b),i(a,a.opts.ul,-a._index*a._slideDistance)}var f,g,c={ie10:a.navigator.msPointerEnabled,ie11:a.navigator.pointerEnabled},d=["touchstart","touchmove","touchend"],e={touch:a.Modernizr&&Modernizr.touch===!0||function(){return!!("ontouchstart"in a||a.DocumentTouch&&document instanceof DocumentTouch)}()};c.ie10&&(d=["MSPointerDown","MSPointerMove","MSPointerUp"]),c.ie11&&(d=["pointerdown","pointermove","pointerup"]),f={touchStart:d[0],touchMove:d[1],touchEnd:d[2]},b.fn.swipeSlide=function(a){var b=[];return this.each(function(c,d){b.push(new g(d,a))}),b},g=function(a,c){var d=this;d.$el=b(a),d._distance=50,d.allowSlideClick=!0,d.init(c)},g.prototype.init=function(d){function p(){var c,a=e.opts.ul.children();e._slideDistance=e.opts.axisX?e.opts.ul.width():e.opts.ul.height(),h(e,e.opts.ul,0),i(e,e.opts.ul,-e._slideDistance*e._index),h(e,a,0),c=e.opts.continuousScroll?-1:0,a.each(function(a){i(e,b(this),e._slideDistance*(a+c))})}var g,e=this;return e.opts=b.extend({},{ul:e.$el.children("ul"),li:e.$el.children().children("li"),index:0,continuousScroll:!0,autoSwipe:!1,speed:4e3,axisX:!0,transitionType:"ease",lazyLoad:!1,firstCallback:function(){},callback:function(){}},d),e._index=e.opts.index,e._liLength=e.opts.li.length,e.isScrolling,e.opts.firstCallback(e._index,e._liLength,e.$el),e._liLength<=1?(e.opts.lazyLoad&&j(e,0),!1):(e.opts.continuousScroll&&e.opts.ul.prepend(e.opts.li.last().clone()).append(e.opts.li.first().clone()),e.opts.lazyLoad&&(j(e,e._index),e.opts.continuousScroll?(j(e,e._index+1),j(e,e._index+2),0==e._index?j(e,e._liLength):e._index+1==e._liLength&&j(e,1)):0==e._index?j(e,e._index+1):e._index+1==e._liLength?j(e,e._index-1):(j(e,e._index+1),j(e,e._index-1))),p(),(c.ie10||c.ie11)&&(g="",g=e.opts.axisX?"pan-y":"none",e.$el.css({"-ms-touch-action":g,"touch-action":g}),e.$el.on("click",function(){return e.allowSlideClick})),o(e),e.$el.on(f.touchStart,function(a){k(a),l(a,e)}),e.$el.on(f.touchMove,function(a){k(a),m(a,e)}),e.$el.on(f.touchEnd,function(){n(e)}),e.opts.ul.on("webkitTransitionEnd MSTransitionEnd transitionend",function(){o(e)}),b(a).on("onorientationchange"in a?"orientationchange":"resize",function(){clearTimeout(e.timer),e.timer=setTimeout(p,150)}),void 0)},g.prototype.goTo=function(a){var b=this;q(b,a,".3")}}(window,window.Zepto||window.jQuery);


// 气泡式弹出层
function popover(msg, delay) {
	var $ele = $('#popover');
	if (!msg) {
		return;
	}
	delay = delay || 2e3;
	if ($ele.length === 0) {
		$ele = $('<div />').attr({'id': 'popover', 'class': 'ui-popover'});
		$ele.appendTo($('body'));
	}

	$ele.html('<span class="txt">' + msg + '</span>').addClass('ui-popover-active');
	this.timer && clearTimeout(this.timer);
	this.timer = setTimeout(function() {
		$ele.removeClass('ui-popover-active');
	}, delay);
	setTimeout(function() {
		$ele.remove();
	}, 350 + delay);
}

function gallery(weChatImagePreview) {
    var $body = $('body'),
        $gallery,
        selector = '.thumb',
        skin = 'gallery',
        weChatImagePreview = weChatImagePreview && window.WeixinJSBridge;

    var lockscroll = function(islock) {
        if (islock) {
            $body.addClass('noscroll');
        } else {
            $body.removeClass('noscroll');
        }
    }

    var initGallery = function() {
        var html = [];
        html.push('<div class="', skin ,'">');
        html.push(' <div class="page"></div>');
        html.push(' <div class="close"><</div>');
        html.push('</div>');
        $('body').append(html.join(''));
        $gallery = $('.' + skin);
    }
    var bindEvent = function() {
        $(selector).on('click', 'img', function() {
            var pic = [],
                src = this.src,
                idx = 0;

            $(this).closest(selector).find('img').each(function(i) {
                pic.push($(this).data('src')) || this.src;
                idx = this.src === src ? i : 0;
            }); 

            if (weChatImagePreview) {
                window.WeixinJSBridge.invoke('imagePreview', {
                    current: pic[idx],
                    urls: pic
                });
            } else {
                showBigPic(pic, idx);
            }
            return false;
        });

        // 关闭按钮
        if (weChatImagePreview) {
            return;
        }
       !weChatImagePreview && $gallery.on('click', '.close', function() {
            $gallery.hide().find('.main').remove();
            lockscroll(false);
       });
    }

    var showBigPic = function(imgUrls, index) {
        var model = [];
        lockscroll(true);

        model.push('<div class="main"><ul>');
        $.each(imgUrls, function(i, src) {
            model.push('<li><img src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" data-src="' + src + '"></li>');
        });
        model.push('</ul></div>');
        $gallery.show().prepend(model.join(''))
        .find('.main').swipeSlide({
            index : index,
            continuousScroll : true,
            autoSwipe : false,
            lazyLoad : true,
            firstCallback : function(i,sum){
                updateIdx(i+1, sum)
            },
            callback : function(i,sum){
                updateIdx(i+1, sum)
            }
        });
    }

    var updateIdx = function(idx, size) {
        $gallery.find('.page').html(idx + '/' + size);
    }

    !weChatImagePreview && initGallery();
    bindEvent();
} 


var _YYY = {
    CARTNAME: 'cartList', // 采购单存储key
    APPLYINFO: 'userInfo', // 申请寄样以及申请选货单提交的资料
    QUOTETIME:'quoteTime',//最近查看quote时间

    is_weixn: /MicroMessenger/.test(navigator.userAgent),

    timeago: {
        fillZero: function(number) {
            return number < 10 ? '0' + number : number;
        },
        format: function(t) {
            return t.getFullYear() + "-"
                + this.fillZero(t.getMonth() + 1) + "-"
                + this.fillZero(t.getDate()) + " " 
                + this.fillZero(t.getHours()) + ':' 
                + this.fillZero(t.getMinutes()); 
        },
        fullTime: function(e) {
            var t = new Date(e.replace(/-/g, '/'));
            return this.format(t);
        },
        shortDate: function(e) {
            var t = new Date(e.replace(/-/g, '/'));
            return t.getFullYear() + "-"
                + this.fillZero(t.getMonth() + 1) + "-"
                + this.fillZero(t.getDate());
        },
        elapsedTime: function(e) {
            var t = new Date(e.replace(/-/g, '/')),
                s = new Date,
                a = (s - t) / 1e3;
            return 10 > a ? "刚刚" : 60 > a ? Math.round(a) + "秒前" : 3600 > a ? Math.round(a / 60) + "分钟前" : 86400 > a ? Math.round(a / 3600) + "小时前" : this.format(t)
        }
    },

    localstorage: {
        support: !!window.localStorage,
        get: function(key) {
            if (this.support && key) {
                return window.localStorage.getItem(key);
            } else {
                return null;
            }
        },
        set: function(key, val) {
            var self = this;
            if (this.support && key) {
                try {
                    window.localStorage.setItem(key, val);
                } catch (t) {
                    self.removeAll();
                    window.localStorage.setItem(key, val);
                }
            }
        },
        remove: function(key) {
            if (this.support && key) {
                window.localStorage.removeItem(key);
            }
        },
        removeAll: function() {
            if (this.support) {
                window.localStorage.clear();
            }
        }
    },

    verify: {
        isMobile: function(e) {
            return e && /^1[345678]\d{9}$/.test(e);
        },
        isEmpty: function(e) {
            return !e.length;
        }
    },

    share: {
        init: function(select) {
            this.insertHtml();
            $model = $('#jwxShare');
            $(select).on('click', function() {
                $model.show();
            })
            $model.on('click', function() {
                $model.hide();
            })
        },
        insertHtml: function() {
            var model = [];
            model.push('<div class="dialog-mask" id="jwxShare">');
            if (_YYY.is_weixn) {
                model.push('    <h2 class="hd">点击这里</h2>');
                model.push('    <p>然后点击 <em>【发送给朋友】</em> 或 <em>【分享到朋友圈】</em></p>');
                model.push('    <div class="guide"></div>');
            } else {
                model.push('    <p>找到浏览器的<em>分享按钮</em><br>然后点击 <em>【发送给朋友】</em> 或 <em>【分享到朋友圈】</em></p>');
            }
            model.push('</div>');
            $('body').append(model.join(''));
        }
    },

    getParams: function() {
        var ret = {},
            params = window.location.search.replace(/^\?/,'').split('&'),
            len = params.length, i = 0, s;

        for (;i<len;i++) {
            if (!params[i]) { 
                continue; 
            }
            s = params[i].split('=');
            ret[s[0]] = s[1];
        }
        return ret;
    }
}


// 导航高亮
function navigationActive(){
    var $nav = $('.ui-footer'),
        URL = document.URL.split('#')[0].split('?')[0].toLowerCase();

    $nav.find('a').each(function() {
        var url = this.href.toLowerCase();
        if (URL === url) {
            $(this).addClass('current');
            return false; // break
        }
    })

    if (_YYY.is_weixn) {
        $('#center').attr('href', $('#center').attr('href') + "?source=WECHAT");
    }
}

$(function() {
    // navigationActive();
})