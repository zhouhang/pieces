<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付结果-上工好药</title>
    <link rel="stylesheet" href="css/order.css" />
</head>

<body>


    <#include "./inc/header-center.ftl"/>

    <div class="wrap">
        <div class="payment">
            <div class="title">
                <h3>支付</h3>
            </div>

            <#if state=="bill">
                <div class="state">
                    <h4><i class="fa fa-check-circle"></i>您的账期申请已提交成功！</h4>
                    <p>平台工作人员会与您联系，沟通具体账期事宜。  </p>
                </div>
            <#else>
                <div class="state">
                <#if payType=="outPay">

                    <h4><i class="fa fa-check-circle"></i>您的付款信息已提交成功！</h4>
                    <p>款项确认无误后平台将立即安排为您发货，您可以在 <a href="/center/order/list" class="c-blue">我的订单</a> 中查看订单状态。</p>
                    <#else>
                        <h4><i class="fa fa-check-circle"></i>您已支付成功，我们将尽快为您发货！</h4>
                        <p>您可以在 <a href="/center/order/list" class="c-blue">我的订单</a> 中查看订单状态，或继续 <a href="/" class="c-blue">采购其他商品。</a></p>
                </#if>
                </div>
            </#if>

        </div>
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    
</body>
</html>