// dropload
!function(a){"use strict";function g(a){a.touches||(a.touches=a.originalEvent.touches)}function h(a,b){b._startY=a.touches[0].pageY,b.touchScrollTop=b.$scrollArea.scrollTop()}function i(b,c){c._curY=b.touches[0].pageY,c._moveY=c._curY-c._startY,c._moveY>0?c.direction="down":c._moveY<0&&(c.direction="up");var d=Math.abs(c._moveY);""!=c.opts.loadUpFn&&c.touchScrollTop<=0&&"down"==c.direction&&!c.isLockUp&&(b.preventDefault(),c.$domUp=a("."+c.opts.domUp.domClass),c.upInsertDOM||(c.$element.prepend('<div class="'+c.opts.domUp.domClass+'"></div>'),c.upInsertDOM=!0),l(c.$domUp,0),d<=c.opts.distance?(c._offsetY=d,c.$domUp.html(c.opts.domUp.domRefresh)):d>c.opts.distance&&d<=2*c.opts.distance?(c._offsetY=c.opts.distance+.5*(d-c.opts.distance),c.$domUp.html(c.opts.domUp.domUpdate)):c._offsetY=c.opts.distance+.5*c.opts.distance+.2*(d-2*c.opts.distance),c.$domUp.css({height:c._offsetY}))}function j(b){var c=Math.abs(b._moveY);""!=b.opts.loadUpFn&&b.touchScrollTop<=0&&"down"==b.direction&&!b.isLockUp&&(l(b.$domUp,300),c>b.opts.distance?(b.$domUp.css({height:b.$domUp.children().height()}),b.$domUp.html(b.opts.domUp.domLoad),b.loading=!0,b.opts.loadUpFn(b)):b.$domUp.css({height:"0"}).on("webkitTransitionEnd transitionend",function(){b.upInsertDOM=!1,a(this).remove()}),b._moveY=0)}function k(a){a._scrollContentHeight=a.opts.scrollArea==b?e.height():a.$element[0].scrollHeight}function l(a,b){a.css({"-webkit-transition":"all "+b+"ms",transition:"all "+b+"ms"})}var f,b=window,c=document,d=a(b),e=a(c);a.fn.dropload=function(a){return new f(this,a)},f=function(b,c){var d=this;d.$element=a(b),d.upInsertDOM=!1,d.loading=!1,d.isLockUp=!1,d.isLockDown=!1,d.isData=!0,d._scrollTop=0,d.init(c)},f.prototype.init=function(f){function l(){k.direction="up",k.$domDown.html(k.opts.domDown.domLoad),k.loading=!0,k.opts.loadDownFn(k)}var k=this;k.opts=a.extend({},{scrollArea:k.$element,domUp:{domClass:"dropload-up",domRefresh:'<div class="dropload-refresh">↓下拉刷新</div>',domUpdate:'<div class="dropload-update">↑释放更新</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中</div>'},domDown:{domClass:"dropload-down",domRefresh:'<div class="dropload-refresh">↑上拉加载更多</div>',domLoad:'<div class="dropload-load"><span class="loading"></span>加载中</div>',domNoData:'<div class="dropload-noData">没有更多了 :)</div>'},distance:50,threshold:"",loadUpFn:"",loadDownFn:""},f),""!=k.opts.loadDownFn&&(k.$element.append('<div class="'+k.opts.domDown.domClass+'">'+k.opts.domDown.domRefresh+"</div>"),k.$domDown=a("."+k.opts.domDown.domClass)),k.opts.scrollArea==b?(k.$scrollArea=d,k._scrollContentHeight=e.height(),k._scrollWindowHeight=c.documentElement.clientHeight):(k.$scrollArea=k.opts.scrollArea,k._scrollContentHeight=k.$element[0].scrollHeight,k._scrollWindowHeight=k.$element.height()),k._scrollContentHeight<=k._scrollWindowHeight&&l(),d.on("resize",function(){k._scrollWindowHeight=k.opts.scrollArea==b?b.innerHeight:k.$element.height()}),k.$element.on("touchstart",function(a){k.loading||(g(a),h(a,k))}),k.$element.on("touchmove",function(a){k.loading||(g(a,k),i(a,k))}),k.$element.on("touchend",function(){k.loading||j(k)}),k.$scrollArea.on("scroll",function(){k._scrollTop=k.$scrollArea.scrollTop(),k._threshold=""===k.opts.threshold?Math.floor(1*k.$domDown.height()/3):k.opts.threshold,""!=k.opts.loadDownFn&&!k.loading&&!k.isLockDown&&k._scrollContentHeight-k._threshold<=k._scrollWindowHeight+k._scrollTop&&l()})},f.prototype.lock=function(a){var b=this;void 0===a?"up"==b.direction?b.isLockDown=!0:"down"==b.direction?b.isLockUp=!0:(b.isLockUp=!0,b.isLockDown=!0):"up"==a?b.isLockUp=!0:"down"==a&&(b.isLockDown=!0)},f.prototype.unlock=function(){var a=this;a.isLockUp=!1,a.isLockDown=!1},f.prototype.noData=function(){var a=this;a.isData=!1},f.prototype.resetload=function(){var b=this;"down"==b.direction&&b.upInsertDOM?b.$domUp.css({height:"0"}).on("webkitTransitionEnd mozTransitionEnd transitionend",function(){b.loading=!1,b.upInsertDOM=!1,a(this).remove(),k(b)}):"up"==b.direction&&(b.loading=!1,b.isData?(b.$domDown.html(b.opts.domDown.domRefresh),k(b)):b.$domDown.html(b.opts.domDown.domNoData))}}(window.Zepto||window.jQuery);

