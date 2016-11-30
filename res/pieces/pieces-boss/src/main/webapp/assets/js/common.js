// 用户中心导航高亮
function currNav() {
	var $side = $('.wrap'),
		URL = document.URL.split('#')[0].split('?')[0],
		base = $('base').attr('href')+"/";
		
	$side.find('a').each(function() {
		var str = this.href.toLowerCase();
		str = str.replace(base,"");
		if (str !== "") {
			var urlBefore = URL.split('/')[3];
			var hrefBefore = str.split('/')[0];
			if(urlBefore === hrefBefore){
				$(this).addClass("on").parent().prev("a").addClass('curr');
				return false; // break
			}
		}
	})

/*	$side.on('click', 'div a', function() {
		$(this).parent().toggleClass('expand').siblings().removeClass('expand');
	})*/
}

function pageInit() {
	// 用户中心导航高亮
	currNav();
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
})(jQuery);

/**
 * Created by kevin1 on 7/18/16.
 */

!(function($) {
    $.fn.code = function (options) {
        var url = $.fn.code.settings.url;
        var $this = $(this);
        $.getJSON(url,{beedId:options.beedId, typeId:options.typeId}, function (data){
            if (data.status === "y") {
                var html = "<option value='-1'>请选择</option>";
                if (data.data != null) {
                    $.each(data.data, function(k, v){
                        html += "<option value='"+v.id+"'>"+ v.name+"</option>";
                    });
                }
                $this.html(html);
            }
        })
    }
    $.fn.code.type = {
        COMMODITY_SPECIFICATIONS: 'SPEC',//"片型"
        COMMODITY_PLACE: 'ORIGIN',//"原药产地"
        COMMODITY_LEVEL: 'LEVEL'//"规格等级"
    }

    $.fn.code.settings = {
        url:"/code/query"
    }

    $.fn.serializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function(){
            if (o[this.name]){
                if(!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }
}(jQuery));


$(function() {
	pageInit();
})
