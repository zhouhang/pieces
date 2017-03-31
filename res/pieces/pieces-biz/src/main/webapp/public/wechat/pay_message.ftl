<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>支付成功-上工好药</title>
</head>
<body>

<section class="ui-content">
    <div class="ui-message">
        <div class="ico ico-right"></div>
        <h3 class="hd">支付成功</h3>
        <div class="bd">款项确认无误后，平台将尽快给您发货</div>
    </div>
    <div class="ui-button">
        <a href="/h5c/order/list" class="ubtn ubtn-red" id="pay">返回我的订单</a>
    </div>
</section><!-- /ui-content -->

<#include "wechat/inc/footer_h5.ftl"/>
<script>
!(function($) {
    var _global = {
        init: function() {
        }
    }
    _global.init();
})(window.Zepto || window.jQuery);
</script>
</body>
</html>