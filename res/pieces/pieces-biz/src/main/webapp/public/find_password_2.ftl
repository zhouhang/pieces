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
                <form action="/findPasswordTwo" id="myform">
                    <div class="group">
                        <ul class="fa-guide">
                            <li>
                                <span>1</span>
                                <strong>验证手机</strong>
                                <i class="fa fa-chevron-right"></i>
                            </li>
                            <li class="curr">
                                <span>2</span>
                                <strong>设置新密码</strong>
                            </li>
                        </ul>
                    </div>
					<input type="hidden" value="${userName!''}" name="userName">
                    <div class="group">
                        <div class="txt">
                            <i>*</i>新密码：
                        </div>
                        <div class="cnt">
                            <input type="password" class="ipt" value="" autocomplete="off" name="pwd" id="pwd" placeholder="请输入新密码">                            
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>确认新密码：
                        </div>
                        <div class="cnt">
                            <input type="password" class="ipt" value="" autocomplete="off" name="pwdRepeat" id="pwdRepeat" placeholder="请再次输入新密码">                            
                        </div>
                    </div>

                    <div class="ft">
                        <div class="cnt">
                            <button type="submit" class="btn btn-red btn-wide" id="submit">提交修改</button>
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

            var formValidate = $("#myform").Validform({
                ajaxPost: true,
                url: '/user/findpwd/steptwo',
                callback: function(data){
    				var status = data.status; 
    				var info = data.info;
    				if(status == 'y'){
    					window.location.href = "/user/findpwd/success";
    				}
                }
            });

            formValidate.addRule([
                {
                    ele: '#pwd',
                    datatype: 'pwd',
                    nullmsg: '请输入新密码',
                    errormsg: '密码由数字、字母或下划线组成，长度为6-20位'
                },
                {
                    ele: '#pwdRepeat',
                    datatype: '*',
                    recheck: 'pwd',
                    nullmsg: '请再次输入新密码',
                    errormsg: '确认新密码与新密码不一致'
                }
            ])


        })
    </script>
</body>
</html>