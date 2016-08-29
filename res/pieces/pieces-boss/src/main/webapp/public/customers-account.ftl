<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>账户信息-boss-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-floor start -->
    <div class="fa-floor">


        <div class="wrap">


            <div  style="display: none" id="error_advices" class="message">
                <i class="fa fa-times-circle"></i>
                <span>修改失败！</span>
            </div>

            <div class="side">
                <dl>
                    <dt>客户信息</dt>
                    <dd>
                        <a href="/user/info/${user.id}">客户界面</a>
                        <a class="curr" href="/user/edit/${user.id}">账户信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="user/save" id="userForm" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="title">
                        <h3><i class="fa fa-people"></i><input type="hidden" value="${(user.userName)!}" name="userName" id="userName">${user.userName}</h3>
                        <div class="extra">
                            <a  class="btn btn-gray" href="/user/index">返回</a>
                            <button id="userFormSubmit" type="submit" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>账户信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>用药单位：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.companyFullName)!}" autocomplete="off" name="companyFullName" id="companyFullName" placeholder="用药单位名称"
                                    data-msg-required="请输入用药单位名称"
								    data-msg-company="用药单位名称长度4-50位，不能包含特殊字符">
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>所在地区：
                                </div>
                                <div class="cnt">
                                    <select name="province" id="province"  data-value="${userArea.provinceId}">
                                        <option value="">-省-</option>
                                    </select>
                                    <select name="city" id="city" data-value="${userArea.cityId}">
                                        <option value="">-市-</option>
                                    </select>
                                    <select name="areaId" id="area" data-value="${userArea.id}" data-msg-required="请选择至最后一级">
                                        <option value="">-区/县-</option>
                                    </select>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>联系人姓名：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.contactName)!}" autocomplete="off" name="contactName" id="contactName" placeholder="联系人姓名"
                                    data-msg-required="请输入联系人姓名"
									data-msg-nickName="联系人姓名长度2-50位，只能包括中文字、英文字母">
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>联系人手机号码：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.contactMobile)!}" autocomplete="off" name="contactMobile" id="contactMobile" placeholder="联系人手机号码"
                                    data-msg-required="请输入联系人手机号码"
									data-msg-mobile="请输入正确的手机号码">
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="user-info">
                        <h3>密码管理</h3>

                        <div class="fa-form">

                            <div class="group">
                                <div class="txt">
                                   新密码：
                                </div>
                                <div class="cnt">
                                    <input type="password" class="ipt" value="" autocomplete="off" name="password" id="password" placeholder="请输入新密码"
                                    data-msg-required="请输入密码"
									data-msg-password="密码由数字、字母或下划线组成，长度为6-20位">
                                </div>
                            </div>

                            <div class="group">
                                <div class="cnt-extra">
                                    <label><input class="cbx" id="random" name="random" value="true" type="checkbox">或发送随机密码</label>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- fa-floor end -->
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
	<script src="/js/validator/jquery.validator.js?local=zh-CN"></script>
    <script src="js/area.js"></script>


<script>
    $(function() {
        var $myform = $('#userForm');

        $myform.validator({
			fields : {
				password : '密码: required; password',
				companyFullName : '用药单位: required, company',
				areaId : '所在地区: required',
				contactName : '联系人: required;nickName',
				contactMobile : '手机号码: required;mobile',
			},
		    valid: function(form) {
		    	var myfromValid = this;
		    	if ( $(form).isValid() ) {
			    	$.ajax({
			            url: "/user/save",
			            data: $(form).formSerialize(),
			            type: "POST",
			            success: function(data){
			            	if(data.status=="y"){
			                    location.href="user/index?advices="+data.info
			                }else{
			                    $("#error_advices").show();
			                }
			            }
			        });
		    	} 
			}
		});

        var $contactMobileCode = $('#random');
        var $password = $('#password');
        var _setpassword = function() {
            var flag = $contactMobileCode.prop('checked');
            if (flag) {
                $myform.data('validator').options.ignore = '#password';
                $password.removeClass('n-invalid').nextAll('.msg-box').html('');
            } else {
                $myform.data('validator').options.ignore = '';
            }
            $password.prop('disabled', flag);
        }
        $contactMobileCode.on('click', _setpassword);
        setTimeout(_setpassword, 200);
        //_setpassword();


    })
</script>
</body>
</html>