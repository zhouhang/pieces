<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户资料-饮片B2B</title>
    <meta name="renderer" content="webkit" />
    <link rel="stylesheet" href="/css/style.css" />
</head>

<body>

    <!-- site-nav end -->
    <div class="site-nav">
        <div class="wrap">
            <p class="fl">欢迎光临药优优饮片采购平台！</p>
            <ul class="fr">
                <li>${user.userName!'' }  <a href="logout.html">退出</a></li>
                <li>|</li>
                <li><a href="logout.html">我的供应链</a></li>
                <li>|</li>
                <li><a href="helper.html">帮助中心</a></li>
                <li>|</li>
                <li><a href="sitemap.html">网站导航 <i class="fa fa-chevron-down"></i></a></li>
            </ul>
        </div>
    </div><!-- site-nav end -->


    <!-- header start -->
    <div class="header header-red">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">饮片B2B首页</a>
            </div>
            <div class="title">
                <h1>我的供应链</h1>
            </div>
            <div class="plus">
                <a class="back" href="home.html"><i class="fa fa-chevron-left"></i> 返回商城首页</a>
            </div>
        </div>
    </div><!-- header end -->


    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <div class="side">
                <dl>
                    <dt>
                        <em class="fa fa-question-circle"></em>
                        <span>询价</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl>
                    <dt>
                        <em class="fa fa-menu"></em>
                        <span>订单</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl>
                    <dt>
                        <em class="fa fa-clock"></em>
                        <span>交货周期</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl>
                    <dt>
                        <em class="fa fa-invoice"></em>
                        <span>发票</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl>
                    <dt>
                        <em class="fa fa-bill"></em>
                        <span>对账单</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl>
                    <dt>
                        <em class="fa fa-heart"></em>
                        <span>收藏</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                </dl>
                <dl class="expand">
                    <dt>
                        <em class="fa fa-setting"></em>
                        <span>设置</span>
                        <i class="fa fa-chevron-right"></i>
                    </dt>
                    <dd>
                        <a href="/user/info">注册资料</a>
                        <a class="curr" href="/user/pwd/update">修改密码</a>
                    </dd>
                </dl>
            </div>

            <div class="main">
                <div class="tips">
                    <i class="fa fa-check-circle"></i>修改成功
                </div>
                <div class="title">
                    <h3>修改密码</h3>
                    <div class="extra"></div>
                </div>
                <div class="fa-form">
                    <form action="" id="myform">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>用户名：
                            </div>
                            <div class="cnt">
                                <span class="val">${user.userName!'' }</span>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>原始密码：
                            </div>
                            <div class="cnt">
                                <input type="password" class="ipt" value="" autocomplete="off" name="pwdOld" id="pwdOld" placeholder="请输入原始密码">                            
                            </div>
                        </div>

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
                                <input type="password" class="ipt" value="" autocomplete="off" name="pwdRepeat" id="pwdRepeat" placeholder="请再次输入的密码">                            
                            </div>
                        </div>

                        <div class="ft">
                            <div class="cnt">
                                <button type="submit" class="btn btn-red btn-wide" id="submit">提交修改</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->


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
    <script src="/js/member.js"></script>
    <script>
        $(function() {
        	var _showMsg = function($element, msg) {
				$element.siblings(".Validform_checktip").attr('class', 'Validform_checktip Validform_wrong').html(msg);
        	}
        	
            var formValidate = $("#myform").Validform({
                ajaxPost: true,
                postonce: true,
                url: '/user/pwd/update',
                callback: function(data){
    				var status = data.status; 
    				var info = data.info;
    				_showMsg($('#pwdRepeat'), info);
                }
            });

            formValidate.addRule([
                {
                    ele: '#pwdOld',
                    datatype: '*',
                    nullmsg: '请输入原始密码'
                },
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
                    nullmsg: '请再重复输入一遍密码，不能留空',
                    errormsg: '确认新密码与新密码不一致'
                }
            ])
        })
    </script>
</body>
</html>