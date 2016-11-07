/**
 * Created by kevin1 on 10/20/16.
 * 常用工具代码
 */
!(function($) {
    /**
     * 表单序列化为对象
     * @returns {{}}
     */
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

    /**
     * 获取参数值
     * @param name 参数名称
     * @returns
     */
    $.fn.getParamValue=function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    /**
     * 获取参数列表
     * @returns {}
     */
    $.fn.getParams=function() {
        var url = window.location.search; //获取url中"?"符后的字串
        var params = {};
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                params[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
            }
        }
        return params;
    }

    /**
     * 把url 后面的参数赋值到对应表单
     * @returns {{}}
     */
    $.fn.initByUrlParams=function() {
        var url = window.location.search; //获取url中"?"符后的字串
        var params = {};
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for(var i = 0; i < strs.length; i ++) {
                var name = strs[i].split("=")[0];
                var value = decodeURI(strs[i].split("=")[1]);
                $("[name="+name+"]").val(value);
            }
        }
        return params;
    }
    /**
     *
     */
    $.fn.code = function (code, val, call) {
        $that = $(this);
        $that.html("");
        $.post("/gen/code/"+code, function (result) {
            if (result.status == 200) {
                var html = "";
                $.each(result.data, function (k, v) {
                    html += '<option value="' + v.id + '">' + v.name + '</option>';
                })
                $that.html(html);
                if (val) {
                    $that.val(val);
                }
                if (call){
                    call();
                }
            }
        })
    }
})(jQuery);

!(function($){
    var defaults = {
        clickToHide: true   // 点击关闭
        ,delay: 5e3         // 5秒后自动关闭，为0时不关闭
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
    var $wrapper;

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
        if (typeof $wrapper === 'undefined') {
            $wrapper = $('<div class="notify-wrapper"></div>').appendTo($('body'));
        }
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
        urlBefore = URL.split('/')[3];

    $aside.on('click', 'dt', function() {
        $(this).next().slideToggle()
        .parent().toggleClass('expand').siblings().removeClass('expand')
        .find('dd').slideUp();
    })
    .find('.active').addClass('extend');

    // 导航高亮
    $aside.find('a').each(function() {
        var url = this.href.toLowerCase(),
            hrefBefore = url.split('/')[3];
        if (URL === url) {
            $(this).addClass('current').closest('dl').addClass('active');
            return false; // break
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
// 相册弹层
function _showImg(url) {
    url && layer.open({
        type: 1,
        shade: .5,
        title: false, //不显示标题
        content: '<img src="' + url + '" alt="" />'
    });
}

$(function() {
    _fix();
    _aside();
})