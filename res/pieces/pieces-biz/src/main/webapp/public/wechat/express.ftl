<!DOCTYPE html>
<html lang="en">
  <head>
  <#include "wechat/inc/meta.ftl"/>
    <title>物流跟踪-上工好药</title>
</head>
<body>

<section class="ui-content">
    <div class="express-box">
        <#if logistical?exists>
        <div class="hd">${logistical.companyCodeName!}</div>
        <table>
            <thead>
                <tr>
                    <th><div class="time">时间</div></th>
                    <th><div class="trace">地点和跟踪进度</div></th>
                </tr>
            </thead>
            <tbody>
            <#list logisticalTraceVos as trace>
                <tr>
                    <td>
                        <div class="time">${trace.acceptTime?string("HH:mm")}</div>
                        <div class="day">${trace.acceptTime?string("yyyy.MM.dd")}</div>
                    </td>
                    <td>
                        <div class="trace">
                        ${trace.acceptStation}
                        </div>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        </#if>
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