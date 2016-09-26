<!DOCTYPE html>
<html lang="en">
<head>
	<#include "./inc/meta.ftl"/>
    <title>注册-上工好药</title>
</head>

<body>

	<!-- header start -->
	<div class="header header-shadow">
		<div class="wrap">
			<div class="logo">
				<a href="/">上工好药首页</a>
			</div>
			<div class="title">
				<h1>欢迎注册</h1>
			</div>
		</div>
	</div>
	<!-- header end -->


	<!-- register start -->
	<div class="reg-box">
		<div class="wrap">
			<div class="fa-form">
				<form action="/user/register" id="myform" method="post">
					<div class="group">
						<div class="txt">
							<i>*</i>用户名：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="userName" id="username" placeholder="以字母开头，支持字母、数字，6-20位"
								data-msg-required="请输入用户名">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>密码：
						</div>
						<div class="cnt">
							<input type="password" class="ipt" value="" autocomplete="off"
								name="password" id="pwd" placeholder="支持字母、数字、下划线，6-20位"
								data-msg-required="请输入密码"
								data-msg-password="密码由数字、字母或下划线组成，长度为6-20位">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>确认密码：
						</div>
						<div class="cnt">
							<input type="password" class="ipt" value="" autocomplete="off"
								name="pwdRepeat" id="pwdRepeat" placeholder="确认您输入的密码"
								data-msg-required="请再重复输入一遍密码，不能留空"
								data-msg-match="两次输入的密码不一致">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>用药单位：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="companyFullName" id="companyName" placeholder="用药单位名称"
								data-msg-required="请输入用药单位名称"
								data-msg-company="用药单位名称长度4-50位，不能包含特殊字符">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>所在地区：
						</div>
						<div class="cnt">
							<select name="provinceCode" id="province">
								<option value="">-省-</option>
							</select> <select name="cityCode" id="city">
								<option value="">-市-</option>
							</select> <select name="areaId" id="area" data-msg-required="请选择至最后一级">
								<option value="">-区/县-</option>
							</select>
						</div>
						<input type="hidden" id="areaFull" name="areaFull" value="">
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>联系人姓名：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="contactName" id="linkMan" placeholder="联系人姓名"
								data-msg-required="请输入联系人姓名"
								data-msg-nickName="联系人姓名长度2-50位，只能包括中文字、英文字母">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>联系人手机号码：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="contactMobile" id="mobile" placeholder="联系人的手机号码"
								data-msg-required="请输入联系人手机号码"
								data-msg-mobile="请输入正确的手机号码">
						</div>
					</div>

					<div class="group">
						<div class="txt">
							<i>*</i>验证码：
						</div>
						<div class="cnt">
							<input type="text" class="ipt" value="" autocomplete="off"
								name="mobileCode" id="mobileCode" placeholder="手机短信收到的验证码"
								data-msg-required="请输入正确的验证码">
							<button type="button" class="btn btn-gray btn-inside"
								id="getMobileCode">获取验证码</button>
						</div>
					</div>

					<div class="group agreement">
						<div class="cnt">
							<label><input type="checkbox" class="cbx"
								name="agreement" id="agreement">我已阅读并同意<a href="/help/16" target="_blank">《上工好药用户注册协议》</a></label>
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
						<i class="fa fa-tel"></i><span>400-123-1234</span>
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- register end -->


	<!-- footer start -->
	<#include "./inc/footer.ftl"/>
    <!-- footer end -->

	<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
	<script src="/js/area.js"></script>
	<script src="/js/jquery.form.js"></script>
	<script>
		var roleAddPage = {
			v : {},
			fn : {
				init : function() {
					this.formValidate().sendCode();
				},
				formValidate : function() {
					this.validator = $('#myform').validator({
						fields : {
							userName : '用户名: required;username;remote(/user/checkusername)',
							password : '密码: required; password',
							pwdRepeat : '确认密码: required; match(password)',
							companyFullName : '用药单位: required, company',
							areaId : '所在地区: required',
							contactName : '联系人: required;nickName',
							contactMobile : '手机号码: required;mobile',
							mobileCode : '验证码: required',
							agreement : '同意协议：checked'
						},
					    valid: function(form) {
					    	var myfromValid = this;
					    	if ( $(form).isValid() ) {
					    		$("#areaFull").val($('#province option:selected').text() + $('#city option:selected').text() + $('#area option:selected').text());
						    	$.ajax({
						            url: "/user/register",
						            data: $(form).formSerialize(),
						            type: "POST",
						            success: function(data){
										var status = data.status; 
										var info = "验证码错误，请重新输入";
										if(status == "y"){
											window.location = "/user/regsuccess?userName="+ $('#username').val() + "&password="+ $('#pwd').val();
										}else{
											myfromValid.showMsg("#mobileCode", {
											    type: "error",
											    msg: info
											})
										}
						            }
						        });
					    	} 
						}
					});
					return this;
				},
				sendCode: function() {
					var self = this;
					var $mobile = $('#mobile'), 
					$getMobileCode = $('#getMobileCode'), timeout = 0, timer = 0, delay = 60, txt = '秒后重试';

					var _clock = function() {
						timer && clearInterval(timer);
						timer = setInterval(function() {
							timeout--;
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
								contactMobile : $mobile.val()
							},
							dataType : 'json',
							success : function(data) {
								if (typeof data.ok === 'string') {
								} else if (typeof data.error === 'string') {
									$('#myform').validator('showMsg', '#mobileCode', {
									    type: "error",
									    msg: data.error
									});
								}
							}
						});
                        timeout = delay;
                        _clock();
                        $getMobileCode.text(timeout + txt).prop('disabled',true);
					}

					// 验证码
					$getMobileCode.prop('disabled', false).on('click', function() {
                        $('#myform').validator('hideMsg', '#mobileCode');
						if (timeout === 0 && $('#mobile').isValid()) {
							timeout = delay;
							_sendMobileCode();
						} else {
							$mobile.focus();
						}
					});
					return this;
				}
			}
		}
		$(function() {
			roleAddPage.fn.init();
		})

	</script>
</body>
</html>