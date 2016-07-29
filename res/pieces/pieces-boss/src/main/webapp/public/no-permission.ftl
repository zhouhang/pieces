<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>消息提示-饮片B2B</title>
</head>

<body>
    <#include "./inc/header.ftl">

    <!-- fa-message start -->
    <div class="fa-message">
        <div class="wrap">
            <div class="hd">
                <i class="fa fa-prompt"></i>
                <strong>您没有访问权限！</strong>
            </div>
            <div class="bd">
                <p>—— 您可以立即使用新密码登录 ——</p>
            </div>
            <div class="ft">
                <a class="btn btn-red" href="logout">立即登录</a>
            </div>
            <div class="links">
                或 <a href="/">返回首页</a>
            </div>
        </div>
    </div><!-- fa-message start -->

    <#include "./inc/footer.ftl"/>


    <button onclick="notify('success', '提交成功！', '5秒后自动跳转到商品详情页')">success</button>
    <button onclick="notify('error', '提交失败！')">error</button>
    <button onclick="notify('warn', '警告')">warn</button>

    <script src="js/common.js"></script>
    <script>
    var options = {
        clickToHide: true   // 点击关闭
        ,delay: 5e3         // 5秒后自动关闭，为0时不关闭
        ,title: '提示消息'  // 文字
        ,text: ''           // 说明
        ,type: 'warn'       // 类型：错误(error)，正确(success)，警告(warn)
        ,call: null         // 关闭后回调
    }

    function notify(type, title, text) {
        $.notify({
            type: type, 
            title: title, 
            text: text, 
            delay: 5e3,
			call: function() {
				setTimeout(function() {
                    // localtion.href=  '';
                }, 5e3);
			}
        });
    }
    </script>
</body>
</html>