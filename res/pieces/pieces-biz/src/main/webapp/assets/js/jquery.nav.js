;
(function($, window, document, undefined) {
	"use strict";
	var 
		$win      = $(window),
		$doc      = $(document),
		isResize  = false,
		defaults  = {
			navItems        : 'a',
			currentClass    : 'current',
			easing          : 'easeInOutExpo',
			scrollSpeed     : 1200,
			scrollThreshold : 0.33
		}

	var OnePageNav = function(elem, options) {
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