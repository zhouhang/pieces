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
                <div class="hd"><a href="/h5/commodity/${commodity.commodityId!}">${commodity.commodityName!}</a></div>
                <div class="bd">${commodity.specs!}${commodity.level!}</div>
                <div class="price">
                    <span>销售价:<em>${(commodity.myPrice?default(0))?string .currency}</em></span>
                    <#if user?exists && user.type == 2>
                    <span>开票价:${(commodity.price?default(commodity.myPrice?default(0)))?string .currency}</span>
                    </#if>
                </div>
                <div class="pic rs-pic">
                    <a href="/h5/commodity/${commodity.commodityId!}"><img src="<#if commodity.pictureUrl=="" || !(commodity.pictureUrl?exists) >/images/blank.jpg<#else >${commodity.pictureUrl?default('/images/blank.jpg')}</#if>"/></a>
                </div>
                <#if bill.status==0 || !commodity.myPrice?exists || (commodity.myPrice == 0) || !((commodity.expireDate?date gte .now?date) || (commodity.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
                <div class="cbx mid"><input type="checkbox" value="${commodity.id!}" class="ico ico-rad" disabled/></div>
                <#else >
                <div class="cbx mid"><input type="checkbox" data-price="${(commodity.price?default(commodity.myPrice?default(0)))?string .currency}" data-name="${commodity.commodityName!}" value="${commodity.id!}" class="ico ico-rad"/></div>
                </#if>
            </li>
        </#list>
        </ul>
    </div>
<#if bill.expireDate?exists&&((bill.expireDate?date gte .now?date) || (bill.expireDate?string("yyyyMMdd") == .now?string("yyyyMMdd")))>
    <div class="ui-button">
    <#if user?exists && user.type == 2>
        <button type="button" class="ubtn ubtn-red" id="submit"><i class="ico ico-edit"></i> 修改开票价</button>
    </#if>
        <button type="button" class="ubtn ubtn-white" id="share"><i class="ico ico-share2"></i> 分享报价</button>
    </div>
</#if>

</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    var weixinShare = {
        appId: '${signature.appid!}',
        title: '中药饮片报价',
        desc: '',
        link: '',
        imgUrl: "",
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
                var desc = "";
                // 默认全选
                $cbxs.each(function() {
                    this.checked = true;
                    commodityStr.push(this.value);
                    desc += $(this).data("name")+ " "+$(this).data("price");
                    weixinShare.link = "${baseUrl}/quote?ids=" + commodityStr.join(',');
                    weixinShare.desc = desc;
                    initWxShareV1();
                });

                // 单选
                $cbxs.on('click', function() {
                    desc = "";
                    commodityStr = [];
                    $cbxs.each(function () {
                        if (this.checked) {
                            commodityStr.push(this.value);
                            desc += $(this).data("name")+ " "+$(this).data("price");
                        }
                    })
                    weixinShare.link = "${baseUrl}/quote?ids=" + commodityStr.join(',');
                    weixinShare.desc = desc;
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