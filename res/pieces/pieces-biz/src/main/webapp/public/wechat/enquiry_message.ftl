<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>询价-上工好药</title>
</head>

<body>
<section class="ui-content">
    <div class="ui-message">
        <div class="ico ico-right"></div>
        <h3 class="hd">提交成功</h3>
        <div class="bd">报价信息将在30分钟内发送给您，请注意查收！</div>
    </div>
    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="share"><i class="ico ico-share"></i> 分享到朋友圈</button>
    </div>
</section><!-- /ui-content -->
<#include "./inc/footer.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                _YYY.share.init('#share'); // 分享
            }
        }
        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>