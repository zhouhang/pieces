<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="css/style.css" />
</head>

<body>

    <!-- header start -->
    <div class="header">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">饮片B2B首页</a>
            </div>
            <div class="title">
                <h1>欢迎注册</h1>
            </div>
        </div>
    </div><!-- header end -->


    <!-- register start -->
    <div class="reg-box">
        <div class="wrap">
            <div class="fa-form">
                <form action="/register" id="myform">
                    <div class="group">
                        <div class="txt">
                            <i>*</i>用户名：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="userName" id="username" placeholder="6-20位，以字母开头，数字结尾的组合组成">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>密码：
                        </div>
                        <div class="cnt">
                            <input type="password" class="ipt" value="" autocomplete="off" name="password" id="pwd" placeholder="由数字、字母或下划线组成，长度为6-20">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>确认密码：
                        </div>
                        <div class="cnt">
                            <input type="password" class="ipt" value="" autocomplete="off" name="pwdRepeat" id="pwdRepeat" placeholder="确认您输入的密码">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业全称：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="companyFullName" id="companyName" placeholder="工商局注册的企业名称">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业注册地：
                        </div>
                        <div class="cnt">
                            <select name="provinceCode" id="province">
                                <option value="">-省-</option>
                            </select>
                            <select name="cityCode" id="city">
                                <option value="">-市-</option>
                            </select>
                            <select name="countyCode" id="area">
                                <option value="">-区/县-</option>
                            </select>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>联系人姓名：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="contactName" id="linkMan" placeholder="企业联系人姓名">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>联系人手机号码：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="contactMobile" id="mobile" placeholder="企业联系人的手机号码">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>验证码：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="mobileCode" id="mobileCode" placeholder="手机短信收到的验证码">
                            <button type="button" class="btn btn-gray btn-inside" id="getMobileCode">获取验证码</button>
                        </div>
                    </div>

                    <div class="group agreement">
                        <div class="cnt">
                            <label><input type="checkbox" class="cbx" name="agreement" id="agreement">我已阅读并同意<a href="#">《药优优用户注册协议》</a></label>                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide" id="submit">立即注册</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="side">
                <div class="hd">
                   已有账号<a class="btn btn-gray" href="login.html">请登录</a> 
                </div>
                <div class="bd">
                    <dl>
                        <dt>平台流程提示：</dt>
                        <dd>注册平台账户</dd>
                        <dd>提交企业资质</dd>
                        <dd>在线选购商品</dd>
                        <dd>提交询价清单</dd>
                        <dd>选择合适报价</dd>
                        <dd>签订框架合同</dd>
                        <dd>等待平台发货</dd>
                        <dd>验收无误收货</dd>
                        <dd>网上对账付款</dd>
                        <dd><a href="helper.html">查看平台详细教程</a></dd>
                    </dl>
                </div>
                <div class="ft">
                    <p>在线咨询：</p>
                    <p><i class="fa fa-tel"></i><span>400-123-1234</span></p>
                </div>
            </div>
        </div>       
    </div><!-- register end -->


    <!-- footer start -->
    <div class="footer">
        <div class="wrap">
            <div class="links">
                <a href="#">关于药优优</a>
                <i>|</i>
                <a href="#">联系我们</a>
                <i>|</i>
                <a href="#">法律申明</a>
                <i>|</i>
                <a href="#">建议与投诉</a>
                <i>|</i>
                <a href="#">友情链接</a>
                <i>|</i>
                <a href="#">站长统计</a>
            </div>
            <div class="copyright">
                <p> 电信与信息服务业务经营许可证号：皖B20140001  备案号：皖ICP备13006003号  互联网药品交易服务资格证：皖B20130001  互联网药品信息服务资格证：（皖）-经营性-2016-0001</p>
                <p>网站商务合作邮箱：bd@copy;yaoyy.com  客户服务企业邮箱：service@copy;yaoyy.com  Copyright &copy; 2015 – 2020 药优优 All Rights Reserved</p>
            </div>
        </div>
    </div><!-- footer end -->

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <script src="js/area.js"></script>
    <script>
        $(function() {
            var icons = {
                error: '<i class="fa fa-prompt"></i>'
            };
            // 注册验证
            $('#myform').validate({
                rules: {
                	userName: {
                        required: true,
                        // remote: 'check.php', // 使用ajax方法调用check.php验证输入值
                        isUsername: true,
                        rangelength: [6,20],
                        remote: {
                                    type:"POST",
                                    url:"/ifExistUserName",
                                    dataType:'json',
                                    data:{
                                    	userName: function() {return $("#username").val();}
                                    }
                                }
                    },
                    password: {
                        required: true,
                        rangelength: [6,20],
                        isPwd: true
                    },
                    pwdRepeat: {
                        required: true,
                        equalTo: '#pwd'
                    },
                    companyFullName: {
                        required: true
                    },
                    countyCode: {
                        required: true
                    },
                    contactName: {
                        required: true
                    },
                    contactMobile: {
                        required: true,
                        isMobile: true,
                        remote: {
                                    type:"POST",
                                    url:"/ifExistMobile",
                                    dataType:'json',
                                    data:{
                                    	contactMobile: function() {return $("#mobile").val();}
                                    }
                                }
                    },
                    mobileCode: {
                        required: true
                    },
                    agreement: {
                        required: true
                    }
                },
                messages: {
                	userName: {
                        required: icons.error + '用户名必须以英文字母开头，长度6到20位',
                        rangelength: icons.error + '用户名长度只能在6-20位字符之间',
                        remote: icons.error + "用户名重复"
                    },
                    password: {
                        required: icons.error + '请输入密码',
                        rangelength: icons.error + '密码由数字、字母或下划线组成，长度为6-20位',
                    },
                    pwdRepeat: {
                        required: icons.error + '请再重复输入一遍密码，不能留空',
                        equalTo: icons.error + '确认新密码与新密码不一致',
                    },
                    companyFullName: {
                        required: icons.error + '请输入企业名称'
                    },
                    countyCode: {
                        required: icons.error + '请选择企业注册地'
                    },
                    contactName: {
                        required: icons.error + '请输入联系人姓名'
                    },
                    contactMobile: {
                        required: icons.error + '请输入手机号码' ,
                        remote:  icons.error + "手机号重复"              
                    },
                    mobileCode: {
                        required: icons.error + '请输入短信验证码'
                    },
                    agreement: {
                        required: icons.error + '请同意协议并勾选'  
                    }
                },
                onfocusout: function(element) { $(element).valid(); },
                errorPlacement: function(error, element) {  
                    element.parent().append(error);
                },
                errorElement: 'span',
                submitHandler: function(form) {
                	form.submit();
                }
            });
            
            $('#submit').on('click', function() {
                if($('#myform').valid()){
                	$.ajax({
                		type : "POST",
            			url : "/register",
            			data : {
            				mobileCode:$('#mobileCode').val(),
            				user : $("myform").serialize()
          				  },
            			dataType : "json",
            			success : function(data){
            				var result = data.result; 
          					var resultMessage = data.resultMessage;
          					if(result != "ok"){
          						_showMsg($('#mobileCode'), resultMessage);
          					}else{
          						window.location = "/login?userName="+ $('#username').val() + "&password="+ $('#pwd').val();
          					}
            			}
                	});
                }else{
                	return false;
                }
            })

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
            	$.ajax({
            		type : "POST",
        			url : "/getMobileCode",
        			data : {
        				contactMobile:$('#mobile').val()
      				  },
        			dataType : "json",
        			success : function(data){
        				var result = data.result; 
      					var resultMessage = data.resultMessage;
      					if(result != "ok"){
      						_showMsg($('#mobileCode'), resultMessage);
      					}
        			}
            	});
            	
                if($mobile.valid() && timeout === 0) {
                    timeout = delay;
                    $getMobileCode.text(timeout + txt).prop('disabled', true).prev().focus();
                    _clock();
                }
            });

        })
    </script>
</body>
</html>