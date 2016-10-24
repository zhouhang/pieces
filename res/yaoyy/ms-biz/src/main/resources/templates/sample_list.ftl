<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>寄样列表-药优优</title>
</head>
<body class="ui-body-nofoot body-gray">
<header class="ui-header">
    <div class="title">寄样列表</div>
    <div class="abs-l mid">
        <a href="javascript:history.back();" class="fa fa-back"></a>
    </div>
</header><!-- /ui-header -->

<section class="ui-content">
    <div class="ui-search">
        <form action="">
            <button type="submit" id="submit" class="fa fa-search submit mid"></button>
            <input type="text" name="keyword" id="keyword" class="ipt" placeholder="请输入原药材品种名称" autocomplete="off">
        </form>
    </div>
    <div class="slist">
        <ul>
            <li>
                <a href="sample_detail.html">
                    <div class="cnt">
                        <div class="title">天麻</div>
                        <div class="summary">
                            安徽天麻  无硫  统货  2015版药典标准 长期供应
                        </div>
                        <div class="attr">
                            <span>产地：安徽</span>
                            <span>规格：统货</span>
                        </div>
                    </div>
                    <div class="pic">
                        <img src="uploads/p5.jpg" width="110" height="90" alt="">
                    </div>
                </a>
                <div class="ft">
                    <span class="status-5">状态：已寄样</span>
                    <button type="button" class="btn mid">确认收货</button>
                </div>
            </li>
            <li>
                <a href="sample_detail.html">
                    <div class="cnt">
                        <div class="title">天麻</div>
                        <div class="summary">
                            安徽天麻  无硫  统货  2015版药典标准 长期供应
                        </div>
                        <div class="attr">
                            <span>产地：安徽</span>
                            <span>规格：统货</span>
                        </div>
                    </div>
                    <div class="pic">
                        <img src="uploads/p5.jpg" width="110" height="90" alt="">
                    </div>
                </a>
                <div class="ft">
                    <span class="status-3">状态：拒绝寄样</span>
                </div>
            </li>
        </ul>
    </div>

</section><!-- /ui-content -->

<#include  "./common/footer.ftl"/>
</body>
</html>