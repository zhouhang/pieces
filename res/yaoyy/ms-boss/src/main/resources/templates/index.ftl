<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>控制面板-药优优</title>
</head>
<body class='wrapper'>
<#include "./common/header.ftl"/>
<#include "./common/aside.ftl"/>
<div class="content">
    <div class="breadcrumb">
        <ul>
            <li>控制面板</li>
        </ul>
    </div>

    <div class="box">
        <ul class="q-list">
            <@shiro.hasPermission name="sample:list">
            <li>
                <div class="cnt bg-aqua">
                    <a href="/sample/list"> <span><i class="fa fa-truck"></i></span> 寄样列表 <sup>4</sup></a>
                </div>
            </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="pick:list">
            <li>
                <div class="cnt bg-green">
                    <a href="/pick/list"><span><i class="fa fa-shopping-bag"></i></span> 选货单列表</a>
                </div>
            </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="commodity:list">
            <li>
                <div class="cnt bg-yellow">
                    <a href="/commodity/list"><span><i class="fa fa-cart-plus"></i></span> 商品列表</a>
                </div>
            </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="special:list">
            <li>
                <div class="cnt bg-red">
                    <a href="special/list"><span><i class="fa fa-star"></i></span> 专场列表</a>
                </div>
            </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="ad:list">
            <li>
                <div class="cnt bg-purple">
                    <a href="ad/list"><span><i class="fa fa-audio-description"></i></span> 广告列表</a>
                </div>
            </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="cms:list">
            <li>
                <div class="cnt bg-maroon">
                    <a href="cms/list"><span><i class="fa fa-file-text"></i></span> CMS列表</a>
                </div>
            </li>
            </@shiro.hasPermission>
        </ul>
    </div>
</div>
<#include "./common/footer.ftl"/>
</body>
</html>