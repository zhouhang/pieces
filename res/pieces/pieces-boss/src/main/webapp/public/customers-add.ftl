<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>新增账户-boss-饮片B2B</title>
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
                    <a href="customers.html">客户界面</a>
                    <a class="curr" href="customers-account.html">账户信息</a>
                </dd>
            </dl>
        </div>
        <div class="main">


            <form action="user/add" id="userForm" method="post">
                <div class="title">
                    <h3><i class="fa fa-people"></i>hehuan</h3>
                    <div class="extra">
                        <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                        <button type="reset" class="btn btn-gray">重置</button>
                        <button id="userFormSubmit"  type="button" class="btn btn-red">保存</button>
                    </div>
                </div>
                <div class="user-info">
                    <h3>账户信息</h3>
                    <div class="fa-form">
                        <div class="group">
                            <div class="txt">
                                <i>*</i>会员名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="userName" id="userName" placeholder="用户名">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>企业全称：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="companyFullName" id="companyFullName" placeholder="企业全称">
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
                                <select name="areaId" id="area" >
                                    <option value="">-区/县-</option>
                                </select>
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>联系人姓名：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="contactName" id="contactName" placeholder="联系人姓名">
                            </div>
                        </div>

                        <div class="group">
                            <div class="txt">
                                <i>*</i>联系人手机号码：
                            </div>
                            <div class="cnt">
                                <input type="text" class="ipt" value="" autocomplete="off" name="contactMobile" id="contactMobile" placeholder="联系人手机号码">
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
                                <label><input class="cbx" id="random"  name="random" value="true" type="checkbox">或发送随机密码</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- fa-floor end -->
</div>


<!-- footer start -->
<#include "./inc/footer.ftl"/>
<!-- footer end -->

<script src="js/validform.min.js"></script>
<script src="js/area.js"></script>

<script>
    $(function() {
        var formValidate = $("#userForm").Validform({
            btnSubmit: '#userFormSubmit'
        });
        formValidate.addRule([
            {
                ele: '#userName',
                datatype: 'uname',
                ajaxurl: 'user/username/check',
                nullmsg: '请输入会员名',
                errormsg: '会员名必须以英文字母开头，长度6到20位'
            },
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
                datatype: 'pwd',
                nullmsg: '请输入密码',
                errormsg: '密码由数字、字母或下划线组成，长度为6-20位'
            }
        ])



        var $mobileCode = $('#random');
        var $pwd = $('#password');
        var _setPwd = function() {
            var flag = $mobileCode.prop('checked');
            if (flag) {
                formValidate.ignore($pwd);
                $pwd.nextAll('.Validform_checktip').removeClass('Validform_wrong').html('');
            } else {
                formValidate.unignore($pwd);
            }
            $pwd.prop('disabled', flag);
        }
        $mobileCode.on('click', _setPwd);
        _setPwd();

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
</body>
</html>