// swipeSlide
!function(a,b){"use strict";function h(a,b,c){b.css({"-webkit-transition":"all "+c+"s "+a.opts.transitionType,transition:"all "+c+"s "+a.opts.transitionType})}function i(a,b,c){var d=a.opts.axisX?c+"px,0,0":"0,"+c+"px,0";b.css({"-webkit-transform":"translate3d("+d+")",transform:"translate3d("+d+")"})}function j(a,c){var d=a.opts.ul.children(),e=d.eq(c).find("[data-src]");e&&e.each(function(){var c=b(this);c.is("img")?(c.attr("src",c.data("src")),c.removeAttr("data-src")):(c.css({"background-image":"url("+c.data("src")+")"}),c.removeAttr("data-src"))})}function k(a){e.touch&&!a.touches&&(a.touches=a.originalEvent.touches)}function l(a,b){b.isScrolling=void 0,b._moveDistance=b._moveDistanceIE=0,b._startX=e.touch?a.touches[0].pageX:a.pageX||a.clientX,b._startY=e.touch?a.touches[0].pageY:a.pageY||a.clientY}function m(a,b){b.opts.autoSwipe&&p(b),b.allowSlideClick=!1,b._curX=e.touch?a.touches[0].pageX:a.pageX||a.clientX,b._curY=e.touch?a.touches[0].pageY:a.pageY||a.clientY,b._moveX=b._curX-b._startX,b._moveY=b._curY-b._startY,"undefined"==typeof b.isScrolling&&(b.isScrolling=b.opts.axisX?!!(Math.abs(b._moveX)>=Math.abs(b._moveY)):!!(Math.abs(b._moveY)>=Math.abs(b._moveX))),b.isScrolling&&(a.preventDefault?a.preventDefault():a.returnValue=!1,h(b,b.opts.ul,0),b._moveDistance=b._moveDistanceIE=b.opts.axisX?b._moveX:b._moveY),b.opts.continuousScroll||(0==b._index&&b._moveDistance>0||b._index+1>=b._liLength&&b._moveDistance<0)&&(b._moveDistance=0),i(b,b.opts.ul,-(b._slideDistance*b._index-b._moveDistance))}function n(a){a.isScrolling||o(a),(c.ie10||c.ie11)&&(Math.abs(a._moveDistanceIE)<5&&(a.allowSlideClick=!0),setTimeout(function(){a.allowSlideClick=!0},100)),Math.abs(a._moveDistance)<=a._distance?q(a,"",".3"):a._moveDistance>a._distance?q(a,"prev",".3"):Math.abs(a._moveDistance)>a._distance&&q(a,"next",".3"),a._moveDistance=a._moveDistanceIE=0}function o(a){a.opts.autoSwipe&&(p(a),a.autoSlide=setInterval(function(){q(a,"next",".3")},a.opts.speed))}function p(a){clearInterval(a.autoSlide)}function q(a,b,c){"number"==typeof b?(a._index=b,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index),j(a,a._index+1),j(a,a._index+2)):(j(a,a._index-1),j(a,a._index),j(a,a._index+1)))):"next"==b?(a._index++,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index+1),a._index+1==a._liLength?j(a,1):a._index==a._liLength&&j(a,0)):j(a,a._index+1))):"prev"==b&&(a._index--,a.opts.lazyLoad&&(a.opts.continuousScroll?(j(a,a._index),0==a._index?j(a,a._liLength):a._index<0&&j(a,a._liLength-1)):j(a,a._index-1))),a.opts.continuousScroll?a._index>=a._liLength?(r(a,c),a._index=0,setTimeout(function(){r(a,0)},300)):a._index<0?(r(a,c),a._index=a._liLength-1,setTimeout(function(){r(a,0)},300)):r(a,c):(a._index>=a._liLength?a._index=0:a._index<0&&(a._index=a._liLength-1),r(a,c)),""!==arguments[1]&&a.opts.callback(a._index,a._liLength,a.$el)}function r(a,b){h(a,a.opts.ul,b),i(a,a.opts.ul,-a._index*a._slideDistance)}var f,g,c={ie10:a.navigator.msPointerEnabled,ie11:a.navigator.pointerEnabled},d=["touchstart","touchmove","touchend"],e={touch:a.Modernizr&&Modernizr.touch===!0||function(){return!!("ontouchstart"in a||a.DocumentTouch&&document instanceof DocumentTouch)}()};c.ie10&&(d=["MSPointerDown","MSPointerMove","MSPointerUp"]),c.ie11&&(d=["pointerdown","pointermove","pointerup"]),f={touchStart:d[0],touchMove:d[1],touchEnd:d[2]},b.fn.swipeSlide=function(a){var b=[];return this.each(function(c,d){b.push(new g(d,a))}),b},g=function(a,c){var d=this;d.$el=b(a),d._distance=50,d.allowSlideClick=!0,d.init(c)},g.prototype.init=function(d){function p(){var c,a=e.opts.ul.children();e._slideDistance=e.opts.axisX?e.opts.ul.width():e.opts.ul.height(),h(e,e.opts.ul,0),i(e,e.opts.ul,-e._slideDistance*e._index),h(e,a,0),c=e.opts.continuousScroll?-1:0,a.each(function(a){i(e,b(this),e._slideDistance*(a+c))})}var g,e=this;return e.opts=b.extend({},{ul:e.$el.children("ul"),li:e.$el.children().children("li"),index:0,continuousScroll:!0,autoSwipe:!1,speed:4e3,axisX:!0,transitionType:"ease",lazyLoad:!1,firstCallback:function(){},callback:function(){}},d),e._index=e.opts.index,e._liLength=e.opts.li.length,e.isScrolling,e.opts.firstCallback(e._index,e._liLength,e.$el),e._liLength<=1?(e.opts.lazyLoad&&j(e,0),!1):(e.opts.continuousScroll&&e.opts.ul.prepend(e.opts.li.last().clone()).append(e.opts.li.first().clone()),e.opts.lazyLoad&&(j(e,e._index),e.opts.continuousScroll?(j(e,e._index+1),j(e,e._index+2),0==e._index?j(e,e._liLength):e._index+1==e._liLength&&j(e,1)):0==e._index?j(e,e._index+1):e._index+1==e._liLength?j(e,e._index-1):(j(e,e._index+1),j(e,e._index-1))),p(),(c.ie10||c.ie11)&&(g="",g=e.opts.axisX?"pan-y":"none",e.$el.css({"-ms-touch-action":g,"touch-action":g}),e.$el.on("click",function(){return e.allowSlideClick})),o(e),e.$el.on(f.touchStart,function(a){k(a),l(a,e)}),e.$el.on(f.touchMove,function(a){k(a),m(a,e)}),e.$el.on(f.touchEnd,function(){n(e)}),e.opts.ul.on("webkitTransitionEnd MSTransitionEnd transitionend",function(){o(e)}),b(a).on("onorientationchange"in a?"orientationchange":"resize",function(){clearTimeout(e.timer),e.timer=setTimeout(p,150)}),void 0)},g.prototype.goTo=function(a){var b=this;q(b,a,".3")}}(window,window.Zepto||window.jQuery);

