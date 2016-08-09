$(function(){
	var $win = $(window),
		SPEED = 300;

	var lazyDom = function() {
		var timer;
		var winHeight = $win.height();
		var space = 100;
		var $ele = $('#lazyDom .idx-floor');
		var getDom = function() {
			var top = $win.scrollTop();
			var bottom = top + winHeight + space;
			$ele.each(function(i) {
				var $self = $(this);
				var offsetY = $self.offset().top;
				if ($self.data('loaded') === '1') {
					return;
				}
				if (offsetY > top - $self.outerHeight() && bottom > offsetY) {
					$self.data('loaded', '1');
					$self.html($self.find('textarea').val());
					
				}
			})
		}

		var _getDom = function() {
			timer && clearTimeout(timer);
			timer = setTimeout(getDom, 100);
		};
		$win.on('scroll.lazyDom', _getDom);
		getDom();
	}
	lazyDom();

	// banner-slide
	var $slide = $('#jslide'),
		size = $slide.find('.bd li').length;
	if (size > 1) {
		var 
			html  = [],
			timer = 0,
			speed = 5e3,
			idx   = 0,
			winWidth = Math.max($win.width(), 1200),
			$lis  = $slide.find('.bd li'),
			$ul  = $lis.parent(),
			$ctrl;
		$slide.css({'overflow':'hidden', '*zoom':'1'});
		$lis.css({'float':'left', 'width': winWidth});
		$ul.css({'width': winWidth * size, 'position':'absolute', 'left':'0', 'top':'0'});

		var autoPlay = function() {
			timer && clearInterval(timer);
			timer = setInterval(function() {
				idx = idx === size - 1 ? 0 : idx + 1;
				doPlay();
			}, speed);
		}
		var doPlay = function() {
			$ctrl.eq(idx).addClass('on').siblings().removeClass('on');
			// $lis.eq(idx).css({zIndex:1}).hide().fadeIn().siblings().css({zIndex:0}).hide();
			$ul.animate({'left': -winWidth * idx}, SPEED);
		}
		var resize = function() {
			winWidth = Math.max($win.width(), 1200);
			$lis.css({width: winWidth});
			$ul.stop().css({'width': winWidth * size, 'left': -winWidth * idx});
		}
		for (var i = 0; i < size; i++) {
			html.push('<span', (i === 0 ? ' class="on"' : ''),'></span>');
		}
		$slide.find('.hd').append(html.join(''));
		$slide.on('click', 'span', function() {
			idx = $(this).index();
			doPlay();
			autoPlay();		
		})
 		$ctrl = $slide.find('.hd span');
 		autoPlay();
 		var _resize = throttle(resize, 50);
 		$win.on('resize', _resize);
	}

	// brands
	if ($('.brands').find('.col').length > 1 ) {
		var $ele = $(this),
			$slide = $ele.find('.wrapper'),
			count = $ele.find('.col').length,
			idx = 0;

		$ele.on('click', '.prev', function() {
			if (idx === 0) {
				return;
			}
			idx -= 1;
			$slide.animate({left: -idx * 186}, SPEED);
		})

		$ele.on('click', '.next', function() {
			if (idx === count - 1) {
				return;
			}
			idx += 1;
			$slide.animate({left: -idx * 186}, SPEED);
		})
	}


	// 楼层导航
	$elevator = $('#jelevator');
	var threshold = $('.idx-main').offset().top;
	var elevator = function() {
		var stop = $win.scrollTop();
		$elevator[stop > threshold ? 'fadeIn' : 'fadeOut'](100);
	}
	// elevator();
	var _elevator = throttle(elevator, 50);
	$win.on('scroll.elevator', _elevator);
	$elevator.onePageNav({scrollSpeed: SPEED});
});