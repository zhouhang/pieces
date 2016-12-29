<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>用户资料-上工好药</title>
</head>

<body>

    <#include "./inc/header-center.ftl"/>
<!-- member-box start -->
    <!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
        <#include "./inc/side-center.ftl"/>

            <div class="main">
                <div class="title">
                    <h3>注册资料</h3>
                    <div class="extra"></div>
                </div>
                <div class="mybill mybill-extra">
                    <#if user.type==1>

                        <#if user.certifyStatus==0 >
                            <div class="state">
                            <i class="fa fa-prompt"></i>
                        <#if cerfiy==0>
                            <span>您的企业资质正在审核中，审核结果将以短信的形式发送给您，如有问题请致电0558-5120088。</span>
                        <#elseif cerfiy==2>
                            <span>您的公司信息审核失败</span>
                            <em>不通过原因：${reason!}</em>
                            <a class="btn btn-red" href="/center/certificate/stepOne">重新提交资质审核</a></span>
                        <#elseif cerfiy==-1>
                            <span>您尚未提交企业资质审核，通过审核后方可进行在线下单。<a class="btn btn-red" href="/center/certificate/stepOne">提交资格审核</a></span>
                        </#if>
                        </div>
                        </#if>


                    </#if>

                    <div class="info">
                        <div class="hd">账号资料</div>
                        <div class="bd">
                            <table>
                                <tr>

                                    <td><em>用户名：</em>**${user.userName?substring(user.userName?length-3)}
                                        <#if user.certifyStatus==1>
                                        <strong class="ok"><i class="fa fa-check-circle"></i>已通过企业资质审核</strong>
                                        </#if>
                                    </td>
                                </tr>
                                <tr>
                                    <td><em>密码：</em>****** <a href="user/pwd/update" class="link">[修改密码]</a></td>
                                </tr>
                            </table>
                        </div>
                    </div>

                    <div class="info">
                        <div class="hd">企业资料</div>
                        <div class="bd">
                            <table>
                               <#if user.type==1>
                                <tr class="item">
                                    <td><em>企业名称：</em>${userCertification.company!''}</td>
                                </tr>
                                <tr class="item">
                                    <td><em>企业类型：</em>${userCertification.typeText!''}</td>
                                </tr>
                               </#if>
                                <tr class="item">
                                    <td><em>联系人姓名：</em>*${user.contactName?substring(user.contactName?length-1) }</td>
                                </tr>
                                <tr class="item">
                                    <td><em>手机号：</em>*******${user.contactMobile?substring(user.contactMobile?length-4) }</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="txtinfo">
                <h3>注册资料</h3>
                <div class="item">
                    <label>用户名：</label>
                    <span>***nky</span>
                </div>
                <div class="item">
                    <label>密码：</label>
                    <span>******</span>
                </div>
            </div>

        </div>
    </div><!-- member-box end --><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
</body>
</html>