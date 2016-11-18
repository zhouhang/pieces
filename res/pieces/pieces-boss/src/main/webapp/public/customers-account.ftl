<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>账户信息-boss-上工好药</title>
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
                        <a  href="/user/certify/${user.id}">企业资质</a>
                    </dd>
                </dl>
            </div>
            <div class="main">
                <form action="/user/save" id="myform" method="post">
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="title">
                        <h3><i class="fa fa-people"></i>${(user.userName)!}</h3>
                        <div class="extra">
                            <input type="hidden" value="${(user.userName)!}" name="userName" id="userName">
                            <button type="button" class="btn btn-gray" onclick="javascript:history.go(-1);">返回</button>
                            <button type="submit" class="btn btn-red">保存</button>
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
                                    <span class="val">${(user.userName)!}</span>
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
                                    <label><input class="cbx" name="random" id="random" type="checkbox">或发送随机密码</label>
                                </div>
                            </div>

                            <div class="group">
                                <div class="txt">
                                    <i>*</i>您的密码：
                                </div>
                                <div class="cnt">
                                    <input type="password" class="ipt" value="" autocomplete="off" name="memberPwd" id="memberPwd" placeholder="请输入当前操作人的boss帐号密码">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="user-info">
                        <h3>绑定代理商</h3>

                        <div class="fa-form">
                            <div class="group">
                                <div class="txt">
                                    代理商ID：
                                </div>
                                <div class="cnt">
                                    <input type="text" class="ipt" value="${userBind.agentId?default('')}" autocomplete="off" name="agentId" id="agentId" placeholder="">
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

    <script src="js/jquery.min.js"></script>
    <script src="js/validform.min.js"></script>


<script>
    $(function() {
        $(function() {
            var formValidate = $("#myform").Validform({
                    ajaxPost:true,
                    callback:function(data){
                    if(data.status=="y"){
                        location.href="/user/index?advices="+data.info
                    }else{
                        $("#error_advices span").html(data.info);
                        $("#error_advices").show();
                }
            }
        });

            formValidate.addRule([
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
                },
                {
                    ele: '#memberPwd',
                    datatype: '*',
                    nullmsg: '请输入当前操作人的boss帐号密码'
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

        })

    })
</script>
</body>
</html>