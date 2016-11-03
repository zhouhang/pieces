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
<footer class="ui-footer">
    <nav class="ui-nav">
        <ul>
            <li>
                <a href="index.html">
                    <i class="fa fa-home"></i>
                    <span>首页</span>
                    <b>12</b>
                </a>
            </li>
            <li>
                <a href="product_list.html">
                    <i class="fa fa-list"></i>
                    <span>品种列表</span>
                    <b>2</b>
                </a>
            </li>
            <li>
                <a href="center.html">
                    <i class="fa fa-cart"></i>
                    <span>采购单</span>
                    <b>99</b>
                </a>
            </li>
            <li>
                <a class="current" href="center.html">
                    <i class="fa fa-user"></i>
                    <span>个人中心</span>
                </a>
            </li>
        </ul>
    </nav>
</footer>

<div class="ui-content">
    <div class="uinfo">
        <div class="inner mid">
            <img class="avatar" src="assets/images/avatar.png" alt="">
            <span class="myname">${nickname!}</span>
        </div>
    </div>
    <div class="umenu">
        <a href="pick_list.html">
            <i class="fa fa-cart"></i>
            <span>我的选货单</span>
        </a>
        <a href="sample_list.html">
            <i class="fa fa-order"></i>
            <span>我的寄样单</span>
        </a>
        <a href="/user/findPassword">
            <i class="fa fa-lock"></i>
            <span>修改密码</span>
        </a>
    </div>
</div>

</body>
</html>