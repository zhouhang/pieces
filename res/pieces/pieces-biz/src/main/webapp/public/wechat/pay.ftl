<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>支付-上工好药</title>
</head>
<body>

<section class="ui-content">
    <div class="floors-info">
        <p><span>应付金额：</span><em>&yen;<#if userType==1>${vo.amountsPayable!}<#else>${vo.deposit!}</#if></em></p>
        <p><span>订单号：</span>${vo.code!}</p>
        <p><span>剩余付款时间：</span>${vo.orderValidityPeriod!}</p>
    </div>
        

    <div class="ui-button mt20">
        <!--<button type="button" class="ubtn ubtn-red" id="weixin">微信支付</button>-->
        <button type="button" class="ubtn ubtn-white" id="bank">银行打款</button>
        <#if userType==2>
        <button type="button" class="ubtn ubtn-white" id="bill">申请账期</button>
        </#if>
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
            var enable = true;

            // 微信支付
            $('#weixin').on('click', function() {
                enable && $.ajax({
                    url: '',
                    // type: 'POST',
                    success: function(res) {
                        console.log(0)
                        // layer.close(index);
                    },
                    complete: function() {
                        enable = true;
                    }
                })
                enable = false;
            })
            
            // 银行打款
            $('#bank').on('click', function() {
                window.location.href = '/h5c/pay/bank/${vo.id?c}';
            })

            // 申请账期
            $('#bill').on('click', function() {
                window.location.href = '/h5c/pay/bill/${vo.id?c}';
            })
        }
    }

    _global.init();
})(window.Zepto || window.jQuery);
</script>
</body>
</html>