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
        	$.post("/login",
  				  {
  					userName:$username.val(),
  				    password:$pwd.val(),
  				  },
  				  function(data,status){
  					if(data != "ok"){
  						_showMsg("用户名密码错误!");
  					}else{
  						window.location = "/user_info";
  					}
  				  });
        }else{
        	return false;
        }
    })
})