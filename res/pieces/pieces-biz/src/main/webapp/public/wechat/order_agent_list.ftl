<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>客户选择-上工好药</title>
</head>

<body class="bg-gray">
<section class="ui-content">
    <div class="floors">
        <div class="hd">终端客户</div>
        <#list list as agent>
        <div class="bd">
            <label>
                <input name="customer" value="${agent.id}" type="radio" class="ico ico-rad mid cbx" />
                <em>${agent.companyFullName}</em>
            </label>
        </div>
        </#list>
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
                var agent = _YYY.localstorage.get('agent_${omd5!}');
                if (agent) {
                    agent = JSON.parse(agent);
                    $('.cbx').each(function () {
                        if (this.value == agent.id) {
                            this.checked = true;
                        }
                    })
                }

                $('.cbx').on('click', function() {
                    var agent = {}
                    agent.id= this.value;
                    agent.name =$(this).next().html();
                    _YYY.localstorage.set('agent_${omd5!}',JSON.stringify(agent)); // 保存客户
                    window.location.href = '/h5c/order/create?omd5=${omd5!}';
                })
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>