<!-- nav start -->
<div class="nav">
    <div class="wrap">
        <ul>
            <li><a href="/">首页</a></li>
            <@shiro.hasPermission name="sales:index">
                <li>
                    <a href="javascript:;">销售</a>
                    <div class="subnav">
                    <@shiro.hasPermission name="enquiry:index">
                    <a href="/enquiry/index">询价管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="order:index">
                    <a href="/order/index">订单管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="pay:index">
                    <a href="/payment/index">支付管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="bill:index">
                    <a href="/account/bill/index">账单管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="logistical:index">
                    <a href="/logistics/index">物流管理</a>
                    </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="directory:index">
                <li>
                    <a href="javascript:;">目录</a>
                    <div class="subnav">
                    <@shiro.hasPermission name="commodity:index">
                        <a href="/commodity/index">商品管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="category:index">
                        <a href="/category/list">分类管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="breed:index">
                        <a href="/breed/list">品种管理</a>
                    </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="customer:index">
                <li>
                    <a href="javascript:;">客户</a>
                    <div class="subnav">
                        <@shiro.hasPermission name="   customer:view">
                        <a href="user/index">客户管理</a>
                        </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="promotion:index">
                <li>
                    <a href="javascript:;">促销</a>
                    <div class="subnav">
                    <@shiro.hasPermission name="ad:index">
                        <a href="ad/index">广告管理</a>
                    </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="cms:index">
                <li>
                    <a href="javascript:;">CMS</a>
                    <div class="subnav">
                        <@shiro.hasPermission name="single:index">
                            <a href="cms/article/index?model=1">单页面管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="single:category">
                            <a href="cms/category/index?model=1">单页面分类管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="post:index">
                            <a href="cms/article/index?model=2">文章管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="post:category">
                            <a href="cms/category/index?model=2">文章分类管理</a>
                        </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="system:index">
                <li>
                    <a href="javascript:;">系统</a>
                    <div class="subnav">
                        <@shiro.hasPermission name="member:index">
                            <a href="member/index">用户管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="role:index">
                            <a href="role/index">角色管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="bank:index">
                            <a href="bank/index">收款账户管理</a>
                        </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
</div>
<!-- nav end -->
