<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>消息提示-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-message start -->
    <div class="fa-message">
        <div class="wrap">
            <div class="hd">
                <i class="fa fa-prompt"></i>
                <strong>您没有访问权限！</strong>
            </div>
            <div class="bd">
                <p>—— 您可以立即使用新密码登录 ——</p>
            </div>
            <div class="ft">
                <a class="btn btn-red" href="logout">立即登录</a>
            </div>
            <div class="links">
                或 <a href="/">返回首页</a>
            </div>
        </div>
    </div><!-- fa-message start -->

    <#include "./inc/footer.ftl"/>


</body>
</html>