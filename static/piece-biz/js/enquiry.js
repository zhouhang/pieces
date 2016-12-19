!(function() {
	var vm  = new Vue({
		el: '#app',
		data: {
			idx: 1,
			items: [
				{idx: 1, val1: '', val2: '', val3: '', e1: false}
			],
			files: [{filename: 'aa'}],
			verification: 'uploads/code.jpg', // 验证码,
			contact: {
				name: '',
				mobile: '',
				verify: ''
			},
			check: {
				name: false,
				mobile: false,
				verify: false,
				mobileText: '请填写联系人电话'
			}
		},
		methods: {
			addRow: function() {
				this.items.push({idx: ++this.idx, val1: '', val2: '', val3: '', e1: false});
			},
			delRow: function(index) {
				for (var i in this.items) {
		            if (this.items[i].idx == index) {
		            	this.items.splice(i, 1);
		            	break;
		            }
		        }
			},
			// 验证码
			changeVcode: function() {
				this.verification = 'https://authcode.jd.com/verify/image?a=0&acid=a&srcid=reg&yys=' + (new Date).getTime();
			},
			submitForm: function() {
				var self = this,
					pass = true;

				if (this.files.length === 0) {
					for (var i in this.items) {
						var item = this.items[i];
						item.e1 = item.val1.length === 0; 
						if (item.e1) {
							pass = false;
							var $tag = $('.ipt[name=name' + item.idx + ']');
							window.scrollTo(0, $tag.offset().top - 100);
							$tag.focus();
							break;
						}
					}
				}
				this.checkName();
				this.checkMobile();
				this.checkVerify();

				// 表单验证失败
				if (!pass || this.check.name || this.check.mobile || this.check.verify) {
					return;
				}
				$.ajax({
					url: '',
					type: 'POST',
					data: {
						list: self.items,
						contact: self.contact,
						files: self.files
					},
					success: function(result) {
						if(result.status=='y'){
	                        $.notify({
	                            type: 'success',
	                            title: '提交成功',
	                            text: result.info
	                        })
	                    }else{
	                        $.notify({
	                            type: 'error',
	                            title: '提交错误',
	                            text: result.info
	                        })
	                    }
					}
				})
			},
			checkName: function() {
				this.check.name = this.contact.name == '';
			},
			checkMobile: function() {
				if (this.contact.mobile == '') {
					this.check.mobile = true;
					this.check.mobileText = '请填写联系人电话';
				} else if (!/^1[3-9]\d{9}$/.test(this.contact.mobile)) {
					this.check.mobile = true;
					this.check.mobileText = '手机号码格式错误';
				} else {
					this.check.mobile = false;
				}
			},
			checkVerify: function() {
				this.check.verify = this.contact.verify == '';

			},
			toInt: function(item) {
				var val = item.val3
                if (!/^\d*$/.test(val)) {
                    val = Math.abs(parseInt(val));
                    item.val3 = isNaN(val) ? '' : val;
                }
			},
			hideMsg: function(item) {
				if (this.files.length > 0) {
					item.e1 = false;
				} else {
					item.e1 = item.val1.length === 0; 
				}
			},
			hideAllMsg: function() {
				for (var i in this.items) {
					this.items[i].e1 = false;
				}
			},
			// 批量询价
			batch: function() {
				var self = this;
				layer.open({
                    moveType: 1,
                    area: ['600px'],
					title: '批量询价',
					content: '<form action="" id="excelForm" method="post" enctype="multipart/form-data"><p>您可以使用我们提供的 <a class="c-blue" href="http://www.sghaoyao.com/file/%E6%89%B9%E9%87%8F%E9%87%87%E8%B4%AD%E6%A8%A1%E7%89%88.xls">询价单模版<i class="fa fa-download"></i></a>，也可以使用您自己的询价单文件（可以是Excel 或照片）。</p><label class="btn btn-file enquiry_btn"><span>上传文件</span><input type="file" name="file"></label><label class="filename"></label></form>',
					btn: ['确定', '取消'],
					yes: function(index) {
						$("#excelForm").ajaxForm({
	                        url:"",
	                        success: function(result) {
	                        	self.files.push({filename: result.filename});
	                        	self.hideAllMsg();
	                        }
	                    });
						$("#excelForm").submit();
						layer.close(index);
					},
					end: function() {
						$('.enquiry_btn').off();
					}
				})
				$('.enquiry_btn').on('change', 'input', function() {
					$('.filename').html($(this).val());
				})
			},
			// 删除附件
			delAttach: function(filename) {
				for (var i in this.files) {
					if (this.files[i].filename == filename) {
						this.files.splice(i, 1);
		            	break;
					}
				}
			}
		}
	})
})(Vue);