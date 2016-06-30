var messages = {
	error: '<i class="fa fa-prompt"></i>',
	username: {
        required: this.error + '用户名必须以英文字母开头，长度6到20位',
        rangelength: this.error + '用户名长度只能在6-20位字符之间'
    },
    pwd: {
        required: this.error + '请输入密码',
        rangelength: this.error + '密码由数字、字母或下划线组成，长度为6-20位',
    },
    pwdOld: {
        required: this.error + '请输入当前操作人的boss帐号密码',
        equalTo: this.error + '请输入当前操作人的boss帐号密码',
    },
    companyName: {
        required: this.error + '请输入企业名称'
    },
    area: {
        required: this.error + '请选择企业注册地'
    },
    linkMan: {
        required: this.error + '请输入联系人姓名'
    },
    mobile: {
        required: this.error + '请输入手机号码'               
    },
    mobileCode: {
        required: this.error + '请输入短信验证码'
    },
    agreement: {
        required: this.error + '请同意协议并勾选'  
    }
}

$.fn.valid = function(options) {
	this.messages = $.extend({}, messages, options.messages);
	var $el = $(this);
}