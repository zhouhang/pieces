<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>成功找回密码-饮片B2B</title>
</head>

<body>

    <!-- header start -->
    <div class="header">
        <div class="wrap">
            <div class="logo">
                <a href="home.html">饮片B2B首页</a>
            </div>
            <div class="title">
                <h1>找回密码</h1>
            </div>
        </div>
    </div><!-- header end -->


    <!-- fa-message start -->
    <div class="fa-message">
        <div class="wrap">
            <div class="hd">
                <i class="fa fa-check-circle"></i>
                <strong>恭喜您，您已成功设置新密码！</strong>
            </div>
            <div class="bd">
                <p>—— 您可以立即使用新密码登录 ——</p>
            </div>
            <div class="ft">
                <a class="btn btn-red" href="/user/login">立即登录</a>
            </div>
            <div class="links">
                或 <a href="/">返回首页</a>
            </div>
        </div>
    </div><!-- fa-message start -->


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->

</body>
</html>