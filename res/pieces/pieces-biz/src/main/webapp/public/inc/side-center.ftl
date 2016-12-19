<div class="side">
<#if user_session_biz?? && user_session_biz.type == 1>
    <dl>
        <dt><i class="fa fa-question-circle"></i>询价</dt>
        <dd>
            <a href="center/enquiry/index"><i class="fa fa-li"></i>我要询价</a>
        </dd>
        <dd>
            <a href="center/enquiry/record"><i class="fa fa-li"></i>询价记录</a>
        </dd>
    </dl>
    <#if user_session_biz.certifyStatus == 1>
    <dl>
    <dt><i class="fa fa-menu"></i>订单</dt>
        <dd>
            <a href="center/order/list"><i class="fa fa-li"></i>我的订单</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-logistics"></i>物流</dt>
        <dd>
            <a href="/center/logistics/list"><i class="fa fa-li"></i>我的物流</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-bill"></i>对账单</dt>
        <dd>
            <a href="/center/pay/record"><i class="fa fa-li"></i>支付记录</a>
        </dd>
        <dd>
            <a href="/center/bill/index"><i class="fa fa-li"></i>帐期账单</a>
        </dd>
    </dl>
    </#if>
    <dl>
        <dt><i class="fa fa-heart"></i>收藏</dt>
        <dd>
            <a href="/center/collect/index" ><i class="fa fa-li"></i>我的收藏</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-setting"></i>设置</dt>
        <dd>
            <a href="user/info"><i class="fa fa-li"></i>注册资料</a>
        </dd>
        <dd>
            <a href="user/pwd/update"><i class="fa fa-li"></i>修改密码</a>
        </dd>
        <dd>
            <a href="user/shippingaddress/index"><i class="fa fa-li"></i>收货地址</a>
        </dd>
    </dl>
    </#if>
    <#if user_session_biz?? && user_session_biz.type == 2>
    <dl>
        <dt><i class="fa fa-menu"></i>订单</dt>
        <dd>
            <a href="/center/order/agent"><i class="fa fa-li"></i>我的订单</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-logistics"></i>物流</dt>
        <dd>
            <a href="/center/logistics/agent/list"><i class="fa fa-li"></i>我的物流</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-bill"></i>对账单</dt>
        <dd>
            <a href="/center/pay/record"><i class="fa fa-li"></i>支付记录</a>
        </dd>
    </dl>
    <dl>
        <dt><i class="fa fa-setting"></i>设置</dt>
        <dd>
            <a href="user/info"><i class="fa fa-li"></i>注册资料</a>
        </dd>
        <dd>
            <a href="user/pwd/update"><i class="fa fa-li"></i>修改密码</a>
        </dd>
    </dl>
    </#if>
</div>
