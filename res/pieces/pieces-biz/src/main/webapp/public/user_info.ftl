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
                    <div class="state">
                        <i class="fa fa-prompt"></i>
                        <#if user.certifyStatus=0 >
                        <#if cerfiy==0>
                            <span>您的公司信息正在审核中，通过审核后方可进行询价、下单等操作，如需帮助请拨打(86)400-8830-393</span>
                        <#elseif cerfiy==2>
                            <span>您的公司信息审核失败<a class="btn btn-red" href="/center/certificate/stepOne">继续提交资格审核</a></span>
                        <#elseif cerfiy==-1>
                            <span>您的公司信息尚未提交审核，通过审核后方可进行询价、下单等操作<a class="btn btn-red" href="/center/certificate/stepOne">提交资格审核</a></span>
                        </#if>
                            <#else>
                                <span>您的公司信息审核通过</span>
                        </#if>

                    </div>
                    </#if>

                    <div class="info">
                        <div class="hd">账号资料</div>
                        <div class="bd">
                            <table>
                                <tr>
                                    <td><em>用户名：</em>${user.contactName!'' }
                                        <#if user.certifyStatus=1>
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
                                <tr class="item">
                                    <td><em>企业名称：</em>${userCertification.company!''}</td>
                                </tr>
                                <tr class="item">
                                    <td><em>企业类型：</em>${userCertification.typeText!''}</td>
                                </tr>
                                <tr class="item">
                                    <td><em>联系人姓名：</em>${user.contactName!'' }</td>
                                </tr>
                                <tr class="item">
                                    <td><em>手机号：</em>${user.contactMobile!''}</td>
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

            <div class="txtinfo">
                <h3>企业资料</h3>
                <div class="item">
                    <label>企业名称：</label>
                    <span>武汉市同济医院</span>
                </div>
                <div class="item">
                    <label>企业类型：</label>
                    <span>公立医院</span>
                </div>
                <div class="item">
                    <label>联系人姓名：</label>
                    <span>*欢</span>
                </div>
                <div class="item">
                    <label>手机号：</label>
                    <span>*******5487</span>
                </div>
            </div>
        </div>
    </div><!-- member-box end --><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
</body>
</html>