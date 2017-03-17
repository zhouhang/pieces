var loginPage = {
    fn: {
        init: function() {
            this.bindEvent();
        },
        // 错误提示
        showMsg: function(msg) {
            var $msg = $('#msg');
            if (!msg) {
                $msg.removeClass('vis');
            } else {
                $msg.html('<i class="fa fa-prompt"></i>' + msg).addClass('vis');
            }
        },
        checkUsername: function() {
            var $username = $('#username'),
                val = $username.val(),
                msg = '';
            if (!val) {
                $username.closest('.group').addClass('error');
                msg = '请输入账户名';
            } else {
                $username.closest('.group').removeClass('error');
            }
            this.showMsg(msg);
            return msg;
        },
        checkPassword: function() {
            var $pwd = $('#pwd'),
                val = $pwd.val(),
                msg = '';
            if (!val) {
                $pwd.closest('.group').addClass('error');
                msg = '请输入密码';
            } else {
                $pwd.closest('.group').removeClass('error');
            }
            this.showMsg(msg);
            return msg;
        },
        checkForm: function() {
            var c2 = this.checkPassword();
            var c1 = this.checkUsername();

            if (c2 || c1) {
                this.showMsg(c1 && c2 ? '请输入账户名和密码' : c1 + c2);
                return false;
            }
            this.showMsg();
            return true;
        },
        bindEvent: function() {
            var self = this;
            $('#myform').find('.ipt').on('blur', function() {
                $(this).closest('.group').removeClass('error');
                self.showMsg('');
            })
        }
    }
}

$(function() {
    loginPage.fn.init();
})