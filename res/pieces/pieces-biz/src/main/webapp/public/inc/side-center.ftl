<div class="side">
<#if user_session_biz?? && user_session_biz.type == 1>
    <dl>
        <dt>
            <em  class="fa fa-question-circle"></em>
            <span>询价</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="center/enquiry/index">我要询价</a>
            <a href="center/enquiry/record">询价记录</a>
        </dd>
    </dl>
    <dl>
        <dt>
            <em class="fa fa-menu"></em>
            <span>订单</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="center/order/list">我的订单</a>
        </dd>
    </dl>
    <dl>
        <dt>
            <em class="fa fa-logistics"></em>
            <span>物流</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="/center/logistics/list">我的物流</a>
        </dd>
    </dl>
    <dl>
        <dt>
            <em class="fa fa-bill"></em>
            <span>对账单</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="/center/pay/record">支付记录</a>
            <a href="/center/bill/index">帐期账单</a>
        </dd>
    </dl>
    <dl>
        <dt>
            <em class="fa fa-heart"></em>
            <span>收藏</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="/center/collect/index" >我的收藏</a>
        </dd>
    </dl>
    <dl>
        <dt>
            <em class="fa fa-setting"></em>
            <span>设置</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="user/info">注册资料</a>
            <a href="user/pwd/update">修改密码</a>
            <a href="user/shippingaddress/index">收货地址</a>
        </dd>
    </dl>
    </#if>
    <#if user_session_biz?? && user_session_biz.type == 2>
    <dl>
        <dt>
            <em class="fa fa-menu"></em>
            <span>订单</span>
            <i class="fa fa-chevron-right"></i>
        </dt>
        <dd>
            <a href="/center/order/agent">我的订单</a>
        </dd>
    </dl>
        <dl>
            <dt>
                <em class="fa fa-logistics"></em>
                <span>物流</span>
                <i class="fa fa-chevron-right"></i>
            </dt>
            <dd>
                <a href="/center/logistics/agent/list">我的物流</a>
            </dd>
        </dl>
        <dl>
            <dt>
                <em class="fa fa-bill"></em>
                <span>对账单</span>
                <i class="fa fa-chevron-right"></i>
            </dt>
            <dd>
                <a href="/center/pay/record">支付记录</a>
            </dd>
        </dl>
        <dl>
            <dt>
                <em class="fa fa-setting"></em>
                <span>设置</span>
                <i class="fa fa-chevron-right"></i>
            </dt>
            <dd>
                <a href="user/info">注册资料</a>
                <a href="user/pwd/update">修改密码</a>
            </dd>
        </dl>
    </#if>
</div>
