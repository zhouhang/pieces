<!DOCTYPE html>
<html lang="en">
<head>
	<#include "./inc/meta.ftl"/>
    <title>登录-饮片B2B</title>
</head>
<body>
<div class="login-box login-mini">
    <div class="form">
        <form action="" id="myform">
            <div class="msg" id="msg">
                <i class="fa fa-prompt"></i>
                <span>用户名与密码不匹配</span>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="fa fa-people"></i>
                </div>
                <div class="cnt">
                	<input type="hidden" value="${url }" id="url" name="url">
                    <input type="text" class="ipt" value="" autocomplete="off" name="username" id="username" placeholder="用户名">
                </div>
            </div>

            <div class="group">
                <div class="txt">
                    <i class="fa fa-lock"></i>
                </div>
                <div class="cnt">
                    <input type="password" class="ipt" value="" autocomplete="off" name="pwd" id="pwd" placeholder="密码">
                </div>
            </div>

            <div class="links cf">
                <a class="fl" href="/user/register" target="_top">免费注册</a>
                <a class="fr" href="/user/findpwd/stepone" target="_top">忘记密码？</a>
            </div>

            <div class="button">
                <button type="button" class="btn btn-red" id="submit">登 录</button>
            </div>
        </form>
    </div>
</div>
<#include "./inc/footer.ftl"/>
    <script>
    $(function() {
        var $username = $('#username'),
            $pwd = $('#pwd'),
            $submit = $('#submit'),
            $msg = $('#msg span'),
        	$myform = $('#myform');

        var _showMsg = function(txt) {
            if (!txt) {
                $msg.parent().removeClass('vis');
            } else {
                $msg.html(txt).parent().addClass('vis');
            }
        }

        var _checkUsername = function() {
            var val = $username.val();
            var txt = '';
            if (!val) {
                $username.closest('.group').addClass('error');
                txt = '请输入用户名';
            } else {
                $username.closest('.group').removeClass('error');
            }
            _showMsg(txt);
            return txt;
        }
        var _checkPassword = function() {
            var val = $pwd.val();
            var txt = '';
            if (!val) {
                $pwd.closest('.group').addClass('error');
                txt = '请输入密码';
            } else {
                $pwd.closest('.group').removeClass('error');
            }
            _showMsg(txt);
            return txt;
        }

        var _checkForm = function() {
            var c2 = _checkPassword();
            var c1 = _checkUsername();

            if (c2 || c1) {
                _showMsg(c1 && c2 ? '请输入用户名和密码' : c1 + c2);
                return false;
            }
            _showMsg();
            return true;
        }

        $username.on('blur', _checkUsername);

        $pwd.on('blur', _checkPassword);

        $submit.on('click', function() {
            if(_checkForm()){
            	$.ajax({
            		type : "POST",
        			url : "/user/login",
        			data : {
        				userName:$username.val(),
      				    password:$pwd.val()
      				  },
        			dataType : "json",
        			success : function(data){
        				var status = data.status; 
        				if(status != "y"){
      						_showMsg("用户名密码错误!");
      					}else{
      						window.parent.productPage.fn.logined();
      					}
        			}
            	});
            }else{
            	return false;
            }
        })
    })
    </script>
</body>
</html>