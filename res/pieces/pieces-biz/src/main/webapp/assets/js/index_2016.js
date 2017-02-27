window.scrollTo(0, 0); // 返回页面顶部

!(function($, window, document, undefined) {
	var 
		$win      = $(window),
		$doc      = $(document),
		defaults = {
			speed: 700,
			delay: 5e3,
			idx: 0,
			easing: 'easeInOutExpo',
			autoPlay: true
		};

	// Carousel
	function Carousel(elem, options) {
		this.settings = $.extend({}, defaults, options);
		this.idx      = this.settings.idx;
		this.$elem    = $(elem);
		this.length   = this.$elem.find('.bd li').length;
		this.length > 1 && this.init();
	}
	Carousel.prototype = {
		init: function() {
			this.createNav();
			this.$slide = this.$elem.find('.bd ul');
			this.$items = this.$slide.find('li');
			this.$nav = this.$elem.find('.hd i');
			this.setCss();
			this.bindEvent();
			this.autoPlay();
		},
		createNav: function() {
			var modal = [];
			var i = 0;
			for (; i < this.length; i++) {
				modal.push('<i', (this.idx === i ? ' class="current"' : '') ,'></i>');
			}
			if (this.$elem.find('.hd').length === 0) {
				this.$elem.append('<div class="hd">' + modal.join('') + '</div>');
			} else {
				this.$elem.find('.hd').html(modal.join(''));	
			}
		},
		doPlay: function() {
			var self = this;
			self.$nav.eq(self.idx).addClass('current').siblings().removeClass('current');
			self.$slide.stop().animate({'left': -self.idx + '00%'}, self.settings.speed, self.settings.easing);
		},
		autoPlay: function() {
			var self = this;
			if (self.settings.autoPlay !== true) {
				return;
			}
			this.timer && clearInterval(this.timer);
			this.timer = setInterval(function() {
				self.idx = self.idx === self.length - 1 ? 0 : self.idx + 1;
				self.doPlay();
			}, self.settings.delay);
		},
		bindEvent: function() {
			var self = this;
			self.$nav.on('click', function() {
				self.idx = $(this).index();
				self.doPlay();
				self.autoPlay();
			})
		},
		setCss: function() {
			this.$slide.css({'width': this.length + '00%', 'position':'absolute', 'left':'0', 'top':'0'});
			this.$items.css({'float':'left', 'width': (100 / this.length) + '%'});
		}
	}
	$.fn.carousel = function(options) {
		return this.each(function() {
			new Carousel(this, options);
		});
	}

	// Slide
	function Slide(elem, options) {
		this.settings = $.extend({}, defaults, options);
		this.idx      = this.settings.idx;
		this.$elem    = $(elem);
		this.width    = this.$elem.width();
		this.length   = this.$elem.find('.col').length;
		this.init();
	}
	Slide.prototype = {
		init: function() {
			this.$items = this.$elem.find('.col');
			this.$prev = this.$elem.find('.prev');
			this.$next = this.$elem.find('.next');
			this.setCss();
			if (this.length < 2) {
				this.$prev.addClass('disabled');
				this.$next.addClass('disabled');
			} else {
				this.bindEvent();
				this.autoPlay();
			}
		},
		doPlay: function(step) {
			var self = this;
			self.$items.eq(self.idx).stop().animate({'left': -1 * step * self.width}, self.settings.speed, function(){
				$(this).removeClass('current');
			});

			if (step === -1) {
				self.idx = self.idx === 0 ? self.length - 1 : self.idx - 1;
			} else {
				self.idx = self.idx === self.length - 1 ? 0 : self.idx + 1;
			}

			self.$items.eq(self.idx).stop().addClass('current').css({'left':step * self.width}).animate({'left':0}, self.settings.speed);
			self.autoPlay();
		},
		autoPlay: function() {
			var self = this;
			if (self.settings.autoPlay !== true) {
				return;
			}
			this.timer && clearInterval(this.timer);
			this.timer = setInterval(function() {
				self.$next.trigger('click');
			}, self.settings.delay);
		},
		bindEvent: function() {
			var self = this;
			self.$prev.on('click', function() {
				self.doPlay(-1);
			})
			self.$next.on('click', function() {
				self.doPlay(1);
			})
		},
		setCss: function() {
			this.$items.eq(0).addClass('current');
		}
	}
	$.fn.slide = function(options) {
		return this.each(function() {
			new Slide(this, options);
		});
	}

	// jquery.nav
	function OnePageNav(elem, options) {
		var defaults  = {
			navItems        : 'a',
			currentClass    : 'current',
			easing          : 'easeInOutExpo',
			scrollSpeed     : 700,
			scrollThreshold : 0.33
		}
		this.isScroll  = false;
		this.sections = {};
		this.settings = $.extend({}, defaults, options);
		this.$elem    = $(elem);
		this.$nav     = this.$elem.find(this.settings.navItems);
		this.init();
	};
	OnePageNav.prototype = {
		init: function() {
			this.getPositions();
			this.$nav.on('click.onePageNav', $.proxy(this.handleClick, this));
			$win.on('resize.onePageNav', $.proxy(this.getPositions, this));
			$win.on('scroll.onePageNav', $.proxy(this.scrollChange, this));
		},
		getPositions: function() {		
			var self = this;
			var throttle = function() {
				self.$nav.each(function() {
					var 
						anchor = self.getHash($(this)),
						$target = $('#' + anchor);

					if ($target.length) {
						self.sections[anchor] = {
							el: $target,
							top: Math.round($target.offset().top)
						}
					}
				})
				self.scrollChange(true);
			}
			self.getPos && clearTimeout(self.getPos);
			self.getPos = setTimeout(throttle, 200);
		},
		scrollChange: function(immediate) {
			var self = this;
			var throttle = function() {
				var position = self.getSection(); 
				if (position !== -1) {
					var $parent = self.$elem.find('a[href$="#' + position + '"]').parent();
					self.adjustNav($parent);
				}
			}
			if (!self.isScroll) {
				self.timer && clearTimeout(self.timer);
				self.timer = setTimeout(throttle, immediate ? 1 : 200);
			}
		},
		adjustNav: function($parent) {
			$parent.addClass(this.settings.currentClass).siblings().removeClass(this.settings.currentClass);
		},
		getHash: function($link) {
			return $link.attr('href').split('#')[1];
		},
		getSection: function() {
			var returnValue = -1;
			var scrollTop = $win.scrollTop();
			var winHeight = Math.round($win.height() * this.settings.scrollThreshold);
			for (var section in this.sections) {
				if (this.sections[section].top < scrollTop + winHeight) {
					returnValue = section;
				}
			}
			return returnValue;
		},
		handleClick: function(e) {
			var self = this;
			var $link = $(e.currentTarget);
			var $parent = $link.parent();
			var newLoc = '#' + self.getHash($link);
			self.isScroll = true;
			self.adjustNav($parent);
			self.scrollTo(newLoc);
			e.preventDefault();
		},
		scrollTo: function(target) {
			var self = this;
			var offset = $(target).offset().top;
			$('html, body').stop().animate({
				scrollTop: offset
			}, this.settings.scrollSpeed, this.settings.easing, function() {
				self.isScroll = false;
			});
		}
	};
	$.fn.onePageNav = function(options) {
		return this.each(function() {
			new OnePageNav(this, options);
		});
	}
})(jQuery, window, document);


$(function(){
	$('#jslide').carousel();

	// 楼层导航
	var 
		$win      = $(window),
		$elevator = $('#jelevator'),
		model     = [];

	$('.idx-floor').each(function() {
		var text = $(this).find('.idx-hd h2').html().replace('类', '');
		var className = text.length > 3 ? ' class="mul"' : '';
		model.push('<li><a href="#', this.id, '"', className, '>', text, '</a></li>');
	});
	model.unshift('<ul>');
	model.push('</ul>');

	$elevator.html(model.join('')).onePageNav();
});