
// lightbox 图片插件
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
            $('<div id="lightboxOverlay" class="lightboxOverlay"></div><div id="lightbox" class="lightbox"><div class="lb-nav"></div><div class="lb-thumb"><img class="lb-image" src="data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw=="/><span class="lb-prev"></span><span class="lb-next"></span></div><div class="lb-loader"></div><div class="lb-close" title="关闭"></div></div>').appendTo($('body'));
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
            return this;
        },
        start: function($img) {
            var self = this;
            self.album = [];
            var idx = 0;
            $img.closest(self.options.selector).find('img').each(function(i) {
                self.album.push({
                    url: $(this).data('src') || this.src,
                    preloader: false
                });
                idx = this.src === $img[0].src ? i : 0;
            }); 
            this.$image.siblings()[self.album.length < 2 ? 'hide' : 'show']();
            this.$overlay.fadeIn(this.options.fadeDuration);
            this.$lightbox.fadeIn(this.options.fadeDuration);
            this.changeImage(idx);
            $body.addClass('noscroll');
        },
        changeImage: function(current) {
            var self = this;
            self.$loader.fadeIn('slow');
            self.$image.hide();

            var img = new Image();
            img.onload = function() {
                var imageHeight = img.height; 
                var imageWidth = img.width;     
                var windowHeight = self.windowHeight - 120;
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
                    marginLeft: -(imageWidth)/2,
                    marginTop: -(imageHeight)/2
                }, self.options.fadeDuration, 'swing', function() {
                    self.$image.attr({
                        'src': self.album[current].url,
                        'width': imageWidth - 8,
                        'height': imageHeight - 8
                    }).fadeIn();

                    self.$loader.stop(true).hide();
                });
            };
            img.src = self.album[current].url;
            self.currentImageIndex = current;
            self.preloadNeighboringImages();
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
            $body.removeClass('noscroll');
        }
    }
    $(function() {
        // new Lightbox();
    })
})(window.Zepto || window.jQuery, window);
