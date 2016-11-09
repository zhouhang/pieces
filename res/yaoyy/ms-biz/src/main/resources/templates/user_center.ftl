<!DOCTYPE html>
<html lang="en">
<head>
    <title>用户中心-药优优</title>
<#include "./common/meta.ftl"/>
</head>
<body class="ui-body body-gray">
<header class="ui-header">
    <div class="title">个人中心</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->
<#include "./common/navigation.ftl">
<div class="ui-content">
    <div class="uinfo">
        <div class="inner mid">
            <img class="avatar" src="assets/images/avatar.png" alt="">
            <span class="myname">${nickname?default("未登入")}</span>
        </div>
    </div>
    <div class="umenu">
        <a href="/pick/list">
            <i class="fa fa-cart"></i>
            <span>我的选货单</span>
        </a>
        <a href="/sample/list">
            <i class="fa fa-order"></i>
            <span>我的寄样单</span>
        </a>
        <a href="/center/updatePassword">
            <i class="fa fa-lock"></i>
            <span>修改密码</span>
        </a>
    </div>
</div>
<#include "./common/footer.ftl"/>
</body>
</html>