/*! fly - v1.0.0 - 2015-03-23
* https://github.com/amibug/fly
* Copyright (c) 2015 wuyuedong; Licensed MIT */
!function(t){function e(e,n){this.$element=t(e),this.settings=t.extend(!0,{},i,n),this.init()}var i={version:"1.0.0",autoPlay:!0,vertex_Rtop:20,speed:1.2,start:{},end:{},onEnd:t.noop};e.prototype={init:function(){this.setOptions(),this.play()},setOptions:function(){var e=this.settings,i=e.start,n=e.end;this.$element.css({marginTop:"0px",marginLeft:"0px",position:"fixed"}).appendTo("body"),null!=n.width&&null!=n.height&&t.extend(!0,i,{width:this.$element.width(),height:this.$element.height()});var o=Math.min(i.top,n.top)-Math.abs(i.left-n.left)/3;o<e.vertex_Rtop&&(o=Math.min(e.vertex_Rtop,Math.min(i.top,n.top)));var h=Math.sqrt(Math.pow(i.top-n.top,2)+Math.pow(i.left-n.left,2)),s=Math.ceil(Math.min(Math.max(Math.log(h)/.05-75,30),100)/e.speed),a=i.top==o?0:-Math.sqrt((n.top-o)/(i.top-o)),p=(a*i.left-n.left)/(a-1),l=n.left==p?0:(n.top-o)/Math.pow(n.left-p,2);t.extend(!0,e,{count:-1,steps:s,vertex_left:p,vertex_top:o,curvature:l})},play:function(){this.settings.autoPlay&&this.move()},move:function(){var e=this.settings,i=e.start,n=e.count,o=e.steps,h=e.end,s=i.left+(h.left-i.left)*n/o,a=0==e.curvature?i.top+(h.top-i.top)*n/o:e.curvature*Math.pow(s-e.vertex_left,2)+e.vertex_top;if(null!=h.width&&null!=h.height){var p=o/2,l=h.width-(h.width-i.width)*Math.cos(p>n?0:(n-p)/(o-p)*Math.PI/2),r=h.height-(h.height-i.height)*Math.cos(p>n?0:(n-p)/(o-p)*Math.PI/2);this.$element.css({width:l+"px",height:r+"px","font-size":Math.min(l,r)+"px"})}this.$element.css({left:s+"px",top:a+"px"}),e.count++;var f=window.requestAnimationFrame(t.proxy(this.move,this));n==o&&(window.cancelAnimationFrame(f),e.onEnd.apply(this))},destroy:function(){this.$element.remove()}},t.fn.fly=function(t){return this.each(function(){new e(this,t)})}}(window.Zepto||window.jQuery);

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

