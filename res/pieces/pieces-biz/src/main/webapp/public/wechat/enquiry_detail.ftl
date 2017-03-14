<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>报价-上工好药</title>
</head>

<body>
<section class="ui-content">
    <div class="ui-notice">
        <b>*</b>本次报价仅于 2017-01-17 23:59:00 前有效。<br>
        <b>*</b>所有价格都是公斤价。
    </div>

    <div class="pdetail">
        <ul>
            <li>
                <div class="hd">黄芪</div>
                <div class="bd">圆片、厚2-3mm、直径0.6cm-1.8cm以上、无空心片、异形片、黑片 ...</div>
                <div class="price">
                    <span>销售价:<em>￥90.00</em></span>
                    <span>开票价:￥95.00</span>
                </div>
                <div class="pic rs-pic">
                    <img src="uploads/p1.jpg" />
                </div>
                <div class="cbx mid"><input type="checkbox" class="ico ico-rad" /></div>
            </li>
            <li>
                <div class="hd">黄芪</div>
                <div class="bd">圆片、厚2-3mm、直径0.6cm-1.8cm以上、无空心片、异形片、黑片 ...</div>
                <div class="price">
                    <span>销售价:<em>￥111190.00</em></span>
                    <span>开票价:￥111195.00</span>
                </div>
                <div class="pic rs-pic">
                    <img src="uploads/p1.jpg" />
                </div>
                <div class="cbx mid"><input type="checkbox" class="ico ico-rad" /></div>
            </li>
            <li>
                <div class="hd">黄芪</div>
                <div class="bd">圆片、厚2-3mm、直径0.6cm-1.8cm以上、无空心片、异形片、黑片 ...</div>
                <div class="price">
                    <span>销售价:<em>￥90.00</em></span>
                    <span>开票价:￥95.00</span>
                </div>
                <div class="pic rs-pic">
                    <img src="uploads/p1.jpg" />
                </div>
                <div class="cbx mid"><input type="checkbox" class="ico ico-rad" /></div>
            </li>
            <li>
                <div class="hd">黄芪</div>
                <div class="bd">圆片、厚2-3mm、直径0.6cm-1.8cm以上、无空心片、异形片、黑片 ...</div>
                <div class="price">
                    <span>销售价:<em>￥90.00</em></span>
                    <span>开票价:￥95.00</span>
                </div>
                <div class="pic rs-pic">
                    <img src="uploads/p1.jpg" />
                </div>
                <div class="cbx mid"><input type="checkbox" class="ico ico-rad" /></div>
            </li>
        </ul>
    </div>

    <div class="ui-button">
        <a href="enquiry_price.html" class="ubtn ubtn-red" id="submit"><i class="ico ico-edit"></i> 修改开票价</a>
        <button type="button" class="ubtn ubtn-white" id="share"><i class="ico ico-share2"></i> 分享报价</button>
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