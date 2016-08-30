<!-- header start -->
<div class="header">
    <div class="wrap">
        <div class="logo">
            <a href="/">上工之选电子商务管理系统</a>
        </div>
        <div class="user">
            <span>登录用户 ${member_session_boss.username }</span>
            <i>|</i>
            <span>${.now?string("yyyy-MM-dd EEEE")}</span>
            <i>|</i>
            <a href="logout">退出</a>
        </div>
    </div>
</div>
<!-- header end -->


<!-- nav start -->
<div class="nav">
    <div class="wrap">
        <ul>
            <li><a href="#!">首页</a></li>
            <@shiro.hasPermission name="sales:index">
                <li>
                    <a href="#!">销售</a>
                    <div class="subnav">
                    <@shiro.hasPermission name="enquiry:index">
                    <a href="/enquiry/index">询价管理</a>
                    </@shiro.hasPermission>
                    <a href="/order/index">订单管理</a>
                    <a href="/payment/index">支付管理</a>
                    </div>
                </li>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="directory:index">
                <li>
                    <a href="#!">目录</a>
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
                    <a href="#!">客户</a>
                    <div class="subnav">
                        <a href="user/index">客户管理</a>
                    </div>
                </li>
            </@shiro.hasPermission>
            <li>
                <a href="#!">促销</a>
                <div class="subnav">
                    <a href="ad/index">广告管理</a>
                </div>
            </li>
            <li><a href="#!">邮件列表</a></li>
            <li>
                <a href="#!">CMS</a>
                <div class="subnav">
                    <a href="cms/article/index?model=1">单页面管理</a>
                    <a href="cms/category/index?model=1">单页面分类管理</a>
                    <a href="cms/article/index?model=2">文章管理</a>
                    <a href="cms/category/index?model=2">文章分类管理</a>
                </div>

            </li>
            <li><a href="#!">报表</a></li>
            <@shiro.hasPermission name="system:index">
                <li>
                    <a href="#!">系统</a>
                    <div class="subnav">
                        <@shiro.hasPermission name="member:index">
                            <a href="member/index">用户管理</a>
                        </@shiro.hasPermission>
                        <@shiro.hasPermission name="role:index">
                            <a href="role/index">角色管理</a>
                        </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
        </ul>
    </div>
</div>
<!-- nav end -->