// 获取链接参数
function getQueryStringArgs() {
    var qs      = location.search.length > 0 ? location.search.substring(1) : "",
        args    = {},
        items   = qs.length ? qs.split("&") : [],
        item    = null,
        name    = null,
        value   = null,
        i       = items.length - 1;

    for (; i >= 0; i--) {
        item = items[i].split("=");
        name = decodeURIComponent(item[0]);
        value = decodeURIComponent(item[1]);
        if (name.length > 1) {
            args[name] = value;
        };
    };
    return args;
}

/** 
 * @description 看大图 
 * @param {Boolen}  微信 Webview 中调用微信的图片浏览器
 */
function gallery(weChatImagePreview) {
	function initGallery() {
		var html = [];
		html.push('<div class="gallery-box">');
		html.push('	<div class="gallery-num">');
		html.push('		<span class="num"></span>/<span class="sum"></span>');
		html.push('	</div>');
		html.push('	<div class="gallery-back">');
		html.push('		<i class="iconfont icon-back"></i>');
		html.push('	</div>');
		html.push('	<div class="gallery-close">');
		html.push('		<i class="iconfont icon-close"></i>');
		html.push('	</div>');
		html.push('</div>');
		$('body').append(html.join(''));
		bindEvent();
	}
	function bindEvent() {
		$('body').on('touchstart', '.gallery-close, .gallery-back, .gallery-ubtn', closeGallery);
		$('body').on('click', '.thumb img', function() {
            var index = $(this).index();
            var imgUrls = [];
            $(this).parent().find('img').each(function() {
                imgUrls.push($(this).data('src'));
            });
            if (weChatImagePreview && window.WeixinJSBridge) {
                window.WeixinJSBridge.invoke('imagePreview', {
                    current: imgUrls[index],
                    urls: imgUrls
                });
            } else {
                showBigPic(imgUrls, index);
            }
			return false;
		});

        var sX = 0;    // 手指初始x坐标
        var sY = 0;    // 手指初始y坐标
        var disX = 0;  // 滑动差值
        var disY = 0;  // 滑动差值

        $('.gallery-box').on('touchstart', function(e){
            e.preventDefault();
        })
        $('.gallery-box').on('touchstart', 'img', function(e){
            e.preventDefault();
        })
	}    
	function closeGallery(e) {
		$('.gallery').remove();
        $('.gallery-box').hide();
        $('body').removeClass('gallery-body');
        e.preventDefault();
	}
	function showBigPic(imgUrls, index) {
        var _result = '<div class="gallery"><ul>';
        $.each(imgUrls, function(i, src) {
            _result += '<li><img src="data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=" data-src="' + src + '"></li>';
        });
        _result += '</ul></div>';
        $('.gallery-box').show().prepend(_result);
        $('body').addClass('gallery-body');

        // swipeSlide
        $('.gallery').swipeSlide({
            index : index,
            continuousScroll : true,
            autoSwipe : false,
            lazyLoad : true,
            firstCallback : function(i,sum){
                $('.gallery-box .num').text(i+1);
                $('.gallery-box .sum').text(sum);
            },
            callback : function(i,sum){
                $('.gallery-box .num').text(i+1);
                $('.gallery-box .sum').text(sum);
            }
        });
	}

	initGallery();
} 


