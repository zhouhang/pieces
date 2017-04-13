<!DOCTYPE html>
<html lang="en">
<head>
    <#include "wechat/inc/meta.ftl"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>输入姓名-上工好药</title>
</head>

<body>
<section class="ui-content">
    <form action="" id="myform">
        <div class="ui-form">
            <div class="item item-b">
                <input type="text" class="ui-ipt" name="name" id="name" placeholder="您的姓名" value="" autocomplete="off">
                <span class="error"></span>
            </div>
        </div>

        <div class="ui-button mt30">
            <button type="button" id="submit" class="ubtn ubtn-red">确定</button>
        </div>
    </form>
</section><!-- /ui-content -->
<#include "wechat/inc/footer_h5.ftl"/>
<script>
    !(function($) {
        var _global = {
            init: function() {
                this.bindEvent();
            },
            bindEvent: function() {
                var that = this;

                $('#submit').on('click', function() {
                    if (that.check()) {
                        $.ajax({
                            url: "/h5/bindName",
                            data: {name:$("#name").val()},
                            type: 'POST',
                            dataType: 'json',
                            success: function (result) {
                                if (result.status=="y") {
                                    window.location.href = result.data;
                                } else {
                                    popover(result.info);
                                }
                            },
                            error: function (XMLHttpRequest, textStatus, errorThrown) {
                                popover('网络连接超时，请您稍后重试！');
                            }
                        })
                    }
                })

                $('.ui-form').on('focus blur', '.ui-ipt', function() {
                    $(this).next().hide();
                })
            },
            check: function() {
                return this.checkName()
            },
            checkName: function() {
                var $el = $('#name'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('请输入您的姓名！').show();

                } else if (!/^([a-zA-Z]|[\u4e00-\u9fa5]){2,50}$/.test(val)) {
                    $el.next().html('联系人姓名长度2-50位，只能包括中文字、英文字母！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            }
        }

        _global.init();
    })(window.Zepto || window.jQuery);
</script>
</body>
</html>