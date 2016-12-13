<!DOCTYPE html>
<html lang="en">
<head>
    <#include "./../inc/meta.ftl"/>
    <title>上工好药</title>
    <style>
    .subject{color:#666;}
    .subject h2{line-height:1;padding-top:75px;text-align:center;font-size:20px;}
    .subject h2:after{content:"";display:block;width:50px;height:10px;margin:0 auto;border-bottom:2px solid #c3272b;overflow:hidden;}
    .subject .text{line-height:1;padding-top:20px;text-align:center;}
    .subject .text p{padding-top:20px;}
    .subject .floor1{padding:70px 0 170px;background:#fff;}
    .subject .floor2{background:#f7f7f7;}
    .subject .floor2 li{float:left;width:25%;line-height:1;padding:60px 0 85px;text-align:center;}
    .subject .floor2 li i{display:block;width:140px;height:140px;margin:0 auto;background:url(/images/sub-icon.png) no-repeat;}
    .subject .floor2 li i.ico2{background-position:-140px 0;}
    .subject .floor2 li i.ico3{background-position:-280px 0;}
    .subject .floor2 li i.ico4{background-position:-420px 0;}
    .subject .floor2 li h3{padding:35px 0 0;font-size:18px;}
    .subject .floor2 li p{padding-top:18px;font-size:12px;}
    .subject .floor3{padding:25px 0 0;background:#fff url(/images/bg-floor3.jpg) no-repeat 50% 100px;}
    .subject .floor3 ul{position:relative;height:511px;color:#999;}
    .subject .floor3 li{float:left;position:relative;}
    .subject .floor3 .g1{margin:128px 0 0 26px;}
    .subject .floor3 .g2{margin:150px 0 0 70px;}
    .subject .floor3 .g3{margin:70px 0 0 100px;}
    .subject .floor3 .g4{margin:104px 0 0 84px;}
    .subject .floor3 .g5{margin:102px 0 0 162px;}
    .subject .floor3 .g6{margin:116px 0 0 94px;}
    .subject .floor3 .hd{width:114px;height:114px;line-height:114px;font-size:18px;color:#999;text-align:center;background:url(/images/circle.png) no-repeat -181px 0;}
    .subject .floor3 .bd{position:absolute;top:100%;left:-36px;z-index:2;width:231px;height:119px;margin-top:12px;padding:58px 36px 0 36px;background:url(/images/pop-bg.png) no-repeat;display:none;}
    .subject .floor3 .sml .hd{width:90px;height:90px;line-height:90px;background-position:0 0;}
    .subject .floor3 .sml .bd{left:-48px;}
    .subject .floor3 em{color:#666;}
    .subject .floor3 li p{line-height:1;padding-bottom:14px;}
    .subject .floor3 li:hover .hd{color:#fff;background-position:-295px 0;}
    .subject .floor3 li.sml:hover .hd{background-position:-90px 0;}
    </style>
</head>

<body class="bg-gray">
    <#include "./../inc/header.ftl"/>

    <!-- banner start -->
    <div class="banner-slider" style="background:url(/images/uploads/banner-05.jpg) no-repeat 50% 0;">
    </div><!-- banner end -->

    <div class="subject">
        <div class="floor1">
            <h2>我们是谁</h2>
            <div class="text">
                <p>上工好药是一个自营式中药饮片采购平台，</p>
                <p>上工是古代中国对医术高明的医生的称谓，上工好药即高明医生使用的药材，这是网站名的第一层含义。</p>
                <p>网站名的第二层含义是用户使用平台提供的饮片，能有很好的疗效，因为我们平台提供的饮片都是经过严格质量控制的，含量达标的好饮片，</p>
                <p>不会让现代中医因为中药质量问题而陷于尴尬的境地，上工好药，只卖好饮片！这是网站名的第二层含义，也是我们的使命。</p>
            </div>
        </div>

        <div class="floor2">
            <h2>我们的优势</h2>
            <ul class="wrap">
                <li>
                    <i class="ico2"></i>
                    <h3>规格全</h3>
                    <p>精细划分规格等级</p>
                    <p>批批严格加工，质量稳定</p>
                </li>
                <li>
                    <i class="ico1"></i>
                    <h3>质量好</h3>
                    <p>所有商品均由有该品种加工优势的</p>
                    <p>正规GMP药企生产，确保质量</p>
                </li>
                <li>
                    <i class="ico3"></i>
                    <h3>价格优</h3>
                    <p>同品种、同规格</p>
                    <p>保持市场价格水平或更低</p>
                </li>
                <li>
                    <i class="ico4"></i>
                    <h3>物流方便</h3>
                    <p>下单后集中发货</p>
                    <p>直接配送到用户指定地点</p>
                </li>
            </ul>
        </div>

        <div class="floor3">
            <h2>平台操作流程</h2>
            <ul class="wrap">
                <li class="g1 sml">
                    <div class="hd">注册</div>
                    <div class="bd">
                        <p>填写企业相关信息；</p>
                        <p>上传企业资质文件；</p>
                        <p>审核通过后成为认证用户。</p>
                    </div>
                </li>
                <li class="g2">
                    <div class="hd">查找商品</div>
                    <div class="bd">
                        <p><em>可以通过以下方式查找商品：</em></p>
                        <p>原药名称搜索、饮片名称搜索、</p>
                        <p>原药分类、原药拼音分类</p>
                    </div>
                </li>
                <li class="g3 sml">
                    <div class="hd">询价</div>
                    <div class="bd">
                        <p>在首页进行快速询价；</p>
                        <p>在商品页面进行询价；</p>
                        <p>用户中心的询价模块进行询价。</p>
                    </div>
                </li>
                <li class="g4">
                    <div class="hd">一键下单</div>
                    <div class="bd">
                        <p>用户中心由报价单生成订单；</p>
                        <p>填写收货地址及备注说明；</p>
                        <p>确认信息无误后提交订单。</p>
                    </div>
                </li>
                <li class="g5 sml">
                    <div class="hd">付款</div>
                    <div class="bd">
                        <p>根据对账单或发票金额进行付款；</p>
                        <p>查询付款明细；</p>
                        <p>查看应付余额。</p>
                    </div>
                </li>
                <li class="g6">
                    <div class="hd">验货签收</div>
                    <div class="bd">
                        <p>查看货物签收单；</p>
                        <p>确认收货；</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <#include "./../inc/helper.ftl"/>

    <!-- footer start -->
    <#include "./../inc/footer.ftl"/>
    <!-- footer end -->

    
    <script>
    var _global = {
        fn: {
            init: function() {
                this.pop();
            },
            pop: function() {
                $('.floor3').on({
                    'mouseenter': function() {
                        $(this).find('.bd').stop().fadeIn(300);
                    },
                    'mouseleave': function() {
                        $(this).find('.bd').stop().fadeOut(300);

                    }
                }, 'li');
            }
        }
    }
    $(function() {
        _global.fn.init();
    })
    </script>

</body>
</html>