<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>您访问的页面不存在-上工好药</title>
</head>

<body>
    <#include "./inc/header.ftl"/>
    <div class="main-body">
        <div class="fa-pro-empty">
            <div class="fa fa-frown"></div>
            <div class="text">
                <h1 class="title">对不起，您访问的页面不存在！</h1>
                <dl>
                    <dd><em id="count">5</em> 秒之后返回首页...</dd>
                </dl>
            </div>
        </div>
    </div>

    <#include "./inc/helper.ftl"/>
    <#include "./inc/footer.ftl"/>
    <script>
    var page = {
        v: {},
        fn: {
            init: function() {
                this.jumpToHomePage();
            },
            // 延迟跳转到首页
            jumpToHomePage: function() {
                var count = document.getElementById('count');
                var delay = parseInt(count.innerHTML, 10);
                if (isNaN(delay)) {
                    delay = 6;
                }
                setInterval(function() {
                    if (delay < 2) {
                        location.href = '/';
                    } else {
                        count.innerHTML = -- delay;
                    }
                }, 1e3);
            }
        }
    }
    page.fn.init();
    </script>
</body>
</html>