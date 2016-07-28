<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>用户资料-饮片B2B</title>
</head>

<body>

    <#include "./inc/header-center.ftl"/>
<!-- member-box start -->
    <div class="member-box">
        <div class="wrap">
            <#include "./inc/side-center.ftl"/>
            <div class="main">
                <div class="title">
                    <h3>注册资料</h3>
                    <div class="extra"></div>
                </div>
                <div class="fa-form">
                    <div class="group">
                        <div class="txt">
                            <i>*</i>公司名：
                        </div>
                        <div class="cnt">
                            <span class="val">${user.companyFullName!''}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>企业注册地：
                        </div>
                        <div class="cnt">
                            <span class="val">${ user.areaFull!''}</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>联系人：
                        </div>
                        <div class="cnt">
                            <span class="val">${user.contactName!'' }</span>
                        </div>
                    </div>

                    <div class="group">
                        <div class="txt">
                            <i>*</i>手机号：                        
                        </div>
                        <div class="cnt">
                            <span class="val">${user.contactMobile!''}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- member-box end -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
</body>
</html>