<!DOCTYPE html>
<html lang="en">
<head>
	<#include "./inc/meta.ftl"/>
    <title>找回密码-上工好药</title>
</head>

<body>


	<!-- header start -->
	<div class="header header-shadow">
		<div class="wrap">
			<div class="logo">
				<a href="/">上工好药首页</a>
			</div>
			<div class="title">
				<h1>找回密码</h1>
			</div>
		</div>
	</div>
	<!-- header end -->


    <!-- find password start -->
    <div class="reg-box">
        <div class="wrap">
            <div class="fa-form">
                <ul class="fa-guide">
                    <li>
                        <span>1</span>
                        <strong>填写账户名</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li>
                        <span>2</span>
                        <strong>验证身份</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li class="curr">
                        <span>3</span>
                        <strong>设置新密码</strong>
                        <i class="fa fa-chevron-right"></i>
                    </li>
                    <li>
                        <span>4</span>
                        <strong>完成</strong>
                    </li>
                </ul>
                <form action="" id="myform">

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
                            <button type="submit" class="btn btn-red btn-wide">提交修改</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="side">
                <div class="hd">
                    已有账号<a class="btn btn-gray" href="/user/login">请登录</a>
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
                        <dd>
                            <a href="/help/15">查看平台详细教程</a>
                        </dd>
                    </dl>
                </div>
                <div class="ft">
                    <p>在线咨询：</p>
                    <p>
                        <i class="fa fa-tel"></i><span>0558-5120088</span>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <!-- find password end -->


	<!-- footer start -->
	<#include "./inc/footer.ftl"/>
    <!-- footer end -->

	<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
	<script src="/js/jquery.form.js"></script>
    <script>
        var _global = {
            v: {},
            fn: {
                init: function() {
                    this.formValidate();
                },
                formValidate: function() {
                    $('#myform').validator({
                        fields: {
                            pwd: {
                                rule: 'required, pwd',
                                msg: {
                                    required: "请输入新密码",
                                    pwd: "密码由数字、字母或下划线组成，长度为6-20位",
                                }
                            },
                            pwdRepeat: {
                                rule: 'required, match(pwd)',
                                msg: {
                                    required: '请再次输入新密码',
                                    match: '确认新密码与新密码不一致'
                                }
                            }
                        },
                        valid: function (form) {
                            myfromValid = this;
                            if ($(form).isValid()) {
                                $.ajax({
                                    url: "/user/findpwd/stepthree",
                                    data: $(form).formSerialize(),
                                    type: "POST",
                                    success: function (data) {
                                        var status = data.status;
                                        var info = data.info;
                                        if (status == 'y') {
                                            window.location.href = '/user/findpwd/success';
                                        }else {
                                            myfromValid.showMsg("#pwdRepeat", {
                                                type: "error",
                                                msg: info
                                            })
                                        }
                                    }
                                });
                                return false;
                            }
                        }
                    })
                }
            }
        }
        $(function() {
            _global.fn.init();
        })
    </script>
</body>
</html>