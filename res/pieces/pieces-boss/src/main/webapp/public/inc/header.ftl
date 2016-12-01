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
                    <a href="/payment/index" id="paymentIndex">支付管理</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="bill:index">
                    <a href="/account/bill/index" id="accountIndex">账单管理</a>
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
           <@shiro.hasPermission name="message:index">
            <li>
                <a href="javascript:;" id="message">消息</a>
                <div class="subnav">
                    <@shiro.hasPermission name="certify:index">
                        <a href="certify/list" id="certifyList">企业资质审核</a>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="anon:enquiry">
                        <a href="anon/enquiry" id="anonEnquiry">新客询价</a>
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
<script>
$(function() {
    /*
      一分钟请求一次
     */
    showMessage();
    setTimeout(showMessage, 60000);
})
function showMessage(){
    $.ajax({
        url: '/message/notHandle',
        type: "POST",
        success: function (data) {
            if(data.status=="y"){
                var result=data.data;
                var ACCOUNT_BILL_NUM=result["1"];
                var ENQUIRYBILL_NUM=result["2"];
                var CERTIFY_RECORD_NUM=result["3"];
                var ANON_ENQUIRY_NUM=result["4"];
                var PAY_RECORD_NUM=result["5"];

                if(ACCOUNT_BILL_NUM!=0||ENQUIRYBILL_NUM!=0||PAY_RECORD_NUM!=0){
                    $("#salePage").html("销售<i></i>");
                    if(ACCOUNT_BILL_NUM==0){
                        ACCOUNT_BILL_NUM="";
                    }
                    if(ENQUIRYBILL_NUM==0){
                        ENQUIRYBILL_NUM="";
                    }
                    if(PAY_RECORD_NUM==0){
                        PAY_RECORD_NUM="";
                    }
                    $("#paymentIndex").html("支付管理<b>"+PAY_RECORD_NUM+"</b>");
                    $("#enquiryIndex").html("询价管理<b>"+ENQUIRYBILL_NUM+"</b>");
                    $("#accountIndex").html("账单管理<b>"+ACCOUNT_BILL_NUM+"</b>");

                }
                else{
                    $("#salePage").html("销售");
                    $("#paymentIndex").html("支付管理");
                    $("#enquiryIndex").html("询价管理");
                    $("#accountIndex").html("账单管理");
                }


                if(CERTIFY_RECORD_NUM!=0||ANON_ENQUIRY_NUM!=0){
                    $("#message").html("消息<i></i>");
                    if(CERTIFY_RECORD_NUM==0){
                        CERTIFY_RECORD_NUM="";
                    }
                    if(ANON_ENQUIRY_NUM==0){
                        ANON_ENQUIRY_NUM="";
                    }
                    $("#certifyList").html("企业资质审核<b>"+CERTIFY_RECORD_NUM+"</b>");
                    $("#anonEnquiry").html("新客询价<b>"+ANON_ENQUIRY_NUM+"</b>");

                }
                else{
                    $("#message").html("消息");
                    $("#certifyList").html("企业资质审核");
                    $("#anonEnquiry").html("新客询价");
                }

            }
        }
    });
}

</script>
