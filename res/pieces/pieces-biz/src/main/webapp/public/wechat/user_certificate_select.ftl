<!DOCTYPE html>
<html lang="en">
  <head>
    <#include "wechat/inc/meta.ftl"/>
    <title>企业类型-上工好药</title>
</head>
  <body class="bg-gray">

  <section class="ui-content">
      <div class="floors">
          <div class="hd">企业类型</div>
          <div class="bd">
              <label>
                  <input name="company" value="1" type="radio" class="ico ico-rad mid cbx" />
                  <em>药店</em>
              </label>
          </div>
          <div class="bd">
              <label>
                  <input name="company" value="2" type="radio" class="ico ico-rad mid cbx" />
                  <em>医疗机构</em>
              </label>
          </div>
          <div class="bd">
              <label>
                  <input name="company" value="3" type="radio" class="ico ico-rad mid cbx" />
                  <em>制药企业</em>
              </label>
          </div>
          <div class="bd">
              <label>
                  <input name="company" value="4" type="radio" class="ico ico-rad mid cbx" />
                  <em>医药公司</em>
              </label>
          </div>
      </div>
  </section><!-- /ui-content -->
</section><!-- /ui-content -->

<#include "wechat/inc/footer_h5.ftl"/>
  <script>
      !(function($) {
          var _global = {
              init: function() {
                  this.bindEvent();
              },
              bindEvent: function() {
                  $('.cbx').on('click', function() {
                      window.location.href = 'h5c/user/certificate?type=' + this.value;
                  })
              }
          }

          _global.init();
      })(window.Zepto || window.jQuery);
  </script>
</body>
</html>