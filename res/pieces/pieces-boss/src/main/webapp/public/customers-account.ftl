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
                                    <select name="province" id="province"  data-value="${userArea.provinceId}">
                                        <option value="">-省-</option>
                                    </select>
                                    <select name="city" id="city" data-value="${userArea.cityId}">
                                        <option value="">-市-</option>
                                    </select>
                                    <select name="areaId" id="area" data-value="${userArea.id}">
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
                                   新密码：
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
    <script src="js/validform.min.js"></script>
    <script src="js/area.js"></script>


<script>
    $(function() {
        var formValidate = $("#userForm").Validform({
        	btnSubmit: '#userFormSubmit',
            ajaxPost: true,
            postonce: true,
            url: '/user/save',
            callback: function(data){
            	if(data.status=="y"){
                    location.href="user/index?advices="+data.info
                }else{
                    $("#error_advices").show();
                }
            }
        });
        

        formValidate.addRule([
            {
                ele: '#companyFullName',
                datatype: /^([a-zA-Z0-9_\(\)-]|[\u4e00-\u9fa5]|[（）]){4,50}$/,
                nullmsg: '用药单位的名称',
                errormsg: '用药单位名称长度4-50位，不能包含特殊字符'
            },
            {
                ele: '#area',
                datatype: '*',
                nullmsg: '请选择所在地区'
            },
            {
                ele: '#contactName',
                datatype: /^([a-zA-Z]|[\u4e00-\u9fa5]){2,50}$/,
                nullmsg: '请输入联系人姓名',
                errormsg: '联系人姓名长度2-50位，只能包括中文字、英文字母'
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


    })
</script>
</body>
</html>