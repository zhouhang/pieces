<footer class="ui-footer">
    <nav class="ui-nav" id="foot-nav">
        <ul>
            <li>
                <a  href="/">
                    <i class="fa fa-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <li>
                <a href="/category/list">
                    <i class="fa fa-list"></i>
                    <span>品种列表</span>
                </a>
            </li>
            <li>
                <a href="/pickCommodity/list" id="commodityCart">
                    <i class="fa fa-cart"></i>
                    <span>选货单</span>
                </a>
            </li>
            <li>
                <a  href="/center/index">
                    <i class="fa fa-user"></i>
                    <span>个人中心</span>
                </a>
            </li>
        </ul>
    </nav>
</footer>
<script>
    window.onload = function () {

        if (is_weixin()) {
            var a = $(".ui-nav a");
            $(a, function (k, v) {
                $(v).attr("href", $(v).attr("href") + "?source=WECHAT")
            })
        }
    }
</script>
