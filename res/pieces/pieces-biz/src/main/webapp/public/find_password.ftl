<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>找回密码-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/css/style.css" />
</head>

<body>


    <!-- header start -->
    <div class="header">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">饮片B2B首页</a>
            </div>
            <div class="title">
                <h1>找回密码</h1>
            </div>
        </div>
    </div><!-- header end -->

    
    <!-- find password start -->
    <div class="reg-box">
        <div class="wrap">
            <div class="fa-form">
                <form action="/findPasswordOne" id="myform">
                    <div class="group">
                        <ul class="fa-guide">
                            <li class="curr">
                                <span>1</span>
                                <strong>验证手机</strong>
                                <i class="fa fa-chevron-right"></i>
                            </li>
                            <li>
                                <span>2</span>
                                <strong>设置新密码</strong>
                            </li>
                        </ul>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>用户名：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="username" id="username" placeholder="6-20位，以字母开头，数字结尾的组合组成">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>手机号：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="mobile" id="mobile" placeholder="请填写绑定的手机号">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>验证码：
                        </div>
                        <div class="cnt">
                            <input type="text" class="ipt" value="" autocomplete="off" name="mobileCode" id="mobileCode" placeholder="请填写短信验证码">
                            <button type="button" class="btn btn-gray btn-inside" id="getMobileCode">获取验证码</button>
                        </div>
                    </div>

                    <div class="ft">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide" id="submit">下一步</button>
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
    </div><!-- find password end -->


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

    <script src="/js/jquery.min.js"></script>
    <script src="/js/validform.min.js"></script>
    <script>
        $(function() {

            var _showMsg = function($element, msg) {
                $element.siblings(".Validform_checktip").attr('class', 'Validform_checktip Validform_wrong').html(msg);
            }
            
            var formValidate = $("#myform").Validform({
                ajaxPost: true,
                url: '/user/findpwd/stepone',
                callback: function(data){
                    switch (data.status) {
                        case 'y':
                            window.location.href = '/user/findpwd/steptwo?userName='+$('#username').val();
                            break;
                        case 'falseCode':
                            _showMsg($('#mobileCode'), data.info);
                            break;
                        case 'falseMobile':
                            _showMsg($('#mobile'), data.info);
                            break;
                        case 'falseName':
                            _showMsg($('#username'), data.info);
                            break;
                        // no default
                    }
                }
            });

            formValidate.addRule([
                {
                    ele: '#username',
                    datatype: '*',
                    nullmsg: '请输入用户名'
                },
                {
                    ele: '#mobile',
                    datatype: 'm',
                    nullmsg: '请输入手机号码',
                    errormsg: '请输入正确的手机号码'
                },
                {
                    ele: '#mobileCode',
                    datatype: '*',
                    nullmsg: '请输入短信验证码'
                }
            ])



            var $mobile = $('#mobile'),
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
            var _sendMobileCode = function() {
                $.ajax({
                    type : 'POST',
                    url : '/gen/code',
                    data : {
                        contactMobile: $mobile.val()
                    },
                    dataType : 'json',
                    success : function(data){
                        if (data.status !== 'y') {
                            clearInterval(timer);
                            $getMobileCode.text('获取验证码').prop('disabled', false);
                            _showMsg($('#mobileCode'), data.resultMessage);
                            timeout = 0;
                        } else {
                            timeout = delay;
                            _clock();
                            $getMobileCode.text(timeout + txt).prop('disabled', true);
                        }
                    }
                });
            }

            // 验证码
            $getMobileCode.prop('disabled', false).on('click', function() {
                if(timeout === 0 && formValidate.check(false, $mobile)) {
                    timeout = delay;
                    _sendMobileCode();
                } else {
                    $mobile.focus();
                }
            });


        })
    </script>
</body>
</html>