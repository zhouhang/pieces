<!-- header start -->
<div class="header">
    <div class="wrap">
        <div class="logo">
            <a href="/"><em>上工好药</em>电子商务管理系统</a>
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
            <li><a href="/">首页</a></li>
            <@shiro.hasPermission name="sales:index">
                <li>
                    <a href="javascript:;" id="salePage">销售</a>
                    <div class="subnav">
                    <@shiro.hasPermission name="enquiry:index">
                    <a href="/enquiry/index" id="enquiryIndex">询价管理<b></b></a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="order:index">
                    <a href="/order/index">订单管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="pay:index">
                    <a href="/payment/index" id="paymentIndex">支付管理<b></b></a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="bill:index">
                    <a href="/account/bill/index" id="accountIndex">账单管理<b></b></a>
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
                        <a href="/contrive/index">广告管理</a>
                    </@shiro.hasPermission>
                    </div>
                </li>
            </@shiro.hasPermission>
           <@shiro.hasPermission name="message:index">
            <li>
                <a href="javascript:;" id="message">消息</a>
                <div class="subnav">
                    <@shiro.hasPermission name="certify:index">
                        <a href="certify/list" id="certifyList">企业资质审核<b></b></a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="recruit:index">
                    <a href="/recruit/index" id="recruit">合作伙伴申请<b></b></a>
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
            <li>
                <a href="/seo/setting/1">seo管理</a>
            </li>
        </ul>
    </div>
</div>