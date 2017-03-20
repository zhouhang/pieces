/*! Lazyload  */
!function(t,e,n){function i(e){this.settings=t.extend({},s,e),this.imgs=t(e.selector),this.container=t(this.settings.container),this.init()}var o=t(e),r=t(n),s={selector:"img",threshold:50,failure_limit:0,speed:300,event:"scroll",effect:"fadeIn",attr:"original",container:e,nopic:"images/blank.png",defaultImg:"images/default-img.png",placeholder:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC"};i.prototype={init:function(){this.process(),this.bindEvent()},bindEvent:function(){var t=this;r.ready(function(){t.update()}),t.container.on(t.settings.event,function(){return t.update()}),o.on("resize",function(){t.update()})},update:function(){var e=this,n=0,i={container:e.settings.container,threshold:e.settings.threshold};e.imgs.each(function(){var o=t(this);if(t.abovethetop(this,i)||t.leftofbegin(this,i));else if(t.belowthefold(this,i)||t.rightoffold(this,i)){if(++n>e.settings.failure_limit)return!1}else o.trigger("appear"),n=0})},process:function(){var e=this;e.imgs.one("appear",function(){var n=t(this),i=n.data(e.settings.attr);if(!n.data("loaded"))if(""==i)n.addClass("def").attr("src",e.settings.nopic).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n);else{var o=new Image;o.onload=function(){o.onload=null,n.attr("src",i).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n)},o.onerror=function(){o.onerror=null,n.addClass("def").attr("src",e.settings.defaultImg).hide()[e.settings.effect](e.settings.speed),n.data("loaded",!0),e.grep(n)},o.src=i}})},grep:function(){var e=this,n=t.grep(e.imgs,function(e){return!t(e).data("loaded")});e.imgs=t(n)}},t.belowthefold=function(n,i){var r;return r=void 0===i.container||i.container===e?(e.innerHeight?e.innerHeight:o.height())+o.scrollTop():t(i.container).offset().top+t(i.container).height(),r<=t(n).offset().top-i.threshold},t.rightoffold=function(n,i){var r;return r=void 0===i.container||i.container===e?o.width()+o.scrollLeft():t(i.container).offset().left+t(i.container).width(),r<=t(n).offset().left-i.threshold},t.abovethetop=function(n,i){var r;return r=void 0===i.container||i.container===e?o.scrollTop():t(i.container).offset().top,r>=t(n).offset().top+i.threshold+t(n).height()},t.leftofbegin=function(n,i){var r;return r=void 0===i.container||i.container===e?o.scrollLeft():t(i.container).offset().left,r>=t(n).offset().left+i.threshold+t(n).width()},e.lazyload=function(t){new i(t)}}(jQuery,window,document);

/* Autocomplete */
!function(a){"use strict";"function"==typeof define&&define.amd?define(["jquery"],a):a("object"==typeof exports&&"function"==typeof require?require("jquery"):jQuery)}(function(a){"use strict";function b(c,d){var e=function(){},f=this,g={ajaxSettings:{},autoSelectFirst:!1,appendTo:document.body,serviceUrl:null,lookup:null,onSelect:null,width:"auto",minChars:1,maxHeight:300,deferRequestBy:0,params:{},formatResult:b.formatResult,delimiter:null,zIndex:9999,type:"GET",noCache:!1,onSearchStart:e,onSearchComplete:e,onSearchError:e,preserveInput:!1,containerClass:"autocomplete-suggestions",tabDisabled:!1,dataType:"text",currentRequest:null,triggerSelectOnValidInput:!0,preventBadQueries:!0,lookupFilter:function(a,b,c){return-1!==a.value.toLowerCase().indexOf(c)},paramName:"query",transformResult:function(b){return"string"==typeof b?a.parseJSON(b):b},showNoSuggestionNotice:!1,noSuggestionNotice:"No results",orientation:"bottom",forceFixPosition:!1};f.element=c,f.el=a(c),f.suggestions=[],f.badQueries=[],f.selectedIndex=-1,f.currentValue=f.element.value,f.intervalId=0,f.cachedResponse={},f.onChangeInterval=null,f.onChange=null,f.isLocal=!1,f.suggestionsContainer=null,f.noSuggestionsContainer=null,f.options=a.extend({},g,d),f.classes={selected:"autocomplete-selected",suggestion:"autocomplete-suggestion"},f.hint=null,f.hintValue="",f.selection=null,f.initialize(),f.setOptions(d)}var c=function(){return{escapeRegExChars:function(a){return a.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g,"\\$&")},createNode:function(a){var b=document.createElement("div");return b.className=a,b.style.position="absolute",b.style.display="none",b}}}(),d={ESC:27,TAB:9,RETURN:13,LEFT:37,UP:38,RIGHT:39,DOWN:40};b.utils=c,a.Autocomplete=b,b.formatResult=function(a,b){if(!b)return a.value;var d="("+c.escapeRegExChars(b)+")";return a.value.replace(new RegExp(d,"gi"),"<strong>$1</strong>").replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/"/g,"&quot;").replace(/&lt;(\/?strong)&gt;/g,"<$1>")},b.prototype={killerFn:null,initialize:function(){var c,d=this,e="."+d.classes.suggestion,f=d.classes.selected,g=d.options;d.element.setAttribute("autocomplete","off"),d.killerFn=function(b){0===a(b.target).closest("."+d.options.containerClass).length&&(d.killSuggestions(),d.disableKillerFn())},d.noSuggestionsContainer=a('<div class="autocomplete-no-suggestion"></div>').html(this.options.noSuggestionNotice).get(0),d.suggestionsContainer=b.utils.createNode(g.containerClass),c=a(d.suggestionsContainer),c.appendTo(g.appendTo),"auto"!==g.width&&c.width(g.width),c.on("mouseover.autocomplete",e,function(){d.activate(a(this).data("index"))}),c.on("mouseout.autocomplete",function(){d.selectedIndex=-1,c.children("."+f).removeClass(f)}),c.on("click.autocomplete",e,function(){return d.select(a(this).data("index")),!1}),d.fixPositionCapture=function(){d.visible&&d.fixPosition()},a(window).on("resize.autocomplete",d.fixPositionCapture),d.el.on("keydown.autocomplete",function(a){d.onKeyPress(a)}),d.el.on("keyup.autocomplete",function(a){d.onKeyUp(a)}),d.el.on("blur.autocomplete",function(){d.onBlur()}),d.el.on("focus.autocomplete",function(){d.onFocus()}),d.el.on("change.autocomplete",function(a){d.onKeyUp(a)}),d.el.on("input.autocomplete",function(a){d.onKeyUp(a)})},onFocus:function(){var a=this;a.fixPosition(),a.el.val().length>=a.options.minChars&&a.onValueChange()},onBlur:function(){this.enableKillerFn()},abortAjax:function(){var a=this;a.currentRequest&&(a.currentRequest.abort(),a.currentRequest=null)},setOptions:function(b){var c=this,d=c.options;a.extend(d,b),c.isLocal=a.isArray(d.lookup),c.isLocal&&(d.lookup=c.verifySuggestionsFormat(d.lookup)),d.orientation=c.validateOrientation(d.orientation,"bottom"),a(c.suggestionsContainer).css({"max-height":d.maxHeight+"px",width:d.width+"px","z-index":d.zIndex})},clearCache:function(){this.cachedResponse={},this.badQueries=[]},clear:function(){this.clearCache(),this.currentValue="",this.suggestions=[]},disable:function(){var a=this;a.disabled=!0,clearInterval(a.onChangeInterval),a.abortAjax()},enable:function(){this.disabled=!1},fixPosition:function(){var b=this,c=a(b.suggestionsContainer),d=c.parent().get(0);if(d===document.body||b.options.forceFixPosition){var e=b.options.orientation,f=c.outerHeight(),g=b.el.outerHeight(),h=b.el.offset(),i={top:h.top,left:h.left};if("auto"===e){var j=a(window).height(),k=a(window).scrollTop(),l=-k+h.top-f,m=k+j-(h.top+g+f);e=Math.max(l,m)===l?"top":"bottom"}if("top"===e?i.top+=-f:i.top+=g,d!==document.body){var n,o=c.css("opacity");b.visible||c.css("opacity",0).show(),n=c.offsetParent().offset(),i.top-=n.top,i.left-=n.left,b.visible||c.css("opacity",o).hide()}"auto"===b.options.width&&(i.width=b.el.outerWidth()-2+"px"),c.css(i)}},enableKillerFn:function(){var b=this;a(document).on("click.autocomplete",b.killerFn)},disableKillerFn:function(){var b=this;a(document).off("click.autocomplete",b.killerFn)},killSuggestions:function(){var a=this;a.stopKillSuggestions(),a.intervalId=window.setInterval(function(){a.visible&&(a.el.val(a.currentValue),a.hide()),a.stopKillSuggestions()},50)},stopKillSuggestions:function(){window.clearInterval(this.intervalId)},isCursorAtEnd:function(){var a,b=this,c=b.el.val().length,d=b.element.selectionStart;return"number"==typeof d?d===c:document.selection?(a=document.selection.createRange(),a.moveStart("character",-c),c===a.text.length):!0},onKeyPress:function(a){var b=this;if(!b.disabled&&!b.visible&&a.which===d.DOWN&&b.currentValue)return void b.suggest();if(!b.disabled&&b.visible){switch(a.which){case d.ESC:b.el.val(b.currentValue),b.hide();break;case d.RIGHT:if(b.hint&&b.options.onHint&&b.isCursorAtEnd()){b.selectHint();break}return;case d.TAB:if(b.hint&&b.options.onHint)return void b.selectHint();if(-1===b.selectedIndex)return void b.hide();if(b.select(b.selectedIndex),b.options.tabDisabled===!1)return;break;case d.RETURN:if(-1===b.selectedIndex)return void b.hide();b.select(b.selectedIndex);break;case d.UP:b.moveUp();break;case d.DOWN:b.moveDown();break;default:return}a.stopImmediatePropagation(),a.preventDefault()}},onKeyUp:function(a){var b=this;if(!b.disabled){switch(a.which){case d.UP:case d.DOWN:return}clearInterval(b.onChangeInterval),b.currentValue!==b.el.val()&&(b.findBestHint(),b.options.deferRequestBy>0?b.onChangeInterval=setInterval(function(){b.onValueChange()},b.options.deferRequestBy):b.onValueChange())}},onValueChange:function(){var b=this,c=b.options,d=b.el.val(),e=b.getQuery(d);return b.selection&&b.currentValue!==e&&(b.selection=null,(c.onInvalidateSelection||a.noop).call(b.element)),clearInterval(b.onChangeInterval),b.currentValue=d,b.selectedIndex=-1,c.triggerSelectOnValidInput&&b.isExactMatch(e)?void b.select(0):void(e.length<c.minChars?b.hide():b.getSuggestions(e))},isExactMatch:function(a){var b=this.suggestions;return 1===b.length&&b[0].value.toLowerCase()===a.toLowerCase()},getQuery:function(b){var c,d=this.options.delimiter;return d?(c=b.split(d),a.trim(c[c.length-1])):b},getSuggestionsLocal:function(b){var c,d=this,e=d.options,f=b.toLowerCase(),g=e.lookupFilter,h=parseInt(e.lookupLimit,10);return c={suggestions:a.grep(e.lookup,function(a){return g(a,b,f)})},h&&c.suggestions.length>h&&(c.suggestions=c.suggestions.slice(0,h)),c},getSuggestions:function(b){var c,d,e,f,g=this,h=g.options,i=h.serviceUrl;if(h.params[h.paramName]=b,d=h.ignoreParams?null:h.params,h.onSearchStart.call(g.element,h.params)!==!1){if(a.isFunction(h.lookup))return void h.lookup(b,function(a){g.suggestions=a.suggestions,g.suggest(),h.onSearchComplete.call(g.element,b,a.suggestions)});g.isLocal?c=g.getSuggestionsLocal(b):(a.isFunction(i)&&(i=i.call(g.element,b)),e=i+"?"+a.param(d||{}),c=g.cachedResponse[e]),c&&a.isArray(c.suggestions)?(g.suggestions=c.suggestions,g.suggest(),h.onSearchComplete.call(g.element,b,c.suggestions)):g.isBadQuery(b)?h.onSearchComplete.call(g.element,b,[]):(g.abortAjax(),f={url:i,data:d,type:h.type,dataType:h.dataType},a.extend(f,h.ajaxSettings),g.currentRequest=a.ajax(f).done(function(a){var c;g.currentRequest=null,c=h.transformResult(a,b),g.processResponse(c,b,e),h.onSearchComplete.call(g.element,b,c.suggestions)}).fail(function(a,c,d){h.onSearchError.call(g.element,b,a,c,d)}))}},isBadQuery:function(a){if(!this.options.preventBadQueries)return!1;for(var b=this.badQueries,c=b.length;c--;)if(0===a.indexOf(b[c]))return!0;return!1},hide:function(){var b=this,c=a(b.suggestionsContainer);a.isFunction(b.options.onHide)&&b.visible&&b.options.onHide.call(b.element,c),b.visible=!1,b.selectedIndex=-1,clearInterval(b.onChangeInterval),a(b.suggestionsContainer).hide(),b.signalHint(null)},suggest:function(){if(0===this.suggestions.length)return void(this.options.showNoSuggestionNotice?this.noSuggestions():this.hide());var b,c=this,d=c.options,e=d.groupBy,f=d.formatResult,g=c.getQuery(c.currentValue),h=c.classes.suggestion,i=c.classes.selected,j=a(c.suggestionsContainer),k=a(c.noSuggestionsContainer),l=d.beforeRender,m="",n=function(a,c){var d=a.data[e];return b===d?"":(b=d,'<div class="autocomplete-group"><strong>'+b+"</strong></div>")};return d.triggerSelectOnValidInput&&c.isExactMatch(g)?void c.select(0):(a.each(c.suggestions,function(a,b){e&&(m+=n(b,g,a)),m+='<div class="'+h+'" data-index="'+a+'">'+f(b,g,a)+"</div>"}),this.adjustContainerWidth(),k.detach(),j.html(m),a.isFunction(l)&&l.call(c.element,j,c.suggestions),c.fixPosition(),j.show(),d.autoSelectFirst&&(c.selectedIndex=0,j.scrollTop(0),j.children("."+h).first().addClass(i)),c.visible=!0,void c.findBestHint())},noSuggestions:function(){var b=this,c=a(b.suggestionsContainer),d=a(b.noSuggestionsContainer);this.adjustContainerWidth(),d.detach(),c.empty(),c.append(d),b.fixPosition(),c.show(),b.visible=!0},adjustContainerWidth:function(){var b,c=this,d=c.options,e=a(c.suggestionsContainer);"auto"===d.width&&(b=c.el.outerWidth()-2,e.width(b>0?b:300))},findBestHint:function(){var b=this,c=b.el.val().toLowerCase(),d=null;c&&(a.each(b.suggestions,function(a,b){var e=0===b.value.toLowerCase().indexOf(c);return e&&(d=b),!e}),b.signalHint(d))},signalHint:function(b){var c="",d=this;b&&(c=d.currentValue+b.value.substr(d.currentValue.length)),d.hintValue!==c&&(d.hintValue=c,d.hint=b,(this.options.onHint||a.noop)(c))},verifySuggestionsFormat:function(b){return b.length&&"string"==typeof b[0]?a.map(b,function(a){return{value:a,data:null}}):b},validateOrientation:function(b,c){return b=a.trim(b||"").toLowerCase(),-1===a.inArray(b,["auto","bottom","top"])&&(b=c),b},processResponse:function(a,b,c){var d=this,e=d.options;a.suggestions=d.verifySuggestionsFormat(a.suggestions),e.noCache||(d.cachedResponse[c]=a,e.preventBadQueries&&0===a.suggestions.length&&d.badQueries.push(b)),b===d.getQuery(d.currentValue)&&(d.suggestions=a.suggestions,d.suggest())},activate:function(b){var c,d=this,e=d.classes.selected,f=a(d.suggestionsContainer),g=f.find("."+d.classes.suggestion);return f.find("."+e).removeClass(e),d.selectedIndex=b,-1!==d.selectedIndex&&g.length>d.selectedIndex?(c=g.get(d.selectedIndex),a(c).addClass(e),c):null},selectHint:function(){var b=this,c=a.inArray(b.hint,b.suggestions);b.select(c)},select:function(a){var b=this;b.hide(),b.onSelect(a)},moveUp:function(){var b=this;if(-1!==b.selectedIndex)return 0===b.selectedIndex?(a(b.suggestionsContainer).children().first().removeClass(b.classes.selected),b.selectedIndex=-1,b.el.val(b.currentValue),void b.findBestHint()):void b.adjustScroll(b.selectedIndex-1)},moveDown:function(){var a=this;a.selectedIndex!==a.suggestions.length-1&&a.adjustScroll(a.selectedIndex+1)},adjustScroll:function(b){var c=this,d=c.activate(b);if(d){var e,f,g,h=a(d).outerHeight();e=d.offsetTop,f=a(c.suggestionsContainer).scrollTop(),g=f+c.options.maxHeight-h,f>e?a(c.suggestionsContainer).scrollTop(e):e>g&&a(c.suggestionsContainer).scrollTop(e-c.options.maxHeight+h),c.options.preserveInput||c.el.val(c.getValue(c.suggestions[b].value)),c.signalHint(null)}},onSelect:function(b){var c=this,d=c.options.onSelect,e=c.suggestions[b];c.currentValue=c.getValue(e.value),c.currentValue===c.el.val()||c.options.preserveInput||c.el.val(c.currentValue),c.signalHint(null),c.suggestions=[],c.selection=e,a.isFunction(d)&&d.call(c.element,e)},getValue:function(a){var b,c,d=this,e=d.options.delimiter;return e?(b=d.currentValue,c=b.split(e),1===c.length?a:b.substr(0,b.length-c[c.length-1].length)+a):a},dispose:function(){var b=this;b.el.off(".autocomplete").removeData("autocomplete"),b.disableKillerFn(),a(window).off("resize.autocomplete",b.fixPositionCapture),a(b.suggestionsContainer).remove()}},a.fn.autocomplete=a.fn.devbridgeAutocomplete=function(c,d){var e="autocomplete";return 0===arguments.length?this.first().data(e):this.each(function(){var f=a(this),g=f.data(e);"string"==typeof c?g&&"function"==typeof g[c]&&g[c](d):(g&&g.dispose&&g.dispose(),g=new b(this,c),f.data(e,g))})}});

// jquery easing
$.easing.easeInOutExpo = function (x, t, b, c, d) {
	if (t==0) return b;
	if (t==d) return b+c;
	if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
	return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
}

// notify
;(function($){
	var defaults = {
		clickToHide: true 	// 点击关闭
		,delay: 5e3 		// 5秒后自动关闭，为0时不关闭
		,title: '提示消息' 	// 标题
		,text: '' 			// 文字描叙，可选
		,type: 'warn' 		// 类型：错误(error)，正确(success)，警告(warn，默认)
		,call: null 		// 出发时的回调，可选
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

function searchBar() {
	var $win = $(window),
		$searchForm = $('#_search_form'),
		$header = $('.header');

	$('#_search_ipt').autocomplete({
        serviceUrl: 'json/keywords.json',
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

	var searchBarFixed = function() {
		if ($win.scrollTop() > 129) {
			$header.addClass('search-animated');
		} else {
			$header.removeClass('search-animated');
		}
	}
	if (typeof searchFixed !== 'undefined' && searchFixed === true) {
		$header.addClass('header-fixed');
		$win.on('scroll', function() {
			searchBarFixed();
		})
		searchBarFixed();
	}
}

// 用户中心导航高亮
function userMenu() {
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

// 商品分类
function category() {
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
	var 
		timer     = 0,
		$elevator = $('.elevator'),
		$win      = $(window),
		threshold = $win.height(),
		$toolbar = $('.toolbar').html('<a class="item wechat" href="javascript:;"><img src="images/qrcode.png"></a><a class="item qq" href="tencent://message/?uin=1296394620&amp;Site=在线QQ&amp;Menu=yes"></a><a class="item gotop" href="javascript:;"></a>'),
		$gotop 	  = $toolbar.find('.gotop');

	var scroll = function() {
		clearTimeout(timer);
		timer = setTimeout(function() {
			var fade = 'fadeIn',
				className = 'addClass';

			if ($win.scrollTop() < threshold) {
				fade = 'fadeOut';
				className = 'removeClass';
			}
			$gotop[className]('fade');
			$elevator[fade]();
		}, 50);
	}

	$win.on('scroll', scroll);

	$toolbar.on('click', '.gotop', function() {
		$('html, body').animate({
			scrollTop: 0
		}, 700, 'easeInOutExpo');
	});
}


var cookieFn = {
	set: function(key, val, day) {
		var ck = key + "=" + escape(val);
		if(day > 0){
            var date = new Date();
            var ms = day * 24 * 3600 * 1e3;
            date.setTime(date.getTime() + ms);
            ck += "; expires=" + date.toGMTString();
        }
		ck += '; path=/';
		// ck += '; path=/; domain=sghaoyao.com';
		document.cookie = ck;
	},
	get: function(key) {
		var ck = document.cookie.split('; ');
		for (var i = 0; i < ck.length; i++) {
			var temp = ck[i].split('=');
			if (temp[0] == key) return unescape(temp[1]);
		}
	},
	del: function(key) {
		var date = new Date();
		date.setTime(date.getTime() - 1e4);
		document.cookie = key + '=v; expires =' + date.toGMTString();
	}
}

// 购物车
var shopcart = {
	init: function() {
		this.$header = $('.header');
		this.$count = this.$header.find('.cart .count');
		this.count = 0;
		if (this.$count.length > 0) {
			this.initCart();
			this.bindEvent();
		}
	},
	initCart: function() {
		var that = this,
			cart = this.getCart().split('@');

		$.ajax({
			url: 'json/cart.php',
			data: {ids: cart.join()},
			beforeSend: function() {
				// loading
				that.$header.find('.cart .bd').html('<div class="arrow"></div><div class="loading"></div>');
			},
			success: function(res) {
				if (res && res.data) {
					that.count = res.data.length;
					that.toHtml(res.data);
				}
				try{
					_global.fn.initCart(res.data); // page cart_index
				}catch(error){};
			}
		})
	},
	toHtml: function(data) {
		var that = this,
			model = [];			

		if (data.length > 0) {
			model.push('<div class="arrow"></div><div class="th">最新加入</div><div class="tb">');
			model.push('<ul>');
			model.push(that.addList(data));
			model.push('</ul></div>');
			model.push('<div class="tf">');
			model.push('<a href="enquiry2.html" class="btn btn-red">查看询价单</a>');
			model.push('共 <em class="count">', that.count, '</em> 件商品 ');
			model.push('</div>');
			that.$header.find('.cart .bd').html(model.join(''));
			that.calcCount(0);
		} else {
			that.empty();	// 购物车为空
		}
	},
	addList: function(data) {
		var that = this,
			model = [],
			cart = this.getCart().split('@'),
			json = [];

		var ellipsis = function(str, maxLength) {
	        var count = 0;
	        var len = str.length;
	        var cut = [];

			for (var i = 0; i < len; i++) {
				var a = str.charAt(i);
				if (count >= maxLength) {
					cut.push('...');
					break;
				} else if (escape(a).length > 4) {
					count += 2;
				} else {
					count += 1;
				}
				cut.push(a);
			}
			return cut.join('');
	    }

		$.each(data, function(i, item) {
			model.push('<li>');
			model.push('<a href="product.html' , item.id ,'" class="name" target="_blank">', item.name , '</a>');
			model.push('<span class="level">', ellipsis(item.level, 50), '</span>');
			model.push('<a href="javascript:;" data-id="', item.id, '" class="fa fa-times"></a>');
			model.push('</li>');
		})
		return model.join('');
	},
	bindEvent: function() {
		var that = this;

		// 开关
		that.$header.find('.cart').on('mouseenter', function() {
			$(this).addClass('cart-hover');
		}).on('mouseleave', function() {
			$(this).removeClass('cart-hover');
		})

		// 删除购物车商品
		that.$header.on('click', '.fa-times', function() {
			$(this).parent().remove();
			that.delCart($(this).data('id'));
		})
	},
	addToCart: function(data) {
		var that = this,
            id = data[0],
			cart = that.getCart(),
            model = [{
		    	'id': data[0],
	        	'name': data[1],
	        	'level': data[2]
		    }];

	    if (that.isInCart(id)) {	    	
	    	return that;
	    } else  if (cart === '') {
			// 第一次添加购物车
			cart = id;
			that.toHtml(model);
		} else {
			// 添加相同商品时，调整前后顺序，保证新添加的商品在最前面
			cart = id + '@' + cart;
			that.$header.find('.cart ul').prepend(that.addList(model));
		}

		that.saveCart(cart);
		that.calcCount(1);

		// 保存到服务器
		this.savaService('/cart/add', {commodityId: id});
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

		// 还原按钮状态
		$('.fa-pro-list').find('.btn-gray').each(function() {
			var data = ($(this).data('s') || '').split('|');
            if (id == data[0]) {
                $(this).removeClass('btn-gray').prop('disabled', false).html('加入询价单');
                return false; // break
            }
		})
		$('#buying').each(function() {
			var data = ($(this).data('s') || '').split('|');
            if (id == data[0]) {
                $(this).html('加入询价单').removeClass('disabled');
            }
		})

		// 保存到服务器
		this.savaService('/cart/delete', {commodityId: id});
	},
	savaService: function(url, data, callback) {
		var that = this;
		$.ajax({
			url: url,
			type: 'POST',
			data: data,
			success: function(res) {
				if (typeof call === 'function') {
					callback.call(that, res);
				}
			}
		})
	},
	calcCount: function(num) {
		var that = this;
		that.count += num;
		if (num < 0) {
			num = '<i>' + num + '</i>';
		} else if (num > 0) {
			num = '<i>+' + num + '</i>';
		} else {
			num = '';
		}
		that.$count.html(that.count + num);
		that.$header.find('.tf .count').html(that.count);
		num && that.$count.find('i').animate({top: '-30px', 'opacity': 0}, 1e3);
	},
	empty: function() {
		this.$header.find('.cart').removeClass('cart-hover').find('.bd').html('<div class="arrow"></div><div class="empty">询价单中还没有商品，<a class="c-blue" href="product_list.html">立即挑选</a> 吧！</div>');
	},
	isInCart: function(id) {
		var cart = this.getCart().split('@');
		for (var i = 0; i < cart.length; i++) {
			if (cart[i] == id) {
				return true;
			}
		}
		return false;
	},
	clearCart: function() {
		this.saveCart('');
	}
}

$(function() {
    lazyload({
		placeholder : 'images/blank.gif',
		selector: '.lazyload'
    });
	shopcart.init();
	category();
	searchBar();
	userMenu();
    gotop();
})
