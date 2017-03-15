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
        <b>*</b>本次报价仅于 ${bill.expireDate?date} 前有效。<br>
        <b>*</b>所有价格都是公斤价。
    </div>

    <div class="pdetail">
        <ul>
        <#list bill.enquiryCommoditys as commodity>
            <li>
                <div class="hd">${commodity.commodityName!}</div>
                <div class="bd">${commodity.specs!}${commodity.level!}</div>
                <div class="price">
                    <span>销售价:<em>￥${commodity.myPrice!}</em></span>
                    <span>开票价:￥${commodity.price!}</span>
                </div>
                <div class="pic rs-pic">
                    <img src="${commodity.pictureUrl!}"/>
                </div>
                <div class="cbx mid"><input type="checkbox" value="${commodity.id!}" class="ico ico-rad"/></div>
            </li>
        </#list>
        </ul>
    </div>

    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="submit"><i class="ico ico-edit"></i> 修改开票价</button>
        <button type="button" class="ubtn ubtn-white" id="share"><i class="ico ico-share2"></i> 分享报价</button>
    </div>

</section><!-- /ui-content -->
<#include "./inc/footer.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                _YYY.share.init('#share'); // 分享
            },
            share:function() {
                var commodityStr = [];
                var $cbxs = $('.enquity-pdetail').find('td input:not(:disabled)')

                $cbxs.each(function() {
                    this.checked && commodityStr.push(this.value);
                })

                if (commodityStr.length === 0) {
                    popover('请先选择商品');
                    return false;
                }
                window.location.href = "/quote?ids=" +commodityStr.join(',');
            },
            update: function () {
                var commodityStr = [], commodityIds;
                var $cbxs = $('.enquity-pdetail').find('td input:not(:disabled)')

                $cbxs.each(function() {
                    this.checked && commodityStr.push(this.value);
                })

                if (commodityStr.length === 0) {
                    popover('请先选择商品');
                    return false;
                }
                window.location.href = "/h5/enquiry/updatePrice?ids=" +commodityStr.join(',');
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>