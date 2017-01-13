!(function(window, document) {
	var imgEyecandyOpacity = 0.5,
		initialZoom = 40,
		zoomFactor = 10,
		rotateFactor = 5;

	// DEFAULT OPTIONS
	var defaults = {
		loadPicture: '',
		hideButton: false,
		uploadUrl: '',
		uploadData: {},
		cropUrl: '',
		cropData: {},
		customUploadButtonId: '',
		loaderHtml: '<span class="loader">图片上传中...</span>',
		scaleToFill: true // 无限放大
	};

	var isAjaxUploadSupported = (function() {
		var input = document.createElement("input");
		input.type = "file";
		return (
			"multiple" in input &&
			typeof File != "undefined" &&
			typeof FormData != "undefined" &&
			typeof(new XMLHttpRequest()).upload != "undefined");
	})();

	window.Croppic = function(id, options) {
		this.id = id;
		this.options = $.extend({}, defaults, options || {});
		this.obj = $('#' + id);
		this.objW = this.obj.width();
		this.objH = this.obj.height();
		this.init();
	};

	Croppic.prototype = {
		init: function() {
			var that = this;
			that.wait = false;
			that.actualRotation = 0;
			that.imgInitW = 0;
			that.imgInitH = 0;
			that.imgW = 0;
			that.imgH = 0;
			if (that.options.customUploadButtonId) {
				that.imgUploadControl = $('#' + that.options.customUploadButtonId);
			} else {
				that.obj.append('<div class="cropControls"><i class="cropControlUpload"></i></div>');
				that.imgUploadControl = that.obj.find('.cropControlUpload');
			}
			if (that.options.loadPicture) {
				that.loadExistingImage();
			} else {
				that.bindImgUploadControl();
			}
		},
		loadExistingImage: function() {
			var that = this;
			var img = new Image();
			that.onBeforeImgUpload('<span class="loader">图片加载中...</span>');
			img.onload = function() {
				that.imgUrl = that.options.loadPicture;
				that.imgInitW = that.imgW = this.width;
				that.imgInitH = that.imgH = this.height;
				that.initCropper();
				that.hideLoader();
				that.onAfterImgUpload({
					url: that.imgUrl
				});
			}
			img.onerror = function() {
				that.hideLoader();
				that.bindImgUploadControl();
			}
			img.src = that.options.loadPicture;
		},
		bindImgUploadControl: function() {
			var that = this;
			if (isAjaxUploadSupported) {
				that.createForm();
			} else {
				that.CreateFallbackIframe();
			}

			that.imgUploadControl.on('click', function() {
				if (that.wait) {
					return;
				}
				if (isAjaxUploadSupported) {
					that.form.find('input[type="file"]').trigger('click');
				} else {
					that.iframeform.find('input[type="file"]').trigger('click');
				}
			});
		},
		createForm: function() {
			var that = this;
			var formHtml = '<form class="' + that.id + '_imgUploadForm" style="visibility:hidden;"> \n <input type="file" name="img"> \n </form>';
			that.obj.append(formHtml);
			that.form = that.obj.find('.' + that.id + '_imgUploadForm');
			that.form.on('change', 'input[type="file"]', function() {
				that.onBeforeImgUpload();
				try {
					// other modern browsers
					formData = new FormData(that.form[0]);
				} catch (e) {
					// IE10 MUST have all form items appended as individual form key / value pairs
					formData = new FormData();
					formData.append('img', that.form.find("input[type=file]")[0].files[0]);
				}
				for (var key in that.options.uploadData) {
					if (that.options.uploadData.hasOwnProperty(key)) {
						formData.append(key, that.options.uploadData[key]);
					}
				}
				$.ajax({
					url: that.options.uploadUrl,
					data: formData,
					context: document.body,
					cache: false,
					contentType: false,
					processData: false,
					type: 'POST'
				}).always(function(data) {
					that.afterUpload(data);
					that.form.html('<input type="file" name="img">');
				});
			});
		},
		CreateFallbackIframe: function() {
			var that = this,
				iframeID = this.id + '_upload_iframe',
				iframe = document.createElement('iframe'),
				myContent = '<!DOCTYPE html><html><head><title>Uploading File</title></head><body><form name="upload_form" action="' + that.options.uploadUrl + '" method="post" enctype="multipart/form-data" encoding="multipart/form-data"> \n <input type="file" name="img"> \n </form></body></html>';

			$('#' + iframeID).remove();
			iframe.id = iframeID;
			iframe.width = 0;
			iframe.height = 0;
			iframe.border = 0;
			iframe.src = 'javascript:false;';
			iframe.style.display = 'none';

			document.body.appendChild(iframe);

			iframe.contentWindow.document.open('text/htmlreplace');
			iframe.contentWindow.document.write(myContent);
			iframe.contentWindow.document.close();

			that.iframeobj = $('#' + iframeID);
			that.iframeform = that.iframeobj.contents().find('html').find('form');

			that.iframeform.find('input').on('change', function() {
				that.onBeforeImgUpload();
				that.iframeform[0].submit();
			});

			iframe.onload = function() {
				var response = that.getIframeContentJSON(iframe);
				iframe.onload = null;
				that.afterUpload(response);
				that.CreateFallbackIframe();
			}
		},
		getIframeContentJSON: function(iframe) {
			var doc, response, innerHTML;
			try {
				doc = iframe.contentDocument ? iframe.contentDocument : iframe.contentWindow.document;
				innerHTML = doc.body.innerHTML;
				if (innerHTML.slice(0, 5).toLowerCase() == "<pre>" && innerHTML.slice(-6).toLowerCase() == "</pre>") {
					innerHTML = doc.body.firstChild.firstChild.nodeValue;
				}
				response = jQuery.parseJSON(innerHTML);
			} catch (err) {
				response = {
					success: false
				};
			}
			return response;
		},
		afterUpload: function(data) {
			var that = this,
				response = typeof data == 'object' ? data : jQuery.parseJSON(data);
			if (response.status == 'success') {
				var img = new Image();
				img.onload = function() {
					that.initCropper();
					that.hideLoader();
					that.onAfterImgUpload(response);
				}
				img.onerror = function() {
					that.hideLoader();
					that.onError('\u56FE\u7247\u4E0A\u4F20\u5931\u8D25');
				}
				img.src = response.url;
				that.imgUrl = response.url;
				that.imgInitW = that.imgW = response.width || img.width;
				that.imgInitH = that.imgH = response.height || img.height;
			}
			if (response.status == 'error') {
				that.hideLoader();
				that.onError(response.message);
			}
		},
		initCropper: function() {
			var that = this;
			if (!that.options.cropUrl) {
				return this;
			}
			that.obj.html('<div class="cropImgWrapper" style="overflow:hidden; z-index:1; position:absolute; width:' + that.objW + 'px; height:' + that.objH + 'px;"><img src="' + that.imgUrl + '" /></div>');

			that.img = that.obj.find('img');
			that.createCropControls();
			that.createEyecandy();
			that.initDrag();
			that.initialScaleImg();
		},
		createCropControls: function() {
			var that = this;
			var control = [];
			control.push('<div class="cropControls cropControlsCrop">');
			control.push('<i class="cropControlZoomMuchIn"></i>');
			control.push('<i class="cropControlZoomIn"></i>');
			control.push('<i class="cropControlZoomOut"></i>');
			control.push('<i class="cropControlZoomMuchOut"></i>');
			control.push('<i class="cropControlRotateLeft"></i>');
			control.push('<i class="cropControlRotateRight"></i>');
			control.push('<i class="cropControlCrop"></i>');
			control.push('<i class="cropControlReset"></i>');
			control.push('</div>');

			that.obj.append(control.join(''));

			that.cropControlsCrop = that.obj.find('.cropControlsCrop');

			that.cropControlsCrop.on('click', '.cropControlZoomMuchIn', function() {
				that.zoom(zoomFactor * 10);
			})
			that.cropControlsCrop.on('click', '.cropControlZoomIn', function() {
				that.zoom(zoomFactor);
			});
			that.cropControlsCrop.on('click', '.cropControlZoomOut', function() {
				that.zoom(-zoomFactor);
			});
			that.cropControlsCrop.on('click', '.cropControlZoomMuchOut', function() {
				that.zoom(-zoomFactor * 10);
			})
			that.cropControlsCrop.on('click', '.cropControlRotateLeft', function() {
				that.rotate(-rotateFactor);
			})
			that.cropControlsCrop.on('click', '.cropControlRotateRight', function() {
				that.rotate(rotateFactor);
			})
			that.cropControlsCrop.on('click', '.cropControlCrop', function() {
				that.crop();
			})
			that.cropControlsCrop.on('click', '.cropControlReset', function() {
				that.reset();
			})
		},
		createEyecandy: function() {
			var that = this;
			that.imgEyecandy = that.img.clone();
			that.imgEyecandy.css({
				'z-index': '0',
				'opacity': imgEyecandyOpacity
			}).appendTo(that.obj);
		},
		initialScaleImg: function() {
			var that = this;
			that.zoom(-that.imgInitW);
			that.zoom(initialZoom);
			that.img.css({
				'left': -(that.imgW - that.objW) / 2,
				'top': -(that.imgH - that.objH) / 2,
				'position': 'relative'
			});
			that.imgEyecandy.css({
				'left': -(that.imgW - that.objW) / 2,
				'top': -(that.imgH - that.objH) / 2,
				'position': 'relative'
			});
		},
		initDrag: function() {
			var that = this;
			that.img.on("mousedown touchstart", function(e) {
				e.preventDefault(); // disable selection

				var pageX = e.pageX,
					pageY = e.pageY,
					z_idx = that.img.css('z-index'),
					drg_h = that.img.outerHeight(),
					drg_w = that.img.outerWidth(),
					pos_y = that.img.offset().top + drg_h - pageY,
					pos_x = that.img.offset().left + drg_w - pageX;

				that.img.css('z-index', 1000).on("mousemove touchmove", function(e) {

					var imgTop = e.pageY + pos_y - drg_h,
						imgLeft = e.pageX + pos_x - drg_w;

					that.img.offset({
						top: imgTop,
						left: imgLeft
					}).on("mouseup", function() {
						$(this).css('z-index', z_idx);
					});

					that.imgEyecandy.offset({
						top: imgTop,
						left: imgLeft
					});

					if (that.objH < that.imgH) {
						if (parseInt(that.img.css('top')) > 0) {
							that.img.css('top', 0);
							that.imgEyecandy.css('top', 0);
						}
						var maxTop = -(that.imgH - that.objH);
						if (parseInt(that.img.css('top')) < maxTop) {
							that.img.css('top', maxTop);
							that.imgEyecandy.css('top', maxTop);
						}
					} else {
						if (parseInt(that.img.css('top')) < 0) {
							that.img.css('top', 0);
							that.imgEyecandy.css('top', 0);
						}
						var maxTop = that.objH - that.imgH;
						if (parseInt(that.img.css('top')) > maxTop) {
							that.img.css('top', maxTop);
							that.imgEyecandy.css('top', maxTop);
						}
					}

					if (that.objW < that.imgW) {
						if (parseInt(that.img.css('left')) > 0) {
							that.img.css('left', 0);
							that.imgEyecandy.css('left', 0);
						}
						var maxLeft = -(that.imgW - that.objW);
						if (parseInt(that.img.css('left')) < maxLeft) {
							that.img.css('left', maxLeft);
							that.imgEyecandy.css('left', maxLeft);
						}
					} else {
						if (parseInt(that.img.css('left')) < 0) {
							that.img.css('left', 0);
							that.imgEyecandy.css('left', 0);
						}
						var maxLeft = (that.objW - that.imgW);
						if (parseInt(that.img.css('left')) > maxLeft) {
							that.img.css('left', maxLeft);
							that.imgEyecandy.css('left', maxLeft);
						}
					}
				});

			}).on("mouseup", function() {
				that.img.off("mousemove");
			}).on("mouseout", function() {
				that.img.off("mousemove");
			});

		},
		rotate: function(x) {
			var that = this;
			that.actualRotation += x;
			that.img.css({
				'-webkit-transform': 'rotate(' + that.actualRotation + 'deg)',
				'-moz-transform': 'rotate(' + that.actualRotation + 'deg)',
				'transform': 'rotate(' + that.actualRotation + 'deg)'
			});
			that.imgEyecandy.css({
				'-webkit-transform': 'rotate(' + that.actualRotation + 'deg)',
				'-moz-transform': 'rotate(' + that.actualRotation + 'deg)',
				'transform': 'rotate(' + that.actualRotation + 'deg)'
			});
		},
		zoom: function(x) {
			var that = this;
			var ratio = that.imgW / that.imgH;
			var newWidth = that.imgW + x;
			var newHeight = newWidth / ratio;
			var doPositioning = true;

			if (newWidth < that.objW || newHeight < that.objH) {

				if (newWidth - that.objW < newHeight - that.objH) {
					newWidth = that.objW;
					newHeight = newWidth / ratio;
				} else {
					newHeight = that.objH;
					newWidth = ratio * newHeight;
				}

				doPositioning = false;

			}

			if (!that.options.scaleToFill && (newWidth > that.imgInitW || newHeight > that.imgInitH)) {
				if (newWidth - that.imgInitW < newHeight - that.imgInitH) {
					newWidth = that.imgInitW;
					newHeight = newWidth / ratio;
				} else {
					newHeight = that.imgInitH;
					newWidth = ratio * newHeight;
				}

				doPositioning = false;

			}

			that.imgW = newWidth;
			that.img.width(newWidth);

			that.imgH = newHeight;
			that.img.height(newHeight);

			var newTop = parseInt(that.img.css('top')) - x / 2;
			var newLeft = parseInt(that.img.css('left')) - x / 2;

			if (newTop > 0) {
				newTop = 0;
			}
			if (newLeft > 0) {
				newLeft = 0;
			}

			var maxTop = -(newHeight - that.objH);
			if (newTop < maxTop) {
				newTop = maxTop;
			}
			var maxLeft = -(newWidth - that.objW);
			if (newLeft < maxLeft) {
				newLeft = maxLeft;
			}

			if (doPositioning) {
				that.img.css({
					'top': newTop,
					'left': newLeft
				});
			}

			that.imgEyecandy.width(newWidth);
			that.imgEyecandy.height(newHeight);

			if (doPositioning) {
				that.imgEyecandy.css({
					'top': newTop,
					'left': newLeft
				});
			}
		},
		crop: function() {
			var that = this;
			that.onBeforeImgCrop();
			that.cropControlsCrop.hide();
			var cropData = {
				imgUrl: that.imgUrl,
				imgInitW: that.imgInitW,
				imgInitH: that.imgInitH,
				imgW: that.imgW,
				imgH: that.imgH,
				imgY1: Math.abs(parseInt(that.img.css('top'))),
				imgX1: Math.abs(parseInt(that.img.css('left'))),
				cropH: that.objH,
				cropW: that.objW,
				rotation: that.actualRotation
			};

			var formData;

			if (typeof FormData == 'undefined') {
				var XHR = new XMLHttpRequest();
				var urlEncodedData = "";
				var urlEncodedDataPairs = [];

				for (var key in cropData) {
					urlEncodedDataPairs.push(encodeURIComponent(key) + '=' + encodeURIComponent(cropData[key]));
				}
				for (var key in that.options.cropData) {
					urlEncodedDataPairs.push(encodeURIComponent(key) + '=' + encodeURIComponent(that.options.cropData[key]));
				}
				urlEncodedData = urlEncodedDataPairs.join('&').replace(/%20/g, '+');

				var eventHandlermyFile = function() {
					that.onError("XHR Request failed");
				}
				if (XHR.addEventListener) {
					XHR.addEventListener("error", eventHandlermyFile, true);
				} else if (XHR.attachEvent) {
					XHR.attachEvent("onerror", eventHandlermyFile);
				}

				XHR.onreadystatechange = function() {
					if (XHR.readyState == 4 && XHR.status == 200) {
						that.afterCrop(XHR.responseText);
					}
				}
				XHR.open('POST', that.options.cropUrl);

				XHR.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
				XHR.setRequestHeader('Content-Length', urlEncodedData.length);

				XHR.send(urlEncodedData);

			} else {
				formData = new FormData();
				for (var key in cropData) {
					if (cropData.hasOwnProperty(key)) {
						formData.append(key, cropData[key]);
					}
				}

				for (var key in that.options.cropData) {
					if (that.options.cropData.hasOwnProperty(key)) {
						formData.append(key, that.options.cropData[key]);
					}
				}

				$.ajax({
					url: that.options.cropUrl,
					data: formData,
					context: document.body,
					cache: false,
					contentType: false,
					processData: false,
					type: 'POST'
				}).always(function(data) {
					that.afterCrop(data);
				});
			}
		},
		afterCrop: function(data) {
			var that = this;
				response = typeof data == 'object' ? data : jQuery.parseJSON(data);

			that.hideLoader();
			that.imgEyecandy.hide();
			that.onAfterImgCrop();
			if (response.status == 'success') {
				that.destroy();
			}
			if (response.status == 'error') {
				that.cropControlsCrop.show();
				that.onError(response.message);
			}
		},
		showLoader: function(txt) {
			var that = this;
			if (that.options.cropUrl) {
				that.obj.append(txt || that.options.loaderHtml);
				that.loader = that.obj.find('.loader');

			} else if (that.options.customUploadButtonId) {
				that.imgUploadControl.html(txt || that.options.loaderHtml);
				that.loader = that.imgUploadControl.find('.loader');
			}
			that.wait = true;
			that.options.hideButton && that.imgUploadControl.hide();
		},
		hideLoader: function() {
			var that = this;
			that.wait = false;
			that.loader && that.loader.remove();
			that.imgUploadControl.show();
		},
		reset: function() {
			var that = this;
			that.destroy();
			that.init();
			that.onReset();
		},
		destroy: function() {
			var that = this;
			that.options.loadPicture = '';
			that.imgUploadControl && that.imgUploadControl.off('click');
			that.loader && that.loader.remove();
			that.img && that.img.off().remove();
			that.imgEyecandy && that.imgEyecandy.remove();
			that.cropControlsCrop && that.cropControlsCrop.off().remove();
			that.iframeobj && that.iframeobj.off().remove();
			that.form && that.form.off().remove();
			that.obj.empty();
		},
		onBeforeImgUpload: function(txt) {
			var that = this;
			if (typeof that.options.onBeforeImgUpload === 'function') {
				that.options.onBeforeImgUpload.call(that);
			}
			that.showLoader(txt);
		},
		onAfterImgUpload: function(response) {
			var that = this;
			if (typeof that.options.onAfterImgUpload === 'function') {
				that.options.onAfterImgUpload.call(that, response);
			}
		},
		onBeforeImgCrop: function() {
			var that = this;
			if (typeof that.options.onBeforeImgCrop === 'function') {
				that.options.onBeforeImgCrop.call(that);
			}
		},
		onAfterImgCrop: function() {
			var that = this;
			if (typeof that.options.onAfterImgCrop === 'function') {
				that.options.onAfterImgCrop.call(that, response);
			}
		},
		onReset: function() {
			var that = this;
			if (typeof that.options.onReset == 'function') {
				that.options.onReset.call(that);
			}
		},
		onError: function(message) {
			var that = this;
			if (typeof that.options.onError === 'function') {
				that.options.onError.call(that, message);
			}
			// that.reset();
		}
	};
})(window, document);