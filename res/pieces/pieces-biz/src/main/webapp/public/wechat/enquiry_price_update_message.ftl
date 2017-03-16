<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>修改销售价-上工好药</title>
</head>

<body>
<section class="ui-content">
    <div class="ui-message">
        <div class="ico ico-right"></div>
        <h3 class="hd">保存成功</h3>
        <div class="bd">您可以将修改后的价格分享给您的朋友</div>
    </div>
    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="share"><i class="ico ico-share"></i> 立即分享</button>
        <a href="/h5/enquiry/detail?billId=${billId!}" class="ubtn ubtn-white">返回询价单</a>
    </div>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    var weixinShare = {
        appId: '${signature.appid!}',
        title: '中药饮片报价《上工好药》',
        desc: '上工好药——中药饮片采购首选 - 正品底价、品质保障、配送及时、轻松采购！',
        link: '${baseUrl}/quote?ids=${ids}',
        imgUrl: "${baseUrl}/images/favicon.ico",
        timestamp: ${signature.timestamp?string("#")},
        nonceStr: '${signature.noncestr!}',
        signature: '${signature.signature!}'
    }
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="${urls.getForLookupPath('/h5-static/js/weixin_share.js')}"></script>
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