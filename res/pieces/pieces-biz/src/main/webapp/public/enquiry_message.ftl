<!DOCTYPE html>
<html lang="en">
<head>
<#include "./inc/meta.ftl"/>
    <title>询价成功-${baseSetting.title!}</title>
    <meta name="description" content="${baseSetting.intro!}" />
    <meta name="Keywords" content="${baseSetting.keyWord!}" />
</head>

<body>

<!-- header start -->
<div class="header header-shadow">
    <div class="wrap">
        <div class="logo">
            <a href="/">上工好药首页</a>
        </div>
        <div class="title">
            <h1>询价成功</h1>
        </div>
    </div>
</div><!-- header end -->

<!-- fa-message start -->
<div class="fa-message">
    <div class="wrap">
        <div class="hd">
            <i class="fa fa-check-circle"></i>
            <strong>您的询价提交成功！</strong>
        </div>
        <div class="bd">
            <p>稍后将有专业人员与您联系，为您提供相对精准的报价。</p>
        </div>
        <div class="ft">
            <a class="btn btn-red" href="/">返回首页</a>
        </div>
    </div>
</div><!-- fa-message end -->

<#include "./inc/footer.ftl"/>


</body>
</html>