<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>企业资质认证-上工好药</title>
</head>

<body>
<#include "./inc/header-center.ftl"/>


<!-- member-box start -->
<div class="member-box">
    <div class="wrap">
       <#include "./inc/side-center.ftl"/>
        <div class="main">
            <div class="title">
                <h3>企业资质认证</h3>
                <div class="extra"></div>
            </div>

            <!-- fa-message start -->
            <div class="fa-message">
                <div class="hd">
                    <i class="fa fa-check-circle"></i>
                    <strong>恭喜您，您已成功提交企业认证资料！</strong>
                </div>
                <div class="bd">
                    <p>审核需要一点时间，您可以先进行询价操作。</p>
                </div>
                <div class="ft">
                    <a class="btn btn-red" href="certificate.html">开始询价</a>
                </div>
            </div><!-- fa-message start -->

        </div>
    </div>
</div><!-- member-box end -->

<#include "./inc/footer.ftl"/>
<script src="js/common.js"></script>
<script>
    var _global = {
        v: {
        },
        fn: {
            init: function() {
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
</script>
</body>
</html>
