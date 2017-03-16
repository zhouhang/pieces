<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>报价单-上工好药</title>
    <style>
        *{-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;}
        html{font-size:62.5%;font-family:'Microsoft Yahei',sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;-webkit-tap-highlight-color:rgba(0, 0, 0, 0);}
        body{margin:0;font-size:1.4rem;background:#fff;color:#333;}
        em{font-size:1rem;font-style:normal;}
        .qoute{padding:0 12px;}
        .title{padding:30px 0;font-size:2.5rem;font-weight:700;text-align:center;}
        .table{border-left:1px solid #ccc;border-bottom:1px solid #ccc;text-align:center;}
        .tr{height:35px;line-height:35px;overflow:hidden;}
        .tr:nth-child(odd){background:#f8f8f8;}
        .th{height:40px;line-height:40px;font-weight:700;background:#e7e7e7!important;}
        .td{float:left;padding:0 5px;border-top:1px solid #ccc;border-right:1px solid #ccc;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
        .w1{width:18%;}
        .w2{width:14%;}
        .w3{width:36%;}
        .w4{width:14%;}
        .w5{width:18%;}
        .tips{line-height:26px;padding:0 0 30px;}
        .tips p{margin:22px 0 15px;font-weight:700;}
        .tips span{margin:0 5px;color:#5c92cf;}
        ul{margin:0;padding-left:18px;list-style:decimal;}
        @media (max-width:414px) {
            .title{padding:40px 0;}
            .w3,.w4{display:none;}
            .w1{width:33%;}
            .w2{width:33%;}
            .w5{width:34%;}
        }
    </style>
</head>

<body>
    <section class="qoute">
        <div class="title">报价单</div>
        <div class="table">
            <div class="tr th">
                <div class="td w1">商品名称</div>
                <div class="td w2">片型</div>
                <div class="td w3">规格等级</div>
                <div class="td w4">产地</div>
                <div class="td w5">单价<em>（元/公斤）</em></div>
            </div>
        <#list list as commodity>
            <div class="tr">
                <div class="td w1">${commodity.commodityName!}</div>
                <div class="td w2">${commodity.specs!}</div>
                <div class="td w3">${commodity.level!}</div>
                <div class="td w4">${commodity.origin!}</div>
                <div class="td w5"><span>&yen; ${commodity.price?default(commodity.myPrice!)}</span></div>
            </div>
        </#list>
        </div>
        <#list list as commodity>
        <#if commodity_index ==1>
        <div class="tips">
            <div>
                * 旋转手机屏幕可以查看更多信息；<br>
                * 本次报价仅于 ${commodity.expireDate?date}前有效。
            </div>
        </div>
        </#if>
        </#list>
    </section>
</body>
</html>
