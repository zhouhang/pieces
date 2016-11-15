<!-- 侧栏菜单 -->
<div class="aside" id="jaside">
    <dl>
        <dt>
            <a href="index"><i class="fa fa-dashboard"></i>控制面板</a>
        </dt>
    </dl>
    <@shiro.hasPermission name="specialad:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-star"></i>
                <span>专场广告</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
        <@shiro.hasPermission name="special:list">
            <a href="special/list"><i class="fa fa-circle-o"></i>专场列表</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="ad:list">
            <a href="ad/list"><i class="fa fa-circle-o"></i>广告列表</a>
        </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="cms:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-file-text"></i>
                <span>CMS管理</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="cms:list">
            <a href="cms/list"><i class="fa fa-circle-o"></i>CMS管理</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="user:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-user"></i>
                <span>用户管理</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="user:list">
            <a href="/user/list"><i class="fa fa-circle-o"></i>用户列表</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="sample:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-truck"></i>
                <span>寄样服务</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="sample:list">
                <a href="/sample/list"><i class="fa fa-circle-o"></i>寄样列表</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="pick:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-shopping-bag"></i>
                <span>选货单管理</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="pick:list">
            <a href="/pick/list"><i class="fa fa-circle-o"></i>选货单列表</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="commodity:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-cart-plus"></i>
                <span>商品管理</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="category:list">
                <a href="/category/list"><i class="fa fa-circle-o"></i>品种列表</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="commodity:list">
                <a href="/commodity/list"><i class="fa fa-circle-o"></i>商品列表</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="memberole:index">
    <dl>
        <dt>
            <a href="javascript:;">
                <i class="fa fa-list"></i>
                <span>账号权限</span>
                <i class="fa fa-angle-down arrow"></i>
            </a>
        </dt>
        <dd>
            <@shiro.hasPermission name="member:list">
                <a href="/member/index"><i class="fa fa-circle-o"></i>管理员列表</a>
            </@shiro.hasPermission>
            <@shiro.hasPermission name="role:list">
                <a href="/role/index"><i class="fa fa-circle-o"></i>角色列表</a>
            </@shiro.hasPermission>
        </dd>
    </dl>
    </@shiro.hasPermission>
</div>
