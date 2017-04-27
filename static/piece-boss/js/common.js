
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

;(function($){
    var defaults = {
        clickToHide: true   // 点击关闭
        ,delay: 3e3         // 3秒后自动关闭，为0时不关闭
        ,title: '提示消息'  // 文字
        ,text: ''           // 说明
        ,type: 'warn'       // 类型：错误(error)，正确(success)，警告(warn)
        ,call: null         // 关闭后回调
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
        modal.push(    '<div class="inner">');
        modal.push(        '<div class="icon">');
        modal.push(            icons[settings.type]);
        modal.push(        '</div>');
        modal.push(        '<div class="text', (settings.text ? '' : ' mid') , '">');
        modal.push(            '<h3>', settings.title, '</h3>');
        modal.push(            '<p>', settings.text, '</p>');
        modal.push(        '</div>');
        modal.push(    '</div>');
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

// 获取页面url参数
function getParams() {
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

// 用户中心导航高亮
function currNav() {
	var $side = $('.nav'),
		URL = document.URL,
		arr = URL.toLowerCase().split('#')[0].split('?')[0].split('/'),
		pa = arr[3] + arr[4];

	$side.find('.subnav a').each(function() {
		var url = this.href.toLowerCase(),
			arr2 = url.toLowerCase().split('#')[0].split('?')[0].split('/'),
			son = arr2[3] + arr2[4];

        if (URL === url) {
            $(this).addClass('on').parent().prev('a').addClass('curr');
        } else if (pa === son) {
        	$(this).parent().prev('a').addClass('curr');
        }
	})
}

function navMsg(notification) {
	$.ajax({
        url: 'json/msglist.json',
        type: 'POST',
        success: function (result) {
            if(result.status=='y'){
                var t = false;
                var ACCOUNT_BILL_NUM = result.data['1'] || '';
                var ENQUIRYBILL_NUM = result.data['2'] || '';
                var CERTIFY_RECORD_NUM = result.data['3'] || '';
                var ANON_ENQUIRY_NUM = result.data['4'] || '';
                var PAY_RECORD_NUM = result.data['5'] || '';

                if (ACCOUNT_BILL_NUM || ENQUIRYBILL_NUM || PAY_RECORD_NUM) {
                	$('#salePage').append('<i></i>');
                	t = true;
                } else {
                	$('#salePage').find('i').remove();
                }

                if (CERTIFY_RECORD_NUM || ANON_ENQUIRY_NUM) {
                	$('#message').append('<i></i>');
                	t = true;
                } else{
                    $('#message').find('i').remove();
                }

                $('#paymentIndex').find('b').html(PAY_RECORD_NUM);
                $('#enquiryIndex').find('b').html(ENQUIRYBILL_NUM);
                $('#accountIndex').find('b').html(ACCOUNT_BILL_NUM);
                $("#certifyList").find('b').html(CERTIFY_RECORD_NUM); 
                $("#anonEnquiry").find('b').html(ANON_ENQUIRY_NUM);
                notification && t && showNotification({
                    title: '上工好药',
                    body: '后台有新的任务待处理'
                });
            }
        }
    });
    
    // 5分钟请求一次
    setTimeout(function() {
        navMsg(true);
    }, 3e5);
}


function showNotification(options) {
    if (!window.Notification || !options) {
        return;
    }
    Notification.requestPermission(function() {}); // 获取权限

    var defaults = {
        body: options.body || '',
        icon: options.icon || 'http://192.168.1.34/yaocai/pieces/static/piece-boss/images/slogan.png',
        tag: options.tag || (new Date).getTime()
    }
    if (defaults.body.length > 40) {
        defaults.body = defaults.body.substring(0, 37) + '...';
    }
    var t = new Notification(options.title || '消息', defaults);

    t.onshow = function(){
        $('body').append('<audio src="media/voice.mp3" id="notification-audio" preload="auto" autoplay></audio>');
    }
    setTimeout(function() {
        $('#notification-audio').remove();
        t.close();
    }, options.delay || 5e3);
}

function pageInit() {
	// 用户中心导航高亮
	currNav();
	// 新消息提示
	// navMsg(true);
}

$(function() {
	pageInit();
})
