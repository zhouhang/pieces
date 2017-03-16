<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>报价-上工好药</title>
</head>

<body>
<section class="ui-content">
    <div class="ui-notice">
        <b>*</b><#if bill.expireDate?exists>本次报价仅于 ${bill.expireDate?date}前有效。<#else > 还没有报价</#if><br>
        <b>*</b>所有价格都是公斤价。
    </div>

    <div class="pdetail">
        <ul>
        <#list bill.enquiryCommoditys as commodity>
            <li>
                <div class="hd">${commodity.commodityName!}</div>
                <div class="bd">${commodity.specs!}${commodity.level!}</div>
                <div class="price">
                    <span>销售价:<em>￥${commodity.myPrice?default(0)}</em></span>
                    <span>开票价:￥${commodity.price?default(commodity.myPrice?default(0))}</span>
                </div>
                <div class="pic rs-pic">
                    <img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>"/>
                </div>
                <div class="cbx mid"><input type="checkbox" value="${commodity.id!}" class="ico ico-rad"/></div>
            </li>
        </#list>
        </ul>
    </div>
<#if bill.expireDate?exists>
    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="submit"><i class="ico ico-edit"></i> 修改开票价</button>
        <button type="button" class="ubtn ubtn-white" id="share"><i class="ico ico-share2"></i> 分享报价</button>
    </div>
</#if>

</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    var weixinShare = {
        appId: '${signature.appid!}',
        title: '中药饮片报价《上工好药》',
        desc: '上工好药——中药饮片采购首选 - 正品底价、品质保障、配送及时、轻松采购！',
        link: '',
        imgUrl: "${baseUrl}/images/favicon.ico",
        timestamp: ${signature.timestamp!},
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
                _YYY.share.init(''); // 分享
                this.share();
                this.update();
            },
            update:function() {
                $("#submit").click(function(){
                    var commodityStr = [];
                    var $cbxs = $('.pdetail').find('.cbx input:not(:disabled)')

                    $cbxs.each(function() {
                        this.checked && commodityStr.push(this.value);
                    })

                    if (commodityStr.length === 0) {
                        popover('请先选择商品');
                        return false;
                    }
                    window.location.href = "/h5/enquiry/updatePrice?billId=${bill.id}&ids=" + commodityStr.join(',');
                })
            },
            share: function () {
                var commodityStr = [];
                var $cbxs = $('.pdetail').find('.cbx input:not(:disabled)')
                // 单选
                $cbxs.on('click', function() {
                    commodityStr = [];
                    $cbxs.each(function () {
                        this.checked && commodityStr.push(this.value);
                    })
                    weixinShare.link = "${baseUrl}/quote?ids=" + commodityStr.join(',');
                    initWxShareV1();
                })

               var $model = $('#jwxShare');
                $("#share").click(function() {
                    commodityStr = [];
                    $cbxs.each(function () {
                        this.checked && commodityStr.push(this.value);
                    })
                    if (commodityStr.length === 0) {
                        popover('请先选择商品');
                        return false;
                    }
                    $model.show();
                    // window.location.href = "/quote?ids=" +commodityStr.join(',');
                    weixinShare.link = "${baseUrl}/quote?ids=" + commodityStr.join(',');
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>