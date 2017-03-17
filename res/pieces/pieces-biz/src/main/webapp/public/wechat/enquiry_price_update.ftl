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
    <div class="pdetail">
        <div class="thead">
            <div class="tr">
                <div class="td">商品名</div>
                <div class="td">片型</div>
                <div class="td">销售价</div>
                <div class="td">开票价</div>
            </div>
        </div>
        <div class="tbody">
            <#list list as commodity>
            <div class="tr">
                <div class="td">${commodity.commodityName!}<input type="text" style="display: none;" value="${commodity.id!}"></div>
                <div class="td">${commodity.specs}</div>
                <div class="td">${(commodity.myPrice?string .currency)!}</div>
                <div class="td"><input type="tel" value="${(commodity.price?default(commodity.myPrice!))?string("0.00")}" class="ipt" tabindex="1" /><span class="error"></span></div>
            </div>
            </#list>
        </div>
    </div>
    <div class="ui-button">
        <button type="button" class="ubtn ubtn-red" id="submit">保存</button>
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
                var $ipt = $('.pdetail').find('.ipt');

                var check = function() {
                    var pass = true;
                    $ipt.each(function() {
                        if (this.value == '') {
                            $(this).next().html('请输入价格').show();
                            pass = false;
                            return false; // break
                        }
                    })
                    return pass;
                }

                // 价格输入框
                $ipt.on('focus blur', function() {
                    $(this).next().hide();
                }).on('keyup', function() {
                    var val = this.value;
                    if (!/^\d+\.?\d*$/.test(val)) {
                        val = Math.abs(parseFloat(val));
                        this.value = isNaN(val) ? '' : val;
                    }
                })

                // 提交
                $('#submit').on('click', function() {
                    if (check()) {
                        var list = new Array();
                        var $trs = $('.tbody').find('.tr');
                        $.each($trs, function (k, v) {
                            list.push({id:$($(v).find("input")[0]).val(),price:$($(v).find("input")[1]).val()});
                        })
                        $.ajaxSetup({
                            headers: {
                                'Content-Type': 'application/json;charset=utf-8'
                            }
                        });

                        $.ajax({
                            url: '/h5/enquiry/updatePrice',
                            data: JSON.stringify(list),
                            type: 'POST',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status=="y") {
                                    window.location.href = '/h5/enquiry/updatePriceSuccess?ids=${ids!}&billId=${billId!}';
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试！');
                            }
                        })
                    }
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>