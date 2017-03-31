<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>发票选择-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="floors">
        <div class="hd">发票</div>
        <div class="bd">
            <label>
                <input name="invoice" value="1" type="radio" class="ico ico-rad mid cbx" checked="" />
                <em>普通发票</em>
            </label>
        </div>
        <div class="bd">
            <label>
                <input name="invoice" value="2" type="radio" class="ico ico-rad mid cbx" />
                <em>增值税专用发票</em>
            </label>
        </div>
        <div class="bd">
            <label>
                <input name="invoice" value="0" type="radio" class="ico ico-rad mid cbx" />
                <em>不需要发票</em>
            </label>
        </div>
    </div>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                var invoice = _YYY.localstorage.get('invoice_${omd5!}');
                if (invoice) {
                    invoice = JSON.parse(invoice);
                    $('.cbx').each(function () {
                        if (this.value == invoice.type) {
                            this.checked = true;
                        }
                    })
                }

                $('.cbx').on('click', function() {
                    var invoice = {}
                    invoice.type= this.value;
                    invoice.name =$(this).next().html();
                    _YYY.localstorage.set('invoice_${omd5!}',JSON.stringify(invoice)); // 保存客户
                    window.location.href = '/h5c/order/create?omd5=${omd5!}';
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>