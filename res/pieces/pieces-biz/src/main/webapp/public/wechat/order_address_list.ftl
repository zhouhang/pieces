<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>收货地址-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="floors">
        <div class="hd">收货地址</div>
        <#list list as address>
        <div class="bd">
            <label>
                <input name="consignee" value="${address.id}" type="radio" class="ico ico-rad mid cbx" checked="" />
                <strong>${address.consignee}  ${address.tel}</strong>
                <span>${address.fullAdd}${address.detail}</span>
            </label>
            <a href="/h5c/address/edit?id=${address.id}&omd5=${omd5!}" class="ico ico-edit2 mid edit"></a>
        </div>
        </#list>
    </div>
    <div class="ui-add">
        <a href="/h5c/address/edit?omd5=${omd5!}" class="add">新增收货地址</a>
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
                var address = _YYY.localstorage.get('address_${omd5!}');
                if (address) {
                    address = JSON.parse(address);
                    $('.cbx').each(function () {
                        if (this.value == address.id) {
                            this.checked = true;
                        }
                    })
                }
                $('.cbx').on('click', function() {
                    var address = {};
                    address.id = $(this).val();
                    address.tel = $(this).next().html();
                    address.detail = $(this).next().next().html();
                    _YYY.localstorage.set('address_${omd5!}', JSON.stringify(address)); // 保存收货地址ID
                    window.location.href = '/h5c/order/create?omd5=${omd5!}';
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>