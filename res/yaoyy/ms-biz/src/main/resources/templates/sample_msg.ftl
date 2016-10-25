<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./common/meta.ftl"/>
    <title>留言-药优优</title>
</head>
<body>
<div class="ui-content">
    <div class="ui-form">
        <form action="">
            <div class="item">
                <textarea name="" id="msg" class="mul" cols="30" rows="10" placeholder="留言"></textarea>
                <span class="error"></span>
            </div>
            <button type="button" class="ubtn ubtn-primary" id="submit">提交</button>
        </form>
    </div>
    <div class="ui-extra">
        <a href="contactus.html">联系我们</a>
    </div>
</div>

<#include  "./common/footer.ftl"/>
<script>

    var _global = {
        v: {
            trackingCreateUrl:"sample/feedBack"
        },
        fn: {
            init: function() {
                this.validator();
            },
            validator: function() {
                var self = this;
                $('#submit').on('click', function() {
                    if (self.checkMsg()) {
                        $.ajax({
                            url: _global.v.trackingCreateUrl,
                            data:  {sendId:${sendId?c},recordType:7,extra:$('#msg').val()},
                            type: "POST",
                            success: function(data) {
                                location.href = 'sample/detail/${sendId?c}';
                            }
                        })

                    } else {

                    }
                    return false;
                })
            },
            checkMsg: function() {
                var $el = $('#msg'),
                        val = $el.val();

                if (!val) {
                    $el.next().html('留言内容不能为空！').show();

                } else {
                    $el.next().hide();
                    return true;
                }
                return false;
            }
        }
    }

    $(function(){
        _global.fn.init();

    });

</script>
</body>
</html>