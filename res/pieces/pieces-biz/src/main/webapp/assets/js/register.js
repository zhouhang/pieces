$(function() {
	var icons = {
        error: '<i class="fa fa-prompt"></i>'
    };
	// 注册验证
	$('#myform').validate({
	    rules: {
	    	username: {
	            required: true,
	            // remote: 'check.php', // 使用ajax方法调用check.php验证输入值
	            isUsername: true,
	            rangelength: [6,20]
	    	},
	        pwd: {
	            required: true,
	            rangelength: [6,20]
	        },
	        pwdRepeat: {
	            required: true,
	            equalTo: '#pwd'
	        },
	        companyName: {
	            required: true
	        },
	        area: {
	            required: true
	        },
	        linkMan: {
	            required: true
	        },
	        mobile: {
	            required: true,
	            isMobile: true
	        },
	        mobileCode: {
	            //required: true
	        },
	        agreement: {
	            required: true
	        }
	    },
	    messages: {
	    	username: {
	    		required: icons.error + '用户名必须以英文字母开头，长度6到20位',
	    		rangelength: icons.error + '用户名长度只能在6-20位字符之间'
	    	},
	        pwd: {
	            required: icons.error + '由数字、字母或下划线组成，长度为6-20',
	            rangelength: icons.error + '密码长度只能在6-20位字符之间',
	        },
	        pwdRepeat: {
	            required: icons.error + '请输入确认密码',
	            equalTo: icons.error + '两次输入的密码不一致',
	        },
	        companyName: {
	            required: icons.error + '请输入企业名称'
	        },
	        area: {
	            required: icons.error + '请选择企业注册地'
	        },
	        linkMan: {
	            required: icons.error + '请输入联系人姓名'
	        },
	        mobile: {
	            required: icons.error + '请输入手机号码'	            
	        },
	        mobileCode: {
	            //required: icons.error + '请输入短信验证码'
	        },
	        agreement: {
	        	required: icons.error + '请同意协议并勾选'	
	        }
	    },
	    onkeyup: false,
	    errorPlacement: function(error, element) {  
	    	element.parent().append(error);
		    // error.appendTo(element.parent());  
		},
	    errorElement: 'span',
	    submitHandler: function(form) {
	    	form.submit();
	    }
	});

	var $code = $('#jCode'),
		$mobile = $('#mobile'),
        $getMobileCode = $('#getMobileCode'),
        timeout = 0, 
        timer = 0,
        delay = 60,
        txt = '秒后重试'

    var _clock = function() {
        timer && clearInterval(timer);
        timer = setInterval(function() {
            timeout --;
            $getMobileCode.text(timeout + txt).prop('disabled', true);
            if (timeout === 0) {
                clearInterval(timer);
                $getMobileCode.text('获取验证码').prop('disabled', false);
            }
        }, 1e3);
    }

	// 验证码
	$getMobileCode.on('click', function() {
		if($mobile.valid() && timeout === 0) {
            timeout = delay;
            $getMobileCode.text(timeout + txt).prop('disabled', true).prev().focus();
            _clock();
        }
	});

})