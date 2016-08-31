<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./inc/meta.ftl"/>
    <title>支付结果-饮片B2B</title>
    <link rel="stylesheet" href="css/style.css" />
    <link rel="stylesheet" href="css/order.css" />
</head>

<body>


    <#include "./inc/header-center.ftl"/>

<div class="wrap">
        <div class="payment">
            <div class="title">
                <h3>支付</h3>
            </div>

            <div class="state">
                <h4><i class="fa fa-check-circle"></i>您的付款信息已提交成功！</h4>
                <p>款项确认无误后平台将立即安排为您发货。 </p>
            </div>

            <!-- 
            <div class="state">
                <h4><i class="fa fa-check-circle"></i>您的账期申请已提交成功！</h4>
                <p>平台工作人员会与您联系，沟通具体账期事宜。  </p>
            </div>
             -->
        </div>
    </div>


    <!-- footer start -->
    <#include "./inc/footer.ftl"/>
    <!-- footer end -->
    
    <div id="imgCropWrap"></div>

</body>
</html>