var _YYY = {
    CARTNAME: 'cartList', // 采购单存储key
    APPLYINFO: 'userInfo', // 申请寄样以及申请选货单提交的资料

    is_weixn: (function() {
        var ua = navigator.userAgent.toLowerCase();  
        if(ua.match(/MicroMessenger/i)=="micromessenger") {  
            return true;  
        } else {  
            return false;  
        }  
    })(),

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
            var t = new Date(e);
            return this.format(t);
        },
        shortDate: function(e) {
            var t = new Date(e);
            return t.getFullYear() + "-"
                + this.fillZero(t.getMonth() + 1) + "-"
                + this.fillZero(t.getDate());
        },
        elapsedTime: function(e) {
            var t = new Date(e),
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
    }
}

// 显示商品数量
function showCommodityCount() {
    var cartName =  _YYY.localstorage.get(_YYY.CARTNAME);
    var arr = cartName === null ? [] : eval(cartName);
    var count = 0;
    $.each(arr, function(i, item) {
        count += parseInt(item.num, 10);
    })
    $('#cartNum').html(count === 0 ? '' : count);
}

// 商品数量
function pickCommodity(id, num){
    var cartName = _YYY.localstorage.get(_YYY.CARTNAME);

    if (isNaN(num)) {
        return;
    }

    if(cartName === null){
        //第一次添加,建立json结构。
         _YYY.localstorage.set(_YYY.CARTNAME,'[{commodityId:' + id + ', num:' + num + '}]');
    }else{
        var arr = eval(cartName),
            isNewCommodity = true; //判断是否已经追加

        //遍历所有对象。如果id相同，让该商品数量递增;
        $.each(arr, function(i, item) {
            if (item.commodityId == id) {
                item.num = parseInt(item.num, 10) + parseInt(num, 10);
                isNewCommodity = false;
                return false; // 跳出循环
            }
        })
        if (isNewCommodity) {
            arr.push({commodityId:id, num:num});
        }
        _YYY.localstorage.set(_YYY.CARTNAME, JSON.stringify(arr));
    }
    showCommodityCount();
}
// 更新商品数量
function updateCommodity(id, num) {
    var cartName =  _YYY.localstorage.get(_YYY.CARTNAME);
    var arr = cartName === null ? [] : eval(cartName);
    $.each(arr, function(i, item) {
        if (item.commodityId == id) {
            item.num = num;
            _YYY.localstorage.set(_YYY.CARTNAME, JSON.stringify(arr));
            return false; // 跳出循环
        }
    })
}

// 删除商品
function deleteCommodity(id) {
    var cartName =  _YYY.localstorage.get(_YYY.CARTNAME);
    var arr = cartName === null ? [] : eval(cartName);
    $.each(arr, function(i, item) {
        if (item.commodityId == id) {
            arr.splice(i, 1);
            _YYY.localstorage.set(_YYY.CARTNAME, JSON.stringify(arr));
            return false; // 跳出循环
        }
    })
    // showCommodityCount();
}

function getAppyInfo(){
    var userinfo = _YYY.localstorage.get(_YYY.APPLYINFO);
    if(userinfo !== null) {
        return eval('(' + userinfo + ')');
    } else {
        return null;
    }
}

function saveAppyinfo(info){
    var userinfo = _YYY.localstorage.get(_YYY.APPLYINFO);
    var arr = userinfo === null ? [] : eval('(' + userinfo + ')');

    if (arr.length !== 0 && arr.region && !info.region) {
        info.region = arr.region;
    }
    _YYY.localstorage.set(_YYY.APPLYINFO, JSON.stringify(info));
}
function deleteInfo() {
    _YYY.localstorage.remove(_YYY.APPLYINFO);
}

// 导航高亮
function navigationActive(){
    var $nav = $('#foot-nav'),
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
    navigationActive();
    showCommodityCount();
})