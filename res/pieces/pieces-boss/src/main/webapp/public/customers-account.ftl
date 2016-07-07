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
            <div class="side">
                <dl>
                    <dt>客户信息</dt>
                    <dd>
                        <a href="user/index">客户界面</a>
                        <a class="curr" href="user/info/${user.id}">账户信息</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="user/save" id="userForm" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="title">
                        <h3><i class="fa fa-people"></i>${user.userName}</h3>
                        <div class="extra">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button type="reset" class="btn btn-gray">重置</button>
                            <button id="userFormSubmit" type="button" class="btn btn-red">保存</button>
                        </div>
                    </div>

                    <div class="user-info">
                        <h3>账户信息</h3>
                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    <i>*</i>企业全称：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.companyFullName)!}" autocomplete="off" name="companyFullName" id="companyFullName" placeholder="">
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>企业注册地：
                                </div>
                                <div class="cnt">
                                    <select name="province" id="province">
                                        <option value="">-省-</option>
                                    </select>
                                    <select name="city" id="city">
                                        <option value="">-市-</option>
                                    </select>
                                    <select name="area" id="area">
                                        <option value="">-区/县-</option>
                                    </select>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>联系人姓名：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.contactName)!}" autocomplete="off" name="contactName" id="contactName" placeholder="">
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>联系人手机号码：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${(user.contactMobile)!}" autocomplete="off" name="contactMobile" id="contactMobile" placeholder="">
                                </div>
                            </div>

                        </div>
                    </div>

                    <div class="user-info">
                        <h3>密码管理</h3>

                        <div class="fa-form">

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>新密码：
                                </div>
                                <div class="cnt">
                                    <input type="password" class="ipt" value="" autocomplete="off" name="password" id="password" placeholder="请输入新密码">
                                </div>
                            </div>

                            <div class="group">
                                <div class="cnt-extra">
                                    <label><input class="cbx" id="contactMobileCode" type="checkbox">或发送随机密码</label>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>您的密码：
                                </div>
                                <div class="cnt">
                                    <input type="password" class="ipt" value="" autocomplete="off" name="memberPassword" id="memberPassword" placeholder="请输入当前操作人的boss帐号密码">
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
    <script src="/js/validform.min.js"></script>
    <script src="/js/area.js"></script>

</body>

<script>
    $(function() {
        var formValidate = $("#userform").Validform();

        formValidate.addRule([
            {
                ele: '#companyFullName',
                datatype: 's',
                nullmsg: '用户名必须以英文字母开头，长度6到20位',
                errormsg: '用户名长度只能在6-20位字符之间'
            },
            {
                ele: '#area',
                datatype: 's',
                nullmsg: '请选择企业注册地'
            },
            {
                ele: '#contactName',
                datatype: 's',
                nullmsg: '请输入联系人姓名'
            },
            {
                ele: '#contactMobile',
                datatype: 'm',
                nullmsg: '请输入手机号码',
                errormsg: '请输入正确的手机号码'
            },
            {
                ele: '#password',
                datatype: 'password',
                nullmsg: '请输入密码',
                errormsg: '密码由数字、字母或下划线组成，长度为6-20位'
            },
            {
                ele: '#memberPassword',
                datatype: '*',
                nullmsg: '请输入当前操作人的boss帐号密码'
            }
        ])

        var $contactMobileCode = $('#contactMobileCode');
        var $password = $('#password');
        var _setpassword = function() {
            var flag = $contactMobileCode.prop('checked');
            if (flag) {
                formValidate.ignore($password);
                $password.nextAll('.Validform_checktip').removeClass('Validform_wrong').html('');
            } else {
                formValidate.unignore($password);
            }
            $password.prop('disabled', flag);
        }
        $contactMobileCode.on('click', _setpassword);
        _setpassword();


        /**
         * 提交表单
         */
        $("#userFormSubmit").click(function(){
            $("#userForm").ajaxSubmit({
                dataType: "json",
                success: function (result) {

                }
            });

        })


    })
</script>
</html>