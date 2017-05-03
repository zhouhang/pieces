!(function($, window) {	
	var defaults = {
		selector: '.thumb',
		albumLabel: '<span>%1 / %2</span>',
		fadeDuration: 300,
		resizeDuration: 100
	};
	var $body = $('body'),
		$window = $(window);

	function Lightbox(options) {
		this.options = $.extend({}, defaults, options);
		this.currentImageIndex = 0;
		this.init();
	}

	Lightbox.prototype = {
		init: function() {
			this.getSize().resize().enable().build();
		},
		resize: function() {
			var self = this,
				timer;

			var winResize = function () {
				if (self.album) {
					timer && clearTimeout(timer);
					timer = setTimeout(function () {
						self.getSize();
						self.changeImage(self.currentImageIndex);
					}, self.options.resizeDuration);
				}
			};
			$window.on('resize.lightbox', winResize);
			return this;
		},
		getSize: function() {
			this.windowWidth = $(window).width();
			this.windowHeight = $(window).height();
			return this;
		},
		enable: function() {
			var self = this;
			$(self.options.selector).on('click', 'img', function() {
				self.start($(this));
				return false;
			})
			return this;
		},
		build: function() {
			var self = this;
			$('<div id="lightboxOverlay" class="lightboxOverlay"></div><div id="lightbox" class="lightbox"><div class="lb-nav"></div><div class="lb-thumb"><img class="lb-image" src="data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="/><span class="lb-prev" title="上一张"></span><span class="lb-next" title="下一张"></span></div><div class="lb-loader"></div><div class="lb-close" title="关闭"></div><div class="lb-op"><button type="button" class="lb-turn-left">左转</button><button type="button" class="lb-turn-right">右转</button></div></div>').appendTo($('body'));
			this.$lightbox = $('#lightbox');
			this.$overlay = $('#lightboxOverlay');
			self.$image = this.$lightbox.find('.lb-image');
			self.$loader = this.$lightbox.find('.lb-loader');
			self.$nav = this.$lightbox.find('.lb-nav');
			this.$overlay.on('click', function() {
				self.end();
				return false;
			});
			this.$lightbox.on('click', '.lb-close', function() {
				self.end();
				return false
			})
			this.$lightbox.on('click', '.lb-prev', function() {
				if (self.currentImageIndex === 0) {
					self.changeImage(self.album.length - 1)
				} else {
					self.changeImage(self.currentImageIndex - 1)
				}
				return false
			});
			this.$lightbox.on('click', '.lb-next', function() {
				if (self.currentImageIndex === self.album.length - 1) {
					self.changeImage(0)
				} else {
					self.changeImage(self.currentImageIndex + 1)
				}
				return false
			});
			this.$lightbox.on('click', '.lb-turn-left', function() {			
				self.rotate(-1);
				return false;
			});
			this.$lightbox.on('click', '.lb-turn-right', function() {				
				self.rotate(1);
				return false;
			});
			return this;
		},
		rotate: function(arrow) {
			var self = this;
			var img = new Image();
			img.onload = function() {
				var imageHeight = img.height; 
				var imageWidth = img.width;		
				var windowHeight = self.windowHeight - 40;
				var windowWidth = self.windowWidth - 20;
				var maxImageHeight = self.options.maxHeight || windowHeight; 
				var maxImageWidth = self.options.maxWidth || windowWidth; 
				var rotation = self.rotation;
				var pos = 0;
				img.onload = null;

				maxImageHeight = Math.min(maxImageHeight, windowHeight);
				maxImageWidth = Math.min(maxImageWidth, windowWidth);

				rotation += arrow;

				if (rotation === 4) {
					rotation = 0;
				} else if (rotation === -1) {
					rotation = 3;
				}

				if (rotation % 2 !== 0) {
					var temp = imageWidth;
					imageWidth = imageHeight;
					imageHeight = temp;
				}
				self.rotation = rotation;

				if ((imageWidth > maxImageWidth) || (imageHeight > maxImageHeight)) {
					if ((imageWidth / maxImageWidth) > (imageHeight / maxImageHeight)) {
						imageHeight = parseInt(imageHeight * maxImageWidth / imageWidth, 10);
						imageWidth = maxImageWidth;
					} else {
						imageWidth = parseInt(imageWidth * maxImageHeight / imageHeight, 10);
						imageHeight = maxImageHeight;
					}
				}

				imageWidth = Math.max(300, imageWidth);
				imageHeight = Math.max(300, imageHeight);

				self.$lightbox.css({
					width: imageWidth,
					height: imageHeight,
					marginLeft: -(imageWidth+8)/2,
					marginTop: -(imageHeight+8)/2
				});

				if (rotation % 2 !== 0) {
					pos = (imageWidth - imageHeight)/2;
					self.$image.css({
						width: imageHeight,
						height: imageWidth
					})
				} else {
					self.$image.css({
						width: imageWidth,
						height: imageHeight
					})
				}
				self.$image.css({
					'transform': 'rotate(' + (90*rotation) + 'deg)',
					'WebkitTransform': 'rotate(' + (90*rotation) + 'deg)',
					'OTransform': 'rotate(' + (90*rotation) + 'deg)', 
					'msTransform': 'rotate(' + (90*rotation) + 'deg)', 
					'MozTransform': 'rotate(' + (90*rotation) + 'deg)',
					'marginLeft': pos + 'px',
					'marginTop': -pos + 'px'
				})
			};
			img.src = self.album[self.currentImageIndex].url;
			return false
		},
		start: function($img) {
			var self = this;
			self.album = [];
			$img.closest(self.options.selector).find('img').each(function() {
				self.album.push({
					url: $(this).data('src') || this.src,
					preloader: false
				});
		    });	
		    this.$image.siblings()[self.album.length < 2 ? 'hide' : 'show']();
			this.$overlay.fadeIn(this.options.fadeDuration);
			this.$lightbox.fadeIn(this.options.fadeDuration);
			this.changeImage($img.index());
		},
		changeImage: function(current) {
			var self = this;
			self.$loader.fadeIn('slow');
			self.$image.hide();

			var img = new Image();
			img.onload = function() {
				var imageHeight = img.height; 
				var imageWidth = img.width;		
				var windowHeight = self.windowHeight - 40;
				var windowWidth = self.windowWidth - 20;
				var maxImageHeight = self.options.maxHeight || windowHeight; 
				var maxImageWidth = self.options.maxWidth || windowWidth; 
				img.onload = null;

				maxImageHeight = Math.min(maxImageHeight, windowHeight);
				maxImageWidth = Math.min(maxImageWidth, windowWidth);

				if ((imageWidth > maxImageWidth) || (imageHeight > maxImageHeight)) {
					if ((imageWidth / maxImageWidth) > (imageHeight / maxImageHeight)) {
						imageHeight = parseInt(imageHeight * maxImageWidth / imageWidth, 10);
						imageWidth = maxImageWidth;
					} else {
						imageWidth = parseInt(imageWidth * maxImageHeight / imageHeight, 10);
						imageHeight = maxImageHeight;
					}
				}

				imageWidth = Math.max(300, imageWidth);
				imageHeight = Math.max(300, imageHeight);

				self.$lightbox.animate({
					width: imageWidth,
					height: imageHeight,
					marginLeft: -(imageWidth+8)/2,
					marginTop: -(imageHeight+8)/2
				}, self.options.fadeDuration, 'swing', function() {
					self.$image.attr({
						'src': self.album[current].url,
						'width': imageWidth,
						'height': imageHeight
					}).fadeIn();

					self.$loader.stop(true).hide();
				});
			};
			img.src = self.album[current].url;
			self.currentImageIndex = current;
			self.preloadNeighboringImages();
			self.rotation = 0;
			self.rotate(0);
			self.showNav();

		},
		preloadNeighboringImages: function() {
			if (this.album.length > this.currentImageIndex + 1) {
				var preloadNext = new Image();
				preloadNext.src = this.album[this.currentImageIndex + 1].url
			}
			if (this.currentImageIndex > 0) {
				var preloadPrev = new Image();
				preloadPrev.src = this.album[this.currentImageIndex - 1].url
			}
		},
		showNav: function() {
			var labelText = this.imageCountLabel(this.currentImageIndex + 1, this.album.length);
			this.$nav.html(labelText);
		},
		imageCountLabel: function(currentImageNum, totalImages) {
			return this.options.albumLabel.replace(/%1/g, currentImageNum).replace(/%2/g, totalImages);
		},
		end: function() {
			this.$lightbox.fadeOut(this.options.fadeDuration);
			this.$overlay.fadeOut(this.options.fadeDuration);
		}
	}
	$(function() {
		new Lightbox();
	})
})(jQuery, window);
