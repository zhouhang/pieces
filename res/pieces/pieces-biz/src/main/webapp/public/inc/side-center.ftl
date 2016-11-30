<div class="side">
<#if user_session_biz?? && user_session_biz.type == 1>
    <dl>
        <dt>询价</dt>
        <dd>
            <a href="center/enquiry/index">我要询价</a>
        </dd>
        <dd>
            <a href="center/enquiry/record">询价记录</a>
        </dd>
    </dl>
    <#if user_session_biz.certifyStatus == 1>
    <dl>
        <dt>订单</dt>
        <dd>
            <a href="center/order/list">我的订单</a>
        </dd>
    </dl>
    <dl>
        <dt>物流</dt>
        <dd>
            <a href="/center/logistics/list">我的物流</a>
        </dd>
    </dl>
    <dl>
        <dt>对账单</dt>
        <dd>
            <a href="/center/pay/record">支付记录</a>
        </dd>
        <dd>
            <a href="/center/bill/index">帐期账单</a>
        </dd>
    </dl>
    </#if>
    <dl>
        <dt>收藏</dt>
        <dd>
            <a href="/center/collect/index" >我的收藏</a>
        </dd>
    </dl>
    <dl>
        <dt>设置</dt>
        <dd>
            <a href="user/info">注册资料</a>
        </dd>
        <dd>
            <a href="user/pwd/update">修改密码</a>
        </dd>
        <dd>
            <a href="user/shippingaddress/index">收货地址</a>
        </dd>
    </dl>
    </#if>
    <#if user_session_biz?? && user_session_biz.type == 2>
    <dl>
        <dt>订单</dt>
        <dd>
            <a href="/center/order/agent">我的订单</a>
        </dd>
    </dl>
    <dl>
        <dt>物流</dt>
        <dd>
            <a href="/center/logistics/agent/list">我的物流</a>
        </dd>
    </dl>
    <dl>
        <dt>对账单</dt>
        <dd>
            <a href="/center/pay/record">支付记录</a>
        </dd>
    </dl>
    <dl>
        <dt>设置</dt>
        <dd>
            <a href="user/info">注册资料</a>
        </dd>
        <dd>
            <a href="user/pwd/update">修改密码</a>
        </dd>
    </dl>
    </#if>
</div>
