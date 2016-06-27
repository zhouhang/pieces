$.fn.slide = function(options) {
    var $wrap = $(this),
        $item = $wrap.find('.item'),
        $nav,
        size = $item.length,
        idx = 0,
        timer = 0,
        delay = 5e3,
        speed = 700;

    if (size <= 1) {
        return false;
    }

    var _setNav = function() {
        var tpl = ['<div class="hd"><i class="on"></i>'];
        for (var i = 1; i < size; i++) {
            tpl.push('<i></i>');
        }
        tpl.push('</div>');
        $nav = $(tpl.join('')).appendTo($wrap);
    }

    var _play = function() {
        $item.eq(idx).fadeIn(speed).siblings().fadeOut();
        $nav.children().eq(idx).addClass('on').siblings().removeClass('on');
    } 

    var _autoPlay = function() {
        timer && clearTimeout(timer);
        timer = setInterval(function() {
            idx = ++idx >= size ? 0 : idx;
            _play();
        }, delay);
    }

    var _bindEvent = function() {
        $nav.on('click', 'i', function() {
            idx = $(this).index();
            _play();
            _autoPlay();
        })
    }

    var _init = function() {
        $item.css({'position':'absolute','display':'none'}).eq(idx).fadeIn(700);
        _setNav();
        _bindEvent();
        _autoPlay();
    }

    _init();
};

function showImg(url, fixed) {
	var w,h;
	var _loadImage = function() {
		var img = new Image(); 
        img.onload = function(){
            img.onload = null;
            w = img.width;
            _showImg();
        }
        img.src = url; 
	}

	var _showImg = function() {
		var css = fixed === 'fixed' ? 'position:fixed;' : 'top:' + ($(window).scrollTop() + 50) + 'px;';
		var style = css + 'left:50%;margin-left:' + (-w / 2) + 'px;';
		var tpl = '<div class="modal" style="' + style + '"><img src="' + url + '"/><span>Share / <a class="icon sina" href="#!"></a></span></div>';
		$('body').append(tpl);
		$('.loading').remove();
	}

	$('body').append('<div class="modal-cover"></div><div class="loading"></div>');

	$('body').off('click.modal').on('click.modal', '.modal-cover', function() {
		$('.modal-cover, .modal, .loading').remove();
	})

	_loadImage();
}



$(function() {
	var $navCat = $('.header .cat');
	var timer = 0;
	var speed = 200;
    $('#nav-cat').on({
    	'mouseenter': function() {
    		timer && clearTimeout(timer);
    		timer = setTimeout(function() {
    			$navCat.stop(false, false).slideDown(300);
    		}, speed);
    	},
    	'mouseleave': function() {
    		timer && clearTimeout(timer);
    		$navCat.stop(false, false).slideUp(300);
    	}
    })

    $navCat.on({
    	'mouseenter': function() {
    		timer && clearTimeout(timer);
    		$navCat.stop(false, false).slideDown(300);
    	},
    	'mouseleave': function() {
    		timer && clearTimeout(timer);
    		$navCat.stop(false, false).slideUp(300);
    	}
    })

    $('.slide').slide();

    // 返回顶部
	$('.gotop').on('click', function(e) {
	    gotoTop();
	    e.preventDefault();
	});

	function gotoTop() {
	    var anim = function() {
	        var scrollTop = $(window).scrollTop();
	        window.scrollTo(0, Math.floor(scrollTop / 1.1));
	        scrollTop <= 0 && clearInterval(timer);
	    }
	    var timer = setInterval(function() {
	        anim();
	    }, 10);
	}

});