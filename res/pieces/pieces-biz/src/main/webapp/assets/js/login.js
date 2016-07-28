var loginPage = {
        v: {
        	$username : $('#username'),
            $pwd : $('#pwd'),
            $msg : $('#msg span'),
        	$myform : $('#myform')
        },
        fn: {
            init: function() {
            	this.bindEvent();
            },
            // 错误提示
            showMsg: function(txt) {
                if (!txt) {
                	loginPage.v.$msg.parent().removeClass('vis');
                } else {
                	loginPage.v.$msg.html(txt).parent().addClass('vis');
                }
            },
            checkUsername: function() {
                var val = loginPage.v.$username.val();
                var txt = '';
                if (!val) {
                	loginPage.v.$username.closest('.group').addClass('error');
                    txt = '请输入用户名';
                } else {
                	loginPage.v.$username.closest('.group').removeClass('error');
                }
                this.showMsg(txt);
                return txt;
            },
            checkPassword: function() {
                var val = loginPage.v.$pwd.val();
                var txt = '';
                if (!val) {
                	loginPage.v.$pwd.closest('.group').addClass('error');
                    txt = '请输入密码';
                } else {
                	loginPage.v.$pwd.closest('.group').removeClass('error');
                }
                this.showMsg(txt);
                return txt;
            },
            checkForm: function() {
                var c2 = this.checkPassword();
                var c1 = this.checkUsername();

                if (c2 || c1) {
                    this.showMsg(c1 && c2 ? '请输入用户名和密码' : c1 + c2);
                    return false;
                }
                this.showMsg();
                return true;
            },
            bindEvent: function() {
                var self = this;
                loginPage.v.$username.on('blur', function() {
                    self.checkUsername();
                });

                loginPage.v.$pwd.on('blur', function() {
                    self.checkPassword();
                });
            }
        }
    }



$(function() {
	loginPage.fn.init();
})